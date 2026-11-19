# pb-master / deductionLineParameter

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/deductionLineParameter/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: adLineConfig, adLineConfigSiteList
- Update tables: adLineConfig
- Delete cascade (in order): adLineConfigSiteList, adLineConfig
- Duplicate checks on save:
  - `SELECT Count(*) As Cnt FROM adLineConfig adlc WHERE adlc.name = :name AND adlc.code <> :code`
  - `SELECT Count(*) As Cnt FROM adLineConfig adlc LEFT JOIN adLineConfigSiteList adlcs ON adlcs.code = adlc.code WHERE adlc.tNatureCode = :tNatureCode AND adlcs.site_Code =:site_Code AND adlc.code <> :cod`
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Transaction Nature (Text); Name (Text); rule: Only amount or percentage is required for deduction1.; rule: Head is required for deduction1.; rule: Only amount or percentage is required for deduction2.; rule: Head is required for deduction2.; rule: Only amount or percentage is required for deduction3.; rule: Head is required for deduction3.; rule: Only amount or percentage is required for deduction4.; rule: Head is required for deduction4.; rule: Only amount or percentage is required for deduction5.; rule: Head is required for deduction5.; rule: Deduction1 head value is defined more than once.; rule: Deduction2 head value is defined more than once.; rule: Deduction3 head value is defined more than once.; rule: Deduction4 head value is defined more than once.; rule: Deduction5 head value is defined more than once.
- Lookups/suggestions: getTxtHTransactionNature
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DeductionLineParameter`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Transaction Nature | DmTextHelp | `txtHTransactionNature` | `TxtHTransactionNature_PrpAcs, 4, true` |
| 2 | Name | DmTextBox | `txtName` | `50, true` |
| 3 | Deduction1 | DmTextBox | `txtHead1` | `20, false` |
| 4 | - | DmTextBoxNumeric | `txtPercentage1` | `4, false, 8, false` |
| 5 | - | DmTextBoxNumeric | `txtAmount1` | `2, false, 12, false` |
| 6 | Deduction2 | DmTextBox | `txtHead2` | `20, false` |
| 7 | - | DmTextBoxNumeric | `txtPercentage2` | `4, false, 8, false` |
| 8 | - | DmTextBoxNumeric | `txtAmount2` | `2, false, 12, false` |
| 9 | Deduction3 | DmTextBox | `txtHead3` | `20, false` |
| 10 | - | DmTextBoxNumeric | `txtPercentage3` | `4, false, 8, false` |
| 11 | - | DmTextBoxNumeric | `txtAmount3` | `2, false, 12, false` |
| 12 | Deduction4 | DmTextBox | `txtHead4` | `20, false` |
| 13 | - | DmTextBoxNumeric | `txtPercentage4` | `4, false, 8, false` |
| 14 | - | DmTextBoxNumeric | `txtAmount4` | `2, false, 12, false` |
| 15 | Deduction5 | DmTextBox | `txtHead5` | `20, false` |
| 16 | - | DmTextBoxNumeric | `txtPercentage5` | `4, false, 8, false` |
| 17 | - | DmTextBoxNumeric | `txtAmount5` | `2, false, 12, false` |

Other widgets (not in tab list): `txtSpouse` (DmTextBox), `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHTransactionNature`: Transaction Nature [Text, w300, name]
- `dmFind`: Transaction Nature [Text, w250, tranNatureName]; Name [Text, w200, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTransactionNature` → `getTxtHTransactionNature`
- `modifyReason` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.removeSpace(DmShared.xNull(txtName.getText()))
- `TransactionNatureCode` ← DmShared.xNull(txtHTransactionNature.getTag())
- `TransactionNatureName` ← DmShared.xNull(txtHTransactionNature.getText())
- `Head1` ← DmShared.removeSpace(DmShared.xNull(txtHead1.getText()))
- `Head2` ← DmShared.removeSpace(DmShared.xNull(txtHead2.getText()))
- `Head3` ← DmShared.removeSpace(DmShared.xNull(txtHead3.getText()))
- `Head4` ← DmShared.removeSpace(DmShared.xNull(txtHead4.getText()))
- `Head5` ← DmShared.removeSpace(DmShared.xNull(txtHead5.getText()))
- `Percentage1` ← (DmShared.xNull(txtPercentage1.getText()))
- `Percentage2` ← (DmShared.xNull(txtPercentage2.getText()))
- `Percentage3` ← (DmShared.xNull(txtPercentage3.getText()))
- `Percentage4` ← (DmShared.xNull(txtPercentage4.getText()))
- `Percentage5` ← (DmShared.xNull(txtPercentage5.getText()))
- `Amount1` ← (DmShared.xNull(txtAmount1.getText()))
- `Amount2` ← (DmShared.xNull(txtAmount2.getText()))
- `Amount3` ← (DmShared.xNull(txtAmount3.getText()))
- `Amount4` ← (DmShared.xNull(txtAmount4.getText()))
- `Amount5` ← (DmShared.xNull(txtAmount5.getText()))

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHTransactionNature.setText` ← mrTemp.getTransactionNatureName()
- `txtHTransactionNature.setTag` ← mrTemp.getTransactionNatureCode()
- `txtName.setText` ← mrTemp.getSharedKeyFieldName()
- `txtHead1.setText` ← mrTemp.getHead1()
- `txtHead2.setText` ← mrTemp.getHead2()
- `txtHead3.setText` ← mrTemp.getHead3()
- `txtHead4.setText` ← mrTemp.getHead4()
- `txtHead5.setText` ← mrTemp.getHead5()
- `txtPercentage1.setText` ← mrTemp.getPercentage1()
- `txtPercentage2.setText` ← mrTemp.getPercentage2()
- `txtPercentage3.setText` ← mrTemp.getPercentage3()
- `txtPercentage4.setText` ← mrTemp.getPercentage4()
- `txtPercentage5.setText` ← mrTemp.getPercentage5()
- `txtAmount1.setText` ← mrTemp.getAmount1()
- `txtAmount2.setText` ← mrTemp.getAmount2()
- `txtAmount3.setText` ← mrTemp.getAmount3()
- `txtAmount4.setText` ← mrTemp.getAmount4()
- `txtAmount5.setText` ← mrTemp.getAmount5()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())

**On New click:**
- `onAdd()`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
txtHTransactionNature.setEnabled(false);
txtName.setFocus(true);
```

