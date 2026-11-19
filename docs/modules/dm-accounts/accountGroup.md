# dm-accounts / accountGroup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/accountGroup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: AcGroup, AcGroupPath
- Update tables: AcGroup, ag
- Delete cascade (in order): AcGroupPositioning, AcGroupPath, AcGroup
- Duplicate checks on save:
  - `Select Count(GroupName) As Cnt From AcGroup Where GroupName = :groupName And GroupCode <> :groupCode`
- "In use" checks before delete:
  - `Select Count(*) As cnt From AcGroup Where GroupCode = :groupcode And isSysGroup = '1'`
  - `Select Count(GroupCode) As Cnt From AcGroupPath Where GroupUnder = :groupcode`
- Validations: Account Group (Text); Main Group (Text); Nature (Text); rule: Group under cannot be same as account group.
- Lookups/suggestions: getSuggestion, getTxtHGroupUnder, getTxtHMainGroup, getTxtHNature
- CSV export: yes

## Screen spec (auto-extracted from client)
### Screen `AccountGroup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | A/c Group Name | DmSuggestBox | `txtGroupName` | `0, 10, false, "GroupName", DmDataType.STRING` |
| 2 | Group Under | DmTextHelp | `txtHGroupUnder` | `com.google.gwt.core.shared.TxtHGroupUnder_PrpAcs, 6, false` |
| 3 | Main Group | DmTextHelp | `txtHMainGroup` | `KeyField_PrpAcs, 4, true, true` |
| 4 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 10` |
| 5 | Print Sr.No. | DmTextBoxNumeric | `txtPrintSrNo` | `0, false, 5, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHGroupUnder`: Group [Text, w0, GroupName]; Main Group [Text, w150, GroupNatureName]
- `txtHMainGroup`: Name [Text, w0, Name]
- `txtHNature`: Name [Text, w1, Nature]
- `dmFind`: A/c Group Name [Text, w1, GroupName]; Group Under [Text, w200, GroupUnderName]; Main Group [Text, w100, GroupNature]; Nature [Text, w100, Nature]; System [Text, w50, isSysGroup]; Print SNo [Text, w100, printSno]

**Lookup / suggestion / find sources (server RPC called):**
- `txtGroupName` → `getSuggestion`
- `txtHGroupUnder` → `getTxtHGroupUnder`
- `txtHMainGroup` → `getTxtHMainGroup`
- `txtHNature` → `getTxtHNature`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtGroupName.getText())
- `GroupUnder` ← DmShared.xNull(txtHGroupUnder.getTag())
- `GroupUnderName` ← DmShared.xNull(txtHGroupUnder.getText())
- `GroupNature` ← DmShared.xNull(txtHMainGroup.getTag())
- `GroupNatureName` ← DmShared.xNull(txtHMainGroup.getText())
- `NatureCode` ← DmShared.xNull(txtHNature.getTag())
- `NatureName` ← DmShared.xNull(txtHNature.getText())
- `Sno` ← DmShared.xNull(txtPrintSrNo.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtGroupName.setText` ← data.getSharedKeyFieldName()
- `txtHGroupUnder.setText` ← data.getGroupUnderName()
- `txtHGroupUnder.setTag` ← data.getGroupUnder()
- `txtHGroupUnder.setTag2nd` ← data.getGroupUnder()
- `txtHMainGroup.setText` ← data.getGroupNatureName()
- `txtHMainGroup.setTag` ← data.getGroupNature()
- `txtHNature.setText` ← data.getNatureName()
- `txtHNature.setTag` ← data.getNatureCode()
- `txtPrintSrNo.setText` ← DmShared.xNull(data.getSno())
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtGroupName.setEnabled(false)`
- `txtGroupName.setEnabled(flag)`

**On New click:**
- `clear()`
- `txtGroupName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtGroupName.setEnabled(false);
if (!isSystemGenerated) {
txtGroupName.setEnabled(flag);
manageGroupUnderDisplay(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
manageDisplay(true);
txtGroupName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtHGroupUnder.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
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

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(AccountGroup.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
reasonInfo_Mdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtGroupName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtGroupName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtGroupName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtGroupName).show();
```

`txtGroupName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtGroupName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtGroupName).show();
```

`txtHGroupUnder.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGroupUnder(helper, blnToEndOfFile, "", new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGroupUnder).show();
public void onSuccess(DmResultPageing<List<TxtHGroupUnder_Mdl>> result) {
if (result.isStatus()) {
txtHGroupUnder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroupUnder).show();
```

`txtHGroupUnder.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGroupUnder.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroupUnder).show();
```

`txtHGroupUnder.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGroupUnder.getTag()).equalsIgnoreCase(DmShared.xNull(txtHGroupUnder.getTag2nd()))) {
resetGroupUnder();
txtHGroupUnder.setTag2nd(DmShared.xNull(txtHGroupUnder.getTag()));
if (blnStatus && tRow != null) {
txtHMainGroup.setText(DmShared.xNull(tRow.getMainGroupName()));
txtHMainGroup.setTag(DmShared.xNull(tRow.getMainGroupCode()));
txtHNature.setText(DmShared.xNull(tRow.getNatureName()));
txtHNature.setTag(DmShared.xNull(tRow.getNatureCode()));
txtPrintSrNo.setText(DmShared.xNull(tRow.getSno()));
manageGroupUnderDisplay(true);
...
```

`txtHMainGroup.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMainGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMainGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMainGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMainGroup).show();
```

`txtHMainGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMainGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMainGroup).show();
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
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

