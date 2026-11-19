# pb-master / transpoterRateContract

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/transpoterRateContract/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: tpRateContract
- Update tables: tpRateContractDetail, tpRateContract
- Delete cascade (in order): tpRateContractDetail
- Duplicate checks on save:
  - `SELECT COUNT(*) AS Cnt FROM tpRateContract tcr WHERE code <>:code AND tcr.name =:name`
- "In use" checks before delete:
  - `SELECT COUNT(tab.cnt) AS cnt FROM (SELECT td.applicableFrom AS cnt FROM tpRateContractDetail td WHERE td.code = :code GROUP BY td.applicableFrom ) AS tab`
- Validations: Contract Name (Text); Applicable Date (Text); Name (Text); rule: Duplicate Record Defined For Transporter: ; rule: Transporter detail is mandatory.
- Lookups/suggestions: getTxtHCopyFromContract, getTxtHTransporter, getTxtHCity, getTxtHArea, getTxtHVeichleType, getTxtHRateType, getTxtHNature, getSuggestionName, getTxtHName
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ApplicableFrom`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Copy Contract | DmTextHelp | `txtHCopyFrom` | `TxtHCopyFrom_PrpAcs, 5, false` |
| 2 | Copy Contract Site | DmTextBox | `txtCopyFromSite` | `` |
| 3 | Copy Applicable Date | DmTextBox | `txtDtCopyFrom` | `11, false` |
| 4 | New Applicable Date | DmTextBox | `txtDtNew` | `100, true` |
| 5 | Copy From Contract | DmTextHelp | `txtHCopyFrom` | `TxtHCopyFrom_PrpAcs, 5, false` |
| 6 | Copy From Site | DmTextBox | `txtCopyFromSite` | `` |
| 7 | Copy Applicable Date | DmTextBox | `txtDtCopyFrom` | `11, false` |
| 8 | New Applicable Date | DmTextBox | `txtDtNew` | `100, true` |

Other widgets (not in tab list): `dmGrdApplicableDate` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHCopyFrom`: Name [Text, w0, name]; Applicable From [Text, w100, replace(convert(varchar, applicableFrom, 106), ' ','-')]; Site Name [Text, w150, siteName]
- `dmGrdApplicableDate`: Applicable From [Text, w1]; Applicable To [Text, w125]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHCopyFrom` → `getTxtHCopyFromContract`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHCopyFrom.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCopyFromContract(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<Li
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCopyFrom).show();
public void onSuccess(DmResultPageing<List<TxtHCopyFromContract_Mdl>> result) {
if (result.isStatus()) {
txtHCopyFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopyFrom).show();
```

`txtHCopyFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCopyFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCopyFrom).show();
```

`txtHCopyFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtCopyFromSite.setTag(DmShared.xNull(t1Row.getSiteCode()));
txtCopyFromSite.setText(DmShared.xNull(t1Row.getSiteName()));
txtDtCopyFrom.setText(DmShared.dNull(t1Row.getApplicableFrom()));
} else {
txtDtCopyFrom.setText("");
```
- Confirm: "Are you sure? You want to delete "
- Alert: "Please Select Applicable From"
- Alert: "Contract is mandatory."
- Alert: "New applicable date is mandatory"
- Alert: "Please Select a Date"
- Alert: "Contract is mandatory."
- Alert: "New Applicable Date is mandatory."

### Screen `AutofillData`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Type | DmTextHelp | `txtHRateType` | `com.google.gwt.core.client.GWT.create(dataman.dmBase.self.shared.KeyField_PrpAcs.class), 5` |
| 2 | Vehicle Type | DmTextHelp | `txtHVehicleType` | `com.google.gwt.core.client.GWT.create(dataman.dmBase.self.shared.KeyField_PrpAcs.class), 5` |
| 3 | City | DmTextHelp | `txtHCity` | `com.google.gwt.core.client.GWT.create(dataman.dmBase.self.shared.KeyField_PrpAcs.class), 5` |
| 4 | Area | DmTextHelp | `txtHArea` | `KeyField_PrpAcs, 4` |
| 5 | Transporter | DmTextHelp | `txtHTransporter` | `TxtHTransporter_PrpAcs, 7, false` |
| 6 | Rate | DmTextBoxNumeric | `txtRate` | `2, false, 11, false` |

