package com.dataman.base.enums;

import com.dataman.base.util.Strings;

public enum EGST_URL_FORMAT_TAG {

    BASE_URL("{url}"),
    FOLDER("{folder}"),
    VERSION("{version}"),
    API_NAME("{api}"),
    ACTION("{action}"),
    ASP_ID("{asp_id}"), //aspid
    ASP_PASSWORD("{asp_password}"), //asppassword
    GSTIN("{gstin}"),
    GSP_USER_NAME("{gsp_user_name}"), //username
    GSP_PASSWORD("{gsp_pwd}"), //ewbpwd
    AUTH_TOKEN("{auth_token}"), //authtoken
    QR_CODE_SIZE("{qr_code_size}"), //qrcodeSize
    PARSE_IRN_RESP("{parse_irn_resp}"), //parseIrnResp
    EWAY_NO("&ewbNo=");     //<Ag> TBR: New format field add and manage this

    private final String value;

    public String getValue() {
        return value;
    }

    EGST_URL_FORMAT_TAG(String value) {
        this.value = value;
    }
}
