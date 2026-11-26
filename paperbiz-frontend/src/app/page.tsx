"use client";

import { useRouter } from "next/navigation";
import { useEffect } from "react";
import { LockScreen } from "@/components/session/LockScreen";
import { MenuTree } from "@/components/session/MenuTree";
import { useSession } from "@/components/session/SessionProvider";
import { Button } from "@/components/ui/button";

/** Home after login: who/where you are, lock and logout, and the modules you may use (from your permissions). */
export default function Home() {
  const router = useRouter();
  const { status, selection, permissions, logout, lock, choose } = useSession();

  useEffect(() => {
    if (status === "anonymous" || status === "selecting") router.replace("/login");
  }, [status, router]);

  if (status === "loading" || !selection || status === "anonymous" || status === "selecting") {
    return <main className="p-8 text-sm text-muted-foreground">Loading...</main>;
  }

  const modules = Object.entries(permissions?.menus ?? {});
  return (
    <main className="mx-auto flex w-full max-w-5xl flex-1 flex-col gap-4 p-6">
      {status === "locked" && <LockScreen />}
      <header className="flex flex-wrap items-center justify-between gap-2 border-b pb-3">
        <div>
          <h1 className="text-xl font-semibold">{selection.companySelected?.name || selection.pltSelected?.name}</h1>
          <p className="text-sm text-muted-foreground">
            {selection.companySelected?.currentYear} · {selection.siteSelected?.name} · {selection.userDescription}
          </p>
        </div>
        <div className="flex gap-2">
          <Button variant="outline" size="sm" onClick={() => void choose(selection.pltSelected?.code)}>Change year / site</Button>
          <Button variant="outline" size="sm" onClick={() => void lock()}>Lock</Button>
          <Button variant="outline" size="sm" onClick={() => void logout()}>Logout</Button>
        </div>
      </header>
      {modules.length === 0 && <p className="text-sm text-muted-foreground">No menu permission for this company.</p>}
      <div className="grid gap-6 sm:grid-cols-2 lg:grid-cols-3">
        {modules.map(([name, items]) => (
          <section key={name} data-testid={`module-${name}`}>
            <h2 className="mb-1 text-base font-medium capitalize">{name}</h2>
            <MenuTree items={items} />
          </section>
        ))}
      </div>
    </main>
  );
}
