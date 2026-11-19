# pb-master / changePriceList

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/changePriceList/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: subGroupPriceList
- Update tables: subGroupPriceList, sg, priceList, Price
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Entry From (Text); Applicable On (Text); Type (Text); Site Specific (Text); Applicable Date (Text); Price List (Text); Action Type (Text); Change Type (Text); rule: Multiple price list can not be copied to a New price list!; rule: New price list name and Price list name can not be same!; rule: Please enter New Applicable Date!; rule: Please enter Rate(%) Or Rate(Value)!; rule: Rate(%) can not be greater than or equal to 100%!; rule: Unit is medatory!
- Lookups/suggestions: getTxtHEntryFrom, getTxtHApplicableOn, getTxtHFreightType, getTxtHPriceListType, getTxtHPriceList, getTxtHNewPriceList, getTxtHActionType, getTxtHBf, getTxtHGsm, getTxtHItemCategory, getTxtHChangeType, getTxtHParty, getTxtHUnit
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ChangePriceList`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Entry From | DmTextHelp | `txtHEntryFrom` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 2 | Applicable On | DmTextHelp | `txtHApplicableOn` | `com.google.gwt.core.client.KeyField_PrpAcs, 3, true` |
| 3 | Freight Type | DmTextHelp | `txtHFreightType` | `com.google.gwt.core.shared.KeyField_PrpAcs, 4, true, true` |
| 4 | Type | DmTextHelp | `txtHPriceListType` | `com.google.gwt.core.shared.KeyField_PrpAcs, 5, true, true` |
| 5 | Site Specific | DmTextHelp | `txtHSiteSpecific` | `com.google.gwt.core.shared.KeyField_PrpAcs, 3, true, true` |
| 6 | Applicable Date | DmTextBox | `txtApplicableDate` | `11, true` |
| 7 | Price List | DmTextHelpMulti | `txtHPriceList` | `com.google.gwt.core.shared.RateList_PrpAcs, 10, true` |
| 8 | Action Type | DmTextHelp | `txtHActionType` | `KeyField_PrpAcs, 3, true, true` |
| 9 | New Applicable Date | DmTextBox | `txtNewApplicableDate` | `11, false` |
| 10 | New Price List | DmTextHelp | `txtHNewPriceList` | `com.google.gwt.core.shared.KeyField_PrpAcs, 5` |
| 11 | BF From | DmTextHelp | `txtHBfFrom` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 12 | To | DmTextHelp | `txtHBfTo` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 13 | GSM From | DmTextHelp | `txtHGsmFrom` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 14 | To | DmTextHelp | `txtHGsmTo` | `com.google.gwt.core.client.KeyField_PrpAcs, 5, false` |
| 15 | Item Category | DmTextHelpMulti | `txtHItemCategory` | `KeyField_PrpAcs, 5` |
| 16 | Party | DmTextHelpMulti | `txtHParty` | `TxtHSupplierName_PrpAcs, 7` |
| 17 | Change Type | DmTextHelp | `txtHChangeType` | `com.google.gwt.core.shared.KeyField_PrpAcs, 3, true, true` |
| 18 | Unit | DmTextHelp | `txtHUnit` | `com.google.gwt.core.shared.KeyField_PrpAcs, 5` |
| 19 | - | DmTextBoxNumeric | `txtRateAt` | `2, false, 10, false` |
| 20 | - | DmTextBoxNumeric | `txtRateValue` | `2, false, 10, false` |

**Grid / popup columns** (header, width, db field):
- `txtHApplicableOn`: Name [Text, w0]
- `txtHEntryFrom`: Entry From [Text, w0]
- `txtHPriceListType`: Type [Text, w0]
- `txtHSiteSpecific`: Site specific [Text, w0]
- `txtHPriceList`: Name [Text, w220, priceListName]; Type [Text, w160, priceTypeName]
- `txtHFreightType`: Type [Text, w0, Name]
- `txtHActionType`: Type [Text, w0]
- `txtHNewPriceList`: Name [Text, w0, name]
- `txtHBfFrom`: BF From [Text, w100, name]
- `txtHBfTo`: BF To [Text, w100, name]
- `txtHGsmFrom`: GSM From [Text, w100, name]
- `txtHGsmTo`: GSM To [Text, w100, name]
- `txtHItemCategory`: Item Category [Text, w220, name]
- `txtHParty`: Name [Text, w350, supplierName]; Manual Code [Text, w100, manualCode]; City [Text, w120, cityName]
- `txtHChangeType`: Category [Text, w0]
- `txtHUnit`: Unit [Text, w0, name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHEntryFrom` → `getTxtHEntryFrom`
- `txtHApplicableOn` → `getTxtHApplicableOn`
- `txtHPriceListType` → `getTxtHPriceListType`
- `txtHSiteSpecific` → `getYesNo`
- `txtHFreightType` → `getTxtHFreightType`
- `txtHActionType` → `getTxtHActionType`
- `txtHNewPriceList` → `getTxtHNewPriceList`
- `txtHBfFrom` → `getTxtHBf`
- `txtHBfTo` → `getTxtHBf`
- `txtHGsmFrom` → `getTxtHGsm`
- `txtHGsmTo` → `getTxtHGsm`
- `txtHChangeType` → `getTxtHChangeType`
- `txtHUnit` → `getTxtHUnit`

