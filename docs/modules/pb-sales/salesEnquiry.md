# pb-sales / salesEnquiry

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/sales/src/salesEnquiry/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: salesEnquiry, customerlist
- Update tables: salesEnquiry, customerlist
- Delete cascade (in order): salesEnquiry
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Customer Name (Text); Product Type (Text); Filter Remark (Text); Mobile No. (Text)
- Lookups/suggestions: getTxtHVoucherType, getTxtHCity, getTxtHProductType, getTxtHStatus, getSuggestionCutomer, getTxtHItem, getTxtHBFFrom, getTxtHGSMFrom, getTxtHBFTo, getTxtHGSMTo
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `HistoryPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `grdItemDetail` (DmGrid)

**Grid / popup columns** (header, width, db field):
- `grdItemDetail`: Curtomer Name [Text, w220]; Mobile No. [Text, w90]; Email [Text, w220]; Enquiry [Text, w390]

### Screen `PriceListPopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmGridPaging | `grdPriceListDetail` | `GrdPaging_PrpAcs, 13, false` |
| 2 | - | DmTextBox | `txtSearchValue` | `50, false` |
| 3 | Item Name | DmTextBox | `txtPartyName` | `50, false` |
| 4 | - | DmTextBox | `txtProductType` | `50, false` |
| 5 | - | DmTextHelpMulti | `txtHItem` | `com.google.gwt.core.client.GridItem_PrpAcs, 10` |
| 6 | GSM To | DmTextHelp | `txtHBFFrom` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, false` |
| 7 | - | DmTextHelp | `txtHBFTo` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, false` |
| 8 | - | DmTextHelp | `txtHGSMFrom` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, false` |
| 9 | - | DmTextHelp | `txtHGSMTo` | `com.google.gwt.core.client.KeyField_PrpAcs, 6, false` |

Other widgets (not in tab list): `cbShowUsedReels` (DmCheckBox), `txtHUnit` (DmTextHelp)

**Grid / popup columns** (header, width, db field):
- `txtHItem`: Name [Text, w250, name]; Manual Code [Text, w100, manualCode]; Stock [Text, w100, currentStock]
- `txtHBFFrom`: Name [Text, w1, name]
- `txtHBFTo`: Name [Text, w1, name]
- `txtHGSMFrom`: Name [Text, w1, name]
- `txtHGSMTo`: Name [Text, w1, name]
- `txtHUnit`: Name [Text, w1, unitName]
- `grdPriceListDetail`:  [Text, w30, isChecked]; Price List [Text, w200, itemName]; Item Name [Text, w300, itemName]; Item Category [Text, w210, itemCatName]; BF From [Text, w60, fromBfCode]; BF To [Text, w70, toBfCode]; GSM From [Text, w70, fromGsmCode]; GSM To [Text, w60, toGsmCode]; Unit [Text, w70, unitName]; Rate [Text, w60, rate]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHBFFrom` → `getTxtHBFFrom`
- `txtHBFTo` → `getTxtHBFTo`
- `txtHGSMFrom` → `getTxtHGSMFrom`
- `txtHGSMTo` → `getTxtHGSMTo`
- `grdPriceListDetail` → `getItemDetail`

**Enable/disable rules** (`manageDIsplay(flag)`, flag = editing mode):
- `txtPartyName.setEnabled(false)`
- `txtProductType.setEnabled(false)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHItem.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItem(hvm, "", false, false, helper, blnToEndOfFile, new AsyncCallback<DmResul
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItem).show();
public void onSuccess(DmResultPageing<List<TxtHItemName_Mdl>> result) {
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItem.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItem).show();
```

`txtHItem.addTextHelpMultiCallBack` → `getSelection()`
```java
if (!DmShared.xNull(txtHItem.getTag()).equalsIgnoreCase(DmShared.xNull(txtHItem.getTag2nd()))) {
clearReelNoTxtHelp();
txtHItem.setTag2nd(DmShared.xNull(txtHItem.getTag()));
priceListPopup_Mdl.setItemCode(DmShared.xNull(txtHItem.getTag()));
priceListPopup_Mdl.setItemName(DmShared.xNull(txtHItem.getText()));
```

`txtHBFFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBFFrom(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBFFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBFFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBFFrom).show();
```

`txtHBFFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBFFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBFFrom).show();
```

`txtHBFFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHBFFrom.getTag()).equalsIgnoreCase(DmShared.xNull(txtHBFFrom.getTag2nd()))) {
clearReelNoTxtHelp();
txtHBFFrom.setTag2nd(DmShared.xNull(txtHBFFrom.getTag()));
priceListPopup_Mdl.setBfFromCode(DmShared.xNull(txtHBFFrom.getTag()));
priceListPopup_Mdl.setBfFrom(DmShared.xNull(txtHBFFrom.getText()));
```

`txtHBFTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBFTo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBFTo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBFTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBFTo).show();
```

`txtHBFTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBFTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBFTo).show();
```

