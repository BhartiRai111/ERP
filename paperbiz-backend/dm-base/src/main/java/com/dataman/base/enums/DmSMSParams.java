package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmSMSParams {

    URL("url"),
    ACCOUNTNAME("accName"),
    PASSWORD("password"),
    MSG("msg"),
    MOBILE("mobile"),
    USER("user");

    private String paramName;

    public String getParamName() {
        return this.paramName;
    }

    public static DmSMSParams getValueFrom(String pName) {
        DmSMSParams param = null;
        if (DmSMSParams.ACCOUNTNAME.getParamName().equalsIgnoreCase(pName)) {
            param = ACCOUNTNAME;
        } else if (DmSMSParams.MOBILE.getParamName().equalsIgnoreCase(pName)) {
            param = MOBILE;
        } else if (DmSMSParams.MSG.getParamName().equalsIgnoreCase(pName)) {
            param = MSG;
        } else if (DmSMSParams.PASSWORD.getParamName().equalsIgnoreCase(pName)) {
            param = PASSWORD;
        } else if (DmSMSParams.URL.getParamName().equalsIgnoreCase(pName)) {
            param = URL;
        } else if (DmSMSParams.USER.getParamName().equalsIgnoreCase(pName)) {
            param = USER;
        }
        return param;
    }

    private DmSMSParams(String pName) {
        this.paramName = pName.trim();
    }
}
