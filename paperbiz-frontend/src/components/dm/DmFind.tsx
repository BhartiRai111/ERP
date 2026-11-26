"use client";

import { AllCommunityModule, ModuleRegistry, type ColDef, type GridApi } from "ag-grid-community";
import { AgGridReact } from "ag-grid-react";
import { useCallback, useEffect, useRef, useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import type { PageResult } from "@/lib/api/types";

ModuleRegistry.registerModules([AllCommunityModule]);

export interface FindColumn {
  /** Name of the property in the row (and of the column the server searches). */
  field: string;
  header: string;
  width?: number;
}

export interface DmFindProps<Row> {
  columns: FindColumn[];
  pageSize?: number;
  load: (o: { start: number; size: number; column: string; q: string; end?: boolean }) => Promise<PageResult<Row>>;
  /** Property holding the key of the record. */
  keyField: keyof Row & string;
  onSelect: (row: Row) => void;
  onCancel: () => void;
}

/**
 * Find screen of an entry (legacy DmFind): a paged grid with a search box. A click on a column header chooses the
 * column searched ("begins with"); typing searches again. Enter/double click opens the row; Ctrl+arrows page;
 * Ctrl+R refresh; Esc cancels.
 */
export function DmFind<Row extends Record<string, unknown>>({ columns, pageSize = 20, load, keyField, onSelect, onCancel }: DmFindProps<Row>) {
  const [column, setColumn] = useState(columns[0]!.field);
  const [q, setQ] = useState("");
  const [page, setPage] = useState<PageResult<Row> | null>(null);
  const [error, setError] = useState("");
  const api = useRef<GridApi<Row> | null>(null);
  const seq = useRef(0);

  const fetchPage = useCallback((start: number, end = false) => {
    const my = ++seq.current;
    load({ start, size: pageSize, column, q, end })
      .then((p) => {
        if (my === seq.current) {
          setPage(p);
          setError("");
        }
      })
      .catch((e: unknown) => setError(e instanceof Error ? e.message : "Unable to load"));
  }, [load, pageSize, column, q]);

  useEffect(() => fetchPage(0), [fetchPage]);

  const choose = useCallback(() => {
    const row = api.current?.getSelectedRows()[0] ?? page?.items[0];
    if (row) onSelect(row);
  }, [onSelect, page]);

  const colDefs: ColDef<Row>[] = columns.map((c) => ({
    field: c.field as never,
    headerName: c.header,
    width: c.width,
    flex: c.width ? undefined : 1,
    headerClass: c.field === column ? "font-bold underline" : undefined,
    onCellClicked: undefined,
  }));

  function onKey(e: React.KeyboardEvent) {
    if (e.key === "Escape") {
      e.stopPropagation();
      onCancel();
    } else if (e.ctrlKey && e.key === "ArrowUp") fetchPage(0);
    else if (e.ctrlKey && e.key === "ArrowLeft") fetchPage(Math.max(0, (page?.rowsStartFrom ?? 0) - pageSize));
    else if (e.ctrlKey && e.key === "ArrowRight" && page && !page.endOfFile) fetchPage((page.rowsStartFrom ?? 0) + pageSize);
    else if (e.ctrlKey && e.key === "ArrowDown") fetchPage(0, true);
    else if (e.ctrlKey && e.key.toLowerCase() === "r") {
      e.preventDefault();
      fetchPage(page?.rowsStartFrom ?? 0);
    } else if (e.ctrlKey && e.key.toLowerCase() === "k") choose();
    else return;
    if (e.ctrlKey) e.preventDefault();
  }

  const header = columns.find((c) => c.field === column)?.header ?? column;
  return (
    <div className="flex flex-1 flex-col gap-2" onKeyDown={onKey} data-testid="dm-find">
      <div className="flex flex-wrap items-center gap-2">
        <Input autoFocus className="max-w-sm" placeholder={`Search ${header}`} value={q} aria-label="Search text"
          onChange={(e) => setQ(e.target.value)}
          onKeyDown={(e) => {
            if (e.key === "Enter") choose();
          }} />
        <span className="text-xs text-muted-foreground">Click a column heading to search in it</span>
        <div className="ml-auto flex gap-1">
          <Button size="sm" variant="outline" disabled={!page || page.beginOfFile} onClick={() => fetchPage(0)}>First</Button>
          <Button size="sm" variant="outline" disabled={!page || page.beginOfFile} onClick={() => fetchPage(Math.max(0, (page?.rowsStartFrom ?? 0) - pageSize))}>Previous</Button>
          <Button size="sm" variant="outline" disabled={!page || page.endOfFile} onClick={() => fetchPage((page?.rowsStartFrom ?? 0) + pageSize)}>Next</Button>
          <Button size="sm" variant="outline" disabled={!page || page.endOfFile} onClick={() => fetchPage(0, true)}>Last</Button>
          <Button size="sm" variant="outline" onClick={() => fetchPage(page?.rowsStartFrom ?? 0)}>Refresh</Button>
          <Button size="sm" onClick={choose}>Ok</Button>
          <Button size="sm" variant="outline" onClick={onCancel}>Cancel</Button>
        </div>
      </div>
      {error && <p className="text-sm text-destructive" role="alert">{error}</p>}
      <div className="ag-theme-quartz min-h-80 flex-1" style={{ width: "100%" }}>
        <AgGridReact<Row>
          rowData={page?.items ?? []}
          columnDefs={colDefs}
          getRowId={(p) => String(p.data[keyField])}
          rowSelection={{ mode: "singleRow", checkboxes: false, enableClickSelection: true }}
          onGridReady={(e) => { api.current = e.api; }}
          onRowDoubleClicked={(e) => { if (e.data) onSelect(e.data); }}
          onColumnHeaderClicked={(e) => {
            if (!("getColId" in e.column)) return;
            const f = e.column.getColId();
            if (columns.some((c) => c.field === f)) setColumn(f);
          }}
        />
      </div>
    </div>
  );
}
