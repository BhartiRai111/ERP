# pb-self-toolkit / stImportData

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/stImportData/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: itemmast, itemSiteList, assetGroupMast, assetGroupProperties, shiftMast, gsmMast, itemCat, subgroup, departmentmast, subGroupSiteList, SubGroup, subgroupsitelist, itemSizeMast, LedgerM, Ledger, machineMast, itemColourMast, packSizeMast, bfMast, its, designationMast, production, productionDetail, itemGroup, stockHead, stockHeadDetail, subGroupAreaList, assetItemMast, assetItemProperties, hsnMast, gstReasonMast, gstUnitMast, unitMast
- Update tables: SubGroup2
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/AccountOpening.java | 899 |
| server/AssetGroupMaster.java | 537 |
| server/AssetItemMaster.java | 702 |
| server/BFMaster.java | 276 |
| server/CustomerMaster.java | 1518 |
| server/DivisionMaster.java | 1479 |
| server/EmployeeMaster.java | 1517 |
| server/FinishedItemMaster.java | 2089 |
| server/GSMMaster.java | 276 |
| server/GSTReasonMast.java | 322 |
| server/GSTUnitMast.java | 321 |
| server/HSNMaster.java | 459 |
| server/ImportData_Server.java | 72 |
| server/ImportDepartmentMaster.java | 657 |
| server/ImportDesignationMaster.java | 331 |
| server/ItemCategory.java | 1622 |
| server/ItemColorMaster.java | 254 |
| server/ItemGroup.java | 252 |
| server/ItemMaster.java | 3129 |
| server/ItemOpening.java | 1645 |
| server/ItemSizeMaster.java | 254 |
| server/JournalVoucher.java | 855 |
| server/MachineMaster.java | 293 |
| server/ManufacturerMaster.java | 1687 |
| server/PackSizeMaster.java | 347 |
| server/PartyMaster.java | 1831 |
| server/ReelOpening.java | 13 |
| server/ReelOpeningBkp.java | 797 |
| server/ServiceMaster.java | 1605 |
| server/SheetOpening.java | 1032 |
| server/ShiftMaster.java | 315 |
| server/StoreOpening.java | 14 |
| server/SupplierMaster.java | 1542 |
| server/UnitMaster.java | 369 |

## Server `FinishedItemMaster.java` (2089 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- SQL 1: `SELECT ec.finishedItemUnit AS skuCode, um.unit AS skuName FROM enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.finishedItemUnit`
- Errors: "Log file path can not be blank." | "Define Trade Item SKU in \" | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `saveFinishedItemMaster`
`private int saveFinishedItemMaster(Model mrTemp)`

- SQL 1: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = {…}`
- SQL 2: `Select Count(ManualCode) As Cnt From itemmast mm Where mm.ManualCode = :ManualCode And Code <> :Code`
- SQL 3: `Select Count(Name) As Cnt From itemMast mm Where mm.Name = :Name`
- SQL 4: `INSERT INTO itemmast (CODE, NAME,displayName, itemGroup,itemType, filterItemType, sku, preparedBY, preparedDt, transferDt,itemCategoryCode, manualCode,itemDesc,isHighValue,isFlowThrough, isConsignment,isReturnableItem,isBilledItem, itemGSTNature,purchaseUnit,saleUnit, isPOMandatory, tl_ExcessPer, tl_ShortagePer, wtaCode,isActive, isIndentMandatory, isQuotationMandatory,isLabRequired , isDiscountAllow,isSaleRateEditable , purchaseUnitConversionRate,saleUnitConversionRate,allowNegativeStock,hsnCode,itsBasedOn , purchaseAc,salesAc,subCode, purchaseReturnAc ,preparedByIP, salesReturnAc ,grirAc,consumptionAc,wipAc,transitAc,cogsAc, itemDeptNature, propertyList) VALUES (:code, :name, :displayName,:itemGroup, :itemType, :filterItemType, :sku, :preparedBY, :preparedDt, NULL, :itemCategoryCode, :manualCode,:itemDesc,:isHighValue,:isFlowThrough, :isConsignment,:isReturnableItem,:isBilledItem ,:itemGSTNature,:purchaseUnit,:saleUnit , :isPOMandatory, :tl_ExcessPer, :tl_ShortagePer, :wtaCode, :isActive , :isIndentMandatory , :isQuotationMandatory ,:isLabRequired , :isDiscountAllow, :isSaleRateEditable , :purchaseUnitConversionRate,:saleUnitConversionRate,:allowNegativeStock,:hsnCode,:itsBasedOn , :purchaseAc,:salesAc,:subCode, :purchaseReturnAc,:preparedByIP , :salesReturnAc ,:grirAc,:consumptionAc,:wipAc,:transitAc,:cogsAc, :itemDeptNature, :propertyList) ;`
- SQL 5: `INSERT INTO itemSiteList (CODE, site_Code) VALUES (:code, :site_Code)`
- Params: `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:Code` ← `DmShared.iNull(mrTemp.getCode()).toString()`; `:Name` ← `DmShared.xNull(mrTemp.getItemTypeText())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `DmShared.xNull(mrTemp.getItemNameText())`; `:displayName` ← `DmShared.xNull(mrTemp.getItemNameText())`; `:itemGroup` ← `DmShared.foriegnKey(mrTemp.getItemGroupTag())`; `:hsnCode` ← `DmShared.foriegnKey(mrTemp.getHsnCode())`; `:manualCode` ← `mrTemp.getManualCode()`; `:itemDesc` ← `DmShared.xNull(mrTemp.getItemDescription())`; `:itemType` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", `; `:filterItemType` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode("FM", "manualCode", "itemType", "code", `; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:isActive` ← `"1"`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getCategoryTag())`; `:isHighValue` ← `DmShared.xNull(mrTemp.getHighValueTag())`; `:isFlowThrough` ← `DmShared.xNull(mrTemp.getFlowThroughTag())`; `:isConsignment` ← `DmShared.xNull(mrTemp.getConsignmentTag())`; `:isReturnableItem` ← `DmShared.xNull(mrTemp.getSalesReturnAllowTag())`; `:isBilledItem` ← `DmShared.xNull(mrTemp.getBilledItemTag())`; `:itemGSTNature` ← `DmShared.xNull(mrTemp.getGstNatureTag())`; `:purchaseUnit` ← `DmShared.xNull(mrTemp.getPurchaseUnitCode())`; `:saleUnit` ← `DmShared.xNull(mrTemp.getSaleUnitCode())`; `:tl_ExcessPer` ← `DmShared.xNull(mrTemp.getTolerancePlus())`; `:tl_ShortagePer` ← `DmShared.xNull(mrTemp.getToleranceMinus())`; `:isPOMandatory` ← `DmShared.xNull(mrTemp.getPoReqTag())`; `:isLabRequired` ← `DmShared.iNull(mrTemp.getLabRequiredTag()).toString()`; `:isIndentMandatory` ← `DmShared.xNull(mrTemp.getIndentReqTag())`; `:isQuotationMandatory` ← `DmShared.xNull(mrTemp.getQuotationReqTag())`; `:purchaseUnitConversionRate` ← `DmServer.vNull(mrTemp.getConversionRatePurchase())`; `:saleUnitConversionRate` ← `DmServer.vNull(mrTemp.getConversionRateSale())`; `:allowNegativeStock` ← `DmShared.xNull(mrTemp.getAllowNegetiveStockCode())`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:wtaCode` ← `""`; `:itsBasedOn` ← `DmShared.iNull(mrTemp.getItsBasedOnTag()).toString()`; `:purchaseAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseAcTag())`; `:salesAc` ← `DmShared.foriegnKey(mrTemp.getSalesAcTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getInventoryAcTag())`; `:purchaseReturnAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseReturnAcTag())`; `:salesReturnAc` ← `DmShared.foriegnKey(mrTemp.getSalesReturnAcTag())`; `:isDiscountAllow` ← `DmShared.iNull(mrTemp.getSaleDiscountAllowTag()).toString()`; `:isSaleRateEditable` ← `DmShared.iNull(mrTemp.getSaleRateEditableTag()).toString()`; `:grirAc` ← `DmShared.foriegnKey(mrTemp.getGrirAcTag())`; `:consumptionAc` ← `DmShared.foriegnKey(mrTemp.getConsumptionAcTag())`; `:wipAc` ← `DmShared.foriegnKey(mrTemp.getWipAcTag())`; `:transitAc` ← `DmShared.foriegnKey(mrTemp.getTransitAcTag())`; `:cogsAc` ← `DmShared.foriegnKey(mrTemp.getCogsAcTag())`; `:itemDeptNature` ← `DmShared.foriegnKey(mrTemp.getItemDepartmentNatureTag())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:propertyList` ← `DmShared.xNull(ItemProperty_Mdl.FINISHED_PAPER_PROPERTIES)`; `:code` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:v_SNo` ← `"1"`; `:rateFrom` ← `"0.00"`; `:rateUpto` ← `"0.00"`; `:gstTypeCode` ← `DmShared.foriegnKey(mrTemp.getGstTypeCode())`
- Errors: "Invalid key." | "Allow negative stock cannot be updated with </b>Yes</b> due to inventory accounting is active." | "Item Group is Mandatory" | "Item Category is Mandatory" | "HSN is Mandatory" | "Gst Nature is Mandatory" | "Consignment is Mandatory" | "Flow Through is Mandatory" | "High Value is Mandatory" | "Lab Required is Mandatory" | "Billed Item is Mandatory" | "Indent Required is Mandatory" | "Quotation Required is Mandatory" | "PO Required is Mandatory" | "SO Required is Mandatory" | "Sale Returnable Item is Mandatory" | "Sale Discount Allow is Mandatory" | "Sale Rate Editable is Mandatory" | "Allow Negative Stock is Mandatory" | "PurchaseUnit is Mandatory" | "SaleUnit is Mandatory" | "ITS Based on is Required" | "Manual Code already exists." | "Purchase conversion rate should be one" | "Sale conversion rate should be one" | "Item Name already exists."
- Other calls: `ERROR.getId`, `GSTNature.values`, `ItsBasedOn.values`, `LabRequired.values`, `NONE.getId`, `SUCCESS.getId`, `Stock_Server.isInventoryAccountingActive`, `WARNING.getId`

### `getYesNoCode`
`private String getYesNoCode(String str)`


## Server `AssetGroupMaster.java` (537 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `AssetVehicleNature.getViaText`, `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveAssetGroup`
`private int saveAssetGroup(GroupHeader_Mdl mrTemp)`

