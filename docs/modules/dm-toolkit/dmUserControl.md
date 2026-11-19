# dm-toolkit / dmUserControl

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmUserControl/`). Hand-written notes are in the "Notes" section.

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
| client/DmControlBlog.java | 31 |
| client/DmTextBlog.java | 57 |
| client/DmUserControl.java | 159 |
| client/DmUserControl_Dsg.java | 22 |

## Widget API (auto-extracted from client classes)

### DmControlBlog (DmControlBlog.java, 30 lines)
Constructors:
- `DmControlBlog(IsWidget isWidget)`

Public methods:
- `void setVisible(boolean visible)`
- `IsWidget getControlBlog()`
- `FlowPanel getControlBlogContainer()`

### DmTextBlog (DmTextBlog.java, 56 lines)
Constructors:
- `DmTextBlog(String caption, String value, int width, boolean leftBoder, boolean rightBorder)`

Public methods:
- `InlineLabel getCaptionLabel()`
- `InlineLabel getValueLabel()`
- `String getCaption()`
- `void setCaption(String caption)`
- `String getValue()`
- `void setValue(String value)`

### DmUserControl (DmUserControl.java, 158 lines)
extends/implements: `DmUserControl_Dsg IsWidget`

Constructors:
- `DmUserControl()`

Public methods:
- `DmControlBlog addGeneralBlog(IsWidget isWidget)`
- `void addControlBlog(String caption, IsWidget isWidget)`
- `void addGap(int gapPercentage)`
- `void removeTextBlog(String caption)`
- `void addTextBlog(String caption, int width, boolean leftBoder, boolean rightBorder)`
- `void setTextBlog(String caption, String value)`
- `String getTextBlog(String caption)`
- `void setColor(String caption, String colorCode)`
- `void setFocus()`
- `String[] getUserControlKeys()`
- `void disableAll()`
- `void setVisible(String[] keys)`
- `void setVisible(String caption)`
- `Widget asWidget()`

## Depends on (imports from other packages)
`dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.self.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Screen ke neeche ki **strip**: `TextBlog` (caption + value, jaise "Prepared By", "Modified By", "System Generated") aur `ControlBlog` (koi bhi widget). `addTextBlog(caption, width, leftBorder, rightBorder)`, `setTextBlog(caption, value)`, `getTextBlog(caption)`, `addGap(%)`. **Permission:** `setVisible(String[] controlNames)` se sirf allowed blogs dikhte hain (user ka control permission), `disableAll()`. `DmTopControl` Browse me allowed ones dikhata hai, New/Edit me sab disable. Frontend: `<UserControlStrip blogs />`.
