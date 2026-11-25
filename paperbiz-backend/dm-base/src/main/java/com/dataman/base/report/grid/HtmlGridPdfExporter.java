package com.dataman.base.report.grid;

import com.dataman.base.exception.DmException;
import com.dataman.base.report.html.HtmlReportService;
import com.dataman.base.util.DateUtil;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

/**
 * List to PDF through the HTML engine: a plain table with a centred title and sub title, a grey heading row,
 * right aligned numbers (2 decimals, grouped), optional shaded odd rows, a bordered footer text and
 * "Page x of y". Only the columns of the report that exist in the result set are printed, in the report's order.
 */
@Service
@ConditionalOnProperty(prefix = "dataman.reports", name = "grid-pdf", havingValue = "html", matchIfMissing = true)
public class HtmlGridPdfExporter implements GridPdfExporter {

    private final HtmlReportService html;

    public HtmlGridPdfExporter(HtmlReportService html) {
        this.html = html;
    }

    @Override
    public byte[] export(GridReport report, ResultSet rows) {
        return html.toPdf(toHtml(report, rows));
    }

    String toHtml(GridReport report, ResultSet rows) {
        try {
            ResultSetMetaData md = rows.getMetaData();
            List<int[]> cols = new ArrayList<>();      // {report column, result set column}
            int total = 0;
            for (int r = 0; r < report.columns().size(); r++) {
                String field = report.columns().get(r).dbField();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    if (field != null && field.equalsIgnoreCase(md.getColumnName(i))) {
                        cols.add(new int[] {r, i});
                        total += Math.max(1, report.columns().get(r).width());
                        break;
                    }
                }
            }
            if (cols.isEmpty()) {
                throw new DmException(0, "No column to print.");
            }
            DecimalFormat money = new DecimalFormat("#,##0.00", DecimalFormatSymbols.getInstance(Locale.ENGLISH));

            StringBuilder sb = new StringBuilder(4096);
            sb.append("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"/><title>")
                    .append(esc(report.title())).append("</title><style>")
                    .append("@page{size:").append(pageSize(report.page())).append(";margin:15mm 12mm;")
                    .append("@bottom-center{content:\"Page \" counter(page) \" of \" counter(pages);font-size:8pt}}")
                    .append("body{font-family:sans-serif;font-size:9pt}h1{font-size:14pt;text-align:center;margin:0}")
                    .append("h2{font-size:10pt;text-align:center;margin:2px 0 8px;font-weight:normal}")
                    .append("table{width:100%;border-collapse:collapse;table-layout:fixed}")
                    .append("thead{display:table-header-group}tr{page-break-inside:avoid}")
                    .append("th{background:#c0c0c0;border-bottom:1px solid #000;padding:2px 3px;text-align:left}")
                    .append("td{padding:2px 3px;word-wrap:break-word}.n{text-align:right}")
                    .append(report.oddRowBackground() ? "tbody tr:nth-child(even) td{background:#eeeeee}" : "")
                    .append(".footer{margin-top:8px;border:1px solid #000;text-align:center;font-weight:bold;font-size:12pt;padding:3px}")
                    .append("</style></head><body><h1>").append(esc(report.title())).append("</h1>");
            if (report.subTitle() != null && !report.subTitle().isBlank()) {
                sb.append("<h2>").append(esc(report.subTitle())).append("</h2>");
            }
            sb.append("<table><colgroup>");
            for (int[] c : cols) {
                sb.append("<col style=\"width:").append(100.0 * Math.max(1, report.columns().get(c[0]).width()) / total).append("%\"/>");
            }
            sb.append("</colgroup><thead><tr>");
            for (int[] c : cols) {
                sb.append(GridExcelExporter.isNumeric(md.getColumnType(c[1])) ? "<th class=\"n\">" : "<th>")
                        .append(esc(report.columns().get(c[0]).caption())).append("</th>");
            }
            sb.append("</tr></thead><tbody>");
            while (rows.next()) {
                sb.append("<tr>");
                for (int[] c : cols) {
                    int type = md.getColumnType(c[1]);
                    boolean numeric = GridExcelExporter.isNumeric(type);
                    sb.append(numeric ? "<td class=\"n\">" : "<td>").append(esc(cell(rows, c[1], type, money))).append("</td>");
                }
                sb.append("</tr>");
            }
            sb.append("</tbody></table>");
            if (report.footer() != null && !report.footer().isBlank()) {
                sb.append("<div class=\"footer\">").append(esc(report.footer())).append("</div>");
            }
            return sb.append("</body></html>").toString();
        } catch (SQLException e) {
            throw new DmException(0, e.getMessage());
        }
    }

    private static String cell(ResultSet rs, int idx, int type, DecimalFormat money) throws SQLException {
        switch (type) {
            case Types.DATE:
                return rs.getDate(idx) == null ? "" : DateUtil.display(rs.getDate(idx));
            case Types.INTEGER, Types.SMALLINT, Types.TINYINT, Types.BIGINT:
                return rs.getObject(idx) == null ? "" : String.valueOf(rs.getLong(idx));
            case Types.FLOAT, Types.DECIMAL, Types.DOUBLE, Types.NUMERIC, Types.REAL:
                return rs.getObject(idx) == null ? "" : money.format(rs.getBigDecimal(idx));
            default:
                String v = rs.getString(idx);
                return v == null ? "" : v;
        }
    }

    private static String pageSize(PageSetup p) {
        String name = p.name().startsWith("LEGAL") ? "legal" : p.name().startsWith("LETTER") ? "letter" : "A4";
        return name + (p.landscape() ? " landscape" : " portrait");
    }

    private static String esc(String s) {
        return HtmlUtils.htmlEscape(s == null ? "" : s);
    }
}
