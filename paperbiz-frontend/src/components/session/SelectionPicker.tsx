"use client";

import { Button } from "@/components/ui/button";
import { nextStep } from "@/lib/session/selection";
import type { Selection } from "@/lib/session/types";

interface Props {
  selection: Selection;
  onChoose: (pltCode?: string, companyCode?: string, siteCode?: string) => void;
  busy?: boolean;
}

/** The "choose product / company-year / site" step after login: only the first thing not yet chosen is shown. */
export function SelectionPicker({ selection, onChoose, busy }: Props) {
  const step = nextStep(selection);
  const pltCode = selection.pltSelected?.code;
  const companyCode = selection.companySelected?.code;

  let title = "";
  let items: { key: string; label: string; sub?: string; pick: () => void }[] = [];
  if (step === "plt") {
    title = "Select company";
    items = selection.plts.map((p) => ({ key: p.code, label: p.name, pick: () => onChoose(p.code) }));
  } else if (step === "company") {
    title = "Select financial year";
    items = selection.companies.map((c) => ({
      key: c.code,
      label: c.currentYear || c.name,
      sub: c.name,
      pick: () => onChoose(pltCode, c.code),
    }));
  } else if (step === "site") {
    title = "Select site";
    items = selection.sites.map((s) => ({
      key: s.code,
      label: s.name,
      sub: s.branchName,
      pick: () => onChoose(pltCode, companyCode, s.code),
    }));
  }

  return (
    <div className="flex flex-col gap-3" data-testid="selection-picker">
      <h2 className="text-base font-medium">{title}</h2>
      {selection.message && <p className="text-sm text-destructive">{selection.message}</p>}
      {items.length === 0 && <p className="text-sm text-muted-foreground">Nothing to select. Permission denied.</p>}
      <ul className="flex flex-col gap-2">
        {items.map((i) => (
          <li key={i.key}>
            <Button variant="outline" className="h-auto w-full justify-start px-3 py-2" disabled={busy} onClick={i.pick}>
              <span className="flex flex-col items-start">
                <span>{i.label}</span>
                {i.sub && <span className="text-xs text-muted-foreground">{i.sub}</span>}
              </span>
            </Button>
          </li>
        ))}
      </ul>
    </div>
  );
}
