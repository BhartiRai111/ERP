"use client";

import * as React from "react";
import { Input } from "@/components/ui/input";
import { DmField } from "./DmField";

export interface DmTextBoxProps {
  id: string;
  label: string;
  value: string;
  onChange: (value: string) => void;
  maxLength?: number;
  mandatory?: boolean;
  disabled?: boolean;
  error?: string;
}

/** Text box: mandatory marker, max length, select all on focus, a disabled box keeps its value. */
export const DmTextBox = React.forwardRef<HTMLInputElement, DmTextBoxProps>(function DmTextBox(
  { id, label, value, onChange, maxLength, mandatory, disabled, error },
  ref,
) {
  return (
    <DmField id={id} label={label} mandatory={mandatory} error={error}>
      <Input
        id={id}
        ref={ref}
        value={value}
        maxLength={maxLength}
        disabled={disabled}
        aria-invalid={!!error}
        onChange={(e) => onChange(e.target.value)}
        onFocus={(e) => e.currentTarget.select()}
      />
    </DmField>
  );
});
