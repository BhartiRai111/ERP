"use client";

import { useQuery } from "@tanstack/react-query";
import { API_BASE_URL, apiFetch } from "@/lib/api/client";

/** L1 placeholder home: proves the stack (Next.js + TanStack Query + API client). Replaced by login + menu in L2-1. */
export default function Home() {
  const health = useQuery({
    queryKey: ["health"],
    queryFn: () => apiFetch<{ status: string }>("/actuator/health"),
  });
  return (
    <main className="mx-auto flex w-full max-w-3xl flex-1 flex-col gap-4 p-8">
      <h1 className="text-2xl font-semibold">PaperBiz</h1>
      <p className="text-sm text-muted-foreground">Backend: {API_BASE_URL}</p>
      <p className="text-sm" data-testid="health">
        {health.isPending ? "Checking backend..." : health.isError ? `Backend not reachable: ${health.error.message}` : `Backend status: ${health.data.status}`}
      </p>
    </main>
  );
}