- SQL 1: `select COUNT(um.name) as cnt from assetGroupMast as um where um.name = :name AND um.code <> :code`
- SQL 2: `INSERT INTO assetGroupMast (code, name,manualCode,assetNature,preparedBY,u_EntDt) VALUES(:code, :name,:manualCode,:assetNature,:preparedBY,:u_EntDt);`
- SQL 3: `DELETE FROM assetGroupProperties WHERE code = :code`
- SQL 4: `insert into assetGroupProperties (code, propertyCode, SNo, nature) values (:code, :propertyCode, :SNo, :nature)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `mrTemp.getCode()`; `:name` ← `mrTemp.getName()`; `:manualCode` ← `mrTemp.getShortName()`; `:assetNature` ← `mrTemp.getAssetNature().getId()`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `mrTemp.getCode()`; `:code` ← `mrTemp.getCode()`; `:propertyCode` ← `node.getCode()`; `:SNo` ← `node.getPrintSno()`; `:nature` ← `node.isExposed() ? "1" : "0"`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ShiftMaster.java` (315 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveShiftMaster`
`private int saveShiftMaster(Model mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM shiftMast WHERE name = :name AND code <> :code`
- SQL 2: `INSERT INTO shiftMast (code, name, manualCode, fromTime, toTime, preparedBy, preparedDt) VALUES (:code, :name, :manualCode, :fromTime, :toTime, :preparedBy, :preparedDt)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `mrTemp.getName()`; `:manualCode` ← `mrTemp.getManualCode()`; `:fromTime` ← `mrTemp.getFromTime()`; `:toTime` ← `mrTemp.getToTime()`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `GSMMaster.java` (276 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveGSMMaster`
`private int saveGSMMaster(Model mrTemp)`

- SQL 1: `SELECT COUNT(code) AS cnt FROM gsmMast WHERE code = :code`
- SQL 2: `INSERT INTO gsmMast (code, isActive, preparedBy, preparedDt) VALUES (:code, :isActive, :preparedBy, :preparedDt)`
- Params: `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:isActive` ← `mrTemp.getActiveCode()`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`
- Errors: "Active field required only Yes/No." | "GSM Active is required."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ItemCategory.java` (1622 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `saveItemCategory`
`private int saveItemCategory(Model mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM itemCat WHERE name = :name AND code <> :code`
- SQL 2: `Insert Into itemCat(code ,name,filterItemType, preparedBy,purchaseAc,purchaseContraAc,salesAc,subCode, purchaseReturnAc, salesReturnAc ,grirAc,consumptionAc,wipAc,transferAc,transitAc,cogsAc,preparedByIP, propertyList,preparedDt,transferDt) Values (:code ,:name,:filterItemType,:preparedBy,:purchaseAc,:purchaseContraAc,:salesAc,:subCode,:purchaseReturnAc,:salesReturnAc,:grirAc,:consumptionAc,:wipAc,:transferAc,:transitAc,:cogsAc,:preparedByIP,:propertyList,:preparedDt,NULL)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `DmShared.xNull(mrTemp.getName())`; `:filterItemType` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:purchaseAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseAcTag())`; `:purchaseContraAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseContraAcTag())`; `:salesAc` ← `DmShared.foriegnKey(mrTemp.getSalesAcTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getInventoryAcTag())`; `:purchaseReturnAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseReturnAcTag())`; `:salesReturnAc` ← `DmShared.foriegnKey(mrTemp.getSalesReturnAcTag())`; `:grirAc` ← `DmShared.foriegnKey(mrTemp.getGrirAcTag())`; `:consumptionAc` ← `DmShared.foriegnKey(mrTemp.getConsumptionAcTag())`; `:wipAc` ← `DmShared.foriegnKey(mrTemp.getWipAcTag())`; `:transferAc` ← `DmShared.foriegnKey(mrTemp.getTransferAcTag())`; `:transitAc` ← `DmShared.foriegnKey(mrTemp.getTransitAcTag())`; `:cogsAc` ← `DmShared.foriegnKey(mrTemp.getCogsAcTag())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:propertyList` ← `DmShared.xNull(mrTemp.getPropertyList())`; `:preparedDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "Serial number already Exist for batch no." | "Serial number not required in case of BatchNo is No." | "Serial number already Exist for colour." | "Serial number not required in case of Colour is No." | "Serial number already Exist for LotNo." | "Serial number not required in case of LotNo is No." | "Serial number already Exist for Bf." | "Serial number not required in case of Bf is No." | "Serial number already Exist for gsm." | "Serial number not required in case of Gsm is No." | "Serial number already Exist for sheetPerReam." | "Serial number not required in case of SheetPerReam is No." | "Serial number already Exist333333 for expiry date." | "Serial number not required in case of ExpiryDate is No." | "Serial number already Exist for item make." | "Serial number not required in case of ItemMake is No." | "Serial number already Exist for size." | "Serial number not required in case of Size is No." | "Serial number already Exist for length." | "Serial number not required in case of Length is No." | "Serial number already Exist for" | "Serial number not required in case of Width is No." | "Serial number already Exist for ream weight." | "Serial number not required in case of ReemWeight is No." | "Serial number already Exist for ream per bundle" | "Serial number not required in case of ReemsPerBundle is No."
- Other calls: `BATCH_NO.getPropertyManualCode`, `BF.getPropertyManualCode`, `COLOUR.getPropertyManualCode`, `ERROR.getId`, `EXPIRY_DATE.getPropertyManualCode`, `GSM.getPropertyManualCode`, `ITEM_MAKE.getPropertyManualCode`, `LENGTH.getPropertyManualCode`, `LOT_NO.getPropertyManualCode`, `NONE.getId`, `REAMS_PER_BUNDLE.getPropertyManualCode`, `REAM_WEIGHT.getPropertyManualCode`, `SHEETS_PER_REAM.getPropertyManualCode`, `SIZE.getPropertyManualCode`, `SUCCESS.getId`, `WARNING.getId`, `WIDTH.getPropertyManualCode`

## Server `ImportDepartmentMaster.java` (657 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getVType`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Department 'DP' nature not exists." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `saveDepartment`
`private int saveDepartment(Model mrTemp)`

- SQL 1: `SELECT COUNT(sG.name) AS cnt FROM subGroup as sG WHERE sG.name = :Name AND sG.subCode <> :subCode`
- SQL 2: `SELECT COUNT(sdm.manualCode) AS cnt FROM subgroup as sdm WHERE LOWER(sdm.manualCode) = :shortName AND sdm.subCode <> :code`
- SQL 3: `Insert Into subgroup(subcode,groupCode,name,nature,filterNature ,manualCode,site_Code,preparedBy,preparedDt,preparedByIP ,legalName, tradeName, add1, add2, cityCode , pin, gsTin, gstinDate, eMail, mobile, pan ) Values (:subcode,:groupCode,:name,:nature,:filterNature ,:manualCode,:sitecode,:preparedBy,:preparedDt,:preparedByIP , :legalName, :tradeName, :add1, :add2, :cityCode ,:pin, :gsTin, :gstinDate, :eMail, :mobile, :pan )`
- SQL 4: `Insert Into departmentmast(subCode,nature) Values (:subcode,:nature)`
- SQL 5: `INSERT INTO subGroupSiteList(subCode, site_Code) VALUES (:subcode, :siteCode );`
- Params: `:subCode` ← `DmShared.xNull(mrTemp.getCode())`; `:Name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName())`; `:subcode` ← `mrTemp.getCode()`; `:name` ← `mrTemp.getName()`; `:manualCode` ← `mrTemp.getShortName()`; `:nature` ← `DmShared.foriegnKey(natureCode)`; `:legalName` ← `mrTemp.getLegalName()`; `:tradeName` ← `mrTemp.getTradeName()`; `:add1` ← `mrTemp.getAddress1()`; `:add2` ← `mrTemp.getAddress2()`; `:cityCode` ← `DmShared.foriegnKey(city)`; `:pin` ← `mrTemp.getPinCode()`; `:gsTin` ← `mrTemp.getGstin()`; `:gstinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:pan` ← `mrTemp.getPan()`; `:mobile` ← `mrTemp.getMobileNo()`; `:eMail` ← `mrTemp.getEmail()`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:groupcode` ← `acDepartmentCode`; `:siteCode` ← `DmServer.getSsnSiteCode(rss)`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:subcode` ← `mrTemp.getCode()`; `:nature` ← `mrTemp.getNature()`; `:subcode` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:siteCode` ← `DmServer.getSsnSiteCode(rss)`
- Errors: "Invalid key." | "PAN Should Be Of 10(Ten) Characters."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `SupplierMaster.java` (1542 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidGSTIN`, `DmShared.isValidPanNo`, `DmShared.isValidText`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Supplier 'SU' nature not exists." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveSupplier`
`private int saveSupplier(Model mrTemp)`

- SQL 1: `SELECT COUNT(sG.name) AS cnt FROM subGroup as sG WHERE sG.name = :Name AND sG.subCode <> :subCode`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 4: `SELECT c.cityCode ,sm.numericCode FROM city c left join stateMast sm on c.stateCode = sm.code WHERE c.cityName = :cityName`
- SQL 5: `Insert Into SubGroup(SubCode,Name,GroupCode,ManualCode,Nature,Add1,Add2,CityCode,PIN,registrationNo, Phone,Mobile,FAX,Remark,isDuplicateTIN,preparedDt,CostCenter,PAN,EMail,TINNo, CreditLimit,DueDays,PartyCat,PartyType,IECCode,TDS_Catg,Zone,LedgerGroup, Distributor,Site_Code,DisplayName,Initial, preparedBy,filterNature,invoiceDiscount, aadhaarNo,gsTinDate,gsTIN) Values (:subCode, :name, :groupCode, :manualCode, :nature, :add1, :add2, :cityCode, :pin,:registrationNo, :phone, :mobile, :fax,:remark, :duplicateTIN, :preparedDt, :costCenter, :pan, :email, :tinNo, :creditLimit, :dueDays, :partyCat, :partyType, :iecCode, :tdsCatg, :zone, :ledgerGroup, :distributor, :siteCode, :displayName, :initial, :preparedBy,:filterNature, :invoiceDiscount,:aadhaarNo,:gsTinDate,:gsTIN)`
- SQL 6: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- SQL 7: `Select Count(*) As Cnt From SubGroup2 Where SubCode= :subCode`
- Params: `:subCode` ← `DmShared.xNull(mrTemp.getCode())`; `:Name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:tinNo` ← `mrTemp.getTIN()`; `:subCode` ← `mrTemp.getCode()`; `:manualCode` ← `manualCode`; `:subCode` ← `mrTemp.getCode()`; `:cityName` ← `mrTemp.getCityName()`; `:subCode` ← `mrTemp.getCode()`; `:initial` ← `DmShared.xNull(mrTemp.getAcInitialCode())`; `:name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:displayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupCode())`; `:manualCode` ← `DmShared.foriegnKey(manualCode)`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `DmShared.xNull(mrTemp.getPinCode())`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:fax` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:duplicateTIN` ← `DmServer.vNull(mrTemp.getIsDuplicate(), "0")`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:pan` ← `DmShared.xNull(mrTemp.getPan().toUpperCase())`; `:email` ← `DmShared.xNull(mrTemp.getEmail())`; `:tinNo` ← `DmShared.xNull(mrTemp.getTIN())`; `:creditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:dueDays` ← `DmServer.vNull(mrTemp.getCreditPeriod(), "0")`; `:partyCat` ← `DmShared.foriegnKey(mrTemp.getPartyCategoryCode())`; `:partyType` ← `DmShared.xNull(mrTemp.getPartyTypeCode()).toString()`; `:iecCode` ← `DmShared.iNull(mrTemp.getIECNo()).toString()`; `:tdsCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategoryCode())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZoneCode())`; `:ledgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroupCode())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getReferenceToCode())`; `:siteCode` ← `DmServer.getSsnSiteCode(rss)`; `:registrationNo` ← `DmShared.xNull(mrTemp.getRegistrationNo())`; `:invoiceDiscount` ← `DmServer.vNull(mrTemp.getInvoiceDis(), "0.00")`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGstin().toUpperCase())`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:siteCode` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`; `:subCode` ← `mrTemp.getCode()`; `:subCode` ← `mrTemp.getCode()`
- Errors: "Invalid key." | "Duplicate TIN Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!" | "For defining GST detail, you should have to fill Party Type first." | "Please fill the Pan No." | "In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date." | "In case of unregistered party type AADHAAR No. or PAN No. is mandatory." | "GSTIN Date should not be greater than current date." | "TIN Should Be Equal To 11(Eleven) Characters."
- Other calls: `Composite.getId`, `Composite.getValue`, `ERROR.getId`, `NONE.getId`, `Registered.getId`, `Registered.getValue`, `SUCCESS.getId`, `Unregistered.getId`, `Unregistered.getValue`, `WARNING.getId`

## Server `ItemSizeMaster.java` (254 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveSizeMaster`
`private int saveSizeMaster(Model mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM itemSizeMast WHERE name = :name AND code <> :code`
- SQL 2: `Insert Into itemSizeMast(code , name, preparedBy, preparedDt, preparedByIP, transferDt) Values (:code, :name, :preparedBy, :preparedDt, :preparedByIP, NULL)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `DmShared.xNull(mrTemp.getName())`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `StoreOpening.java` (14 lines)
## Server `AccountOpening.java` (899 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnCompanyCode`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `saveAccountOpening`
`private int saveAccountOpening(Model mrTemp)`

