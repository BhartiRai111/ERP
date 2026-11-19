# dm-toolkit / printerMapping

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/printerMapping/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHPrinter
- CSV export: no

## Files
| file | lines |
|---|---|
| client/PrinterMapping.java | 245 |
| client/PrinterMapping_Dsg.java | 175 |
| server/PrinterMapping_SrvImpl.java | 103 |
| shared/PrinterMappingLocalSetting_Mdl.java | 31 |
| shared/PrinterMapping_Srv.java | 20 |
| shared/PrinterMapping_SrvAsync.java | 20 |

## UI labels / columns (client)
`Menu Text`, `Module Name`, `Report Name`, `Printer Name`

## Models (shared DTOs)
### PrinterMappingLocalSetting_Mdl
| type | field |
|---|---|
| List<DmLocalSettingPrintMap_Mdl> | reportMappings |
| List<DmLocalSettingPrinter_Mdl> | printerMappings |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHPrinter | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getJsonOfLocalSettingReportMapModel | DmLocalSettingPrintMap_Mdl reportMapModel | DmResult<String> |
| getPrinterMappingLocalSettingFromJSon | String jsonOfJVMLocalSettingModel | DmResult<PrinterMappingLocalSetting_Mdl> |

## Server `PrinterMapping_SrvImpl.java` (103 lines)
Helpers used: `DmServer.convertFromJson`, `DmServer.convertToJson`, `DmShared.filterKeyFieldList`, `DmShared.xNull`

### `getTxtHPrinter`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHPrinter(DmHelper helper, boolean blnToEndOfFile)`


### `getJsonOfLocalSettingReportMapModel`
`public DmResult<String> getJsonOfLocalSettingReportMapModel(DmLocalSettingPrintMap_Mdl reportMapModel)`


### `getPrinterMappingLocalSettingFromJSon`
`public DmResult<PrinterMappingLocalSetting_Mdl> getPrinterMappingLocalSettingFromJSon(String jsonOfJVMLocalSettingModel)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.jvmSerialization.shared`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.printerMapping.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
