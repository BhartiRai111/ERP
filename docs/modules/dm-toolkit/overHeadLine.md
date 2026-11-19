# dm-toolkit / overHeadLine

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/overHeadLine/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHPostingAC
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `OverHeadLineGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Particulars | DmTextBox | `txtOverHead` | `50, true` |
| 2 | Alias | DmTextBox | `txtOverHeadAlias` | `50, true` |
| 3 | A/c Name | DmTextHelp | `txtHPostingAC` | `TxtHPostingAC_PrpAcs, 7, false` |
| 4 | At Rate | DmTextBoxNumeric | `txtAtRate` | `DmTextBoxNumeric.DecimalNature.NONE, true, 6, false` |
| 5 | Amount | DmTextBoxNumeric | `txtAmount` | `DmTextBoxNumeric.DecimalNature.NONE, true, 15, false` |

**Grid / popup columns** (header, width, db field):
- `txtHPostingAC`: Name [Text, w0, name]; Code [Text, w50, ManualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHPostingAC` → `getTxtHPostingAC`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtOverHeadAlias.setEnabled(false)`
- `txtAtRate.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtHPostingAC.setEnabled(false)`
- `txtOverHeadAlias.setEnabled(true)`
- `txtAmount.setEnabled(true)`
- `txtAtRate.setEnabled(true)`
- `txtHPostingAC.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtOverHeadAlias.setEnabled(false);
txtAtRate.setMandatory(false);
txtAtRate.setEnabled(false);
txtAmount.setMandatory(false);
txtAmount.setEnabled(false);
txtHPostingAC.setEnabled(false);
if (parentRow != null) {
if (DmShared.vNull(parentRow.getAtRateMandatory()).doubleValue() == 1) {
txtAtRate.setMandatory(true);
if (DmShared.vNull(parentRow.getAmountMandatory()).doubleValue() == 1) {
txtAmount.setMandatory(true);
if (callBack.allowEditable()) {
if (DmShared.vNull(parentRow.getOverHeadAliasEditable()).doubleValue() == 1) {
txtOverHeadAlias.setEnabled(true);
if (DmShared.vNull(parentRow.getAmountEditable()).doubleValue() == 1) {
txtAmount.setEnabled(true);
if (DmShared.vNull(parentRow.getAtRateEditable()).doubleValue() == 1) {
txtAtRate.setEnabled(true);
if (DmShared.vNull(parentRow.getAcEditable()).doubleValue() == 1) {
txtHPostingAC.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHPostingAC.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPostingAC(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Acco
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPostingAC).show();
public void onSuccess(DmResultPageing<List<AccountHelp_Mdl>> result) {
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

### Screen `OverHeadLine`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grid` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grid`: Particulars [Text]; At Rate [Text, w63]; Amount [Text, w100]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`overHeadGridEdit.addCallBack` → `onOk()`
```java
calculate(getItemValue());
grid.commitChanges();
```

`overHeadGridEdit.addCallBack` → `onDelete()`
```java
grid.commitChanges();
```

`overHeadGridEdit.addCallBack` → `allowEditable()`
```java
if (callBack != null) {
return callBack.allowEditable();
return true;
```

## Files
| file | lines |
|---|---|
| client/OverHeadLine.java | 349 |
| client/OverHeadLineGridEdit.java | 204 |
| client/OverHeadLineGridEdit_Dsg.java | 144 |
| client/OverHeadLineHandler.java | 378 |
| client/OverHeadLine_Dsg.java | 148 |
| server/OverHeadLine_Server.java | 541 |
| server/OverHeadLine_SrvImpl.java | 95 |
| shared/AccountHelp_Mdl.java | 22 |
| shared/ITS_Mdl.java | 131 |
| shared/OHLExtended.java | 13 |
| shared/OverHeadLine_Mdl.java | 257 |
| shared/OverHeadLine_Shared.java | 229 |
| shared/OverHeadLine_Srv.java | 16 |
| shared/OverHeadLine_SrvAsync.java | 16 |
| shared/OverHeadLine_Vld.java | 28 |
| shared/SyncOverHeadMA_Mdl.java | 32 |
| shared/SyncOverHead_Mdl.java | 41 |

## UI labels / columns (client)
`Particulars`, `Alias`, `A/c Name`, `At Rate`, `Amount`

## Models (shared DTOs)
### SyncOverHeadMA_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### ITS_Mdl
| type | field |
|---|---|
| String | itsCode |
| String | taxTypeCode |
| String | taxTypeName |
| String | overHeadManualCode |
| String | overHeadCode |
| String | accountManualCode |
| String | accountCode |
| String | accountName |
| String | atRate |

### SyncOverHead_Mdl
| type | field |
|---|---|
| String | ohManualCode |
| String | amount |
| List<SyncOverHeadMA_Mdl> | overHeadMAList |

### OverHeadLine_Mdl (extends `OHBase_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | overHeadConfigCode |
| String | formula |
| String | nature |
| String | overHeadAlias |
| String | atRateMandatory |
| String | amountMandatory |
| String | roundOff |
| String | uniqueId |
| String | atRateSaveIn |
| String | amountSaveIn |
| String | acSaveIn |
| String | tableName |
| String | atRateEditable |
| String | amountEditable |
| String | acEditable |
| String | overHeadAliasEditable |
| String | showFormulaValue |
| String | printable |
| String | display |
| String | accountName |
| String | accountCode |
| String | calLValOn |

### AccountHelp_Mdl (extends `KeyField implements Serializable, IsSerializable`)
## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHPostingAC | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<AccountHelp_Mdl>> |
| getOverHead | String vType | DmResult<List<OverHeadLine_Mdl>> |

## Validator `OverHeadLine_Vld`
```java
public class OverHeadLine_Vld {
    public static void validateOverHead(List<OverHeadLine_Mdl> mrTemp, int errorNo) {
        validateOverHead(mrTemp, errorNo, null, null, -1);
    }
    public static void validateOverHead(List<OverHeadLine_Mdl> mrTemp, int errorNo, String errorSource, String errorSourceCode, int lineNo) {
        String lineNoMsg = lineNo > -1 ? (" in line no. " + lineNo + ".") : "";
        for (OverHeadLine_Mdl overHead_Mdl : mrTemp) {
            if (DmShared.iNull(overHead_Mdl.getAtRateMandatory()).intValue() == 1 && DmShared.vNull(overHead_Mdl.getAtRate()).doubleValue() == 0) {
                throw new DmException(errorNo, errorSource, errorSourceCode, "'At Rate' is mandatory for '" + overHead_Mdl.getSharedKeyFieldName() + "'" + lineNoMsg);
            }
            if (DmShared.iNull(overHead_Mdl.getAmountMandatory()).intValue() == 1 && DmShared.vNull(overHead_Mdl.getAmount()).doubleValue() == 0) {
                throw new DmException(errorNo, errorSource, errorSourceCode, "'Amount' is mandatory for '" + overHead_Mdl.getSharedKeyFieldName() + "'" + lineNoMsg);
            }
        }
    }
}
```

## Server `OverHeadLine_Server.java` (541 lines)
Helpers used: `DmServer.convertDate`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getITS`
`private static List<ITS_Mdl> getITS(String itsCode, String locationCode, String gstTypeCode, String siteCode, Connection cnTemp)`

- SQL 1: `SELECT its.code, its.gstTypeCode, gt.name AS gstTypeName , ohm.manualcode AS overHeadManualCode, itsd.overheadCode , sg.manualcode AS acManualCode, sg.subCode AS acCode, sg.name AS acName , itsd.atRate FROM its LEFT JOIN itsdetail itsd ON itsd.code = its.code LEFT JOIN overheadmast ohm ON itsd.overheadcode = ohm.code LEFT JOIN gstType gt ON gt.code = its.gstTypeCode LEFT JOIN subgroup sg ON itsd.subcode = sg.subcode WHERE 1 = 1 {…}`
- Params: `:siteCode` ← `DmShared.iNull(siteCode).toString()`; `:itsCode` ← `DmShared.iNull(itsCode).toString()`; `:locationCode` ← `DmShared.iNull(locationCode).toString()`; `:gstTypeCode` ← `DmShared.iNull(gstTypeCode).toString()`

### `getITSOld`
`public static List<ITS_Mdl> getITSOld(String itsCode, String locationCode, String siteCode, String applicableDate, Connection cnTemp)`

- SQL 1: `select itsd.overHeadCode,sg.subCode,sg.name as sgName,itsd.atRate,itsd.amount,ohm.manualCode, sg.manualCode AS sgManualCode from itsDetail itsd left join subgroup sg on itsd.subCode = sg.subCode left join overHeadMast ohm on itsd.overHeadCode = ohm.code where itsd.code = :itsCode and itsd.locationCode = :locationCode and isNull(itsd.site_Code,:siteCode) = :siteCode and :applicableDate between itsd.applicableFrom and itsd.applicableTo`
- Params: `:itsCode` ← `DmShared.iNull(itsCode).toString()`; `:locationCode` ← `DmShared.iNull(locationCode).toString()`; `:siteCode` ← `DmShared.iNull(siteCode).toString()`; `:applicableDate` ← `DmServer.convertDate(applicableDate)`

### `copyOverHead`
`public static void copyOverHead(List<OverHeadLine_Mdl> from, List<OverHeadLine_Mdl> to, boolean isCopyAccount, Connection cnTemp)`


### `getOverHead`
`private static List<OverHeadLine_Mdl> getOverHead(String vType, String siteCode, String docId, String v_SNo, String overHeadLineTableName, Connection cnTemp)`

- SQL 1: `SELECT ohcn.code AS ohConfigCode , ohc.overHeadCode , '' AS subcode , '' AS acName , ohc.atRate AS configAtRate , ohc.atRate , ohc.nature , ohc.atRateSaveIn , ohc.amountSaveIn , ohc.acSaveIn , ohc.tableName , ohc.isatRateEditable , ohc.isamountEditable , ohc.isacEditable , ohc.isshowFormulaValue , ohc.roundOff , ohc.UniqueId , ohm.name as ohName , ohm.manualCode As ohManualCode , 0 As Amount , ohc.formula , ohc.isamountMandatory , ohc.isatRateMandatory , Null As overheadAlias , ohc.isoverHeadAliasEditable , ohc.isPrintable , ohc.isDisplay , ohc.calLValOn FROM overHeadConfigName ohcn LEFT JOIN (SELECT vt.voucherGroupCode FROM voucher_Type vt WHERE vt.v_Type = :vType GROUP BY vt.voucherGroupCode ) Vvt ON Vvt.voucherGroupCode = ohcn.voucherGroupCode LEFT JOIN overHeadConfigSite ohcs ON ohcs.code = ohcn.code LEFT JOIN overHeadLineConfig ohc ON ohc.code = ohcn.code LEFT JOIN overHeadMast ohm ON ohm.Code = ohc.overHeadCode WHERE ohcn.isActive = 1 AND ISNULL(ohcs.site_Code, :siteCode) = :siteCode AND ( ohcn.v_type = :vType OR (ISNULL(ohcn.v_type, '') = '' AND ISNULL(Vvt.voucherGroupCode, '') <> '')) {…} ORDER BY ohc.sno`
- SQL 2: `SELECT ohd.ohConfigCode , ohd.overHeadCode , ohd.subcode , sg.name As acName , ohc.atRate AS configAtRate , ohd.atRate , ohd.amount , /*ohd.overheadAlias*/ '' AS overheadAlias , ohc.nature , ohc.sNo , ohc.atRateSaveIn , ohc.amountSaveIn , ohc.acSaveIn , ohc.tableName , ohc.isatRateEditable , ohc.isamountEditable , ohc.isacEditable , ohc.isshowFormulaValue , ohc.roundOff , ohc.UniqueId , ohm.Name ohName , ohm.manualCode As ohManualCode , ohc.formula , ohc.isamountMandatory , ohc.isatRateMandatory , ohc.isoverHeadAliasEditable , ohc.isPrintable , ohc.isDisplay , ohc.calLValOn FROM {…} ohd LEFT JOIN overHeadLineConfig ohc ON ohc.code = ohd.ohConfigCode AND ohc.overHeadCode = ohd.overHeadCode LEFT JOIN overHeadMast ohm ON ohm.Code = ohc.overHeadCode LEFT JOIN subGroup sg ON sg.subCode = ohd.subcode WHERE ohd.docId = :docId AND ohd.v_SNo = :v_SNo ORDER BY ohd.sNo`
- Params: `:siteCode` ← `siteCode`; `:vType` ← `vType`; `:docId` ← `docId`; `:v_SNo` ← `DmShared.iNull(v_SNo).toString()`

### `delete`
`public static void delete(String docId, String overHeadLineTableName, Connection cnTemp)`

- SQL 1: `delete from {…} where docId = :docId;`
- SQL 2: `delete from overheadDetailTrans where docId = :docId;`
- Params: `:docId` ← `docId`; `:docId` ← `docId`

### `save`
`public static void save(List<? extends OHLExtended> rowList, String docId, String siteCode, Connection cnTemp, String overHeadLineTableName, String affectedTableName)`

- SQL 1: `INSERT INTO {…} (docId, v_SNo, sNo, overHeadCode, ohConfigCode, subCode, atRate, amount) VALUES(:docId, :v_SNo, :sNo, :overHeadCode, :ohConfigCode, :subCode, :atRate, :amount)`
- Params: `:docId` ← `docId`; `:v_SNo` ← `vSNo`; `:sNo` ← `++sNo`; `:overHeadCode` ← `DmShared.xNull(overHead_Mdl.getSharedKeyFieldCode())`; `:ohConfigCode` ← `DmShared.xNull(overHead_Mdl.getOverHeadConfigCode())`; `:subCode` ← `DmShared.foriegnKey(overHead_Mdl.getAccountCode())`; `:atRate` ← `DmShared.foriegnKey(overHead_Mdl.getAtRate())`; `:amount` ← `DmShared.foriegnKey(overHead_Mdl.getAmount())`
- Errors: "Invalid key field."
- Other calls: `OverHeadLine_Server.delete`

## Server `OverHeadLine_SrvImpl.java` (95 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.xNull`

### `getTxtHPostingAC`
`public DmResultPageing<List<AccountHelp_Mdl>> getTxtHPostingAC(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SubCode,Name,ManualCode From Subgroup`

### `getOverHead`
`public DmResult<List<OverHeadLine_Mdl>> getOverHead(String vType)`

- Other calls: `OverHeadLine_Server.getOverHeadConfig`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.overHead.client`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
