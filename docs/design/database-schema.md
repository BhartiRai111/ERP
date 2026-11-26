# Database schema (existing MS SQL Server, unchanged)

Source: the user's scripts `LeatherZ_Script.sql` (transaction DB) and `LeatherZ_Company_Script.sql` (master DB), plus the procedure/function/view archives. The new code **never creates or alters tables** (no Flyway/Liquibase/ddl-auto). The scripts are the reference; this note is the map.

## 1. Two kinds of database
| | Master ("company") DB | Transaction DB (one per company/year) |
|---|---|---|
| Example | `LeatherZ_Company` | `LeatherZ` |
| Tables | 34 | 450 (+ 1,835 foreign keys, 837 indexes) |
| Procedures / functions | 1 / 0 | 111 / 4 |
| Holds | users, login data, menu/permissions, product licensee (PLT), company + financial year, sites/branches, date locks, scheduler jobs, file-type list, modules, machine interface config | all business data: masters, sales, purchase, store, production, gate, accounts links, approvals, stock |
| Selected by | `dataman.db.default-catalog` | per request: `setCatalog(companyDb)` (`CompanyCatalogDataSource`), reset on close |

`company.start_Dt/end_Dt/cYear/v_Prefix` identify the financial year; `v_Prefix` is also the voucher prefix used in document numbers.

## 2. Master DB tables (34)
- **Identity and access:** `userMast` (code, user_Name, passWd varchar(50) legacy MD5, isAdmin, isActive, UserLockTimeOut, tokenKey, user_Role), `user_Permission_Web`, `user_Control_Permission_Web`, `user_Voucher_Permission`, `user_Favorite_Web`, `user_Restrict_System`, `userLinkAccount`, `userMobileAppPermisson`, `mobileApp`.
- **Menu:** `moduleMaster`, `menuCodification`, `report_Module_Menu`.
- **Organisation:** `productLicensedTo` (PLT), `company`, `branchMast`, `siteMast` (code, pltCode, branchCode, names, isHO, address, GSTIN/PAN ...), `pltLockDate`.
- **System:** `schedulerMast`, `backupInfo`, `fileTypeMast`, `generateCode` (+ proc `GenerateCodeMast`), `linkVerification`, `machineInterfaceConfig`, `machineSubTestMapping`, `machineSubTestOutput`, `report_*` (report filter/help/format definitions).

## 3. Transaction DB: shape
**Hub tables** (number of foreign keys pointing at them): `subGroup` 439 (the central account/party master: customer, supplier, manufacturer, employee, division, department, warehouse ... told apart by `filterNature` PT/CT/SU/MF/EM/DV/DP...), `itemMast` 146, `siteMast` 126, `unitMast` 94, `voucher_Type` 87, `itemBatch` 49, `city` 39, `overHeadMast` 39, `overHeadConfigName` 32, `departmentMast` 30, `priceListMast` 26, `hsnMast` 26, `packSizeMast` 25, `locationMast` 23, `stateMast` 23, `currency` 21, `transType` 20, `gstType` 18, `itemNature` 17, `acGroup` 14.

**Widest tables:** `eGst` 187 columns, `sBill` 151, `pBill` 136, `sChallan` 133, `sBillDetail`/`pBillDetail` 132, `sOrderDetail` 121, `pChallan` 120.

**Table families (name prefix):** `acGroup*`, `address*`, `amendment*` (PO/SO amendments), `asset*`, `item*`, `sOrder*`/`pOrder*`, `sChallan*`/`pChallan*`, `sBill*`/`pBill*`, `proformaInvoice*`, `quotation*`, `enquiry*`, `stock*`/`itemStock*`/`itemBatchStock*`, `overHead*`, `scheme*`, `priceList*`, `numberSystem*`, `codeMaster`, `docIdMaster`, approval (walk-through) tables, `eGst` (e-invoice/e-waybill), `voucher_Type`.

