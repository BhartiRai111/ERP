# dm-toolkit-r / dmTextHelp

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkitR/src/dmTextHelp/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DmTextHelp`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `textBox` (DmTextBox)

**Enable/disable rules** (`setEnabled(flag)`, flag = editing mode):
- `textBox.setEnabled(enable)`

**Event handlers (condensed code, CRUD flow and field callbacks):**

`dmHelp.addPageHelpCallBack` → `populateData()`
```java
if (textHelpCallBack != null) {
textHelpCallBack.populateData(helper, blnToEndOfFile, blnCallBackWait);
```

`dmHelp.addPageHelpCallBack` → `getSelectedRow()`
```java
KeyField keyField;
if (t1Row != null) {
keyField = (KeyField) t1Row;
DmTextHelp.this.setText(keyField.getSharedKeyFieldName());
DmTextHelp.this.setTag(keyField.getSharedKeyFieldCode());
} else if (isAppendable()) {
DmTextHelp.this.setTag("");
} else {
DmTextHelp.this.setText("");
DmTextHelp.this.setTag("");
...
```

## Files
| file | lines |
|---|---|
| client/DmTextHelp.java | 189 |
| client/DmTextHelp_Dsg.java | 22 |

## Depends on (imports from other packages)
`dataman.dmToolkitR.dmPageHelp.client`, `dataman.dmToolkitR.dmTextBox.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