**`manageDisplay()` logic (condensed code):**
```java
manageEntryFromDisplay();
manageChangeTypeDisplay();
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHEntryFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHEntryFrom(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHEntryFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHEntryFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEntryFrom).show();
```

`txtHEntryFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHEntryFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHEntryFrom).show();
```

`txtHEntryFrom.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHEntryFrom.getTag2nd()).equalsIgnoreCase(txtHEntryFrom.getTag())) {
txtHEntryFrom.setTag2nd(DmShared.xNull(txtHEntryFrom.getTag()));
resetEntryFrom();
manageEntryFromDisplay();
```

`txtHApplicableOn.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHApplicableOn(DmShared.xNull(txtHEntryFrom.getTag()), helper, blnToEndOfFile, 
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHApplicableOn).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHApplicableOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHApplicableOn).show();
```

`txtHApplicableOn.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHApplicableOn.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHApplicableOn).show();
```

`txtHApplicableOn.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHApplicableOn.getTag2nd()).equalsIgnoreCase(txtHApplicableOn.getTag())) {
txtHApplicableOn.setTag2nd(DmShared.xNull(txtHApplicableOn.getTag()));
resetApplicableOn();
```

`txtHPriceListType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPriceListType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPriceListType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHPriceListType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPriceListType).show();
```

`txtHPriceListType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPriceListType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPriceListType).show();
```

`txtHPriceListType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHPriceListType.getTag2nd()).equalsIgnoreCase(txtHPriceListType.getTag())) {
txtHPriceListType.setTag2nd(DmShared.xNull(txtHPriceListType.getTag()));
```

`txtHSiteSpecific.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getYesNo(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExte
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSiteSpecific.setData(result.getData(), result.getPaging());
```

`txtHSiteSpecific.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSiteSpecific.setData(result.getData(), result.getPaging());
```

`txtHSiteSpecific.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHSiteSpecific.getTag2nd()).equalsIgnoreCase(txtHSiteSpecific.getTag())) {
txtHSiteSpecific.setTag2nd(DmShared.xNull(txtHSiteSpecific.getTag()));
```

`txtHFreightType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHFreightType(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHFreightType).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHFreightType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFreightType).show();
```

`txtHFreightType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHFreightType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHFreightType).show();
```

`txtHFreightType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHFreightType.getTag2nd()).equalsIgnoreCase(txtHFreightType.getTag())) {
txtHFreightType.setTag2nd(txtHFreightType.getTag());
```

`txtHPriceList.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHPriceList(getChangePriceListMdl(), helper, blnToEndOfFile, new AsyncCallback<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHPriceList).show();
public void onSuccess(DmResultPageing<List<TxtHPriceList_Mdl>> result) {
if (result.isStatus()) {
txtHPriceList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPriceList).show();
```

`txtHPriceList.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHPriceList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHPriceList).show();
```

`txtHPriceList.addTextHelpMultiCallBack` → `getSelection()`
```java
partyCode = "";
refCustomerCode = "";
List<TxtHPriceList_Mdl> plList = txtHPriceList.getDmGrid().getGrid().getSelectionModel().getSelectedItems();
if (plList != null && plList.size() == 1) {
partyCode = DmShared.xNull(plList.get(plList.size() - 1).getPartyCode());
refCustomerCode = DmShared.xNull(plList.get(plList.size() - 1).getRefCustomerCode());
```

`txtHActionType.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHActionType(refCustomerCode, helper, blnToEndOfFile, new AsyncCallback<DmResul
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), (DmTextBox) null).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHActionType.setData(result.getData(), result.getPaging());
```

`txtHActionType.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHActionType.setData(result.getData(), result.getPaging());
```

`txtHActionType.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHActionType.getTag2nd()).equalsIgnoreCase(txtHActionType.getTag())) {
resetActionType();
txtHActionType.setTag2nd(DmShared.xNull(txtHActionType.getTag()));
if (blnStatus && t1Row != null) {
txtHUnit.setTag(DmShared.xNull(dvm.getUnitCode()));
txtHUnit.setText(DmShared.xNull(dvm.getUnitName()));
txtHUnit.setTag2nd(DmShared.xNull(dvm.getUnitCode()));
manageActionTypeDisplay();
```

`txtHNewPriceList.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHNewPriceList(txtHEntryFrom.getTag(), txtHPriceListType.getTag(),
txtHApplicableOn.getTag(), txtHSiteSpecific.getTag(), txtHPriceList.getTag(), partyCode,
helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNewPriceList).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNewPriceList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNewPriceList).show();
...
```

`txtHNewPriceList.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNewPriceList.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNewPriceList).show();
```

