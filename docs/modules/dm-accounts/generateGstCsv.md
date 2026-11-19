# dm-accounts / generateGstCsv

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/generateGstCsv/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GenerateGstCsv`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Financial Period | DmTextBox | `txtFromDate` | `11, true` |
| 2 | CSV Type | DmTextHelp | `txtHType` | `KeyField_PrpAcs, 3, true, true` |

Other widgets (not in tab list): `txtToDate` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHType`: Type [Text, w0, Type]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHType` → `getTxtHType`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```

`txtHType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHType).show();
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select From Date"
- Alert: "Please select To Date"
- Alert: "Please select CSV Type"

## Files
| file | lines |
|---|---|
| client/GenerateGstCsv.java | 235 |
| client/GenerateGstCsv_Dsg.java | 149 |
| server/GenerateGstCsv_SrvImpl.java | 309 |
| shared/Filter_Mdl.java | 49 |
| shared/GenerateGstCsv_Srv.java | 17 |
| shared/GenerateGstCsv_SrvAsync.java | 17 |

## UI labels / columns (client)
`Financial Period`, `CSV Type`

## Models (shared DTOs)
### Filter_Mdl
| type | field |
|---|---|
| String | sessionId |
| String | fromDate |
| String | toDate |
| String | type |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportGstDataToCsv | Filter_Mdl filter | DmResult<String[]> |

## Server `GenerateGstCsv_SrvImpl.java` (309 lines)
Helpers used: `DmServer.convertDate`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHType(DmHelper helper, boolean blnToEndOfFile)`


### `exportGstDataToCsv`
`public DmResult<String[]> exportGstDataToCsv(Filter_Mdl filter)`

- SQL 1: `SELECT sm.manualCode FROM siteMast sm WHERE sm.code = {…}`
- SQL 2: `SELECT sm.gstin AS ourGSTIN , sm.code AS ourSiteCode , format(gst.v_Date, 'MMM-yyyy') AS financialPeriod , gst.gstin AS customerGSTIN , gst.partyType AS customerGstNature , '' AS eCommerceGSTIN , gst.gstDocType AS documentType , 'OE' AS throughECommerce , gst.docId , gst.v_Sno , gst.gstDocNature AS documentNature , gst.recIdPrefix AS voucherNoPrefix , gst.recIdSerial AS voucherNoSerial , gst.recId AS voucherNo , format(gst.v_Date,'dd-MMM-yyyy') AS voucherDate , stm.numericCode AS placeOfSupply , CASE WHEN ISNULL(gst.rcmApplicable, 0) = 1 THEN 'Y' ELSE 'N' END AS reverseCharge , gst.invoiceValue , hsn.hsn AS hsn_sac_code , SUBSTRING(im.name, 1, 30) AS itemName , gst.itemGSTNature , SUBSTRING(dm.nature, 1, 20) AS itemStore , gst.qty AS itemQuantity , gum.manualCode AS itemUnit , gst.itemGrossAmount , gst.itemTaxableAmount , gst.itemIgstRate , gst.itemIgstAmount , gst.itemSgstRate , gst.itemSgstAmount , gst.itemCgstRate , gst.itemCgstAmount , gst.itemUgstRate , gst.itemUgstAmount , gst.itemCessRate , gst.itemCessAmount , gst.itemNetAmount , gst.shippingBillNo , format(gst.shippingBillDt,'dd-MMM-yyyy') AS shippingBillDate , gst.shippingPortCode , gst.refDocId , gst.refRecId , format(gst.refV_Date,'dd-MMM-yyyy') AS refV_Date , gst.refPartyBillNo , format(gst.refPartyBillDt,'dd-MMM-yyyy') AS refPartyBillDt , gst.refGstDocType AS refDocType , gst.refDocValue , grm.name AS reason , '' AS preGst , '' AS ammendedDocId , gst.partyBillNO , format(gst.partyBillDt,'dd-MMM-yyyy') AS partyBillDt , sgp.name AS customerName FROM gstTrans gst LEFT JOIN siteMast sm ON sm.code = gst.site_Code LEFT JOIN stateMast stm ON stm.code = gst.placeOfSupply LEFT JOIN hsnMast hsn ON hsn.code = gst.hsnCode LEFT JOIN itemMast im ON im.code = gst.itemCode LEFT JOIN departmentMast dm ON dm.subCode = gst.departmentCode LEFT JOIN gstUnitMast gum ON gum.code = gst.gstUnitCode LEFT JOIN gstReasonMast grm ON grm.code = gst.gstReasonCode LEFT JOIN subGroup sgp ON sgp.subCode = gst.partyCode WHERE gst.site_Code = :site_Code AND gst.isStatus =0 AND gst.v_Date BETWEEN :fromDate AND :toDate {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:fromDate` ← `DmServer.convertDate(filter.getFromDate())`; `:toDate` ← `DmServer.convertDate(filter.getToDate())`
- Errors: "Invalid Session ID..." | "From Date is mandatory..." | "To Date is mandatory..." | "Type is mandatory..." | "Invalid Type..."
- Other calls: `INPUT.toInt`, `OUTPUT.toInt`

## Depends on (imports from other packages)
`dataman.accounts.generateGstCsv.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| exportGstDataToCsv | `if (DmShared.xNull(filter.getSessionId()).trim().equalsIgnoreCase(""))` | "Invalid Session ID..." |
| exportGstDataToCsv | `if (DmShared.dNull(filter.getFromDate()).trim().equalsIgnoreCase(""))` | "From Date is mandatory..." |
| exportGstDataToCsv | `if (DmShared.dNull(filter.getToDate()).trim().equalsIgnoreCase(""))` | "To Date is mandatory..." |
| exportGstDataToCsv | `if (DmShared.xNull(filter.getType()).trim().equalsIgnoreCase(""))` | "Type is mandatory..." |
| exportGstDataToCsv | `} else if (DmShared.xNull(filter.getType()).trim().equalsIgnoreCase("S"))` | "Invalid Type..." |

## Notes (hand-written)
_No hand-written notes yet._
