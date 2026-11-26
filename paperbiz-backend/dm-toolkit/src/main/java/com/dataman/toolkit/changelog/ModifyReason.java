package com.dataman.toolkit.changelog;

/** The reason a user gives when changing or deleting a record (code/name from modifyReasonMast, free remark). */
public record ModifyReason(String code, String name, String remark) {
}
