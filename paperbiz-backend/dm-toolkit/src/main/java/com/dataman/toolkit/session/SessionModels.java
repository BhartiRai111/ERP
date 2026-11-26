package com.dataman.toolkit.session;

import java.util.List;

/** What the screens get back after login / selection (legacy PCSData, PLT, Company, Site). */
public final class SessionModels {

    private SessionModels() {
    }

    public record Plt(String code, String name, String linkedAccountCode) {
    }

    public record Company(String code, String name, String currentYear, String voucherPrefix, String startDate,
            String endDate) {
    }

    public record Site(String code, String name, String branchName) {
    }

    /**
     * Login/selection answer. {@code *Selected} are null until chosen (a single choice is chosen automatically,
     * as before). {@code token} is the new token for the current selection.
     */
    public record Selection(String token, String userName, String userDescription, String loginDate,
            List<Plt> plts, Plt pltSelected, List<Company> companies, Company companySelected,
            List<Site> sites, Site siteSelected, String regionalLanguage, String localeCompanyCode,
            String message) {
    }
}
