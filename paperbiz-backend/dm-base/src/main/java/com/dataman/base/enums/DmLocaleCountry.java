package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmLocaleCountry {
    //Link to get alpha code of country
    //https://en.wikipedia.org/wiki/List_of_ISO_3166_country_codes
    //INDIA("IND", "IND", "India", "Indian Rupee", "RS", "Rupee", "Paisa", true, new Integer[]{10}, "91"),
    INDIA("IND", "IND", "India", "Indian Rupee", "RS", "Rupee", "Paisa", true, new Integer[]{10}, "91"),
    CAMBODIA("KHM", "KHM", "Cambodia", "Cambodian Riel", "KHR", "Riel", "KAK", false, new Integer[]{8, 9, 10}, "855"),
    USA("USA", "USA", "United States of America", "US Dollar", "USD", "Dollar", "Cent", false, new Integer[]{10}, "1"),
    CAMBODIA_USA("KHM-USA", "USA", "United States of America", "US Dollar", "USD", "Dollar", "Cent", false, new Integer[]{8, 9, 10}, "1");

    private String dmCode, isoCode3, countryName, currencyName, currencyShortName, majorCurrency, minorCurrency, isdCode;
    private Integer[] mobileDigit;
    private boolean isLakhFormat = false;

    private DmLocaleCountry(String dmCode, String isoCode3, String countryName,
            String currencyName, String currencyShortName, String majorCurrency,
            String minorCurrency, boolean isLakhFormat, Integer[] mobileDigit, String isdCode) {
        this.dmCode = dmCode;
        this.isoCode3 = isoCode3;
        this.countryName = countryName;
        this.currencyName = currencyName;
        this.currencyShortName = currencyShortName;
        this.majorCurrency = majorCurrency;
        this.minorCurrency = minorCurrency;
        this.isLakhFormat = isLakhFormat;
        this.mobileDigit = mobileDigit;
        this.isdCode = isdCode;
    }

    public String getDmCode() {
        return dmCode;
    }

    public String getISOCode3() {
        return isoCode3;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public String getCurrencyShortName() {
        return currencyShortName;
    }

    public String getMajorCurrency() {
        return majorCurrency;
    }

    public String getMinorCurrency() {
        return minorCurrency;
    }

    public boolean isLakhFormat() {
        return isLakhFormat;
    }

    public Integer[] getMobileDigit() {
        return mobileDigit;
    }

    public String getISDCode() {
        return isdCode;
    }

    public static DmLocaleCountry getLocaleViaDmCode(String dmCode) {
        DmLocaleCountry rtnValue = USA;
        dmCode = Strings.xNull(dmCode).trim();

        if (dmCode.equalsIgnoreCase(INDIA.getDmCode())) {
            rtnValue = INDIA;
        } else if (dmCode.equalsIgnoreCase(CAMBODIA.getDmCode())) {
            rtnValue = CAMBODIA;
        } else if (dmCode.equalsIgnoreCase(CAMBODIA_USA.getDmCode())) {
            rtnValue = CAMBODIA_USA;
        }

        return rtnValue;
    }
}