`topControl.addDeleteHandeller` → `deleteClicked()`
```java
new DmConfirmMessageBox("Are you sure? You want to delete this record.", new DialogHideEvent.DialogHideHandler() {
public void onDialogHide(DialogHideEvent event) {
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
showModifyReason();
}).show();
```

`topControl.addDeleteHandeller` → `onDialogHide()`
```java
if (event.getHideButton() == Dialog.PredefinedButton.YES) {
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

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(DeductionLineParameter.this);
```

`txtHTransactionNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTransactionNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransactionNature).show();
public void onSuccess(DmResultPageing<List<TxtHTransactioNature_Mdl>> result) {
if (result.isStatus()) {
txtHTransactionNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransactionNature).show();
```

`txtHTransactionNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransactionNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransactionNature).show();
```

`txtHTransactionNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.xNull(txtName.getText()).trim().equalsIgnoreCase("")
|| DmShared.xNull(txtHTransactionNature.getText()).trim().equalsIgnoreCase("")) {
txtName.setText(DmShared.removeSpace(DmShared.xNull(txtHTransactionNature.getText())));
if (blnStatus && tRow != null) {
```

`modifyReason.addCallBack` → `onOk()`
```java
DeductionLineParameter.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
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

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Can be used in browse mode only."

## Files
| file | lines |
|---|---|
| client/DeductionLineParameter.java | 548 |
| client/DeductionLineParameter_Dsg.java | 520 |
| server/DeductionLineParameter_SrvImpl.java | 631 |
| shared/DeductionLineParameter_Mdl.java | 182 |
| shared/DeductionLineParameter_Srv.java | 25 |
| shared/DeductionLineParameter_SrvAsync.java | 25 |
| shared/DeductionLineParameter_Vld.java | 112 |
| shared/Find_Mdl.java | 179 |
| shared/TxtHTransactioNature_Mdl.java | 23 |

## UI labels / columns (client)
` `, `Head`, `Amount`, `%`, `Transaction Nature`, `Name`, `Deduction1`, `Deduction2`, `Deduction3`, `Deduction4`, `Deduction5`

## Models (shared DTOs)
### DeductionLineParameter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | transactionNatureName |
| String | transactionNatureCode |
| String | head2 |
| String | head3 |
| String | head4 |
| String | head1 |
| String | head5 |
| String | percentage1 |
| String | percentage2 |
| String | percentage3 |
| String | percentage4 |
| String | percentage5 |
| String | amount1 |
| String | amount2 |
| String | amount3 |
| String | amount4 |
| String | amount5 |
| ModifyReason_Mdl | modifyReasonMdl |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | transactionNatureCode |
| String | transactionNatureName |
| String | DisplayName |
| String | head1 |
| String | head2 |
| String | head3 |
| String | head4 |
| String | head5 |
| String | percentage1 |
| String | percentage2 |
| String | percentage3 |
| String | percentage4 |
| String | percentage5 |
| String | amount1 |
| String | amount2 |
| String | amount3 |
| String | amount4 |
| String | amount5 |

### TxtHTransactioNature_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<DeductionLineParameter_Mdl> |
| saveRecord | DeductionLineParameter_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl reasonInfo_Mdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHTransactionNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTransactioNature_Mdl>> |

## Validator `DeductionLineParameter_Vld`
```java
public class DeductionLineParameter_Vld {
    public static void chkSave(DeductionLineParameter_Mdl mrTemp, String currentDate) {
        DmShared.isValidText(mrTemp.getTransactionNatureCode(), "Transaction Nature", 100);
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Name", 200);
        if (!DmShared.xNull(mrTemp.getHead1()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getAmount1()).doubleValue() != 0 && DmShared.vNull(mrTemp.getPercentage1()).doubleValue() != 0) {
                throw new DmException(300, "Only amount or percentage is required for deduction1.");
            }
        } else if (DmShared.xNull(mrTemp.getHead1()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getAmount1()).doubleValue() != 0 || DmShared.vNull(mrTemp.getPercentage1()).doubleValue() != 0) {
                throw new DmException(400, "Head is required for deduction1.");
            }
        }
        if (!DmShared.xNull(mrTemp.getHead2()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getAmount2()).doubleValue() != 0 && DmShared.vNull(mrTemp.getPercentage2()).doubleValue() != 0) {
                throw new DmException(500, "Only amount or percentage is required for deduction2.");
            }
        } else if (DmShared.xNull(mrTemp.getHead2()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getAmount2()).doubleValue() != 0 || DmShared.vNull(mrTemp.getPercentage2()).doubleValue() != 0) {
                throw new DmException(550, "Head is required for deduction2.");
            }
        }
        if (!DmShared.xNull(mrTemp.getHead3()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getAmount3()).doubleValue() != 0 && DmShared.vNull(mrTemp.getPercentage3()).doubleValue() != 0) {
                throw new DmException(560, "Only amount or percentage is required for deduction3.");
            }
        } else if (DmShared.xNull(mrTemp.getHead3()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getAmount3()).doubleValue() != 0 || DmShared.vNull(mrTemp.getPercentage3()).doubleValue() != 0) {
                throw new DmException(570, "Head is required for deduction3.");
            }
        }
        if (!DmShared.xNull(mrTemp.getHead4()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getAmount4()).doubleValue() != 0 && DmShared.vNull(mrTemp.getPercentage4()).doubleValue() != 0) {
                throw new DmException(580, "Only amount or percentage is required for deduction4.");
            }
        } else if (DmShared.xNull(mrTemp.getHead4()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getAmount4()).doubleValue() != 0 || DmShared.vNull(mrTemp.getPercentage4()).doubleValue() != 0) {
                throw new DmException(590, "Head is required for deduction4.");
            }
        }
        if (!DmShared.xNull(mrTemp.getHead5()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getAmount5()).doubleValue() != 0 && DmShared.vNull(mrTemp.getPercentage5()).doubleValue() != 0) {
                throw new DmException(600, "Only amount or percentage is required for deduction5.");
            }
        } else if (DmShared.xNull(mrTemp.getHead5()).equalsIgnoreCase("")) {
            if (DmShared.vNull(mrTemp.getAmount5()).doubleValue() != 0 || DmShared.vNull(mrTemp.getPercentage5()).doubleValue() != 0) {
                throw new DmException(610, "Head is required for deduction5.");
            }
        }
        if (!DmShared.xNull(mrTemp.getHead1()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getHead2()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead1()).trim())
                    || DmShared.xNull(mrTemp.getHead3()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead1()).trim())
                    || DmShared.xNull(mrTemp.getHead4()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead1()).trim())
                    || DmShared.xNull(mrTemp.getHead5()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead1()).trim())) {
                throw new DmException(620, "Deduction1 head value is defined more than once.");
            }
        }
        if (!DmShared.xNull(mrTemp.getHead2()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getHead1()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead2()).trim())
                    || DmShared.xNull(mrTemp.getHead3()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead2()).trim())
                    || DmShared.xNull(mrTemp.getHead4()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead2()).trim())
                    || DmShared.xNull(mrTemp.getHead5()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead2()).trim())) {
                throw new DmException(630, "Deduction2 head value is defined more than once.");
            }
        }
        if (!DmShared.xNull(mrTemp.getHead3()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getHead1()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead3()).trim())
                    || DmShared.xNull(mrTemp.getHead2()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead3()).trim())
                    || DmShared.xNull(mrTemp.getHead4()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead3()).trim())
                    || DmShared.xNull(mrTemp.getHead5()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead3()).trim())) {
                throw new DmException(640, "Deduction3 head value is defined more than once.");
            }
        }
        if (!DmShared.xNull(mrTemp.getHead4()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getHead1()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead4()).trim())
                    || DmShared.xNull(mrTemp.getHead2()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead4()).trim())
                    || DmShared.xNull(mrTemp.getHead3()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead4()).trim())
                    || DmShared.xNull(mrTemp.getHead5()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead4()).trim())) {
                throw new DmException(650, "Deduction4 head value is defined more than once.");
            }
        }
        if (!DmShared.xNull(mrTemp.getHead5()).trim().equalsIgnoreCase("")) {
            if (DmShared.xNull(mrTemp.getHead1()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead5()).trim())
                    || DmShared.xNull(mrTemp.getHead2()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead5()).trim())
                    || DmShared.xNull(mrTemp.getHead4()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead5()).trim())
                    || DmShared.xNull(mrTemp.getHead3()).trim().equalsIgnoreCase(DmShared.xNull(mrTemp.getHead5()).trim())) {
                throw new DmException(660, "Deduction5 head value is defined more than once.");
            }
        }
    }
}
```

## Server `DeductionLineParameter_SrvImpl.java` (631 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.executeScalar`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select alc.code , alc.Name From adLineConfig alc Order By alc.name`

### `getMoveRec`
`public DmResult<DeductionLineParameter_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT alc.code, alc.name, alc.tNatureCode,alc.ded1_Head , alc.ded1_Per, alc.ded1_Amt, alc.ded2_Head, alc.ded2_Per, alc.ded2_Amt , alc.ded3_Head, alc.ded3_Per, alc.ded3_Amt, alc.ded4_Head, alc.ded4_Per , alc.ded4_Amt, alc.ded5_Head, alc.ded5_Per, alc.ded5_Amt, alc.preparedBy , alc.preparedDt, alc.u_Name, alc.u_EntDt, alc.transferDt ,tnm.name as tranNatureName FROM adLineConfig alc left join transactionNatureMast tnm on tnm.code=alc.tNatureCode WHERE alc.code=:code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`

### `saveRecord`
`public DmResult<String> saveRecord(DeductionLineParameter_Mdl mrTemp)`

- SQL 1: `Select isNull(max(code),0) + 1 As Cnt From adLineConfig {…} 0`
- SQL 2: `SELECT Count(*) As Cnt FROM adLineConfig adlc WHERE adlc.name = :name AND adlc.code <> :code`
- SQL 3: `SELECT Count(*) As Cnt FROM adLineConfig adlc LEFT JOIN adLineConfigSiteList adlcs ON adlcs.code = adlc.code WHERE adlc.tNatureCode = :tNatureCode AND adlcs.site_Code =:site_Code AND adlc.code <> :code`
- SQL 4: `INSERT INTO adLineConfig (code, name, tNatureCode, ded1_Head, ded1_Per , ded1_Amt, ded2_Head, ded2_Per, ded2_Amt, ded3_Head, ded3_Per, ded3_Amt , ded4_Head, ded4_Per, ded4_Amt, ded5_Head, ded5_Per, ded5_Amt, preparedBy , preparedDt ) VALUES (:code, :name, :tnaturecode, :ded1_head, :ded1_per, :ded1_amt , :ded2_head, :ded2_per, :ded2_amt, :ded3_head, :ded3_per, :ded3_amt , :ded4_head, :ded4_per, :ded4_amt, :ded5_head, :ded5_per, :ded5_amt , :preparedby, :prepareddt )`
- SQL 5: `UPDATE adLineConfig SET code = :code, name = :name, tNatureCode = :tnaturecode, ded1_Head = :ded1_head, ded1_Per = :ded1_per, ded1_Amt = :ded1_amt, ded2_Head = :ded2_head, ded2_Per = :ded2_per, ded2_Amt = :ded2_amt, ded3_Head = :ded3_head, ded3_Per = :ded3_per, ded3_Amt = :ded3_amt, ded4_Head = :ded4_head, ded4_Per = :ded4_per, ded4_Amt = :ded4_amt, ded5_Head = :ded5_head, ded5_Per = :ded5_per, ded5_Amt = :ded5_amt, u_Name = :u_name, u_EntDt = :u_entdt Where code=:code`
- SQL 6: `INSERT INTO adLineConfigSiteList (code, site_Code) VALUES (:code, :siteCode) ;`
- Params: `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:tNatureCode` ← `DmShared.xNull(mrTemp.getTransactionNatureCode())`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:tnaturecode` ← `DmShared.iNull(mrTemp.getTransactionNatureCode()).toString()`; `:ded1_head` ← `DmShared.xNull(mrTemp.getHead1())`; `:ded2_head` ← `DmShared.xNull(mrTemp.getHead2())`; `:ded3_head` ← `DmShared.xNull(mrTemp.getHead3())`; `:ded4_head` ← `DmShared.xNull(mrTemp.getHead4())`; `:ded5_head` ← `DmShared.xNull(mrTemp.getHead5())`; `:ded1_Per` ← `DmServer.vNull(mrTemp.getPercentage1())`; `:ded2_Per` ← `DmServer.vNull(mrTemp.getPercentage2())`; `:ded3_Per` ← `DmServer.vNull(mrTemp.getPercentage3())`; `:ded4_Per` ← `DmServer.vNull(mrTemp.getPercentage4())`; `:ded5_Per` ← `DmServer.vNull(mrTemp.getPercentage5())`; `:ded1_Amt` ← `DmServer.vNull(mrTemp.getAmount1(), "0.00")`; `:ded2_Amt` ← `DmServer.vNull(mrTemp.getAmount2(), "0.00")`; `:ded3_Amt` ← `DmServer.vNull(mrTemp.getAmount3(), "0.00")`; `:ded4_Amt` ← `DmServer.vNull(mrTemp.getAmount4(), "0.00")`; `:ded5_Amt` ← `DmServer.vNull(mrTemp.getAmount5(), "0.00")`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:tnaturecode` ← `DmShared.iNull(mrTemp.getTransactionNatureCode()).toString()`; `:ded1_head` ← `DmShared.xNull(mrTemp.getHead1())`; `:ded2_head` ← `DmShared.xNull(mrTemp.getHead2())`; `:ded3_head` ← `DmShared.xNull(mrTemp.getHead3())`; `:ded4_head` ← `DmShared.xNull(mrTemp.getHead4())`; `:ded5_head` ← `DmShared.xNull(mrTemp.getHead5())`; `:ded1_Per` ← `DmServer.vNull(mrTemp.getPercentage1())`; `:ded2_Per` ← `DmServer.vNull(mrTemp.getPercentage2())`; `:ded3_Per` ← `DmServer.vNull(mrTemp.getPercentage3())`; `:ded4_Per` ← `DmServer.vNull(mrTemp.getPercentage4())`; `:ded5_Per` ← `DmServer.vNull(mrTemp.getPercentage5())`; `:ded1_Amt` ← `DmServer.vNull(mrTemp.getAmount1(), "0.00")`; `:ded2_Amt` ← `DmServer.vNull(mrTemp.getAmount2(), "0.00")`; `:ded3_Amt` ← `DmServer.vNull(mrTemp.getAmount3(), "0.00")`; `:ded4_Amt` ← `DmServer.vNull(mrTemp.getAmount4(), "0.00")`; `:ded5_Amt` ← `DmServer.vNull(mrTemp.getAmount5(), "0.00")`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:siteCode` ← `DmShared.foriegnKey(siteList)`
- Errors: "Invalid key." | "Record already Exist with this name." | "Record already Exist for selected transaction nature."
- Other calls: `DeductionLineParameter_Vld.chkSave`, `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl reasonInfo_Mdl)`

