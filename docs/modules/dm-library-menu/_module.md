# dm-library-menu: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/dataman_V2/libraryMenu/src` (9 java files, 408 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
`dm-library-menu`: menu-related shared classes (9 java: `self` aur `mi` = machine interface menu entries). `dm-base` par nirbhar.

## Summary
| Kind | Features | Lines |
|---|---|---|
| shared only | 2 | 408 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [self](self.md) | shared only | 7 | 337 | 0 | 254 | accounts 157, utility 38, webJobs 21, master 11 | - |
| [mi](mi.md) | shared only | 2 | 71 | 0 | 5 | paperbiz 2, selfToolkit 1, stPlugin 1, dmPlugin 1 | - |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-base` 5

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 3 |
| `dm-base` / `dmBaseStatic.shared` | 2 |
