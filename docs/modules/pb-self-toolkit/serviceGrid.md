# pb-self-toolkit / serviceGrid

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/serviceGrid/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: ServiceRow; rule: Followng Services are not valid
- Lookups/suggestions: getTxtHItemName, getTxtHGstType, getTxtHValuationType
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `BifurcatedServicePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBoxNumeric | `txtService1Amt` | `2, false, 12, false` |
| 2 | - | DmTextBoxNumeric | `txtService2Amt` | `2, false, 12, false` |
| 3 | - | DmTextBoxNumeric | `txtService3Amt` | `2, false, 12, false` |
| 4 | - | DmTextBoxNumeric | `txtService4Amt` | `2, false, 12, false` |
| 5 | - | DmTextBoxNumeric | `txtService5Amt` | `2, false, 12, false` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtService1Amt.setEnabled(flag)`
- `txtService2Amt.setEnabled(flag)`
- `txtService3Amt.setEnabled(flag)`
- `txtService4Amt.setEnabled(flag)`
- `txtService5Amt.setEnabled(flag)`

**`manageDisplay()` logic (condensed code):**
```java
txtService1Amt.setEnabled(flag);
txtService2Amt.setEnabled(flag);
txtService3Amt.setEnabled(flag);
txtService4Amt.setEnabled(flag);
txtService5Amt.setEnabled(flag);
```

### Screen `ServiceGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Service | DmTextHelp | `txtHItemName` | `com.google.gwt.core.client.TxtHItemName_PrpAcs, 7, true` |
| 2 | SAC | DmTextBox | `txtHsn` | `` |
| 3 | GST Type | DmTextHelp | `txtHGstType` | `KeyField_PrpAcs, 6, true` |
| 4 | Cess Type | DmTextBox | `txtCessEvalType` | `` |
| 5 | Valuation Type | DmTextHelp | `txtHServiceEvalType` | `KeyField_PrpAcs, 4, true, true` |
| 6 | Amount | DmTextBoxNumeric | `txtServiceEvalRate` | `4, false, 12, true` |
| 7 | - | DmTextBoxNumeric | `txtAmount` | `2, false, 12, false` |
| 8 | SGST/UGST | DmTextBoxNumeric | `txtSgstUgstPer` | `4, false, 12, false` |
| 9 | - | DmTextBoxNumeric | `txtSgstUgst` | `2, true, 12, false` |
| 10 | CGST | DmTextBoxNumeric | `txtCGSTPer` | `4, false, 12, false` |
| 11 | - | DmTextBoxNumeric | `txtCGST` | `2, false, 12, false` |
| 12 | IGST | DmTextBoxNumeric | `txtIGSTPer` | `4, false, 12, false` |
| 13 | - | DmTextBoxNumeric | `txtIGST` | `2, true, 12, false` |
| 14 | Cess | DmTextBoxNumeric | `txtCessPer` | `4, false, 12, false` |
| 15 | - | DmTextBoxNumeric | `txtCess` | `2, true, 12, false` |
| 16 | Cess Non Advol | DmTextBoxNumeric | `txtCessNaRate` | `2, false, 12, false` |
| 17 | - | DmTextHelp | `txtHCessNaUnit` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 18 | - | DmTextBoxNumeric | `txtCessNa` | `2, true, 12, false` |
| 19 | VAT | DmTextBoxNumeric | `txtIGSTPer` | `4, false, 12, false` |
| 20 | - | DmTextBoxNumeric | `txtIGST` | `2, true, 12, false` |
| 21 | Total Amount | DmTextBoxNumeric | `txtNetAmount` | `2, false, 12, false` |

