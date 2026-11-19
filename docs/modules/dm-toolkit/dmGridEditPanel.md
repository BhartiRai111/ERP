# dm-toolkit / dmGridEditPanel

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmGridEditPanel/`). Hand-written notes are in the "Notes" section.

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
| client/DmGridEditPanel.java | 479 |
| client/DmGridEditPanel_Dsg.java | 121 |

## Widget API (auto-extracted from client classes)

### DmGridEditPanel (DmGridEditPanel.java, 460 lines)
extends/implements: `DmGridEditPanel_Dsg DmRootEvents, AttachEvent.Handler, ClickHandler`

Constructors:
- `DmGridEditPanel(DmGrid<T1, T2> grid, String title, String width, String height)`

Public methods:
- `void onOk()`
- `void onNew()`
- `void onDelete()`
- `void onClose()`
- `void manageOk(T1 row)`
- `void manageDelete()`
- `T1 getNewRow()`
- `void displayRow(T1 row)`
- `void onOtherClick(Widget widget)`
- `void displayRowInBackground(T1 row)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void onAttachOrDetach(AttachEvent event)`
- `void setPosition(int offsetWidth, int offsetHeight)`
- `void hide(boolean withFocusOnGrid)`
- `void hide()`
- `void proceedOk()`
- `void proceedOk(boolean isHide)`
- `void onHide(int rowIndex)`
- `void proceedNew()`
- `void proceedDelete()`
- `DmGrid<T1, T2> getDmGrid()`
- `int getCurrentRowIndex()`
- `void setVisibleOk(boolean visible)`
- `void addOtherCallBack(OtherCallBack otherCallBack)`
- `void setVisibleOther(boolean visible)`
- `void setVisibleNew(boolean visible)`
- `void setVisibleDelete(boolean visible)`
- `void setDeleteCaptionToDiscard()`
- `void setDiscardCaptionToDelete()`
- `boolean isOkEnable()`
- `boolean isNewEnable()`
- `void setTitle(String title)`
- `void onClick(ClickEvent event)`

Nested: interface `OtherCallBack`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmToolContainer.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Detail grid ka row editor popup** (voucher/line items): grid ke upar **New / Ok / Delete / Close** (aur "Other" button). `onNew` nayi row (`getNewRow`), `displayRow(row)` row ko fields me dikhata hai, `manageOk(row)` fields se row me wapas, `manageDelete`. Hotkeys root key events se. Frontend: `<DmGridEditPanel grid fields onSave onDelete />` (row ke liye dialog/inline form).
