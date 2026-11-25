package com.dataman.base.serial;

/**
 * Voucher number (legacy RecId): the final text, the counter it came from and the fixed part
 * before the counter (also the key of the counter row in numberSystemCounter.nsFormatValue).
 */
public record RecId(String value, Long counter, String prefix) {

    /** A voucher number typed by the user (no counter generation). */
    public static RecId manual(String value) {
        return new RecId(value, null, null);
    }

    /** Nothing given yet: the service generates one. */
    public static RecId auto() {
        return new RecId("", null, null);
    }
}
