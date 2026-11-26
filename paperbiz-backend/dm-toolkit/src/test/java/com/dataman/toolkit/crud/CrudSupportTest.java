package com.dataman.toolkit.crud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.paging.Operator;
import com.dataman.base.paging.PageQuery;
import com.dataman.base.paging.SortType;
import com.dataman.base.permission.MenuPermission;
import com.dataman.toolkit.crud.EntryGuard.Action;
import org.junit.jupiter.api.Test;

class CrudSupportTest {

    @Test
    void queryStringBecomesPageQuery() {
        PageQuery q = PageParams.of(40, 20, "name", "kg", "name", true, false);
        assertEquals(40, q.rowsStartFrom());
        assertEquals(20, q.rowsPerPage());
        assertEquals(Operator.BEGINS_WITH, q.filters().get(0).operator());
        assertEquals("kg", q.filters().get(0).value());
        assertEquals(SortType.DESC, q.sorts().get(0).type());
    }

    @Test
    void defaultsAndLimits() {
        PageQuery q = PageParams.of(null, null, null, null, null, null, null);
        assertEquals(0, q.rowsStartFrom());
        assertEquals(20, q.rowsPerPage());
        assertTrue(q.filters().isEmpty() && q.sorts().isEmpty() && !q.toEndOfFile());
        assertEquals(500, PageParams.of(0, 100000, null, null, null, null, null).rowsPerPage());
        assertEquals(0, PageParams.of(-5, -1, null, null, null, null, null).rowsStartFrom());
        assertTrue(PageParams.of(0, 10, "name", "", null, null, null).filters().isEmpty(), "blank search = no filter");
    }

    @Test
    void permissionLettersDecideTheAction() {
        MenuPermission aep = MenuPermission.of("Unit", "AEP");
        assertTrue(EntryGuard.allowed(aep, Action.VIEW));
        assertTrue(EntryGuard.allowed(aep, Action.ADD));
        assertTrue(EntryGuard.allowed(aep, Action.EDIT));
        assertFalse(EntryGuard.allowed(aep, Action.DELETE));
        assertTrue(EntryGuard.allowed(aep, Action.PRINT));
    }
}
