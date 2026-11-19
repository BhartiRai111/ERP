package com.dataman.base.context;

/** Site (branch) details of the logged-in session (legacy getSsnSite* getters). */
public record SiteInfo(String code, String name, String shortName, String displayName, String branchName,
                       String address1, String address2, String cityName, String pin, String pan, String tan,
                       String phone, String email, String drugLicenseNo, String gstin, String gstinDate,
                       String stateName, String stateNumericCode, String countryName, String gstCountryCode) {
}
