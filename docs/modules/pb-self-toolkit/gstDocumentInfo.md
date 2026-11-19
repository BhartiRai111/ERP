# pb-self-toolkit / gstDocumentInfo

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/gstDocumentInfo/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHDocumentType, getTxtHDocumentNature, getTxtHGSTItemType, getTxtHGSTItemNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GSTDocumentInfo`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | GST Document Type | DmTextHelp | `txtHGstDocumentType` | `KeyField_PrpAcs, 7, true, true` |
| 2 | GST Document Nature | DmTextHelp | `txtHGstDocumentNature` | `KeyField_PrpAcs, 7, true, true` |
| 3 | GST Item Type | DmTextHelp | `txtHGstItemType` | `KeyField_PrpAcs, 4, true, true` |
| 4 | GST Item Nature | DmTextHelp | `txtHGstItemNature` | `KeyField_PrpAcs, 5, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHGstDocumentType`: Name [Text, w50, name]
- `txtHGstDocumentNature`: Name [Text, w50, name]
- `txtHGstItemType`: Name [Text, w50, name]
- `txtHGstItemNature`: Name [Text, w50, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHGstDocumentType` → `getTxtHDocumentType`
- `txtHGstDocumentNature` → `getTxtHDocumentNature`
- `txtHGstItemNature` → `getTxtHGSTItemNature`
- `txtHGstItemType` → `getTxtHGSTItemType`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHGstDocumentType.setEnabled(flag)`
- `txtHGstDocumentNature.setEnabled(flag)`
- `txtHGstItemType.setEnabled(flag)`
- `txtHGstItemNature.setEnabled(flag)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**`manageDisplay()` logic (condensed code):**
```java
boolean flag = false;
if (gstdi_Mdl != null && (DmShared.xNull(mode).endsWith("New")
|| DmShared.xNull(mode).endsWith("Edit"))) {
flag = true;
txtHGstDocumentType.setEnabled(flag);
txtHGstDocumentNature.setEnabled(flag);
txtHGstItemType.setEnabled(flag);
txtHGstItemNature.setEnabled(flag);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHGstDocumentType.addTextHelpCallBack` → `populateData()`
```java
refreshGSTDocInfoParam_Mdl();
srvAsync.invokeRPC(blnCallBackWait).getTxtHDocumentType(voucherCategory, helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstDocumentType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstDocumentType).show();
```

`txtHGstDocumentType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstDocumentType).show();
```

`txtHGstDocumentType.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean flag = false;
if (!DmShared.xNull(txtHGstDocumentType.getTag2nd()).equalsIgnoreCase(txtHGstDocumentType.getTag())) {
txtHGstDocumentNature.setTag("");
txtHGstDocumentNature.setTag2nd("");
txtHGstDocumentNature.setText("");
txtHGstDocumentType.setTag2nd(DmShared.xNull(txtHGstDocumentType.getTag()));
flag = true;
if (t1Row != null) {
if (!DmShared.xNull(t1Row.getSharedPreparedBy()).trim().equalsIgnoreCase("")) {
DmEnum.GstDocumentNature gstDocNature = DmEnum.GstDocumentNature.getViaShortName(DmShared.xNull(t1Row.getSharedPreparedB
...
```

`txtHGstDocumentNature.addTextHelpCallBack` → `populateData()`
```java
DmEnum.GstDocumentType gstDocType = DmEnum.GstDocumentType.getViaShortName(DmShared.xNull(txtHGstDocumentType.getTag()))
srvAsync.invokeRPC(blnCallBackWait).getTxtHDocumentNature(gstDocType != null ? gstDocType.getGstDocNatureShortName() : "
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstDocumentNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstDocumentNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstDocumentNature).show();
```

`txtHGstDocumentNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstDocumentNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstDocumentNature).show();
```

`txtHGstDocumentNature.addTextHelpCallBack` → `getSelectedRow()`
```java
onChange();
```

`txtHGstItemNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSTItemNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstItemNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstItemNature).show();
```

`txtHGstItemNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstItemNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstItemNature).show();
```

`txtHGstItemNature.addTextHelpCallBack` → `getSelectedRow()`
```java
onChange();
```

`txtHGstItemType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSTItemType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGstItemType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGstItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstItemType).show();
```

`txtHGstItemType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGstItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHGstItemType).show();
```

`txtHGstItemType.addTextHelpCallBack` → `getSelectedRow()`
```java
onChange();
```

