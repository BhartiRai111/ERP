# pb-st-plugin / mi_mindray_bs240_tcp

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/stPlugin/src/mi_mindray_bs240_tcp/`). Hand-written notes are in the "Notes" section.

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
| BS240_Parser.java | 370 |
| bs240MachineImplementation.java | 74 |

## Depends on (imports from other packages)
`dataman.dmPlugin.CFunctions`, `dataman.machineInterface.reader.astm`, `dataman.machineInterface.reader.hl7`, `dataman.miToolkit.MachineInterfaceConfiguration`, `dataman.miToolkit.MiToolkit`, `dataman.miToolkit.abstracts.AbstractMachineInterface`, `dataman.miToolkit.record.AbstractHL7Record`, `dataman.miToolkit.record.IMachineParserHL7`, `dataman.stPlugin.STFunctions`

## Classes (plain classes: auto-extracted)

### BS240_Parser (BS240_Parser.java, 370 lines)
class; extends/implements: `AbstractTCPMachineConfig IMachineParserHL7`

Public methods:
- `void parseMessage(HL7Message messageToParse)`
- `void clearDummyRecords()`
- `void fillDummyRecords()`
- `String getTransmissionMode()`
- `String getProtocol()`
- `String getCommunicationMode()`
- `String getProtocolVersion()`
- `String getMachineName()`
- `String getMachineCode()`
- `String getMachineModel()`
- `List<String> getMachineObservableTestList()`
- `void setMachineManualCode(String manualMachineCode)`
- `void start()`
- `void processHL7Message(HL7Message hl7Message)`
- `void stop()`
- `void setCommunicationPort(String portToConnect)`
- `String getCommunicationPort()`
- `String getMachineManualCode()`

### bs240MachineImplementation (bs240MachineImplementation.java, 74 lines)
class; extends/implements: `AbstractMachineInterface`

Public methods:
- `void processHL7Message(HL7Message hl7Message)`
- `MachineInterfaceConfiguration getMachineInterfaceConfiguration()`
- `void handleHL7Message(HL7Message hl7Message)`
- `HL7Message getResponseHL7Message(HL7Message receivedHL7Message)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
