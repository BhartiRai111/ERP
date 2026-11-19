package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmUserValidationMode {
    USERNAME("User Name", 0),
    EMAIL("eMail", 1),
    MOBILE("Mobile", 2),
    TokenKey("Token Key", 3);

    private final int validationMode;
    private final String validationModeName;

    public String getValidationModeName() {
        return this.validationModeName;
    }

    public int getValidationMode() {
        return this.validationMode;
    }

    public static DmUserValidationMode getValueFrom(String validationModeName) {
        DmUserValidationMode userValidationMode = null;
        if (DmUserValidationMode.USERNAME.getValidationModeName().equalsIgnoreCase(validationModeName)) {
            userValidationMode = DmUserValidationMode.USERNAME;
        } else if (DmUserValidationMode.EMAIL.getValidationModeName().equalsIgnoreCase(validationModeName)) {
            userValidationMode = DmUserValidationMode.EMAIL;
        } else if (DmUserValidationMode.TokenKey.getValidationModeName().equalsIgnoreCase(validationModeName)) {
            userValidationMode = DmUserValidationMode.TokenKey;
        }
        return userValidationMode;
    }

    public static DmUserValidationMode getValueFrom(int validationMode) {
        DmUserValidationMode userValidationMode = null;

        if (DmUserValidationMode.USERNAME.getValidationMode() == validationMode) {
            userValidationMode = DmUserValidationMode.USERNAME;
        } else if (DmUserValidationMode.EMAIL.getValidationMode() == validationMode) {
            userValidationMode = DmUserValidationMode.EMAIL;
        } else if (DmUserValidationMode.MOBILE.getValidationMode() == validationMode) {
            userValidationMode = DmUserValidationMode.MOBILE;
        } else if (DmUserValidationMode.TokenKey.getValidationMode() == validationMode) {
            userValidationMode = DmUserValidationMode.TokenKey;
        }

        return userValidationMode;
    }

    private DmUserValidationMode(String validationModeName, int validationMode) {
        this.validationModeName = validationModeName;
        this.validationMode = validationMode;
    }
}
