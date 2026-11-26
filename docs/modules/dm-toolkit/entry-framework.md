# dm-toolkit: entry framework (L2-2) - change log, crud support, widgets, entry screen

Vertical slice: **Unit Master** (`pb-master` `com.paperbiz.master.unit`, frontend `src/entries/master/UnitMasterScreen.tsx`, route `/master/unit-master`). Every other simple master follows the same pattern.

## Backend (dm-toolkit)
| Part | What it does |
|---|---|
| `changelog.ModifyReasonService` | Reason list (`GET /api/modify-reasons`, table `modifyReasonMast`). `log(entry, reason)` writes one document to Mongo `datamodificationlog` of the company file DB (same fields as legacy: session, PLT, company, year, site, user, IP, key, table, module, menu, mode, reason code/name/remark, old/new data as JSON). New/Print/Approve are logged with the reason "System Activity"; Edit/Delete need a reason ("Reason can't empty."). |
| `crud.EntryGuard` | Permission check on the server: opening needs the menu item, New A, Edit E, Delete D, Print P; else "Permission denied." (legacy only greyed buttons). |
| `crud.PageParams` | Query string of Find / lookups / suggestions: `start`, `size` (max 500, 0 = all), `column`+`q` (begins with), `sort`, `desc`, `end` (last page). Columns are whitelisted by the query. |
| `dm-base context.SessionContextHolder` | Request-scoped session details (names for the log) set by `JwtContextFilter`. |

Rules of an entry service (Unit Master is the model): guard -> validate (legacy messages and error numbers) -> key from `SerialNoService` -> duplicate check -> insert/update with audit columns -> **change log in the same transaction** (so a change never exists without its log; a failing log rolls the change back) -> return the key. Delete: DB foreign-key message is returned as before. Old record for the log is read again from the DB (legacy kept a copy in the session).

## Frontend
| Component / hook | Replaces | Behaviour |
|---|---|---|
| `DmTextBox`, `DmNumber` (`lib/entry/numeric`) | dmTextBox, dmTextBoxNumeric | red left border for mandatory, select all on focus; numeric: digits / one "." (only with decimals) / leading "-" (only if allowed), tidied on leaving (no padding zeros, max 7 decimals, natures ROUNDOFF/CEILING/FLOOR) |
| `DmSuggest` | dmSuggestBox | suggestions from server, free text allowed |
| `DmTextHelp` | dmTextHelp | code+name lookup; typing or button opens a paged list searching the first column; Up/Down/Enter/Esc/Tab; First/Previous/Next/Last; leaving restores the last pick (unless appendable) |
| `DmFind` | dmFind | AG Grid, server paging, header click = search column, Ctrl+arrows/R/K, Esc |
| `Dialogs` (`useDialogs`) | dmMessageBox | `alert(message, focusElement)` and `confirm(message)` as promises |
| `TopControl` + `lib/entry/topControl` | dmTopControl | buttons by mode/permission/open record; hot keys F2, Ctrl+N/E/D/T/F/P/S/R/O, Ctrl+arrows, Esc |
| `ModifyReasonDialog` | modifyReason | reason (lookup) + remark, Ok refused without a reason |
| `UserControlStrip` | dmUserControl | Prepared By / Modified By / System Generated |
| `useEntry` + `EntryScreen` | per-screen handlers | New (permission A, defaults), Edit (E), Save (Edit asks the reason), Cancel (confirm "Are you sure? You want to cancel the changes."), Delete (confirm "Are you sure? You want to delete this record.", reason), navigator buttons, Find; backend errors become alerts that focus the field of the error number |

## Not done yet
- Date lock (`datelock`): needed by vouchers, not by masters; comes with the first voucher entry.
- CSV export / import ("Other" menu) of masters: needs the ImportData formats (pb-self-toolkit).
- `DmGrid` editing grids, `DmTextHelpMulti`, `DmCheckbox`, `DmDate`, `DmTime`, `DmMobile`, file upload widgets: with the entries that need them.
- Excel download of Find.
