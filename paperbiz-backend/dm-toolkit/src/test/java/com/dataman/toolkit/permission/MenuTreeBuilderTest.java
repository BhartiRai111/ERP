package com.dataman.toolkit.permission;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.permission.MenuPermission;
import com.dataman.base.permission.UserPermissions;
import com.dataman.toolkit.permission.MenuTreeBuilder.Row;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class MenuTreeBuilderTest {

    private static Row row(String module, String name, int level, String parent, String perm) {
        return new Row(module, name, name + " text", level, parent, "", perm, null, null, null, 0, "");
    }

    @Test
    void buildsTreePerModuleInRowOrder() {
        List<Row> rows = List.of(
                row("Master", "Masters", 0, "", ""),
                row("Master", "Unit", 1, "Masters", "AEDP"),
                row("Master", "Item", 1, "Masters", "AE"),
                row("Sales", "Orders", 0, "", ""),
                row("Master", "ItemGroup", 2, "Item", "A"));
        Map<String, List<MenuPermission>> tree = MenuTreeBuilder.build(rows, Map.of("master|Item", List.of("Approve")));
        assertEquals(List.of("master", "sales"), List.copyOf(tree.keySet()));
        MenuPermission masters = tree.get("master").get(0);
        assertEquals(List.of("Unit", "Item"), masters.children().stream().map(MenuPermission::menuName).toList());
        MenuPermission item = masters.children().get(1);
        assertEquals(List.of("Approve"), item.controlPermissions());
        assertEquals("ItemGroup", item.children().get(0).menuName());
        assertTrue(masters.children().get(0).canDelete() && !item.canDelete());
    }

    @Test
    void loopInBadDataDoesNotHang() {
        List<Row> rows = List.of(row("M", "A", 0, "", ""), row("M", "B", 1, "A", ""), row("M", "A", 2, "B", ""));
        assertEquals(1, MenuTreeBuilder.build(rows, Map.of()).get("m").size());
    }

    @Test
    void findsMenuAnywhereInTheModule() {
        List<Row> rows = List.of(row("Master", "Masters", 0, "", ""), row("Master", "Unit", 1, "Masters", "AE"));
        UserPermissions up = new UserPermissions(MenuTreeBuilder.build(rows, Map.of()), List.of());
        assertTrue(PermissionService.find(up, "MASTER", "unit").orElseThrow().canEdit());
        assertTrue(PermissionService.find(up, "Master", "nope").isEmpty());
        assertTrue(PermissionService.find(up, "Other", "Unit").isEmpty());
    }
}
