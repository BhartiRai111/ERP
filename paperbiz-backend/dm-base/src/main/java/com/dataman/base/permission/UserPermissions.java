package com.dataman.base.permission;

import java.util.List;
import java.util.Map;

/** All permissions of a user (legacy UserPermission): menus grouped by module + voucher limits. */
public record UserPermissions(Map<String, List<MenuPermission>> menus, List<VoucherPermission> vouchers) {

    public UserPermissions {
        menus = menus == null ? Map.of() : Map.copyOf(menus);
        vouchers = vouchers == null ? List.of() : List.copyOf(vouchers);
    }
}
