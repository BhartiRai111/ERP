# dm-toolkit / dmChart

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmChart/`). Hand-written notes are in the "Notes" section.

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
| client/DmChart.java | 571 |
| client/DmChartMultiValue.java | 447 |
| client/DmChartMultiValue_Dsg.java | 21 |
| client/DmChart_Dsg.java | 42 |
| client/DynamicValueProvider.java | 30 |
| shared/DmChart_Mdl.java | 70 |
| shared/ItemDetail_Mdl.java | 31 |

## Models (shared DTOs)
### ItemDetail_Mdl
| type | field |
|---|---|
| Map<String, Double> | data |
| String | name |

### DmChart_Mdl
| type | field |
|---|---|
| String | groupCode |
| String | groupName |
| String | labelCode |
| String | labelName |
| double | dataValue |

## Widget API (auto-extracted from client classes)

### DmChart (DmChart.java, 570 lines)
extends/implements: `DmChart_Dsg IsWidget`

Constructors:
- `DmChart(int height, int width, DmChartType dmChartType)`

Public methods:
- `void onSelection(DmChart_Mdl dmChart_Mdl)`
- `void addCallBack(CallBack callBack)`
- `void setText(String horizontalText, String verticalText)`
- `void isNameRequiredInPieChart(boolean isNameRequired)`
- `Widget asWidget()`
- `Chart<DmChart_Mdl> drawChart(List<DmChart_Mdl> dataList)`
- `String getLabel(String item)`
- `void onSeriesSelection(SeriesSelectionEvent<DmChart_Mdl> event)`
- `void onSeriesLeaveItem(SeriesItemOutEvent<DmChart_Mdl> event)`
- `void onSeriesOverItem(SeriesItemOverEvent<DmChart_Mdl> event)`
- `void onSeriesItemUp(SeriesItemUpEvent<DmChart_Mdl> event)`
- `void spriteRenderer(Sprite sprite, int index, ListStore<DmChart_Mdl> store)`
- `void highlight(Sprite sprite)`
- `void unHighlight(Sprite sprite)`
- `void onSeriesSelection(SeriesSelectionEvent<DmChart_Mdl> event)`
- `void onSeriesLeaveItem(SeriesItemOutEvent<DmChart_Mdl> event)`
- `void onSeriesOverItem(SeriesItemOverEvent<DmChart_Mdl> event)`
- `void onSeriesItemUp(SeriesItemUpEvent<DmChart_Mdl> event)`
- `void spriteRenderer(Sprite sprite, int index, ListStore<DmChart_Mdl> store)`
- `void highlight(Sprite sprite)`
- `void unHighlight(Sprite sprite)`
- `void onSeriesSelection(SeriesSelectionEvent<DmChart_Mdl> event)`
- `void onSeriesLeaveItem(SeriesItemOutEvent<DmChart_Mdl> event)`
- `void onSeriesOverItem(SeriesItemOverEvent<DmChart_Mdl> event)`
- `void onSeriesItemUp(SeriesItemUpEvent<DmChart_Mdl> event)`

Nested: interface `CallBack`

### DmChartMultiValue (DmChartMultiValue.java, 444 lines)
extends/implements: `DmChartMultiValue_Dsg IsWidget`

Constructors:
- `DmChartMultiValue(int height, int width, DmChartType dmChartType)`

Public methods:
- `void onSelection(DmChart_Mdl dmChart_Mdl)`
- `void addCallBack(CallBack callBack)`
- `void setText(String horizontalText, String verticalText)`
- `Widget asWidget()`
- `void drawChart(List<DmChart_Mdl> dataList)`
- `String getKey(ItemDetail_Mdl item)`
- `String getValue(ItemDetail_Mdl object)`
- `void setValue(ItemDetail_Mdl object, String value)`
- `String getPath()`
- `String getLabel(String item)`
- `String getKey(ItemDetail_Mdl item)`
- `String getValue(ItemDetail_Mdl object)`
- `void setValue(ItemDetail_Mdl object, String value)`
- `String getPath()`

Nested: interface `CallBack`

### DynamicValueProvider (DynamicValueProvider.java, 29 lines)
extends/implements: `ValueProvider<ItemDetail_Mdl, Double>`

Constructors:
- `DynamicValueProvider(String field)`

Public methods:
- `Double getValue(ItemDetail_Mdl object)`
- `void setValue(ItemDetail_Mdl object, Double value)`
- `String getPath()`

## Depends on (imports from other packages)
`dataman.dmBase.self.shared`, `dataman.dmToolkit.dmChart.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
