"use client";

import type { MenuPermission } from "@/lib/session/types";

/** One module's menu as nested lists. Screens are linked later, when the entries exist. */
export function MenuTree({ items }: { items: MenuPermission[] }) {
  return (
    <ul className="flex flex-col gap-1 pl-3 text-sm">
      {items.map((m) => (
        <li key={`${m.moduleName}|${m.menuName}`}>
          <span className={m.children.length ? "font-medium" : ""} title={m.toolTip}>{m.menuText}</span>
          {m.children.length > 0 && <MenuTree items={m.children} />}
        </li>
      ))}
    </ul>
  );
}
