# dm-toolkit / openSourceReport

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/openSourceReport/`). Hand-written notes are in the "Notes" section.

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
| client/OpenSourceReport.java | 227 |
| server/OpenSourceReport_SrvImpl.java | 198 |
| shared/OpenSourceReport_Srv.java | 18 |
| shared/OpenSourceReport_SrvAsync.java | 18 |

## RPC interface
| method | params | returns |
|---|---|---|
| execute | String reportName, String paperSize, String reportTitle, LinkedHashMap<String, FieldUDT_Mdl> filter, String regionalLanguage, DmReportProperty_Mdl reportProperty, String RuleCode | DmResult<String> |
| getReportSetting | String v_Type | DmResult<KeyFieldExtended> |

## Server `OpenSourceReport_SrvImpl.java` (198 lines)
Helpers used: `DmServer.compileJasper`, `DmServer.getConnection`, `DmServer.getConnectionMongo`, `DmServer.getFieldUDT`, `DmServer.getSsnDBName`, `DmServer.getSsnFileDBName`, `DmServer.getSsnSiteCode`, `DmServer.setDmCustomJasperProperties`, `DmServer.setJasperVariables`, `DmServer.showJasper`, `DmShared.iNull`, `DmShared.xNull`

### `execute`
`public DmResult<String> execute(String reportName, String paperSize, String reportTitle, LinkedHashMap<String, FieldUDT_Mdl> filter, String regionalLanguage, DmReportProperty_Mdl reportProperty, String ruleCode)`

- Other calls: `JasperFillManager.fillReport`, `RegionalLanguage.getValueFrom`

### `getReportSetting`
`public DmResult<KeyFieldExtended> getReportSetting(String v_Type)`

- SQL 1: `select top 1 swrc.reportName, swrc.pageSize from siteWiseReportConfig swrc where swrc.v_Type = :v_Type and isNull(swrc.site_Code,:siteCode) = :siteCode order by swrc.site_Code desc`
- Params: `:v_Type` ← `DmShared.iNull(v_Type).toString()`; `:siteCode` ← `DmShared.iNull(DmServer.getSsnSiteCode(this)).toString()`

### `getImage`
`private HashMap<String, ByteArrayInputStream> getImage(String docId, String ruleCode, Connection cnTemp)`

- SQL 1: `select fileLink,docid from linkfilecollection where docId in ( {…} ) and sNO = 1 and ruleCode = ' {…} '`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmFileManagerMongo.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.openSourceReport.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Open-source (non-Jasper) report** ka screen/server (`OpenSourceReport_SrvImpl`): detail dekhna baaki.
