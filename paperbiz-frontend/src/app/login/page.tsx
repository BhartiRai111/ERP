"use client";

import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { SelectionPicker } from "@/components/session/SelectionPicker";
import { useSession } from "@/components/session/SessionProvider";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";

export default function LoginPage() {
  const router = useRouter();
  const { status, selection, login, choose } = useSession();
  const [userName, setUserName] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [busy, setBusy] = useState(false);

  useEffect(() => {
    if (status === "ready") router.replace("/");
  }, [status, router]);

  async function run(action: () => Promise<void>) {
    setBusy(true);
    setError("");
    try {
      await action();
    } catch (e) {
      setError(e instanceof Error ? e.message : "Request failed");
    } finally {
      setBusy(false);
    }
  }

  return (
    <main className="mx-auto flex w-full max-w-sm flex-1 flex-col justify-center gap-4 p-6">
      <h1 className="text-2xl font-semibold">PaperBiz</h1>
      {status === "selecting" && selection ? (
        <SelectionPicker selection={selection} busy={busy} onChoose={(p, c, s) => void run(() => choose(p, c, s))} />
      ) : (
        <form
          className="flex flex-col gap-3"
          onSubmit={(e) => {
            e.preventDefault();
            void run(() => login(userName, password));
          }}
        >
          <Label htmlFor="user">User name</Label>
          <Input id="user" autoComplete="username" autoFocus value={userName} onChange={(e) => setUserName(e.target.value)} />
          <Label htmlFor="password">Password</Label>
          <Input id="password" type="password" autoComplete="current-password" value={password} onChange={(e) => setPassword(e.target.value)} />
          <Button type="submit" disabled={busy || status === "loading"}>Login</Button>
        </form>
      )}
      {error && <p className="text-sm text-destructive" role="alert">{error}</p>}
    </main>
  );
}
