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
