# pb-lab / sampleLabel

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/lab/src/sampleLabel/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSampleItem, getTxtHRefrenceType, getTxtHSampleAssign
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SampleLabel`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdSample` | `GrdSample_PrpAcs, 20` |

Other widgets (not in tab list): `txtSearchValueAsset` (DmTextBox), `txtSearchValueLedger` (DmTextBox), `txtSearchValueDown` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `grdSample`:  [Text, w30]; Sample No [Text, w150, recId]; Sample Date [Text, w100, v_Date]; Refrence Type [Text, w150, refType]; Refrence No. [Text, w150, refRecId]; Item Name [Text, w350, itemName]; Test Method [Text, w150, testTemplateName]; Quantity [Text, w100, sampleQty]; Unit [Text, w80, unit]

**Lookup / suggestion / find sources (server RPC called):**
- `grdSample` → `getSampleDetail`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`settingPopup.addCallBack` → `onOk()`
```java
setting_Mdl = sm;
lstkeyFields.clear();
grdSample.getDmGrid().commitChanges();
grdSample.reQuery();
```

`grdSample.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(true).getSampleDetail(searchValue_Mdl, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<SampleLabel_Mdl>> result) {
if (result.isStatus()) {
grdSample.setData(result.getData(), result.getPaging());
List<SampleLabel_Mdl> listIndentDetails = grdSample.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < lstkeyFields.size(); i++) {
KeyFieldExtended kf = lstkeyFields.get(i);
for (int j = 0; j < listIndentDetails.size(); j++) {
...
```

`grdSample.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdSample.setData(result.getData(), result.getPaging());
List<SampleLabel_Mdl> listIndentDetails = grdSample.getDmGrid().getGrid().getStore().getAll();
for (int i = 0; i < lstkeyFields.size(); i++) {
KeyFieldExtended kf = lstkeyFields.get(i);
for (int j = 0; j < listIndentDetails.size(); j++) {
SampleLabel_Mdl details_Mdl = listIndentDetails.get(j);
if (DmShared.xNull(kf.getSharedKeyFieldCode()).trim().equalsIgnoreCase(DmShared.xNull(details_Mdl.getSharedKeyFieldCode(
grdSample.getDmGrid().getGrid().getStore().get(j).setIsChecked("1");
grdSample.getDmGrid().commitChanges();
...
```

`grdSample.addCallBack` → `getParam()`
```java
return setting_Mdl;
```
- Confirm: "Are you sure? You want to close."
- Alert: "Please select items to print labels."

### Screen `Setting`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | From Date | DmTextBox | `txtFromDate` | `15, false` |
| 2 | To Date | DmTextBox | `txtToDate` | `15, false` |
| 3 | Refrence Type | DmTextHelpMulti | `txtHRefrenceType` | `KeyField_PrpAcs, 10` |
| 4 | Sample Item | DmTextHelpMulti | `txtHItem` | `KeyField_PrpAcs, 10` |
| 5 | Sample Assign | DmTextHelp | `txtHSampleAssign` | `KeyField_PrpAcs, 3, true` |

**Grid / popup columns** (header, width, db field):
- `txtHRefrenceType`: Name [Text, w0, description]
- `txtHItem`: Name [Text, w0, itemName]
- `txtHSampleAssign`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSampleAssign` → `getTxtHSampleAssign`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtFromDate.addValueChangeHandler` → `onValueChange()`
```java
txtFromDate.setText(DmClient.returnDate(txtFromDate.getText()));
```

`txtToDate.addValueChangeHandler` → `onValueChange()`
```java
txtToDate.setText(DmClient.returnDate(txtToDate.getText()));
```

`txtHRefrenceType.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRefrenceType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRefrenceType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRefrenceType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefrenceType).show();
```

`txtHRefrenceType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRefrenceType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRefrenceType).show();
```

