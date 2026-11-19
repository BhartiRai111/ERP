# pb-master / masterSitePermission

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/master/src/masterSitePermission/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemSiteList, subGroupSiteList, assetSiteList, shiftSiteList
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Site (Text); Master Nature (Text); Master Name (Text); Shift Name (Text); rule: Please select data for site assign/unassign.
- Lookups/suggestions: getTxtHSite, getTxtHMasterNature, getTxtHMasterName, getTxtHItemType, getTxtHItemCategory, getTxtHItemGroup, getTxtHItemName, getTxtHAccountGroup, getTxtHNature, getTxtHAccountName, getTxtHShiftName, getTxtHAssetNature, getTxtHAssetItem, getTxtHAssetGroup, getTxtHAssetCategory, getTxtHLedgerGroup
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `MasterSitePermission`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Site | DmTextHelpMulti | `txtHSite` | `KeyField_PrpAcs, 10, true` |
| 2 | Master Nature | DmTextHelp | `txtHMasterNature` | `KeyField_PrpAcs, 7, true` |
| 3 | Master Name | DmTextHelp | `txtHMasterName` | `KeyField_PrpAcs, 7, true` |
| 4 | Type | DmTextHelpMulti | `txtHItemType` | `TxtHItemType_PrpAcs, 6, false` |
| 5 | Category | DmTextHelpMulti | `txtHItemCat` | `KeyField_PrpAcs, 7, false` |
| 6 | Group | DmTextHelpMulti | `txtHItemGroup` | `KeyField_PrpAcs, 7, false` |
| 7 | - | DmTextHelpMulti | `txtHItemName` | `GridItem_PrpAcs, 10, false` |
| 8 | Nature | DmTextHelpMulti | `txtHNature` | `com.google.gwt.core.client.KeyField_PrpAcs, 10, false` |
| 9 | Account Group | DmTextHelpMulti | `txtHAccountGroup` | `TxtHAccountGroup, 6, false` |
| 10 | Ledger Group | DmTextHelpMulti | `txtHLedgerGroup` | `com.google.gwt.core.client.KeyField_PrpAcs, 10, false` |
| 11 | Name | DmTextHelpMulti | `txtHAccountName` | `TxtHAccountName_PrpAcs, 7, false` |
| 12 | Asset Category | DmTextHelpMulti | `txtHAssetCategory` | `KeyField_PrpAcs, 7, false` |
| 13 | Asset Group | DmTextHelpMulti | `txtHAssetGroup` | `KeyField_PrpAcs, 7, false` |
| 14 | Asset Item | DmTextHelpMulti | `txtHAssetItem` | `KeyField_PrpAcs, 7, false` |
| 15 | Shift Name | DmTextHelpMulti | `txtHShiftName` | `KeyField_PrpAcs, 7, true` |

**Grid / popup columns** (header, width, db field):
- `txtHSite`: Name [Text, w300, Name]
- `txtHMasterNature`: Name [Text, w0, Name]
- `txtHMasterName`: Name [Text, w0, Name]
- `txtHItemType`: Name [Text, w150, name]; Short Name [Text, w70, manualcode]
- `txtHItemCat`: Name [Text, w175, categoryName]
- `txtHItemGroup`: Name [Text, w150, Name]
- `txtHItemName`: Name [Text, w150, Name]; Manual Code [Text, w100, manualCode]; Type [Text, w100, itemType]
- `txtHNature`: Name [Text, w150, Nature]
- `txtHAccountGroup`: Name [Text, w150, GroupName]; Nature [Text, w135, NatureName]
- `txtHLedgerGroup`: Name [Text, w150, Name]
- `txtHAccountName`: Name [Text, w200, name]; Manual Code [Text, w100, manualCode]; Group Name [Text, w200, GroupName]; Nature [Text, w100, Nature]
- `txtHAssetGroup`: Name [Text, w150, Name]
- `txtHAssetCategory`: Name [Text, w150, Name]
- `txtHAssetItem`: Name [Text, w275, Name]
- `txtHShiftName`: Name [Text, w150, Name]

