package com.dataman.base.report.jasper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Rectangle2D;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.renderers.AbstractRenderer;
import net.sf.jasperreports.renderers.DimensionRenderable;
import net.sf.jasperreports.renderers.Graphics2DRenderable;

/** Grey slanted text printed behind a page (legacy DmWaterMark); pass it as an image parameter of the report. */
public class WatermarkRenderer extends AbstractRenderer implements Graphics2DRenderable, DimensionRenderable {

    private final String text;
    private final double rotate;
    private final int leftMargin;
    private final int width;
    private final int height;
    private final int fontSize;

    public WatermarkRenderer(String text, double rotate, int leftMargin, int width, int height, int fontSize) {
        this.text = text == null ? "" : text;
        this.rotate = rotate;
        this.leftMargin = leftMargin;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
    }

    /** Defaults of the legacy screens: "Watermark", -50 degrees, A4 size, font 110. */
    public static WatermarkRenderer of(String text) {
        return new WatermarkRenderer(text, -50, 0, 595, 700, 110);
    }

    @Override
    public Dimension2D getDimension(JasperReportsContext context) throws JRException {
        return new Dimension(width, height);
    }

    @Override
    public void render(JasperReportsContext context, Graphics2D g2, Rectangle2D rectangle) throws JRException {
        AffineTransform original = g2.getTransform();
        g2.translate(rectangle.getX() + leftMargin, rectangle.getMaxY());
        g2.rotate(rotate * Math.PI / 180);
        Font font = new Font("Tahoma", Font.BOLD, fontSize);
        Shape shape = font.createGlyphVector(g2.getFontRenderContext(), text).getOutline();
        g2.setColor(new Color(204, 204, 204));
        g2.fill(shape);
        g2.draw(shape);
        g2.setFont(font);
        g2.setTransform(original);
    }
}