**Top control options:** setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Export Data

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."

## Files
| file | lines |
|---|---|
| client/AccountGroup.java | 621 |
| client/AccountGroup_Dsg.java | 243 |
| server/AccountGroup_SrvImpl.java | 1109 |
| shared/AccountGroup_Mdl.java | 116 |
| shared/AccountGroup_Srv.java | 30 |
| shared/AccountGroup_SrvAsync.java | 30 |
| shared/AccountGroup_Vld.java | 20 |
| shared/Find_Mdl.java | 58 |
| shared/TxtHGroupUnder_Mdl.java | 60 |

## UI labels / columns (client)
`A/c Group Name`, `Group Under`, `Main Group`, `Nature`, `Print Sr.No.`

## Models (shared DTOs)
### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | groupUnderName |
| String | groupNatureName |
| String | nature |
| String | sysGroup |
| String | printSno |

### AccountGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sysGroup |
| String | groupUnder |
| String | groupUnderName |
| String | groupNature |
| String | groupNatureName |
| String | natureCode |
| String | natureName |
| String | sno |
| String | mainStreamCode |
| String | level |
| ModifyReason_Mdl | reasonInfo_Mdl |

### TxtHGroupUnder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | mainGroupCode |
| String | mainGroupName |
| String | natureCode |
| String | natureName |
| String | sno |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<AccountGroup_Mdl> |
| saveRecord | AccountGroup_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getTxtHGroupUnder | DmHelper pageing, boolean blnToEndOfFile, String strCode | DmResultPageing<List<TxtHGroupUnder_Mdl>> |
| getTxtHMainGroup | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| exportCSV | String sessionId | DmResult<KeyFieldExtended> |

## Validator `AccountGroup_Vld`
```java
public class AccountGroup_Vld {
    public static void chkSave(AccountGroup_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Account Group", 100);
        DmShared.isValidText(mrTemp.getGroupNature(), "Main Group", 300);
        DmShared.isValidText(mrTemp.getNatureCode(), "Nature", 400);
        if (!DmShared.xNull(mrTemp.getGroupUnder()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getGroupUnder()).equalsIgnoreCase(DmShared.xNull(mrTemp.getSharedKeyFieldCode()))) {
                throw new DmException(500, "Group under cannot be same as account group.");
            }
        }
    }
}
```

