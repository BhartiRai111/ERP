# dm-toolkit / excelReport

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/excelReport/`). Hand-written notes are in the "Notes" section.

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
| server/ExcelReport_Server.java | 460 |
| shared/ExcelReportConfig.java | 246 |

## Server `ExcelReport_Server.java` (460 lines)
Helpers used: `DmShared.xNull`

### `createAndMergeBlankRows`
`public static int createAndMergeBlankRows(XSSFSheet sheet, int rowIndex, int noOfBlankRows, int firstColumnIndex, int lastColumnIndex)`


### `createAndMergeCells`
`public static XSSFRow createAndMergeCells(XSSFSheet sheet, XSSFRow row, int firstMergeRowIndex, int lastMergeRowIndex, int firstMergeColIndex, int lastMergeColIndex, String cellValue, CellStyle cellStyle, CellType cellType)`

- Other calls: `DmBaseShared.xNull`

### `getCellStyleHeader`
`public static XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet)`

- Other calls: `BLACK.getIndex`, `Color.decode`

### `getCellStyleHeader`
`public static XSSFCellStyle getCellStyleHeader(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold)`


### `getCellStyle`
`public static XSSFCellStyle getCellStyle(DmXlsWriter xlsWriter, XSSFSheet workSheet, boolean fontBold, String dataFormat)`


### `getDataFormat`
`public static short getDataFormat(DmXlsWriter xlsWriter, String dataFormat)`


### `newCell`
`public static XSSFCell newCell(XSSFRow rowObject, int columnIndex, Object cellValue)`

- Other calls: `Byte.parseByte`, `Short.parseShort`

### `newCell`
`public static XSSFCell newCell(XSSFRow rowObject, int columnIndex, Object cellValue, XSSFCellStyle cellStyle)`


### `printFilterData`
`public static int printFilterData(DmXlsWriter xlsWriter, XSSFSheet workSheet, int rowIndex, LinkedHashMap<String, FieldUDT_Mdl> filter, int totalColums)`

- Other calls: `DmBaseShared.xNull`

### `getAllMonths`
`public static String[] getAllMonths(boolean financialMonthFormat)`


### `getAllMonthsWithYear`
`public static String[] getAllMonthsWithYear(Date startDate, boolean financialMonthOrder, boolean shortYear)`


### `getAllMonthsWithYear`
`public static List<String> getAllMonthsWithYear(Date startDate, Date endDate, boolean shortYear)`


### `getQuartersWithYear`
`public static String[] getQuartersWithYear(Date startDate, boolean financialMonthOrder, boolean shortYear)`


### `getQuartersWithYear`
`public static List<String> getQuartersWithYear(Date startDate, Date endDate, boolean shortYear)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`, `dataman.dmToolkit.dmStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
