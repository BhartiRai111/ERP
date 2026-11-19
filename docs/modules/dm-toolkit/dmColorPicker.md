# dm-toolkit / dmColorPicker

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmColorPicker/`). Hand-written notes are in the "Notes" section.

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
| client/Color.java | 258 |
| client/ColorPicker.java | 737 |
| client/SliderBar.java | 247 |
| client/SliderMap.java | 202 |
| client/TransparencyImpl.java | 65 |
| client/images/ColorPickerImageResource.java | 66 |

## Widget API (auto-extracted from client classes)

### Color (Color.java, 256 lines)

Public methods:
- `int getBlue()`
- `int getGreen()`
- `String getHex()`
- `int getHue()`
- `int getRed()`
- `int getSaturation()`
- `int getValue()`
- `void setHex(String hex)`
- `void setHSV(int h, int s, int v)`
- `void setRGB(int r, int g, int b)`

### ColorPicker (ColorPicker.java, 724 lines)
extends/implements: `Composite KeyPressHandler, ClickHandler, ChangeHandler`

Constructors:
- `ColorPicker()`

Public methods:
- `void close(int statusOfClosing)`
- `void onClick(ClickEvent event)`
- `void onClick(ClickEvent event)`
- `void addCloseCallback(CloseCallback callback)`
- `String getHexColor()`
- `Color getRGBColor()`
- `void onAttach()`
- `void onBarSelected(int y)`
- `void onChange(ChangeEvent event)`
- `void onChange(Widget sender)`
- `void onClick(ClickEvent event)`
- `void onClick(Widget sender)`
- `void onKeyPress(KeyPressEvent event)`
- `void onMapSelected(int x, int y)`
- `void setHex(String hex)`
- `void setHSV(int hue, int saturation, int value)`
- `void setRGB(int red, int green, int blue)`

Nested: interface `CloseCallback`

### SliderBar (SliderBar.java, 246 lines)
extends/implements: `HTML`

Constructors:
- `SliderBar(ColorPicker parent)`

Public methods:
- `void onAttach()`
- `void onBrowserEvent(Event event)`
- `void onLoad()`
- `void onUnload()`
- `void setColorSelectMode(int mode)`
- `void setLayerColor(String color, int layer)`
- `void setLayerOpacity(int alpha, int layer)`
- `void setSliderPosition(int y)`

### SliderMap (SliderMap.java, 201 lines)
extends/implements: `HTML`

Constructors:
- `SliderMap(ColorPicker parent)`

Public methods:
- `void onAttach()`
- `void onBrowserEvent(Event event)`
- `void onLoad()`
- `void onUnload()`
- `void setColorSelectMode(int mode)`
- `void setOverlayColor(String color)`
- `void setOverlayOpacity(int alpha)`
- `void setSliderPosition(int x, int y)`
- `void setUnderlayColor(String color)`

### TransparencyImpl (TransparencyImpl.java, 60 lines)

Public methods:
- `native float getIEVersion()`
- `void setBackgroundColor(Element elem, String color)`
- `void setTransparency(Element elem, int alpha)`

### ColorPickerImageResource (ColorPickerImageResource.java, 65 lines)
extends/implements: `ClientBundle`


Public methods:
- `ImageResource bar_blue_bl()`
- `ImageResource bar_blue_br()`
- `ImageResource bar_blue_tl()`
- `ImageResource bar_blue_tr()`
- `ImageResource bar_brightness()`
- `ImageResource bar_green_bl()`
- `ImageResource bar_green_br()`
- `ImageResource bar_green_tl()`
- `ImageResource bar_green_tr()`
- `ImageResource bar_red_bl()`
- `ImageResource bar_red_br()`
- `ImageResource bar_red_tl()`
- `ImageResource bar_red_tr()`
- `ImageResource bar_saturation()`
- `ImageResource bar_hue()`
- `ImageResource bar_white()`
- `ImageResource rangearrows()`
- `ImageResource map_blue_max()`
- `ImageResource map_blue_min()`
- `ImageResource map_brightness()`
- `ImageResource map_green_max()`
- `ImageResource map_green_min()`
- `ImageResource map_hue()`
- `ImageResource mappoint()`
- `ImageResource map_red_max()`
- `ImageResource map_red_min()`
- `ImageResource map_saturation()`
- `ImageResource map_saturation_overlay()`
- `ImageResource map_white()`

## Depends on (imports from other packages)
`dataman.dmToolkit.dmColorPicker.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