**Lookup / suggestion / find sources (server RPC called):**
- `txtHMasterNature` → `getTxtHMasterNature`
- `txtHMasterName` → `getTxtHMasterName`

**Save: model field ← widget** (`topControlSaveInitialize`):
- `SharedKeyFieldCode` ← DmShared.xNull(txtHSite.getTag())
- `SharedKeyFieldName` ← DmShared.xNull(txtHSite.getText())
- `MasterNatureTag` ← DmShared.xNull(txtHMasterNature.getTag())
- `MasterNatureText` ← DmShared.xNull(txtHMasterNature.getText())
- `MasterNameTag` ← DmShared.xNull(txtHMasterName.getTag())
- `MasterNameText` ← DmShared.xNull(txtHMasterName.getText())
- `FilterItemType` ← DmShared.xNull(txtHMasterName.getTag())
- `ItemTypeTag` ← DmShared.xNull(txtHItemType.getTag())
- `ItemTypeText` ← DmShared.xNull(txtHItemType.getText())
- `ItemCatTag` ← DmShared.xNull(txtHItemCat.getTag())
- `ItemCatText` ← DmShared.xNull(txtHItemCat.getText())
- `ItemGroupTag` ← DmShared.xNull(txtHItemGroup.getTag())
- `ItemGroupText` ← DmShared.xNull(txtHItemGroup.getText())
- `ItemNameTag` ← DmShared.xNull(txtHItemName.getTag())
- `ItemNameText` ← DmShared.xNull(txtHItemName.getText())
- `FilterSubgroupNature` ← DmShared.xNull(txtHMasterName.getTag())
- `AccountGroupTag` ← DmShared.xNull(txtHAccountGroup.getTag())
- `AccountGroupText` ← DmShared.xNull(txtHAccountGroup.getText())
- `LedgerGroupTag` ← DmShared.xNull(txtHLedgerGroup.getTag())
- `LedgerGroupText` ← DmShared.xNull(txtHLedgerGroup.getText())
- `AccountNatureTag` ← DmShared.xNull(txtHNature.getTag())
- `AccountNatureText` ← DmShared.xNull(txtHNature.getText())
- `AccountNameTag` ← DmShared.xNull(txtHAccountName.getTag())
- `AccountNameText` ← DmShared.xNull(txtHAccountName.getText())
- `AssetCatTag` ← DmShared.xNull(txtHAssetCategory.getTag())
- `AssetCatText` ← DmShared.xNull(txtHAssetCategory.getText())
- `AssetGroupTag` ← DmShared.xNull(txtHAssetGroup.getTag())
- `AssetGroupText` ← DmShared.xNull(txtHAssetGroup.getText())
- `AssetItemTag` ← DmShared.xNull(txtHAssetItem.getTag())
- `AssetItemText` ← DmShared.xNull(txtHAssetItem.getText())
- `ShiftTag` ← DmShared.xNull(txtHShiftName.getTag())
- `ShiftText` ← DmShared.xNull(txtHShiftName.getText())

**Enable/disable rules** (`manageDisplay(flag)`, flag = editing mode):
- `txtHMasterNature.setEnabled(true)`
- `txtHMasterName.setEnabled(true)`
- `txtHMasterName.setEnabled(false)`

**`manageDisplay()` logic (condensed code):**
```java
waContainer.clear();
if (!DmShared.xNull(txtHSite.getText()).trim().equalsIgnoreCase("")) {
txtHMasterNature.setEnabled(true);
if (txtHMasterNature.isEnabled() && !DmShared.xNull(txtHMasterNature.getText()).equalsIgnoreCase("")) {
txtHMasterName.setEnabled(true);
} else {
txtHMasterName.setText("");
txtHMasterName.setTag("");
txtHMasterName.setEnabled(false);
if (!DmShared.xNull(txtHSite.getText()).trim().equalsIgnoreCase("")
&& !DmShared.xNull(txtHMasterNature.getText()).trim().equalsIgnoreCase("")
&& !DmShared.xNull(txtHMasterName.getText()).trim().equalsIgnoreCase("")) {
loadWAContainer(txtHMasterNature.getText());
```

