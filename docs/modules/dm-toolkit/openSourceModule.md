# dm-toolkit / openSourceModule

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/openSourceModule/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getHelpColumnDetail, getHelp
- CSV export: no

## Files
| file | lines |
|---|---|
| client/DynamicHelpModelKeyProvider.java | 19 |
| client/DynamicHelpValueProvider.java | 32 |
| client/OpenSourceLayout.java | 288 |
| client/OpenSourceModule.java | 107 |
| server/OpenSourceModule_SrvImpl.java | 696 |
| shared/ColumnDetail_Mdl.java | 30 |
| shared/CustomizedModule_Vld.java | 31 |
| shared/DynamicHelp_Mdl.java | 19 |
| shared/HelpColFormat_Mdl.java | 48 |
| shared/OpenSourceFilter_Mdl.java | 132 |
| shared/OpenSourceLayout_Mdl.java | 81 |
| shared/OpenSourceModule_Srv.java | 18 |
| shared/OpenSourceModule_SrvAsync.java | 18 |

## Models (shared DTOs)
### DynamicHelp_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| LinkedHashMap<String, Object> | cells |

### ColumnDetail_Mdl
| type | field |
|---|---|
| String | columnName |
| int | columnType |

### HelpColFormat_Mdl
| type | field |
|---|---|
| String | headerText |
| String | dbFieldName |
| int | colWidth |
| int | colAligment |

### OpenSourceFilter_Mdl
| type | field |
|---|---|
| String | fieldCode |
| String | fieldName |
| int | fieldDataType |
| boolean | mandatory |
| int | maxLength |
| int | decimalPlaces |
| String | defaultTag |
| String | defaultText |
| byte[] | helpSQLQuery |
| int | helpHeight |
| int | helpWidth |
| int | helpRowPerPage |
| List<HelpColFormat_Mdl> | listHelpColDetail |

### OpenSourceLayout_Mdl
| type | field |
|---|---|
| String | refModule |
| String | menuName |
| String | menuText |
| int | reportType |
| boolean canExcelExport, canPDFExport, | canWordExport |
| List<OpenSourceFilter_Mdl> | filters |

## RPC interface
| method | params | returns |
|---|---|---|
| initiate | String mnuMenu | DmResult<OpenSourceLayout_Mdl> |
| getHelp | byte[] helpSQLQuery, List<HelpColFormat_Mdl> listHelpColDetail, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<DynamicHelp_Mdl>> |
| execute | String session, String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty | DmResult<KeyFieldExtended> |

## Validator `CustomizedModule_Vld`
```java
public class CustomizedModule_Vld implements Serializable, IsSerializable {
    public static void validate(OpenSourceLayout_Mdl oswMdl, LinkedHashMap<String, FieldUDT_Mdl> filter) {
        if (oswMdl != null && filter != null) {
            for (OpenSourceFilter_Mdl osrFilterMdl : oswMdl.getFilters()) {
                if (osrFilterMdl.isMandatory()) {
                    if (DmEnum.DmDataType.valueOf_(osrFilterMdl.getFieldDataType()) == DmEnum.DmDataType.INTEGER
                            || DmEnum.DmDataType.valueOf_(osrFilterMdl.getFieldDataType()) == DmEnum.DmDataType.DOUBLE
                            || DmEnum.DmDataType.valueOf_(osrFilterMdl.getFieldDataType()) == DmEnum.DmDataType.LONG) {
                        DmShared.isValidAmount(filter.get(DmShared.xNull(osrFilterMdl.getFieldCode())).getText(), DmShared.xNull(osrFilterMdl.getFieldName()), 0);
                    } else if ( DmEnum.DmDataType.valueOf_(osrFilterMdl.getFieldDataType()) == DmEnum.DmDataType.HELPSINGLE
                            || DmEnum.DmDataType.valueOf_(osrFilterMdl.getFieldDataType()) == DmEnum.DmDataType.HELPMULTI) {
                        DmShared.isValidText(filter.get(DmShared.xNull(osrFilterMdl.getFieldCode())).getTag(), DmShared.xNull(osrFilterMdl.getFieldName()), 0);
                    } else {
                        DmShared.isValidText(filter.get(DmShared.xNull(osrFilterMdl.getFieldCode())).getText(), DmShared.xNull(osrFilterMdl.getFieldName()), 0);
                    }
                }
            }
        }
    }
}
```

