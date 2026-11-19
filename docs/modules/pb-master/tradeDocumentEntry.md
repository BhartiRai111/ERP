# pb-master / tradeDocumentEntry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/tradeDocumentEntry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: tradeDocumentEntry
- Update tables: tradeDocumentEntry
- Delete cascade (in order): tradeDocumentEntry
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `select count(*) cnt from tradeDocumentEntry where docId = :docId`
- Validations: Entry Date (Text); Document Type (Text); Party (Text); Bank Name (Text); Document No. (Text); Issue Date (Text); Value (Amount); Valid Upto (Text); Document Nature (Text); Active (Text)
- Lookups/suggestions: getTxtHVoucherType, getTxtHDocumentType, getTxtHParty, getTxtHDocNature, getTxtHIsActive
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `TradeDocumentEntry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Entry Date | DmTextBox | `txtEntryDate` | `11, true` |
| 2 | Entry No. | DmTextBox | `txtEntryNo` | `20, false` |
| 3 | Document Type | DmTextHelp | `txtHDocumentType` | `TxtHDocumentType_PrpAcs, 6, true, true` |
| 4 | Party | DmTextHelp | `txtHParty` | `TxtHPartyName_PrpAcs, 7, true` |
| 5 | Bank | DmTextBox | `txtBankName` | `100, true` |
| 6 | Document No. | DmTextBox | `txtDocumentNo` | `20, true` |
| 7 | Issue Date | DmTextBox | `txtIssueDate` | `11, true` |
| 8 | Value | DmTextBoxNumeric | `txtValue` | `2, false, 12, true` |
| 9 | Valid Upto | DmTextBox | `txtValidUpto` | `11, true` |
| 10 | Document Nature | DmTextHelp | `txtHDocNature` | `KeyField_PrpAcs, 3, true, true` |
| 11 | Is Active | DmTextHelp | `txtHIsActive` | `KeyField_PrpAcs, 5, true, true` |
| 12 | Remark | DmRemarkTextBox | `txtRemarks` | `250, false` |

Other widgets (not in tab list): `txtHVoucherType` (DmTextHelp), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHDocNature`: Nature [Text, w0]
- `txtHIsActive`: Type [Text, w0, name]
- `txtHVoucherType`: Name [Text, w0, Description]
- `txtHDocumentType`: Name [Text, w0, name]; ManualCode [Text, w100, manualCode]
- `txtHParty`: Name [Text, w405, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]
- `dmFind`: Entry No [Text, w120, name]; Entry Date [Text, w100, replace(convert(varchar, v_Date, 106), ' ','-')]; Document Type [Text, w250, documentName]; Party [Text, w300, partyName]; Value [Text, w100, value]; Doc. Nature [Text, w100, documentNatureName]; Is Active [Text, w100, isActive]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtHDocNature` → `getTxtHDocNature`
- `txtHDocumentType` → `getTxtHDocumentType`
- `txtHParty` → `getTxtHParty`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `VoucherTypeTag` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeText` ← DmShared.xNull(txtHVoucherType.getText())
- `EntryDate` ← DmShared.dNull(txtEntryDate.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtEntryNo.getText())
- `TradeDocTypeCode` ← DmShared.xNull(txtHDocumentType.getTag())
- `TradeDocTypeName` ← DmShared.xNull(txtHDocumentType.getText())
- `PartyCode` ← DmShared.xNull(txtHParty.getTag())
- `PartyName` ← DmShared.xNull(txtHParty.getText())
- `BankName` ← DmShared.xNull(txtBankName.getText())
- `DocNo` ← DmShared.xNull(txtDocumentNo.getText())
- `IssueDate` ← DmShared.dNull(txtIssueDate.getText())
- `Value` ← DmShared.xNull(txtValue.getText())
- `ValidUpto` ← DmShared.dNull(txtValidUpto.getText())
- `DocNatureCode` ← DmShared.xNull(txtHDocNature.getTag())
- `DocNatureName` ← DmShared.xNull(txtHDocNature.getText())
- `IsActiveCode` ← DmShared.xNull(txtHIsActive.getTag())
- `IsActiveText` ← DmShared.xNull(txtHIsActive.getText())
- `Remark` ← DmShared.xNull(txtRemarks.getText())
- `U_EntDt_LatestLine` ← DmShared.iNull(txtEntryNo.getTag()).toString()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtEntryDate.setText` ← DmShared.dNull(data.getEntryDate())
- `txtEntryNo.setText` ← data.getRecid().getRecIdValue()
- `txtEntryNo.setTag` ← DmShared.iNull(data.getU_EntDt_LatestLine()).toString()
- `txtHDocumentType.setTag` ← DmShared.xNull(data.getTradeDocTypeCode())
- `txtHDocumentType.setText` ← DmShared.xNull(data.getTradeDocTypeName())
- `txtHVoucherType.setText` ← data.getVoucherTypeText()
- `txtHVoucherType.setTag` ← data.getVoucherTypeTag()
- `txtHParty.setTag` ← DmShared.xNull(data.getPartyCode())
- `txtHParty.setText` ← DmShared.xNull(data.getPartyName())
- `txtBankName.setText` ← data.getBankName()
- `txtDocumentNo.setText` ← data.getDocNo()
- `txtIssueDate.setText` ← data.getIssueDate()
- `txtValue.setText` ← DmShared.xNull(data.getValue())
- `txtValidUpto.setText` ← data.getValidUpto()
- `txtHDocNature.setTag` ← data.getDocNatureCode()
- `txtHDocNature.setText` ← data.getDocNatureName()
- `txtHIsActive.setTag` ← data.getIsActiveCode()
- `txtHIsActive.setText` ← data.getIsActiveText()
- `txtRemarks.setText` ← DmShared.xNull(data.getRemark())
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtEntryNo.setEnabled(false)`
- `txtHVoucherType.setEnabled(false)`
- `txtEntryNo.setEnabled(false)`
- `txtHIsActive.setEnabled(flag)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtEntryNo.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtHVoucherType.setEnabled(false);
txtEntryNo.setEnabled(false);
txtHIsActive.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
TradeDocumentEntry.this.modifyReasonMdl = modifyReason;
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
txtEntryDate.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(TradeDocumentEntry.this);
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
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
if (blnStatus && tRow != null) {
voucherCategory = DmShared.xNull(tRow.getSharedPreparedBy());
```

`txtHDocNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDocNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDocNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDocNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDocNature).show();
```

`txtHDocNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDocNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDocNature).show();
```

`txtHDocumentType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHDocumentType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDocumentType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDocumentType).show();
```

`txtHDocumentType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDocumentType).show();
```

`txtHParty.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHParty(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPart
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHParty).show();
public void onSuccess(DmResultPageing<List<TxtHParty_Mdl>> result) {
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHParty.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHParty).show();
```

`txtHParty.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(tRow.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
if (!DmShared.xNull(txtHParty.getTag2nd()).equalsIgnoreCase(txtHParty.getTag())) {
txtHParty.setTag2nd(txtHParty.getTag());
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/TradeDocumentEntry.java | 676 |
| client/TradeDocumentEntry_Dsg.java | 413 |
| server/TradeDocumentEntry_SrvImpl.java | 902 |
| shared/DefaultValue_Mdl.java | 86 |
| shared/Find_Mdl.java | 77 |
| shared/TradeDocumentEntry_Mdl.java | 201 |
| shared/TradeDocumentEntry_Srv.java | 37 |
| shared/TradeDocumentEntry_SrvAsync.java | 37 |
| shared/TradeDocumentEntry_Vld.java | 21 |
| shared/TxtHParty_Mdl.java | 104 |

## UI labels / columns (client)
`Entry Date`, `Entry No.`, `Document Type`, `Party`, `Bank`, `Document No.`, `Issue Date`, `Value`, `Valid Upto`, `Document Nature`, `Is Active`, `Remark`

## Models (shared DTOs)
### TradeDocumentEntry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherTypeTag |
| String | voucherTypeText |
| RecId | recid |
| String | vNo |
| String | entryDate |
| String | tradeDocTypeCode |
| String | tradeDocTypeName |
| String | partyCode |
| String | partyName |
| String | bankName |
| String | docNo |
| String | issueDate |
| String | value |
| String | validUpto |
| String | docNatureCode |
| String | docNatureName |
| String | isActiveCode |
| String | isActiveText |
| String | remark |
| String | u_EntDt_LatestLine |
| ModifyReason_Mdl | modifyReasonMdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| boolean | isContinuousNewMode |
| String | newVoucherTypeTag |
| String | newVoucherTypeText |
| String | newVoucherCategory |
| String | voucherTypeTag |
| String | voucherTypeText |
| String | currentDate |
| String | voucherCategory |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | entryDate |
| String | documentType |
| String | voucherType |
| String | party |
| String | value |
| String | documentNature |
| String | isActiveText |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | stateCode |
| String | distributorCode |
| String | distributorName |
| String | distributorCity |
| String | guiConfigCode |
| String | zoneCode |
| String | zoneName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<TradeDocumentEntry_Mdl> |
| saveRecord | TradeDocumentEntry_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHDocNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIsActive | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDocumentType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `TradeDocumentEntry_Vld`
```java
public class TradeDocumentEntry_Vld {
    public static void chkSave(TradeDocumentEntry_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getEntryDate(), "Entry Date", 100);
        DmShared.isValidText(mrTemp.getTradeDocTypeCode(), "Document Type", 200);
        DmShared.isValidText(mrTemp.getPartyCode(), "Party", 300);
        DmShared.isValidText(mrTemp.getBankName(), "Bank Name", 400);
        DmShared.isValidText(mrTemp.getDocNo(), "Document No.", 500);
        DmShared.isValidText(mrTemp.getIssueDate(), "Issue Date", 600);
        DmShared.isValidAmount(mrTemp.getValue(), "Value", 700);
        DmShared.isValidText(mrTemp.getValidUpto(), "Valid Upto", 800);
        DmShared.isValidText(DmShared.iNull(mrTemp.getDocNatureCode()).intValue() <= 0 ? "" : DmShared.xNull(mrTemp.getDocNatureCode()), "Document Nature", 900);
        DmShared.isValidText(mrTemp.getIsActiveCode(), "Active", 1000);
    }
}
```

## Server `TradeDocumentEntry_SrvImpl.java` (902 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT docId From tradeDocumentEntry tde LEFT JOIN voucher_Type vt ON vt.v_Type = tde.v_Type where vt.category IN ( {…} ) and tde.site_Code = :site_Code and tde.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `select top 1 description,V_Type from voucher_Type where category IN (' {…} ')`

### `getMoveRec`
`public DmResult<TradeDocumentEntry_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT tde.docId, tde.v_Type, vt.description, tde.recId, tde.v_Date, tde.tradeDocTypeCode, tdt.name as tradeDocTypeName, tde.partyCode, sg.name as partyName, tde.bankName, tde.documentNo, tde.issueDate, tde.v_No, tde.recIdPrefix, tde.value, tde.validUpto, tde.documentNature, tde.isActive, tde.remark, tde.u_EntDt_LatestLine, tde.u_EntDt, tde.u_Name, tde.preparedDt, tde.preparedBy, tde.preparedByIP from tradeDocumentEntry tde LEFT JOIN tradeDocumentType tdt ON tdt.code = tde.tradeDocTypeCode LEFT JOIN subgroup sg ON sg.subCode = tde.partyCode LEFT JOIN voucher_type vt ON vt.v_Type = tde.v_Type WHERE tde.docId = :docId`
- Params: `:docId` ← `strKey`
- Other calls: `DocumentNature.getViaCode`

### `saveRecord`
`public DmResult<String> saveRecord(TradeDocumentEntry_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `SELECT u_EntDt_LatestLine FROM tradeDocumentEntry WHERE docId = :docId`
- SQL 2: `INSERT INTO tradeDocumentEntry (docId, v_Type, v_No, recIdPrefix, recId, v_Prefix, v_Date, tradeDocTypeCode, partyCode, site_Code, bankName, documentNo, issueDate, value, validUpto, documentNature, isActive, remark, preparedDt, preparedBy, preparedByIP) VALUES (:docId, :v_Type, :v_No, :recIdPrefix, :recId, :v_Prefix, :v_Date, :tradeDocTypeCode, :partyCode, :site_Code, :bankName, :documentNo, :issueDate, :value, :validUpto, :documentNature, :isActive, :remark, :preparedDt, :preparedBy, :preparedByIP)`
- SQL 3: `UPDATE tradeDocumentEntry SET tradeDocTypeCode = :tradeDocTypeCode , partyCode = :partyCode , bankName = :bankName , documentNo = :documentNo , value = :value , documentNature = :documentNature , isActive = :isActive , validUpto = :validUpto , issueDate = :issueDate , u_EntDt = :uEntDt , u_EntDt_LatestLine= :uEntDt , u_name = :u_name, remark = :remark, v_No = :v_No WHERE docId = :docId ;`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`; `:v_Type` ← `DmShared.xNull(mrTemp.getVoucherTypeTag())`; `:v_No` ← `DmShared.iNull(vNo).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecid().getPrefix())`; `:recId` ← `mrTemp.getRecid().getRecIdValue()`; `:v_Prefix` ← `voucherPrefix`; `:v_Date` ← `DmServer.convertDate(mrTemp.getEntryDate())`; `:tradeDocTypeCode` ← `DmShared.foriegnKey(mrTemp.getTradeDocTypeCode())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:bankName` ← `DmShared.xNull(mrTemp.getBankName())`; `:documentNo` ← `DmShared.xNull(mrTemp.getDocNo())`; `:value` ← `DmServer.vNull(mrTemp.getValue(), "0.00")`; `:documentNature` ← `DmShared.iNull(mrTemp.getDocNatureCode()).toString()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).toString()`; `:issueDate` ← `DmServer.convertDate(mrTemp.getIssueDate())`; `:validUpto` ← `DmServer.convertDate(mrTemp.getValidUpto())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:preparedDt` ← `timestamp`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`; `:tradeDocTypeCode` ← `DmShared.foriegnKey(mrTemp.getTradeDocTypeCode())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:bankName` ← `DmShared.xNull(mrTemp.getBankName())`; `:documentNo` ← `DmShared.xNull(mrTemp.getDocNo())`; `:value` ← `DmServer.vNull(mrTemp.getValue(), "0.00")`; `:documentNature` ← `DmShared.iNull(mrTemp.getDocNatureCode()).toString()`; `:isActive` ← `DmShared.iNull(mrTemp.getIsActiveCode()).toString()`; `:issueDate` ← `DmServer.convertDate(mrTemp.getIssueDate())`; `:validUpto` ← `DmServer.convertDate(mrTemp.getValidUpto())`; `:v_No` ← `DmShared.iNull(mrTemp.getvNo()).toString()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:docId` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Invalid key." | "Record has been changed, can't update record, refresh record then update"
- Other calls: `TradeDocumentEntry_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `select count(*) cnt from tradeDocumentEntry where docId = :docId`
- SQL 2: `Delete From tradeDocumentEntry Where docId = :docId`
- Params: `:docId` ← `strKey`; `:docId` ← `strKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT tde.docId, tde.recId as name, tde.v_No as v_No, tde.v_Date , sg.name as partyName, tdt.name as documentName , vt.short_Name as short_Name, tde.isActive, tde.documentNature, tde.value , (CASE WHEN tde.documentNature = {…} THEN ' {…} ' WHEN tde.documentNature = {…} THEN ' {…} ' ELSE '' END) AS documentNatureName FROM tradeDocumentEntry tde LEFT JOIN tradeDocumentType tdt ON tdt.code = tde.tradeDocTypeCode LEFT JOIN subgroup sg ON sg.subCode = tde.partyCode LEFT JOIN voucher_type vt ON vt.v_Type = tde.v_Type where vt.category IN ( {…} ) and tde.site_Code = :site_Code and tde.v_prefix = :v_prefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_prefix` ← `DmServer.getSsnVoucherPrefix(this)`
- Other calls: `EXPORT.getCode`, `EXPORT.getValue`, `IMPORT.getCode`, `IMPORT.getValue`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT VT.V_type, VT.Description,VT.Category FROM Voucher_Type VT WHERE Category IN ( {…} )`

### `getTxtHDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tdt.code, tdt.name, tdt.manualCode FROM tradeDocumentType tdt WHERE tdt.isActive = 1`

### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , sg.guiConfigCode , sg.zone , zm.name as zoneName , c.cityName , c.cityCode , c.stateCode , ISNULL(sg.distributor, sg.subCode) as distributorCode , sgd.name as distributorName , c1.cityName as distributorCity FROM subGroup sg LEFT JOIN subGroup sgd ON sgd.subCode = sg.distributor LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN city c1 ON c1.cityCode = sgd.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature LEFT JOIN zoneMast zm ON zm.code = sg.zone Where sg.isActive = 1 AND sgsl.site_Code = :site_Code and sg.filterNature = :filterNature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterNature` ← `DmShared.xNull(DmServer.getCodeViaManualCode("PT", "manualCode", "subGroupNature", "code",`

### `getTxtHDocNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DocumentNature.values`

### `getTxtHIsActive`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIsActive(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |

## Notes (hand-written)
_No hand-written notes yet._
