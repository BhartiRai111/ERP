# pb-self-toolkit / otherChargesGrid

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/otherChargesGrid/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: otherChargesTrans
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: Followng Services are not valid
- Lookups/suggestions: getTxtHItemName, getTxtHValuationType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GridEditPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 2 | Valuation Type | DmTextHelp | `txtHValuationType` | `KeyField_PrpAcs, 4, true, true` |
| 3 | Value | DmTextBoxNumeric | `txtValue` | `4, false, 12, false` |

Other widgets (not in tab list): `txtUnit` (DmTextBox), `txtOverHead` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Name [Text, w200, name]; Manual Code [Text, w100, manualCode]
- `txtHValuationType`: Type [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHValuationType` → `getTxtHValuationType`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHItemName.setEnabled(false)`
- `txtHValuationType.setEnabled(false)`
- `txtOverHead.setEnabled(false)`
- `txtValue.setEnabled(flag)`
- `flwOk.setVisible(flag)`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = DmShared.xNull(headerEntry.topControl.getMode()).equalsIgnoreCase("New")
|| DmShared.xNull(headerEntry.topControl.getMode()).equalsIgnoreCase("Edit");
txtHItemName.setEnabled(false);
txtHValuationType.setEnabled(false);
txtOverHead.setEnabled(false);
txtValue.setEnabled(flag);
flwOk.setVisible(flag);
txtValue.setFocus(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHValuationType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHValuationType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHValuationType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHValuationType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHValuationType).show();
```

`txtHValuationType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHValuationType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHValuationType).show();
```
- Alert: "Name is mandatory."
- Alert: "Valuation Type is Mandatory."

### Screen `OtherChargesGrid`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `gridOtherCharges` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `gridOtherCharges`: Item Name [Text]; Valuation Type [Text, w120]; Value [Text, w120]; Quantity [Text, w120]; Amount [Text, w85]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridEditPopup.addCallBack` → `onOk()`
```java
callBack.onOk(calculate(popup_Mdl));
```

## Files
| file | lines |
|---|---|
| client/GridEditPopup.java | 283 |
| client/GridEditPopup_Dsg.java | 250 |
| client/OtherChargesGrid.java | 301 |
| client/OtherChargesGrid_Dsg.java | 68 |
| server/OtherChargesGrid_Server.java | 166 |
| server/OtherChargesGrid_SrvImpl.java | 154 |
| shared/BifurcatedServicePopup_Mdl.java | 135 |
| shared/OtherChargeGrid_Vld.java | 30 |
| shared/OtherChargesDetail_Mdl.java | 151 |
| shared/OtherChargesGrid_Srv.java | 19 |
| shared/OtherChargesGrid_SrvAsync.java | 19 |
| shared/TxtHItemName_Mdl.java | 68 |

## UI labels / columns (client)
`Name`, `Unit`, `Valuation Type`, `Value`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | skuCode |
| String | skuName |
| String | overHeadCode |
| String | overHeadName |
| String | overHeadManualCode |

### OtherChargesDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | unitCode |
| String | unitName |
| String | valuationTypeCode |
| String | valuationTypeName |
| String | value |
| String | totalQuantity |
| String | totalAmount |
| String | ohCode |
| String | ohName |
| String | ohManualCode |

### BifurcatedServicePopup_Mdl
| type | field |
|---|---|
| String | smfCode |
| String | serviceCode |
| String | serviceName |
| String | evalTypeTag |
| String | evalTypeText |
| String | iSubCode |
| String | balancingAc |
| String | unitCode |
| String | unitName |
| String | amount |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHItemName | String balancingAcField, boolean isInService, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHValuationType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| initialize | String overHeadConfigCode | DmResult<List<OtherChargesDetail_Mdl>> |

## Validator `OtherChargeGrid_Vld`
```java
public class OtherChargeGrid_Vld {
    public static void chkSave(List<OtherChargesDetail_Mdl> grdServiceLst) {
        String msg = "";
        if (grdServiceLst.size() > 0) {
            for (OtherChargesDetail_Mdl serviceDetail_Mdl : grdServiceLst) {
                if (DmShared.iNull(serviceDetail_Mdl.getSharedKeyFieldCode()).longValue() > 0) {
                    if (DmShared.vNull(serviceDetail_Mdl.getTotalAmount()).doubleValue() <= 0) {
                    }
                }
            }
        }
        if (!DmShared.xNull(msg).trim().equalsIgnoreCase("")) {
            throw new DmException(0, "Followng Services are not valid" + msg);
        }
    }
}
```

