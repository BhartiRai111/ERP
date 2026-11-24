import { afterEach, describe, expect, it, vi } from "vitest";
import { apiFetch } from "./client";
import { ApiRequestError } from "./errors";
import { pageQueryToParams } from "./paging";
import { setDevContext } from "../session/devContext";

afterEach(() => {
  vi.restoreAllMocks();
  window.localStorage.clear();
});

function mockFetch(status: number, body: unknown) {
  return vi.spyOn(globalThis, "fetch").mockResolvedValue(
    new Response(body === undefined ? "" : JSON.stringify(body), { status, headers: { "Content-Type": "application/json" } }),
  );
}

describe("apiFetch", () => {
  it("returns the JSON body and sends the temporary X-Dm-* headers", async () => {
    setDevContext({ user: "sam", companyDb: "Comp1", site: "1" });
    const spy = mockFetch(200, { ok: true });
    await expect(apiFetch<{ ok: boolean }>("/api/x", { query: { a: 1 } })).resolves.toEqual({ ok: true });
    const [url, init] = spy.mock.calls[0];
    expect(String(url)).toContain("/api/x?a=1");
    expect((init as RequestInit).headers).toMatchObject({ "X-Dm-User": "sam", "X-Dm-Company-Db": "Comp1", "X-Dm-Site": "1" });
  });

  it("throws ApiRequestError with the backend errorNumber and message", async () => {
    mockFetch(400, { errorNumber: 300, message: "Unit Name is mandatory." });
    await expect(apiFetch("/api/x", { method: "POST", body: {} })).rejects.toMatchObject({
      name: "ApiRequestError",
      status: 400,
      errorNumber: 300,
      message: "Unit Name is mandatory.",
    });
  });

  it("wraps network failures", async () => {
    vi.spyOn(globalThis, "fetch").mockRejectedValue(new Error("offline"));
    await expect(apiFetch("/api/x")).rejects.toBeInstanceOf(ApiRequestError);
  });
});

describe("pageQueryToParams", () => {
  it("encodes start/size/filters/sorts", () => {
    const p = pageQueryToParams({
      rowsStartFrom: 20,
      rowsPerPage: 10,
      filters: [{ column: "name", operator: "BEGINS_WITH", value: "ab" }],
      sorts: [{ column: "name", type: "ASC" }],
      toEndOfFile: true,
    });
    expect(p.get("start")).toBe("20");
    expect(p.get("size")).toBe("10");
    expect(p.get("toEnd")).toBe("true");
    expect(p.getAll("f")).toEqual(["name|BEGINS_WITH|ab|AND"]);
    expect(p.getAll("s")).toEqual(["name|ASC"]);
  });
});
