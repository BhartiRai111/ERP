# dm-accounts / egstParam

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/accounts/src/egstParam/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: eGstParam
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: User Gstin (Text); E-way User Name (Text); E-way User Password (Text); E-way Asp Id (Text); E-way Asp Password (Text); E-invoice User Name (Text); E-invoice User Password (Text); E-invoice Asp Id (Text); E-invoice Asp Password (Text)
- Lookups/suggestions: getTxtHGstIn, getTxtHBaseUrl
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `EgstParam`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | User GSTIN | DmTextHelp | `txtHUserGstin` | `KeyField_PrpAcs, 7, true` |
| 2 | User Name | DmTextBox | `txtEwayUserName` | `50, true` |
| 3 | ASP ID | DmTextBox | `txtEwayAspId` | `10, true` |
| 4 | Auth Format | DmRemarkTextBox | `txtEwayAuthFormat` | `400, false` |
| 5 | Base URL Format | DmRemarkTextBox | `txtEwayUrlFormat` | `400, false` |
| 6 | Print Format | DmRemarkTextBox | `txtEwayPrintFormat` | `400, false` |
| 7 | Example Format | DmRemarkTextBox | `txtEwayExampleFormat` | `400, false` |
| 8 | - | DmTextHelp | `txtHEwayIRP` | `IrpEway_PrpAcs, 5` |
| 9 | Base URL | DmRemarkTextBox | `txtEwayBaseUrl` | `250, false` |
| 10 | GSP Name | DmTextBox | `txtEwayGSPName` | `50, false` |
| 11 | Auth Folder Name | DmTextBox | `txtEwayAuthFolderName` | `50, false` |
| 12 | Base Folder Name | DmTextBox | `txtEwayBaseFolderName` | `50, false` |
| 13 | Auth Version | DmTextBox | `txtEwayAuthVersion` | `10, false` |
| 14 | Base Version | DmTextBox | `txtEwayBaseVersion` | `10, false` |
| 15 | Print Version | DmTextBox | `txtEwayPrintlVersion` | `10, false` |
| 16 | Auth API Name | DmTextBox | `txtEwayAuthApiName` | `50, false` |
| 17 | Base API Name | DmTextBox | `txtEwayBaseApiName` | `50, false` |
| 18 | Print API Name | DmTextBox | `txtEwayPrintlApiName` | `50, false` |
| 19 | Auth Action | DmTextBox | `txtEwayAuthAction` | `50, false` |
| 20 | Genrate Action | DmTextBox | `txtEwayGenerateAction` | `50, false` |
| 21 | Cancel Action | DmTextBox | `txtEwayCancelAction` | `50, false` |
| 22 | Get Detail Action | DmTextBox | `txtEwaygetDetailAction` | `50, false` |
| 23 | Print Action | DmTextBox | `txtEwayPrintAction` | `50, false` |
| 24 | Detailed Print Action | DmTextBox | `txtEwayDetailedPrintAction` | `50, false` |
| 25 | User Name | DmTextBox | `txtEinvUserName` | `50, true` |
| 26 | ASP ID | DmTextBox | `txtEinvAspId` | `10, false` |
| 27 | Auth Format | DmRemarkTextBox | `txtEinvAuthFormat` | `400, false` |
| 28 | Base URL Format | DmRemarkTextBox | `txtEinvUrlFormat` | `400, false` |
| 29 | Print Format | DmRemarkTextBox | `txtEinvPrintFormat` | `400, false` |
| 30 | Example Format | DmRemarkTextBox | `txtEinvExampleFormat` | `400, false` |
| 31 | - | DmTextHelp | `txtHEinvIRP` | `IrpEinv_PrpAcs, 5` |
| 32 | Base URL | DmRemarkTextBox | `txtEinvBaseUrl` | `250, false` |
| 33 | GSP Name | DmTextBox | `txtEinvGSPName` | `50, false` |
| 34 | Auth Folder Name | DmTextBox | `txtEinvAuthFolderName` | `50, false` |
| 35 | Base Folder Name | DmTextBox | `txtEinvBaseFolderName` | `50, false` |
| 36 | Auth Version | DmTextBox | `txtEinvAuthVersion` | `10, false` |
| 37 | Base Version | DmTextBox | `txtEinvBaseVersion` | `10, true` |
| 38 | Print Version | DmTextBox | `txtEinvPrintlVersion` | `10, false` |
| 39 | Auth API Name | DmTextBox | `txtEinvAuthApiName` | `50, false` |
| 40 | Base API Name | DmTextBox | `txtEinvBaseApiName` | `50, false` |
| 41 | Print API Name | DmTextBox | `txtEinvPrintlApiName` | `50, false` |
| 42 | Auth Action | DmTextBox | `txtEinvAuthAction` | `50, false` |
| 43 | Genrate Action | DmTextBox | `txtEinvGenerateAction` | `50, false` |
| 44 | Cancel Action | DmTextBox | `txtEinvCancelAction` | `50, false` |
| 45 | Get Detail Action | DmTextBox | `txtEinvgetDetailAction` | `50, false` |
| 46 | Print Action | DmTextBox | `txtEinvPrintAction` | `50, false` |
| 47 | Detailed Print Action | DmTextBox | `txtEinvDetailedPrintAction` | `50, false` |
| 48 | Qr Code Size | DmTextBoxNumeric | `txtQrCodeSize` | `DmTextBoxNumeric.DecimalNature.NONE, false, 5, false` |
| 49 | Parse IRN Response | DmTextBoxNumeric | `txtParseIrnResp` | `DmTextBoxNumeric.DecimalNature.NONE, false, 5, false` |
| 50 | Cancel Format | DmRemarkTextBox | `txtEinvCancelFormat` | `250, false` |
| 51 | E-Way Via IRN API Name | DmTextBox | `txtEinv_Eway_ViaIRNApiName` | `50, false` |
| 52 | E-Way Via IRN API Folder | DmTextBox | `txtEinv_Eway_ViaIRNApiFolder` | `50, false` |
| 53 | E-Way Via IRN Version | DmTextBox | `txtEinv_Eway_ViaIRNApiVersion` | `20, false` |
| 54 | E-Way Via IRN URL Format | DmRemarkTextBox | `txtEinv_Eway_ViaIRNUrlFormat` | `250, false` |

