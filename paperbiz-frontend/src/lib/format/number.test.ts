import { describe, expect, it } from "vitest";
import { allowedDecimals, applyNumericKey, formatNumeric } from "./number";

describe("formatNumeric (legacy DmTextBoxNumeric)", () => {
  it("shows up to N decimals without trailing zeros", () => {
    expect(formatNumeric("5.50", { decimals: 4 })).toBe("5.5");
    expect(formatNumeric("1234.5678", { decimals: 2 })).toBe("1234.57");
    expect(formatNumeric("7", { decimals: 3 })).toBe("7");
  });
  it("default nature is #.## and ignores grouping", () => {
    expect(formatNumeric("1234567.891")).toBe("1234567.89");
  });
  it("round-off, ceiling and floor give integers", () => {
    expect(formatNumeric("2.5", { nature: "ROUNDOFF" })).toBe("3");
    expect(formatNumeric("2.1", { nature: "CEILING" })).toBe("3");
    expect(formatNumeric("2.9", { nature: "FLOOR" })).toBe("2");
  });
  it("non-number gives empty, negative becomes 0 unless allowed", () => {
    expect(formatNumeric("abc")).toBe("");
    expect(formatNumeric("")).toBe("");
    expect(formatNumeric("-5", { decimals: 2 })).toBe("0");
    expect(formatNumeric("-5", { decimals: 2, allowNegative: true })).toBe("-5");
  });
  it("caps decimals at 7", () => {
    expect(allowedDecimals({ decimals: 12 })).toBe(7);
    expect(allowedDecimals({ nature: "NONE" })).toBe(7);
    expect(allowedDecimals({ decimals: 0 })).toBe(0);
  });
});

describe("applyNumericKey (legacy onKeyPress)", () => {
  it("accepts digits and rejects letters", () => {
    expect(applyNumericKey("1", "2", { decimals: 2 })).toBe("12");
    expect(applyNumericKey("1", "a", { decimals: 2 })).toBeNull();
  });
  it("dot only when decimals are allowed", () => {
    expect(applyNumericKey("1", ".", { decimals: 2 })).toBe("1.");
    expect(applyNumericKey("1", ".", { decimals: 0 })).toBeNull();
  });
  it("minus only when negatives are allowed and always goes first", () => {
    expect(applyNumericKey("12", "-", { decimals: 2 })).toBeNull();
    expect(applyNumericKey("12", "-", { decimals: 2, allowNegative: true })).toBe("-12");
    expect(applyNumericKey("-12", "-", { decimals: 2, allowNegative: true })).toBe("-12");
  });
});
