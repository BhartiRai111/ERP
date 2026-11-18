package com.dataman.base.paging;

import java.util.List;

/** Replacement of the legacy DmResultPageing + DmPaging flags. */
public record PageResult<T>(List<T> items, int rowsStartFrom, int rowsPerPage, int rowCount,
                            boolean beginOfFile, boolean endOfFile) {
}
