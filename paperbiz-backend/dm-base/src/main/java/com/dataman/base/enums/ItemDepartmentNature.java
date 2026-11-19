package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum ItemDepartmentNature {
    RESTRICTED(1, "Restricted", "R"),
    ALLOWED(2, "Allowed", "A");

    private final int code;
    private final String name;
    private final String shortName;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    ItemDepartmentNature(int code, String name, String shortName) {

        this.code = code;
        this.name = name;
        this.shortName = shortName;
    }

    public static ItemDepartmentNature getViaCode(int code) {
        ItemDepartmentNature returnValue = null;
        for (ItemDepartmentNature en : ItemDepartmentNature.values()) {
            if (code == en.getCode()) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static ItemDepartmentNature getViaName(String name) {
        ItemDepartmentNature returnValue = null;
        for (ItemDepartmentNature en : ItemDepartmentNature.values()) {
            if (name.equalsIgnoreCase(en.getName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

    public static ItemDepartmentNature getViaShortName(String shortName) {
        ItemDepartmentNature returnValue = null;
        for (ItemDepartmentNature en : ItemDepartmentNature.values()) {
            if (shortName.equalsIgnoreCase(en.getShortName())) {
                returnValue = en;
                break;
            }
        }

        return returnValue;
    }

}
