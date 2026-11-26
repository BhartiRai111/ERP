"use client";

import { useCallback, useEffect, useRef, useState } from "react";
import { ApiRequestError } from "../api/errors";
import { neighbour, type ButtonId, type EntryMode } from "./topControl";
import type { ModifyReason } from "./entryApi";

export interface EntryApi<T> {
  defaults: () => Promise<Record<string, string>>;
  navigator: () => Promise<string[]>;
  open: (code: string) => Promise<T>;
  create: (body: T) => Promise<{ code: string }>;
  update: (code: string, body: T) => Promise<{ code: string }>;
  remove: (code: string, reason: ModifyReason) => Promise<void>;
}

export interface Dialogs {
  alert: (message: string, focus?: HTMLElement | null) => Promise<void>;
  confirm: (message: string) => Promise<boolean>;
}

export interface EntryConfig<T> {
  api: EntryApi<T>;
  /** An empty record. */
  empty: () => T;
  /** Fills the new record from the server defaults. */
  applyDefaults?: (record: T, defaults: Record<string, string>) => T;
  /** Letters A/E/D/P of the user's menu permission. */
  permission: string;
  dialogs: Dialogs;
  /** Error number of the backend -> id of the field to focus. */
  fieldForError?: (errorNumber: number) => string | undefined;
  /** Extra rule before "Delete" (legacy: system generated records cannot be deleted); returns a message to refuse. */
  deleteBlocked?: (record: T) => string | undefined;
  /** Key of an opened record. */
  keyOf: (record: T) => string;
  /** Called after a record was saved/deleted so the screen can refresh other data. */
  onChanged?: () => void;
}

export interface EntryState<T> {
  mode: EntryMode;
  record: T;
  setField: <K extends keyof T>(key: K, value: T[K]) => void;
  setRecord: (r: T) => void;
  hasRecord: boolean;
  busy: boolean;
  codes: string[];
  fieldError: { id: string; message: string } | null;
  /** The reason pop-up is open; call `reasonDone` with the reason or null. */
  reasonOpen: boolean;
  reasonDone: (r: ModifyReason | null) => void;
  findOpen: boolean;
  setFindOpen: (open: boolean) => void;
  open: (code: string) => Promise<void>;
  press: (b: ButtonId) => void;
}

/**
 * The flow every master entry shares (legacy DmTopControl handlers): New / Edit / Save / Cancel / Delete with the
 * old confirm texts and permission checks, record navigation, Find, the reason pop-up for edit and delete, and
 * backend errors shown as alerts that focus the field the error number points at.
 */
