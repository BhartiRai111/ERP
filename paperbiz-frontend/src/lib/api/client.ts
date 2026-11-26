import { ApiRequestError, isApiErrorBody } from "./errors";
import { devContextHeaders, getDevContext } from "../session/devContext";
import { EVT_LOCKED, EVT_UNAUTHORIZED, emit } from "../session/events";
import { getToken } from "../session/tokenStore";

export const API_BASE_URL = (process.env.NEXT_PUBLIC_API_BASE_URL ?? "http://localhost:8080").replace(/\/$/, "");

export interface ApiOptions {
  method?: "GET" | "POST" | "PUT" | "DELETE";
  body?: unknown;
  query?: URLSearchParams | Record<string, string | number | boolean | undefined>;
  signal?: AbortSignal;
}

function toSearch(query: ApiOptions["query"]): string {
  if (!query) return "";
  const p =
    query instanceof URLSearchParams
      ? query
      : new URLSearchParams(Object.entries(query).filter(([, v]) => v !== undefined).map(([k, v]) => [k, String(v)]));
  const s = p.toString();
  return s ? `?${s}` : "";
}

/**
 * Calls the backend. Failure (non-2xx) throws ApiRequestError carrying the backend's
 * `{errorNumber, message}` (messages are the legacy ones, shown to the user as they are).
 */
export async function apiFetch<T>(path: string, opts: ApiOptions = {}): Promise<T> {
  const token = getToken();
  const headers: Record<string, string> = { Accept: "application/json" };
  if (token) headers.Authorization = `Bearer ${token}`;
  else Object.assign(headers, devContextHeaders(getDevContext()));
  let body: string | undefined;
  if (opts.body !== undefined) {
    headers["Content-Type"] = "application/json";
    body = JSON.stringify(opts.body);
  }
  let res: Response;
  try {
    res = await fetch(`${API_BASE_URL}${path}${toSearch(opts.query)}`, {
      method: opts.method ?? "GET",
      headers,
      body,
      signal: opts.signal,
    });
  } catch (e) {
    throw new ApiRequestError(0, 0, e instanceof Error ? e.message : "Network error");
  }
  const text = await res.text();
  let json: unknown;
  try {
    json = text ? JSON.parse(text) : undefined;
  } catch {
    json = undefined;
  }
  if (!res.ok) {
    if (token && res.status === 401) emit(EVT_UNAUTHORIZED);
    if (token && res.status === 423) emit(EVT_LOCKED);
    if (isApiErrorBody(json)) throw new ApiRequestError(res.status, json.errorNumber, json.message);
    throw new ApiRequestError(res.status, 0, text || res.statusText || "Request failed");
  }
  return json as T;
}
