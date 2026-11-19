# pb-self-toolkit / reelBundlePopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/reelBundlePopup/`). Hand-written notes are in the "Notes" section.

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
### Screen `ReelBundlePopUpGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Customer Reel Bundle No. | DmTextBox | `txtCustomerReelBundleNo` | `20, false` |
| 2 | Customer Barcode | DmTextBox | `txtCustomerBarCode` | `20, false` |
| 3 | Weight | DmTextBoxNumeric | `txtRBWeight` | `4, false, 12, true` |
| 4 | Sample No. | DmTextBox | `txtSampleNo` | `20, false` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtCustomerBarCode.setEnabled(headerEntry.isEditable)`
- `txtCustomerReelBundleNo.setEnabled(headerEntry.isEditable)`
- `txtRBWeight.setEnabled(headerEntry.isEditable)`
- `txtSampleNo.setEnabled(headerEntry.isEditable)`

**`manageDisplay()` logic (condensed code):**
```java
txtCustomerBarCode.setEnabled(headerEntry.isEditable);
txtCustomerReelBundleNo.setEnabled(headerEntry.isEditable);
txtRBWeight.setEnabled(headerEntry.isEditable);
txtSampleNo.setEnabled(headerEntry.isEditable);
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtCustomerReelBundleNo.setFocus(true);
```
- Alert: "Weight is mandatory!"
- Alert: "Weight should be less than or equal to pending weight!"

### Screen `ReelBundlePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdReelBundleDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdReelBundleDetail`: Reel/Bundle No. [Text, w150]; Barcode [Text, w150]; Customer Reel/Bundle No. [Text, w150]; Customer Barcode [Text, w150]; Weight [Text, w100]; Sample No. [Text, w100]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridPopup.addCallBack` → `onOk()`
```java
fillGridData(rbDetail_Mdl);
```

`gridPopup.addCallBack` → `onClose()`
```java
grdReelBundleDetail.commitChanges();
calculatePendingWeight("");
```

## Files
| file | lines |
|---|---|
| client/ReelBundlePopUpGridEdit.java | 185 |
| client/ReelBundlePopUpGridEdit_Dsg.java | 174 |
| client/ReelBundlePopup.java | 205 |
| client/ReelBundlePopup_Dsg.java | 188 |
| shared/GrdReelBundle_Mdl.java | 152 |

## UI labels / columns (client)
`Customer Reel Bundle No.`, `Customer Barcode`, `Weight`, `Sample No.`, `Total Weight :`, `Pending Weight :`

## Models (shared DTOs)
### GrdReelBundle_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | barcode |
| String | customerBarcode |
| String | customerReelBundleNo |
| String | v_SNo |
| String | sno |
| String | packCount |
| String | weight |
| String | totalQty |
| String | pendingQty |
| String | sampleNoDocId |
| String | sampleNo |

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.reelBundlePopup.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
