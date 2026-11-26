import type { ReactNode } from "react";
import { cn } from "cn";
import { Label } from "@/components/ui/label";

/** Label + widget + message; a mandatory field gets the red left border (legacy look). */
export function DmField({ id, label, mandatory, error, className, children }: {
  id: string;
  label: string;
  mandatory?: boolean;
  error?: string;
  className?: string;
  children: ReactNode;
}) {
  return (
    <div className={cn("flex flex-col gap-1", className)}>
      <Label htmlFor={id}>{label}</Label>
      <div className={cn(mandatory && "border-l-[3px] border-l-red-500 pl-1.5")}>{children}</div>
      {error && <p className="text-xs text-destructive" role="alert">{error}</p>}
    </div>
  );
}
