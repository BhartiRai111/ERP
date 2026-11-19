# pb-self-toolkit / productionOrder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/productionOrder/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: sOrder, sOrderDetail
- Update tables: sOrder, sOrderDetail
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Order Type (Text); Order Date (Text); rule: GridRow; rule: Sales Order Details are mandatory.
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/ProductionOrder_Server.java | 261 |
| shared/GrdItemDetail_Mdl.java | 267 |
| shared/ProductionOrder_Mdl.java | 139 |
| shared/ProductionOrder_Vld.java | 53 |

## Models (shared DTOs)
### ProductionOrder_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recIdOrder |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | vTypeShortName |
| String | voucherDate |
| String | remark |
| String | approvedBy |
| String | wtaCode |
| String | u_EntDt_LatestLine |
| String | guiConfigCode |
| DmGUI_Mdl | guiMdl |
| List<GrdItemDetail_Mdl> | grdItemDetail |
| ModifyReason_Mdl | modifyReasonMdl |

### GrdItemDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | vSNo |
| String | description |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDecimalPlaces |
| String | skuQtyForRate |
| String | skuCode |
| String | skuName |
| String | SkuDecimalPlaces |
| String | quantity |
| String | skuQty |
| String | isStatus |
| String | deliveryDate |
| String | packSizeCode |
| String | packSizeName |
| String | packCount |
| String | packSizeSkuCode |
| String | packUnitCode |
| String | packUnitName |
| String | packUnitDisplayName |
| String | packUnitConversionRate |
| String | packUnitDecimalPlaces |
| String | isBestBefore |
| String | itemType |
| String | propertyList |
| String | propertiesCsv |
| ItemProperty_Mdl | itemProperty_Mdl |

