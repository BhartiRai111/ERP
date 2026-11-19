# pb-gate / visitorGatePass

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/gate/src/visitorGatePass/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: visitorGatePass
- Update tables: visitorGatePass
- Delete cascade (in order): visitorGatePass
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Date (Text); Time (Text); In Gate No (Text); Visitor Name (Text); Address (Text); City (Text); Vehicle Type (Text); Vehicle No. (Text); Person To Meet (Text); Purpose (Text); Out Date (Text); Out Time (Text); Out Gate No. (Text); rule: Out date can not be less than in date.; rule: Out time can not be less than in time.
- Lookups/suggestions: getTxtHVoucher, getHVehicleType, getTxtHGateNo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `VisitorGatePass`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Gate Pass Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 4, true` |
| 2 | Date/Time | DmTextBox | `txtDate` | `11, true` |
| 3 | - | DmTextBoxTime | `txtTime` | `true` |
| 4 | Gate Pass Name | DmTextBox | `txtEntryNo` | `20, false` |
| 5 | Mobile No. | DmTextBoxMobile | `txtMobile` | `false` |
| 6 | In Gate No. | DmTextHelp | `txtHGateNo` | `KeyField_PrpAcs, 5, true` |
| 7 | Visitor Name | DmTextBox | `txtVisitorName` | `100, true` |
| 8 | Organization Name | DmTextBox | `txtOrganizationName` | `100, false` |
| 9 | Address | DmRemarkTextBox | `txtAddress` | `250, true` |
| 10 | City | DmTextHelp | `txtHCity` | `txtHCity_PrpAcs, 5, true` |
| 11 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `KeyField_PrpAcs, 5, false` |
| 12 | Vehicle No. | DmTextBox | `txtVehicleNo` | `15, false` |
| 13 | Person To Meet | DmTextBox | `txtPersonToMeet` | `100, true` |
| 14 | Purpose | DmRemarkTextBox | `txtPurpose` | `250, true` |

Other widgets (not in tab list): `txtGateOutDate` (DmTextBox), `txtSlipNo` (DmTextBox), `txtGateOutTime` (DmTextBoxTime), `txtHOutGateNo` (DmTextHelp), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHGateNo`: Gate No. [Text, w0, name]
- `txtHOutGateNo`: Gate No. [Text, w0, name]
- `txtHCity`: City [Text, w100, cityname]; State [Text, w100, statename]
- `txtHVehicleType`: Vehicle Type [Text, w0, name]
- `dmFind`: Gate Pass No. [Text, w100, recId]; In Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; In Time [Text, w80, v_Time]; Gate No. [Text, w90, inGateNoCode]; Out Date [Text, w100, replace(convert(varchar, gateOutDate, 106), ' ','-')]; Out Time [Text, w80, gateOutTime]; Out Gate No. [Text, w90, outGateNoname]; Visitor Name [Text, w150, name]; Organization [Text, w150, organization]; Mobile [Text, w110, mobile]; Person To Meet [Text, w120, personToMeet]; Purpose [Text, w120, purpose]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCity` → `getTxtCity`
- `txtHVoucherType` → `getTxtHVoucher`
- `txtHVehicleType` → `getHVehicleType`
- `txtHGateNo` → `getTxtHGateNo`
- `txtHOutGateNo` → `getTxtHGateNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtEntryNo.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `InDate` ← DmShared.dNull(txtDate.getText())
- `InTime` ← DmShared.vNull(txtTime.getText()).toString()
- `VisitorName` ← DmShared.removeSpace(DmShared.xNull(txtVisitorName.getText()))
- `Address` ← DmShared.xNull(txtAddress.getText())
- `City_Code` ← DmShared.xNull(txtHCity.getTag())
- `City_Name` ← DmShared.xNull(txtHCity.getText())
- `Mobile` ← DmShared.xNull(txtMobile.getText())
- `VehicleTypeCode` ← DmShared.xNull(txtHVehicleType.getTag())
- `VehicleTypeName` ← DmShared.xNull(txtHVehicleType.getText())
- `VehicleNo` ← DmShared.xNull(txtVehicleNo.getText())
- `PersonToMeet` ← DmShared.removeSpace(DmShared.xNull(txtPersonToMeet.getText()))
- `Purpose` ← DmShared.xNull(txtPurpose.getText())
- `GateOutDate` ← DmShared.dNull(txtGateOutDate.getText())
- `GateOutTime` ← DmShared.xNull(txtGateOutTime.getText())
- `OrganizationName` ← DmShared.xNull(txtOrganizationName.getText())
- `InGateNoCode` ← DmShared.xNull(txtHGateNo.getTag())
- `InGateNoName` ← DmShared.xNull(txtHGateNo.getText())
- `OutGateNoCode` ← DmShared.xNull(txtHOutGateNo.getTag())
- `OutGateNoName` ← DmShared.xNull(txtHOutGateNo.getText())
- `vTypeCode` ← txtHVoucherType.getTag()
- `vTypeName` ← txtHVoucherType.getText()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtEntryNo.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtDate.setText` ← data.getInDate()
- `txtTime.setText` ← data.getInTime()
- `txtHVoucherType.setTag` ← data.getVoucherTypeCode()
- `txtHVoucherType.setText` ← data.getVoucherTypeName()
- `txtVisitorName.setText` ← data.getVisitorName()
- `txtAddress.setText` ← data.getAddress()
- `txtHCity.setTag` ← data.getCity_Code()
- `txtHCity.setText` ← data.getCity_Name()
- `txtMobile.setText` ← data.getMobile()
- `txtHVehicleType.setTag` ← DmShared.xNull(data.getVehicleTypeCode())
- `txtHVehicleType.setText` ← DmShared.xNull(data.getVehicleTypeName())
- `txtVehicleNo.setText` ← data.getVehicleNo()
- `txtPersonToMeet.setText` ← data.getPersonToMeet()
- `txtPurpose.setText` ← data.getPurpose()
- `txtGateOutDate.setText` ← data.getGateOutDate()
- `txtGateOutTime.setText` ← DmShared.xNull(data.getGateOutTime())
- `txtOrganizationName.setText` ← DmShared.xNull(data.getOrganizationName())
- `txtHGateNo.setText` ← DmShared.xNull(data.getInGateNoName())
- `txtHGateNo.setTag` ← DmShared.xNull(data.getInGateNoCode())
- `txtHOutGateNo.setTag` ← DmShared.xNull(data.getOutGateNoCode())
- `txtHOutGateNo.setText` ← DmShared.xNull(data.getOutGateNoName())
- `txtSlipNo.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(data.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy())
- `userControl.setTextBlog` ← gateOutByBlog, DmShared.xNull(data.getGateOutBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(data.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy()))
- userControl.setTextBlog(gateOutByBlog, DmShared.xNull(data.getGateOutBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtEntryNo.setEnabled(false)`
- `txtMobile.setEnabled(true)`
- `txtMobile.setEnabled(false)`
- `txtDate.setEnabled(false)`
- `txtTime.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtGateOutDate.setEnabled(dateTimeEditable)`
- `txtGateOutTime.setEnabled(dateTimeEditable)`
- `txtSlipNo.setEnabled(true)`
- `txtHOutGateNo.setEnabled(true)`
- `txtGateOutDate.setEnabled(false)`
- `txtGateOutTime.setEnabled(false)`
- `txtSlipNo.setEnabled(false)`
- `txtHOutGateNo.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtEntryNo.setEnabled(false);
flwInDateTimeContainer.clear();
flwGateOutContainer.clear();
flwSearchTime.clear();
flwSearchGatePass.clear();
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("New")) {
txtMobile.setEnabled(true);
} else {
txtMobile.setEnabled(false);
if (flag) {
if (!dateTimeEditable) {
txtDate.setEnabled(false);
txtTime.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
if (dateTimeEditable) {
flwInDateTimeContainer.add(flwInDateTime);
} else {
flwInDateTimeContainer.add(flwInDateTime);
} else {
if (DmShared.xNull(userControl.getTextBlog(gateOutByBlog)).equalsIgnoreCase("")) {
if (isGateOutPermission) {
flwGateOutContainer.add(flwGateOutEntry);
if (!DmShared.xNull(topControl.getDocId()).trim().equalsIgnoreCase("")) {
...
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
if (DmShared.xNull(userControl.getTextBlog(gateOutByBlog)).equalsIgnoreCase("")) {
manageDisplay(true);
txtVisitorName.setFocus(true);
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is mark as. You can't modify this record.", (DmTextBox) null).show();
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
if (topControl.getMode().equalsIgnoreCase("New")) {
topControlSave();
} else {
showModifyReason();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(VisitorGatePass.this);
```

`topControl.addFindHandeller` → `findClicked()`
```java
flwForm.remove(vrtInnerForm);
flwForm.add(dmFind);
```

`visitorGatePassSearch.addCallBack` → `ok()`
```java
gateOutFlag = true;
topControlMoveRec(strKey);
```

`visitorGatePassSearch.addCallBack` → `close()`
```java
txtSlipNo.setFocus(true);
```

`modifyReason.addCallBack` → `onOk()`
```java
VisitorGatePass.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
```

`txtHCity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHCity_M
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCity).show();
public void onSuccess(DmResultPageing<List<TxtHCity_Mdl>> result) {
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCity).show();
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucher(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHVoucherType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag2nd()))) {
txtHVoucherType.setTag2nd(txtHVoucherType.getTag());
```

`txtHVehicleType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHVehicleType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVehicleType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVehicleType).show();
```

`txtHVehicleType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVehicleType).show();
```

`txtHGateNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateNo(DmShared.iNull(STEnum.GateType.OUT.getCode()).toString(), helper, blnT
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGateNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGateNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateNo).show();
```

`txtHGateNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGateNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGateNo).show();
```

`txtHOutGateNo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGateNo(DmShared.iNull(STEnum.GateType.IN.getCode()).toString(), helper, blnTo
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOutGateNo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOutGateNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOutGateNo).show();
```

`txtHOutGateNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOutGateNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOutGateNo).show();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(topControl.getPermissions(), helper, blnToEndOfFile, new AsyncCallback<DmRes
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

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Process Message

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to Process Message."
- Alert: "Record is mark as. You can't modify this record."
- Alert: "Enter Mobile No."
- Alert: "No Record Found"
- Alert: "Can be use in Browse Mode only and select a record."
- Alert: "Message is processed successfully!"
- Alert: "Message can be send only in Browse Mode!"

## Files
| file | lines |
|---|---|
| client/VisitorGatePass.java | 1072 |
| client/VisitorGatePass_Dsg.java | 706 |
| server/VisitorGatePass_SrvImpl.java | 1323 |
| shared/Default_Mdl.java | 59 |
| shared/Find_Mdl.java | 186 |
| shared/TxtHCity_Mdl.java | 23 |
| shared/TxtHVoucher_Mdl.java | 23 |
| shared/VisitorGatePass_Mdl.java | 268 |
| shared/VisitorGatePass_Srv.java | 45 |
| shared/VisitorGatePass_SrvAsync.java | 44 |
| shared/VisitorGatePass_Vld.java | 44 |

## UI labels / columns (client)
`Gate Pass Type`, `Date/Time`, `Gate Pass Name`, `Mobile No.`, `In Gate No.`, `Visitor Name`, `Organization Name`, `Address`, `City`, `Vehicle Type`, `Vehicle No.`, `Person To Meet`, `Purpose`, `Slip No.`, `Gate Out Date/Time`, `Out Gate No.`

## Models (shared DTOs)
### TxtHVoucher_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

### VisitorGatePass_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | entryNo |
| String | inDate |
| String | inTime |
| String | visitorName |
| String | address |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | vehicleNo |
| String | personToMeet |
| String | gateOutDate |
| String | gateOutTime |
| String | GateOutBy |
| String | purpose |
| String | city_Code |
| String | mobile |
| String | v_Type |
| ModifyReason_Mdl | modifyReasonMdl |
| String | organizationName |
| String | inGateNoCode |
| String | inGateNoName |
| String | outGateNoCode |
| String | outGateNoName |
| DmSnapshot_Mdl | snapshot_Mdl |
| String | city_Name |

### Default_Mdl
| type | field |
|---|---|
| String | vTypeCode |
| String | vTypeName |
| String | date |
| String | time |
| DmFileRuleType | fileRuleTypeObj |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | inDate |
| String | inTime |
| String | visitorName |
| String | address |
| String | mobile |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | vehicleNo |
| String | personToMeet |
| String | gateOutDate |
| String | gateOutTime |
| String | purpose |
| String | cityName |
| String | entryNo |
| String | organization |
| String | inGateNoCode |
| String | inGateNoName |
| String | outGateNoCode |
| String | outGateNoName |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getMoveRec | String strKey | DmResult<VisitorGatePass_Mdl> |
| sendMessage | String strKey, String vTypeCode | DmResult<String> |
| setGateOut | VisitorGatePass_Mdl mrTemp, boolean isGateOut | DmResult<String> |
| saveRecord | VisitorGatePass_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getDefaultValues |  | DmResult<Default_Mdl> |
| getVisitorDetails | String mobileNo | DmResult<VisitorGatePass_Mdl> |
| getTxtHVoucher | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtCity | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getHVehicleType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGateNo | String excludeGateType, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultDateTime |  | DmResult<Default_Mdl> |
| printRecords | String docId | DmResult<String> |

## Validator `VisitorGatePass_Vld`
```java
public class VisitorGatePass_Vld {
    public static void chkSave(VisitorGatePass_Mdl mrTemp, boolean isOutEntry) {
        DmShared.isValidText(mrTemp.getInDate(), "Date", 100);
        DmShared.isValidText(mrTemp.getInTime(), "Time", 200);
        DmShared.isValidText(mrTemp.getInGateNoCode(), "In Gate No", 150);
        if (!isOutEntry) {
            DmShared.isValidText(mrTemp.getVisitorName(), "Visitor Name", 300);
            DmShared.isValidText(mrTemp.getAddress(), "Address", 400);
            DmShared.isValidText(mrTemp.getCity_Code(), "City", 500);
            if (!DmShared.xNull(mrTemp.getMobile()).trim().equalsIgnoreCase("")) {
                DmShared.isValidMobile(mrTemp.getMobile(), 600);
            }
            if (!DmShared.xNull(mrTemp.getVehicleTypeCode()).trim().equalsIgnoreCase("") || !DmShared.xNull(mrTemp.getVehicleNo()).trim().equalsIgnoreCase("")) {
                DmShared.isValidText(mrTemp.getVehicleTypeCode(), "Vehicle Type", 700);
                DmShared.isValidText(mrTemp.getVehicleNo(), "Vehicle No.", 750);
            }
            DmShared.isValidText(mrTemp.getPersonToMeet(), "Person To Meet", 800);
            DmShared.isValidText(mrTemp.getPurpose(), "Purpose", 900);
        } else {
            DmShared.isValidText(mrTemp.getGateOutDate(), "Out Date", 1000);
            DmShared.isValidText(mrTemp.getGateOutTime(), "Out Time", 1100);
            DmShared.isValidText(mrTemp.getOutGateNoCode(), "Out Gate No.", 1200);
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getGateOutDate())).getTime() < DmShared.convertDate(DmShared.dNull(mrTemp.getInDate())).getTime()) {
                throw new DmException(800, "Out date can not be less than in date.");
            }
            if (DmShared.convertDate(DmShared.dNull(mrTemp.getGateOutDate())).getTime() == DmShared.convertDate(DmShared.dNull(mrTemp.getInDate())).getTime()) {
                if (DmShared.vNull(mrTemp.getGateOutTime()).doubleValue() < DmShared.vNull(mrTemp.getInTime()).doubleValue()) {
                    throw new DmException(900, "Out time can not be less than in time.");
                }
            }
        }
    }
}
```

## Server `VisitorGatePass_SrvImpl.java` (1323 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.isDocumentEMailRequired`, `DmServer.sendMail`, `DmServer.setSsnOldData`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT vgp.docId FROM visitorGatePass vgp LEFT JOIN voucher_Type vt ON vgp.v_Type = vt.v_Type WHERE vt.category=(' {…} ') AND vgp.site_Code = :site_Code AND vgp.v_prefix = :v_Prefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vgp.docId ,vgp.recId, vgp.v_Date ,vgp.v_Time ,vgp.name ,vgp.address ,vgp.mobile ,vgp.inGateNoCode ,gnm.name as inGateNoname ,gnmout.name as outGateNoname ,vgp.outGateNoCode ,vgp.vehicleTypeCode ,vtm.name as vehicleTypeName ,vgp.vehicleNo ,vgp.personToMeet ,vgp.purpose ,vgp.gateOutDate ,vgp.gateOutTime ,vgp.organization , ctb.cityName FROM visitorGatePass vgp LEFT JOIN City ctb ON ctb.cityCode = vgp.cityCode Left join gatenomast gnm ON gnm.code = vgp.inGateNoCode Left join gatenomast gnmout ON gnmout.code = vgp.outGateNoCode Left join vehicleTypeMast vtm ON vtm.Code = vgp.vehicleTypeCode LEFT JOIN voucher_Type vt ON vgp.v_Type = vt.v_Type WHERE vt.category=(' {…} ') AND vgp.v_prefix = :v_Prefix`
- Params: `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getMoveRec`
`public DmResult<VisitorGatePass_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT vgp.docId ,vgp.recId ,vgp.recIdPrefix ,vgp.v_Type ,vgp.v_No ,vgp.v_Date ,vgp.v_Time ,vgp.name ,vgp.u_Name ,vgp.cityCode ,vgp.address ,vgp.mobile ,vgp.vehicleTypeCode ,vtm.name as vehicleTypeName ,vgp.vehicleNo ,vgp.personToMeet ,vgp.purpose ,vgp.gateOutDate ,vgp.gateOutTime ,vgp.preparedBy ,vgp.gateOutBy , ct.cityName , vgp.organization , vgp.inGateNoCode , vgp.outGateNoCode , gnm.name as inGateNoname , vgp.preparedDt , vgp.preparedByIP ,vgp.u_EntDt , vt.description AS voucherTypeName , gnmo.name as outGateNoName FROM visitorGatePass vgp LEFT JOIN voucher_Type vt ON vt.v_Type = vgp.v_Type LEFT JOIN City ct ON ct .cityCode = vgp.cityCode Left join vehicleTypeMast vtm ON vtm.Code = vgp.vehicleTypeCode Left join gatenomast gnm ON gnm.code = vgp.inGateNoCode Left join gatenomast gnmo ON gnmo.code = vgp.outGateNoCode`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `VISITOR_GATE_PASS.getRuleCode`

### `getMongoDBFileLink`
`private String getMongoDBFileLink(String ruleCode, String Docid, String Sno, Connection cnTemp) throws Exception`

- SQL 1: `Select fileLink from linkFileCollection where docId=' {…} ' and sNo=' {…} ' and ruleCode=' {…} '`

### `setGateOut`
`public DmResult<String> setGateOut(VisitorGatePass_Mdl mrTemp, boolean isGateOut)`

- SQL 1: `select vgp.gateOutBy, vgp.gateOutDate, vgp.gateOutTime ,vgp.outGateNoCode ,(case when :u_Name IN (ISNULL(gateOutBy,''),'SA','SUPER') then 1 else 0 end) canGateOut , vgp.u_EntDt, vgp.u_Name from visitorGatePass vgp where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:u_Name` ← `DmServer.getSsnUserName(this)`
- Errors: "Visitor already gate out." | "Unauthorized user to gate out." | "Visitor already gate in."
- Other calls: `VisitorGatePass_Vld.chkSave`

### `saveRecord`
`public DmResult<String> saveRecord(VisitorGatePass_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `INSERT INTO visitorGatePass( docId,v_Type,v_No,recIdPrefix,recId,v_Prefix,v_Date,v_Time ,name,cityCode, address,mobile,vehicleTypeCode,vehicleNo,personToMeet,purpose, preparedBy ,site_Code, preparedDt, preparedByIP, organization, ingateNoCode ) VALUES( :docId, :v_Type, :v_No, :recIdPrefix, :recId, :v_Prefix, :v_Date ,:v_Time, :name, :cityCode, :address, :mobile, :vehicleTypeCode, :vehicleNo ,:personToMeet, :purpose, :preparedBy, :site_Code ,:preparedDt, :preparedByIP ,:organization ,:ingateNoCode)`
- SQL 2: `UPDATE visitorGatePass SET v_Type = :v_Type ,v_Date = :v_Date ,v_Time = :v_Time ,name = :name ,cityCode = :cityCode ,address = :address ,mobile = :mobile ,ingateNoCode = :ingateNoCode ,vehicleTypeCode = :vehicleTypeCode ,vehicleNo = :vehicleNo ,personToMeet = :personToMeet ,purpose= :purpose ,u_EntDt = :u_EntDt ,u_Name = :u_Name WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Prefix` ← `voucherPrefix`; `:v_Date` ← `DmServer.convertDate(mrTemp.getInDate())`; `:v_Time` ← `DmServer.vNull(mrTemp.getInTime())`; `:name` ← `DmShared.xNull(mrTemp.getVisitorName())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCity_Code())`; `:address` ← `DmShared.xNull(mrTemp.getAddress())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobile())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:personToMeet` ← `DmShared.xNull(mrTemp.getPersonToMeet())`; `:purpose` ← `DmShared.xNull(mrTemp.getPurpose())`; `:preparedBy` ← `userName`; `:preparedDt` ← `timestamp`; `:organization` ← `DmShared.xNull(mrTemp.getOrganizationName())`; `:ingateNoCode` ← `DmShared.foriegnKey(mrTemp.getInGateNoCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Type` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getInDate())`; `:v_Time` ← `DmServer.vNull(mrTemp.getInTime())`; `:name` ← `DmShared.xNull(mrTemp.getVisitorName())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCity_Code())`; `:address` ← `DmShared.xNull(mrTemp.getAddress())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobile())`; `:ingateNoCode` ← `DmShared.foriegnKey(mrTemp.getInGateNoCode())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:personToMeet` ← `DmShared.xNull(mrTemp.getPersonToMeet())`; `:purpose` ← `DmShared.foriegnKey(mrTemp.getPurpose())`; `:u_Name` ← `userName`; `:u_EntDt` ← `timestamp`
- Errors: "Invalid key."
- Other calls: `VisitorGatePass_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `select gateOutBy from visitorGatePass where docId = :docId`
- SQL 2: `Delete From visitorGatePass Where docId= :docId`
- SQL 3: `SELECT lfc.sNo, lfc.ruleCode FROM linkFileCollection lfc WHERE docid = :subCode`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`; `:docId` ← `DmShared.iNull(strKey).toString()`; `:subCode` ← `DmShared.iNull(strKey).toString()`
- Errors: "Visitor already gate out. You can't delete this record."
- Other calls: `ModifyReason_Server.delete`

### `getDefaultValues`
`public DmResult<Default_Mdl> getDefaultValues()`

- SQL 1: `select TOP 1 vt.v_Type, vt.description FROM voucher_Type vt WHERE vt.category = ' {…} ' AND vt.isActive = 1`
- SQL 2: `select ftm.ruleCode , ftm.fileType , ftm.maxSize , ftm.extFilters , ftm.description from {…} .dbo.fileTypeMast ftm WHERE ruleCode = :ruleCode and isDefault =1`
- Params: `:ruleCode` ← `ProjectRuleCode.VISITOR_GATE_PASS.getRuleCode()`
- Other calls: `VISITOR_GATE_PASS.getRuleCode`

### `getTxtHVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucher(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type as code , vt.description as name , vt.category , vt.short_Name FROM voucher_Type vt WHERE vt.category=(' {…} ') AND vt.isActive = 1`

### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `getTxtCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode , ct.cityName , sm.name as stateName from city ct left join stateMast sm on sm.code = ct.stateCode`

### `getDefaultDateTime`
`public DmResult<Default_Mdl> getDefaultDateTime()`


### `getTxtHGateNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGateNo(String excludeGateType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gn.code AS code , gn.name AS name FROM gateNoMast gn {…}`
- Params: `:type` ← `excludeGateType`

### `getVisitorDetails`
`public DmResult<VisitorGatePass_Mdl> getVisitorDetails(String mobileNo)`

- SQL 1: `SELECT top 1 vgp.docId,vgp.name,vgp.organization,vgp.address,vgp.cityCode,ct.cityName, vgp.vehicleNo,vtm.name as vehicleTypeName, vgp.vehicleTypeCode,vgp.personToMeet FROM visitorGatePass vgp LEFT JOIN voucher_Type vt ON vt.v_Type = vgp.v_Type LEFT JOIN vehicleTypeMast vtm on vtm.code = vgp.vehicleTypeCode LEFT JOIN City ct ON ct .cityCode = vgp.cityCode WHERE vt.category=(' {…} ') AND vgp.mobile = :mobileNo order By v_Date,v_Time Desc`
- Params: `:mobileNo` ← `mobileNo`
- Other calls: `VISITOR_GATE_PASS.getRuleCode`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT vgp.docId, vgp.recId, CONCAT(FORMAT(vgp.v_Date, 'dd-MMM-yyyy'), ' ', REPLACE(FORMAT(vgp.v_Time,'00.00'),'.',':')) as dateTime, vgp.name as visitorName, CONCAT(vgp.address, '', ctb.cityName) as address, vgp.mobile, vtm.name AS vehicleType, vgp.vehicleNo, gni.name AS gateInNo , gno.name AS gateOutNo , vgp.personToMeet, vgp.purpose FROM visitorGatePass vgp LEFT JOIN City ctb ON ctb.cityCode = vgp.cityCode LEFT JOIN voucher_Type vt ON vgp.v_Type = vt.v_Type LEFT JOIN gateNoMast as gni ON gni.code = vgp.inGateNoCode LEFT JOIN gateNoMast as gno ON gno.code = vgp.outGateNoCode Left Join vehicleTypeMast as vtm ON vtm.code = vgp.vehicleTypeCode where vgp.docId =:docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

### `sendMessage`
`public DmResult<String> sendMessage(String strKey, String vTypeCode)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmFileManager.shared`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmSnapshot.client`, `dataman.dmToolkit.dmSnapshot.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxMobile.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.gate.visitorGatePass.shared`, `paperbiz.gate.visitorGatePassSearch.client`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.modifyReason.server`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| setGateOut | (unconditional) | "Visitor already gate out." |
| setGateOut | (unconditional) | "Unauthorized user to gate out." |
| setGateOut | (unconditional) | "Visitor already gate in." |
| saveRecord | `if (DmShared.xNull(mrTemp.getSharedKeyFieldCode()).equalsIgnoreCase(""))` | "Invalid key." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("gateOutBy")).equalsIgnoreCase(""))` | "Visitor already gate out. You can't delete this record." |

## Notes (hand-written)
_No hand-written notes yet._