- SQL 1: `select start_Dt,v_Prefix from company where comp_Code = :comp_Code`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = {…}`
- SQL 3: `select count (*) as Cnt from subGroup sg left join subGroupNature sgn on sgn.code = sg.nature where sgn.name = 'BANK' and sg.subCode = :subCode`
- SQL 4: `select postedBy FROM LedgerM WHERE docId = :docId`
- SQL 5: `Select Count(*) as Cnt From DataAudit Where DocId= :docId`
- SQL 6: `Select Count(*) as cnt From LedgerAdj Where Adj_DocID= :docId`
- SQL 7: `Delete From LedgerCostCenter Where DocId= :docId`
- SQL 8: `Delete From Ledger Where DocId= :docId`
- SQL 9: `select count(*) as cnt from ledgerM lm LEFT JOIN ledger l on l.docId = lm.docId LEFT JOIN ledgerCostCenter lcc on lcc.docId = l.docId and lcc.v_SNo = l.v_SNo LEFT JOIN voucher_Type vt on vt.v_Type = l.v_Type where vt.category = {…} {…}`
- SQL 10: `Insert Into LedgerM(docId, V_Type, V_Prefix, Site_Code, RecId, recIdPrefix, v_No, V_Date, SubCode, U_EntDt,PreparedBy,PostedBy) Values (:docId,:vType,:vPrefix,:siteCode,:recId, :recIdPrefix, :v_No,:vDate,:subCode,:uEntDt,:preparedBy,:PostedBy)`
- SQL 11: `Insert Into Ledger(DocId,RecId, recIdPrefix, v_No,V_Date,SubCode,AmtDr,AmtCr,Chq_No,Chq_Date,Clg_Date,favourOf, Narration,V_Type,V_SNo,V_Prefix,Site_Code,isPosted,RefNo,RefDate) Values (:docId,:recId, :recIdPrefix, :v_No,:vDate,:subCode,:amtDr,:amtCr,:chq_No,:chq_Date,:clg_Date,:favourOf,:narration,:vType,:vSNo,:vPrefix,:siteCode,:posted,:refNo,:refDate)`
- Params: `:comp_Code` ← `DmShared.xNull(DmServer.getSsnCompanyCode(rss))`; `:subCode` ← `DmShared.xNull(mrTemp.getAccountNameCode())`; `:docId` ← `DmShared.iNull(mrTemp.getCode()).toString()`; `:docId` ← `DmShared.xNull(mrTemp.getCode())`; `:docId` ← `DmShared.xNull(mrTemp.getCode())`; `:docId` ← `DmShared.xNull(mrTemp.getCode())`; `:docId` ← `DmShared.xNull(mrTemp.getCode())`; `:subcode` ← `DmShared.xNull(mrTemp.getAccountNameCode())`; `:costCenter` ← `DmShared.iNull(mrTemp.getCostCenterCode()).toString()`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:chequeDate` ← `DmServer.convertDate(mrTemp.getChqDate())`; `:chq_No` ← `DmShared.xNull(mrTemp.getChqNo())`; `:favourOf` ← `DmShared.xNull(mrTemp.getFavourOf())`; `:amtDr` ← `DmShared.xNull(mrTemp.getDebit())`; `:amtCr` ← `DmShared.xNull(mrTemp.getCredit())`; `:docId` ← `DmShared.xNull(mrTemp.getCode())`; `:vType` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:vPrefix` ← `DmShared.xNull(mrTemp.getvPrefix())`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(rss))`; `:recId` ← `DmShared.xNull(mrTemp.getRecId())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:vDate` ← `DmServer.convertDate(mrTemp.getDate())`; `:subCode` ← `DmShared.xNull(mrTemp.getAccountNameCode())`; `:PostedBy` ← `(DmServer.getSsnUserName(rss))`; `:preparedBy` ← `DmShared.xNull(DmServer.getSsnUserName(rss))`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:docId` ← `DmShared.xNull(mrTemp.getCode())`; `:recId` ← `DmShared.xNull(mrTemp.getRecId())`; `:recIdPrefix` ← `DmShared.xNull(mrTemp.getObjRecId().getPrefix())`; `:v_No` ← `DmShared.iNull(mrTemp.getObjRecId().getCounter()).toString()`; `:vDate` ← `DmServer.convertDate(mrTemp.getLedgerVoucherDate())`; `:subCode` ← `DmShared.xNull(mrTemp.getAccountNameCode())`; `:amtDr` ← `DmServer.vNull(mrTemp.getDebit(), "0.00")`; `:amtCr` ← `DmServer.vNull(mrTemp.getCredit(), "0.00")`; `:chq_No` ← `DmShared.xNull(mrTemp.getChqNo())`; `:chq_Date` ← `DmServer.convertDate(mrTemp.getChqDate())`; `:clg_Date` ← `DmServer.convertDate(mrTemp.getChqClearDate())`; `:favourOf` ← `DmShared.xNull(mrTemp.getFavourOf())`; `:narration` ← `DmShared.xNull(mrTemp.getNarration())`; `:vType` ← `DmShared.xNull(mrTemp.getVoucherTypeCode())`; `:vSNo` ← `"1"`; `:vPrefix` ← `DmShared.xNull(mrTemp.getvPrefix())`; `:siteCode` ← `DmShared.xNull(DmServer.getSsnSiteCode(rss))`; `:posted` ← `"1"`; `:refNo` ← `DmShared.xNull(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:docId` ← `DmShared.xNull(mrTemp.getCode())`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:amtDr` ← `DmServer.vNull(mrTemp.getDebit(), "0.00")`; `:amtCr` ← `DmServer.vNull(mrTemp.getCredit(), "0.00")`
- Errors: "Invalid key." | "Opening Type required only Dr/Cr." | "Opening Type is required." | "cheque no  required." | "cheque date  required." | "favour is  required." | "cheque not  required." | "cheque date not  required." | "favour is not  required." | "Record is approved. You can't edit this record." | "This entry has been done from [Journal Voucher]. You can not edit/delete this entry." | "Record Has Been Audited. You Can Not Edit/ Delete This Record." | "Record Has Been Adjusted  You Can Not Edit/Delete This Bill." | "No Record Found." | "Duplicate Record Exist"
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ManufacturerMaster.java` (1687 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidGSTIN`, `DmShared.isValidPanNo`, `DmShared.isValidText`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Manufacturer 'MF' nature not exists." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveManufacturer`
`private int saveManufacturer(Model mrTemp)`

- SQL 1: `SELECT COUNT(sG.name) AS cnt FROM subGroup as sG WHERE sG.name = :Name AND sG.subCode <> :subCode`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 4: `SELECT c.cityCode ,sm.numericCode FROM city c left join stateMast sm on c.stateCode = sm.code WHERE c.cityName = :cityName`
- SQL 5: `Insert Into SubGroup(SubCode,Name,GroupCode,ManualCode,Nature,Add1,Add2,CityCode,PIN, Phone,Mobile,FAX,ContactPerson, Remark,isDuplicateTIN,PolicyNo,CostCenter,PAN,CSTNo,EMail,TINNo, CreditLimit,DueDays,PartyType,ECCCode,Excise,Range,Division,STNo,IECCode,TDS_Catg,Zone,LedgerGroup, Distributor,Site_Code,DisplayName,Initial, preparedBy,filterNature, aadhaarNo,gsTinDate,gsTIN) Values (:subCode, :name, :groupCode, :manualCode, :nature, :add1, :add2, :cityCode, :pin, :phone, :mobile, :fax,:contactPerson, :remark, :duplicateTIN, :policyNo, :costCenter, :pan, :cstNo, :email, :tinNo, :creditLimit, :dueDays, :partyType, :eccCode, :Excise, :range, :division, :stNo, :iecCode, :tds_Catg, :zone, :ledgerGroup, :distributor, :siteCode, :displayName, :initial, :preparedBy,:filterNature, :aadhaarNo,:gsTinDate,:gsTIN)`
- SQL 6: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- SQL 7: `Select Count(*) As Cnt From SubGroup2 Where SubCode= :subCode`
- Params: `:subCode` ← `DmShared.xNull(mrTemp.getCode())`; `:Name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:tinNo` ← `mrTemp.getTIN()`; `:subCode` ← `mrTemp.getCode()`; `:manualCode` ← `manualCode`; `:subCode` ← `mrTemp.getCode()`; `:cityName` ← `mrTemp.getCityName()`; `:subCode` ← `mrTemp.getCode()`; `:initial` ← `DmShared.xNull(mrTemp.getAcInitialCode())`; `:name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:displayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupCode())`; `:manualCode` ← `DmShared.foriegnKey(manualCode)`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `DmShared.xNull(mrTemp.getPinCode())`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:fax` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:contactPerson` ← `DmShared.xNull(mrTemp.getContactPerson())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:duplicateTIN` ← `DmServer.vNull(duplicateCode, "0")`; `:policyNo` ← `DmServer.vNull(mrTemp.getPolicyNo())`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:pan` ← `DmShared.xNull(mrTemp.getPan().toUpperCase())`; `:cstNo` ← `DmShared.xNull(mrTemp.getCstNo())`; `:email` ← `mrTemp.getEmail()`; `:tinNo` ← `DmShared.xNull(mrTemp.getTIN())`; `:creditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:dueDays` ← `DmServer.vNull(mrTemp.getCreditPeriod(), "0")`; `:partyType` ← `mrTemp.getPartyTypeCode()`; `:eccCode` ← `mrTemp.getEccCode()`; `:Excise` ← `mrTemp.getExcise()`; `:range` ← `mrTemp.getRange()`; `:division` ← `mrTemp.getDivision()`; `:stNo` ← `mrTemp.getStNo()`; `:iecCode` ← `mrTemp.getIECNo()`; `:tds_Catg` ← `DmShared.foriegnKey(mrTemp.getTdsCategoryName())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZoneCode())`; `:ledgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroupCode())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getReferenceToCode())`; `:siteCode` ← `DmServer.getSsnSiteCode(rss)`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGstin().toUpperCase())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:siteCode` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`; `:subCode` ← `mrTemp.getCode()`; `:subCode` ← `mrTemp.getCode()`
- Errors: "Invalid key." | "Duplicate TIN Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!" | "For defining GST detail, you should have to fill Party Type first." | "Please fill the Pan No." | "In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date." | "In case of unregistered party type AADHAAR No. or PAN No. is mandatory." | "GSTIN Date should not be greater than current date." | "TIN Should Not Be Greater Than 11(Eleven) Characters."
- Other calls: `Composite.getId`, `Composite.getValue`, `ERROR.getId`, `NONE.getId`, `Registered.getId`, `Registered.getValue`, `SUCCESS.getId`, `Unregistered.getId`, `Unregistered.getValue`, `WARNING.getId`

## Server `MachineMaster.java` (293 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveMachineMaster`
`private int saveMachineMaster(Model mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM machineMast WHERE name = :name AND code <> :code`
- SQL 2: `INSERT INTO machineMast (code, name, isActive, preparedBy, preparedDt) VALUES (:code,:name, :isActive, :preparedBy, :preparedDt)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `mrTemp.getName()`; `:isActive` ← `mrTemp.getActiveCode()`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "Active field required only Yes/No." | "Machine Active is required."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ItemColorMaster.java` (254 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveColourMaster`
`private int saveColourMaster(Model mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM itemColourMast WHERE name = :name AND code <> :code`
- SQL 2: `Insert Into itemColourMast(code , name, preparedBy, preparedDt, preparedByIP, transferDt) Values (:code, :name, :preparedBy, :preparedDt, :preparedByIP, NULL)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `DmShared.xNull(mrTemp.getName())`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `PackSizeMaster.java` (347 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `savePackSizeMaster`
`private int savePackSizeMaster(Model mrTemp)`

- SQL 1: `SELECT COUNT(*) cnt FROM packSizeMast WHERE name = :name AND code <> :code`
- SQL 2: `Insert Into packSizeMast(code, name,sku,packUnitConversionRate,packUnitCode,isActive, preparedBy,preparedDt,preparedByIP,u_EntDt_Latestline,transferDt) Values(:code, :name,:sku,:packUnitConversionRate,:packUnitCode,:isActive, :preparedBy,:preparedDt,:preparedByIP,:u_EntDt_Latestline,NULL)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `mrTemp.getCode()`; `:name` ← `DmShared.xNull(mrTemp.getName())`; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:packUnitConversionRate` ← `DmServer.vNull(mrTemp.getPackUnitConversion())`; `:packUnitCode` ← `DmShared.foriegnKey(mrTemp.getPackUnitSkuCode())`; `:isActive` ← `"1"`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:u_EntDt_Latestline` ← `DmServer.getServerTimestamp()`
- Errors: "Invalid key." | "Pack Size already exists." | "SKU not exist " | "Pack unit not exist "
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`

## Server `DivisionMaster.java` (1479 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidGSTIN`, `DmShared.isValidPanNo`, `DmShared.isValidText`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Division 'DV' nature not exists." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveDivision`
`private int saveDivision(Model mrTemp)`

- SQL 1: `SELECT COUNT(sG.name) AS cnt FROM subGroup as sG WHERE sG.name = :Name AND sG.subCode <> :subCode`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 4: `Insert Into SubGroup(SubCode,Name,GroupCode,ManualCode,Nature,Add1,Add2,CityCode,PIN,Phone,Mobile,FAX,ContactPerson,Remark,isDuplicateTIN,PolicyNo,U_EntDt,CostCenter,PAN,CSTNo,EMail,TINNo,CreditLimit,DueDays,PartyType,ECCCode,Excise,Range,Division,STNo,IECCode,TDS_Catg,Zone,LedgerGroup,Distributor,Site_Code,DisplayName,Initial, preparedBy,filterNature,aadhaarNo,gsTinDate,gsTIN) Values (:subCode, :name, :groupCode, :manualCode, :nature, :add1, :add2, :cityCode, :pin, :phone, :mobile, :fax, :contactPerson, :remark, :duplicateTIN, :policyNo, :uEntDt, :costCenter, :pan, :cstNo, :email,:tinNo, :creditLimit, :dueDays, :partyType, :eccCode, :Excise, :range, :division, :stNo, :iecCode,:tdsCatg, :zone, :ledgerGroup, :distributor, :siteCode, :displayName, :initial, :userName,:filterNature,:aadhaarNo,:gsTinDate,:gsTIN)`
- SQL 5: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- SQL 6: `Select Count(*) As Cnt From SubGroup2 Where SubCode= :subCode`
- SQL 7: `update SubGroup2 Set FatherName=:fatherName where SubCode=:subCode`
- Params: `:subCode` ← `DmShared.xNull(mrTemp.getCode())`; `:Name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:tinNo` ← `mrTemp.getTIN()`; `:subCode` ← `mrTemp.getCode()`; `:manualCode` ← `manualCode`; `:subCode` ← `mrTemp.getCode()`; `:cityName` ← `mrTemp.getCityName()`; `:subCode` ← `mrTemp.getCode()`; `:name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupCode())`; `:manualCode` ← `DmShared.xNull(manualCode)`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `DmShared.xNull(mrTemp.getPinCode())`; `:phone` ← `DmShared.xNull(mrTemp.getPhoneNo())`; `:mobile` ← `DmShared.xNull(mrTemp.getMobileNo())`; `:fax` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:contactPerson` ← `DmShared.xNull(mrTemp.getContactPerson())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:duplicateTIN` ← `DmShared.xNull(duplicateCode)`; `:policyNo` ← `DmShared.xNull(mrTemp.getPolicyNo())`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:userName` ← `DmServer.getSsnUserName(rss)`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:pan` ← `DmShared.xNull(mrTemp.getPan().toUpperCase())`; `:cstNo` ← `DmShared.xNull(mrTemp.getCstNo())`; `:email` ← `DmShared.xNull(mrTemp.getEmail())`; `:tinNo` ← `DmShared.xNull(mrTemp.getTIN())`; `:creditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:dueDays` ← `DmServer.vNull(mrTemp.getDueDays(), "0")`; `:partyType` ← `mrTemp.getPartyTypeCode()`; `:eccCode` ← `DmShared.xNull(mrTemp.getECCNo())`; `:Excise` ← `DmShared.xNull(mrTemp.getExciseNo())`; `:range` ← `DmShared.xNull(mrTemp.getRange())`; `:division` ← `DmShared.xNull(mrTemp.getDivision())`; `:stNo` ← `DmShared.xNull(mrTemp.getStNo())`; `:iecCode` ← `DmShared.xNull(mrTemp.getIECNo())`; `:tdsCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategoryCode())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZoneCode())`; `:ledgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroupCode())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getReferenceToCode())`; `:siteCode` ← `DmServer.getSsnSiteCode(rss)`; `:displayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:initial` ← `DmShared.xNull(mrTemp.getAcInitialCode())`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGstin().toUpperCase())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:siteCode` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`; `:subCode` ← `mrTemp.getCode()`; `:subCode` ← `mrTemp.getCode()`; `:fatherName` ← `mrTemp.getFatherName()`; `:fatherName` ← `mrTemp.getFatherName()`; `:subCode` ← `mrTemp.getCode()`
- Errors: "Invalid key." | "Duplicate TIN Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!" | "TIN Should Not Be Greater Than 11(Eleven) Characters." | "For defining GST detail, you should have to fill Party Type first." | "Please fill the Pan No." | "In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date." | "In case of unregistered party type AADHAAR No. or PAN No. is mandatory." | "GSTIN Date should not be greater than current date."
- Other calls: `Composite.getId`, `Composite.getValue`, `ERROR.getId`, `NONE.getId`, `Registered.getId`, `Registered.getValue`, `SUCCESS.getId`, `Unregistered.getId`, `Unregistered.getValue`, `WARNING.getId`

## Server `BFMaster.java` (276 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveBFMaster`
`private int saveBFMaster(Model mrTemp)`

