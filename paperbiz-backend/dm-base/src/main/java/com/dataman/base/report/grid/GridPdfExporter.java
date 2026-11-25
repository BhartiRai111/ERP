package com.dataman.base.report.grid;

import com.dataman.base.enums.DmExportType;
import com.dataman.base.exception.DmException;
import com.dataman.base.report.jasper.ReportService;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignBand;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.design.JRDesignStaticText;
import net.sf.jasperreports.engine.design.JRDesignTextField;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.OrientationEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import org.springframework.stereotype.Service;

/**
 * List to PDF (legacy DmDynamicReportBuilder on DynamicJasper): the report layout is built in code with the
 * Jasper design API, so no extra library is needed. Title and sub title centred, grey heading row with a
 * bottom border, numbers right aligned, optional shaded odd rows and a bordered footer text.
 */
@Service
public class GridPdfExporter {

    private static final int ROW_HEIGHT = 16;

    private final ReportService reports;

    public GridPdfExporter(ReportService reports) {
        this.reports = reports;
    }

    public byte[] export(GridReport report, ResultSet rows) {
        try {
            ResultSetMetaData md = rows.getMetaData();
            List<Mapped> cols = mapColumns(report, md);
            if (cols.isEmpty()) {
                throw new DmException(0, "No column to print.");
            }
            int margin = 20;
            int width = report.page().width() - 2 * margin;
            JasperReport compiled = JasperCompileManager.compileReport(design(report, cols, width, margin));
            Map<String, Object> params = new HashMap<>();
            params.put("dmFooter", report.footer());
            JasperPrint print = JasperFillManager.fillReport(compiled, params, new JRResultSetDataSource(rows));
            return reports.export(print, DmExportType.PDF, report.fileStem() + ".pdf");
        } catch (SQLException | JRException e) {
            throw new DmException(0, e.getMessage());
        }
    }

    private record Mapped(GridColumn column, int index, String name, Class<?> type, boolean numeric, int width) {
    }

    private static List<Mapped> mapColumns(GridReport report, ResultSetMetaData md) throws SQLException {
        List<Mapped> mapped = new ArrayList<>();
        int total = 0;
        for (GridColumn c : report.columns()) {
            for (int i = 1; i <= md.getColumnCount(); i++) {
                if (c.dbField() != null && c.dbField().equalsIgnoreCase(md.getColumnName(i))) {
                    int t = md.getColumnType(i);
                    mapped.add(new Mapped(c, i, md.getColumnLabel(i), javaType(t), GridExcelExporter.isNumeric(t), Math.max(1, c.width())));
                    total += Math.max(1, c.width());
                    break;
                }
            }
        }
        return mapped;
    }

    private static Class<?> javaType(int sqlType) {
        return switch (sqlType) {
            case Types.INTEGER, Types.SMALLINT, Types.TINYINT -> Integer.class;
            case Types.BIGINT -> Long.class;
            case Types.FLOAT, Types.DOUBLE, Types.REAL -> Double.class;
            case Types.DECIMAL, Types.NUMERIC -> java.math.BigDecimal.class;
            case Types.DATE -> java.util.Date.class;
            case Types.TIMESTAMP -> java.sql.Timestamp.class;
            default -> String.class;
        };
    }

    private static JasperDesign design(GridReport report, List<Mapped> cols, int width, int margin) throws JRException {
        JasperDesign d = new JasperDesign();
        d.setName("GridReport");
        d.setLanguage("java");
        d.setPageWidth(report.page().width());
        d.setPageHeight(report.page().height());
        d.setOrientation(report.page().landscape() ? OrientationEnum.LANDSCAPE : OrientationEnum.PORTRAIT);
        d.setColumnWidth(width);
        d.setLeftMargin(margin);
        d.setRightMargin(margin);
        d.setTopMargin(margin);
        d.setBottomMargin(margin);
        d.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);

        JRDesignParameter footerParam = new JRDesignParameter();
        footerParam.setName("dmFooter");
        footerParam.setValueClass(String.class);
        d.addParameter(footerParam);

        // widths scaled to the page
        int sum = cols.stream().mapToInt(Mapped::width).sum();
        int[] w = new int[cols.size()];
        int used = 0;
        for (int i = 0; i < w.length; i++) {
            w[i] = i == w.length - 1 ? width - used : width * cols.get(i).width() / sum;
            used += w[i];
        }

