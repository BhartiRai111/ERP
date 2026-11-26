"use client";

import type { ReactNode } from "react";
import { DmFind, type FindColumn } from "@/components/dm/DmFind";
import type { PageResult } from "@/lib/api/types";
import type { EntryState } from "@/lib/entry/useEntry";
import type { ButtonRules } from "@/lib/entry/topControl";
import { ModifyReasonDialog } from "./ModifyReasonDialog";
import { TopControl } from "./TopControl";
import { UserControlStrip } from "./UserControlStrip";

export interface EntryScreenProps<T, Row extends Record<string, unknown>> {
  title: string;
  entry: EntryState<T>;
  rules: Omit<ButtonRules, "hasRecord">;
  /** The form fields. */
  children: ReactNode;
  blogs: { caption: string; value: string }[];
  find: {
    columns: FindColumn[];
    keyField: keyof Row & string;
    load: (o: { start: number; size: number; column: string; q: string; end?: boolean }) => Promise<PageResult<Row>>;
  };
}

/** Frame of a master entry: title, top control, the form, the user strip, Find and the reason pop-up. */
export function EntryScreen<T, Row extends Record<string, unknown>>({ title, entry, rules, children, blogs, find }: EntryScreenProps<T, Row>) {
  return (
    <main className="mx-auto flex w-full max-w-4xl flex-1 flex-col gap-3 p-4">
      <h1 className="text-lg font-semibold">{title}</h1>
      <TopControl mode={entry.mode} rules={{ ...rules, hasRecord: entry.hasRecord }} onButton={entry.press} />
      {entry.findOpen ? (
        <DmFind<Row>
          columns={find.columns}
          keyField={find.keyField}
          load={find.load}
          onCancel={() => entry.setFindOpen(false)}
          onSelect={(row) => {
            entry.setFindOpen(false);
            void entry.open(String(row[find.keyField]));
          }}
        />
      ) : (
        <form className="flex flex-col gap-3" onSubmit={(e) => e.preventDefault()} aria-busy={entry.busy}>
          <fieldset disabled={entry.busy} className="grid gap-3 sm:grid-cols-2">{children}</fieldset>
        </form>
      )}
      <UserControlStrip blogs={blogs} />
      {entry.reasonOpen && (
        <ModifyReasonDialog onOk={(r) => entry.reasonDone(r)} onCancel={() => entry.reasonDone(null)} />
      )}
    </main>
  );
}
