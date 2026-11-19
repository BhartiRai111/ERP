# pb-self-toolkit / stockReleasePopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/stockReleasePopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: stockRelease
- Update tables: stockReserve
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `StockReleaseGridEditPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site Name | DmTextBox | `txtSiteName` | `` |
| 2 | Warehouse | DmTextBox | `txtDepartment` | `` |
| 3 | Item | DmTextBox | `txtItemName` | `` |
| 4 | - | DmTextBox | `txtProperties` | `` |
| 5 | Stock Release Qty | DmTextBoxNumeric | `txtStockReserveQty` | `4, false, 12, false` |
| 6 | - | DmTextBoxNumeric | `txtStockReleaseQty` | `4, false, 12, true` |

Other widgets (not in tab list): `txtStockReserveQtyBox` (DmTextBox), `txtStockReleaseQtyBox` (DmTextBox)

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtSiteName.setEnabled(false)`
- `txtDepartment.setEnabled(false)`
- `txtItemName.setEnabled(false)`
- `txtStockReserveQty.setEnabled(false)`
- `txtStockReleaseQty.setEnabled(super.isOkEnable())`
- `txtProperties.setReadOnly(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtSiteName.setEnabled(false);
txtDepartment.setEnabled(false);
txtItemName.setEnabled(false);
txtStockReserveQty.setEnabled(false);
txtStockReleaseQty.setEnabled(super.isOkEnable());
txtProperties.setReadOnly(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`propertyPopup.addCallBack` → `onOk()`
```java
itemProperty_Mdl = property_Mdl;
txtProperties.setText(DmShared.xNull(property_Mdl.getPropertiesCsv()));
txtProperties.setTag(itemProperty_Mdl.getIbCode());
txtProperties.setFocus(true);
```

`propertyPopup.addCallBack` → `onClose()`
```java
txtProperties.setFocus(true);
```

`aProperties.addClickHandler` → `onClick()`
```java
showPropertyPopUp();
```

`txtProperties.addKeyUpHandler` → `onKeyUp()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_SPACE) {
if (event.getSource() == txtProperties) {
showPropertyPopUp();
```

`txtProperties.addKeyDownHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
if (event.getSource() == txtProperties) {
if (!DmShared.xNull(txtItemName.getTag2nd()).trim().equalsIgnoreCase("")) {
try {
ItemPropertyPopup_Vld.validateProperties(StockReleasePopup_Vld.getApplicableProperties(itemProperty_Mdl.getPropertyList(
} catch (DmException e) {
showPropertyPopUp();
```
- Alert: "Release qty. can not be zero!"
- Alert: "Release qty. can not be greater than reserved qty.!"

### Screen `StockReleasePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `txtItemName` (DmTextBox), `txtOrderPlan` (DmTextBox), `txtStockReserveQty` (DmTextBoxNumeric), `txtStockReserveQtyBox` (DmTextBox), `txtStockReleasedQty` (DmTextBoxNumeric), `txtStockReleasedQtyBox` (DmTextBox), `txtStockReleaseQty` (DmTextBoxNumeric), `txtStockReleaseQtyBox` (DmTextBox), `grdStockDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdStockDetail`: Site [Text, w180]; Warehouse [Text, w180]; Stock Reserve [Text, w100]; Stock Release [Text, w100]; Properties [Text, w620]

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtItemName.setEnabled(false)`
- `txtOrderPlan.setEnabled(false)`
- `txtStockReleaseQty.setEnabled(false)`
- `txtStockReserveQty.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtItemName.setEnabled(false);
txtOrderPlan.setEnabled(false);
txtStockReleaseQty.setEnabled(false);
txtStockReserveQty.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`stockPopupGridEdit.addCallBack` → `onOk()`
```java
grdStockDetail.commitChanges();
calculate();
```

`stockPopupGridEdit.addCallBack` → `onDelete()`
```java
grdStockDetail.commitChanges();
calculate();
```

## Files
| file | lines |
|---|---|
| client/StockReleaseGridEditPopup.java | 257 |
| client/StockReleaseGridEditPopup_Dsg.java | 217 |
| client/StockReleasePopup.java | 257 |
| client/StockReleasePopup_Dsg.java | 257 |
| server/StockRelease_Server.java | 428 |
| shared/GrdStockRelease_Mdl.java | 105 |
| shared/StockReleasePopup_Vld.java | 24 |
| shared/StockRelease_Mdl.java | 220 |

## UI labels / columns (client)
`Plan No.`, `Item Name`, `Stock Reserve Qty.`, `Stock Release Qty.`, `Site Name`, `Warehouse`, `Item`, `Stock Reserved Qty`, `Stock Release Qty`

## Models (shared DTOs)
### GrdStockRelease_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | reserveVsno |
| String | reserveSno |
| String | reserveDepartmentCode |
| String | reserveDepartmentName |
| String | reserveQty |
| String | releaseQty |
| String | propertyDispCsv |

### StockRelease_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | sNo |
| String | v_Type |
| String | v_Date |
| String | site_Code |
| String | itemCode |
| String | itemName |
| String | itemTypeManualCode |
| String | isBestBefore |
| String | skuCode |
| String | skuName |
| String | skuDecimalPlaces |
| String | reserveDocId |
| String | reserveRecId |
| String | reserveVDate |
| String | totalReserveQty |
| String | totalReleaseQty |
| List<GrdStockRelease_Mdl> | grdStockDetail_Mdl |

## Validator `StockReleasePopup_Vld`
```java
public class StockReleasePopup_Vld {
    public static void chkSave(StockRelease_Mdl mrTemp) {
        for (GrdStockRelease_Mdl grdStockRelease_Mdl : mrTemp.getGrdStockDetail_Mdl()) {
            if (DmShared.vNull(grdStockRelease_Mdl.getReleaseQty()).doubleValue() > DmShared.vNull(grdStockRelease_Mdl.getReserveQty()).doubleValue()) {
                throw new DmException(500, DmShared.xNull(mrTemp.getSharedKeyFieldCode()), DmShared.xNull(mrTemp.getSharedKeyFieldName()), "Release qty can not be greater than reserve qty!");
            }
        }
    }
    public static String getApplicableProperties(String propertyList, String itemTypeManualCode) {
        String sortedPropertiesList = ItemPropertyPopup_Vld.getSortedPropertiesCsv(propertyList);
        String poApplicableProperties = "";
        poApplicableProperties = ItemPropertyPopup_Vld.getApplicableProperties(sortedPropertiesList, true, itemTypeManualCode);
        return poApplicableProperties;
    }
}
```

## Server `StockRelease_Server.java` (428 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getParamList`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getStockReleaseData`
`public static StockRelease_Mdl getStockReleaseData(String transDocId, String transVSNo, String transSNo, String planDocid, String planVSNo, String planSNo, boolean flagGetAutoReleaseData, Connection cnTemp) throws Exception`

- SQL 1: `SELECT stkRes.itemCode, im.name as itemName, im.isBestBefore , it.manualCode as itemType, im.sku AS skuCode, im.propertyList , um.unit AS skuName , um.decimalPlaces AS skuAllowDecimalPlaces , stkRes.refDocId as planDocId , stkRes.refRecId as planRecId , stkRes.refV_Date as planDate , stkRes.refV_Sno as planVsNo , stkRes.refSno as planSNo , stkRes.ibCode , stkRes.site_code as siteCode , sm.name as siteName , stkRes.departmentCode , sg.name as departmentName , (ISNULL(stkRes.quantity, :zero) - ISNULL(stkRes.releasedQty, :zero ) + CASE WHEN stkRel.relDocId IS NOT NULL THEN ISNULL(stkRel.quantity, :zero ) ELSE :zero END) AS stockReserveQty , ISNULL(stkRel.quantity, :zero) AS stockReleaseQty , stkRel.relDocId , stkRel.relRecId , stkRel.relRecIdPrefix , stkRel.relV_No , stkRel.relV_Sno , stkRel.relSno , stkRel.relV_Type , stkRel.relV_Date FROM stockReserve stkRes LEFT JOIN stockRelease stkRel ON stkRel.itemCode = stkRes.itemCode AND stkRel.ibCode = stkRes.ibCode AND stkRel.site_Code = stkRes.site_Code AND stkRel.departmentCode = stkRes.departmentCode AND stkRel.refDocId = stkRes.refDocId AND ISNULL(stkRel.refV_Sno, :zero ) = ISNULL(stkRes.refV_Sno, :zero ) AND ISNULL(stkRel.refSno, :zero ) = ISNULL(stkRes.refSno, :zero ) AND ISNULL(stkRel.relDocId, :zero ) = :transDocId AND ISNULL(stkRel.relV_Sno, :zero ) = :transVSNo AND ISNULL(stkRel.relSno, :zero) = :transSNo LEFT JOIN itemMast im ON im.code = stkRes.itemCode LEFT JOIN itemType it ON it.code = im.itemType LEFT JOIN unitMast um ON um.code = im.sku LEFT JOIN siteMast sm ON sm.code = stkRes.site_code LEFT JOIN subgroup sg ON sg.subCode = stkRes.departmentCode {…}`
- Params: `:transDocId` ← `DmShared.iNull(transDocId).toString()`; `:transVSNo` ← `DmShared.iNull(transVSNo).toString()`; `:transSNo` ← `DmShared.iNull(transSNo).toString()`; `:planDocId` ← `DmShared.xNull(planDocid)`; `:zero` ← `0`; `:one` ← `1`; `:two` ← `2`
- Other calls: `ItemBatch_Server.getIPM`, `ItemPropertyPopup_Vld.getApplicableProperties`, `ItemPropertyPopup_Vld.getSortedPropertiesCsv`

### `saveStockRelease`
`public static void saveStockRelease(StockRelease_Mdl stockRelease_Mdl, String uName, Timestamp timestamp, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO stockRelease (itemCode ,ibCode ,site_Code ,departmentCode ,refDocId ,refV_Sno ,refSno ,relDocId ,relV_Sno ,relSno ,relV_Type ,relRecIdPrefix ,relRecId ,relV_No ,relSite_Code ,relV_Date ,quantity ,u_Name ,u_EntDt) VALUES ( :itemCode , :ibCode , :site_Code , :departmentCode , :refDocId , :refV_Sno , :refSno , :relDocId , :relV_Sno , :relSno , :relV_Type , :relRecIdPrefix , :relRecId , :relV_No , :relSite_Code , :relV_Date , :quantity , :u_Name , :u_EntDt)`
- Params: `:relDocId` ← `DmShared.foriegnKey(stockRelease_Mdl.getSharedKeyFieldCode())`; `:relV_Sno` ← `DmShared.foriegnKey(stockRelease_Mdl.getSharedKeyFieldName())`; `:relSno` ← `DmShared.foriegnKey(stockRelease_Mdl.getsNo())`; `:relV_Type` ← `DmShared.foriegnKey(stockRelease_Mdl.getV_Type())`; `:relRecIdPrefix` ← `DmShared.foriegnKey(stockRelease_Mdl.getRecId().getPrefix())`; `:relRecId` ← `DmShared.foriegnKey(stockRelease_Mdl.getRecId().getRecIdValue())`; `:relV_No` ← `DmShared.foriegnKey(DmShared.iNull(stockRelease_Mdl.getRecId().getCounter()).toString())`; `:relSite_Code` ← `DmShared.foriegnKey(stockRelease_Mdl.getSite_Code())`; `:relV_Date` ← `DmServer.convertDate(stockRelease_Mdl.getV_Date())`; `:itemCode` ← `DmShared.foriegnKey(stockRelease_Mdl.getItemCode())`; `:ibCode` ← `DmShared.foriegnKey(grdStockDetail_Mdl.getIpm().getIbCode())`; `:site_Code` ← `DmShared.foriegnKey(grdStockDetail_Mdl.getSharedKeyFieldCode())`; `:departmentCode` ← `DmShared.foriegnKey(grdStockDetail_Mdl.getReserveDepartmentCode())`; `:refDocId` ← `DmShared.foriegnKey(stockRelease_Mdl.getReserveDocId())`; `:refV_Sno` ← `DmShared.foriegnKey(grdStockDetail_Mdl.getReserveVsno())`; `:refSno` ← `DmShared.foriegnKey(grdStockDetail_Mdl.getReserveSno())`; `:quantity` ← `DmServer.vNull(grdStockDetail_Mdl.getReleaseQty())`; `:u_Name` ← `uName`; `:u_EntDt` ← `timestamp`
- Other calls: `StockReleasePopup_Vld.chkSave`

### `deleteStockRelease`
`public static void deleteStockRelease(String planTableName, String planFieldName, String relDocid, String relV_SnNo, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM stockRelease WHERE relDocId = :relDocId {…}`
- Params: `:relDocId` ← `DmShared.iNull(relDocid).toString()`; `:zero` ← `0`; `:relV_Sno` ← `DmShared.iNull(relV_SnNo).intValue()`

### `updateStockReserveStatus`
`public static void updateStockReserveStatus(String planTableName, String planFieldName, String relDocid, String relV_SNo, boolean isDelete, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT stkRes.refDocId,stkRes.refV_Sno,stkRes.refSno , stkRes.itemCode, stkRes.ibCode, stkRes.site_code, stkRes.departmentCode , stkRes.quantity as reservedQty, stkRes.releasedQty as totalRelQty , stkRel.quantity as transRelQty , stkRes.isStatus, stkRel.relDocId as relDocId, stkRel.relV_Sno as relVsno FROM stockReserve stkRes INNER JOIN ( SELECT SUM(rel.quantity) AS quantity , rel.itemCode , rel.ibCode , rel.site_Code , rel.departmentCode , rel.refDocId , rel.refV_Sno , rel.refSno , rel.relDocid, rel.relV_Sno FROM stockRelease rel WHERE rel.relDocid = :relDocid {…} GROUP BY rel.itemCode,rel.ibCode,rel.site_Code,rel.departmentCode,rel.refDocId,rel.refV_Sno,rel.refSno, rel.relDocid,rel.relV_Sno )stkRel ON stkRel.itemCode = stkRes.itemCode AND stkRel.ibCode = stkRes.ibCode AND stkRel.site_Code = stkRes.site_Code AND stkRel.departmentCode = stkRes.departmentCode AND stkRel.refDocId = stkRes.refDocId AND ISNULL(stkRel.refV_Sno, 0) = ISNULL(stkRes.refV_Sno, 0) AND ISNULL(stkRel.refSno, 0) = ISNULL(stkRes.refSno, 0)`
- SQL 2: `UPDATE stockReserve SET isStatus = :isStatus , releasedQty = :releaseQty WHERE refDocId = :refDocId AND itemCode = :itemCode AND ibCode= :ibCode AND site_code= :site_code AND departmentCode= :departmentCode AND ISNULL(refV_Sno,0) = :refV_Sno AND ISNULL(refSno,0) = :refSno`
- Params: `:relDocid` ← `DmShared.iNull(relDocid).toString()`; `:zero` ← `0`; `:relV_Sno` ← `DmShared.iNull(relV_SNo).intValue()`; `:refDocId` ← `DmShared.xNull(rsTemp.getString("refDocId"))`; `:itemCode` ← `DmShared.xNull(rsTemp.getString("itemCode"))`; `:ibCode` ← `DmShared.xNull(rsTemp.getString("ibCode"))`; `:site_code` ← `DmShared.xNull(rsTemp.getString("site_code"))`; `:departmentCode` ← `DmShared.xNull(rsTemp.getString("departmentCode"))`; `:refV_Sno` ← `DmShared.iNull(rsTemp.getString("refV_Sno")).toString()`; `:refSno` ← `DmShared.iNull(rsTemp.getString("refSno")).toString()`; `:isStatus` ← `DmShared.iNull(isStatus).toString()`; `:releaseQty` ← `DmServer.vNull(totalRelQty)`; `:uEntDt` ← `timeStamp`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemPropertyPopup.client`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stockReleasePopup.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Stock reservation/release:** `stockRelease` insert, `stockReserve` update (sales order ke liye reserved stock chhodna).
