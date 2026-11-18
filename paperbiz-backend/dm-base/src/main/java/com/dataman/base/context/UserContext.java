package com.dataman.base.context;

/**
 * Who is calling and for which company/site. Replaces the legacy session getters
 * (getSsnUserName, getSsnDBName, getSsnSiteCode, client IP).
 */
public record UserContext(String userName, String companyDb, String siteCode, String ip) {
}
