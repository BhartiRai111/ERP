# pb-master / swapPartyAccountMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/swapPartyAccountMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: subgroup
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Swap to (Text); Party/Account (Text)
- Lookups/suggestions: getTxtHPartyAc, getTxtHSwap
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SwapPartyAccountMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Swap | DmTextHelp | `txtHSwap` | `KeyField_PrpAcs, 7, true` |
| 2 | Party/Account Name | DmTextHelpMulti | `txtHPartyAc` | `TxtHPartyAc_PrpAcs, 7, true` |

**Grid / popup columns** (header, width, db field):
- `txtHSwap`: Name [Text, w0, Name]
- `txtHPartyAc`: Name [Text, w200, Name]; Manual Code [Text, w100, manualCode]; City [Text, w85, cityName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSwap` → `getTxtHSwap`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SwapTag` ← DmShared.xNull(txtHSwap.getTag())
- `SwapText` ← DmShared.xNull(txtHSwap.getText())
- `PartyAcTag` ← DmShared.xNull(txtHPartyAc.getTag())
- `PartyAcText` ← DmShared.xNull(txtHPartyAc.getText())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSwap.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSwap(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSwap).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSwap.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSwap).show();
```

`txtHSwap.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSwap.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSwap).show();
```

`txtHSwap.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.xNull(txtHSwap.getTag2nd()).trim().equalsIgnoreCase("")
|| !DmShared.xNull(txtHSwap.getTag2nd()).equalsIgnoreCase(txtHSwap.getTag())) {
txtHSwap.setTag2nd(DmShared.xNull(txtHSwap.getTag()));
```

`txtHPartyAc.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPartyAc(DmShared.xNull(txtHSwap.getTag()), helper, blnToEndOfFile, new AsyncC
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPartyAc).show();
public void onSuccess(DmResultPageing<List<TxtHPartyAc_Mdl>> result) {
if (result.isStatus()) {
txtHPartyAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPartyAc).show();
```

`txtHPartyAc.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPartyAc.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPartyAc).show();
```

`txtHPartyAc.addTextHelpMultiCallBack` → `getSelection()`
```java
if (DmShared.xNull(txtHPartyAc.getTag2nd()).trim().equalsIgnoreCase("")
|| !DmShared.xNull(txtHPartyAc.getTag2nd()).equalsIgnoreCase(txtHPartyAc.getTag())) {
txtHPartyAc.setTag2nd(DmShared.xNull(txtHPartyAc.getTag()));
```
- Confirm: "Are you sure? You want to Swap "
- Confirm: "Are you sure? You want to close."
- Confirm: "Are you sure? You want to Swap "
- Alert: "Swap is mandatory!"
- Alert: "Selected party/account swapped successfully!"
- Alert: "Swap is mandatory!"

## Files
| file | lines |
|---|---|
| client/SwapPartyAccountMaster.java | 259 |
| client/SwapPartyAccountMaster_Dsg.java | 192 |
| server/SwapPartyAccountMaster_SrvImpl.java | 259 |
| shared/SwapPartyAccountMaster_Mdl.java | 52 |
| shared/SwapPartyAccountMaster_Srv.java | 19 |
| shared/SwapPartyAccountMaster_SrvAsync.java | 19 |
| shared/SwapPartyAccountMaster_Vld.java | 13 |
| shared/TxtHPartyAc_Mdl.java | 33 |

## UI labels / columns (client)
`Swap`, `Party/Account Name`, ` After swapping please update nature and other details in the respective master ! `

## Models (shared DTOs)
### SwapPartyAccountMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | swapTag |
| String | swapText |
| String | partyAcTag |
| String | partyAcText |

### TxtHPartyAc_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | SwapPartyAccountMaster_Mdl mrTemp | DmResult<String> |
| getTxtHPartyAc | String name, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHPartyAc_Mdl>> |
| getTxtHSwap | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `SwapPartyAccountMaster_Vld`
```java
public class SwapPartyAccountMaster_Vld {
    public static void chkSave(SwapPartyAccountMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSwapTag(), "Swap to", 100);
        DmShared.isValidText(mrTemp.getPartyAcTag(), "Party/Account", 200);
    }
}
```

## Server `SwapPartyAccountMaster_SrvImpl.java` (259 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(SwapPartyAccountMaster_Mdl mrTemp)`

- SQL 1: `UPDATE subgroup SET filterNature =:filterNature WHERE subCode IN ( {…} :subCode {…} )`
- Params: `:filterNature` ← `DmShared.xNull(DmServer.getCodeViaManualCode("LG", "manualCode", "subgroupNature", "code",`; `:filterNature` ← `DmShared.xNull(DmServer.getCodeViaManualCode("PT", "manualCode", "subgroupNature", "code",`
- Other calls: `SwapPartyAccountMaster_Vld.chkSave`

### `getTxtHPartyAc`
`public DmResultPageing<List<TxtHPartyAc_Mdl>> getTxtHPartyAc(String swapCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode as code ,sg.name ,sg.manualCode , c.cityName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode {…}`
- Params: `:filterNature` ← `DmShared.xNull(DmServer.getCodeViaManualCode("PT", "manualCode", "subgroupNature", "code",`; `:filterNature` ← `DmShared.xNull(DmServer.getCodeViaManualCode("LG", "manualCode", "subgroupNature", "code",`

### `getTxtHSwap`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSwap(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