`txtHBFTo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHBFTo.getTag()).equalsIgnoreCase(DmShared.xNull(txtHBFTo.getTag2nd()))) {
clearReelNoTxtHelp();
txtHBFTo.setTag2nd(DmShared.xNull(txtHBFTo.getTag()));
priceListPopup_Mdl.setBfToCode(DmShared.xNull(txtHBFTo.getTag()));
priceListPopup_Mdl.setBfTo(DmShared.xNull(txtHBFTo.getText()));
```

`txtHGSMFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSMFrom(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFie
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGSMFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGSMFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGSMFrom).show();
```

`txtHGSMFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGSMFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGSMFrom).show();
```

`txtHGSMFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGSMFrom.getTag()).equalsIgnoreCase(DmShared.xNull(txtHGSMFrom.getTag2nd()))) {
clearReelNoTxtHelp();
txtHGSMFrom.setTag2nd(DmShared.xNull(txtHGSMFrom.getTag()));
priceListPopup_Mdl.setGsmFromCode(DmShared.xNull(txtHGSMFrom.getTag()));
priceListPopup_Mdl.setGsmFrom(DmShared.xNull(txtHGSMFrom.getText()));
if (blnStatus && t1Row != null) {
```

`txtHGSMTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGSMTo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyField
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGSMTo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGSMTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGSMTo).show();
```

`txtHGSMTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGSMTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGSMTo).show();
```

`txtHGSMTo.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHGSMTo.getTag()).equalsIgnoreCase(DmShared.xNull(txtHGSMTo.getTag2nd()))) {
clearReelNoTxtHelp();
txtHGSMTo.setTag2nd(DmShared.xNull(txtHGSMTo.getTag()));
priceListPopup_Mdl.setGsmToCode(DmShared.xNull(txtHGSMTo.getTag()));
priceListPopup_Mdl.setGsmTo(DmShared.xNull(txtHGSMTo.getText()));
if (blnStatus && t1Row != null) {
```

`grdPriceListDetail.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(false).getItemDetail(DmShared.xNull(partycode), priceListPopup_Mdl, helper, blnToEndOfFile, new Async
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<PriceListGridDetail_Mdl>> result) {
if (result.isStatus()) {
grdPriceListDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdPriceListDetail.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
grdPriceListDetail.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), (DmTextBox) null).show();
```

`grdPriceListDetail.addCallBack` → `getParam()`
```java
return valueIntialize();
```

### Screen `SalesEnquiry`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Enquiry Type | DmTextHelp | `txtHVoucherType` | `KeyField_PrpAcs, 4, true` |
| 2 | Date | DmTextBox | `txtVoucherDate` | `11, true` |
| 3 | Sales Enquiry No. | DmTextBox | `txtsaleEnquiryNo` | `20, false` |
| 4 | Customer Name | DmSuggestBox | `txtCustomer` | `100, 5, true, "Name", DmDataType.STRING` |
| 5 | Phone No. | DmTextBox | `txtPhoneNo` | `35, false` |
| 6 | Mobile No | DmTextBox | `txtMobileNo` | `35, true` |
| 7 | Address | DmTextBox | `txtAddress1` | `100, false` |
| 8 | - | DmTextBox | `txtAddress2` | `100, false` |
| 9 | City | DmTextHelp | `txtHCity` | `TxtHCity_Mdl_PrpAcs, 4, false` |
| 10 | State | DmTextBox | `txtState` | `100, false` |
| 11 | Email | DmTextBox | `txtEmail` | `250, false` |
| 12 | GSTIN | DmTextBox | `txtGstTin` | `15, false` |
| 13 | Product Type | DmTextHelp | `txtHProductType` | `KeyField_PrpAcs, 7, true` |
| 14 | Status | DmTextHelp | `txtHStatus` | `KeyField_PrpAcs, 7, false` |
| 15 | Sales Enquiry | DmTextBox | `txtSaleEnquery` | `100, false` |
| 16 | Remark | DmTextBox | `txtRemark` | `100, false` |
| 17 | - | DmTextBox | `txtFilterRemark` | `100, false` |

Other widgets (not in tab list): `dmFind` (DmFind)

