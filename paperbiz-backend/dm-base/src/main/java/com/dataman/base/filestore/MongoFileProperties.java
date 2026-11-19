package com.dataman.base.filestore;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Properties under {@code dataman.mongo.*} (the file server). Credentials come from config/environment. */
@ConfigurationProperties(prefix = "dataman.mongo")
public class MongoFileProperties {

    private String host;
    private int port = 27017;
    private String username;
    private String password;
    private String authSource = "admin";

    public String getHost() { return host; }
    public void setHost(String host) { this.host = host; }
    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getAuthSource() { return authSource; }
    public void setAuthSource(String authSource) { this.authSource = authSource; }
}