Other widgets (not in tab list): `txtSGSTPer` (DmTextBoxNumeric), `txtUGSTPer` (DmTextBoxNumeric), `txtSGST` (DmTextBoxNumeric), `txtUGST` (DmTextBoxNumeric), `txtServiceUnit` (DmTextBox), `txtSmf` (DmTextBox), `txtTempSgstUgst` (DmTextBoxNumeric), `txtTempSgst` (DmTextBoxNumeric), `txtTempUgst` (DmTextBoxNumeric), `txtTempCGST` (DmTextBoxNumeric), `txtTempIGST` (DmTextBoxNumeric), `txtTempCess` (DmTextBoxNumeric), `txtTempCessNa` (DmTextBoxNumeric), `txtTempSubTotal` (DmTextBoxNumeric), `txtTempItemValue` (DmTextBoxNumeric), `cbIsManualGstType` (DmCheckBox), `cbIsManualTax` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHItemName`: Service Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; SAC [Text, w80, hsn]
- `txtHGstType`: Name [Text, w0, name]
- `txtHServiceEvalType`: Type [Text, w0]
- `txtHCessNaUnit`: Name [Text, w100, unitName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemName` → `getTxtHItemName`
- `txtHGstType` → `getTxtHGstType`
- `txtHServiceEvalType` → `getTxtHValuationType`
- `txtHCessNaUnit` → `getCessNaUnit`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtAmount.setEnabled(false)`
- `txtNetAmount.setEnabled(false)`
- `txtServiceEvalRate.setEnabled(true)`
- `txtHItemName.setEnabled(true)`
- `txtHServiceEvalType.setEnabled(true)`
- `cbIsManualGstType.setEnabled(true)`
- `cbIsManualTax.setEnabled(true)`
- `txtHGstType.setEnabled(true)`
- `txtHsn.setEnabled(true)`
- `txtCessNa.setEnabled(true)`
- `txtCessNaRate.setEnabled(true)`
- `txtHCessNaUnit.setEnabled(true)`
- `txtCessEvalType.setEnabled(true)`
- `txtHItemName.setEnabled(false)`
- `txtHServiceEvalType.setEnabled(false)`
- `txtHItemName.setEnabled(false)`
- `txtHServiceEvalType.setEnabled(false)`
- `txtServiceEvalRate.setEnabled(false)`
- `cbIsManualGstType.setEnabled(false)`
- `cbIsManualTax.setEnabled(false)`
- `txtHGstType.setEnabled(false)`
- `txtHsn.setEnabled(false)`
- `txtCessNa.setEnabled(false)`
- `txtCessNaRate.setEnabled(false)`
- `txtHCessNaUnit.setEnabled(false)`
- `txtCessEvalType.setEnabled(false)`
- `txtSgstUgstPer.setEnabled(false)`
- `txtSGSTPer.setEnabled(false)`
- `txtUGSTPer.setEnabled(false)`
- `txtCGSTPer.setEnabled(false)`
- `txtIGSTPer.setEnabled(false)`
- `txtSGST.setEnabled(false)`
- `txtUGST.setEnabled(false)`
- `txtCessPer.setEnabled(false)`
- `txtCess.setEnabled(false)`
- `txtCessNa.setEnabled(true)`
- `txtCessNaRate.setEnabled(true)`
- `txtTempSgstUgst.setEnabled(false)`
- `txtTempSgst.setEnabled(false)`
- `txtTempUgst.setEnabled(false)`
- `txtTempCGST.setEnabled(false)`
- `txtTempIGST.setEnabled(false)`
- `txtTempCess.setEnabled(false)`
- `txtTempCessNa.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtAmount.setEnabled(false);
txtNetAmount.setEnabled(false);
txtServiceEvalRate.setEnabled(true);
txtHItemName.setEnabled(true);
txtHServiceEvalType.setEnabled(true);
cbIsManualGstType.setEnabled(true);
cbIsManualTax.setEnabled(true);
txtHGstType.setEnabled(true);
txtHsn.setEnabled(true);
txtCessNa.setEnabled(true);
txtCessNaRate.setEnabled(true);
txtHCessNaUnit.setEnabled(true);
txtCessEvalType.setEnabled(true);
txtHGstType.setMandatory(true);
if (serviceGrid.isServiceBifurcation) {
if (DmShared.iNull(serviceGrid.autoBifurcatedRowCount).intValue() > 0) {
if (DmShared.vNull(manualServiceValue).doubleValue() > 0) {
txtHItemName.setEnabled(false);
txtHServiceEvalType.setEnabled(false);
this.setVisibleDelete(false);
} else {
txtHItemName.setEnabled(false);
txtHServiceEvalType.setEnabled(false);
txtServiceEvalRate.setEnabled(false);
...
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(serviceGrid.balancingAcField, serviceGrid.isInService, helper, blnTo
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHServiceDetail_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHItemName.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHItemName.getTag()))) {
resetItem();
txtHItemName.setTag2nd(DmShared.xNull(DmShared.xNull(txtHItemName.getTag())));
if (blnStatus && t1Row != null) {
txtSmf.setTag(DmShared.xNull(t1Row.getSmfTag()));
txtSmf.setText(DmShared.xNull(t1Row.getSmfText()));
boolean flagPolicyEvalRate = false;
if (DmShared.xNull(t1Row.getUniqueId()).equalsIgnoreCase(ItemUniqueID.INWARD_INSURANCE_POLICY.getId())
|| DmShared.xNull(t1Row.getUniqueId()).equalsIgnoreCase(ItemUniqueID.OUTWARD_INSURANCE_POLICY.getId())
|| DmShared.xNull(t1Row.getUniqueId()).equalsIgnoreCase(ItemUniqueID.INWARD_OUTWARD_INSURANCE_POLICY.getId())) {
...
```

`txtHGstType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGstType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstType).show();
```

`txtHGstType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGstType).show();
```

`txtHGstType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGstType.getTag2nd()).equalsIgnoreCase(DmShared.xNull(txtHGstType.getTag()))) {
resetItemType();
txtHGstType.setTag2nd(DmShared.xNull(txtHGstType.getTag()));
if (blnStatus && t1Row != null) {
if (!serviceGrid.isServiceBifurcation) {
getITS(t1Row.getSharedKeyFieldCode(), txtAmount.getText(), itsBasedOn, serviceGrid.location, txtHGstType.getTag(), cbIsM
```

`txtHServiceEvalType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHValuationType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHServiceEvalType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHServiceEvalType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHServiceEvalType).show();
```

`txtHServiceEvalType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHServiceEvalType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHServiceEvalType).show();
```

`txtHServiceEvalType.addTextHelpCallBack` → `getSelectedRow()`
```java
calculate();
```

`txtHCessNaUnit.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getCessNaUnit(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCessNaUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCessNaUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCessNaUnit).show();
```

`txtHCessNaUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCessNaUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCessNaUnit).show();
```

`txtHCessNaUnit.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```
- Alert: "Service is mandatory."
- Alert: "Value is Mandatory."
- Alert: "SAC is mandatory for service "
- Alert: "GST Type is mandatory for service "
- Alert: "Service value can not be less than from "
- Alert: "No item found to auto bifurctae this service amount!"
- Alert: "Invalid amount."
- Alert: "Valuation is Mandatory."
- Alert: "Cess non advol rate is Mandatory !"
- Alert: "Cess non advol rate unit is Mandatory !"
- Alert: "Cess non advol amount is Mandatory !"
- Alert: "Cess non advol details is not required"
- Alert: "Cess non advol details is not required"
- Alert: "Service a/c is required."
- Alert: "Balancing a/c is required."
- Alert: "SGST a/c is required."
- Alert: "SGST-RCM a/c is required."
- Alert: "UGST a/c is required."
- Alert: "UGST-RCM a/c is required."
- Alert: "CGST a/c is required."
- Alert: "CGST-RCM a/c is required."
- Alert: "IGST a/c is required."
- Alert: "IGST-RCM a/c is required."
- Alert: "IGST a/c is required."
- Alert: "Cess-RCM a/c is required."
- Alert: "Service already exists."

### Screen `ServiceGrid`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdServiceDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdServiceDetail`: Service [Text, w0]; SAC [Text, w85]; Amount [Text, w85]; GST @ [Text, w85];  [Text, w85]; CGST [Text, w85]; IGST [Text, w85]; VAT [Text, w85]; Cess [Text, w85]; Cess NA [Text, w85]

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`serviceDetailGridEdit.addCallBack` → `onOk()`
```java
callBack.onOk(serviceDetail_Mdl);
```

`serviceDetailGridEdit.addCallBack` → `onDelete()`
```java
callBack.onDelete();
```
- Alert: "Place of Supply is mandatory."

## Files
| file | lines |
|---|---|
| client/BifurcatedServicePopup.java | 317 |
| client/BifurcatedServicePopup_Dsg.java | 245 |
| client/ServiceGrid.java | 393 |
| client/ServiceGridEdit.java | 1325 |
| client/ServiceGridEdit_Dsg.java | 543 |
| client/ServiceGrid_Dsg.java | 91 |
| server/ServiceGrid_Server.java | 369 |
| server/ServiceGrid_SrvImpl.java | 358 |
| shared/BifurcatedServicePopup_Mdl.java | 135 |
| shared/DefaultValue_Mdl.java | 33 |
| shared/ServiceDetail_Mdl.java | 783 |
| shared/ServiceGrid_Srv.java | 22 |
| shared/ServiceGrid_SrvAsync.java | 23 |
| shared/ServiceGrid_Vld.java | 32 |
| shared/TxtHServiceDetail_Mdl.java | 213 |

## UI labels / columns (client)
`Manual Tax`, `Service`, `Service Unit`, `SAC`, `GST Type`, `Cess Type`, `Valuation Type`, `Value`, `Amount`, `SGST/UGST`, `CGST`, `IGST`, `Cess`, `Cess Non Advol`, `VAT`, `Total Amount`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | ac_RoundOffGST |
| TreeMap<String, GstLocation_Mdl> | gstLocationTreeMap |

### ServiceDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | smfTag |
| String | smfText |
| String | serviceEvalTypeCode |
| String | serviceEvalTypeName |
| String | serviceEvalRate |
| String | isubCode |
| String | purchaseSalesAcCode |
| String | balancingAcCode |
| String | sgstOrUgstAmt |
| String | sgstAmt |
| String | ugstAmt |
| String | cgstAmt |
| String | igstAmt |
| String | cessAmt |
| String | cessNaAmt |
| String | taxableAmt |
| String | subTotal |
| String | netAmt |
| String | amount |
| String | gstRate |
| String | sgstOrUgstRate |
| String | sgstRate |
| String | ugstRate |
| String | cgstRate |
| String | igstRate |
| String | cessRate |
| String | cessNaRate |
| String | sgstAcCode |
| String | ugstAcCode |
| String | cgstAcCode |
| String | igstAcCode |
| String | cessAcCode |
| String | sgstRcmAcCode |
| String | ugstRcmAcCode |
| String | cgstRcmAcCode |
| String | igstRcmAcCode |
| String | cessRcmAcCode |
| boolean | manualTax |
| boolean | manualGstType |
| String | gstTypeCode |
| String | gstTypeName |
| String | cessEvalTypeCode |
| String | cessEvalTypeName |
| String | itsBasedOn |
| String | itemGSTNature |
| String | gstUnitCode |
| String | unitCode |
| String | unitName |
| String | cessNaUnitCode |
| String | cessNaUnitName |
| String | hsnCode |
| String | hsn |
| String | v_Sno |
| String | quantity |
| String | rate |
| String | manualServiceValue |
| String | tempSgstUgstPer |
| String | tempSgstUgst |
| String | tempSgstPer |
| String | tempSgst |
| String | tempUgstPer |
| String | tempUgst |
| String | tempCgstPer |
| String | tempCgst |
| String | tempIgstPer |
| String | tempIgst |
| String | tempCessPer |
| String | tempCess |
| String | tempCessNaRatePerUnit |
| String | tempCessNa |
| String | tempSubTotal |
| String | tempItemValue |
| List<ITS_Mdl> | lstITS |
| boolean | gstApplicable |

### TxtHServiceDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | smfTag |
| String | smfText |
| String | serviceEvalTypeCode |
| String | serviceEvalTypeName |
| String | serviceEvalRate |
| String | hsnCode |
| String | hsn |
| String | itemGSTNature |
| String | manualCode |
| String | uniqueId |
| String | itsBasedOn |
| String | gstUnitCode |
| String | skuCode |
| String | skuName |
| String | isubCode |
| String | balancingAc |
| String | purchaseAc |
| String | purchaseReturnAc |
| String | salesAc |
| String | salesReturnAc |
| String | tranferAc |
| String | transitAc |

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
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getITS | String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualITS | DmResult<List<GSTS_Mdl>> |
| getTxtHItemName | String balancingAcField,boolean isInService, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHServiceDetail_Mdl>> |
| getTxtHGstType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHValuationType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getCessNaUnit | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ServiceGrid_Vld`
```java
public class ServiceGrid_Vld {
    public static void chkSave(List<ServiceDetail_Mdl> grdServiceLst) {
        String msg = "";
        if (grdServiceLst.size() > 0) {
            for (ServiceDetail_Mdl serviceDetail_Mdl : grdServiceLst) {
                if (DmShared.iNull(serviceDetail_Mdl.getSharedKeyFieldCode()).longValue() > 0) {
                    if (DmShared.iNull(serviceDetail_Mdl.getSmfTag()).longValue() == 0) {
                        msg += "<br>" + serviceDetail_Mdl.getSharedKeyFieldName();
                    }
                    if (DmShared.vNull(serviceDetail_Mdl.getAmount()).doubleValue() <= 0) {
                        throw new DmException(0, "ServiceRow", DmShared.iNull(grdServiceLst.indexOf(serviceDetail_Mdl)).toString(), "Please enter Amount in line no. " + DmShared.iNull(grdServiceLst.indexOf(serviceDetail_Mdl) + 1).toString() + ".");
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

## Server `ServiceGrid_Server.java` (369 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultServicesForBifurcate`
`public static TreeMap<String, BifurcatedServicePopup_Mdl> getDefaultServicesForBifurcate(String balancingAcField, String siteCode, boolean isInService, Connection cnTemp) throws Exception`

- SQL 1: `SELECT im.code , im.name , im.smfCode , im.subCode AS iSubCode , {…} as balancingAc , im.sku , um.unit as unitName , smf.name AS smfName FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN serviceMappingField smf ON smf.code = im.smfCode LEFT JOIN unitMast um ON im.sku = um.code {…}`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`; `:ItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmShared.xNull(siteCode)`; `:IN` ← `STEnum.ServiceUsedIn.INWARD_TRANSACTION.getId()`; `:OUT` ← `STEnum.ServiceUsedIn.OUTWARD_TRANSACTION.getId()`; `:IO` ← `STEnum.ServiceUsedIn.BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId()`
- Other calls: `BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId`, `FREE_VALUE.getCode`, `FREE_VALUE.getName`, `INWARD_TRANSACTION.getId`, `OUTWARD_TRANSACTION.getId`

### `saveBifurcateService`
`public static void saveBifurcateService(String docid, String v_Sno, String tableName, TreeMap<String, BifurcatedServicePopup_Mdl> bifurcateServiceTreeMap, Connection cnTemp) throws Exception`

- SQL 1: `SELECT COUNT(*) AS CNT FROM {…}`
- SQL 2: `INSERT INTO {…} (docid,v_SNo) VALUES(:docid,:v_SNo)`
- SQL 3: `UPDATE {…} SET {…}`
- Params: `:docid` ← `DmShared.xNull(docid)`; `:v_SNo` ← `DmShared.xNull(v_Sno)`; `:docid` ← `DmShared.xNull(docid)`; `:v_SNo` ← `DmShared.xNull(v_Sno)`; `:docid` ← `DmShared.xNull(docid)`; `:v_SNo` ← `DmShared.xNull(v_Sno)`

### `getBifurcateService`
`public static TreeMap<String, BifurcatedServicePopup_Mdl> getBifurcateService(String docid, String v_Sno, String tableName, String tableDocIdField, String tableV_SnoField, String transTableName, String transTableDocIdField, String transTableServiceCodeField, String transTableEvalTypeField, String transTableUnitCode, String transTableIsubCodeField, String transTableBalancingAcField, Connection cnTemp) throws Exception`

- Params: `:FREE_VALUE_CODE` ← `DmEnum.ServiceEvalType.FREE_VALUE.getCode()`; `:FREE_VALUE_NAME` ← `DmEnum.ServiceEvalType.FREE_VALUE.getName()`; `:PERCENTAGE_OF_GOODS_TAXABLE_VALUE_CODE` ← `DmEnum.ServiceEvalType.PERCENTAGE_OF_GOODS_TAXABLE_VALUE.getCode()`; `:PERCENTAGE_OF_GOODS_TAXABLE_VALUE_NAME` ← `DmEnum.ServiceEvalType.PERCENTAGE_OF_GOODS_TAXABLE_VALUE.getName()`; `:PER_UNIT_CODE` ← `DmEnum.ServiceEvalType.PER_UNIT.getCode()`; `:PER_UNIT_NAME` ← `DmEnum.ServiceEvalType.PER_UNIT.getName()`; `:tableDocIdField` ← `DmShared.xNull(docid)`; `:tableV_SnoField` ← `DmShared.xNull(v_Sno)`
- Other calls: `FREE_VALUE.getCode`, `FREE_VALUE.getName`, `PERCENTAGE_OF_GOODS_TAXABLE_VALUE.getCode`, `PERCENTAGE_OF_GOODS_TAXABLE_VALUE.getName`, `PER_UNIT.getCode`, `PER_UNIT.getName`

### `getServiceColumnList`
`private static List<String> getServiceColumnList(String tableName, Connection cnTemp) throws Exception`

- SQL 1: `SELECT c.COLUMN_NAME from information_schema.columns c where c.TABLE_NAME = :tableName and (c.COLUMN_NAME like :columName1 or c.COLUMN_NAME like :columName2 )`
- Params: `:tableName` ← `tableName`; `:columName1` ← `AMOUNT_PREFIX + "Service%"`; `:columName2` ← `CODE_PREFIX + "Service%"`

### `getBifurcateServiceFieldHashMap`
`private static TreeMap<String, String[]> getBifurcateServiceFieldHashMap(String tableName, Connection cnTemp) throws Exception`

- Other calls: `ServiceMappingField.getViaText`

### `getBifurcateServiceFieldForUpdate`
`private static String getBifurcateServiceFieldForUpdate(String tableName, TreeMap<String, BifurcatedServicePopup_Mdl> bifurcateServiceTreeMap, Connection cnTemp) throws Exception`

- Other calls: `ServiceMappingField.getViaText`

## Server `ServiceGrid_SrvImpl.java` (358 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getITS`
`public DmResult<List<GSTS_Mdl>> getITS(String itemCode, String itemRate, String itsBasedOn, String locationCode, String gstTypeCode, boolean isManualITS)`

- Other calls: `STServer.getGSTS`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHServiceDetail_Mdl>> getTxtHItemName(String balancingAcField, boolean isInService, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.smfCode , smf.name AS smfName , im.serviceEvalTypeCode , im.serviceEvalRate , im.hsnCode , im.itemGSTNature , im.itsBasedOn , im.sku , im.manualCode , im.uniqueId , hm.hsn , um.gstUnitCode , um.unit as unitName , im.subCode AS iSubCode , {…} as balancingAc , im.purchaseAc, im.purchaseReturnAc, im.salesAc, im.salesReturnAc, im.transferAc, im.transitAc FROM itemMast im LEFT JOIN serviceMappingField smf ON smf.code = im.smfCode LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hm ON hm.code=im.hsnCode LEFT JOIN unitMast um ON im.sku = um.code`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTem`; `:ItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SV", "manualCode", "itemType", "code", cnTem`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId`, `FREE_VALUE.getCode`, `FREE_VALUE.getName`, `INWARD_TRANSACTION.getId`, `OUTWARD_TRANSACTION.getId`, `ServiceEvalType.getViaCode`

### `getTxtHGstType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT it.code, it.name, it.printSrNo FROM gstType it WHERE it.isActive = 1`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT ec.ac_RoundOffGST FROM enviroCompany ec`
- Other calls: `STServer.getSystemDefinedGstLocationMap`

### `getTxtHValuationType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHValuationType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ServiceEvalType.values`

### `getCessNaUnit`
`public DmResultPageing<List<KeyFieldExtended>> getCessNaUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as unitName,um.decimalPlaces as unitDecimalPlaces from unitMast um`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.overHeadLine.shared`, `paperbiz.selfToolkit.serviceGrid.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
