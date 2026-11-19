# dm-accounts / tdsCategoryMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/tdsCategoryMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: tdscat
- Update tables: tdscat_Det, tdscat
- Delete cascade (in order): TDSCat_Det, tdscat
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT COUNT(cnt) AS cnt FROM (SELECT td.applicableFrom AS cnt FROM tdscat_Det td WHERE td.code = :code GROUP BY td.applicableFrom ) AS tab`
- Validations: TDS Description (Text); rule: Please Define Posting A/c For Description: ; rule: Duplicate Record Defined For Description: ; rule: Please Fill Entry
- Lookups/suggestions: getSuggestion, getTxtHDescription, getTxtHPostingAC, getTxtHCopyFromTDS, getTxtHType, getTxtHSection
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ApplicableFrom`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Copy TDS/TCS | DmTextHelp | `txtHCopyFromTds` | `TxtHCopyFromTDS_PrpAcs, 5, false` |
| 2 | Copy Applicable Date | DmTextBox | `txtDtCopyFrom` | `11, false` |
| 3 | New Applicable Date | DmTextBox | `txtDtNew` | `100, true` |
| 4 | Copy TDS/TCS | DmTextHelp | `txtHCopyFromTds` | `TxtHCopyFromTDS_PrpAcs, 5, false` |
| 5 | Copy Applicable Date | DmTextBox | `txtDtCopyFrom` | `11, false` |

