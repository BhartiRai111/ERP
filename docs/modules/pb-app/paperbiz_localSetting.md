# pb-app / paperbiz_localSetting

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/paperbiz_localSetting/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: machineInterfaceConfig, machineSubTestMapping
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHMenuText, getTxtHMachineSpecification, getTxtHMachineName, getSuggestion
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `GrdEditMachineConfig`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Machine Code | DmTextBox | `txtMachineCode` | `100, false` |

Other widgets (not in tab list): `txtMachineName` (DmSuggestBox), `txtCompanyName` (DmTextBox), `txtCommunicationMode` (DmTextBox), `txtTransmissionMode` (DmTextBox), `txtProtocol` (DmTextBox), `txtIPAddress` (DmTextBox), `txtPortNo` (DmTextBox), `txtComPortNo` (DmTextBox), `txtBaudRate` (DmTextBox), `txtStopBit` (DmTextBox), `txtDataBit` (DmTextBox), `txtParityStatus` (DmTextBox), `txtHardwareControl` (DmTextBox), `txtHMachineName` (DmTextHelp), `txtHModelNo` (DmTextHelp), `grdLabTestCodification` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHMachineName`: Name [Text, w0, name]
- `txtHModelNo`: Model No. [Text, w100]; Company Name [Text, w120]; Comn. Mode [Text, w80]; Trans. Mode [Text, w100]; Protocol [Text, w100]
- `grdLabTestCodification`: Machine Subtest Name [Text, w260]; Our Subtest Name [Text, w280]

**Lookup / suggestion / find sources (server RPC called):**
- `txtMachineName` → `getSuggestion`
- `txtHModelNo` → `getTxtHMachineSpecification`
- `txtHMachineName` → `getTxtHMachineName`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtMachineName.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestion(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<String>
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtMachineName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtMachineName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtMachineName).show();
```

`txtMachineName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtMachineName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtMachineName).show();
```

`txtHModelNo.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMachineSpecification(helper, blnToEndOfFile, new AsyncCallback<DmResultPagein
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHModelNo).show();
public void onSuccess(DmResultPageing<List<TxtHMachineSpecification_Mdl>> result) {
if (result.isStatus()) {
txtHModelNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHModelNo).show();
```

`txtHModelNo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHModelNo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHModelNo).show();
```

`txtHModelNo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
txtCompanyName.setText(tRow.getCompanyName());
txtCommunicationMode.setText(tRow.getCommunicationMode());
txtTransmissionMode.setText(tRow.getTransmissionMode());
txtProtocol.setText(tRow.getProtocoll());
txtMachineCode.setText(tRow.getSharedKeyFieldCode());
flwDynamicMain.clear();
if (tRow.getCommunicationMode().equalsIgnoreCase("TCP")) {
flwDynamicMain.add(flwFrameTCP);
...
```

`txtHMachineName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMachineName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMachineName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMachineName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachineName).show();
```

`txtHMachineName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMachineName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMachineName).show();
```

`txtHMachineName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHMachineName.getTag()).equalsIgnoreCase("")) {
getData();
grdLabTestCodification.getGrid().getStore().add(new GrdLabTestCodification_Mdl());
```

`gridEditPopup.addCallBack` → `onOk()`
```java
grdLabTestCodification.commitChanges();
```

`gridEditPopup.addCallBack` → `onDelete()`
```java
grdLabTestCodification.commitChanges();
```
- Confirm: "Are you sure to delete < "
- Alert: "IP Address is mandatory "
- Alert: "Port No. By is mandatory "
- Alert: "COM Port No. is mandatory "
- Alert: "Baud Rate is mandatory "
- Alert: "Stop Bit is mandatory "
- Alert: "Data Bit is mandatory "
- Alert: "Parity is mandatory "
- Alert: "Hardware Control is mandatory "
- Alert: "Machine Name is mandatory "
- Alert: "Machine Name already exist."
- Alert: "Machine Name is mandatory "
- Alert: "Model No. is mandatory "
- Alert: "Select Machine Name"

