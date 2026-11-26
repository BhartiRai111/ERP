import { render, screen, waitFor, within } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { beforeEach, describe, expect, it, vi } from "vitest";
import { DialogProvider } from "@/components/dm/Dialogs";
import { ApiRequestError } from "@/lib/api/errors";
import { UnitMasterScreen } from "./UnitMasterScreen";

const calls: { path: string; method: string; body?: unknown }[] = [];
let handlers: Record<string, (body?: unknown) => unknown> = {};

vi.mock("@/lib/api/client", () => ({
  API_BASE_URL: "http://test",
  apiFetch: async (path: string, opts: { method?: string; body?: unknown } = {}) => {
    const method = opts.method ?? "GET";
    calls.push({ path, method, body: opts.body });
    const h = handlers[`${method} ${path}`];
    if (!h) throw new Error(`unexpected call ${method} ${path}`);
    return h(opts.body);
  },
}));

const unit = (over: Record<string, string> = {}) => ({
  code: "101", name: "Kg", decimalPlaces: "2", gstUnitCode: "5", gstUnitName: "OTHERS", unitNatureCode: "1",
  unitNatureName: "Mass", isSystemGenerated: "No", preparedBy: "admin - 09-Jul-2026 - 10:00", preparedDate: "", preparedIp: "",
  modifiedBy: "", modifyDate: "", ...over,
});

function renderScreen(permission = "AEDP") {
  return render(<DialogProvider><UnitMasterScreen permission={permission} /></DialogProvider>);
}

beforeEach(() => {
  calls.length = 0;
  handlers = {
    "GET /api/master/unit-master/navigator": () => ["101", "102"],
    "GET /api/master/unit-master/defaults": () => ({ gstUnitCode: "9", gstUnitName: "OTH UNIT" }),
    "GET /api/master/unit-master/101": () => unit(),
    "GET /api/master/unit-master/102": () => unit({ code: "102", name: "Nos", isSystemGenerated: "Yes" }),
  };
});

describe("Unit Master screen", () => {
  it("opens records with the navigator buttons and shows the user strip", async () => {
    renderScreen();
    await waitFor(() => expect(calls.some((c) => c.path.endsWith("/navigator"))).toBe(true));
    await userEvent.click(await screen.findByRole("button", { name: "First" }));
    expect(await screen.findByDisplayValue("Kg")).toBeDisabled();           // Browse: fields are locked
    expect(screen.getByTestId("user-control-strip")).toHaveTextContent("admin - 09-Jul-2026 - 10:00");
    await userEvent.click(screen.getByRole("button", { name: "Next" }));
    expect(await screen.findByDisplayValue("Nos")).toBeInTheDocument();
    expect(screen.getByTestId("user-control-strip")).toHaveTextContent("Yes");
  });

  it("New starts with the default GST unit; a backend error is shown and the field is focused", async () => {
    handlers["POST /api/master/unit-master"] = () => {
      throw new ApiRequestError(400, 100, "Unit Name is mandatory.");
    };
    renderScreen();
    await userEvent.click(await screen.findByRole("button", { name: "New" }));
    expect(screen.getByTestId("entry-mode")).toHaveTextContent("New");
    expect(await screen.findByDisplayValue("OTH UNIT")).toBeInTheDocument();
    await userEvent.click(screen.getByRole("button", { name: "Save" }));
    const alert = await screen.findByRole("alertdialog");
    expect(within(alert).getByText("Unit Name is mandatory.")).toBeInTheDocument();
    await userEvent.click(within(alert).getByRole("button", { name: "Ok" }));
    await waitFor(() => expect(document.activeElement?.id).toBe("unit-name"));
  });

  it("without the A permission New says Permission denied.", async () => {
    renderScreen("E");
    expect(screen.queryByRole("button", { name: "New" })).toBeNull();   // the button is not even shown
  });

  it("a system generated unit cannot be deleted and its name and nature stay locked in Edit", async () => {
    renderScreen();
    await userEvent.click(await screen.findByRole("button", { name: "Last" }));
    await screen.findByDisplayValue("Nos");
    await userEvent.click(screen.getByRole("button", { name: "Edit" }));
    expect(screen.getByLabelText("Unit Name")).toBeDisabled();
    expect(screen.getByLabelText("Nature")).toBeDisabled();
    expect(screen.getByLabelText("Decimal Places")).toBeEnabled();
    await userEvent.click(screen.getByRole("button", { name: "Cancel" }));
    await userEvent.click(await screen.findByRole("button", { name: "Yes" }));    // confirm cancel
    await waitFor(() => expect(screen.getByTestId("entry-mode")).toHaveTextContent("Browse"));
    await userEvent.click(screen.getByRole("button", { name: "Delete" }));
    expect(await screen.findByText("Record is system generated. You can't delete this record.")).toBeInTheDocument();
    expect(calls.some((c) => c.method === "DELETE")).toBe(false);
  });

  it("Edit asks for a reason before saving and sends it with the record", async () => {
    handlers["GET /api/modify-reasons"] = () => ({ items: [{ code: "3", name: "Data correction" }], rowsStartFrom: 0, rowsPerPage: 10, rowCount: 1, beginOfFile: true, endOfFile: true });
    handlers["PUT /api/master/unit-master/101"] = () => ({ code: "101" });
    renderScreen();
    await userEvent.click(await screen.findByRole("button", { name: "First" }));
    await screen.findByDisplayValue("Kg");
    await userEvent.click(screen.getByRole("button", { name: "Edit" }));
    await userEvent.click(screen.getByRole("button", { name: "Save" }));
    const dialog = await screen.findByRole("dialog", { name: "Modify reason" });
    // Ok without a reason is refused
    await userEvent.click(within(dialog).getByRole("button", { name: "Ok" }));
    expect(await within(dialog).findByText("Reason can't empty.")).toBeInTheDocument();
    await userEvent.click(within(dialog).getByRole("button", { name: "Reason help" }));
    await userEvent.click(await within(dialog).findByText("Data correction"));
    await userEvent.click(within(dialog).getByRole("button", { name: "Ok" }));
    await waitFor(() => expect(calls.some((c) => c.method === "PUT")).toBe(true));
    const put = calls.find((c) => c.method === "PUT")!;
    expect((put.body as { modifyReason: { code: string } }).modifyReason.code).toBe("3");
  });
});