**Grid / popup columns** (header, width, db field):
- `txtHRateType`: Type [Text, w0, Name]
- `txtHVehicleType`: Veichle Type [Text, w0, Name]
- `txtHCity`: City [Text, w0, Name]
- `txtHArea`: Area [Text, w250, name]
- `txtHTransporter`: Name [Text, w0, Name]; Manual Code [Text, w100, manualCode]; City [Text, w100, cityName]; State [Text, w100, stateName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTransporter` → `getTxtHTransporter`
- `txtHVehicleType` → `getTxtHVeichleType`
- `txtHRateType` → `getTxtHRateType`
- `txtHCity` → `getTxtHCity`
- `txtHArea` → `getTxtHArea`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTransporter.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTransporter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tr
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransporter).show();
public void onSuccess(DmResultPageing<List<TranspoterDetail_Mdl>> result) {
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHTransporter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHTransporter.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHTransporter.setTag2nd("");
if (blnStatus && t1Row != null) {
txtHTransporter.setTag2nd(DmShared.xNull(t1Row.getContactNo()));
```

`txtHVehicleType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVeichleType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVehicleType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVehicleType).show();
```

`txtHVehicleType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVehicleType).show();
```

`txtHVehicleType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHRateType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRateType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRateType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRateType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRateType).show();
```

`txtHRateType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRateType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRateType).show();
```

`txtHRateType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHCity.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `getSelectedRow()`
```java
txtHCity.setTag2nd("");
if (blnStatus && t1Row != null) {
txtHCity.setTag2nd(DmShared.xNull(t1Row.getSharedPreparedBy()));
```

`txtHArea.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHArea(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHArea).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHArea.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHArea).show();
```

`txtHArea.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHArea.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHArea).show();
```

### Screen `CreateTransporterRateContractPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmSuggestBox | `txtName` | `25, 10, true, "Name", DmDataType.STRING` |

**Lookup / suggestion / find sources (server RPC called):**
- `txtName` → `getSuggestionName`

**Save: model field ← widget** (`saveInitialize`):
- `SharedKeyFieldName` ← DmShared.xNull(txtName.getText())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtName.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Str
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtName).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

`txtName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtName).show();
```

### Screen `TranspoterRateContractGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Vehicle Type | DmTextHelp | `txtHRateType` | `com.google.gwt.core.client.GWT.create(dataman.dmBase.self.shared.KeyField_PrpAcs.class), 5` |
| 2 | - | DmTextHelp | `txtHVehicleType` | `com.google.gwt.core.client.GWT.create(dataman.dmBase.self.shared.KeyField_PrpAcs.class), 5` |
| 3 | State | DmTextHelp | `txtHCity` | `com.google.gwt.core.client.GWT.create(dataman.dmBase.self.shared.KeyField_PrpAcs.class), 5` |
| 4 | - | DmTextHelp | `txtHArea` | `KeyField_PrpAcs, 4` |
| 5 | - | DmTextBox | `txtState` | `100, false` |
| 6 | Transporter | DmTextHelp | `txtHTransporter` | `TxtHTransporter_PrpAcs, 7, true` |
| 7 | Contact No. | DmTextBox | `txtContactNo` | `10, false` |
| 8 | Rate | DmTextBoxNumeric | `txtRate` | `2, false, 11, true` |

