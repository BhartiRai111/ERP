package com.dataman.base.report.jasper;

import java.nio.file.Path;
import net.sf.jasperreports.engine.JasperReport;

/** A compiled report plus the folder holding its compiled sub reports (value of SUBREPORT_DIR). */
public record CompiledReport(JasperReport report, Path subReportDir) {
}
