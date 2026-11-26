"use client";

import { useRouter } from "next/navigation";
import { useEffect } from "react";
import { useSession } from "@/components/session/SessionProvider";
import { UnitMasterScreen } from "@/entries/master/UnitMasterScreen";
import { findMenu } from "@/lib/session/selection";

export default function UnitMasterPage() {
  const router = useRouter();
  const { status, permissions } = useSession();
  useEffect(() => {
    if (status === "anonymous" || status === "selecting") router.replace("/login");
  }, [status, router]);
  if (status !== "ready" && status !== "locked") return <main className="p-8 text-sm text-muted-foreground">Loading...</main>;
  const menu = findMenu(permissions?.menus["master"], "Unit Master");
  if (!permissions) return <main className="p-8 text-sm text-muted-foreground">Loading...</main>;
  if (!menu) return <main className="p-8 text-sm">Permission denied.</main>;
  return <UnitMasterScreen permission={menu.permission} />;
}
