package com.dataman.base.context;

/**
 * Everything the legacy session carried about the logged-in user/company/site. It is loaded once
 * at login (a later step: login/JWT) and replaces the ~140 getSsn...(this) calls.
 */
public record SessionInfo(UserInfo user, CompanyInfo company, SiteInfo site, String companyDb, String fileDb) {
}
