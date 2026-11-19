package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum RegionalLanguage {

    HINDI("Hindi", "Kruti Dev 010", "20px"),
    ENGLISH("English", "Arial", "20px"),
    BANGALI("Bangali", "AdarshaLipiNormal", "20px"),
    MARATHI("Marathi", "Shivaji01", "20px"),
    PUNJABI("Punjabi", "Asees", "20px");

    private String regionalName;
    private String fontName;
    private String cssFontSize;

    public String getRegionalName() {
        return regionalName;
    }

    public String getFontFamilyName() {
        return getRegionalName().toLowerCase();
    }

    public String getFontName() {
        return fontName;
    }

    public String getCssStyleName() {
        return getFontFamilyName() + "Font";
    }

    public String getCssFontSize() {
        return cssFontSize;
    }

    public static RegionalLanguage getValueFrom(String regionalName) {
        RegionalLanguage[] languages = RegionalLanguage.values();
        RegionalLanguage language = null;
        for (int i = 0; i < languages.length; i++) {
            if (languages[i].getRegionalName().equalsIgnoreCase(regionalName.trim())) {
                language = languages[i];
                break;
            }
        }
        return language;
    }

    public static RegionalLanguage getValueFromCssStyle(String cssStyleName) {
        RegionalLanguage[] languages = RegionalLanguage.values();
        RegionalLanguage language = null;
        for (int i = 0; i < languages.length; i++) {
            if (languages[i].getCssStyleName().equalsIgnoreCase(cssStyleName.trim())) {
                language = languages[i];
                break;
            }
        }
        return language;
    }

    private RegionalLanguage(String regName, String fontName, String fontSize) {
        this.regionalName = regName.trim();
        this.fontName = fontName.trim();
        this.cssFontSize = fontSize.trim();
    }

}
