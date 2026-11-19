# dm-utility / docIdMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/docIdMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHSite
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DocIdMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site Name | DmTextHelp | `txtHSite` | `KeyField_PrpAcs, 10` |
| 2 | Voucher Prefix | DmTextBox | `txtVoucherPrefix` | `35, true` |

Other widgets (not in tab list): `dmFind` (DmFind), `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w0, Name]
- `dmFind`: Search Code [Text, w120, SearchCode]; Voucher Type [Text, w100, V_Type]; Description [Text, w150, Description]; Voucher Prefix [Text, w100, V_Prefix]; Voucher No. [Text, w100, serialNo]; Site Name [Text, w150, Site_Name]; Site Code [Text, w100, Code]
- `dmGrd`: Voucher Type [Text, w150]; Voucher Description [Text, w740]; Series [Text, w50]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSite` → `getTxtHSite`
- Find popup → `getFind`

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHSite.setText` ← data.getSiteName()
- `txtHSite.setTag` ← data.getSiteCode()
- `txtVoucherPrefix.setText` ← data.getVoucherPrefix()

**On New click:**
- `txtHSite.setFocus(true)`
- `txtVoucherPrefix.setText(DmClient.getVoucherPrefix())`
- `txtVoucherPrefix.setEnabled(false)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
txtHSite.setFocus(true);
txtVoucherPrefix.setText(DmClient.getVoucherPrefix());
txtVoucherPrefix.setEnabled(false);
dmGrd.getGrid().getStore().clear();
dmGrd.getGrid().getStore().add(new GrdDocIdMaster_Mdl());
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtHSite.setFocus(true);
txtVoucherPrefix.setText(DmClient.getVoucherPrefix());
txtVoucherPrefix.setEnabled(false);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
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
topControlMoveRec(strFetchCode);
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(DocIdMaster.this);
```

`txtHSite.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, "", new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
FillRecords(tRow.getSharedKeyFieldCode(), txtVoucherPrefix.getText());
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(DmClient.getVoucherPrefix(), helper, blnToEndOfFile, new AsyncCallback<DmRes
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

**Top control options:** setRefreshRequired(false), setOtherRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/DocIdMaster.java | 277 |
| client/DocIdMaster_Dsg.java | 199 |
| server/DocIdMaster_SrvImpl.java | 302 |
| shared/DocIdMaster_Mdl.java | 52 |
| shared/DocIdMaster_Srv.java | 34 |
| shared/DocIdMaster_SrvAsync.java | 34 |
| shared/DocIdMaster_Vdl.java | 17 |
| shared/Find_Mdl.java | 77 |
| shared/GrdDocIdMaster_Mdl.java | 31 |

## UI labels / columns (client)
`Site Name`, `Voucher Prefix`

## Models (shared DTOs)
### GrdDocIdMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | voucherDescription |
| String | voucherSerise |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | serachCode |
| String | voucherType |
| String | voucherDescription |
| String | prefix |
| String | voucherSerise |
| String | siteName |
| String | siteCode |

### DocIdMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | siteName |
| String | siteCode |
| String | voucherPrefix |
| List<GrdDocIdMaster_Mdl> | grdData |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<DocIdMaster_Mdl> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile, String strCode | DmResultPageing<List<KeyFieldExtended>> |
| getFind | String vPrefix, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| fillGrid | String siteCode, String vPrefix | DmResult<List<GrdDocIdMaster_Mdl>> |

## Server `DocIdMaster_SrvImpl.java` (302 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT DISTINCT CONCAT(Site_Code , '|' , V_Prefix) AS SearchCode FROM DocIdMaster ORDER BY CONCAT(Site_Code , '|' , V_Prefix)`

### `getMoveRec`
`public DmResult<DocIdMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT VT.V_Type,VT.Description,DM.V_Prefix,VT.Short_Name, isNull(DM.serialNo,0) AS serialNo,SM.Name AS SiteName, SM.Code AS SiteCode FROM DocIdMaster DM LEFT JOIN Voucher_Type VT ON VT.V_Type = DM.V_Type LEFT JOIN SiteMast SM ON DM.Site_Code = SM.Code WHERE CONCAT(DM.Site_Code , '|' , DM.V_Prefix) = :code order by Description`
- Params: `:code` ← `strKey`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile, String strCode)`

- SQL 1: `SELECT SM.Code,SM.Name FROM SiteMast SM`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(String vPrefix, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CONCAT(SM.Code ,'|', DM.V_Prefix) AS SearchCode,VT.V_Type,VT.Description,DM.V_Prefix,DM.serialNo, SM.Name AS Site_Name,SM.Code FROM DocIdMaster DM LEFT JOIN SiteMast SM ON DM.Site_Code = SM.Code LEFT JOIN Voucher_Type VT ON DM.V_Type = VT.V_Type WHERE DM.V_Prefix = :v_Prefix`
- Params: `:v_Prefix` ← `vPrefix`

### `fillGrid`
`public DmResult<List<GrdDocIdMaster_Mdl>> fillGrid(String siteName, String vprefix)`

- SQL 1: `SELECT VT.V_Type,VT.Description,VT.Short_Name FROM Voucher_Type VT LEFT JOIN DocIdMaster DM ON VT.V_Type = DM.V_Type AND DM.Site_Code = :siteCode AND DM.V_Prefix = :vPrefix WHERE isNull(DM.V_Type,'') = '' ORDER BY VT.V_Type`
- Params: `:siteCode` ← `siteName`; `:vPrefix` ← `vprefix`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.docIdMaster.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
