package com.dataman.base.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** HikariCP pool (replaces BoneCP) wrapped for per-request company database selection. */
@Configuration
@EnableConfigurationProperties(DmDbProperties.class)
public class DmDbConfiguration {

    @Bean
    @ConditionalOnProperty(prefix = "dataman.db", name = "url")
    public DataSource dataSource(DmDbProperties p) {
        if (p.getUrl() == null || p.getUrl().isBlank()) {
            throw new IllegalStateException("Database is not configured: set DM_DB_URL (dataman.db.url), DM_DB_USER, DM_DB_PASSWORD and DM_DB_DEFAULT_CATALOG.");
        }
        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(p.getUrl());
        cfg.setUsername(p.getUsername());
        cfg.setPassword(p.getPassword());
        if (p.getDefaultCatalog() != null && !p.getDefaultCatalog().isBlank()) {
            cfg.setCatalog(p.getDefaultCatalog());
        }
        cfg.setMaximumPoolSize(p.getMaximumPoolSize());
        cfg.setMinimumIdle(p.getMinimumIdle());
        cfg.setPoolName("dataman-pool");
        return new CompanyCatalogDataSource(new HikariDataSource(cfg), p.getDefaultCatalog());
    }
}