**Grid / popup columns** (header, width, db field):
- `txtHVoucherType`: Type [Text, w0, name]
- `txtHCity`: City [Text, w0, CityName]; State [Text, w100, stateName]; Country [Text, w100, countryName]
- `txtHProductType`: Type [Text, w0, name]
- `txtHStatus`: Status [Text, w0, name]
- `dmFind`: PartyName [Text, w250, cutomerName]; Product Type [Text, w100]; Status [Text, w90, statusName]; City Name [Text, w110, cityName]; State Name [Text, w120, stateName]; Enquiry [Text, w250, enquiry]; Filter Remark [Text, w350, filterRemark]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHVoucherType` → `getTxtHVoucherType`
- `txtCustomer` → `getSuggestionCutomer`
- `txtHCity` → `getTxtHCity`
- `txtHProductType` → `getTxtHProductType`
- `txtHStatus` → `getTxtHStatus`
- `priceListPopup` → `getFind`
- Find popup → `getFind`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← topControl.getDocId()
- `VoucherTypeCode` ← DmShared.xNull(txtHVoucherType.getTag())
- `VoucherTypeName` ← DmShared.xNull(txtHVoucherType.getText())
- `U_EntDt_LatestLine` ← DmShared.xNull(txtsaleEnquiryNo.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtsaleEnquiryNo.getText())
- `SaleEnquiryDate` ← DmShared.dNull(txtVoucherDate.getText())
- `CustomerName` ← DmShared.xNull(txtCustomer.getText())
- `ProductCode` ← DmShared.xNull(txtHProductType.getTag())
- `ProductName` ← DmShared.xNull(txtHProductType.getText())
- `StatusCode` ← DmShared.xNull(txtHStatus.getTag())
- `StatusName` ← DmShared.xNull(txtHStatus.getText())
- `PhoneNo` ← DmShared.xNull(txtPhoneNo.getText())
- `MobileNo` ← DmShared.xNull(txtMobileNo.getText())
- `Address1` ← DmShared.xNull(txtAddress1.getText())
- `Address2` ← DmShared.xNull(txtAddress2.getText())
- `CityCode` ← DmShared.xNull(txtHCity.getTag())
- `CityName` ← DmShared.xNull(txtHCity.getText())
- `StateName` ← DmShared.xNull(txtState.getText())
- `GstTin` ← DmShared.xNull(txtGstTin.getText())
- `Email` ← DmShared.xNull(txtEmail.getText())
- `Enquiry` ← DmShared.xNull(txtSaleEnquery.getText())
- `Remark` ← DmShared.xNull(txtRemark.getText())
- `FilterRemark` ← DmShared.xNull(txtFilterRemark.getText())

**Load (record opened): widget ← model** (`topControlMoveRecSuccess`):
- `txtsaleEnquiryNo.setText` ← mrTemp.getSharedKeyFieldName()
- `txtsaleEnquiryNo.setTag` ← DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).toString()
- `txtsaleEnquiryNo.setTag2nd` ← mrTemp.getSharedKeyFieldCode()
- `txtHVoucherType.setTag` ← mrTemp.getVoucherTypeCode()
- `txtHVoucherType.setText` ← mrTemp.getVoucherTypeName()
- `txtVoucherDate.setText` ← mrTemp.getSaleEnquiryDate()
- `txtVoucherDate.setTag` ← mrTemp.getSaleEnquiryDate()
- `txtCustomer.setText` ← mrTemp.getCustomerName()
- `txtHProductType.setTag` ← mrTemp.getProductCode()
- `txtHProductType.setText` ← mrTemp.getProductName()
- `txtHStatus.setTag` ← mrTemp.getStatusCode()
- `txtHStatus.setText` ← mrTemp.getStatusCode()
- `txtPhoneNo.setText` ← mrTemp.getPhoneNo()
- `txtMobileNo.setText` ← mrTemp.getMobileNo()
- `txtAddress1.setText` ← mrTemp.getAddress1()
- `txtAddress2.setText` ← mrTemp.getAddress2()
- `txtHCity.setTag` ← mrTemp.getCityCode()
- `txtHCity.setTag2nd` ← mrTemp.getCityCode()
- `txtHCity.setText` ← mrTemp.getCityName()
- `txtState.setText` ← mrTemp.getStateName()
- `txtState.setTag` ← mrTemp.getStateNumericCode()
- `txtGstTin.setText` ← mrTemp.getGstTin()
- `txtEmail.setText` ← mrTemp.getEmail()
- `txtSaleEnquery.setText` ← mrTemp.getEnquiry()
- `txtRemark.setText` ← mrTemp.getRemark()
- `txtFilterRemark.setText` ← mrTemp.getFilterRemark()
- `userControl.setTextBlog` ← preparedByBlog, mrTemp.getSharedPreparedBy()
- `userControl.setTextBlog` ← modifiedByBlog, mrTemp.getSharedModifiedBy()
- userControl.setTextBlog(preparedByBlog, mrTemp.getSharedPreparedBy())
- userControl.setTextBlog(modifiedByBlog, mrTemp.getSharedModifiedBy())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtsaleEnquiryNo.setEnabled(false)`

**Defaults when the screen opens** (`iniDefaultValues`):
- calls server `getDefaultValues`

**On New click:**
- `onAdd()`

**`manageDisplay()` logic (condensed code):**
```java
txtsaleEnquiryNo.setEnabled(false);
txtCustomer.setFocus(true);
flwHistory.add(btnHistory);
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`topControl.addNewHandeller` → `newClicked()`
```java
onAdd();
```

`topControl.addEditHandeller` → `editClicked()`
```java
onEdit();
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

`topControl.addCloseHandeller` → `closeClicked()`
```java
DmClient.<SetWidgetInvoker>getStoreItem("swiEvent").closeWidget(SalesEnquiry.this);
```

`topControl.addOtherHandeller` → `otherClicked()`
```java
menu.show(topControl.getButton("Other"));
```

