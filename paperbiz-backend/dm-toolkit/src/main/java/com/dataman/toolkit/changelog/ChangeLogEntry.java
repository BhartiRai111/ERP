package com.dataman.toolkit.changelog;

import com.dataman.base.enums.DmTransactionMode;

/**
 * What one saved/deleted record writes to the change log (legacy ModifyData_Mdl). {@code oldData}/{@code newData}
 * are JSON texts of the record before and after.
 */
public record ChangeLogEntry(String keyId, String keyName, String keyDescription, String tableName, String moduleName,
        String menuName, String voucherTypeShortName, String voucherTypeDescription, String controlText,
        DmTransactionMode mode, String modelClassName, String oldData, String newData) {

    /** Master data record: one key, no voucher type. */
    public static ChangeLogEntry master(String keyId, String keyName, String tableName, String moduleName,
            String menuName, DmTransactionMode mode, String modelClassName, String oldData, String newData) {
        return new ChangeLogEntry(keyId, keyName, keyName, tableName, moduleName, menuName, "", "", "", mode,
                modelClassName, oldData, newData);
    }
}
