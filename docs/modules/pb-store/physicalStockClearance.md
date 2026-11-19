# pb-store / physicalStockClearance

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/store/src/physicalStockClearance/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockHead, stockHeadDetail
- Update tables: stockHead
- Delete cascade (in order): stockHeadDetail, stockHead
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Voucher Type (Text); Date On (Text); Godown (Text); rule: Please define balancing a/c for Physical Stock Posting.
- Lookups/suggestions: getTxtHDateOn
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PhysicalStockClearance`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Date On | DmTextHelp | `txtHVoucherDate` | `TxtHDateOn_PrpAcs, 6, true` |
| 2 | Voucher No. | DmTextBox | `txtVoucherNo` | `20, false` |
| 3 | Godown | DmTextBox | `txtDepartment` | `` |
| 4 | Filter Type | DmTextBox | `txtFilterType` | `` |
| 5 | Filter Value | DmTextBox | `txtFilterValue` | `` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherDate`: Date [Text, w100, REPLACE(CONVERT(varchar, date,106), ' ', '-')]; Godown [Text, w180, departmentName]; Filter Type [Text, w140, filterTypeName]; Filter Value [Text, w180, filterValueName]
- `dmFind`: Voucher No. [Text, w130, recId]; Date [Text, w80, replace(convert(varchar, v_Date, 106), ' ','-')]; Type [Text, w50, short_Name]; Godown [Text, w100, departmentName]; Posting [Text, w300, postedBy]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherDate` → `getTxtHDateOn`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherNo.getText())
- `VoucherDate` ← DmShared.dNull(txtHVoucherDate.getText())
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherDate.getTag2nd())
- `DepartmentCode` ← DmShared.xNull(txtDepartment.getTag())
- `DepartmentName` ← DmShared.xNull(txtDepartment.getText())
- `FilterTypeCode` ← DmShared.xNull(txtFilterType.getTag())
- `FilterTypeName` ← DmShared.xNull(txtFilterType.getText())
- `FilterValueCode` ← DmShared.xNull(txtFilterValue.getTag())
- `FilterValueName` ← DmShared.xNull(txtFilterValue.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherNo.setText` ← DmShared.xNull(mrTemp.getSharedKeyFieldName())
- `txtVoucherNo.setTag` ← DmShared.iNull(mrTemp.getRecIdObj().getCounter()).toString()
- `txtVoucherNo.setTag2nd` ← DmShared.xNull(mrTemp.getRecIdObj().getPrefix())
- `txtHVoucherDate.setTag2nd` ← DmShared.xNull(mrTemp.getVoucherTypeCode())
- `txtHVoucherDate.setText` ← DmShared.dNull(mrTemp.getVoucherDate())
- `txtDepartment.setTag` ← DmShared.xNull(mrTemp.getDepartmentCode())
- `txtDepartment.setText` ← DmShared.xNull(mrTemp.getDepartmentName())
- `txtFilterType.setTag` ← DmShared.xNull(mrTemp.getFilterTypeCode())
- `txtFilterType.setText` ← DmShared.xNull(mrTemp.getFilterTypeName())
- `txtFilterValue.setTag` ← DmShared.xNull(mrTemp.getFilterValueCode())
- `txtFilterValue.setText` ← DmShared.xNull(mrTemp.getFilterValueName())
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy())
- `userControl.setTextBlog` ← postedByBlog, DmShared.xNull(mrTemp.getPostedBy())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(mrTemp.getSharedPreparedBy()))
- userControl.setTextBlog(postedByBlog, DmShared.xNull(mrTemp.getPostedBy()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtVoucherNo.setEnabled(false)`
- `txtDepartment.setEnabled(false)`
- `txtFilterType.setEnabled(false)`
- `txtFilterValue.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtVoucherNo.setEnabled(false);
txtDepartment.setEnabled(false);
txtFilterType.setEnabled(false);
txtFilterValue.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(postedByBlog)).equalsIgnoreCase("")) {
manageDisplay(true);
txtHVoucherDate.setFocus(true);
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is posted. You can't modify this record.", (DmTextBox) null).show();
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
if (DmShared.xNull(userControl.getTextBlog(postedByBlog)).equalsIgnoreCase("")) {
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
} else {
topControl.btnClick("");
new DmAlertMessageBox("Record is posted. You can't delete this record.", (DmTextBox) null).show();
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
topControlMoveRec(fetchCode);
```

`topControl.addPrintHandeller` → `printClicked()`
```java
prnRecord(false);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
if (topControl.getMode().equalsIgnoreCase("New")) {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(PhysicalStockClearance.this);
```

`txtHVoucherDate.addTextHelpCallBack` → `populateData()`
```java
selectedVoucherDate = DmShared.dNull(txtHVoucherDate.getText());
srvAsync.invokeRPC(blnCallBackWait).getTxtHDateOn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHDat
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherDate).show();
public void onSuccess(DmResultPageing<List<TxtHDateOn_Mdl>> result) {
if (result.isStatus()) {
txtHVoucherDate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherDate).show();
```

`txtHVoucherDate.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherDate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherDate).show();
```

`txtHVoucherDate.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (!DmShared.dNull(selectedVoucherDate).trim().equalsIgnoreCase(DmShared.dNull(txtHVoucherDate.getText()).trim())) {
txtDepartment.setTag("");
txtDepartment.setText("");
txtFilterType.setTag("");
txtFilterType.setText("");
txtFilterValue.setTag("");
txtFilterValue.setText("");
if (t1Row != null) {
txtDepartment.setTag(t1Row.getDepartmentCode());
...
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
```

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Record is posted. You can't modify this record."
- Alert: "Record is posted. You can't delete this record."

## Files
| file | lines |
|---|---|
| client/PhysicalStockClearance.java | 555 |
| client/PhysicalStockClearance_Dsg.java | 229 |
| server/PhysicalStockClearance_SrvImpl.java | 1658 |
| shared/DefaultValue_Mdl.java | 39 |
| shared/Find_Mdl.java | 50 |
| shared/GrdItemDetail_Mdl.java | 151 |
| shared/PhysicalStockClearance_Mdl.java | 135 |
| shared/PhysicalStockClearance_Srv.java | 34 |
| shared/PhysicalStockClearance_SrvAsync.java | 34 |
| shared/PhysicalStockClearance_Vld.java | 27 |
| shared/TxtHDateOn_Mdl.java | 68 |

## UI labels / columns (client)
`Date On`, `Voucher No.`, `Godown`, `Filter Type`, `Filter Value`

## Models (shared DTOs)
### PhysicalStockClearance_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdObj |
| String | voucherTypeCode |
| String | voucherDate |
| String | departmentCode |
| String | departmentName |
| String | filterTypeCode |
| String | filterTypeName |
| String | filterValueCode |
| String | filterValueName |
| String | postedBy |
| String | u_EntDt_LatestLine |
| String | balancingAc |
| boolean | inventoryAccountingActive |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isubCode |
| String | computerQty |
| String | adjQty |
| String | skuValue |
| String | EType_IR |
| String | v_SNo |
| String | adjPackQty |
| String | computerPackQty |
| String | packSizeCode |
| String | ibCode |
| String | propertyList |
| String | propertyDisplay |
| String | itemType |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherTypeCode |
| String | balancingAc |
| boolean | inventoryAccountingActive |

### Find_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | voucherType |
| String | voucherDate |
| String | department |
| String | posting |

### TxtHDateOn_Mdl (extends `KeyField`)
| type | field |
|---|---|
| String | departmentCode |
| String | departmentName |
| String | filterTypeCode |
| String | filterTypeName |
| String | filterValueCode |
| String | filterValueName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHDateOn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHDateOn_Mdl>> |
| setPosted | PhysicalStockClearance_Mdl mrTemp | DmResult<String> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| saveRecord | PhysicalStockClearance_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| getMoveRec | String strKey | DmResult<PhysicalStockClearance_Mdl> |
| deleteRecord | String strKey | DmResult<String> |
| getNavigator | UserPermission userPermission | DmResult<List<KeyFieldExtended>> |
| getFind | UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| printRecords | String docId | DmResult<String> |

## Validator `PhysicalStockClearance_Vld`
```java
public class PhysicalStockClearance_Vld {
    public static void chkSave(PhysicalStockClearance_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Voucher Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Date On", 100);
        DmShared.isValidText(mrTemp.getDepartmentCode(), "Godown", 100);
        if (mrTemp.isInventoryAccountingActive()) {
            if (DmShared.iNull(mrTemp.getBalancingAc()).longValue() == 0) {
                throw new DmException(100, "Please define balancing a/c for Physical Stock Posting.");
            }
        }
    }
     public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String ApplicableProperties = "";
        ApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, false, itemTypeManualCode);
        return ApplicableProperties;
    }
}
```

## Server `PhysicalStockClearance_SrvImpl.java` (1658 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkFinancialYearDate`, `DmServer.compileJasper`, `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.fillJasper`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.getUserViewCondition`, `DmServer.getUserWithDateTime`, `DmServer.sendMail`, `DmServer.showJasper`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getTxtHDateOn`
`public DmResultPageing<List<TxtHDateOn_Mdl>> getTxtHDateOn(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT pst.v_Date AS date , pst.departmentCode , sg.name AS departmentName , pst.filterType AS filterTypeCode , CASE ISNULL(pst.filterType, 0) WHEN {…} THEN ' {…} ' WHEN {…} THEN ' {…} ' WHEN {…} THEN ' {…} ' WHEN {…} THEN ' {…} ' ELSE NULL END AS filterTypeName , pst.filterValue AS filterValueCode , CASE ISNULL(pst.filterType, 0) WHEN {…} THEN ic.name WHEN {…} THEN ig.name WHEN {…} THEN mm.companyname WHEN {…} THEN im.name ELSE NULL END AS filterValueName FROM ( SELECT sh.v_Date, shd.departmentCode, sh.site_Code, sh.filterType , CASE ISNULL(sh.filterType, 0) WHEN {…} THEN sh.itemCategoryCode WHEN {…} THEN sh.itemGroupCode WHEN {…} THEN sh.itemMakeCode WHEN {…} THEN sh.itemCode ELSE '' END AS filterValue FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd1.docid, MAX(shd1.departmentCode) AS departmentCode FROM stockHeadDetail shd1 GROUP BY shd1.docid ) AS shd ON shd.docid = sh.docid WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code {…} GROUP BY sh.v_Date, shd.departmentCode, sh.site_Code, sh.filterType , CASE ISNULL(sh.filterType, 0) WHEN {…} THEN sh.itemCategoryCode WHEN {…} THEN sh.itemGroupCode WHEN {…} THEN sh.itemMakeCode WHEN {…} THEN sh.itemCode ELSE '' END ) pst LEFT JOIN ( SELECT sh.v_Date, shd.departmentCode, sh.site_Code, sh.filterType , CASE ISNULL(sh.filterType, 0) WHEN {…} THEN sh.itemCategoryCode WHEN {…} THEN sh.itemGroupCode WHEN {…} THEN sh.itemMakeCode WHEN {…} THEN sh.itemCode ELSE '' END AS filterValue FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd1.docid, MAX(shd1.departmentCode) AS departmentCode FROM stockHeadDetail shd1 GROUP BY shd1.docid ) AS shd ON shd.docid = sh.docid WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code {…} GROUP BY sh.v_Date, shd.departmentCode, sh.site_Code, sh.filterType , CASE ISNULL(sh.filterType, 0) WHEN {…} THEN sh.itemCategoryCode WHEN {…} THEN sh.itemGroupCode WHEN {…} THEN sh.itemMakeCode WHEN {…} THEN sh.itemCode ELSE '' END ) AS pstp ON pstp.v_Date = pst.v_Date AND pstp.departmentCode = pst.departmentCode AND pstp.site_Code = pst.site_Code AND ISNULL(pstp.filterType, 0) = ISNULL(pst.filterType, 0) AND ISNULL(pstp.filterValue, 0) = ISNULL(pst.filterValue, 0) LEFT JOIN ( SELECT sh.v_Date, shd.departmentCode, sh.site_Code, sh.filterType , CASE ISNULL(sh.filterType, 0) WHEN {…} THEN sh.itemCategoryCode WHEN {…} THEN sh.itemGroupCode WHEN {…} THEN sh.itemMakeCode WHEN {…} THEN sh.itemCode ELSE '' END AS filterValue FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd1.docid, MAX(shd1.departmentCode) AS departmentCode FROM stockHeadDetail shd1 GROUP BY shd1.docid ) AS shd ON shd.docid = sh.docid WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code {…} GROUP BY sh.v_Date, shd.departmentCode, sh.site_Code, sh.filterType , CASE ISNULL(sh.filterType, 0) WHEN {…} THEN sh.itemCategoryCode WHEN {…} THEN sh.itemGroupCode WHEN {…} THEN sh.itemMakeCode WHEN {…} THEN sh.itemCode ELSE '' END ) AS pstc ON pstc.v_Date = pst.v_Date AND pstc.departmentCode = pst.departmentCode AND pstc.site_Code = pst.site_Code AND ISNULL(pstc.filterType, 0) = ISNULL(pst.filterType, 0) AND ISNULL(pstc.filterValue, 0) = ISNULL(pst.filterValue, 0) LEFT JOIN subGroup sg ON sg.subCode = pst.departmentCode LEFT JOIN itemCat ic ON ic.code = pst.filterValue LEFT JOIN itemGroup ig ON ig.code = pst.filterValue LEFT JOIN itemMakeMast mm ON mm.code = pst.filterValue LEFT JOIN itemMast im ON im.code = pst.filterValue WHERE pstp.v_Date IS NOT NULL AND pstc.v_Date IS NULL`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ITEM.getId`, `ITEM.getValueStore`, `ITEM_CATEGORY.getId`, `ITEM_CATEGORY.getValueStore`, `ITEM_GROUP.getId`, `ITEM_GROUP.getValueStore`, `ITEM_MAKE.getId`, `ITEM_MAKE.getValueStore`, `STServer.getSsnDepartmentCodeList`

### `getItemDetails`
`private List<GrdItemDetail_Mdl> getItemDetails(String dateOn, String departmentCode, String filterTypeCode, String filterValueCode, boolean isInventoryAccountingActive, Connection cnTemp) throws Exception, Exception`

- SQL 1: `SELECT COUNT(*) AS totalInvalidRecords FROM ( SELECT shd.itemCode, shd.ibCode, shd.departmentCode, shd.site_Code, shd.packSizeCode FROM stockHeadDetail shd LEFT JOIN stockHead sh ON sh.docId = shd.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type WHERE vt.category = ' {…} ' AND sh.v_Date = :dateOn AND sh.site_Code = :site_Code AND shd.departmentCode = :departmentCode GROUP BY shd.itemCode, shd.ibCode, shd.departmentCode, shd.site_Code, shd.packSizeCode ) AS vShd LEFT JOIN ( SELECT shp.itemCode, shp.ibCode, shp.departmentCode, shp.site_Code, shp.packSizeCode FROM stockHeadDetail shp LEFT JOIN stockHead sh ON sh.docId = shp.docId LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type WHERE vt.category = ' {…} ' AND ISNULL(sh.postedBy, '') <> '' AND sh.v_Date = :dateOn AND sh.site_Code = :site_Code AND shp.departmentCode = :departmentCode GROUP BY shp.itemCode, shp.ibCode, shp.departmentCode, shp.site_Code, shp.packSizeCode ) AS vShp ON vShp.itemCode = vShd.itemCode AND ISNULL(vShp.ibCode, 0) = ISNULL(vShd.ibCode, 0) AND vShp.departmentCode = vShd.departmentCode AND vShp.site_Code = vShd.site_Code AND ISNULL(vShp.packSizeCode, 0) = ISNULL(vShd.packSizeCode, 0) WHERE vShp.itemCode IS NULL`
- SQL 2: `SELECT im.code AS itemCode , im.name AS itemName , im.subCode AS iSubCode , im.propertyList , vSt.ibCode , iit.manualCode as itemType , ISNULL(vSt.computerQty, 0) AS computerQty , CASE WHEN (ISNULL(vSt.computerQty, 0)) > 0 THEN 1 WHEN (ISNULL(vSt.computerQty, 0)) < 0 THEN 2 ELSE 0 END AS EType_IR , im.packSizeCode , im.sku as itemSkuCode, psm.sku as packSizeSkuCode , psm.packUnitConversionRate FROM ( SELECT st.itemCode, st.ibCode, st.departmentCode, st.site_Code , ISNULL(SUM(st.recQty),0) - ISNULL(SUM(st.isuQty),0) AS computerQty FROM stock st LEFT JOIN itemMast im ON im.code = st.itemCode WHERE st.v_Date <= :dateOn AND st.site_Code = :site_Code AND st.departmentCode = :departmentCode {…} GROUP BY st.itemCode, st.ibCode, st.departmentCode, st.site_Code ) AS vSt LEFT JOIN ( SELECT shd.itemCode, shd.ibCode, shd.departmentCode, shd.site_Code FROM stockHeadDetail shd INNER JOIN stockHead sh ON sh.docId = shd.docId INNER JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN itemMast im ON im.code = shd.itemCode WHERE vt.category = ' {…} ' AND sh.v_Date = :dateOn AND sh.site_Code = :site_Code AND shd.departmentCode = :departmentCode {…} GROUP BY shd.itemCode, shd.ibCode, shd.departmentCode, shd.site_Code ) AS vShd ON vShd.itemCode = vSt.itemCode AND ISNULL(vShd.ibCode, 0) = ISNULL(vSt.ibCode, 0) AND vShd.departmentCode = vSt.departmentCode AND vShd.site_Code = vSt.site_Code LEFT JOIN itemMast im ON im.code = vSt.itemCode LEFT JOIN itemType iit ON iit.code = im.itemType LEFT JOIN packSizeMast psm ON psm.code = im.packSizeCode WHERE vShd.itemCode IS NULL AND ISNULL(vSt.computerQty, 0) <> 0 ORDER BY im.name ASC`
- Params: `:dateOn` ← `DmServer.convertDate(dateOn)`; `:departmentCode` ← `DmShared.xNull(departmentCode)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:dateOn` ← `DmServer.convertDate(dateOn)`; `:departmentCode` ← `DmShared.xNull(departmentCode)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:filterValueCode` ← `DmShared.iNull(filterValueCode).toString()`
- Errors: "Please post all entries for selected date, department and filter." | "Inventory a/c is not defined for item: "
- Other calls: `ItemBatch_Server.getIPM`, `PhysicalStockClearance_Vld.getApplicableProperties`, `PhysicalStockFilterType.get`

### `setPosted`
`public DmResult<String> setPosted(PhysicalStockClearance_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) AS totalInvalidRecords FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_Date > :v_Date AND vShd.departmentCode = :departmentCode`
- SQL 2: `SELECT sh.u_EntDt_LatestLine FROM stockHead sh WHERE sh.docId = :docId`
- SQL 3: `SELECT sh.postedBy , sh.postedDateTime , (CASE WHEN :userName IN (ISNULL(sh.postedBy,''),'SA','SUPER') THEN 1 ELSE 0 END) canUnpost , sh.v_Prefix , sh.balancingAc FROM stockHead sh WHERE sh.docId = :docId`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentCode` ← `DmShared.xNull(mrTemp.getDepartmentCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:userName` ← `DmShared.xNull(DmServer.getSsnUserName(this))`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`
- Errors: "Record already exists for dates greater than " | "Record has been changed, can't update record, refresh record then update" | "Error in stock posting..." | "You are not authorized to unpost."
- Other calls: `AccountPosting_Server.deleteAccountPosting`, `AccountPosting_Server.saveAccountPosting`, `STAccountPosting.iniPhysicalStockClearance_AcPostList`, `Stock_Server.chkRecordInUse`, `Stock_Server.deleteStock`, `Stock_Server.isInventoryAccountingActive`, `Stock_Server.saveStock`

### `updateUEntDtLastestLine`
`private void updateUEntDtLastestLine(Connection cnTemp, String docId) throws Exception`

- SQL 1: `UPDATE stockHead SET u_EntDt_LatestLine = :uEntDt WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT vt.v_Type AS code , vt.description AS name , vt.balancingAc FROM voucher_Type vt WHERE vt.category = ' {…} ' AND vt.isActive = 1`
- SQL 2: `SELECT ec.isInventoryAccountingActive FROM enviroCompany ec`
- Errors: "Inventory Accounting Active is not defined in Environment Setting (Company)."

### `saveRecord`
`public DmResult<String> saveRecord(PhysicalStockClearance_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `SELECT COUNT(*) AS totalInvalidRecords FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_Date > :v_Date AND vShd.departmentCode = :departmentCode AND sh.docId != :docId`
- SQL 2: `SELECT COUNT(*) AS totalInvalidRecords FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_Date < :v_Date AND vShd.departmentCode = :departmentCode AND ISNULL(sh.postedBy, '') = ''`
- SQL 3: `SELECT COUNT(*) AS totalClearanceEntry FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_Date = :v_Date AND vShd.departmentCode = :departmentCode`
- SQL 4: `Insert Into stockHead (docId, v_Type, v_No, v_Date, v_Prefix, recId, site_Code, transferDt, preparedBy, preparedDt, u_EntDt_LatestLine, recIdPrefix, filterType, itemCategoryCode, itemGroupCode, itemMakeCode, itemCode, balancingAc) values (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId, :site_Code, :transferDt, :preparedBy, :preparedDt,:preparedDt, :recIdPrefix, :filterType, :itemCategoryCode, :itemGroupCode, :itemMakeCode, :itemCode, :balancingAc)`
- SQL 5: `update stockHead Set v_Date = :v_Date, balancingAc = :balancingAc, transferDt = :transferDt, u_Name = :u_Name, u_EntDt = :uEntDt, u_EntDt_LatestLine = :uEntDt, filterType = :filterType, itemCategoryCode = :itemCategoryCode, itemGroupCode = :itemGroupCode, itemMakeCode = :itemMakeCode, itemCode = :itemCode Where docId = :docId`
- SQL 6: `DELETE FROM stockHeadDetail WHERE docId = :docId`
- SQL 7: `INSERT INTO stockHeadDetail( docId, v_SNo, departmentCode, itemCode, computerQty , quantity, site_Code , ibCode , oh_Ac_Item , skuValue , packSizeCode,computerPackQty,packCount ) values ( :docId, :v_SNo, :departmentCode, :itemCode, :computerQty, :quantity, :site_Code , :ibCode , :oh_Ac_Item , :skuValue , :packSizeCode,:computerPackQty,:packCount )`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentCode` ← `DmShared.xNull(mrTemp.getDepartmentCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentCode` ← `DmShared.xNull(mrTemp.getDepartmentCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:departmentCode` ← `DmShared.xNull(mrTemp.getDepartmentCode())`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdObj().getCounter()).toString()`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getRecIdObj().getPrefix())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:transferDt` ← `null`; `:preparedBy` ← `userName`; `:filterType` ← `DmShared.foriegnKey(mrTemp.getFilterTypeCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:transferDt` ← `null`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:u_Name` ← `userName`; `:filterType` ← `DmShared.foriegnKey(mrTemp.getFilterTypeCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAc())`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemCode` ← `DmShared.foriegnKey("")`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey(mrTemp.getFilterValueCode())`; `:itemCategoryCode` ← `DmShared.foriegnKey("")`; `:itemGroupCode` ← `DmShared.foriegnKey("")`; `:itemMakeCode` ← `DmShared.foriegnKey("")`; `:itemCode` ← `DmShared.foriegnKey("")`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getV_SNo()).toString()`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:computerQty` ← `DmServer.vNull(detail_Mdl.getComputerQty())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getAdjQty())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:oh_Ac_Item` ← `DmShared.foriegnKey(detail_Mdl.getIsubCode())`; `:skuValue` ← `DmServer.vNull(detail_Mdl.getSkuValue())`; `:packSizeCode` ← `DmShared.foriegnKey(detail_Mdl.getPackSizeCode())`; `:computerPackQty` ← `DmServer.vNull(detail_Mdl.getComputerPackQty())`; `:packCount` ← `DmServer.vNull(detail_Mdl.getAdjPackQty())`
- Errors: "Record already exists for dates greater than " | "Unposted records already exist for dates less than " | "Clearance entry already exist for " | "Invalid key." | "Items not found to save..."
- Other calls: `PhysicalStockClearance_Vld.chkSave`, `PhysicalStockFilterType.get`

### `getMoveRec`
`public DmResult<PhysicalStockClearance_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT sh.docId , sh.recId , sh.v_No , sh.recIdPrefix , sh.v_Date , sh.v_Type , vt.description , vShd.departmentCode , sg.name AS departmentName , sh.preparedBy , sh.u_Name , sh.postedBy , sh.u_EntDt_LatestLine , sh.filterType , sh.itemCategoryCode , ic.name AS itemCategoryName , sh.itemGroupCode , ig.name AS itemGroupName , sh.itemMakeCode , mm.companyName AS itemMakeName , sh.itemCode , sh.preparedDt , sh.postedDateTime , im.name AS itemName , sh.balancingAc FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN itemCat ic ON ic.code = sh.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = sh.itemGroupCode LEFT JOIN itemMakeMast mm ON mm.code = sh.itemMakeCode LEFT JOIN itemMast im ON im.code = sh.itemCode LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd WHERE shd.docId = :docId GROUP BY shd.docId ) AS vShd ON vShd.docId = sh.docId LEFT JOIN subGroup sg ON sg.subCode = vShd.departmentCode WHERE sh.docId = :docId`
- Params: `:docId` ← `DmShared.iNull(strKey).toString()`
- Other calls: `PhysicalStockFilterType.get`

### `deleteRecord`
`public DmResult<String> deleteRecord(String keyCode)`

- SQL 1: `SELECT v_date from stockHead where docid = :docid`
- SQL 2: `SELECT sh.postedBy FROM stockHead sh WHERE sh.docId = :docId`
- SQL 3: `Delete From stockHeadDetail Where docId = :docId`
- SQL 4: `Delete From stockHead Where docId = :docId`
- Params: `:docid` ← `DmShared.xNull(keyCode)`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`; `:docId` ← `DmShared.iNull(keyCode).toString()`
- Errors: "Record is posted. You can't modify this record."

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator(UserPermission userPermission)`

- SQL 1: `SELECT sh.docId FROM stockHead sh LEFT JOIN voucher_Type vt ON sh.v_Type = vt.v_Type WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(UserPermission userPermission, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sh.docId , sh.recId , sh.v_Date , sg.name AS departmentName , vt.short_Name , sh.v_No , sh.postedBy , format(sh.postedDateTime,'dd-MMM-yyyy HH.mm') AS postedDateTime FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN ( SELECT shd.docId, max(shd.departmentCode) AS departmentCode FROM stockHeadDetail shd GROUP BY shd.docId ) vShd ON vShd.docId = sh.docId LEFT JOIN subGroup sg ON sg.subCode = vShd.departmentCode WHERE vt.category = ' {…} ' AND sh.site_Code = :site_Code AND sh.v_prefix = :vPrefix`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:vPrefix` ← `DmServer.getSsnVoucherPrefix(this)`

### `printRecords`
`public DmResult<String> printRecords(String docId)`

- SQL 1: `SELECT sh.docId, sh.recId, sh.v_Date, vt.short_Name as vType, shd.departmentCode, sg.name AS departmentName, sh.preparedBy, sh.postedBy, im.name AS itemName, um.unit AS skuName, shd.quantity as ajustQty, shd.computerQty, ib.batchNo, ib.expiryDate, imm.companyName AS itemMakeName, icm.name AS colourName, ism.name AS sizeName, ib.mrp ,ib.propertyDispCsv , psm.name as packSizeName , shd.packCount , umps.unit as packUnit ,CASE WHEN (ISNULL(shd.physicalQty, 0) - ISNULL(shd.computerQty, 0) ) < 0 THEN 'I' WHEN ( ISNULL(shd.physicalQty, 0) - ISNULL(shd.computerQty, 0)) > 0 THEN 'R' ELSE '' END AS EType_IR, CASE ISNULL(filterType, 0) WHEN 1 THEN 'Item Category' WHEN 2 THEN 'Item Group' WHEN 3 THEN 'Item Company' WHEN 4 THEN 'Item' ELSE '' END AS filterType, CASE ISNULL(filterType, 0) WHEN 1 THEN ic.name WHEN 2 THEN ig.name WHEN 3 THEN mm.companyname WHEN 4 THEN shim.name ELSE '''' END AS filterValue FROM stockHead sh LEFT JOIN voucher_Type vt ON vt.v_Type = sh.v_Type LEFT JOIN itemCat ic ON ic.code = sh.itemCategoryCode LEFT JOIN itemGroup ig ON ig.code = sh.itemGroupCode LEFT JOIN itemMast shim ON shim.code = sh.itemCode LEFT JOIN stockHeadDetail shd ON sh.docid = shd.docid LEFT JOIN packSizeMast as psm on psm.code = shd.packSizeCode LEFT JOIN unitMast as umps on psm.packUnitCode = umps.code LEFT JOIN itemMast im ON im.code = shd.itemCode LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN itemBatch ib ON ib.code = shd.ibCode LEFT JOIN itemColourMast icm ON icm.code = ib.colourCode LEFT JOIN itemSizeMast ism ON ism.code = ib.sizeCode left join itemMakeMast imm ON imm.code = ib.itemMakeCode LEFT JOIN itemMakeMast mm ON mm.code = ib.itemMakeCode LEFT JOIN subGroup sg ON sg.subCode = shd.departmentCode WHERE sh.docId =:docId`
- Params: `:docId` ← `DmShared.xNull(docId)`
- Other calls: `STServer.setJasperVariables`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.accountPosting.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.documentSystemReport.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.openSourceReport.client`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`, `paperbiz.store.physicalStockClearance.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getItemDetails | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Please post all entries for selected date, department and filter." |
| getItemDetails | `if (DmShared.iNull(rsTemp.getString("iSubCode")).longValue() == 0)` | "Inventory a/c is not defined for item: " |
| setPosted | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Record already exists for dates greater than " |
| setPosted | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| setPosted | (unconditional) | "Error in stock posting..." |
| setPosted | `if (mrTemp.isInventoryAccountingActive())` | "You are not authorized to unpost." |
| getDefaultValues | `if (rsTemp.getString("isInventoryAccountingActive") == null)` | "Inventory Accounting Active is not defined in Environment Setting (Company)." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Record already exists for dates greater than " |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalInvalidRecords")).intValue() > 0)` | "Unposted records already exist for dates less than " |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("totalClearanceEntry")).intValue() > 0)` | "Clearance entry already exist for " |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (v_SNo == 0)` | "Items not found to save..." |
| deleteRecord | `if (!DmShared.xNull(rsTemp.getString("postedBy")).equalsIgnoreCase(""))` | "Record is posted. You can't modify this record." |

## Notes (hand-written)
_No hand-written notes yet._