### Screen `GrdEditPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Menu Text | DmTextHelp | `txtHMenuText` | `com.google.gwt.core.client.DmtxtHmenuText_PrpAcs, 10,true` |
| 2 | Module Name | DmTextBox | `txtModule` | `50, true` |
| 3 | Print Menu Name | DmTextBox | `txtReportName` | `50, true` |

**Grid / popup columns** (header, width, db field):
- `txtHMenuText`: Menu Text [Text, w150, MnuText]; Module Name [Text, w100, MnuModule]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHMenuText` → `getTxtHMenuText`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHMenuText.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHMenuText(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHM
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMenuText).show();
public void onSuccess(DmResultPageing<List<TxtHMenuText_Mdl>> result) {
if (result.isStatus()) {
txtHMenuText.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHMenuText).show();
```

`txtHMenuText.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMenuText.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHMenuText).show();
```

`txtHMenuText.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
txtModule.setText(DmShared.xNull(tRow.getMenuModuleName()));
txtReportName.setFocus(true);
```
- Alert: "Menu Text is mandatory "
- Alert: "Report Name is mandatory "

### Screen `GrdEditTestCodification`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Machine Subtest Name | DmTextBox | `txtMachineTestName` | `50, true` |
| 2 | Our Subtest Name | DmTextBox | `txtManualTestName` | `50, true` |

**Grid / popup columns** (header, width, db field):
- `grdHSubTest`: Test Name [Text, w242, testName]; Sub Test Name [Text, w242, subTest]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`grdHSubTest.addHelpGridCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSubTest(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtManualTestName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
grdHSubTest.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtManualTestName).show();
```

`grdHSubTest.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdHSubTest.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtManualTestName).show();
```

`grdHSubTest.addHelpGridCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
fillManualTestName(t1Row);
```
- Alert: "Machine Subtest Name is mandatory "
- Alert: "Our Subtest Name is mandatory "
- Alert: "Combination of Machine Subtest & Our Subtest Name already exist."

### Screen `MachineInterfacePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdLabMachineConfiguration` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdLabMachineConfiguration`: Machine Name [Text, w145]; Machine Code [Text, w293]; Model No. [Text, w100]; Company Name [Text, w135]; Comn. Mode [Text, w80]; Trans. Mode [Text, w100]; Protocol [Text, w70]

