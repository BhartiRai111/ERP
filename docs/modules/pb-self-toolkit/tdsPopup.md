# pb-self-toolkit / tdsPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/tdsPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: tdsTrans
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHTdsFor, getTxtHTdsCategory, getTxtHTdsDeduct
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `TdsPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Category | DmTextHelp | `txtHTdsCategory` | `KeyField_PrpAcs, 7, true` |
| 2 | On Amount | DmTextBoxNumeric | `txtTdsOnAmount` | `2, false, 15, true` |
| 3 | Deduct Form | DmTextHelp | `txtHTdsDeduct` | `TxtHTdsDeduct, 7, true` |
| 4 | Type | DmTextBox | `txtTdsTcsType` | `20, false` |

Other widgets (not in tab list): `txtVDate` (DmTextBox), `tdsCategoryGrid` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHTdsCategory`: Name [Text, w1, Name]
- `txtHTdsDeduct`: Name [Text, w1, Name]; City [Text, w100, CityName]; Code [Text, w100, ManualCode]
- `tdsCategoryGrid`: Description [Text, w1]; Posting A/c [Text, w200]; (%) [Text, w80]; Amount [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTdsCategory` → `getTxtHTdsCategory`
- `txtHTdsDeduct` → `getTxtHTdsDeduct`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTdsCategory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTdsCategory(txtTdsTcsType.getTag(), helper, blnToEndOfFile, new AsyncCallback
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTdsCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHTdsCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTdsCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHTdsCategory.addTextHelpCallBack` → `getSelectedRow()`
```java
fillTdsGridData(txtHTdsCategory.getTag(), txtVDate.getText());
if (blnStatus && tRow != null) {
```

`txtHTdsDeduct.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTdsDeduct(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHTdsDeductFrom_Mdl>> result) {
if (result.isStatus()) {
txtHTdsDeduct.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHTdsDeduct.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTdsDeduct.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHTdsDeduct.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
lblLedgerBalance.setText("");
lblFinYrCredit.setText("");
lblFinYrDebit.setText("");
lblLedgerTcs.setText("");
lblLedgerTds.setText("");
getLedgerBalance(String.valueOf(txtHTdsDeduct.getTag()));
```

`txtTdsOnAmount.addBlurHandler` → `onBlur()`
```java
calculate();
```

## Files
| file | lines |
|---|---|
| client/TdsPopup.java | 510 |
| client/TdsPopup_Dsg.java | 351 |
| server/TdsPopup_Server.java | 224 |
| server/TdsPopup_SrvImpl.java | 295 |
| shared/AcBalanceTds_Mdl.java | 76 |
| shared/GrdTdsDetail_Mdl.java | 131 |
| shared/TDSPopup_Mdl.java | 223 |
| shared/TdsPopup_Srv.java | 20 |
| shared/TdsPopup_SrvAsync.java | 20 |
| shared/TxtHTdsDeductFrom_Mdl.java | 32 |

## UI labels / columns (client)
`Dr 0.00`, `F.Y Dr. 0.00`, `F.Y Cr. 0.00`, `TCS Amt. 0.00`, `TDS Amt. 0.00`, `Category`, `On Amount`, `Deduct Form`, `Total`, `Type`

## Models (shared DTOs)
### TDSPopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | tdsOnAmount |
| String | tdsDeductFromCode |
| String | tdsDeductFromName |
| String | tdsTcsTypeCode |
| String | tdsForCode |
| String | totalAmount |
| String | tan |
| String | pan |
| String | vr_VSno |
| String | refNo |
| String | refDate |
| String | v_prefix |
| String | v_Type |
| String | v_Date |
| String | vr_DocId |
| String | vr_SubCode |
| String | docId |
| List<GrdTdsDetail_Mdl> | tdsGridList |

### AcBalanceTds_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | balance |
| String | financialYrCr |
| String | financialYrDr |
| String | accountNature |
| String | tempBalance |
| String | finalTDS |
| String | finalTCS |

### TxtHTdsDeductFrom_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | city |
| String | sCode |

### GrdTdsDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | descriptionCode |
| String | descriptionMCode |
| String | postingAccount |
| String | postingAccountCode |
| String | percent |
| String | amount |
| String | formula |
| String | roundOffTDS |
| String | vSNo |
| String | calcOnValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHTdsFor | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTdsCategory | String tdsFor, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTDSData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getTxtHTdsDeduct | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTdsDeductFrom_Mdl>> |
| getGetLedgerBalance | String StrSubCode, String docId | DmResult<AcBalanceTds_Mdl> |

## Server `TdsPopup_SrvImpl.java` (295 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnVoucherPrefix`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHTdsFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTdsFor(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TDS_For.values`

### `getTxtHTdsCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTdsCategory(String tdsFor, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select tc.code, tc.name From tdsCat tc WHERE tc.type = :tdsFor`
- Params: `:tdsFor` ← `DmShared.xNull(tdsFor)`

### `getTDSData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTDSData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTDSData`

### `getTxtHTdsDeduct`
`public DmResultPageing<List<TxtHTdsDeductFrom_Mdl>> getTxtHTdsDeduct(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode, SG.Name, isNull(CT.CityName,'') as CityName, SG.ManualCode From SubGroup SG Left Join City CT On CT.CityCode=SG.CityCode LEFT JOIN subgroupsitelist SGL ON SG.subCode=SGL.subCode Where SGL.site_Code=' {…} '`

### `getGetLedgerBalance`
`public DmResult<AcBalanceTds_Mdl> getGetLedgerBalance(String StrSubCode, String docId)`

- SQL 1: `Select (isNull(Sum(LG.AmtDr),0) - isNull(Sum(LG.AmtCr),0)) As Balance From Ledger LG Where LG.SubCode = :SubCode and LG.Site_Code = :Site_Code and LG.docId <> :docId`
- SQL 2: `Select isNull(Sum(LG.AmtDr),0) as finDr ,isNull(Sum(LG.AmtCr),0) As finCr , max(sgn.name) as acNature From Ledger LG left join subgroup sg on sg.subcode=lg.subcode left join subGroupNature sgn on sgn.code=sg.nature Where LG.SubCode = :SubCode and LG.Site_Code = :Site_Code and LG.v_Prefix = :v_Prefix and LG.docId <> :docId`
- SQL 3: `SELECT SUM(CASE WHEN tt.tdsFor = 'P' THEN ISNULL(tt.amount,0) ELSE 0 END) AS tdsAmt , SUM(CASE WHEN tt.tdsFor = 'R' THEN ISNULL(tt.amount,0) ELSE 0 END) AS tcsAmt FROM tdsTrans tt WHERE tt.vr_SubCode = :SubCode AND tt.v_prefix = :v_Prefix and tt.docId <> :docId`
- Params: `:SubCode` ← `DmShared.xNull(StrSubCode)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:docId` ← `DmShared.iNull(docId).toString()`; `:SubCode` ← `DmShared.iNull(StrSubCode).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:docId` ← `DmShared.iNull(docId).toString()`; `:SubCode` ← `DmShared.iNull(StrSubCode).toString()`; `:v_Prefix` ← `DmServer.getSsnVoucherPrefix(this)`; `:docId` ← `DmShared.iNull(docId).toString()`

## Server `TdsPopup_Server.java` (224 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getServerTimestamp`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getTDSData`
`public static List<GrdTdsDetail_Mdl> getTDSData(String tdsCategoryCode, String date, Connection cnTemp, DmRSSPrivate rss) throws Exception`

- SQL 1: `SELECT ec.ac_RoundOffTDS FROM enviroCompany ec`
- SQL 2: `Select TC.TDSDesc,tcd.manualCode as tcdManualCode,TCD.Name As DName,TC.ACCode, SG.Name As AName,TC.Percentage ,TC.FormulaString From TDSCat_Det TC Left Join TDSCat_Description TCD On TC.TDSDesc=TCD.Code Left Join SubGroup SG On TC.AcCode=SG.SubCode Where TC.Code=:code {…} Order By TC.SrNo`
- Params: `:code` ← `DmShared.iNull(tdsCategoryCode).toString()`; `:one` ← `1`; `:two` ← `2`; `:date` ← `DmServer.convertDate(date)`

### `saveTdsTrans`
`public static void saveTdsTrans(TDSPopup_Mdl tpm, Connection cnTemp, DmRSSPrivate rss) throws Exception`

- SQL 1: `Insert Into tdsTrans (docId, recId, recIdPrefix , v_No, v_SNo , v_Date, v_Prefix , v_Type, site_Code , pan, tan , subCode , type, tdsFor , tdsCategory, tdsOnAmt ,tdsDeductFrom , tdsDesc ,tdsPer , formulaString,roundOffTds , amount, calcOnValue, u_Name,u_EntDt, refNo , refDate , vr_DocId, vr_Vsno, vr_SubCode , isStatus ) values ( :docId,:recId, :recIdPrefix , :v_No, :v_SNo,:v_Date, :v_Prefix,:v_Type,:site_Code , :pan , :tan , :subCode , :type, :tdsFor , :tdsCategory ,:tdsOnAmt , :tdsDeductFrom,:tdsDesc, :tdsPer , :formulaString , :roundOffTds , :amount, :calcOnValue, :u_Name,:u_EntDt, :refNo,:refDate, :vr_DocId, :vr_Vsno, :vr_SubCode , :isStatus )`
- Params: `:docId` ← `DmShared.foriegnKey(tpm.getDocId())`; `:v_SNo` ← `DmShared.foriegnKey(DmShared.iNull(++vSno).toString())`; `:v_No` ← `DmShared.iNull(tpm.getRecId().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(tpm.getRecId().getPrefix()))`; `:recId` ← `DmShared.xNull(tpm.getRecId().getRecIdValue())`; `:v_Prefix` ← `DmShared.xNull(tpm.getV_prefix())`; `:v_Type` ← `DmShared.foriegnKey(tpm.getV_Type())`; `:v_Date` ← `DmServer.convertDate(DmShared.dNull(tpm.getV_Date()))`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`; `:subCode` ← `DmShared.foriegnKey(tdsDet_Mdl.getPostingAccountCode())`; `:tan` ← `DmShared.xNull(tpm.getTan())`; `:pan` ← `DmShared.xNull(tpm.getPan())`; `:tdsFor` ← `DmShared.foriegnKey(tpm.getTdsForCode())`; `:type` ← `DmShared.foriegnKey(tpm.getTdsTcsTypeCode())`; `:tdsCategory` ← `DmShared.foriegnKey(tpm.getSharedKeyFieldCode())`; `:tdsDeductFrom` ← `DmShared.foriegnKey(tpm.getTdsDeductFromCode())`; `:tdsDesc` ← `DmShared.foriegnKey(tdsDet_Mdl.getDescriptionCode())`; `:tdsPer` ← `DmServer.vNull(tdsDet_Mdl.getPercent())`; `:formulaString` ← `DmShared.xNull(tdsDet_Mdl.getFormula())`; `:roundOffTds` ← `DmShared.xNull(tdsDet_Mdl.getRoundOffTDS())`; `:u_Name` ← `DmServer.getSsnUserName(rss)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:refNo` ← `DmShared.xNull(tpm.getRefNo())`; `:refDate` ← `DmServer.convertDate(DmShared.dNull(tpm.getRefDate()))`; `:vr_DocId` ← `DmShared.foriegnKey(tpm.getVr_DocId())`; `:vr_Vsno` ← `DmShared.foriegnKey(tpm.getVr_VSno())`; `:vr_SubCode` ← `DmShared.foriegnKey(tpm.getVr_SubCode())`; `:isStatus` ← `1`; `:tdsOnAmt` ← `DmServer.vNull(tpm.getTdsOnAmount())`; `:amount` ← `DmServer.vNull(tdsDet_Mdl.getAmount())`; `:calcOnValue` ← `DmServer.vNull(tdsDet_Mdl.getCalcOnValue())`
- Errors: "Error in TDS record saving."

### `deleteTdsTrans`
`public static void deleteTdsTrans(String docid, Connection cnTemp) throws Exception`

- SQL 1: `Delete From TdsTrans Where DocId= :DocId`
- Params: `:DocId` ← `DmShared.xNull(docid)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.overHeadLine.shared`, `paperbiz.selfToolkit.tdsPopup.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
TDS entry popup (voucher ke andar): `getTxtHTdsFor`, `getTxtHTdsCategory`, `getTxtHTdsDeduct`, `getGetLedgerBalance` (party ledger balance), `getTDSData`; `saveTdsTrans` / `deleteTdsTrans` -> `tdsTrans`. Hisaab ke helpers `dm-toolkit/dmStatic` ke TDS/TCS methods me.
