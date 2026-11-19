package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmLocaleCompany {
    DatamanDemonstration(1, "1", DmLocaleCountry.INDIA, TaxType.GST, ProductType.ALL, true),
    //DatamanDemonstration(1, "1", DmLocaleCountry.INDIA, TaxType.GST, ProductType.ALL, false), // For Sarvi
    ABC_COMPANY(2, "5", DmLocaleCountry.USA, TaxType.VAT, ProductType.TRADING, true);

    private int code;
    private String pltCode;
    private DmLocaleCountry localeCountry;
    private TaxType taxType;
    private ProductType productType;
    private boolean corrogation;

    private DmLocaleCompany(int code, String pltCode, DmLocaleCountry localeCountry, TaxType taxType, ProductType productType, boolean corrogation) {
        this.code = code;
        this.pltCode = pltCode;
        this.localeCountry = localeCountry;
        this.taxType = taxType;
        this.productType = productType;
        this.corrogation = corrogation;
    }

    public static DmLocaleCompany getLocaleViaCode(int code) {
        DmLocaleCompany company = DatamanDemonstration;

        switch (code) {
            case 1:
                company = DatamanDemonstration;
                break;
            case 2:
                company = ABC_COMPANY;
                break;
            default:
        }

        return company;
    }

    public static DmLocaleCompany getLocaleViaPltCode(String pltCode) {
        DmLocaleCompany company = DatamanDemonstration;

        switch (Strings.xNull(pltCode).trim()) {
            case "1":
                company = DatamanDemonstration;
                break;
            case "5":
                company = ABC_COMPANY;
                break;
            default:
        }

        return company;
    }

    public int getCode() {
        return code;
    }

    public String getPltCode() {
        return pltCode;
    }

    public DmLocaleCountry getLocaleCountry() {
        return localeCountry;
    }

    public TaxType getTaxType() {
        return taxType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public boolean isCorrogation() {
        return corrogation;
    }

    public void setCorrogation(boolean corrogation) {
        this.corrogation = corrogation;
    }

}
