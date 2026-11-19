# pb-assets / propertiesPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/assets/src/propertiesPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: assetPropertiesLog
- Update tables: assetPropertiesDetail
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Properties (Amount); Asset Name (Amount)
- Lookups/suggestions: getTxtHProperties, getTxtHVisibilityNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PropertiesPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Properties | DmTextHelp | `txtHPropeties` | `Property_PrAcs, 7, true` |
| 2 | Description | DmTextBox | `txtAssociatedValue` | `30, false` |
| 3 | Date | DmTextBox | `txtAssociatedDate` | `11, false` |
| 4 | Remark | DmRemarkTextBox | `txtRemark` | `250, false` |
| 5 | Due Date | DmTextBox | `txtDueOn` | `11, false` |
| 6 | Service No. | DmTextBox | `txtServiceNo` | `11, false` |
| 7 | Date | DmTextBox | `txtLastServiceDueOn` | `11, false` |
| 8 | Amount | DmTextBoxNumeric | `txtLastServiceAmount` | `11, false, 10, false` |

Other widgets (not in tab list): `txtPrintSNo` (DmTextBoxNumeric), `txtHVisNature` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHVisNature`: Name [Text, w0, name]
- `txtHPropeties`: Name [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHPropeties` → `getTxtHProperties`
- `txtHVisNature` → `getTxtHVisibilityNature`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHPropeties.setEnabled(!isHelpDisable)`
- `txtPrintSNo.setEnabled(!isHelpDisable)`
- `txtHVisNature.setEnabled(!isHelpDisable)`

**`manageDisplay()` logic (condensed code):**
```java
txtHPropeties.setEnabled(!isHelpDisable);
txtPrintSNo.setEnabled(!isHelpDisable);
txtHVisNature.setEnabled(!isHelpDisable);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHPropeties.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHProperties(assetItem, helper, blnToEndOfFile, new AsyncCallback<DmResultPagei
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPropeties).show();
public void onSuccess(DmResultPageing<List<PropertiesPoup_Mdl>> result) {
if (result.isStatus()) {
txtHPropeties.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPropeties).show();
```

`txtHPropeties.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPropeties.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPropeties).show();
```

`txtHPropeties.addTextHelpCallBack` → `getSelectedRow()`
```java
txtPrintSNo.setText(DmShared.xNull(tRow.getSNo()));
txtHVisNature.setTag(DmShared.xNull(tRow.getNatureCode()));
```

`txtHVisNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHVisibilityNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVisNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVisNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVisNature).show();
```

`txtHVisNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVisNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVisNature).show();
```
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Property is mandatory."
- Alert: "Property already exists."

## Files
| file | lines |
|---|---|
| client/PropertiesPopup.java | 375 |
| client/PropertiesPopup_Dsg.java | 289 |
| server/PropertiesPopup_Server.java | 124 |
| server/PropertiesPopup_SrvImpl.java | 152 |
| shared/PropertiesPopup_Srv.java | 18 |
| shared/PropertiesPopup_SrvAsync.java | 19 |
| shared/PropertiesPopup_Vld.java | 14 |
| shared/PropertiesPoup_Mdl.java | 108 |

## UI labels / columns (client)
`Associated`, `Last Serive Detail`, `Properties`, `Description`, `Date`, `Nature`, `Remark`, `Due Date`, `Service No.`, `Amount`

## Models (shared DTOs)
### PropertiesPoup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | assetCode |
| String | serviceNo |
| String associatedDate, | associatedValue |
| String lastServiceDueOn, | lastServiceAmount |
| String dueOn, | remarks |
| String SNo, natureCode, | natureName |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | boolean isDelete,PropertiesPoup_Mdl mrTemp | DmResult<String> |
| getTxtHProperties | String assetItemCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<PropertiesPoup_Mdl>> |
| getTxtHVisibilityNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `PropertiesPopup_Vld`
```java
public class PropertiesPopup_Vld {
    public static void chkSave(PropertiesPoup_Mdl mrTemp) {
        DmShared.isValidAmount(mrTemp.getSharedKeyFieldCode(), "Properties", 100);
        DmShared.isValidAmount(mrTemp.getAssetCode(), "Asset Name", 200);
    }
}
```

## Server `PropertiesPopup_SrvImpl.java` (152 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(boolean isDelete, PropertiesPoup_Mdl mrTemp)`

- Errors: "Invalid key."
- Other calls: `PropertiesPopup_Server.save`, `PropertiesPopup_Vld.chkSave`

### `getTxtHProperties`
`public DmResultPageing<List<PropertiesPoup_Mdl>> getTxtHProperties(String assetItemCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select aip.propertyCode as code, apm.name,aip.SNo, aip.nature from assetItemProperties aip left join assetPropertyMast apm on apm.code = aip.propertyCode where aip.code = :assetItemCode`
- Params: `:assetItemCode` ← `DmShared.xNull(assetItemCode)`