Other widgets (not in tab list): `txtHNature` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHRateType`: Type [Text, w0, Name]
- `txtHNature`: Nature [Text, w0, Name]
- `txtHVehicleType`: Veichle Type [Text, w0, Name]
- `txtHCity`: City [Text, w0, Name]
- `txtHArea`: Area [Text, w250, name]
- `txtHTransporter`: Name [Text, w0, Name]; Manual Code [Text, w100, manualCode]; City [Text, w100, cityName]; State [Text, w100, stateName]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTransporter` → `getTxtHTransporter`
- `txtHVehicleType` → `getTxtHVeichleType`
- `txtHRateType` → `getTxtHRateType`
- `txtHNature` → `getTxtHNature`
- `txtHCity` → `getTxtHCity`
- `txtHArea` → `getTxtHArea`

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtState.setEnabled(false)`
- `txtContactNo.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
txtState.setEnabled(false);
txtContactNo.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTransporter.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTransporter(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tr
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTransporter).show();
public void onSuccess(DmResultPageing<List<TranspoterDetail_Mdl>> result) {
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHTransporter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHTransporter).show();
```

`txtHTransporter.addTextHelpCallBack` → `getSelectedRow()`
```java
txtContactNo.setText("");
if (blnStatus && t1Row != null) {
txtContactNo.setText(DmShared.xNull(t1Row.getContactNo()));
```

`txtHVehicleType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHVeichleType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVehicleType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVehicleType).show();
```

`txtHVehicleType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVehicleType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVehicleType).show();
```

`txtHVehicleType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHRateType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHRateType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHRateType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHRateType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRateType).show();
```

`txtHRateType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHRateType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHRateType).show();
```

`txtHRateType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
```

`txtHCity.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && t1Row != null) {
txtHCity.setTag2nd(DmShared.xNull(txtHCity.getTag()));
txtState.setText(DmShared.xNull(t1Row.getSharedPreparedBy()));
```

`txtHArea.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHArea(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHArea).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
try {
if (result.isStatus()) {
txtHArea.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHArea).show();
```

`txtHArea.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHArea.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHArea).show();
```
- Alert: " Record already exists!"

### Screen `TranspoterRateContract`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextHelp | `txtHName` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true` |
| 2 | Applicable From | DmTextBox | `txtApplicableDate` | `11, true` |

Other widgets (not in tab list): `txtRate` (DmTextBoxNumeric), `txtState` (DmTextBox), `dmFind` (DmFind), `dmGrd` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHName`: Name [Text, w0, Name]
- `dmFind`: Name [Text, w150, name]
- `dmGrd`: Type [Text, w100]; Vehicle Type [Text, w100]; City [Text, w120]; Area [Text, w120]; State [Text, w120]; Transporter [Text, w350]; Contact No. [Text, w150]; Rate [Text, w100]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHName` → `getTxtHName`
- `transporterDetailGridEdit` → `fillGrid`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHName.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHName.getText())
- `ApplicableFrom` ← DmShared.dNull(txtApplicableDate.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHName.setTag` ← DmShared.xNull(data.getSharedKeyFieldCode())
- `txtHName.setTag2nd` ← DmShared.xNull(data.getSharedKeyFieldCode())
- `txtHName.setText` ← DmShared.xNull(data.getSharedKeyFieldName())
- `txtApplicableDate.setText` ← DmShared.dNull(DmClient.getStartDate())
- `txtApplicableDate.setText` ← DmShared.dNull(data.getApplicableFrom())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHName).show();
```

`txtHName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHName).show();
```

`txtHName.addTextHelpCallBack` → `getSelectedRow()`
```java
boolean isNameChange = false;
if (!DmShared.xNull(txtHName.getTag2nd()).equalsIgnoreCase(txtHName.getTag())) {
resetName();
txtHName.setTag2nd(DmShared.xNull(txtHName.getTag()));
isNameChange = true;
if (blnStatus && tRow != null) {
if (isNameChange) {
topControlMoveRec(DmShared.xNull(tRow.getSharedKeyFieldCode()));
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TranspoterRat
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), dmFind.getTextBox()).show();
public void onSuccess(DmResultPageing<List<TranspoterRateContract_Mdl>> result) {
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

`modifyReason.addCallBack` → `onOk()`
```java
try {
TranspoterRateContract.this.modifyReasonMdl = modifyReason;
if (isDeleteRecord) {
isDeleteRecord = false;
topControlDelete();
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`createTRC.addCallBack` → `onOk()`
```java
if (mrTemp != null) {
if (DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue() > 0) {
topControlMoveRec(DmShared.xNull(mrTemp.getSharedKeyFieldCode()));
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtHName.setFocus(true);
```

`createTRC.addCallBack` → `execute()`
```java
txtHName.setFocus(true);
```

`createTRC.addCallBack` → `onClose()`
```java
txtHName.setFocus(true);
```

`createTRC.addCallBack` → `onDelete()`
```java
clear();
txtHName.setFocus(true);
```

`applicableFrom.addCallBack` → `onOk()`
```java
try {
txtApplicableDate.setText(DmShared.dNull(newDtApplicable));
if (!DmShared.dNull(newDtApplicable).equalsIgnoreCase("")) {
fillGrid(DmShared.xNull(txtHName.getTag()), "", newDtApplicable, isCopyFrom);
} catch (Exception ex) {
new DmAlertMessageBox(ex.getMessage(), (DmTextBox) null).show();
```

`applicableFrom.addCallBack` → `onDelete()`
```java
topControlMoveRec(DmShared.xNull(txtHName.getTag()));
```

`applicableFrom.addCallBack` → `onCopyTDS()`
```java
if (!DmShared.dNull(newDtApplicable).trim().equalsIgnoreCase("")) {
txtApplicableDate.setText(DmShared.dNull(newDtApplicable));
if (!DmShared.xNull(copyCategory).trim().equals("") && !DmShared.xNull(copySiteCode).trim().equals("") && !DmShared.dNul
fillGrid(copyCategory, copySiteCode, copyDtApplicable, isCopyFrom);
```

`defaultValuesForm.addCallBack` → `onOk()`
```java
autofillData = tdDef.clone();
txtHName.setFocus(true);
```

`transporterDetailGridEdit.addCallBack` → `onOk()`
```java
dmGrd.commitChanges();
```

`transporterDetailGridEdit.addCallBack` → `onDelete()`
```java
dmGrd.commitChanges();
```

**Menu items (Other button):** Autofill Data

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to close."
- Alert: "No record selected !"
- Alert: "Name is mandatory"
- Alert: "Please fill applicable Date"
- Alert: "Select Record To View/Add Applicable From"

## Files
| file | lines |
|---|---|
| client/ApplicableFrom.java | 426 |
| client/ApplicableFrom_Dsg.java | 418 |
| client/AutofillData.java | 300 |
| client/AutofillData_Dsg.java | 289 |
| client/CreateTransporterRateContractPopup.java | 263 |
| client/CreateTransporterRateContractPopup_Dsg.java | 133 |
| client/TranspoterRateContract.java | 648 |
| client/TranspoterRateContractGridEdit.java | 394 |
| client/TranspoterRateContractGridEdit_Dsg.java | 247 |
| client/TranspoterRateContract_Dsg.java | 348 |
| server/TranspoterRateContract_SrvImpl.java | 1597 |
| shared/AutofillData_Mdl.java | 153 |
| shared/Find_Mdl.java | 33 |
| shared/TranspoterDetail_Mdl.java | 140 |
| shared/TranspoterRateContract_Mdl.java | 70 |
| shared/TranspoterRateContract_Srv.java | 49 |
| shared/TranspoterRateContract_SrvAsync.java | 50 |
| shared/TranspoterRateContract_Vld.java | 58 |
| shared/TxtHCopyFromContract_Mdl.java | 38 |

## UI labels / columns (client)
`Category`, `Copy Contract`, `Copy Contract Site`, `Copy Applicable Date`, `New Applicable Date`, `Copy From Contract`, `Copy From Site`, `Copy Rate Contract`, `Type`, `Vehicle Type`, `City`, `Area`, `Transporter`, `Rate`, `Applicable From`, `State`, `Contact No.`, `Name`

## Models (shared DTOs)
### TranspoterRateContract_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | applicableFrom |
| String | applicableTo |
| String | u_EntDt_LatestLine |
| List<TranspoterDetail_Mdl> | grdData |
| ModifyReason_Mdl | modifyReasonMdl |
| String | duplicateTransporterDetailCondition |

### AutofillData_Mdl
| type | field |
|---|---|
| String | rateTypeCode |
| String | rateTypeName |
| String | transporterCode |
| String | transporterName |
| String | contactNo |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | cityCode |
| String | cityName |
| String | stateName |
| String | areaCode |
| String | areaName |
| String | rate |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | typeCode |
| String | type |

### TxtHCopyFromContract_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | siteCode |
| String | siteName |
| String | applicableFrom |

### TranspoterDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | cityCode |
| String | cityName |
| String | areaCode |
| String | areaName |
| String | stateName |
| String | contactNo |
| String | manualCode |
| String | rate |
| String | vehicleTypeCode |
| String | vehicleTypeName |
| String | rateTypeCode |
| String | rateTypeName |
| String | rateNatureCode |
| String | rateNatureName |

## RPC interface
| method | params | returns |
|---|---|---|
| getMoveRec | String strKey | DmResult<TranspoterRateContract_Mdl> |
| saveRecord | TranspoterRateContract_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TranspoterRateContract_Mdl>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TranspoterDetail_Mdl>> |
| getTxtHCopyFromContract | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCopyFromContract_Mdl>> |
| fillGridApplicableDate | String keyCode | DmResult<List<KeyFieldExtended>> |
| getMaxApplicableDate | String keyCode | DmResult<KeyFieldExtended> |
| deleteRecordApplicable | String keyCode, String dNull | DmResult<String> |
| fillGrid | String xNull, String copySiteCode, String dNull, boolean isCopyFrom | DmResult<List<TranspoterDetail_Mdl>> |
| getTxtHCity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHArea | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHVeichleType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHRateType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| saveContractName | KeyFieldExtended mrTemp | DmResult<String> |
| deleteContractName | String strKey | DmResult<String> |

## Validator `TranspoterRateContract_Vld`
```java
public class TranspoterRateContract_Vld {
    public static void chkSave(TranspoterRateContract_Mdl mrTemp) {
        List<TranspoterDetail_Mdl> listData;
        boolean flag = false;
        String duplicateTransporterDetailCondition = "";
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Contract Name", 100);
        DmShared.isValidText(DmShared.dNull(mrTemp.getApplicableFrom()), "Applicable Date", 400);
        listData = mrTemp.getGrdData();
        if (listData != null) {
            for (int i = 0; i < listData.size(); i++) {
                if (!DmShared.xNull(listData.get(i).getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                    flag = true;
                    for (int j = i + 1; j < listData.size(); j++) {
                        if (DmShared.xNull(listData.get(j).getSharedKeyFieldCode()).equalsIgnoreCase(listData.get(i).getSharedKeyFieldCode())
                                && DmShared.xNull(listData.get(j).getRateTypeCode()).equalsIgnoreCase(listData.get(i).getRateTypeCode())
                                && DmShared.xNull(listData.get(j).getVehicleTypeCode()).equalsIgnoreCase(listData.get(i).getVehicleTypeCode())
                                && DmShared.xNull(listData.get(j).getCityCode()).equalsIgnoreCase(listData.get(i).getCityCode())
                                && DmShared.xNull(listData.get(j).getAreaCode()).equalsIgnoreCase(listData.get(i).getAreaCode())) {
                            throw new DmException(500, "Duplicate Record Defined For Transporter: " + listData.get(i).getSharedKeyFieldName() + "");
                        }
                    }
                    if (!duplicateTransporterDetailCondition.trim().equalsIgnoreCase("")) {
                        duplicateTransporterDetailCondition += " OR ";
                    } else {
                        duplicateTransporterDetailCondition += " ";
                    }
                    duplicateTransporterDetailCondition += " (ISNULL(tprcd.transporterCode, 0) = '" + DmShared.xNull(listData.get(i).getSharedKeyFieldCode()) + "'";
                    duplicateTransporterDetailCondition += " AND ISNULL(tprcd.vehicleTypeCode, 0) = '" + DmShared.xNull(listData.get(i).getVehicleTypeCode()) + "'";
                    duplicateTransporterDetailCondition += " AND ISNULL(tprcd.cityCode, 0) = '" + DmShared.xNull(listData.get(i).getCityCode()) + "'";
                    duplicateTransporterDetailCondition += " AND ISNULL(tprcd.areaCode, 0) = '" + DmShared.xNull(listData.get(i).getAreaCode()) + "'";
                    duplicateTransporterDetailCondition += " AND ISNULL(tprcd.type, 0) = '" + DmShared.xNull(listData.get(i).getRateTypeCode()) + "')";
                }
            }
        }
        mrTemp.setDuplicateTransporterDetailCondition(duplicateTransporterDetailCondition);
        if (!flag) {
            throw new DmException(600, "Transporter detail is mandatory.");
        }
    }
    public static void chkSaveContractName(KeyFieldExtended mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getSharedKeyFieldName()), "Name", 100);
    }
}
```

## Server `TranspoterRateContract_SrvImpl.java` (1597 lines)
Helpers used: `DmServer.addDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getMoveRec`
`public DmResult<TranspoterRateContract_Mdl> getMoveRec(String strKey)`


### `getMoveRec`
`private TranspoterRateContract_Mdl getMoveRec(String strKey, Connection cnTemp) throws Exception`

- SQL 1: `Select trc.Code , trc.name , trc.preparedBy, trc.preparedDt, trc.preparedByIP, trc.u_Name, trc.u_EntDt , trc.u_EntDt_LatestLine, trcdf.applicableFrom, trcdt.applicableTo From tpRateContract trc LEFT JOIN ( SELECT trcd.code, max(trcd.applicableFrom) as applicableFrom from tpRateContractDetail trcd where trcd.code = :code and trcd.applicableFrom <= cast(:uEntDt as date) group by trcd.code ) trcdf on trcdf.code = trc.code LEFT JOIN ( SELECT trcd.code, trcd.applicableFrom, MAX(trcd.applicableTo) AS applicableTo, MAX(trcd.rate) AS rate FROM tpRateContractDetail trcd WHERE trcd.code = :code AND trcd.applicableFrom <= cast(:uEntDt as date) GROUP BY trcd.code, trcd.applicableFrom ) trcdt on trcdt.code = trcdf.code AND trcdt.applicableFrom = trcdf.applicableFrom Where trc.code = :code`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:uEntDt` ← `DmServer.getServerTimestamp()`

### `saveRecord`
`public DmResult<String> saveRecord(TranspoterRateContract_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) AS Cnt FROM tpRateContract tcr WHERE code <>:code AND tcr.name =:name`
- SQL 2: `SELECT u_EntDt_LatestLine FROM tpRateContract WHERE code = :code`
- SQL 3: `SELECT STRING_AGG(CONVERT(NVARCHAR(max),'<b>Vehicle Type:</b> ' + vtm.name +', <b>City:</b> ' + c.cityName +', <b>Area:</b> ' + am.name +', <b>Transporter:</b> ' + sg.name), char(10)) AS invalidRecordName , STRING_AGG(CONVERT(NVARCHAR(max), (CAST(tprcd.transporterCode AS varchar) + '|' + CAST(tprcd.vehicleTypeCode AS varchar)) + '|' + CAST(tprcd.cityCode AS varchar) + '|' + CAST(tprcd.areaCode AS varchar) + '|' + CAST(tprcd.type AS varchar)), '#') AS invalidRecordCode FROM tpRateContractDetail tprcd LEFT JOIN subGroup sg ON sg.subCode = tprcd.transporterCode LEFT JOIN vehicleTypeMast vtm ON vtm.code = tprcd.vehicleTypeCode LEFT JOIN city c ON c.cityCode = tprcd.cityCode LEFT JOIN areaMast am ON am.code = tprcd.areaCode WHERE ISNULL(tprcd.nature, 0) = :nature AND ISNULL(tprcd.site_Code, 0) = :site_Code AND tprcd.applicableFrom = :applicableFromDate AND ( {…} ) AND tprcd.code <> :code`
- SQL 4: `select MAX(trcd.applicableFrom) applicableFromMax, MIN(trcd.applicableFrom) applicableFromMin from tpRateContractDetail trcd where trcd.code = :code and trcd.nature = :nature`
- SQL 5: `update tpRateContractDetail set applicableTo = :applicableToDate where applicableFrom = :applicableFromDate and nature = :nature and code = :code`
- SQL 6: `select applicableFrom, applicableTo FROM tpRateContractDetail where applicableFrom > :applicableFromDate and nature = :nature and code = :code group by applicableFrom, applicableTo order by applicableFrom asc offset 0 rows fetch next 1 rows only`
- SQL 7: `update tpRateContractDetail set applicableTo = :applicableToDate where applicableFrom = :applicableFromDate and code = :code and nature = :nature`
- SQL 8: `Update tpRateContract Set name =:name, transferDt = null, U_Name = :uName, U_EntDt = :u_EntDt, u_EntDt_LatestLine =:u_EntDt Where code = :code`
- SQL 9: `Delete From tpRateContractDetail where Code= :code and applicableFrom = :applicableFrom and nature = :nature and site_Code = :site_Code`
- Params: `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:applicableFromDate` ← `DmServer.convertDate(mrTemp.getApplicableFrom())`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:code` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:availableFromDate` ← `DmServer.convertDate(mrTemp.getApplicableFrom())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:applicableToDate` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(mrTemp.getApplicab`; `:applicableFromDate` ← `rsTemp.getDate("applicableFrom")`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:applicableFromDate` ← `DmServer.convertDate(mrTemp.getApplicableFrom())`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode()).toString()`; `:applicableFromDate` ← `DmServer.convertDate(maxApplicableDate)`; `:applicableToDate` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(mrTemp.getApplicab`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:uName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:applicableFrom` ← `DmShared.xNull(mrTemp.getApplicableFrom())`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:code` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(this))`; `:vehicleTypeCode` ← `DmShared.foriegnKey(gtc.getVehicleTypeCode())`; `:rateType` ← `DmShared.foriegnKey(gtc.getRateTypeCode())`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:cityCode` ← `DmShared.foriegnKey(gtc.getCityCode())`; `:areaCode` ← `DmShared.foriegnKey(gtc.getAreaCode())`; `:transporterCode` ← `DmShared.foriegnKey(gtc.getSharedKeyFieldCode())`; `:rate` ← `DmServer.vNull(gtc.getRate(), "0.00")`; `:applicableFrom` ← `DmServer.convertDate(mrTemp.getApplicableFrom())`; `:applicableTo` ← `DmServer.convertDate(applicableTo)`
- Errors: "Invalid key." | "Transporter rate contract already exists with this name!" | "Record has been changed, can't update record, refresh record then update"
- Other calls: `FREIGHT.getId`, `TranspoterRateContract_Vld.chkSave`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `SELECT COUNT(tab.cnt) AS cnt FROM (SELECT td.applicableFrom AS cnt FROM tpRateContractDetail td WHERE td.code = :code GROUP BY td.applicableFrom ) AS tab`
- SQL 2: `Delete From tpRateContractDetail Where Code= :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "Record have multiple Contract Details. Could not delete this record."

### `getFind`
`public DmResultPageing<List<TranspoterRateContract_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select trc.code as Code , trc.name From tpRateContract trc INNER JOIN ( SELECT DISTINCT trcd.code, trcd.site_Code from tpRateContractDetail trcd WHERE trcd.nature=:nature) AS trcd ON trcd.code = trc.code WHERE trcd.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`
- Other calls: `FREIGHT.getId`

### `getTxtHCopyFromContract`
`public DmResultPageing<List<TxtHCopyFromContract_Mdl>> getTxtHCopyFromContract(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tc.code , MAX(tc.name) AS name , tcd.site_Code , MAX(sm.Name) AS siteName , tcd.applicableFrom FROM tpRateContract tc INNER JOIN tpRateContractDetail tcd ON (tcd.code = tc.code AND tcd.nature = :nature) LEFT JOIN siteMast sm ON sm.code = tcd.site_Code GROUP BY tc.code, tcd.site_Code, tcd.applicableFrom`
- Params: `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`
- Other calls: `FREIGHT.getId`

### `fillGridApplicableDate`
`public DmResult<List<KeyFieldExtended>> fillGridApplicableDate(String code)`

- SQL 1: `SELECT trcd.code, trcd.applicableFrom , MAX(trcd.applicableTo) AS applicableTo FROM tpRateContractDetail trcd WHERE trcd.code = :code and trcd.nature = :nature AND trcd.site_Code = :siteCode GROUP BY trcd.code, trcd.applicableFrom ORDER BY trcd.applicableFrom`
- Params: `:code` ← `code`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `FREIGHT.getId`

### `getMaxApplicableDate`
`public DmResult<KeyFieldExtended> getMaxApplicableDate(String code)`

- SQL 1: `select top 1 trc.code, max(trc.name) as name , max(trcd.applicableFrom) as applicableFrom , trcd.site_Code , MAX(sm.name) AS siteName FROM tpRateContract trc LEFT JOIN tpRateContractDetail trcd ON trcd.code = trc.code LEFT JOIN siteMast sm on sm.code = trcd.site_Code WHERE trc.code = :code and trcd.nature = :nature AND trcd.site_Code = :siteCode GROUP BY trc.code, trcd.applicableFrom ,trcd.site_Code ORDER BY trcd.applicableFrom desc`
- Params: `:code` ← `code`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:siteCode` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `FREIGHT.getId`

### `deleteRecordApplicable`
`public DmResult<String> deleteRecordApplicable(String strKey, String applicableDate)`

- SQL 1: `SELECT applicableFrom , applicableTo FROM tpRateContractDetail WHERE code= :code and applicableFrom= :applicableFrom and nature= :nature`
- SQL 2: `SELECT TOP 1 code, applicableFrom, applicableTo FROM tpRateContractDetail WHERE code= :code AND applicableFrom < :applicableFrom AND nature= :nature ORDER BY applicableFrom DESC`
- SQL 3: `update tpRateContractDetail set applicableTo=:applicableTo where code = :code and ApplicableFrom = :ApplicableFrom and nature = :nature`
- SQL 4: `delete from tpRateContractDetail where code=:code and applicableFrom= :applicableFrom and nature= :nature`
- Params: `:code` ← `DmShared.iNull(strKey).toString()`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:applicableFrom` ← `DmServer.convertDate(applicableDate)`; `:code` ← `DmShared.iNull(strKey).toString()`; `:applicableFrom` ← `DmServer.convertDate(applicableDate)`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:applicableTo` ← `applicableToDate`; `:code` ← `DmShared.iNull(strKey).toString()`; `:ApplicableFrom` ← `rsTemp.getString("ApplicableFrom")`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:code` ← `DmShared.iNull(strKey).toString()`; `:applicableFrom` ← `DmServer.convertDate(applicableDate)`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`
- Errors: "Applicable From Date is invalid!"
- Other calls: `FREIGHT.getId`

### `fillGrid`
`public DmResult<List<TranspoterDetail_Mdl>> fillGrid(String code, String copySiteCode, String applicableFromDate, boolean isCopyFrom)`


### `fillTransporterGridDetails`
`private List<TranspoterDetail_Mdl> fillTransporterGridDetails(String keyCode, String siteCode, String applicableFromDate, boolean isCopyFrom, Connection cnTemp) throws Exception`

- SQL 1: `SELECT trcd.transporterCode, sg.name as transporterName, sg.manualCode , c.cityCode as cityCode, c.cityName as cityName , sm.name as stateName, trcd.areaCode, am.name AS areaName , trcd.site_Code as siteCode , trcd.rate as rate, trcd.vehicleTypeCode as vehicleTypeCode, vt.name as veichleTypeName, trcd.type as rateType, trcd.nature as nature , CONCAT(sg.mobile, IIF(ISNULL(sg.phone, '') <> '',', '+sg.phone, '')) AS contact FROM tpRateContractDetail AS trcd LEFT JOIN SubGroup AS sg ON sg.SubCode=trcd.transporterCode LEFT JOIN city c ON c.cityCode = trcd.cityCode LEFT JOIN stateMast sm ON c.stateCode = sm.code LEFT JOIN vehicleTypeMast vt on vt.code = trcd.vehicleTypeCode LEFT JOIN areaMast am ON am.code = trcd.areaCode where trcd.code=:code and trcd.applicableFrom =:applicableFrom and trcd.nature =:nature and trcd.site_Code =:site_Code`
- Params: `:code` ← `DmShared.iNull(keyCode).toString()`; `:applicableFrom` ← `DmServer.convertDate(applicableFromDate)`; `:nature` ← `DmShared.foriegnKey(DmShared.iNull(STEnum.TransporterRateNature.FREIGHT.getId()).toString(`; `:site_Code` ← `DmShared.xNull(siteCode)`
- Errors: "Site code is not vaild."
- Other calls: `FREIGHT.getId`, `TransporterRateNature.getViaKey`, `TransporterRateType.getViaKey`

### `getTxtHTransporter`
`public DmResultPageing<List<TranspoterDetail_Mdl>> getTxtHTransporter(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityCode, c.cityName, sm.name as stateName, CONCAT( ISNULL(sg.phone,'') , CASE WHEN ISNULL(sg.phone,'') <> '' THEN ',' ELSE '' END , ISNULL(sg.mobile,'') ) AS contact FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN stateMast sm ON c.stateCode = sm.code WHERE sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHCity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode as code ,ct.cityName as name,ct.stateCode,sm.name as stateName from city ct left join stateMast sm on ct.stateCode = sm.code`

### `getTxtHArea`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHArea(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT am.code, am.name FROM areaMast am`

### `getTxtHVeichleType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVeichleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name FROM vehicleTypeMast vt`

### `getTxtHRateType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHRateType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransporterRateType.values`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransporterRateNature.values`

### `getSuggestionName`
`public DmResultPageing<List<String>> getSuggestionName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select name From tpRateContract`

### `saveContractName`
`public DmResult<String> saveContractName(KeyFieldExtended mrTemp)`

- SQL 1: `SELECT COUNT(*) AS Cnt FROM tpRateContract tcr WHERE code <>:code AND tcr.name =:name`
- SQL 2: `INSERT INTO tpRateContract( code, name ,preparedBy , preparedDt, u_EntDt_LatestLine, preparedByIP ) VALUES (:code,:name ,:preparedBy,:preparedDt ,:u_EntDt_LatestLine, :preparedByIP )`
- SQL 3: `UPDATE tpRateContract SET name = :name, u_EntDt_LatestLine = :u_EntDt_LatestLine WHERE code = :code`
- Params: `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:u_EntDt_LatestLine` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(this)`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:u_EntDt_LatestLine` ← `timestamp`; `:name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`
- Errors: "Transporter rate contract already exists with this name!" | "Invalid key."
- Other calls: `TranspoterRateContract_Vld.chkSaveContractName`

### `getTxtHName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT tprc.code AS code , tprc.name AS name FROM tpRateContract tprc`

### `deleteContractName`
`public DmResult<String> deleteContractName(String strKey)`

- SQL 1: `SELECT COUNT(td.code) AS cnt FROM tpRateContractDetail td WHERE td.code = :code`
- SQL 2: `Delete From tpRateContract Where Code = :code`
- Params: `:code` ← `strKey`; `:code` ← `strKey`
- Errors: "can not delete Contract name is in used!"

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Transporter rate contract already exists with this name!" |
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (!DmShared.xNull(rsTemp.getString("invalidRecordName")).trim().equalsIgnoreCase(""))` | "invalidRecordName" |
| deleteRecord | `if (rsTemp.getLong("cnt") > 1)` | "Record have multiple Contract Details. Could not delete this record." |
| deleteRecordApplicable | `if (rsTemp.next())` | "Applicable From Date is invalid!" |
| fillTransporterGridDetails | `if (isCopyFrom)` | "Site code is not vaild." |
| saveContractName | `if (rsTemp.getLong("cnt") > 0)` | "Transporter rate contract already exists with this name!" |
| saveContractName | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |
| deleteContractName | `if (rsTemp.getLong("cnt") > 0)` | "can not delete Contract name is in used!" |

## Notes (hand-written)
_No hand-written notes yet._
