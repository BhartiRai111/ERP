# dm-utility / transactionType

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/transactionType/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: transType, transType_ItemCatDetail, transType_PostingAcDetail, transType_VoucherDetail
- Update tables: transType
- Delete cascade (in order): transType_ItemCatDetail, transType_PostingAcDetail, transType_VoucherDetail, transType
- Duplicate checks on save:
  - `SELECT COUNT(tr.name) AS cnt FROM transType as tr WHERE LOWER(tr.name) = :name AND tr.code <> :code AND tr.site_Code = :site_Code`
  - `SELECT COUNT(tr.manualCode) AS cnt FROM transType as tr WHERE LOWER(tr.manualCode) = :manualCode AND tr.code <> :code AND tr.site_Code = :site_Code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Transaction Type (Text); Manual Code (Text); rule: GridRow
- Lookups/suggestions: getTxtHSite, getTxtHVoucherTye, getTxtHItemCat, getTxtHOverhead, getTxtHPostAC
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `OHGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | OverHead | DmTextHelp | `txtHOverhead` | `TxtHOverhead_PrpAcs, 7, true` |
| 2 | Posting AC | DmTextHelp | `txtHPostAC` | `TxtHPostAC_PrpAcs, 7` |

**Grid / popup columns** (header, width, db field):
- `txtHOverhead`: Name [Text, w0, NAME]; Manual Code [Text, w100, manualCode]
- `txtHPostAC`: Name [Text, w0, NAME]; Manual Code [Text, w100, groupCode]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHOverhead` → `getTxtHOverhead`
- `txtHPostAC` → `getTxtHPostAC`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHOverhead.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHOverhead(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHO
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHOverhead_Mdl>> result) {
if (result.isStatus()) {
txtHOverhead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHOverhead.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOverhead.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHOverhead.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
```

`txtHPostAC.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPostAC(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHPos
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<TxtHPostAC_Mdl>> result) {
if (result.isStatus()) {
txtHPostAC.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```

`txtHPostAC.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPostAC.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(DmShared.xNull(result.getErrorMessage())), (DmTextBox) null).show();
```
- Alert: "Overhead is already exists."

### Screen `TransactionType`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Transaction Type | DmTextBox | `txtTransactionType` | `100, true` |
| 2 | Manual Code | DmTextBox | `txtManualCode` | `5, true` |
| 3 | Site Name | DmTextHelp | `txtHSite` | `TxtHSite_PrpAcs, 7, false` |
| 4 | Site Manual Code | DmTextBox | `txtSiteManualCode` | `5, false` |
| 5 | Voucher Type | DmTextHelpMulti | `txtHVoucherType` | `TxtHVoucherType_PrpAcs, 7` |
| 6 | Item Catagory | DmTextHelpMulti | `txtHItemCat` | `TxtHItemCat_PrpAcs, 7` |

