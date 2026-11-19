# dm-base: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/dataman_V2/dmBase/src` (176 java files, 34414 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Summary
| Kind | Features | Lines |
|---|---|---|
| server/shared | 8 | 21841 |
| UI + server | 2 | 7569 |
| shared only | 2 | 544 |
| plain classes | 6 | 4460 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [dataman_dmBase_self](dataman_dmBase_self.md) | server/shared | 66 | 11564 | 0 | 5668 | selfToolkit 678, accounts 672, master 663, store 474 | - |
| [dataman_dmBase_dmBaseStatic](dataman_dmBase_dmBaseStatic.md) | UI + server | 9 | 5611 | 0 | 1036 | selfToolkit 176, dashBoard 116, master 91, accounts 86 | - |
| [dataman_dmBase_dmSession](dataman_dmBase_dmSession.md) | UI + server | 21 | 1958 | 0 | 689 | accounts 103, selfToolkit 94, dmToolkit 71, master 70 | - |
| [dataman_dmBase_dmPrepareStatement](dataman_dmBase_dmPrepareStatement.md) | server/shared | 5 | 1236 | 0 | 677 | accounts 121, selfToolkit 106, master 56, store 53 | - |
| [dataman_dmBase_dmFileManager](dataman_dmBase_dmFileManager.md) | shared only | 5 | 352 | 0 | 69 | dmToolkit 20, webJobs 17, master 9, gate 9 | - |
| [dataman_dmBase_dmFileManagerMongo](dataman_dmBase_dmFileManagerMongo.md) | server/shared | 1 | 702 | 0 | 33 | dmToolkit 7, master 6, gate 4, dmToolkitR 3 | LINKFILECOLLECTION |
| [dataman_dmBase_dmRefreshDataStructure](dataman_dmBase_dmRefreshDataStructure.md) | server/shared | 3 | 1453 | 0 | 17 | paperbiz 3, sales 1, jobWork 1, assets 1 | - |
| [dataman_dmBase_dmScheduler](dataman_dmBase_dmScheduler.md) | server/shared | 23 | 3727 | 0 | 13 | webJobs 4, utility 4, dmToolkit 2, dmBase 2 | - |
| [dataman_dmBase_jvmSerialization](dataman_dmBase_jvmSerialization.md) | shared only | 3 | 192 | 0 | 10 | dmBase 3, stPlugin 2, dmPlugin 2, paperbiz 1 | - |
| [dataman_dmBase_dmEventbus](dataman_dmBase_dmEventbus.md) | server/shared | 8 | 399 | 0 | 9 | utility 4, dmBase 3, selfToolkit 1, dmToolkit 1 | - |
| [dataman_queue](dataman_queue.md) | plain classes | 6 | 1455 | 0 | 6 | dmToolkit 5, dmBase 1 | - |
| [net_sf_jasperreports_engine_base](net_sf_jasperreports_engine_base.md) | plain classes | 1 | 10 | 0 | 4 | dmBase 2, accounts 2 | - |
| [dataman_dmBase_dmDynamicReportBuilder](dataman_dmBase_dmDynamicReportBuilder.md) | server/shared | 9 | 2195 | 0 | 3 | dmBase 2, dmToolkit 1 | - |
| [dataman_queue_page](dataman_queue_page.md) | plain classes | 4 | 542 | 0 | 3 | dmBase 3 | - |
| [dataman_queue_utils](dataman_queue_utils.md) | plain classes | 3 | 90 | 0 | 3 | dmBase 3 | - |
| [dataman_dmBase_dmBaseJasper](dataman_dmBase_dmBaseJasper.md) | server/shared | 6 | 565 | 0 | 1 | dmToolkit 1 | - |
| [dataman_queue_cache](dataman_queue_cache.md) | plain classes | 2 | 255 | 0 | 1 | dmBase 1 | - |
| [net_sf_jasperreports_engine_export](net_sf_jasperreports_engine_export.md) | plain classes | 1 | 2108 | 0 | 1 | dmBase 1 | - |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): none

