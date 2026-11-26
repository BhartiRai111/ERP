import { describe, expect, it } from "vitest";
import { allowedDecimals, formatOnBlur, sanitizeTyping } from "./numeric";

const int = { decimals: 0, allowNegative: false };

describe("sanitizeTyping", () => {
  it("keeps only what the old box accepted", () => {
    expect(sanitizeTyping("12ab3", int)).toBe("123");
    expect(sanitizeTyping("1.5", int)).toBe("15");               // no dot without decimals
    expect(sanitizeTyping("-5", int)).toBe("5");                  // no minus when negatives are off
    expect(sanitizeTyping("1.2.3", { decimals: 3, allowNegative: false })).toBe("1.23");
    expect(sanitizeTyping("1.23456", { decimals: 3, allowNegative: false })).toBe("1.234");
    expect(sanitizeTyping("-1-2", { decimals: 0, allowNegative: true })).toBe("-12");
    expect(sanitizeTyping("5-", { decimals: 0, allowNegative: true })).toBe("5");
  });
});

describe("formatOnBlur", () => {
  it("empties non numbers and zeroes forbidden negatives", () => {
    expect(formatOnBlur("", int)).toBe("");
    expect(formatOnBlur("-", { decimals: 2, allowNegative: true })).toBe("");
    expect(formatOnBlur("-4", int)).toBe("0");
    expect(formatOnBlur("-4", { decimals: 0, allowNegative: true })).toBe("-4");
  });
  it("limits decimals without padding zeros", () => {
    const o = { decimals: 4, allowNegative: false };
    expect(formatOnBlur("5.50", o)).toBe("5.5");
    expect(formatOnBlur("5.123456", o)).toBe("5.1235");
    expect(formatOnBlur("007", o)).toBe("7");
    expect(formatOnBlur("3", { decimals: "DEFAULT", allowNegative: false })).toBe("3");
    expect(formatOnBlur("3.14159", { decimals: "DEFAULT", allowNegative: false })).toBe("3.14");
  });
  it("nature rounding gives whole numbers", () => {
    expect(formatOnBlur("2.5", { decimals: "ROUNDOFF", allowNegative: false })).toBe("3");
    expect(formatOnBlur("2.1", { decimals: "CEILING", allowNegative: false })).toBe("3");
    expect(formatOnBlur("2.9", { decimals: "FLOOR", allowNegative: false })).toBe("2");
  });
  it("decimal places are capped at 7", () => {
    expect(allowedDecimals(12)).toBe(7);
    expect(allowedDecimals("NONE")).toBe(7);
    expect(allowedDecimals("CEILING")).toBe(0);
  });
});
