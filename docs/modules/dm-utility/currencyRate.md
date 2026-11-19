# dm-utility / currencyRate

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/currencyRate/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): currencyRateList
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Rate (Text)
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `CurrencyRatePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Rate | DmTextBoxNumeric | `txtRate` | `7, false, 12, true` |

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtRate.setEnabled(isEditable)`
- `flwOk.setVisible(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtRate.setEnabled(isEditable);
txtRate.setFocus(true);
flwOk.setVisible(true);
```

### Screen `CurrencyRate`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Local Currency | DmTextBox | `txtLocalCurrency` | `10, true` |

Other widgets (not in tab list): `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdItemDetail`: Currency [Text, w200, currency]; Description [Text, w200, description]; Small Currency [Text, w200, smallCurrency]; Rate [Text, w100]

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtLocalCurrency.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtLocalCurrency.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtLocalCurrency.setTag` ← data.getSharedKeyFieldCode()
- `txtLocalCurrency.setText` ← data.getSharedKeyFieldName()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
topControlDelete(topControl.getDocId());
```

`topControl.addMoveRecHandeller` → `moveRec()`
```java
topControlMoveRec(strFetchCode);
```

`topControl.addSaveHandeller` → `saveClicked()`
```java
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(CurrencyRate.this);
```

`ratePopup.addCallBack` → `onOk()`
```java
grdItemDetail.getGrid().getSelectionModel().getSelectedItem().setRate(DmClient.vNull(rate, "0.00#####"));
grdItemDetail.commitChanges();
```

`ratePopup.addCallBack` → `onClose()`
```java
ratePopup.hide();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/CurrencyRate.java | 302 |
| client/CurrencyRatePopup.java | 123 |
| client/CurrencyRatePopup_Dsg.java | 119 |
| client/CurrencyRate_Dsg.java | 178 |
| server/CurrencyRate_SrvImpl.java | 299 |
| shared/CurrencyRate_Mdl.java | 28 |
| shared/CurrencyRate_Srv.java | 19 |
| shared/CurrencyRate_SrvAsync.java | 19 |
| shared/CurrencyRate_Vld.java | 12 |
| shared/Grid_Mdl.java | 82 |
| shared/TxtHCountryLocale_Mdl.java | 28 |

## UI labels / columns (client)
`Rate`, `Local Currency`

## Models (shared DTOs)
### CurrencyRate_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| List<Grid_Mdl> | grd |

### TxtHCountryLocale_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | countryName |
| String | currencyName |

### Grid_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | currencyCode |
| String | currency |
| String | description |
| String | smallCurrency |
| String | rate |
| String | srNo |
| String | isStatus |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<CurrencyRate_Mdl> |
| saveRecord | CurrencyRate_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getDefaultValues |  | DmResult<CurrencyRate_Mdl> |

## Validator `CurrencyRate_Vld`
```java
public class CurrencyRate_Vld {
    public static void chkSave(Grid_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getRate()), "Rate", 100);
    }
}
```

## Server `CurrencyRate_SrvImpl.java` (299 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.vNull`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select Code From currencyRateList ;`

### `getMoveRec`
`public DmResult<CurrencyRate_Mdl> getMoveRec(String key)`


### `getMoveRec`
`public DmResult<CurrencyRate_Mdl> getMoveRec(String key, Connection cnTemp)`

- SQL 1: `SELECT ec.localCurrencyCode, cm.name AS localCurrency FROM enviroCompany AS ec LEFT JOIN currency AS cm ON cm.code = ec.localCurrencyCode`
- SQL 2: `SELECT Vcr.code, cm.code AS currencyCode, cm.name AS currency, cm.smallCurrency, cm.description , ISNULL(Vcr.rate, '0') AS rate , Vcr.u_Name, Vcr.u_EntDt FROM currency cm LEFT JOIN ( SELECT * FROM currencyRateList cr WHERE cr.code = :localCurrencyCode ) Vcr ON Vcr.currencyCode = cm.code WHERE cm.code <> :localCurrencyCode`
- Params: `:localCurrencyCode` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From currencyRateList Where Code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(CurrencyRate_Mdl mrTemp)`

- SQL 1: `DELETE FROM currencyRateList`
- Params: `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:currencyCode` ← `DmShared.xNull(detail.getCurrencyCode())`; `:rate` ← `DmServer.vNull(detail.getRate())`; `:site_code` ← `DmServer.getSsnSiteCode(this)`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key."
- Other calls: `Grid_Mdl.chkSave`

### `getDefaultValues`
`public DmResult<CurrencyRate_Mdl> getDefaultValues()`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.currencyRate.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
