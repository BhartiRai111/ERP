# dm-toolkit / dmGUI

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmGUI/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: rule: GUI
- Lookups/suggestions: getHelp
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DmGUI`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `textBoxDate` (DmTextBox), `textBoxNumeric` (DmTextBoxNumeric), `textHelp` (DmTextHelp), `txtHelpMulti` (DmTextHelpMulti), `textBox` (DmTextBox), `textArea` (DmTextArea), `chkBox` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `textHelp`: Name [Text, w0]
- `txtHelpMulti`: Name [Text, w0]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHelp` → `getHelp`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`textBoxDate.addBlurHandler` → `onBlur()`
```java
textBoxDate.setText(DmClient.returnDate(textBoxDate.getText()));
```

`txtHelp.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHelp(helpSQLQuery, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHelp).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHelp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHelp).show();
```

`txtHelp.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHelp.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHelp).show();
```

`txtHelpMulti.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getHelp(helpSQLQuery, helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHelpMulti).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHelpMulti.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHelpMulti).show();
```

`txtHelpMulti.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHelpMulti.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHelpMulti).show();
```

### Screen `DmGUI_MultiLine`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextArea | `txtRemark` | `250, false` |

**Event handlers (condensed code, CRUD flow and field callbacks):**

`label.addClickHandler` → `onClick()`
```java
DmGUI_MultiLine.this.show();
```

## Files
| file | lines |
|---|---|
| client/DmGUI.java | 658 |
| client/DmGUI_Dsg.java | 72 |
| client/DmGUI_MultiLine.java | 126 |
| client/DmGUI_MultiLine_Dsg.java | 87 |
| server/DmGUI_Server.java | 462 |
| server/DmGUI_SrvImpl.java | 131 |
| shared/DmGUIControl_Mdl.java | 207 |
| shared/DmGUIRow_Mdl.java | 32 |
| shared/DmGUI_Mdl.java | 69 |
| shared/DmGUI_Srv.java | 18 |
| shared/DmGUI_SrvAsync.java | 18 |
| shared/DmGUI_Vld.java | 23 |

## Models (shared DTOs)
### DmGUI_Mdl (extends `KeyField`)
| type | field |
|---|---|
| byte[] | tableName |
| byte[] | sqlQuery |
| String | reportNamePrefix |

### DmGUIRow_Mdl
### DmGUIControl_Mdl
| type | field |
|---|---|
| String | saveInFieldName |
| String | fieldCaption |
| DmEnum.DmDataType | dataType |
| String | controlWidth |
| String | controlStyle |
| String | captionWidth |
| String | captionStyle |
| String | maxLength |
| boolean | mandatory |
| boolean | allowNegative |
| boolean | copy |
| String | allowDecimalPlaces |
| String | defaultText |
| String | defaultCode |
| String | viewFieldText |
| String | viewFieldCode |
| byte[] | helpSQLQuery |
| String | helpWidth |
| String | helpRowPerPage |
| String | customFieldName |
| String | fieldName |

## RPC interface
| method | params | returns |
|---|---|---|
| getGUI | String guiConfigCode, String guiTemplateCode | DmResult<DmGUI_Mdl> |
| getHelp | byte[] helpSQLQuery, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getCopyGUIDetails | String guiCodeFrom, String docIdGUIFrom | DmResult<DmGUI_Mdl> |

## Validator `DmGUI_Vld`
```java
public class DmGUI_Vld {
    public static void chkSave(List<DmGUIRow_Mdl> guiRowList) {
        if (guiRowList != null) {
            for (DmGUIRow_Mdl guiRow : guiRowList) {
                for (DmGUIControl_Mdl guiControl : guiRow.getGUIControlList()) {
                    if (guiControl.isMandatory()) {
                        if (DmShared.xNull(guiControl.getDefaultText()).trim().equalsIgnoreCase("")) {
                            throw new DmException(0, "GUI", DmShared.xNull(guiControl.getSaveInFieldName()), "Please fill " + DmShared.xNull(guiControl.getFieldCaption()));
                        }
                    }
                }
            }
        }
    }
}
```

## Server `DmGUI_SrvImpl.java` (131 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.iNull`, `DmShared.xNull`

### `getGUI`
`public DmResult<DmGUI_Mdl> getGUI(String guiConfigCode, String guiTemplateCode)`

- Other calls: `DmGUI_Server.getGUIViaConfigCode`, `DmGUI_Server.getGUIViaTemplateCode`

### `getHelp`
`public DmResultPageing<List<KeyFieldExtended>> getHelp(byte[] helpSQLQuery, DmHelper helper, boolean blnToEndOfFile)`


### `getCopyGUIDetails`
`public DmResult<DmGUI_Mdl> getCopyGUIDetails(String guiCodeFrom, String docIdGUIFrom)`

- Other calls: `DmGUI_Server.getGUIViaConfigCode`

## Server `DmGUI_Server.java` (462 lines)
Helpers used: `DmServer.convertDate`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getGUI`
`private static DmGUI_Mdl getGUI(boolean searchByGUICode, String guiRelatedCode, String docId, String vSNo, String siteCode, Connection cnTemp)`

- SQL 1: `Select * from ( {…} ) as t where docId = :docId`
- SQL 2: `Select *,isNull(gcd.isCopy,1) as isCopyName from guiConfigDetail gcd where gcd.code = :code order by gcd.guiRowNo, gcd.V_SNo`
- Params: `:docId` ← `docId`; `:v_SNo` ← `vSNo`; `:docId` ← `docId`; `:code` ← `gui.getSharedKeyFieldCode()`

### `getGUIHeader`
`private static DmGUI_Mdl getGUIHeader(boolean searchByGUICode, String guiRelatedCode, String siteCode, Connection cnTemp)`

- SQL 1: `Select * from guiConfig gc`
- Params: `:code` ← `guiRelatedCode`; `:guiTemplateCode` ← `guiRelatedCode`; `:siteCode` ← `siteCode`

### `addGUI`
`private static void addGUI(DmGUIRow_Mdl listNode, ResultSet rsTemp, ResultSet rsGUI) throws Exception`


### `getFormatedValue`
`public static String getFormatedValue(DmGUIControl_Mdl guiControl, String value)`


### `getDataType`
`public static DmDataType getDataType(int dataType)`

- Other calls: `CHECKBOX.toInt`, `CUSTOM.toInt`, `DATE.toInt`, `DATETIME.toInt`, `DOUBLE.toInt`, `HELPMULTI.toInt`, `HELPSINGLE.toInt`, `INTEGER.toInt`, `STRING.toInt`, `STRING_MULTILINE.toInt`

### `delete`
`private static void delete(boolean searchByGUICode, String guiRelatedCode, String docId, String vSNo, String siteCode, Connection cnTemp)`

- SQL 1: `delete from {…} where docId = :docId and v_Sno = :v_Sno`
- SQL 2: `delete from {…} where docId = :docId`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:v_Sno` ← `DmShared.iNull(vSNo).toString()`; `:docId` ← `DmShared.iNull(docId).toString()`

### `save`
`private static void save(List<DmGUIRow_Mdl> guiRowList, boolean searchByGUICode, String guiRelatedCode, String docId, String vSNo, String siteCode, Connection cnTemp)`

- SQL 1: `insert into {…}`
- Params: `:docId` ← `docId`; `:v_Sno` ← `vSNo`
- Errors: "Invalid key field."
- Other calls: `DmGUI_Vld.chkSave`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGUI.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextArea.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
