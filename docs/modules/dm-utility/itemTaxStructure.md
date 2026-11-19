# dm-utility / itemTaxStructure

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/itemTaxStructure/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: its, itsDetail
- Update tables: its, itsDetail
- Delete cascade (in order): itsDetail, its
- Duplicate checks on save:
  - `Select Count(*) As Cnt From its Where LOWER(name) =:name AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: ITS Name (Text); Location Name (Text); Tax Type (Text); rule: Atleast one ITS should be define.; rule: Location,Overhead,site and applicable from combination already exist.
- Lookups/suggestions: getTxtHLocation, getTxtHCopyFromITS, getTxtHGstType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ExportData`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdUserRole` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdUserRole`:  [Text, w25, v_Type]; Description [Text, w100, description]
- Confirm: "Are you sure? You want to export selected records."
- Alert: "Please select any ITS to export."

### Screen `ItemTaxStructureGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Overhead | DmTextHelp | `txtHOverHead` | `KeyField_PrpAcs, 3, true` |
| 2 | Tax % | DmTextBoxNumeric | `txtAtRate` | `DmTextBoxNumeric.DecimalNature.DEFAULT, false, 5, false` |
| 3 | Account Affected | DmTextHelp | `txtHAcntAffected` | `KeyField_PrpAcs, 8, false` |

**Grid / popup columns** (header, width, db field):
- `txtHAcntAffected`: Name [Text, w0, Name]
- `txtHOverHead`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHAcntAffected` → `getAccountAffected`
- `txtHOverHead` → `getOverhead`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHAcntAffected.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getAccountAffected(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAcntAffected).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAcntAffected.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAcntAffected).show();
```

`txtHAcntAffected.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAcntAffected.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAcntAffected).show();
```

`txtHOverHead.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getOverhead(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOverHead).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOverHead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOverHead).show();
```

`txtHOverHead.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOverHead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOverHead).show();
```
- Alert: "Overhead is mandatory."
- Alert: "ITS detail already exist."

### Screen `ItemTaxStructure`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | ITS Name | DmTextBox | `txtITSName` | `50, true` |
| 2 | Location | DmTextHelp | `txtHLocation` | `KeyField_PrpAcs, 5, true` |
| 3 | Tax Type | DmTextHelp | `txtHTaxType` | `KeyField_PrpAcs, 5, true, true` |
| 4 | Site Name | DmTextHelp | `txtHSite` | `KeyField_PrpAcs, 3, false` |

