# pb-master / itemMakeMaster

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/itemMakeMaster/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemMakeMast
- Update tables: itemMakeMast
- Delete cascade (in order): itemMakeMast
- Duplicate checks on save:
  - `SELECT COUNT(companyName) AS cnt FROM itemMakeMast WHERE LOWER(companyName) = :companyName AND code <> :code`
  - `SELECT COUNT(shortName) AS cnt FROM itemMakeMast WHERE LOWER(shortName) = :shortName AND code <> :code`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Item Make (Text); Short Name (Text)
- Lookups/suggestions: getSuggestionCompanyName, getSuggestionShortName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ItemMakeMaster`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Make | DmSuggestBox | `txtCompanyName` | `50, 10, true, "companyName", DmDataType.STRING` |
| 2 | Short Name | DmSuggestBox | `txtShortName` | `20, 10, true, "shortName", DmDataType.STRING` |
| 3 | Contact Person | DmTextBox | `txtLclPerson` | `35, false` |
| 4 | Address | DmTextBox | `txtLclAddress` | `100, false` |
| 5 | - | DmTextBox | `txtLclAddress1` | `100, false` |
| 6 | Mobile | DmTextBox | `txtLclMobile` | `30, false` |
| 7 | Fax | DmTextBox | `txtLclFax` | `30, false` |
| 8 | Phone (O) | DmTextBox | `txtLclPhoneO` | `30, false` |
| 9 | Phone (R) | DmTextBox | `txtLclPhoneR` | `30, false` |
| 10 | Email | DmTextBox | `txtLclEmail` | `100, false` |
| 11 | Contact Person | DmTextBox | `txtRegPerson` | `35, false` |
| 12 | Address | DmTextBox | `txtRegAddress` | `100, false` |
| 13 | - | DmTextBox | `txtRegAddress1` | `100, false` |
| 14 | Mobile | DmTextBox | `txtRegMobile` | `30, false` |
| 15 | Fax | DmTextBox | `txtRegFax` | `30, false` |
| 16 | Phone (O) | DmTextBox | `txtRegPhoneO` | `30, false` |
| 17 | Phone (R) | DmTextBox | `txtRegPhoneR` | `30, false` |
| 18 | Email | DmTextBox | `txtRegEmail` | `100, false` |
| 19 | Contact Person | DmTextBox | `txtNatPerson` | `35, false` |
| 20 | Address | DmTextBox | `txtNatAddress` | `100, false` |
| 21 | - | DmTextBox | `txtNatAddress1` | `100, false` |
| 22 | Mobile | DmTextBox | `txtNatMobile` | `30, false` |
| 23 | Fax | DmTextBox | `txtNatFax` | `30, false` |
| 24 | Phone (O) | DmTextBox | `txtNatPhoneO` | `30, false` |
| 25 | Phone (R) | DmTextBox | `txtNatPhoneR` | `30, false` |
| 26 | Email | DmTextBox | `txtNatEmail` | `100, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `dmFind`: Item Make [Text, w0, companyName]; Short Name [Text, w100, shortName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtCompanyName` → `getSuggestionCompanyName`
- `txtShortName` → `getSuggestionShortName`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtCompanyName.getText())
- `ShortName` ← DmShared.xNull(txtShortName.getText())
- `LclPerson` ← DmShared.xNull(txtLclPerson.getText())
- `LclAddress` ← DmShared.xNull(txtLclAddress.getText())
- `LclAddress1` ← DmShared.xNull(txtLclAddress1.getText())
- `LclMobile` ← DmShared.xNull(txtLclMobile.getText())
- `LclFax` ← DmShared.xNull(txtLclFax.getText())
- `LclPhoneR` ← DmShared.xNull(txtLclPhoneR.getText())
- `LclPhoneO` ← DmShared.xNull(txtLclPhoneO.getText())
- `LclEmail` ← DmShared.xNull(txtLclEmail.getText())
- `RegPerson` ← DmShared.xNull(txtRegPerson.getText())
- `RegAddress` ← DmShared.xNull(txtRegAddress.getText())
- `RegAddress1` ← DmShared.xNull(txtRegAddress1.getText())
- `RegMobile` ← DmShared.xNull(txtRegMobile.getText())
- `RegFax` ← DmShared.xNull(txtRegFax.getText())
- `RegPhoneR` ← DmShared.xNull(txtRegPhoneR.getText())
- `RegPhoneO` ← DmShared.xNull(txtRegPhoneO.getText())
- `RegEmail` ← DmShared.xNull(txtRegEmail.getText())
- `NatPerson` ← DmShared.xNull(txtNatPerson.getText())
- `NatAddress` ← DmShared.xNull(txtNatAddress.getText())
- `NatAddress1` ← DmShared.xNull(txtNatAddress1.getText())
- `NatMobile` ← DmShared.xNull(txtNatMobile.getText())
- `NatFax` ← DmShared.xNull(txtNatFax.getText())
- `NatPhoneR` ← DmShared.xNull(txtNatPhoneR.getText())
- `NatPhoneO` ← DmShared.xNull(txtNatPhoneO.getText())
- `NatEmail` ← DmShared.xNull(txtNatEmail.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `userControl.setTextBlog` ← preparedByBlog, data.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, data.getSharedModifiedBy()
- `txtCompanyName.setText` ← data.getSharedKeyFieldName()
- `txtShortName.setText` ← data.getShortName()
- `txtLclAddress.setText` ← data.getLclAddress()
- `txtLclAddress1.setText` ← data.getLclAddress1()
- `txtLclMobile.setText` ← data.getLclMobile()
- `txtLclPhoneR.setText` ← data.getLclPhoneR()
- `txtLclPhoneO.setText` ← data.getLclPhoneO()
- `txtLclEmail.setText` ← data.getLclEmail()
- `txtLclFax.setText` ← data.getLclFax()
- `txtLclPerson.setText` ← data.getLclPerson()
- `txtRegAddress.setText` ← data.getRegAddress()
- `txtRegAddress1.setText` ← data.getRegAddress1()
- `txtRegMobile.setText` ← data.getRegMobile()
- `txtRegPhoneR.setText` ← data.getRegPhoneR()
- `txtRegPhoneO.setText` ← data.getRegPhoneO()
- `txtRegEmail.setText` ← data.getRegEmail()
- `txtRegFax.setText` ← data.getRegFax()
- `txtRegPerson.setText` ← data.getRegPerson()
- `txtNatAddress.setText` ← data.getNatAddress()
- `txtNatAddress1.setText` ← data.getNatAddress1()
- `txtNatMobile.setText` ← data.getNatMobile()
- `txtNatPhoneR.setText` ← data.getNatPhoneR()
- `txtNatPhoneO.setText` ← data.getNatPhoneO()
- `txtNatEmail.setText` ← data.getNatEmail()
- `txtNatFax.setText` ← data.getNatFax()
- `txtNatPerson.setText` ← data.getNatPerson()
- userControl.setTextBlog(preparedByBlog, data.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, data.getSharedModifiedBy())

**On New click:**
- `clear()`
- `txtCompanyName.setFocus(true)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`modifyReason.addCallBack` → `onOk()`
```java
try {
ItemMakeMaster.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
txtCompanyName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtCompanyName.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton().equals(Dialog.PredefinedButton.YES)) {
showModifyReason();
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
if (DmShared.xNull(topControl.getMode()).equalsIgnoreCase("Edit")) {
showModifyReason();
} else {
topControlSave();
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(ItemMakeMaster.this);
```

`txtCompanyName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionCompanyName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtCompanyName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtCompanyName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCompanyName).show();
```

`txtCompanyName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtCompanyName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCompanyName).show();
```

`txtShortName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionShortName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Lis
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtShortName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtShortName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtShortName).show();
```

`txtShortName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtShortName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtShortName).show();
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

**Top control options:** setOtherRequired(false), setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."

## Files
| file | lines |
|---|---|
| client/ItemMakeMaster.java | 554 |
| client/ItemMakeMaster_Dsg.java | 644 |
| server/ItemMakeMaster_SrvImpl.java | 689 |
| shared/Find_Mdl.java | 23 |
| shared/ItemMakeMaster_Mdl.java | 249 |
| shared/ItemMakeMaster_Srv.java | 26 |
| shared/ItemMakeMaster_SrvAsync.java | 26 |
| shared/ItemMakeMaster_Vld.java | 15 |

## UI labels / columns (client)
`Item Make`, `Short Name`, `Item Make Detail`, `Contact Person`, `Address`, `Mobile`, `Fax`, `Phone (O)`, `Phone (R)`, `Email`

## Models (shared DTOs)
### ItemMakeMaster_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | lclPerson |
| String | lclAddress |
| String | lclAddress1 |
| String | lclMobile |
| String | lclFax |
| String | lclPhoneR |
| String | lclPhoneO |
| String | lclEmail |
| String | regPerson |
| String | regAddress |
| String | regAddress1 |
| String | regMobile |
| String | regFax |
| String | regPhoneR |
| String | regPhoneO |
| String | regEmail |
| String | natPerson |
| String | natAddress |
| String | natAddress1 |
| String | natMobile |
| String | natFax |
| String | natPhoneR |
| String | natPhoneO |
| String | natEmail |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionCompanyName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionShortName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<ItemMakeMaster_Mdl> |
| saveRecord | ItemMakeMaster_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |

## Validator `ItemMakeMaster_Vld`
```java
public class ItemMakeMaster_Vld {
    public static void chkSave(ItemMakeMaster_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName().trim(), "Item Make", 100);
        DmShared.isValidText(mrTemp.getShortName().trim(), "Short Name", 200);
        DmShared.isValidEMail(mrTemp.getLclEmail(), 300);
        DmShared.isValidEMail(mrTemp.getRegEmail(), 400);
        DmShared.isValidEMail(mrTemp.getNatEmail(), 500);
    }
}
```

## Server `ItemMakeMaster_SrvImpl.java` (689 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmShared.foriegnKey`, `DmShared.removeSpace`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT mm.code FROM itemMakeMast mm WHERE filterItemType = :filterItemType ORDER BY mm.companyName`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getSuggestionCompanyName`
`public DmResultPageing<List<String>> getSuggestionCompanyName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT companyName FROM itemMakeMast`