- SQL 1: `SELECT COUNT(code) AS cnt FROM bfMast WHERE code = :code`
- SQL 2: `INSERT INTO bfMast (code, isActive, preparedBy, preparedDt) VALUES (:code, :isActive, :preparedBy, :preparedDt)`
- Params: `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:isActive` ← `mrTemp.getActiveCode()`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`
- Errors: "Active field required only Yes/No." | "BF Active is required."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `CustomerMaster.java` (1518 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSiteGSTIN`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidGSTIN`, `DmShared.isValidPanNo`, `DmShared.isValidText`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Customer 'CT' nature not exists." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveCustomer`
`private int saveCustomer(Model mrTemp)`

- SQL 1: `SELECT COUNT(sG.name) AS cnt FROM subGroup as sG WHERE sG.name = :Name AND sG.subCode <> :subCode`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 4: `SELECT c.cityCode ,sm.numericCode FROM city c left join stateMast sm on c.stateCode = sm.code WHERE c.cityName = :cityName`
- SQL 5: `Insert Into SubGroup(SubCode,Name,GroupCode,ManualCode,Nature,Add1,Add2,CityCode,PIN,registrationNo, Phone,Mobile,FAX,Remark,isDuplicateTIN,U_EntDt,CostCenter,PAN,EMail,TINNo, CreditLimit,DueDays,PartyCat,PartyType,IECCode,TDS_Catg,Zone,LedgerGroup, Distributor,Site_Code,DisplayName,Initial, preparedBy,filterNature,invoiceDiscount, aadhaarNo,gsTinDate,gsTIN) Values (:subCode, :name, :groupCode, :manualCode, :nature, :add1, :add2, :cityCode, :pin,:registrationNo, :phone, :mobile, :fax,:remark, :duplicateTIN, :uEntDt, :costCenter, :pan, :email, :tinNo, :creditLimit, :dueDays, :partyCat, :partyType, :iecCode, :tdsCatg, :zone, :ledgerGroup, :distributor, :siteCode, :displayName, :initial, :userName,:filterNature, :invoiceDiscount,:aadhaarNo,:gsTinDate,:gsTIN)`
- SQL 6: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- SQL 7: `Select Count(*) As Cnt From SubGroup2 Where SubCode= :subCode`
- Params: `:subCode` ← `DmShared.xNull(mrTemp.getCode())`; `:Name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:tinNo` ← `mrTemp.getTIN()`; `:subCode` ← `mrTemp.getCode()`; `:manualCode` ← `manualCode`; `:subCode` ← `mrTemp.getCode()`; `:cityName` ← `mrTemp.getCityName()`; `:subCode` ← `mrTemp.getCode()`; `:initial` ← `DmShared.xNull(mrTemp.getAcInitialCode())`; `:name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:displayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupCode())`; `:manualCode` ← `DmShared.foriegnKey(manualCode)`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `DmShared.xNull(mrTemp.getPinCode())`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:fax` ← `DmShared.xNull(mrTemp.getFaxNo())`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:duplicateTIN` ← `DmServer.vNull(duplicateCode, "0")`; `:userName` ← `DmServer.getSsnUserName(rss)`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:pan` ← `DmShared.xNull(mrTemp.getPan().toUpperCase())`; `:email` ← `mrTemp.getEmail()`; `:tinNo` ← `DmShared.xNull(mrTemp.getTIN())`; `:creditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:dueDays` ← `DmServer.vNull(mrTemp.getCreditPeriod(), "0")`; `:partyCat` ← `DmShared.foriegnKey(mrTemp.getPartyCategoryCode())`; `:partyType` ← `mrTemp.getPartyTypeCode()`; `:iecCode` ← `mrTemp.getIECNo()`; `:tdsCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategoryCode())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZoneCode())`; `:ledgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroupCode())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getReferenceToCode())`; `:siteCode` ← `DmServer.getSsnSiteCode(rss)`; `:registrationNo` ← `DmShared.xNull(mrTemp.getRegistrationNo())`; `:invoiceDiscount` ← `DmServer.vNull(mrTemp.getInvoiceDis(), "0.00")`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGstin().toUpperCase())`; `:uEntDt` ← `DmServer.getServerTimestamp()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:siteCode` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`; `:subCode` ← `mrTemp.getCode()`; `:subCode` ← `mrTemp.getCode()`
- Errors: "Invalid key." | "Duplicate TIN Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!" | "For defining GST detail, you should have to fill Party Type first." | "Please fill the Pan No." | "In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date." | "In case of unregistered party type AADHAAR No. or PAN No. is mandatory." | "GSTIN Date should not be greater than current date." | "TIN Should Be Equal To 11(Eleven) Characters."
- Other calls: `Composite.getId`, `Composite.getValue`, `ERROR.getId`, `NONE.getId`, `Registered.getId`, `Registered.getValue`, `SUCCESS.getId`, `Unregistered.getId`, `Unregistered.getValue`, `WARNING.getId`

## Server `ReelOpening.java` (13 lines)
## Server `ImportData_Server.java` (72 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getSerialNoInteger`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getITSCode`
`public static String getITSCode(String lineNoDisplay, String itsName, Connection cnTemp, DmRSS rss, DmHandelFile handelFile)`

- SQL 1: `Select Count(*) As Cnt From its Where name =:name AND code <> :code`
- SQL 2: `Insert Into its(code,name,U_EntDt,transferDt,preparedBy) Values (:code ,:name, getdate(),:transferDt,:preparedBy)`
- Params: `:name` ← `DmShared.xNull(itsName)`; `:code` ← `DmShared.xNull(itsCode)`; `:code` ← `DmShared.xNull(itsCode)`; `:name` ← `DmShared.xNull(itsName)`; `:preparedBy` ← `DmServer.getSsnUserName(rss)`
- Errors: "Invalid key." | "ITS Name already exists."

## Server `ImportDesignationMaster.java` (331 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `saveDesignation`
`private int saveDesignation(ImportDesignationMaster.Model mrTemp)`

- SQL 1: `SELECT COUNT(dm.name) AS cnt FROM designationMast as dm WHERE LOWER(dm.name) = :name AND dm.code <> :code`
- SQL 2: `SELECT COUNT(dm.manualCode) AS cnt FROM designationMast as dm WHERE LOWER(dm.manualCode) = :shortName AND dm.code <> :code`
- SQL 3: `Insert Into designationMast(code,name,manualCode,level_No,preparedBy,preparedDt,preparedByIP) Values (:code,:name,:manualCode,:level_No,:preparedBy,:preparedDt, :preparedByIP)`
- Params: `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:shortName` ← `DmShared.xNull(mrTemp.getShortName())`; `:code` ← `DmShared.iNull(mrTemp.getCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getName())`; `:manualCode` ← `DmShared.xNull(mrTemp.getShortName())`; `:level_No` ← `DmShared.iNull(mrTemp.getLevelNo()).toString()`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `timestamp`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `SheetOpening.java` (1032 lines)
Helpers used: `DmBaseServer.getCodeViaManualCode`, `DmServer.checkForwardVoucherDateTime`, `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- SQL 1: `SELECT v_Type FROM voucher_type WHERE short_Name = ' {…} '`
- SQL 2: `SELECT e.prod_SheetConversion, e.reamWeightAutoCal FROM enviro e WHERE e.site_Code = :site_Code`
- SQL 3: `SELECT imn.roundOff FROM itemNature imn WHERE imn.manualCode = :manualCode`
- SQL 4: `SELECT 1 FROM barCodeMast WHERE reelBundleNo = :reelBundleNo`
- Params: `:site_Code` ← `siteCode`; `:manualCode` ← `STEnum.ItemNature.SHEET.getManualCode()`; `:reelBundleNo` ← `DmShared.xNull(reelNo)`
- Errors: "Log file path can not be blank." | "Voucher type is not defined!" | "Please enter 'Sheet Conversion Factor' in enviro!" | "Data file path can not be blank." | "Data file not found." | "Duplicate ReelNo("
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `SHEET.getManualCode`, `WARNING.getId`

### `saveItemOpening`
`private int saveItemOpening(String vType, List<HeaderModel> lstHeader)`

- SQL 1: `Insert Into production ( docId, v_Type, v_No, v_Date, v_Prefix, recId, against,remark,itemNature, balancingAc, site_Code, transferDt, preparedBy, recIdPrefix,preparedDt,u_EntDt_LatestLine,preparedByIP) values (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId,:against,:remark,:itemNature, :balancingAc, :site_Code, :transferDt, :preparedBy, :recIdPrefix, :preparedDt, :preparedDt,:preparedByIP)`
- SQL 2: `Delete From productionDetail Where docId= :docId`
- SQL 3: `SELECT im.code, im.subcode FROM itemMast as im WHERE im.name = :name`
- SQL 4: `SELECT um.decimalPlaces as skuDecimalPlaces, im.isBestBefore, im.propertyList, im.filterItemType FROM itemMast as im LEFT JOIN unitmast um ON im.sku = um.code WHERE im.Code =:itemCode`
- SQL 5: `insert into productionDetail(docId, v_SNo, departmentCode, itemCode, quantity, remark ,ibCode,rate,amount,oh_Ac_Item,packCount,barCodeId) values (:docId, :v_SNo, :departmentCode, :itemCode, :quantity, :remark ,:ibCode ,:rate,:amount,:oh_Ac_Item,:packCount,:barCodeId)`
- Params: `:docId` ← `DmShared.iNull(headerModel.getDocId()).toString()`; `:recId` ← `DmShared.xNull(recId.getRecIdValue())`; `:recIdPrefix` ← `DmShared.xNull(recId.getPrefix())`; `:v_Type` ← `DmShared.foriegnKey(vType)`; `:v_Date` ← `DmServer.convertDate(endDate)`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(recId.getCounter()).toString()`; `:site_Code` ← `siteCode`; `:transferDt` ← `null`; `:preparedBy` ← `userName`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:against` ← `String.valueOf(DmEnum.DmStockReceiveAgainst.OTHER.getCode())`; `:itemNature` ← `itemNatureCode`; `:balancingAc` ← `null`; `:remark` ← `null`; `:docId` ← `DmShared.iNull(headerModel.getDocId()).toString()`; `:name` ← `DmShared.xNull(lineModel.getItemProperty_Mdl().getItemName())`; `:itemCode` ← `DmShared.iNull(lineModel.getItemProperty_Mdl().getItemCode()).toString()`; `:docId` ← `DmShared.xNull(headerModel.getDocId())`; `:v_SNo` ← `DmShared.iNull(lineModel.getV_SNo()).toString()`; `:departmentCode` ← `DmShared.foriegnKey(headerModel.getDepartmentCode())`; `:itemCode` ← `DmShared.foriegnKey(lineModel.getItemProperty_Mdl().getItemCode())`; `:quantity` ← `DmServer.vNull(lineModel.getQuantity())`; `:remark` ← `DmShared.xNull(lineModel.getRemark())`; `:rate` ← `DmServer.vNull(lineModel.getSkuRate(), "0.####")`; `:amount` ← `"0"`; `:ibCode` ← `DmShared.foriegnKey(lineModel.getItemProperty_Mdl().getIbCode())`; `:oh_Ac_Item` ← `DmShared.foriegnKey(lineModel.getIsubCode())`; `:packCount` ← `"1"`; `:barCodeId` ← `DmShared.foriegnKey(barCodeId)`
- Errors: "Invalid key."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `CalendarUtil.addDaysToDate`, `ERROR.getId`, `NONE.getId`, `OTHER.getCode`, `REEL.getManualCode`, `SHEET.getManualCode`, `STAccountPosting.iniItemOpening_AcPostList`, `STServer.generateBarCode`, `STServer.saveBarCode`, `SUCCESS.getId`, `Stock_Server.chkRecordInUse`, `Stock_Server.isInventoryAccountingActive`, `Stock_Server.saveStock`, `WARNING.getId`

### `calculateReamsWeightAndTotalWeight`
`private void calculateReamsWeightAndTotalWeight(LineModel mrTempLine, String sheetConversionFactor, String reamsWeightRoundOff)`

- Other calls: `INCH.getName`, `MM.getName`

### `calculateTotalSheets`
`private String calculateTotalSheets(LineModel mrTempLine, String sheetConversionFactor, String totalWeight)`

- Other calls: `INCH.getName`, `MM.getName`

