package com.dataman.base.report.grid;

import java.util.List;

/**
 * What to print for a list (legacy DmMasterReport, reduced to what the screens use).
 *
 * @param headerLines extra lines under the title in the Excel header (company, site, GSTIN/PAN, address, contact, filters)
 * @param footer      optional text printed in a bordered box at the end of the PDF (legacy dmFooter)
 */
public record GridReport(String title, String subTitle, List<GridColumn> columns, PageSetup page,
        List<String> headerLines, String footer, boolean oddRowBackground) {

    public GridReport {
        columns = List.copyOf(columns);
        headerLines = headerLines == null ? List.of() : List.copyOf(headerLines);
        page = page == null ? PageSetup.A4_PORTRAIT : page;
    }

    public GridReport(String title, List<GridColumn> columns) {
        this(title, null, columns, PageSetup.A4_PORTRAIT, List.of(), null, false);
    }

    /** File name stem: the title without anything but letters and digits (legacy). */
    public String fileStem() {
        String s = title == null ? "" : title.trim().replaceAll("[^a-zA-Z0-9]", "");
        return s.isEmpty() ? "Report" : s;
    }
}
