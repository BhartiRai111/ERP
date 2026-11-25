package com.dataman.base.report.jasper;

import com.dataman.base.db.FieldUdt;
import com.dataman.base.enums.DmExportType;
import com.dataman.base.exception.DmException;
import com.dataman.base.util.Strings;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.pdf.JRPdfExporter;
import net.sf.jasperreports.pdf.SimplePdfExporterConfiguration;
import net.sf.jasperreports.pdf.type.PdfVersionEnum;
import org.springframework.stereotype.Service;

/**
 * Fills reports and exports them (legacy fillJasper / setJasperVariables / showJasper). A report is filled
 * either from a result set / list given by the caller, or by the report itself through the connection it is
 * handed (REPORT_CONNECTION, with the filter table as the {@code fieldUDT} parameter).
 */
@Service
@org.springframework.boot.autoconfigure.condition.ConditionalOnClass(name = "net.sf.jasperreports.engine.JasperReport")
public class ReportService {

    private static final DateTimeFormatter CURRENT_DATE_TIME =
            DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm", Locale.ENGLISH);

    private final ReportCompiler compiler;

    public ReportService(ReportCompiler compiler) {
        this.compiler = compiler;
    }

    /** Fill from a result set (a report procedure's output). No rows: "No record found!". */
    public JasperPrint fill(String title, CompiledReport compiled, Map<String, Object> parameters,
            Map<String, FieldUdt> filter, ResultSet rows) {
        try {
            if (!rows.isBeforeFirst()) {
                throw new DmException(0, "No record found!");
            }
            Map<String, Object> p = variables(title, compiled, filter, parameters);
            return custom(compiled.report(), JasperFillManager.fillReport(compiled.report(), p, new JRResultSetDataSource(rows)));
        } catch (SQLException | JRException e) {
            throw new DmException(0, e.getMessage());
        }
    }

    /** Fill from a list of beans. Empty list: "No record found!". */
    public <T> JasperPrint fill(String title, CompiledReport compiled, Map<String, Object> parameters,
            Map<String, FieldUdt> filter, List<T> rows) {
        if (rows == null || rows.isEmpty()) {
            throw new DmException(0, "No record found!");
        }
        try {
            Map<String, Object> p = variables(title, compiled, filter, parameters);
            return custom(compiled.report(), JasperFillManager.fillReport(compiled.report(), p, new JRBeanCollectionDataSource(rows, false)));
        } catch (JRException e) {
            throw new DmException(0, e.getMessage());
        }
    }

    /** The report runs its own queries on {@code connection} (parameters REPORT_CONNECTION and fieldUDT). */
    public JasperPrint fill(String title, CompiledReport compiled, Map<String, Object> parameters,
            Map<String, FieldUdt> filter, Connection connection) {
        try {
            Map<String, Object> p = variables(title, compiled, filter, parameters);
            p.put("REPORT_CONNECTION", connection);
            p.put("fieldUDT", FieldUdt.toTable(filter));
            return custom(compiled.report(), JasperFillManager.fillReport(compiled.report(), p, connection));
        } catch (SQLException | JRException e) {
            throw new DmException(0, e.getMessage());
        }
    }

    /** Parameters every report may declare: dmCurrentDateTime, SUBREPORT_DIR, dmFrmRptFormulaFieldDisplay. */
    Map<String, Object> variables(String title, CompiledReport compiled, Map<String, FieldUdt> filter,
            Map<String, Object> given) {
        Map<String, Object> p = given == null ? new HashMap<>() : new HashMap<>(given);
        for (JRParameter jr : compiled.report().getParameters()) {
            String name = jr.getName();
            if (name.equalsIgnoreCase("dmCurrentDateTime")) {
                p.put(name, CURRENT_DATE_TIME.format(LocalDateTime.now()));
            } else if (name.equalsIgnoreCase("SUBREPORT_DIR")) {
                p.put(name, compiled.subReportDir().toString() + java.io.File.separator);
            } else if (name.equalsIgnoreCase("dmFrmRptFormulaFieldDisplay")) {
                p.put(name, filterText(filter));
            }
        }
        return p;
    }

    static String filterText(Map<String, FieldUdt> filter) {
        StringBuilder sb = new StringBuilder();
        if (filter != null) {
            for (FieldUdt f : filter.values()) {
                if (f != null && !Strings.xNull(f.caption()).isEmpty() && !Strings.xNull(f.tag2nd()).equals("1")) {
                    sb.append("<b><u>").append(f.caption()).append(":</b></u> ").append(Strings.xNull(f.text())).append("; ");
                }
            }
        }
        return sb.toString();
    }

    /** Report properties starting with "dm" are copied to the print (legacy setDmCustomJasperProperties). */
    private static JasperPrint custom(JasperReport report, JasperPrint print) {
        for (String name : report.getPropertiesMap().getPropertyNames()) {
            if (name.trim().startsWith("dm")) {
                print.getPropertiesMap().setProperty(name.trim(), Strings.xNull(report.getPropertiesMap().getProperty(name)).trim());
            }
        }
        return print;
    }

    /** The exported file's bytes (PDF 1.7 compressed, XLSX or DOCX). */
    public byte[] export(JasperPrint print, DmExportType type, String documentTitle) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            SimpleExporterInput in = new SimpleExporterInput(print);
            SimpleOutputStreamExporterOutput target = new SimpleOutputStreamExporterOutput(out);
            if (type == DmExportType.EXCEL) {
                JRXlsxExporter x = new JRXlsxExporter();
                x.setExporterInput(in);
                x.setExporterOutput(target);
                x.exportReport();
            } else if (type == DmExportType.WORD) {
                JRDocxExporter d = new JRDocxExporter();
                d.setExporterInput(in);
                d.setExporterOutput(target);
                d.exportReport();
            } else {
                JRPdfExporter pdf = new JRPdfExporter();
                pdf.setExporterInput(in);
                pdf.setExporterOutput(target);
                SimplePdfExporterConfiguration cfg = new SimplePdfExporterConfiguration();
                cfg.setCompressed(Boolean.TRUE);
                cfg.setPdfVersion(PdfVersionEnum.VERSION_1_7);
                cfg.setDisplayMetadataTitle(Boolean.TRUE);
                cfg.setMetadataTitle(documentTitle);
                pdf.setConfiguration(cfg);
                pdf.exportReport();
            }
            return out.toByteArray();
        } catch (JRException e) {
            throw new DmException(0, e.getMessage());
        }
    }

    public ReportCompiler compiler() {
        return compiler;
    }
}
