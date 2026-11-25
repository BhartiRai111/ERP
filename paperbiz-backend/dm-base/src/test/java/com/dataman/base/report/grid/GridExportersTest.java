package com.dataman.base.report.grid;

import com.dataman.base.report.ReportProperties;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.report.AmountFormatter;
import com.dataman.base.report.AmountInWords;
import com.dataman.base.report.LocaleSettings;
import com.dataman.base.report.html.HtmlReportService;
import com.dataman.base.report.jasper.JasperGridPdfExporter;
import com.dataman.base.report.jasper.ReportCompiler;
import com.dataman.base.report.ReportProperties;
import com.dataman.base.report.jasper.ReportService;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class GridExportersTest {

    private final GridReport report = new GridReport("Item List", "Active items",
            List.of(new GridColumn("Item", "ITEMNAME", 150), new GridColumn("Rate", "RATE", 80),
                    new GridColumn("Qty", "QTY", 60), new GridColumn("Missing", "NOPE", 50)),
            PageSetup.A4_LANDSCAPE, List.of("Dataman (2026-27)", "Head Office"), "Total: 2", true);

    @BeforeAll
    static void headless() {
        System.setProperty("java.awt.headless", "true");
    }

    private ResultSet data(Connection cn) throws Exception {
        try (Statement st = cn.createStatement()) {
            st.execute("create table t(itemName varchar(50), rate decimal(10,2), qty int)");
            st.execute("insert into t values ('Paper', 12.5, 3), ('Board', 7, 10)");
        }
        return cn.createStatement().executeQuery("select * from t");
    }

    @Test
    void excelHasHeaderHeadingsAndOnlyKnownColumns() throws Exception {
        try (Connection cn = DriverManager.getConnection("jdbc:h2:mem:x1")) {
            byte[] xlsx = new GridExcelExporter().export(report, data(cn));
            try (XSSFWorkbook wb = new XSSFWorkbook(new ByteArrayInputStream(xlsx))) {
                XSSFSheet s = wb.getSheet("Sheet1");
                assertEquals("Item List", s.getRow(0).getCell(0).getStringCellValue());
                assertEquals("Head Office", s.getRow(2).getCell(0).getStringCellValue());
                int head = 5;   // title + 2 lines + 2 empty rows
                assertEquals("Item", s.getRow(head).getCell(0).getStringCellValue());
                assertEquals(3, s.getRow(head).getLastCellNum());   // "Missing" is skipped
                assertEquals("Paper", s.getRow(head + 1).getCell(0).getStringCellValue());
                assertEquals(12.5, s.getRow(head + 1).getCell(1).getNumericCellValue());
                assertEquals(10.0, s.getRow(head + 2).getCell(2).getNumericCellValue());
                assertEquals(150 * 40, s.getColumnWidth(0));
            }
        }
    }

    @Test
    void htmlPdfHasTitleHeadingsRowsAndFooter() throws Exception {
        LocaleSettings ls = new LocaleSettings("IND");
        HtmlReportService svc = new HtmlReportService(new ReportProperties(), new AmountFormatter(ls), new AmountInWords(ls));
        try (Connection cn = DriverManager.getConnection("jdbc:h2:mem:x3")) {
            byte[] pdf = new HtmlGridPdfExporter(svc).export(report, data(cn));
            try (org.apache.pdfbox.pdmodel.PDDocument doc = org.apache.pdfbox.Loader.loadPDF(pdf)) {
                String text = new org.apache.pdfbox.text.PDFTextStripper().getText(doc);
                assertTrue(text.contains("Item List") && text.contains("Active items"), text);
                assertTrue(text.contains("Paper") && text.contains("12.50") && text.contains("Total: 2"), text);
                assertTrue(text.contains("Page 1 of 1"), text);
                assertTrue(!text.contains("Missing"), "unknown columns are skipped");
            }
        }
    }

    @Test
    void pdfIsProduced() throws Exception {
        ReportProperties p = new ReportProperties();
        ReportService service = new ReportService(new ReportCompiler(p));
        try (Connection cn = DriverManager.getConnection("jdbc:h2:mem:x2")) {
            byte[] pdf = new JasperGridPdfExporter(service).export(report, data(cn));
            assertTrue(new String(pdf, 0, 8, StandardCharsets.ISO_8859_1).startsWith("%PDF-1.7"));
            assertTrue(pdf.length > 1000);
        }
    }
}
