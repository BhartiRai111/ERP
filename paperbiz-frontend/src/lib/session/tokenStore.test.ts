import { afterEach, describe, expect, it, vi } from "vitest";
import { API_BASE_URL, apiFetch } from "../api/client";
import { EVT_LOCKED, EVT_UNAUTHORIZED } from "./events";
import { getToken, resetTokenStoreForTests, setToken, subscribeToken } from "./tokenStore";

afterEach(() => {
  window.sessionStorage.clear();
  resetTokenStoreForTests();
  vi.restoreAllMocks();
});

describe("token store", () => {
  it("keeps the token across a reload (sessionStorage) and tells listeners", () => {
    const seen = vi.fn();
    subscribeToken(seen);
    setToken("abc");
    expect(getToken()).toBe("abc");
    expect(seen).toHaveBeenCalledTimes(1);
    resetTokenStoreForTests();   // like a page reload: memory is gone, storage stays
    expect(getToken()).toBe("abc");
    setToken(null);
    expect(window.sessionStorage.getItem("pb.token")).toBeNull();
  });
});

describe("apiFetch with a token", () => {
  function mockFetch(status: number, body: unknown) {
    return vi.spyOn(globalThis, "fetch").mockResolvedValue(new Response(JSON.stringify(body), { status }));
  }

  it("sends the bearer token and no dev headers", async () => {
    setToken("tok");
    const f = mockFetch(200, { ok: 1 });
    await apiFetch("/api/x");
    const init = f.mock.calls[0][1] as RequestInit;
    expect((init.headers as Record<string, string>).Authorization).toBe("Bearer tok");
    expect(f.mock.calls[0][0]).toBe(`${API_BASE_URL}/api/x`);
  });

  it("announces a lost session on 401 and a locked one on 423", async () => {
    setToken("tok");
    const un = vi.fn();
    const lk = vi.fn();
    window.addEventListener(EVT_UNAUTHORIZED, un);
    window.addEventListener(EVT_LOCKED, lk);
    mockFetch(401, { errorNumber: 0, message: "Session expired. Please login again." });
    await expect(apiFetch("/api/x")).rejects.toThrow("Session expired");
    mockFetch(423, { errorNumber: 0, message: "Session is locked." });
    await expect(apiFetch("/api/x")).rejects.toThrow("locked");
    expect(un).toHaveBeenCalledTimes(1);
    expect(lk).toHaveBeenCalledTimes(1);
    window.removeEventListener(EVT_UNAUTHORIZED, un);
    window.removeEventListener(EVT_LOCKED, lk);
  });

  it("a wrong login (401 without a token) does not announce anything", async () => {
    const un = vi.fn();
    window.addEventListener(EVT_UNAUTHORIZED, un);
    mockFetch(400, { errorNumber: 0, message: "Access denied" });
    await expect(apiFetch("/api/auth/login", { method: "POST", body: {} })).rejects.toThrow("Access denied");
    expect(un).not.toHaveBeenCalled();
    window.removeEventListener(EVT_UNAUTHORIZED, un);
  });
});
