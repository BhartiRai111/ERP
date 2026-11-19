# pb-assets / machineDownEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/machineDownEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: machineMaintainanceDetail, taskSchedule
- Update tables: machineMaintainanceDetail, taskSchedule
- Delete cascade (in order): taskSchedule, machineMaintainanceDetail
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `select count(*) as cnt from taskScheduleHistory where taskId=:taskId`
- Validations: Voucher Date (Text); Machine Item (Amount); Machine Name (Amount); Down Date (Text); Down Time (Text); Reason (Text); Up Time (Text); rule: Down Date should be less than or equal to Voucher Date.; rule: Up Date should be greater than or equal to Down Date.; rule: Up Time should be greater than or equal to registration time.; rule: Up Date should be less than or equal to Current Date.; rule: Up Time should be less than or equal to current time.
- Lookups/suggestions: getTxtHMachineItem, getTxtHMachineName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `MachineDownEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Next Follow-Up | DmTextBox | `txtFollowUpDate` | `11, false` |
| 2 | - | DmTextBoxTime | `txtFollowUpTime` | `false` |
| 3 | Voucher No. | DmTextBox | `txtVoucherNo` | `11, false` |
| 4 | Date | DmTextBox | `txtVDate` | `11, true` |
| 5 | Machine Item | DmTextHelp | `txtHMachineItem` | `KeyField_PrpAcs, 6, true` |
| 6 | Machine Name | DmTextHelp | `txtHMachineName` | `KeyField_PrpAcs, 6, true` |
| 7 | Date | DmTextBox | `txtDownDate` | `11, true` |
| 8 | - | DmTextBoxTime | `txtDownTime` | `true` |
| 9 | Reason | DmRemarkTextBox | `txtDownReason` | `250, true` |
| 10 | Date | DmTextBox | `txtUpDate` | `11, false` |
| 11 | - | DmTextBoxTime | `txtUpTime` | `false` |
| 12 | Mechanic Name | DmTextBox | `txtMechanicName` | `30, false` |
| 13 | Mechanic Remark | DmRemarkTextBox | `txtMechanicRemark` | `250, false` |
| 14 | Assemble Parts | DmRemarkTextBox | `txtAssembleParts` | `250, false` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdMachineDownEntry` (DmGrid), `grdActionHistory` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHMachineItem`: Machine Item [Text, w0, name]
- `txtHMachineName`: Machine Name [Text, w0, manualCode]
- `dmFind`: Voucher No. [Text, w130, recId]; Date [Text, w80, v_Date]; Machine Item [Text, w150, machineItem]; Machine Name [Text, w200, machineName]; Down Date [Text, w100, machineDownDate]; Down Time [Text, w80, machineDownTime]; Up Date [Text, w100, machineUpDate]; Up Time [Text, w80, machineUpTime]; Mechanic Name [Text, w170, mechanicName]
- `grdMachineDownEntry`: Date [Text, w80]; Time [Text, w50]; Down Reason [Text, w100]; Date [Text, w80]; Time [Text, w50]; Mechanic Name [Text, w130]; Mechanic Remark [Text, w150]; Assemble Parts [Text, w100]
- `grdActionHistory`: Action [Text, w100]; Schedule Date [Text, w100]; Entry Date [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHMachineItem` → `getTxtHMachineItem`
- `txtHMachineName` → `getTxtHMachineName`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `VoucherDate` ← DmShared.dNull(txtVDate.getText())
- `MachineCode` ← DmShared.iNull(txtHMachineName.getTag()).toString()
- `MachineName` ← DmShared.xNull(txtHMachineName.getText())
- `MachineItemCode` ← DmShared.iNull(txtHMachineItem.getTag()).toString()
- `MachineItemName` ← DmShared.xNull(txtHMachineItem.getText())
- `DownDate` ← DmShared.dNull(txtDownDate.getText())
- `DownTime` ← DmShared.xNull(txtDownTime.getText())
- `DownReason` ← DmShared.xNull(txtDownReason.getText())
- `UpDate` ← DmShared.dNull(txtUpDate.getText())
- `UpTime` ← DmShared.xNull(txtUpTime.getText())
- `MechanicName` ← DmShared.xNull(txtMechanicName.getText())
- `MechanicRemark` ← DmShared.xNull(txtMechanicRemark.getText())
- `AssembleParts` ← DmShared.xNull(txtAssembleParts.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtHMachineItem.setText` ← DmShared.xNull(data.getMachineItemName())
- `txtHMachineItem.setTag` ← DmShared.xNull(data.getMachineItemCode())
- `txtVDate.setText` ← DmShared.dNull(data.getVoucherDate())
- `txtHMachineName.setText` ← DmShared.xNull(data.getMachineName())
- `txtHMachineName.setTag` ← DmShared.xNull(data.getMachineCode())
- `txtDownDate.setText` ← DmShared.dNull(data.getDownDate())
- `txtDownTime.setText` ← DmShared.xNull(data.getDownTime())
- `txtDownReason.setText` ← DmShared.xNull(data.getDownReason())
- `txtUpDate.setText` ← DmShared.dNull(data.getUpDate())
- `txtUpTime.setText` ← DmShared.xNull(data.getUpTime())
- `txtMechanicName.setText` ← DmShared.xNull(data.getMechanicName())
- `txtMechanicRemark.setText` ← DmShared.xNull(data.getMechanicRemark())
- `txtAssembleParts.setText` ← DmShared.xNull(data.getAssembleParts())
- `txtFollowUpDate.setText` ← DmShared.dNull(data.getNextFollwUpDate())
- `txtFollowUpTime.setText` ← DmShared.xNull(data.getNextFollwUpTime())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVDate.setEnabled(false)`
- `txtVoucherNo.setEnabled(false)`
- `txtFollowUpDate.setEnabled(false)`
- `txtFollowUpTime.setEnabled(false)`
- `txtHMachineItem.setEnabled(flag)`
- `txtHMachineName.setEnabled(flag)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValue`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVDate.setEnabled(false);
txtVoucherNo.setEnabled(false);
txtFollowUpDate.setEnabled(false);
txtFollowUpTime.setEnabled(false);
txtHMachineItem.setEnabled(flag);
txtHMachineName.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtDownDate.setFocus(true);
manageDisplay(false);
```

`topControl.addEditHandeller` → `execute()`
```java
txtDownDate.setFocus(true);
```

`topControl.addCardHandeller` → `cardClicked()`
```java
topControlCard();
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

`topControl.addFindHandeller` → `findClicked()`
```java
flwForm.remove(vrtInnerForm);
flwForm.add(dmFind);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(MachineDownEntry.this);
```

`topControl.addMoveRecHandeller` → `moveRec()`
```java
topControlMoveRec(strFetchCode);
```

`txtHMachineItem.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMachineItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMachineItem).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMachineItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachineItem).show();
```

`txtHMachineItem.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMachineItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachineItem).show();
```

`txtHMachineItem.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHMachineName.setTag((""));
txtHMachineName.setText("");
grdMachineDownEntry.getGrid().getStore().clear();
```

`txtHMachineName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMachineName(DmShared.iNull(txtHMachineItem.getTag()).toString(), helper, blnT
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMachineName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMachineName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachineName).show();
```

`txtHMachineName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMachineName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachineName).show();
```

`txtHMachineName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
downAssetDetail(DmShared.iNull(txtHMachineName.getTag()).toString());
} else {
grdMachineDownEntry.getGrid().getStore().clear();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
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

**Top control options:** setRefreshRequired(false), setOtherRequired(true), setDisplay(true)

**Menu items (Other button):** Process Message
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to print this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/MachineDownEntry.java | 691 |
| client/MachineDownEntry_Dsg.java | 647 |
| server/MachineDownEntry_SrvImpl.java | 791 |
| shared/DefaultValue_Mdl.java | 44 |
| shared/Find_Mdl.java | 85 |
| shared/GrdActionHistory_Mdl.java | 77 |
| shared/MachineDownEntry_Mdl.java | 195 |
| shared/MachineDownEntry_Srv.java | 35 |
| shared/MachineDownEntry_SrvAsync.java | 36 |
| shared/MachineDownEntry_Vld.java | 58 |

## UI labels / columns (client)
`Next Follow-Up`, `Voucher No.`, `Date`, `Machine Item`, `Machine Name`, `Down Detail`, `Reason`, `Up Detail`, `Mechanic Name`, `Mechanic Remark`, `Assemble Parts`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String voucherTypeTag, | voucherTypeText |
| String currentDate, | currentTime |

### MachineDownEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String voucherTag, | voucherText |
| String machineItemName, | machineItemCode |
| RecId | recIdVoucher |
| String | grdMachineDownEntry |
| String | nextFollwUpDate |
| String | nextFollwUpTime |
| List<MachineDownEntry_Mdl> | lstMachineDwn |
| List<GrdActionHistory_Mdl> | lstActionHistory |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### GrdActionHistory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | scheduleDate |
| String | scheduleTime |
| String | status |
| String | statusRemark |
| String | entryDate |
| String | entryTime |
| String | entryUserName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHMachineName | String assetItemCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMachineItem | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | MachineDownEntry_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<MachineDownEntry_Mdl> |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| sendMessage | String strKey | DmResult<String> |
| getServerDateTime |  | String[] |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| deleteRecord | String strKey | DmResult<String> |
| getMachineDetail | String assetCode | DmResult<List<MachineDownEntry_Mdl>> |

## Validator `MachineDownEntry_Vld`
```java
public class MachineDownEntry_Vld {
    public static void chkSave(MachineDownEntry_Mdl mrTemp, String currentDate, String currentTime) {
        DmShared.isValidText(DmShared.dNull(mrTemp.getVoucherDate()), "Voucher Date", 100);
        DmShared.isValidAmount(DmShared.xNull(mrTemp.getMachineItemCode()), "Machine Item", 200);
        DmShared.isValidAmount(DmShared.xNull(mrTemp.getMachineCode()), "Machine Name", 300);
        DmShared.isValidText(DmShared.dNull(mrTemp.getDownDate()), "Down Date", 400);
        DmShared.isValidText(DmShared.xNull(mrTemp.getDownTime()).trim(), "Down Time", 500);
        DmShared.isValidText(DmShared.xNull(mrTemp.getDownReason()).trim(), "Reason", 600);
        if ((DmShared.convertDate(mrTemp.getVoucherDate()).getTime() < (DmShared.convertDate(mrTemp.getDownDate())).getTime())) {
            throw new DmException(400, "Down Date should be less than or equal to Voucher Date.");
        }
        if (!DmShared.dNull(mrTemp.getUpDate()).equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getUpDate()).getTime() < (DmShared.convertDate(mrTemp.getDownDate())).getTime())) {
                throw new DmException(700, "Up Date should be greater than or equal to Down Date.");
            }
            DmShared.isValidText(DmShared.xNull(mrTemp.getUpTime()).trim(), "Up Time", 800);
            if ((DmShared.convertDate(mrTemp.getUpDate()).getTime() <= (DmShared.convertDate(mrTemp.getDownDate())).getTime())) {
                if ((DmShared.vNull(mrTemp.getUpTime()).doubleValue()) < (DmShared.vNull(mrTemp.getDownTime()).doubleValue())) {
                    throw new DmException(800, "Up Time should be greater than or equal to registration time.");
                }
            }
            if ((DmShared.convertDate(mrTemp.getUpDate()).getTime() > (DmShared.convertDate(currentDate)).getTime())) {
                throw new DmException(700, "Up Date should be less than or equal to Current Date.");
            }
            if ((DmShared.convertDate(mrTemp.getUpDate()).getTime() <= (DmShared.convertDate(currentDate)).getTime())) {
                if ((DmShared.vNull(mrTemp.getUpTime()).doubleValue()) > (DmShared.vNull(currentTime).doubleValue())) {
                    throw new DmException(800, "Up Time should be less than or equal to current time.");
                }
            }
        }
    }
}
```

## Server `MachineDownEntry_SrvImpl.java` (791 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getVType`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(MachineDownEntry_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `Insert Into machineMaintainanceDetail(docId,v_Type, v_No, recid_Prefix, recid, v_Prefix, v_Date, assetCode, machineDownDate, machineDownTime, downReason, machineUpDate, machineUpTime, mechanicName, mechanicRemark, assembleParts, preparedBy, u_EntDt, site_Code) Values (:docId, :v_Type, :v_No, :recIdPrefix, :recId, :v_Prefix, :v_Date, :assetCode, :machineDownDate, :machineDownTime, :downReason, :machineUpDate, :machineUpTime, :mechanicName, :mechanicRemark, :assembleParts, :preparedBy, :u_EntDt, :site_Code)`
- SQL 2: `Insert Into taskSchedule (taskId, v_type, v_No, recidPrefix, recid, v_Prefix, scheduleDate, scheduleTime, description, remark, taskOwner,assetCode, isStatus,site_Code, preparedBy, preparedByDate,U_EntDt, u_EntDt_LatestLine) values (:taskId, :v_type, :v_No, :recIdPrefix, :recId, :v_Prefix,:scheduleDate, :scheduleTime, :description, :remark, :taskOwner, :assetCode, :isStatus,:site_Code, :preparedBy, :preparedByDate,:preparedByDate, :preparedByDate)`
- SQL 3: `Update machineMaintainanceDetail Set machineDownDate = :machineDownDate, machineDownTime = :machineDownTime, downReason = :downReason, machineUpDate = :machineUpDate, machineUpTime = :machineUpTime, mechanicName = :mechanicName, mechanicRemark = :mechanicRemark, assembleParts = :assembleParts, U_Name = :uName, U_EntDt = :U_EntDt Where docId = :docId`
- SQL 4: `Update taskSchedule set description = :description, remark = :remark, u_Name = :u_Name, u_EntDt = :u_EntDt, u_EntDt_LatestLine = :u_EntDt where taskId = :taskId`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTag())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdVoucher().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdVoucher().getPrefix())`; `:recId` ← `DmShared.xNull(mrTemp.getRecIdVoucher().getRecIdValue())`; `:v_Prefix` ← `voucherPrefix`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getMachineCode())`; `:machineDownDate` ← `DmServer.convertDate(mrTemp.getDownDate())`; `:machineDownTime` ← `DmServer.vNull(mrTemp.getDownTime(), "00.00")`; `:downReason` ← `DmShared.xNull(mrTemp.getDownReason())`; `:machineUpDate` ← `DmServer.convertDate(mrTemp.getUpDate())`; `:machineUpTime` ← `DmServer.vNull(mrTemp.getUpTime(), "00.00")`; `:mechanicName` ← `DmShared.xNull(mrTemp.getMechanicName())`; `:mechanicRemark` ← `DmShared.xNull(mrTemp.getMechanicRemark())`; `:assembleParts` ← `DmShared.xNull(mrTemp.getAssembleParts())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:site_Code` ← `siteCode`; `:taskId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTag())`; `:recId` ← `DmShared.xNull(mrTemp.getRecIdVoucher().getRecIdValue())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdVoucher().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdVoucher().getPrefix())`; `:scheduleDate` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:scheduleTime` ← `DmServer.getServerDateTime()[1]`; `:remark` ← `DmShared.xNull(mrTemp.getDownReason())`; `:taskOwner` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getMachineCode())`; `:isStatus` ← `"0"`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedByDate` ← `DmServer.getServerTimestamp()`; `:site_Code` ← `siteCode`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:machineDownDate` ← `DmServer.convertDate(mrTemp.getDownDate())`; `:machineDownTime` ← `DmShared.xNull(mrTemp.getDownTime())`; `:downReason` ← `DmShared.xNull(mrTemp.getDownReason())`; `:machineUpDate` ← `DmServer.convertDate(mrTemp.getUpDate())`; `:machineUpTime` ← `DmShared.xNull(mrTemp.getUpTime())`; `:mechanicName` ← `DmShared.xNull(mrTemp.getMechanicName())`; `:mechanicRemark` ← `DmShared.xNull(mrTemp.getMechanicRemark())`; `:assembleParts` ← `DmShared.xNull(mrTemp.getAssembleParts())`; `:uName` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `DmServer.getServerTimestamp()`; `:taskId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:remark` ← `DmShared.xNull(mrTemp.getDownReason())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key."
- Other calls: `MachineDownEntry_Vld.chkSave`

### `getMoveRec`
`public DmResult<MachineDownEntry_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT mmd.docId, mmd.recId, mmd.recId_Prefix, mmd.v_no, mmd.v_Date, mmd.v_Prefix, mmd.v_Type, mmd.assetCode, mmd.machineDownDate, mmd.machineDownTime, mmd.downReason, mmd.machineUpDate, mmd.machineUpTime, mmd.mechanicName, mmd.mechanicRemark, mmd.assembleParts, mmd.u_Name, mmd.preparedBy, aim.name as machineItem , am.assetItemCode as machineItemCode , am.manualCode as machineName, vt.description from machineMaintainanceDetail mmd left join voucher_Type vt on vt.v_Type = mmd.v_Type left join assetMast am on am.code = mmd.assetCode left join assetItemMast aim on aim.code = am.assetItemCode where mmd.docId = :code`
- SQL 2: `SELECT mmd.docId, mmd.recId, mmd.v_Date, mmd.machineDownDate, mmd.machineDownTime, mmd.downReason, mmd.machineUpDate, mmd.machineUpTime, mmd.mechanicName, mmd.mechanicRemark, mmd.assembleParts from machineMaintainanceDetail mmd where mmd.assetCode = :assetCode`
- SQL 3: `select tsh.taskId,tsh.scheduleDate,tsh.scheduleTime,tsh.isStatus, case tsh.isStatus when ' {…} ' then ' {…} ' when ' {…} ' then ' {…} ' when ' {…} ' then ' {…} ' when ' {…} ' then ' {…} ' when ' {…} ' then ' {…} ' when ' {…} ' then ' {…} ' when ' {…} ' then ' {…} ' else '' end as taskStatus, tsh.statusRemark,tsh.preparedBy as userName,format(tsh.u_entDt, 'dd-MMM-yyyy HH:mm') as u_entDt from taskScheduleHistory as tsh where tsh.taskid = :taskid order by tsh.u_entDt desc`
- SQL 4: `select ts.scheduleDate,ts.scheduleTime from taskSchedule as ts where ts.taskid = :taskid`
- Params: `:code` ← `strKey`; `:assetCode` ← `DmShared.iNull(mrTemp.getMachineCode()).toString()`; `:taskid` ← `strKey`; `:taskid` ← `strKey`
- Other calls: `CANCEL.getId`, `CANCEL.getValue`, `CLOSE.getId`, `CLOSE.getValue`, `COMPLETE.getId`, `COMPLETE.getValue`, `FOLLOW_UP.getId`, `FOLLOW_UP.getValue`, `OPEN.getId`, `OPEN.getValue`, `RE_OPEN.getId`, `RE_OPEN.getValue`, `START.getId`, `START.getValue`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `select mmd.docId from machineMaintainanceDetail mmd left join voucher_Type vt on vt.v_Type = mmd.v_Type where mmd.v_Prefix = :v_prefix and mmd.site_Code = :site_Code and vt.category in (' {…} ')`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getTxtHMachineItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachineItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim left join assetSiteList asl on asl.code= aim.Code where asl.site_Code = :siteCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHMachineName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachineName(String assetItemCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, manualCode from assetMast where assetItemCode = :assetItem`
- Params: `:assetItem` ← `assetItemCode`

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`

- SQL 1: `select v_Type, description from voucher_Type where short_Name = ' {…} '`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT mmd.docId, mmd.recId, mmd.v_no, mmd.v_Date, mmd.v_Type, vt.short_Name, mmd.machineDownDate, mmd.machineDownTime, mmd.downReason, mmd.machineUpDate, mmd.machineUpTime, mmd.mechanicName,mmd.mechanicRemark, mmd.assembleParts, aim.name as machineItem , am.manualCode as machineName, vt.description from machineMaintainanceDetail mmd left join voucher_Type vt on vt.v_Type = mmd.v_Type left join assetMast am on am.code = mmd.assetCode left join assetItemMast aim on aim.code = am.assetItemCode where mmd.site_Code = :site_Code and vt.category in (' {…} ') and mmd.v_prefix = :v_prefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `select count(*) as cnt from taskScheduleHistory where taskId=:taskId`
- SQL 2: `Delete From taskSchedule Where taskId= :taskId`
- SQL 3: `Delete From machineMaintainanceDetail Where docId= :docId`
- Params: `:taskId` ← `strKey`; `:taskId` ← `strKey`; `:docId` ← `strKey`
- Errors: "Record is already in use you can't delete this record."

### `getMachineDetail`
`public DmResult<List<MachineDownEntry_Mdl>> getMachineDetail(String assetCode)`

- SQL 1: `SELECT mmd.docId, mmd.recId, mmd.v_Date, mmd.machineDownDate, mmd.machineDownTime, mmd.downReason, mmd.machineUpDate, mmd.machineUpTime, mmd.mechanicName, mmd.mechanicRemark, mmd.assembleParts from machineMaintainanceDetail mmd where mmd.assetCode = :assetCode`
- Params: `:assetCode` ← `DmShared.xNull(assetCode)`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.openSourceReport.client`, `dataman.dmToolkit.self.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record is already in use you can't delete this record." |

## Notes (hand-written)
_No hand-written notes yet._