## Server `ServiceMaster.java` (1605 lines)
Helpers used: `DmBaseServer.getServerTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `saveServiceMaster`
`private int saveServiceMaster(Model mrTemp)`

- SQL 1: `select ec.finishedItemUnit as finishedItemUnitCode, um.unit as finishedItemUnitName from enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.finishedItemUnit`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(Name) As Cnt From itemMast mm Where mm.Name = :Name And Code <> :Code`
- SQL 4: `SELECT COUNT(im.smfCode) AS cnt FROM itemMast AS im WHERE smfCode = :smfCode And code <> :code {…}`
- SQL 5: `Select Count(ManualCode) As Cnt From itemmast mm Where mm.ManualCode = :ManualCode And Code <> :Code`
- SQL 6: `Select Count(code) As Cnt From itemSiteList isl Where isl.code = :code`
- SQL 7: `INSERT INTO itemmast ( CODE , NAME , itemGroup , itemType , filterItemType , sku , preparedBY , preparedByIP , preparedDt , transferDt , isActive , itemCategoryCode , manualCode , hsnCode , itemGSTNature , purchaseUnit , saleUnit , purchaseUnitConversionRate , saleUnitConversionRate , allowNegativeStock , displayName , uniqueId , smfCode , usedIn , serviceEvalTypeCode , serviceEvalRate , printSrNo , itsBasedOn , purchaseAc, purchaseContraAc, itemDesc ,purchaseReturnAc, salesAc, salesReturnAc, subCode , grirAc,consumptionAc,wipAc,transitAc,cogsAc,statusChangedBy,statusChangedDate ) VALUES ( :code , :name , :itemGroup , :itemType , :filterItemType , :sku , :preparedBY , :preparedByIP , :preparedDt , NULL , :isActive , :itemCategoryCode , :manualCode , :hsnCode , :itemGSTNature , :purchaseUnit , :saleUnit , :purchaseUnitConversionRate , :saleUnitConversionRate , :allowNegativeStock , :displayName , :uniqueId , :smfCode , :usedIn , :serviceEvalTypeCode , :serviceEvalRate , :printSrNo , :itsBasedOn , :purchaseAc, :purchaseContraAc, :itemDesc, :purchaseReturnAc, :salesAc, :salesReturnAc ,:subCode , :grirAc,:consumptionAc,:wipAc,:transitAc,:cogsAc,:statusChangedBy,:statusChangedDate)`
- SQL 8: `INSERT INTO itemSiteList (CODE, site_Code) VALUES (:code, :site_Code)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getMapWithField())`; `:Name` ← `mrTemp.getServiceName()`; `:Code` ← `mrTemp.getCode()`; `:Code` ← `mrTemp.getCode()`; `:uniqueId` ← `mrTemp.getUniqueId()`; `:smfCode` ← `mrTemp.getMapWithFieldTag()`; `:code` ← `mrTemp.getCode()`; `:usedInCode` ← `DmShared.iNull(mrTemp.getUsedInCode()).intValue()`; `:IUsedInCode` ← `STEnum.ServiceUsedIn.INWARD_TRANSACTION.getId()`; `:OUsedInCode` ← `STEnum.ServiceUsedIn.OUTWARD_TRANSACTION.getId()`; `:IOUsedInCode` ← `STEnum.ServiceUsedIn.BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId()`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:Code` ← `DmShared.iNull(mrTemp.getCode()).toString()`; `:Code` ← `mrTemp.getCode()`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `DmShared.xNull(mrTemp.getServiceName())`; `:itemGroup` ← `DmShared.foriegnKey(mrTemp.getGroupTag())`; `:manualCode` ← `mrTemp.getManualCode()`; `:itemType` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode(mrTemp.getTypeManualCode(), "manualCode"`; `:filterItemType` ← `DmServer.getCodeViaManualCode("OI", "manualCode", "itemType", "code", cnTemp)`; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:isActive` ← `"1"`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getCategoryTag())`; `:hsnCode` ← `DmShared.foriegnKey(mrTemp.getHsn())`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:preparedDt` ← `DmBaseServer.getServerTimestamp()`; `:itemGSTNature` ← `DmShared.xNull(mrTemp.getGstNatureTag())`; `:purchaseContraAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseContraAcTag())`; `:itemDesc` ← `DmShared.xNull(mrTemp.getItemDesc())`; `:purchaseUnit` ← `DmShared.xNull(mrTemp.getPurchaseUnitSkuCode())`; `:saleUnit` ← `DmShared.xNull(mrTemp.getSaleUnitSkuCode())`; `:purchaseUnitConversionRate` ← `DmServer.vNull("1")`; `:saleUnitConversionRate` ← `DmServer.vNull("1")`; `:allowNegativeStock` ← `DmShared.xNull("1")`; `:displayName` ← `DmShared.xNull(mrTemp.getServiceName())`; `:uniqueId` ← `DmShared.foriegnKey(DmShared.removeSpace(mrTemp.getUniqueId()))`; `:smfCode` ← `DmShared.foriegnKey(mrTemp.getMapWithFieldTag())`; `:usedIn` ← `DmShared.foriegnKey(mrTemp.getUsedInCode())`; `:serviceEvalTypeCode` ← `DmShared.foriegnKey(mrTemp.getServiceEvalTypeCode())`; `:serviceEvalRate` ← `DmShared.xNull(mrTemp.getValue())`; `:printSrNo` ← `DmShared.xNull(mrTemp.getPrintSrNo())`; `:itsBasedOn` ← `DmShared.xNull(mrTemp.getTaxBasedOnTag())`; `:purchaseAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseAcTag())`; `:salesAc` ← `DmShared.foriegnKey(mrTemp.getSalesAcTag())`; `:purchaseReturnAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseReturnAcTag())`; `:salesReturnAc` ← `DmShared.foriegnKey(mrTemp.getSalesReturnAcTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getServiceAcTag())`; `:grirAc` ← `DmShared.foriegnKey(mrTemp.getGrirAcTag())`; `:consumptionAc` ← `DmShared.foriegnKey(mrTemp.getConsumptionAcTag())`; `:wipAc` ← `DmShared.foriegnKey(mrTemp.getWipAcTag())`; `:transitAc` ← `DmShared.foriegnKey(mrTemp.getTransitAcTag())`; `:cogsAc` ← `DmShared.foriegnKey(mrTemp.getCogsAcTag())`; `:statusChangedBy` ← `DmServer.getSsnUserName(rss)`; `:statusChangedDate` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`
- Errors: "Invalid key." | "Please define service item unit in enviro setting(company)." | "Invalid service mapping!" | " invalid valuation Type name!" | "Gst Nature is Mandatory" | "ITS Based on is Required" | "Manual Code is mandatory." | "Sevice Name  already exists." | "unique Id already exists." | "Service mapping already assigned!" | "Manual Code already exists." | "Valuation Type is Mandatory."
- Other calls: `BOTH_INWARD_AND_OUTWARD_TRANSACTION.getId`, `ERROR.getId`, `GSTNature.values`, `INWARD_TRANSACTION.getId`, `ItsBasedOn.values`, `NONE.getId`, `OUTWARD_TRANSACTION.getId`, `SUCCESS.getId`, `ServiceEvalType.getViaName`, `ServiceUsedIn.getViaText`, `WARNING.getId`

## Server `ItemGroup.java` (252 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveItemGroup`
`private int saveItemGroup(Model mrTemp)`

