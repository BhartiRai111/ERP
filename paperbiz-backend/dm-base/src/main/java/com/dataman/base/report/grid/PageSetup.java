package com.dataman.base.report.grid;

/** Page size in points and orientation (legacy DmMasterReportPage presets). */
public enum PageSetup {
    A4_PORTRAIT(595, 842), A4_LANDSCAPE(842, 595),
    LEGAL_PORTRAIT(612, 1008), LEGAL_LANDSCAPE(1008, 612),
    LETTER_PORTRAIT(612, 792), LETTER_LANDSCAPE(792, 612);

    private final int width;
    private final int height;

    PageSetup(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    public boolean landscape() {
        return width > height;
    }
}
