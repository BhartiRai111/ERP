# pb-master / policyMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/policyMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: policyMast
- Update tables: policyMast, pm
- Delete cascade (in order): policyMast, policyTrans
- Duplicate checks on save:
  - `select count(*) as cnt from policyMast pM where pM.insuranceComp=:insuranceComp and pM.name=:name And Code <> :Code`
- "In use" checks before delete:
  - `select count(*) cnt from policyTrans where policyCode = :policyCode and docId <> :policyCode`
- Validations: Policy No. (Text); Insurance Company (Text); From Date (Text); To Date (Text); Entry Date (Text); Amount (Amount); rule: To Date should be greater than From Date.
- Lookups/suggestions: getTxtHInsuranceCompany, getTxtHIsActive, getTxtHValuationType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PolicyMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Policy No. | DmTextBox | `txtPolicyNo` | `50, true` |
| 2 | Policy/Renewal Date | DmTextBox | `txtEntryDate` | `11, true` |
| 3 | Insurance Company | DmTextHelp | `txtHInsuranceComp` | `KeyField_PrpAcs, 5, true` |
| 4 | From Date | DmTextBox | `txtFromDate` | `11, true` |
| 5 | To Date | DmTextBox | `txtToDate` | `11, true` |
| 6 | Description | DmRemarkTextBox | `txtDescription` | `250, false` |
| 7 | Amount | DmTextBoxNumeric | `txtAmt` | `2, false, 12, true` |
| 8 | Valuation Type | DmTextHelp | `txtHValuationType` | `KeyField_PrpAcs, 4, true, false` |
| 9 | Value | DmTextBoxNumeric | `txtValue` | `4, false, 10, false` |
| 10 | Remarks | DmRemarkTextBox | `txtRemarks` | `250, false` |
| 11 | Active | DmTextHelp | `txtHIsActive` | `KeyField_PrpAcs, 5, true` |
| 12 | Policy Renewal Details | DmGridPaging | `gridPolicyHistory` | `GrdPaging_PrpAcs, 5, false` |

