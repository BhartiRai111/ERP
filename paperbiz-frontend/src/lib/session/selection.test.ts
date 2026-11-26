import { describe, expect, it } from "vitest";
import { can, findMenu, nextStep } from "./selection";
import type { MenuPermission, Selection } from "./types";

const base: Selection = {
  token: "t", userName: "admin", userDescription: "Admin", loginDate: "2026-07-09",
  plts: [], pltSelected: null, companies: [], companySelected: null, sites: [], siteSelected: null,
  regionalLanguage: "", localeCompanyCode: "1", message: "",
};
const plt = { code: "2", name: "P", linkedAccountCode: "" };
const company = { code: "7", name: "C", currentYear: "2026-27", voucherPrefix: "1", startDate: "", endDate: "" };
const site = { code: "1", name: "HO", branchName: "B" };

function menu(name: string, permission: string, children: MenuPermission[] = []): MenuPermission {
  return { moduleName: "Master", refModuleName: "", menuText: name, menuName: name, toolTip: "", permission, saveDate: null,
    viewDate: null, deleteDate: null, allowValueUpto: 0, controlPermissions: [], children };
}

describe("nextStep", () => {
  it("follows PLT, company, site", () => {
    expect(nextStep(base)).toBe("plt");
    expect(nextStep({ ...base, pltSelected: plt })).toBe("company");
    expect(nextStep({ ...base, pltSelected: plt, companySelected: company })).toBe("site");
    expect(nextStep({ ...base, pltSelected: plt, companySelected: company, siteSelected: site })).toBe("ready");
  });
});

describe("menu helpers", () => {
  const tree = [menu("Masters", "", [menu("Unit", "AEP"), menu("Item", "A", [menu("ItemGroup", "AEDP")])])];
  it("finds nested items without regard to case", () => {
    expect(findMenu(tree, "itemgroup")?.menuName).toBe("ItemGroup");
    expect(findMenu(tree, "nope")).toBeUndefined();
    expect(findMenu(undefined, "Unit")).toBeUndefined();
  });
  it("reads the permission letters", () => {
    const unit = findMenu(tree, "Unit");
    expect([can.add(unit), can.edit(unit), can.delete(unit), can.print(unit)]).toEqual([true, true, false, true]);
    expect(can.add(undefined)).toBe(false);
  });
});
