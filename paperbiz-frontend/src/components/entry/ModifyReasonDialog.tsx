"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Label } from "@/components/ui/label";
import { DmTextHelp, type HelpValue } from "@/components/dm/DmTextHelp";
import { modifyReasonApi, type ModifyReason } from "@/lib/entry/entryApi";

/** Asked before an edit is saved and before a delete: pick a reason and add a remark (legacy ModifyReason). */
export function ModifyReasonDialog({ onOk, onCancel }: { onOk: (r: ModifyReason) => void; onCancel: () => void }) {
  const [reason, setReason] = useState<HelpValue>({ code: "", name: "" });
  const [remark, setRemark] = useState("");
  const [error, setError] = useState("");

  function ok() {
    if (!reason.code) {
      setError("Reason can't empty.");
      return;
    }
    onOk({ code: reason.code, name: reason.name, remark: remark.trim() });
  }

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-black/40" role="dialog" aria-modal="true" aria-label="Modify reason"
      onKeyDown={(e) => { if (e.key === "Escape") onCancel(); }}>
      <div className="flex w-96 max-w-[90vw] flex-col gap-3 rounded-xl border bg-card p-5 shadow-lg">
        <h2 className="text-base font-medium">Reason</h2>
        <DmTextHelp id="modify-reason" label="Reason" mandatory value={reason} onChange={(v) => { setReason(v); setError(""); }}
          load={(q) => modifyReasonApi.list({ start: q.start, size: q.size, column: "name", q: q.q, end: q.end }).then((p) => ({ ...p, items: p.items }))}
          error={error} />
        <div className="flex flex-col gap-1">
          <Label htmlFor="modify-remark">Remark</Label>
          <textarea id="modify-remark" className="min-h-16 w-full rounded-lg border border-input bg-transparent p-2 text-sm"
            maxLength={250} value={remark} onChange={(e) => setRemark(e.target.value)} />
        </div>
        <div className="flex justify-end gap-2">
          <Button onClick={ok}>Ok</Button>
          <Button variant="outline" onClick={onCancel}>Cancel</Button>
        </div>
      </div>
    </div>
  );
}
