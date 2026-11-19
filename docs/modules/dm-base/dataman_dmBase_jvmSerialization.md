# dm-base / dataman_dmBase_jvmSerialization

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_jvmSerialization/`). Hand-written notes are in the "Notes" section.

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
| shared/DmJsonResponse_Mdl.java | 75 |
| shared/DmJsonUserAuth_Mdl.java | 44 |
| shared/JVMLocalSetting_Mdl.java | 73 |

## Models (shared DTOs)
### JVMLocalSetting_Mdl
| type | field |
|---|---|
| List<DmLocalSettingPrinter_Mdl> | printerSettings |
| List<DmLocalSettingPrintMap_Mdl> | reportMapSettings |
| Map<String, String> | extendedLocalSettings |

### DmJsonUserAuth_Mdl
| type | field |
|---|---|
| byte[] | userName |
| byte[] | password |
| DmEnum.DmUserValidationMode | userValidationMode |

### DmJsonResponse_Mdl
| type | field |
|---|---|
| boolean | status |
| String | value1 |
| String | value2 |
| String | errorMessage |
| int | errorNumber |
| T | data |

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
