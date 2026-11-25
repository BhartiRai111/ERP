package com.dataman.base.report.jasper;

import com.dataman.base.report.ReportProperties;
import com.dataman.base.exception.DmException;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.springframework.stereotype.Service;

/**
 * Finds and compiles report sources (legacy compileJasper). Look-up order for sub directory "system":
 * {@code systemCustomized/<name>.jrxml} first (a customer's own version), then {@code system/<name>.jrxml};
 * any other sub directory ("osr") is used as is. Compiled reports are cached in memory and rebuilt when the
 * .jrxml changes (legacy kept .jasper files on disk and never refreshed them). Sub reports named in the
 * report ({@code "name.jasper"}) are compiled too, into the cache folder that becomes SUBREPORT_DIR.
 */
@Service
@org.springframework.boot.autoconfigure.condition.ConditionalOnClass(name = "net.sf.jasperreports.engine.JasperReport")
public class ReportCompiler {

    private static final Pattern SUBREPORT_FILE = Pattern.compile("\"([A-Za-z0-9_\\-]+)\\.jasper\"");
    private static final Pattern REPORT_NAME = Pattern.compile("[A-Za-z0-9_\\- ]+");

    private final ReportProperties props;
    private final Map<String, Cached> cache = new ConcurrentHashMap<>();

    public ReportCompiler(ReportProperties props) {
        this.props = props;
    }

    private record Cached(long modified, CompiledReport report) {
    }

    /**
     * @param fontOverrides style name -> font name; the style's font is replaced (legacy DmJasperStyle,
     *                      used to print regional languages)
     */
    public CompiledReport compile(String reportName, String subDirectory, Map<String, String> fontOverrides) {
        Path source = locate(reportName, subDirectory);
        String key = source + "|" + (fontOverrides == null ? "" : new java.util.TreeMap<>(fontOverrides));
        long modified = source.toFile().lastModified();
        Cached c = cache.get(key);
        if (c != null && c.modified() == modified) {
            return c.report();
        }
        try {
            Path out = cacheFolder().resolve(source.getParent().getFileName().toString());
            Files.createDirectories(out);
            JasperReport report = compileFile(source, out, fontOverrides);
            CompiledReport result = new CompiledReport(report, out);
            cache.put(key, new Cached(modified, result));
            return result;
        } catch (JRException | IOException e) {
            throw new DmException(0, e.getMessage());
        }
    }

    public CompiledReport compile(String reportName, String subDirectory) {
        return compile(reportName, subDirectory, null);
    }

    Path locate(String reportName, String subDirectory) {
        String name = reportName == null ? "" : reportName.trim();
        if (!REPORT_NAME.matcher(name).matches()) {
            throw new DmException(0, "Invalid report name.");
        }
        String sub = subDirectory == null || subDirectory.isBlank() ? "system" : subDirectory.trim();
        if (!REPORT_NAME.matcher(sub).matches()) {
            throw new DmException(0, "Invalid report folder.");
        }
        Path base = Path.of(props.getDir());
        if (sub.equalsIgnoreCase("system")) {
            Path custom = base.resolve("systemCustomized").resolve(name + ".jrxml");
            if (Files.isRegularFile(custom)) {
                return custom;
            }
        }
        Path file = base.resolve(sub).resolve(name + ".jrxml");
        if (!Files.isRegularFile(file)) {
            throw new DmException(0, "Report file not found: " + name);
        }
        return file;
    }

    private JasperReport compileFile(Path jrxml, Path outDir, Map<String, String> fontOverrides)
            throws JRException, IOException {
        for (String sub : subReportNames(jrxml)) {
            Path subSource = jrxml.getParent().resolve(sub + ".jrxml");
            if (Files.isRegularFile(subSource) && !subSource.equals(jrxml)) {
                JasperReport compiled = compileFile(subSource, outDir, fontOverrides);
                net.sf.jasperreports.engine.util.JRSaver.saveObject(compiled, new File(outDir.toFile(), sub + ".jasper"));
            }
        }
        JasperDesign design = JRXmlLoader.load(jrxml.toFile());
        String scriptlet = design.getScriptletClass();
        if (scriptlet != null && props.getScriptletClasses().containsKey(scriptlet)) {
            design.setScriptletClass(props.getScriptletClasses().get(scriptlet));
        }
        if (fontOverrides != null) {
            fontOverrides.forEach((style, font) -> {
                if (design.getStylesMap().get(style) instanceof JRDesignStyle s) {
                    s.setFontName(font);
                }
            });
        }
        return JasperCompileManager.compileReport(design);
    }

    private static java.util.Set<String> subReportNames(Path jrxml) throws IOException {
        Matcher m = SUBREPORT_FILE.matcher(Files.readString(jrxml, StandardCharsets.UTF_8));
        java.util.Set<String> names = new java.util.LinkedHashSet<>();
        while (m.find()) {
            names.add(m.group(1));
        }
        return names;
    }

    private Path cacheFolder() throws IOException {
        Path p = props.getCacheDir() == null || props.getCacheDir().isBlank()
                ? Path.of(System.getProperty("java.io.tmpdir"), "paperbiz-reports")
                : Path.of(props.getCacheDir());
        return Files.createDirectories(p);
    }
}
