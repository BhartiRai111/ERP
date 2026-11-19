# pb-master / environmentSettingCompany

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/environmentSettingCompany/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: enviroCompany
- Update tables: enviroCompany, im, SubGroup, hl7Mast
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: A/c Group Department (Text); A/c Group Employee (Text); A/c Group Supplier (Text); A/c Group Manufacturer (Text); A/c Group Division (Text); A/c Group Customer (Text); A/c Group Transporter (Text); Finished Item Unit (Text); Inventory Accounting Active (Text); A/c Process Group (Text); Production Size Unit (Text); Local Currency (Text)
- Lookups/suggestions: getTxtHACGroup, getTxtHYesNo, getTxtHUnit, getTxtHDefaultPeriodicity, getTxtHDesignation, getTxtHDirectSupplyWarehouse, getTxtHGUITemplate, getTxtHLocalCurrency, getTxtHSite
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `EnvironmentSettingCompany`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | A/c Group Department | DmTextHelp | `txtHACGroupDepartment` | `KeyField_PrpAcs, 3, true` |
| 2 | A/c Group Employee | DmTextHelp | `txtHACGroupEmployee` | `KeyField_PrpAcs, 3, true` |
| 3 | A/c Group Supplier | DmTextHelp | `txtHACGroupSupplier` | `KeyField_PrpAcs, 3, true` |
| 4 | A/c Group Manufacturer | DmTextHelp | `txtHACGroupManufacturer` | `KeyField_PrpAcs, 3, true` |
| 5 | A/c Group Division | DmTextHelp | `txtHACGroupDivision` | `KeyField_PrpAcs, 3, true` |
| 6 | A/c Group Customer | DmTextHelp | `txtHACGroupCustomer` | `KeyField_PrpAcs, 3, true` |
| 7 | A/c Group Transporter | DmTextHelp | `txtHACGroupTransporter` | `KeyField_PrpAcs, 3, true` |
| 8 | A/c Group Process | DmTextHelp | `txtHACGroupProcess` | `KeyField_PrpAcs, 6, true` |
| 9 | Finished Item Unit | DmTextHelp | `txtHFinishedItemUnit` | `KeyField_PrpAcs, 5, true` |
| 10 | Inventory Accounting Active | DmTextHelp | `txtHInventoryAccountingActive` | `KeyField_PrpAcs, 3, true` |
| 11 | Production Size Unit | DmTextHelp | `txtHProductionSizeUnit` | `KeyField_PrpAcs, 5, true` |
| 12 | Default Ledger Periodicity | DmTextHelp | `txtHDefaultPeriodicity` | `KeyField_PrpAcs, 5, false` |
| 13 | Owner Designation | DmTextHelp | `txtHOwnerDesignation` | `KeyField_PrpAcs, 4` |
| 14 | AO Designation | DmTextHelp | `txtHAoDesignation` | `KeyField_PrpAcs, 4` |
| 15 | SO Designation | DmTextHelp | `txtHSrDesignation` | `KeyField_PrpAcs, 4` |
| 16 | Relationship Manager Designation | DmTextHelp | `txtHRsmDesignation` | `KeyField_PrpAcs, 4` |
| 17 | Lab Chemist Designation | DmTextHelp | `txtHLabChemistDesignation` | `KeyField_PrpAcs, 4` |
| 18 | Direct Supply Warehouse | DmTextHelp | `txtHDirectSupplyWarehouse` | `KeyField_PrpAcs, 6, false` |
| 19 | Local Currency | DmTextHelp | `txtHLocalCurrency` | `KeyField_PrpAcs, 7, true` |
| 20 | Default Sites (Party/Item Master) | DmTextHelpMulti | `txtHDefaultSites` | `TxtHSite_PrpAcs, 5, true` |