`txtHNewPriceList.addTextHelpCallBack` → `getSelectedRow()`
```java
if (!DmShared.xNull(txtHNewPriceList.getTag2nd()).equalsIgnoreCase(txtHNewPriceList.getTag())) {
resetNewPriceList();
txtHNewPriceList.setTag2nd(DmShared.xNull(txtHChangeType.getTag()));
manageActionTypeDisplay();
```

`txtHBfFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBf(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBfFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBfFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfFrom).show();
```

`txtHBfFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBfFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfFrom).show();
```

`txtHBfTo.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHBf(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldExt
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHBfTo).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHBfTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfTo).show();
```

`txtHBfTo.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHBfTo.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHBfTo).show();
```

`txtHGsmFrom.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHGsm(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldEx
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHGsmFrom).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHGsmFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsmFrom).show();
```

`txtHGsmFrom.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHGsmFrom.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHGsmFrom).show();
```
- Confirm: "Are you sure? You want to close."
- Confirm: "Do you really want to 'Change Price List'?"

## Files
| file | lines |
|---|---|
| client/ChangePriceList.java | 1111 |
| client/ChangePriceList_Dsg.java | 630 |
| server/ChangePriceList_SrvImpl.java | 1899 |
| shared/ChangePriceList_Mdl.java | 349 |
| shared/ChangePriceList_Srv.java | 43 |
| shared/ChangePriceList_SrvAsync.java | 43 |
| shared/ChangePriceList_Vld.java | 50 |
| shared/DefaultValue_Mdl.java | 33 |
| shared/ItemDetail_Mdl.java | 224 |
| shared/PartyPriceList_Mdl.java | 36 |
| shared/PriceListBase_Mdl.java | 34 |
| shared/TxtHParty_Mdl.java | 42 |
| shared/TxtHPriceList_Mdl.java | 63 |

## UI labels / columns (client)
`Increase Rate(%)`, `Increase Rate(Value)`, `Entry From`, `Applicable On`, `Freight Type`, `Type`, `Site Specific`, `Applicable Date`, `Price List`, `Action Type`, `New Applicable Date`, `New Price List`, `BF From`, `To`, `GSM From`, `Item Category`, `Party`, `Change Type`, `Unit`

## Models (shared DTOs)
### PartyPriceList_Mdl
| type | field |
|---|---|
| String | partyCode |
| List<ItemDetail_Mdl> | detail_Mdls |

### PriceListBase_Mdl
| type | field |
|---|---|
| String | priceListCode |
| List<PartyPriceList_Mdl> | partyPriceListMdl |

### DefaultValue_Mdl
| type | field |
|---|---|
| String | unitCode |
| String | unitName |

### TxtHPriceList_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | priceListTypeTag |
| String | priceListTypeText |
| String | partyCode |
| String | refCustomerCode |

### TxtHParty_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | cityCode |
| String | cityName |

### ChangePriceList_Mdl
| type | field |
|---|---|
| String | voucherPrefix |
| String | priceListCode |
| String | priceListName |
| String | entryFromCode |
| String | entryFromName |
| String | applicableOnCode |
| String | applicableOnName |
| String | priceListTypeCode |
| String | priceListTypeName |
| String | siteSpecificCode |
| String | siteSpecificName |
| String | freightTypeCode |
| String | freightTypeName |
| String | applicableDate |
| String | bfFromCode |
| String | bfFromName |
| String | bfToCode |
| String | bfToName |
| String | gsmFromCode |
| String | gsmFromName |
| String | gsmToCode |
| String | gsmToName |
| String | actionTypeCode |
| String | actionTypeName |
| String | newPriceListCode |
| String | newPriceListName |
| String | newApplicableDate |
| String | itemCatCode |
| String | itemCatName |
| String | partyCode |
| String | partyName |
| String | changeTypeCode |
| String | changeTypeName |
| String | unitCode |
| String | unitName |
| String | rateAt |
| String | rateValue |

### ItemDetail_Mdl
| type | field |
|---|---|
| String | priceListCode |
| String | v_SNo |
| String | entryFrom |
| String | entryFromNature |
| String | site_code |
| String | subCode |
| String | refSubCode |
| String | itemCode |
| String | unitCode |
| String | applicableFrom |
| String | applicableTo |
| String | nature |
| String | fromBfCode |
| String | toBfCode |
| String | fromGsmCode |
| String | toGsmCode |
| String | rate |
| String | cmn_Addition |
| String | cmn_Deduction |
| String | estdLandedRate |
| String | printSno |
| String | transferDt |
| List<GrdDeduction_Mdl> | grdDeductionLst |