- SQL 1: `SELECT COUNT(name) AS cnt FROM itemGroup WHERE name = :name AND code <> :code`
- SQL 2: `INSERT INTO itemGroup (code, name, preparedBy, preparedDt, filterItemType, preparedByIP, transferDt) VALUES (:code,:name,:preparedBy, :preparedDt, :filterType, :preparedByIP, NULL)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName()).toLowerCase()`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `mrTemp.getName()`; `:filterType` ← `DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", cnTemp)`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ItemOpening.java` (1645 lines)
Helpers used: `DmServer.checkForwardVoucherDateTime`, `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.getNumberFormat`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `init`
`public void init(DmRSSPrivate rss)`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- SQL 1: `SELECT v_Type FROM voucher_type WHERE short_Name = {…}`
- SQL 2: `SELECT ec.prod_SizeUnitCode AS sizeUnitCode, um.unit AS sizeUnitName FROM enviroCompany ec LEFT JOIN unitMast um ON um.code = ec.prod_SizeUnitCode`
- SQL 3: `SELECT um.unit, um.decimalPlaces FROM unitMast um WHERE um.unit IN(' {…} ',' {…} ',' {…} ')`
- Errors: "Log file path can not be blank." | "Voucher type is not defined!" | "Size unit is not defined in environment company!" | "Data file path can not be blank." | "Data file not found."
- Other calls: `CM.getName`, `DmImportStatus.getImportStatus`, `ERROR.getId`, `INCH.getName`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `MM.getName`, `WARNING.getId`

### `saveItemOpening`
`private int saveItemOpening(String vType, List<HeaderModel> lstHeader)`

- SQL 1: `Insert Into stockHead (docId, v_Type, v_No, v_Date, v_Prefix, recId,preparedDt, site_Code, transferDt, preparedBy, recIdPrefix,preparedByIP) values (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId,:preparedDt, :site_Code, :transferDt, :preparedBy,:recIdPrefix,:preparedByIP)`
- SQL 2: `Delete From stockHeadDetail Where docId= :docId`
- SQL 3: `SELECT im.code, im.subcode FROM itemMast as im WHERE im.name = :name`
- SQL 4: `SELECT um.decimalPlaces as skuDecimalPlaces, im.isBestBefore, im.propertyList, im.filterItemType FROM itemMast as im LEFT JOIN unitmast um ON im.sku = um.code WHERE im.Code =:itemCode`
- SQL 5: `SELECT count(*) as cnt FROM stockHead as sh LEFT JOIN stockHeadDetail as shd ON sh.docId = shd.docId WHERE sh.site_Code = :site_Code and sh.v_Date=:v_Date and isNull(shd.departmentCode,0)=:departmentCode and isNull(shd.subCode,0)=:receivedFrom and isNull(shd.itemCode,0)=:itemCode and isNull(shd.packSizeCode,0)=:sizeCode and isNull(shd.ibCode,0)=:ibCode`
- SQL 6: `insert into stockHeadDetail(docId, v_SNo, departmentCode, refNo,refDate, oh_Ac_Item, itemCode, quantity, remark,subCode,site_Code,rate,skuRate,skuValue,skuQtyForRate, ibCode,packSizeCode,packCount,skuValueWithTax, barCodeId) values (:docId, :v_SNo, :departmentCode, :refNo, :refDate, :oh_Ac_Item, :itemCode, :quantity, :remark,:subCode,:site_Code,:rate,:skuRate,:skuValue,:skuQtyForRate, :ibCode,:packSizeCode,:packCount,:skuValueWithTax, :barCodeId)`
- Params: `:docId` ← `DmShared.iNull(headerModel.getDocId()).toString()`; `:recId` ← `DmShared.xNull(recId.getRecIdValue())`; `:v_Type` ← `DmShared.foriegnKey(vType)`; `:v_Date` ← `DmServer.convertDate(startDate)`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(recId.getCounter()).toString()`; `:site_Code` ← `siteCode`; `:transferDt` ← `null`; `:preparedBy` ← `userName`; `:recIdPrefix` ← `DmShared.xNull(recId.getPrefix())`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:docId` ← `DmShared.iNull(headerModel.getDocId()).toString()`; `:name` ← `DmShared.xNull(lineModel.getItemProperty_Mdl().getItemName())`; `:itemCode` ← `DmShared.iNull(lineModel.getItemProperty_Mdl().getItemCode()).toString()`; `:site_Code` ← `DmShared.xNull(DmServer.getSsnSiteCode(rss))`; `:v_Date` ← `DmShared.dNull(startDate)`; `:departmentCode` ← `DmShared.iNull(headerModel.getDepartmentCode()).toString()`; `:receivedFrom` ← `DmShared.iNull(headerModel.getReceivedFromCode()).toString()`; `:itemCode` ← `DmShared.iNull(lineModel.getItemProperty_Mdl().getItemCode()).toString()`; `:sizeCode` ← `DmShared.iNull(lineModel.getPackSizeCode()).toString()`; `:ibCode` ← `DmShared.iNull(lineModel.getItemProperty_Mdl().getIbCode()).toString()`; `:docId` ← `DmShared.iNull(headerModel.getDocId()).toString()`; `:v_SNo` ← `DmShared.iNull(lineModel.getV_SNo()).toString()`; `:departmentCode` ← `DmShared.foriegnKey(headerModel.getDepartmentCode())`; `:refNo` ← `DmShared.xNull(lineModel.getRefNo())`; `:refDate` ← `DmServer.convertDate(lineModel.getRefDate())`; `:oh_Ac_Item` ← `DmShared.foriegnKey(lineModel.getIsubCode())`; `:itemCode` ← `DmShared.foriegnKey(lineModel.getItemProperty_Mdl().getItemCode())`; `:quantity` ← `DmServer.vNull(lineModel.getQuantity())`; `:rate` ← `DmServer.vNull(lineModel.getSkuRate())`; `:skuRate` ← `DmServer.vNull(lineModel.getSkuRate())`; `:skuValue` ← `DmServer.vNull(lineModel.getSkuValue(), "0.00")`; `:skuValueWithTax` ← `DmServer.vNull(lineModel.getAmountWithTax(), "0.00")`; `:skuQtyForRate` ← `DmServer.vNull("1")`; `:remark` ← `DmShared.xNull(lineModel.getRemark())`; `:subCode` ← `DmShared.foriegnKey(headerModel.getReceivedFromCode())`; `:site_Code` ← `DmShared.foriegnKey(siteCode)`; `:ibCode` ← `DmShared.foriegnKey(lineModel.getItemProperty_Mdl().getIbCode())`; `:packSizeCode` ← `DmShared.foriegnKey(lineModel.getPackSizeCode())`; `:packCount` ← `DmServer.vNull(lineModel.getPackCount())`; `:barCodeId` ← `DmShared.foriegnKey(lineModel.getReelNo())`
- Errors: "Invalid key."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `BATCH_NO.getPropertyManualCode`, `COLOUR.getPropertyManualCode`, `CalendarUtil.addDaysToDate`, `ERROR.getId`, `EXPIRY_DATE.getPropertyManualCode`, `Grain.getViaText`, `ITEM_MAKE.getPropertyManualCode`, `LOT_NO.getPropertyManualCode`, `MRP.getPropertyManualCode`, `NONE.getId`, `SIZE.getPropertyManualCode`, `STAccountPosting.iniItemOpening_AcPostList`, `SUCCESS.getId`, `Stock_Server.chkRecordInUse`, `Stock_Server.isInventoryAccountingActive`, `Stock_Server.saveStock`, `WARNING.getId`

### `getIbCode`
`protected String getIbCode(ItemProperty_Mdl mdl, Connection cnTemp, DmRSSPrivate rss) throws Exception`

- Other calls: `ItemBatch_Server.createItemBatch`

### `convertSizeValue`
`private String[] convertSizeValue(String sizeValue)`

- Other calls: `CM.getName`, `INCH.getName`, `MM.getName`

## Server `EmployeeMaster.java` (1517 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `saveEmployeeRecord`
`private int saveEmployeeRecord(Model mrTemp, DmRSSPrivate rss)`

- SQL 1: `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 4: `SELECT c.cityCode FROM city c WHERE c.cityName = :cityName`
- SQL 5: `Insert Into SubGroup(SubCode,Name,GroupCode,ManualCode ,Nature,Add1,Add2,filterNature,CityCode,PIN ,Phone,Mobile,Remark,PAN,EMail, tds_Catg,Zone,DisplayName,Initial, preparedBy , preparedDt, ifscCode,isActive, site_code,aadhaarNo )Values (:subCode, :name, :groupCode, :manualCode, :nature, :add1, :add2, :filterNature, :cityCode,:pin, :phone, :mobile,:remark,:pan,:email,:tds_Catg, :zone, :displayName, :initial, :preparedBy, :preparedDt, :ifscCode,:isActive, :siteCode,:aadhaarNo)`
- SQL 6: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode, :siteCode) ;`
- SQL 7: `Select Count(*) As Cnt From SubGroup2 Where SubCode= :subCode`
- Params: `:name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:subCode` ← `mrTemp.getCode()`; `:manualCode` ← `manualCode`; `:subCode` ← `mrTemp.getCode()`; `:cityName` ← `mrTemp.getCityName()`; `:subCode` ← `mrTemp.getCode()`; `:name` ← `mrTemp.getAccountName()`; `:groupCode` ← `mrTemp.getAcGroupCode()`; `:manualCode` ← `mrTemp.getManualCode()`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:add1` ← `mrTemp.getAddress1()`; `:add2` ← `mrTemp.getAddress2()`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `mrTemp.getPinCode()`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:remark` ← `mrTemp.getRemark()`; `:pan` ← `mrTemp.getPan()`; `:email` ← `mrTemp.getEmail()`; `:tds_Catg` ← `DmShared.foriegnKey(mrTemp.getTdsCategoryCode())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZoneCode())`; `:displayName` ← `mrTemp.getDisplayName()`; `:initial` ← `mrTemp.getAcInitialCode()`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:siteCode` ← `DmServer.getSsnSiteCode(rss)`; `:ifscCode` ← `mrTemp.getIfsccode()`; `:isActive` ← `DmShared.iNull("1").toString()`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaarNo())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:siteCode` ← `DmServer.getSsnSiteCode(rss)`; `:subCode` ← `mrTemp.getCode()`; `:subCode` ← `mrTemp.getCode()`; `:genderCode` ← `DmShared.foriegnKey(mrTemp.getGenderCode())`; `:maritalStatusCode` ← `DmShared.foriegnKey(mrTemp.getMaritalStatusCode())`; `:religionCode` ← `DmShared.foriegnKey(mrTemp.getReligionCode())`; `:fatherName` ← `mrTemp.getFatherName()`; `:departmentCode` ← `DmShared.foriegnKey(mrTemp.getDepartmentCode())`; `:DesignationCode` ← `DmShared.foriegnKey(mrTemp.getDesignationCode())`; `:tADD1` ← `DmShared.xNull(mrTemp.getTempAdd1())`; `:tADD2` ← `DmShared.xNull(mrTemp.getTempAdd2())`; `:tCityCode` ← `DmShared.foriegnKey(mrTemp.getTempCityCode())`; `:tPIN` ← `DmShared.xNull(mrTemp.getTempPin())`; `:dob` ← `DmServer.convertDate(mrTemp.getDob())`; `:doa` ← `DmServer.convertDate(mrTemp.getDoa())`; `:doj` ← `DmServer.convertDate(mrTemp.getDoj())`; `:dor` ← `DmServer.convertDate(mrTemp.getDor())`; `:bloodGroupCode` ← `DmShared.foriegnKey(mrTemp.getBloodGroupCode())`; `:pfAcNo` ← `mrTemp.getPfAccNo()`; `:bankAcNo` ← `mrTemp.getBankAccNo()`; `:licenseNo` ← `mrTemp.getLicenseNo()`; `:licenseExpiry` ← `DmServer.convertDate(mrTemp.getLicenseExpiry())`; `:passportNo` ← `mrTemp.getPassportNo()`; `:pIssueDate` ← `DmServer.convertDate(mrTemp.getPassportIssueDate())`; `:pExpDate` ← `DmServer.convertDate(mrTemp.getPassportExpirydDate())`; `:pIssuePlace` ← `mrTemp.getPassportIssuePlace()`; `:qualification` ← `mrTemp.getQualification()`; `:spouseName` ← `DmShared.removeSpace(mrTemp.getSpouseName())`
- Errors: "Invalid key." | "Duplicate Name Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!"
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ReelOpeningBkp.java` (797 lines)
Helpers used: `DmBaseServer.getCodeViaManualCode`, `DmServer.checkForwardVoucherDateTime`, `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- SQL 1: `SELECT v_Type FROM voucher_type WHERE short_Name = ' {…} '`
- SQL 2: `SELECT 1 FROM barCodeMast WHERE reelBundleNo = :reelBundleNo`
- Params: `:reelBundleNo` ← `DmShared.xNull(reelNo)`
- Errors: "Log file path can not be blank." | "Voucher type is not defined!" | "Data file path can not be blank." | "Data file not found." | "Duplicate ReelNo("
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `saveItemOpening`
`private int saveItemOpening(String vType, List<HeaderModel> lstHeader)`

- SQL 1: `Insert Into production ( docId, v_Type, v_No, v_Date, v_Prefix, recId, against,remark,itemNature, balancingAc, site_Code, transferDt, preparedBy, recIdPrefix,preparedDt,u_EntDt_LatestLine,preparedByIP) values (:docId, :v_Type, :v_No, :v_Date, :v_Prefix, :recId,:against,:remark,:itemNature, :balancingAc, :site_Code, :transferDt, :preparedBy, :recIdPrefix, :preparedDt, :preparedDt,:preparedByIP)`
- SQL 2: `Delete From productionDetail Where docId= :docId`
- SQL 3: `SELECT im.code, im.subcode FROM itemMast as im WHERE im.name = :name`
- SQL 4: `SELECT um.decimalPlaces as skuDecimalPlaces, im.isBestBefore, im.propertyList, im.filterItemType FROM itemMast as im LEFT JOIN unitmast um ON im.sku = um.code WHERE im.Code =:itemCode`
- SQL 5: `insert into productionDetail(docId, v_SNo, departmentCode, itemCode, quantity, remark ,ibCode,rate,amount,oh_Ac_Item,packCount,barCodeId) values (:docId, :v_SNo, :departmentCode, :itemCode, :quantity, :remark ,:ibCode ,:rate,:amount,:oh_Ac_Item,:packCount,:barCodeId)`
- Params: `:docId` ← `DmShared.iNull(headerModel.getDocId()).toString()`; `:recId` ← `DmShared.xNull(recId.getRecIdValue())`; `:recIdPrefix` ← `DmShared.xNull(recId.getPrefix())`; `:v_Type` ← `DmShared.foriegnKey(vType)`; `:v_Date` ← `DmServer.convertDate(endDate)`; `:v_Prefix` ← `voucherPrefix`; `:v_No` ← `DmShared.iNull(recId.getCounter()).toString()`; `:site_Code` ← `siteCode`; `:transferDt` ← `null`; `:preparedBy` ← `userName`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:against` ← `String.valueOf(DmEnum.DmStockReceiveAgainst.OTHER.getCode())`; `:itemNature` ← `itemNatureCode`; `:balancingAc` ← `null`; `:remark` ← `null`; `:docId` ← `DmShared.iNull(headerModel.getDocId()).toString()`; `:name` ← `DmShared.xNull(lineModel.getItemProperty_Mdl().getItemName())`; `:itemCode` ← `DmShared.iNull(lineModel.getItemProperty_Mdl().getItemCode()).toString()`; `:docId` ← `DmShared.xNull(headerModel.getDocId())`; `:v_SNo` ← `DmShared.iNull(lineModel.getV_SNo()).toString()`; `:departmentCode` ← `DmShared.foriegnKey(headerModel.getDepartmentCode())`; `:itemCode` ← `DmShared.foriegnKey(lineModel.getItemProperty_Mdl().getItemCode())`; `:quantity` ← `DmServer.vNull(lineModel.getQuantity())`; `:remark` ← `DmShared.xNull(lineModel.getRemark())`; `:rate` ← `DmServer.vNull(lineModel.getSkuRate(), "0.####")`; `:amount` ← `"0"`; `:ibCode` ← `DmShared.foriegnKey(lineModel.getItemProperty_Mdl().getIbCode())`; `:oh_Ac_Item` ← `DmShared.foriegnKey(lineModel.getIsubCode())`; `:packCount` ← `"1"`; `:barCodeId` ← `DmShared.foriegnKey(barCodeId)`
- Errors: "Invalid key."
- Other calls: `AccountPosting_Server.saveAccountPosting`, `CalendarUtil.addDaysToDate`, `ERROR.getId`, `NONE.getId`, `OTHER.getCode`, `REEL.getManualCode`, `REEL_PRODUCTION.getCode`, `STAccountPosting.iniItemOpening_AcPostList`, `STServer.generateBarCode`, `STServer.saveBarCode`, `SUCCESS.getId`, `Stock_Server.chkRecordInUse`, `Stock_Server.isInventoryAccountingActive`, `Stock_Server.saveStock`, `WARNING.getId`

