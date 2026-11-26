"use client";

import * as React from "react";
import { useEffect, useRef, useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import type { PageResult } from "@/lib/api/types";
import { DmField } from "./DmField";

export interface HelpRow {
  code: string;
  name: string;
  [extra: string]: unknown;
}
export interface HelpColumn {
  field: string;
  header: string;
}
export interface HelpValue {
  code: string;
  name: string;
}

export interface DmTextHelpProps {
  id: string;
  label: string;
  value: HelpValue;
  onChange: (value: HelpValue) => void;
  /** One page of rows whose first column begins with `q`. `end` = jump to the last page. */
  load: (query: { q: string; start: number; size: number; end?: boolean }) => Promise<PageResult<HelpRow>>;
  columns?: HelpColumn[];
  pageSize?: number;
  mandatory?: boolean;
  /** Free text is kept as typed (no code). */
  appendable?: boolean;
  disabled?: boolean;
  error?: string;
}

/**
 * Lookup field (legacy DmTextHelp): code + name; typing or the button opens a paged list that searches the first
 * column ("begins with"). Up/Down choose, Enter takes the row (or the first one), Esc/Tab close. Leaving the box
 * puts the last chosen name back (unless appendable).
 */
export const DmTextHelp = React.forwardRef<HTMLInputElement, DmTextHelpProps>(function DmTextHelp(
  { id, label, value, onChange, load, columns, pageSize = 10, mandatory, appendable, disabled, error },
  ref,
) {
  const cols = columns ?? [{ field: "name", header: "Name" }];
  const [text, setText] = useState(value.name);
  const [open, setOpen] = useState(false);
  const [page, setPage] = useState<PageResult<HelpRow> | null>(null);
  const [active, setActive] = useState(-1);
  const [err, setErr] = useState("");
  const query = useRef({ q: "", start: 0, end: false });
  const seq = useRef(0);

  useEffect(() => setText(value.name), [value.name]);

  function fetchPage(q: string, start: number, end = false) {
    query.current = { q, start, end };
    const my = ++seq.current;
    load({ q, start, size: pageSize, end })
      .then((p) => {
        if (my !== seq.current) return;
        setPage(p);
        setActive(p.items.length > 0 ? 0 : -1);
        setErr("");
      })
      .catch((e: unknown) => setErr(e instanceof Error ? e.message : "Unable to load"));
  }

  function openWith(q: string) {
    setOpen(true);
    fetchPage(q, 0);
  }

  function choose(row: HelpRow) {
    onChange({ code: row.code, name: row.name });
    setText(row.name);
    setOpen(false);
  }

  function restore() {
    setOpen(false);
    if (appendable) {
      if (text !== value.name) onChange({ code: "", name: text });
    } else {
      setText(value.name);
    }
  }

  const rows = page?.items ?? [];
  return (
    <DmField id={id} label={label} mandatory={mandatory} error={error}>
      <div className="relative">
        <div className="flex gap-1">
          <Input
            id={id}
            ref={ref}
            value={text}
            disabled={disabled}
            autoComplete="off"
            role="combobox"
            aria-expanded={open}
            aria-invalid={!!error}
            onChange={(e) => {
              setText(e.target.value);
              openWith(e.target.value);
            }}
            onFocus={(e) => e.currentTarget.select()}
            onBlur={() => setTimeout(restore, 150)}
            onKeyDown={(e) => {
              if (e.key === "ArrowDown") {
                e.preventDefault();
                if (!open) openWith(text);
                else setActive((a) => Math.min(rows.length - 1, a + 1));
              } else if (e.key === "ArrowUp") {
                e.preventDefault();
                setActive((a) => Math.max(0, a - 1));
              } else if (e.key === "Enter" && open) {
                e.preventDefault();
                const row = rows[active >= 0 ? active : 0];
                if (row) choose(row);
              } else if ((e.key === "Escape" || e.key === "Tab") && open) {
                if (e.key === "Escape") e.stopPropagation();
                restore();
              }
            }}
          />
          <Button type="button" variant="outline" size="icon" disabled={disabled} aria-label={`${label} help`}
            onMouseDown={(e) => e.preventDefault()} onClick={() => (open ? setOpen(false) : openWith(""))}>
            ...
          </Button>
        </div>
        {open && (
          <div className="absolute z-20 mt-1 w-full min-w-64 rounded-lg border bg-popover text-sm shadow" onMouseDown={(e) => e.preventDefault()}>
            {err && <p className="px-2.5 py-1 text-destructive">{err}</p>}
            <table className="w-full">
              <thead>
                <tr className="border-b text-left text-xs text-muted-foreground">
                  {cols.map((c) => <th key={c.field} className="px-2.5 py-1 font-medium">{c.header}</th>)}
                </tr>
              </thead>
              <tbody>
                {rows.map((r, i) => (
                  <tr key={r.code} role="option" aria-selected={i === active}
                    className={`cursor-pointer ${i === active ? "bg-muted" : ""}`} onClick={() => choose(r)}>
                    {cols.map((c) => <td key={c.field} className="px-2.5 py-1">{String(r[c.field] ?? "")}</td>)}
                  </tr>
                ))}
                {rows.length === 0 && !err && (
                  <tr><td className="px-2.5 py-1 text-muted-foreground" colSpan={cols.length}>No record found</td></tr>
                )}
              </tbody>
            </table>
            <div className="flex gap-1 border-t p-1">
              <Button type="button" size="xs" variant="ghost" disabled={!page || page.beginOfFile} onClick={() => fetchPage(query.current.q, 0)}>First</Button>
              <Button type="button" size="xs" variant="ghost" disabled={!page || page.beginOfFile}
                onClick={() => fetchPage(query.current.q, Math.max(0, (page?.rowsStartFrom ?? 0) - pageSize))}>Previous</Button>
              <Button type="button" size="xs" variant="ghost" disabled={!page || page.endOfFile}
                onClick={() => fetchPage(query.current.q, (page?.rowsStartFrom ?? 0) + pageSize)}>Next</Button>
              <Button type="button" size="xs" variant="ghost" disabled={!page || page.endOfFile}
                onClick={() => fetchPage(query.current.q, 0, true)}>Last</Button>
            </div>
          </div>
        )}
      </div>
    </DmField>
  );
});