Other widgets (not in tab list): `dmGrdApplicableDate` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHCopyFromTds`: Name [Text, w0, name]; Type [Text, w100, type]; Applicable From [Text, w100, applicableFrom]
- `dmGrdApplicableDate`: Applicable From [Text, w1]; Applicable To [Text, w125]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCopyFromTds` → `getTxtHCopyFromTDS`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCopyFromTds.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCopyFromTDS(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCopyFromTds).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCopyFromTds.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopyFromTds).show();
```

`txtHCopyFromTds.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCopyFromTds.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopyFromTds).show();
```

`txtHCopyFromTds.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtDtCopyFrom.setText(DmShared.dNull(t1Row.getSharedPreparedBy()));
} else {
txtDtCopyFrom.setText("");
```
- Confirm: "Are you sure? You want to delete "
- Alert: "Please Select Applicable From "
- Alert: "Copy TDS is mandatory."
- Alert: "Please Select a Date"
- Alert: "Copy TDS is mandatory."
- Alert: "New Applicable Date is mandatory."

### Screen `TdsCategoryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Description | DmTextHelp | `txtHDescription` | `TxtHDescription_PrpAcs, 7, true` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `10, false` |
| 3 | Posting A/c | DmTextHelp | `txtHPostingAC` | `com.google.gwt.core.shared.TxtHPostingAC_PrpAcs, 7, true` |
| 4 | Percentage % | DmTextBoxNumeric | `txtPercent` | `DmTextBoxNumeric.DecimalNature.NONE, false, 6, false` |
| 5 | Calculate On | DmTextBox | `txtCalculateOn` | `100, false` |

**Grid / popup columns** (header, width, db field):
- `txtHDescription`: Description [Text, w0, Name]; Manual Code [Text, w100, manualCode]
- `txtHPostingAC`: Name [Text, w0, Name]; Code [Text, w50, ManualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDescription` → `getTxtHDescription`
- `txtHPostingAC` → `getTxtHPostingAC`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDescription.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDescription(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDescription).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDescription.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDescription).show();
```

`txtHDescription.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDescription.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDescription).show();
```

`txtHDescription.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtManualCode.setText(DmShared.xNull(t1Row.getSharedModifiedBy()));
} else {
txtManualCode.setText("");
```

`txtHPostingAC.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPostingAC(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPostingAC).show();
public void onSuccess(DmResultPageing<List<TxtHPostingAC_Mdl>> result) {
if (result.isStatus()) {
txtHPostingAC.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPostingAC).show();
```

`txtHPostingAC.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPostingAC.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPostingAC).show();
```
- Alert: "Description Already Exists!"

### Screen `TdsCategoryMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Category Name | DmSuggestBox | `txtCategoryName` | `0, 10, true, "Name", DmDataType.STRING` |
| 2 | Section | DmTextHelp | `txtHSection` | `TxtHSection_PrpAcs, 5, false` |
| 3 | Type | DmTextHelp | `txtHType` | `TxtHType_PrpAcs, 5, true` |
| 4 | Applicable From | DmTextBox | `txtApplicableDate` | `11, false` |

Other widgets (not in tab list): `section` (DmTextBox), `dmFind` (DmFind), `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Category Name [Text, w50, Name]; Section [Text, w130, tdsSectionName]; Type [Text, w100, type]
- `dmGrd`: Description [Text, w200]; Manual Code [Text, w200]; Posting A/c [Text, w250]; Percentage % [Text, w200]; Calculate On [Text, w250]
- `txtHType`: Name [Text, w220, name]
- `txtHSection`: Section [Text, w140, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtCategoryName` → `getSuggestion`
- `txtHType` → `getTxtHType`
- `txtHSection` → `getTxtHSection`
- `tdsCategoryGridEdit` → `fillGrid`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `ApplicableFrom` ← txtApplicableDate.getText()
- `Type` ← txtHType.getText()
- `TypeCode` ← txtHType.getTag()
- `Section` ← txtHSection.getText()
- `SectionCode` ← txtHSection.getTag()
- `SharedKeyFieldName` ← DmShared.xNull(txtCategoryName.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtCategoryName.setText` ← data.getSharedKeyFieldName()
- `txtApplicableDate.setText` ← data.getApplicableFrom()
- `txtHType.setText` ← data.getType()
- `txtHType.setTag` ← data.getTypeCode()
- `txtHType.setTag2nd` ← data.getTypeCode()
- `txtHSection.setText` ← data.getSection()
- `txtHSection.setTag` ← data.getSectionCode()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtApplicableDate.setEnabled(false)`

**On New click:**
- `clear()`
- `txtCategoryName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtApplicableDate.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
manageDisplay(true);
manageFlwApplicable(true);
txtCategoryName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
manageFlwApplicable(false);
dmGrd.getGrid().getStore().add(new GrdTdsCategory_Mdl());
txtCategoryName.setFocus(true);
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

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(TdsCategoryMaster.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtCategoryName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtCategoryName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtCategoryName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCategoryName).show();
```

`txtCategoryName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtCategoryName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCategoryName).show();
```

`txtHType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHType.getTag2nd()))) {
txtHType.setTag2nd(DmShared.xNull(txtHType.getTag()));
dmGrd.getGrid().getStore().clear();
dmGrd.getGrid().getStore().add(new GrdTdsCategory_Mdl());
if (blnStatus && t1Row != null) {
```

`txtHSection.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSection(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSection).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSection.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSection).show();
```

`txtHSection.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSection.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSection).show();
```

`txtHSection.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExten
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
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

`applicableFrom.addCallBack` → `onOk()`
```java
try {
txtApplicableDate.setTag(DmShared.dNull(oldDtApplicable));
txtApplicableDate.setText(DmShared.dNull(newDtApplicable));
if (!DmShared.dNull(oldDtApplicable).equalsIgnoreCase("")) {
fillGrid(topControl.getDocId(), txtApplicableDate.getTag());
} catch (Exception ex) {
new DmAlertMessageBox(ex.getMessage(), (DmTextBox) null).show();
```

`applicableFrom.addCallBack` → `onDelete()`
```java
topControlMoveRec(DmShared.xNull(topControl.getDocId()));
```

`applicableFrom.addCallBack` → `onCopyTDS()`
```java
if (!DmShared.dNull(newDtApplicable).equalsIgnoreCase("")) {
txtApplicableDate.setText(DmShared.dNull(newDtApplicable));
if (!DmShared.xNull(copyCategory).trim().equalsIgnoreCase("") && !DmShared.dNull(copyDtApplicable).equalsIgnoreCase(""))
fillGrid(copyCategory, copyDtApplicable);
```

`tdsCategoryGridEdit.addCallBack` → `onOk()`
```java
dmGrd.commitChanges();
```

`tdsCategoryGridEdit.addCallBack` → `onDelete()`
```java
dmGrd.commitChanges();
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Select Record To View/Add ApplicableFrom"

## Files
| file | lines |
|---|---|
| client/ApplicableFrom.java | 404 |
| client/ApplicableFrom_Dsg.java | 326 |
| client/TdsCategoryGridEdit.java | 212 |
| client/TdsCategoryGridEdit_Dsg.java | 149 |
| client/TdsCategoryMaster.java | 647 |
| client/TdsCategoryMaster_Dsg.java | 303 |
| server/TdsCategoryMaster_SrvImpl.java | 1190 |
| shared/Find_Mdl.java | 33 |
| shared/GrdTdsCategory_Mdl.java | 77 |
| shared/TdsCategoryMaster_Mdl.java | 90 |
| shared/TdsCategoryMaster_Srv.java | 55 |
| shared/TdsCategoryMaster_SrvAsync.java | 55 |
| shared/TdsCategoryMaster_Vld.java | 44 |
| shared/TxtHPostingAC_Mdl.java | 35 |

## UI labels / columns (client)
`Category`, `Copy TDS/TCS`, `Copy Applicable Date`, `New Applicable Date`, `COPY ITS`, `Category Name`, `Section`, `Type`, `Applicable From`, `Description`, `Manual Code`, `Posting A/c`, `Percentage %`, `Calculate On`

## Models (shared DTOs)
### TdsCategoryMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | applicableFrom |
| String | type |
| String | typeCode |
| String | section |
| String | sectionCode |
| List<GrdTdsCategory_Mdl> | GrdData |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | typeCode |
| String | type |

### TxtHPostingAC_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### GrdTdsCategory_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | descriptionCode |
| String | postingAccount |
| String | PostingAccountCode |
| String | Percent |
| String | CalculateOn |
| String | manualCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<TdsCategoryMaster_Mdl> |
| saveRecord | TdsCategoryMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDescription | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPostingAC | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPostingAC_Mdl>> |
| getTxtHCopyFromTDS | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillGridApplicableDate | String keyCode | DmResult<List<KeyFieldExtended>> |
| getMaxApplicableDate | String keyCode | DmResult<KeyFieldExtended> |
| deleteRecordApplicable | String keyCode, String dNull | DmResult<String> |
| fillGrid | String xNull, String dNull | DmResult<List<GrdTdsCategory_Mdl>> |
| getTxtHType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSection | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `TdsCategoryMaster_Vld`
```java
public class TdsCategoryMaster_Vld {
    public static void chkSave(TdsCategoryMaster_Mdl mrTemp) {
        List<GrdTdsCategory_Mdl> listData;
        boolean flagGridData = false;
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "TDS Description", 100);
        listData = mrTemp.getGrdData();
        if (listData != null) {
            for (int i = 0; i < listData.size(); i++) {
                if (!DmShared.xNull(listData.get(i).getDescriptionCode()).trim().equalsIgnoreCase("")) {
                    if (DmShared.xNull(listData.get(i).getPostingAccountCode()).trim().equalsIgnoreCase("")) {
                        throw new DmException(300, "Please Define Posting A/c For Description: " + listData.get(i).getDescription() + "");
                    }
                    for (int j = i + 1; j < listData.size(); j++) {
                        if (DmShared.xNull(listData.get(j).getDescriptionCode()).equalsIgnoreCase(listData.get(i).getDescriptionCode())) {
                            throw new DmException(300, "Duplicate Record Defined For Description: " + listData.get(i).getDescription() + "");
                        }
                    }
                    if (!flagGridData) {
                        flagGridData = true;
                    }
                }
            }
        }
        if (!flagGridData) {
            throw new DmException(200, "Please Fill Entry");
        }
    }
}
```

## Server `TdsCategoryMaster_SrvImpl.java` (1190 lines)
Helpers used: `DmServer.addDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT code FROM TDSCat Order By name`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code, Name From tdscat`

### `getMoveRec`
`public DmResult<TdsCategoryMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `Select tdsc.Code, max(tdsc.Name) as Name ,max( tdsc.preparedBy) as preparedBy ,max( tdsc.u_Name) as u_Name, max(tdsc.preparedDt) as preparedDt ,max(tdsc.preparedByIP) as preparedByIP, max(tdsc.u_EntDt) as u_EntDt , max(tcd.applicableFrom) applicableFrom, max(tcd.applicableTo) applicableTo,max(tdsc.type) type,max(tdsc.tdsSectionCode) tdsSectionCode, max(tsm.name) tdsSectionName From tdscat tdsc left join tdscat_Det tcd on tcd.code = tdsc.code left join tdsSectionMast AS tsm ON tsm.code = tdsc.tdsSectionCode left join ( select max(td.applicableFrom) applicableFrom , td.code from tdscat_Det td where td.code = :code and td.applicableFrom <= cast(:uEntDt as date) group by td.code ) id on id.code = tcd.code Where tdsc.code = :code group by tdsc.code order by name`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Other calls: `TDS_TCS_Type.getViaCode`

### `saveRecord`
`public DmResult<String> saveRecord(TdsCategoryMaster_Mdl mrTemp)`

- SQL 1: `Select Count(Name) As Cnt From TDSCAT TD Where TD.Name= :name`
- SQL 2: `select MAX(id.applicableFrom) applicableFromMax, MIN(applicableFrom) applicableFromMin from tdscat_Det id where id.code = :code`
- SQL 3: `update tdscat_Det set applicableTo = :applicableToDate where applicableFrom = :applicableFromDate and code = :code`
- SQL 4: `select applicableFrom, applicableTo FROM tdscat_Det where applicableFrom > :applicableFromDate and code = :code group by applicableFrom, applicableTo order by applicableFrom asc offset 0 rows fetch next 1 rows only`
- SQL 5: `update tdscat_Det set applicableTo = :applicableToDate where applicableFrom = :applicableFromDate and code = :code`
- SQL 6: `Insert Into tdscat(code,name, preparedBy,preparedByIP,preparedDt, U_EntDt ,type, tdsSectionCode) Values (:code,:name,:u_Name, :preparedByIP, :preparedDt,:preparedDt,:type, :tdsSectionCode )`
- SQL 7: `Update tdscat Set Name = :name, transferDt = null, U_Name = :uName, U_EntDt = :u_EntDt ,type = :type ,tdsSectionCode = :tdsSectionCode Where code = :code`
- SQL 8: `Delete From tdscat_DET where Code= :code and applicableFrom = :applicableFrom`
- Params: `:name` ← `mrTemp.getSharedKeyFieldName()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:applicableFromDate` ← `DmServer.convertDate(mrTemp.getApplicableFrom())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:applicableToDate` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(mrTemp.getApplicab`; `:applicableFromDate` ← `rsTemp.getDate("applicableFromDate")`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:applicableFromDate` ← `DmServer.convertDate(mrTemp.getApplicableFrom())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:applicableFromDate` ← `DmServer.convertDate(maxApplicableDate)`; `:applicableToDate` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(mrTemp.getApplicab`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:type` ← `DmShared.foriegnKey(mrTemp.getTypeCode())`; `:tdsSectionCode` ← `DmShared.foriegnKey(mrTemp.getSectionCode())`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:u_EntDt` ← `timestamp`; `:type` ← `DmShared.foriegnKey(mrTemp.getTypeCode())`; `:tdsSectionCode` ← `DmShared.foriegnKey(mrTemp.getSectionCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:applicableFrom` ← `DmShared.xNull(mrTemp.getApplicableFrom())`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:srNo` ← `Integer.toString(SrNo++)`; `:tDSDesc` ← `DmShared.foriegnKey(gtc.getDescriptionCode())`; `:acCode` ← `DmShared.foriegnKey(gtc.getPostingAccountCode())`; `:percentage` ← `DmServer.vNull(gtc.getPercent())`; `:formulaString` ← `DmShared.xNull(gtc.getCalculateOn())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:applicableFrom` ← `DmServer.convertDate(mrTemp.getApplicableFrom())`; `:applicableTo` ← `DmServer.convertDate(applicableTo)`
- Errors: "TDS Description Already Exists." | "Invalid key."
- Other calls: `TdsCategoryMaster_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT COUNT(cnt) AS cnt FROM (SELECT td.applicableFrom AS cnt FROM tdscat_Det td WHERE td.code = :code GROUP BY td.applicableFrom ) AS tab`
- SQL 2: `Delete From TDSCat_Det Where Code= :code`
- SQL 3: `Delete From tdscat Where code= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Record have multiple TDS List. Could not delete this record."

### `getFind`
`public DmResultPageing<List<KeyFieldExtended>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tdsc.code, tdsc.name, tdsc.type ,tsm.name As tdsSectionName From tdscat tdsc LEFT JOIN tdsSectionMast AS tsm ON tsm.code = tdsc.tdsSectionCode`
- Other calls: `TDS_TCS_Type.getViaCode`

### `getTxtHDescription`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDescription(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Code,Name,manualCode From TDSCat_Description`

### `getTxtHPostingAC`
`public DmResultPageing<List<TxtHPostingAC_Mdl>> getTxtHPostingAC(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode ,SG.Name,SG.ManualCode FROM SubGroup SG left join subGroupNature as sgn on sg.nature = sgn.code where SG.isActive = 1 and sgn.manualCode not in ('BK','CS')`

### `getTxtHCopyFromTDS`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCopyFromTDS(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tc.code, MAX(tc.name) AS name ,tcd.applicableFrom, max(tc.type) as type FROM tdsCat tc INNER JOIN tdsCat_Det tcd ON tcd.code = tc.code GROUP BY tc.code, tcd.applicableFrom`
- Other calls: `TDS_TCS_Type.getViaCode`

### `fillGridApplicableDate`
`public DmResult<List<KeyFieldExtended>> fillGridApplicableDate(String code)`

- SQL 1: `SELECT MAX(code) AS code, applicableFrom , MAX(applicableTo) AS applicableTo FROM tdscat_Det WHERE (code = :code) GROUP BY applicableFrom ORDER BY applicableFrom`
- Params: `:code` ← `code`

### `getMaxApplicableDate`
`public DmResult<KeyFieldExtended> getMaxApplicableDate(String code)`

- SQL 1: `select top 1 tds.code, max(tdsd.applicableFrom) as applicableFrom, MAX( tds.name) AS name FROM tdscat tds LEFT JOIN tdscat_Det tdsd ON tdsd.code = tds.code WHERE ( tds.code = :code) GROUP BY tds.code, tdsd.applicableFrom ORDER BY tdsd.applicableFrom desc`
- Params: `:code` ← `code`

### `deleteRecordApplicable`
`public DmResult<String> deleteRecordApplicable(String strKey, String applicableDate)`

- SQL 1: `SELECT applicableFrom , applicableTo FROM tdscat_Det WHERE code= :code and applicableFrom= :applicableFrom`
- SQL 2: `SELECT code, applicableFrom, applicableTo FROM tdscat_Det WHERE code= :code AND applicableFrom < :applicableFrom ORDER BY applicableFrom DESC`
- SQL 3: `update tdscat_Det set applicableTo=:applicableTo where code = :code and ApplicableFrom = :ApplicableFrom`
- SQL 4: `delete from tdscat_Det where code=:code and applicableFrom= :applicableFrom`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:applicableFrom` ← `DmServer.convertDate(applicableDate)`; `:code` ← `DmShared.iNull(strKey).toString()`; `:applicableFrom` ← `DmServer.convertDate(applicableDate)`; `:applicableTo` ← `applicableToDate`; `:code` ← `DmShared.iNull(strKey).toString()`; `:ApplicableFrom` ← `rsTemp.getString("ApplicableFrom")`; `:code` ← `DmShared.iNull(strKey).toString()`; `:applicableFrom` ← `DmServer.convertDate(applicableDate)`
- Errors: "Applicable From Date is Invalid"

### `fillGrid`
`public DmResult<List<GrdTdsCategory_Mdl>> fillGrid(String code, String applicableFromDate)`


### `fillTDSGrid`
`private List<GrdTdsCategory_Mdl> fillTDSGrid(String keyCode, String applicableDate, Connection cnTemp) throws Exception`

- SQL 1: `SELECT TD.FormulaString,TD.CODE AS CODE,T.Code ,TD.SrNo,SG.Name AS AcName,TD.Percentage,TD.TDSDesc ,TCD.Name AS Desc_Name, TCD.manualCode,TD.AcCode FROM TDSCat_Det AS TD LEFT JOIN SubGroup AS SG ON SG.SubCode=TD.AcCode LEFT JOIN TDSCat AS T ON T.Code=TD.Code LEFT JOIN TDSCat_Description AS TCD ON TCD.Code =TD.TDSDesc where TD.code=:code and TD.applicableFrom =:applicableFrom`
- Params: `:code` ← `DmShared.iNull(keyCode).toString()`; `:applicableFrom` ← `DmServer.convertDate(applicableDate)`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TDS_TCS_Type.values`

### `getTxtHSection`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSection(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tsm.code, tsm.name FROM tdsSectionMast tsm WHERE tsm.isActive = 1`

## Depends on (imports from other packages)
`dataman.accounts.tdsCategoryMaster.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "TDS Description Already Exists." |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| deleteRecord | `if (rsTemp.getLong("cnt") > 1)` | "Record have multiple TDS List. Could not delete this record." |
| deleteRecordApplicable | `if (rsTemp.next())` | "Applicable From Date is Invalid" |

## Notes (hand-written)
_No hand-written notes yet._