## Validator `ProductionOrder_Vld`
```java
public class ProductionOrder_Vld {
    public static void chkSave(ProductionOrder_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getVoucherTypeCode(), "Order Type", 100);
        DmShared.isValidText(mrTemp.getVoucherDate(), "Order Date", 200);
        boolean flag = false;
        List<GrdItemDetail_Mdl> listItemDetail = mrTemp.getGrdItemDetail();
        for (GrdItemDetail_Mdl detail_Mdl : listItemDetail) {
            if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                flag = true;
                if (DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).equalsIgnoreCase("")) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter item in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (DmShared.vNull(detail_Mdl.getQuantity()).doubleValue() == 0) {
                    throw new DmException(500, "GridRow", DmShared.iNull(listItemDetail.indexOf(detail_Mdl)).toString(), "Please enter weight in line no. " + DmShared.iNull(listItemDetail.indexOf(detail_Mdl) + 1).toString() + ".");
                }
                if (!DmShared.xNull(detail_Mdl.getPropertyList()).trim().equalsIgnoreCase("")) {
                    try {
                        ItemPropertyPopup_Vld.validateProperties(getApplicableProperties(detail_Mdl.getPropertyList(), detail_Mdl.getItemType()), "0", "", detail_Mdl.getItemProperty_Mdl());
                    } catch (DmException e) {
                        throw new DmException(500, "", "Properties", "Properties is Mandatory");
                    }
                }
                if (DmShared.iNull(detail_Mdl.getItemProperty_Mdl().getIbCode()).longValue() == 0) {
                    throw new DmException(500, "", "Properties", "Please re-select the item to refresh its properties in line no");
                }
            }
        }
        if (!flag) {
            throw new DmException(600, "Sales Order Details are mandatory.");
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

## Server `ProductionOrder_Server.java` (261 lines)
Helpers used: `DmServer.checkCurrentSiteRecord`, `DmServer.checkForwardVoucherDateTime`, `DmServer.chkIfDocumentUsed`, `DmServer.convertDate`, `DmServer.formatTimestamp`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `saveProductionOrder`
`public static void saveProductionOrder(ProductionOrder_Mdl mrTemp, String voucherPrefix, DmRSSPrivate rss, Connection cnTemp, boolean isCallFromProductionOrderEntry) throws Exception`

- SQL 1: `select approvedBy FROM sOrder WHERE docId = :docId`
- SQL 2: `SELECT u_EntDt_LatestLine FROM sOrder WHERE docId = :docId`
- SQL 3: `INSERT INTO sOrder ( docId, recId, recIdPrefix, v_Type, v_No, v_Prefix, v_Date, site_Code, wtaCode, remark, isItc, guiConfigCode, preparedBy ,preparedByIP ,preparedDt, transferDt, u_EntDt_LatestLine ) Values (:docId, :recId, :recIdPrefix, :v_Type, :v_No, :v_Prefix, :v_Date, :site_Code, :wtaCode, :remark, :isItc, :guiConfigCode, :preparedBy ,:preparedByIP ,:preparedDt, :transferDt, :uEntDt )`
- SQL 4: `UPDATE sOrder SET v_Date = :v_Date ,remark = :remark ,u_Name = :u_name ,u_EntDt = :u_EntDt ,u_EntDt_LatestLine = :u_EntDt ,transferDt = :transferDt Where docId = :docId`
- SQL 5: `DELETE FROM sOrderDetail WHERE docId = :docId AND isStatus NOT IN ('1','2')`
- SQL 6: `INSERT INTO sOrderDetail ( docId, v_SNo, itemCode, itemDesc, quantity, isStatus, skuQty, unitCode, deliveryDt ,skuQtyForRate , ibCode ) VALUES ( :docId, :v_SNo, :itemCode, :itemDesc, :quantity, :isStatus, :skuQty, :unitCode, :deliveryDt ,:skuQtyForRate , :ibCode )`
- SQL 7: `UPDATE sOrderDetail SET v_SNo = :v_SNo WHERE docid = :docId AND v_SNo = :previous_VSno`
- Params: `:category` ← `new DmVoucherCategory().SUB_ASSEMBLY_PRODUCTION_ORDER`; `:isActive` ← `1`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecIdOrder().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecIdOrder().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:isItc` ← `"0"`; `:guiConfigCode` ← `DmShared.foriegnKey(mrTemp.getGuiConfigCode())`; `:wtaCode` ← `DmShared.foriegnKey(mrTemp.getWtaCode())`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:transferDt` ← `null`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getVoucherDate())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:u_name` ← `DmServer.getSsnUserName(rss)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:transferDt` ← `null`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:itemCode` ← `DmShared.foriegnKey(detail_Mdl.getSharedKeyFieldCode())`; `:itemDesc` ← `DmShared.xNull(detail_Mdl.getDescription())`; `:unitCode` ← `DmShared.foriegnKey(detail_Mdl.getItemUnitCode())`; `:skuQty` ← `DmServer.vNull(detail_Mdl.getSkuQty())`; `:quantity` ← `DmServer.vNull(detail_Mdl.getQuantity())`; `:deliveryDt` ← `DmServer.convertDate(detail_Mdl.getDeliveryDate())`; `:isStatus` ← `"0"`; `:skuQtyForRate` ← `DmServer.vNull(detail_Mdl.getSkuQtyForRate())`; `:ibCode` ← `DmShared.foriegnKey(detail_Mdl.getItemProperty_Mdl().getIbCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_SNo` ← `DmShared.iNull(detail_Mdl.getvSNo()).intValue()`; `:previous_VSno` ← `previous_VSno`
- Errors: "Record is approved. You can't edit rss record." | "Record has been changed, can't update record, refresh record then update" | "Invalid key."
- Other calls: `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.saveViaConfigCode`, `ProductionOrder_Vld.chkSave`, `WalkThrough_Server.applyWalkThrough`

### `getWalkThroughFor`
`private static WalkThroughDefault_Mdl getWalkThroughFor(ProductionOrder_Mdl mrTemp, DmRSSPrivate dmRSSPrivate)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.walkThrough.server`, `dataman.dmToolkit.walkThrough.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.productionOrder.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Production order sales order ki tables me (`sOrder`, `sOrderDetail`)** save hota hai (voucher type se alag); `pb-production/productionOrder` se judta hai.
