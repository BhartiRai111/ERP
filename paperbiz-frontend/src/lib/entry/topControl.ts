/** The mode and button rules of the old top control (DmTopControl), as pure functions. */
export type EntryMode = "" | "Browse" | "New" | "Edit";

export type ButtonId =
  | "New" | "Edit" | "Delete" | "Card" | "First" | "Previous" | "Next" | "Last" | "Find" | "Print"
  | "Save" | "Cancel" | "Refresh" | "Other" | "Close";

export interface ButtonRules {
  /** Letters of the menu permission: A add, E edit, D delete, P print. */
  permission: string;
  /** A record is open. */
  hasRecord: boolean;
  cardRequired?: boolean;
  refreshRequired?: boolean;
  otherRequired?: boolean;
  printRequired?: boolean;
}

/** Which buttons are shown: Browse by permission and open record; New/Edit only Save and Cancel. */
export function visibleButtons(mode: EntryMode, r: ButtonRules): ButtonId[] {
  if (mode === "New" || mode === "Edit") return ["Save", "Cancel"];
  const out: ButtonId[] = [];
  if (r.permission.includes("A")) out.push("New");
  if (r.hasRecord && r.permission.includes("E")) out.push("Edit");
  if (r.hasRecord && r.permission.includes("D")) out.push("Delete");
  if (r.cardRequired) out.push("Card");
  out.push("First", "Previous", "Next", "Last", "Find");
  if (r.hasRecord && r.printRequired && r.permission.includes("P")) out.push("Print");
  if (r.refreshRequired) out.push("Refresh");
  if (r.otherRequired) out.push("Other");
  out.push("Close");
  return out;
}

/** Hot keys of the old screens: F2 / Ctrl+N new, Ctrl+E edit, Ctrl+D delete, Ctrl+T card, Ctrl+arrows move, ... Esc cancel. */
export function hotKeyToButton(e: { key: string; ctrlKey: boolean; altKey?: boolean; shiftKey?: boolean }, mode: EntryMode): ButtonId | null {
  if (e.altKey) return null;
  if (e.key === "F2") return "New";
  if (e.key === "Escape") return mode === "New" || mode === "Edit" ? "Cancel" : "Close";
  if (!e.ctrlKey) return null;
  switch (e.key.toLowerCase()) {
    case "n": return "New";
    case "e": return "Edit";
    case "d": return "Delete";
    case "t": return "Card";
    case "f": return "Find";
    case "p": return "Print";
    case "s": return "Save";
    case "r": return "Refresh";
    case "o": return "Other";
    case "arrowup": return "First";
    case "arrowleft": return "Previous";
    case "arrowright": return "Next";
    case "arrowdown": return "Last";
    default: return null;
  }
}

/** Moves along the navigator list (codes in the entry's order). */
export function neighbour(codes: string[], current: string, move: "First" | "Previous" | "Next" | "Last"): string | null {
  if (codes.length === 0) return null;
  const i = codes.indexOf(current);
  switch (move) {
    case "First": return codes[0]!;
    case "Last": return codes[codes.length - 1]!;
    case "Previous": return i <= 0 ? codes[0]! : codes[i - 1]!;
    case "Next": return i < 0 ? codes[0]! : i >= codes.length - 1 ? codes[codes.length - 1]! : codes[i + 1]!;
  }
}
