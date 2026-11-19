# dm-base / dataman_dmBase_dmDynamicReportBuilder

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_dmDynamicReportBuilder/`). Hand-written notes are in the "Notes" section.

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
| server/DmDynamicReportBuilder.java | 614 |
| server/RptFind.java | 239 |
| server/RptFindExtension.java | 132 |
| shared/DmMasterReport.java | 337 |
| shared/DmMasterReportBorder.java | 59 |
| shared/DmMasterReportColumn.java | 82 |
| shared/DmMasterReportFont.java | 96 |
| shared/DmMasterReportPage.java | 75 |
| shared/DmMasterReportStyle.java | 446 |

## Server `RptFindExtension.java` (132 lines)
### `generateExtendedFindReport`
`public File generateExtendedFindReport() throws Exception`

- Other calls: `File.createTempFile`

## Server `DmDynamicReportBuilder.java` (614 lines)
### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, String property, String className, int width, Style style) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, String property, String className, int width, Style style, Style headerStyle) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, String property, String className, int width) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, String property, String className, int width, boolean fixedWidth) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, String property, String className, int width, Style style, Style headerStyle, boolean fixedWidth) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addImageColumn`
`public DmDynamicReportBuilder addImageColumn(String title, String property, int width, boolean fixedWidth, ImageScaleMode imageScaleMode) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addImageColumn`
`public DmDynamicReportBuilder addImageColumn(String title, String property, int width, boolean fixedWidth, ImageScaleMode imageScaleMode, Style style) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addImageColumn`
`public DmDynamicReportBuilder addImageColumn(String title, CustomExpression expression, int width, boolean fixedWidth, ImageScaleMode imageScaleMode, Style style) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addImageColumn`
`public DmDynamicReportBuilder addImageColumn(String title, String property, String className, int width, boolean fixedWidth, ImageScaleMode imageScaleMode, Style style) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addBarcodeColumn`
`public DmDynamicReportBuilder addBarcodeColumn(String title, String property, String className, int barcodeType, boolean showText, int width, boolean fixedWidth, ImageScaleMode imageScaleMode) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addBarcodeColumn`
`public DmDynamicReportBuilder addBarcodeColumn(String title, String property, String className, int barcodeType, boolean showText, int width, boolean fixedWidth, ImageScaleMode imageScaleMode, Style style) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addBarcodeColumn`
`public DmDynamicReportBuilder addBarcodeColumn(String title, String property, String className, int barcodeType, boolean showText, boolean checkSum, String applicationIdentifier, int width, boolean fixedWidth, ImageScaleMode imageScaleMode, Style style) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, String property, String className, int width, boolean fixedWidth, String pattern) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, String property, String className, int width, boolean fixedWidth, String pattern, Style style) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, String property, Class clazz, int width, boolean fixedWidth, String pattern, Style style) throws ColumnBuilderException, ClassNotFoundException`


### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, String property, String className, int width, boolean fixedWidth, String pattern, Style style, String fieldDescription) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, String property, Class clazz, int width, boolean fixedWidth, String pattern, Style style, String fieldDescription) throws ColumnBuilderException, ClassNotFoundException`


### `addColumn`
`public DmDynamicReportBuilder addColumn(String title, CustomExpression expression, int width, boolean fixedWidth, String pattern, Style style) throws ColumnBuilderException, ClassNotFoundException`

- Other calls: `ColumnBuilder.getNew`

### `guessStyle`
`protected void guessStyle(String className, AbstractColumn column) throws ClassNotFoundException`

- Other calls: `Class.forName`

### `addGroups`
`public DmDynamicReportBuilder addGroups(int numgroups)`


### `addGroups`
`public DmDynamicReportBuilder addGroups(int numgroups, GroupLayout gLayout)`


### `setGroupLayout`
`public DmDynamicReportBuilder setGroupLayout(int groupNumber, GroupLayout layout) throws BuilderException`


### `addGlobalHeaderVariable`
`public DmDynamicReportBuilder addGlobalHeaderVariable(int colNumber, DJCalculation op, Style style)`


### `addHeaderVariable`
`public DmDynamicReportBuilder addHeaderVariable(int groupNum, int colNumber, DJCalculation op, Style style) throws BuilderException`


### `addHeaderVariable`
`public DmDynamicReportBuilder addHeaderVariable(int groupNum, int colNumber, DJCalculation op, Style style, DJValueFormatter valueFormatter) throws BuilderException`


### `getGroupByNumber`
`private DJGroup getGroupByNumber(int groupNum) throws BuilderException`

- Errors: "No such group, use addGroups(int) first"

### `addGlobalFooterVariable`
`public DmDynamicReportBuilder addGlobalFooterVariable(int colNumber, DJCalculation op, Style style)`


### `addFooterVariable`
`public DmDynamicReportBuilder addFooterVariable(int groupNum, int colNumber, DJCalculation op, Style style) throws BuilderException`


### `addGroupVariable`
`public DmDynamicReportBuilder addGroupVariable(String position, int groupNum, int colNumber, DJCalculation op, Style style) throws BuilderException`

- Other calls: `FOOTER.equals`

### `addFooterVariable`
`public DmDynamicReportBuilder addFooterVariable(int groupNum, int colNumber, DJCalculation op, Style style, DJValueFormatter valueFormatter) throws BuilderException`


### `addGroupVariable`
`public DmDynamicReportBuilder addGroupVariable(String position, int groupNum, int colNumber, DJCalculation op, Style style, DJValueFormatter valueFormatter) throws BuilderException`

- Other calls: `FOOTER.equals`

### `addHeaderCrosstab`
`public DmDynamicReportBuilder addHeaderCrosstab(int groupNumber, DJCrosstab djcross) throws BuilderException`


### `addFooterCrosstab`
`public DmDynamicReportBuilder addFooterCrosstab(int groupNumber, DJCrosstab djcross) throws BuilderException`


### `addVariable`
`public DmDynamicReportBuilder addVariable(String name, DJCalculation calculation, CustomExpression expression)`


### `addVariable`
`public DmDynamicReportBuilder addVariable(String name, DJCalculation calculation, CustomExpression expression, CustomExpression initialValueExpression, DJVariableResetType resetType, int resetGroup) throws BuilderException`

- Errors: "Invalid parameter [resetGroup = "
- Other calls: `GROUP.equals`

## Server `RptFind.java` (239 lines)
Helpers used: `DmBaseServer.getSessionId`, `DmBaseServer.setReportHeaderExcel`

### `downloadExcel`
`public String[] downloadExcel(DmRSS rssTemp)`

- Errors: "excel not found.." | "datasource not found.."
- Other calls: `BLACK.getIndex`, `Color.decode`, `DmBaseShared.dNull`, `DmBaseShared.iNull`, `DmBaseShared.vNull`, `DmBaseShared.xNull`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmDynamicReportBuilder.shared`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmBase.self.xls`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
