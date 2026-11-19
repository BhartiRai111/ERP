# pb-mi-toolkit / miToolkit_abstracts

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/miToolkit_abstracts/`). Hand-written notes are in the "Notes" section.

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
| AbstractMachineInterface.java | 161 |
| AbstractMachineParser_SerialAstm.java | 221 |
| AbstractSerialMachineConfig.java | 40 |

## Depends on (imports from other packages)
`dataman.machineInterface.reader.astm`, `dataman.machineInterface.reader.hl7`, `dataman.miToolkit.MachineInterfaceConfiguration`, `dataman.miToolkit.MiToolkit`, `dataman.miToolkit.interfaces.IMachineInterface`, `dataman.miToolkit.interfaces.IMessageChannel`, `dataman.miToolkit.interfaces.readers`, `dataman.miToolkit.interfaces.writers`, `dataman.miToolkit.record.IMachineParserASTM`, `dataman.miToolkit.transport.serialPort`, `dataman.miToolkit.transport.tcp`, `paperbiz.projectMenu.mi.IMachineConfiguration`, `paperbiz.projectMenu.mi.serial`, `paperbiz.projectMenu.mi.shared`

## Classes (plain classes: auto-extracted)

### AbstractMachineInterface (AbstractMachineInterface.java, 161 lines)
class; extends/implements: `IMachineInterface`

Public methods:
- `void startMachineInterface()`
- `IMessageStreamReader<HL7Message> createMessageReader(MachineInterfaceConfiguration.CommunicationProtocolId protocolId)`
- `IMessageStreamWriter<HL7Message> createMessageWriter(MachineInterfaceConfiguration.CommunicationProtocolId protocolId)`
- `void handleReceivedMessage(HL7Message messageReceived)`
- `HL7Message getCorrespondingResponseMessage(HL7Message receivedMessage)`
- `void handleMessage(HL7Message message)`
- `HL7Message getResponseMessage(HL7Message receivedMessage)`
- `void handleHL7Message(HL7Message hl7Message)`
- `HL7Message getResponseHL7Message(HL7Message hL7Message)`
- `void stopMachineInterface()`

### AbstractMachineParser_SerialAstm (AbstractMachineParser_SerialAstm.java, 221 lines)
class; extends/implements: `AbstractSerialMachineConfig IMachineParserASTM`

Public methods:
- `String getCommunicationMode()`
- `String getProtocol()`
- `String getBaudRate()`
- `void setBaudRate(String baudRate)`
- `String getStopBits()`
- `void setStopBits(String stopBits)`
- `String getDataBits()`
- `void setDataBits(String dataBits)`
- `String getParity()`
- `void setParity(String parity)`
- `String getHardwareControl()`
- `void setHardwareControl(String hardwareControl)`
- `String getMachineName()`
- `String getMachineCode()`
- `String getMachineModel()`
- `String getTransmissionMode()`
- `String getCommunicationPort()`
- `void setCommunicationPort(String portToConnect)`
- `List<String> getMachineObservableTestList()`
- `String getMachineManualCode()`
- `void setMachineManualCode(String manualMachineCode)`
- `void start()`
- `void handleASTMMessage(ASTMMessage astmMessage)`
- `void stop()`
- `ASTMSeps getMachineBasedSeperators()`

### AbstractSerialMachineConfig (AbstractSerialMachineConfig.java, 40 lines)
class; extends/implements: `IMachineConfigurationSerial`

Public methods:
- `int hashCode()`
- `boolean equals(Object obj)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
