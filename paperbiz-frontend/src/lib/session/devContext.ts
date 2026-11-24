/**
 * TEMPORARY (until JWT login exists): who is calling and for which company/site.
 * Sent as X-Dm-* headers, exactly what the backend `UserContextFilter` reads.
 */
export interface DevContext {
  user: string;
  companyDb: string;
  site: string;
}

const KEY = "pb.devContext";

export function getDevContext(): DevContext | null {
  try {
    const raw = typeof window === "undefined" ? null : window.localStorage.getItem(KEY);
    if (!raw) return null;
    const v = JSON.parse(raw) as Partial<DevContext>;
    if (typeof v.companyDb !== "string" || v.companyDb === "") return null;
    return { user: v.user ?? "", companyDb: v.companyDb, site: v.site ?? "" };
  } catch {
    return null;
  }
}

export function setDevContext(ctx: DevContext | null): void {
  try {
    if (ctx === null) window.localStorage.removeItem(KEY);
    else window.localStorage.setItem(KEY, JSON.stringify(ctx));
  } catch {
    /* storage blocked: the app still renders, calls just have no company context */
  }
}

export function devContextHeaders(ctx: DevContext | null): Record<string, string> {
  if (!ctx) return {};
  return { "X-Dm-User": ctx.user, "X-Dm-Company-Db": ctx.companyDb, "X-Dm-Site": ctx.site };
}
