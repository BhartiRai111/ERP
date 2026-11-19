package com.dataman.base.permission;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MenuPermissionTest {

    @Test
    void lettersMapToActions() {
        MenuPermission p = MenuPermission.of("Unit Master", "AED");
        assertTrue(p.canAdd());
        assertTrue(p.canEdit());
        assertTrue(p.canDelete());
        assertFalse(p.canPrint());
    }

    @Test
    void nullPermissionAllowsNothing() {
        MenuPermission p = MenuPermission.of("X", null);
        assertFalse(p.canAdd() || p.canEdit() || p.canDelete() || p.canPrint());
    }
}