`modifyReason.addCallBack` → `onOk()`
```java
try {
SalesEnquiry.this.modifyReasonMdl = modifyReason;
if (topControl.getMode().equalsIgnoreCase("Browse") && DmShared.iNull(topControl.getDocId()).longValue() > 0) {
topControlDelete(topControl.getDocId());
} else {
topControlSave();
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), (DmTextBox) null).show();
```

`txtHVoucherType.addTextHelpCallBack` → `populateData()`
```java
selectedVoucherType = DmShared.xNull(txtHVoucherType.getTag());
srvAsync.invokeRPC(blnCallBackWait).getTxtHVoucherType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHVoucherType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHVoucherType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHVoucherType).show();
```

`txtHVoucherType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(selectedVoucherType).equalsIgnoreCase(DmShared.xNull(txtHVoucherType.getTag()))) {
voucherCategory = "";
if (blnStatus && t1Row != null) {
voucherCategory = t1Row.getSharedPreparedDate();
```

`txtCustomer.addCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getSuggestionCutomer(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtCustomer).show();
public void onSuccess(DmResultPageing<List<String>> result) {
if (result.isStatus()) {
txtCustomer.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCustomer).show();
```

`txtCustomer.addCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtCustomer.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtCustomer).show();
```

`txtHCity.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHCity(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<TxtHCity_
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHCity).show();
public void onSuccess(DmResultPageing<List<TxtHCity_Mdl>> result) {
try {
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `onSuccess()`
```java
try {
if (result.isStatus()) {
txtHCity.setData(result.getData(), result.getPaging());
} catch (Exception e) {
new DmAlertMessageBox(e.getMessage(), txtHCity).show();
```

`txtHCity.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHCity.getTag()).equalsIgnoreCase(DmShared.xNull(txtHCity.getTag2nd()))) {
txtHCity.setTag2nd(txtHCity.getTag());
if (blnStatus && tRow != null) {
txtState.setText(DmShared.xNull(tRow.getStateName()));
txtState.setTag(tRow.getStateNumericCode());
```

`txtHProductType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHProductType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHProductType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHProductType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProductType).show();
```

`txtHProductType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHProductType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHProductType).show();
```

`txtHStatus.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHStatus(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFiel
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHStatus).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`txtHStatus.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHStatus.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHStatus).show();
```

`priceListPopup.addCallBack` → `ok()`
```java
txtFilterRemark.setText("");
priceListPopup_Mdl = gplpm;
itemCodeCSV = gplpm.getSelectedItemCodeCSV();
itemNameCSV = gplpm.getSelectedItemNameCSV();
txtFilterRemark.setText(itemNameCSV);
```

`dmFind.addHelpGridCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getFind(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Find_Mdl>>>()
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

`txtMobileNo.addDomHandler` → `onKeyDown()`
```java
if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
fillCutomerDetail();
fillEnquiryHistory();
```

**Top control options:** setRefreshRequired(false), setOtherRequired(false), setDisplay(true)

**Menu items (Other button):** Manage Site, Export Department

_Print permission "P" is removed for this screen (replace "P" with "*")._

**Modify reason popup:** yes (Edit save and Delete).
- Confirm: "Are you sure? You want to delete this record."
- Alert: "Customer is mandatory."
- Alert: "Mobile No. is mandatory."
- Alert: "Address is mandatory."
- Alert: "City is mandatory."
- Alert: "Product Type is mandatory."
- Alert: "Filter Remark is mandatory."

## Files
| file | lines |
|---|---|
| client/HistoryPopup.java | 162 |
| client/HistoryPopup_Dsg.java | 156 |
| client/PriceListPopup.java | 553 |
| client/PriceListPopup_Dsg.java | 512 |
| client/SalesEnquiry.java | 891 |
| client/SalesEnquiry_Dsg.java | 507 |
| server/SalesEnquiry_SrvImpl.java | 1559 |
| shared/DefaultValue_Mdl.java | 139 |
| shared/Find_Mdl.java | 113 |
| shared/GetPriceListPopup_Mdl.java | 197 |
| shared/HeaderValue_Mdl.java | 67 |
| shared/HistoryGridDetail_Mdl.java | 59 |
| shared/PriceListGridDetail_Mdl.java | 212 |
| shared/SalesEnquiry_Mdl.java | 278 |
| shared/SalesEnquiry_Srv.java | 52 |
| shared/SalesEnquiry_SrvAsync.java | 54 |
| shared/SalesEnquiry_Vld.java | 17 |
| shared/TxtHCity_Mdl.java | 50 |
| shared/TxtHItemName_Mdl.java | 250 |
| shared/TxtHParty_Mdl.java | 150 |

## UI labels / columns (client)
`Filter Remark`, `Enquiry Type`, `Date`, `Sales Enquiry No.`, `Customer Name`, `Phone No.`, `Mobile No`, `Address`, `City`, `State`, `Email`, `GSTIN`, `Product Type`, `Status`, `Sales Enquiry`, `Remark`, `Party`, `Item Name`, `BF From`, `BF To`, `GSM From`, `GSM To`

## Models (shared DTOs)
### TxtHItemName_Mdl (extends `FullPaperProperty_Mdl implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | itsBasedOn |
| String | itemUnitCode |
| String | itemUnitName |
| String | itemUnitDisplayName |
| String | itemUnitDecimalPlaces |
| String | skuUnitDecimalPlaces |
| String | itemUnitConversionRate |
| String | itemdesc |
| String | tl_ExcessPer |
| String | tl_ShortagePer |
| String | hsnCode |
| String | hsnNo |
| String | itemGstNature |
| boolean | saleDiscountAllow |
| boolean | saleRateEditable |
| String | isubCode |
| String | salesAc |
| String | skuName |
| String | skuCode |
| String | gstUnitCode |
| String | balancingAc |
| String | itemType |
| String | categoryCode |
| String | categoryName |
| String | currentStock |

