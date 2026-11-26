/** The API client tells the app about a lost or locked session through window events. */
export const EVT_UNAUTHORIZED = "pb:unauthorized";
export const EVT_LOCKED = "pb:locked";

export function emit(name: string): void {
  if (typeof window !== "undefined") window.dispatchEvent(new Event(name));
}
