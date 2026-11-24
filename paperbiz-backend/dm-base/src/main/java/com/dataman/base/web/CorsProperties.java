package com.dataman.base.web;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/** Properties under {@code dataman.cors.*}: which browser origins (the Next.js app) may call the API. */
@ConfigurationProperties(prefix = "dataman.cors")
public class CorsProperties {

    private List<String> allowedOrigins = List.of("http://localhost:3000");

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }
}
