package com.dataman.base.file;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.exception.DmException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FileUtilsTest {

    @TempDir
    Path tmp;

    @Test
    void lineWriterAppends() throws IOException {
        Path f = tmp.resolve("sub/out.csv");
        try (LineFileWriter w = new LineFileWriter(f)) {
            w.writeLine("a,b");
        }
        try (LineFileWriter w = new LineFileWriter(f)) {
            w.writeLine("c,d");
        }
        assertEquals(List.of("a,b", "c,d"), Files.readAllLines(f));
    }

    @Test
    void zipContainsRelativeEntries() throws IOException {
        Path src = Files.createDirectories(tmp.resolve("src/inner"));
        Files.writeString(src.resolve("x.txt"), "x");
        Files.writeString(tmp.resolve("src/y.txt"), "y");
        Path zip = tmp.resolve("o.zip");
        ZipFolder.zip(tmp.resolve("src"), zip);
        try (ZipFile z = new ZipFile(zip.toFile())) {
            assertTrue(z.getEntry("y.txt") != null);
            assertTrue(z.getEntry("inner/x.txt") != null);
        }
    }

    @Test
    void csvHeadersAndLowerCaseKeys() throws IOException {
        Path f = tmp.resolve("in.csv");
        Files.writeString(f, "Unit   Name , Decimal Places\n\"Kg\",\"2\"\nPcs,0\n");
        CsvFileReader.CsvContent c = CsvFileReader.readAll(f, false);
        assertEquals(List.of("Unit Name", "Decimal Places"), c.headers());
        assertEquals(2, c.rows().size());
        assertEquals("Kg", c.rows().get(0).get("unit name"));
        assertEquals("0", c.rows().get(1).get("decimal places"));
    }

    @Test
    void csvStopsWhenHandlerReturnsFalseAndRejectsMissingFile() throws IOException {
        Path f = tmp.resolve("in2.csv");
        Files.writeString(f, "a\n1\n2\n3\n");
        int[] seen = {0};
        CsvFileReader.read(f, false, new CsvFileReader.RowHandler() {
            public boolean onHeaders(List<String> h) {
                return true;
            }

            public boolean onRow(int n, List<String> h, java.util.Map<String, String> r) {
                seen[0]++;
                return n < 2;
            }
        });
        assertEquals(2, seen[0]);
        assertThrows(DmException.class, () -> CsvFileReader.readAll(tmp.resolve("nope.csv"), false));
    }

    @Test
    void xlsWriterWritesSheets() throws Exception {
        try (XlsWriter w = new XlsWriter(tmp, "r.xlsx", "Main")) {
            w.createSheet("Second").createRow(0).createCell(0).setCellValue("hi");
        }
        try (XSSFWorkbook wb = new XSSFWorkbook(Files.newInputStream(tmp.resolve("r.xlsx")))) {
            assertEquals(2, wb.getNumberOfSheets());
            assertEquals("hi", wb.getSheet("Second").getRow(0).getCell(0).getStringCellValue());
        }
        assertThrows(DmException.class, () -> new XlsWriter(tmp, " ", "Main"));
    }
}
