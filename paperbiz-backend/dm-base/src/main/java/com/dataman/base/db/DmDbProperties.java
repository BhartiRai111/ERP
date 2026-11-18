package com.dataman.base.db;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Properties under {@code dataman.db.*}. Credentials come from config/environment, never from code. */
@ConfigurationProperties(prefix = "dataman.db")
public class DmDbProperties {

    /** JDBC url of the SQL Server (without per-company database; that is set per request). */
    private String url;
    private String username;
    private String password;
    /** Database used when a request has no company context (and restored after each request). */
    private String defaultCatalog;
    private int maximumPoolSize = 20;
    private int minimumIdle = 2;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDefaultCatalog() { return defaultCatalog; }
    public void setDefaultCatalog(String defaultCatalog) { this.defaultCatalog = defaultCatalog; }
    public int getMaximumPoolSize() { return maximumPoolSize; }
    public void setMaximumPoolSize(int maximumPoolSize) { this.maximumPoolSize = maximumPoolSize; }
    public int getMinimumIdle() { return minimumIdle; }
    public void setMinimumIdle(int minimumIdle) { this.minimumIdle = minimumIdle; }
}
