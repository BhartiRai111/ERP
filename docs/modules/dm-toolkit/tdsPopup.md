# dm-toolkit / tdsPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/tdsPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHTdsFor, getTxtHTdsTcsType, getTxtHTdsTcsCategory, getTxtHTdsTcsDeductFrom
- CSV export: no

## Files
| file | lines |
|---|---|
| client/TdsPopup.java | 785 |
| client/TdsPopup_Dsg.java | 552 |
| server/TdsPopup_Server.java | 93 |
| server/TdsPopup_SrvImpl.java | 244 |
| shared/GrdTdsDetail_Mdl.java | 131 |
| shared/PartyTdsTcsAcBalance_Mdl.java | 59 |
| shared/TDSPopup_Mdl.java | 235 |
| shared/TdsPopup_Srv.java | 22 |
| shared/TdsPopup_SrvAsync.java | 22 |
| shared/TxtHTdsDeductFrom_Mdl.java | 32 |

## UI labels / columns (client)
`Dr 0.00`, `F.Y Dr. 0.00`, `F.Y Cr. 0.00`, `TCS Amt. 0.00`, `TDS Amt. 0.00`, `Type`, `For`, `Category`, `Party`, `PAN`, `Turnover`, `Transaction Value`, `Limit`, `Applied On Value`, `Applicable On Value`, `Amount`, `On Amount`, `Total`

## Models (shared DTOs)
### TDSPopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | tdsOnAmount |
| String | tdsDeductFromCode |
| String | tdsDeductFromName |
| String | tdsTcsTypeCode |
| String | tdsTcsTypeName |
| String | tdsForCode |
| String | tdsForName |
| String | totalAmount |
| String | tan |
| String | pan |
| String | v_Date |
| String | vr_OriginalAmt |
| String | docId |
| String | totalTurnover |
| String | transValue |
| String | appliedOnValue |
| String | limitValue |
| String | applicableOnAmount |
| boolean | manualTdsOnAmount |
| List<GrdTdsDetail_Mdl> | tdsGridList |

### TxtHTdsDeductFrom_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | city |
| String | sCode |

### GrdTdsDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | description |
| String | descriptionCode |
| String | descriptionMCode |
| String | postingAccount |
| String | postingAccountCode |
| String | percent |
| String | amount |
| String | formula |
| String | roundOffTDS |
| String | vSNo |
| String | calcOnValue |

### PartyTdsTcsAcBalance_Mdl
| type | field |
|---|---|
| String | balance |
| String | financialYrCr |
| String | financialYrDr |
| String | financialYrTDS |
| String | financialYrTCS |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHTdsFor | String restrictForCode, boolean showHelp, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTdsTcsType | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHTdsTcsCategory | String type, DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTdsTcsData | String tdsCategoryCode, String date | DmResult<List<GrdTdsDetail_Mdl>> |
| getTxtHTdsTcsDeductFrom | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<TxtHTdsDeductFrom_Mdl>> |
| getPartyTdsTcsAcBalance | String docid, String partyCode, String partyPan | DmResult<PartyTdsTcsAcBalance_Mdl> |

## Server `TdsPopup_SrvImpl.java` (244 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getPartyTdsTcsAcBalance`, `DmServer.getSsnDBName`, `DmServer.getSsnEndDate`, `DmServer.getSsnSiteCode`, `DmServer.getSsnSitePAN`, `DmServer.getSsnStartDate`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHTdsFor`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTdsFor(String restrictForCode, boolean showHelp, DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TDS_For.values`

### `getTxtHTdsTcsType`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTdsTcsType(DmHelper helper, boolean blnToEndOfFile)`

- Other calls: `TDS_TCS_Type.values`

### `getTxtHTdsTcsCategory`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHTdsTcsCategory(String type, DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select tc.code, tc.name, tc.type From tdsCat tc WHERE tc.type = :type`
- Params: `:type` ← `DmShared.xNull(type)`

### `getTdsTcsData`
`public DmResult<List<GrdTdsDetail_Mdl>> getTdsTcsData(String tdsCategoryCode, String date)`

- Other calls: `TdsPopup_Server.getTdsTcsData`

### `getTxtHTdsTcsDeductFrom`
`public DmResultPageing<List<TxtHTdsDeductFrom_Mdl>> getTxtHTdsTcsDeductFrom(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `Select SG.SubCode, SG.Name, isNull(CT.CityName,'') as CityName, SG.ManualCode From SubGroup SG Left Join City CT On CT.CityCode=SG.CityCode LEFT JOIN subgroupsitelist SGL ON SG.subCode=SGL.subCode Where SGL.site_Code=' {…} '`

### `getPartyTdsTcsAcBalance`
`public DmResult<PartyTdsTcsAcBalance_Mdl> getPartyTdsTcsAcBalance(String docid, String partyCode, String partyPan)`


## Server `TdsPopup_Server.java` (93 lines)
Helpers used: `DmServer.convertDate`, `DmServer.vNull`, `DmShared.dNull`, `DmShared.iNull`, `DmShared.xNull`

### `getTdsTcsData`
`public static List<GrdTdsDetail_Mdl> getTdsTcsData(String tdsCategoryCode, String date, Connection cnTemp) throws Exception`

- SQL 1: `SELECT ec.ac_RoundOffTDS FROM enviroCompany ec`
- SQL 2: `Select TC.TDSDesc,tcd.manualCode as tcdManualCode,TCD.Name As DName,TC.ACCode, SG.Name As AName,TC.Percentage ,TC.FormulaString From TDSCat_Det TC Left Join TDSCat_Description TCD On TC.TDSDesc=TCD.Code Left Join SubGroup SG On TC.AcCode=SG.SubCode Where TC.Code=:code {…} Order By TC.SrNo`
- Params: `:code` ← `DmShared.iNull(tdsCategoryCode).toString()`; `:one` ← `1`; `:two` ← `2`; `:date` ← `DmServer.convertDate(date)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.overHeadLine.shared`, `dataman.dmToolkit.tdsPopup.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