## Server `AccountGroup_SrvImpl.java` (1109 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select GroupCode From AcGroup Order By GroupName;`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select groupName From AcGroup`

### `getMoveRec`
`public DmResult<AccountGroup_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select AG.GroupCode,AG.isSysGroup,AG.GroupName,AG.GroupUnder,AG.Nature as NatureCode,sgn.name as NatureName, AG1.GroupName As GrUnderName,AG.GroupNature,AG.preparedby ,AG.preparedDt,AG.preparedByIP,AG.u_EntDt,AG.u_name, AG.sNo From AcGroup AG LEFT JOIN AcGroup AG1 On AG.GroupUnder = AG1.GroupCode LEFT JOIN subGroupNature as sgn on AG.nature=sgn.code WHERE AG.GroupCode = :groupCode`
- Params: `:groupCode` ← `DmShared.iNull(strKey).toString()`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Select Count(*) As cnt From AcGroup Where GroupCode = :groupcode And isSysGroup = '1'`
- SQL 2: `Select Count(GroupCode) As Cnt From AcGroupPath Where GroupUnder = :groupcode`
- SQL 3: `Delete From AcGroupPositioning Where GroupCode = :groupcode`
- SQL 4: `Delete From AcGroupPath Where GroupCode = :groupcode`
- SQL 5: `Delete From AcGroup Where GroupCode = :groupcode`
- Params: `:groupcode` ← `DmShared.iNull(strKey).toString()`; `:groupcode` ← `DmShared.iNull(strKey).toString()`; `:groupcode` ← `DmShared.iNull(strKey).toString()`; `:groupcode` ← `DmShared.iNull(strKey).toString()`; `:groupcode` ← `DmShared.iNull(strKey).toString()`
- Errors: "You can not edit / delete system define group." | "Record is in use. You can not delete it."

### `saveRecord`
`public DmResult<String> saveRecord(AccountGroup_Mdl mrTemp)`

- SQL 1: `Select Count(GroupName) As Cnt From AcGroup Where GroupName = :groupName And GroupCode <> :groupCode`
- SQL 2: `Select GroupCode,SNo,GroupUnder From AcGroupPath Where GroupCode = :groupCode Order By SNo`
- SQL 3: `Delete From AcGroupPath Where GroupCode = :groupCode`
- SQL 4: `Delete From AcGroupPositioning Where GroupCode = :groupCode`
- SQL 5: `Insert Into AcGroup(GroupCode,GroupName, GroupUnder, Nature,preparedBY,isSysGroup, GroupNature,sNo, mainStreamCode, level ,preparedByIP,preparedDt) VALUES (:groupCode,:groupName,:groupUnder,:nature,:preparedBY,:sysgroup,:groupNature ,:sNo, :mainStreamCode, :level ,:preparedByIP,:preparedDt)`
- SQL 6: `Update AcGroup Set GroupName = :groupName, GroupUnder = :groupUnder, GroupNature = :groupNature, Nature = :nature, transferDt = :transferDt, sNo = :sNo, mainStreamCode =:mainStreamCode, level = :level, U_Name = :u_name, U_EntDt = :U_EntDt Where GroupCode = :groupCode`
- SQL 7: `Insert Into AcGroupPath(GroupCode,SNo,GroupUnder) Values (:groupCode,:sno,:groupUnder)`
- SQL 8: `Insert Into AcGroupPath(GroupCode,SNo,GroupUnder) Values (:groupCode,:sno,:groupUnder)`
- SQL 9: `Select GroupCode,SNo,GroupUnder From AcGroupPath Where GroupCode = :groupCode Order By SNo`
- SQL 10: `Select GroupCode,SNo,GroupUnder From AcGroupPath Where GroupUnder = :groupCode Order By SNo`
- SQL 11: `Select GroupCode,SNo,GroupUnder From AcGroupPath Where GroupCode = :groupCode And SNo >= :sno Order By SNo`
- SQL 12: `Delete From AcGroupPath Where GroupCode = :groupCode`
- SQL 13: `Insert Into AcGroupPath(GroupCode,SNo,GroupUnder) Values (:groupCode,:sno,:groupUnder)`
- SQL 14: `Insert Into AcGroupPath(GroupCode,SNo,GroupUnder) Values (:groupCode,:sno,:groupUnder)`
- SQL 15: `Update AcGroup Set GroupNature = :groupNature Where GroupCode = :groupCode`
- Params: `:groupName` ← `mrTemp.getSharedKeyFieldName()`; `:groupCode` ← `mrTemp.getSharedKeyFieldCode()`; `:groupCode` ← `mrTemp.getGroupUnder()`; `:groupCode` ← `mrTemp.getSharedKeyFieldCode()`; `:groupCode` ← `mrTemp.getSharedKeyFieldCode()`; `:groupCode` ← `mrTemp.getSharedKeyFieldCode()`; `:groupName` ← `mrTemp.getSharedKeyFieldName()`; `:groupUnder` ← `DmShared.foriegnKey(mrTemp.getGroupUnder())`; `:nature` ← `mrTemp.getNatureCode()`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:sysgroup` ← `"0"`; `:groupNature` ← `mrTemp.getGroupNature()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:sNo` ← `DmShared.iNull(mrTemp.getSno()).intValue()`; `:mainStreamCode` ← `DmShared.foriegnKey(mrTemp.getMainStreamCode())`; `:level` ← `DmShared.foriegnKey(mrTemp.getLevel())`; `:preparedDt` ← `timestamp`; `:groupCode` ← `mrTemp.getSharedKeyFieldCode()`; `:groupName` ← `mrTemp.getSharedKeyFieldName()`; `:groupUnder` ← `DmShared.foriegnKey(mrTemp.getGroupUnder())`; `:groupNature` ← `mrTemp.getGroupNature()`; `:nature` ← `mrTemp.getNatureCode()`; `:transferDt` ← `null`; `:sNo` ← `DmShared.iNull(mrTemp.getSno()).intValue()`; `:mainStreamCode` ← `DmShared.foriegnKey(mrTemp.getMainStreamCode())`; `:level` ← `DmShared.foriegnKey(mrTemp.getLevel())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `timestamp`; `:groupCode` ← `mrTemp.getSharedKeyFieldCode()`; `:sno` ← `intSNo`; `:groupUnder` ← `rsTemp1.getString("GroupUnder")`; `:groupCode` ← `mrTemp.getSharedKeyFieldCode()`; `:sno` ← `intSNo`; `:groupUnder` ← `mrTemp.getGroupUnder()`; `:groupCode` ← `mrTemp.getSharedKeyFieldCode()`; `:groupCode` ← `mrTemp.getSharedKeyFieldCode()`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:sno` ← `rsTemp2.getInt("SNo")`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:sno` ← `intSNo`; `:groupUnder` ← `rsTemp1.getString("GroupUnder")`; `:groupCode` ← `rsTemp2.getString("GroupCode")`; `:sno` ← `intSNo`; `:groupUnder` ← `rsTemp3.getString("GroupUnder")`; `:groupNature` ← `mrTemp.getGroupNature()`; `:groupCode` ← `rsTemp2.getString("GroupCode")`
- Errors: "Invalid key." | "A/c Group Name Already Exists." | "Invalid main stream code."
- Other calls: `AccountGroup_Vld.chkSave`

### `getTxtHGroupUnder`
`public DmResultPageing<List<TxtHGroupUnder_Mdl>> getTxtHGroupUnder(DmHelper helper, boolean blnToEndOfFile, String strCode)`

- SQL 1: `Select ag.GroupCode,ag.GroupName,ag.GroupNature as GroupNatureCode, (Case ag.GroupNature When '0' Then 'Liabilities' When '1' Then 'Assets' When '2' Then 'Expenses Direct' When '3' Then 'Expenses Indirect' When '4' Then 'Revenue Direct' When '5' Then 'Revenue Indirect' End) As GroupNatureName,ag.Nature as NatureCode, sgn.name as NatureName, ag.sNo From AcGroup as ag left join subGroupNature as sgn on ag.nature=sgn.code where ag.GroupCode <> :groupCode And ag.GroupCode Not In (Select GroupCode From AcGroupPath Where GroupUnder = :groupCode)`
- Params: `:groupCode` ← `DmShared.iNull(strCode).toString()`

### `getTxtHMainGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMainGroup(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code ,Name as Nature From subGroupNature`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select AC.GroupCode,AC.GroupName, (Case AC.GroupNature When '0' Then 'Liabilities' When '1' Then 'Assets' When '2' Then 'Expenses Direct' When '3' Then 'Expenses Indirect' When '4' Then 'Revenue Direct' When '5' Then 'Revenue Indirect' End)As GroupNature,sgn.name as Nature, (Case isnull(AC.isSysGroup,'0') When '0' Then 'N' When '1' Then 'Y' End) as isSysGroup ,AC1.GroupName As GroupUnderName, ac.sno AS printSno From AcGroup AC Left Join AcGroup AC1 On AC.GroupUnder = AC1.GroupCode left join subGroupNature as sgn on AC.nature=sgn.code`

### `exportCSV`
`public DmResult<KeyFieldExtended> exportCSV(String sessionId)`

- SQL 1: `select ag.groupName, ag1.groupName groupUnder, sgn.name as nature from acGroup ag left join acGroup ag1 on ag1.groupCode = ag.groupUnder left join subGroupNature sgn on sgn.code = ag.nature order by ag.groupName, ag1.groupName`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `DmImportData_Server.getCSVFormats`

### `getMainStreamCode`
`private String[] getMainStreamCode(String strKey, String groupUnderCode, boolean flagDelete, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) cnt FROM acgroup ag WHERE ag.groupCode = :groupCode`
- SQL 2: `SELECT ag.mainStreamCode FROM acgroup ag WHERE ag.groupCode = :groupCode`
- SQL 3: `SELECT ag.mainStreamCode FROM acgroup ag WHERE ag.groupCode = :groupUnder`
- SQL 4: `UPDATE ag SET ag.mainStreamCode = {…} , ag.level = {…} FROM acGroup ag WHERE LEFT(ISNULL(ag.mainStreamCode,:blank), LEN(:previousMsCode)) = :previousMsCode AND LEN(ISNULL(ag.mainStreamCode,:blank)) > LEN(:previousMsCode)`
- Params: `:groupCode` ← `DmShared.iNull(strKey).longValue()`; `:groupCode` ← `DmShared.iNull(strKey).longValue()`; `:groupUnder` ← `DmShared.iNull(groupUnderCode).longValue()`; `:blank` ← `""`; `:comma` ← `","`; `:msCode` ← `msCode`; `:previousMsCode` ← `previousMsCode`
- Errors: "Invalid key for main stream code generation!" | "Record is deleted without managing its downline!" | "Invalid main stream code!"

## Depends on (imports from other packages)
`dataman.accounts.accountGroup.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmImportData.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "You can not edit / delete system define group." |
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record is in use. You can not delete it." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "A/c Group Name Already Exists." |
| saveRecord | `if (DmShared.xNull(mrTemp.getMainStreamCode()).trim().equalsIgnoreCase(""))` | "Invalid main stream code." |
| exportCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| getMainStreamCode | `if (DmShared.iNull(strKey).longValue() == 0)` | "Invalid key for main stream code generation!" |
| getMainStreamCode | `if (flagDelete)` | "Record is deleted without managing its downline!" |
| getMainStreamCode | `if (DmShared.xNull(msCode).trim().equalsIgnoreCase(""))` | "Invalid main stream code!" |

## Notes (hand-written)
_No hand-written notes yet._
