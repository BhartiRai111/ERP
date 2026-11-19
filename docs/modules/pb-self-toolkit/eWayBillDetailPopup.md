# pb-self-toolkit / eWayBillDetailPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/eWayBillDetailPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `EWayBillDetailPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | EWay Bill Date | DmTextBox | `txtEWayBillNo` | `50, true` |
| 2 | - | DmTextBox | `txtEWayDate` | `11, true` |
- Alert: "EWay Bill No. is mandatory."
- Alert: "EWay Bill Date should be less than or equal to current date."
- Alert: "EWay Bill Date should be less than or equal to Invoice date."
- Alert: "EWay Bill Date is mandatory."

## Files
| file | lines |
|---|---|
| client/EWayBillDetailPopup.java | 158 |
| client/EWayBillDetailPopup_Dsg.java | 159 |
| shared/EWayBillDetailPopup_Mdl.java | 40 |

## UI labels / columns (client)
`EWay Bill No`, `EWay Bill Date`

## Models (shared DTOs)
### EWayBillDetailPopup_Mdl
| type | field |
|---|---|
| String | eWayBillNo |
| String | eWayBillDate |
| String | v_Date |

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.eWayBillDetailPopup.shared`, `paperbiz.selfToolkit.stStatic.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
E-way bill detail popup UI (`dm-toolkit/gst` ke saath).