`txtHSampleAssign.addKeyDownHandler` → `onKeyDown()`
```java
txtHSampleAssign.addTextHelpCallBack(new DmTextHelp.TextHelpCallBack<KeyFieldExtended>() {
public void populateData(DmHelper helper, boolean blnToEndOfFile, boolean blnCallBackWait) {
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSampleAssign(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSampleAssign).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSampleAssign.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleAssign).show();
...
```

`txtHSampleAssign.addKeyDownHandler` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSampleAssign(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSampleAssign).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSampleAssign.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleAssign).show();
```

`txtHSampleAssign.addKeyDownHandler` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSampleAssign.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleAssign).show();
```

`txtHSampleAssign.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSampleAssign(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSampleAssign).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSampleAssign.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleAssign).show();
```

`txtHSampleAssign.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSampleAssign.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSampleAssign).show();
```

`txtHItem.addTextHelpMultiCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSampleItem(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```
- Alert: "From Date should be less than or equal to To Date."
- Alert: "Please fill sample assign."

## Files
| file | lines |
|---|---|
| client/SampleLabel.java | 368 |
| client/SampleLabel_Dsg.java | 222 |
| client/Setting.java | 244 |
| client/Setting_Dsg.java | 203 |
| server/SampleLabel_SrvImpl.java | 292 |
| shared/SampleLabel_Mdl.java | 95 |
| shared/SampleLabel_Srv.java | 19 |
| shared/SampleLabel_SrvAsync.java | 19 |
| shared/Setting_Mdl.java | 86 |

## UI labels / columns (client)
`From Date`, `To Date`, `Refrence Type`, `Sample Item`, `Sample Assign`

## Models (shared DTOs)
### Setting_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | fromDate |
| String | toDate |
| String | itemCode |
| String | itemName |
| String | refrenceCode |
| String | refrenceName |
| String | sampleAssignCode |
| String | sampleAssignName |

### SampleLabel_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | sampleNo |
| String | sampleDate |
| String | referenceType |
| String | referenceNo |
| String | itemName |
| String | testTemplate |
| String | quantity |
| String | itemUnit |
| String | isChecked |

## RPC interface
| method | params | returns |
|---|---|---|
| getSampleDetail | Setting_Mdl searchValue_Mdl, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<SampleLabel_Mdl>> |
| getTxtHSampleItem | DmHelper helper, boolean blnEndofFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRefrenceType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSampleAssign | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<KeyFieldExtended> |

## Server `SampleLabel_SrvImpl.java` (292 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getSampleDetail`
`public DmResultPageing<List<SampleLabel_Mdl>> getSampleDetail(Setting_Mdl setting_Mdl, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select smpl.docId, smpl.recId, smpl.v_Date, vt.short_Name as refType,smpl.refRecId, im.name as itemName, tmm.Description as testTemplateName, smpl.sampleQty, um.unit from labSample smpl LEFT JOIN voucher_Type vt ON vt.v_Type = smpl.refVType LEFT JOIN itemMast im ON im.code = smpl.itemCode LEFT JOIN testTemplateMast tmm ON tmm.Code = smpl.testTemplateCode LEFT JOIN itemImpurityMast iim ON iim.Code = smpl.itemImpurityCode LEFT JOIN unitMast um ON um.code = iim.sampleUnit where 1=1 {…}`
- Params: `:fromv_Date` ← `DmServer.convertDate(setting_Mdl.getFromDate())`; `:tov_Date` ← `DmServer.convertDate(setting_Mdl.getToDate())`

### `getTxtHSampleItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSampleItem(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.code as itemCode, im.name as itemName from itemMast im left join itemSiteList sl on sl.code = im.code where sl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHRefrenceType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRefrenceType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select DISTINCT(smpl.refVType) as refVType, vt.description from labSample smpl LEFT JOIN voucher_Type vt ON vt.v_Type = smpl.refVType`

### `getDefaultValues`
`public DmResult<KeyFieldExtended> getDefaultValues()`


### `getTxtHSampleAssign`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSampleAssign(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.openSourceReport.client`, `paperbiz.lab.sampleLabel.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
