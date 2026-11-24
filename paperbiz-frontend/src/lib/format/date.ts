/** Date text of the legacy screens: shown as dd-MMM-yyyy; typed as dd-MM-yyyy (/ . - separators accepted). */
const MONTHS = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];

/** "05-Mar-2026", or "" for null/invalid (legacy dNull). */
export function formatDisplayDate(d: Date | null | undefined): string {
  if (!d || Number.isNaN(d.getTime())) return "";
  return `${String(d.getDate()).padStart(2, "0")}-${MONTHS[d.getMonth()]}-${d.getFullYear()}`;
}

/** dd-MM-yyyy (also dd/MM/yyyy, dd.MM.yyyy) -> Date, or null (legacy convertDate; out-of-range days roll over like Java's lenient parser). */
export function parseDate(text: string | null | undefined): Date | null {
  if (!text) return null;
  const m = /^(\d{1,2})[-/.](\d{1,2})[-/.](\d{4})$/.exec(text.trim());
  if (!m) return null;
  return new Date(Number(m[3]), Number(m[2]) - 1, Number(m[1]));
}
