# dm-toolkit / partyDetailPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/partyDetailPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Party Name (Text)
- Lookups/suggestions: getTxtHCity, getTxtHAdPlaceOfBusiness
- CSV export: no

## Files
| file | lines |
|---|---|
| client/PartyDetailPopup.java | 644 |
| client/PartyDetailPopup_Dsg.java | 602 |
| server/PartyDetailPopup_Server.java | 766 |
| server/PartyDetailPopup_SrvImpl.java | 308 |
| shared/PartyDetailPopup_Mdl.java | 277 |
| shared/PartyDetailPopup_Srv.java | 20 |
| shared/PartyDetailPopup_SrvAsync.java | 18 |
| shared/PartyDetailPopup_Vld.java | 14 |
| shared/TxtHAdPlaceOfBusiness_Mdl.java | 149 |
| shared/TxtHCity_Mdl.java | 86 |

## UI labels / columns (client)
`Name`, `Display Name`, `Legal Name`, `Trade Name`, `GSTIN`, `GSTIN Date`, `Transporter Id`, `Additional Place Of Business`, `Address`, `City`, `District`, `Pincode`, `State`, `Country`, `Mobile`, `Phone`, `E-mail`, `PAN`, `TAN`

## Models (shared DTOs)
### PartyDetailPopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | subCode |
| String | name |
| String | siteCode |
| String | legalName |
| String | displayName |
| String | tradeName |
| String | gstin |
| String | gstinDate |
| String | transporterId |
| String | gstCountryCode |
| String | adPlaceOfBusinessSNo |
| String | adPlaceOfBusinessName |
| String | address1 |
| String | address2 |
| String | cityCode |
| String | cityName |
| String | districtCode |
| String | districtName |
| String | stateCode |
| String | stateName |
| String | countryCode |
| String | countryName |
| String | stateNumericCode |
| String | pinCode |
| String | mobile |
| String | phone |
| String | email |
| String | pan |
| String | tan |

### TxtHAdPlaceOfBusiness_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | address1 |
| String | address2 |
| String | cityCode |
| String | cityName |
| String | districtCode |
| String | districtName |
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | countryCode |
| String | countryName |
| String | gstCountryCode |
| String | pin |
| String | email |
| String | mobile |

### TxtHCity_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | stateCode |
| String | stateName |
| String | stateNumericCode |
| String | gstCountryCode |
| String | countryCode |
| String | countryName |
| String | districtCode |
| String | districtName |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHCity | String gstin, String stateNumericCode, String subCode, String siteCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHCity_Mdl>> |
| getTxtHAdPlaceOfBusiness | String subCode, String siteCode, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHAdPlaceOfBusiness_Mdl>> |
| getAdditionalPartyDetail | String subCode | DmResult<PartyDetailPopup_Mdl> |
| savePartyTransRecord | PartyDetailPopup_Mdl mrTemp, String docId, int recordType | DmResult<String> |

## Validator `PartyDetailPopup_Vld`
```java
public class PartyDetailPopup_Vld {
    public static void chkSave(PartyDetailPopup_Mdl mrTemp) {
        DmShared.isValidText(DmShared.xNull(mrTemp.getName()), "Party Name", 100);
        DmShared.isValidMobile(DmShared.xNull(mrTemp.getMobile()), 300);
        DmShared.isValidEMail(DmShared.xNull(mrTemp.getEmail()), 400);
    }
}
```