Other widgets (not in tab list): `dmFind` (DmFind), `grdVoucherType` (DmGrid), `grdItemCat` (DmGrid), `grdOhConfig` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Voucher Type [Text, w120, name]
- `txtHItemCat`: Item Catagory [Text, w120, name]
- `txtHSite`: Name [Text, w300, name]; Manual Code [Text, w100, manualCode]
- `dmFind`: Transaction Type [Text, w200, name]; Manuale Code [Text, w500, manualCode]; Site Name [Text, w200, siteName]; Site Manual Code [Text, w300, siteManualCode]
- `grdVoucherType`: Voucher Type [Text, w100]
- `grdItemCat`: Item Catagory [Text, w100]
- `grdOhConfig`: OverHead [Text, w100]; Ac Name [Text, w250]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSite` → `getTxtHSite`
- `oHGridEdit` → `fillVTypeGrid`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtTransactionType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtManualCode.getTag())
- `ManualCode` ← DmShared.xNull(txtManualCode.getText())
- `SiteCode` ← DmShared.xNull(txtHSite.getTag())
- `SiteName` ← DmShared.xNull(txtHSite.getText())
- `SiteManualCode` ← DmShared.xNull(txtSiteManualCode.getText())
- `ItemCatCode` ← DmShared.xNull(txtHItemCat.getTag())
- `ItemCatName` ← DmShared.xNull(txtHItemCat.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtTransactionType.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtManualCode.setText` ← DmShared.xNull(data.getManualCode())
- `txtHSite.setTag` ← DmShared.xNull(data.getSiteCode())
- `txtHSite.setText` ← DmShared.xNull(data.getSiteName())
- `txtSiteManualCode.setText` ← DmShared.xNull(data.getSiteManualCode())
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `onAdd()`

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
topControlDelete(DmShared.xNull(topControl.getDocId()));
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(DmShared.xNull(topControl.getDocId()));
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(TransactionType.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

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
if (!DmShared.xNull(txtHSite.getTag2nd()).equalsIgnoreCase(txtHSite.getTag())) {
txtHSite.setTag2nd(txtHSite.getTag());
if (blnStatus && t1Row != null) {
txtSiteManualCode.setText(DmShared.xNull(t1Row.getSharedPreparedBy()));
```

`txtHVoucherType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherTye(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Grd
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<GrdVoucherType_Mdl>> result) {
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
if (!DmShared.xNull(txtHVoucherType.getTag()).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag2nd()))) {
txtHVoucherType.setTag2nd(DmShared.xNull(txtHVoucherType.getTag()));
grdVoucherType.getGrid().getStore().clear();
grdVoucherType.getGrid().getStore().add(new GrdVoucherType_Mdl());
if (blnStatus && !DmShared.xNull(keyCode).trim().equalsIgnoreCase("")) {
txtHVoucherType.setTag2nd(keyCode);
fillVoucherType(keyCode);
```

`txtHItemCat.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemCat(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<GrdIte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCat).show();
public void onSuccess(DmResultPageing<List<GrdItemCat_Mdl>> result) {
if (result.isStatus()) {
txtHItemCat.getDmGrid().getGrid().getStore().clear();
txtHItemCat.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCat).show();
```

`txtHItemCat.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCat.getDmGrid().getGrid().getStore().clear();
txtHItemCat.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCat).show();
```

`txtHItemCat.addTextHelpMultiCallBack` → `getSelection()`
```java
if (!DmShared.xNull(txtHItemCat.getTag()).equalsIgnoreCase(DmShared.xNull(txtHItemCat.getTag2nd()))) {
txtHItemCat.setTag2nd(DmShared.xNull(txtHItemCat.getTag()));
grdItemCat.getGrid().getStore().clear();
grdItemCat.getGrid().getStore().add(new GrdItemCat_Mdl());
if (blnStatus && !DmShared.xNull(keyCode).trim().equalsIgnoreCase("")) {
txtHItemCat.setTag2nd(keyCode);
fillItemCat(keyCode);
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
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

`oHGridEdit.addCallBack` → `onOk()`
```java
grdOhConfig.commitChanges();
```

`oHGridEdit.addCallBack` → `onDelete()`
```java
grdOhConfig.commitChanges();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Please Fill Transaction Type"
- Alert: "Please Fill 'Voucher Type'"

## Files
| file | lines |
|---|---|
| client/OHGridEdit.java | 175 |
| client/OHGridEdit_Dsg.java | 113 |
| client/TransactionType.java | 676 |
| client/TransactionType_Dsg.java | 404 |
| server/TransactionType_SrvImpl.java | 915 |
| shared/Find_Mdl.java | 41 |
| shared/GrdItemCat_Mdl.java | 58 |
| shared/GrdOHConfig_Mdl.java | 41 |
| shared/GrdVoucherType_Mdl.java | 58 |
| shared/TransactionType_Mdl.java | 106 |
| shared/TransactionType_Srv.java | 36 |
| shared/TransactionType_SrvAsync.java | 36 |
| shared/TransactionType_Vld.java | 41 |
| shared/TxtHOverhead_Mdl.java | 24 |
| shared/TxtHPostAC_Mdl.java | 23 |

## UI labels / columns (client)
`OverHead`, `Posting AC`, `Transaction Type`, `Manual Code`, `Site Name`, `Site Manual Code`, `Voucher Type`, `Item Catagory`, `Voucher Type :`, `Item Catagory :`, `OH Detail :`

## Models (shared DTOs)
### TransactionType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | siteCode |
| String | siteName |
| String | siteManualCode |
| String | itemCatCode |
| String | itemCatName |
| String | u_EntDt_LatestLine |
| List<GrdVoucherType_Mdl> | grdVoucherType_Mdls |
| List<GrdItemCat_Mdl> | grdItemCat_Mdls |
| List<GrdOHConfig_Mdl> | grdOHConfig_Mdls |

### TxtHPostAC_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### GrdOHConfig_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | acCode |
| String | acName |
| String | vSNo |

### GrdVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | vSNo |
| String | isChecked |
| String | fieldName |
| String | fieldNameValue |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | siteName |
| String | siteManualCode |

### TxtHOverhead_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |

### GrdItemCat_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | isChecked |
| String | vSNo |
| String | fieldName |
| String | fieldNameValue |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<TransactionType_Mdl> |
| saveRecord | TransactionType_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVoucherTye | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdVoucherType_Mdl>> |
| getTxtHItemCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<GrdItemCat_Mdl>> |
| getTxtHOverhead | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHOverhead_Mdl>> |
| getTxtHPostAC | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPostAC_Mdl>> |
| fillVTypeGrid | String code | DmResult<TransactionType_Mdl> |
| fillItemCatGrid | String code | DmResult<TransactionType_Mdl> |

## Validator `TransactionType_Vld`
```java
public class TransactionType_Vld {
    public static void chkSave(TransactionType_Mdl mrTemp) {
        DmBaseShared.isValidText(mrTemp.getSharedKeyFieldName(), "Transaction Type", 100);
        DmBaseShared.isValidText(mrTemp.getManualCode(), "Manual Code", 200);
        List<GrdOHConfig_Mdl> listItemDetail = mrTemp.getGrdOHConfig_Mdls();
        boolean flag = false;
        if (listItemDetail != null) {
            for (int i = 0; i < listItemDetail.size(); i++) {
                GrdOHConfig_Mdl detail_Mdl = listItemDetail.get(i);
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                    flag = true;
                    if (DmShared.iNull(detail_Mdl.getSharedKeyFieldCode()).intValue() == 0) {
                        throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please Fill 'OverHead' in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                    if (DmShared.iNull(detail_Mdl.getAcCode()).intValue() == 0) {
                        throw new DmException(300, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please Fill 'Posting Account' in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                    }
                }
            }
        }
        if (flag == false) {
        }
    }
}
```

## Server `TransactionType_SrvImpl.java` (915 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getManageUser`, `DmServer.getParamList`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteList`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select code From transType Order By name;`

### `getMoveRec`
`public DmResult<TransactionType_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT tr.code, tr.name, tr.manualCode , tr.site_Code, sm.name AS siteName, sm.manualCode AS siteManualCode , tr.itemCatCode , tr.u_EntDt_LatestLine, tr.u_EntDt, tr.u_Name , tr.preparedBy, tr.preparedDt, tr.transferDt, tr.preparedByIP FROM dbo.transType tr LEFT JOIN siteMast sm ON sm.code = tr.site_Code LEFT JOIN itemCat ic ON ic.code = tr.itemCatCode WHERE tr.code = :code`
- SQL 2: `SELECT trVt.code, trVt.sNo, trVt.v_Type , vt.description, vt.category ,CONCAT(vt.Description,' (', vt.short_Name,')' )AS name FROM dbo.transType_VoucherDetail trVt LEFT JOIN voucher_Type vt ON vt.v_Type = trVt.v_Type WHERE trVt.code = :code`
- SQL 3: `SELECT trIc.code, trIc.sNo , trIc.itemCatCode, ic.name AS itemCatName FROM dbo.transType_ItemCatDetail trIc LEFT JOIN itemCat ic ON ic.code = trIc.itemCatCode WHERE trIc.code = :code`
- SQL 4: `SELECT trAc.code, trAc.sNo , trAc.overHeadCode, ohm.name AS overHeadName , trAc.acCode, sg.name AS postingAcName FROM dbo.transType_PostingAcDetail trAc LEFT JOIN overheadmast ohm ON ohm.code = trAc.overHeadCode LEFT JOIN subgroup sg ON sg.subCode = trAc.acCode WHERE trAc.code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.xNull(strKey)`; `:code` ← `DmShared.xNull(strKey)`; `:code` ← `DmShared.xNull(strKey)`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `DELETE FROM transType_ItemCatDetail WHERE Code = :Code`
- SQL 2: `DELETE FROM transType_PostingAcDetail WHERE Code = :Code`
- SQL 3: `DELETE FROM transType_VoucherDetail WHERE Code = :Code`
- SQL 4: `Delete From transType Where code = :code`
- Params: `:Code` ← `DmShared.iNull(strKey).toString()`; `:Code` ← `DmShared.iNull(strKey).toString()`; `:Code` ← `DmShared.iNull(strKey).toString()`; `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(TransactionType_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(tr.name) AS cnt FROM transType as tr WHERE LOWER(tr.name) = :name AND tr.code <> :code AND tr.site_Code = :site_Code`
- SQL 2: `SELECT COUNT(tr.manualCode) AS cnt FROM transType as tr WHERE LOWER(tr.manualCode) = :manualCode AND tr.code <> :code AND tr.site_Code = :site_Code`
- SQL 3: `INSERT INTO transType (code, name, manualCode, site_Code, itemCatCode , u_EntDt_LatestLine, preparedBy , preparedDt, transferDt, preparedByIP) VALUES (:code, :name, :manualcode, :site_code, :itemCatCode , :preparedDt, :preparedby , :prepareddt, :transferdt, :preparedbyip)`
- SQL 4: `UPDATE transType SET code = :code, name = :name, manualCode = :manualcode, site_Code = :site_code, itemCatCode = :itemcatcode, u_EntDt_LatestLine = :u_EntDt, u_EntDt = :u_entdt, u_Name = :u_name WHERE Code = :Code`
- SQL 5: `DELETE FROM transType_ItemCatDetail WHERE Code = :Code`
- SQL 6: `DELETE FROM transType_PostingAcDetail WHERE Code = :Code`
- SQL 7: `DELETE FROM transType_VoucherDetail WHERE Code = :Code`
- SQL 8: `INSERT INTO transType_ItemCatDetail (code, sNo, itemCatCode) VALUES (:code, :sNo, :itemcatcode)`
- SQL 9: `INSERT INTO transType_PostingAcDetail (code, sNo, overHeadCode, acCode) VALUES (:code, :sNo, :overHeadCode, :acCode)`
- SQL 10: `INSERT INTO transType_VoucherDetail (code, sNo, v_Type) VALUES (:code, :sNo, :v_type)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.xNull(mrTemp.getSiteCode())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode()).toLowerCase().trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.xNull(mrTemp.getSiteCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:itemCatCode` ← `DmShared.xNull(mrTemp.getItemCatCode())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:transferDt` ← `null`; `:Code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:itemCatCode` ← `DmShared.xNull(mrTemp.getItemCatCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:Code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sNo` ← `DmShared.iNull(cat_Mdl.getvSNo()).intValue()`; `:itemCatCode` ← `DmShared.xNull(cat_Mdl.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sNo` ← `DmShared.iNull(config_Mdl.getvSNo()).intValue()`; `:overHeadCode` ← `DmShared.xNull(config_Mdl.getSharedKeyFieldCode())`; `:acCode` ← `DmShared.xNull(config_Mdl.getAcCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:sNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:v_Type` ← `DmShared.xNull(detail_Mdl.getSharedKeyFieldCode())`
- Errors: "Name already exists." | "manual Code already exists."
- Other calls: `TransactionType_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tr.code, tr.name, tr.manualCode , sm.name AS siteName, sm.manualCode AS siteManualCode FROM dbo.transType tr LEFT JOIN siteMast sm ON sm.code = tr.site_Code LEFT JOIN itemCat ic ON ic.code = tr.itemCatCode`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name ,sm.manualCode FROM siteMast sm where sm.code in( {…} :code {…} )`

### `getTxtHVoucherTye`
`public DmResultPageing<List<GrdVoucherType_Mdl>> getTxtHVoucherTye(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type AS code ,CONCAT(vt.Description,' (', vt.short_Name,')' )AS name ,vt.category,vt.description FROM voucher_Type vt`

### `getTxtHItemCat`
`public DmResultPageing<List<GrdItemCat_Mdl>> getTxtHItemCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code , ic.name FROM itemcat ic`

### `fillVTypeGrid`
`public DmResult<TransactionType_Mdl> fillVTypeGrid(String code)`

- SQL 1: `SELECT vt.v_Type AS code ,CONCAT(vt.Description,' (', vt.short_Name,')' )AS name ,vt.category,vt.description FROM voucher_Type vt WHERE vt.v_Type IN ( {…} :code {…} )`

### `fillItemCatGrid`
`public DmResult<TransactionType_Mdl> fillItemCatGrid(String code)`

- SQL 1: `SELECT ic.code , ic.name FROM itemcat ic WHERE ic.code IN ( {…} :code {…} )`

### `getTxtHOverhead`
`public DmResultPageing<List<TxtHOverhead_Mdl>> getTxtHOverhead(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CODE, NAME, manualCode FROM overheadmast`

### `getTxtHPostAC`
`public DmResultPageing<List<TxtHPostAC_Mdl>> getTxtHPostAC(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Subcode, NAME, groupCode FROM subgroup`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.transactionType.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Name already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "manual Code already exists." |

## Notes (hand-written)
_No hand-written notes yet._
