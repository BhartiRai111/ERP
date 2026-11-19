# dm-web-jobs: module note (survey)

> Status: DRAFT (auto-survey). Source: `legacy-gwt/dataman_V2/webJobs/src` (142 java files, 30318 lines). Har feature/entry ki apni file is folder me hai: SQL, fields, validators, RPC methods auto-extracted; "Notes (hand-written)" jahan likhe hain.
> "Used by" = kitni files (baaki features/modules me) is feature ke package ko import karti hain.

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
