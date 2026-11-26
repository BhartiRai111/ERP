import { apiFetch } from "../api/client";
import type { Selection, UserPermissions } from "./types";

export type LoginMode = "USERNAME" | "EMAIL" | "MOBILE";

export const authApi = {
  login: (userName: string, password: string, mode: LoginMode = "USERNAME") =>
    apiFetch<Selection>("/api/auth/login", { method: "POST", body: { userName, password, mode } }),
  select: (pltCode?: string, companyCode?: string, siteCode?: string) =>
    apiFetch<Selection>("/api/auth/select", { method: "POST", body: { pltCode, companyCode, siteCode } }),
  me: () => apiFetch<Selection>("/api/auth/me"),
  lock: () => apiFetch<void>("/api/auth/lock", { method: "POST" }),
  unlock: (password: string) => apiFetch<void>("/api/auth/unlock", { method: "POST", body: { password } }),
  logout: () => apiFetch<void>("/api/auth/logout", { method: "POST" }),
  permissions: () => apiFetch<UserPermissions>("/api/permissions"),
};
