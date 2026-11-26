package com.dataman.toolkit.permission;

import com.dataman.base.permission.MenuPermission;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Turns the flat menu rows of one user into the menu tree per module (legacy recursive getUserMenu). A row is a
 * child of the row of the same module whose {@code mnuName} equals its {@code parent}; top rows are level 0 with
 * no parent. Order is the row order (sNo).
 */
public final class MenuTreeBuilder {

    private MenuTreeBuilder() {
    }

    /** One row of user_Permission_Web joined with the menu short hand and the dates already worked out. */
    public record Row(String module, String menuName, String menuText, int level, String parent, String refModule,
            String permission, String viewDate, String saveDate, String deleteDate, double allowValueUpto,
            String toolTip) {
    }

    public static Map<String, List<MenuPermission>> build(List<Row> rows, Map<String, List<String>> controlGroups) {
        Map<String, List<MenuPermission>> byModule = new LinkedHashMap<>();
        for (Row r : rows) {
            byModule.computeIfAbsent(r.module().toLowerCase(), k -> new ArrayList<>());
        }
        for (String module : new ArrayList<>(byModule.keySet())) {
            List<Row> inModule = rows.stream().filter(r -> r.module().equalsIgnoreCase(module)).toList();
            byModule.put(module, children(inModule, "", 0, controlGroups, 0));
        }
        return byModule;
    }

    private static List<MenuPermission> children(List<Row> rows, String parent, int level,
            Map<String, List<String>> controlGroups, int depth) {
        List<MenuPermission> list = new ArrayList<>();
        if (depth > 12) {
            return list;   // a loop in bad menu data must not hang the login
        }
        for (Row r : rows) {
            if (r.level() == level && (r.parent() == null ? "" : r.parent()).equals(parent)) {
                List<MenuPermission> kids = children(rows, r.menuName(), level + 1, controlGroups, depth + 1);
                list.add(new MenuPermission(r.module(), r.refModule(), r.menuText(), r.menuName(), r.toolTip(),
                        r.permission(), r.saveDate(), r.viewDate(), r.deleteDate(), r.allowValueUpto(),
                        controlGroups.getOrDefault(r.module().toLowerCase() + "|" + r.menuName(), List.of()), kids));
            }
        }
        return list;
    }
}
