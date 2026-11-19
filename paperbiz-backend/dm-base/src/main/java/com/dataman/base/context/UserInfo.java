package com.dataman.base.context;

import java.util.List;

/** Login user details (legacy getSsnUser*, getSsnLinked*, getSsnPLT*, site/cost-centre lists). */
public record UserInfo(String userName, String description, String natureCode, String linkedUser,
                       String linkedAccountCode, String linkedAccountName, int lockTimeOutMinutes,
                       String pltCode, String pltName, List<String> siteList, List<String> costCenterList) {

    public UserInfo {
        siteList = siteList == null ? List.of() : List.copyOf(siteList);
        costCenterList = costCenterList == null ? List.of() : List.copyOf(costCenterList);
    }
}
