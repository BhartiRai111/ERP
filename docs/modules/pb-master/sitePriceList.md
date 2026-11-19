# pb-master / sitePriceList

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/sitePriceList/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: siteMast, sm
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Site (Text); Price List (Text)
- Lookups/suggestions: getTxtHSite, getTxtHPriceList
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SitePriceList`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site | DmTextHelp | `txtHSite` | `TxtHSite_PrpAcs, 7, true` |
| 2 | Price List | DmTextHelp | `txtHPriceList` | `RateList_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w300, name]; Manual Code [Text, w100, manualCode]
- `txtHPriceList`: Name [Text, w230, name]; Type [Text, w50, priceTypeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSite` → `getTxtHSite`
- `txtHPriceList` → `getTxtHPriceList`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHSite.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHSite.getText())
- `PriceListTag` ← DmShared.xNull(txtHPriceList.getTag())
- `PriceListText` ← DmShared.xNull(txtHPriceList.getText())

**Load (record opened): widget ← model** (`moveRecSuccess`):
- `txtHSite.setTag` ← DmShared.xNull(data.getSharedKeyFieldCode())
- `txtHSite.setTag2nd` ← DmShared.xNull(data.getSharedKeyFieldCode())
- `txtHSite.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtHPriceList.setTag` ← DmShared.xNull(data.getPriceListTag())
- `txtHPriceList.setTag2nd` ← DmShared.xNull(data.getPriceListTag())
- `txtHPriceList.setText` ← DmShared.xNull(data.getPriceListText())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSite.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flag = false;
if (!DmShared.xNull(txtHSite.getTag2nd()).equalsIgnoreCase(txtHSite.getTag())) {
resetSite();
txtHSite.setTag2nd(txtHSite.getTag());
flag = true;
if (blnStatus && t1Row != null) {
if (flag) {
moveRec(DmShared.xNull(txtHSite.getTag()));
```

`txtHPriceList.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPriceList(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Pric
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPriceList).show();
public void onSuccess(DmResultPageing<List<PriceList_Mdl>> result) {
if (result.isStatus()) {
txtHPriceList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPriceList).show();
```

`txtHPriceList.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPriceList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPriceList).show();
```

`txtHPriceList.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHPriceList.getTag2nd()).equalsIgnoreCase(txtHPriceList.getTag())) {
txtHPriceList.setTag2nd(txtHPriceList.getTag());
if (blnStatus && tRow != null) {
if (!DmShared.xNull(txtHPriceList.getTag()).trim().equalsIgnoreCase("")) {
topControlSave();
```
- Confirm: "Are you sure? You want to save this record."
- Confirm: "Are you sure? You want to close."
- Confirm: "Are you sure? You want to delete site price list."
- Alert: "Please select site."
- Alert: "No pricelist is linked to selected site."

## Files
| file | lines |
|---|---|
| client/SitePriceList.java | 325 |
| client/SitePriceList_Dsg.java | 194 |
| server/SitePriceList_SrvImpl.java | 412 |
| shared/DefaultValue_Mdl.java | 49 |
| shared/Find_Mdl.java | 68 |
| shared/PriceList_Mdl.java | 70 |
| shared/SitePriceList_Mdl.java | 45 |
| shared/SitePriceList_Srv.java | 20 |
| shared/SitePriceList_SrvAsync.java | 20 |
| shared/SitePriceList_Vld.java | 14 |

## UI labels / columns (client)
`Site`, `Price List`

## Models (shared DTOs)
### PriceList_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListTypeTag |
| String | priceListTypeName |
| String | applicableOn |
| String | applicableOnName |
| String | isSiteSpecificCode |
| String | isSiteSpecificName |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | currentDate |
| String | currentTime |
| String | voucherType |
| String | voucherTypeTag |

### SitePriceList_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListTag |
| String | priceListText |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | indentType |
| String | department |
| String | indentor |
| String | requisition |
| String | indentDate |
| String | approval |

## RPC interface
| method | params | returns |
|---|---|---|
| getMoveRec | String siteCode | DmResult<SitePriceList_Mdl> |
| delete | String siteCode | DmResult<String> |
| saveRecord | SitePriceList_Mdl prevMrtemp, SitePriceList_Mdl mrTemp | DmResult<String> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPriceList | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<PriceList_Mdl>> |

## Validator `SitePriceList_Vld`
```java
public class SitePriceList_Vld {
    public static void chkSave(SitePriceList_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldCode(), "Site", 100);
        DmShared.isValidText(mrTemp.getPriceListTag(), "Price List", 200);
    }
}
```

## Server `SitePriceList_SrvImpl.java` (412 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.xNull`

### `getMoveRec`
`public DmResult<SitePriceList_Mdl> getMoveRec(String site_Code)`


### `delete`
`public DmResult<String> delete(String site_Code)`

- SQL 1: `UPDATE siteMast SET priceListCode = :priceListCode WHERE code = :code`
- Params: `:priceListCode` ← `DmShared.foriegnKey("")`; `:code` ← `DmShared.xNull(site_Code)`

### `getMoveRec`
`public SitePriceList_Mdl getMoveRec(String site_Code, Connection cnTemp) throws Exception`

- SQL 1: `SELECT priceListCode, name from siteMast WHERE code = :site_Code`
- SQL 2: `SELECT plm.code as priceListCode,plm.name as priceListName , plm.applicableOn,plm.priceListType, plm.isSiteSpecific FROM priceListMast plm where plm.code = :priceListCode AND plm.applicableOn = :applicableOn`
- Params: `:site_Code` ← `site_Code`; `:applicableOn` ← `STEnum.PriceListApplicableOn.SALES.getId()`; `:priceListCode` ← `DmShared.xNull(activePriceListCode)`
- Other calls: `SALES.getId`

### `saveRecord`
`public DmResult<String> saveRecord(SitePriceList_Mdl prevMrtemp, SitePriceList_Mdl mrTemp)`

- SQL 1: `UPDATE sm SET sm.priceListCode = :priceListCode, sm.u_EntDt = :uEntDt , sm.u_name = :u_name FROM siteMast sm WHERE sm.code = :site_Code`
- Params: `:site_Code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:priceListCode` ← `DmShared.foriegnKey(mrTemp.getPriceListTag())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`
- Other calls: `SitePriceList_Vld.chkSave`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name ,sm.manualCode FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHPriceList`
`public DmResultPageing<List<PriceList_Mdl>> getTxtHPriceList(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT plm.code, plm.name , plm.priceListType as priceListTypeTag , CASE WHEN plm.priceListType = :BFCODE THEN :BFNAME WHEN plm.priceListType = :GSMCODE THEN :GSMNAME WHEN plm.priceListType = :BOTHCODE THEN :BOTHNAME WHEN plm.priceListType = :NONECODE THEN :NONENAME ELSE '' END AS priceTypeName , plm.applicableOn as applicableOnTag , CASE WHEN plm.applicableOn = :PURCHASECODE THEN :PURCHASENAME WHEN plm.applicableOn = :SALECODE THEN :SALENAME ELSE '' END AS applicableOn ,plm.isSiteSpecific AS isSiteSpecificCode , CASE WHEN plm.isSiteSpecific = 0 THEN 'No' WHEN plm.isSiteSpecific = 1 THEN 'Yes' ELSE '' END AS isSiteSpecific FROM priceListMast plm LEFT JOIN subGroup sg on sg.subCode = plm.subCode WHERE applicableOn = :SALECODE`
- Params: `:BFNAME` ← `STEnum.PriceListType.BF.getValue()`; `:GSMNAME` ← `STEnum.PriceListType.GSM.getValue()`; `:BOTHNAME` ← `STEnum.PriceListType.BOTH.getValue()`; `:NONENAME` ← `STEnum.PriceListType.NONE.getValue()`; `:PURCHASENAME` ← `STEnum.PriceListApplicableOn.PURCHASE.getValue()`; `:SALENAME` ← `STEnum.PriceListApplicableOn.SALES.getValue()`
- Other calls: `BF.getId`, `BF.getValue`, `BOTH.getId`, `BOTH.getValue`, `GSM.getId`, `GSM.getValue`, `NONE.getId`, `NONE.getValue`, `PURCHASE.getId`, `PURCHASE.getValue`, `SALES.getId`, `SALES.getValue`

### `saveSitePriceListLog`
`private void saveSitePriceListLog(String keyCode, SitePriceList_Mdl prevRecord, SitePriceList_Mdl newRecord, DmEnum.DmTransactionMode transactionMode)`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
