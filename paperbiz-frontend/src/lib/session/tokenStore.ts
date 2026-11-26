/**
 * The session token. Kept in memory and mirrored to sessionStorage (so a page reload keeps the login, a new
 * browser tab or window does not). Storage can be blocked: everything still works for the life of the page.
 */
const KEY = "pb.token";
let token: string | null = null;
let loaded = false;
const listeners = new Set<() => void>();

function load(): void {
  if (loaded) return;
  loaded = true;
  try {
    token = typeof window === "undefined" ? null : window.sessionStorage.getItem(KEY);
  } catch {
    token = null;
  }
}

export function getToken(): string | null {
  load();
  return token;
}

export function setToken(value: string | null): void {
  load();
  token = value;
  try {
    if (value === null) window.sessionStorage.removeItem(KEY);
    else window.sessionStorage.setItem(KEY, value);
  } catch {
    /* storage blocked */
  }
  listeners.forEach((l) => l());
}

export function subscribeToken(listener: () => void): () => void {
  listeners.add(listener);
  return () => listeners.delete(listener);
}

/** Test helper: forget what was loaded. */
export function resetTokenStoreForTests(): void {
  token = null;
  loaded = false;
  listeners.clear();
}