Other widgets (not in tab list): `txtSearchValue` (DmTextBox), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHInsuranceComp`: Name [Text, w0, insuranceComp]
- `txtHIsActive`: Type [Text, w0, name]
- `txtHValuationType`: Type [Text, w0]
- `dmFind`: Policy No [Text, w0, name]; Insurance Company [Text, w250, insuranceComp]; From Date [Text, w100, dateFrom]; To Date [Text, w100, dateTo]; Amount [Text, w100, amount]; Valuation Type [Text, w250, evalTypeText]; Value [Text, w100, evalRate]; Is Active [Text, w100, isActive]
- `gridPolicyHistory`: Renewal Date [Text, w100, replace(convert(varchar, renewalDate, 106), ' ','-')]; From Date [Text, w100, replace(convert(varchar, fromDate, 106), ' ','-')]; To Date [Text, w100, replace(convert(varchar, toDate, 106), ' ','-')]; Policy Amount [Text, w150, policyAmt]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHInsuranceComp` → `getTxtHInsuranceCompany`
- `txtHIsActive` → `getTxtHIsActive`
- `txtHValuationType` → `getTxtHValuationType`
- `gridPolicyHistory` → `getFillHistory`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `PolicyNo` ← DmShared.removeSpace(DmShared.xNull(txtPolicyNo.getText().trim()))
- `InsuranceCompany` ← DmShared.removeSpace(DmShared.xNull(txtHInsuranceComp.getText()))
- `FromDate` ← DmShared.xNull(txtFromDate.getText())
- `ToDate` ← DmShared.xNull(txtToDate.getText())
- `Description` ← DmShared.xNull(txtDescription.getText())
- `Amt` ← DmShared.xNull(txtAmt.getText())
- `IsActiveCode` ← DmShared.xNull(txtHIsActive.getTag())
- `IsActiveText` ← DmShared.xNull(txtHIsActive.getText())
- `EvalTypeCode` ← DmShared.xNull(txtHValuationType.getTag())
- `EvalTypeText` ← DmShared.xNull(txtHValuationType.getText())
- `EvalRate` ← DmShared.xNull(txtValue.getText())
- `Remark` ← DmShared.xNull(txtRemarks.getText())
- `U_EntDt_LatestLine` ← DmShared.iNull(txtPolicyNo.getTag()).toString()
- `EntryDate` ← DmShared.dNull(txtEntryDate.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtPolicyNo.setText` ← data.getPolicyNo()
- `txtPolicyNo.setTag` ← DmShared.iNull(data.getU_EntDt_LatestLine()).toString()
- `txtHInsuranceComp.setText` ← data.getInsuranceCompany()
- `txtFromDate.setText` ← data.getFromDate()
- `txtToDate.setText` ← data.getToDate()
- `txtDescription.setText` ← data.getDescription()
- `txtAmt.setText` ← data.getAmt()
- `txtHIsActive.setTag` ← data.getIsActiveCode()
- `txtHIsActive.setText` ← data.getIsActiveText()
- `txtRemarks.setText` ← DmShared.xNull(data.getRemark())
- `txtHValuationType.setTag` ← DmShared.xNull(data.getEvalTypeCode())
- `txtHValuationType.setText` ← DmShared.xNull(data.getEvalTypeText())
- `txtValue.setText` ← DmShared.xNull(data.getEvalRate())
- `txtEntryDate.setText` ← DmShared.dNull(data.getEntryDate())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHIsActive.setEnabled(flag)`
- `txtPolicyNo.setEnabled(flag)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtHIsActive.setEnabled(flag);
txtPolicyNo.setEnabled(flag);
if (topControl.getMode().equals("Browse")) {
if (allowRenewalpolicy) {
flwMainBtnRenew.add(flwBtnRenew);
if (allowRemoveRenewalpolicy && isRenewalExist) {
flwMainBtnRemove.add(flwBtnRemove);
} else {
flwMainBtnRenew.remove(flwBtnRenew);
flwMainBtnRemove.remove(flwBtnRemove);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
PolicyMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
txtPolicyNo.setFocus(true);
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
txtHIsActive.setEnabled(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PolicyMaster.this);
```

`txtHInsuranceComp.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHInsuranceCompany(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHInsuranceComp).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHInsuranceComp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInsuranceComp).show();
```

`txtHInsuranceComp.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHInsuranceComp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInsuranceComp).show();
```

`txtHIsActive.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHIsActive(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsActive).show();
```

`txtHIsActive.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsActive).show();
```

`txtHValuationType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHValuationType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHValuationType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHValuationType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHValuationType).show();
```

`txtHValuationType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHValuationType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHValuationType).show();
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
manageDisplay(false);
```

`policyRenewalPopUp.addCallBack` → `onOk()`
```java
txtFromDate.setText(DmShared.dNull(pm_Mdl.getFromDate()));
txtToDate.setText(DmShared.dNull(pm_Mdl.getToDate()));
txtEntryDate.setText(DmShared.dNull(pm_Mdl.getEntryDate()));
txtAmt.setText(DmClient.vNull(pm_Mdl.getAmt(), "0.00"));
isSaveRenewalPolicy = true;
policyRenewalPopUp.hide();
topControlSave();
reQuery();
```

`policyRenewalPopUp.addCallBack` → `onClose()`
```java
txtHValuationType.setFocus(true);
```

`gridPolicyHistory.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnToEndOfFile).getFillHistory(DmShared.xNull(topControl.getDocId()), helper, blnToEndOfFile, new Asy
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<GrdPolicyHistory_Mdl>> result) {
if (result.isStatus()) {
gridPolicyHistory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridPolicyHistory.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
gridPolicyHistory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`gridPolicyHistory.addCallBack` → `getParam()`
```java
return "";
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to delete this record."

### Screen `PolicyRenewalPopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Policy No. | DmTextBox | `txtPolicyNo` | `11, true` |
| 2 | From Date | DmTextBox | `txtFromDate` | `20, true` |
| 3 | To Date | DmTextBox | `txttoDate` | `100, true` |
| 4 | Amount | DmTextBoxNumeric | `txtAmt` | `4, true, 12, true` |
| 5 | Entry Date | DmTextBox | `txtEntryDate` | `11, true` |

## Files
| file | lines |
|---|---|
| client/PolicyMaster.java | 769 |
| client/PolicyMaster_Dsg.java | 428 |
| client/PolicyRenewalPopUp.java | 170 |
| client/PolicyRenewalPopUp_Dsg.java | 177 |
| server/PolicyMaster_SrvImpl.java | 955 |
| shared/Find_Mdl.java | 88 |
| shared/GrdPolicyHistory_Mdl.java | 52 |
| shared/PolicyMaster_Mdl.java | 157 |
| shared/PolicyMaster_Srv.java | 37 |
| shared/PolicyMaster_SrvAsync.java | 36 |
| shared/PolicyMaster_Vld.java | 25 |
| shared/TxtHPolicyNo_Mdl.java | 61 |

## UI labels / columns (client)
`Policy No.`, `From Date`, `To Date`, `Amount`, `Entry Date`, `Policy/Renewal Date`, `Insurance Company`, `Description`, `Valuation Type`, `Value`, `Remarks`, `Active`, `Policy Renewal Details`

## Models (shared DTOs)
### PolicyMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | policyNo |
| String | insuranceCompany |
| String | fromDate |
| String | toDate |
| String | description |
| String | amt |
| String | isActiveCode |
| String | isActiveText |
| String | remark |
| String | u_EntDt_LatestLine |
| String | entryDate |
| boolean | renewalExist |
| String | evalTypeCode |
| String | evalTypeText |
| String | evalRate |
| ModifyReason_Mdl | modifyReasonMdl |

### GrdPolicyHistory_Mdl (extends `KeyField implements IsSerializable, Serializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | policyAmt |
| String | renewalDate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | policyNo |
| String | insuranceComp |
| String | dateFrom |
| String | dateTo |
| String | amount |
| String | isActiveText |
| String | evalRate |
| String | evalTypeText |

### TxtHPolicyNo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | insuranceComp |
| String | policyNo |
| String | amt |
| String | isActiveCode |
| String | isActiveText |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<PolicyMaster_Mdl> |
| saveRecord | PolicyMaster_Mdl mrTemp, boolean isSaveRenewalPolicy | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| removeRenewalPolicy | String strKey, String fromDate, String toDate | DmResult<String> |
| getTxtHInsuranceCompany | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHIsActive | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFillHistory | String docId, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdPolicyHistory_Mdl>> |
| getTxtHValuationType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `PolicyMaster_Vld`
```java
public class PolicyMaster_Vld {
    public static void chkSave(PolicyMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getPolicyNo().trim(), "Policy No.", 100);
        DmShared.isValidText(mrTemp.getInsuranceCompany().trim(), "Insurance Company", 200);
        DmShared.isValidText(mrTemp.getFromDate().trim(), "From Date", 300);
        DmShared.isValidText(mrTemp.getToDate().trim(), "To Date", 400);
        DmShared.isValidText(mrTemp.getEntryDate().trim(), "Entry Date", 500);
        if (!DmShared.dNull(mrTemp.getFromDate()).trim().equalsIgnoreCase("") && !DmShared.dNull(mrTemp.getToDate()).trim().equalsIgnoreCase("")) {
            if ((DmShared.convertDate(mrTemp.getToDate()).getTime() <= (DmShared.convertDate(DmShared.dNull(mrTemp.getFromDate())).getTime()))) {
                throw new DmException(400, "To Date should be greater than From Date.");
            }
        }
        DmShared.isValidAmount(mrTemp.getAmt().trim(), "Amount", 600);
    }
}
```

## Server `PolicyMaster_SrvImpl.java` (955 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select pM.code as code From policyMast as pM`

### `getMoveRec`
`public DmResult<PolicyMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `select pm.code ,pm.name ,pm.insuranceComp ,pm.dateFrom ,pm.dateTo ,pm.entryDate ,pm.description ,pm.amount ,pm.isActive ,pm.preparedBy ,pm.preparedByIP ,pm.u_Name ,pm.preparedDt ,pm.u_EntDt ,pm.remark ,pm.evalRate ,pm.evalTypeCode ,pm.u_EntDt_LatestLine ,vpt.cnt from policyMast pm LEFT JOIN (select count(*) as cnt, pt.docId from policyTrans pt where pt.docId = :code group by pt.docId)vpt on vpt.docId = pm.code where pM.code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`
- Other calls: `ServiceEvalType.getViaCode`

### `saveRecord`
`public DmResult<String> saveRecord(PolicyMaster_Mdl mrTemp, boolean isSaveRenewalPolicy)`

- SQL 1: `select count(*) as cnt from policyMast pM where pM.insuranceComp=:insuranceComp and pM.name=:name And Code <> :Code`
- SQL 2: `SELECT u_EntDt_LatestLine, dateFrom, dateTo FROM policyMast WHERE code = :code`
- SQL 3: `select count(*) as cnt from policyTrans pt where pt.policyCode = :code AND ( :fromDate BETWEEN pt.dateFrom and pt.dateTo OR :toDate BETWEEN pt.dateFrom and pt.dateTo )`
- SQL 4: `Insert Into policyMast(code,name,insuranceComp, dateFrom,dateTo,entryDate,description,amount, preparedBy,preparedDt,preparedByIP, remark, evalTypeCode, evalRate, isActive) Values (:code,:name,:insuranceComp,:dateFrom,:dateTo,:entryDate,:description,:amount, :preparedBy,:preparedDt,:preparedByIP, :remark, :evalTypeCode, :evalRate, :isActive)`
- SQL 5: `Update policyMast set name = :name, insuranceComp = :insuranceComp, dateFrom = :dateFrom, dateTo = :dateTo, entryDate = :entryDate , description = :description, amount = :amount, u_Name = :u_Name, u_EntDt = :u_EntDt, isActive = :isActive, evalRate = :evalRate , evalTypeCode = :evalTypeCode, remark = :remark Where code = :code`
- SQL 6: `DELETE FROM policyTrans WHERE docId = :docId`
- SQL 7: `select ISNULL(Max(v_SNo),0) as v_SNo from policyTrans where docId = :policyCode`
- SQL 8: `select ISNULL(SUM(pt.policyAmt),0) - ISNULL(SUM(pt.utilizedAmt),0) as balAmt from policyTrans pt where pt.policyCode = :policyCode And pt.v_Date < = :dateTo`
- Params: `:name` ← `DmShared.xNull(mrTemp.getPolicyNo())`; `:insuranceComp` ← `DmShared.xNull(mrTemp.getInsuranceCompany())`; `:Code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:Code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:fromDate` ← `DmServer.convertDate(mrTemp.getFromDate())`; `:toDate` ← `DmServer.convertDate(mrTemp.getToDate())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getPolicyNo())`; `:insuranceComp` ← `DmShared.xNull(mrTemp.getInsuranceCompany())`; `:dateFrom` ← `DmServer.convertDate(mrTemp.getFromDate())`; `:dateTo` ← `DmServer.convertDate(mrTemp.getToDate())`; `:entryDate` ← `DmServer.convertDate(mrTemp.getEntryDate())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:amount` ← `DmServer.vNull(mrTemp.getAmt(), "0.00")`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).toString()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:evalRate` ← `DmServer.vNull(mrTemp.getEvalRate())`; `:evalTypeCode` ← `DmShared.foriegnKey(mrTemp.getEvalTypeCode())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:name` ← `DmShared.xNull(mrTemp.getPolicyNo())`; `:insuranceComp` ← `DmShared.xNull(mrTemp.getInsuranceCompany())`; `:dateFrom` ← `DmServer.convertDate(mrTemp.getFromDate())`; `:dateTo` ← `DmServer.convertDate(mrTemp.getToDate())`; `:entryDate` ← `DmServer.convertDate(mrTemp.getEntryDate())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:amount` ← `DmServer.vNull(mrTemp.getAmt(), "0.00")`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).toString()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:evalRate` ← `DmServer.vNull(mrTemp.getEvalRate())`; `:evalTypeCode` ← `DmShared.foriegnKey(mrTemp.getEvalTypeCode())`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:dateFrom` ← `DmServer.convertDate(mrTemp.getFromDate())`; `:dateTo` ← `DmServer.convertDate(mrTemp.getToDate())`; `:dateFrom` ← `DmServer.convertDate(DmShared.dNull(dateFrom))`; `:dateTo` ← `DmServer.convertDate(DmShared.dNull(dateTo))`; `:policyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:policyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:dateTo` ← `DmShared.dNull(mrTemp.getToDate())`
- Errors: "Invalid key." | "Policy No. already exists for this Insurance company" | "Record has been changed, can't update record, refresh record then update" | "Utilized amount should not be greater than policy Amt "
- Other calls: `PolicyMaster_Vld.chkSave`, `PolicyTrans_Server.savePolicyTrans`, `STApprovals.managePolicyTransRecord`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `select count(*) cnt from policyTrans where policyCode = :policyCode and docId <> :policyCode`
- SQL 2: `Delete From policyMast Where code = :code`
- SQL 3: `Delete From policyTrans Where policyCode = :code`
- Params: `:policyCode` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Transaction Exist Cannot Delete Records."

