package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum DmPluginQueryActions {

    PACPortWeb("PACPortWeb"),;

    private String queryAction;

    @Override
    public String toString() {
        return this.queryAction;
    }

    private DmPluginQueryActions(String qAction) {
        this.queryAction = qAction.toLowerCase().trim();
    }
}
