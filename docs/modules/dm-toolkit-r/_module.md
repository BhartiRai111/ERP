# dm-toolkit-r: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/dataman_V2/dmToolkitR/src` (22 java files, 3649 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
`dm-toolkit-r`: `dm-toolkit` ka **reduced (R) version**: sirf `dmStatic`, `dmGrid`, `dmGridPaging`, `dmTextBox`, `dmTextHelp`, `dmPageHelp`, `dmFileServlets`, `isWidgetExtended` (22 files). Kisi doosre module ka dependency nahi (sirf `dm-base` par). Shayad alag (kam-widget) client ke liye; zaroorat par dekhenge.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 1 | 2147 |
| UI only | 6 | 1199 |
| plain classes | 1 | 303 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [dmStatic](dmStatic.md) | UI + server | 4 | 2147 | 0 | 5 | dmToolkitR 5 | User_permission_web, user_control_permission_web |
| [dmGrid](dmGrid.md) | UI only | 6 | 339 | 0 | 3 | dmToolkitR 3 | - |
| [dmGridPaging](dmGridPaging.md) | UI only | 2 | 331 | 0 | 2 | dmToolkitR 2 | - |
| [dmTextBox](dmTextBox.md) | UI only | 2 | 110 | 0 | 1 | dmToolkitR 1 | - |
| [dmPageHelp](dmPageHelp.md) | UI only | 2 | 181 | 0 | 1 | dmToolkitR 1 | - |
| [dmFileServlets](dmFileServlets.md) | plain classes | 3 | 303 | 0 | 1 | dmToolkitR 1 | - |
| [isWidgetExtended](isWidgetExtended.md) | UI only | 1 | 27 | 0 | 0 |  | - |
| [dmTextHelp](dmTextHelp.md) | UI only | 2 | 211 | 0 | 0 |  | - |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-base` 51

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 29 |
| `dm-base` / `dmSession.server` | 7 |
| `dm-base` / `dmBaseStatic.shared` | 3 |
| `dm-base` / `dmPrepareStatement.server` | 3 |
| `dm-base` / `dmFileManager.shared` | 3 |
| `dm-base` / `dmFileManagerMongo.server` | 3 |
| `dm-base` / `dmBaseStatic.client` | 2 |
| `dm-base` / `dmBaseStatic.server` | 1 |