### `getTxtHVisibilityNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVisibilityNature(DmHelper helper, boolean blnToEndOfFile)`


## Server `PropertiesPopup_Server.java` (124 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getServerTimestamp`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `save`
`public static void save(PropertiesPoup_Mdl mrTemp, boolean isDelete, Connection cnTemp, DmRSSPrivate rss)`

- SQL 1: `select count(*) as cnt from assetPropertiesDetail apd where apd.assetCode = :assetCode and propertyCode = :propertyCode`
- SQL 2: `update assetPropertiesDetail set associatedDate = :associatedDate, associatedValue = :associatedValue, dueDate = :dueDate, lastServiceNo = :lastServiceNo, lastServiceAmount = :lastServiceAmount, lastServiceDate = :lastServiceDate, remark = :remark , sno = :sno , nature = :nature where assetCode = :assetCode and propertyCode = :propertyCode`
- SQL 3: `delete from assetPropertiesDetail where assetCode = :assetCode and propertyCode = :propertyCode`
- SQL 4: `Insert Into assetPropertiesLog(assetCode, propertyCode, associatedDate, associatedValue, dueDate, lastServiceDate, lastServiceNo, lastServiceAmount, remark, u_Name, u_EntDt , isDeleted ,sno,nature ) Values (:assetCode, :propertyCode, :associatedDate, :associatedValue, :dueDate, :lastServiceDate, :lastServiceNo, :lastServiceAmount, :remark, :u_Name, :u_EntDt, :isDeleted , :sno , :nature)`
- Params: `:assetCode` ← `DmShared.xNull(mrTemp.getAssetCode())`; `:propertyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getAssetCode())`; `:propertyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:associatedDate` ← `DmServer.convertDate(mrTemp.getAssociatedDate())`; `:associatedValue` ← `DmShared.xNull(mrTemp.getAssociatedValue())`; `:dueDate` ← `DmServer.convertDate(mrTemp.getDueOn())`; `:lastServiceNo` ← `DmShared.xNull(mrTemp.getServiceNo())`; `:lastServiceAmount` ← `DmServer.vNull(mrTemp.getLastServiceAmount(), "0.00")`; `:lastServiceDate` ← `DmServer.convertDate(mrTemp.getLastServiceDueOn())`; `:remark` ← `DmShared.xNull(mrTemp.getRemarks())`; `:SNo` ← `DmShared.iNull(mrTemp.getSNo()).toString()`; `:nature` ← `DmShared.iNull(mrTemp.getNatureCode()).toString()`; `:assetCode` ← `DmShared.xNull(mrTemp.getAssetCode())`; `:propertyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:associatedDate` ← `DmServer.convertDate(mrTemp.getAssociatedDate())`; `:associatedValue` ← `DmShared.xNull(mrTemp.getAssociatedValue())`; `:dueDate` ← `DmServer.convertDate(mrTemp.getDueOn())`; `:lastServiceDate` ← `DmServer.convertDate(mrTemp.getLastServiceDueOn())`; `:lastServiceNo` ← `DmShared.xNull(mrTemp.getServiceNo())`; `:lastServiceAmount` ← `DmShared.xNull(mrTemp.getLastServiceAmount())`; `:remark` ← `DmShared.xNull(mrTemp.getRemarks())`; `:sno` ← `DmShared.iNull(mrTemp.getSNo()).toString()`; `:nature` ← `DmShared.iNull(mrTemp.getNatureCode()).toString()`; `:assetCode` ← `DmShared.foriegnKey(mrTemp.getAssetCode())`; `:propertyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:assetCode` ← `DmShared.xNull(mrTemp.getAssetCode())`; `:propertyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:associatedDate` ← `DmServer.convertDate(mrTemp.getAssociatedDate())`; `:associatedValue` ← `DmShared.xNull(mrTemp.getAssociatedValue())`; `:dueDate` ← `DmServer.convertDate(mrTemp.getDueOn())`; `:lastServiceDate` ← `DmServer.convertDate(mrTemp.getLastServiceDueOn())`; `:lastServiceNo` ← `DmShared.xNull(mrTemp.getServiceNo())`; `:lastServiceAmount` ← `DmShared.xNull(mrTemp.getLastServiceAmount())`; `:remark` ← `DmShared.xNull(mrTemp.getRemarks())`; `:u_Name` ← `DmServer.getSsnUserName(rss)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:isDeleted` ← `isDelete ? 1 : 0`; `:sno` ← `DmShared.iNull(mrTemp.getSNo()).toString()`; `:nature` ← `DmShared.iNull(mrTemp.getNatureCode()).toString()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `paperbiz.assets.propertiesPopup.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
