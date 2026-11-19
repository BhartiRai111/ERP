# dm-toolkit / overHead

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/overHead/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: 'Posting Account' is mandatory for '
- Lookups/suggestions: getTxtHPostingAC
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `OverHeadExcludePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grid` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grid`: Overhead [Text, w247]; Amount [Text, w100];  [Text, w30]

### Screen `OverHeadGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Particulars | DmTextBox | `txtOverHead` | `50, true` |
| 2 | Alias | DmTextBox | `txtOverHeadAlias` | `50, true` |
| 3 | A/c Name | DmTextHelp | `txtHPostingAC` | `TxtHPostingAC_PrpAcs, 7, false` |
| 4 | At Rate | DmTextBoxNumeric | `txtAtRate` | `DmTextBoxNumeric.DecimalNature.NONE, true, 6, false` |
| 5 | Amount | DmTextBoxNumeric | `txtAmount` | `DmTextBoxNumeric.DecimalNature.NONE, true, 15, false` |

Other widgets (not in tab list): `txtDrCrType` (DmTextBox), `gridMA` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHPostingAC`: Name [Text, w0, name]; Code [Text, w50, ManualCode]
- `gridMA`: A/c Name [Text, w1]; Amount [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHPostingAC` → `getTxtHPostingAC`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtOverHead.setEnabled(false)`
- `txtOverHeadAlias.setEnabled(false)`
- `txtAtRate.setEnabled(false)`
- `txtAmount.setEnabled(false)`
- `txtOverHeadAlias.setEnabled(true)`
- `txtAmount.setEnabled(true)`
- `txtAtRate.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtOverHead.setEnabled(false);
txtOverHeadAlias.setEnabled(false);
txtAtRate.setMandatory(false);
txtAtRate.setEnabled(false);
txtAmount.setMandatory(false);
txtAmount.setEnabled(false);
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
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHPostingAC.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPostingAC(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Acco
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

`overHeadGridMAEdit.addCallBack` → `onOk()`
```java
gridMA.commitChanges();
```

`overHeadGridMAEdit.addCallBack` → `onDelete()`
```java
gridMA.commitChanges();
```
- Alert: "Please fill A/c Name."
- Alert: "Duplicate A/c Name."
- Alert: "Please bifurcate full amount"

### Screen `OverHeadGridMAEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Posting A/c | DmTextHelp | `txtHPostingAC` | `TxtHPostingAC_PrpAcs, 7, true` |
| 2 | Amount | DmTextBoxNumeric | `txtAmount` | `DmTextBoxNumeric.DecimalNature.NONE, true, 15, true` |

**Grid / popup columns** (header, width, db field):
- `txtHPostingAC`: Name [Text, w0, name]; Code [Text, w50, ManualCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHPostingAC` → `getTxtHPostingAC`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHPostingAC.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPostingAC(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Acco
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
- Alert: "A/c Name is mandatory."
- Alert: "Amount is mandatory."
- Alert: "A/c Name already exists."

### Screen `OverHead`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grid` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grid`: Particulars [Text]; Particulars [Text, w185]; At Rate [Text]; At Rate [Text, w65]; Amount [Text]; Amount [Text, w100]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`overHeadGridEdit.addCallBack` → `onOk()`
```java
calculate(grossAmount);
grid.commitChanges();
```

`overHeadGridEdit.addCallBack` → `onDelete()`
```java
grid.commitChanges();
```

`overHeadGridEdit.addCallBack` → `allowEditable()`
```java
if (callback != null) {
return callback.allowEditable();
return true;
```

`excludePopup.addCallBack` → `ok()`
```java
String excludeFormula = "";
for (OverHeadExclude_Mdl excludeMdl : excludePopup.grid.getGrid().getStore().getAll()) {
if (DmShared.xNull(excludeMdl.getIsSelected()).trim().equals("1")) {
if (!excludeFormula.equals("")) {
excludeFormula += "+";
excludeFormula += "[" + DmShared.xNull(excludeMdl.getManualCode()) + "]";
for (OverHead_Mdl ohMdl : getOverHeadList()) {
if (DmShared.xNull(ohMdl.getCalLValOn()).trim().equals("1")) {
if (!DmShared.xNull(excludeFormula).trim().equalsIgnoreCase("")) {
if (!DmShared.xNull(ohMdl.getActualFormula()).trim().equalsIgnoreCase("")) {
...
```

`excludePopup.addCallBack` → `close()`
```java
focusGrid();
```

`postingAccountPopup.addCallBack` → `close()`
```java
focusGrid();
```

### Screen `PostingAccountDetailPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `gridMA` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `gridMA`: A/c Name [Text, w230]; Amount [Text, w100]

### Screen `PostingAccountPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtDrAmount` (DmTextBoxNumeric), `txtCrAmount` (DmTextBoxNumeric), `grid` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grid`: OverHead [Text, w185]; At Rate [Text, w65]; Amount [Text, w100]; Account [Text, w245]; Dr/Cr [Text, w50]

## Files
| file | lines |
|---|---|
| client/OverHead.java | 558 |
| client/OverHeadExcludePopup.java | 207 |
| client/OverHeadExcludePopup_Dsg.java | 140 |
| client/OverHeadGridEdit.java | 375 |
| client/OverHeadGridEdit_Dsg.java | 187 |
| client/OverHeadGridMAEdit.java | 145 |
| client/OverHeadGridMAEdit_Dsg.java | 90 |
| client/OverHead_Dsg.java | 173 |
| client/PostingAccountDetailPopup.java | 113 |
| client/PostingAccountDetailPopup_Dsg.java | 94 |
| client/PostingAccountPopup.java | 191 |
| client/PostingAccountPopup_Dsg.java | 187 |
| server/OverHead_Server.java | 833 |
| server/OverHead_SrvImpl.java | 97 |
| shared/AccountHelp_Mdl.java | 22 |
| shared/DrCrAmount_Mdl.java | 31 |
| shared/OHBaseAPI.java | 15 |
| shared/OHBaseShared.java | 51 |
| shared/OHBase_Mdl.java | 116 |
| shared/OHHeadAccount_Mdl.java | 41 |
| shared/OverHeadDimension.java | 70 |
| shared/OverHeadExclude_Mdl.java | 59 |
| shared/OverHeadMA_Mdl.java | 42 |
| shared/OverHeadPost_Mdl.java | 40 |
| shared/OverHead_Mdl.java | 331 |
| shared/OverHead_Shared.java | 370 |
| shared/OverHead_Srv.java | 16 |
| shared/OverHead_SrvAsync.java | 16 |
| shared/OverHead_Vld.java | 54 |

## UI labels / columns (client)
`Posting A/c`, `Amount`, `Particulars`, `Alias`, `A/c Name`, `At Rate`

## Models (shared DTOs)
### OverHeadExclude_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | atRate |
| String | amount |
| String | formula |
| String | manualCode |
| String | isSelected |

### OverHead_Mdl (extends `OHBase_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | overHeadConfigCode |
| String | formula |
| String | actualFormula |
| String | excludeFormula |
| String | drCr |
| String | contraOhMCode |
| String | nature |
| String | overHeadAlias |
| String | atRateMandatory |
| String | amountMandatory |
| String | uniqueId |
| String | OHSetUp |
| String | roundOff |
| String | atRateSaveIn |
| String | amountSaveIn |
| String | tableName |
| String | atRateEditable |
| String | amountEditable |
| String | acEditable |
| String | overHeadAliasEditable |
| String | showFormulaValue |
| String | printable |
| String | display |
| String | calLValOn |
| String | accountName |
| String | accountCode |
| String | isMultiContraText |
| List<OverHeadMA_Mdl> | overheadMA_List |

### OHBase_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ohManualCode |
| String | configAtRate |
| String | atRate |
| String | amount |

### OHHeadAccount_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | overHeadManualCode |
| String | acCode |
| String | acName |

### OverHeadPost_Mdl (extends `KeyField implements Serializable, IsSerializable`)
### DrCrAmount_Mdl
| type | field |
|---|---|
| String | drAmount |
| String | crAmount |

### OverHeadMA_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | amount |

### AccountHelp_Mdl (extends `KeyField implements Serializable, IsSerializable`)
## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHPostingAC | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<AccountHelp_Mdl>> |
| getOverHead | String vType | DmResult<List<OverHead_Mdl>> |

## Validator `OverHead_Vld`
```java
public class OverHead_Vld {
    public static void validateOverHead(List<OverHead_Mdl> mrTemp) {
        validateOverHead(mrTemp, 0, false);
    }
    public static void validateOverHead(List<OverHead_Mdl> mrTemp, boolean isPostingNeeded) {
        validateOverHead(mrTemp, 0, isPostingNeeded);
    }
    public static void validateOverHead(List<OverHead_Mdl> mrTemp, int errorNo, boolean isPostingNeeded) {
        for (OverHead_Mdl overHead_Mdl : mrTemp) {
            boolean isValidPostingAccount = false;
            double drCrAmount = DmShared.vNull(overHead_Mdl.getAmount()).doubleValue();
            if (DmShared.iNull(overHead_Mdl.getAmountMandatory()).intValue() == 1 && DmShared.vNull(overHead_Mdl.getAmount()).doubleValue() == 0) {
                throw new DmException(errorNo, "'Amount' is mandatory for '" + overHead_Mdl.getSharedKeyFieldName() + "'");
            }
            if (isPostingNeeded) {
                if (drCrAmount != 0 && !DmShared.xNull(overHead_Mdl.getDrCr()).trim().isEmpty()) {
                    if (!DmShared.xNull(overHead_Mdl.getAccountCode()).trim().isEmpty()) {
                        isValidPostingAccount = true;
                    } else {
                        final List<OverHeadMA_Mdl> overheadMA_List = overHead_Mdl.getOverheadMA_List();
                        if (overheadMA_List != null && !overheadMA_List.isEmpty()) {
                            for (OverHeadMA_Mdl overHeadMA_Mdl : overheadMA_List) {
                                if (overHeadMA_Mdl != null && !DmShared.xNull(overHeadMA_Mdl.getSharedKeyFieldCode()).trim().isEmpty()) {
                                    isValidPostingAccount = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!isValidPostingAccount) {
                        throw new DmException(0, "'Posting Account' is mandatory for '" + overHead_Mdl.getSharedKeyFieldName() + "'");
                    }
                }
            }
        }
    }
}
```

## Server `OverHead_Server.java` (833 lines)
Helpers used: `DmBaseServer.ContraText`, `DmBaseServer.getCodeViaManualCode`, `DmServer.convertDate`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getVoucherGroupCode`
`public static String getVoucherGroupCode(String manualCode, Connection cnTemp)`


### `getOverHeadTrans`
`public static List<OverHead_Mdl> getOverHeadTrans(String docId, String overHeadTableName, String overHead_MA_TableName, Connection cnTemp)`

- Other calls: `OverHead_Server.getOverHead`

### `getOverHead`
`private static List<OverHead_Mdl> getOverHead(String vType, String siteCode, String docId, String overHeadTableName, String overHead_MA_TableName, Connection cnTemp)`

- SQL 1: `SELECT ohcn.code AS ohConfigCode , ohc.overHeadCode , '' AS subcode , '' AS acName , ohc.atRate AS configAtRate , ohc.atRate , ohc.contraOhMCode , ohc.drCr , ohc.nature , ohc.atRateSaveIn , ohc.amountSaveIn , ohc.tableName , ohc.isatRateEditable , ohc.isamountEditable , ohc.isacEditable , ohc.isshowFormulaValue , ohc.roundOff , ohc.ohSetUp , ohc.UniqueId , ohm.name AS ohName , ohm.manualCode AS ohManualCode , 0 AS Amount , ohc.formula , ohc.formula AS actualFormula , NULL AS excludeFormula , NULL AS overheadAlias , ohc.isamountMandatory , ohc.isatRateMandatory , ohc.isoverHeadAliasEditable , ohc.isPrintable , ohc.isDisplay , ohc.calLValOn , ohc.isMultiContraText FROM overHeadConfigName ohcn LEFT JOIN (SELECT vt.voucherGroupCode FROM voucher_Type vt WHERE vt.v_Type = :vType GROUP BY vt.voucherGroupCode ) Vvt ON Vvt.voucherGroupCode = ohcn.voucherGroupCode LEFT JOIN overHeadConfigSite ohcs ON ohcs.code = ohcn.code LEFT JOIN overHeadConfig ohc ON ohc.code = ohcn.code LEFT JOIN overHeadMast ohm ON ohm.Code = ohc.overHeadCode LEFT JOIN subGroup sg ON sg.SubCode = ohc.subcode WHERE ohcn.isActive = 1 AND ISNULL(ohcs.site_Code, :siteCode) = :siteCode AND ( ohcn.v_type = :vType OR (ISNULL(ohcn.v_type, '') = '' AND ISNULL(Vvt.voucherGroupCode, '') <> '')) {…} ORDER BY ohc.sno`
- SQL 2: `SELECT oh.ohConfigCode , ohc.overHeadCode , oh.subcode , sg.name As acName , ohc.atRate AS configAtRate , oh.atRate , oh.amount , /*oh.actualFormula*/ '' AS actualFormula , /*oh.excludeFormula*/ '' AS excludeFormula , /*oh.overheadAlias*/ '' AS overheadAlias , ohc.contraOhMCode , ohc.drCr , ohc.nature , ohc.sNo , ohc.atRateSaveIn , ohc.amountSaveIn , ohc.tableName , ohc.isatRateEditable , ohc.isamountEditable , ohc.isacEditable , ohc.isshowFormulaValue , ohc.roundOff , ohc.ohSetUp , ohc.UniqueId , ohm.name ohName , ohm.manualCode AS ohManualCode , ohc.formula , ohc.isamountMandatory , ohc.isatRateMandatory , ohc.isoverHeadAliasEditable , ohc.isPrintable , ohc.isDisplay , ohc.calLValOn , ohc.isMultiContraText FROM {…} oh LEFT JOIN overHeadConfig ohc ON ohc.code = oh.ohConfigCode AND ohc.overHeadCode = oh.overHeadCode LEFT JOIN overHeadMast ohm ON ohm.Code = ohc.overHeadCode LEFT JOIN subGroup sg ON sg.subCode = oh.subcode WHERE oh.docId = :docId ORDER BY oh.sNo`
- Params: `:siteCode` ← `siteCode`; `:vType` ← `vType`; `:docId` ← `docId`; `:docId` ← `docId`; `:v_SNo` ← `rsTemp.getString("sNo")`

### `delete`
`public static void delete(String docId, Connection cnTemp)`


### `delete`
`public static void delete(String docId, String overHeadTableName, String overHead_MA_TableName, String ledgerTableName, Connection cnTemp)`

- SQL 1: `delete from {…} where docId = :docId;`
- SQL 2: `delete from {…} where docId = :docId;`
- SQL 3: `delete from {…} where docId = :docId;`
- SQL 4: `delete from overheadMATrans where docId = :docId;`
- SQL 5: `delete from overheadTrans where docId = :docId;`
- Params: `:docId` ← `docId`; `:docId` ← `docId`; `:docId` ← `docId`; `:docId` ← `docId`; `:docId` ← `docId`

### `save`
`public static void save(List<OverHead_Mdl> overHeadList, String docId, RecId recId, String vType, String vPrefix, String siteCode, String vDate, String commonNarration, String refNo, String refDate, String dueDate, String chequeNo, String chequeDate, boolean isPosted, boolean isPostingNeeded, Connection cnTemp)`


### `save`
`public static void save(List<OverHead_Mdl> overHeadList, String docId, RecId recId, String vType, String vPrefix, String siteCode, String vDate, String commonNarration, String refNo, String refDate, String dueDate, String chequeNo, String chequeDate, boolean isPosted, boolean isPostingNeeded, Connection cnTemp, String overHeadTableName, String overHead_MA_TableName, String affectedTableName)`


### `save`
`public static void save(List<OverHead_Mdl> overHeadList, String docId, RecId recId, String vType, String vPrefix, String siteCode, String vDate, String commonNarration, String refNo, String refDate, String dueDate, String chequeNo, String chequeDate, boolean isPosted, boolean isPostingNeeded, Connection cnTemp, String overHeadTableName, String overHead_MA_TableName, String affectedTableName, String ledgerTableName)`

- SQL 1: `INSERT INTO {…} (docId, sNo, overHeadCode, ohConfigCode, subCode, atRate, amount) VALUES(:docId, :sNo, :overHeadCode, :ohConfigCode, :subCode, :atRate, :amount)`
- SQL 2: `INSERT INTO {…} (docId, v_SNo, sNo, subCode, amount) VALUES (:docId, :v_SNo, :sNo, :subCode, :amount);`
- SQL 3: `UPDATE {…} SET`
- SQL 4: `Insert Into {…} (DocId,Site_Code,V_No,RecIdPrefix,RecId,V_Sno,Narration,V_Type,V_Prefix, SubCode,ContraSub,ContraText,AmtDr,AmtCr,V_Date,RefNo,RefDate,Chq_No,Chq_Date,isPosted, dueDate) Values(:DocId,:Site_Code,:V_No,:RecIdPrefix,:RecId,:V_Sno,:Narration,:V_Type,:V_Prefix, :SubCode,:ContraSub,:ContraText,:AmtDr,:AmtCr,:V_Date,:RefNo,:RefDate,:Chq_No,:Chq_Date,:isPosted, :dueDate)`
- Params: `:docId` ← `docId`; `:sNo` ← `++sNo1`; `:overHeadCode` ← `DmShared.xNull(overHead_Mdl.getSharedKeyFieldCode())`; `:ohConfigCode` ← `DmShared.xNull(overHead_Mdl.getOverHeadConfigCode())`; `:subCode` ← `DmShared.foriegnKey(overHead_Mdl.getAccountCode())`; `:atRate` ← `DmShared.foriegnKey(overHead_Mdl.getAtRate())`; `:amount` ← `DmShared.foriegnKey(overHead_Mdl.getAmount())`; `:docId` ← `docId`; `:v_SNo` ← `sNo1`; `:sNo` ← `++sNo2`; `:subCode` ← `overHeadMA_Mdl.getSharedKeyFieldCode()`; `:amount` ← `DmShared.foriegnKey(overHeadMA_Mdl.getAmount())`; `:DocId` ← `docId`; `:Site_Code` ← `siteCode`; `:RecId` ← `recId.getRecIdValue()`; `:recIdPrefix` ← `DmShared.xNull(recId.getPrefix())`; `:V_No` ← `DmShared.iNull(recId.getCounter()).longValue()`; `:v_SNo` ← `++vSNo`; `:Narration` ← `DmShared.foriegnKey(commonNarration)`; `:V_Type` ← `vType`; `:V_Prefix` ← `vPrefix`; `:SubCode` ← `ohPost_Mdl.getSharedKeyFieldCode()`; `:ContraSub` ← `DmShared.foriegnKey(ohPost_Mdl.getContraCode())`; `:ContraText` ← `DmShared.foriegnKey(contraText)`; `:AmtDr` ← `DmShared.foriegnKey(debit)`; `:AmtCr` ← `DmShared.foriegnKey(credit)`; `:V_Date` ← `DmServer.convertDate(vDate)`; `:RefNo` ← `DmShared.foriegnKey(refNo)`; `:RefDate` ← `DmServer.convertDate(refDate)`; `:Chq_No` ← `DmShared.foriegnKey(chequeNo)`; `:Chq_Date` ← `DmServer.convertDate(chequeDate)`; `:isPosted` ← `isPosted ? "1" : "0"`; `:dueDate` ← `DmServer.convertDate(DmShared.dNull(dueDate))`
- Errors: "Invalid key field." | "Invalid 'Account Posting'!"
- Other calls: `Collectors.toList`, `OverHead_Shared.getOHByManualCode`, `OverHead_Shared.getOHByUniqueId`, `Stream.empty`, `Stream.of`

### `prepareSQLLedger`
`private static void prepareSQLLedger(List<OverHeadPost_Mdl> overHeadPostList, OverHead_Mdl overHead, String contraAc, String contraText, String acCode, String amount)`


### `getOHHeadAccounts`
`public static List<OHHeadAccount_Mdl> getOHHeadAccounts(String siteCode, String v_Type, String orTransactionTypeCode, Connection cnTemp)`

- SQL 1: `SELECT ttpa.overHeadCode, ohm.name AS overHeadName, ohm.manualCode , ttpa.acCode, sg.name AS acName FROM transType tt Left JOIN transType_VoucherDetail ttvd ON ttvd.code = tt.code LEFT JOIN transType_PostingAcDetail ttpa ON ttpa.code = tt.code LEFT JOIN subGroup sg ON sg.subCode = ttpa.acCode LEFT JOIN overHeadMast ohm ON ohm.code = ttpa.overHeadCode WHERE 1 = 1 {…}`
- Params: `:site_Code` ← `siteCode`; `:v_Type` ← `v_Type`; `:code` ← `orTransactionTypeCode`

### `isValidDrCrAmount`
`public static boolean isValidDrCrAmount(List<OverHead_Mdl> overHeadList)`


### `calculateDrCrAmount`
`public static DrCrAmount_Mdl calculateDrCrAmount(List<OverHead_Mdl> overHeadList)`


## Server `OverHead_SrvImpl.java` (97 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.xNull`

### `getTxtHPostingAC`
`public DmResultPageing<List<AccountHelp_Mdl>> getTxtHPostingAC(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SubCode,Name,ManualCode From Subgroup`

### `getOverHead`
`public DmResult<List<OverHead_Mdl>> getOverHead(String vType)`

- Other calls: `OverHead_Server.getOverHeadConfig`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
