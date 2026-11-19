# dm-base / dataman_dmBase_dmFileManager

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_dmFileManager/`). Hand-written notes are in the "Notes" section.

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
| shared/DmAttachment_Mdl.java | 159 |
| shared/DmFileParameter_Mdl.java | 39 |
| shared/DmFileRuleType.java | 75 |
| shared/DmFile_Mdl.java | 58 |
| shared/DmIFile_Mdl.java | 21 |

## Models (shared DTOs)
### DmFile_Mdl
| type | field |
|---|---|
| String | fileName |
| String | fileSize |
| String | serverFileName |
| String | serverDirectory |

### DmFileParameter_Mdl
| type | field |
|---|---|
| String | ruleCode |
| String | docId |
| String | vsno |

### DmAttachment_Mdl (extends `DmFileRuleType implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | resourceAlocationId |
| String | fileLink |
| String | docId |
| String | sNo |
| String | fileName |
| String | fileSize |
| String | remarks |
| String | userName |
| String | modifyDateTime |
| String | serverDirectory |
| String | tempDocId |
| Timestamp | u_EntDt |

## Depends on (imports from other packages)


## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
