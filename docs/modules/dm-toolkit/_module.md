# dm-toolkit (legacy dataman_V2/dmToolkit): module note

> Status: DRAFT. Source: `legacy-gwt/dataman_V2/dmToolkit/src`. Har feature ki apni file is folder me hai (SQL, fields, validators, RPC methods auto-extracted; "Notes" hand-written jahan likhe hain).
> "Used by" = kitni files (baaki modules/features me) is feature ke package ko import karti hain.

## Summary
| Kind | Features | Lines |
|---|---|---|
| UI + server | 28 | 58862 |
| UI only (frontend widget) | 36 | 19643 |
| server/shared | 10 | 23450 |
| shared only | 2 | 0 |

## All features (most used first)
| Feature | Kind | Files | Lines | Services | Used by (files) | Mostly used by | Writes tables |
|---|---|---|---|---|---|---|---|
| [dmStatic](dmStatic.md) | UI + server | 6 | 5842 | 0 | 2992 | selfToolkit 378, accounts 377, master 330, dmToolkit 243 | user_Permission_Web, User_permission_web, user_control_permission_web, wjMessageQueue, Voucher_Type |
| [dmTextBox](dmTextBox.md) | UI only (frontend widget) | 2 | 120 | 0 | 1212 | accounts 148, master 133, selfToolkit 125, dashBoard 110 | - |
| [dmTextHelp](dmTextHelp.md) | UI only (frontend widget) | 2 | 408 | 0 | 1031 | master 170, accounts 117, utility 114, store 100 | - |
| [dmMessageBox](dmMessageBox.md) | UI only (frontend widget) | 2 | 305 | 0 | 997 | selfToolkit 128, accounts 116, master 113, dashBoard 106 | - |
| [dmLabel](dmLabel.md) | UI only (frontend widget) | 2 | 29 | 0 | 706 | master 107, dashBoard 81, selfToolkit 77, utility 72 | - |
| [dmGrid](dmGrid.md) | UI only (frontend widget) | 10 | 2334 | 0 | 681 | master 98, utility 90, purchase 63, store 55 | - |
| [modifyReason](modifyReason.md) | UI + server | 9 | 1107 | 1 | 645 | master 177, accounts 81, purchase 67, store 65 | - |
| [self](self.md) | UI + server | 20 | 2706 | 0 | 506 | accounts 163, utility 63, master 60, store 45 | userMobileAppPermisson |
| [dmToolContainer](dmToolContainer.md) | UI only (frontend widget) | 2 | 143 | 0 | 436 | selfToolkit 96, dashBoard 82, dmToolkit 37, master 35 | - |
| [dmTextHelpMulti](dmTextHelpMulti.md) | UI only (frontend widget) | 2 | 308 | 0 | 433 | dashBoard 86, selfToolkit 63, accounts 60, store 40 | - |
| [dmTopControl](dmTopControl.md) | UI only (frontend widget) | 2 | 1409 | 0 | 372 | master 84, utility 48, accounts 43, store 33 | - |
| [isWidgetExtended](isWidgetExtended.md) | UI only (frontend widget) | 1 | 41 | 0 | 356 | dashBoard 68, master 56, accounts 36, selfToolkit 29 | - |
| [dmFind](dmFind.md) | UI only (frontend widget) | 2 | 700 | 0 | 342 | master 83, utility 46, accounts 42, store 32 | - |
| [dmTextBoxNumeric](dmTextBoxNumeric.md) | UI only (frontend widget) | 2 | 533 | 0 | 292 | master 48, purchase 29, accounts 29, production 28 | - |
| [walkThrough](walkThrough.md) | UI + server | 15 | 1470 | 1 | 292 | selfToolkit 120, purchase 44, sales 24, accounts 21 | WalkThroughFootPrint, WalkThroughApplicable, walkThroughFootPrint |
| [dmGridPaging](dmGridPaging.md) | UI only (frontend widget) | 2 | 407 | 0 | 250 | dashBoard 62, selfToolkit 62, purchase 22, store 20 | - |
| [dmUserControl](dmUserControl.md) | UI only (frontend widget) | 4 | 265 | 0 | 217 | master 47, accounts 27, utility 25, selfToolkit 16 | - |
| [dmGridEditPanel](dmGridEditPanel.md) | UI only (frontend widget) | 2 | 598 | 0 | 176 | master 33, utility 32, purchase 19, production 15 | - |
| [overHeadLine](overHeadLine.md) | UI + server | 17 | 2732 | 1 | 172 | purchase 66, sales 47, jobWork 23, store 16 | - |
| [dmSuggestBox](dmSuggestBox.md) | UI only (frontend widget) | 2 | 165 | 0 | 165 | master 70, accounts 24, utility 21, webJobs 14 | - |
| [dmReportLayout](dmReportLayout.md) | UI + server | 17 | 2514 | 1 | 160 | accounts 55, store 27, sales 18, purchase 18 | - |
| [overHead](overHead.md) | UI + server | 29 | 4702 | 1 | 159 | purchase 63, sales 39, jobWork 22, store 15 | - |
| [gst](gst.md) | server/shared | 41 | 9695 | 0 | 138 | purchase 46, sales 27, selfToolkit 13, dmToolkit 13 | gstTrans, gstTransHead, eGst, eGstDetail, eGstContractDetail, eGstDocDetail, eGstToken |
| [partyDetailPopup](partyDetailPopup.md) | UI + server | 10 | 2874 | 1 | 129 | purchase 50, sales 33, jobWork 15, store 10 | - |
| [dmRemarkTextBox](dmRemarkTextBox.md) | UI only (frontend widget) | 4 | 471 | 0 | 123 | purchase 22, store 17, master 14, accounts 12 | - |
| [dmGUI](dmGUI.md) | UI + server | 12 | 1891 | 1 | 113 | purchase 24, master 17, production 17, transport 12 | - |
| [dmTermsConfig](dmTermsConfig.md) | UI + server | 8 | 1251 | 1 | 88 | purchase 40, sales 30, jobWork 12, master 5 | termsTrans |
| [dmCheckBox](dmCheckBox.md) | UI only (frontend widget) | 2 | 139 | 0 | 73 | purchase 15, sales 11, selfToolkit 8, transport 7 | - |
| [dmImportData](dmImportData.md) | server/shared | 13 | 4395 | 0 | 68 | selfToolkit 32, paperbiz 20, master 9, accounts 4 | initialMast, overheadmast, SubGroup, subgroupsitelist, AcGroup, AcGroupPath, City, country, stateMast, currency |
| [eGstPopup](eGstPopup.md) | UI + server | 17 | 6813 | 1 | 67 | purchase 24, sales 13, store 8, transport 8 | - |
| [documentSystemReport](documentSystemReport.md) | UI only (frontend widget) | 1 | 99 | 0 | 60 | store 15, purchase 12, sales 6, jobWork 5 | - |
| [dmTextArea](dmTextArea.md) | UI only (frontend widget) | 2 | 112 | 0 | 44 | master 9, dmToolkit 9, webJobs 9, utility 6 | - |
| [stock](stock.md) | server/shared | 7 | 4602 | 0 | 42 | store 14, selfToolkit 10, purchase 5, accounts 4 | stock, stockItemId, stockFifo, itemStock |
| [excelReport](excelReport.md) | server/shared | 2 | 708 | 0 | 41 | accounts 20, sales 9, store 9, purchase 2 | - |
| [dmFileManager](dmFileManager.md) | UI + server | 10 | 1777 | 1 | 35 | master 13, purchase 8, sales 2, gate 2 | - |
| [accountPosting](accountPosting.md) | server/shared | 2 | 274 | 0 | 32 | store 11, purchase 6, sales 5, selfToolkit 5 | - |
| [dmJasper](dmJasper.md) | server/shared | 4 | 340 | 0 | 30 | store 9, accounts 7, dashBoard 6, sales 2 | - |
| [accountAdjustment](accountAdjustment.md) | UI + server | 20 | 3709 | 1 | 28 | accounts 12, purchase 9, jobWork 2, selfToolkit 2 | - |
| [dmDisplayStrip](dmDisplayStrip.md) | UI only (frontend widget) | 4 | 342 | 0 | 26 | sales 12, purchase 10, store 4 | - |
| [dmSnapshot](dmSnapshot.md) | UI + server | 6 | 507 | 1 | 25 | gate 14, master 5, production 2, accounts 2 | - |
| [dmMenuItem](dmMenuItem.md) | UI only (frontend widget) | 2 | 85 | 0 | 22 | dmToolkit 4, sales 2, purchase 2, master 1 | - |
| [dmMenuBar](dmMenuBar.md) | UI only (frontend widget) | 2 | 70 | 0 | 21 | dmToolkit 3, sales 2, purchase 2, master 1 | - |
| [dmTextBoxTime](dmTextBoxTime.md) | UI only (frontend widget) | 2 | 225 | 0 | 21 | production 7, lab 3, dmToolkit 3, gate 2 | - |
| [manageSite](manageSite.md) | UI + server | 6 | 769 | 1 | 19 | master 14, sales 1, assets 1, production 1 | - |
| [tdsPopup](tdsPopup.md) | UI + server | 10 | 2165 | 1 | 19 | accounts 16, dmToolkit 2, selfToolkit 1 | - |
| [dmChart](dmChart.md) | UI only (frontend widget) | 7 | 1205 | 0 | 18 | dashBoard 18 | - |
| [itemAdjustment](itemAdjustment.md) | UI + server | 17 | 3568 | 1 | 16 | accounts 10, purchase 3, production 1, selfToolkit 1 | stockOtherAdjustment |
| [dmFileUploadButton](dmFileUploadButton.md) | UI only (frontend widget) | 3 | 876 | 0 | 15 | webJobs 7, utility 4, master 2, dmToolkit 2 | - |
| [openSourceReport](openSourceReport.md) | UI + server | 4 | 457 | 1 | 15 | lab 4, accounts 4, store 3, assets 2 | - |
| [narrationSearch](narrationSearch.md) | UI + server | 6 | 550 | 1 | 10 | accounts 5, production 2, purchase 2, dmToolkit 1 | - |
| [dmTextBoxMobile](dmTextBoxMobile.md) | UI only (frontend widget) | 2 | 164 | 0 | 9 | dmToolkit 3, master 2, gate 1, selfToolkit 1 | - |
| [dmHelpGrid](dmHelpGrid.md) | UI only (frontend widget) | 2 | 584 | 0 | 8 | accounts 4, master 2, paperbiz 2 | - |
| [dmHelpGridMulti](dmHelpGridMulti.md) | UI only (frontend widget) | 2 | 879 | 0 | 7 | dmToolkit 3, assets 2, production 2 | - |
| [singleLineMaster](singleLineMaster.md) | UI + server | 8 | 1368 | 1 | 6 | master 1, gate 1, assets 1, dmToolkit 1 | - |
| [dmInputBox](dmInputBox.md) | UI only (frontend widget) | 2 | 205 | 0 | 4 | paperbiz 2, dmToolkit 1, accounts 1 | - |
| [gstVoucherEntryApproval](gstVoucherEntryApproval.md) | UI + server | 11 | 1752 | 1 | 4 | dashBoard 1, selfToolkit 1, dmToolkit 1, accounts 1 | - |
| [rdp](rdp.md) | shared only | 0 | 0 | 0 | 4 | dmToolkit 4 | - |
| [sftp](sftp.md) | server/shared | 9 | 1992 | 0 | 4 | dmToolkit 4 | - |
| [voucherEntryApproval](voucherEntryApproval.md) | UI + server | 11 | 1955 | 1 | 4 | dashBoard 1, selfToolkit 1, dmToolkit 1, accounts 1 | - |
| [dmFileUploader](dmFileUploader.md) | UI only (frontend widget) | 25 | 3539 | 0 | 3 | dmToolkit 3 | - |
| [dmMenu](dmMenu.md) | UI only (frontend widget) | 2 | 79 | 0 | 3 | dmToolkit 3 | - |
| [dmMenuBarItem](dmMenuBarItem.md) | UI only (frontend widget) | 2 | 117 | 0 | 3 | dmToolkit 3 | - |
| [dmTextHelpGrid](dmTextHelpGrid.md) | UI only (frontend widget) | 2 | 565 | 0 | 3 | production 2, dmToolkit 1 | - |
| [dmWebSocket](dmWebSocket.md) | UI only (frontend widget) | 4 | 138 | 0 | 3 | dmToolkit 2, paperbiz 1 | - |
| [dmImageCanvas](dmImageCanvas.md) | UI + server | 7 | 1173 | 1 | 2 | dmToolkit 2 | - |
| [dmNotifications](dmNotifications.md) | UI + server | 12 | 646 | 1 | 2 | paperbiz 1, dmToolkit 1 | - |
| [printerMapping](printerMapping.md) | UI + server | 6 | 595 | 1 | 2 | dmToolkit 2 | - |
| [tdsTrans](tdsTrans.md) | server/shared | 2 | 509 | 0 | 2 | accounts 2 | tdsTrans |
| [accountDetailPopup](accountDetailPopup.md) | UI + server | 9 | 939 | 1 | 1 | dmToolkit 1 | - |
| [customizedModule](customizedModule.md) | UI + server | 13 | 1524 | 1 | 1 | dmToolkit 1 | - |
| [digitalSignature](digitalSignature.md) | server/shared | 3 | 650 | 0 | 1 | master 1 | - |
| [dmColorPicker](dmColorPicker.md) | UI only (frontend widget) | 6 | 1925 | 0 | 1 | dmToolkit 1 | - |
| [dmFileServlets](dmFileServlets.md) | shared only | 0 | 0 | 0 | 1 | dmToolkit 1 | - |
| [dmMasterReportLayout](dmMasterReportLayout.md) | UI only (frontend widget) | 1 | 59 | 0 | 0 |  | - |
| [dml](dml.md) | server/shared | 2 | 285 | 0 | 0 |  | - |
| [openSourceModule](openSourceModule.md) | UI + server | 13 | 1506 | 1 | 0 |  | - |
