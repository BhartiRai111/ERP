# dm-toolkit / dmGrid

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmGrid/`). Hand-written notes are in the "Notes" section.

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
| client/DataGridValueProvider.java | 35 |
| client/DmColumnConfig.java | 63 |
| client/DmDataGrid.java | 481 |
| client/DmDataGrid_Bkp.java | 480 |
| client/DmDynamicModelKeyProvider.java | 19 |
| client/DmDynamicValueProvider.java | 32 |
| client/DmGrid.java | 323 |
| client/DmGrid_Bkp.java | 538 |
| client/GridValueChangeHandler.java | 26 |
| client/WidgetAdapter.java | 274 |

## Widget API (auto-extracted from client classes)

### DataGridValueProvider (DataGridValueProvider.java, 34 lines)
extends/implements: `ValueProvider<T, String>`

Constructors:
- `DataGridValueProvider(String fieldName, Function<T, String> getter, BiConsumer<T, String> setter)`

Public methods:
- `String getValue(T object)`
- `void setValue(T object, String value)`
- `String getPath()`

### DmColumnConfig (DmColumnConfig.java, 62 lines)
extends/implements: `ColumnConfig<M, N>`

Constructors:
- `DmColumnConfig(ValueProvider<? super M, N> valueProvider)`
- `DmColumnConfig(ValueProvider<? super M, N> valueProvider, int width)`
- `DmColumnConfig(ValueProvider<? super M, N> valueProvider, int width, SafeHtml header)`
- `DmColumnConfig(ValueProvider<? super M, N> valueProvider, int width, String header)`

Public methods:
- `String getDbFieldName()`
- `void setDbFieldName(String dbFieldName)`
- `String getDbFieldNameWithNoFormating()`
- `void setDbFieldNameWithNoFormating(String dbFieldNameWithNoFormating)`
- `DmEnum.DmDataType getDbFieldDataType()`
- `void setDbFieldDataType(DmEnum.DmDataType dbFieldDataType)`
- `boolean isEditable()`
- `void setEditable(boolean editable)`

### DmDataGrid (DmDataGrid.java, 471 lines)
extends/implements: `IsWidgetExtended`

Constructors:
- `DmDataGrid()`

Public methods:
- `DataGrid<T> getGrid()`
- `void setData(List<T> data)`
- `ListDataProvider<T> getStore()`
- `void addData(List<T> rows)`
- `void createColumns(List<ColumnConfig_Mdl> cols)`
- `int compare(ColumnConfig_Mdl o1, ColumnConfig_Mdl o2)`
- `void sortColumns(List<ColumnConfig_Mdl> columnConfig_Mdls)`
- `void onModuleLoad()`
- `void onKeyDown(KeyDownEvent event)`
- `void onCellPreview(CellPreviewEvent event)`
- `void execute()`
- `CellSelection getSelectedCell()`
- `Column addColumnForKey(final ColumnConfig_Mdl config_Mdl, final ValueProvider<T> valueProvider_)`
- `String getValue(T obj)`
- `void onBrowserEvent(Cell.Context context, Element elem, T obj, NativeEvent event)`
- `ValueProvider<T> getValueProvider()`
- `void setValueProvider(ValueProvider<T> valueProvider)`
- `void update(int index, T obj, String value)`
- `Column addTextColumnForKey(final ColumnConfig_Mdl config_Mdl, final ValueProvider<T> valueProvider_)`
- `String getValue(T obj)`
- `void setComparator(Object columnObj, final ValueProvider<T> valueProvider)`
- `int compare(T obj1, T obj2)`
- `Widget asWidget()`
- `void searchRecord(String key)`
- `void execute()`
- `void onBrowserEvent(Event event)`
- `void onHeaderCellClick(final int cellIndex, Element cellElement)`

### DmDataGrid_Bkp (DmDataGrid_Bkp.java, 469 lines)
extends/implements: `IsWidgetExtended`

Constructors:
- `DmDataGrid_Bkp()`

Public methods:
- `DataGrid<Map<String, String>> getGrid()`
- `void setData(List<Map<String, String>> data)`
- `ListDataProvider<Map<String, String>> getStore()`
- `void addData(List<Map<String, String>> rows)`
- `void createColumns(List<ColumnConfig_Mdl> cols)`
- `int compare(ColumnConfig_Mdl o1, ColumnConfig_Mdl o2)`
- `void sortColumns(List<ColumnConfig_Mdl> columnConfig_Mdls)`
- `void onModuleLoad()`
- `void onKeyDown(KeyDownEvent event)`
- `void onCellPreview(CellPreviewEvent event)`
- `void execute()`
- `CellSelection getSelectedCell()`
- `Column addColumnForKey(final ColumnConfig_Mdl config_Mdl)`
- `String getValue(Map<String, String> obj)`
- `void onBrowserEvent(Cell.Context context, Element elem, Map<String, String> obj, NativeEvent event)`
- `void update(int index, Map<String, String> obj, String value)`
- `Column addTextColumnForKey(final ColumnConfig_Mdl config_Mdl)`
- `String getValue(Map<String, String> obj)`
- `void setComparator(Object columnObj, final String key)`
- `int compare(Map<String, String> hm1, Map<String, String> hm2)`
- `Widget asWidget()`
- `void searchRecord(String key)`
- `void execute()`
- `void onBrowserEvent(Event event)`
- `void onHeaderCellClick(final int cellIndex, Element cellElement)`

### DmDynamicModelKeyProvider (DmDynamicModelKeyProvider.java, 18 lines)
extends/implements: `ModelKeyProvider<DmDynamicGridRow>`

Constructors:
- `DmDynamicModelKeyProvider(String keyName)`

Public methods:
- `String getKey(DmDynamicGridRow item)`

### DmDynamicValueProvider (DmDynamicValueProvider.java, 31 lines)
extends/implements: `ValueProvider<DmDynamicGridRow, Object>`

Constructors:
- `DmDynamicValueProvider(String path)`

Public methods:
- `Object getValue(DmDynamicGridRow object)`
- `void setValue(DmDynamicGridRow object, Object value)`
- `String getPath()`

### DmGrid (DmGrid.java, 318 lines)
Constructors:
- `DmGrid(Object t2PrpAcs)`

Public methods:
- `void beforeInitialize(ColumnModel<T1> columnModel)`
- `void onInitialized()`
- `void onCompleteEdit(CompleteEditEvent<T1> event)`
- `T1 addRow(StartEditEvent<T1> event)`
- `void addBeforeInitializeHandler(BeforeInitialize beforeInitialize)`
- `void addInitializeGridCallBackHandler(InitializeGridCallBack initializeGridCallBack)`
- `void addCompleteEditCallBack(CompleteEditCallBack completeEditCallBack)`
- `void onCompleteEdit(CompleteEditEvent<T1> event)`
- `void addAutoAddRowCallBack(AutoAddRow<T1> autoAddRow)`
- `void onStartEdit(StartEditEvent<T1> event)`
- `void initializeGrid(ModelKeyProvider<T1> mkpVar, int width, int height)`
- `void initializeGrid(ModelKeyProvider<T1> mkpVar, int width, String widthUnit, int height, String heightUnit, int autoExpandColumn)`
- `void initializeGrid(ModelKeyProvider<T1> mkpVar, int width, int height, int autoExpandColumn)`
- `void setEditor(int intColumnIndex, IsField<String> field)`
- `T2 getPropertyAccess()`
- `CheckBoxSelectionModel addCheckSelectionColumn(Style.SelectionMode mode)`
- `DmColumnConfig<T1, String> addTextColumn(ValueProvider vp, String header, int width, String dbFieldName)`
- `DmColumnConfig<T1, String> addTextColumn(ValueProvider vp, String header, int width, String dbFieldName, DmEnum.DmDataType dbFieldDataType)`
- `DmColumnConfig<T1, String> addTextColumn(ValueProvider vp, String header, int width, String dbFieldName, DmEnum.DmDataType dbFieldDataType, String dbFieldNameWithNoFormating)`
- `RowNumberer<T1> addRowNumberColumn()`
- `Grid<T1> getGrid()`
- `void commitChanges()`
- `CellSelection getSelectedCell()`
- `int getWidth()`
- `int getHeight()`
- `void setDefaultColumnIndex(int defaultColumnIndex)`
- `void lockScroll()`
- `void releaseScroll()`

Nested: interface `BeforeInitialize`, interface `InitializeGridCallBack`, interface `CompleteEditCallBack`, interface `AutoAddRow`

### DmGrid_Bkp (DmGrid_Bkp.java, 531 lines)
extends/implements: `BlurHandler`

Constructors:
- `DmGrid_Bkp(Object t2PrpAcs)`

Public methods:
- `void beforeInitialize(ColumnModel<T1> columnModel)`
- `void onInitialized()`
- `void onCompleteEdit(CompleteEditEvent<T1> event)`
- `T1 addRow(StartEditEvent<T1> event)`
- `void onCellClick(CellClickEvent event)`
- `void onKeyDown(KeyDownEvent eventParent)`
- `void onBlur(BlurEvent event)`
- `void onBrowserEvent(Event event)`
- `void onBrowserEvent(Event event)`
- `void onBrowserEvent(Event event)`
- `void execute()`
- `void onBlur(BlurEvent event)`
- `void execute()`
- `void addBeforeInitializeHandler(BeforeInitialize beforeInitialize)`
- `void addInitializeGridCallBackHandler(InitializeGridCallBack initializeGridCallBack)`
- `void addCompleteEditCallBack(CompleteEditCallBack completeEditCallBack)`
- `void onCompleteEdit(CompleteEditEvent<T1> event)`
- `void addAutoAddRowCallBack(AutoAddRow<T1> autoAddRow)`
- `void onStartEdit(StartEditEvent<T1> event)`
- `void initializeGrid(ModelKeyProvider<T1> mkpVar, int width, int height)`
- `void initializeGrid(ModelKeyProvider<T1> mkpVar, int width, String widthUnit, int height, String heightUnit, int autoExpandColumn)`
- `void initializeGrid(ModelKeyProvider<T1> mkpVar, int width, int height, int autoExpandColumn)`
- `void setEditor(int intColumnIndex, IsField<String> field)`
- `T2 getPropertyAccess()`
- `CheckBoxSelectionModel addCheckSelectionColumn(Style.SelectionMode mode)`
- `DmColumnConfig<T1, String> addTextColumn(ValueProvider vp, String header, int width, String dbFieldName)`
- `DmColumnConfig<T1, String> addTextColumn(ValueProvider vp, String header, int width, String dbFieldName, DmEnum.DmDataType dbFieldDataType)`
- `DmColumnConfig<T1, String> addTextColumn(ValueProvider vp, String header, int width, String dbFieldName, DmEnum.DmDataType dbFieldDataType, String dbFieldNameWithNoFormating)`
- `RowNumberer<T1> addRowNumberColumn()`
- `Grid<T1> getGrid()`
- `void commitChanges()`
- `void commitChanges(boolean isRestoreFocus)`
- `CellSelection getSelectedCell()`
- `int getWidth()`
- `int getHeight()`
- `void setDefaultColumnIndex(int defaultColumnIndex)`
- `void lockScroll()`
- `void releaseScroll()`

Nested: interface `BeforeInitialize`, interface `InitializeGridCallBack`, interface `CompleteEditCallBack`, interface `AutoAddRow`

### GridValueChangeHandler (GridValueChangeHandler.java, 25 lines)
extends/implements: `ValueChangeHandler<String>`

Constructors:
- `GridValueChangeHandler(int columnIndex)`

Public methods:
- `void onCommitChanges(int columnIndex, ValueChangeEvent<String> event)`
- `void onValueChange(ValueChangeEvent<String> event)`

Nested: interface `CommitChangesCallBack`

### WidgetAdapter (WidgetAdapter.java, 273 lines)
extends/implements: `IsWidgetExtended BlurHandler, ClickHandler, AttachEvent.Handler, DmRootEvents`

Constructors:
- `WidgetAdapter()`

Public methods:
- `void hide()`
- `void onOk()`
- `void addCallBack(CallBack callBack)`
- `void onKeyDown(KeyDownEvent event)`
- `boolean isVisibility()`
- `void setVisibility(boolean visibility)`
- `void onBlur(BlurEvent event)`
- `void onBrowserEvent(Cell.Context context, Element elem, NativeEvent event)`
- `void show(final DmTextHelp obj, Element element, DmClient.ViaList vl, String tag, String text)`
- `void onBlur(BlurEvent event)`
- `void execute()`
- `void show(final DmTextHelpMulti obj, Element element, Cell.Context context, DmClient.ViaList vl, String tag, String text)`
- `void execute()`
- `void show(final DmTextBox obj, Element element, Cell.Context context, DmClient.ViaList vl, String tag, String text)`
- `void execute()`
- `void show(final DmTextBoxNumeric obj, Element element, Cell.Context context, DmClient.ViaList vl, String tag, String text)`
- `void execute()`
- `void show(final Button obj, Element element, Cell.Context context, DmClient.ViaList vl)`
- `void execute()`
- `void onClick(ClickEvent event)`
- `void onOk()`
- `void hide()`
- `Widget asWidget()`
- `void searchRecord(String key)`
- `void onAttachOrDetach(AttachEvent event)`
- `void rootKeyInvoked(DmRootEvent rootEvent)`

Nested: interface `CallBack`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmReportLayout.shared`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTextHelpMulti.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.dmToolkit.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
