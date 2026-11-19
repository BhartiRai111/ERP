# dm-utility / numberSystem

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/numberSystem/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: numbersystemformat, numberSystemCounter
- Update tables: -
- Delete cascade (in order): numbersystemformat, numberSystem, related
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `SELECT count(*) AS count FROM numberSystemCounter WHERE nsGroup=:nsGroup`
- Validations: Voucher Type (Text); Ns Group (Text); Number Format (Text); Counter Format (Text); Reset Year Wise (Text); Reset Site Wise (Text)
- Lookups/suggestions: getTxtHSite, getTxtHNSGroup, getTxtHIsYesNo, getTxtHVoucherTye, getTxtHFormatTemplate
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `NumberSystem`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextHelpMulti | `txtHVoucherType` | `DmVoucher_PrpAcs, 12, true` |
| 2 | NS Group | DmTextHelp | `txtHVoucherTypeGroup` | `DmTxtHVoucherType_PrpAcs, 10, true` |
| 3 | Number Format | DmTextHelp | `txtHNSFormatTemplate` | `TxtHNSFormatTemplate_PrpAcs, 10, true` |
| 4 | - | DmTextArea | `txtNumberFormatTemp` | `150, true` |
| 5 | Counter Format | DmTextBox | `txtCounterFormat` | `150, true` |
| 6 | Reset Year Wise | DmTextHelp | `txtHIsYearWise` | `KeyField_PrpAcs, 3, true` |
| 7 | Reset Site Wise | DmTextHelp | `txtHIsSiteWise` | `KeyField_PrpAcs, 3, true` |

Other widgets (not in tab list): `gridVoucherType` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherTypeGroup`: Name [Text, w0, Name]; Category [Text, w100, Category]
- `txtHVoucherType`: Type [Text, w220, name]; ShortName [Text, w70, short_Name]; Module [Text, w160, mnuAttachedInModule]
- `txtHNSFormatTemplate`: Number Format [Text, w0, REPLACE(REPLACE(name, '{', ''), '}', '')]; Counter Format [Text, w110, counterFormat]; Format Type [Text, w80, formatTypeName]
- `txtHIsYearWise`: Name [Text, w0, name]
- `txtHIsSiteWise`: Name [Text, w0, name]
- `gridVoucherType`: Voucher Type [Text, w1]
- `dmFind`: Voucher Type [Text, w150, Name]; Short Name [Text, w100, short_Name]; Category [Text, w100, Category]; NS Group [Text, w208, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherTypeGroup` → `getTxtHNSGroup`
- `txtHNSFormatTemplate` → `getTxtHFormatTemplate`
- `txtHIsYearWise` → `getTxtHIsYesNo`
- `txtHIsSiteWise` → `getTxtHIsYesNo`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldCode` ← DmShared.xNull(txtHVoucherTypeGroup.getTag())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `NumberFormat` ← DmShared.xNull(txtHNSFormatTemplate.getText())
- `CounterFormat` ← DmShared.xNull(txtCounterFormat.getText())
- `IsYearWiseCode` ← DmShared.xNull(txtHIsYearWise.getTag())
- `IsSiteWiseCode` ← DmShared.xNull(txtHIsSiteWise.getTag())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtHVoucherType.setText` ← DmShared.xNull(data.getVoucherTypeName())
- `txtHVoucherType.setTag` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtHVoucherType.setTag2nd` ← DmShared.xNull(data.getVoucherTypeCode())
- `txtHVoucherTypeGroup.setText` ← data.getSharedKeyFieldName()
- `txtHVoucherTypeGroup.setTag` ← data.getSharedKeyFieldCode()
- `txtHVoucherTypeGroup.setTag2nd` ← data.getSharedKeyFieldCode()
- `txtHNSFormatTemplate.setText` ← DmShared.xNull(data.getNumberFormat())
- `txtNumberFormatTemp.setText` ← DmShared.xNull(data.getNumberFormat())
- `txtCounterFormat.setText` ← DmShared.xNull(data.getCounterFormat())
- `txtHIsYearWise.setText` ← DmShared.xNull(data.getIsYearWiseName())
- `txtHIsYearWise.setTag` ← DmShared.xNull(data.getIsYearWiseCode())
- `txtHIsSiteWise.setText` ← DmShared.xNull(data.getIsSiteWiseName())
- `txtHIsSiteWise.setTag` ← DmShared.xNull(data.getIsSiteWiseCode())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtNumberFormatTemp.setReadOnly(true)`
- `txtCounterFormat.setEnabled(false)`
- `txtHIsYearWise.setEnabled(flag)`
- `txtHIsSiteWise.setEnabled(flag)`

