package com.dataman.base.paging;

import java.util.List;

/**
 * Replacement of the legacy DmHelper (paging + filter + sort).
 * {@code rowsPerPage <= 0} means "no paging" (all rows), like the legacy helper.
 * {@code toEndOfFile} = jump to the last page (legacy blnToEndOfFile).
 */
public record PageQuery(int rowsStartFrom, int rowsPerPage, List<Filter> filters, List<Sort> sorts, boolean toEndOfFile) {

    public PageQuery {
        filters = filters == null ? List.of() : List.copyOf(filters);
        sorts = sorts == null ? List.of() : List.copyOf(sorts);
    }

    public static PageQuery of(int rowsStartFrom, int rowsPerPage) {
        return new PageQuery(rowsStartFrom, rowsPerPage, List.of(), List.of(), false);
    }
}