### SalesEnquiry_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| RecId | recId |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | voucherCategory |
| String | saleEnquiryNo |
| String | saleEnquiryDate |
| String | u_EntDt_LatestLine |
| String | customerName |
| String | cityCode |
| String | cityName |
| String | address1 |
| String | address2 |
| String | stateName |
| String | stateNumericCode |
| String | gstTin |
| String | email |
| String | phoneNo |
| String | mobileNo |
| String | productCode |
| String | productName |
| String | statusCode |
| String | statusName |
| String | enquiry |
| String | remark |
| String | filterRemark |
| String | subCode |
| String | priceListCSV |
| ModifyReason_Mdl | modifyReasonMdl |
| GetPriceListPopup_Mdl | priceListPopup_Mdl |

### GetPriceListPopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_sNo |
| String | fieldName |
| String | fieldNameValue |
| String | partyCode |
| String | partyName |
| String | productCode |
| String | productName |
| String | itemCode |
| String | itemName |
| String | bfFromCode |
| String | bfFrom |
| String | gsmFromCode |
| String | gsmFrom |
| String | bfToCode |
| String | bfTo |
| String | gsmToCode |
| String | gsmTo |
| String | selectedItemCodeCSV |
| String | selectedItemNameCSV |
| List<PriceListGridDetail_Mdl> | gridDetail_Mdls |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | voucherCategory |
| String | voucherTypeCode |
| String | voucherTypeName |
| String | currentDate |
| String | newVoucherDate |
| String | guiTemplateCode |
| String | balancingAc |
| String | newVoucherTypeCode |
| String | newVoucherTypeName |
| String | newVoucherCategory |
| String | newGuiTemplateCode |
| String | newBalancingAc |
| boolean | continuousNewMode |
| String | siteCode |

### HistoryGridDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | customerCode |
| String | customerName |
| String | phoneNo |
| String | email |
| String | enquiry |

### HeaderValue_Mdl
| type | field |
|---|---|
| String | partyCode |
| String | partyName |
| String | productCode |
| String | productName |
| String | mode |
| String | priceListCSV |

### Find_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | partyCode |
| String | partyName |
| String | productTypeCode |
| String | productType |
| String | statusCode |
| String | statusName |
| String | enquiry |
| String | filterRemark |
| String | cityCode |
| String | cityName |
| String | stateName |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |
| String | stateCode |
| String | stateName |
| String | zoneCode |
| String | zoneName |
| String | address1 |
| String | address2 |
| String | phoneNo |
| String | mobileNo |
| String | email |
| String | gstin |
| String | subCode |
| String | mode |

### PriceListGridDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemCatName |
| String | itemCatCode |
| String | itemName |
| String | itemCode |
| String | unitCode |
| String | unitName |
| String | bfFromCode |
| String | bfFrom |
| String | bfToCode |
| String | bfTo |
| String | gsmFromCode |
| String | gsmFrom |
| String | gsmToCode |
| String | gsmTo |
| String | rate |
| String | isChecked |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | countryName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHVoucherType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHProductType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHStatus | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getSuggestionCutomer | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<String>> |
| getTxtHCity | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getTxtHItem | HeaderValue_Mdl hvm, String itemCategoryCode, boolean flagScheme, boolean flagShowHelp, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemName_Mdl>> |
| getTxtHBFFrom | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSMFrom | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBFTo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSMTo | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getDefaultValues |  | DmResult<DefaultValue_Mdl> |
| getNavigator |  | DmResult<List<KeyFieldExtended>> |
| getMoveRec | String strKey | DmResult<SalesEnquiry_Mdl> |
| saveRecord | SalesEnquiry_Mdl mrTemp, String voucherPrefix | DmResult<String> |
| deleteRecord | String strKey, ModifyReason_Mdl modifyReasonMdl | DmResult<String> |
| getFind | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<Find_Mdl>> |
| getCustomerDetail | String phoneNumber | DmResult<TxtHParty_Mdl> |
| getEnquiryHistory | String phoneNumber | DmResult<List<HistoryGridDetail_Mdl>> |
| getItemDetail | String party, GetPriceListPopup_Mdl listPopup_Mdl, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<PriceListGridDetail_Mdl>> |

