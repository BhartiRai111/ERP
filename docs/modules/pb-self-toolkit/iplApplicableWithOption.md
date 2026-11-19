# pb-self-toolkit / iplApplicableWithOption

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/iplApplicableWithOption/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
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
| client/IPLApplicableWithOption.java | 221 |
| client/IPLApplicableWithOption_Dsg.java | 143 |
| server/IPLApplicableWithOption_SrvImpl.java | 128 |
| shared/Applicable_Mdl.java | 22 |
| shared/IPLApplicableWithOption_Srv.java | 15 |
| shared/IPLApplicableWithOption_SrvAsync.java | 15 |

## Models (shared DTOs)
### Applicable_Mdl
| type | field |
|---|---|
| String | applicableDate |

## RPC interface
| method | params | returns |
|---|---|---|
| getApplicableDateList | PriceListEntryFrom priceListEntryFrom, String keyCode, String organizationCode | DmResult<List<KeyFieldExtended>> |
| deleteApplicableDate | String applicableDate, PriceListEntryFrom priceListEntryFrom, String keyCode, String rateListCode, String fromBfCode, String toBfCode, String fromGsmCode, String toGsmCode | DmResult<String> |

## Server `IPLApplicableWithOption_SrvImpl.java` (128 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.dNull`, `DmShared.xNull`

### `getApplicableDateList`
`public DmResult<List<KeyFieldExtended>> getApplicableDateList(PriceListEntryFrom priceListEntryFrom, String keyCode, String rateListCode)`

- SQL 1: `select applicableFrom,applicableTo from priceList where entryFrom = :entryFrom and site_Code = :site_Code {…} group by applicableFrom,applicableTo order by applicableFrom desc`
- Params: `:entryFrom` ← `String.valueOf(priceListEntryFrom.getCode())`; `:site_Code` ← `DmServer.getSsnSiteCode(this)`; `:priceListCode` ← `rateListCode`
- Errors: "Price List: Entry From not found."

### `deleteApplicableDate`
`public DmResult<String> deleteApplicableDate(String applicableDate, PriceListEntryFrom priceListEntryFrom, String keyCode, String priceListCode, String fromBfCode, String toBfCode, String fromGsmCode, String toGsmCode)`

- Other calls: `PriceList_Server.deletePriceList`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.iplApplicableWithOption.shared`, `paperbiz.selfToolkit.priceList.server`, `paperbiz.selfToolkit.priceList.shared`, `paperbiz.selfToolkit.stStatic.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| getApplicableDateList | (unconditional) | "Price List: Entry From not found." |

## Notes (hand-written)
_No hand-written notes yet._
