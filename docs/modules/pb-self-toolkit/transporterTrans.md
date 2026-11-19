# pb-self-toolkit / transporterTrans

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/transporterTrans/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: transporterTrans
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| server/TransporterTrans_Server.java | 106 |
| shared/TransporterTrans_Mdl.java | 178 |

## Models (shared DTOs)
### TransporterTrans_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | v_Date |
| String | v_Type |
| String | siteCode |
| RecId | recId |
| String | transporterCode |
| String | cityCode |
| String | refNo |
| String | refDate |
| String | partyCode |
| String | deliveryTo |
| String | vehicleNo |
| String | vehicleTypeCode |
| String | transportMode |
| String | lrNo |
| String | lrDate |
| String | freightTransAmt |
| String | tpRateType |
| String | tpPaymentBy |

## Server `TransporterTrans_Server.java` (106 lines)
Helpers used: `DmServer.convertDate`, `DmServer.vNull`, `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `saveTransporterTrans`
`public static void saveTransporterTrans(TransporterTrans_Mdl mrTemp, boolean isDocApproved, Timestamp timeStamp, Connection cnTemp) throws Exception`

- SQL 1: `INSERT INTO transporterTrans (docId,v_Type,v_Date,site_Code,refNo ,refDate ,partyCode, deliveryTo, vehicleNo , vehicleTypeCode ,transporterCode ,transportMode, lrNo,lrDate,recId,recIdPrefix,v_No ,freightTransAmt,cityCode,tpRateType,tpPaymentBy,isStatus,u_EntDt_LatestLine) values (:docId,:v_Type,:v_Date,:site_Code,:refNo ,:refDate ,:partyCode, :deliveryTo, :vehicleNo , :vehicleTypeCode ,:transporterCode ,:transportMode, :lrNo,:lrDate,:recId,:recIdPrefix,:v_No ,:freightTransAmt,:cityCode,:tpRateType,:tpPaymentBy,:isStatus,:u_EntDt_LatestLine)`
- Params: `:docId` ← `DmShared.xNull(mrTemp.getSharedKeyFieldCode())`; `:v_Type` ← `DmShared.foriegnKey(mrTemp.getV_Type())`; `:v_Date` ← `DmServer.convertDate(mrTemp.getV_Date())`; `:site_Code` ← `DmShared.foriegnKey(mrTemp.getSiteCode())`; `:refNo` ← `DmShared.foriegnKey(mrTemp.getRefNo())`; `:refDate` ← `DmServer.convertDate(mrTemp.getRefDate())`; `:partyCode` ← `DmShared.foriegnKey(mrTemp.getPartyCode())`; `:deliveryTo` ← `DmShared.foriegnKey(mrTemp.getDeliveryTo())`; `:vehicleNo` ← `DmShared.xNull(mrTemp.getVehicleNo())`; `:vehicleTypeCode` ← `DmShared.foriegnKey(mrTemp.getVehicleTypeCode())`; `:transporterCode` ← `DmShared.foriegnKey(mrTemp.getTransporterCode())`; `:transportMode` ← `DmShared.foriegnKey(mrTemp.getTransportMode())`; `:recId` ← `DmShared.foriegnKey(mrTemp.getRecId().getRecIdValue())`; `:recIdPrefix` ← `DmShared.foriegnKey(mrTemp.getRecId().getPrefix())`; `:v_No` ← `DmShared.foriegnKey(mrTemp.getRecId().getCounter().toString())`; `:freightTransAmt` ← `DmServer.vNull(mrTemp.getFreightTransAmt(), "0.00")`; `:tpRateType` ← `DmShared.foriegnKey(mrTemp.getTpRateType())`; `:tpPaymentBy` ← `DmShared.foriegnKey(mrTemp.getTpPaymentBy())`; `:lrNo` ← `DmShared.xNull(mrTemp.getLrNo())`; `:lrDate` ← `DmServer.convertDate(mrTemp.getLrDate())`; `:cityCode` ← `DmShared.foriegnKey(mrTemp.getCityCode())`; `:isStatus` ← `isDocApproved ? 0 : 1`; `:u_EntDt_LatestLine` ← `timeStamp`

### `deleteTransporterTrans`
`public static void deleteTransporterTrans(String transCode, Connection cnTemp) throws Exception`

- Params: `:docId` ← `DmShared.xNull(transCode)`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `paperbiz.selfToolkit.transporterTrans.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
