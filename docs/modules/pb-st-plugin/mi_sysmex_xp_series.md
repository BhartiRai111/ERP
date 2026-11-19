# pb-st-plugin / mi_sysmex_xp_series

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/stPlugin/src/mi_sysmex_xp_series/`). Hand-written notes are in the "Notes" section.

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
| Sysmex_XP_Parser.java | 89 |

## Depends on (imports from other packages)
`dataman.dmPlugin.CFunctions`, `dataman.machineInterface.reader.astm`, `dataman.miToolkit.MiToolkit`, `dataman.miToolkit.abstracts.AbstractMachineParser_SerialAstm`, `dataman.miToolkit.record.AbstractASTMRecord`, `dataman.miToolkit.transport.serialPort`, `dataman.stPlugin.STFunctions`

## Classes (plain classes: auto-extracted)

### Sysmex_XP_Parser (Sysmex_XP_Parser.java, 89 lines)
class; extends/implements: `AbstractMachineParser_SerialAstm`

Public methods:
- `String getProtocolVersion()`
- `void parseMessage(ASTMMessage astmMessage)`
- `ASTMSeps getMachineBasedSeperators()`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
