# dm-toolkit / dmMessageBox

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmMessageBox/`). Hand-written notes are in the "Notes" section.

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
| client/DmAlertMessageBox.java | 260 |
| client/DmConfirmMessageBox.java | 47 |

## Widget API (auto-extracted from client classes)

### DmAlertMessageBox (DmAlertMessageBox.java, 259 lines)
extends/implements: `AlertMessageBox DmRootEvents, AttachEvent.Handler`

Constructors:
- `DmAlertMessageBox(String message, HideEvent.HideHandler hideEvent)`
- `DmAlertMessageBox(String message, final DmTextBox textbox)`
- `DmAlertMessageBox(String message, final DmTextBoxTime textbox)`
- `DmAlertMessageBox(String message, final PasswordTextBox textbox)`
- `DmAlertMessageBox(String message, final DmTextBoxNumeric textbox)`
- `DmAlertMessageBox(String message, final DmTextBoxMobile textbox)`
- `DmAlertMessageBox(String message, final DmSuggestBox textbox)`
- `DmAlertMessageBox(String message, final DmTextHelp textbox)`
- `DmAlertMessageBox(String message, final TextArea textArea)`
- `DmAlertMessageBox(String message, final DmTextHelpMulti textbox)`
- `DmAlertMessageBox(String message, final Button button)`
- `DmAlertMessageBox(String message, final DmGUI gui, final String saveInField)`
- `DmAlertMessageBox(String message, final DmRemarkTextBox textBox)`

Public methods:
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void onHide(HideEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void onAttachOrDetach(AttachEvent event)`

### DmConfirmMessageBox (DmConfirmMessageBox.java, 46 lines)
extends/implements: `ConfirmMessageBox DmRootEvents, AttachEvent.Handler`

Constructors:
- `DmConfirmMessageBox(String message, DialogHideEvent.DialogHideHandler hideEvent)`

Public methods:
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `void onAttachOrDetach(AttachEvent event)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxMobile.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Alert** aur **Confirm** popups (997 files). `DmAlertMessageBox(message, widget)`: message dikhata hai aur **Ok par focus us widget par wapas** karta hai (widget = text box/lookup/numeric/button... jisme galti thi). `DmConfirmMessageBox(message, handler)`: **Yes/No**, handler me `event.getHideButton() == YES`. Standard messages codebase me hardcoded hain (jaise "Are you sure? You want to delete this record."). Frontend: `useAlert(message, focusRef)` aur `useConfirm(message)` (Promise<boolean>).
