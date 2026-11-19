package com.dataman.base.permission;

/** Per voucher type limits (legacy VoucherPermission): earliest dates allowed and highest value allowed. */
public record VoucherPermission(String category, String voucherType, String viewDate, String deleteDate,
                                String saveDate, double allowValueUpto) {
}
