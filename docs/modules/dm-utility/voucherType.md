# dm-utility / voucherType

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/voucherType/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: Voucher_Type
- Update tables: Voucher_Type
- Delete cascade (in order): siteWiseReportConfig, AcFilteration, itemCatFilteration, vouchertypeformlist, Voucher_Type
- Duplicate checks on save: none found
- "In use" checks before delete:
  - `Select Count(DocId) As Cnt From Ledger Where V_Type= :V_Type`
- Validations: Short Name (Text); Description (Text); Category Nature (Text); Category (Text); Default Report (Text); Landscape (Text); Active (Text); Bank Cash Book (Text)
- Lookups/suggestions: getSuggestionShortName, getSuggestionDescription, getTxtHNCat, getTxtHCategory, getTxtHParentVoucher, getTxtHMenuText, getTxtHDefaultReport, getTxtHDefaultAC, getTxtHYesNO, getTxtHNature, getTxtHFormApplied, getTxtHGUITemplate, getTxtHTemplateName, getTxtHPaymentMode, getTxtHIsDefault, getTxtHDocumentType, getTxtHDocumentNature, getTxtHSite, getTxtHPageSize, getTxtHItemCategory, getHEinvSupplyType, getTxtHDefaultBankAC
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DocumentTypeGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Document Type | DmTextHelp | `txtHDocumentType` | `KeyField_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHDocumentType`: Document Type [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHDocumentType` → `getTxtHDocumentType`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHDocumentType.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDocumentType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDocumentType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHDocumentType).show();
```

`txtHDocumentType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDocumentType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHDocumentType).show();
```

### Screen `ExportData`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdVoucherType` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdVoucherType`:  [Text, w25, v_Type]; Description [Text, w100, description]; Short Name [Text, w100, short_Name]; category [Text, w100, category]
- Confirm: "Are you sure? You want to export selected records."
- Alert: "Please select any voucher type to export."

### Screen `FilterationGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Nature | DmTextHelp | `txtHNature` | `KeyField_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHNature`: Name [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHNature` → `getTxtHNature`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHNature.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNature).show();
```

`txtHNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNature).show();
```

### Screen `FormAppliedGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Name | DmTextHelp | `txtHFormApplied` | `Grid_FormApplied, 10` |

Other widgets (not in tab list): `txtLC` (DmTextBox), `txtTRN` (DmTextBox)

**Grid / popup columns** (header, width, db field):
- `txtHFormApplied`: Name [Text, w120, Name]; Location [Text, w100, L_C]; Applied For [Text, w140, TrnType]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHFormApplied` → `getTxtHFormApplied`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHFormApplied.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHFormApplied(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFormApplied).show();
public void onSuccess(DmResultPageing<List<TxtHFormApplied_Mdl>> result) {
if (result.isStatus()) {
txtHFormApplied.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHFormApplied).show();
```

`txtHFormApplied.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFormApplied.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHFormApplied).show();
```

`txtHFormApplied.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (tRow != null) {
txtHFormApplied.setText(tRow.getSharedKeyFieldCode());
txtHFormApplied.setText(tRow.getSharedKeyFieldName());
txtTRN.setText(tRow.getAppliedFor());
txtLC.setText(tRow.getLocation());
```

### Screen `ItemCategoryGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Item Category | DmTextHelp | `txtHItemCategory` | `KeyField_PrpAcs, 10` |

