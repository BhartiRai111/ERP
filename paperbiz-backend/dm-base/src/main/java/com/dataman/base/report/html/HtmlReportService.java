package com.dataman.base.report.html;

import com.dataman.base.exception.DmException;
import com.dataman.base.report.AmountFormatter;
import com.dataman.base.report.AmountInWords;
import com.dataman.base.report.ReportProperties;
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.FileTemplateResolver;

/**
 * Reports as HTML templates (the main way of printing): a Thymeleaf template + data gives HTML, which can be
 * shown as is or turned into PDF. Template look-up is the same as the old reports: for folder "system" a
 * {@code systemCustomized/<name>.html} (a customer's own version) wins over {@code system/<name>.html}; any
 * other folder ("osr") is used as is. Page size, margins, repeating header/footer and page numbers are plain
 * CSS ({@code @page}); see docs/modules/dm-base/report-templates.md.
 *
 * <p>Model values are escaped by default ({@code th:text}); {@code ${dm}} gives amount/quantity/words/date helpers.
 */
@Service
public class HtmlReportService {

    private static final Pattern NAME = Pattern.compile("[A-Za-z0-9_\\- ]+");

    private final ReportProperties props;
    private final TemplateEngine engine;
    private final DmReportHelper helper;
    private volatile java.util.List<FontFile> fonts;

    public HtmlReportService(ReportProperties props, AmountFormatter amounts, AmountInWords words) {
        this.props = props;
        this.helper = new DmReportHelper(amounts, words);
        FileTemplateResolver resolver = new FileTemplateResolver();
        resolver.setPrefix(Path.of(props.getDir()).toAbsolutePath() + File.separator);
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setCacheable(props.isTemplateCache());
        this.engine = new SpringTemplateEngine();
        this.engine.setTemplateResolver(resolver);
    }

    /** The finished HTML. */
    public String render(String reportName, String subDirectory, Map<String, Object> model) {
        Context ctx = new Context(Locale.ENGLISH);
        Map<String, Object> vars = new HashMap<>(model == null ? Map.of() : model);
        vars.put("dm", helper);
        ctx.setVariables(vars);
        try {
            return engine.process(locate(reportName, subDirectory), ctx);
        } catch (org.thymeleaf.exceptions.TemplateInputException e) {
            throw new DmException(0, "Report template not found: " + reportName);
        } catch (DmException e) {
            throw e;
        } catch (RuntimeException e) {
            throw new DmException(0, "Report template error: " + e.getMessage());
        }
    }

    /** HTML to PDF (PDF/A-less, embedded fonts from {@code dataman.reports.fonts-dir}). */
    public byte[] toPdf(String html) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            org.w3c.dom.Document dom = new W3CDom().fromJsoup(Jsoup.parse(html));
            PdfRendererBuilder b = new PdfRendererBuilder();
            b.useFastMode();
            b.withW3cDocument(dom, Path.of(props.getDir()).toAbsolutePath().toUri().toString());
            for (FontFile f : fonts()) {
                b.useFont(f.file(), f.family(), f.weight(), f.style(), true);
            }
            b.toStream(out);
            b.run();
            return out.toByteArray();
        } catch (IOException e) {
            throw new DmException(0, e.getMessage());
        } catch (RuntimeException e) {
            throw new DmException(0, "PDF error: " + e.getMessage());
        }
    }

    public byte[] renderPdf(String reportName, String subDirectory, Map<String, Object> model) {
        return toPdf(render(reportName, subDirectory, model));
    }

    /** Template name relative to the reports folder, e.g. {@code systemCustomized/AC_Ledger}. */
    String locate(String reportName, String subDirectory) {
        String name = reportName == null ? "" : reportName.trim();
        String sub = subDirectory == null || subDirectory.isBlank() ? "system" : subDirectory.trim();
        if (!NAME.matcher(name).matches() || !NAME.matcher(sub).matches()) {
            throw new DmException(0, "Invalid report name.");
        }
        Path base = Path.of(props.getDir());
        if (sub.equalsIgnoreCase("system") && Files.isRegularFile(base.resolve("systemCustomized").resolve(name + ".html"))) {
            return "systemCustomized/" + name;
        }
        if (!Files.isRegularFile(base.resolve(sub).resolve(name + ".html"))) {
            throw new DmException(0, "Report file not found: " + name);
        }
        return sub + "/" + name;
    }

    private record FontFile(File file, String family, int weight,
            BaseRendererBuilder.FontStyle style) {
    }

    private java.util.List<FontFile> fonts() {
        java.util.List<FontFile> local = fonts;
        if (local == null) {
            local = loadFonts();
            fonts = local;
        }
        return local;
    }

    private java.util.List<FontFile> loadFonts() {
        java.util.List<FontFile> list = new java.util.ArrayList<>();
        String dir = props.getFontsDir();
        if (dir == null || dir.isBlank() || !Files.isDirectory(Path.of(dir))) {
            return list;
        }
        try (Stream<Path> files = Files.list(Path.of(dir))) {
            for (Path p : files.filter(x -> x.toString().toLowerCase().matches(".*\\.(ttf|otf)$")).toList()) {
                try {
                    Font font = Font.createFont(Font.TRUETYPE_FONT, p.toFile());
                    String n = font.getFontName().toLowerCase();
                    list.add(new FontFile(p.toFile(), font.getFamily(), n.contains("bold") ? 700 : 400,
                            n.contains("italic") || n.contains("oblique") ? BaseRendererBuilder.FontStyle.ITALIC
                                    : BaseRendererBuilder.FontStyle.NORMAL));
                } catch (Exception ignored) {
                    // not a usable font file: skipped
                }
            }
        } catch (IOException ignored) {
            // unreadable folder: built-in fonts only
        }
        return list;
    }
}