## Validator `SalesEnquiry_Vld`
```java
public class SalesEnquiry_Vld {
    public static void chkSave(SalesEnquiry_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getCustomerName().trim(), "Customer Name", 100);
        DmShared.isValidText(mrTemp.getProductCode().trim(), "Product Type", 200);
        DmShared.isValidText(mrTemp.getFilterRemark().trim(), "Filter Remark", 500);
        DmShared.isValidText(mrTemp.getMobileNo().trim(), "Mobile No.", 350);
        DmShared.isValidMobile(mrTemp.getMobileNo(), 350);
        DmShared.isValidEMail(mrTemp.getEmail(), 400);
    }
}
```

## Server `SalesEnquiry_SrvImpl.java` (1559 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnOldData`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmServer.setSsnOldData`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getTxtHVoucherType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHVoucherType(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type , vt.description AS name , vt.guiTemplateCode , vt.balancingAc , vt.category FROM voucher_Type vt WHERE vt.category IN ( {…} ) AND vt.isActive = 1`

### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select cityCode,cityName,stateCode,sm.name as stateName,sm.numericCode, ctr.name as countryName from city ct left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code`

### `getTxtHProductType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHProductType(DmHelper pageing, boolean blnToEndOfFile)`

- Other calls: `CORRUGATION.getCode`, `CORRUGATION.getName`, `MANUFACTURING.getCode`, `MANUFACTURING.getName`, `TRADING.getCode`, `TRADING.getName`

### `getTxtHStatus`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHStatus(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, manualCode, name FROM status`

### `getSuggestionCutomer`
`public DmResultPageing<List<String>> getSuggestionCutomer(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT cl.name FROM customerlist cl`

### `getTxtHItem`
`public DmResultPageing<List<TxtHItemName_Mdl>> getTxtHItem(HeaderValue_Mdl hvm, String itemCategoryCode, boolean flagScheme, boolean flagShowHelp, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT im.code , im.name , im.manualCode , im.itsBasedOn , im.itemDesc , im.saleUnit as itemUnitCode , um.unit as itemUnitName, um.gstUnitCode , um.decimalPlaces as itemUnitDecimalPlaces , ums.decimalPlaces as skuUnitDecimalPlaces , ums.unit as skuUnitName , im.sku as skuUnitCode , ISNULL(im.saleUnitConversionRate, 0) AS itemSaleUnitConversionRate , im.tl_ExcessPer , im.tl_ShortagePer , hsn.hsn as hsnNo , im.hsnCode , im.itemGSTNature , im.isSaleRateEditable , im.isDiscountAllow , im.subCode as iSubCode , im.salesAc , im.saleUnitConversionRate , im.cogsAc , it.manualCode as itemType , im.itemCategoryCode, ic.name as itemCategoryName, vis.currentStock FROM itemMast im LEFT JOIN itemSiteList sl ON sl.code = im.code LEFT JOIN hsnMast hsn on hsn.code = im.hsnCode LEFT JOIN unitMast um on um.code = im.saleUnit LEFT JOIN unitmast ums on ums.code = im.sku LEFT JOIN itemType it on it.code = im.itemType LEFT JOIN itemCat ic ON ic.code = im.itemCategoryCode LEFT JOIN ( SELECT ibs.itemCode , SUM(ibs.balanceQty) AS currentStock FROM itemBatchStock ibs WHERE ibs.site_Code = :site_Code GROUP BY ibs.itemCode ) AS vis ON im.code = vis.itemCode {…} WHERE im.IsActive = :one AND sl.site_Code = :site_Code {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:itemType` ← `DmShared.xNull(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", cnTem`

### `getTxtHBFFrom`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBFFrom(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf WHERE bf.isActive = 1`

### `getTxtHGSMFrom`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSMFrom(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm WHERE gm.isActive = 1`

### `getTxtHBFTo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBFTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf WHERE bf.isActive = 1`

### `getTxtHGSMTo`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSMTo(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm WHERE gm.isActive = 1`

### `getDefaultValues`
`public DmResult<DefaultValue_Mdl> getDefaultValues()`

- SQL 1: `SELECT TOP 1 vt.v_Type AS code , vt.description AS name , vt.guiTemplateCode , vt.category , vt.balancingAc FROM voucher_Type vt WHERE vt.category = :se AND vt.isActive = 1`
- Params: `:se` ← `pvc.GENERAL_SALES_ENQUIRY`

### `getNavigator`
`public DmResult<List<KeyFieldExtended>> getNavigator()`

- SQL 1: `SELECT se.docId FROM salesEnquiry se WHERE se.site_Code = :site_Code Order By se.cutomerName;`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

### `getMoveRec`
`public DmResult<SalesEnquiry_Mdl> getMoveRec(String strKey)`

