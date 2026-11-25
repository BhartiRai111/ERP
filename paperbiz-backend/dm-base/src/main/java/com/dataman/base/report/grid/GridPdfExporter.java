package com.dataman.base.report.grid;

import java.sql.ResultSet;

/** List to PDF. The default is {@link HtmlGridPdfExporter}; {@code dataman.reports.grid-pdf=jasper} switches to Jasper. */
public interface GridPdfExporter {

    byte[] export(GridReport report, ResultSet rows);
}