## RPC interface
| method | params | returns |
|---|---|---|
| getDefault |  | DmResult<DefaultValue_Mdl> |
| saveRecord | List<PriceListBase_Mdl> plMdlList, ChangePriceList_Mdl mrTemp | DmResult<String> |
| getPriceListDetail | String[] priceListCodeAry, ChangePriceList_Mdl mrTemp | DmResult<List<PriceListBase_Mdl>> |
| getTxtHEntryFrom | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHApplicableOn | String entryFrom,DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHFreightType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPriceListType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHPriceList | ChangePriceList_Mdl cplm, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHPriceList_Mdl>> |
| getTxtHActionType | String refCustomerCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHNewPriceList | String entryFrom, String priceListType, String applicableOn, String isSiteSpecific, String copyFromPriceListCode, String partyCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHBf | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGsm | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemCategory | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHParty | String entryFrom, String applicableOnCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHParty_Mdl>> |
| getTxtHChangeType | String actionType,DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHUnit | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getYesNo | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `ChangePriceList_Vld`
```java
public class ChangePriceList_Vld {
    public static void chkSave(ChangePriceList_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getEntryFromCode(), "Entry From", 100);
        DmShared.isValidText(mrTemp.getApplicableOnCode(), "Applicable On", 200);
        DmShared.isValidText(mrTemp.getPriceListTypeCode(), "Type", 300);
        DmShared.isValidText(mrTemp.getSiteSpecificCode(), "Site Specific", 400);
        DmShared.isValidText(mrTemp.getApplicableDate(), "Applicable Date", 500);
        DmShared.isValidText(mrTemp.getPriceListCode(), "Price List", 600);
        DmShared.isValidText(mrTemp.getActionTypeCode(), "Action Type", 700);
        DmShared.isValidText(mrTemp.getChangeTypeCode(), "Change Type", 800);
        if (!DmShared.xNull(mrTemp.getNewPriceListCode()).trim().equalsIgnoreCase("") && DmShared.xNull(mrTemp.getPriceListCode()).split(",").length > 1) {
            throw new DmException(600, "Multiple price list can not be copied to a New price list!");
        }
        if (!DmShared.xNull(mrTemp.getNewPriceListCode()).trim().equalsIgnoreCase("") && DmShared.xNull(mrTemp.getPriceListCode()).equalsIgnoreCase(DmShared.xNull(mrTemp.getNewPriceListCode()))) {
            throw new DmException(600, "New price list name and Price list name can not be same!");
        }
        if (DmShared.iNull(mrTemp.getActionTypeCode()).intValue() == 1) {
            if (DmShared.dNull(mrTemp.getNewApplicableDate()).trim().equalsIgnoreCase("")) {
                throw new DmException(900, "Please enter New Applicable Date!");
            }
        }
        if (DmShared.iNull(mrTemp.getChangeTypeCode()).intValue() != 3) {
            if (DmShared.vNull(mrTemp.getRateAt()).doubleValue() == 0 && DmShared.vNull(mrTemp.getRateValue()).doubleValue() == 0) {
                throw new DmException(1000, "Please enter Rate(%) Or Rate(Value)!");
            } else {
                if (DmShared.iNull(mrTemp.getChangeTypeCode()).intValue() == 2) {
                    if (DmShared.vNull(mrTemp.getRateAt()).doubleValue() >= 100) {
                        throw new DmException(1000, "Rate(%) can not be greater than or equal to 100%!");
                    }
                }
                if (DmShared.vNull(mrTemp.getRateValue()).doubleValue() >= 0) {
                    if (DmShared.xNull(mrTemp.getUnitCode()).trim().equalsIgnoreCase("")) {
                        throw new DmException(1100, "Unit is medatory!");
                    }
                }
            }
        }
    }
}
```