- SQL 1: `SELECT se.docId,se.v_Type,vt.description as voucherName, vt.category AS voucherCategory , se.v_No,se.recId,se.recIdPrefix , se.v_Prefix,se.v_Date , se.priceList, se.cutomerName , c.cityCode, c.cityName , sm.name as stateName , se.add1, se.add2, se.gstin, se.mobile, se.phone, se.email , se.productType, se.status, s.name AS statusName , se.enquiry, se.filterRemark, se.preparedBy, se.preparedByIP , se.preparedDt, se.remark, se.site_Code , se.u_EntDt, se.u_Name, se.subCode FROM salesEnquiry se LEFT JOIN voucher_Type vt ON vt.v_Type = se.v_Type LEFT JOIN city c ON c.cityCode = se.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN status s ON s.code = se.status Where se.docId= :docId`
- SQL 2: `SELECT sg.subCode, sg.name, sg.phone, sg.mobile FROM subGroup sg WHERE sg.phone = :phone`
- Params: `:docId` ← `strKey`; `:phone` ← `DmShared.xNull(mrTemp.getPhoneNo())`
- Other calls: `ProductType.getViaCode`

### `deleteRecord`
`public DmResult<String> deleteRecord(String strKey, ModifyReason_Mdl modifyReasonMdl)`

- SQL 1: `Delete From salesEnquiry Where code= :code`
- Params: `:code` ← `strKey`

### `saveRecord`
`public DmResult<String> saveRecord(SalesEnquiry_Mdl mrTemp, String voucherPrefix)`

