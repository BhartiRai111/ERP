/**
 * Rules of the old numeric text box (DmTextBoxNumeric), as pure functions.
 * Typing: digits, one "." only when decimals > 0, "-" only at the start and only when negatives are allowed.
 * On leaving the box: not a number -> empty; negative when not allowed -> 0; at most `decimals` (max 7) decimals,
 * without padding zeros ("5.50" -> "5.5"). Natures ROUNDOFF / CEILING / FLOOR give whole numbers.
 */
export type DecimalNature = "DEFAULT" | "ROUNDOFF" | "CEILING" | "FLOOR" | "NONE";

export interface NumericOptions {
  /** Number of decimals allowed, or a nature (DEFAULT = 2, NONE = 7). */
  decimals: number | DecimalNature;
  allowNegative: boolean;
}

export function allowedDecimals(d: NumericOptions["decimals"]): number {
  if (typeof d === "number") return Math.max(0, Math.min(7, d));
  switch (d) {
    case "DEFAULT":
      return 2;
    case "NONE":
      return 7;
    default:
      return 0;
  }
}

/** Text while typing: drops every character the old box would have refused. */
export function sanitizeTyping(text: string, o: NumericOptions): string {
  const places = allowedDecimals(o.decimals);
  let out = "";
  let dot = false;
  for (const ch of text) {
    if (ch >= "0" && ch <= "9") out += ch;
    else if (ch === "." && places > 0 && !dot) {
      out += ch;
      dot = true;
    } else if (ch === "-" && o.allowNegative && out === "") out += ch;
  }
  if (dot && out.split(".")[1]!.length > places) out = out.slice(0, out.indexOf(".") + 1 + places);
  return out;
}

/** Text after leaving the box. */
export function formatOnBlur(text: string, o: NumericOptions): string {
  const t = text.trim();
  if (t === "" || t === "-" || t === ".") return "";
  const n = Number(t);
  if (!Number.isFinite(n)) return "";
  if (n < 0 && !o.allowNegative) return "0";
  const places = allowedDecimals(o.decimals);
  let v = n;
  if (typeof o.decimals !== "number") {
    if (o.decimals === "ROUNDOFF") v = Math.round(n);
    else if (o.decimals === "CEILING") v = Math.ceil(n);
    else if (o.decimals === "FLOOR") v = Math.floor(n);
  }
  const fixed = v.toFixed(places);
  return places === 0 ? String(Number(fixed)) : String(Number(fixed));
}