        JRDesignBand title = new JRDesignBand();
        title.setHeight(report.subTitle() == null || report.subTitle().isBlank() ? 28 : 46);
        title.addElement(staticText(report.title(), 0, 0, width, 24, 14, true, HorizontalTextAlignEnum.CENTER, false));
        if (report.subTitle() != null && !report.subTitle().isBlank()) {
            title.addElement(staticText(report.subTitle(), 0, 26, width, 16, 10, false, HorizontalTextAlignEnum.CENTER, false));
        }
        d.setTitle(title);

        JRDesignBand head = new JRDesignBand();
        head.setHeight(ROW_HEIGHT + 2);
        JRDesignBand detail = new JRDesignBand();
        detail.setHeight(ROW_HEIGHT);
        int x = 0;
        for (int i = 0; i < cols.size(); i++) {
            Mapped m = cols.get(i);
            String name = m.name();
            if (!name.matches("[A-Za-z_][A-Za-z0-9_]*")) {
                throw new DmException(0, "Unsupported column name: " + name);
            }
            JRDesignField f = new JRDesignField();
            f.setName(name);
            f.setValueClass(m.type());
            d.addField(f);
            head.addElement(staticText(m.column().caption(), x, 0, w[i], ROW_HEIGHT + 2, 9, true,
                    m.numeric() ? HorizontalTextAlignEnum.RIGHT : HorizontalTextAlignEnum.LEFT, true));
            JRDesignTextField tf = new JRDesignTextField();
            tf.setX(x);
            tf.setY(0);
            tf.setWidth(w[i]);
            tf.setHeight(ROW_HEIGHT);
            tf.setFontSize(9f);
            tf.setBlankWhenNull(true);
            tf.setHorizontalTextAlign(m.numeric() ? HorizontalTextAlignEnum.RIGHT : HorizontalTextAlignEnum.LEFT);
            tf.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            tf.setTextAdjust(net.sf.jasperreports.engine.type.TextAdjustEnum.STRETCH_HEIGHT);
            if (m.type() == java.math.BigDecimal.class || m.type() == Double.class) {
                tf.setPattern("#,##0.00");
            } else if (m.type() == java.util.Date.class) {
                tf.setPattern("dd/MM/yy");
            } else if (m.type() == java.sql.Timestamp.class) {
                tf.setPattern("dd/MM/yy hh:mm:ss");
            }
            tf.setExpression(new JRDesignExpression("$F{" + name + "}"));
            if (report.oddRowBackground()) {
                tf.setMode(net.sf.jasperreports.engine.type.ModeEnum.OPAQUE);
                tf.setBackcolor(java.awt.Color.WHITE);
                net.sf.jasperreports.engine.design.JRDesignStyle odd = new net.sf.jasperreports.engine.design.JRDesignStyle();
                odd.setName("odd" + i);
                net.sf.jasperreports.engine.design.JRDesignConditionalStyle cs =
                        new net.sf.jasperreports.engine.design.JRDesignConditionalStyle();
                cs.setConditionExpression(new JRDesignExpression("$V{REPORT_COUNT} % 2 == 0"));
                cs.setBackcolor(new java.awt.Color(0xEE, 0xEE, 0xEE));
                odd.addConditionalStyle(cs);
                d.addStyle(odd);
                tf.setStyle(odd);
            }
            detail.addElement(tf);
            x += w[i];
        }
        d.setColumnHeader(head);
        ((JRDesignSection) d.getDetailSection()).addBand(detail);

        if (report.footer() != null && !report.footer().isBlank()) {
            JRDesignBand summary = new JRDesignBand();
            summary.setHeight(22);
            JRDesignTextField ft = new JRDesignTextField();
            ft.setX(0);
            ft.setY(0);
            ft.setWidth(width);
            ft.setHeight(20);
            ft.setFontSize(12f);
            ft.setBold(true);
            ft.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
            ft.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
            ft.getLineBox().getPen().setLineWidth(1f);
            ft.setExpression(new JRDesignExpression("$P{dmFooter}"));
            summary.addElement(ft);
            d.setSummary(summary);
        }
        return d;
    }

    private static JRDesignStaticText staticText(String text, int x, int y, int w, int h, float size, boolean bold,
            HorizontalTextAlignEnum align, boolean headingBox) {
        JRDesignStaticText s = new JRDesignStaticText();
        s.setX(x);
        s.setY(y);
        s.setWidth(w);
        s.setHeight(h);
        s.setText(text == null ? "" : text);
        s.setFontSize(size);
        s.setBold(bold);
        s.setHorizontalTextAlign(align);
        s.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        if (headingBox) {
            s.setMode(net.sf.jasperreports.engine.type.ModeEnum.OPAQUE);
            s.setBackcolor(java.awt.Color.LIGHT_GRAY);
            s.getLineBox().getBottomPen().setLineWidth(1f);
        }
        return s;
    }
}
