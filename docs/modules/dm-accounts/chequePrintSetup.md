# dm-accounts / chequePrintSetup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/chequePrintSetup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: chequePrintingSetup
- Update tables: chequePrintingSetup
- Delete cascade (in order): chequePrintingSetup
- Duplicate checks on save:
  - `SELECT COUNT(bankCode) AS cnt FROM chequePrintingSetup WHERE LOWER(bankCode) = :bankCode AND Code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Bank Name (Text)
- Lookups/suggestions: getTxtHBankName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ChequePrintSetup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Bank Name | DmTextHelp | `txtHBankName` | `com.google.gwt.core.shared.BankName_PrpAcs, 6, true` |
| 2 | Top | DmTextBoxNumeric | `txtChequeDateTop` | `2, false, 6, false` |
| 3 | Left | DmTextBoxNumeric | `txtChequeDateLeft` | `2, false, 6, false` |
| 4 | Top | DmTextBoxNumeric | `txtFavourOfTop` | `2, false, 6, false` |
| 5 | Left | DmTextBoxNumeric | `txtFavourOfLeft` | `2, false, 6, false` |
| 6 | Top | DmTextBoxNumeric | `txtAmtInWordsTop` | `2, false, 6, false` |
| 7 | Left | DmTextBoxNumeric | `txtAmtInWordsLeft` | `2, false, 6, false` |
| 8 | Top | DmTextBoxNumeric | `txtAmountTop` | `2, false, 6, false` |
| 9 | Left | DmTextBoxNumeric | `txtAmountLeft` | `2, false, 6, false` |
| 10 | Top | DmTextBoxNumeric | `txtCrossChequeTop` | `2, false, 6, false` |
| 11 | Left | DmTextBoxNumeric | `txtCrossChequeLeft` | `2, false, 6, false` |
| 12 | Authorized Signatory | DmTextBox | `txtAuthSign` | `250, false` |
| 13 | Top | DmTextBoxNumeric | `txtAuthSignTop` | `2, false, 6, false` |
| 14 | Left | DmTextBoxNumeric | `txtAuthSignLeft` | `2, false, 6, false` |
| 15 | Company Name | DmTextBox | `txtCompanyName` | `250, false` |
| 16 | Top | DmTextBoxNumeric | `txtCompanyNameTop` | `2, false, 6, false` |
| 17 | Left | DmTextBoxNumeric | `txtCompanyNameLeft` | `2, false, 6, false` |
| 18 | Width | DmTextBoxNumeric | `txtPaperWidthMM` | `2, false, 6, false` |
| 19 | - | DmTextBoxNumeric | `txtPaperWidth` | `2, false, 6, false` |
| 20 | Height | DmTextBoxNumeric | `txtPaperHeightMM` | `2, false, 6, false` |
| 21 | - | DmTextBoxNumeric | `txtPaperHeight` | `2, false, 6, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Bank Name [Text, w0, bankName]; Authorized Signatory [Text, w170, authSign]; Company Name [Text, w170, companyName]; Paper Width [Text, w110, paperWidth]; Paper Height [Text, w110, paperHeight]
- `txtHBankName`: Bank [Text, w100, bankName]; Manual Code [Text, w100, manualCode]; City [Text, w100, cityName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHBankName` → `getTxtHBankName`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `BankCode` ← DmShared.xNull(txtHBankName.getTag())
- `ChequeDateTop` ← DmClient.vNull(txtChequeDateTop.getText(), "0.00")
- `ChequeDateLeft` ← DmClient.vNull(txtChequeDateLeft.getText(), "0.00")
- `FavourOfTop` ← DmClient.vNull(txtFavourOfTop.getText(), "0.00")
- `FavourOfLeft` ← DmClient.vNull(txtFavourOfLeft.getText(), "0.00")
- `AmtInWordsTop` ← DmClient.vNull(txtAmtInWordsTop.getText(), "0.00")
- `AmtInWordsLeft` ← DmClient.vNull(txtAmtInWordsLeft.getText(), "0.00")
- `AmtTop` ← DmClient.vNull(txtAmountTop.getText(), "0.00")
- `AmtLeft` ← DmClient.vNull(txtAmountLeft.getText(), "0.00")
- `CrossChequeTop` ← DmClient.vNull(txtCrossChequeTop.getText(), "0.00")
- `CrossChequeLeft` ← DmClient.vNull(txtCrossChequeLeft.getText(), "0.00")
- `AuthSign` ← DmShared.xNull(txtAuthSign.getText())
- `AuthSignTop` ← DmClient.vNull(txtAuthSignTop.getText(), "0.00")
- `AuthSignLeft` ← DmClient.vNull(txtAuthSignLeft.getText(), "0.00")
- `CompanyName` ← DmShared.xNull(txtCompanyName.getText())
- `CompanyNameTop` ← DmClient.vNull(txtCompanyNameTop.getText(), "0.00")
- `CompanyNameLeft` ← DmClient.vNull(txtCompanyNameLeft.getText(), "0.00")
- `PaperWidth` ← DmClient.vNull(txtPaperWidth.getText(), "0.00")
- `PaperHeight` ← DmClient.vNull(txtPaperHeight.getText(), "0.00")

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHBankName.setText` ← data.getBankName()
- `txtHBankName.setTag` ← data.getBankCode()
- `txtChequeDateTop.setText` ← data.getChequeDateTop()
- `txtChequeDateLeft.setText` ← data.getChequeDateLeft()
- `txtFavourOfTop.setText` ← data.getFavourOfTop()
- `txtFavourOfLeft.setText` ← data.getFavourOfLeft()
- `txtAmtInWordsTop.setText` ← data.getAmtInWordsTop()
- `txtAmtInWordsLeft.setText` ← data.getAmtInWordsLeft()
- `txtAmountTop.setText` ← data.getAmtTop()
- `txtAmountLeft.setText` ← data.getAmtLeft()
- `txtCrossChequeTop.setText` ← data.getCrossChequeTop()
- `txtCrossChequeLeft.setText` ← data.getCrossChequeLeft()
- `txtAuthSign.setText` ← data.getAuthSign()
- `txtAuthSignTop.setText` ← data.getAuthSignTop()
- `txtAuthSignLeft.setText` ← data.getAuthSignLeft()
- `txtCompanyName.setText` ← data.getCompanyName()
- `txtCompanyNameTop.setText` ← data.getCompanyNameTop()
- `txtCompanyNameLeft.setText` ← data.getCompanyNameLeft()
- `txtPaperWidth.setText` ← data.getPaperWidth()
- `txtPaperHeight.setText` ← data.getPaperHeight()
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtHBankName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtHBankName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtHBankName.setFocus(true);
```

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

`topControl.addSaveHandeller` → `saveClicked()`
```java
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ChequePrintSetup.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`copyPopup.addCallBack` → `onOk()`
```java
bankCode = data.getBankCode();
txtChequeDateTop.setText(data.getChequeDateTop());
txtChequeDateLeft.setText(data.getChequeDateLeft());
txtFavourOfTop.setText(data.getFavourOfTop());
txtFavourOfLeft.setText(data.getFavourOfLeft());
txtAmtInWordsTop.setText(data.getAmtInWordsTop());
txtAmtInWordsLeft.setText(data.getAmtInWordsLeft());
txtAmountTop.setText(data.getAmtTop());
txtAmountLeft.setText(data.getAmtLeft());
txtCrossChequeTop.setText(data.getCrossChequeTop());
...
```

`txtHBankName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBankName(topControl.getMode().equalsIgnoreCase("New") ? bankCode : "", false,
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBankName).show();
public void onSuccess(DmResultPageing<List<BankName_Mdl>> result) {
if (result.isStatus()) {
txtHBankName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBankName).show();
```

`txtHBankName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBankName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBankName).show();
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<Find_Mdl>> result) {
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
dmFind.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), dmFind.getTextBox()).show();
```

`dmFind.addHelpGridCallBack` → `getSelectedRow()`
```java
flwForm.remove(dmFind);
flwForm.add(vrtInnerForm);
if (blnStatus) {
topControlMoveRec(tRow.getSharedKeyFieldCode());
```

**Top control options:** setOtherRequired(true), setRefreshRequired(false), setDisplay(true)

**Menu items (Other button):** Copy

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Can be use in New Mode only"

### Screen `CopyPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Bank Name | DmTextHelp | `txtHBankName` | `com.google.gwt.core.shared.BankName_PrpAcs, 6, true` |

**Grid / popup columns** (header, width, db field):
- `txtHBankName`: Bank [Text, w100, bankName]; Manual Code [Text, w100, manualCode]; City [Text, w100, cityName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHBankName` → `getTxtHBankName`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHBankName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHBankName("", true, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBankName).show();
public void onSuccess(DmResultPageing<List<BankName_Mdl>> result) {
if (result.isStatus()) {
txtHBankName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBankName).show();
```

`txtHBankName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBankName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBankName).show();
```
- Alert: "Bank Name is mandatory"

## Files
| file | lines |
|---|---|
| client/ChequePrintSetup.java | 455 |
| client/ChequePrintSetup_Dsg.java | 500 |
| client/CopyPopup.java | 188 |
| client/CopyPopup_Dsg.java | 160 |
| server/ChequePrintSetup_SrvImpl.java | 546 |
| shared/BankName_Mdl.java | 43 |
| shared/ChequePrintSetup_Mdl.java | 194 |
| shared/ChequePrintSetup_Srv.java | 22 |
| shared/ChequePrintSetup_SrvAsync.java | 22 |
| shared/ChequePrintSetup_Vld.java | 12 |
| shared/Find_Mdl.java | 68 |

## UI labels / columns (client)
`Bank Name`, `Cheque Date`, `Top`, `Left`, `Favour Of`, `Amount In Words`, `Amount`, `Cross Cheque`, `Authorized Signatory`, `Company Name`, `Paper`, `Width`, `Height`

## Models (shared DTOs)
### BankName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | bankCode |
| String | bankName |
| String | authSign |
| String | companyName |
| String | paperWidth |
| String | paperHeight |

### ChequePrintSetup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | bankName |
| String | bankCode |
| String | chequeDateTop |
| String | chequeDateLeft |
| String | crossChequeTop |
| String | crossChequeLeft |
| String | favourOfTop |
| String | favourOfLeft |
| String | amtInWordsTop |
| String | amtInWordsLeft |
| String | amtTop |
| String | amtLeft |
| String | authSign |
| String | authSignTop |
| String | authSignLeft |
| String | companyName |
| String | companyNameTop |
| String | companyNameLeft |
| String | paperWidth |
| String | paperHeight |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey, String bankCode, boolean isCopy | DmResult<ChequePrintSetup_Mdl> |
| saveRecord | ChequePrintSetup_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHBankName | String docId, boolean isCopy, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<BankName_Mdl>> |

## Validator `ChequePrintSetup_Vld`
```java
public class ChequePrintSetup_Vld {
    public static void chkSave(ChequePrintSetup_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getBankCode().trim(), "Bank Name", 100);
    }
}
```

## Server `ChequePrintSetup_SrvImpl.java` (546 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select cps.Code From chequePrintingSetup as cps Left join subgroup as sg on cps.bankCode =sg.subCode Order By sg.name`

### `getMoveRec`
`public DmResult<ChequePrintSetup_Mdl> getMoveRec(String strKey, String bankCode, boolean isCopy)`

- SQL 1: `SELECT cps.code , cps.bankCode , sg.name as bankName , cps.chequeDateTop , cps.chequeDateLeft , cps.favourOfTop , cps.favourOfLeft , cps.AmtInWordsTop , cps.AmtInWordsLeft , cps.AmtTop , cps.AmtLeft , cps.crossChequeTop , cps.crossChequeLeft , cps.authSignatory , cps.authSignatoryTop , cps.authSignatoryLeft , cps.companyName , cps.companyNameTop , cps.companyNameLeft , cps.paperWidth , cps.paperHeight , cps.u_Name , cps.preparedBy FROM chequePrintingSetup as cps LEFT JOIN subgroup AS sg ON cps.bankCode=sg.subCode`
- Params: `:bankCode` ← `DmShared.iNull(bankCode).toString()`; `:code` ← `strKey`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Delete From chequePrintingSetup Where code= :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(ChequePrintSetup_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(bankCode) AS cnt FROM chequePrintingSetup WHERE LOWER(bankCode) = :bankCode AND Code <> :code`
- SQL 2: `INSERT INTO chequePrintingSetup ( code , bankCode , chequeDateTop , chequeDateLeft , favourOfTop , favourOfLeft , crossChequeTop , crossChequeLeft , AmtInWordsTop , AmtInWordsLeft , AmtTop , AmtLeft , authSignatory , authSignatoryTop , authSignatoryLeft , companyName , companyNameTop , companyNameLeft , paperWidth , paperHeight , preparedBy , U_EntDt , transferDt ) VALUES ( :code , :bankCode , :chequeDateTop , :chequeDateLeft , :favourOfTop , :favourOfLeft , :crossChequeTop , :crossChequeLeft , :AmtInWordsTop , :AmtInWordsLeft , :AmtTop , :AmtLeft , :authSignatory , :authSignatoryTop , :authSignatoryLeft , :companyName , :companyNameTop , :companyNameLeft , :paperWidth , :paperHeight , :uName , :uEntDt , NULL)`
- SQL 3: `Update chequePrintingSetup Set bankCode = :bankCode, chequeDateTop = :chequeDateTop, chequeDateLeft = :chequeDateLeft, favourOfTop = :favourOfTop, favourOfLeft = :favourOfLeft, AmtInWordsTop = :AmtInWordsTop, AmtInWordsLeft = :AmtInWordsLeft, crossChequeTop=:crossChequeTop, crossChequeLeft=:crossChequeLeft, AmtTop = :AmtTop, AmtLeft = :AmtLeft, authSignatory = :authSignatory, authSignatoryTop = :authSignatoryTop, authSignatoryLeft = :authSignatoryLeft, companyName = :companyName, companyNameTop = :companyNameTop, companyNameLeft = :companyNameLeft, paperWidth = :paperWidth, paperHeight = :paperHeight, U_Name = :uName, U_EntDt = GETDATE() Where code = :code`
- Params: `:bankCode` ← `DmShared.xNull(mrTemp.getBankCode()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:bankCode` ← `DmShared.foriegnKey(mrTemp.getBankCode())`; `:chequeDateTop` ← `DmServer.vNull(mrTemp.getChequeDateTop(), "0.00")`; `:chequeDateLeft` ← `DmServer.vNull(mrTemp.getChequeDateLeft(), "0.00")`; `:favourOfTop` ← `DmServer.vNull(mrTemp.getFavourOfTop(), "0.00")`; `:favourOfLeft` ← `DmServer.vNull(mrTemp.getFavourOfLeft(), "0.00")`; `:crossChequeTop` ← `DmServer.vNull(mrTemp.getCrossChequeTop(), "0.00")`; `:crossChequeLeft` ← `DmServer.vNull(mrTemp.getCrossChequeLeft(), "0.00")`; `:AmtInWordsTop` ← `DmServer.vNull(mrTemp.getAmtInWordsTop(), "0.00")`; `:AmtInWordsLeft` ← `DmServer.vNull(mrTemp.getAmtInWordsLeft(), "0.00")`; `:AmtTop` ← `DmServer.vNull(mrTemp.getAmtTop(), "0.00")`; `:AmtLeft` ← `DmServer.vNull(mrTemp.getAmtLeft(), "0.00")`; `:authSignatory` ← `DmShared.xNull(mrTemp.getAuthSign())`; `:authSignatoryTop` ← `DmServer.vNull(mrTemp.getAuthSignTop(), "0.00")`; `:authSignatoryLeft` ← `DmServer.vNull(mrTemp.getAuthSignLeft(), "0.00")`; `:companyName` ← `DmShared.xNull(mrTemp.getCompanyName())`; `:companyNameTop` ← `DmServer.vNull(mrTemp.getCompanyNameTop(), "0.00")`; `:companyNameLeft` ← `DmServer.vNull(mrTemp.getCompanyNameLeft(), "0.00")`; `:paperWidth` ← `DmServer.vNull(mrTemp.getPaperWidth(), "0.00")`; `:paperHeight` ← `DmServer.vNull(mrTemp.getPaperHeight(), "0.00")`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:bankCode` ← `DmShared.foriegnKey(mrTemp.getBankCode())`; `:chequeDateTop` ← `DmServer.vNull(mrTemp.getChequeDateTop(), "0.00")`; `:chequeDateLeft` ← `DmServer.vNull(mrTemp.getChequeDateLeft(), "0.00")`; `:favourOfTop` ← `DmServer.vNull(mrTemp.getFavourOfTop(), "0.00")`; `:favourOfLeft` ← `DmServer.vNull(mrTemp.getFavourOfLeft(), "0.00")`; `:AmtInWordsTop` ← `DmServer.vNull(mrTemp.getAmtInWordsTop(), "0.00")`; `:AmtInWordsLeft` ← `DmServer.vNull(mrTemp.getAmtInWordsLeft(), "0.00")`; `:crossChequeTop` ← `DmServer.vNull(mrTemp.getCrossChequeTop(), "0.00")`; `:crossChequeLeft` ← `DmServer.vNull(mrTemp.getCrossChequeLeft(), "0.00")`; `:AmtTop` ← `DmServer.vNull(mrTemp.getAmtTop(), "0.00")`; `:AmtLeft` ← `DmServer.vNull(mrTemp.getAmtLeft(), "0.00")`; `:authSignatory` ← `DmShared.xNull(mrTemp.getAuthSign())`; `:authSignatoryTop` ← `DmServer.vNull(mrTemp.getAuthSignTop(), "0.00")`; `:authSignatoryLeft` ← `DmServer.vNull(mrTemp.getAuthSignLeft(), "0.00")`; `:companyName` ← `DmShared.xNull(mrTemp.getCompanyName())`; `:companyNameTop` ← `DmServer.vNull(mrTemp.getCompanyNameTop(), "0.00")`; `:companyNameLeft` ← `DmServer.vNull(mrTemp.getCompanyNameLeft(), "0.00")`; `:paperWidth` ← `DmServer.vNull(mrTemp.getPaperWidth(), "0.00")`; `:paperHeight` ← `DmServer.vNull(mrTemp.getPaperHeight(), "0.00")`; `:uName` ← `DmServer.getSsnUserName(this)`; `:code` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Invalid key." | " Bank Name already exists."
- Other calls: `ChequePrintSetup_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT cps.Code,sg.name AS bankName ,cps.bankCode,cps.authSignatory as authSign, cps.companyName, cps.paperWidth,cps.paperHeight FROM chequePrintingSetup AS cps LEFT JOIN subgroup AS sg ON cps.bankCode=sg.subCode`

### `getTxtHBankName`
`public DmResultPageing<List<BankName_Mdl>> getTxtHBankName(String docId, boolean isCopy, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode , sg.name as bankName , sg.manualCode , sg.cityCode , c.cityName FROM subgroup as sg LEFT JOIN subgroupNature as sgn on sg.nature = sgn.code LEFT JOIN city c on c.cityCode = sg.cityCode`
- Params: `:subCode` ← `DmShared.iNull(docId).toString()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.accounts.bulkChequePrinting.shared`, `dataman.accounts.chequePrintSetup.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | " Bank Name already exists." |

## Notes (hand-written)
_No hand-written notes yet._