**On New click:**
- `clear()`
- `manageDisplay()`
- `txtHVoucherType.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = topControl.getMode().equalsIgnoreCase("New") || topControl.getMode().equalsIgnoreCase("Edit");
txtNumberFormatTemp.setReadOnly(true);
txtCounterFormat.setEnabled(false);
txtHIsYearWise.setEnabled(flag);
txtHIsSiteWise.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtHVoucherType.setFocus(true);
gridVoucherType.getGrid().getStore().clear();
gridVoucherType.getGrid().getStore().add(new KeyFieldExtended());
manageDisplay();
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay();
txtHVoucherType.setFocus(true);
txtHVoucherTypeGroup.setEnabled(false);
gridVoucherType.getGrid().getStore().add(new KeyFieldExtended());
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

`topControl.addSaveHandeller` → `saveClicked()`
```java
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(NumberSystem.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtHVoucherType.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherTye(topControl.getMode().trim().equalsIgnoreCase("Edit") ? txtHVoucher
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherType.getDmGrid().getGrid().getStore().clear();
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.getDmGrid().getGrid().getStore().clear();
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpMultiCallBack` → `getSelection()`
```java
if (!DmShared.xNull(txtHVoucherType.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag()))) {
if (topControl.getMode().equalsIgnoreCase("New")) {
txtHVoucherTypeGroup.clear();
txtHNSFormatTemplate.clear();
txtHVoucherType.setTag2nd(DmShared.xNull(txtHVoucherType.getTag()));
gridVoucherType.getGrid().getStore().clear();
gridVoucherType.getGrid().getStore().add(new KeyFieldExtended());
if (blnStatus) {
if (!DmShared.xNull(keyCode).trim().equalsIgnoreCase("") && topControl.getMode().equalsIgnoreCase("New")) {
if (DmShared.xNull(keyCode).split(",").length == 1) {
...
```

`txtHVoucherTypeGroup.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNSGroup(txtHVoucherType.getTag(), helper, blnToEndOfFile, new AsyncCallback<D
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherTypeGroup).show();
public void onSuccess(DmResultPageing<List<TxtHVoucherType_Mdl>> result) {
if (result.isStatus()) {
txtHVoucherTypeGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHVoucherTypeGroup).show();
```

`txtHVoucherTypeGroup.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherTypeGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHVoucherTypeGroup).show();
```

`txtHNSFormatTemplate.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHFormatTemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNSFormatTemplate).show();
public void onSuccess(DmResultPageing<List<TxtHNSFormatTemplate_Mdl>> result) {
if (result.isStatus()) {
txtHNSFormatTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNSFormatTemplate).show();
```

`txtHNSFormatTemplate.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNSFormatTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNSFormatTemplate).show();
```

`txtHNSFormatTemplate.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHNSFormatTemplate.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHNSFormatTemplate.getTag()))) {
resetNumberFormatTemp();
txtHNSFormatTemplate.setTag2nd(DmShared.xNull(txtHNSFormatTemplate.getTag()));
if (blnStatus && tRow != null) {
txtNumberFormatTemp.setText(DmShared.xNull(txtHNSFormatTemplate.getText()));
txtCounterFormat.setText(DmShared.xNull(tRow.getCounterFormat()));
```

`txtHIsYearWise.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIsYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsYearWise).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsYearWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHIsYearWise).show();
```

`txtHIsYearWise.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsYearWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHIsYearWise).show();
```

`txtHIsYearWise.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
```

`txtHIsSiteWise.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIsYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsSiteWise).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsSiteWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHIsSiteWise).show();
```

`txtHIsSiteWise.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsSiteWise.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHIsSiteWise).show();
```

`txtHIsSiteWise.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
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
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getNsGroup());
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Confirm: "Are you sure? You want to export records."

## Files
| file | lines |
|---|---|
| client/NumberSystem.java | 680 |
| client/NumberSystem_Dsg.java | 419 |
| server/NumberSystem_SrvImpl.java | 1222 |
| shared/Find_Mdl.java | 50 |
| shared/NumberSystem_Mdl.java | 119 |
| shared/NumberSystem_Srv.java | 47 |
| shared/NumberSystem_SrvAsync.java | 47 |
| shared/NumberSystem_Vld.java | 17 |
| shared/TxtHNSFormatTemplate_Mdl.java | 50 |
| shared/TxtHVoucherType_Mdl.java | 43 |