## Server `ChangePriceList_SrvImpl.java` (1899 lines)
Helpers used: `DmServer.addDate`, `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getParamList`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getDefault`
`public DmResult<DefaultValue_Mdl> getDefault()`

- SQL 1: `SELECT ec.finishedItemUnit AS unitCode, um.unit AS unitName FROM enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.finishedItemUnit`

### `saveRecord`
`public DmResult<String> saveRecord(List<PriceListBase_Mdl> plMdlList, ChangePriceList_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) cnt FROM subGroupPriceList sgpl WHERE sgpl.subCode = :partyCode AND sgpl.priceListCode = :priceListCode AND sgpl.applicableFrom = :applicableFrom`
- SQL 2: `SELECT COUNT(*) cnt FROM subGroupPriceList sgpl WHERE sgpl.subCode = :partyCode AND sgpl.applicableFrom = :applicableFrom`
- SQL 3: `SELECT applicableFrom FROM subGroupPriceList WHERE subCode = :partyCode ORDER BY applicableFrom desc OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY`
- SQL 4: `UPDATE subGroupPriceList SET applicableTo = :applicableTo WHERE applicableFrom = :applicableFrom AND subCode = :partyCode`
- SQL 5: `select applicableFrom,applicableTo FROM subGroupPriceList where applicableFrom > :applicableFrom and subCode = :partyCode group by applicableFrom,applicableTo order by applicableFrom asc offset 0 rows fetch next 1 rows only`
- SQL 6: `update subGroupPriceList set applicableTo = :applicableTo where applicableFrom = :applicableFrom and subCode = :partyCode`
- SQL 7: `select ISNULL(MAX(sgpl.sNo),0) + 1 AS sno from subgroupPriceList sgpl WHERE sgpl.subCode = :partyCode`
- SQL 8: `Insert into subGroupPriceList(subCode, sNo, priceListCode, applicableFrom, applicableTo) Values(:subCode, :sNo, :priceListCode, :applicableFrom, :applicableTo)`
- SQL 9: `UPDATE sg SET sg.priceListCode = sgpl.priceListCode, sg.u_EntDt = :uEntDt , sg.u_name = :u_name FROM subGroup sg LEFT JOIN subGroupPriceList sgpl ON sgpl.subCode = sg.subCode AND sgpl.applicableTo = :applicableTo where sg.subcode = :partyCode`
- SQL 10: `SELECT COUNT(*) cnt FROM priceList pl WHERE pl.entryFrom = :entryFrom AND pl.site_Code = :site_Code AND pl.priceListCode = :priceListCode AND pl.applicableFrom = :newApplicableDate`
- SQL 11: `UPDATE priceList SET rate = :rate , estdLandedRate = :estdLandedRate , cmn_Addition = :cmn_Addition , cmn_Deduction = :cmn_Deduction WHERE priceListcode = :priceListcode AND (v_SNo = :v_SNo)`
- Params: `:partyCode` ← `DmShared.xNull(partyPLMdl.getPartyCode())`; `:priceListCode` ← `DmShared.xNull(plBase_Mdl.getPriceListCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getNewApplicableDate()))`; `:partyCode` ← `DmShared.xNull(partyPLMdl.getPartyCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getNewApplicableDate()))`; `:partyCode` ← `DmShared.xNull(partyPLMdl.getPartyCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getNewApplicableDate()))`; `:partyCode` ← `DmShared.xNull(partyPLMdl.getPartyCode())`; `:applicableTo` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(mrTemp.getNewAppli`; `:applicableFrom` ← `rsTemp.getDate("ApplicableFrom")`; `:partyCode` ← `DmShared.xNull(partyPLMdl.getPartyCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getNewApplicableDate()))`; `:partyCode` ← `DmShared.xNull(partyPLMdl.getPartyCode())`; `:applicableFrom` ← `DmServer.convertDate(maxApplicableDate)`; `:applicableTo` ← `DmServer.convertDate(DmServer.addDate(Calendar.DATE, -1, DmShared.dNull(mrTemp.getNewAppli`; `:partyCode` ← `DmShared.xNull(partyPLMdl.getPartyCode())`; `:partyCode` ← `DmShared.xNull(partyPLMdl.getPartyCode())`; `:subCode` ← `DmShared.xNull(partyPLMdl.getPartyCode())`; `:sNo` ← `DmShared.iNull(subGroupPriceListSNo).intValue()`; `:priceListCode` ← `DmShared.foriegnKey(plBase_Mdl.getPriceListCode())`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getNewApplicableDate()))`; `:applicableTo` ← `DmServer.convertDate(applicableTo)`; `:applicableTo` ← `DmServer.convertDate("31-Dec-9999")`; `:partyCode` ← `DmShared.xNull(partyPLMdl.getPartyCode())`; `:u_name` ← `DmServer.getSsnUserName(this)`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:newApplicableDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getNewApplicableDate()))`; `:entryFrom` ← `STEnum.PriceListEntryFrom.PRICELIST_MASTER.getCode()`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(this))`; `:priceListCode` ← `DmShared.xNull(mrTemp.getNewPriceListCode())`; `:priceListCode` ← `DmShared.xNull(plBase_Mdl.getPriceListCode())`; `:v_SNo` ← `DmShared.iNull(plRecord.getV_SNo()).intValue()`; `:priceListcode` ← `DmShared.xNull(plRecord.getPriceListCode())`; `:rate` ← `DmServer.vNull(plRecord.getRate(), "0.00")`; `:estdLandedRate` ← `DmServer.vNull(plRecord.getEstdLandedRate(), "0.00")`; `:cmn_Addition` ← `DmServer.vNull(plRecord.getAdditionAmount(), "0.00")`; `:cmn_Deduction` ← `DmServer.vNull(plRecord.getDeductionAmount(), "0.00")`
- Errors: "Party price list already exists for selected period." | "Invalid key." | "price list already exists for selected period."
- Other calls: `PARTY_PRICELIST.getCode`, `PRICELIST_MASTER.getCode`, `PriceListEntryFrom.getViaCode`, `PriceList_Server.deletePriceList`, `PriceList_Server.savePriceList_ExistingFunction`

### `getPriceListDetail`
`public DmResult<List<PriceListBase_Mdl>> getPriceListDetail(String[] priceListCodeAry, ChangePriceList_Mdl mrTemp)`

- SQL 1: `SELECT COUNT(*) cnt FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode WHERE pl.refSubCode IS NOT NULL AND pl.entryFrom = :entryFrom AND pl.priceListCode = :priceListCode AND CASE WHEN pl.site_code = :site_code THEN 1 ELSE CASE WHEN plm.isSiteSpecific = 1 THEN 0 ELSE 1 END END = 1`
- SQL 2: `SELECT COUNT(*) cnt FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode WHERE pl.refSubCode IS NOT NULL AND pl.entryFrom = :entryFrom AND pl.applicableTo <> :applicableTo AND pl.priceListCode = :priceListCode AND CASE WHEN pl.site_code = :site_code THEN 1 ELSE CASE WHEN plm.isSiteSpecific = 1 THEN 0 ELSE 1 END END = 1`
- SQL 3: `SELECT COUNT(*) cnt FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode LEFT JOIN itemMast im ON im.code = pl.itemCode WHERE 1=1 {…} HAVING 1=1 {…}`
- SQL 4: `SELECT COUNT(*) cnt FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode LEFT JOIN itemMast im ON im.code = pl.itemCode WHERE 1=1 {…} HAVING 1=1 {…}`
- SQL 5: `SELECT COUNT(*) cnt FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode LEFT JOIN itemMast im ON im.code = pl.itemCode WHERE pl.rate <= :rate {…} HAVING 1=1 {…}`
- Params: `:entryFrom` ← `DmShared.xNull(DmShared.xNull(mrTemp.getEntryFromCode()))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:priceListCode` ← `DmShared.xNull(mrTemp.getNewPriceListCode())`; `:entryFrom` ← `DmShared.xNull(DmShared.xNull(mrTemp.getEntryFromCode()))`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:applicableTo` ← `DmServer.convertDate(DmShared.dNull("31-Dec-9999"))`; `:priceListCode` ← `DmShared.xNull(mrTemp.getPriceListCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:entryFrom` ← `DmShared.xNull(DmShared.xNull(mrTemp.getEntryFromCode()))`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:unitCode` ← `DmShared.xNull(DmShared.xNull(mrTemp.getUnitCode()))`; `:fromBfCode` ← `DmShared.xNull(mrTemp.getBfFromCode())`; `:toBfCode` ← `DmShared.xNull(mrTemp.getBfToCode())`; `:fromGsmCode` ← `DmShared.xNull(mrTemp.getGsmFromCode())`; `:toGsmCode` ← `DmShared.xNull(mrTemp.getGsmToCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:entryFrom` ← `DmShared.xNull(DmShared.xNull(mrTemp.getEntryFromCode()))`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:unitCode` ← `DmShared.xNull(DmShared.xNull(mrTemp.getUnitCode()))`; `:fromBfCode` ← `DmShared.xNull(mrTemp.getBfFromCode())`; `:toBfCode` ← `DmShared.xNull(mrTemp.getBfToCode())`; `:fromGsmCode` ← `DmShared.xNull(mrTemp.getGsmFromCode())`; `:toGsmCode` ← `DmShared.xNull(mrTemp.getGsmToCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:entryFrom` ← `DmShared.xNull(DmShared.xNull(mrTemp.getEntryFromCode()))`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:unitCode` ← `DmShared.xNull(DmShared.xNull(mrTemp.getUnitCode()))`; `:fromBfCode` ← `DmShared.xNull(mrTemp.getBfFromCode())`; `:toBfCode` ← `DmShared.xNull(mrTemp.getBfToCode())`; `:fromGsmCode` ← `DmShared.xNull(mrTemp.getGsmFromCode())`; `:toGsmCode` ← `DmShared.xNull(mrTemp.getGsmToCode())`; `:rate` ← `DmServer.vNull(mrTemp.getRateValue())`
- Errors: "Price list already exists!" | "Price list is closed!" | "Price list can not be copied if it contains different item units!" | "No record found!" | "Operation can not be performed, Some items contain rate less than or equal to 'Rate(Value)'!"
- Other calls: `BF.getId`, `BOTH.getId`, `ChangePriceList_Vld.chkSave`, `GSM.getId`, `PARTY_PRICELIST.getCode`, `PRICELIST_MASTER.getCode`, `PURCHASE.getId`

### `getPriceListDetail`
`private List<PartyPriceList_Mdl> getPriceListDetail(String priceListCode, ChangePriceList_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `WITH ctePriceList AS ( SELECT pl.subCode FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode LEFT JOIN itemMast im ON im.code = pl.itemCode WHERE 1=1 {…} GROUP BY pl.subCode ) SELECT STRING_AGG(CONVERT(NVARCHAR(max),cpl.subCode), ',') AS csv FROM ctePriceList cpl`
- SQL 2: `WITH ctePriceList AS ( SELECT pl.priceListCode, MIN(pl.fromBfCode) fromBfCode, MAX(pl.toBfCode) toBfCode , MIN(pl.fromGsmCode) fromGsmCode, MAX(pl.toGsmCode) toGsmCode FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode LEFT JOIN itemMast im ON im.code = pl.itemCode WHERE 1=1 {…} GROUP BY pl.priceListCode HAVING COUNT(*) > 0 {…} ) SELECT pl.priceListCode , pl.v_SNo, pl.entryFrom, pl.entryFromNature, pl.site_code, pl.subCode, pl.refSubCode, pl.itemCode, pl.unitCode , pl.applicableFrom, pl.applicableTo, pl.nature, pl.fromBfCode, pl.toBfCode, pl.fromGsmCode, pl.toGsmCode , pl.rate, pl.cmn_Addition, pl.cmn_Deduction, pl.estdLandedRate, pl.printSno, pl.transferDt FROM priceList pl LEFT JOIN priceListMast plm ON plm.code = pl.priceListCode LEFT JOIN ctePriceList cpl ON cpl.priceListCode = pl.priceListCode {…} LEFT JOIN itemMast im ON im.code = pl.itemCode WHERE cpl.priceListCode IS NOT NULL {…}`
- Params: `:newPLCode` ← `mrTemp.getNewPriceListCode()`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:entryFrom` ← `DmShared.xNull(DmShared.xNull(mrTemp.getEntryFromCode()))`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:unitCode` ← `DmShared.xNull(DmShared.xNull(mrTemp.getUnitCode()))`; `:fromBfCode` ← `DmShared.xNull(mrTemp.getBfFromCode())`; `:toBfCode` ← `DmShared.xNull(mrTemp.getBfToCode())`; `:fromGsmCode` ← `DmShared.xNull(mrTemp.getGsmFromCode())`; `:toGsmCode` ← `DmShared.xNull(mrTemp.getGsmToCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:priceListCode` ← `DmShared.xNull(priceListCode)`; `:entryFrom` ← `DmShared.xNull(DmShared.xNull(mrTemp.getEntryFromCode()))`; `:applicableFrom` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:unitCode` ← `DmShared.xNull(DmShared.xNull(mrTemp.getUnitCode()))`; `:fromBfCode` ← `DmShared.xNull(mrTemp.getBfFromCode())`; `:toBfCode` ← `DmShared.xNull(mrTemp.getBfToCode())`; `:fromGsmCode` ← `DmShared.xNull(mrTemp.getGsmFromCode())`; `:toGsmCode` ← `DmShared.xNull(mrTemp.getGsmToCode())`; `:partyCode` ← `DmShared.xNull(partyCode)`
- Errors: "Calculated rate can not be less than or equal to zero."
- Other calls: `BF.getId`, `BOTH.getId`, `GSM.getId`, `PARTY_PRICELIST.getCode`, `PURCHASE.getId`, `PriceList_Server.getGridOhDeductionDetails`

### `managePriceListGUI`
`private void managePriceListGUI(String copiedPriceListCode, ChangePriceList_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `SELECT gplh.docId, gplh.guiConfigCode, gplh.v_Type, gplh.v_Date , gplh.entryFrom, gplh.subCode,gplh.priceListCode FROM guipriceListHead gplh WHERE 1=1 {…}`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:entryFrom` ← `DmShared.xNull(DmShared.xNull(mrTemp.getEntryFromCode()))`; `:applicableDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:priceListCode` ← `DmShared.xNull(copiedPriceListCode)`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:entryFrom` ← `DmShared.xNull(DmShared.xNull(mrTemp.getEntryFromCode()))`; `:applicableDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getNewApplicableDate()))`; `:priceListCode` ← `DmShared.xNull(mrTemp.getNewPriceListCode())`
- Other calls: `DmGUI_Server.deleteViaConfigCode`, `DmGUI_Server.getGUIViaConfigCode`, `NONE.getCode`, `PriceList_Server.savePriceListGUI`

### `convertPriceListMdl`
`private List<ItemPriceList_Mdl> convertPriceListMdl(List<ItemDetail_Mdl> plDetail_Mdls, boolean ignoreV_SNo)`

- Other calls: `NONE.getCode`, `PriceListEntryFrom.getViaCode`

### `getTxtHEntryFrom`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHEntryFrom(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PriceListEntryFrom.values`

### `getTxtHApplicableOn`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHApplicableOn(String entryFrom, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PriceListApplicableOn.values`, `PriceListEntryFrom.getViaCode`

### `getTxtHFreightType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHFreightType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `FreightType.values`

### `getTxtHPriceListType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPriceListType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `PriceListType.values`

### `getTxtHPriceList`
`public DmResultPageing<List<TxtHPriceList_Mdl>> getTxtHPriceList(ChangePriceList_Mdl mrTemp, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT plm.code, plm.name as priceListName , plm.priceListType as priceListTypeTag , plm.subCode, plm.refSubCode , CASE WHEN plm.priceListType = :bfCode THEN :bgName WHEN plm.priceListType = :gsmCode THEN :gsmName WHEN plm.priceListType = :bothCode THEN :bothName WHEN plm.priceListType = :noneCode THEN :noneName ELSE '' END AS priceTypeName FROM priceListMast plm LEFT JOIN ( SELECT pl.priceListCode, MAX(pl.freightTypeCode) AS freightTypeCode FROM priceList pl WHERE pl.applicableFrom = :applicableDate AND pl.entryFrom = :entryFrom AND pl.entryFromNature = :entryFromNature GROUP BY pl.priceListCode ) AS plV ON plV.priceListCode = plm.code WHERE plV.priceListCode IS NOT NULL AND plm.applicableOn = :applicableOn AND plm.priceListType = :priceListType AND plV.freightTypeCode = :freightTypeCode AND plm.isSiteSpecific = :isSiteSpecific {…}`
- Params: `:bgName` ← `STEnum.PriceListType.BF.getValue()`; `:gsmName` ← `STEnum.PriceListType.GSM.getValue()`; `:bothName` ← `STEnum.PriceListType.BOTH.getValue()`; `:noneName` ← `STEnum.PriceListType.NONE.getValue()`; `:entryFrom` ← `DmShared.xNull(mrTemp.getEntryFromCode())`; `:applicableOn` ← `DmShared.xNull(mrTemp.getApplicableOnCode())`; `:priceListType` ← `DmShared.xNull(mrTemp.getPriceListTypeCode())`; `:isSiteSpecific` ← `DmShared.xNull(mrTemp.getSiteSpecificCode())`; `:freightTypeCode` ← `DmShared.xNull(mrTemp.getFreightTypeCode())`; `:applicableDate` ← `DmServer.convertDate(DmShared.dNull(mrTemp.getApplicableDate()))`; `:one` ← `"2"`
- Other calls: `BF.getId`, `BF.getValue`, `BOTH.getId`, `BOTH.getValue`, `GSM.getId`, `GSM.getValue`, `NONE.getCode`, `NONE.getId`, `NONE.getValue`

### `getTxtHNewPriceList`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNewPriceList(String entryFrom, String priceListType, String applicableOn, String isSiteSpecific, String copyFromPriceListCode, String partyCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT plm.code, plm.name FROM priceListMast plm {…} WHERE plm.priceListType = :priceListType AND plm.applicableOn = :applicableOn AND plm.isSiteSpecific = :isSiteSpecific {…}`
- Params: `:priceListType` ← `DmShared.xNull(priceListType)`; `:applicableOn` ← `DmShared.xNull(applicableOn)`; `:isSiteSpecific` ← `DmShared.xNull(isSiteSpecific)`; `:subCode` ← `DmShared.xNull(partyCode)`; `:copyFromPriceListCode` ← `DmShared.xNull(copyFromPriceListCode)`
- Other calls: `PARTY_PRICELIST.getCode`, `PRICELIST_MASTER.getCode`, `PURCHASE.getId`

### `getTxtHActionType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHActionType(String refCustomerCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Update Price List`

### `getTxtHBf`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHBf(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT bf.code, bf.code as name FROM bfMast bf where bf.isActive = 1`

### `getTxtHGsm`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGsm(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT gm.code, gm.code as name FROM gsmMast gm where gm.isActive = 1`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ic.code , ic.name FROM itemcat ic`

### `getTxtHChangeType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHChangeType(String actionType, DmHelper helper, boolean blnToEndOfFile)`


### `getTxtHParty`
`public DmResultPageing<List<TxtHParty_Mdl>> getTxtHParty(String entryFrom, String applicableOnCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code , sg.name as supplierName , sg.manualCode , c.cityName , sg.cityCode FROM subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.code IN ( SELECT acf.Nature FROM AcFilteration acf {…} ) AND sg.isActive = :one AND sgsl.site_Code = :site_Code`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:PO` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_ORDER, "cate`; `:POU` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_ORDER_UNREGI`; `:PB` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL, "categ`; `:PBU` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL_UNREGIS`; `:PBD` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL_DIRECT,`; `:PBDU` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().PURCHASE_BILL_DIRECT_`; `:SOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().SALES_ORDER, "categor`; `:SOOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().GENERAL_SALES_ORDER, `; `:SBV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().SALES_INVOICE, "categ`; `:SBOV` ← `DmShared.xNull(DmServer.getCodeViaManualCode(new DmVoucherCategory().GENERAL_SALES_INVOICE`
- Other calls: `PARTY_PRICELIST.getCode`, `PriceListApplicableOn.getViaKey`

### `getTxtHUnit`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHUnit(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT um.code, um.unit AS name FROM unitMast um`

### `getYesNo`
`public DmResultPageing<List<KeyFieldExtended>> getYesNo(DmHelper helper, boolean blnToEndOfFile)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmGUI.server`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.priceList.client`, `paperbiz.selfToolkit.priceList.server`, `paperbiz.selfToolkit.priceList.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Party price list already exists for selected period." |
| saveRecord | `if (DmShared.iNull(subGroupPriceListSNo).intValue() == 0)` | "Invalid key." |
| saveRecord | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "price list already exists for selected period." |
| getPriceListDetail | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Price list already exists!" |
| getPriceListDetail | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Price list is closed!" |
| getPriceListDetail | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Price list can not be copied if it contains different item units!" |
| getPriceListDetail | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() == 0)` | "No record found!" |
| getPriceListDetail | `if (DmShared.iNull(rsTemp.getString("cnt")).intValue() > 0)` | "Operation can not be performed, Some items contain rate less than or equal to 'Rate(Value)'!" |
| getPriceListDetail | `if (rate <= 0)` | "Calculated rate can not be less than or equal to zero." |

## Notes (hand-written)
_No hand-written notes yet._
