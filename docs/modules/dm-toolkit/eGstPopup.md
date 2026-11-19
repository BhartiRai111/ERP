# dm-toolkit / eGstPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/eGstPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHTransportMode, getTxtHTransporter, getHVehicleType, getHEwaySubSupplyType, getHEwayDocumentType, getHEwayTransactionType, getTxtHCancelReason, getHEinvSupplyType, getHEinvDocumentType, getTxtHDocumentType, getTxtHDocumentNature, getTxtHGSTItemType, getTxtHGSTItemNature, getTxtHIrp, getTxtHCity
- CSV export: no

## Files
| file | lines |
|---|---|
| client/EGstCancelPopup.java | 182 |
| client/EGstCancelPopup_Dsg.java | 162 |
| client/EGstPopup.java | 2069 |
| client/EGstPopup_Dsg.java | 2732 |
| client/Setting.java | 229 |
| client/Setting_Dsg.java | 160 |
| server/EGstPopup_Server.java | 64 |
| server/EGstPopup_SrvImpl.java | 777 |
| shared/EGstPopup_Srv.java | 50 |
| shared/EGstPopup_SrvAsync.java | 50 |
| shared/IEGstPopup.java | 13 |
| shared/IEGstPopup_Srv.java | 12 |
| shared/IEGstPopup_SrvAsync.java | 12 |
| shared/IEGstTransaction.java | 10 |
| shared/TxtHCity_Mdl.java | 86 |
| shared/TxtHIrpInfo_Mdl.java | 41 |
| shared/TxtHTranporter_Mdl.java | 181 |

## UI labels / columns (client)
`Refresh Token`, `Genrate E-Way`, `E-Way via IRN`, `Cancel E-Way`, `Cancel E-invoice`, `Print E-way`, `Print Detail E-way`, `Genrate E-invoice`, `Update GST Doc.`, `E-way sub-supply type`, `E-way doc. type`, `E-invoice supply type`, `E-invoice doc. type`, `E-way Transcation type`, `E-way sub-supply type description`, `Auth Token`, `Expiry at`, `Actions`, `Transporter`, `Transport Id`, `Date`, `Transport Mode`, `Distance`, `Vehicle Type`, `Vehicle No.`, `LR No.`, `E-invoice Ack. No.`, `E-invoice Ack. Date`, `E-invoice Status`, `E-invoice IRN No.`, `E-Way No.`, `E-Way Bill Date`, `E-Way Bill Valid Upto`, `E-Way Message`, `Name`, `GSTIN`, `Legal Name`, `Trade Name`, `Address 1`, `Address 2`, `City`, `State`, `Pincode`, `Phone/Mobile`, `Email`, `GSTIN Date`, `Party Name`, `Party GSTIN`, `Party GSTIN Date`, `GST Document Type`, `GST Document Nature`, `GST Item Type`, `GST Item Nature`, `Reason`, `Remark`, `E-Way IRP`, `E-Invoice IRP`

## Models (shared DTOs)
### TxtHTranporter_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | gstin |
| String | legalName |
| String | tradeName |
| String | address1 |
| String | address2 |
| String | pinCode |
| String | stateNumericCode |
| String | cityName |
| String | placeOfSupplyCode |
| String | phone |
| String | email |
| String | name |
| String | stateName |
| String | gstinDate |
| String | transporterId |
| String | countryCode |
| String | manualCode |

### TxtHIrpInfo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | irpTypeTag |
| String | irpTypeText |
| String | url |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | districtCode |
| String | districtName |
| String | stateNumericCode |
| String | countryCode |
| String | gstCountryCode |
| String | countryName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHTransportMode | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHCancelReason | int actionCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTransporter | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHTranporter_Mdl>> |
| getHVehicleType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEwaySubSupplyType | String supplyType, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEwayDocumentType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEwayTransactionType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEinvSupplyType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getHEinvDocumentType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getAuthToken | int actionCode | DmResult<EGstToken_Mdl> |
| print | String ewayNo, int actionCode | DmResult<KeyFieldExtended> |
| getTxtHDocumentType | String voucherCategory, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHDocumentNature | String gstDocNatureShortName, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSTItemType | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHGSTItemNature | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHIrp | int irpTypeCode, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHIrpInfo_Mdl>> |
| getEgstParamSessionDetail |  | DmResult<EGstParam_Mdl> |
| setEgstParamSessionDetail | EGstParam_Mdl mrTemp | DmResult<String> |
| getTxtHCity | String gstin, String stateNumericCode, String subCode, String siteCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |

## RPC interface
| method | params | returns |
|---|---|---|
| processEGst | MasterEGst_Mdl masterEGst_Mdl, int actionCode | DmResult<MasterEGst_Mdl> |

## Server `EGstPopup_Server.java` (64 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`

### `processEGst`
`public static void processEGst(MasterEGst_Mdl masterEGst_Mdl, int actionCode, DmRSSPrivate rss, IEGstPopup eGstProcessed) throws Exception`

