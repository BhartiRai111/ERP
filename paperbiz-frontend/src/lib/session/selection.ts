import type { MenuPermission, Selection } from "./types";

/** Which screen is next after login, from what is still not chosen (same order as the old login: PLT, company, site). */
export type SelectionStep = "plt" | "company" | "site" | "ready";

export function nextStep(s: Selection): SelectionStep {
  if (!s.pltSelected) return "plt";
  if (!s.companySelected) return "company";
  if (!s.siteSelected) return "site";
  return "ready";
}

/** Finds a menu item by name anywhere in a module's tree (case-insensitive, like the backend). */
export function findMenu(items: MenuPermission[] | undefined, menuName: string): MenuPermission | undefined {
  for (const m of items ?? []) {
    if (m.menuName.toLowerCase() === menuName.toLowerCase()) return m;
    const inner = findMenu(m.children, menuName);
    if (inner) return inner;
  }
  return undefined;
}

export const can = {
  add: (m?: MenuPermission) => !!m && m.permission.includes("A"),
  edit: (m?: MenuPermission) => !!m && m.permission.includes("E"),
  delete: (m?: MenuPermission) => !!m && m.permission.includes("D"),
  print: (m?: MenuPermission) => !!m && m.permission.includes("P"),
};