### `getMoveRec`
`public DmResult<ItemMakeMaster_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT mm.code, mm.companyName, mm.shortName, mm.lclAddress1, mm.lclAddress2, mm.lclEmail, mm.lclFax, mm.lclMobile, mm.lclOPhone, mm.lclPerson, mm.lclRPhone, mm.regAddress1, mm.regAddress2, mm.regEmail, mm.regFax, mm.regMobile, mm.regOPhone, mm.regPerson, mm.regRPhone, mm.natAddress1, mm.natAddress2, mm.natEmail, mm.natFax, mm.natMobile, mm.natOPhone, mm.natPerson, mm.natRPhone, mm.preparedBY,mm.u_name,mm.preparedDt, mm.preparedByIP,mm.u_EntDt FROM itemMakeMast mm WHERE mm.code = :docId and mm.filterItemType = :filterItemType ORDER BY mm.companyName ;`
- Params: `:docId` ← `strKey`; `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From itemMakeMast Where code=:code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(ItemMakeMaster_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(companyName) AS cnt FROM itemMakeMast WHERE LOWER(companyName) = :companyName AND code <> :code`
- SQL 2: `SELECT COUNT(shortName) AS cnt FROM itemMakeMast WHERE LOWER(shortName) = :shortName AND code <> :code`
- SQL 3: `INSERT INTO itemMakeMast (CODE, companyName, shortName,filterItemType, lclPerson, lclMobile, lclFax, lclRPhone, lclOPhone, lclEmail, lclAddress1, lclAddress2, regPerson, regMobile, regFax, regRPhone, regOPhone, regEmail, regAddress1, regAddress2, natPerson, natMobile, natFax, natRPhone, natOPhone, natEmail, natAddress1, natAddress2, preparedBY, preparedDt, transferDt, preparedByIP) VALUES(:code, :companyName, :shortName, :filterItemType, :lclPerson, :lclMobile, :lclFax, :lclRPhone, :lclOPhone, :lclEmail, :lclAddress1, :lclAddress2, :regPerson, :regMobile, :regFax, :regRPhone, :regOPhone, :regEmail, :regAddress1, :regAddress2, :natPerson, :natMobile, :natFax, :natRPhone, :natOPhone, :natEmail, :natAddress1, :natAddress2, :preparedBY, :preparedDt, NULL, :preparedByIP) ;`
- SQL 4: `UPDATE itemMakeMast SET companyName = :companyName, shortName = :shortName, filterItemType = :filterItemType, lclPerson = :lclPerson, lclMobile = :lclMobile, lclFax = :lclFax, lclRPhone = :lclRPhone, lclOPhone = :lclOPhone, lclEmail = :lclEmail, lclAddress1 = :lclAddress1, lclAddress2 = :lclAddress2, regPerson = :regPerson, regMobile = :regMobile, regFax = :regFax, regRPhone = :regRPhone, regOPhone = :regOPhone, regEmail = :regEmail, regAddress1 = :regAddress1, regAddress2 = :regAddress2, natPerson = :natPerson, natMobile = :natMobile, natFax = :natFax, natRPhone = :natRPhone, natOPhone = :natOPhone, natEmail = :natEmail, natAddress1 = :natAddress1, natAddress2 = :natAddress2, u_Name = :userName, u_EntDt = :uEntDt, transferDt = NULL WHERE CODE = :code ;`
- Params: `:companyName` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName()).toLowerCase().trim()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:companyName` ← `mrTemp.getSharedKeyFieldName()`; `:shortName` ← `mrTemp.getShortName()`; `:filterItemType` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", `; `:lclPerson` ← `mrTemp.getLclPerson()`; `:lclMobile` ← `mrTemp.getLclMobile()`; `:lclFax` ← `mrTemp.getLclFax()`; `:lclRPhone` ← `mrTemp.getLclPhoneR()`; `:lclOPhone` ← `mrTemp.getLclPhoneO()`; `:lclEmail` ← `mrTemp.getLclEmail()`; `:lclAddress1` ← `mrTemp.getLclAddress()`; `:lclAddress2` ← `mrTemp.getLclAddress1()`; `:regPerson` ← `mrTemp.getRegPerson()`; `:regMobile` ← `mrTemp.getRegMobile()`; `:regFax` ← `mrTemp.getRegFax()`; `:regRPhone` ← `mrTemp.getRegPhoneR()`; `:regOPhone` ← `mrTemp.getRegPhoneO()`; `:regEmail` ← `mrTemp.getRegEmail()`; `:regAddress1` ← `mrTemp.getRegAddress()`; `:regAddress2` ← `mrTemp.getRegAddress1()`; `:natPerson` ← `mrTemp.getNatPerson()`; `:natMobile` ← `mrTemp.getNatMobile()`; `:natFax` ← `mrTemp.getNatFax()`; `:natRPhone` ← `mrTemp.getNatPhoneR()`; `:natOPhone` ← `mrTemp.getNatPhoneO()`; `:natEmail` ← `mrTemp.getNatEmail()`; `:natAddress1` ← `mrTemp.getNatAddress()`; `:natAddress2` ← `mrTemp.getNatAddress1()`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:preparedDt` ← `timestamp`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:companyName` ← `mrTemp.getSharedKeyFieldName()`; `:shortName` ← `mrTemp.getShortName()`; `:filterItemType` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", `; `:lclPerson` ← `mrTemp.getLclPerson()`; `:lclMobile` ← `mrTemp.getLclMobile()`; `:lclFax` ← `mrTemp.getLclFax()`; `:lclRPhone` ← `mrTemp.getLclPhoneR()`; `:lclOPhone` ← `mrTemp.getLclPhoneO()`; `:lclEmail` ← `mrTemp.getLclEmail()`; `:lclAddress1` ← `mrTemp.getLclAddress()`; `:lclAddress2` ← `mrTemp.getLclAddress1()`; `:regPerson` ← `mrTemp.getRegPerson()`; `:regMobile` ← `mrTemp.getRegMobile()`; `:regFax` ← `mrTemp.getRegFax()`; `:regRPhone` ← `mrTemp.getRegPhoneR()`; `:regOPhone` ← `mrTemp.getRegPhoneO()`; `:regEmail` ← `mrTemp.getRegEmail()`; `:regAddress1` ← `mrTemp.getRegAddress()`; `:regAddress2` ← `mrTemp.getRegAddress1()`; `:natPerson` ← `mrTemp.getNatPerson()`; `:natMobile` ← `mrTemp.getNatMobile()`; `:natFax` ← `mrTemp.getNatFax()`; `:natRPhone` ← `mrTemp.getNatPhoneR()`; `:natOPhone` ← `mrTemp.getNatPhoneO()`; `:natEmail` ← `mrTemp.getNatEmail()`; `:natAddress1` ← `mrTemp.getNatAddress()`; `:natAddress2` ← `mrTemp.getNatAddress1()`; `:userName` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `timestamp`
- Errors: "Invalid key." | "Item Make already exists." | "Short Name already exists."
- Other calls: `ItemMakeMaster_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, companyName, shortName FROM itemMakeMast mm WHERE mm.filterItemType = :filterItemType`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTem`

### `getSuggestionShortName`
`public DmResultPageing<List<String>> getSuggestionShortName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT shortName FROM itemMakeMast`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Item Make already exists." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Short Name already exists." |

## Notes (hand-written)
_No hand-written notes yet._