### `getTxtHInsuranceCompany`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHInsuranceCompany(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select distinct insuranceComp from policyMast`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select pM.code ,pM.name ,pM.insuranceComp ,pM.dateFrom ,pM.dateTo ,pM.amount ,pM.isActive ,pM.evalRate ,pM.evalTypeCode , CASE WHEN pM.evalTypeCode = {…} THEN ' {…} ' WHEN pM.evalTypeCode = {…} THEN ' {…} ' WHEN pM.evalTypeCode = {…} THEN ' {…} ' ELSE '' END AS evalTypeText from policyMast pM`
- Other calls: `FREE_VALUE.getCode`, `FREE_VALUE.getName`, `PERCENTAGE_OF_GOODS_TAXABLE_VALUE.getCode`, `PERCENTAGE_OF_GOODS_TAXABLE_VALUE.getName`, `PER_UNIT.getCode`, `PER_UNIT.getName`

### `getTxtHIsActive`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIsActive(DmHelper helper, boolean blnToEndOfFile)`


### `getFillHistory`
`public DmResultPageing<List<GrdPolicyHistory_Mdl>> getFillHistory(String policyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select pt.dateFrom as fromDate, pt.dateTo as toDate, pt.v_Date as renewalDate, pt.policyAmt from policyTrans pt where pt.docId = :policyCode`
- Params: `:policyCode` ← `DmShared.xNull(policyCode)`

