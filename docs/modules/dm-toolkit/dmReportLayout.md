# dm-toolkit / dmReportLayout

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmReportLayout/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHManageTemplate, getTxtHAcName
- CSV export: no

## Files
| file | lines |
|---|---|
| client/DmReportLayout.java | 427 |
| client/DmReportLayout_Dsg.java | 324 |
| client/DmTemplatePopup.java | 265 |
| client/DmTemplatePopupGridEdit.java | 338 |
| client/DmTemplatePopupGridEdit_Dsg.java | 265 |
| client/DmTemplatePopup_Dsg.java | 185 |
| server/DmReportLayout_SrvImpl.java | 131 |
| server/DmReportLayout_Srvlt.java | 50 |
| shared/ColumnConfig_Mdl.java | 121 |
| shared/ColumnKeyMapper.java | 72 |
| shared/DmReportLayout_Srv.java | 16 |
| shared/DmReportLayout_SrvAsync.java | 17 |
| shared/DynamicGridItemModel.java | 23 |
| shared/DynamicValueProviderFactory.java | 27 |
| shared/GridConfig.java | 105 |
| shared/GridConfig_Mdl.java | 50 |
| shared/TxtHAcName_Mdl.java | 69 |

## UI labels / columns (client)
`Templates`, `SR.`, `A/c Name`, `Send To Name`, `Send To Email`, `Send To CC`, `Send To BCC`, `Send To Mobile`, `Hide/Show All Filters`

## Models (shared DTOs)
### TxtHAcName_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | manualCode |
| String | displayName |
| String | cityName |
| String | email |
| String | mobile |
| String | nature |

### ColumnConfig_Mdl
| type | field |
|---|---|
| String | moduleName |
| int | index |
| String | name |
| int | width |
| boolean | wrap |
| boolean | visiblity |
| String | gridColKey |
| String | mapperKey |

### GridConfig_Mdl
| type | field |
|---|---|
| String | moduleName |
| String | menuName |
| String | type |
| List<ColumnConfig_Mdl> | columnConfig_Mdls |

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHManageTemplate | String prefix,String suffix, DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |
| getTxtHAcName | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHAcName_Mdl>> |

## Server `DmReportLayout_SrvImpl.java` (131 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getTxtHManageTemplate`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHManageTemplate(String constPrefix, String constSuffix, DmHelper pageing, boolean blnToEndOfFile)`

- SQL 1: `SELECT code,name from wjTemplateMast where name LIKE ' {…} [\_]% {…} {…} [\_]% {…} '`

### `getTxtHAcName`
`public DmResultPageing<List<TxtHAcName_Mdl>> getTxtHAcName(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode AS code, sg.name, sg.manualCode, c.cityName , sg.displayName, sg.email, sg.mobile, sgn.name AS subGroupNatureName FROM subGroup sg LEFT JOIN city c ON c.cityCode = sg.cityCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature`

## Server `DmReportLayout_Srvlt.java` (50 lines)
### `processRequest`
`public void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.client`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmRemarkTextBox.client`, `dataman.dmToolkit.dmReportLayout.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextBoxTime.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