- SQL 1: `Delete From adLineConfigSiteList Where code= :code`
- SQL 2: `Delete From adLineConfig Where code = :Code`
- Params: `:code` ← `strKey`; `:Code` ← `strKey`

### `saveDiscardLog`
`private String saveDiscardLog(String keyCode, ModifyReason_Mdl reason_Mdl, boolean isEInvoiceCancel, Connection cnTemp) throws Exception`

- Other calls: `ModifyReason_Server.saveNew`, `SYSTEM_ACTIVITY.getName`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT alc.code, alc.name, alc.tNatureCode,alc.ded1_Head , alc.ded1_Per, alc.ded1_Amt, alc.ded2_Head, alc.ded2_Per, alc.ded2_Amt , alc.ded3_Head, alc.ded3_Per, alc.ded3_Amt, alc.ded4_Head, alc.ded4_Per , alc.ded4_Amt, alc.ded5_Head, alc.ded5_Per, alc.ded5_Amt, alc.preparedBy , alc.preparedDt, alc.u_Name, alc.u_EntDt, alc.transferDt, tnm.name as tranNatureName FROM adLineConfig alc left join transactionNatureMast tnm on tnm.code=alc.tNatureCode`

### `getTxtHTransactionNature`
`public DmResultPageing<List<TxtHTransactioNature_Mdl>> getTxtHTransactionNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name,shortName from transactionNatureMast`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record already Exist with this name." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Record already Exist for selected transaction nature." |

## Notes (hand-written)
_No hand-written notes yet._
