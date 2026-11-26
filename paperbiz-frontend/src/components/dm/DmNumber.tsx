"use client";

import * as React from "react";
import { Input } from "@/components/ui/input";
import { formatOnBlur, sanitizeTyping, type NumericOptions } from "@/lib/entry/numeric";
import { DmField } from "./DmField";

export interface DmNumberProps extends NumericOptions {
  id: string;
  label: string;
  value: string;
  onChange: (value: string) => void;
  maxLength?: number;
  mandatory?: boolean;
  disabled?: boolean;
  error?: string;
}

/** Numeric text box (legacy DmTextBoxNumeric): only digits, ".", "-" as allowed; tidied when you leave the box. */
export const DmNumber = React.forwardRef<HTMLInputElement, DmNumberProps>(function DmNumber(
  { id, label, value, onChange, decimals, allowNegative, maxLength, mandatory, disabled, error },
  ref,
) {
  const opts: NumericOptions = { decimals, allowNegative };
  return (
    <DmField id={id} label={label} mandatory={mandatory} error={error}>
      <Input
        id={id}
        ref={ref}
        inputMode="decimal"
        className="text-right"
        value={value}
        maxLength={maxLength}
        disabled={disabled}
        aria-invalid={!!error}
        onChange={(e) => onChange(sanitizeTyping(e.target.value, opts))}
        onBlur={() => onChange(formatOnBlur(value, opts))}
        onFocus={(e) => e.currentTarget.select()}
      />
    </DmField>
  );
});
