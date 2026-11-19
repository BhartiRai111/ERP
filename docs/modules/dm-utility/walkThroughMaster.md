# dm-utility / walkThroughMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/walkThroughMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: walkThroughMast
- Update tables: walkThroughMast
- Delete cascade (in order): walkThroughDefault, walkThroughDetail, walkThroughMast
- Duplicate checks on save:
  - `SELECT COUNT(name) AS cnt FROM walkThroughMast WHERE LOWER(name) = :Name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Name (Text); rule: Authority is Mandatory.
- Lookups/suggestions: getTxtHDepartment, getTxtHUser, getTxtHVType, getTxtHSite, getSuggestionSetupName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AuthorityGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | S.No. | DmTextBoxNumeric | `txtSrlNo` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, true, 3, true` |
| 2 | Authority | DmTextBox | `txtauthorityName` | `35, true` |
| 3 | Linked User | DmTextHelpMulti | `txtHLinkedUser` | `KeyField_PrpAcs, 6, true` |

**Grid / popup columns** (header, width, db field):
- `txtHLinkedUser`: User Name [Text, w0, Name]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHLinkedUser.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUser(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLinkedUser).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLinkedUser.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLinkedUser).show();
```

`txtHLinkedUser.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLinkedUser.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLinkedUser).show();
```

`txtHLinkedUser.addTextHelpMultiCallBack` → `getSelection()`
```java
if (blnStatus) {
txtHLinkedUser.setText(keyName + ",");
```
- Alert: "S.No. is mandatory."
- Alert: "Authority is mandatory."
- Alert: "Linked User is mandatory."

### Screen `DefaultDetailGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | User Name | DmTextHelp | `txtHUserName` | `GridUser_PrpAcs, 6, false` |
| 2 | Voucher Type | DmTextHelp | `txtHVType` | `KeyField_PrpAcs, 6, false` |
| 3 | Site Name | DmTextHelp | `txtHSiteName` | `GridSite_PrpAcs, 5, false` |
| 4 | Department | DmTextHelp | `txtHDepartment` | `GridDepartment_PrpAcs, 6, false` |
| 5 | Value Upto | DmTextBoxNumeric | `txtValue` | `0, false, 8, false` |

**Grid / popup columns** (header, width, db field):
- `txtHUserName`: User [Text, w100, name]
- `txtHVType`: Nature [Text, w0, Name]
- `txtHSiteName`: Site Name [Text, w0, Name]
- `txtHDepartment`: Department [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHUserName` → `getTxtHUser`
- `txtHVType` → `getTxtHVType`
- `txtHSiteName` → `getTxtHSite`
- `txtHDepartment` → `getTxtHDepartment`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHUserName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUser(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUserName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUserName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUserName).show();
```

`txtHUserName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUserName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUserName).show();
```

`txtHVType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVType).show();
```

`txtHVType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVType).show();
```

`txtHSiteName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSiteName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSiteName).show();
```

`txtHSiteName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSiteName).show();
```

`txtHDepartment.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
} else {
```
- Alert: "Combination already exist."

### Screen `WalkThroughMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `35, 10, true, "name", DmEnum.DmDataType.STRING` |

Other widgets (not in tab list): `gui` (DmGUI), `dmFind` (DmFind), `gridAuthority` (DmGrid), `gridDefaultDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Name [Text, w100, name]
- `gridAuthority`: S.No. [Text, w40]; Authority [Text, w130]; Linked User [Text, w200]
- `gridDefaultDetail`: User Name [Text, w110]; v_Type [Text, w110]; Site Name [Text, w130]; Department [Text, w150]; Val.Upto [Text, w70]

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionSetupName`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtName.setText` ← data.getSharedKeyFieldName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtName.setEnabled(flag)`

**On New click:**
- `clear()`
- `onAdd()`
- `txtName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtName.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
onAdd();
txtName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
onAdd();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
```

`topControl.addCardHandeller` → `cardClicked()`
```java
topControlCard();
```

`topControl.addFindHandeller` → `findClicked()`
```java
flwForm.remove(vrtInnerForm);
flwForm.add(dmFind);
```

`topControl.addMoveRecHandeller` → `moveRec()`
```java
topControlMoveRec(strFetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(WalkThroughMaster.this);
```

`txtName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionSetupName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

`txtName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

`authorityGridEdit.addCallBack` → `onOk()`
```java
gridAuthority.commitChanges();
```

`authorityGridEdit.addCallBack` → `onDelete()`
```java
gridAuthority.commitChanges();
```

`defaultGridEdit.addCallBack` → `onOk()`
```java
gridDefaultDetail.commitChanges();
```

`defaultGridEdit.addCallBack` → `onDelete()`
```java
gridDefaultDetail.commitChanges();
```

**Top control options:** setRefreshRequired(false), setOtherRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/AuthorityGridEdit.java | 183 |
| client/AuthorityGridEdit_Dsg.java | 121 |
| client/DefaultDetailGridEdit.java | 259 |
| client/DefaultDetailGridEdit_Dsg.java | 195 |
| client/WalkThroughMaster.java | 495 |
| client/WalkThroughMaster_Dsg.java | 231 |
| server/WalkThroughMaster_SrvImpl.java | 709 |
| shared/Find_Mdl.java | 13 |
| shared/GrdAuthority_Mdl.java | 41 |
| shared/GrdDefaultDetail_Mdl.java | 78 |
| shared/WalkThroughMaster_Mdl.java | 53 |
| shared/WalkThroughMaster_Srv.java | 33 |
| shared/WalkThroughMaster_SrvAsync.java | 33 |
| shared/WalkThroughMaster_Vld.java | 30 |

## UI labels / columns (client)
`User Name`, `Voucher Type`, `Site Name`, `Department`, `Value Upto`, `S.No.`, `Authority`, `Linked User`, `Name`, `Aproval Default Detail:`

## Models (shared DTOs)
### GrdDefaultDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_TypeCode |
| String | v_TypeName |
| String | siteName |
| String | siteCode |
| String | departmentCode |
| String | departmentName |
| String | valueUpto |

### WalkThroughMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<GrdAuthority_Mdl> | grdAuthority_Mdl |
| List<GrdDefaultDetail_Mdl> | grdDefaultMdl |
| String | preparedBy |
| String | modifiedBy |

### GrdAuthority_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | srlNo |
| String | linkedUserCode |
| String | linkedUserName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<WalkThroughMaster_Mdl> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUser | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionSetupName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | WalkThroughMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHVType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `WalkThroughMaster_Vld`
```java
public class WalkThroughMaster_Vld {
    public static void chkSave(WalkThroughMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Name", 100);
        if (mrTemp.getGrdAuthority_Mdl().size() == 0) {
            throw new DmException(200, "Authority is Mandatory.");
        } else if (mrTemp.getGrdAuthority_Mdl().size() >= 1) {
            if (DmShared.xNull(mrTemp.getGrdAuthority_Mdl().get(0).getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                throw new DmException(200, "Authority is Mandatory.");
            }
        }
    }
}
```

## Server `WalkThroughMaster_SrvImpl.java` (709 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code From walkThroughMast order by name;`

### `getMoveRec`
`public DmResult<WalkThroughMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `select code, name,preparedBy,u_Name from walkThroughMast where code=:code`
- SQL 2: `select authority,linkedUser,v_SNo from walkThroughDetail where code=:code`
- SQL 3: `select masd.code,masd.userName,masd.v_Type as voucherCode,vt.description as voucherName,masd.site_Code ,sm.name as siteName,sg.name as departmentName,masd.departmentCode , masd.valueUpto from walkThroughDefault AS masd LEFT JOIN voucher_type AS vt ON masd.v_Type=vt.v_Type LEFT JOIN siteMast AS sm ON masd.site_Code=sm.code LEFT JOIN departmentMast AS dm ON masd.departmentCode=dm.subCode LEFT JOIN subGroup AS sg ON dm.subCode=sg.subCode where masd.code=:code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode,sg.name FROM departmentMast AS dm LEFT JOIN subGroup AS sg ON dm.subCode=sg.subCode`

### `getTxtHUser`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUser(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,user_Name as name from userMast`

### `saveRecord`
`public DmResult<String> saveRecord(WalkThroughMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM walkThroughMast WHERE LOWER(name) = :Name AND code <> :code`
- SQL 2: `Insert Into walkThroughMast(code ,Name, preparedBy, u_EntDt) Values (:code ,:Name,:preparedBy,getdate())`
- SQL 3: `Update walkThroughMast Set Name = :Name, U_Name = :u_Name, U_EntDt = getdate() Where code = :code`
- SQL 4: `delete from walkThroughDetail Where Code= :Code`
- SQL 5: `delete from walkThroughDefault Where Code= :Code`
- Params: `:Name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:Name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:Name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(authority_Mdl.getSrlNo()).toString()`; `:authority` ← `DmShared.xNull(authority_Mdl.getSharedKeyFieldName())`; `:linkedUser` ← `DmShared.xNull(authority_Mdl.getLinkedUserName())`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:userName` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(detail_Mdl.getV_TypeCode())`; `:site_Code` ← `DmShared.foriegnKey(detail_Mdl.getSiteCode())`; `:departmentCode` ← `DmShared.foriegnKey(detail_Mdl.getDepartmentCode())`; `:valueUpto` ← `DmServer.vNull(detail_Mdl.getValueUpto(), "0.00")`
- Errors: "Invalid key." | "Name already exists."
- Other calls: `WalkThroughMaster_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `delete from walkThroughDefault Where code= :code`
- SQL 2: `delete from walkThroughDetail Where code= :code`
- SQL 3: `delete from walkThroughMast Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name from walkThroughMast`

### `getTxtHVType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT v_Type as code , description as name from voucher_Type`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM siteMast`

### `getSuggestionSetupName`
`public DmResultPageing<List<String>> getSuggestionSetupName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From walkThroughMast`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGUI.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.walkThroughMaster.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Name already exists." |

## Notes (hand-written)
_No hand-written notes yet._
