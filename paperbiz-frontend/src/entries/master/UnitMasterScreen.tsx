"use client";

import { useRef } from "react";
import { DmNumber } from "@/components/dm/DmNumber";
import { DmSuggest } from "@/components/dm/DmSuggest";
import { DmTextHelp } from "@/components/dm/DmTextHelp";
import { useDialogs } from "@/components/dm/Dialogs";
import { EntryScreen } from "@/components/entry/EntryScreen";
import { useEntry } from "@/lib/entry/useEntry";
import { emptyUnit, unitMasterApi, type UnitFindRow, type UnitMaster } from "./unitMasterApi";

/** Error numbers of the backend -> field to focus (legacy isValidText numbers). */
const FIELD_FOR_ERROR: Record<number, string> = { 100: "unit-name", 200: "decimal-places", 300: "gst-unit", 400: "unit-nature" };

/**
 * Unit Master (module Master). Fields in the legacy tab order: Unit Name, Decimal Places, GST Unit, Nature.
 * New starts with the default GST unit; for a system generated unit the name and nature are locked while editing.
 */
export function UnitMasterScreen({ permission }: { permission: string }) {
  const dialogs = useDialogs();
  const entry = useEntry<UnitMaster>({
    api: unitMasterApi,
    empty: emptyUnit,
    permission,
    dialogs,
    keyOf: (r) => r.code,
    fieldForError: (n) => FIELD_FOR_ERROR[n],
    applyDefaults: (r, d) => ({ ...r, gstUnitCode: d.gstUnitCode ?? "", gstUnitName: d.gstUnitName ?? "" }),
    deleteBlocked: (r) => (r.isSystemGenerated === "Yes" ? "Record is system generated. You can't delete this record." : undefined),
  });
  const nameRef = useRef<HTMLInputElement>(null);

  const editing = entry.mode === "New" || entry.mode === "Edit";
  const locked = entry.mode === "Edit" && entry.record.isSystemGenerated === "Yes";
  const err = (id: string) => (entry.fieldError?.id === id ? entry.fieldError.message : undefined);
  const r = entry.record;

  return (
    <EntryScreen<UnitMaster, UnitFindRow>
      title="Unit Master"
      entry={entry}
      rules={{ permission }}
      blogs={[
        { caption: "Prepared By", value: r.preparedBy },
        { caption: "Modified By", value: r.modifiedBy },
        { caption: "System Generated", value: r.isSystemGenerated },
      ]}
      find={{
        keyField: "code",
        load: (o) => unitMasterApi.find(o),
        columns: [
          { field: "name", header: "Unit Name", width: 450 },
          { field: "decimalPlaces", header: "Decimal Places", width: 250 },
          { field: "gstUnitName", header: "GST Unit", width: 300 },
          { field: "unitNatureName", header: "Unit Nature", width: 200 },
        ],
      }}
    >
      <DmSuggest id="unit-name" ref={nameRef} label="Unit Name" mandatory maxLength={10} value={r.name}
        disabled={!editing || locked} error={err("unit-name")} onChange={(v) => entry.setField("name", v)}
        load={(q) => unitMasterApi.suggestions({ start: 0, size: 5, column: "name", q }).then((p) => p.items)} />
      <DmNumber id="decimal-places" label="Decimal Places" decimals={0} allowNegative={false} maxLength={1}
        value={r.decimalPlaces} disabled={!editing} error={err("decimal-places")}
        onChange={(v) => entry.setField("decimalPlaces", v)} />
      <DmTextHelp id="gst-unit" label="GST Unit" mandatory pageSize={4} disabled={!editing} error={err("gst-unit")}
        value={{ code: r.gstUnitCode, name: r.gstUnitName }}
        onChange={(v) => { entry.setField("gstUnitCode", v.code); entry.setField("gstUnitName", v.name); }}
        columns={[{ field: "name", header: "Name" }, { field: "manualCode", header: "Code" }]}
        load={(q) => unitMasterApi.lookup("gst-unit", { start: q.start, size: q.size, q: q.q, end: q.end, column: "name" })} />
      <DmTextHelp id="unit-nature" label="Nature" mandatory pageSize={5} disabled={!editing || locked} error={err("unit-nature")}
        value={{ code: r.unitNatureCode, name: r.unitNatureName }}
        onChange={(v) => { entry.setField("unitNatureCode", v.code); entry.setField("unitNatureName", v.name); }}
        columns={[{ field: "name", header: "Nature" }]}
        load={(q) =>
          // the nature list is fixed and short: the server returns it whole, the box pages nothing
          unitMasterApi.lookup("unit-nature", { q: q.q, column: "name" }).then((p) => {
            const items = Array.isArray(p) ? (p as unknown as { code: string; name: string }[]) : p.items;
            return { items, rowsStartFrom: 0, rowsPerPage: items.length, rowCount: items.length, beginOfFile: true, endOfFile: true };
          })} />
    </EntryScreen>
  );
}