**Grid / popup columns** (header, width, db field):
- `txtHItemCategory`: Category [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHItemCategory` → `getTxtHItemCategory`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItemCategory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHItemCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHItemCategory).show();
```

`txtHItemCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHItemCategory).show();
```

### Screen `ReceiptTypeGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Payment Mode | DmTextHelp | `txtHPaymentMode` | `DmtxtHPaymentMode_PrpAcs, 7, true` |
| 2 | Is Default | DmTextHelp | `txtHIsDefault` | `KeyField_PrpAcs, 5, true` |

**Grid / popup columns** (header, width, db field):
- `txtHPaymentMode`: Payment Mode [Text, w1, name]; Short Name [Text, w100, shortName]
- `txtHIsDefault`: Name [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHPaymentMode` → `getTxtHPaymentMode`
- `txtHIsDefault` → `getTxtHIsDefault`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHPaymentMode.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPaymentMode(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Tx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPaymentMode).show();
public void onSuccess(DmResultPageing<List<TxtHPaymentMode_Mdl>> result) {
if (result.isStatus()) {
txtHPaymentMode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHPaymentMode).show();
```

`txtHPaymentMode.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPaymentMode.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHPaymentMode).show();
```

`txtHPaymentMode.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtHPaymentMode.setTag2nd(DmShared.xNull(tRow.getShortName()));
txtHIsDefault.setTag2nd(DmShared.xNull(tRow.getCardType()));
} else {
txtHPaymentMode.setTag2nd("");
txtHIsDefault.setTag2nd("");
```

`txtHIsDefault.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHIsDefault(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHIsDefault).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHIsDefault.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsDefault).show();
```

`txtHIsDefault.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHIsDefault.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHIsDefault).show();
```
- Alert: "Is Default is mandatory."
- Alert: "Is Default value already selected."

### Screen `ReportConfigGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site | DmTextHelp | `txtHSite` | `KeyField_PrpAcs, 7, false` |
| 2 | Report Name | DmTextBox | `txtReportName` | `100, true` |
| 3 | Procedure Name | DmTextBox | `txtProcedureName` | `100, false` |
| 4 | Page Size | DmTextHelp | `txtHPageSize` | `KeyField_PrpAcs, 7, true, false` |
| 5 | Email Required | DmTextHelp | `txtHEmailRequired` | `KeyField_PrpAcs, 3, true, true` |

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w1, name]
- `txtHPageSize`: Name [Text, w1, name]
- `txtHEmailRequired`: Name [Text, w0, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHSite` → `getTxtHSite`
- `txtHPageSize` → `getTxtHPageSize`
- `txtHEmailRequired` → `getTxtHYesNO`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSite.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHSite).show();
```

`txtHSite.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHSite).show();
```

`txtHPageSize.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHPageSize(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFi
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPageSize).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPageSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHPageSize).show();
```

`txtHPageSize.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPageSize.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHPageSize).show();
```

`txtHEmailRequired.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHYesNO(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEmailRequired).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHEmailRequired.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHEmailRequired).show();
```

`txtHEmailRequired.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEmailRequired.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHEmailRequired).show();
```
- Alert: "Site is duplicate."

### Screen `VoucherType`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Voucher Type | DmTextBoxNumeric | `txtVoucherType` | `DmTextBoxNumeric.DecimalNature.ROUNDOFF, false, 3, false` |
| 2 | Short Name | DmSuggestBox | `txtShortName` | `5, 10, true, "Short_Name", DmDataType.STRING` |
| 3 | Description | DmSuggestBox | `txtDescription` | `50, 10, true, "Description", DmDataType.STRING` |
| 4 | Category Nature | DmTextHelp | `txtHNCat` | `KeyField_PrpAcs, 10, true` |
| 5 | Category | DmTextHelp | `txtHCategory` | `DmtxtHCategory_PrpAcs, 10, true` |
| 6 | Serial No. / Priority | DmTextBoxNumeric | `txtSerialNo` | `0, false, 3, true` |
| 7 | Parent Voucher | DmTextHelp | `txtHParentVoucher` | `KeyField_PrpAcs, 10` |
| 8 | Menu Text | DmTextHelp | `txtHMenuText` | `DmtxtHmenuText_PrpAcs, 10` |
| 9 | Menu Name | DmTextBox | `txtMenuName` | `100, false` |
| 10 | Menu Used In Module | DmTextBox | `txtMenuUsedInModule` | `100, false` |
| 11 | Accept (Adding) Entry After | DmTextBox | `txtAcceptEntryAfter` | `11, false` |
| 12 | Default Report | DmTextHelp | `txtHDefaultReport` | `KeyField_PrpAcs, 3, true` |
| 13 | Paper Size (Name) | DmTextBox | `txtPaperSize` | `30, false` |
| 14 | Landscape | DmTextHelp | `txtHlandescape` | `KeyField_PrpAcs, 3, true, false` |
| 15 | Default A/c | DmTextHelp | `txtHDefaultAC` | `DmtxtHDefaultAc_PrpAcs, 10` |
| 16 | Balancing A/c | DmTextHelp | `txtHBalancingAC` | `DmtxtHBalancingAc_PrpAcs, 10` |
| 17 | Default Document Type | DmTextHelp | `txtHDocumentType` | `KeyField_PrpAcs, 7, true, false` |
| 18 | Default Doc. Nature | DmTextHelp | `txtHDocumentNature` | `KeyField_PrpAcs, 7, true, false` |
| 19 | Default Bank A/C | DmTextHelp | `txtHDefaultBankAC` | `TxtHDefaultBankAC_PrpAcs, 8` |
| 20 | Bank Cash Book | DmTextHelp | `txtHBankCashBook` | `KeyField_PrpAcs, 3, true, true` |
| 21 | E-invoice Supply Type | DmTextHelp | `txtHEinvoiceSupplyType` | `KeyField_PrpAcs, 5, false` |
| 22 | Active | DmTextHelp | `txtHActiveYN` | `KeyField_PrpAcs, 3, true, true` |

Other widgets (not in tab list): `txtHGUITemplate` (DmTextHelp), `dmFind` (DmFind), `gridFormApplied` (DmGrid), `gridACFilteration` (DmGrid), `gridWebJobs` (DmGrid), `gridReportConfig` (DmGrid), `gridPaymentMode` (DmGrid), `gridItemCat` (DmGrid), `gridDocumentType` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `txtHCategory`: Name [Text, w150, Name]; Parent Voucher [Text, w150, parentVoucher]
- `txtHNCat`: Name [Text, w0, Name]
- `txtHGUITemplate`: Name [Text, w50, Name]
- `txtHParentVoucher`: Name [Text, w0, Name]
- `txtHDefaultReport`: Name [Text, w0, Name]
- `txtHlandescape`: Name [Text, w0, Name]
- `txtHActiveYN`: Name [Text, w0, Name]
- `txtHMenuText`: Menu Text [Text, w200, MnuText]; Menu Name [Text, w200, MnuName]; Module Name [Text, w200, MnuModule]
- `txtHDefaultAC`: Name [Text, w200, Name]; Manual Code [Text, w100, ManualCode]
- `txtHBalancingAC`: Name [Text, w200, Name]; Manual Code [Text, w100, ManualCode]
- `txtHBankCashBook`: Name [Text, w0, Name]
- `dmFind`: Description [Text, w1, Description]; Short Name [Text, w80, short_Name]; Category Nature [Text, w100, ncat]; Category [Text, w100, Category]; Module [Text, w100, ModuleName]; vType [Text, w50, VoucherType]
- `txtHDocumentType`: Name [Text, w50, name]
- `txtHDocumentNature`: Name [Text, w50, name]
- `txtHEinvoiceSupplyType`: Supply Type [Text, w0, name]
- `txtHDefaultBankAC`: Account Name [Text, w170, acHolderName]; Account No. [Text, w130, accountNo]; Bank [Text, w100, bankName]
- `gridFormApplied`: Name [Text, w200]; Location [Text, w160]; Applied For [Text, w210]
- `gridACFilteration`: Nature [Text, w485]
- `gridWebJobs`: Template Name [Text, w240]; Template Type [Text, w250]
- `gridReportConfig`: Site [Text, w220]; Report Name [Text, w240]; Procedure Name [Text, w240]
- `gridPaymentMode`: Payment Mode  [Text, w220]; Short Name [Text, w230]; Default [Text, w60]
- `gridItemCat`: Item Category [Text, w485]
- `gridDocumentType`: Document Type [Text, w700]

**Lookup / suggestion / find sources (server RPC called):**
- `txtShortName` → `getSuggestionShortName`
- `txtDescription` → `getSuggestionDescription`
- `txtHActiveYN` → `getTxtHYesNO`
- `txtHCategory` → `getTxtHCategory`
- `txtHDefaultAC` → `getTxtHDefaultAC`
- `txtHBalancingAC` → `getTxtHDefaultAC`
- `txtHDefaultReport` → `getTxtHDefaultReport`
- `txtHMenuText` → `getTxtHMenuText`
- `txtHNCat` → `getTxtHNCat`
- `txtHlandescape` → `getTxtHYesNO`
- `txtHParentVoucher` → `getTxtHParentVoucher`
- `txtHGUITemplate` → `getTxtHGUITemplate`
- `txtHDocumentType` → `getTxtHDocumentType`
- `txtHDocumentNature` → `getTxtHDocumentNature`
- `txtHBankCashBook` → `getTxtHYesNO`
- `txtHEinvoiceSupplyType` → `getHEinvSupplyType`
- `txtHDefaultBankAC` → `getTxtHDefaultBankAC`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `SharedKeyFieldName` ← DmShared.xNull(txtVoucherType.getText())
- `Description` ← DmShared.xNull(txtDescription.getText())
- `ShortName` ← DmShared.xNull(txtShortName.getText())
- `NcatCode` ← DmShared.xNull(txtHNCat.getTag())
- `CategoryCode` ← DmShared.xNull(txtHCategory.getTag())
- `MenutextCode` ← DmShared.xNull(txtHMenuText.getTag())
- `MenutextName` ← DmShared.xNull(txtHMenuText.getText())
- `Menuname` ← DmShared.xNull(txtMenuName.getText())
- `MenuUsedInModule` ← DmShared.xNull(txtMenuUsedInModule.getText())
- `ActiveCode` ← DmShared.xNull(txtHActiveYN.getTag())
- `ActiveName` ← DmShared.xNull(txtHActiveYN.getText())
- `DefaultReportCode` ← DmShared.xNull(txtHDefaultReport.getTag())
- `DefaultReportName` ← DmShared.xNull(txtHDefaultReport.getText())
- `PaperSize` ← DmShared.xNull(txtPaperSize.getText())
- `LandscapeCode` ← DmShared.xNull(txtHlandescape.getTag())
- `LandscapeName` ← DmShared.xNull(txtHlandescape.getText())
- `DefaultAcCode` ← DmShared.xNull(txtHDefaultAC.getTag())
- `DefaultAcName` ← DmShared.xNull(txtHDefaultAC.getText())
- `BalancingAcCode` ← DmShared.xNull(txtHBalancingAC.getTag())
- `BalancingAcName` ← DmShared.xNull(txtHBalancingAC.getText())
- `EinvSupplyTypeCode` ← DmShared.xNull(txtHEinvoiceSupplyType.getTag())
- `EinvSupplyTypeName` ← DmShared.xNull(txtHEinvoiceSupplyType.getText())
- `SerialNo` ← DmClient.vNull(txtSerialNo.getText(), "0")
- `ParentVoucherCode` ← DmShared.xNull(txtHParentVoucher.getText())
- `ParentVoucherName` ← DmShared.xNull(txtHParentVoucher.getText())
- `AcceptEntryAfter` ← DmShared.xNull(txtAcceptEntryAfter.getText())
- `GuiTemplateCode` ← DmShared.xNull(txtHGUITemplate.getTag())
- `GuiTemplateName` ← DmShared.xNull(txtHGUITemplate.getText())
- `IsBankCashBookCode` ← DmShared.xNull(txtHBankCashBook.getTag())
- `DocumentTypeCode` ← DmShared.xNull(txtHDocumentType.getTag())
- `DocumentTypeName` ← DmShared.xNull(txtHDocumentType.getText())
- `DocumentNatureCode` ← DmShared.xNull(txtHDocumentNature.getTag())
- `DocumentNatureName` ← DmShared.xNull(txtHDocumentNature.getText())
- `BankAcCode` ← DmShared.xNull(txtHDefaultBankAC.getTag())
- `BankAcName` ← DmShared.xNull(txtHDefaultBankAC.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtVoucherType.setText` ← DmClient.vNull(data.getSharedKeyFieldName(), "0")
- `userControl.setTextBlog` ← preparedByBlog, DmShared.xNull(data.getSharedPreparedBy())
- `userControl.setTextBlog` ← modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy())
- `userControl.setTextBlog` ← sysGroupByBlog, DmShared.xNull(data.getSysGroup())
- `txtDescription.setText` ← DmShared.xNull(data.getDescription())
- `txtShortName.setText` ← DmShared.xNull(data.getShortName())
- `txtHNCat.setTag` ← DmShared.xNull(data.getNcatCode())
- `txtHNCat.setText` ← DmShared.xNull(data.getNcatCode())
- `txtHCategory.setTag` ← DmShared.xNull(data.getCategoryCode())
- `txtHCategory.setTag2nd` ← DmShared.xNull(data.getCategoryCode())
- `txtHCategory.setText` ← DmShared.xNull(data.getCategoryCode())
- `txtHMenuText.setTag` ← DmShared.xNull(data.getMenutextCode())
- `txtHMenuText.setText` ← DmShared.xNull(data.getMenutextName())
- `txtMenuName.setText` ← DmShared.xNull(data.getMenuname())
- `txtMenuUsedInModule.setText` ← DmShared.xNull(data.getMenuUsedInModule())
- `txtHActiveYN.setTag` ← DmShared.xNull(data.getActiveCode())
- `txtHActiveYN.setText` ← DmShared.xNull(data.getActiveName())
- `txtHDefaultReport.setTag` ← DmShared.xNull(data.getDefaultReportCode())
- `txtHDefaultReport.setText` ← DmShared.xNull(data.getDefaultReportName())
- `txtPaperSize.setText` ← DmShared.xNull(data.getPaperSize())
- `txtHlandescape.setTag` ← DmShared.xNull(data.getLandscapeCode())
- `txtHlandescape.setText` ← DmShared.xNull(data.getLandscapeName())
- `txtHDefaultAC.setTag` ← DmShared.xNull(data.getDefaultAcCode())
- `txtHDefaultAC.setText` ← DmShared.xNull(data.getDefaultAcName())
- `txtHBalancingAC.setTag` ← DmShared.xNull(data.getBalancingAcCode())
- `txtHBalancingAC.setText` ← DmShared.xNull(data.getBalancingAcName())
- `txtHGUITemplate.setTag` ← DmShared.xNull(data.getGuiTemplateCode())
- `txtHGUITemplate.setText` ← DmShared.xNull(data.getGuiTemplateName())
- `txtSerialNo.setText` ← DmClient.vNull(data.getSerialNo(), "0")
- `txtHParentVoucher.setTag` ← DmShared.xNull(data.getParentVoucherName())
- `txtHParentVoucher.setText` ← DmShared.xNull(data.getParentVoucherName())
- `txtAcceptEntryAfter.setText` ← DmShared.xNull(data.getAcceptEntryAfter())
- `txtHBankCashBook.setTag` ← DmShared.xNull(data.getIsBankCashBookCode())
- `txtHBankCashBook.setText` ← DmShared.xNull(data.getIsBankCashBookName())
- `txtHDocumentNature.setText` ← DmShared.xNull(data.getDocumentNatureName())
- `txtHDocumentNature.setTag` ← DmShared.xNull(data.getDocumentNatureCode())
- `txtHDocumentType.setTag` ← DmShared.xNull(data.getDocumentTypeCode())
- `txtHDocumentType.setText` ← DmShared.xNull(data.getDocumentTypeName())
- `txtHEinvoiceSupplyType.setTag` ← DmShared.xNull(data.getEinvSupplyTypeCode())
- `txtHEinvoiceSupplyType.setText` ← DmShared.xNull(data.getEinvSupplyTypeName())
- `txtHDefaultBankAC.setTag` ← DmShared.xNull(data.getBankAcCode())
- `txtHDefaultBankAC.setText` ← DmShared.xNull(data.getBankAcName())
- userControl.setTextBlog(preparedByBlog, DmShared.xNull(data.getSharedPreparedBy()))
- userControl.setTextBlog(modifiedByBlog, DmShared.xNull(data.getSharedModifiedBy()))
- userControl.setTextBlog(sysGroupByBlog, DmShared.xNull(data.getSysGroup()))

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHParentVoucher.setEnabled(false)`
- `txtVoucherType.setEnabled(false)`
- `txtMenuName.setEnabled(false)`
- `txtMenuUsedInModule.setEnabled(false)`
- `txtHNCat.setEnabled(false)`
- `txtHCategory.setEnabled(false)`

**On New click:**
- `clear()`
- `txtShortName.setFocus(true)`

**`manageDisplay()` logic (condensed code):**
```java
txtHParentVoucher.setEnabled(false);
txtVoucherType.setEnabled(false);
if (topControl.getMode().equalsIgnoreCase("Edit")) {
txtMenuName.setEnabled(false);
txtMenuUsedInModule.setEnabled(false);
txtHNCat.setEnabled(false);
txtHCategory.setEnabled(false);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
clear();
manageDisplay(true);
txtShortName.setFocus(true);
```

`topControl.addEditHandeller` → `editClicked()`
```java
manageDisplay(true);
gridACFilteration.getGrid().getStore().add(new GrdACFilteration_Mdl());
gridItemCat.getGrid().getStore().add(new KeyFieldExtended());
gridFormApplied.getGrid().getStore().add(new GrdFormApplied_Mdl());
gridWebJobs.getGrid().getStore().add(new GrdWebJobs_Mdl());
gridPaymentMode.getGrid().getStore().add(new GrdReceiptType_Mdl());
gridReportConfig.getGrid().getStore().add(new GrdReportConfig_Mdl());
txtShortName.setFocus(true);
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
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(VoucherType.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
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
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtShortName).show();
```

`txtShortName.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtShortName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtShortName).show();
```

`txtDescription.addCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getSuggestionDescription(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<L
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtDescription).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtDescription.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtDescription).show();
```

`txtDescription.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtDescription.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtDescription).show();
```

`txtHActiveYN.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHYesNO(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHActiveYN).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActiveYN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHActiveYN).show();
```

`txtHActiveYN.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHActiveYN.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHActiveYN).show();
```

`txtHCategory.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHCategory(helper, blnToEndOfFile, DmShared.xNull(txtHNCat.getTag()), new Async
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHCategory).show();
```

`txtHCategory.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHCategory).show();
```

`txtHCategory.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHCategory.getTag()).equalsIgnoreCase(DmShared.xNull(txtHCategory.getTag2nd()))) {
txtHParentVoucher.setTag("");
txtHParentVoucher.setTag2nd("");
txtHParentVoucher.setText("");
txtSerialNo.setTag("");
txtSerialNo.setTag2nd("");
txtSerialNo.setText("");
txtHCategory.setTag2nd(DmShared.xNull(txtHCategory.getTag()));
if (blnStatus && tRow != null) {
txtHParentVoucher.setTag(DmShared.xNull(tRow.getSharedPreparedBy()));
...
```

`txtHDefaultAC.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDefaultAC(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDefaultAC).show();
public void onSuccess(DmResultPageing<List<TxtHDefaultAC_Mdl>> result) {
if (result.isStatus()) {
txtHDefaultAC.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHDefaultAC).show();
```

`txtHDefaultAC.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDefaultAC.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHDefaultAC).show();
```

`txtHBalancingAC.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDefaultAC(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtH
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBalancingAC).show();
public void onSuccess(DmResultPageing<List<TxtHDefaultAC_Mdl>> result) {
if (result.isStatus()) {
txtHBalancingAC.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHBalancingAC).show();
```

`txtHBalancingAC.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBalancingAC.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHBalancingAC).show();
```

`txtHDefaultReport.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHDefaultReport(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHDefaultReport).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHDefaultReport.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHDefaultReport).show();
```

`txtHDefaultReport.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHDefaultReport.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHDefaultReport).show();
```

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
txtMenuName.setText(DmShared.xNull(tRow.getMenuName()));
txtMenuUsedInModule.setText(DmShared.xNull(tRow.getMenuModuleName()));
```

`txtHNCat.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHNCat(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNCat).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNCat.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNCat).show();
```

`txtHNCat.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNCat.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHNCat).show();
```

**Top control options:** setRefreshRequired(false), setDisplay(true)

_Print permission "P" is removed for this screen (replace "P" with "*")._
- Confirm: "Are you sure? You want to delete this record."

### Screen `WebJobsGridEdit`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Template Name | DmTextHelp | `txtHTemplateName` | `Grid_TemplateName, 7, true` |
| 2 | Template Type | DmTextBox | `txtTemplateType` | `50, false` |

**Grid / popup columns** (header, width, db field):
- `txtHTemplateName`: Template Name [Text, w1, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHTemplateName` → `getTxtHTemplateName`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHTemplateName.addTextHelpCallBack` → `populateData()`
```java
SrvAsync.invokeRPC(blnCallBackWait).getTxtHTemplateName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<T
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHTemplateName).show();
public void onSuccess(DmResultPageing<List<TxtHTemplate_Mdl>> result) {
if (result.isStatus()) {
txtHTemplateName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHTemplateName).show();
```

`txtHTemplateName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHTemplateName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(DmShared.xNull(result.getErrorMessage()), txtHTemplateName).show();
```

`txtHTemplateName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus && tRow != null) {
txtTemplateType.setTag(tRow.getTemplateTypeCode());
txtTemplateType.setText(tRow.getWebJobType().getName());
} else {
txtTemplateType.setTag("");
txtTemplateType.setText("");
```

## Files
| file | lines |
|---|---|
| client/DocumentTypeGridEdit.java | 133 |
| client/DocumentTypeGridEdit_Dsg.java | 62 |
| client/ExportData.java | 225 |
| client/ExportData_Dsg.java | 131 |
| client/FilterationGridEdit.java | 136 |
| client/FilterationGridEdit_Dsg.java | 62 |
| client/FormAppliedGridEdit.java | 147 |
| client/FormAppliedGridEdit_Dsg.java | 85 |
| client/ItemCategoryGridEdit.java | 133 |
| client/ItemCategoryGridEdit_Dsg.java | 62 |
| client/ReceiptTypeGridEdit.java | 195 |
| client/ReceiptTypeGridEdit_Dsg.java | 97 |
| client/ReportConfigGridEdit.java | 207 |
| client/ReportConfigGridEdit_Dsg.java | 151 |
| client/VoucherType.java | 1345 |
| client/VoucherType_Dsg.java | 958 |
| client/WebJobsGridEdit.java | 148 |
| client/WebJobsGridEdit_Dsg.java | 88 |
| server/VoucherType_SrvImpl.java | 1902 |
| shared/BankDetail_Mdl.java | 33 |
| shared/Find_Mdl.java | 69 |
| shared/GrdACFilteration_Mdl.java | 32 |
| shared/GrdFormApplied_Mdl.java | 33 |
| shared/GrdReceiptType_Mdl.java | 49 |
| shared/GrdReportConfig_Mdl.java | 59 |
| shared/GrdVoucherType_Mdl.java | 40 |
| shared/GrdWebJobs_Mdl.java | 33 |
| shared/TxtHDefaultAC_Mdl.java | 22 |
| shared/TxtHFormApplied_Mdl.java | 33 |
| shared/TxtHGUITemplate_Mdl.java | 31 |
| shared/TxtHMenuText_Mdl.java | 42 |
| shared/TxtHPaymentMode_Mdl.java | 30 |
| shared/TxtHTemplate_Mdl.java | 34 |
| shared/VoucherType_Mdl.java | 399 |
| shared/VoucherType_Srv.java | 69 |
| shared/VoucherType_SrvAsync.java | 70 |
| shared/VoucherType_Vld.java | 22 |

## UI labels / columns (client)
`Nature`, `Payment Mode`, `Is Default`, `Site`, `Report Name`, `Procedure Name`, `Page Size`, `Email Required`, `Voucher Type`, `Short Name`, `Description`, `Category Nature`, `Category`, `Serial No. / Priority`, `Parent Voucher`, `Menu Text`, `Menu Name`, `Menu Used In Module`, `Accept (Adding) Entry After`, `Default Report`, `Paper Size (Name)`, `Landscape`, `Default A/c`, `Balancing A/c`, `Default Document Type`, `Default Doc. Nature`, `Default Bank A/C`, `Bank Cash Book`, `E-invoice Supply Type`, `Active`, `Template Name`, `Template Type`, `Name`, `Document Type`, `Item Category`

## Models (shared DTOs)
### TxtHDefaultAC_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | menuModule |

### TxtHFormApplied_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | location |
| String | appliedFor |

### TxtHMenuText_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | menuText |
| String | menuName |
| String | menuModuleName |

### GrdACFilteration_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | nature |
| String | natureCode |

### TxtHTemplate_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | templateTypeCode |
| DmWebJobTypes | webJobType |

### VoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | description |
| String | ncatCode |
| String | categoryCode |
| String | serialNo |
| String | parentVoucherCode |
| String | parentVoucherName |
| String | menutextCode |
| String | menutextName |
| String | menuname |
| String | menuUsedInModule |
| String | acceptEntryAfter |
| String | defaultReportCode |
| String | defaultReportName |
| String | paperSize |
| String | landscapeCode |
| String | landscapeName |
| String | defaultAcCode |
| String | defaultAcName |
| String | balancingAcCode |
| String | balancingAcName |
| String | activeCode |
| String | activeName |
| String | formApplied |
| String | sysGroup |
| String | guiTemplateCode |
| String | guiTemplateName |
| String | isBankCashBookCode |
| String | isBankCashBookName |
| String | documentTypeCode |
| String | documentTypeName |
| String | documentNatureCode |
| String | documentNatureName |
| String | einvSupplyTypeCode |
| String | einvSupplyTypeName |
| String | bankAcCode |
| String | bankAcName |
| List<GrdACFilteration_Mdl> | grdFilteration |
| List<GrdFormApplied_Mdl> | grdAppliedForm |
| List<GrdWebJobs_Mdl> | grdWebJobs |
| List<GrdReceiptType_Mdl> | grdReceiptType |
| List<GrdReportConfig_Mdl> | grdReportConfig |
| List<KeyFieldExtended> | grdItemCategory |
| List<KeyFieldExtended> | grdDocType |

### TxtHGUITemplate_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String appliedSiteText, | appliedSiteCode |

### GrdFormApplied_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | location |
| String | appliedFor |

### GrdVoucherType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String shortName, | category |
| boolean | checked |

### GrdReportConfig_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | reportName |
| String | procedureName |
| String | pageSize |
| String | emailRequiredCode |
| String | emailRequiredName |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | type |
| String | shortName |
| String | Description |
| String | ncat |
| String | Module |
| String | Category |

### GrdWebJobs_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | templateTypeCode |
| String | templateTypeName |

### GrdReceiptType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortName |
| String | cardType |
| String | isDefaultCode |
| String | isDefaultName |

### TxtHPaymentMode_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String shortName, | cardType |

### BankDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountNo |
| String | bankName |

## RPC interface
| method | params | returns |
|---|---|---|
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getSuggestionShortName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getSuggestionDescription | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getMoveRec | String strKey | DmResult<VoucherType_Mdl> |
| saveRecord | VoucherType_Mdl mrTemp | DmResult<String> |
| deleteRecord | String strKey | DmResult<String> |
| getTxtHNCat | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCategory | DmHelper pageing, boolean blnToEndOfFile, String NCat | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParentVoucher | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMenuText | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHMenuText_Mdl>> |
| getTxtHDefaultReport | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDefaultAC | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHDefaultAC_Mdl>> |
| getTxtHYesNO | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getTxtHFormApplied | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHFormApplied_Mdl>> |
| getTxtHGUITemplate | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTemplateName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTemplate_Mdl>> |
| getTxtHPaymentMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPaymentMode_Mdl>> |
| getTxtHIsDefault | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| exportJson | String sessionId, List<String> lstVoucherType | DmResult<KeyFieldExtended> |
| getTxtHDocumentType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDocumentNature | String gstDocNatureShortName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHSite | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getVoucherType |  | DmResult<List<GrdVoucherType_Mdl>> |
| getTxtHPageSize | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEinvSupplyType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDefaultBankAC | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<BankDetail_Mdl>> |

## Validator `VoucherType_Vld`
```java
public class VoucherType_Vld extends KeyField implements Serializable, IsSerializable {
    public static void chkSave(VoucherType_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getShortName(), "Short Name", 200);
        DmShared.isValidText(mrTemp.getDescription(), "Description", 300);
        DmShared.isValidText(mrTemp.getNcatCode(), "Category Nature", 400);
        DmShared.isValidText(mrTemp.getCategoryCode(), "Category", 500);
        DmShared.isValidText(mrTemp.getDefaultReportCode(), "Default Report", 600);
        DmShared.isValidText(mrTemp.getLandscapeCode(), "Landscape", 700);
        DmShared.isValidText(mrTemp.getActiveCode(), "Active", 900);
        DmShared.isValidText(mrTemp.getIsBankCashBookCode(), "Bank Cash Book", 1000);
    }
}
```

## Server `VoucherType_SrvImpl.java` (1902 lines)
Helpers used: `DmServer.convertDate`, `DmServer.evaluateHelper`, `DmServer.executeScalar`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.vNull`, `DmShared.xNull`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `Select V_Type As SearchCode,isSystemDefine From Voucher_Type VT Order By VT.V_Type`

