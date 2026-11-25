package com.dataman.base.report.html;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.exception.DmException;
import com.dataman.base.report.AmountFormatter;
import com.dataman.base.report.AmountInWords;
import com.dataman.base.report.LocaleSettings;
import com.dataman.base.report.ReportProperties;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

class HtmlReportServiceTest {

    private HtmlReportService service() {
        ReportProperties p = new ReportProperties();
        p.setDir("src/test/resources/reports");
        LocaleSettings ls = new LocaleSettings("IND");
        return new HtmlReportService(p, new AmountFormatter(ls), new AmountInWords(ls));
    }

    private Map<String, Object> model() {
        return Map.of("title", "Item <List>", "filter", "Party: A & B",
                "rows", List.of(Map.of("item", "Paper", "qty", new BigDecimal("12.5"), "amount", new BigDecimal("1500")),
                        Map.of("item", "<b>Board</b>", "qty", 3, "amount", new BigDecimal("250.5"))),
                "total", new BigDecimal("1750.5"));
    }

    @Test
    void rendersEscapedValuesAndHelpers() {
        String html = service().render("DemoInvoice", "system", model());
        assertTrue(html.contains("Item &lt;List&gt;"));
        assertTrue(html.contains("Party: A &amp; B"));
        assertTrue(html.contains("&lt;b&gt;Board&lt;/b&gt;"), "user data must be escaped");
        assertTrue(html.contains(">12.5<"));
        assertTrue(html.contains("1500.00"));
        assertTrue(html.contains("Rupee"), html);
    }

    @Test
    void pdfContainsTheText() throws Exception {
        byte[] pdf = service().renderPdf("DemoInvoice", "system", model());
        assertTrue(new String(pdf, 0, 5, StandardCharsets.ISO_8859_1).startsWith("%PDF-"));
        try (PDDocument doc = Loader.loadPDF(pdf)) {
            String text = new PDFTextStripper().getText(doc);
            assertTrue(text.contains("Paper"), text);
            assertTrue(text.contains("Page 1 of 1"), text);
        }
    }

    @Test
    void customerVersionWinsOnlyForSystemFolder() {
        assertTrue(service().render("Both", "system", Map.of()).contains("customer version"));
    }

    @Test
    void badOrMissingNamesAreRejected() {
        HtmlReportService s = service();
        assertThrows(DmException.class, () -> s.render("../DemoInvoice", "system", Map.of()));
        assertEquals("Report file not found: Nope",
                assertThrows(DmException.class, () -> s.render("Nope", "system", Map.of())).getMessage());
    }
}
