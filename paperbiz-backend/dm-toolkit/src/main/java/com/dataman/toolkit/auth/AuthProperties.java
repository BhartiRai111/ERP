package com.dataman.toolkit.auth;

import org.springframework.boot.context.properties.ConfigurationProperties;

/** Properties under {@code dataman.auth.*}. The signing secret comes from config/environment, never from code. */
@ConfigurationProperties(prefix = "dataman.auth")
public class AuthProperties {

    /** HMAC secret for the tokens, at least 32 characters. */
    private String jwtSecret;
    /** Lifetime of a token in minutes (a new one is issued at every company/site selection). */
    private int tokenMinutes = 480;
    /** Accept the temporary X-Dm-* headers instead of a token (development only). */
    private boolean devHeaders = false;
    /** Wrong passwords in a row before the user (from that address) is blocked for {@link #lockMinutes}. */
    private int maxFailures = 5;
    private int lockMinutes = 15;

    public String getJwtSecret() { return jwtSecret; }
    public void setJwtSecret(String jwtSecret) { this.jwtSecret = jwtSecret; }
    public int getTokenMinutes() { return tokenMinutes; }
    public void setTokenMinutes(int tokenMinutes) { this.tokenMinutes = tokenMinutes; }
    public boolean isDevHeaders() { return devHeaders; }
    public void setDevHeaders(boolean devHeaders) { this.devHeaders = devHeaders; }
    public int getMaxFailures() { return maxFailures; }
    public void setMaxFailures(int maxFailures) { this.maxFailures = maxFailures; }
    public int getLockMinutes() { return lockMinutes; }
    public void setLockMinutes(int lockMinutes) { this.lockMinutes = lockMinutes; }
}