## Server `JournalVoucher.java` (855 lines)
Helpers used: `DmServer.convertDate`, `DmServer.convertToJson`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getDocId`, `DmServer.getLocalIPAddresses`, `DmServer.getRecId`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.getSsnVoucherPrefix`, `DmServer.prepareContraText`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.vNull`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- SQL 1: `SELECT v_Type FROM voucher_type WHERE short_Name = {…}`
- Errors: "Log file path can not be blank." | "Voucher type is not defined!" | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `saveVoucherEntry`
`private int saveVoucherEntry(String vType, List<HeaderModel> lstHeader)`

- SQL 1: `SELECT COUNT(RefNo) AS cnt FROM Ledger WHERE RefNo = :RefNo AND RefDate <> :RefDate`
- SQL 2: `Insert Into LedgerM (DocId, V_Type, V_Prefix, Site_Code, V_No, V_Date, Narration, RecId, recIdPrefix , PreparedBy,remark, preparedDt, preparedByIP) Values (:DocId,:V_Type,:V_Prefix,:Site_Code , :V_No, :V_Date, :Narration , :RecId, :recIdPrefix , :preparedBy, :remark, :preparedDt, :preparedByIP)`
- SQL 3: `Insert Into Ledger (DocId, RecId, recIdPrefix, v_No, V_SNo, V_Date, SubCode, AmtDr, AmtCr , Narration, V_Type, V_Prefix, Site_Code, isSystem_Generated , Chq_No, Chq_Date, RefNo, RefDate, remark, clg_Date, ContraSub, ContraText) Values (:DocId, :RecId, :recIdPrefix, :v_No, :V_SNo, :V_Date, :SubCode, :AmtDr, :AmtCr , :Narration, :V_Type, :V_Prefix, :Site_Code, '0' , :Chq_No, :Chq_Date, :RefNo , :RefDate, :remark, :clg_Date, :ContraSub, :ContraText)`
- SQL 4: `SELECT CAST((SUM(AmtDr) - SUM(AmtCr)) AS DECIMAL(18,2)) AS diffDrCr FROM Ledger WHERE DocId = :DocId`
- Params: `:RefNo` ← `DmShared.xNull(headerModel.getReferenceNo())`; `:RefDate` ← `DmServer.convertDate(headerModel.getReferenceDate())`; `:DocId` ← `DmShared.iNull(headerModel.getDocId()).toString()`; `:RecId` ← `DmShared.xNull(recid.getRecIdValue())`; `:V_Type` ← `DmShared.foriegnKey(vType)`; `:V_Date` ← `DmServer.convertDate(headerModel.getReferenceDate())`; `:V_Prefix` ← `voucherPrefix`; `:V_No` ← `DmShared.iNull(recid.getCounter()).toString()`; `:Narration` ← `""`; `:remark` ← `DmShared.xNull(headerModel.getRemark())`; `:Site_Code` ← `siteCode`; `:preparedBy` ← `userName`; `:recIdPrefix` ← `DmShared.xNull(recid.getPrefix())`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:DocId` ← `DmShared.iNull(headerModel.getDocId()).toString()`; `:RecId` ← `DmShared.xNull(recid.getRecIdValue())`; `:V_Type` ← `DmShared.foriegnKey(vType)`; `:V_Date` ← `DmServer.convertDate(headerModel.getReferenceDate())`; `:V_Prefix` ← `voucherPrefix`; `:recIdPrefix` ← `DmShared.xNull(recid.getPrefix())`; `:V_No` ← `DmShared.iNull(recid.getCounter()).toString()`; `:V_SNo` ← `DmShared.iNull(lineModel.getV_SNo()).toString()`; `:SubCode` ← `DmShared.foriegnKey(lineModel.getAccountNameCode())`; `:AmtDr` ← `DmServer.vNull(lineModel.getAmtDr())`; `:AmtCr` ← `DmServer.vNull(lineModel.getAmtCr())`; `:remark` ← `DmShared.xNull(lineModel.getNarration())`; `:Narration` ← `narrationTemp`; `:Site_Code` ← `siteCode`; `:RefNo` ← `DmShared.xNull(headerModel.getReferenceNo())`; `:RefDate` ← `DmServer.convertDate(headerModel.getReferenceDate())`; `:Chq_No` ← `DmShared.xNull(lineModel.getInstrumentNo())`; `:Chq_Date` ← `DmServer.convertDate(lineModel.getInstrumentDate())`; `:clg_Date` ← `DmServer.convertDate(lineModel.getInstrumentClearDate())`; `:ContraSub` ← `DmShared.foriegnKey(contra)`; `:ContraText` ← `contraTextJV`; `:DocId` ← `DmShared.xNull(headerModel.getDocId())`
- Errors: "Invalid key." | "Future date entry is not allowed" | "Record with same Reference No. already exists." | "Debit and Credit amounts are not balanced."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `ItemMaster.java` (3129 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoBigInt`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `saveItemMaster`
`private int saveItemMaster(Model mrTemp)`

- SQL 1: `Select code,manualCode From itemType it Where it.name =:itemName`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(ManualCode) As Cnt From itemmast mm Where mm.ManualCode = :ManualCode And Code <> :Code`
- SQL 4: `Select Count(Name) As Cnt From itemMast mm Where mm.Name = :Name`
- SQL 5: `INSERT INTO itemmast (code, name, displayName, itemGroup,itemType, filterItemType, sku, preparedBY, preparedDt, transferDt,itemCategoryCode, manualCode,itemDesc,isBestBefore,isHighValue,isFlowThrough, isConsignment,isReturnableItem,isBilledItem, itemGSTNature,purchaseUnit,saleUnit ,isPOMandatory, tl_ExcessPer, tl_ShortagePer, wtaCode,isActive, isIndentMandatory, isQuotationMandatory,isLabRequired , isDiscountAllow,isSaleRateEditable , purchaseUnitConversionRate,saleUnitConversionRate,itemPacking,allowNegativeStock,hsnCode,itsBasedOn , purchaseAc,salesAc,subCode, purchaseReturnAc ,preparedByIP, salesReturnAc ,grirAc,consumptionAc,wipAc,transitAc,cogsAc, itemDeptNature,purchaseContraAc,transferAc,propertyList,statusChangedBy,statusChangedDate) VALUES (:code, :name, :displayName,:itemGroup, :itemType, :filterItemType, :sku, :preparedBY, :preparedDt, NULL, :itemCategoryCode, :manualCode,:itemDesc,:isBestBefore,:isHighValue,:isFlowThrough, :isConsignment,:isReturnableItem,:isBilledItem ,:itemGSTNature,:purchaseUnit,:saleUnit , :isPOMandatory, :tl_ExcessPer, :tl_ShortagePer, :wtaCode, :isActive , :isIndentMandatory , :isQuotationMandatory ,:isLabRequired , :isDiscountAllow, :isSaleRateEditable , :purchaseUnitConversionRate,:saleUnitConversionRate,:itemPacking,:allowNegativeStock,:hsnCode,:itsBasedOn , :purchaseAc,:salesAc,:subCode, :purchaseReturnAc,:preparedByIP , :salesReturnAc ,:grirAc,:consumptionAc,:wipAc,:transitAc,:cogsAc, :itemDeptNature,:purchaseContraAc,:transferAc,:propertyList,:statusChangedBy,:statusChangedDate) ;`
- SQL 6: `INSERT INTO itemSiteList (CODE, site_Code) VALUES (:code, :site_Code)`
- Params: `:itemName` ← `DmShared.xNull(mrTemp.getItemType()).trim()`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:Code` ← `DmShared.iNull(mrTemp.getCode()).toString()`; `:itemName` ← `DmShared.xNull(mrTemp.getItemType()).trim()`; `:Name` ← `DmShared.xNull(mrTemp.getItemTypeText())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:name` ← `DmShared.xNull(mrTemp.getItemNameText())`; `:displayName` ← `DmShared.xNull(mrTemp.getItemNameText())`; `:itemGroup` ← `DmShared.foriegnKey(mrTemp.getItemGroupTag())`; `:hsnCode` ← `DmShared.foriegnKey(mrTemp.getHsnCode())`; `:manualCode` ← `mrTemp.getManualCode()`; `:itemDesc` ← `DmShared.xNull(mrTemp.getItemDescription())`; `:itemType` ← `DmShared.foriegnKey(mrTemp.getItemTypeCode())`; `:filterItemType` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode("SM", "manualCode", "itemType", "code", `; `:sku` ← `DmShared.foriegnKey(mrTemp.getSkuTag())`; `:isActive` ← `"1"`; `:itemCategoryCode` ← `DmShared.foriegnKey(mrTemp.getCategoryTag())`; `:isBestBefore` ← `DmShared.xNull(mrTemp.getBestBeforeTag())`; `:isHighValue` ← `DmShared.xNull(mrTemp.getHighValueTag())`; `:isFlowThrough` ← `DmShared.xNull(mrTemp.getFlowThroughTag())`; `:isConsignment` ← `DmShared.xNull(mrTemp.getConsignmentTag())`; `:isReturnableItem` ← `DmShared.xNull(mrTemp.getSalesReturnAllowTag())`; `:isBilledItem` ← `DmShared.xNull(mrTemp.getBilledItemTag())`; `:itemGSTNature` ← `DmShared.xNull(mrTemp.getGstNatureTag())`; `:purchaseUnit` ← `DmShared.xNull(mrTemp.getPurchaseUnitCode())`; `:saleUnit` ← `DmShared.xNull(mrTemp.getSaleUnitCode())`; `:tl_ExcessPer` ← `DmShared.xNull(mrTemp.getTolerancePlus())`; `:tl_ShortagePer` ← `DmShared.xNull(mrTemp.getToleranceMinus())`; `:isPOMandatory` ← `DmShared.xNull(mrTemp.getPoReqTag())`; `:isLabRequired` ← `DmShared.iNull(mrTemp.getLabRequiredTag()).toString()`; `:isIndentMandatory` ← `DmShared.xNull(mrTemp.getIndentReqTag())`; `:isQuotationMandatory` ← `DmShared.xNull(mrTemp.getQuotationReqTag())`; `:purchaseUnitConversionRate` ← `DmServer.vNull(mrTemp.getConversionRatePurchase())`; `:saleUnitConversionRate` ← `DmServer.vNull(mrTemp.getConversionRateSale())`; `:itemPacking` ← `DmShared.xNull(mrTemp.getItemDescription())`; `:allowNegativeStock` ← `DmShared.xNull(mrTemp.getAllowNegetiveStockCode())`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:wtaCode` ← `""`; `:itsBasedOn` ← `DmShared.iNull(mrTemp.getItsBasedOnTag()).toString()`; `:purchaseAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseAcTag())`; `:salesAc` ← `DmShared.foriegnKey(mrTemp.getSalesAcTag())`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getInventoryAcTag())`; `:purchaseReturnAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseReturnAcTag())`; `:salesReturnAc` ← `DmShared.foriegnKey(mrTemp.getSalesReturnAcTag())`; `:isDiscountAllow` ← `DmShared.iNull(mrTemp.getSaleDiscountAllowTag()).toString()`; `:isSaleRateEditable` ← `DmShared.iNull(mrTemp.getSaleRateEditableTag()).toString()`; `:grirAc` ← `DmShared.foriegnKey(mrTemp.getGrirAcTag())`; `:consumptionAc` ← `DmShared.foriegnKey(mrTemp.getConsumptionAcTag())`; `:wipAc` ← `DmShared.foriegnKey(mrTemp.getWipAcTag())`; `:transitAc` ← `DmShared.foriegnKey(mrTemp.getTransitAcTag())`; `:cogsAc` ← `DmShared.foriegnKey(mrTemp.getCogsAcTag())`; `:itemDeptNature` ← `DmShared.foriegnKey(mrTemp.getItemDepartmentNatureTag())`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:purchaseContraAc` ← `DmShared.foriegnKey(mrTemp.getPurchaseContraAcTag())`; `:transferAc` ← `DmShared.foriegnKey(mrTemp.getTransferAcTag())`; `:propertyList` ← `DmShared.xNull(mrTemp.getPropertyList())`; `:statusChangedBy` ← `DmServer.getSsnUserName(rss)`; `:statusChangedDate` ← `DmServer.getServerTimestamp()`; `:code` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:site_Code` ← `DmShared.foriegnKey(DmServer.getSsnSiteCode(rss))`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:v_SNo` ← `"1"`; `:sNo` ← `"1"`; `:rateFrom` ← `"0.00"`; `:rateUpto` ← `"0.00"`; `:gstTypeCode` ← `DmShared.foriegnKey(mrTemp.getGstTypeCode())`
- Errors: "Invalid key." | "Allow negative stock cannot be updated with </b>Yes</b> " | "Item Type is Mandatory" | "Item Group is Mandatory" | "Item Category is Mandatory" | "HSN is Mandatory" | "Gst Nature is Mandatory" | "Consignment is Mandatory" | "Best Before is Mandatory" | "Flow Through is Mandatory" | "High Value is Mandatory" | "Lab Required is Mandatory" | "Billed Item is Mandatory" | "Indent Required is Mandatory" | "Quotation Required is Mandatory" | "PO Required is Mandatory" | "Sale Returnable Item is Mandatory" | "Sale Discount Allow is Mandatory" | "Sale Rate Editable is Mandatory" | "Allow Negative Stock is Mandatory" | "SKU is Mandatory" | "PurchaseUnit is Mandatory" | "SaleUnit is Mandatory" | "ITS Based on is Required" | "Serial number already Exist for batch no." | "Serial number not required in case of BatchNo is No." | "Serial number already Exist for colour." | "Serial number not required in case of Colour is No." | "Serial number already Exist for LotNo." | "Serial number not required in case of LotNo is No." | "Serial number already Exist for Bf." | "Serial number not required in case of Bf is No." | "Serial number already Exist for gsm." | "Serial number not required in case of Gsm is No." | "Serial number already Exist for sheetPerReam." | "Serial number not required in case of SheetPerReam is No." | "Serial number already Exist for expiry date." | "Serial number not required in case of ExpiryDate is No." | "Serial number already Exist for item make." | "Serial number not required in case of ItemMake is No." | "Serial number already Exist for size." | "Serial number not required in case of Size is No." | "Serial number already Exist for length." | "Serial number not required in case of Length is No." | "Serial number already Exist for" | "Serial number not required in case of Width is No." | "Serial number already Exist for ream weight." | "Serial number not required in case of ReemWeight is No." | "Serial number already Exist for ream per bundle" | "Serial number not required in case of ReemsPerBundle is No." | "Manual Code is mandatory." | "Manual Code already exists." | "Purchase conversion rate should be one" | "Sale conversion rate should be one" | "Item Name already exists."
- Other calls: `BATCH_NO.getPropertyManualCode`, `BF.getPropertyManualCode`, `COLOUR.getPropertyManualCode`, `ERROR.getId`, `EXPIRY_DATE.getPropertyManualCode`, `GSM.getPropertyManualCode`, `GSTNature.values`, `ITEM_MAKE.getPropertyManualCode`, `ItsBasedOn.values`, `LENGTH.getPropertyManualCode`, `LOT_NO.getPropertyManualCode`, `LabRequired.values`, `NONE.getId`, `REAMS_PER_BUNDLE.getPropertyManualCode`, `REAM_WEIGHT.getPropertyManualCode`, `SHEETS_PER_REAM.getPropertyManualCode`, `SIZE.getPropertyManualCode`, `SUCCESS.getId`, `Stock_Server.isInventoryAccountingActive`, `WARNING.getId`, `WIDTH.getPropertyManualCode`

### `getYesNoCode`
`private String getYesNoCode(String str)`


## Server `PartyMaster.java` (1831 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getFieldUDT`, `DmServer.getSerialNoBigInt`, `DmServer.getServerDateTime`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Supplier 'SU' nature not exists." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`, `WARNING.getId`

### `savePartyRecord`
`private int savePartyRecord(Model mrTemp, DmRSSPrivate rss)`