**Event handlers (condensed code, CRUD flow and field callbacks):**

`txtHSite.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHSite(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyFieldE
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHSite).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHSite.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHSite).show();
```

`txtHSite.addTextHelpMultiCallBack` → `getSelection()`
```java
manageDisplay();
```

`txtHMasterNature.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHMasterNature(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<K
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMasterNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMasterNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMasterNature).show();
```

`txtHMasterNature.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMasterNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMasterNature).show();
```

`txtHMasterNature.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.xNull(txtHMasterNature.getTag2nd()).trim().equalsIgnoreCase("")
|| !DmShared.xNull(txtHMasterNature.getTag2nd()).equalsIgnoreCase(txtHMasterNature.getTag())) {
clearWAContainer();
txtHMasterNature.setTag2nd(DmShared.xNull(txtHMasterNature.getTag()));
txtHMasterName.setText("");
txtHMasterName.setTag("");
txtHMasterName.setTag2nd("");
manageDisplay();
```

`txtHMasterName.addTextHelpCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHMasterName(DmShared.xNull(txtHMasterNature.getText()), helper, blnToEndOfFile
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHMasterName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHMasterName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMasterName).show();
```

`txtHMasterName.addTextHelpCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHMasterName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHMasterName).show();
```

`txtHMasterName.addTextHelpCallBack` → `getSelectedRow()`
```java
if (DmShared.xNull(txtHMasterName.getTag2nd()).trim().equalsIgnoreCase("")
|| !DmShared.xNull(txtHMasterName.getTag2nd()).equalsIgnoreCase(txtHMasterName.getTag())) {
clearWAContainer();
txtHMasterName.setTag2nd(DmShared.xNull(txtHMasterName.getTag()));
manageDisplay();
```

`txtHItemType.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemType(DmShared.xNull(txtHMasterName.getTag()), helper, blnToEndOfFile, new
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemType).show();
public void onSuccess(DmResultPageing<List<TxtHItemType_Mdl>> result) {
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemType.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemType.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemType).show();
```

`txtHItemGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemGroup(DmShared.xNull(txtHMasterName.getTag()), txtHItemType.getTag(), hel
new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
);
...
```

`txtHItemGroup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemGroup).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemName(DmShared.xNull(txtHMasterName.getTag()), DmShared.xNull(txtHItemType
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemName).show();
public void onSuccess(DmResultPageing<List<TxtHItem_Mdl>> result) {
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemName).show();
```

`txtHItemCat.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHItemCategory(DmShared.xNull(txtHMasterName.getTag()), txtHItemType.getTag(), 
new AsyncCallback<DmResultPageing<List<KeyFieldExtended>>>() {
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHItemCat).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHItemCat.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCat).show();
);
...
```

`txtHItemCat.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHItemCat.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHItemCat).show();
```

`txtHNature.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHNature(DmShared.xNull(txtHMasterName.getTag()), helper, blnToEndOfFile, new A
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHNature).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHNature.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHNature.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHNature).show();
```

`txtHLedgerGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHLedgerGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Ke
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHLedgerGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHLedgerGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLedgerGroup).show();
```

`txtHLedgerGroup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHLedgerGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHLedgerGroup).show();
```

`txtHAccountGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountGroup(DmShared.xNull(txtHMasterName.getTag()), helper, blnToEndOfFile,
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountGroup).show();
public void onSuccess(DmResultPageing<List<TxtHAccountGroup_Mdl>> result) {
if (result.isStatus()) {
txtHAccountGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountGroup).show();
```

`txtHAccountGroup.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountGroup).show();
```

`txtHAccountName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAccountName(DmShared.xNull(txtHMasterName.getTag()), DmShared.xNull(txtHNatur
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAccountName).show();
public void onSuccess(DmResultPageing<List<TxtHAccountName_Mdl>> result) {
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountName).show();
```

