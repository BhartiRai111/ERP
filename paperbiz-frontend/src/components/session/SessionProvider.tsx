"use client";

import { useQuery, useQueryClient } from "@tanstack/react-query";
import { createContext, useCallback, useContext, useEffect, useMemo, useState, type ReactNode } from "react";
import { EVT_LOCKED, EVT_UNAUTHORIZED } from "@/lib/session/events";
import { authApi } from "@/lib/session/authApi";
import { getToken, setToken } from "@/lib/session/tokenStore";
import type { Selection, UserPermissions } from "@/lib/session/types";
import { nextStep } from "@/lib/session/selection";

type Status = "loading" | "anonymous" | "selecting" | "ready" | "locked";

interface SessionValue {
  status: Status;
  selection: Selection | null;
  permissions: UserPermissions | undefined;
  login: (userName: string, password: string) => Promise<void>;
  choose: (pltCode?: string, companyCode?: string, siteCode?: string) => Promise<void>;
  logout: () => Promise<void>;
  lock: () => Promise<void>;
  unlock: (password: string) => Promise<void>;
}

const Ctx = createContext<SessionValue | null>(null);

export function useSession(): SessionValue {
  const v = useContext(Ctx);
  if (!v) throw new Error("useSession must be used inside SessionProvider");
  return v;
}

export function SessionProvider({ children }: { children: ReactNode }) {
  const qc = useQueryClient();
  const [selection, setSelection] = useState<Selection | null>(null);
  const [locked, setLocked] = useState(false);
  const [loading, setLoading] = useState(() => getToken() !== null);

  const apply = useCallback((s: Selection) => {
    setToken(s.token);
    setSelection(s);
  }, []);

  const clear = useCallback(() => {
    setToken(null);
    setSelection(null);
    setLocked(false);
    qc.clear();
  }, [qc]);

  // a page reload keeps the token: ask the server who we are
  useEffect(() => {
    if (getToken() === null) return;
    authApi
      .me()
      .then(apply)
      .catch(() => clear())
      .finally(() => setLoading(false));
  }, [apply, clear]);

  useEffect(() => {
    const onLost = () => clear();
    const onLocked = () => setLocked(true);
    window.addEventListener(EVT_UNAUTHORIZED, onLost);
    window.addEventListener(EVT_LOCKED, onLocked);
    return () => {
      window.removeEventListener(EVT_UNAUTHORIZED, onLost);
      window.removeEventListener(EVT_LOCKED, onLocked);
    };
  }, [clear]);

  const status: Status = loading ? "loading" : !selection ? "anonymous" : locked ? "locked" : nextStep(selection) === "ready" ? "ready" : "selecting";

  const permissionsQuery = useQuery({
    queryKey: ["permissions", selection?.companySelected?.code, selection?.siteSelected?.code],
    queryFn: authApi.permissions,
    enabled: status === "ready",
    staleTime: Infinity,
  });

  const value = useMemo<SessionValue>(
    () => ({
      status,
      selection,
      permissions: permissionsQuery.data,
      login: async (u, p) => {
        qc.clear();
        apply(await authApi.login(u, p));
      },
      choose: async (plt, company, site) => {
        qc.removeQueries({ queryKey: ["permissions"] });
        apply(await authApi.select(plt, company, site));
      },
      logout: async () => {
        try {
          await authApi.logout();
        } catch {
          /* the session may already be gone on the server */
        }
        clear();
      },
      lock: async () => {
        await authApi.lock();
        setLocked(true);
      },
      unlock: async (password) => {
        await authApi.unlock(password);
        setLocked(false);
      },
    }),
    [status, selection, permissionsQuery.data, qc, apply, clear],
  );

  return <Ctx.Provider value={value}>{children}</Ctx.Provider>;
}
