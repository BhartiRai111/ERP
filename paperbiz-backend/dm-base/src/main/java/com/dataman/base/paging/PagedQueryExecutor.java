package com.dataman.base.paging;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/** Runs a paged query (Find popup, lookups, suggestions) and returns the legacy-style paging flags. */
@Component
public class PagedQueryExecutor {

    private final NamedParameterJdbcTemplate jdbc;

    public PagedQueryExecutor(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public <T> PageResult<T> query(String baseSql, Map<String, Object> baseParams, Set<String> allowedColumns,
                                   Sort defaultSort, PageQuery q, RowMapper<T> mapper) {
        PagedSql sql = PagedSql.build(baseSql, baseParams, allowedColumns, defaultSort, q);
        int start = Math.max(0, q.rowsStartFrom());
        int size = q.rowsPerPage();
        int rowCount = 0;
        if (q.toEndOfFile() && size > 0) {
            Integer cnt = jdbc.queryForObject(sql.countSql(), sql.params(), Integer.class);
            rowCount = cnt == null ? 0 : cnt;
            start = PagedSql.lastPageStart(rowCount, size);
        }
        List<T> items = jdbc.query(sql.pageSql(start, size), sql.params(), mapper);
        boolean endOfFile = size <= 0 || items.size() < size || q.toEndOfFile();
        boolean beginOfFile = start <= 0;
        return new PageResult<>(items, start, size, rowCount, beginOfFile, endOfFile);
    }
}
