package com.dataman.base.report.jasper;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Properties under {@code dataman.reports.*}. */
@ConfigurationProperties(prefix = "dataman.reports")
public class ReportProperties {

    /** Folder with the report sources: {@code <dir>/system}, {@code <dir>/systemCustomized}, {@code <dir>/osr} (.jrxml). */
    private String dir = "reports";
    /** Folder for compiled sub reports ({@code .jasper}); default: a folder under the system temp directory. */
    private String cacheDir;

    /**
     * Legacy scriptlet class name (as written in the .jrxml files, e.g. dataman.dmToolkit.dmJasper.server.DmJasper)
     * -> class to use now. The old reports keep working without editing them.
     */
    private java.util.Map<String, String> scriptletClasses = new java.util.HashMap<>();

    public java.util.Map<String, String> getScriptletClasses() { return scriptletClasses; }
    public void setScriptletClasses(java.util.Map<String, String> scriptletClasses) { this.scriptletClasses = scriptletClasses; }

    public String getDir() { return dir; }
    public void setDir(String dir) { this.dir = dir; }
    public String getCacheDir() { return cacheDir; }
    public void setCacheDir(String cacheDir) { this.cacheDir = cacheDir; }
}