## Server `OtherChargesGrid_SrvImpl.java` (154 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItemName(String balancingAcField, boolean isInService, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.sku, im.manualCode , um.unit as unitName , im.subCode AS iSubCode , ohm.code AS overHeadCode , ohm.name AS overHeadName , ohm.manualCode AS overHeadManualCode FROM itemMast im LEFT JOIN overHeadMast ohm ON ohm.itemCode = im.code LEFT JOIN unitMast um ON im.sku = um.code WHERE im.ItemType = :ItemType AND im.isActive = 1`
- Params: `:ItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`

### `getTxtHValuationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValuationType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `OtherChargesValuationType.values`

### `initialize`
`public DmResult<List<OtherChargesDetail_Mdl>> initialize(String overHeadConfigCode)`

- Other calls: `OtherChargesGrid_Server.getOtherCharges`

## Server `OtherChargesGrid_Server.java` (166 lines)
Helpers used: `DmServer.vNull`, `DmShared.vNull`, `DmShared.xNull`

### `save`
`public static void save(String docId, List<OtherChargesDetail_Mdl> details, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO otherChargesTrans (docId, itemCode, overHeadCode, valuationTypeCode, value, totalQuantity, totalAmount) VALUES (:docId, :itemCode, :overHeadCode, :valuationTypeCode, :value, :totalQuantity, :totalAmount)`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:itemCode` ← `DmShared.xNull(detail.getSharedKeyFieldCode())`; `:overHeadCode` ← `DmShared.xNull(detail.getOhCode())`; `:valuationTypeCode` ← `DmShared.xNull(detail.getValuationTypeCode())`; `:value` ← `DmServer.vNull(detail.getValue())`; `:totalQuantity` ← `DmServer.vNull(detail.getTotalQuantity())`; `:totalAmount` ← `DmServer.vNull(detail.getTotalAmount())`

### `getOtherCharges`
`public static List<OtherChargesDetail_Mdl> getOtherCharges(String docId, String overHeadConfigCode, Connection cnTemp) throws Exception`

- SQL 1: `WITH CTE AS ( SELECT oc.itemCode AS code , oc.value, oc.totalQuantity, oc.totalAmount FROM otherChargesTrans oc WHERE 1 = 1 {…} UNION SELECT im.code , im.serviceEvalRate AS value, 0 AS totalQuantity, 0 AS totalAmount FROM itemMast im LEFT JOIN (SELECT ohlc.itemCode FROM overHeadLineConfig ohlc WHERE 1 = 1 {…} AND ISNULL(ohlc.itemCode, '') <> '' ) Vohlc ON Vohlc.itemCode = im.code WHERE ISNULL(Vohlc.itemCode, '') <> '' AND NOT EXISTS ( SELECT 1 FROM otherChargesTrans oc WHERE 1 = 1 {…} AND oc.itemCode = im.code ) ) SELECT oc.code, im.name, im.sku AS unitCode, um.unit AS unitName , ohm.code AS overHeadCode, ohm.name AS overHeadName, ohm.manualCode , im.serviceEvalTypeCode AS valuationTypeCode , (CASE WHEN im.serviceEvalTypeCode = {…} THEN ' {…} ' WHEN im.serviceEvalTypeCode = {…} THEN ' {…} ' WHEN im.serviceEvalTypeCode = {…} THEN ' {…} ' ELSE '' END) AS valuationTypeName , oc.value, oc.totalQuantity, oc.totalAmount FROM CTE oc LEFT JOIN itemMast im ON im.code = oc.code LEFT JOIN overHeadMast ohm ON ohm.itemCode = im.code LEFT JOIN unitMast um ON um.code = im.sku`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:overHeadConfigCode` ← `DmShared.xNull(overHeadConfigCode)`
- Other calls: `AMOUNT.getCode`, `AMOUNT.getName`, `AT_RATE.getCode`, `AT_RATE.getName`, `PER_UNIT.getCode`, `PER_UNIT.getName`

### `delete`
`public static void delete(String docId, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM otherChargesTrans WHERE docId = :docId`
- Params: `:docId` ← `DmShared.xNull(docId)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.overHead.shared`, `dataman.dmToolkit.overHeadLine.shared`, `paperbiz.selfToolkit.otherChargesGrid.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Voucher ke "other charges" grid (`otherChargesTrans`): item/charge, valuation type; `initialize`.