- SQL 1: `INSERT INTO salesEnquiry(docId, v_Type, v_no, recIdPrefix,recId ,v_Prefix,v_Date, cutomerName, subCode, productType, status, enquiry, phone, mobile , gsTin, cityCode, add1, add2, email , remark, filterRemark, priceList, site_Code, u_Name, u_EntDt, preparedBy , preparedDt, transferDt) VALUES (:docId, :v_Type, :v_no, :recIdPrefix,:recId,:v_Prefix ,:v_Date, :cutomerName, :subCode, :productType, :status, :enquiry, :phone, :mobile, :gsTin , :cityCode, :add1, :add2, :email , :remark, :filterRemark, :priceList, :site_Code, :u_Name, :u_EntDt, :preparedBy , :preparedDt, :transferDt)`
- SQL 2: `INSERT INTO customerlist (code, name, phone, mobile , gsTin, cityCode, add1, add2, email, subcode) VALUES(:code, :name, :phone, :mobile, :gsTin , :cityCode, :add1, :add2, :email, :subcode)`
- SQL 3: `Update salesEnquiry Set v_Date = :v_Date, cutomerName = :cutomerName, subCode = :subCode, phone = :phone, mobile = :mobile, gsTin = :gsTin, cityCode = :cityCode, add1 = :add1, add2 = :add2, email = :email, productType = :productType, status = :status, filterRemark = :filterRemark, remark = :remark, enquiry = :enquiry, u_Name = :uName, u_EntDt_LatestLine = :u_EntDt, u_EntDt = :u_EntDt Where docId = :docId`
- SQL 4: `Update customerlist Set name = :name, phone = :phone, mobile = :mobile, gsTin = :gsTin, cityCode = :cityCode, add1 = :add1, add2 = :add2, email = :email, subcode = :subcode Where code = :code`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:recId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:recIdPrefix` ← `DmShared.xNull(DmShared.xNull(mrTemp.getRecId().getPrefix()))`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getVoucherTypeCode())`; `:v_No` ← `DmShared.iNull(mrTemp.getRecId().getCounter()).toString()`; `:v_Prefix` ← `DmShared.xNull(voucherPrefix)`; `:v_Date` ← `DmServer.convertDate(mrTemp.getSaleEnquiryDate())`; `:cutomerName` ← `DmShared.xNull(mrTemp.getCustomerName())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSubCode())`; `:productType` ← `DmShared.xNull(mrTemp.getProductCode())`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:gsTin` ← `mrTemp.getGstTin()`; `:cityCode` ← `mrTemp.getCityCode()`; `:add1` ← `mrTemp.getAddress1()`; `:add2` ← `mrTemp.getAddress2()`; `:email` ← `mrTemp.getEmail()`; `:productType` ← `DmShared.xNull(mrTemp.getProductCode())`; `:status` ← `DmShared.xNull(mrTemp.getStatusCode())`; `:enquiry` ← `DmShared.xNull(mrTemp.getEnquiry())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:filterRemark` ← `DmShared.xNull(mrTemp.getFilterRemark())`; `:priceList` ← `DmShared.xNull(mrTemp.getPriceListPopup_Mdl().getSelectedItemCodeCSV())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:preparedBY` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `timestamp`; `:transferDt` ← `null`; `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getCustomerName()`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:gsTin` ← `mrTemp.getGstTin()`; `:cityCode` ← `mrTemp.getCityCode()`; `:add1` ← `mrTemp.getAddress1()`; `:add2` ← `mrTemp.getAddress2()`; `:email` ← `mrTemp.getEmail()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSubCode())`; `:cutomerName` ← `mrTemp.getCustomerName()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSubCode())`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:gsTin` ← `mrTemp.getGstTin()`; `:cityCode` ← `mrTemp.getCityCode()`; `:add1` ← `mrTemp.getAddress1()`; `:add2` ← `mrTemp.getAddress2()`; `:email` ← `mrTemp.getEmail()`; `:productType` ← `mrTemp.getProductCode()`; `:status` ← `mrTemp.getStatusCode()`; `:filterRemark` ← `DmShared.xNull(mrTemp.getFilterRemark())`; `:remark` ← `mrTemp.getRemark()`; `:enquiry` ← `mrTemp.getEnquiry()`; `:uName` ← `DmServer.getSsnUserName(this)`; `:u_EntDt` ← `timestamp`; `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getSaleEnquiryDate())`; `:name` ← `mrTemp.getCustomerName()`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:gsTin` ← `mrTemp.getGstTin()`; `:cityCode` ← `mrTemp.getCityCode()`; `:add1` ← `mrTemp.getAddress1()`; `:add2` ← `mrTemp.getAddress2()`; `:email` ← `mrTemp.getEmail()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSubCode())`; `:code` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`
- Errors: "Record has been changed, can't update record, refresh record then update" | "Invalid key."
- Other calls: `SalesEnquiry_Vld.chkSave`

### `getFind`
`public DmResultPageing<List<Find_Mdl>> getFind(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT se.docId, se.cutomerName , c.cityCode, c.cityName , sm.name as stateName , se.productType, se.status, st.name AS statusName , se.enquiry, se.filterRemark FROM salesEnquiry se LEFT JOIN city c ON c.cityCode = se.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode LEFT JOIN status st ON st.code = se.status WHERE se.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`
- Other calls: `ProductType.getViaCode`

### `getItemDetail`
`public DmResultPageing<List<PriceListGridDetail_Mdl>> getItemDetail(String party, GetPriceListPopup_Mdl svm, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT plm.code, plm.name, im.name as itemName, pl.itemCode , pl.unitCode, plm.applicableOn , um.unit AS unitName , im.itemCategoryCode as itemCatCode , ic.name as itemCatName , pl.rate, pl.cmn_Deduction, pl.cmn_Addition, pl.printSno, 2 as recordType , pl.fromBfCode, pl.toBfCode, pl.fromGsmCode, pl.toGsmCode, pl.estdLandedRate , pl.v_SNo , (CASE WHEN {…} THEN '1' ELSE '2' END) AS isChecked FROM priceList pl LEFT JOIN priceListMast plm On plm.code = pl.priceListCode LEFT JOIN itemMast im on im.code = pl.itemCode LEFT JOIN unitMast um ON um.code = pl.unitCode LEFT JOIN itemCat ic on ic.code = im.itemCategoryCode LEFT JOIN subGroupPriceList spl On spl.priceListCode = pl.priceListCode WHERE pl.site_Code = :site_Code AND plm.applicableOn = :applicableOn {…}`
- SQL 2: `selectedPriceList {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:applicableOn` ← `DmShared.iNull(STEnum.PriceListApplicableOn.SALES.getId()).toString()`; `:partyCode` ← `DmShared.xNull(party)`; `:fromBfCode` ← `DmShared.xNull(svm.getBfFromCode())`; `:toBfCode` ← `DmShared.xNull(svm.getBfToCode())`; `:fromGsmCode` ← `DmShared.xNull(svm.getGsmFromCode())`; `:toGsmCode` ← `DmShared.xNull(svm.getGsmToCode())`
- Other calls: `SALES.getId`

### `getCustomerDetail`
`public DmResult<TxtHParty_Mdl> getCustomerDetail(String phoneNumber)`

- SQL 1: `SELECT cl.code, cl.name, cl.phone, cl.mobile , cl.gsTin, cl.email , cl.cityCode, c.cityName , c.stateCode, sm.name AS stateName , cl.add1, cl.add2, cl.subcode FROM customerlist cl LEFT JOIN city c ON c.cityCode = cl.cityCode LEFT JOIN stateMast sm ON sm.code = c.stateCode {…}`
- Params: `:mobile` ← `DmShared.xNull(phoneNumber)`

### `getEnquiryHistory`
`public DmResult<List<HistoryGridDetail_Mdl>> getEnquiryHistory(String phoneNumber)`

- SQL 1: `SELECT se.docId, se.cutomerName, se.phone, se.email, se.enquiry FROM salesEnquiry se WHERE se.phone = :phone`
- Params: `:phone` ← `DmShared.xNull(phoneNumber)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmFind.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridPaging.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmSuggestBox.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmTopControl.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.manageSite.client`, `dataman.dmToolkit.modifyReason.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.sales.salesEnquiry.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.priceList.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(mrTemp.getU_EntDt_LatestLine()).longValue() != rsTemp.getTimestamp("u_EntDt_LatestLine").getTime())` | "Record has been changed, can't update record, refresh record then update" |
| saveRecord | `if (mrTemp.getSharedKeyFieldCode().equalsIgnoreCase(""))` | "Invalid key." |

## Notes (hand-written)
_No hand-written notes yet._
