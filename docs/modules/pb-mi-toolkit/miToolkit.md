# pb-mi-toolkit / miToolkit

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/miToolKit/src/miToolkit/`). Hand-written notes are in the "Notes" section.

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
| MachineInterfaceConfiguration.java | 312 |
| MiToolkit.java | 19 |

## Depends on (imports from other packages)


## Classes (plain classes: auto-extracted)

### MachineInterfaceConfiguration (MachineInterfaceConfiguration.java, 312 lines)

Public methods:
- `String toString()`
- `CommunicationProtocolId getProtocol(String protocolId)`
- `String toString()`
- `CommunicationMode getCommunicationMode(String communicationMode)`
- `String toString()`
- `ConfigurationConstants getConfigurationConstant(String consValue)`
- `int toInt()`
- `SerialPortDatabits getDataBitsFromValue(int value)`
- `int toInt()`
- `SerialPortFlowControl getSerialPortControlFlow(int value)`
- `int toInt()`
- `SerialPortParity getSerialPortParty(int value)`
- `String toString()`
- `SerialPortStopBits getStopBits(String value)`
- `int toInt()`
- `SerialPortBaudRates getBaudRate(int value)`
- `TreeMap<String, String> getConfigurationSettings()`

### MiToolkit (MiToolkit.java, 19 lines)

Public methods:
- `boolean isNullOrEmpty(String stringToTest)`
- `String nullToEmpty(String stringToConvert)`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
