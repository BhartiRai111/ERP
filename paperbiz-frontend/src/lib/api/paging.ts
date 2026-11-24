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
