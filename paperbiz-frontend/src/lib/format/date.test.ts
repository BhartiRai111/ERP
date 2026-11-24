import { describe, expect, it } from "vitest";
import { formatDisplayDate, parseDate } from "./date";

describe("legacy date text", () => {
  it("displays dd-MMM-yyyy", () => {
    expect(formatDisplayDate(new Date(2026, 2, 5))).toBe("05-Mar-2026");
    expect(formatDisplayDate(null)).toBe("");
  });
  it("parses dd-MM-yyyy with - / . separators", () => {
    for (const t of ["05-03-2026", "05/03/2026", "05.03.2026"]) {
      expect(formatDisplayDate(parseDate(t))).toBe("05-Mar-2026");
    }
  });
  it("invalid text gives null", () => {
    expect(parseDate("garbage")).toBeNull();
    expect(parseDate("")).toBeNull();
  });
});
