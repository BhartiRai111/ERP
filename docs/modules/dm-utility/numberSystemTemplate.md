# dm-utility / numberSystemTemplate

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/numberSystemTemplate/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: nsFormatTemplate
- Update tables: nsFormatTemplate
- Delete cascade (in order): nsFormatTemplate
- Duplicate checks on save:
  - `SELECT COUNT(*) AS cnt FROM nsFormatTemplate nst WHERE nst.nsFormat = :nsFormat AND nst.code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Number Format Type (Text); Number Format (Text); Counter Format (Text)
- Lookups/suggestions: getTxtHNumberFormatType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `NumberSystemTemplate`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Type | DmTextHelp | `txtHNumberFormatType` | `KeyField_PrpAcs, 4, true` |
| 2 | Number Format | DmTextArea | `txtNumberFormat` | `150, true` |
| 3 | Counter Format | DmTextBox | `txtCounterFormat` | `10, true` |
| 4 | Sample | DmTextBox | `txtNumberFormatSample` | `10, true` |

Other widgets (not in tab list): `txtNsFormatName` (DmSuggestBox), `dmFind` (DmFind), `dmNuberFormatGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHNumberFormatType`: Name [Text, w0, Name]
- `dmFind`: Number Format [Text, w520, nsFormat]; Format Type [Text, w280, formatType]; Counter Format [Text, w300, counterFormat]
- `dmNuberFormatGrd`: Number Format [Text, w150]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHNumberFormatType` → `getTxtHNumberFormatType`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.removeSpace(txtNsFormatName.getText())
- `NsFormat` ← DmShared.removeSpace(txtNumberFormat.getText())
- `CounterFormat` ← DmShared.removeSpace(DmShared.xNull(txtCounterFormat.getText()))
- `FormatTypeCode` ← DmShared.xNull(txtHNumberFormatType.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtNsFormatName.setText` ← data.getSharedKeyFieldName()
- `txtCounterFormat.setText` ← data.getCounterFormat()
- `txtNumberFormat.setText` ← data.getNsFormat()
- `txtHNumberFormatType.setTag` ← data.getFormatTypeCode()
- `txtHNumberFormatType.setText` ← data.getFormatTypeName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtNumberFormatSample.setReadOnly(true)`
- `txtNumberFormat.setReadOnly(true)`
- `txtNumberFormat.setReadOnly(false)`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtNumberFormatSample.setReadOnly(true);
txtNumberFormat.setReadOnly(true);
if (DmShared.xNull(txtHNumberFormatType.getTag()).equalsIgnoreCase("C")) {
txtNumberFormat.setReadOnly(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
onEdit();
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
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(NumberSystemTemplate.this);
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
NumberSystemTemplate.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHNumberFormatType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHNumberFormatType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNumberFormatType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNumberFormatType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNumberFormatType).show();
```

`txtHNumberFormatType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNumberFormatType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNumberFormatType).show();
```

`txtHNumberFormatType.addTextHelpCallBack` → `getSelectedRow()`
```java
resetNumberFormat();
if (blnStatus) {
if (DmShared.xNull(txtHNumberFormatType.getTag()).equalsIgnoreCase("D")) {
txtNumberFormat.setText("{day}{month}{year}{counter}");
} else if (DmShared.xNull(txtHNumberFormatType.getTag()).equalsIgnoreCase("M")) {
txtNumberFormat.setText("{month}{year}{counter}");
manageDisplay();
generatFormatSample();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
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

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "This number format has been used in number format"
- Alert: "This number format has been used in number format"
- Alert: "Only Zero value allow"
- Alert: "Only Number value allow"

## Files
| file | lines |
|---|---|
| client/NumberSystemTemplate.java | 600 |
| client/NumberSystemTemplate_Dsg.java | 226 |
| server/NumberSystemTemplate_SrvImpl.java | 762 |
| shared/Find_Mdl.java | 41 |
| shared/NumberSystemTemplate_Mdl.java | 82 |
| shared/NumberSystemTemplate_Srv.java | 28 |
| shared/NumberSystemTemplate_SrvAsync.java | 29 |
| shared/NumberSystemTemplate_Vld.java | 14 |

## UI labels / columns (client)
`Type`, `Number Format`, `Counter Format`, `Sample`

## Models (shared DTOs)
### NumberSystemTemplate_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<KeyFieldExtended> | grdVType |
| String | nsFormat |
| String | counterFormat |
| String | formatTypeCode |
| String | formatTypeName |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | nsFormat |
| String | counterFormat |
| String | formatTypeName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<NumberSystemTemplate_Mdl> |
| saveRecord | NumberSystemTemplate_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| fillVTypeGrid | String code | DmResult<NumberSystemTemplate_Mdl> |
| getTxtHNumberFormatType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillNumberFormat |  | DmResult<List<KeyFieldExtended>> |
| generatFormatSample | String numberFormat, String counter, String loginDate, String voucherPrefix | DmResult<String> |

## Validator `NumberSystemTemplate_Vld`
```java
public class NumberSystemTemplate_Vld {
    public static void chkSave(NumberSystemTemplate_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getFormatTypeCode(), " Number Format Type", 100);
        DmShared.isValidText(mrTemp.getNsFormat().trim(), "Number Format", 150);
        DmShared.isValidText(mrTemp.getCounterFormat(), "Counter Format ", 200);
    }
}
```

## Server `NumberSystemTemplate_SrvImpl.java` (762 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteSName`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.removeSpace`, `DmShared.xNull`

### `generatFormatSample`
`public DmResult<String> generatFormatSample(String numberFormat, String counter, String loginDate, String voucherPrefix)`

- SQL 1: `Select c.Start_Dt, c.End_Dt, p.sName From {…} .dbo.Company c left join {…} .dbo.productLicensedTo p on c.pltCode = p.pltCode Where c.comp_Code = :comp_Code And c.v_Prefix = :voucherPrefix;`
- Params: `:voucherPrefix` ← `voucherPrefix`; `:comp_Code` ← `getSsnCompanyCode(this)`
- Other calls: `DmBaseShared.convertDate`, `DmBaseShared.dNull`, `DmBaseShared.iNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT nsft.code FROM nsFormatTemplate nsft`

### `getMoveRec`
`public DmResult<NumberSystemTemplate_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT nsft.code,nsft.counterFormat, nsft.formatType , nsft.nsFormat , nsft.preparedBY, nsft.preparedDt, nsft.preparedByIP , nsft.u_EntDt, nsft.u_name FROM nsFormatTemplate nsft Where Code= :code`
- Params: `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From nsFormatTemplate Where code= :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(NumberSystemTemplate_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) AS cnt FROM nsFormatTemplate nst WHERE nst.nsFormat = :nsFormat AND nst.code <> :code`
- SQL 2: `Insert Into nsFormatTemplate(code, nsFormat,counterFormat, formatType, preparedBy, preparedDt, preparedByIP) Values (:code,:nsFormat,:counterFormat, :formatType, :preparedBy, :preparedDt, :preparedByIP )`
- SQL 3: `Update nsFormatTemplate Set nsFormat = :nsFormat, counterFormat = :counterFormat, formatType = :formatType, u_Name = :uName, u_EntDt = :u_EntDt Where code = :code`
- Params: `:nsFormat` ← `DmShared.xNull(mrTemp.getNsFormat())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:nsFormat` ← `mrTemp.getNsFormat()`; `:counterFormat` ← `mrTemp.getCounterFormat()`; `:formatType` ← `DmShared.xNull(mrTemp.getFormatTypeCode())`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:nsFormat` ← `mrTemp.getNsFormat()`; `:counterFormat` ← `mrTemp.getCounterFormat()`; `:formatType` ← `DmShared.xNull(mrTemp.getFormatTypeCode())`; `:uName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`
- Errors: "Invalid key." | "'Number Format' already Exits !!!"
- Other calls: `NumberSystemTemplate_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT nsft.code, nsft.counterFormat, nsft.formatType , nsft.nsFormat FROM nsFormatTemplate nsft`

### `fillVTypeGrid`
`public DmResult<NumberSystemTemplate_Mdl> fillVTypeGrid(String code)`

- SQL 1: `SELECT vt.v_Type AS code ,CONCAT(vt.Description,' (', vt.short_Name,')' )AS name ,vt.category,vt.description FROM voucher_Type vt WHERE vt.v_Type IN ( {…} :code {…} )`

### `getTxtHNumberFormatType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNumberFormatType(DmHelper pageing, boolean blnToEndOfFile)`


### `fillNumberFormat`
`public DmResult<List<KeyFieldExtended>> fillNumberFormat()`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `dataman.utility.numberSystemTemplate.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "'Number Format' already Exits !!!" |

## Notes (hand-written)
_No hand-written notes yet._