- Other calls: `GST_UPDATE_DOCUMENTS.getCode`, `Gst_Server.processEGst`, `Gst_Server.saveEGstRecord`, `Gst_Server.updateGstDocument`

## Server `EGstPopup_SrvImpl.java` (777 lines)
Helpers used: `DmBaseServer.getSsnData`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmServer.setSsnData`, `DmShared.dNull`, `DmShared.filterKeyFieldList`, `DmShared.isValidEMail`, `DmShared.isValidMobile`, `DmShared.xNull`

### `getTxtHTransportMode`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTransportMode(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TransportMode.values`

### `getTxtHTransporter`
`public DmResultPageing<List<TxtHTranporter_Mdl>> getTxtHTransporter(DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `select sg.subCode as code, sg.name , sg.legalName ,sg.manualCode , sg.tradeName ,sg.add1,sg.add2,sg.gstin,sg.gstinDate ,st.code stateCode,st.name as stateName, sg.pin as pinCode ,st.numericCode as stateNumericCode, sg.transporterId, c.cityCode as cityCode, c.cityName as cityName ,sg.mobile,sg.email from subGroup sg LEFT JOIN subGroupSiteList sgsl ON sgsl.subCode = sg.subCode LEFT JOIN city c on c.cityCode = sg.cityCode LEFT JOIN stateMast st on st.code = c.stateCode where sg.isActive = 1 AND sgsl.site_Code = :site_Code AND sg.nature = :nature`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:nature` ← `DmServer.getCodeViaManualCode("TP", "manualCode", "subGroupNature", "code", cnTemp)`

### `getHVehicleType`
`public DmResultPageing<List<KeyFieldExtended>> getHVehicleType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.code AS code , vt.name AS name, vt.gstVehicleType FROM vehicleTypeMast vt`

### `getHEwaySubSupplyType`
`public DmResultPageing<List<KeyFieldExtended>> getHEwaySubSupplyType(String supplyType, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `BOTH_INWARD_OUTWARD.getCode`, `Eway_SubSupplyType.values`

### `getHEwayDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getHEwayDocumentType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Eway_DocType.values`

### `getHEwayTransactionType`
`public DmResultPageing<List<KeyFieldExtended>> getHEwayTransactionType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Eway_TransactionType.values`

### `getAuthToken`
`public DmResult<EGstToken_Mdl> getAuthToken(int actionCode)`

- Other calls: `Gst_Server.getAuthToken`, `Gst_Server.getToken`

### `print`
`public DmResult<KeyFieldExtended> print(String ewayNo, int actionCode)`

- Other calls: `Gst_Server.printEWayBill`

### `getTxtHCancelReason`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHCancelReason(int actionCode, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `E_INV_CANCEL_BILL.getCode`, `E_WAY_CANCEL_BILL.getCode`, `EinvCancelReason.values`, `EwayCancelReason.values`

### `getHEinvSupplyType`
`public DmResultPageing<List<KeyFieldExtended>> getHEinvSupplyType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Einv_SupplyTypeCode.values`

### `getHEinvDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getHEinvDocumentType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `Einv_DocTypeCode.values`

### `getTxtHDocumentType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentType(String voucherCategory, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT vt.v_Type, vt.documentTypeCode, vt.documentNatureCode, vt.itemGstType , vt.itemGSTNature, vt.documentTypeCSV FROM voucher_Type vt WHERE vt.category = :category`
- Params: `:category` ← `DmShared.xNull(voucherCategory)`
- Other calls: `GstDocumentType.getViaShortName`, `GstDocumentType.values`

### `getTxtHDocumentNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHDocumentNature(String gstDocNatureShortName, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GstDocumentNature.values`

### `getTxtHGSTItemType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTItemType(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT git.code , git.name , git.manualCode FROM gstItemType git`

### `getTxtHGSTItemNature`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHGSTItemNature(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `GSTNature.values`

### `getTxtHIrp`
`public DmResultPageing<List<TxtHIrpInfo_Mdl>> getTxtHIrp(int irpTypeCode, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT irm.code, irm.name as irpName , irm.url FROM irpMast irm WHERE irm.type = :irpType AND irm.isActive = 1`

### `getEgstParamSessionDetail`
`public DmResult<EGstParam_Mdl> getEgstParamSessionDetail()`


### `setEgstParamSessionDetail`
`public DmResult<String> setEgstParamSessionDetail(EGstParam_Mdl mrTemp)`


### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(String gstin, String stateNumericCode, String subCode, String siteCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode,ct.cityName,ct.stateCode,sm.name as stateName,sm.numericCode, ct.countryCode,ctr.name as countryName, ctr.gstCode AS countryGstCode, ct.districtCode, cd.cityName AS districtName FROM city ct LEFT JOIN stateMast sm on ct.stateCode = sm.code LEFT JOIN country ctr on ct.countryCode = ctr.code LEFT JOIN city cd on cd.cityCode = ct.districtCode WHERE 1=1 {…}`
- Params: `:stateNumericCode` ← `tempStateNumericCode`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.eGstPopup.shared`, `dataman.dmToolkit.gst.server`, `dataman.dmToolkit.gst.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
