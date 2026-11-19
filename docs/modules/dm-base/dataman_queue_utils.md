# dm-base / dataman_queue_utils

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_queue_utils/`). Hand-written notes are in the "Notes" section.

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
| Calculator.java | 18 |
| FileUtil.java | 48 |
| FolderNameValidator.java | 24 |

## Depends on (imports from other packages)


## Classes (plain classes: auto-extracted)

### Calculator (Calculator.java, 18 lines)

Public methods:
- `long mod(long val, int bits)`
- `long mul(long val, int bits)`
- `long div(long val, int bits)`

### FileUtil (FileUtil.java, 48 lines)

Public methods:
- `boolean isFilenameValid(String file)`
- `void deleteDirectory(File dir)`
- `void deleteFile(File file)`

### FolderNameValidator (FolderNameValidator.java, 24 lines)

Public methods:
- `void validate(String name)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
