# pb-st-plugin / stPlugin

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/stPlugin/src/stPlugin/`). Hand-written notes are in the "Notes" section.

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
| MiStartUp.java | 301 |
| STFunctions.java | 176 |
| STPluginServer.java | 444 |
| STPlugin_Router.java | 16 |
| frmCurrentDirectory.java | 90 |
| frmMiStartupResults.java | 194 |

## Depends on (imports from other packages)
`dataman.dmBase.jvmSerialization.shared`, `dataman.dmBase.self.shared`, `dataman.dmPlugin.CFunctions`, `dataman.dmPlugin.ILocalSettingsRefreshCommander`, `dataman.dmPlugin.resources.DmPlugInResources`, `dataman.dmPlugin.routing.DmPluginBaseRouter`, `dataman.dmPlugin.server.util`, `dataman.libraryMenu.mi.shared`, `dataman.mi.routing.MiConfigService`, `dataman.mi.routing.MiPersistanceService`, `dataman.miToolkit.MiToolkit`, `dataman.radiology.DmProcess`, `dataman.radiology.RadiologyDocCommands`, `dataman.stPlugin.swing.controls`

## Classes (plain classes: auto-extracted)

### MiStartUp (MiStartUp.java, 301 lines)
class; extends/implements: `ILocalSettingsRefreshCommander`

Public methods:
- `boolean containsMachineInRunningMode(String machineCode, String manualMachineCode)`
- `boolean containsMachineInRunningMode(MIConfigEnum miConfig, String manualMachineCode)`
- `IMachineConfiguration getRunningMachineFromCache(MIConfigEnum miConfig, String manualMachineCode)`
- `void removeMachineConfigFromRunningCache(IMachineConfiguration miConfig)`
- `void addMachineConfigToRunningCache(IMachineConfiguration miConfig)`
- `IMachineConfiguration createMachineInterfaceParserInstance(MIConfigEnum miConfig)`
- `void executeAfterLocalSettingReloaded(JVMLocalSetting_Mdl reloadedLocalSettings, Map<String, String> additionalParams)`
- `void saveMachineInterfaceSettings(String jsonOfLocalSettings, Map<String, String> additionalParams)`
- `String saveMachineInterfaceSettings(JVMLocalSetting_Mdl localSettings, Map<String, String> additionalParams)`
- `void executeAfterServerStarted()`
- `void executeAfterServerStopped()`

### STFunctions (STFunctions.java, 175 lines)
class; extends/implements: `CFunctions`

Public methods:
- `DmProcess putRadiologyReportProcess(RadiologyDocumentRequest_Mdl requestModel)`
- `RadiologyDocumentRequest_Mdl getStoredRadiologyRequestModel(RadiologyDocumentRequest_Mdl requestModel)`
- `DmProcess getRadiologyReportProcess(RadiologyDocumentRequest_Mdl requestModel)`
- `DmProcess removeRadiologyReportProcess(RadiologyDocumentRequest_Mdl requestModel)`
- `void removeAndKillAllRadiologyReportsProcesses(RadiologyDocumentRequest_Mdl requestModel)`
- `void transmitPersistanceModelsToServer(List<MiPersistenceModel> listPersistanceModels)`

### STPluginServer (STPluginServer.java, 443 lines)

Public methods:
- `ExecutorService getCachedThreads()`
- `void main(String[] args)`
- `void actionPerformed(ActionEvent e)`
- `void actionPerformed(ActionEvent e)`
- `void actionPerformed(ActionEvent e)`
- `void actionPerformed(ActionEvent e)`

### STPlugin_Router (STPlugin_Router.java, 16 lines)
class; extends/implements: `DmPluginBaseRouter`

Public methods:
- `void addSTPlugRoutingMappings()`

### frmCurrentDirectory (frmCurrentDirectory.java, 88 lines)
class; extends/implements: `javax.swing.JFrame`

Public methods:
- `void actionPerformed(java.awt.event.ActionEvent evt)`
- `void actionPerformed(java.awt.event.ActionEvent evt)`

### frmMiStartupResults (frmMiStartupResults.java, 189 lines)
class; extends/implements: `javax.swing.JFrame`

Public methods:
- `int getColumnCount()`
- `String getColumnName(int column)`
- `int getRowCount()`
- `String getValueAt(int row, int column)`
- `boolean isCellEditable(int row, int column)`
- `void actionPerformed(java.awt.event.ActionEvent evt)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