export function useEntry<T>(cfg: EntryConfig<T>): EntryState<T> {
  const [mode, setMode] = useState<EntryMode>("Browse");
  const [record, setRecord] = useState<T>(cfg.empty);
  const [busy, setBusy] = useState(false);
  const [codes, setCodes] = useState<string[]>([]);
  const [fieldError, setFieldError] = useState<{ id: string; message: string } | null>(null);
  const [reasonOpen, setReasonOpen] = useState(false);
  const [findOpen, setFindOpen] = useState(false);
  const reasonResolver = useRef<((r: ModifyReason | null) => void) | null>(null);
  const defaults = useRef<Record<string, string>>({});
  const cfgRef = useRef(cfg);
  useEffect(() => {
    cfgRef.current = cfg;
  });

  const key = cfg.keyOf(record);
  const hasRecord = key !== "";

  const fail = useCallback(async (e: unknown) => {
    const c = cfgRef.current;
    const message = e instanceof Error ? e.message : "Request failed";
    const id = e instanceof ApiRequestError ? c.fieldForError?.(e.errorNumber) : undefined;
    if (id) setFieldError({ id, message });
    await c.dialogs.alert(message, id ? document.getElementById(id) : null);
  }, []);

  const loadCodes = useCallback(async () => {
    try {
      setCodes(await cfgRef.current.api.navigator());
    } catch (e) {
      await fail(e);
    }
  }, [fail]);

  useEffect(() => {
    void loadCodes();
    cfgRef.current.api.defaults().then((d) => (defaults.current = d)).catch(() => undefined);
  }, [loadCodes]);

  const open = useCallback(async (code: string) => {
    setBusy(true);
    try {
      setRecord(await cfgRef.current.api.open(code));
      setMode("Browse");
      setFieldError(null);
    } catch (e) {
      await fail(e);
    } finally {
      setBusy(false);
    }
  }, [fail]);

  function askReason(): Promise<ModifyReason | null> {
    setReasonOpen(true);
    return new Promise((resolve) => {
      reasonResolver.current = resolve;
    });
  }

  const reasonDone = useCallback((r: ModifyReason | null) => {
    setReasonOpen(false);
    reasonResolver.current?.(r);
    reasonResolver.current = null;
  }, []);

  async function save() {
    const c = cfgRef.current;
    setBusy(true);
    try {
      if (mode === "Edit") {
        setBusy(false);
        const reason = await askReason();
        if (!reason) return;
        setBusy(true);
        const r = await c.api.update(key, withReason(record, reason));
        await open(r.code);
      } else {
        const r = await c.api.create(record);
        await open(r.code);
      }
      setFieldError(null);
      await loadCodes();
      c.onChanged?.();
    } catch (e) {
      await fail(e);
    } finally {
      setBusy(false);
    }
  }

  async function remove() {
    const c = cfgRef.current;
    const blocked = c.deleteBlocked?.(record);
    if (blocked) {
      await c.dialogs.alert(blocked);
      return;
    }
    if (!(await c.dialogs.confirm("Are you sure? You want to delete this record."))) return;
    const reason = await askReason();
    if (!reason) return;
    setBusy(true);
    try {
      await c.api.remove(key, reason);
      const list = await c.api.navigator();
      setCodes(list);
      const next = neighbour(codes.filter((x) => x !== key), key, "Next");
      if (next) await open(next);
      else {
        setRecord(c.empty());
        setMode("Browse");
      }
      c.onChanged?.();
    } catch (e) {
      await fail(e);
    } finally {
      setBusy(false);
    }
  }

  function press(b: ButtonId) {
    const c = cfgRef.current;
    switch (b) {
      case "New":
        if (!c.permission.includes("A")) return void c.dialogs.alert("Permission denied.");
        setFieldError(null);
        setRecord(c.applyDefaults ? c.applyDefaults(c.empty(), defaults.current) : c.empty());
        setMode("New");
        return;
      case "Edit":
        if (!hasRecord) return;
        if (!c.permission.includes("E")) return void c.dialogs.alert("Permission denied.");
        setMode("Edit");
        return;
      case "Save":
        return void save();
      case "Cancel":
        void c.dialogs.confirm("Are you sure? You want to cancel the changes.").then(async (yes) => {
          if (!yes) return;
          setFieldError(null);
          if (hasRecord) await open(key);
          else {
            setRecord(c.empty());
            setMode("Browse");
          }
        });
        return;
      case "Delete":
        if (!hasRecord) return;
        if (!c.permission.includes("D")) return void c.dialogs.alert("Permission denied.");
        return void remove();
      case "First":
      case "Previous":
      case "Next":
      case "Last": {
        const to = neighbour(codes, key, b);
        if (to && to !== key) void open(to);
        return;
      }
      case "Find":
        setFindOpen(true);
        return;
      case "Refresh":
        void loadCodes();
        return;
      default:
        return;
    }
  }

  const setField = useCallback(<K extends keyof T>(k: K, value: T[K]) => {
    setRecord((r) => ({ ...r, [k]: value }));
    setFieldError(null);
  }, []);

  return { mode, record, setField, setRecord, hasRecord, busy, codes, fieldError, reasonOpen, reasonDone, findOpen, setFindOpen, open, press };
}

function withReason<T>(record: T, reason: ModifyReason): T {
  return { ...record, modifyReason: reason } as T;
}
