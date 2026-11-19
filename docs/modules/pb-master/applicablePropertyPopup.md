# pb-master / applicablePropertyPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/applicablePropertyPopup/`). Hand-written notes are in the "Notes" section.

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
### Screen `ApplicablePropertyPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBox | `txtSearchValue` | `50, false` |

Other widgets (not in tab list): `grdItemProperty` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdItemProperty`:  [Text, w30]; Property [Text, w180, name]; Serial No. [Text, w60, serailNo]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtSearchValue.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_DOWN) {
((CellSelectionModel) grdItemProperty.getGrid().getSelectionModel()).selectCell(0, 1);
} else {
fillFilteredData();
```

## Files
| file | lines |
|---|---|
| client/ApplicablePropertyPopup.java | 340 |
| client/ApplicablePropertyPopup_Dsg.java | 208 |
| shared/GrdApplicablePrperty_Mdl.java | 41 |

## UI labels / columns (client)
`SNo.`

## Models (shared DTOs)
### GrdApplicablePrperty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | serialNo |
| String | isChecked |

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