Other widgets (not in tab list): `grdITS` (DmGrid), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `grdITS`: Overhead [Text, w170]; Tax % [Text, w100]; Account Affected [Text, w160]
- `txtHLocation`: Name [Text, w120, Name]
- `txtHSite`: Name [Text, w120, Name]
- `txtHTaxType`: Type [Text, w120, name]
- `dmFind`: ITS Name [Text, w0, NAME]; Site Name [Text, w250, siteName]; Location Name [Text, w150, locationName]; Tax Type [Text, w80, taxTypeName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHLocation` → `getTxtHLocation`
- `txtHSite` → `getSite`
- `txtHTaxType` → `getTxtHGstType`
- `grdEdit` → `fillGrid`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← txtITSName.getTag()
- `SharedKeyFieldName` ← txtITSName.getText()
- `LocationCode` ← txtHLocation.getTag()
- `LocationName` ← txtHLocation.getText()
- `SiteCode` ← txtHSite.getTag()
- `SiteName` ← txtHSite.getText()
- `TaxTypeCode` ← txtHTaxType.getTag()
- `TaxTypeName` ← txtHTaxType.getText()

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtITSName.setTag` ← data.getSharedKeyFieldCode()
- `txtITSName.setText` ← data.getSharedKeyFieldName()
- `txtHLocation.setTag` ← data.getLocationCode()
- `txtHLocation.setText` ← data.getLocationName()
- `txtHSite.setTag` ← data.getSiteCode()
- `txtHSite.setText` ← data.getSiteName()
- `txtHTaxType.setTag` ← data.getTaxTypeCode()
- `txtHTaxType.setText` ← data.getTaxTypeName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtITSName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtITSName.setFocus(true);
grdITS.getGrid().getStore().clear();
grdITS.getGrid().getStore().add(new GrdItemTaxStructure_Mdl());
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtITSName.setFocus(true);
grdITS.getGrid().getStore().add(new GrdItemTaxStructure_Mdl());
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemTaxStructure.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`txtHLocation.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHLocation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLocation).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLocation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocation).show();
```

`txtHLocation.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLocation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLocation).show();
```

`txtHSite.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExten
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

`txtHTaxType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGstType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTaxType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTaxType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTaxType).show();
```

`txtHTaxType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTaxType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTaxType).show();
```

`txtHTaxType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus & t1Row != null) {
```

`grdEdit.addCallBack` → `onOk()`
```java
grdITS.commitChanges();
```

`grdEdit.addCallBack` → `onDelete()`
```java
grdITS.commitChanges();
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

**Menu items (Other button):** Export ITS

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Please fill 'ITS Name'!"
- Alert: "Please select 'Location Name'!"
- Alert: "Please select 'Tax Type'!"

## Files
| file | lines |
|---|---|
| client/ExportData.java | 222 |
| client/ExportData_Dsg.java | 123 |
| client/ItemTaxStructure.java | 527 |
| client/ItemTaxStructureGridEdit.java | 181 |
| client/ItemTaxStructureGridEdit_Dsg.java | 103 |
| client/ItemTaxStructure_Dsg.java | 254 |
| server/ItemTaxStructure_SrvImpl.java | 1086 |
| shared/Find_Mdl.java | 68 |
| shared/GrdExport_Mdl.java | 22 |
| shared/GrdItemTaxStructure_Mdl.java | 60 |
| shared/ItemTaxStructure_Mdl.java | 74 |
| shared/ItemTaxStructure_Srv.java | 44 |
| shared/ItemTaxStructure_SrvAsync.java | 44 |
| shared/ItemTaxStructure_Vld.java | 44 |

## UI labels / columns (client)
`ITS Name`, `Location`, `Tax Type`, `Site Name`, `Overhead`, `Tax %`, `Account Affected`

## Models (shared DTOs)
### ItemTaxStructure_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | siteCode |
| String | siteName |
| String | locationCode |
| String | locationName |
| String | taxTypeCode |
| String | taxTypeName |
| List<GrdItemTaxStructure_Mdl> | grdItemTaxStructure |

### GrdExport_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| boolean | checked |

### GrdItemTaxStructure_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | overHeadCode |
| String | overHeadName |
| String | affectedAcCode |
| String | affectedAcName |
| String | atRate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | siteCode |
| String | siteName |
| String | locationCode |
| String | locationName |
| String | taxTypeCode |
| String | taxTypeName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<ItemTaxStructure_Mdl> |
| saveRecord | ItemTaxStructure_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAccountAffected | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getOverhead | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLocation | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHCopyFromITS | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getMaxApplicableDate | String code | DmResult<KeyFieldExtended> |
| deleteRecordApplicable | String strKey, String applicableFromDate | DmResult<String> |
| fillGrid | String code, String applicableFromDate | DmResult<List<GrdItemTaxStructure_Mdl>> |
| fillGridApplicableDate | String code | DmResult<List<KeyFieldExtended>> |
| exportJson | String sessionId, List<String> lstITS | DmResult<KeyFieldExtended> |
| getITS |  | DmResult<List<GrdExport_Mdl>> |
| getTxtHGstType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ItemTaxStructure_Vld`
```java
public class ItemTaxStructure_Vld {
    public static void chkSave(ItemTaxStructure_Mdl mrTemp) {
        boolean isDataExists = false;
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "ITS Name", 100);
        DmShared.isValidText(mrTemp.getLocationCode(), "Location Name", 300);
        DmShared.isValidText(mrTemp.getTaxTypeCode(), "Tax Type", 400);
        if (mrTemp.getGrdItemTaxStructure().size() <= 0) {
            throw new DmException(200, "Atleast one ITS should be define.");
        }
        String code, overHeadCode;
        for (int i = 0; i < mrTemp.getGrdItemTaxStructure().size(); i++) {
            if (DmShared.iNull(mrTemp.getGrdItemTaxStructure().get(i).getSharedKeyFieldCode()).longValue() > 0) {
                GrdItemTaxStructure_Mdl voucherLink_Mdl = mrTemp.getGrdItemTaxStructure().get(i);
                code = DmShared.xNull(voucherLink_Mdl.getSharedKeyFieldCode());
                overHeadCode = DmShared.xNull(voucherLink_Mdl.getOverHeadCode());
                for (int j = i + 1; j < mrTemp.getGrdItemTaxStructure().size() - 1; j++) {
                    GrdItemTaxStructure_Mdl voucherLink_Mdl1 = mrTemp.getGrdItemTaxStructure().get(j);
                    if (DmShared.xNull(voucherLink_Mdl1.getOverHeadCode()).equalsIgnoreCase(overHeadCode)) {
                        if (DmShared.xNull(voucherLink_Mdl1.getSharedKeyFieldCode()).equalsIgnoreCase(code)) {
                            throw new DmException(200, "Location,Overhead,site and applicable from combination already exist.");
                        }
                    }
                }
                if (!isDataExists) {
                    isDataExists = true;
                }
            }
            if (!isDataExists) {
                throw new DmException(200, "Atleast one ITS should be define.");
            }
        }
    }
}
```

## Server `ItemTaxStructure_SrvImpl.java` (1086 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `select code from its order by name`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT its.code,its.name , its.locationCode,lm.name AS locationName , its.site_Code,sm.name AS siteName , its.gstTypeCode, gt.name AS gstTypeName FROM its LEFT JOIN locationMast lm ON lm.code = its.locationCode LEFT JOIN siteMast sm ON sm.code = its.site_Code LEFT JOIN gstType gt ON gt.code = its.gstTypeCode`