**Lookup / suggestion / find sources (server RPC called):**
- `gridEditPopup` → `deleteRecordFromSQL`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridEditPopup.addCallBack` → `onOk()`
```java
String machineName = "";
if (mode.equalsIgnoreCase("Delete")) {
machineName = machineConfig.getMachineName();
for (int i = 0; i < grdLabMachineConfiguration.getGrid().getStore().getAll().size() - 1; i++) {
if (!DmShared.xNull(grdLabMachineConfiguration.getGrid().getStore().get(i).getMachineName().trim()).equalsIgnoreCase(mac
grdLabMachineConfiguration.getGrid().getStore().remove(grdLabMachineConfiguration.getGrid().getStore().get(i));
break;
deleteRecordFromSQL(machineName);
grdLabMachineConfiguration.commitChanges();
```

`gridEditPopup.addCallBack` → `onClose()`
```java
if (mode.equalsIgnoreCase("Assign") || mode.equalsIgnoreCase("Create")) {
grdLabMachineConfiguration.getGrid().getStore().remove(currentRowIndex);
```
- Alert: "Machine Interfaces saved successfully "
- Alert: "An error occured wlile mapping the printers !"

### Screen `PrinterMappingPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Is Byte | DmTextHelp | `txtHPrinter1` | `KeyField_PrpAcs, 5, false` |
| 2 | Is Byte | DmTextHelp | `txtHPrinter2` | `KeyField_PrpAcs, 5, false` |
| 3 | Is Byte | DmTextHelp | `txtHPrinter3` | `KeyField_PrpAcs, 5, false` |
| 4 | Is Byte | DmTextHelp | `txtHPrinter4` | `KeyField_PrpAcs, 5, false` |
| 5 | Is Byte | DmTextHelp | `txtHPrinter5` | `KeyField_PrpAcs, 5, false` |

Other widgets (not in tab list): `chkBoxPrntr1` (DmCheckBox), `chkBoxPrntr2` (DmCheckBox), `chkBoxPrntr3` (DmCheckBox), `chkBoxPrntr4` (DmCheckBox), `chkBoxPrntr5` (DmCheckBox)

**Grid / popup columns** (header, width, db field):
- `txtHPrinter1`: Name [Text, w0, Name]
- `txtHPrinter2`: Name [Text, w0, Name]
- `txtHPrinter3`: Name [Text, w0, Name]
- `txtHPrinter4`: Name [Text, w0, Name]
- `txtHPrinter5`: Name [Text, w0, Name]

**Save: model field ← widget** (`topControlSaveInitialize`):
- `PrinterName` ← txtHPrinter1.getText()
- `PrinterImageType` ← (chkBoxPrntr1.getValue()) ? DmEnum.DmPrinterImageType.BYTE : DmEnum.DmPrinterIma
- `PrinterName` ← txtHPrinter2.getText()
- `PrinterImageType` ← (chkBoxPrntr2.getValue()) ? DmEnum.DmPrinterImageType.BYTE : DmEnum.DmPrinterIma
- `PrinterName` ← txtHPrinter3.getText()
- `PrinterImageType` ← (chkBoxPrntr3.getValue()) ? DmEnum.DmPrinterImageType.BYTE : DmEnum.DmPrinterIma
- `PrinterName` ← txtHPrinter4.getText()
- `PrinterImageType` ← (chkBoxPrntr4.getValue()) ? DmEnum.DmPrinterImageType.BYTE : DmEnum.DmPrinterIma
- `PrinterName` ← txtHPrinter5.getText()
- `PrinterImageType` ← (chkBoxPrntr5.getValue()) ? DmEnum.DmPrinterImageType.BYTE : DmEnum.DmPrinterIma

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHPrinter1.addTextHelpCallBack` → `populateData()`
```java
getPrinterInfo(txtHPrinter1, helper);
```

`txtHPrinter1.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHPrinter1.setText(t1Row.getSharedKeyFieldName());
txtHPrinter1.setTag(t1Row.getSharedKeyFieldCode());
```

`txtHPrinter2.addTextHelpCallBack` → `populateData()`
```java
getPrinterInfo(txtHPrinter2, helper);
```

`txtHPrinter2.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHPrinter2.setText(t1Row.getSharedKeyFieldName());
txtHPrinter2.setTag(t1Row.getSharedKeyFieldCode());
```

`txtHPrinter3.addTextHelpCallBack` → `populateData()`
```java
getPrinterInfo(txtHPrinter3, helper);
```

`txtHPrinter3.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHPrinter3.setText(t1Row.getSharedKeyFieldName());
txtHPrinter3.setTag(t1Row.getSharedKeyFieldCode());
```

`txtHPrinter4.addTextHelpCallBack` → `populateData()`
```java
getPrinterInfo(txtHPrinter4, helper);
```

`txtHPrinter4.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHPrinter4.setText(t1Row.getSharedKeyFieldName());
txtHPrinter4.setTag(t1Row.getSharedKeyFieldCode());
```

`txtHPrinter5.addTextHelpCallBack` → `populateData()`
```java
getPrinterInfo(txtHPrinter5, helper);
```

`txtHPrinter5.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHPrinter5.setText(t1Row.getSharedKeyFieldName());
txtHPrinter5.setTag(t1Row.getSharedKeyFieldCode());
```

`prntrstgs.addCallBack` → `ok()`
```java
if (listSettingPrintMap != null) {
if (DmShared.xNull(title).trim().equalsIgnoreCase("Printer-1")) {
listSettingPrintMap1.clear();
listSettingPrintMap1.addAll(listSettingPrintMap);
} else if (DmShared.xNull(title).trim().equalsIgnoreCase("Printer-2")) {
listSettingPrintMap2.clear();
listSettingPrintMap2.addAll(listSettingPrintMap);
} else if (DmShared.xNull(title).trim().equalsIgnoreCase("Printer-3")) {
listSettingPrintMap3.clear();
listSettingPrintMap3.addAll(listSettingPrintMap);
...
```
- Alert: "Printers are mapped successfully "

### Screen `PrinterSettingsPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdPrinterMapping` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdPrinterMapping`: Menu Text [Text, w150]; Module [Text, w120]; Report Name [Text, w200]

**Event handlers (condensed code, CRUD flow and field callbacks):**

`gridEditPopup.addCallBack` → `onOk()`
```java
grdPrinterMapping.commitChanges();
```

`gridEditPopup.addCallBack` → `onDelete()`
```java
grdPrinterMapping.commitChanges();
```

## Files
| file | lines |
|---|---|
| client/GrdEditMachineConfig.java | 714 |
| client/GrdEditMachineConfig_Dsg.java | 408 |
| client/GrdEditPopup.java | 192 |
| client/GrdEditPopup_Dsg.java | 131 |
| client/GrdEditTestCodification.java | 198 |
| client/GrdEditTestCodification_Dsg.java | 119 |
| client/LocalSetting.java | 143 |
| client/LocalSetting_Dsg.java | 157 |
| client/MachineInterfacePopup.java | 405 |
| client/MachineInterfacePopup_Dsg.java | 203 |
| client/PrinterMappingPopup.java | 599 |
| client/PrinterMappingPopup_Dsg.java | 379 |
| client/PrinterSettingsPopup.java | 169 |
| client/PrinterSettingsPopup_Dsg.java | 150 |
| server/LocalSetting_SrvImpl.java | 920 |
| shared/GrdLabTestCodification_Mdl.java | 32 |
| shared/LabMachineConfiguration_Mdl.java | 216 |
| shared/LocalSetting_Srv.java | 44 |
| shared/LocalSetting_SrvAsync.java | 46 |
| shared/LocalSetting_Vld.java | 13 |
| shared/TxtHMachineSpecification_Mdl.java | 50 |
| shared/TxtHMenuText_Mdl.java | 29 |

## UI labels / columns (client)
`Machine Name`, `Model No.`, `Machine Code`, `Company Name`, `Transmission Mode`, `Communication Mode`, `Protocol`, `IP Address`, `Port No.`, `COM Port No.`, `Baud Rate`, `Stop Bit`, `Data Bit`, `Parity`, `Hardware Control`, `Printer-1`, `Is Byte`, `Printer-2`, `Printer-3`, `Printer-4`, `Printer-5`, `Menu Text`, `Module Name`, `Print Menu Name`, `Machine Subtest Name`, `Our Subtest Name`

## Models (shared DTOs)
### TxtHMachineSpecification_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | communicationMode |
| String | transmissionMode |
| String | protocoll |
| String | companyName |

### TxtHMenuText_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | menuText |
| String | menuModuleName |

### GrdLabTestCodification_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | machineTestName |
| String | manualName |

### LabMachineConfiguration_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| int | uiHashCode |
| String | machineName |
| String | machineCode |
| String | modelNo |
| String | companyName |
| String | communicationMode |
| String | transmissionMode |
| String | protocoll |
| String | ipAddress |
| String | portNo |
| String | comPortNo |
| String | baudRate |
| String | stopBit |
| String | dataBit |
| String | parity |
| String | hardwareControl |
| String | mode |
| List<GrdLabTestCodification_Mdl> | grdData |

## RPC interface
| method | params | returns |
|---|---|---|
| convertPrintServicesModel | DmHelper helper, String printerJsonString | DmResultPageing<List<KeyFieldExtended>> |
| getJsonOfSettings | STLocalSetting_Mdl settingModel | DmResult<String> |
| getSettingsFromJson | String jsonString | DmResult<STLocalSetting_Mdl> |
| getTxtHMenuText | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHMenuText_Mdl>> |
| getTxtHMachineSpecification | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHMachineSpecification_Mdl>> |
| parseMachineDetails | String jsonString | DmResult<LabMachineConfiguration_Mdl> |
| getJsonOfLabMachineConfigurations | List<LabMachineConfiguration_Mdl> labMachineConfigurations | DmResult<String> |
| getLabMachineModelsFromJsonOfSavedConfigs | String jsonString | DmResult<List<LabMachineConfiguration_Mdl>> |
| saveRecordToSQL | LabMachineConfiguration_Mdl mrTemp | DmResult<String> |
| getTxtHMachineName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getData | String strKey | DmResult<LabMachineConfiguration_Mdl> |
| getSuggestion | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| deleteRecordFromSQL | String strKey | DmResult<String> |
| getSubTest | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `LocalSetting_Vld`
```java
public class LocalSetting_Vld {
    public static void chkSave(STLocalSetting_Mdl mrTemp) {
    }
}
```

## Server `LocalSetting_SrvImpl.java` (920 lines)
Helpers used: `DmServer.convertFromJson`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `convertPrintServicesModel`
`public DmResultPageing<List<KeyFieldExtended>> convertPrintServicesModel(DmHelper helper, String printerJsonString)`


### `getJsonOfSettings`
`public DmResult<String> getJsonOfSettings(STLocalSetting_Mdl settingModel)`

- Other calls: `MI_Configurations.toString`

### `getSettingsFromJson`
`public DmResult<STLocalSetting_Mdl> getSettingsFromJson(String jsonString)`

- Other calls: `MI_Configurations.toString`

### `getTxtHMenuText`
`public DmResultPageing<List<TxtHMenuText_Mdl>> getTxtHMenuText(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select MnuText + 'Ã¾' + MnuName + 'Ã¾' + MnuModule As Code,MnuText,MnuName,MnuModule From User_Permission_Web UP Where UserName='SA'`

### `getTxtHMachineSpecification`
`public DmResultPageing<List<TxtHMachineSpecification_Mdl>> getTxtHMachineSpecification(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `MIConfigEnum.values`

### `parseMachineDetails`
`public DmResult<LabMachineConfiguration_Mdl> parseMachineDetails(String jsonString)`

- Other calls: `BaudRate.toString`, `CommunicationMode.toString`, `CommunicationPort.toString`, `ConfigName.toString`, `DataBits.toString`, `HardwareControl.toString`, `MachineModel.toString`, `MachineName.toString`, `ObservableTestList.toString`, `Parity.toString`, `Protocol.toString`, `StopBits.toString`

### `getJsonOfLabMachineConfigurations`
`public DmResult<String> getJsonOfLabMachineConfigurations(List<LabMachineConfiguration_Mdl> labMachineConfigurations)`

- SQL 1: `select count(machineName) as cnt from machineInterfaceConfig`
- Errors: "Invalid License Key for No. of Machine Interface."
- Other calls: `MIConfigEnum.getConfiguration`, `STServer.getSsnNoOfMachine`

### `getLabMachineModelsFromJsonOfSavedConfigs`
`public DmResult<List<LabMachineConfiguration_Mdl>> getLabMachineModelsFromJsonOfSavedConfigs(String jsonString)`

- SQL 1: `select * from machineInterfaceConfig where (1 = 1)`
- Other calls: `MIConfigEnum.getConfiguration`

### `saveRecordToSQL`
`public DmResult<String> saveRecordToSQL(LabMachineConfiguration_Mdl mrTemp)`

- SQL 1: `Create {…}`
- SQL 2: `SELECT machineName FROM machineInterfaceConfig WHERE machineName = :machineName`
- SQL 3: `Delete From machineInterfaceConfig Where machineName= :machineName`
- SQL 4: `Delete From machineSubTestMapping Where machineName = :machineName`
- SQL 5: `INSERT INTO machineInterfaceConfig (machineName,machineCode,ipAddress,portNo,comPort,baudRate,stopBit,dataBit,parity,hardwareControl,u_EntDt,preparedBy) VALUES ( :machineName, :machineCode, :ipAddress, :portNo, :comPort, :baudRate, :stopBit, :dataBit, :parity, :hardwareControl, :u_EntDt, :preparedBy)`
- SQL 6: `INSERT INTO machineSubTestMapping (machineName,machineSubTestName,ourSubTestName) VALUES(:machineName, :machineSubTestName, :ourSubTestName )`
- Params: `:machineName` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getMachineName().trim()))`; `:machineName` ← `DmShared.xNull(mrTemp.getMachineName())`; `:machineName` ← `DmShared.xNull(mrTemp.getMachineName())`; `:machineName` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getMachineName().trim()))`; `:machineCode` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getMachineCode().trim()))`; `:ipAddress` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getIpAddress().trim()))`; `:portNo` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getPortNo().trim()))`; `:comPort` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getComPortNo().trim()))`; `:baudRate` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getBaudRate().trim()))`; `:stopBit` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getStopBit().trim()))`; `:dataBit` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getDataBit().trim()))`; `:parity` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getParity().trim()))`; `:hardwareControl` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getHardwareControl().trim()))`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:preparedBy` ← `userName`; `:machineName` ← `DmShared.removeSpace(DmShared.xNull(mrTemp.getMachineName().trim()))`; `:machineSubTestName` ← `DmShared.removeSpace(DmShared.xNull(grdMdl.getMachineTestName().trim()))`; `:ourSubTestName` ← `DmShared.removeSpace(DmShared.xNull(grdMdl.getManualName().trim()))`
- Errors: "Machine Name already exist in Database."

### `getTxtHMachineName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMachineName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select machineName as code , machineName as name from machineInterfaceConfig`

### `getData`
`public DmResult<LabMachineConfiguration_Mdl> getData(String strKey)`

- SQL 1: `SELECT machineCode,ipAddress,portNo,comPort,baudRate,stopBit,dataBit,parity,hardwareControl FROM dbo.machineInterfaceConfig as mic where mic.machineName = :machineName`
- Params: `:machineName` ← `DmShared.xNull(strKey)`

### `getFooterGridData`
`private List<GrdLabTestCodification_Mdl> getFooterGridData(String strKey, Connection cnTemp)`

- SQL 1: `SELECT machineSubTestName,ourSubTestName FROM machineSubTestMapping mstm where mstm.machineName = :machineName`
- Params: `:machineName` ← `DmShared.xNull(strKey)`

### `getSuggestion`
`public DmResultPageing<List<String>> getSuggestion(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select machineName as name from machineInterfaceConfig`

### `deleteRecordFromSQL`
`public DmResult<String> deleteRecordFromSQL(String keyCode)`

- SQL 1: `Delete From machineInterfaceConfig Where machineName= :machineName`
- SQL 2: `Delete From machineSubTestMapping Where machineName= :machineName`
- Params: `:machineName` ← `DmShared.xNull(keyCode)`; `:machineName` ← `DmShared.xNull(keyCode)`

### `getSubTest`
`public DmResultPageing<List<KeyFieldExtended>> getSubTest(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select psm.name as testName, tsc.name as subTest, psm.code from patientServiceMast psm left join testSubCategory tsc on psm.code=tsc.code WHERE psm.serviceNature = {…}`
- Other calls: `PATHOLOGY_TEST.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.jvmSerialization.shared`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmHelpGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.mi.shared`, `paperbiz.localSetting.shared.GrdLabTestCodification_Mdl`, `paperbiz.localSetting.shared.LabMachineConfiguration_Mdl`, `paperbiz.localSetting.shared.LocalSetting_Srv`, `paperbiz.localSetting.shared.LocalSetting_SrvAsync`, `paperbiz.localSetting.shared.LocalSetting_Vld`, `paperbiz.localSetting.shared.TxtHMachineSpecification_Mdl`, `paperbiz.localSetting.shared.TxtHMenuText_Mdl`, `paperbiz.projectMenu.mi.shared`, `paperbiz.selfToolkit.stStatic.client`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getJsonOfLabMachineConfigurations | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > DmShared.iNull(STServer.getSsnNoOfMachine(this)).intValue())` | "Invalid License Key for No. of Machine Interface." |
| saveRecordToSQL | `if (rsTemp.next())` | "Machine Name already exist in Database." |

## Notes (hand-written)
_No hand-written notes yet._