## Files
| file | lines |
|---|---|
| client/GSTDocumentInfo.java | 325 |
| client/GSTDocumentInfo_Dsg.java | 154 |
| server/GSTDocumentInfo_SrvImpl.java | 282 |
| shared/DefaultValue_Mdl.java | 33 |
| shared/GSTDocumentInfoParam_Mdl.java | 30 |
| shared/GSTDocumentInfo_Srv.java | 19 |
| shared/GSTDocumentInfo_SrvAsync.java | 20 |
| shared/GSTDocumentInfo_Vld.java | 40 |
| shared/TxtHServiceDetail_Mdl.java | 213 |

## UI labels / columns (client)
`GST Document Type`, `GST Document Nature`, `GST Item Type`, `GST Item Nature`

## Models (shared DTOs)
### DefaultValue_Mdl
| type | field |
|---|---|
| String | documentTypeCSV |
| GstDocument_Mdl | gstdi_Mdl |

### GSTDocumentInfoParam_Mdl
| type | field |
|---|---|
| String | voucherCategory |
| String | mode |

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

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValues | String voucherCategory | DmResult<DefaultValue_Mdl> |
| getTxtHDocumentType | String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDocumentNature | String gstDocNatureShortName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSTItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSTItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `GSTDocumentInfo_Vld`
```java
public class GSTDocumentInfo_Vld {
    public static enum ErrorType {
        GST_DOCUMENT_TYPE(999910), GST_DOCUMENT_NATURE(999920), ITEM_GST_TYPE(999930), ITEM_GST_NATURE(999940);
        private int errorCode;
        ErrorType(int errorCode) {
            this.errorCode = errorCode;
        }
        public int getErrorCode() {
            return errorCode;
        }
    }
    public static ErrorType getErrorTypeViaCode(int errorCode) {
        ErrorType errorCodeType = null;
        for (ErrorType type : ErrorType.values()) {
            if (DmShared.iNull(errorCode).intValue() == type.getErrorCode()) {
                return type;
            }
        }
        return errorCodeType;
    }
    public static void validate(GstDocument_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getGstDocTypeCode(), "GST Document Type", ErrorType.GST_DOCUMENT_TYPE.getErrorCode());
        DmShared.isValidText(mrTemp.getGstDocNatureCode(), "GST Document Nature", ErrorType.GST_DOCUMENT_NATURE.getErrorCode());
        DmShared.isValidText(mrTemp.getItemGstTypeCode(), "Item GST Type", ErrorType.ITEM_GST_TYPE.getErrorCode());
        DmShared.isValidText(mrTemp.getItemGSTNatureCode(), "Item GST Nature", ErrorType.ITEM_GST_NATURE.getErrorCode());
    }
}
```

## Server `GSTDocumentInfo_SrvImpl.java` (282 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues(String voucherCategory)`


### `getDefaultValue`
`public DefaultValue_Mdl getDefaultValue(String voucherCategory, Connection cnTemp)`

- SQL 1: `SELECT vt.v_Type, vt.documentTypeCode, vt.documentNatureCode, vt.itemGstType , vt.itemGSTNature, vt.documentTypeCSV FROM voucher_Type vt WHERE vt.category = :category`
- Params: `:category` ← `DmShared.xNull(voucherCategory)`
- Errors: "'Voucher Type' is mandatory for 'GST Document Info'!"
- Other calls: `GstDocumentNature.getViaShortName`, `GstDocumentType.getViaShortName`

### `getTxtHDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentType(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GstDocumentType.getViaShortName`, `GstDocumentType.values`

### `getTxtHDocumentNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentNature(String gstDocNatureShortName, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GstDocumentNature.values`

### `getTxtHGSTItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT git.code , git.name , git.manualCode FROM gstItemType git`

### `getTxtHGSTItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTItemNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GSTNature.values`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.gst.shared`, `paperbiz.selfToolkit.gstDocumentInfo.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getDefaultValue | `if (DmShared.xNull(voucherCategory).trim().equalsIgnoreCase(""))` | "'Voucher Type' is mandatory for 'GST Document Info'!" |

## Notes (hand-written)
GST document ka info popup: lookups document type, document nature, GST item type, GST item nature (`getTxtHDocumentType`, `getTxtHDocumentNature`, `getTxtHGSTItemType`, `getTxtHGSTItemNature`) aur defaults. Values dm-base enums (`GstDocumentType`, `GstDocumentNature`) se.
