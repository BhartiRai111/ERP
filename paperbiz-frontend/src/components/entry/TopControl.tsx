"use client";

import { useEffect } from "react";
import { Button } from "@/components/ui/button";
import { hotKeyToButton, visibleButtons, type ButtonId, type ButtonRules, type EntryMode } from "@/lib/entry/topControl";

/** Button bar on top of every entry (legacy DmTopControl) with its hot keys. */
export function TopControl({ mode, rules, onButton }: { mode: EntryMode; rules: ButtonRules; onButton: (b: ButtonId) => void }) {
  const buttons = visibleButtons(mode, rules);

  useEffect(() => {
    function onKey(e: KeyboardEvent) {
      const b = hotKeyToButton(e, mode);
      if (b && buttons.includes(b)) {
        e.preventDefault();
        onButton(b);
      }
    }
    window.addEventListener("keydown", onKey);
    return () => window.removeEventListener("keydown", onKey);
  });

  return (
    <div className="flex flex-wrap items-center gap-1 border-b pb-2" role="toolbar" aria-label="Entry buttons">
      {buttons.map((b) => (
        <Button key={b} size="sm" variant={b === "Save" ? "default" : "outline"} onClick={() => onButton(b)}>{b}</Button>
      ))}
      {mode && <span className="ml-auto text-sm text-muted-foreground" data-testid="entry-mode">{mode}</span>}
    </div>
  );
}