Other widgets (not in tab list): `txtHCustomerGUITemplate` (DmTextHelp), `txtHSupplierGUITemplate` (DmTextHelp), `txtHTransporterGUITemplate` (DmTextHelp), `grdPriority` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHACGroupDepartment`: Name [Text, w0, Name]
- `txtHACGroupEmployee`: Name [Text, w0, Name]
- `txtHACGroupSupplier`: Name [Text, w0, Name]
- `txtHACGroupManufacturer`: Name [Text, w0, Name]
- `txtHACGroupDivision`: Name [Text, w0, Name]
- `txtHACGroupCustomer`: Name [Text, w0, Name]
- `txtHACGroupTransporter`: Name [Text, w0, Name]
- `txtHFinishedItemUnit`: Unit [Text, w0, name]
- `txtHInventoryAccountingActive`: Active [Text, w0]
- `txtHACGroupProcess`: Name [Text, w100, Name]
- `txtHProductionSizeUnit`: Unit [Text, w80, name]
- `txtHDefaultPeriodicity`: Periodicity [Text, w80, name]
- `txtHOwnerDesignation`: Name [Text, w0, NAME]
- `txtHAoDesignation`: Name [Text, w0, NAME]
- `txtHSrDesignation`: Name [Text, w0, NAME]
- `txtHRsmDesignation`: Name [Text, w0, NAME]
- `txtHLabChemistDesignation`: Name [Text, w0, NAME]
- `txtHDirectSupplyWarehouse`: Name [Text, w0, name]
- `txtHCustomerGUITemplate`: Name [Text, w50, Name]
- `txtHSupplierGUITemplate`: Name [Text, w50, Name]
- `txtHTransporterGUITemplate`: Name [Text, w50, Name]
- `txtHLocalCurrency`: Name [Text, w0, Name]
- `txtHDefaultSites`: Site [Text, w0, name]
- `grdPriority`: Priority [Text, w55, shortName]; Description [Text, w310, name]; Priority Index [Text, w100, priorityIndex]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHACGroupDepartment` → `getTxtHACGroup`
- `txtHACGroupEmployee` → `getTxtHACGroup`
- `txtHACGroupSupplier` → `getTxtHACGroup`
- `txtHACGroupManufacturer` → `getTxtHACGroup`
- `txtHACGroupDivision` → `getTxtHACGroup`
- `txtHACGroupCustomer` → `getTxtHACGroup`
- `txtHACGroupTransporter` → `getTxtHACGroup`
- `txtHFinishedItemUnit` → `getTxtHUnit`
- `txtHInventoryAccountingActive` → `getTxtHYesNo`
- `txtHACGroupProcess` → `getTxtHACGroup`
- `txtHProductionSizeUnit` → `getTxtHUnit`
- `txtHDefaultPeriodicity` → `getTxtHDefaultPeriodicity`
- `txtHOwnerDesignation` → `getTxtHDesignation`
- `txtHAoDesignation` → `getTxtHDesignation`
- `txtHSrDesignation` → `getTxtHDesignation`
- `txtHRsmDesignation` → `getTxtHDesignation`
- `txtHLabChemistDesignation` → `getTxtHDesignation`
- `txtHDirectSupplyWarehouse` → `getTxtHDirectSupplyWarehouse`
- `txtHCustomerGUITemplate` → `getTxtHGUITemplate`
- `txtHSupplierGUITemplate` → `getTxtHGUITemplate`
- `txtHTransporterGUITemplate` → `getTxtHGUITemplate`
- `txtHLocalCurrency` → `getTxtHLocalCurrency`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `AcGroupDepartCode` ← DmShared.xNull(txtHACGroupDepartment.getTag())
- `AcGroupEmpCode` ← DmShared.xNull(txtHACGroupEmployee.getTag())
- `AcGroupEmpName` ← DmShared.xNull(txtHACGroupEmployee.getText())
- `AcGroupSupplierCode` ← DmShared.xNull(txtHACGroupSupplier.getTag())
- `AcGroupSupplierName` ← DmShared.xNull(txtHACGroupSupplier.getText())
- `AcGroupManufacturerCode` ← DmShared.xNull(txtHACGroupManufacturer.getTag())
- `AcGroupManufacturerName` ← DmShared.xNull(txtHACGroupManufacturer.getText())
- `AcGroupDivisionCode` ← DmShared.xNull(txtHACGroupDivision.getTag())
- `AcGroupDivisionName` ← DmShared.xNull(txtHACGroupDivision.getText())
- `AcGroupCustomerCode` ← DmShared.xNull(txtHACGroupCustomer.getTag())
- `AcGroupCustomerName` ← DmShared.xNull(txtHACGroupCustomer.getText())
- `AcGroupTransporterCode` ← DmShared.xNull(txtHACGroupTransporter.getTag())
- `AcGroupTransporterName` ← DmShared.xNull(txtHACGroupTransporter.getText())
- `FinishedItemUnitCode` ← DmShared.xNull(txtHFinishedItemUnit.getTag())
- `ProcessGroupCode` ← DmShared.xNull(txtHACGroupProcess.getTag())
- `ProcessGroupName` ← DmShared.xNull(txtHACGroupProcess.getText())
- `ProductionSizeUnitCode` ← DmShared.xNull(txtHProductionSizeUnit.getTag())
- `ProductionSizeUnitName` ← DmShared.xNull(txtHProductionSizeUnit.getText())
- `InventoryAccountingActive` ← DmShared.xNull(txtHInventoryAccountingActive.getTag())
- `DefaultPeriodicityCode` ← DmShared.xNull(txtHDefaultPeriodicity.getTag())
- `DefaultPeriodicity` ← DmShared.xNull(txtHDefaultPeriodicity.getText())
- `OwnerDesignationCode` ← DmShared.xNull(txtHOwnerDesignation.getTag())
- `OwnerDesignationName` ← DmShared.xNull(txtHOwnerDesignation.getText())
- `AoDesignationCode` ← DmShared.xNull(txtHAoDesignation.getTag())
- `AoDesignationName` ← DmShared.xNull(txtHAoDesignation.getText())
- `SrDesignationCode` ← DmShared.xNull(txtHSrDesignation.getTag())
- `SrDesignationName` ← DmShared.xNull(txtHSrDesignation.getText())
- `RsmDesignationCode` ← DmShared.xNull(txtHRsmDesignation.getTag())
- `RsmDesignationName` ← DmShared.xNull(txtHRsmDesignation.getText())
- `LabChemistDesignationCode` ← DmShared.xNull(txtHLabChemistDesignation.getTag())
- `LabChemistDesignationName` ← DmShared.xNull(txtHLabChemistDesignation.getText())
- `DirectSupplyWarehouseTag` ← DmShared.xNull(txtHDirectSupplyWarehouse.getTag())
- `DirectSupplyWarehouseText` ← DmShared.xNull(txtHDirectSupplyWarehouse.getText())
- `CustomerGuiTemplateCode` ← DmShared.xNull(txtHCustomerGUITemplate.getTag())
- `CustomerGuiTemplateText` ← DmShared.xNull(txtHCustomerGUITemplate.getText())
- `SupplierGuiTemplateCode` ← DmShared.xNull(txtHSupplierGUITemplate.getTag())
- `SupplierGuiTemplateText` ← DmShared.xNull(txtHSupplierGUITemplate.getText())
- `TransporterGuiTemplateCode` ← DmShared.xNull(txtHTransporterGUITemplate.getTag())
- `TransporterGuiTemplateText` ← DmShared.xNull(txtHTransporterGUITemplate.getText())
- `LocalCurrencyCode` ← DmShared.xNull(txtHLocalCurrency.getTag())
- `LocalCurrencyName` ← DmShared.xNull(txtHLocalCurrency.getText())
- `DefaultSitesCode` ← DmShared.xNull(txtHDefaultSites.getTag())
- `DefaultSitesName` ← DmShared.xNull(txtHDefaultSites.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtHACGroupDepartment.setTag` ← DmShared.xNull(mrTemp.getAcGroupDepartCode())
- `txtHACGroupDepartment.setText` ← DmShared.xNull(mrTemp.getAcGroupDepartName())
- `txtHACGroupEmployee.setTag` ← DmShared.xNull(mrTemp.getAcGroupEmpCode())
- `txtHACGroupEmployee.setText` ← DmShared.xNull(mrTemp.getAcGroupEmpName())
- `txtHACGroupSupplier.setTag` ← DmShared.xNull(mrTemp.getAcGroupSupplierCode())
- `txtHACGroupSupplier.setText` ← DmShared.xNull(mrTemp.getAcGroupSupplierName())
- `txtHACGroupManufacturer.setTag` ← DmShared.xNull(mrTemp.getAcGroupManufacturerCode())
- `txtHACGroupManufacturer.setText` ← DmShared.xNull(mrTemp.getAcGroupManufacturerName())
- `txtHACGroupDivision.setTag` ← DmShared.xNull(mrTemp.getAcGroupDivisionCode())
- `txtHACGroupDivision.setText` ← DmShared.xNull(mrTemp.getAcGroupDivisionName())
- `txtHACGroupCustomer.setTag` ← DmShared.xNull(mrTemp.getAcGroupCustomerCode())
- `txtHACGroupCustomer.setText` ← DmShared.xNull(mrTemp.getAcGroupCustomerName())
- `txtHACGroupTransporter.setTag` ← DmShared.xNull(mrTemp.getAcGroupTransporterCode())
- `txtHACGroupTransporter.setText` ← DmShared.xNull(mrTemp.getAcGroupTransporterName())
- `txtHACGroupProcess.setTag` ← DmShared.xNull(mrTemp.getProcessGroupCode())
- `txtHACGroupProcess.setText` ← DmShared.xNull(mrTemp.getProcessGroupName())
- `txtHFinishedItemUnit.setTag` ← DmShared.xNull(mrTemp.getFinishedItemUnitCode())
- `txtHFinishedItemUnit.setText` ← DmShared.xNull(mrTemp.getFinishedItemUnitName())
- `txtHProductionSizeUnit.setTag` ← DmShared.xNull(mrTemp.getProductionSizeUnitCode())
- `txtHProductionSizeUnit.setText` ← DmShared.xNull(mrTemp.getProductionSizeUnitName())
- `txtHDefaultPeriodicity.setTag` ← DmShared.xNull(mrTemp.getDefaultPeriodicityCode())
- `txtHDefaultPeriodicity.setText` ← DmShared.xNull(mrTemp.getDefaultPeriodicity())
- `txtHOwnerDesignation.setTag` ← DmShared.xNull(mrTemp.getOwnerDesignationCode())
- `txtHOwnerDesignation.setText` ← DmShared.xNull(mrTemp.getOwnerDesignationName())
- `txtHAoDesignation.setTag` ← DmShared.xNull(mrTemp.getAoDesignationCode())
- `txtHAoDesignation.setText` ← DmShared.xNull(mrTemp.getAoDesignationName())
- `txtHSrDesignation.setTag` ← DmShared.xNull(mrTemp.getSrDesignationCode())
- `txtHSrDesignation.setText` ← DmShared.xNull(mrTemp.getSrDesignationName())
- `txtHRsmDesignation.setTag` ← DmShared.xNull(mrTemp.getRsmDesignationCode())
- `txtHRsmDesignation.setText` ← DmShared.xNull(mrTemp.getRsmDesignationName())
- `txtHLabChemistDesignation.setTag` ← DmShared.xNull(mrTemp.getLabChemistDesignationCode())
- `txtHLabChemistDesignation.setText` ← DmShared.xNull(mrTemp.getLabChemistDesignationName())
- `txtHDirectSupplyWarehouse.setTag` ← DmShared.xNull(mrTemp.getDirectSupplyWarehouseTag())
- `txtHDirectSupplyWarehouse.setText` ← DmShared.xNull(mrTemp.getDirectSupplyWarehouseText())
- `txtHCustomerGUITemplate.setTag` ← DmShared.xNull(mrTemp.getCustomerGuiTemplateCode())
- `txtHCustomerGUITemplate.setText` ← DmShared.xNull(mrTemp.getCustomerGuiTemplateText())
- `txtHSupplierGUITemplate.setTag` ← DmShared.xNull(mrTemp.getSupplierGuiTemplateCode())
- `txtHSupplierGUITemplate.setText` ← DmShared.xNull(mrTemp.getSupplierGuiTemplateText())
- `txtHTransporterGUITemplate.setTag` ← DmShared.xNull(mrTemp.getTransporterGuiTemplateCode())
- `txtHTransporterGUITemplate.setText` ← DmShared.xNull(mrTemp.getTransporterGuiTemplateText())
- `txtHLocalCurrency.setTag` ← DmShared.xNull(mrTemp.getLocalCurrencyCode())
- `txtHLocalCurrency.setText` ← DmShared.xNull(mrTemp.getLocalCurrencyName())
- `txtHDefaultSites.setTag` ← DmShared.xNull(mrTemp.getDefaultSitesCode())
- `txtHDefaultSites.setText` ← DmShared.xNull(mrTemp.getDefaultSitesName())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHFinishedItemUnit.setEnabled(false)`
- `txtHFinishedItemUnit.setEnabled(true)`

**`manageDisplay()` logic (condensed code):**
```java
if (DmShared.iNull(dvm.getIsFinishedItemUsed()).intValue() == 1) {
txtHFinishedItemUnit.setEnabled(false);
} else {
txtHFinishedItemUnit.setEnabled(true);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHACGroupDepartment.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHACGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHACGroupDepartment).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHACGroupDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupDepartment).show();
```

`txtHACGroupDepartment.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHACGroupDepartment.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupDepartment).show();
```

`txtHACGroupEmployee.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHACGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHACGroupEmployee).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHACGroupEmployee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupEmployee).show();
```

`txtHACGroupEmployee.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHACGroupEmployee.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupEmployee).show();
```

`txtHACGroupSupplier.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHACGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHACGroupSupplier).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHACGroupSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupSupplier).show();
```

`txtHACGroupSupplier.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHACGroupSupplier.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupSupplier).show();
```

`txtHACGroupManufacturer.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHACGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHACGroupManufacturer).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHACGroupManufacturer.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupManufacturer).show();
```

`txtHACGroupManufacturer.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHACGroupManufacturer.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupManufacturer).show();
```

`txtHACGroupDivision.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHACGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHACGroupDivision).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHACGroupDivision.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupDivision).show();
```

`txtHACGroupDivision.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHACGroupDivision.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupDivision).show();
```

`txtHACGroupCustomer.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHACGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHACGroupCustomer).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHACGroupCustomer.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupCustomer).show();
```

`txtHACGroupCustomer.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHACGroupCustomer.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupCustomer).show();
```

`txtHACGroupTransporter.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHACGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHACGroupTransporter).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHACGroupTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupTransporter).show();
```

`txtHACGroupTransporter.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHACGroupTransporter.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupTransporter).show();
```

`txtHFinishedItemUnit.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(String.valueOf(STEnum.UnitNature.MASS.getId()), helper, blnToEndOfFile, 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFinishedItemUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFinishedItemUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFinishedItemUnit).show();
```

`txtHFinishedItemUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFinishedItemUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFinishedItemUnit).show();
```

`tabPanel.addSelectionHandler` → `onSelection()`
```java
if (event.getSelectedItem() == 1) {
```

`txtHInventoryAccountingActive.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHInventoryAccountingActive).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHInventoryAccountingActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInventoryAccountingActive).show();
```

`txtHInventoryAccountingActive.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHInventoryAccountingActive.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHInventoryAccountingActive).show();
```

`txtHACGroupProcess.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHACGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHACGroupProcess).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHACGroupProcess.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupProcess).show();
```

`txtHACGroupProcess.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHACGroupProcess.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHACGroupProcess).show();
```

`txtHProductionSizeUnit.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHUnit(String.valueOf(STEnum.UnitNature.LENGTH.getId()), helper, blnToEndOfFile
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProductionSizeUnit).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHProductionSizeUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProductionSizeUnit).show();
```

`txtHProductionSizeUnit.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProductionSizeUnit.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProductionSizeUnit).show();
```

`txtHDefaultPeriodicity.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDefaultPeriodicity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDefaultPeriodicity).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDefaultPeriodicity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDefaultPeriodicity).show();
```

`txtHDefaultPeriodicity.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDefaultPeriodicity.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHDefaultPeriodicity).show();
```

`txtHOwnerDesignation.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDesignation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHOwnerDesignation).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHOwnerDesignation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOwnerDesignation).show();
```

`txtHOwnerDesignation.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHOwnerDesignation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHOwnerDesignation).show();
```

`txtHAoDesignation.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDesignation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAoDesignation).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAoDesignation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAoDesignation).show();
```

`txtHAoDesignation.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAoDesignation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAoDesignation).show();
```

`txtHSrDesignation.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDesignation(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSrDesignation).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSrDesignation.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSrDesignation).show();
```
- Confirm: "Are you sure? You want to export records."
- Confirm: "Are you sure? You want to close."

### Screen `PriorityGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Priority | DmTextBox | `txtPriority` | `1, false` |
| 2 | Description | DmTextBox | `txtDescriprion` | `100, false` |
| 3 | Priority Index | DmTextBoxNumeric | `txtSorting` | `0, false, 2, true` |
| 4 | Color | DmTextBox | `txtColor` | `35, false` |
- Alert: "order Index already exists."
- Alert: "Sorting is mandatory"

## Files
| file | lines |
|---|---|
| client/EnvironmentSettingCompany.java | 1067 |
| client/EnvironmentSettingCompany_Dsg.java | 864 |
| client/PriorityGridEdit.java | 118 |
| client/PriorityGridEdit_Dsg.java | 95 |
| server/EnvironmentSettingCompany_SrvImpl.java | 1078 |
| shared/DefaultValue_Mdl.java | 23 |
| shared/EnvironmentSettingCompany_Mdl.java | 431 |
| shared/EnvironmentSettingCompany_Srv.java | 35 |
| shared/EnvironmentSettingCompany_SrvAsync.java | 35 |
| shared/EnvironmentSettingCompany_Vld.java | 24 |
| shared/GrdPriority_Mdl.java | 42 |

## UI labels / columns (client)
`Priority`, `Description`, `Priority Index`, `Color`, `Account Details`, `A/c Group Department`, `A/c Group Employee`, `A/c Group Supplier`, `A/c Group Manufacturer`, `A/c Group Division`, `A/c Group Customer`, `A/c Group Transporter`, `A/c Group Process`, `Other Details`, `Finished Item Unit`, `Inventory Accounting Active`, `Production Size Unit`, `Default Ledger Periodicity`, `Owner Designation`, `AO Designation`, `SO Designation`, `Relationship Manager Designation`, `Lab Chemist Designation`, `Direct Supply Warehouse`, `Local Currency`, `Default Sites (Party/Item Master)`

## Models (shared DTOs)
### DefaultValue_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | isFinishedItemUsed |

### EnvironmentSettingCompany_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | acGroupDepartCode |
| String | acGroupDepartName |
| String | acGroupEmpCode |
| String | acGroupEmpName |
| String | acGroupSupplierCode |
| String | acGroupSupplierName |
| String | acGroupManufacturerCode |
| String | acGroupManufacturerName |
| String | acGroupDivisionCode |
| String | acGroupDivisionName |
| String | acGroupCustomerCode |
| String | acGroupCustomerName |
| String | acGroupTransporterCode |
| String | acGroupTransporterName |
| String | processGroupCode |
| String | processGroupName |
| String | finishedItemUnitCode |
| String | finishedItemUnitName |
| String | productionSizeUnitCode |
| String | productionSizeUnitName |
| String | InventoryAccountingActive |
| String | defaultPeriodicityCode |
| String | defaultPeriodicity |
| String | ownerDesignationCode |
| String | ownerDesignationName |
| String | aoDesignationCode |
| String | aoDesignationName |
| String | srDesignationCode |
| String | srDesignationName |
| String | rsmDesignationCode |
| String | rsmDesignationName |
| String | labChemistDesignationCode |
| String | labChemistDesignationName |
| String | directSupplyWarehouseTag |
| String | directSupplyWarehouseText |
| String | transporterGuiTemplateCode |
| String | transporterGuiTemplateText |
| String | supplierGuiTemplateCode |
| String | supplierGuiTemplateText |
| String | customerGuiTemplateCode |
| String | customerGuiTemplateText |
| String | localCurrencyCode |
| String | localCurrencyName |
| String | defaultSitesCode |
| String | defaultSitesName |
| List<GrdPriority_Mdl> | grdPriorityList |

### GrdPriority_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | sorting |
| String | color |

## RPC interface
| method | params | returns |
|---|---|---|
| getMoveRec | String strKey | DmResult<EnvironmentSettingCompany_Mdl> |
| saveRecord | EnvironmentSettingCompany_Mdl mrTemp | DmResult<String> |
| getTxtHACGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportJSON | String sessionId | DmResult<KeyFieldExtended> |
| getTxtHUnit | String nature, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDefaultPeriodicity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDesignation | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDirectSupplyWarehouse | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValue |  | DmResult<DefaultValue_Mdl> |
| getTxtHGUITemplate | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLocalCurrency | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `EnvironmentSettingCompany_Vld`
```java
public class EnvironmentSettingCompany_Vld {
    public static void chkSave(EnvironmentSettingCompany_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getAcGroupDepartCode(), "A/c Group Department", 100);
        DmShared.isValidText(mrTemp.getAcGroupEmpCode(), "A/c Group Employee", 200);
        DmShared.isValidText(mrTemp.getAcGroupSupplierCode(), "A/c Group Supplier", 300);
        DmShared.isValidText(mrTemp.getAcGroupManufacturerCode(), "A/c Group Manufacturer", 400);
        DmShared.isValidText(mrTemp.getAcGroupDivisionCode(), "A/c Group Division", 500);
        DmShared.isValidText(mrTemp.getAcGroupCustomerCode(), "A/c Group Customer", 600);
        DmShared.isValidText(mrTemp.getAcGroupTransporterCode(), "A/c Group Transporter", 700);
        DmShared.isValidText(mrTemp.getFinishedItemUnitCode(), "Finished Item Unit", 800);
        DmShared.isValidText(mrTemp.getInventoryAccountingActive(), "Inventory Accounting Active", 900);
        DmShared.isValidText(mrTemp.getProcessGroupCode(), "A/c Process Group", 1000);
        DmShared.isValidText(mrTemp.getProductionSizeUnitCode(), "Production Size Unit", 1100);
        DmShared.isValidText(mrTemp.getLocalCurrencyName(), "Local Currency", 1200);
    }
}
```

## Server `EnvironmentSettingCompany_SrvImpl.java` (1078 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteList`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getMoveRec`
`public DmResult<EnvironmentSettingCompany_Mdl> getMoveRec(String strKey)`


### `moveRec`
`private EnvironmentSettingCompany_Mdl moveRec(Connection cnTemp)`

- SQL 1: `SELECT ec.acGroupDepartmentCode, acg3.groupName AS acGroupDepartmentName, ec.acGroupEmployeeCode, acg6.groupName AS acGroupEmployeeName, ec.acGroupSupplierCode, acg7.groupName AS acGroupSupplierName, ec.acGroupManufacturerCode, acg8.groupName AS acGroupManufacturerName, ec.acGroupDivisionCode, acg9.groupName AS acGroupDivisionName, ec.acGroupCustomerCode, acg10.groupName AS acGroupCustomerName, ec.acGroupTransporterCode, acg11.groupName AS acGroupTransporterName, ec.acGroupProcessCode, acg12.groupName AS acGroupProcessName, ec.finishedItemUnit AS finishedItemUnitCode, um.unit AS finishedItemUnitName, ec.prod_SizeUnitCode, ums.unit AS prod_SizeUnitName, ec.isInventoryAccountingActive, ec.ac_LedgerPeriodicity, ec.ownerDesignationCode, ODM.Name AS ownerDesignationName, ec.srDesignationCode, SODM.Name AS soDesignationName, ec.rsmDesignationCode, SRMDM.name AS rsmDesignationName, ec.aoDesignationCode, AODM.Name AS aoDesignationName, ec.labChemistDesignationCode, LCDM.Name AS labChemistDesignationName, ec.directSupplyWarehouseCode, sg.name AS directSupplyWarehouseName, ec.customerGuiTemplateCode, gt.name AS customerGuiTemplateName, ec.supplierGuiTemplateCode, gtm.name AS supplierGuiTemplateName, ec.TransporterGuiTemplateCode, gtmm.name AS TransporterGuiTemplateName , ec.LocalCurrencyCode , cur.name AS LocalCurrencyName , ec.defaultSitesCode FROM enviroCompany ec Left join acGroup acg3 ON ec.acGroupDepartmentCode = acg3.groupCode Left join acGroup acg6 ON ec.acGroupEmployeeCode = acg6.groupCode Left join acGroup acg7 ON ec.acGroupSupplierCode = acg7.groupCode Left join acGroup acg8 ON ec.acGroupManufacturerCode = acg8.groupCode Left join acGroup acg9 ON ec.acGroupDivisionCode = acg9.groupCode Left join acGroup acg10 ON ec.acGroupCustomerCode = acg10.groupCode Left join acGroup acg11 ON ec.acGroupTransporterCode = acg11.groupCode Left join acGroup acg12 ON ec.acGroupProcessCode = acg12.groupCode LEFT JOIN DesignationMast ODM ON ODM.Code = ec.ownerDesignationCode LEFT JOIN DesignationMast SODM ON SODM.Code = ec.srDesignationCode LEFT JOIN DesignationMast SRMDM ON SRMDM.Code = ec.rsmDesignationCode LEFT JOIN DesignationMast AODM ON AODM.Code = ec.aoDesignationCode LEFT JOIN DesignationMast LCDM ON LCDM.Code = ec.labChemistDesignationCode LEFT JOIN subgroup sg ON sg.subcode = ec.directSupplyWarehouseCode LEFT JOIN unitMast um ON um.code = ec.finishedItemUnit LEFT JOIN unitMast ums ON ums.code = ec.prod_SizeUnitCode LEFT JOIN guiTemplateMast gt ON gt.code = ec.customerGuiTemplateCode LEFT JOIN guiTemplateMast gtm ON gtm.code = ec.supplierGuiTemplateCode LEFT JOIN guiTemplateMast gtmm ON gtmm.code = ec.TransporterGuiTemplateCode LEFT JOIN currency cur ON cur.code = ec.LocalCurrencyCode`
- SQL 2: `SELECT STRING_AGG(sm.code, ',') AS code , STRING_AGG(sm.name, ',') AS name FROM siteMast sm WHERE sm.code IN ( {…} :siteCode {…} )`
- SQL 3: `select code,name as description,shortName,priorityIndex,colorName from hl7Mast where tableName='priorityType' order by priorityIndex desc, shortName asc`

### `saveRecord`
`public DmResult<String> saveRecord(EnvironmentSettingCompany_Mdl mrTemp)`

- SQL 1: `select ec.finishedItemUnit from enviroCompany ec`
- SQL 2: `SELECT COUNT(*) cnt FROM itemBatchStock ibs WHERE ibs.balanceQty < 0`
- SQL 3: `SELECT count(*) as cnt FROM enviroCompany`
- SQL 4: `INSERT INTO enviroCompany ( acGroupDepartmentCode , acGroupEmployeeCode , acGroupSupplierCode , acGroupManufacturerCode , acGroupDivisionCode , acGroupCustomerCode , acGroupTransporterCode , acGroupProcessCode , finishedItemUnit , prod_SizeUnitCode , isInventoryAccountingActive , ownerDesignationCode , srDesignationCode , rsmDesignationCode , aoDesignationCode , labChemistDesignationCode , directSupplyWarehouseCode , ac_LedgerPeriodicity , customerGuiTemplateCode , supplierGuiTemplateCode , transporterGuiTemplateCode , LocalCurrencyCode , defaultSitesCode ) VALUES ( :acgroupdepartmentcode , :acGroupEmployeeCode , :acGroupSupplierCode , :acGroupManufacturerCode , :acGroupDivisionCode , :acGroupCustomerCode , :acGroupTransporterCode , :acGroupProcessCode , :finishedItemUnit , :prod_SizeUnitCode , :isInventoryAccountingActive , :ownerDesignationCode , :srDesignationCode , :rsmDesignationCode , :aoDesignationCode , :labChemistDesignationCode , :directSupplyWarehouseCode , :ac_LedgerPeriodicity , :customerGuiTemplateCode , :supplierGuiTemplateCode , :transporterGuiTemplateCode , :LocalCurrencyCode , :defaultSitesCode )`
- SQL 5: `update enviroCompany set acgroupdepartmentcode = :acgroupdepartmentcode, acGroupSupplierCode = :acGroupSupplierCode, acGroupEmployeeCode = :acGroupEmployeeCode, acGroupManufacturerCode = :acGroupManufacturerCode, acGroupDivisionCode = :acGroupDivisionCode, acGroupCustomerCode = :acGroupCustomerCode, acGroupTransporterCode = :acGroupTransporterCode, finishedItemUnit = :finishedItemUnit, acGroupProcessCode = :acGroupProcessCode, prod_SizeUnitCode = :prod_SizeUnitCode, isInventoryAccountingActive = :isInventoryAccountingActive, ownerDesignationCode = :ownerDesignationCode, srDesignationCode = :srDesignationCode, rsmDesignationCode = :rsmDesignationCode, aoDesignationCode = :aoDesignationCode, labChemistDesignationCode = :labChemistDesignationCode, directSupplyWarehouseCode = :directSupplyWarehouseCode, ac_LedgerPeriodicity = :ac_LedgerPeriodicity, customerGuiTemplateCode = :customerGuiTemplateCode, supplierGuiTemplateCode = :supplierGuiTemplateCode, transporterGuiTemplateCode = :transporterGuiTemplateCode, LocalCurrencyCode = :LocalCurrencyCode, defaultSitesCode = :defaultSitesCode`
- SQL 6: `UPDATE im SET im.allowNegativeStock = 0 FROM itemMast im LEFT JOIN enviroCompany ec ON 1 = 1 WHERE im.allowNegativeStock = 1 AND ec.isInventoryAccountingActive = 1`
- SQL 7: `update SubGroup set groupCode = :acgroupdepartmentcode where filterNature = {…} DPmanualCodesubgroupNatureCode {…}`
- SQL 8: `update SubGroup set groupCode = :acGroupSupplierCode where filterNature = {…} SUmanualCodesubgroupNatureCode {…}`
- SQL 9: `Update SubGroup set groupCode = :acGroupManufacturerCode where filterNature = {…} MFmanualCodesubgroupNatureCode {…}`
- SQL 10: `Update SubGroup set groupCode = :acGroupDivisionCode where filterNature = {…} DVmanualCodesubgroupNatureCode {…}`
- SQL 11: `Update SubGroup set isActive = 0 WHERE subCode = :directSupplyWarehouseCode`
- SQL 12: `update hl7Mast set priorityIndex = :priorityIndex, colorName = :colorName where code = :code`
- Params: `:acgroupdepartmentcode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDepartCode())`; `:acGroupEmployeeCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupEmpCode())`; `:acGroupSupplierCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupSupplierCode())`; `:acGroupManufacturerCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupManufacturerCode())`; `:acGroupDivisionCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDivisionCode())`; `:acGroupCustomerCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupCustomerCode())`; `:acGroupTransporterCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupTransporterCode())`; `:acGroupProcessCode` ← `DmShared.foriegnKey(mrTemp.getProcessGroupCode())`; `:finishedItemUnit` ← `DmShared.foriegnKey(mrTemp.getFinishedItemUnitCode())`; `:prod_SizeUnitCode` ← `DmShared.foriegnKey(mrTemp.getProductionSizeUnitCode())`; `:ownerDesignationCode` ← `DmShared.foriegnKey(mrTemp.getOwnerDesignationCode())`; `:srDesignationCode` ← `DmShared.foriegnKey(mrTemp.getSrDesignationCode())`; `:rsmDesignationCode` ← `DmShared.foriegnKey(mrTemp.getRsmDesignationCode())`; `:aoDesignationCode` ← `DmShared.foriegnKey(mrTemp.getAoDesignationCode())`; `:labChemistDesignationCode` ← `DmShared.foriegnKey(mrTemp.getLabChemistDesignationCode())`; `:directSupplyWarehouseCode` ← `DmShared.foriegnKey(mrTemp.getDirectSupplyWarehouseTag())`; `:isInventoryAccountingActive` ← `DmShared.iNull(mrTemp.getInventoryAccountingActive()).toString()`; `:ac_LedgerPeriodicity` ← `DmShared.xNull(mrTemp.getDefaultPeriodicityCode())`; `:customerGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getCustomerGuiTemplateCode())`; `:supplierGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getSupplierGuiTemplateCode())`; `:transporterGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getTransporterGuiTemplateCode())`; `:LocalCurrencyCode` ← `DmShared.foriegnKey(mrTemp.getLocalCurrencyCode())`; `:defaultSitesCode` ← `DmShared.foriegnKey(mrTemp.getDefaultSitesCode())`; `:acgroupdepartmentcode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDepartCode())`; `:acGroupEmployeeCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupEmpCode())`; `:acGroupSupplierCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupSupplierCode())`; `:acGroupManufacturerCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupManufacturerCode())`; `:acGroupDivisionCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDivisionCode())`; `:acGroupCustomerCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupCustomerCode())`; `:acGroupTransporterCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupTransporterCode())`; `:finishedItemUnit` ← `DmShared.foriegnKey(mrTemp.getFinishedItemUnitCode())`; `:acGroupProcessCode` ← `DmShared.foriegnKey(mrTemp.getProcessGroupCode())`; `:prod_SizeUnitCode` ← `DmShared.foriegnKey(mrTemp.getProductionSizeUnitCode())`; `:ownerDesignationCode` ← `DmShared.foriegnKey(mrTemp.getOwnerDesignationCode())`; `:srDesignationCode` ← `DmShared.foriegnKey(mrTemp.getSrDesignationCode())`; `:rsmDesignationCode` ← `DmShared.foriegnKey(mrTemp.getRsmDesignationCode())`; `:aoDesignationCode` ← `DmShared.foriegnKey(mrTemp.getAoDesignationCode())`; `:labChemistDesignationCode` ← `DmShared.foriegnKey(mrTemp.getLabChemistDesignationCode())`; `:directSupplyWarehouseCode` ← `DmShared.foriegnKey(mrTemp.getDirectSupplyWarehouseTag())`; `:isInventoryAccountingActive` ← `DmShared.iNull(mrTemp.getInventoryAccountingActive()).toString()`; `:ac_LedgerPeriodicity` ← `DmShared.xNull(mrTemp.getDefaultPeriodicityCode())`; `:customerGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getCustomerGuiTemplateCode())`; `:supplierGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getSupplierGuiTemplateCode())`; `:transporterGuiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getTransporterGuiTemplateCode())`; `:LocalCurrencyCode` ← `DmShared.foriegnKey(mrTemp.getLocalCurrencyCode())`; `:defaultSitesCode` ← `DmShared.foriegnKey(mrTemp.getDefaultSitesCode())`; `:acgroupdepartmentcode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDepartCode())`; `:acGroupSupplierCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupSupplierCode())`; `:acGroupManufacturerCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupManufacturerCode())`; `:acGroupDivisionCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupDivisionCode())`; `:directSupplyWarehouseCode` ← `DmShared.foriegnKey(mrTemp.getDirectSupplyWarehouseTag())`; `:code` ← `DmShared.xNull(grdPriority.getSharedKeyFieldCode())`; `:priorityIndex` ← `DmShared.iNull(grdPriority.getSorting()).toString()`; `:colorName` ← `DmShared.xNull(grdPriority.getColor()).trim()`
- Errors: "Finished item unit is used, you can not change this." | "Cannot activate inventory accounting as negative stock exists for items."

### `getTxtHACGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHACGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT groupCode AS code,groupName AS Name FROM acGroup`

### `getTxtHYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNo(DmHelper helper, boolean blnToEndOfFile)`


### `exportJSON`
`public DmResult<KeyFieldExtended> exportJSON(String session)`

- Errors: "Invalid request." | "File Name not given."

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(String nature, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select um.code,um.unit as name from unitMast um where um.nature = :nature`
- Params: `:nature` ← `DmShared.iNull(nature).toString()`

### `finishedItemUsed`
`private boolean finishedItemUsed(Connection cnTemp) throws Exception`

- SQL 1: `select COUNT(*) as cnt from itemMast where filterItemType = :filterItemType`
- Params: `:filterItemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHDefaultPeriodicity`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDefaultPeriodicity(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `LedgerPeriodicity.values`

### `getTxtHDesignation`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDesignation(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DM.Code,DM.Name FROM DesignationMast DM`

### `getTxtHDirectSupplyWarehouse`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDirectSupplyWarehouse(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select dm.subCode as code, sg.name from departmentMast as dm left join subGroup as sg on dm.subCode = sg.subCode where sg.isActive = 1 {…}`
- Other calls: `STServer.getSsnDepartmentCodeList`

### `getDefaultValue`
`public DmResult<DefaultValue_Mdl> getDefaultValue()`


### `getTxtHGUITemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGUITemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Code,Name FROM guiTemplateMast`

### `getTxtHLocalCurrency`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLocalCurrency(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT code , name FROM currency`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code , sm.name FROM siteMast sm where sm.code in( {…} :code {…} )`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `paperbiz.master.environmentSetting.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (finishedItemUsed(cnTemp))` | "Finished item unit is used, you can not change this." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Cannot activate inventory accounting as negative stock exists for items." |
| exportJSON | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportJSON | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