- SQL 1: `Select Count(*) As Cnt From SubGroup Where Name = :name and SubCode<> :subCode`
- SQL 2: `SELECT vt.v_Type FROM voucher_type vt WHERE vt.category = ' {…} '`
- SQL 3: `Select Count(*) As Cnt From SubGroup where ManualCode = :manualCode and SubCode<> :subCode`
- SQL 4: `Select manualCode,code From subGroupNature where Name = :name`
- SQL 5: `SELECT c.cityCode ,sm.numericCode FROM city c left join stateMast sm on c.stateCode = sm.code WHERE c.cityName = :cityName`
- SQL 6: `Insert Into SubGroup(SubCode,Name ,groupCode,acGroupDr,acGroupCr,ManualCode,Nature,Add1,Add2,CityCode,PIN,registrationNo, Phone,Mobile,Remark,preparedDt,CostCenter,PAN,EMail,CreditLimit,DueDays,PartyCat,PartyType,TDS_Catg,Zone,LedgerGroup,Distributor,Site_Code,DisplayName,Initial, preparedBy,filterNature ,aadhaarNo,gsTinDate,gsTIN, LegalName ,tcs_Catg,isTcsActive,transporterId, tradeName, areaCode, statusChangedBy, statusChangedDt) Values (:subCode, :name, :groupCode, :acGroupDr, :acGroupCr, :manualCode, :nature, :add1, :add2, :cityCode, :pin,:registrationNo, :phone, :mobile, :remark, :preparedDt, :costCenter, :pan, :email,:creditLimit, :dueDays, :partyCat, :partyType,:tdsCatg, :zone, :ledgerGroup, :distributor , :siteCode, :displayName, :initial, :preparedBy,:filterNature, :aadhaarNo,:gsTinDate,:gsTIN, :legalName , :tcs_Catg , :isTcsActive , :transporterId, :tradeName, :areaCode, :statusChangedBy, :statusChangedDt)`
- SQL 7: `INSERT INTO subgroupsitelist (subCode, site_Code) VALUES (:subCode,:siteCode) ;`
- SQL 8: `Select Count(*) As Cnt From SubGroup2 Where SubCode= :subCode`
- SQL 9: `SELECT {…} AS areaName`
- SQL 10: `Insert Into subGroupAreaList(subCode,areaCode) values( :subCode,:areaCode)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:subCode` ← `mrTemp.getCode()`; `:manualCode` ← `manualCode`; `:subCode` ← `mrTemp.getCode()`; `:name` ← `DmShared.xNull(mrTemp.getNatureName()).trim()`; `:cityName` ← `mrTemp.getCityName()`; `:subCode` ← `mrTemp.getCode()`; `:name` ← `DmShared.xNull(mrTemp.getAccountName())`; `:groupCode` ← `DmShared.foriegnKey(mrTemp.getAcGroupCode())`; `:acGroupDr` ← `DmShared.foriegnKey(mrTemp.getDebitAcGroupCode())`; `:acGroupCr` ← `DmShared.foriegnKey(mrTemp.getCreditAcGroupCode())`; `:manualCode` ← `DmShared.foriegnKey(mrTemp.getManualCode())`; `:nature` ← `DmShared.foriegnKey(mrTemp.getNatureCode())`; `:add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:filterNature` ← `DmShared.foriegnKey(filterNature)`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:pin` ← `DmShared.xNull(mrTemp.getPinCode())`; `:phone` ← `mrTemp.getPhoneNo()`; `:mobile` ← `mrTemp.getMobileNo()`; `:remark` ← `DmShared.xNull(mrTemp.getRemark())`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:costCenter` ← `DmShared.foriegnKey(mrTemp.getCostCenterCode())`; `:pan` ← `DmShared.xNull(mrTemp.getPan().toUpperCase())`; `:email` ← `mrTemp.getEmail()`; `:creditLimit` ← `DmServer.vNull(mrTemp.getCreditLimit(), "0.00")`; `:dueDays` ← `DmServer.vNull(mrTemp.getCreditPeriod(), "0")`; `:partyCat` ← `DmShared.foriegnKey(mrTemp.getPartyCategoryCode())`; `:partyType` ← `mrTemp.getPartyTypeCode()`; `:tdsCatg` ← `DmShared.foriegnKey(mrTemp.getTdsCategoryCode())`; `:zone` ← `DmShared.foriegnKey(mrTemp.getZoneCode())`; `:ledgerGroup` ← `DmShared.foriegnKey(mrTemp.getLedgerGroupCode())`; `:distributor` ← `DmShared.foriegnKey(mrTemp.getReferenceToCode())`; `:siteCode` ← `DmServer.getSsnSiteCode(rss)`; `:displayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:legalName` ← `DmShared.xNull(mrTemp.getLegalName())`; `:initial` ← `DmShared.xNull(mrTemp.getAcInitialCode())`; `:registrationNo` ← `DmShared.xNull(mrTemp.getRegistrationNo())`; `:aadhaarNo` ← `DmShared.xNull(mrTemp.getAadhaar())`; `:gsTinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:gsTIN` ← `DmShared.xNull(mrTemp.getGstin()).toUpperCase()`; `:tcs_Catg` ← `DmShared.foriegnKey(mrTemp.getTCSCategoryCode())`; `:isTcsActive` ← `DmShared.iNull(getYesNoCode(mrTemp.getTCSActivetag())).toString()`; `:transporterId` ← `DmShared.xNull(mrTemp.getTransporterId())`; `:tradeName` ← `DmShared.xNull(mrTemp.getTradeName())`; `:areaCode` ← `DmShared.foriegnKey(mrTemp.getAreaCode())`; `:statusChangedBy` ← `DmServer.getSsnUserName(rss)`; `:statusChangedDt` ← `DmServer.getServerTimestamp()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getCode())`; `:siteCode` ← `DmShared.foriegnKey(siteCode)`; `:subCode` ← `mrTemp.getCode()`; `:SubCode` ← `mrTemp.getCode()`; `:msmeCode` ← `DmShared.foriegnKey(mrTemp.getMSMETypeCode())`; `:msmeNo` ← `DmShared.xNull(mrTemp.getMSMENo())`; `:subCode` ← `DmShared.xNull(mrTemp.getCode())`; `:areaCode` ← `DmShared.foriegnKey(areaCode)`
- Errors: "Invalid key." | "PAN Should Be Of 10(Ten) Characters." | "Duplicate Party Name Not Allowed !!!" | "Duplicate Manual Code Not Allowed !!!" | "TCS Active is Mandatory" | "For defining GST detail, you should have to fill Party Type first." | "In case of unregistered party type, you should not fill GSTIN No. and GSTIN Date." | "GSTIN Date should not be greater than current date." | "AADHAAR No. should be 12 digit." | "TIN Should Be Equal To 11(Eleven) Characters."
- Other calls: `Composite.getId`, `Composite.getValue`, `ERROR.getId`, `NONE.getId`, `Registered.getId`, `Registered.getValue`, `SUCCESS.getId`, `Unregistered.getId`, `Unregistered.getValue`, `WARNING.getId`

### `getYesNoCode`
`private String getYesNoCode(String str)`


## Server `AssetItemMaster.java` (702 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveAssetItem`
`private int saveAssetItem(GroupHeader_Mdl mrTemp)`

- SQL 1: `select COUNT(um.name) as cnt from assetItemMast as um where um.name = :name AND um.code <> :code`
- SQL 2: `insert Into assetItemMast(code, name, manualCode, assetGroupCode, assetCatCode, assetLife, assetDepRate, depMethod, preparedBy, u_EntDt) values (:code, :name, :manualCode, :assetGroupCode, :assetCatCode, :assetLife, :assetDepRate, :depMethod, :preparedBy, :u_EntDt)`
- SQL 3: `DELETE FROM assetItemProperties WHERE code = :code`
- SQL 4: `INSERT INTO assetItemProperties (code, propertyCode, sno, nature) VALUES (:code, :propertyCode, :sno, :nature)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `mrTemp.getCode()`; `:name` ← `mrTemp.getName()`; `:manualCode` ← `mrTemp.getShortName()`; `:assetGroupCode` ← `mrTemp.getAssetGroupCode()`; `:assetCatCode` ← `mrTemp.getAssetCategoryCode()`; `:assetLife` ← `mrTemp.getAssetLife()`; `:assetDepRate` ← `mrTemp.getAssetDepRate()`; `:depMethod` ← `mrTemp.isDepMethod() ? "0" : "1"`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:u_EntDt` ← `DmServer.getServerTimestamp()`; `:code` ← `mrTemp.getCode()`; `:code` ← `mrTemp.getCode()`; `:propertyCode` ← `node.getCode()`; `:sno` ← `node.getPrintSno()`; `:nature` ← `node.isExposed() ? "1" : "0"`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `HSNMaster.java` (459 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveHSNMast`
`private int saveHSNMast(Model mrTemp) throws Exception`

- SQL 1: `SELECT COUNT(name) AS cnt FROM hsnMast WHERE name = :name AND code <> :code`
- SQL 2: `Insert Into hsnMast(code,name,hsn,nature,transferDt, preparedBy,preparedDt,preparedByIP) Values (:code,:name,:hsn,:nature,NULL,:preparedBy,:preparedDt,:preparedByIP)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `DmShared.iNull(mrTemp.getCode()).toString()`; `:code` ← `DmShared.iNull(mrTemp.getCode()).toString()`; `:name` ← `DmShared.xNull(mrTemp.getName()).trim()`; `:hsn` ← `DmShared.iNull(mrTemp.getHsn()).toString()`; `:nature` ← `DmShared.xNull(goodsServiceNatureCode)`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`
- Errors: "Invalid key." | "Name already exists."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `GSTReasonMast.java` (322 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveGSTReasonMast`
`private int saveGSTReasonMast(Model mrTemp)`

- SQL 1: `SELECT isNull(MAX(code),0) + 1 AS code FROM gstReasonMast`
- SQL 2: `select COUNT(name) as cnt from gstReasonMast where name = :name AND code <> :code`
- SQL 3: `select COUNT(manualCode) as cnt from gstReasonMast where manualCode = :manualCode AND code <> :code`
- SQL 4: `INSERT INTO gstReasonMast (code, name,manualCode,description,preparedBY, u_EntDt,transferDt) VALUES(:code, :name,:manualCode,:description,:preparedBY,getdate(),:transferDt);`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `mrTemp.getCode()`; `:name` ← `mrTemp.getName()`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode().trim())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:transferDt` ← `null`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `GSTUnitMast.java` (321 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveUnitQuantityCode`
`private int saveUnitQuantityCode(Model mrTemp)`

- SQL 1: `SELECT isNull(MAX(code),0) + 1 AS code FROM gstUnitMast`
- SQL 2: `select COUNT(name) as cnt from gstUnitMast where name = :name AND code <> :code`
- SQL 3: `select COUNT(manualCode) as cnt from gstUnitMast where manualCode = :manualCode AND code <> :code`
- SQL 4: `INSERT INTO gstUnitMast (code, name,manualCode,description,preparedBY, u_EntDt,transferDt) VALUES(:code, :name,:manualCode,:description,:preparedBY,getdate(),:transferDt);`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `mrTemp.getCode()`; `:name` ← `mrTemp.getName()`; `:manualCode` ← `DmShared.xNull(mrTemp.getManualCode())`; `:description` ← `DmShared.xNull(mrTemp.getDescription())`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:transferDt` ← `null`
- Errors: "Invalid key."
- Other calls: `ERROR.getId`, `SUCCESS.getId`, `WARNING.getId`

## Server `UnitMaster.java` (369 lines)
Helpers used: `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getLocalIPAddresses`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.removeSpace`, `DmShared.xNull`

### `getImportDataModel`
`public ImportData_Mdl getImportDataModel()`


### `importCSV`
`public DmImportStatus importCSV(DmRSSPrivate rss, ImportData_Mdl importData)`

- Errors: "Log file path can not be blank." | "Data file path can not be blank." | "Data file not found."
- Other calls: `DmImportStatus.getImportStatus`, `ERROR.getId`, `ImportData_Server.checkHeader`, `ImportData_Server.getCSVFormats`, `ImportData_Server.validateField`

### `saveUnitMast`
`private int saveUnitMast(Model mrTemp)`

- SQL 1: `select COUNT(um.unit) as cnt from unitMast as um where um.unit = :name AND um.code <> :code`
- SQL 2: `INSERT INTO unitMast (code, unit,decimalPlaces,gstUnitCode,nature,transferDt,preparedBY,preparedDt,preparedByIP,isSystemGenerated) VALUES(:code, :unit,:decimalPlaces,:gstUnitCode,:nature,NULL,:preparedBY,:preparedDt,:preparedByIP,:isSystemGenerated);`
- Params: `:name` ← `DmShared.xNull(mrTemp.getName())`; `:code` ← `DmShared.xNull(mrTemp.getCode())`; `:code` ← `mrTemp.getCode()`; `:unit` ← `mrTemp.getName()`; `:decimalPlaces` ← `DmServer.vNull(mrTemp.getDecimalPlaces(), "0")`; `:gstUnitCode` ← `DmShared.foriegnKey(mrTemp.getGSTUnitCode())`; `:gstUnitCode` ← `DmShared.foriegnKey(gstUnitCode)`; `:nature` ← `DmShared.foriegnKey(mrTemp.getUnitNatureCode())`; `:preparedBY` ← `DmServer.getSsnUserName(rss)`; `:preparedDt` ← `DmServer.getServerTimestamp()`; `:preparedByIP` ← `DmServer.getLocalIPAddresses(rss)`; `:isSystemGenerated` ← `"0"`
- Errors: "Invalid key." | "Unit name is required." | "Unit nature is mandatory." | "Decimal places can't be greater than 4."
- Other calls: `ERROR.getId`, `NONE.getId`, `SUCCESS.getId`, `UnitNature.getViaText`, `WARNING.getId`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountPosting.server`, `dataman.dmToolkit.dmImportData.server`, `dataman.dmToolkit.dmImportData.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.modifyReason.shared`, `dataman.dmToolkit.stock.server`, `dataman.dmToolkit.stock.shared`, `dataman.libraryMenu.self.shared`, `paperbiz.selfToolkit.itemBatch.server`, `paperbiz.selfToolkit.itemBatch.shared`, `paperbiz.selfToolkit.itemPropertyPopup.shared`, `paperbiz.selfToolkit.stStatic.server`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
