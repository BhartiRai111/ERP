import { describe, expect, it } from "vitest";
import { hotKeyToButton, neighbour, visibleButtons } from "./topControl";

describe("visibleButtons", () => {
  it("browse shows what the permission and the open record allow", () => {
    const none = visibleButtons("Browse", { permission: "", hasRecord: false });
    expect(none).not.toContain("New");
    expect(none).not.toContain("Edit");
    const aep = visibleButtons("Browse", { permission: "AEP", hasRecord: true, printRequired: true });
    expect(aep).toEqual(expect.arrayContaining(["New", "Edit", "Print"]));
    expect(aep).not.toContain("Delete");
    expect(visibleButtons("Browse", { permission: "AED", hasRecord: false })).toEqual(
      ["New", "First", "Previous", "Next", "Last", "Find", "Close"]);
  });
  it("new and edit show only save and cancel", () => {
    expect(visibleButtons("New", { permission: "AEDP", hasRecord: true })).toEqual(["Save", "Cancel"]);
    expect(visibleButtons("Edit", { permission: "AEDP", hasRecord: true })).toEqual(["Save", "Cancel"]);
  });
});

describe("hotKeyToButton", () => {
  it("maps the legacy keys", () => {
    expect(hotKeyToButton({ key: "F2", ctrlKey: false }, "Browse")).toBe("New");
    expect(hotKeyToButton({ key: "s", ctrlKey: true }, "Edit")).toBe("Save");
    expect(hotKeyToButton({ key: "ArrowDown", ctrlKey: true }, "Browse")).toBe("Last");
    expect(hotKeyToButton({ key: "Escape", ctrlKey: false }, "Edit")).toBe("Cancel");
    expect(hotKeyToButton({ key: "Escape", ctrlKey: false }, "Browse")).toBe("Close");
    expect(hotKeyToButton({ key: "x", ctrlKey: true }, "Browse")).toBeNull();
    expect(hotKeyToButton({ key: "n", ctrlKey: false }, "Browse")).toBeNull();
  });
});

describe("neighbour", () => {
  const codes = ["a", "b", "c"];
  it("walks the list and stops at the ends", () => {
    expect(neighbour(codes, "b", "Next")).toBe("c");
    expect(neighbour(codes, "c", "Next")).toBe("c");
    expect(neighbour(codes, "b", "Previous")).toBe("a");
    expect(neighbour(codes, "a", "Previous")).toBe("a");
    expect(neighbour(codes, "b", "First")).toBe("a");
    expect(neighbour(codes, "b", "Last")).toBe("c");
    expect(neighbour([], "x", "Next")).toBeNull();
  });
});