## UI labels / columns (client)
`Voucher Type`, `NS Group`, `Number Format`, `Counter Format`, `Reset Year Wise`, `Reset Site Wise`

## Models (shared DTOs)
### NumberSystem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<KeyFieldExtended> | grdVType |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | numberFormat |
| String | counterFormat |
| String | siteCode |
| String | siteName |
| String | isSiteWiseCode |
| String | isSiteWiseName |
| String | isYearWiseCode |
| String | isYearWiseName |

### TxtHVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |
| String | description |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | category |
| String | description |
| String | nsGroup |
| String | shortName |

### TxtHNSFormatTemplate_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | formatTypeCode |
| String | formatTypeName |
| String | numberFormat |
| String | counterFormat |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<NumberSystem_Mdl> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| fillType | String voucherType | DmResult<List<KeyFieldExtended>> |
| saveRecord | NumberSystem_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIsYesNo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNSGroup | String v_Type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHVoucherType_Mdl>> |
| fillNumberFormat |  | DmResult<List<KeyFieldExtended>> |
| getCountList | String strKey | DmResult<List<TxtHNSFormatTemplate_Mdl>> |
| deleteRecordPopUp | TxtHNSFormatTemplate_Mdl grdCounter_Mdl | DmResult<String> |
| chkNumberFormat | String numberFormat, List<KeyFieldExtended> numberFormatLst | DmResult<String> |
| InsertRecordPopUp | TxtHNSFormatTemplate_Mdl grdCounter_Mdl | DmResult<String> |
| exportDataToCSV | String session | DmResult<KeyFieldExtended> |
| getTxtHVoucherTye | String nsGroup, String voucherTypeCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| fillVTypeGrid | String code | DmResult<NumberSystem_Mdl> |
| getTxtHFormatTemplate | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHNSFormatTemplate_Mdl>> |

## Validator `NumberSystem_Vld`
```java
public class NumberSystem_Vld {
    public static void chkSave(NumberSystem_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getSharedKeyFieldCode(), "Ns Group", 200);
        DmShared.isValidText(mrTemp.getNumberFormat().trim(), "Number Format", 300);
        DmShared.isValidText(mrTemp.getCounterFormat().trim(), "Counter Format", 300);
        DmShared.isValidText(mrTemp.getIsYearWiseCode().trim(), "Reset Year Wise", 600);
        DmShared.isValidText(mrTemp.getIsSiteWiseCode().trim(), "Reset Site Wise", 700);
    }
}
```

## Server `NumberSystem_SrvImpl.java` (1222 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT MAX(nsGroup) AS SearchCode FROM numbersystemformat GROUP BY nsGroup ORDER BY nsGroup`

