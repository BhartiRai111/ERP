package com.dataman.base.paging;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.exception.DmException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

class PagedSqlTest {

    private static final Set<String> COLS = Set.of("code", "name");
    private static final Sort DEFAULT = new Sort("name", SortType.ASC);

    @Test
    void wrapsFiltersSortAndOffsetLikeLegacy() {
        PageQuery q = new PageQuery(20, 10, List.of(new Filter("name", Operator.CONTAINS, "ab")),
                List.of(new Sort("code", SortType.DESC)), false);
        PagedSql s = PagedSql.build("SELECT code,name FROM unitmast", Map.of(), COLS, DEFAULT, q);
        assertEquals("Select * From ( SELECT code,name FROM unitmast) As T Where name Like :dmPageFilter1 "
                + "Order By code DESC Offset 20 Rows Fetch Next 10 Rows Only ", s.pageSql(20, 10));
        assertEquals("%ab%", s.params().get("dmPageFilter1"));
    }

    @Test
    void multipleFiltersAreJoinedWithOperand() {
        PageQuery q = new PageQuery(0, 10, List.of(new Filter("code", Operator.EQUAL, "1"),
                new Filter("name", Operator.BEGINS_WITH, "x", Operand.OR)), List.of(), false);
        String sql = PagedSql.build("SELECT 1", Map.of(), COLS, DEFAULT, q).pageSql(0, 10);
        assertTrue(sql.contains("code = :dmPageFilter1 Or name Like :dmPageFilter2"), sql);
    }

    @Test
    void defaultSortUsedWhenNoSort() {
        String sql = PagedSql.build("SELECT 1", Map.of(), COLS, DEFAULT, PageQuery.of(0, 5)).pageSql(0, 5);
        assertTrue(sql.contains("Order By name ASC"), sql);
    }

    @Test
    void noPagingWhenRowsPerPageIsZero() {
        String sql = PagedSql.build("SELECT 1", Map.of(), COLS, DEFAULT, PageQuery.of(0, 0)).pageSql(0, 0);
        assertTrue(!sql.contains("Offset"), sql);
    }

    @Test
    void unknownColumnIsRejected() {
        PageQuery q = new PageQuery(0, 5, List.of(new Filter("name; drop table x", Operator.EQUAL, "1")), List.of(), false);
        assertThrows(DmException.class, () -> PagedSql.build("SELECT 1", Map.of(), COLS, DEFAULT, q));
    }

    @Test
    void lastPageStartMatchesLegacy() {
        assertEquals(20, PagedSql.lastPageStart(25, 10));
        assertEquals(20, PagedSql.lastPageStart(30, 10));
        assertEquals(0, PagedSql.lastPageStart(0, 10));
        assertEquals(0, PagedSql.lastPageStart(7, 10));
    }
}
