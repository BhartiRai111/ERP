package com.dataman.base.paging;

import com.dataman.base.exception.DmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Builds the SQL of a paged query the same way the legacy DmHelper/evaluateHelper did:
 * {@code Select * From (<base>) As T Where <filters> Order By ... Offset n Rows Fetch Next m Rows Only}.
 * Column names of filters/sorts are checked against a whitelist (no SQL injection).
 */
public final class PagedSql {

    private final String filteredSql;
    private final Map<String, Object> params;
    private final String orderBy;

    private PagedSql(String filteredSql, Map<String, Object> params, String orderBy) {
        this.filteredSql = filteredSql;
        this.params = params;
        this.orderBy = orderBy;
    }

    /**
     * @param baseSql        the entry's own select (named params allowed)
     * @param baseParams     params of baseSql
     * @param allowedColumns columns of baseSql that may be filtered/sorted
     * @param defaultSort    used when the query has no sort (SQL Server paging needs an Order By)
     */
    public static PagedSql build(String baseSql, Map<String, Object> baseParams, Set<String> allowedColumns,
                                 Sort defaultSort, PageQuery q) {
        Set<String> allowed = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        allowed.addAll(allowedColumns);
        Map<String, Object> params = new HashMap<>(baseParams == null ? Map.of() : baseParams);

        StringBuilder cond = new StringBuilder();
        int idx = 0;
        for (Filter f : q.filters()) {
            if (f == null || f.column() == null || f.column().isBlank()) {
                continue;
            }
            checkColumn(allowed, f.column());
            String p = "dmPageFilter" + (++idx);
            if (cond.length() > 0) {
                cond.append(f.operand() == Operand.OR ? "Or " : "And ");
            }
            String v = f.value() == null ? "" : f.value();
            switch (f.operator()) {
                case EQUAL -> {
                    cond.append(f.column()).append(" = :").append(p).append(' ');
                    params.put(p, v);
                }
                case CONTAINS -> {
                    cond.append(f.column()).append(" Like :").append(p).append(' ');
                    params.put(p, "%" + v + "%");
                }
                case BEGINS_WITH -> {
                    cond.append(f.column()).append(" Like :").append(p).append(' ');
                    params.put(p, v + "%");
                }
            }
        }
        String filtered = "Select * From ( " + baseSql + ") As T ";
        if (cond.length() > 0) {
            filtered += "Where " + cond;
        }

        List<String> parts = new ArrayList<>();
        for (Sort s : q.sorts()) {
            if (s != null && s.column() != null && !s.column().isBlank()) {
                checkColumn(allowed, s.column());
                parts.add(s.column().trim() + " " + (s.type() == null ? SortType.ASC : s.type()).name());
            }
        }
        if (parts.isEmpty() && defaultSort != null) {
            checkColumn(allowed, defaultSort.column());
            parts.add(defaultSort.column() + " " + defaultSort.type().name());
        }
        return new PagedSql(filtered, params, String.join(", ", parts));
    }

    private static void checkColumn(Set<String> allowed, String column) {
        if (!allowed.contains(column.trim())) {
            throw new DmException("Invalid column: " + column);
        }
    }

    public Map<String, Object> params() {
        return params;
    }

    public String countSql() {
        return "Select Count(*) As Cnt From ( " + filteredSql + ") As T1 ";
    }

    /** All rows (no paging), ordered when a sort is known. */
    public String allRowsSql() {
        return orderBy.isEmpty() ? filteredSql : filteredSql + "Order By " + orderBy + " ";
    }

    public String pageSql(int rowsStartFrom, int rowsPerPage) {
        if (rowsPerPage <= 0) {
            return allRowsSql();
        }
        String ob = orderBy.isEmpty() ? "(Select 0)" : orderBy;
        return filteredSql + "Order By " + ob + " Offset " + Math.max(0, rowsStartFrom)
                + " Rows Fetch Next " + rowsPerPage + " Rows Only ";
    }

    /** Legacy "to end of file": start of the last page for the given row count. */
    public static int lastPageStart(int rowCount, int rowsPerPage) {
        if (rowsPerPage <= 0) {
            return 0;
        }
        int start = (rowCount / rowsPerPage) * rowsPerPage;
        if (rowCount % rowsPerPage == 0) {
            start -= rowsPerPage;
        }
        return Math.max(0, start);
    }

    static String lower(String s) {
        return s.toLowerCase(Locale.ROOT);
    }
}