`txtHAccountName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAccountName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAccountName).show();
```

`txtHShiftName.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHShiftName(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<KeyF
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHShiftName).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHShiftName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShiftName).show();
```

`txtHShiftName.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHShiftName.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHShiftName).show();
```

`txtHAssetCategory.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAssetCategory(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetCategory).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetCategory).show();
```

`txtHAssetCategory.addTextHelpMultiCallBack` → `onSuccess()`
```java
if (result.isStatus()) {
txtHAssetCategory.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetCategory).show();
```

`txtHAssetGroup.addTextHelpMultiCallBack` → `populateData()`
```java
srvAsync.invokeRPC(blnCallBackWait).getTxtHAssetGroup(helper, blnToEndOfFile, new AsyncCallback<DmResultPageing<List<Key
public void onFailure(Throwable caught) {
new DmAlertMessageBox(caught.getMessage(), txtHAssetGroup).show();
public void onSuccess(DmResultPageing<List<KeyFieldExtended>> result) {
if (result.isStatus()) {
txtHAssetGroup.setData(result.getData(), result.getPaging());
} else {
new DmAlertMessageBox(result.getErrorMessage(), txtHAssetGroup).show();
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/MasterSitePermission.java | 760 |
| client/MasterSitePermission_Dsg.java | 726 |
| server/MasterSitePermission_SrvImpl.java | 1442 |
| shared/MSPEnum.java | 38 |
| shared/MasterSitePermission_Mdl.java | 290 |
| shared/MasterSitePermission_Srv.java | 48 |
| shared/MasterSitePermission_SrvAsync.java | 48 |
| shared/MasterSitePermission_Vld.java | 40 |
| shared/TxtHAccountGroup_Mdl.java | 34 |
| shared/TxtHAccountName_Mdl.java | 41 |
| shared/TxtHItemType_Mdl.java | 24 |
| shared/TxtHItem_Mdl.java | 69 |

## UI labels / columns (client)
`Item Name`, `Site`, `Master Nature`, `Master Name`, `Type`, `Category`, `Group`, `Nature`, `Account Group`, `Ledger Group`, `Name`, `Asset Category`, `Asset Group`, `Asset Item`, `Shift Name`

## Models (shared DTOs)
### TxtHItemType_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | shortname |

### TxtHItem_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | itemType |
| String | itemGroup |
| String | itemCategory |
| String | unit |
| String | unitCode |
| String | manualCode |

### MasterSitePermission_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | masterNatureTag |
| String | masterNatureText |
| String | masterNameTag |
| String | masterNameText |
| String | filterItemType |
| String | itemTypeTag |
| String | itemTypeText |
| String | itemCatTag |
| String | itemCatText |
| String | itemGroupTag |
| String | itemGroupText |
| String | itemNameTag |
| String | itemNameText |
| String | filterSubgroupNature |
| String | accountGroupTag |
| String | accountGroupText |
| String | ledgerGroupTag |
| String | ledgerGroupText |
| String | accountNatureTag |
| String | accountNatureText |
| String | accountNameTag |
| String | accountNameText |
| String | assetCatTag |
| String | assetCatText |
| String | assetGroupTag |
| String | assetGroupText |
| String | assetItemTag |
| String | assetItemText |
| String | shiftTag |
| String | shiftText |

### TxtHAccountName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | groupName |
| String | nature |

### TxtHAccountGroup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | natureCode |
| String | natureName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHSite | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHMasterNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemType | String filterItemtCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItemType_Mdl>> |
| getTxtHItemGroup | String masterName, String itemTypeCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHItemName | String masterName, String itemTypeCode, String itemCat, String itemGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHItem_Mdl>> |
| getTxtHItemCategory | String masterName, String itemType, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| saveRecord | MasterSitePermission_Mdl mrTemp, boolean assign | DmResult<String> |
| getTxtHAccountGroup | String masterName, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountGroup_Mdl>> |
| getTxtHNature | String masterName, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAccountName | String masterName, String natureTypeCode, String accountGroup, String ledgerGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHAccountName_Mdl>> |
| getTxtHMasterName | String masterNature, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHShiftName | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetNature | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetItem | String assetCat, String assetGroup, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAssetCategory | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHLedgerGroup | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `MasterSitePermission_Vld`
```java
public class MasterSitePermission_Vld {
    public static void chkSave(MasterSitePermission_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getSharedKeyFieldName(), "Site", 100);
        DmShared.isValidText(mrTemp.getMasterNatureTag(), "Master Nature", 200);
        DmShared.isValidText(mrTemp.getMasterNameTag(), "Master Name", 300);
        if (mrTemp.getMasterNatureTag().equalsIgnoreCase(MSPEnum.MasterNature.ITEM.getValue())) {
            if (DmShared.xNull(mrTemp.getItemTypeTag()).trim().equalsIgnoreCase("")
                    && DmShared.xNull(mrTemp.getItemCatTag()).trim().equalsIgnoreCase("")
                    && DmShared.xNull(mrTemp.getItemGroupTag()).trim().equalsIgnoreCase("")
                    && DmShared.xNull(mrTemp.getItemNameTag()).trim().equalsIgnoreCase("")) {
                throw new DmException(400, "Please select data for site assign/unassign.");
            }
        } else if (mrTemp.getMasterNatureTag().equalsIgnoreCase(MSPEnum.MasterNature.ACCOUNT.getValue())) {
            if (DmShared.xNull(mrTemp.getAccountNatureTag()).trim().equalsIgnoreCase("")
                    && DmShared.xNull(mrTemp.getAccountGroupTag()).trim().equalsIgnoreCase("")
                    && DmShared.xNull(mrTemp.getAssetCatTag()).trim().equalsIgnoreCase("")
                    && DmShared.xNull(mrTemp.getAccountNameTag()).trim().equalsIgnoreCase("")) {
                throw new DmException(500, "Please select data for site assign/unassign.");
            }
        } else if (mrTemp.getMasterNatureTag().equalsIgnoreCase(MSPEnum.MasterNature.ASSET.getValue())) {
            if (DmShared.xNull(mrTemp.getAssetCatTag()).trim().equalsIgnoreCase("")
                    && DmShared.xNull(mrTemp.getAssetGroupTag()).trim().equalsIgnoreCase("")
                    && DmShared.xNull(mrTemp.getAssetItemTag()).trim().equalsIgnoreCase("")) {
                throw new DmException(600, "Please select data for site assign/unassign.");
            }
        } else if (mrTemp.getMasterNatureTag().equalsIgnoreCase(MSPEnum.MasterNature.SHIFT.getValue())) {
            DmShared.isValidText(mrTemp.getShiftTag(), "Shift Name", 700);
        }
    }
}
```

## Server `MasterSitePermission_SrvImpl.java` (1442 lines)
Helpers used: `DmServer.convertToJson`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getParamList`, `DmServer.getSsnDBName`, `DmShared.filterKeyFieldList`, `DmShared.iNull`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(MasterSitePermission_Mdl mrTemp, boolean isAssign)`

- Other calls: `ACCOUNT.getValue`, `ASSET.getValue`, `ITEM.getValue`, `MasterSitePermission_Vld.chkSave`, `SHIFT.getValue`

### `saveRecordItem`
`private String saveRecordItem(MasterSitePermission_Mdl mrTemp, boolean isAssign, Connection cnTemp) throws Exception`

- SQL 1: `DELETE isl FROM itemSiteList isl LEFT JOIN itemMast im ON im.code = isl.code WHERE isl.site_Code IN ( {…} :site_code {…} ) {…}`
- SQL 2: `INSERT INTO itemSiteList (code, site_Code) (SELECT im.code, sm.code FROM itemMast im, siteMast sm WHERE sm.code IN ( {…} :site_code {…} ) {…} )`

### `saveRecordAccount`
`private String saveRecordAccount(MasterSitePermission_Mdl mrTemp, boolean isAssign, Connection cnTemp) throws Exception`

- SQL 1: `DELETE sgsl FROM subGroupSiteList sgsl LEFT JOIN subGroup sg ON sg.subCode = sgsl.subCode WHERE sgsl.site_Code IN ( {…} :site_code {…} ) {…}`
- SQL 2: `INSERT INTO subGroupSiteList (subCode, site_Code) (SELECT sg.subCode, sm.code FROM subgroup sg, siteMast sm WHERE sm.code IN ( {…} :site_code {…} ) {…} )`
- Params: `:filterNatureCode` ← `DmShared.xNull(DmServer.getCodeViaManualCode(mrTemp.getFilterSubgroupNature(), "manualCode`; `:filterNatureCode` ← `DmShared.xNull(DmServer.getCodeViaManualCode(mrTemp.getFilterSubgroupNature(), "manualCode`

### `saveRecordAsset`
`private String saveRecordAsset(MasterSitePermission_Mdl mrTemp, boolean isAssign, Connection cnTemp) throws Exception`

- SQL 1: `DELETE asl FROM assetSiteList asl LEFT JOIN assetItemMast aim ON aim.code = asl.code WHERE asl.site_Code IN ( {…} :site_code {…} ) {…}`
- SQL 2: `INSERT INTO assetSiteList (code, site_Code) (SELECT aim.code, sm.code FROM assetItemMast aim, siteMast sm WHERE sm.code IN ( {…} :site_code {…} ) {…} )`

### `saveRecordShift`
`private String saveRecordShift(MasterSitePermission_Mdl mrTemp, boolean assign, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM shiftSiteList WHERE code in ( {…} :shiftList {…} ) AND site_Code in ( {…} :site_code {…} )`
- SQL 2: `INSERT INTO shiftSiteList (code, site_Code) (SELECT sm.code, smt.code FROM shiftMast sm, siteMast smt WHERE sm.code IN ( {…} :shiftList {…} ) AND smt.code IN ( {…} :site_Code {…} ))`

### `getTxtHSite`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHSite(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sm.code, sm.name FROM siteMast sm`

### `getTxtHMasterNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMasterNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ACCOUNT.getValue`, `ASSET.getValue`, `ITEM.getValue`, `SHIFT.getValue`

### `getTxtHMasterName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHMasterName(String masterNature, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `ACCOUNT.getValue`, `ASSET.getValue`, `ITEM.getValue`, `SHIFT.getValue`

### `getTxtHItemType`
`public DmResultPageing<List<TxtHItemType_Mdl>> getTxtHItemType(String masterName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code,name,manualcode from itemType {…}`

### `getTxtHItemCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemCategory(String masterName, String itemType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ic.code as categoryCode , ic.name as categoryName from itemcat ic WHERE 1 = 1 {…}`

### `getTxtHItemGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHItemGroup(String masterName, String itemType, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ig.code, ig.name FROM itemgroup ig WHERE 1 = 1 {…}`

### `getTxtHItemName`
`public DmResultPageing<List<TxtHItem_Mdl>> getTxtHItemName(String masterName, String itemTypeCode, String itemCat, String itemGroup, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select im.itemCategoryCode, im.itemGroup, im.itemType, im.code, im.name, im.manualCode, im.filterItemType FROM itemMast im WHERE 1 = 1 {…}`

### `getTxtHAccountGroup`
`public DmResultPageing<List<TxtHAccountGroup_Mdl>> getTxtHAccountGroup(String masterName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT ag.GroupCode,ag.GroupName , ag.Nature as NatureCode,sgn.name as NatureName, ag.GroupNature FROM AcGroup ag left join subgroupNature sgn on ag.nature= sgn.code Left Join acgroup ag1 On ag.GroupUnder = ag1.GroupCode {…}`
- Params: `:customer` ← `DmServer.getCodeViaManualCode("CT", "manualCode", "subGroupNature", "code", cnTemp)`; `:employee` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subGroupNature", "code", cnTemp)`; `:supplier` ← `DmServer.getCodeViaManualCode("SU", "manualCode", "subGroupNature", "code", cnTemp)`; `:transporter` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`; `:department` ← `DmServer.getCodeViaManualCode("DP", "manualCode", "subGroupNature", "code", cnTemp)`; `:process` ← `DmServer.getCodeViaManualCode("PC", "manualCode", "subGroupNature", "code", cnTemp)`; `:party` ← `DmServer.getCodeViaManualCode("PT", "manualCode", "subGroupNature", "code", cnTemp)`; `:common` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:warehouse` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`; `:PT` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`; `:CT` ← `DmServer.getCodeViaManualCode("CT", "manualCode", "subGroupNature", "code", cnTemp)`; `:SU` ← `DmServer.getCodeViaManualCode("SU", "manualCode", "subGroupNature", "code", cnTemp)`; `:TP` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:DP` ← `DmServer.getCodeViaManualCode("DP", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`; `:natureCode` ← `DmServer.getCodeViaManualCode(masterName, "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHNature(String masterName, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select sgn.Code ,sgn.Name as Nature From subGroupNature sgn {…}`
- Params: `:customer` ← `DmServer.getCodeViaManualCode("CT", "manualCode", "subGroupNature", "code", cnTemp)`; `:employee` ← `DmServer.getCodeViaManualCode("EM", "manualCode", "subGroupNature", "code", cnTemp)`; `:supplier` ← `DmServer.getCodeViaManualCode("SU", "manualCode", "subGroupNature", "code", cnTemp)`; `:transporter` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`; `:department` ← `DmServer.getCodeViaManualCode("DP", "manualCode", "subGroupNature", "code", cnTemp)`; `:process` ← `DmServer.getCodeViaManualCode("PC", "manualCode", "subGroupNature", "code", cnTemp)`; `:party` ← `DmServer.getCodeViaManualCode("PT", "manualCode", "subGroupNature", "code", cnTemp)`; `:common` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:warehouse` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`; `:PT` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`; `:CT` ← `DmServer.getCodeViaManualCode("CT", "manualCode", "subGroupNature", "code", cnTemp)`; `:SU` ← `DmServer.getCodeViaManualCode("SU", "manualCode", "subGroupNature", "code", cnTemp)`; `:TP` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`; `:CM` ← `DmServer.getCodeViaManualCode("CM", "manualCode", "subGroupNature", "code", cnTemp)`; `:DP` ← `DmServer.getCodeViaManualCode("DP", "manualCode", "subGroupNature", "code", cnTemp)`; `:GD` ← `DmServer.getCodeViaManualCode("GD", "manualCode", "subGroupNature", "code", cnTemp)`; `:natureCode` ← `DmServer.getCodeViaManualCode(masterName, "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHAccountName`
`public DmResultPageing<List<TxtHAccountName_Mdl>> getTxtHAccountName(String masterName, String natureTypeCode, String accountGroup, String ledgerGroup, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ag.groupName, sgn.name as Nature, sg.subCode, sg.name, sg.manualCode from subGroup sg left join subgroupNature sgn on sg.nature= sgn.code Left Join acgroup ag On sg.GroupCode = ag.GroupCode {…}`
- Params: `:filterNatureCode` ← `DmServer.getCodeViaManualCode(masterName, "manualCode", "subGroupNature", "code", cnTemp)`

### `getTxtHShiftName`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHShiftName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT code, name FROM shiftMast`

### `getTxtHAssetNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `AssetVehicleNature.values`

### `getTxtHAssetItem`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetItem(String assetCat, String assetGroup, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select aim.code, aim.name from assetItemMast aim {…}`

### `getTxtHAssetGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code , name from assetGroupMast`

### `getTxtHAssetCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAssetCategory(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select code , name from assetCatMast`

### `getTxtHLedgerGroup`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHLedgerGroup(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT lg.code,lg.name FROM ledgerGroup lg`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.modifyReason.shared`, `dataman.libraryMenu.self.shared`, `paperbiz.projectMenu.self.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
