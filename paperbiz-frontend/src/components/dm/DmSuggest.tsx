"use client";

import * as React from "react";
import { useEffect, useRef, useState } from "react";
import { Input } from "@/components/ui/input";
import { DmField } from "./DmField";

export interface DmSuggestProps {
  id: string;
  label: string;
  value: string;
  onChange: (value: string) => void;
  /** Names that begin with the typed text. */
  load: (startsWith: string) => Promise<string[]>;
  maxLength?: number;
  mandatory?: boolean;
  disabled?: boolean;
  error?: string;
}

/** Text box with suggestions from the server; any text may be typed (legacy DmSuggestBox). */
export const DmSuggest = React.forwardRef<HTMLInputElement, DmSuggestProps>(function DmSuggest(
  { id, label, value, onChange, load, maxLength, mandatory, disabled, error },
  ref,
) {
  const [items, setItems] = useState<string[]>([]);
  const [open, setOpen] = useState(false);
  const [active, setActive] = useState(-1);
  const seq = useRef(0);

  useEffect(() => {
    if (!open) return;
    const my = ++seq.current;
    load(value)
      .then((r) => {
        if (my === seq.current) {
          setItems(r);
          setActive(-1);
        }
      })
      .catch(() => setItems([]));
  }, [value, open, load]);

  function pick(v: string) {
    onChange(v);
    setOpen(false);
  }

  return (
    <DmField id={id} label={label} mandatory={mandatory} error={error}>
      <div className="relative">
        <Input
          id={id}
          ref={ref}
          value={value}
          maxLength={maxLength}
          disabled={disabled}
          autoComplete="off"
          aria-invalid={!!error}
          aria-expanded={open}
          role="combobox"
          aria-controls={`${id}-list`}
          onChange={(e) => {
            onChange(e.target.value);
            setOpen(true);
          }}
          onFocus={(e) => e.currentTarget.select()}
          onBlur={() => setTimeout(() => setOpen(false), 120)}
          onKeyDown={(e) => {
            if (e.key === "ArrowDown") {
              e.preventDefault();
              setOpen(true);
              setActive((a) => Math.min(items.length - 1, a + 1));
            } else if (e.key === "ArrowUp") {
              e.preventDefault();
              setActive((a) => Math.max(0, a - 1));
            } else if (e.key === "Enter" && open && active >= 0 && items[active] !== undefined) {
              e.preventDefault();
              pick(items[active]!);
            } else if (e.key === "Escape" && open) {
              e.stopPropagation();
              setOpen(false);
            }
          }}
        />
        {open && items.length > 0 && (
          <ul id={`${id}-list`} role="listbox" className="absolute z-20 mt-1 max-h-56 w-full overflow-auto rounded-lg border bg-popover text-sm shadow">
            {items.map((s, i) => (
              <li
                key={s}
                role="option"
                aria-selected={i === active}
                className={`cursor-pointer px-2.5 py-1 ${i === active ? "bg-muted" : ""}`}
                onMouseDown={(e) => {
                  e.preventDefault();
                  pick(s);
                }}
              >
                {s}
              </li>
            ))}
          </ul>
        )}
      </div>
    </DmField>
  );
});
