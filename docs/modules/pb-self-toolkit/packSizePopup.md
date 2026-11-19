# pb-self-toolkit / packSizePopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/packSizePopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: packSizeMast
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `PackSizePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | Packing | DmTextBox | `txtPack` | `20, true` |

**Save: model field ← widget** (`topControlSaveInitialize`):
- `PackSize` ← DmShared.xNull(txtPack.getText())
- Alert: "Packing is mandatory."

## Files
| file | lines |
|---|---|
| client/PackSizePopup.java | 134 |
| client/PackSizePopup_Dsg.java | 116 |
| server/PackSizePopup_SrvImpl.java | 89 |
| shared/PackSizePopup_Mdl.java | 22 |
| shared/PackSizePopup_Srv.java | 11 |
| shared/PackSizePopup_SrvAsync.java | 11 |

## UI labels / columns (client)
`Packing`

## Models (shared DTOs)
### PackSizePopup_Mdl
| type | field |
|---|---|
| String | packSize |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | PackSizePopup_Mdl mrTemp | DmResult<String> |

## Server `PackSizePopup_SrvImpl.java` (89 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSerialNoInteger`, `DmServer.getServerTimestamp`, `DmServer.getSsnDBName`, `DmServer.getSsnUserName`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(PackSizePopup_Mdl mrTemp)`

- SQL 1: `select count(*) as cnt from packSizeMast where name =:name`
- SQL 2: `Insert Into packSizeMast(code, name, isActive, preparedBy,preparedDt) Values(:code, :name, :isActive, :preparedBy,:preparedDt)`
- Params: `:name` ← `DmShared.xNull(mrTemp.getPackSize())`; `:code` ← `DmServer.getSerialNoInteger(this, "packSizeMast", cnTemp)`; `:name` ← `DmShared.xNull(mrTemp.getPackSize())`; `:isActive` ← `"1"`; `:preparedBy` ← `DmServer.getSsnUserName(this)`; `:preparedDt` ← `DmServer.getServerTimestamp()`
- Errors: "Pack Size is Mandatory" | "Pack Size already exist."

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.packSizePopup.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (DmShared.xNull(mrTemp.getPackSize()).equalsIgnoreCase(""))` | "Pack Size is Mandatory" |
| saveRecord | `if (rsTemp.getLong("cnt") > 0)` | "Pack Size already exist." |

## Notes (hand-written)
Pack size ka quick-add popup (`packSizeMast` insert; `saveRecord`).
