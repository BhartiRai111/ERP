/** Error body returned by the backend for every failed request (dm-base `ApiError`). */
export interface ApiErrorBody {
  /** Error number; the screen focuses the field it maps to (legacy `isValidText(..., N)`). */
  errorNumber: number;
  message: string;
}

export type Operator = "BEGINS_WITH" | "CONTAINS" | "EQUAL";
export type Operand = "AND" | "OR";
export type SortType = "ASC" | "DESC";

export interface Filter {
  column: string;
  operator: Operator;
  value: string;
  operand?: Operand;
}

export interface Sort {
  column: string;
  type: SortType;
}

/** Same as backend `PageQuery` (legacy DmHelper). `rowsPerPage <= 0` means "no paging". */
export interface PageQuery {
  rowsStartFrom: number;
  rowsPerPage: number;
  filters?: Filter[];
  sorts?: Sort[];
  toEndOfFile?: boolean;
}

/** Same as backend `PageResult` (legacy DmResultPageing + DmPaging flags). */
export interface PageResult<T> {
  items: T[];
  rowsStartFrom: number;
  rowsPerPage: number;
  rowCount: number;
  beginOfFile: boolean;
  endOfFile: boolean;
}
