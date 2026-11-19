package com.dataman.base.file;

import com.dataman.base.exception.DmException;
import com.dataman.base.util.Strings;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Thin Apache POI helper (legacy DmXlsWriter): a workbook with named sheets plus default cell
 * style/font, written to {@code directory/fileName} on {@link #close()}.
 */
public final class XlsWriter implements AutoCloseable {

    private final Path directory;
    private final String fileName;
    private final Map<String, XSSFSheet> sheets = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
    private XSSFWorkbook workbook = new XSSFWorkbook();

    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    private VerticalAlignment verticalAlignment = VerticalAlignment.CENTER;
    private IndexedColors fontColor = IndexedColors.BLACK;
    private final String fontName = "Calibri";
    private final short fontSize = 11;

    public XlsWriter(Path directory, String fileName, String defaultSheetName) {
        if (directory == null) {
            throw new DmException("File directory path can't be empty.");
        }
        if (Strings.xNull(fileName).trim().isEmpty()) {
            throw new DmException("File name can't be empty.");
        }
        this.directory = directory;
        this.fileName = fileName.trim();
        createSheet(defaultSheetName);
    }

    public XSSFWorkbook workbook() {
        return workbook;
    }

    public void setHorizontalAlignment(HorizontalAlignment value) {
        this.horizontalAlignment = value;
    }

    public void setVerticalAlignment(VerticalAlignment value) {
        this.verticalAlignment = value;
    }

    public void setFontColor(IndexedColors value) {
        this.fontColor = value;
    }

    /** Returns the sheet, creating it when new. Excel limits sheet names to 30 characters here. */
    public XSSFSheet createSheet(String name) {
        String n = Strings.xNull(name).trim();
        if (n.isEmpty()) {
            throw new DmException("Worksheet Name is mandatory to create.");
        }
        if (n.length() >= 31) {
            throw new DmException("Name can't be exceeded from 30 characters.");
        }
        return sheets.computeIfAbsent(n, workbook::createSheet);
    }

    public void removeSheet(String name) {
        String n = Strings.xNull(name).trim();
        XSSFSheet sheet = sheets.remove(n);
        if (sheet != null) {
            workbook.removeSheetAt(workbook.getSheetIndex(sheet));
        }
    }

    public XSSFCellStyle newCellStyle() {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(horizontalAlignment);
        style.setVerticalAlignment(verticalAlignment);
        return style;
    }

    public XSSFFont newFont() {
        XSSFFont font = workbook.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints(fontSize);
        font.setColor(fontColor.getIndex());
        return font;
    }

    /** Writes the file (replacing an existing one) and closes the workbook. */
    @Override
    public void close() throws IOException {
        if (workbook == null) {
            return;
        }
        Files.createDirectories(directory);
        Path target = directory.resolve(fileName);
        Files.deleteIfExists(target);
        try (OutputStream out = Files.newOutputStream(target)) {
            workbook.write(out);
        } finally {
            workbook.close();
            workbook = null;
        }
    }
}
