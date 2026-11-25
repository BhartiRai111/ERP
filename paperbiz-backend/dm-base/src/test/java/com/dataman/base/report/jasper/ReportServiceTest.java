package com.dataman.base.report.jasper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.db.FieldUdt;
import com.dataman.base.enums.DmExportType;
import com.dataman.base.exception.DmException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JasperPrint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ReportServiceTest {

    @BeforeAll
    static void headless() {
        System.setProperty("java.awt.headless", "true");
    }

    public record Row(String name, Double amount) {
        public String getName() { return name; }
        public Double getAmount() { return amount; }
    }

    private ReportService service(java.nio.file.Path cache) {
        ReportProperties p = new ReportProperties();
        p.setDir("src/test/resources/reports");
        p.setCacheDir(cache.toString());
        return new ReportService(new ReportCompiler(p));
    }

    @Test
    void fillsFromListWithSubReportAndExportsAllFormats(@TempDir java.nio.file.Path cache) {
        ReportService s = service(cache);
        CompiledReport c = s.compiler().compile("demo", "system", Map.of("regionalLanguageStyle", "DejaVu Sans"));
        Map<String, FieldUdt> filter = new LinkedHashMap<>();
        filter.put("a", new FieldUdt("Party", "ABC", "x"));
        filter.put("b", new FieldUdt("Hidden", "no", "x", "1"));
        JasperPrint print = s.fill("Demo", c, null, filter, List.of(new Row("Paper", 12.5), new Row("Board", 7.0)));
        assertEquals("Demo", print.getPropertiesMap().getProperty("dmTitle"));

        byte[] pdf = s.export(print, DmExportType.PDF, "demo.pdf");
        assertTrue(new String(pdf, 0, 8, StandardCharsets.ISO_8859_1).startsWith("%PDF-1.7"));
        byte[] xlsx = s.export(print, DmExportType.EXCEL, "demo");
        byte[] docx = s.export(print, DmExportType.WORD, "demo");
        assertEquals('P', xlsx[0]);
        assertEquals('P', docx[0]);
    }

    @Test
    void noRowsAndBadNamesAreRejected(@TempDir java.nio.file.Path cache) {
        ReportService s = service(cache);
        CompiledReport c = s.compiler().compile("demo", "system");
        DmException e = assertThrows(DmException.class, () -> s.fill("t", c, null, null, List.<Row>of()));
        assertEquals("No record found!", e.getMessage());
        assertThrows(DmException.class, () -> s.compiler().compile("../demo", "system"));
        assertThrows(DmException.class, () -> s.compiler().compile("missing", "system"));
    }

    @Test
    void filterTextSkipsBlankCaptionAndHiddenTag() {
        Map<String, FieldUdt> f = new LinkedHashMap<>();
        f.put("a", new FieldUdt("Party", "ABC", null));
        f.put("b", new FieldUdt("", "x", null));
        f.put("c", new FieldUdt("Hidden", "y", null, "1"));
        assertEquals("<b><u>Party:</b></u> ABC; ", ReportService.filterText(f));
    }
}
