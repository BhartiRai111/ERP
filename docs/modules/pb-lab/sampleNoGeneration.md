# pb-lab / sampleNoGeneration

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/lab/src/sampleNoGeneration/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Date (Text); Time (Text); No. Of Sample (Text); Test Template (Text)
- Lookups/suggestions: getTxtHTestTemplate
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `SampleNoGeneration`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Date & Time | DmTextBox | `txtDate` | `12, true` |
| 2 | - | DmTextBoxTime | `txtTime` | `true` |
| 3 | No. Of Sample | DmTextBoxNumeric | `txtSampleNo` | `0, false, 3, true` |
| 4 | Test Template | DmTextHelp | `txtHTestTemplate` | `com.google.gwt.core.shared.GridTestTemplate_PrpAcs, 6, true` |

**Grid / popup columns** (header, width, db field):
- `txtHTestTemplate`: Method name [Text, w150, Description]; Unit [Text, w65, sampleUnit]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTestTemplate` → `getTxtHTestTemplate`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `EntryTypeCode` ← DmShared.xNull(txtDate.getTag())
- `EntryDate` ← DmShared.dNull(txtDate.getText())
- `EntryTime` ← DmShared.xNull(txtTime.getText())
- `TotalSampleNo` ← DmShared.xNull(txtSampleNo.getText())
- `TestTemplateCode` ← DmShared.xNull(txtHTestTemplate.getTag())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtDate.setEnabled(dateTimeEditable)`
- `txtTime.setEnabled(dateTimeEditable)`

**`manageDisplay()` logic (condensed code):**
```java
if (dateTimeEditable) {
txtDate.setEnabled(dateTimeEditable);
txtTime.setEnabled(dateTimeEditable);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTestTemplate.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHTestTemplate(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTestTemplate).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHTestTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTestTemplate).show();
```

`txtHTestTemplate.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTestTemplate.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTestTemplate).show();
```
- Confirm: "Do you want to send print to printer?"
- Confirm: "Are you sure? You want to close."
- Alert: "Record saved successfully."

## Files
| file | lines |
|---|---|
| client/SampleNoGeneration.java | 364 |
| client/SampleNoGeneration_Dsg.java | 226 |
| server/SampleNoGeneration_SrvImpl.java | 212 |
| shared/SampleNoGeneration_Srv.java | 22 |
| shared/SampleNoGeneration_SrvAsync.java | 21 |
| shared/SampleNoGeneration_Vld.java | 16 |

## UI labels / columns (client)
`Date & Time`, `No. Of Sample`, `Test Template`

## RPC interface
| method | params | returns |
|---|---|---|
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getDefaultDateTime |  | DmResult<KeyFieldExtended> |
| getTxtHTestTemplate | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | SampleEntry_Mdl mrTemp, String sampleNo, String voucherPrefix | DmResult<List<String>> |

## Validator `SampleNoGeneration_Vld`
```java
public class SampleNoGeneration_Vld {
    public static void chkSave(SampleEntry_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getEntryDate().trim(), "Date", 100);
        DmShared.isValidText(mrTemp.getEntryTime().trim(), "Time", 200);
        DmShared.isValidText(mrTemp.getTotalSampleNo().trim(), "No. Of Sample", 300);
        DmShared.isValidText(mrTemp.getTestTemplateCode().trim(), "Test Template", 400);
    }
}
```

## Server `SampleNoGeneration_SrvImpl.java` (212 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`

- SQL 1: `select description, V_Type from voucher_Type where category=' {…} '`

### `getDefaultDateTime`
`public DmResult<KeyFieldExtended> getDefaultDateTime()`


### `getTxtHTestTemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTestTemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ttm.Code as code, ttm.description, um.unit as sampleUnit FROM testTemplateMast ttm LEFT JOIN itemImpurityMast ipm ON ipm.testTemplateCode = ttm.Code LEFT JOIN unitMast um ON um.code = ipm.sampleUnit where ipm.isActive = 1`

### `saveRecord`
`public DmResult<List<String>> saveRecord(SampleEntry_Mdl mrTemp, String sampleNo, String voucherPrefix)`

- Other calls: `SampleEntry_Server.saveRecord`, `SampleNoGeneration_Vld.chkSave`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.openSourceReport.client`, `paperbiz.lab.sampleEntry.server`, `paperbiz.lab.sampleEntry.shared`, `paperbiz.lab.sampleNoGeneration.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