## 4. Conventions seen in the data
- **Audit columns:** `u_Name`/`u_EntDt` (173 tables), `preparedBy`/`preparedDt`/`preparedByIP` (149/117/108), `transferDt` (153), `site_Code` (117), `docId` (234), `v_SNo` (158), `subCode` (104).
- **Keys:** no identity columns anywhere; keys are generated (see section 5). 21 tables have no primary key (e.g. `codeMaster`, `numberSystemCounter`, `numberSystemFormat`, `currencyRateList`, `scheme`, `otherChargesTrans`, `itemITSDetail`).
- **Document id:** `1` + site (4) + voucher type (3) + prefix (2) + number (8) = bigint-like text; built by `getDocId`.
- **Names:** mixed case, no schema prefix other than `dbo`; column names are case-insensitive in SQL Server, so the code must not depend on case.
- **Collation:** text columns carry the DB collation (`collate: true` in the scripts); comparisons are case-insensitive.

## 5. Stored procedures and functions
| Group | Names (count) | Role |
|---|---|---|
| Key / number generators | `getCode` (counter in `codeMaster` -> `v_No`), `getDocId` (`docIdMaster`), `getRecId` (`numberSystemCounter`; `UPDATE ... SET @n = counter+1`, insert when absent) | used by `SerialNoService`, `RecIdService` |
| Manual-code generators (take `dbo.fieldUDT`) | `getItemManualCode`, `getFinishedItemManualCode`, `getServiceManualCode`, `getAccountId`, `getCustomerId`, `getSupplierId`, `getManufacturerId`, `getPartyId`, `getDivisionId`, `getEmployeeId`, `getAssetId`, `getReelBarcode`, `getSheetBarcode`, `getLabRefrence` | prefix = first 2 characters of each field + counter; loop until the code is unused (17 `get*` in all) |
| Report procedures | `Purchase_*` 22, `GPurchase_*` 24, `Sales_*` 21, `GSales_*` 15, `osrDms_*` 3, `Import_*` 3, `Master_*` 2, `Gate_pass_mirza`, `Sale_AmendmentSaleOrder`, `Terms_And_Condition` (~92) | feed the printed documents (HTML templates now); read with `ReportDataService` |
| Other | `generateUserName` | |
| Functions | `FA_Split`, `getCurrencyValue`, `getTurnAroundTime`, `removeSpecialChars` | |
| Master DB | `GenerateCodeMast` | |

**Table types:** `dbo.fieldUDT (fieldCode varchar(30) NOT NULL, fieldName varchar(30), fieldText varchar(max), fieldTag varchar(max), fieldTag2nd varchar(max))` is the input of the generators and many report procedures (`FieldUdt.toTable`, key/name length is checked to 30). `dbo.graphUDT (groupCode, groupName, labelCode, labelName, dataValue)` exists in the script but nothing in the legacy code or procedures uses it, so there is no code for it.

## 6. Differences from the legacy code (the DB is a slightly older "LeatherZ" variant)
- About 99.6% of the columns the legacy code reads/writes exist. Missing or different: tables `machineMast`, `dscMast`, `itemUpdateHistory`; some Tally and e-invoice columns. Policy for these is open (skip the feature, or the user supplies an updated script).
- Password-type columns (`wjAccountMast.password`, `userPLTConfig.password`, `userMobileAppPermisson.mpin`) may be legacy `codify` (AES) values; not decided.

## 7. Creating a dev database from the scripts (order)
1. Table types: `fieldUDT`, `graphUDT`.
2. Tables (master DB script, then transaction DB script).
3. Foreign keys, indexes.
4. Functions, then procedures (extracted from the archives; use `unrar`, RAR5).
5. Seed data (missing today: voucher types, number system + formats, `codeMaster`, menu/modules, one admin user, one PLT/company/site, city/state/currency/unit/GST masters).
Integration tests run against that DB; there is no DB in the build container.
