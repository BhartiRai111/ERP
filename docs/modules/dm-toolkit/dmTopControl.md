# dm-toolkit / dmTopControl

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmTopControl/`). Hand-written notes are in the "Notes" section.

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
| client/DmTopControl.java | 1123 |
| client/DmTopControl_Dsg.java | 288 |

## Widget API (auto-extracted from client classes)

### DmTopControl (DmTopControl.java, 1121 lines)
extends/implements: `DmTopControl_Dsg IsWidget, DmRootEvents, AttachEvent.Handler, ClickHandler`

Constructors:
- `DmTopControl(DmClient.ViaList viaList, DmUserControl userControl)`

Public methods:
- `void moveRec(String strFetchCode)`
- `void newClicked()`
- `void editClicked()`
- `void deleteClicked()`
- `void cardClicked()`
- `void findClicked()`
- `void printClicked()`
- `void saveClicked()`
- `void refreshClicked()`
- `void otherClicked()`
- `void closeClicked()`
- `void onClick(ClickEvent event)`
- `boolean isCardViewRequired()`
- `void setCardViewRequired(boolean cardViewRequired)`
- `boolean isRefreshRequired()`
- `void setRefreshRequired(boolean refreshRequired)`
- `boolean isOtherRequired()`
- `void setOtherRequired(boolean otherRequired)`
- `boolean isFindRequired()`
- `void setFindRequired(boolean findRequired)`
- `void onAttachOrDetach(AttachEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`
- `Widget getButton(String buttonCaption)`
- `Widget asWidget()`
- `void addMoveRecHandeller(iMoveRec onMoveRecVar)`
- `void addNewHandeller(eNew onNewVar)`
- `void addEditHandeller(eEdit onEditVar)`
- `void addDeleteHandeller(eDelete onDeleteVar)`
- `void addCardHandeller(eCard onCardVar)`
- `void addFindHandeller(eFind onFindVar)`
- `void addPrintHandeller(ePrint onPrintVar)`
- `void addSaveHandeller(eSave onSaveVar)`
- `void addRefershHandeller(eRefersh onRefershVar)`
- `void addOtherHandeller(eOther onOtherVar)`
- `void addCloseHandeller(eClose onCloseVar)`
- `void setDisplay(boolean isBrowseMode)`
- `void btnClick(Button button)`
- `void onDialogHide(DialogHideEvent event)`
- `void onDialogHide(DialogHideEvent event)`
- `void btnClick(String buttonCaption)`
- `void setNavigator(List<? extends KeyField> lstKey)`
- `void attachButton(Button btn)`
- `void deAttachButton(Button btn)`
- `void enableButton(Button btn, boolean isEnable)`
- `String getDocId()`
- `void setDocId(String strDocIdVar)`
- `String getMode()`
- `void setMode(String strMode)`
- `String getUserPermission()`
- `void setUserPermission(String userPermission)`
- `void setUserPermission(UserMenu menuPermission, VoucherPermissionHandler voucherPermissionHandler)`
- `UserPermission getPermissions()`
- `void setTitle(String title)`
- `String getTitle()`
- `void setFocus()`
- `void setDeleteCaptionToDiscard()`

Nested: interface `iMoveRec`, interface `eNew`, interface `eEdit`, interface `eDelete`, interface `eCard`, interface `eFind`, interface `ePrint`, interface `eSave`, interface `eRefersh`, interface `eOther`, interface `eClose`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxMobile.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Kya hai:** har master/entry ke upar ka button bar (372 files).
**Buttons:** New, Edit, Delete, Card, First, Previous, Next, Last, Find, Print, Save, Cancel, Refresh, Other, Close.
**Modes:** `""`, **Browse**, **New**, **Edit** (mode label dikhta hai).
- **Browse me:** New/Edit/Delete/Print tabhi dikhte hain jab user permission string me `A`/`E`/`D`/`P` ho. Agar koi record khula nahi (docId khali) to Edit/Delete/Print chhup jaate hain. Card sirf `cardViewRequired` ho to. Save/Cancel nahi dikhte.
- **New/Edit me:** sirf **Save** aur **Cancel** dikhte hain; baaki sab hide.
- **New:** permission `A` zaroori (warna "Permission denied."); form saaf, mode=New, `newClicked` callback.
- **Edit:** permission `E` + voucher permission check ("Permission denied. Due to voucher permission."), mode=Edit.
- **Delete:** permission `D` + voucher check; confirm; (screen modify-reason popup dikhata hai).
- **Cancel:** confirm "Are you sure? You want to cancel the changes." → Yes par mode=Browse aur record dobara load (`moveRec(docId)`).
- **Save:** Edit me modify-reason popup, New me seedha (entry ka kaam).
- **Keyboard:** F2 ya Ctrl+N = New, Ctrl+E Edit, Ctrl+D Delete, Ctrl+T Card, Ctrl+Up/Left/Right/Down = First/Previous/Next/Last, Ctrl+F Find, Ctrl+P Print, Ctrl+S Save, Ctrl+R Refresh, Ctrl+O Other, Esc = Cancel (ya Close).
- Voucher permission: `saveDate/viewDate/deleteDate` aur `allowValueUpto` (UserMenu/VoucherPermission) se date/value ki rok.
**Frontend:** `<DmTopControl mode permission(A/E/D/P) docId handlers />` + hotkeys + confirm dialogs; entry ek `useEntryMode()` state se isse chalayegi.
