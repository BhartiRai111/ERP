# dm-web-jobs / self

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/webJobs/src/self/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/WebJobs.java | 283 |
| server/WebJobsDataStructure.java | 153 |
| server/WebJobs_Cntx.java | 93 |
| shared/WebJobs_Model.java | 61 |
| shared/WebJobs_Shared.java | 10 |

## Server `WebJobs_Cntx.java` (93 lines)
### `contextInitialized`
`public void contextInitialized(ServletContextEvent sce)`


## Server `WebJobsDataStructure.java` (153 lines)
### `wjTemplateFileDetail`
`private void wjTemplateFileDetail(String tableName) throws Exception`


### `enviro`
`private void enviro(String tableName) throws Exception`


### `wjTemplateGroupMast`
`private void wjTemplateGroupMast(String tableName) throws Exception`


### `wjTemplateMast`
`private void wjTemplateMast(String tableName) throws Exception`


### `wjTemplateAttachment`
`private void wjTemplateAttachment(String tableName) throws Exception`


### `wjTemplateStaffInfo`
`private void wjTemplateStaffInfo(String tableName) throws Exception`


### `wjTemplateSendTo`
`private void wjTemplateSendTo(String tableName) throws Exception`


### `wjApplyConfig`
`private void wjApplyConfig(String tableName) throws Exception`


### `wjMessageQueue`
`private void wjMessageQueue(String tableName) throws Exception`


## Depends on (imports from other packages)
`dataman.dmBase.dmRefreshDataStructure.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMenuBar.client`, `dataman.dmToolkit.dmMenuItem.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.singleLineMaster.client`, `dataman.libraryMenu.self.shared`, `dataman.webJobs.applyConfiguration.client`, `dataman.webJobs.applyConfiguration.server`, `dataman.webJobs.configureEmailAccount.client`, `dataman.webJobs.configureEmailAccount.server`, `dataman.webJobs.configureSMSAccount.client`, `dataman.webJobs.configureSMSAccount.server`, `dataman.webJobs.configureWhatsAppAccount.client`, `dataman.webJobs.configureWhatsAppAccount.server`, `dataman.webJobs.emailManager.client`, `dataman.webJobs.emailManager.server`, `dataman.webJobs.emailTemplateMast.client`, `dataman.webJobs.emailTemplateMast.server`, `dataman.webJobs.scheduler.client`, `dataman.webJobs.scheduler.server`, `dataman.webJobs.smsManager.client`, `dataman.webJobs.smsManager.server`, `dataman.webJobs.smsTemplate.client`, `dataman.webJobs.smsTemplate.server`, `dataman.webJobs.staffInfo.client`, `dataman.webJobs.staffInfo.server`, `dataman.webJobs.whatsAppTemplateMast.client`, `dataman.webJobs.whatsAppTemplateMast.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
