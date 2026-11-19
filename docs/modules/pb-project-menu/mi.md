# pb-project-menu / mi

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/projectMenu/src/mi/`). Hand-written notes are in the "Notes" section.

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
| IMachineConfiguration.java | 31 |
| jvm/MIExtendedStartupParams.java | 148 |
| jvm/MiPersistenceModel.java | 42 |
| serial/IMachineConfigurationSerial.java | 35 |
| shared/MIConfigCommands.java | 31 |
| shared/MIConfigDetailsParams.java | 70 |
| shared/MIConfigEnum.java | 94 |
| tcp/AbstractTCPMachineConfig.java | 39 |
| tcp/IMachineConfigurationTcp.java | 16 |

## Depends on (imports from other packages)
`paperbiz.projectMenu.mi.IMachineConfiguration`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