## Server `PartyDetailPopup_SrvImpl.java` (308 lines)
Helpers used: `DmServer.chkIfDocumentUsed`, `DmServer.evaluateHelper`, `DmServer.getCodeViaManualCode`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHCity`
`public DmResultPageing<List<TxtHCity_Mdl>> getTxtHCity(String gstin, String stateNumericCode, String subCode, String siteCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `select ct.cityCode,ct.cityName,ct.stateCode,sm.name as stateName,sm.numericCode, ct.countryCode,ctr.name as countryName, ctr.gstCode AS countryGstCode, ct.districtCode, cd.cityName AS districtName FROM city ct LEFT JOIN stateMast sm on ct.stateCode = sm.code LEFT JOIN country ctr on ct.countryCode = ctr.code LEFT JOIN city cd on cd.cityCode = ct.districtCode WHERE 1=1 {…}`
- Params: `:stateNumericCode` ← `tempStateNumericCode`

### `getTxtHAdPlaceOfBusiness`
`public DmResultPageing<List<TxtHAdPlaceOfBusiness_Mdl>> getTxtHAdPlaceOfBusiness(String subCode, String siteCode, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT * FROM ( SELECT sg1.subCode , NULL AS siteCode,NULL AS sNo, ' {…} ' AS name ,sg1.add1,sg1.add2,sg1.pin,sg1.mobile, sg1.eMail , sg1.cityCode as cityCode,ct.cityName as cityName , ct.stateCode, sm.name as stateName,sm.numericCode AS stateNumericCode , ct.countryCode, ctr.name as countryName,ctr.gstCode AS gstCountryCode , ct.districtCode, dc.cityName AS districtName FROM subGroup as sg1 LEFT JOIN city ct on sg1.cityCode = ct.cityCode LEFT JOIN stateMast sm on ct.stateCode = sm.code LEFT JOIN country ctr on ct.countryCode = ctr.code LEFT JOIN city dc ON dc.cityCode = ct.districtCode UNION ALL SELECT NULL AS subCode , stm.code AS siteCode, NULL AS sNo, ' {…} ' AS name ,stm.address1 as add1,stm.address2 AS add2,stm.pin,stm.mobile, stm.eMail , ct.cityCode ,stm.city AS cityName , ct.stateCode, sm.name as stateName,sm.numericCode AS stateNumericCode , ct.countryCode, ctr.name as countryName,ctr.gstCode AS gstCountryCode , ct.districtCode, dc.cityName AS districtName FROM siteMast as stm left join city ct on stm.city = ct.cityName left join stateMast sm on ct.stateCode = sm.code left join country ctr on ct.countryCode = ctr.code left join city dc ON dc.cityCode = ct.districtCode UNION ALL SELECT apb.subCode, NULL AS siteCode, apb.sNo,apb.place AS name ,apb.add1,apb.add2,apb.pin,apb.mobile, apb.eMail , apb.cityCode as cityCode,ct.cityName as cityName , ct.stateCode, sm.name as stateName,sm.numericCode AS stateNumericCode , ct.countryCode, ctr.name as countryName,ctr.gstCode AS gstCountryCode , ct.districtCode, dc.cityName AS districtName FROM additionalPlaceOfBusiness as apb left join subgroup as sg on sg.subcode = apb.subCode left join city ct on apb.cityCode = ct.cityCode LEFT JOIN stateMast sm on ct.stateCode = sm.code LEFT JOIN country ctr on ct.countryCode = ctr.code LEFT JOIN city dc ON dc.cityCode = ct.districtCode ) AS T WHERE 1 = 1 {…}`
- Params: `:siteCode` ← `DmShared.xNull(siteCode)`; `:subCode` ← `DmShared.xNull(subCode)`

### `getAdditionalPartyDetail`
`public DmResult<PartyDetailPopup_Mdl> getAdditionalPartyDetail(String subCode)`

- Other calls: `PartyDetailPopup_Server.getPartyDetailPopup_Mdl`

### `savePartyTransRecord`
`public DmResult<String> savePartyTransRecord(PartyDetailPopup_Mdl mrTemp, String docId, int recordType)`

- Other calls: `PartyDetailPopup_Server.savePartyTransRecord`, `PartyDetailPopup_Vld.chkSave`

## Server `PartyDetailPopup_Server.java` (766 lines)
Helpers used: `DmServer.convertDate`, `DmServer.formatTimestamp`, `DmServer.getCodeViaManualCode`, `DmServer.getLocalIPAddresses`, `DmServer.getServerTimestamp`, `DmServer.getSsnUserName`, `DmServer.getUserWithDateTime`, `DmShared.convertDate`, `DmShared.dNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.isValidEMail`, `DmShared.isValidMobile`, `DmShared.removeSpace`, `DmShared.xNull`

### `savePartyTransRecord`
`public static void savePartyTransRecord(PartyDetailPopup_Mdl mrTemp, DmRSSPrivate rss, Connection cnTemp) throws Exception`


### `savePartyTransRecord`
`public static void savePartyTransRecord(String tableName, PartyDetailPopup_Mdl mrTemp, DmRSSPrivate rss, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO {…} ( docId, recordType, subCode,site_Code, Name, Gstin, GstinDate, GstCountryCode, LegalName, displayName, TradeName,adPlaceOfBusinessSNo,adPlaceOfBusinessName, Add1, Add2 , CityCode, CityName, DistrictCode, DistrictName, StateCode, StateName, StateNumericCode , CountryCode, CountryName, Pin, Mobile, Phone, Email, pan, tan , transporterId ) VALUES ( :docId, :recordType, :subCode,:site_Code, :Name, :Gstin, :GstinDate, :GstCountryCode, :LegalName, :displayName, :TradeName, :adPlaceOfBusinessSNo,:adPlaceOfBusinessName, :Add1, :Add2 , :CityCode, :CityName, :DistrictCode, :DistrictName, :StateCode, :StateName, :StateNumericCode , :CountryCode, :CountryName, :Pin, :Mobile, :Phone, :Email, :pan, :tan, :transporterId )`
- Params: `:cityCode` ← `DmShared.iNull(DmShared.xNull(mrTemp.getCityCode())).toString()`; `:docId` ← `DmShared.iNull(mrTemp.getSharedKeyFieldCode()).longValue()`; `:recordType` ← `DmShared.iNull(mrTemp.getSharedKeyFieldName()).intValue()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSubCode())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:Name` ← `DmShared.xNull(mrTemp.getName())`; `:Gstin` ← `DmShared.xNull(mrTemp.getGstin())`; `:GstinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:GstCountryCode` ← `DmShared.xNull(mrTemp.getGstCountryCode())`; `:displayName` ← `DmShared.xNull(mrTemp.getDisplayName())`; `:LegalName` ← `DmShared.xNull(mrTemp.getLegalName())`; `:TradeName` ← `DmShared.xNull(mrTemp.getTradeName())`; `:adPlaceOfBusinessSNo` ← `DmShared.foriegnKey(mrTemp.getAdPlaceOfBusinessSNo())`; `:adPlaceOfBusinessName` ← `DmShared.foriegnKey(mrTemp.getAdPlaceOfBusinessName())`; `:Add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:Add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:CityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:CityName` ← `DmShared.xNull(mrTemp.getCityName())`; `:DistrictCode` ← `DmShared.foriegnKey(mrTemp.getDistrictCode())`; `:DistrictName` ← `DmShared.xNull(mrTemp.getDistrictName())`; `:StateCode` ← `DmShared.foriegnKey(mrTemp.getStateCode())`; `:StateName` ← `DmShared.xNull(mrTemp.getStateName())`; `:StateNumericCode` ← `DmShared.foriegnKey(mrTemp.getStateNumericCode())`; `:CountryCode` ← `DmShared.foriegnKey(mrTemp.getCountryCode())`; `:CountryName` ← `DmShared.xNull(mrTemp.getCountryName())`; `:Pin` ← `DmShared.xNull(mrTemp.getPinCode())`; `:Mobile` ← `DmShared.xNull(mrTemp.getMobile())`; `:Phone` ← `DmShared.xNull(mrTemp.getPhone())`; `:Email` ← `DmShared.xNull(mrTemp.getEmail())`; `:pan` ← `DmShared.xNull(mrTemp.getPan())`; `:tan` ← `DmShared.xNull(mrTemp.getTan())`; `:transporterId` ← `DmShared.xNull(mrTemp.getTransporterId())`
- Errors: "Invalid key."
- Other calls: `PartyDetailPopup_Vld.chkSave`

### `savePartyTransRecord`
`public static void savePartyTransRecord(String tableName, String docId, int recordTypeCode, Party_Mdl mrTemp, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO {…} ( docId, recordType, subCode,site_Code, Name, Gstin, GstinDate, GstCountryCode, LegalName, TradeName,adPlaceOfBusinessName, Add1, Add2 , CityCode, CityName, DistrictCode, DistrictName, StateCode, StateName, StateNumericCode , CountryCode, CountryName, Pin, Mobile, Phone, Email , transporterId ) VALUES ( :docId, :recordType, :subCode,:site_Code, :Name, :Gstin, :GstinDate, :GstCountryCode, :LegalName, :TradeName,:adPlaceOfBusinessName, :Add1, :Add2 , :CityCode, :CityName, :DistrictCode, :DistrictName, :StateCode, :StateName, :StateNumericCode , :CountryCode, :CountryName, :Pin, :Mobile, :Phone, :Email, :transporterId )`
- Params: `:cityCode` ← `DmShared.iNull(DmShared.xNull(mrTemp.getCityCode())).toString()`; `:docId` ← `DmShared.iNull(docId).longValue()`; `:recordType` ← `DmShared.iNull(recordTypeCode).intValue()`; `:subCode` ← `DmShared.foriegnKey(mrTemp.getSharedKeyFieldCode())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:Name` ← `DmShared.xNull(mrTemp.getSharedKeyFieldName())`; `:Gstin` ← `null`; `:GstinDate` ← `null`; `:Gstin` ← `DmShared.xNull(mrTemp.getGstin())`; `:GstinDate` ← `DmServer.convertDate(mrTemp.getGstinDate())`; `:GstCountryCode` ← `DmShared.xNull(mrTemp.getGstCountryCode())`; `:LegalName` ← `DmShared.xNull(mrTemp.getLegalName())`; `:TradeName` ← `DmShared.xNull(mrTemp.getTradeName())`; `:adPlaceOfBusinessName` ← `DmShared.foriegnKey(DmShared.PRIMARY_ADDRESS_OF_BUSINESS)`; `:Add1` ← `DmShared.xNull(mrTemp.getAddress1())`; `:Add2` ← `DmShared.xNull(mrTemp.getAddress2())`; `:CityCode` ← `DmShared.foriegnKey(DmShared.xNull(mrTemp.getCityCode()))`; `:CityName` ← `DmShared.xNull(mrTemp.getCityName())`; `:DistrictCode` ← `DmShared.foriegnKey(districtCode)`; `:DistrictName` ← `DmShared.xNull(districtName)`; `:StateCode` ← `DmShared.foriegnKey(stateCode)`; `:StateName` ← `DmShared.xNull(mrTemp.getStateName())`; `:StateNumericCode` ← `DmShared.foriegnKey(mrTemp.getStateNumericCode())`; `:CountryCode` ← `DmShared.foriegnKey(countryCode)`; `:CountryName` ← `DmShared.xNull(mrTemp.getCountryName())`; `:Pin` ← `DmShared.xNull(mrTemp.getPinCode())`; `:Mobile` ← `DmShared.xNull(mrTemp.getPhone())`; `:Phone` ← `DmShared.xNull(mrTemp.getPhone())`; `:Email` ← `DmShared.xNull(mrTemp.getEmail())`; `:transporterId` ← `DmShared.xNull(mrTemp.getTransporterId())`

### `deletePartyTransRecord`
`public static void deletePartyTransRecord(String docId, Connection cnTemp) throws Exception`


### `deletePartyTransRecord`
`public static void deletePartyTransRecord(String tableName, String docId, int recordType, Connection cnTemp) throws Exception`

- SQL 1: `DELETE FROM {…} WHERE docid= :docid {…}`
- Params: `:docid` ← `docId`; `:recordType` ← `recordType`
- Errors: "Invalid Key"

### `getPartyTransRecord`
`public static HashMap<Integer, PartyDetailPopup_Mdl> getPartyTransRecord(String docId, Connection cnTemp) throws Exception`


### `getPartyTransRecord`
`public static HashMap<Integer, PartyDetailPopup_Mdl> getPartyTransRecord(String docId, int recordType, Connection cnTemp) throws Exception`


### `getPartyTransRecord`
`public static HashMap<Integer, PartyDetailPopup_Mdl> getPartyTransRecord(String tableName, String docId, int recordType, Connection cnTemp) throws Exception`

- SQL 1: `SELECT tpi.docId , tpi.recordType , tpi.subCode , tpi.site_Code , tpi.Name , tpi.Gstin , tpi.GstinDate , tpi.GstCountryCode , tpi.LegalName , tpi.displayName , tpi.TradeName , tpi.adPlaceOfBusinessSNo , tpi.adPlaceOfBusinessName , tpi.Add1 , tpi.Add2 , tpi.CityCode , tpi.CityName , tpi.DistrictCode , tpi.DistrictName , tpi.StateCode , tpi.StateName , tpi.StateNumericCode , tpi.CountryCode , tpi.CountryName , tpi.Pin , tpi.Mobile , tpi.Phone , tpi.Email , tpi.pan , tpi.tan , tpi.transporterId FROM {…} tpi WHERE tpi.docId =:docId {…}`
- Params: `:docId` ← `DmShared.iNull(docId).toString()`; `:recordType` ← `DmShared.iNull(recordType).intValue()`

### `getPartyTransInfo`
`public static Party_Mdl getPartyTransInfo(String docid, int recordType, String transactionDate, DmEnum.DmPartyType partyTypeForEntry, boolean isTransporter, Connection cnTemp) throws Exception`


### `getPartyTransInfo`
`public static Party_Mdl getPartyTransInfo(String tableName, String docid, int recordType, String transactionDate, DmEnum.DmPartyType partyTypeForEntry, boolean isTransporter, Connection cnTemp) throws Exception`

- Other calls: `PartyTransRecordType.getValue`

### `getPartyDetailPopup_Mdl`
`public static PartyDetailPopup_Mdl getPartyDetailPopup_Mdl(String subCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sg.subCode , sg.Name , sg.gstin , sg.gstinDate , sg.legalName , sg.displayName , sg.tradeName , sg.add1 , sg.add2 , sg.cityCode , c.cityName , c.districtCode , cd.cityName as districtName , c.stateCode , st.name as stateName , st.numericCode as stateNumericCode , c.countryCode , cn.name as countryName , cn.gstCode AS gstCountryCode , sg.pin , sg.mobile , sg.phone , sg.email , sg.pan , sg.tan , sg.transporterId , NULL AS adPlaceOfBusinessSNo , ' {…} ' AS adPlaceOfBusinessName FROM subGroup sg LEFT JOIN city c on c.cityCode = sg.cityCode LEFT JOIN city cd on cd.cityCode = c.districtCode LEFT JOIN stateMast st on st.code = c.stateCode LEFT JOIN country cn on cn.code = c.countryCode WHERE sg.subCode =:subCode`
- Params: `:subCode` ← `DmShared.iNull(subCode).toString()`

### `getSiteDetailPopup_Mdl`
`public static PartyDetailPopup_Mdl getSiteDetailPopup_Mdl(String siteCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT sm.subCode , sm.code AS site_Code , sm.Name , sm.gstin , sm.gstinDate , sm.legalName , sm.siteDisplayName , sm.tradeName , sm.address1 as add1 , sm.address2 as add2 , c.cityCode , sm.city as cityName , c.districtCode , cd.cityName as districtName , st.code as stateCode , sm.stateName as stateName , sm.stateNumericCode , cn.code as countryCode , sm.country as countryName , sm.gstCountryCode , sm.pin , sm.mobile , sm.phone , sm.email , sm.panNo as pan , sm.tan , NULL AS adPlaceOfBusinessSNo , ' {…} ' AS adPlaceOfBusinessName FROM siteMast sm LEFT JOIN country cn on cn.gstCode = sm.gstCountryCode LEFT JOIN stateMast st on st.numericCode = sm.stateNumericCode LEFT JOIN city c on c.cityName = sm.city and c.stateCode = st.code and c.countryCode = cn.code LEFT JOIN city cd on cd.cityCode = c.districtCode WHERE sm.code = :siteCode`
- Params: `:siteCode` ← `DmShared.iNull(siteCode).toString()`

### `updatePartyTransRecord`
`public static void updatePartyTransRecord(String docid, int recordType, Party_Mdl party_Mdl, boolean insertIfNotFound, Connection cnTemp) throws Exception`


### `updatePartyTransRecord`
`public static void updatePartyTransRecord(String tableName, String docid, int recordType, Party_Mdl party_Mdl, boolean insertIfNotFound, Connection cnTemp) throws Exception`

- SQL 1: `UPDATE {…} SET cityCode = :cityCode , cityName = :cityName , districtCode = :districtCode , districtName = :districtName , stateCode = :stateCode , stateName = :stateName , stateNumericCode = :stateNumericCode , countryCode = :countryCode , countryName = :countryName , gstCountryCode = :gstCountryCode , add1 = :add1 , add2 = :add2 , pin = :pin , mobile = :mobile , email = :email WHERE docId = :docId AND recordType = :recordType {…}`
- Params: `:docId` ← `docid`; `:recordType` ← `recordType`; `:cityCode` ← `DmShared.foriegnKey(party_Mdl.getCityCode())`; `:cityName` ← `DmShared.foriegnKey(party_Mdl.getCityName())`; `:districtCode` ← `DmShared.foriegnKey(districtCode)`; `:districtName` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode(DmShared.xNull(districtCode), "cityCode"`; `:stateCode` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode(party_Mdl.getStateNumericCode(), "numeri`; `:stateName` ← `DmShared.foriegnKey(party_Mdl.getStateName())`; `:stateNumericCode` ← `DmShared.foriegnKey(party_Mdl.getStateNumericCode())`; `:countryCode` ← `DmShared.foriegnKey(DmServer.getCodeViaManualCode(party_Mdl.getGstCountryCode(), "gstCode"`; `:countryName` ← `DmShared.foriegnKey(party_Mdl.getCountryName())`; `:gstCountryCode` ← `DmShared.foriegnKey(party_Mdl.getGstCountryCode())`; `:add1` ← `DmShared.foriegnKey(party_Mdl.getAddress1())`; `:add2` ← `DmShared.foriegnKey(party_Mdl.getAddress2())`; `:pin` ← `DmShared.foriegnKey(party_Mdl.getPinCode())`; `:mobile` ← `DmShared.foriegnKey(party_Mdl.getPhone())`; `:email` ← `DmShared.foriegnKey(party_Mdl.getEmail())`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxMobile.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.gst.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
