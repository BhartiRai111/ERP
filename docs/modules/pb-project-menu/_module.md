# pb-project-menu: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/paperbiz/projectMenu/src` (37 java files, 1717 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
`pb-project-menu`: **menu ke naam/links** (`pmMaster`, `pmSales`... `MenuLink(moduleName, caption)` constants) jo har screen permission lookup (`DmClient.searchMenu(module, menuText)`) ke liye use karti hai; `mi` (machine interface) aur `radiologyDoc` menus, `servlet` (4). 37 files.

## Summary
| Kind | Features | Lines |
|---|---|---|
| shared only | 4 | 1717 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [self](self.md) | shared only | 21 | 968 | 0 | 716 | master 107, store 102, selfToolkit 98, dashBoard 94 | - |
| [mi](mi.md) | shared only | 9 | 555 | 0 | 7 | paperbiz 3, miToolKit 2, selfToolkit 2 | - |
| [servlet](servlet.md) | shared only | 4 | 42 | 0 | 1 | selfToolkit 1 | - |
| [radiologyDoc](radiologyDoc.md) | shared only | 3 | 152 | 0 | 0 |  | - |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-base` 18, `dm-toolkit` 1

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 17 |
| `dm-toolkit` / `dmStatic` | 1 |
| `dm-base` / `dmBaseStatic.shared` | 1 |
