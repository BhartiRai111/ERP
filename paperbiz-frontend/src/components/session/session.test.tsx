import { render, screen } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { describe, expect, it, vi } from "vitest";
import { SelectionPicker } from "./SelectionPicker";
import type { Selection } from "@/lib/session/types";

const base: Selection = {
  token: "t", userName: "admin", userDescription: "Admin", loginDate: "2026-07-09",
  plts: [{ code: "2", name: "ABC Papers", linkedAccountCode: "" }, { code: "5", name: "XYZ Boards", linkedAccountCode: "" }],
  pltSelected: null, companies: [], companySelected: null, sites: [], siteSelected: null,
  regionalLanguage: "", localeCompanyCode: "1", message: "",
};

describe("SelectionPicker", () => {
  it("asks for the company first and reports the choice", async () => {
    const onChoose = vi.fn();
    render(<SelectionPicker selection={base} onChoose={onChoose} />);
    expect(screen.getByText("Select company")).toBeInTheDocument();
    await userEvent.click(screen.getByText("XYZ Boards"));
    expect(onChoose).toHaveBeenCalledWith("5");
  });

  it("then the year, keeping the chosen company in the next call", async () => {
    const onChoose = vi.fn();
    const sel: Selection = {
      ...base, pltSelected: base.plts[0],
      companies: [{ code: "7", name: "ABC Papers", currentYear: "2026-27", voucherPrefix: "1", startDate: "", endDate: "" }],
    };
    render(<SelectionPicker selection={sel} onChoose={onChoose} />);
    expect(screen.getByText("Select financial year")).toBeInTheDocument();
    await userEvent.click(screen.getByText("2026-27"));
    expect(onChoose).toHaveBeenCalledWith("2", "7");
  });

  it("shows the server message (e.g. Permission denied.)", () => {
    const sel: Selection = { ...base, pltSelected: base.plts[0], message: "Permission denied." };
    render(<SelectionPicker selection={sel} onChoose={() => {}} />);
    expect(screen.getByText("Permission denied.")).toBeInTheDocument();
  });
});