### `getSuggestionShortName`
`public DmResultPageing<List<String>> getSuggestionShortName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Short_Name From Voucher_Type VT`

### `getSuggestionDescription`
`public DmResultPageing<List<String>> getSuggestionDescription(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select Description From Voucher_Type VT`

### `getMoveRec`
`public DmResult<VoucherType_Mdl> getMoveRec(String strKey)`


### `getVocherType`
`private VoucherType_Mdl getVocherType(String code, Connection cnTemp)`

- SQL 1: `select vt.v_Type as searchCode, vt.description, vt.short_Name, vt.nCat, vt.category, vt.preparedBy, vt.defaultReport, vt.paperSizeName, vt.serialNo, vt.isActive, vt.isSystemDefine, vt.mnuName, vt.mnuText, vt.mnuAttachedInModule, vt.acceptEntryAfter, vt.isLandscape, vt.defaultAc,vt.balancingAc, sg.name as defaultAccount, sgb.name as balancingAcName, vt.parentVoucher, vt.u_name, vt.guiTemplateCode, gtm.Name as guiTemplateName, vt.attachWJTemplates, vt.documentTypeCSV, vt.attachPaymentMode, vt.isBankCashBook, vt.documentTypeCode, vt.documentNatureCode, vt.einvSupplyTypeCode,vt.bankAcCode, bd.acHolderName from voucher_Type vt left join subGroup sg on sg.subCode = vt.defaultAc left join subGroup sgb on sgb.subCode = vt.balancingAc left join guiTemplateMast gtm on vt.guiTemplateCode = gtm.code LEFT JOIN bankDetail bd ON CONCAT(bd.subCode, '#', bd.sno) = vt.bankAcCode where vt.v_Type= :V_Type`
- SQL 2: `SELECT acf.nature, sgn.name as natureName FROM acFilteration acf LEFT JOIN subGroupNature sgn on sgn.Code = acf.nature WHERE acf.v_Type = :V_Type`
- SQL 3: `SELECT icf.itemCategoryCode, ic.name as itemCategoryName FROM itemCatFilteration icf LEFT JOIN itemCat ic ON ic.code = icf.itemCategoryCode WHERE icf.v_Type = :V_Type`
- SQL 4: `SELECT TFM.code, TFM.name, TFM.TrnType, TFM.L_C FROM vouchertypeformlist AS vTFL LEFT JOIN taxformMast AS TFM ON vTFL.taxCode=TFM.code WHERE vTFL.v_type= :V_Type order by TFM.name`
- SQL 5: `select code, name, templateType from wjTemplateMast where code in ( {…} :code {…} ) order by name`
- SQL 6: `SELECT code, Name, shortName, cardType FROM cardTypeMast where code in ( {…} :code {…} ) order by Name`
- SQL 7: `SELECT swr.reportName, swr.procedureName, swr.site_Code , swr.pageSize, sm.name as siteName , swr.isEmailRequired FROM siteWiseReportConfig AS swr LEFT JOIN siteMast AS sm ON swr.site_code=sm.code WHERE swr.v_type = :V_Type ORDER BY sm.name`
- Params: `:V_Type` ← `code`; `:V_Type` ← `code`; `:V_Type` ← `code`; `:V_Type` ← `code`; `:V_Type` ← `code`
- Other calls: `DmWebJobTypes.getViaCode`, `Einv_SupplyTypeCode.getValue`, `GstDocumentNature.getViaShortName`, `GstDocumentType.getViaShortName`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey)`

- SQL 1: `Select isNull(isSystemDefine,'0') as Cnt From Voucher_Type Where V_Type= :V_Type`
- SQL 2: `Select Count(DocId) As Cnt From Ledger Where V_Type= :V_Type`
- SQL 3: `Delete From siteWiseReportConfig Where V_Type = :V_Type`
- SQL 4: `Delete From AcFilteration Where V_Type = :V_Type`
- SQL 5: `Delete From itemCatFilteration Where V_Type = :V_Type`
- SQL 6: `Delete From vouchertypeformlist Where V_Type = :V_Type`
- SQL 7: `Delete From Voucher_Type Where V_Type = :V_Type`
- Params: `:V_Type` ← `strKey`; `:V_Type` ← `strKey`; `:V_Type` ← `strKey`; `:V_Type` ← `strKey`; `:V_Type` ← `strKey`; `:V_Type` ← `strKey`; `:V_Type` ← `strKey`
- Errors: "This is system define. You can not delete it." | "Record is in use. You can not delete this record."

### `saveRecord`
`public DmResult<String> saveRecord(VoucherType_Mdl mrTemp)`

- SQL 1: `Select isNull(max(v_type),0) + 1 As Cnt From Voucher_Type VT {…} 0`
- SQL 2: `Select V_Type From Voucher_Type Where Description = :description And V_Type <> :V_Type`
- SQL 3: `Select V_Type From Voucher_Type Where Short_Name = :Short_Name And V_Type <> :V_Type`
- SQL 4: `Delete From AcFilteration Where v_type = :V_Type`
- SQL 5: `Delete From itemCatFilteration Where v_type = :V_Type`
- SQL 6: `Delete From vouchertypeformlist Where v_type = :V_Type`
- SQL 7: `Delete From siteWiseReportConfig Where v_type = :V_Type`
- SQL 8: `Insert Into Voucher_Type ( NCat,Category,V_Type,Description,Short_Name, isSystemDefine,PreparedBy,U_EntDt, MnuName,MnuText,MnuAttachedInModule,AcceptEntryAfter,DefaultReport, PaperSizeName,isLandscape,DefaultAc,balancingAc,SerialNo, ParentVoucher,isActive,guiTemplateCode, attachWJTemplates,attachPaymentMode, isBankCashBook,documentTypeCSV,documentTypeCode,documentNatureCode, einvSupplyTypeCode, bankAcCode )Values( :NCat,:Category,:V_Type,:Description,:Short_Name, :isSystemDefine,:PreparedBy,:U_EntDt, :MnuName,:MnuText,:MnuAttachedInModule,:AcceptEntryAfter,:DefaultReport, :PaperSizeName,:isLandscape,:DefaultAc,:balancingAc,:SerialNo, :ParentVoucher,:isActive,:guiTemplateCode, :attachWJTemplates,:attachPaymentMode, :isBankCashBook,:documentTypeCSV,:documentTypeCode,:documentNatureCode, :einvSupplyTypeCode, :bankAcCode )`
- SQL 9: `Update Voucher_Type Set NCat=:NCat, Category=:Category, Description=:Description, Short_Name=:Short_Name, MnuName=:MnuName,MnuText=:MnuText, MnuAttachedInModule=:MnuAttachedInModule, AcceptEntryAfter= :AcceptEntryAfter, DefaultReport=:DefaultReport, PaperSizeName=:PaperSizeName, isActive=:isActive, isLandscape=:isLandscape, SerialNo=:SerialNo, attachWJTemplates = :attachWJTemplates, ParentVoucher=:ParentVoucher, DefaultAc=:DefaultAc,balancingAc=:balancingAc, U_EntDt= :U_EntDt, u_name=:u_name, guiTemplateCode = :guiTemplateCode, attachPaymentMode=:attachPaymentMode, isBankCashBook=:isBankCashBook, documentTypeCSV=:documentTypeCSV, documentNatureCode=:documentNatureCode, documentTypeCode=:documentTypeCode, einvSupplyTypeCode=:einvSupplyTypeCode, bankAcCode = :bankAcCode Where V_Type= :V_Type`
- Params: `:description` ← `mrTemp.getDescription()`; `:V_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:Short_Name` ← `mrTemp.getShortName()`; `:V_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:V_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:V_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:V_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:V_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:NCat` ← `mrTemp.getNcatCode()`; `:Category` ← `mrTemp.getCategoryCode()`; `:V_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:Description` ← `mrTemp.getDescription()`; `:Short_Name` ← `mrTemp.getShortName()`; `:isSystemDefine` ← `mrTemp.getSysGroup()`; `:PreparedBy` ← `DmServer.getSsnUserName(this)`; `:MnuName` ← `mrTemp.getMenuname()`; `:MnuText` ← `mrTemp.getMenutextName()`; `:MnuAttachedInModule` ← `mrTemp.getMenuUsedInModule()`; `:AcceptEntryAfter` ← `DmServer.convertDate(mrTemp.getAcceptEntryAfter())`; `:DefaultReport` ← `mrTemp.getDefaultReportCode()`; `:PaperSizeName` ← `mrTemp.getPaperSize()`; `:isLandscape` ← `mrTemp.getLandscapeCode()`; `:DefaultAc` ← `DmShared.foriegnKey(mrTemp.getDefaultAcCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAcCode())`; `:SerialNo` ← `DmServer.vNull(mrTemp.getSerialNo(), "0")`; `:ParentVoucher` ← `mrTemp.getParentVoucherName()`; `:isActive` ← `mrTemp.getActiveCode()`; `:guiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getGuiTemplateCode())`; `:attachWJTemplates` ← `DmShared.xNull(templateCode)`; `:attachPaymentMode` ← `DmShared.xNull(paymentMode)`; `:isBankCashBook` ← `DmShared.xNull(mrTemp.getIsBankCashBookCode())`; `:U_EntDt` ← `DmServer.getServerTimestamp()`; `:documentTypeCSV` ← `DmShared.xNull(doctype)`; `:documentNatureCode` ← `DmShared.foriegnKey(mrTemp.getDocumentNatureCode())`; `:documentTypeCode` ← `DmShared.foriegnKey(mrTemp.getDocumentTypeCode())`; `:einvSupplyTypeCode` ← `DmShared.foriegnKey(mrTemp.getEinvSupplyTypeCode())`; `:bankAcCode` ← `DmShared.foriegnKey(mrTemp.getBankAcCode())`; `:NCat` ← `mrTemp.getNcatCode()`; `:Category` ← `mrTemp.getCategoryCode()`; `:Description` ← `mrTemp.getDescription()`; `:Short_Name` ← `mrTemp.getShortName()`; `:MnuName` ← `mrTemp.getMenuname()`; `:MnuText` ← `mrTemp.getMenutextName()`; `:MnuAttachedInModule` ← `mrTemp.getMenuUsedInModule()`; `:AcceptEntryAfter` ← `DmServer.convertDate(mrTemp.getAcceptEntryAfter())`; `:DefaultReport` ← `mrTemp.getDefaultReportCode()`; `:PaperSizeName` ← `mrTemp.getPaperSize()`; `:isActive` ← `mrTemp.getActiveCode()`; `:isLandscape` ← `mrTemp.getLandscapeCode()`; `:SerialNo` ← `DmServer.vNull(mrTemp.getSerialNo(), "0")`; `:ParentVoucher` ← `mrTemp.getParentVoucherName()`; `:DefaultAc` ← `DmShared.foriegnKey(mrTemp.getDefaultAcCode())`; `:balancingAc` ← `DmShared.foriegnKey(mrTemp.getBalancingAcCode())`; `:V_Type` ← `mrTemp.getSharedKeyFieldCode()`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:guiTemplateCode` ← `DmShared.foriegnKey(mrTemp.getGuiTemplateCode())`; `:attachWJTemplates` ← `DmShared.xNull(templateCode)`; `:attachPaymentMode` ← `DmShared.xNull(paymentMode)`; `:isBankCashBook` ← `DmShared.xNull(mrTemp.getIsBankCashBookCode())`; `:U_EntDt` ← `DmServer.getServerTimestamp()`; `:documentTypeCSV` ← `DmShared.xNull(doctype)`; `:documentNatureCode` ← `DmShared.foriegnKey(mrTemp.getDocumentNatureCode())`; `:documentTypeCode` ← `DmShared.foriegnKey(mrTemp.getDocumentTypeCode())`; `:einvSupplyTypeCode` ← `DmShared.foriegnKey(mrTemp.getEinvSupplyTypeCode())`; `:bankAcCode` ← `DmShared.foriegnKey(mrTemp.getBankAcCode())`; `:V_type` ← `mrTemp.getSharedKeyFieldCode()`; `:Nature` ← `mrTemp.getGrdFilteration().get(i).getNatureCode()`; `:U_Name` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `DmServer.getServerTimestamp()`; `:V_type` ← `mrTemp.getSharedKeyFieldCode()`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getGrdItemCategory().get(i).getSharedKeyFieldCode())`; `:U_Name` ← `DmServer.getSsnUserName(this)`; `:U_EntDt` ← `DmServer.getServerTimestamp()`; `:V_type` ← `mrTemp.getSharedKeyFieldCode()`; `:taxCode` ← `mrTemp.getGrdAppliedForm().get(i).getSharedKeyFieldCode()`; `:v_type` ← `mrTemp.getSharedKeyFieldCode()`; `:site_Code` ← `DmShared.foriegnKey(config_Mdl.getSharedKeyFieldCode())`; `:reportName` ← `DmShared.xNull(config_Mdl.getReportName())`; `:procedureName` ← `DmShared.xNull(config_Mdl.getProcedureName())`; `:pageSize` ← `DmShared.xNull(config_Mdl.getPageSize())`; `:isEmailRequired` ← `DmShared.iNull(config_Mdl.getEmailRequiredCode()).intValue()`
- Errors: "Voucher Type is mis-matching!" | "Invalid key." | "Description already exists." | "Short Name already exists." | "One payment mode must be default."
- Other calls: `CardType.getValueOf`, `VoucherType_Vld.chkSave`

### `getTxtHNCat`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNCat(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select NCat As Code,NCat as Name From Voucher_Type VT Group By NCat`

