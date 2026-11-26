import { apiFetch } from "../api/client";
import { searchParams, type SearchOptions } from "../api/paging";
import type { PageResult } from "../api/types";

export interface ModifyReason {
  code: string;
  name: string;
  remark: string;
}

/** The common REST contract of every entry (docs/design/library-design.md section 3). */
export function createEntryApi<T, FindRow = unknown, Lookup = { code: string; name: string }>(base: string) {
  return {
    defaults: () => apiFetch<Record<string, string>>(`${base}/defaults`),
    navigator: () => apiFetch<string[]>(`${base}/navigator`),
    open: (code: string) => apiFetch<T>(`${base}/${encodeURIComponent(code)}`),
    create: (body: T) => apiFetch<{ code: string }>(base, { method: "POST", body }),
    update: (code: string, body: T) => apiFetch<{ code: string }>(`${base}/${encodeURIComponent(code)}`, { method: "PUT", body }),
    remove: (code: string, reason: ModifyReason) =>
      apiFetch<void>(`${base}/${encodeURIComponent(code)}`, { method: "DELETE", body: reason }),
    find: (o: SearchOptions) => apiFetch<PageResult<FindRow>>(`${base}/find`, { query: searchParams(o) }),
    lookup: (name: string, o: SearchOptions) =>
      apiFetch<PageResult<Lookup>>(`${base}/lookups/${name}`, { query: searchParams(o) }),
    suggestions: (o: SearchOptions) => apiFetch<PageResult<string>>(`${base}/suggestions`, { query: searchParams(o) }),
  };
}

export const modifyReasonApi = {
  list: (o: SearchOptions) => apiFetch<PageResult<{ code: string; name: string }>>("/api/modify-reasons", { query: searchParams(o) }),
};
