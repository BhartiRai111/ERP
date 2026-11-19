package com.dataman.base.context;

/** Company details of the logged-in session (legacy getSsnCompany* getters). */
public record CompanyInfo(String code, String name, String currentYear, String startDate, String endDate,
                          String voucherPrefix, String address1, String address2, String cityName, String pin,
                          String stateName, String stateNumericCode, String countryName, String gstCountryCode) {
}
