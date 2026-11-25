package com.dataman.base.report.grid;

import com.dataman.base.exception.DmException;
import com.dataman.base.util.DateUtil;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

/**
 * List to Excel (legacy RptFind.downloadExcel): report header lines, then a shaded heading row and the data
 * rows. Only the columns of the report that exist in the result set are written, in the report's order;
 * numbers are right aligned (decimals "#.00"), widths are width x 40 (1/256 characters).
 */
@Service
public class GridExcelExporter {

    public byte[] export(GridReport report, ResultSet rows) {
        try (XSSFWorkbook wb = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            XSSFSheet sheet = wb.createSheet("Sheet1");
            int rowIndex = header(wb, sheet, report);
            ResultSetMetaData md = rows.getMetaData();
            List<int[]> mapped = mapColumns(report, md);   // {report column index, result set column index}

            XSSFCellStyle headLeft = headStyle(wb, HorizontalAlignment.LEFT);
            XSSFCellStyle headRight = headStyle(wb, HorizontalAlignment.RIGHT);
            XSSFCellStyle number = wb.createCellStyle();
            number.setAlignment(HorizontalAlignment.RIGHT);
            number.setDataFormat(wb.createDataFormat().getFormat("#.00"));

            XSSFRow head = sheet.createRow(rowIndex++);
            for (int c = 0; c < mapped.size(); c++) {
                GridColumn col = report.columns().get(mapped.get(c)[0]);
                XSSFCell cell = head.createCell(c);
                cell.setCellValue(col.caption());
                cell.setCellStyle(isNumeric(md.getColumnType(mapped.get(c)[1])) ? headRight : headLeft);
                sheet.setColumnWidth(c, col.width() * 40);
            }
            while (rows.next()) {
                XSSFRow row = sheet.createRow(rowIndex++);
                for (int c = 0; c < mapped.size(); c++) {
                    int idx = mapped.get(c)[1];
                    int type = md.getColumnType(idx);
                    XSSFCell cell = row.createCell(c);
                    switch (type) {
                        case Types.DATE -> cell.setCellValue(rows.getDate(idx) == null ? "" : DateUtil.display(rows.getDate(idx)));
                        case Types.INTEGER, Types.SMALLINT, Types.TINYINT, Types.BIGINT -> cell.setCellValue(rows.getLong(idx));
                        case Types.FLOAT, Types.DECIMAL, Types.DOUBLE, Types.NUMERIC, Types.REAL -> cell.setCellValue(rows.getDouble(idx));
                        default -> cell.setCellValue(rows.getString(idx));
                    }
                    if (isNumeric(type)) {
                        cell.setCellStyle(number);
                    }
                }
            }
            wb.write(out);
            return out.toByteArray();
        } catch (SQLException | IOException e) {
            throw new DmException(0, e.getMessage());
        }
    }

    private static List<int[]> mapColumns(GridReport report, ResultSetMetaData md) throws SQLException {
        List<int[]> mapped = new ArrayList<>();
        for (int r = 0; r < report.columns().size(); r++) {
            String field = report.columns().get(r).dbField();
            for (int i = 1; i <= md.getColumnCount(); i++) {
                if (field != null && field.equalsIgnoreCase(md.getColumnName(i))) {
                    mapped.add(new int[] {r, i});
                    break;
                }
            }
        }
        return mapped;
    }

    private static int header(XSSFWorkbook wb, XSSFSheet sheet, GridReport report) {
        int r = 0;
        XSSFFont bold = wb.createFont();
        bold.setBold(true);
        bold.setColor(IndexedColors.BLACK.getIndex());
        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setFont(bold);
        XSSFCell t = sheet.createRow(r++).createCell(0);
        t.setCellStyle(titleStyle);
        t.setCellValue(report.title());

        XSSFFont grey = wb.createFont();
        grey.setColor(IndexedColors.GREY_50_PERCENT.getIndex());
        grey.setFontHeightInPoints((short) 10);
        XSSFCellStyle greyStyle = wb.createCellStyle();
        greyStyle.setFont(grey);
        for (String line : report.headerLines()) {
            XSSFCell c = sheet.createRow(r++).createCell(0);
            c.setCellStyle(greyStyle);
            c.setCellValue(line);
        }
        return r + 2;   // two empty rows before the headings (legacy)
    }

    private static XSSFCellStyle headStyle(XSSFWorkbook wb, HorizontalAlignment align) {
        XSSFCellStyle s = wb.createCellStyle();
        XSSFFont f = wb.createFont();
        f.setColor(IndexedColors.BLACK.getIndex());
        s.setFont(f);
        s.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        s.setFillForegroundColor(new XSSFColor(Color.decode("0xddebf7"), wb.getStylesSource().getIndexedColors()));
        s.setAlignment(align);
        s.setBorderTop(BorderStyle.THIN);
        s.setBorderBottom(BorderStyle.THIN);
        s.setBorderLeft(BorderStyle.THIN);
        s.setBorderRight(BorderStyle.THIN);
        return s;
    }

    static boolean isNumeric(int type) {
        return switch (type) {
            case Types.BIGINT, Types.DECIMAL, Types.DOUBLE, Types.FLOAT, Types.INTEGER, Types.NUMERIC, Types.REAL,
                    Types.SMALLINT, Types.TINYINT -> true;
            default -> false;
        };
    }
}