### `getMoveRec`
`public DmResult<ItemTaxStructure_Mdl> getMoveRec(String strKey)`


### `getMoveRecITS`
`private ItemTaxStructure_Mdl getMoveRecITS(String keyCode, Connection cnTemp)`

- SQL 1: `SELECT its.code,max(its.name) name,max(its.u_Name) u_Name,max(its.preparedBy) preparedBy , max(its.locationCode) AS locationCode, MAX(lm.name) AS locationName , max(its.site_Code ) AS site_Code, MAX(sm.name) AS siteName , MAX(its.gstTypeCode) AS gstTypeCode, MAX(gt.name) AS gstTypeName FROM its LEFT JOIN itsDetail itsd ON itsd.code = its.code LEFT JOIN locationMast lm on lm.code = its.locationCode LEFT JOIN siteMast sm on sm.code = its.site_Code LEFT JOIN gstType gt ON gt.code = its.gstTypeCode WHERE its.code = :code GROUP BY its.code ORDER BY name`
- Params: `:code` ← `keyCode`

### `saveRecord`
`public DmResult<String> saveRecord(ItemTaxStructure_Mdl mrTemp)`

- SQL 1: `Select Count(*) As Cnt From its Where LOWER(name) =:name AND code <> :code`
- SQL 2: `Insert Into its(code, name, locationCode, site_Code, gstTypeCode, U_EntDt, transferDt, preparedBy) Values (:code, :name, :locationCode, :site_Code, :gstTypeCode, :uEntDt, :transferDt, :preparedBy)`
- SQL 3: `Update its Set name = :name, locationCode = :locationCode, site_Code = :site_Code, gstTypeCode = :gstTypeCode, u_Name = :u_Name, transferDt = :transferDt, u_EntDt = :uEntDt Where code = :code`
- SQL 4: `Delete from itsDetail where code=:code`
- SQL 5: `Insert Into itsDetail(code,overHeadCode,atRate,subCode) Values (:code ,:overHeadCode,:atRate,:subCode)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName().trim())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:gstTypeCode` ← `DmShared.foriegnKey(mrTemp.getTaxTypeCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:transferDt` ← `null`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName().trim())`; `:locationCode` ← `DmShared.foriegnKey(mrTemp.getLocationCode())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:gstTypeCode` ← `DmShared.foriegnKey(mrTemp.getTaxTypeCode())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:transferDt` ← `null`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:overHeadCode` ← `DmShared.foriegnKey(itemTaxStructure_Mdl.getSharedKeyFieldCode())`; `:atRate` ← `DmServer.vNull(itemTaxStructure_Mdl.getAtRate(), "0.00")`; `:subCode` ← `DmShared.foriegnKey(itemTaxStructure_Mdl.getAffectedAcCode())`
- Errors: "Invalid key." | "ITS Name already exists."
- Other calls: `ItemTaxStructure_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String key)`

- SQL 1: `DELETE FROM itsDetail WHERE code= :code`
- SQL 2: `DELETE FROM its WHERE code= :code`
- Params: `:code` ← `key`; `:code` ← `key`

### `getSite`
`public DmResultPageing<List<KeyFieldExtended>> getSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name from siteMast`

### `getAccountAffected`
`public DmResultPageing<List<KeyFieldExtended>> getAccountAffected(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode AS code,sg.name from subGroup sg`

