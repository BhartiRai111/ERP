# dm-base / net_sf_jasperreports_engine_export

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/net_sf_jasperreports_engine_export/`). Hand-written notes are in the "Notes" section.

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
| DmJRXlsExporter.java | 2049 |

## Depends on (imports from other packages)


## Classes (plain classes: auto-extracted)

### DmJRXlsExporter (DmJRXlsExporter.java, 2007 lines)
class; extends/implements: `JRXlsAbstractExporter<XlsReportConfiguration, XlsExporterConfiguration, JRXlsExporterContext>`

Public methods:
- `void exportText(JRPrintText textElement, JRExporterGridCell gridCell, int colIndex, int rowIndex)`
- `void handle(StringTextValue textValue)`
- `void handle(NumberTextValue textValue)`
- `void handle(DateTextValue textValue)`
- `void handle(BooleanTextValue textValue)`
- `byte[] getRGB(int i)`
- `void exportImage(JRPrintImage element,  JRExporterGridCell gridCell,  int colIndex,  int rowIndex,  int emptyCols,  int yCutsRow,  JRGridLayout layout)`
- `String getExporterKey()`
- `String getExporterPropertiesPrefix()`
- `void setBox(JRLineBox box)`
- `void setPen(JRPen pen)`
- `int hashCode()`
- `boolean equals(Object o)`
- `String toString()`
- `void setDataFormat(short dataFormat)`
- `boolean hasDataFormat()`
- `short getDataFormat()`
- `int hashCode()`
- `boolean equals(Object o)`
- `String toString()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
