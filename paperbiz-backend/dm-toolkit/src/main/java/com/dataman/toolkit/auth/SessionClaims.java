package com.dataman.toolkit.auth;

/**
 * The few facts a token carries. Everything else (menus, permissions, site details, settings) stays on the
 * server in the {@link SessionStore} entry with the same {@code sessionId}.
 */
public record SessionClaims(String userName, String sessionId, String pltCode, String companyCode,
        String companyDb, String fileDb, String siteCode) {

    public SessionClaims {
        pltCode = nz(pltCode);
        companyCode = nz(companyCode);
        companyDb = nz(companyDb);
        fileDb = nz(fileDb);
        siteCode = nz(siteCode);
    }

    private static String nz(String s) {
        return s == null ? "" : s;
    }

    /** Logged in but company and site are not chosen yet. */
    public boolean hasWorkContext() {
        return !companyDb.isEmpty() && !siteCode.isEmpty();
    }
}