### `getTxtHLocation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT s.isUt FROM siteMast sm LEFT JOIN stateMast s on s.numericCode = sm.stateNumericCode WHERE sm.code = :siteCode`
- SQL 2: `SELECT lm.code, lm.name from locationMast lm WHERE lm.isActive = 1 AND lm.manualCode <> :manualCode`
- Params: `:siteCode` ← `DmServer.getSsnSiteCode(this)`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.INTRA_STATE.getManualCode()`; `:manualCode` ← `DmEnum.DmGstLocation_SystemDefined.UNION_TERRITORY.getManualCode()`
- Other calls: `INTRA_STATE.getManualCode`, `UNION_TERRITORY.getManualCode`

### `getOverhead`
`public DmResultPageing<List<KeyFieldExtended>> getOverhead(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name from overHeadMast`

### `getMaxApplicableDate`
`public DmResult<KeyFieldExtended> getMaxApplicableDate(String code)`

- SQL 1: `select top 1 its.code, max(itsd.applicableFrom) as applicableFrom, MAX(its.name) AS name FROM its LEFT JOIN itsDetail itsd ON itsd.code = its.code WHERE (its.code = :code) GROUP BY its.code, itsd.applicableFrom ORDER BY itsd.applicableFrom desc`
- Params: `:code` ← `code`

### `deleteRecordApplicable`
`public DmResult<String> deleteRecordApplicable(String strKey, String applicableDate)`

- SQL 1: `SELECT applicableFrom , applicableTo FROM itsDetail WHERE code= :code and applicableFrom= :applicableFrom`
- SQL 2: `SELECT code, applicableFrom, applicableTo FROM itsDetail WHERE code= :code AND applicableFrom < :applicableFrom ORDER BY applicableFrom DESC`
- SQL 3: `update itsDetail set applicableTo=:applicableTo where code = :code and ApplicableFrom = :ApplicableFrom`
- SQL 4: `delete from itsDetail where code=:code and applicableFrom= :applicableFrom`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:applicableFrom` ← `DmServer.convertDate(applicableDate)`; `:code` ← `DmShared.iNull(strKey).toString()`; `:ApplicableFrom` ← `DmServer.convertDate(applicableDate)`; `:applicableTo` ← `applicableToDate`; `:code` ← `DmShared.iNull(strKey).toString()`; `:ApplicableFrom` ← `rsTemp.getString("ApplicableFrom")`; `:code` ← `DmShared.iNull(strKey).toString()`; `:applicableFrom` ← `DmServer.convertDate(applicableDate)`
- Errors: "ApplicableFrom Date is Invalid"

### `fillGrid`
`public DmResult<List<GrdItemTaxStructure_Mdl>> fillGrid(String code, String applicableFromDate)`


### `fillITSGrid`
`private List<GrdItemTaxStructure_Mdl> fillITSGrid(String keyCode, Connection cnTemp)`

- SQL 1: `SELECT itsd.code,itsd.overHeadCode, itsd.atRate , itsd.subCode AS acCode, sg.name AS acName , ohm.name AS ohName FROM itsDetail AS itsd LEFT JOIN its AS its ON itsd.code=its.code LEFT JOIN overHeadMast AS ohm ON ohm.code=itsd.overHeadCode LEFT JOIN subGroup AS sg ON sg.subCode=itsd.subCode WHERE itsd.code = :code ORDER BY ohName`
- Params: `:code` ← `DmShared.iNull(keyCode).toString()`

### `fillGridApplicableDate`
`public DmResult<List<KeyFieldExtended>> fillGridApplicableDate(String code)`

- SQL 1: `SELECT MAX(code) AS code, applicableFrom, MAX(applicableTo) AS applicableTo FROM itsDetail WHERE (code = :code) GROUP BY applicableFrom ORDER BY applicableFrom`
- Params: `:code` ← `code`

### `getTxtHCopyFromITS`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCopyFromITS(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT its.code, MAX(its.name) AS name, id.applicableFrom FROM its INNER JOIN itsDetail id ON id.code = its.code GROUP BY its.code, id.applicableFrom`

### `exportJson`
`public DmResult<KeyFieldExtended> exportJson(String session, List<String> lstVoucherType)`

- Errors: "Invalid request." | "File Name not given."

### `getITS`
`public DmResult<List<GrdExport_Mdl>> getITS()`

- SQL 1: `select code, name from its order by name`

### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name, printSrNo FROM gstType Where isActive = '1'`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.itemTaxStructure.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("Cnt") > 0)` | "ITS Name already exists." |
| deleteRecordApplicable | `if (rsTemp.next())` | "ApplicableFrom Date is Invalid" |
| exportJson | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportJson | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