## Server `OpenSourceModule_SrvImpl.java` (696 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.iNull`, `DmShared.xNull`

### `initiate`
`public DmResult<OpenSourceLayout_Mdl> initiate(String mnuMenu)`

- SQL 1: `Select rmm.mnuModule, rmm.refModule, rmm.mnuName, rmm.mnuText, rmm.reportType, rmm.canExcelExport, rmm.canPDFExport, rmm.canWordExport, ri.fieldCode, ri.fieldName, ri.fieldDataType, ri.isMandatory, ri.maxLength, ri.decimalPlaces, ri.defaultTag, ri.defaultText, ri.dmHgQuery, ri.dmHgHeight, ri.dmHgWidth, ri.dmHGRowPerPage from report_module_menu as rmm left join report_initialize as ri on lower(rmm.mnuModule) = lower(ri.mnuModule) and lower(rmm.mnuName) = lower(ri.mnuName) where rmm.isActive = 1 and lower(rmm.mnuModule) = :mnuModule and lower(rmm.mnuName) = :mnuName order by ri.rowIndex;`
- Params: `:mnuName` ← `DmShared.xNull(mnuMenu).toLowerCase()`
- Errors: "Invalid Help Detail." | "Invalid Menu."
- Other calls: `DmDataType.valueOf_`

### `getParamValue`
`private String getParamValue(String paramVal)`


### `getHelpColumnDetail`
`private List<HelpColFormat_Mdl> getHelpColumnDetail(String mnuName, String fieldCode, Connection cnTemp)`

- SQL 1: `Select headerText, dbFieldName, colWidth, colAlignment from report_HGColFormat where lower(mnuModule) = :mnuModule and lower(mnuName) = :mnuName and lower(fieldCode) = :fieldCode order by colIndex;`
- Params: `:mnuName` ← `DmShared.xNull(mnuName).toLowerCase()`; `:fieldCode` ← `DmShared.xNull(fieldCode).toLowerCase()`

### `getHelp`
`public DmResultPageing<List<DynamicHelp_Mdl>> getHelp(byte[] helpSQLQuery, List<HelpColFormat_Mdl> listHelpColDetail, DmHelper helper, boolean blnToEndOfFile)`


### `execute`
`public DmResult<KeyFieldExtended> execute(String session, String widgetName, LinkedHashMap<String, FieldUDT_Mdl> filter, DmReportProperty_Mdl reportProperty)`

- Errors: "Invalid request." | "File Name not given."

### `copyFile`
`private void copyFile(File src, File dest) throws IOException`


### `recursiveDeleteFile`
`private void recursiveDeleteFile(File file)`


### `getDBSourceFromExcel`
`private HashMap<String, String> getDBSourceFromExcel(String fileName)`


### `setParamValueInExcelSheet`
`private void setParamValueInExcelSheet(String fileName, LinkedHashMap<String, FieldUDT_Mdl> filter)`


### `createDataSheet`
`private void createDataSheet(String directoryPath, String dataFileName, LinkedHashMap<String, FieldUDT_Mdl> filter, Connection cnTemp)`

- Errors: "File Name not given." | "Directory not found."

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.openSourceModule.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| initiate | `if (oswfm.getListHelpColDetail().size() == 0)` | "Invalid Help Detail." |
| initiate | `if (DmShared.xNull(oswm.getMenuName()).trim().equalsIgnoreCase(""))` | "Invalid Menu." |
| execute | `if (!DmSession.getSessions().containsKey(sessionId))` | "Invalid request." |
| execute | `if (DmShared.xNull(fileName).equalsIgnoreCase(""))` | "File Name not given." |
| execute | `if (!from.exists())` | ".dsg.xlsx file not found." |
| createDataSheet | `if (DmShared.xNull(dataFileName).trim().equalsIgnoreCase(""))` | "File Name not given." |
| createDataSheet | `if (!sFile.exists())` | "Directory not found." |

## Notes (hand-written)
_No hand-written notes yet._
