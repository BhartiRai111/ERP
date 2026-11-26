package com.paperbiz.master.unit;

import com.dataman.toolkit.changelog.ModifyReason;

/**
 * One unit of measure as the screen shows and saves it (legacy UnitMaster_Mdl). Texts for dates and users are
 * display formats ("user - dd-MMM-yyyy - HH:mm"). {@code modifyReason} is sent on edit and delete only.
 */
public record UnitMasterDto(String code, String name, String decimalPlaces, String gstUnitCode, String gstUnitName,
        String unitNatureCode, String unitNatureName, String isSystemGenerated, String preparedBy, String preparedDate,
        String preparedIp, String modifiedBy, String modifyDate, ModifyReason modifyReason) {
}