**Grid / popup columns** (header, width, db field):
- `txtHEwayIRP`: Name [Text, w80, irpName]; URL [Text, w200, url]
- `txtHUserGstin`: GSTIN [Text, w0, gstin]
- `txtHUserGstinCopy`: GSTIN [Text, w0, gstin]
- `txtHEinvIRP`: Name [Text, w80, irpName]; URL [Text, w200, url]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHUserGstin` → `getTxtHGstIn`
- `txtHEwayIRP` → `getTxtHBaseUrl`
- `txtHEinvIRP` → `getTxtHBaseUrl`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `UserGstin` ← DmShared.xNull(txtHUserGstin.getText())
- `Eway_UserName` ← DmShared.xNull(txtEwayUserName.getText())
- `Eway_UserPassword` ← DmShared.xNull(txtEwayUserPass.getText())
- `Eway_AspUserId` ← DmShared.xNull(txtEwayAspId.getText())
- `Eway_AspPassword` ← DmShared.xNull(txtEwayAspPassword.getText())
- `Eway_BillApiVersion` ← DmShared.xNull(txtEwayBaseVersion.getText())
- `Eway_GenerateAction` ← DmShared.xNull(txtEwayGenerateAction.getText())
- `Eway_CancelAction` ← DmShared.xNull(txtEwayCancelAction.getText())
- `Eway_BaseUrl` ← DmShared.xNull(txtEwayBaseUrl.getText())
- `Eway_IrpCode` ← DmShared.xNull(txtHEwayIRP.getTag())
- `Eway_IrpName` ← DmShared.xNull(txtHEwayIRP.getText())
- `Eway_AuthApiVersion` ← DmShared.xNull(txtEwayAuthVersion.getText())
- `Eway_GSPName` ← DmShared.xNull(txtEwayGSPName.getText())
- `Eway_UrlFormat` ← DmShared.xNull(txtEwayUrlFormat.getText())
- `Eway_BillApiName` ← DmShared.xNull(txtEwayBaseApiName.getText())
- `Eway_AuthApiName` ← DmShared.xNull(txtEwayAuthApiName.getText())
- `Eway_AuthAction` ← DmShared.xNull(txtEwayAuthAction.getText())
- `Eway_AuthFormat` ← DmShared.xNull(txtEwayAuthFormat.getText())
- `Eway_PrintApiName` ← DmShared.xNull(txtEwayPrintlApiName.getText())
- `Eway_PrintAction` ← DmShared.xNull(txtEwayPrintAction.getText())
- `Eway_DetailePrintAction` ← DmShared.xNull(txtEwayDetailedPrintAction.getText())
- `Eway_PrintFormat` ← DmShared.xNull(txtEwayPrintFormat.getText())
- `Eway_AuthFolderName` ← DmShared.xNull(txtEwayAuthFolderName.getText())
- `Eway_BillFolderName` ← DmShared.xNull(txtEwayBaseFolderName.getText())
- `Eway_PrintApiVersion` ← DmShared.xNull(txtEwayPrintlVersion.getText())
- `Eway_getDetailAction` ← DmShared.xNull(txtEwaygetDetailAction.getText())
- `Einv_UserName` ← DmShared.xNull(txtEinvUserName.getText())
- `Einv_UserPassword` ← DmShared.xNull(txtEinvUserPass.getText())
- `Einv_ASPID` ← DmShared.xNull(txtEinvAspId.getText())
- `Einv_ASPID_Password` ← DmShared.xNull(txtEinvAspPassword.getText())
- `Einv_BillApiVersion` ← DmShared.xNull(txtEinvBaseVersion.getText())
- `Einv_GenerateAction` ← DmShared.xNull(txtEinvGenerateAction.getText())
- `Einv_CancelAction` ← DmShared.xNull(txtEinvCancelAction.getText())
- `Einv_IrpCode` ← DmShared.xNull(txtHEinvIRP.getTag())
- `Einv_IrpName` ← DmShared.xNull(txtHEinvIRP.getText())
- `Einv_BaseUrl` ← DmShared.xNull(txtEinvBaseUrl.getText())
- `Einv_AuthApiVersion` ← DmShared.xNull(txtEinvAuthVersion.getText())
- `Einv_GSPName` ← DmShared.xNull(txtEinvGSPName.getText())
- `Einv_UrlFormat` ← DmShared.xNull(txtEinvUrlFormat.getText())
- `Einv_BillApiName` ← DmShared.xNull(txtEinvBaseApiName.getText())
- `Einv_AuthApiName` ← DmShared.xNull(txtEinvAuthApiName.getText())
- `Einv_AuthAction` ← DmShared.xNull(txtEinvAuthAction.getText())
- `Einv_AuthFormat` ← DmShared.xNull(txtEinvAuthFormat.getText())
- `Einv_PrintApiName` ← DmShared.xNull(txtEinvPrintlApiName.getText())
- `Einv_PrintAction` ← DmShared.xNull(txtEinvPrintAction.getText())
- `Einv_DetailedPrintAction` ← DmShared.xNull(txtEinvDetailedPrintAction.getText())
- `Einv_PrintFormat` ← DmShared.xNull(txtEinvPrintFormat.getText())
- `Einv_AuthFolderName` ← DmShared.xNull(txtEinvAuthFolderName.getText())
- `Einv_ApiFolderName` ← DmShared.xNull(txtEinvBaseFolderName.getText())
- `Einv_PrintApiVersion` ← DmShared.xNull(txtEinvPrintlVersion.getText())
- `Einv_getDetailAction` ← DmShared.xNull(txtEinvgetDetailAction.getText())
- `Einv_CancelFormat` ← DmShared.xNull(txtEinvCancelFormat.getText())
- `Einv_eWayViaIrnApiName` ← DmShared.xNull(txtEinv_Eway_ViaIRNApiName.getText())
- `Einv_eWayViaIrnApiFolderName` ← DmShared.xNull(txtEinv_Eway_ViaIRNApiFolder.getText())
- `Einv_eWayViaIrnApiVersion` ← DmShared.xNull(txtEinv_Eway_ViaIRNApiVersion.getText())
- `Einv_eWayViaIrnUrlFormat` ← DmShared.xNull(txtEinv_Eway_ViaIRNUrlFormat.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtEwayUserName.setText` ← DmShared.xNull(data.getEway_UserName())
- `txtEwayUserPass.setText` ← DmShared.xNull(data.getEway_UserPassword())
- `txtEwayAspId.setText` ← DmShared.xNull(data.getEway_AspUserId())
- `txtEwayAspPassword.setText` ← DmShared.xNull(data.getEway_AspPassword())
- `txtEwayBaseVersion.setText` ← DmShared.xNull(data.getEway_BillApiVersion())
- `txtEwayGenerateAction.setText` ← DmShared.xNull(data.getEway_GenerateAction())
- `txtEwayCancelAction.setText` ← DmShared.xNull(data.getEway_CancelAction())
- `txtHEwayIRP.setTag` ← DmShared.xNull(data.getEway_IrpCode())
- `txtHEwayIRP.setText` ← DmShared.xNull(data.getEway_IrpName())
- `txtEwayBaseUrl.setText` ← DmShared.xNull(data.getEway_BaseUrl())
- `txtEwayAuthVersion.setText` ← DmShared.xNull(data.getEway_AuthApiVersion())
- `txtEwayGSPName.setText` ← DmShared.xNull(data.getEway_GSPName())
- `txtEwayUrlFormat.setText` ← DmShared.xNull(data.getEway_UrlFormat())
- `txtEwayBaseApiName.setText` ← DmShared.xNull(data.getEway_BillApiName())
- `txtEwayAuthApiName.setText` ← DmShared.xNull(data.getEway_AuthApiName())
- `txtEwayAuthAction.setText` ← DmShared.xNull(data.getEway_AuthAction())
- `txtEwayAuthFormat.setText` ← DmShared.xNull(data.getEway_AuthFormat())
- `txtEwayPrintlApiName.setText` ← DmShared.xNull(data.getEway_PrintApiName())
- `txtEwayPrintAction.setText` ← DmShared.xNull(data.getEway_PrintAction())
- `txtEwayDetailedPrintAction.setText` ← DmShared.xNull(data.getEway_DetailePrintAction())
- `txtEwayPrintFormat.setText` ← DmShared.xNull(data.getEway_PrintFormat())
- `txtEwayAuthFolderName.setText` ← DmShared.xNull(data.getEway_AuthFolderName())
- `txtEwayBaseFolderName.setText` ← DmShared.xNull(data.getEway_BillFolderName())
- `txtEwayPrintlVersion.setText` ← DmShared.xNull(data.getEway_PrintApiVersion())
- `txtEwaygetDetailAction.setText` ← DmShared.xNull(data.getEway_getDetailAction())
- `txtEinvUserName.setText` ← DmShared.xNull(data.getEinv_UserName())
- `txtEinvUserPass.setText` ← DmShared.xNull(data.getEinv_UserPassword())
- `txtEinvAspId.setText` ← DmShared.xNull(data.getEinv_ASPID())
- `txtEinvAspPassword.setText` ← DmShared.xNull(data.getEinv_ASPID_Password())
- `txtEinvBaseVersion.setText` ← DmShared.xNull(data.getEinv_BillApiVersion())
- `txtEinvGenerateAction.setText` ← DmShared.xNull(data.getEinv_GenerateAction())
- `txtEinvCancelAction.setText` ← DmShared.xNull(data.getEinv_CancelAction())
- `txtHEinvIRP.setTag` ← DmShared.xNull(data.getEinv_IrpCode())
- `txtHEinvIRP.setText` ← DmShared.xNull(data.getEinv_IrpName())
- `txtEinvBaseUrl.setText` ← DmShared.xNull(data.getEinv_BaseUrl())
- `txtEinvAuthVersion.setText` ← DmShared.xNull(data.getEinv_AuthApiVersion())
- `txtEinvGSPName.setText` ← DmShared.xNull(data.getEinv_GSPName())
- `txtEinvUrlFormat.setText` ← DmShared.xNull(data.getEinv_UrlFormat())
- `txtEinvBaseApiName.setText` ← DmShared.xNull(data.getEinv_BillApiName())
- `txtEinvAuthApiName.setText` ← DmShared.xNull(data.getEinv_AuthApiName())
- `txtEinvAuthAction.setText` ← DmShared.xNull(data.getEinv_AuthAction())
- `txtEinvAuthFormat.setText` ← DmShared.xNull(data.getEinv_AuthFormat())
- `txtEinvPrintlApiName.setText` ← DmShared.xNull(data.getEinv_PrintApiName())
- `txtEinvPrintAction.setText` ← DmShared.xNull(data.getEinv_PrintAction())
- `txtEinvDetailedPrintAction.setText` ← DmShared.xNull(data.getEinv_DetailedPrintAction())
- `txtEinvPrintFormat.setText` ← DmShared.xNull(data.getEinv_PrintFormat())
- `txtEinvAuthFolderName.setText` ← DmShared.xNull(data.getEinv_AuthFolderName())
- `txtEinvBaseFolderName.setText` ← DmShared.xNull(data.getEinv_ApiFolderName())
- `txtEinvPrintlVersion.setText` ← DmShared.xNull(data.getEinv_PrintApiVersion())
- `txtEinvgetDetailAction.setText` ← DmShared.xNull(data.getEinv_getDetailAction())
- `txtQrCodeSize.setText` ← DmShared.xNull(data.getEinv_qrCodeSize())
- `txtEinvCancelFormat.setText` ← DmShared.xNull(data.getEinv_CancelFormat())
- `txtEinv_Eway_ViaIRNApiName.setText` ← DmShared.xNull(data.getEinv_eWayViaIrnApiName())
- `txtEinv_Eway_ViaIRNApiFolder.setText` ← DmShared.xNull(data.getEinv_eWayViaIrnApiFolderName())
- `txtEinv_Eway_ViaIRNApiVersion.setText` ← DmShared.xNull(data.getEinv_eWayViaIrnApiVersion())
- `txtEinv_Eway_ViaIRNUrlFormat.setText` ← DmShared.xNull(data.getEinv_eWayViaIrnUrlFormat())
- `userControl.setTextBlog` ← modifiedByBlog, data.getUserDateTime()
- userControl.setTextBlog(modifiedByBlog, data.getUserDateTime())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHUserGstin.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGstIn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHUserGstin).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUserGstin.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUserGstin).show();
```

`txtHUserGstin.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUserGstin.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHUserGstin).show();
```

`txtHUserGstin.addTextHelpCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (t1Row != null) {
topControlMoveRec(DmShared.xNull(txtHUserGstin.getTag()));
destGstinCode = txtHUserGstin.getTag();
destGstinName = txtHUserGstin.getText();
```

`txtHUserGstinCopy.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGstIn(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHUserGstinCopy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHUserGstinCopy.addHelpGridCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHUserGstinCopy.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`txtHUserGstinCopy.addHelpGridCallBack` → `getSelectedRow()`
```java
if (blnStatus) {
if (t1Row != null) {
isCopy = t1Row.getSharedKeyFieldCode();
topControlMoveRec(t1Row.getSharedKeyFieldCode());
txtHUserGstin.setText(DmShared.xNull(destGstinName));
txtHUserGstin.setTag(DmShared.iNull(destGstinCode).toString());
```

`txtHEwayIRP.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBaseUrl(DmEnum.IRP_Type.E_WAY.getCode(), helper, blnToEndOfFile, new AsyncCal
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEwayIRP).show();
public void onSuccess(DmResultPageing<List<IrpInfoPopup_Mdl>> result) {
if (result.isStatus()) {
txtHEwayIRP.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEwayIRP).show();
```

`txtHEwayIRP.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEwayIRP.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEwayIRP).show();
```

`txtHEwayIRP.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHEwayIRP.getTag2nd()).equalsIgnoreCase(txtHEwayIRP.getTag())) {
txtEwayBaseUrl.setText("");
txtHEwayIRP.setTag2nd(DmShared.xNull(txtHEwayIRP.getTag()));
if (tRow != null && blnStatus) {
txtEwayBaseUrl.setText(DmShared.xNull(tRow.getUrl()));
```

`txtHEinvIRP.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBaseUrl(DmEnum.IRP_Type.E_INVOICE.getCode(), helper, blnToEndOfFile, new Asyn
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEinvIRP).show();
public void onSuccess(DmResultPageing<List<IrpInfoPopup_Mdl>> result) {
if (result.isStatus()) {
txtHEinvIRP.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEinvIRP).show();
```

`txtHEinvIRP.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEinvIRP.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEinvIRP).show();
```

`txtHEinvIRP.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHEinvIRP.getTag2nd()).equalsIgnoreCase(txtHEinvIRP.getTag())) {
txtEinvBaseUrl.setText("");
txtHEinvIRP.setTag2nd(DmShared.xNull(txtHEinvIRP.getTag()));
if (tRow != null && blnStatus) {
txtEinvBaseUrl.setText(DmShared.xNull(tRow.getUrl()));
```

`irpInfo.addCallBack` → `onOk()`
```java
if (mrTemp != null) {
if (DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue() > 0) {
if (DmEnum.IRP_Type.getViaCode(irpTypeCode) != null) {
switch (DmEnum.IRP_Type.getViaCode(irpTypeCode)) {
case E_WAY:
if (DmShared.iNull(mrTemp.getIsActiveCode()).intValue() == 1) {
txtHEwayIRP.setTag(DmShared.xNull(mrTemp.getSharedKeyFieldCode()));
txtHEwayIRP.setTag2nd(DmShared.xNull(mrTemp.getSharedKeyFieldCode()));
txtHEwayIRP.setText(DmShared.xNull(mrTemp.getSharedKeyFieldName()));
txtEwayBaseUrl.setText(DmShared.xNull(mrTemp.getUrl()));
...
```

`irpInfo.addCallBack` → `execute()`
```java
txtHEwayIRP.setFocus(true);
```

`irpInfo.addCallBack` → `execute()`
```java
txtHEinvIRP.setFocus(true);
```

`irpInfo.addCallBack` → `onClose()`
```java
txtHEwayIRP.setFocus(true);
```

`irpInfo.addCallBack` → `onDelete()`
```java
if (DmEnum.IRP_Type.getViaCode(irpTypeCode) != null) {
switch (DmEnum.IRP_Type.getViaCode(irpTypeCode)) {
case E_WAY:
txtHEwayIRP.setTag("");
txtHEwayIRP.setTag2nd("");
txtHEwayIRP.setText("");
txtEwayBaseUrl.setText("");
Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
public void execute() {
txtHEwayIRP.setFocus(true);
...
```

`irpInfo.addCallBack` → `execute()`
```java
txtHEwayIRP.setFocus(true);
```

`irpInfo.addCallBack` → `execute()`
```java
txtHEinvIRP.setFocus(true);
```
- Confirm: "Are you sure? You want to close."
- Alert: "Record saved successfully"

## Files
| file | lines |
|---|---|
| client/EgstParam.java | 665 |
| client/EgstParam_Dsg.java | 1428 |
| server/EgstParam_SrvImpl.java | 405 |
| shared/EgstParam_Srv.java | 23 |
| shared/EgstParam_SrvAsync.java | 24 |
| shared/EgstParam_Vld.java | 21 |

## UI labels / columns (client)
`IRP`, `User GSTIN`, `User Name`, `User Password`, `Confirm Password`, `ASP ID`, `ASP Password`, `Auth Format`, `Base URL Format`, `Print Format`, `Example Format`, `Base URL`, `GSP Name`, `Auth Folder Name`, `Base Folder Name`, `Auth Version`, `Base Version`, `Print Version`, `Auth API Name`, `Base API Name`, `Print API Name`, `Auth Action`, `Genrate Action`, `Cancel Action`, `Get Detail Action`, `Print Action`, `Detailed Print Action`, `Qr Code Size`, `Parse IRN Response`, `Cancel Format`, `E-Way Via IRN API Name`, `E-Way Via IRN API Folder`, `E-Way Via IRN Version`, `E-Way Via IRN URL Format`

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | EGstParam_Mdl mdl | DmResult<String> |
| getMoveRec | String strKey | DmResult<EGstParam_Mdl> |
| getDefaultValue |  | DmResult<KeyFieldExtended> |
| getTxtHGstIn | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBaseUrl | int irpTypeCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<IrpInfoPopup_Mdl>> |

## Validator `EgstParam_Vld`
```java
public class EgstParam_Vld {
    public static void chkSave(EGstParam_Mdl mrTemp) throws DmException {
        DmShared.isValidText(mrTemp.getUserGstin(), "User Gstin", 100);
        DmShared.isValidText(mrTemp.getEway_UserName(), "E-way User Name", 200);
        DmShared.isValidText(mrTemp.getEway_UserPassword(), "E-way User Password", 300);
        DmShared.isValidText(mrTemp.getEway_AspUserId(), "E-way Asp Id", 400);
        DmShared.isValidText(mrTemp.getEway_AspPassword(), "E-way Asp Password", 500);
        DmShared.isValidText(mrTemp.getEinv_UserName(), "E-invoice User Name", 600);
        DmShared.isValidText(mrTemp.getEinv_UserPassword(), "E-invoice User Password", 700);
        DmShared.isValidText(mrTemp.getEinv_ASPID(), "E-invoice Asp Id", 800);
        DmShared.isValidText(mrTemp.getEinv_ASPID_Password(), "E-invoice Asp Password", 900);
    }
}
```

## Server `EgstParam_SrvImpl.java` (405 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getEGstParam`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.setSsnData`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(EGstParam_Mdl mrTemp)`

- SQL 1: `select count(*) as cnt from eGstParam where userGstin = :userGstin`
- SQL 2: `UPDATE eGstParam SET eway_UserName =:eway_UserName ,eway_UserPassword =:eway_UserPassword ,eway_UrlVersion =:eway_UrlVersion ,eway_GenerateAction =:eway_GenerateAction ,eway_CancelAction =:eway_CancelAction ,eway_IrpCode =:eway_IrpCode ,eway_ASPID =:eway_ASPID ,eway_ASPID_Password =:eway_ASPID_Password ,eway_GSPName =:eway_GSPName ,eway_UrlFormat =:eway_UrlFormat ,eway_BillApiName =:eway_BillApiName ,eway_AuthApiName =:eway_AuthApiName ,eway_AuthAction =:eway_AuthAction ,eway_AuthFormat =:eway_AuthFormat ,eway_PrintApiName =:eway_PrintApiName ,eway_PrintAction =:eway_PrintAction ,eway_DetailedPrintAction =:eway_DetailedPrintAction ,eway_PrintFormat =:eway_PrintFormat ,eway_AuthFolderName =:eway_AuthFolderName ,eway_BillFolderName =:eway_BillFolderName ,eway_AuthApiVersion =:eway_AuthApiVersion ,eway_BillApiVersion =:eway_BillApiVersion ,eway_PrintApiVersion =:eway_PrintApiVersion ,eway_getDetailAction =:eway_getDetailAction ,einv_UserName =:einv_UserName ,einv_UserPassword =:einv_UserPassword ,einv_IrpCode =:einv_IrpCode ,einv_GSPName =:einv_GSPName ,einv_ASPID =:einv_ASPID ,einv_ASPID_Password =:einv_ASPID_Password ,einv_UrlFormat =:einv_UrlFormat ,einv_AuthFormat =:einv_AuthFormat ,einv_PrintFormat =:einv_PrintFormat ,einv_AuthFolderName =:einv_AuthFolderName ,einv_ApiFolderName =:einv_ApiFolderName ,einv_AuthApiName =:einv_AuthApiName ,einv_BillApiName =:einv_BillApiName ,einv_PrintApiName =:einv_PrintApiName ,einv_AuthApiVersion =:einv_AuthApiVersion ,einv_BillApiVersion =:einv_BillApiVersion ,einv_PrintApiVersion =:einv_PrintApiVersion ,einv_AuthAction =:einv_AuthAction ,einv_GenerateAction =:einv_GenerateAction ,einv_CancelAction =:einv_CancelAction ,einv_PrintAction =:einv_PrintAction ,einv_DetailedPrintAction =:einv_DetailedPrintAction ,einv_getDetailAction =:einv_getDetailAction ,einv_qrCodeSize =:einv_qrCodeSize ,einv_parseIrnResp =:einv_parseIrnResp ,einv_eWayViaIrnApiName =:einv_eWayViaIrnApiName ,einv_eWayViaIrnApiFolderName =:einv_eWayViaIrnApiFolderName ,einv_eWayViaIrnApiVersion =:einv_eWayViaIrnApiVersion ,einv_eWayViaIrnUrlFormat =:einv_eWayViaIrnUrlFormat ,einv_CancelFormat =:einv_CancelFormat ,u_Name =:u_Name ,u_EntDt =:u_EntDt WHERE userGstin =:userGstin`
- Params: `:userGstin` ← `DmShared.xNull(mrTemp.getUserGstin())`; `:userGstin` ← `DmShared.xNull(mrTemp.getUserGstin())`; `:userGstin` ← `DmShared.foriegnKey(mrTemp.getUserGstin())`; `:eway_UserName` ← `DmShared.xNull(mrTemp.getEway_UserName())`; `:eway_UserPassword` ← `DmShared.xNull(mrTemp.getEway_UserPassword())`; `:eway_UrlVersion` ← `DmShared.xNull(mrTemp.getEway_BillApiVersion())`; `:eway_GenerateAction` ← `DmShared.xNull(mrTemp.getEway_GenerateAction())`; `:eway_CancelAction` ← `DmShared.xNull(mrTemp.getEway_CancelAction())`; `:eway_IrpCode` ← `DmShared.foriegnKey(mrTemp.getEway_IrpCode())`; `:eway_ASPID` ← `DmShared.xNull(mrTemp.getEway_AspUserId())`; `:eway_ASPID_Password` ← `DmShared.xNull(mrTemp.getEway_AspPassword())`; `:eway_GSPName` ← `DmShared.xNull(mrTemp.getEway_GSPName())`; `:eway_UrlFormat` ← `DmShared.xNull(mrTemp.getEway_UrlFormat())`; `:eway_BillApiName` ← `DmShared.xNull(mrTemp.getEway_BillApiName())`; `:eway_AuthApiName` ← `DmShared.xNull(mrTemp.getEway_AuthApiName())`; `:eway_AuthAction` ← `DmShared.xNull(mrTemp.getEway_AuthAction())`; `:eway_AuthFormat` ← `DmShared.xNull(mrTemp.getEway_AuthFormat())`; `:eway_PrintApiName` ← `DmShared.xNull(mrTemp.getEway_PrintApiName())`; `:eway_PrintAction` ← `DmShared.xNull(mrTemp.getEway_PrintAction())`; `:eway_DetailedPrintAction` ← `DmShared.xNull(mrTemp.getEway_DetailePrintAction())`; `:eway_PrintFormat` ← `DmShared.xNull(mrTemp.getEway_PrintFormat())`; `:eway_AuthFolderName` ← `DmShared.xNull(mrTemp.getEway_AuthFolderName())`; `:eway_BillFolderName` ← `DmShared.xNull(mrTemp.getEway_BillFolderName())`; `:eway_AuthApiVersion` ← `DmShared.xNull(mrTemp.getEway_AuthApiVersion())`; `:eway_BillApiVersion` ← `DmShared.xNull(mrTemp.getEway_BillApiVersion())`; `:eway_PrintApiVersion` ← `DmShared.xNull(mrTemp.getEway_PrintApiVersion())`; `:eway_getDetailAction` ← `DmShared.xNull(mrTemp.getEway_getDetailAction())`; `:einv_UserName` ← `DmShared.xNull(mrTemp.getEinv_UserName())`; `:einv_UserPassword` ← `DmShared.xNull(mrTemp.getEinv_UserPassword())`; `:einv_GenerateAction` ← `DmShared.xNull(mrTemp.getEinv_GenerateAction())`; `:einv_CancelAction` ← `DmShared.xNull(mrTemp.getEinv_CancelAction())`; `:einv_IrpCode` ← `DmShared.foriegnKey(mrTemp.getEinv_IrpCode())`; `:einv_ASPID` ← `DmShared.xNull(mrTemp.getEinv_ASPID())`; `:einv_ASPID_Password` ← `DmShared.xNull(mrTemp.getEinv_ASPID_Password())`; `:einv_GSPName` ← `DmShared.xNull(mrTemp.getEinv_GSPName())`; `:einv_UrlFormat` ← `DmShared.xNull(mrTemp.getEinv_UrlFormat())`; `:einv_BillApiName` ← `DmShared.xNull(mrTemp.getEinv_BillApiName())`; `:einv_AuthApiName` ← `DmShared.xNull(mrTemp.getEinv_AuthApiName())`; `:einv_AuthAction` ← `DmShared.xNull(mrTemp.getEinv_AuthAction())`; `:einv_AuthFormat` ← `DmShared.xNull(mrTemp.getEinv_AuthFormat())`; `:einv_PrintApiName` ← `DmShared.xNull(mrTemp.getEinv_PrintApiName())`; `:einv_PrintAction` ← `DmShared.xNull(mrTemp.getEinv_PrintAction())`; `:einv_DetailedPrintAction` ← `DmShared.xNull(mrTemp.getEinv_DetailedPrintAction())`; `:einv_PrintFormat` ← `DmShared.xNull(mrTemp.getEinv_PrintFormat())`; `:einv_AuthFolderName` ← `DmShared.xNull(mrTemp.getEinv_AuthFolderName())`; `:einv_ApiFolderName` ← `DmShared.xNull(mrTemp.getEinv_ApiFolderName())`; `:einv_AuthApiVersion` ← `DmShared.xNull(mrTemp.getEinv_AuthApiVersion())`; `:einv_BillApiVersion` ← `DmShared.xNull(mrTemp.getEinv_BillApiVersion())`; `:einv_PrintApiVersion` ← `DmShared.xNull(mrTemp.getEinv_PrintApiVersion())`; `:einv_getDetailAction` ← `DmShared.xNull(mrTemp.getEinv_getDetailAction())`; `:einv_qrCodeSize` ← `DmShared.xNull(mrTemp.getEinv_qrCodeSize())`; `:einv_qrCodeSize` ← `"250"`; `:einv_parseIrnResp` ← `DmShared.xNull(mrTemp.getEinv_parseIrnResp())`; `:einv_eWayViaIrnApiName` ← `DmShared.xNull(mrTemp.getEinv_eWayViaIrnApiName())`; `:einv_eWayViaIrnApiFolderName` ← `DmShared.xNull(mrTemp.getEinv_eWayViaIrnApiFolderName())`; `:einv_eWayViaIrnApiVersion` ← `DmShared.xNull(mrTemp.getEinv_eWayViaIrnApiVersion())`; `:einv_eWayViaIrnUrlFormat` ← `DmShared.xNull(mrTemp.getEinv_eWayViaIrnUrlFormat())`; `:einv_CancelFormat` ← `DmShared.xNull(mrTemp.getEinv_CancelFormat())`; `:u_Name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid Gstin"
- Other calls: `EgstParam_Vld.chkSave`

### `getTxtHGstIn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGstIn(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT DISTINCT s.gstin FROM siteMast as s WHERE ISNULL(s.gstin, '')<> ''`

### `getMoveRec`
`public DmResult<EGstParam_Mdl> getMoveRec(String keyCode)`


### `getDefaultValue`
`public DmResult<KeyFieldExtended> getDefaultValue()`


### `refreshSessionValues`
`private void refreshSessionValues(String userGstin)`


### `getTxtHBaseUrl`
`public DmResultPageing<List<IrpInfoPopup_Mdl>> getTxtHBaseUrl(int irpTypeCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT irm.code, irm.name as irpName , irm.url FROM irpMast irm WHERE irm.type = :irpType AND irm.isActive = 1`

## Depends on (imports from other packages)
`dataman.accounts.egstParam.shared`, `dataman.accounts.irpInfoPopup.client`, `dataman.accounts.irpInfoPopup.shared`, `dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmHelpGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.gst.shared`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (mrTemp.getUserGstin().equalsIgnoreCase(""))` | "Invalid Gstin" |

## Notes (hand-written)
_No hand-written notes yet._
