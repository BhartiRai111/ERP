import type { PageQuery } from "./types";

/**
 * Turns a PageQuery into URL search params for `GET .../find` and `.../lookups/{name}`:
 * start, size, toEnd, f=<column>|<operator>|<value>[|<operand>] (repeated), s=<column>|<ASC|DESC> (repeated).
 */
export function pageQueryToParams(q: PageQuery): URLSearchParams {
  const p = new URLSearchParams();
  p.set("start", String(Math.max(0, q.rowsStartFrom)));
  p.set("size", String(q.rowsPerPage));
  if (q.toEndOfFile) p.set("toEnd", "true");
  for (const f of q.filters ?? []) {
    p.append("f", [f.column, f.operator, f.value, f.operand ?? "AND"].join("|"));
  }
  for (const s of q.sorts ?? []) {
    p.append("s", `${s.column}|${s.type}`);
  }
  return p;
}

/** Query string of the backend's Find / lookup / suggestion calls (`PageParams`): start, size, one search column, sort. */
export interface SearchOptions {
  start?: number;
  size?: number;
  /** Column and text for a "begins with" search. */
  column?: string;
  q?: string;
  sort?: string;
  desc?: boolean;
  /** Jump to the last page. */
  end?: boolean;
}

export function searchParams(o: SearchOptions): Record<string, string | number | boolean | undefined> {
  const out: Record<string, string | number | boolean | undefined> = {
    start: o.start ?? 0,
    size: o.size ?? 20,
  };
  if (o.column && o.q) {
    out.column = o.column;
    out.q = o.q;
  }
  if (o.sort) out.sort = o.sort;
  if (o.desc) out.desc = true;
  if (o.end) out.end = true;
  return out;
}
