"use client";

import { createContext, useCallback, useContext, useRef, useState, type ReactNode } from "react";
import { Button } from "@/components/ui/button";

interface DialogState {
  kind: "alert" | "confirm";
  message: string;
  resolve: (ok: boolean) => void;
}

interface DialogApi {
  /** Shows a message; resolves when Ok is pressed, then focus returns to `focus` (the field with the problem). */
  alert: (message: string, focus?: HTMLElement | null) => Promise<void>;
  /** Yes / No question. */
  confirm: (message: string) => Promise<boolean>;
}

const Ctx = createContext<DialogApi | null>(null);

export function useDialogs(): DialogApi {
  const v = useContext(Ctx);
  if (!v) throw new Error("useDialogs must be used inside DialogProvider");
  return v;
}

/** Alert and confirm boxes (legacy DmAlertMessageBox / DmConfirmMessageBox). */
export function DialogProvider({ children }: { children: ReactNode }) {
  const [state, setState] = useState<DialogState | null>(null);
  const focusAfter = useRef<HTMLElement | null>(null);

  const alert = useCallback(
    (message: string, focus?: HTMLElement | null) =>
      new Promise<void>((resolve) => {
        focusAfter.current = focus ?? null;
        setState({ kind: "alert", message, resolve: () => resolve() });
      }),
    [],
  );
  const confirm = useCallback(
    (message: string) =>
      new Promise<boolean>((resolve) => {
        focusAfter.current = null;
        setState({ kind: "confirm", message, resolve });
      }),
    [],
  );

  function close(ok: boolean) {
    const s = state;
    setState(null);
    s?.resolve(ok);
    const el = focusAfter.current;
    if (el) setTimeout(() => el.focus(), 0);
  }

  return (
    <Ctx.Provider value={{ alert, confirm }}>
      {children}
      {state && (
        <div className="fixed inset-0 z-[60] flex items-center justify-center bg-black/40" role="alertdialog" aria-modal="true" aria-label={state.message}>
          <div
            className="flex w-96 max-w-[90vw] flex-col gap-4 rounded-xl border bg-card p-5 shadow-lg"
            onKeyDown={(e) => {
              if (e.key === "Escape") close(false);
            }}
          >
            <p className="text-sm">{state.message}</p>
            <div className="flex justify-end gap-2">
              {state.kind === "confirm" ? (
                <>
                  <Button autoFocus onClick={() => close(true)}>Yes</Button>
                  <Button variant="outline" onClick={() => close(false)}>No</Button>
                </>
              ) : (
                <Button autoFocus onClick={() => close(true)}>Ok</Button>
              )}
            </div>
          </div>
        </div>
      )}
    </Ctx.Provider>
  );
}
