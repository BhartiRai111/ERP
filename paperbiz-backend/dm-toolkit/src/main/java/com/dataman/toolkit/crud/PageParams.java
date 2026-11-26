package com.dataman.toolkit.crud;

import com.dataman.base.paging.Filter;
import com.dataman.base.paging.Operand;
import com.dataman.base.paging.Operator;
import com.dataman.base.paging.PageQuery;
import com.dataman.base.paging.Sort;
import com.dataman.base.paging.SortType;
import java.util.List;

/**
 * The query string of Find, lookup and suggestion calls turned into a {@link PageQuery}:
 * {@code start}, {@code size} (0 = all), {@code column} + {@code q} (begins with), {@code sort} (column), {@code desc},
 * {@code end} (jump to the last page). Columns are checked against the query's whitelist later by {@code PagedSql}.
 */
public final class PageParams {

    private PageParams() {
    }

    public static PageQuery of(Integer start, Integer size, String column, String q, String sort, Boolean desc, Boolean end) {
        List<Filter> filters = column == null || column.isBlank() || q == null || q.isEmpty() ? List.of()
                : List.of(new Filter(column, Operator.BEGINS_WITH, q, Operand.AND));
        List<Sort> sorts = sort == null || sort.isBlank() ? List.of()
                : List.of(new Sort(sort, Boolean.TRUE.equals(desc) ? SortType.DESC : SortType.ASC));
        int rows = size == null ? 20 : Math.max(0, Math.min(size, 500));
        return new PageQuery(start == null ? 0 : Math.max(0, start), rows, filters, sorts, Boolean.TRUE.equals(end));
    }
}
