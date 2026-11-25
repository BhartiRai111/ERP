package com.dataman.base.report.grid;

/** One printed column of a list: heading, result set column name and width (legacy DmMasterReportColumn). */
public record GridColumn(String caption, String dbField, int width) {
}
