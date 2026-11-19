package com.dataman.base.permission;

import java.util.List;

/**
 * What a user may do on one menu item (legacy UserMenu). {@code permission} is a string of
 * letters: A = add/new, E = edit, D = delete, P = print. The date fields/limit are used by
 * vouchers (earliest date allowed to save/view/delete, highest value allowed to save).
 */
public record MenuPermission(String moduleName, String refModuleName, String menuText, String menuName,
                             String toolTip, String permission, String saveDate, String viewDate,
                             String deleteDate, double allowValueUpto, List<String> controlPermissions,
                             List<MenuPermission> children) {

    public MenuPermission {
        controlPermissions = controlPermissions == null ? List.of() : List.copyOf(controlPermissions);
        children = children == null ? List.of() : List.copyOf(children);
    }

    /** Menu with only a permission string (handy for tests and simple screens). */
    public static MenuPermission of(String menuText, String permission) {
        return new MenuPermission("", "", menuText, menuText, "", permission, null, null, null, 0, null, null);
    }

    private boolean has(char letter) {
        return permission != null && permission.indexOf(letter) >= 0;
    }

    public boolean canAdd() {
        return has('A');
    }

    public boolean canEdit() {
        return has('E');
    }

    public boolean canDelete() {
        return has('D');
    }

    public boolean canPrint() {
        return has('P');
    }
}
