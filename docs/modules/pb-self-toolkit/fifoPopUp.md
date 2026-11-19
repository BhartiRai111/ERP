# pb-self-toolkit / fifoPopUp

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/fifoPopUp/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: At least one parameter selection is mandatory.
- Lookups/suggestions: getTxtHItemName, getTxtHCat, getTxtHGroup, getTxtHDepartment
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `FifoPopUp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Status : | DmTextArea | `txtLog` | `` |

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settings.addCallBack` → `onOk()`
```java
fifoSettingMdl = values;
```
- Alert: "FIFO Process Completed"

### Screen `Settings`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Name | DmTextHelpMulti | `txtHItemName` | `KeyField_PrpAcs, 10` |
| 2 | Department | DmTextHelpMulti | `txtHDepartment` | `KeyField_PrpAcs, 5` |
| 3 | Item Category | DmTextHelpMulti | `txtHCat` | `KeyField_PrpAcs, 10` |
| 4 | Item Group | DmTextHelpMulti | `txtHGroup` | `KeyField_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHGroup`: Name [Text, w1, name]
- `txtHCat`: Name [Text, w1, name]
- `txtHItemName`: Name [Text, w1, name]; Manual Code [Text, w100, manualCode]
- `txtHDepartment`: Name [Text, w1, Name]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDepartment.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDepartment(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHDepartment.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDepartment).show();
```

`txtHCat.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCat(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCat).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCat.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCat).show();
```

`txtHCat.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCat.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCat).show();
```

`txtHGroup.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroup).show();
```

`txtHGroup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGroup).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```
- Alert: "At least one parameter selection is mandatory!"

## Files
| file | lines |
|---|---|
| client/FifoPopUp.java | 228 |
| client/FifoPopUp_Dsg.java | 170 |
| client/Settings.java | 284 |
| client/Settings_Dsg.java | 243 |
| server/FifoPopUp_SrvImpl.java | 434 |
| shared/FifoPopUp_Srv.java | 23 |
| shared/FifoPopUp_SrvAsync.java | 23 |
| shared/FifoPopUp_Vld.java | 20 |
| shared/FifoSetting_Mdl.java | 103 |

## UI labels / columns (client)
`Logs : `, `Status :`, `Item Name`, `Department`, `Item Category`, `Item Group`

## Models (shared DTOs)
### FifoSetting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemCode |
| String | itemName |
| String | departmentCode |
| String | departmentName |
| String | categoryCode |
| String | categoryName |
| String | groupCode |
| String | groupName |
| String | siteCode |
| String | siteName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHCat | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDepartment | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| startFifo | FifoSetting_Mdl mdl | DmResult<String> |
| readLogFile | String filePath | DmResult<KeyFieldExtended> |

## Validator `FifoPopUp_Vld`
```java
public class FifoPopUp_Vld {
    public FifoPopUp_Vld() {
    }
    public static void chkSave(FifoSetting_Mdl mrTemp) {
        if (DmShared.xNull(mrTemp.getItemCode()).trim().equalsIgnoreCase("")
                && DmShared.xNull(mrTemp.getGroupCode()).trim().equalsIgnoreCase("")
                && DmShared.xNull(mrTemp.getDepartmentCode()).trim().equalsIgnoreCase("")
                && DmShared.xNull(mrTemp.getCategoryCode()).trim().equalsIgnoreCase("")) {
            throw new DmException(200, "At least one parameter selection is mandatory.");
        }
    }
}
```

## Server `FifoPopUp_SrvImpl.java` (434 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnPLTCode`, `DmServer.getSsnSiteCode`, `DmShared.xNull`

### `getTxtHItemName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.Code,im.name,im.manualCode FROM itemMast im WHERE im.filterItemType in(:FM,:SM)`
- Params: `:FM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`; `:SM` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getTxtHCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code, ic.name FROM itemCat ic`

### `getTxtHGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig`

### `getTxtHDepartment`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDepartment(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT dm.subCode AS code, sg.name FROM departmentMast dm LEFT JOIN subGroup sg ON sg.subCode = dm.subCode WHERE sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `startFifo`
`public DmResult<String> startFifo(FifoSetting_Mdl mdl)`


### `runFIFO`
`private void runFIFO(String itemCode, String departmentCode, String siteCode, String categoryCode, String groupCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT im.code as itemCode FROM itemMast im left join itemType it ON it.code=im.filterItemType WHERE it.manualCode in ('FM','SM') {…}`
- Other calls: `Stock_Server.getFile`, `Stock_Server.runStockFIFO_Process`

### `readLogFile`
`public DmResult<KeyFieldExtended> readLogFile(String filePath)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.stock.server`, `paperbiz.selfToolkit.fifoPopUp.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
