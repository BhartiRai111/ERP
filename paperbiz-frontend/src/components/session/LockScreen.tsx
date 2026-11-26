"use client";

import { useState } from "react";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { useSession } from "./SessionProvider";

/** Covers the app while the session is locked; opened with the user's own password. */
export function LockScreen() {
  const { selection, unlock, logout } = useSession();
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [busy, setBusy] = useState(false);

  async function submit(e: React.FormEvent) {
    e.preventDefault();
    setBusy(true);
    setError("");
    try {
      await unlock(password);
      setPassword("");
    } catch (err) {
      setError(err instanceof Error ? err.message : "Unable to unlock");
    } finally {
      setBusy(false);
    }
  }

  return (
    <div className="fixed inset-0 z-50 flex items-center justify-center bg-background/95" role="dialog" aria-label="Session locked">
      <form onSubmit={submit} className="flex w-80 flex-col gap-3 rounded-xl border bg-card p-6 shadow">
        <h2 className="text-lg font-semibold">Locked</h2>
        <p className="text-sm text-muted-foreground">{selection?.userDescription}</p>
        <Label htmlFor="unlock-password">Password</Label>
        <Input id="unlock-password" type="password" autoFocus value={password} onChange={(e) => setPassword(e.target.value)} />
        {error && <p className="text-sm text-destructive" role="alert">{error}</p>}
        <Button type="submit" disabled={busy}>Unlock</Button>
        <Button type="button" variant="ghost" onClick={() => void logout()}>Logout</Button>
      </form>
    </div>
  );
}
