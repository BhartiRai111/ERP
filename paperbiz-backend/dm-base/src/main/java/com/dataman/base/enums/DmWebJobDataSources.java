package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmWebJobDataSources {

    FUNCTION("SQL", 0),
    CSV("CSV", 1);

    private String sName;
    private int sInt;

    public String getSourceName() {
        return this.sName;
    }

    public int getSourceType() {
        return this.sInt;
    }

    public static DmWebJobDataSources getValueFrom(String sName) {
        DmWebJobDataSources source = null;
        if (DmWebJobDataSources.FUNCTION.getSourceName().equalsIgnoreCase(sName)) {
            source = DmWebJobDataSources.FUNCTION;
        } else if (DmWebJobDataSources.CSV.getSourceName().equalsIgnoreCase(sName)) {
            source = DmWebJobDataSources.CSV;
        }
        return source;
    }

    public static DmWebJobDataSources getValueFrom(int sType) {
        DmWebJobDataSources source = null;
        if (DmWebJobDataSources.FUNCTION.getSourceType() == sType) {
            source = DmWebJobDataSources.FUNCTION;
        } else if (DmWebJobDataSources.CSV.getSourceType() == sType) {
            source = DmWebJobDataSources.CSV;
        }
        return source;
    }

    private DmWebJobDataSources(String sourceName, int sourceType) {
        this.sInt = sourceType;
        this.sName = sourceName;
    }
}