### `removeRenewalPolicy`
`public DmResult<String> removeRenewalPolicy(String strKey, String fromDate, String toDate)`

- SQL 1: `select count(*) cnt from policyTrans where policyCode = :policyCode and docId <> :policyCode`
- SQL 2: `Delete From policyTrans Where policyCode = :code and dateFrom = :fromDate and dateTo = :todate`
- SQL 3: `UPDATE pm SET pm.dateFrom = vPt.dateFrom , pm.dateTo = vPt.dateTo ,pm.amount = vpt.policyAmt ,pm.u_EntDt_LatestLine = :u_EntDt_LatestLine FROM policyMast pm INNER JOIN ( SELECT pt.docId, pt.v_Date, pt.dateFrom, pt.dateTo, pt.policyAmt FROM policyTrans pt INNER JOIN ( SELECT ptp.docId, MAX(ptp.dateFrom) AS dateFrom FROM policyTrans ptp WHERE ptp.docId = :code GROUP BY ptp.docId ) vPtp ON vPtp.docId = pt.docId AND vPtp.dateFrom = pt.dateFrom WHERE pt.docId = :code ) vPt ON vPt.docId = pm.code WHERE pm.code = :code`
- Params: `:policyCode` ← `strKey`; `:code` ← `strKey`; `:fromDate` ← `DmServer.convertDate(fromDate)`; `:todate` ← `DmServer.convertDate(toDate)`; `:code` ← `strKey`; `:u_EntDt_LatestLine` ← `DmServer.getServerTimestamp()`
- Errors: "Transaction Exist Cannot Delete Records."

### `getTxtHValuationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValuationType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ServiceEvalType.values`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.policyTrans.server`, `paperbiz.selfToolkit.policyTrans.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 1)` | "Policy No. already exists for this Insurance company" |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (rsTemp.getLong("balAmt") < 0)` | "Utilized amount should not be greater than policy Amt " |
| deleteRecord | `if (rsTemp.getLong("cnt") > 0)` | "Transaction Exist Cannot Delete Records." |
| removeRenewalPolicy | `if (rsTemp.getLong("cnt") > 0)` | "Transaction Exist Cannot Delete Records." |

## Notes (hand-written)
_No hand-written notes yet._
