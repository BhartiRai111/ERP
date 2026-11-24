/**
 * Number rules of the legacy DmTextBoxNumeric (1,086 screens use it).
 * `#.##`-style display: up to N decimals, NO trailing zeros ("5.50" -> "5.5"), max 7 decimals.
 */
export type DecimalNature = "DEFAULT" | "CUSTOM" | "NONE" | "ROUNDOFF" | "CEILING" | "FLOOR";

export interface NumericOptions {
  /** Used with nature CUSTOM (the legacy int constructor). */
  decimals?: number;
  allowNegative?: boolean;
  nature?: DecimalNature;
}

export const MAX_DECIMALS = 7;

function fixed(value: number, maxDecimals: number): string {
  return new Intl.NumberFormat("en-US", {
    maximumFractionDigits: maxDecimals,
    minimumFractionDigits: 0,
    useGrouping: false,
    roundingMode: "halfExpand",
  }).format(value);
}

/** Effective number of decimals for the options (legacy startUp rules). */
export function allowedDecimals(o: NumericOptions): number {
  const nature = o.nature ?? (o.decimals === undefined ? "DEFAULT" : "CUSTOM");
  switch (nature) {
    case "ROUNDOFF":
    case "CEILING":
    case "FLOOR":
      return 0;
    case "NONE":
      return MAX_DECIMALS;
    case "DEFAULT":
      return 2;
    case "CUSTOM": {
      const d = o.decimals ?? 0;
      return d > 0 ? Math.min(d, MAX_DECIMALS) : 0;
    }
  }
}

/** Display value of a raw string (legacy `formattedValue`): non-number -> "", negative -> 0 if not allowed. */
export function formatNumeric(raw: string, o: NumericOptions = {}): string {
  const n = Number.parseFloat(raw);
  if (!Number.isFinite(n) || raw.trim() === "") return "";
  let v = n;
  if (!o.allowNegative && v < 0) v = 0;
  const nature = o.nature ?? (o.decimals === undefined ? "DEFAULT" : "CUSTOM");
  switch (nature) {
    case "CEILING":
      return fixed(Math.ceil(v), 0);
    case "FLOOR":
      return fixed(Math.floor(v), 0);
    default:
      return fixed(v, allowedDecimals(o));
  }
}

/**
 * Key rules while typing (legacy onKeyPress): digits; "." only when decimals > 0; "-" only when negatives are
 * allowed and it always becomes the leading sign; every other printable key is rejected (returns null).
 */
export function applyNumericKey(current: string, key: string, o: NumericOptions = {}): string | null {
  if (key.length !== 1) return current; // control keys (Backspace, Arrow...) are not handled here
  if (key >= "0" && key <= "9") return current + key;
  if (key === ".") return allowedDecimals(o) > 0 ? current + key : null;
  if (key === "-") return o.allowNegative ? "-" + current.replaceAll("-", "").trim() : null;
  return null;
}