### `getMoveRec`
`public DmResult<NumberSystem_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT ns.v_Type AS v_TypeCode, vt.description AS v_TypeName , nsf.nsGroup AS nsGroupCode, vtnsg.description AS nsGroupName, nsf.nsFormat , ns.includeV_Type, nsf.nsFormat, nsf.counterFormat , nsf.isYearWise, nsf.isSiteWise , nsf.site_Code, sm.name as siteName FROM numberSystem ns LEFT JOIN numbersystemformat nsf ON nsf.nsGroup = ns.nsGroup LEFT JOIN voucher_type vtnsg ON vtnsg.v_Type = nsf.nsGroup LEFT JOIN voucher_type vt ON vt.v_Type = ns.v_Type LEFT JOIN sitemast sm ON sm.code = nsf.site_Code WHERE nsf.nsGroup = :nsGroup ORDER BY nsf.nsGroup`
- Params: `:nsGroup` ← `strKey`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT (ns.v_type) AS CODE,ns.nsGroup,VT.Description AS NAME , vt.short_Name, (VT.category) AS category,(VT.Description) AS Description FROM numberSystem ns LEFT JOIN voucher_type vt ON ns.v_type=vt.v_Type`

### `fillType`
`public DmResult<List<KeyFieldExtended>> fillType(String voucherType)`

- SQL 1: `SELECT V_Type,Description,short_Name FROM Voucher_Type WHERE V_Type<> :voucherType`

### `saveRecord`
`public DmResult<String> saveRecord(NumberSystem_Mdl mrTemp)`

- SQL 1: `Delete From numbersystemformat Where nsGroup = :nsGroup`
- SQL 2: `Delete From numberSystem Where nsGroup = :nsGroup`
- SQL 3: `Insert Into numbersystemformat(nsGroup, site_Code, nsFormat, counterFormat, isYearWise, isSiteWise) Values (:nsGroup, :site_Code, :nsFormat, :counterFormat, :isYearWise, :isSiteWise)`
- Params: `:nsGroup` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:nsGroup` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:nsGroup` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:nsFormat` ← `DmShared.xNull(mrTemp.getNumberFormat())`; `:counterFormat` ← `DmShared.xNull(mrTemp.getCounterFormat())`; `:isYearWise` ← `DmServer.vNull(mrTemp.getIsYearWiseCode(), "0")`; `:isSiteWise` ← `DmServer.vNull(mrTemp.getIsSiteWiseCode(), "0")`; `:nsGroup` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:u_Name` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:v_type` ← `DmShared.foriegnKey(vType.getSharedKeyFieldCode())`; `:includeV_Type` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`
- Other calls: `NumberSystem_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `SELECT count(*) AS count FROM numberSystemCounter WHERE nsGroup=:nsGroup`
- SQL 2: `Delete From numbersystemformat Where nsGroup=:nsGroup`
- SQL 3: `Delete From numberSystem Where nsGroup =:nsGroup`
- SQL 4: `Delete related counter.`
- Params: `:nsGroup` ← `strKey`; `:nsGroup` ← `strKey`; `:nsGroup` ← `strKey`
- Errors: "Delete related counter."

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SM.Code,SM.Name FROM SiteMast SM WHERE SM.pltCode= :pltCode`
- Params: `:pltCode` ← `DmServer.getSsnPLTCode(this)`

### `getTxtHNSGroup`
`public DmResultPageing<List<TxtHVoucherType_Mdl>> getTxtHNSGroup(String v_Type, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.V_Type AS v_Type, vt.Description AS name, vt.short_Name, vt.category, vt.Description FROM Voucher_Type vt WHERE vt.v_Type IN ( {…} :v_Type {…} )`

### `fillNumberFormat`
`public DmResult<List<KeyFieldExtended>> fillNumberFormat()`


### `getTxtHIsYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIsYesNo(DmHelper pageing, boolean blnToEndOfFile)`


### `getTxtHVoucherTye`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherTye(String nsGroup, String voucherTypeCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code , vt.Description AS name , vt. short_Name , vt.category , vt.mnuAttachedInModule , IIF(ISNULL(vt.mnuAttachedInModule, '') <> '', 0, 1) AS orderIndex , {…} AS checkedIndex FROM Voucher_Type vt WHERE vt.v_type NOT IN ( SELECT v_type FROM( SELECT ns.v_type FROM numberSystem ns WHERE 1 = 1 AND ns.nsGroup <> :nsGroup UNION ALL SELECT nsf.nsGroup AS v_Type FROM numbersystemformat nsf WHERE 1 = 1 AND nsf.nsGroup <> :nsGroup ) AS tab )`
- Params: `:nsGroup` ← `DmShared.xNull(nsGroup)`

### `getCountList`
`public DmResult<List<TxtHNSFormatTemplate_Mdl>> getCountList(String strKey)`

- SQL 1: `SELECT nsc.nsFormatValue AS Value, nsc.v_prefix AS v_type, nsc.site_Code AS scode, sm.name AS snanm, nsc.counter AS counter FROM numberSystemCounter AS nsc LEFT JOIN numberSystem AS ns ON nsc.nsGroup=ns.nsGroup LEFT JOIN siteMast AS sm ON sm.code = nsc.site_Code LEFT JOIN voucher_Type AS vt ON vt.v_Type = nsc.nsGroup WHERE nsc.nsGroup=:nsGroup`
- Params: `:nsGroup` ← `strKey`

### `deleteRecordPopUp`
`public DmResult<String> deleteRecordPopUp(TxtHNSFormatTemplate_Mdl linkedUserGrid)`

- SQL 1: `Delete From numberSystemCounter Where nsGroup =:nsGroup AND nsFormatValue =:nsFormatValue AND v_prefix=:v_prefix AND isnull( site_Code,'')=:site_Code`
- Params: `:nsFormatValue` ← `DmShared.xNull(linkedUserGrid.getNumberFormat())`; `:nsGroup` ← `DmShared.xNull(linkedUserGrid.getSharedKeyFieldCode())`

### `chkNumberFormat`
`public DmResult<String> chkNumberFormat(String numberFormat, List<KeyFieldExtended> numberFormatLst)`


### `InsertRecordPopUp`
`public DmResult<String> InsertRecordPopUp(TxtHNSFormatTemplate_Mdl objGrdCounter_Mdl)`

- SQL 1: `SELECT count(*) as cnt FROM numberSystemCounter WHERE nsFormatValue =:nsFormatValue AND v_prefix=:v_prefix AND isnull(site_Code,'')=:site_Code AND nsGroup =:nsGroup`
- SQL 2: `SELECT nsGroup,nsFormatValue,v_prefix,site_code,counter FROM numberSystemCounter WHERE nsGroup =:nsGroup`
- SQL 3: `Delete From numberSystemCounter Where nsGroup =:nsGroup`
- SQL 4: `INSERT INTO numberSystemCounter (nsGroup,nsFormatValue,v_prefix,site_Code,counter) VALUES (:nsGroup,:nsFormatValue,:v_prefix,:site_Code,:counter)`
- Params: `:nsFormatValue` ← `DmShared.xNull(objGrdCounter_Mdl.getNumberFormat())`; `:nsGroup` ← `DmShared.xNull(objGrdCounter_Mdl.getSharedKeyFieldCode())`; `:nsFormatValue` ← `DmShared.xNull(objGrdCounter_Mdl.getNumberFormat())`; `:nsGroup` ← `DmShared.xNull(objGrdCounter_Mdl.getSharedKeyFieldCode())`; `:counter` ← `DmShared.xNull(objGrdCounter_Mdl.getCounterFormat())`; `:nsGroup` ← `DmShared.xNull(objGrdCounter_Mdl.getSharedKeyFieldCode())`; `:nsGroup` ← `DmShared.xNull(objGrdCounter_Mdl.getSharedKeyFieldCode())`; `:nsGroup` ← `DmShared.foriegnKey(lstCounterEntry.get(i).getSharedKeyFieldCode())`; `:nsFormatValue` ← `DmShared.xNull(lstCounterEntry.get(i).getNumberFormat())`; `:counter` ← `DmServer.vNull(lstCounterEntry.get(i).getCounterFormat(), "0")`
- Errors: "This Number Format already exists."

### `exportDataToCSV`
`public DmResult<KeyFieldExtended> exportDataToCSV(String session)`

- SQL 1: `SELECT nsf.counterFormat,nsf.nsFormat,ns.nsGroup AS nsgVType,ns.v_Type AS nsVType,vtns.short_Name AS nsGroup,vtin.short_Name AS includeV_Type,vtin.short_Name AS v_Type,nsf.isSiteWise,nsf.isYearWise FROM numbersystemformat nsf LEft JOIN numberSystem ns ON ns.nsGroup=nsf.nsGroup LEFT JOIN sitemast sm ON nsf.site_Code = sm.code LEFT JOIN voucher_type vtin ON ns.v_Type= vtin.v_Type LEFT JOIN voucher_type vtns ON ns.nsGroup= vtns.v_Type WHERE nsf.site_Code is null and ns.v_Type is not null order by ns.nsGroup`
- Errors: "Invalid request." | "File Name not given."
- Other calls: `FILE_HEADER.toString`

### `fillVTypeGrid`
`public DmResult<NumberSystem_Mdl> fillVTypeGrid(String code)`

- SQL 1: `SELECT vt.v_Type AS code , vt.Description AS name , vt.category,vt.description FROM voucher_Type vt WHERE vt.v_Type IN ( {…} :code {…} )`

### `getTxtHFormatTemplate`
`public DmResultPageing<List<TxtHNSFormatTemplate_Mdl>> getTxtHFormatTemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ft.code, ft.nsFormat AS name , ft.nsFormat, ft.counterFormat , ft.formatType , (CASE WHEN ft.formatType = 'D' THEN 'Daily' WHEN ft.formatType = 'M' THEN 'Monthly' WHEN ft.formatType = 'C' THEN 'Custom' ELSE '' END ) AS formatTypeName FROM nsFormatTemplate ft`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.numberSystem.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | (unconditional) | "Delete related counter." |
| chkNumberFormat | `if (strArray.length > 2)` | " Number Format is repeating, You can use only one Number Format ." |
| InsertRecordPopUp | `} else if (DmShared.vNull(rsTemp.getString("cnt")).doubleValue() > 1)` | "This Number Format already exists." |
| exportDataToCSV | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportDataToCSV | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