### `getTxtHCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCategory(DmHelper helper, boolean blnToEndOfFile, String NCat)`

- SQL 1: `Select vt.Category As Code, vt.Category As Name , MAX(vt.parentVoucher) AS parentVoucher, MAX(vt.serialNo) AS serialNo From Voucher_Type vt Where NCat= :NCat Group By Category`
- Params: `:NCat` ← `NCat`

### `getTxtHParentVoucher`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHParentVoucher(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHMenuText`
`public DmResultPageing<List<TxtHMenuText_Mdl>> getTxtHMenuText(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select MnuText + 'þ' + MnuName + 'þ' + MnuModule As Code,MnuText,MnuName,MnuModule From User_Permission_Web UP Where UserName='SA'`

### `getTxtHDefaultReport`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDefaultReport(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHDefaultAC`
`public DmResultPageing<List<TxtHDefaultAC_Mdl>> getTxtHDefaultAC(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT SG.SubCode, SG.NAME, SG.ManualCode FROM SubGroup SG LEFT JOIN subgroupsitelist SGL ON SG.subCode = SGL.subCode WHERE SGL.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getTxtHYesNO`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHYesNO(DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code,name FROM subgroupNature`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select V_Type As Code,V_Type As VoucherType,Description,NCat,MnuAttachedInModule AS ModuleName, Category,short_Name From Voucher_Type`

### `getTxtHFormApplied`
`public DmResultPageing<List<TxtHFormApplied_Mdl>> getTxtHFormApplied(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code,name,L_C,TrnType FROM taxformMast`

### `getTxtHGUITemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGUITemplate(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT Code,Name FROM guiTemplateMast`

### `getTxtHTemplateName`
`public DmResultPageing<List<TxtHTemplate_Mdl>> getTxtHTemplateName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code, name, templateType from wjTemplateMast`
- Other calls: `DmWebJobTypes.getViaCode`

### `getTxtHPaymentMode`
`public DmResultPageing<List<TxtHPaymentMode_Mdl>> getTxtHPaymentMode(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name, shortName, cardType FROM cardTypeMast`

### `getTxtHIsDefault`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHIsDefault(DmHelper pageing, boolean blnToEndOfFile)`


### `exportJson`
`public DmResult<KeyFieldExtended> exportJson(String session, List<String> lstVoucherType)`

- Errors: "Invalid request." | "File Name not given."

### `getTxtHDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GstDocumentType.values`

### `getTxtHDocumentNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentNature(String gstDocNatureShortName, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GstDocumentNature.values`

### `getVoucherType`
`public DmResult<List<GrdVoucherType_Mdl>> getVoucherType()`

- SQL 1: `select v_Type, description, category, short_Name from voucher_Type order by description, short_Name, category`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM SiteMast`

### `getTxtHPageSize`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPageSize(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `DmPageSizes.values`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select imc.code, imc.name from itemcat imc`

### `getHEinvSupplyType`
`public DmResultPageing<List<KeyFieldExtended>> getHEinvSupplyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Einv_SupplyTypeCode.values`

### `getTxtHDefaultBankAC`
`public DmResultPageing<List<BankDetail_Mdl>> getTxtHDefaultBankAC(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CONCAT(BD.subCode, '#', sNo) AS code,bd.subCode , bd.accountNo,bd.acHolderName,bd.bankName,bd.branch , bd.branchAddress,bd.ifscCode FROM bankDetail bd LEFT JOIN subGroup sg ON sg.subCode = bd.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode = 'BK'`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`, `dataman.utility.voucherType.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| deleteRecord | `if (DmShared.vNull(rsTemp.getString("Cnt")).doubleValue() == 1)` | "This is system define. You can not delete it." |
| deleteRecord | `if (rsTemp.getLong("Cnt") > 0)` | "Record is in use. You can not delete this record." |
| saveRecord | `if (DmShared.iNull(mrTemp.getSharedKeyFieldCode()).intValue()` | "Voucher Type is mis-matching!" |
| saveRecord | `if (DmShared.iNull(mrTemp.getSharedKeyFieldCode()).intValue() == 0)` | "Invalid key." |
| saveRecord | `if (rsTemp.next())` | "Description already exists." |
| saveRecord | `if (rsTemp.next())` | "Short Name already exists." |
| saveRecord | `if (CardType.FOREIGN_CURRENCY == CardType.getValueOf(DmShared.iNull(node.getCardType()).intValue()))` | " can not be default." |
| saveRecord | `if (DmShared.xNull(defaultValue).trim().equalsIgnoreCase("") && !DmShared.xNull(paymentList).trim().equalsIgnoreCase(""))` | "One payment mode must be default." |
| exportJson | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| exportJson | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |

## Notes (hand-written)
_No hand-written notes yet._
