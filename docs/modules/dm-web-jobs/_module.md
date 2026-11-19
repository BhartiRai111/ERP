# dm-web-jobs: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/dataman_V2/webJobs/src` (142 java files, 30318 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

## Overview (hand-written)
**Web jobs = communication & scheduling:** email/SMS/WhatsApp **templates** (`wjTemplateMast`, send-to, attachments), **accounts** (`configureEmailAccount`, `configureSMSAccount`, `configureWhatsAppAccount`: `wjAccountMast`), `applyConfiguration` (`wjApplyConfig`: kis event par kaun sa template), `scheduler` (`schedulerMast`), `staffInfo`, `emailManager`, `smsManager`. Engine `dm-base` ke `dmScheduler` (baaki) me.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 12 | 30318 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [emailManager](emailManager.md) | UI + server | 15 | 3449 | 1 | 10 | webJobs 10 | - |
| [self](self.md) | UI + server | 5 | 600 | 0 | 8 | webJobs 6, paperbiz 2 | - |
| [smsTemplate](smsTemplate.md) | UI + server | 14 | 3453 | 1 | 8 | webJobs 7, paperbiz 1 | wjTemplateMast |
| [staffInfo](staffInfo.md) | UI + server | 9 | 667 | 1 | 7 | webJobs 7 | wjTemplateStaffInfo |
| [emailTemplateMast](emailTemplateMast.md) | UI + server | 21 | 4549 | 1 | 3 | webJobs 2, paperbiz 1 | wjTemplateMast |
| [configureSMSAccount](configureSMSAccount.md) | UI + server | 8 | 1552 | 1 | 2 | webJobs 2 | wjAccountMast |
| [whatsAppTemplateMast](whatsAppTemplateMast.md) | UI + server | 20 | 4422 | 1 | 2 | webJobs 2 | wjTemplateMast, wjTemplateSendTo, wjTemplateAttachment, wjTemplateFileDetail |
| [applyConfiguration](applyConfiguration.md) | UI + server | 10 | 1743 | 1 | 2 | webJobs 2 | wjApplyConfig |
| [smsManager](smsManager.md) | UI + server | 11 | 2745 | 1 | 2 | webJobs 2 | - |
| [scheduler](scheduler.md) | UI + server | 9 | 3418 | 1 | 2 | webJobs 2 | schedulerMast |
| [configureWhatsAppAccount](configureWhatsAppAccount.md) | UI + server | 8 | 1429 | 1 | 2 | webJobs 2 | wjAccountMast |
| [configureEmailAccount](configureEmailAccount.md) | UI + server | 12 | 2291 | 1 | 2 | webJobs 2 | wjAccountMast |

## Dependencies (auto from imports of this module's java files)

**Other modules used** (number of import statements): `dm-toolkit` 484, `dm-base` 363, `dm-library-menu` 22

**Most used shared features:**

| Shared feature | Imports |
|---|---|
| `dm-base` / `self.shared` | 267 |
| `dm-toolkit` / `dmStatic` | 115 |
| `dm-toolkit` / `modifyReason` | 63 |
| `dm-toolkit` / `dmMessageBox` | 40 |
| `dm-toolkit` / `dmTextBox` | 36 |
| `dm-toolkit` / `dmGrid` | 34 |
| `dm-toolkit` / `dmLabel` | 25 |
| `dm-toolkit` / `dmTextHelp` | 24 |
| `dm-base` / `dmPrepareStatement.server` | 21 |
| `dm-base` / `dmFileManager.shared` | 18 |
| `dm-base` / `dmBaseStatic.client` | 18 |
| `dm-toolkit` / `self` | 15 |
| `dm-base` / `dmSession.server` | 15 |
| `dm-toolkit` / `dmSuggestBox` | 14 |
| `dm-toolkit` / `dmTopControl` | 14 |
| `dm-toolkit` / `dmFind` | 14 |
| `dm-toolkit` / `dmFileUploadButton` | 14 |
| `dm-toolkit` / `dmToolContainer` | 12 |
| `dm-toolkit` / `dmGridEditPanel` | 11 |
| `dm-toolkit` / `isWidgetExtended` | 10 |
| `dm-toolkit` / `dmUserControl` | 9 |
| `dm-toolkit` / `dmTextArea` | 9 |
| `dm-base` / `dmBaseStatic.server` | 7 |
| `dm-base` / `dmScheduler.server` | 7 |
| `dm-toolkit` / `dmTextBoxNumeric` | 4 |
