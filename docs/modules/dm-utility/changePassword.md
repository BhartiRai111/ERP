# dm-utility / changePassword

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/utility/src/changePassword/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: UserMast
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: Old Password (Text); New Password (Text); Confirm Password (Text); rule: Confirm Password Not Matched.
- Lookups/suggestions: -
- CSV export: no

## Files
| file | lines |
|---|---|
| client/ChangePassword.java | 169 |
| client/ChangePassword_Dsg.java | 157 |
| server/ChangePassword_SrvImpl.java | 91 |
| shared/ChangePassword_Mdl.java | 41 |
| shared/ChangePassword_Srv.java | 24 |
| shared/ChangePassword_SrvAsync.java | 24 |
| shared/ChangePassword_Vld.java | 17 |

## UI labels / columns (client)
`Old Password`, `New Password`, `Confirm Password`

## Models (shared DTOs)
### ChangePassword_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | oldPassword |
| String | newPassword |
| String | confirmPasword |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | ChangePassword_Mdl mrTemp | DmResult<String> |

## Validator `ChangePassword_Vld`
```java
public class ChangePassword_Vld {
    public static void chkSave(ChangePassword_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getOldPassword(), "Old Password", 100);
        DmShared.isValidText(mrTemp.getNewPassword(), "New Password", 200);
        DmShared.isValidText(mrTemp.getConfirmPasword(), "Confirm Password", 300);
        if (!DmShared.xNull(mrTemp.getNewPassword()).trim().toUpperCase().equalsIgnoreCase(DmShared.xNull(mrTemp.getConfirmPasword()))) {
            throw new DmException(400, "Confirm Password Not Matched.");
        }
    }
}
```

## Server `ChangePassword_SrvImpl.java` (91 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnUserName`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(ChangePassword_Mdl mrTemp)`

- SQL 1: `SELECT count(*) as Cnt FROM usermast WHERE passwd = convert(varchar(32),HashBytes('MD5',CONCAT( :user_name , :userPass)),2) AND user_name = :user_name`
- SQL 2: `Update UserMast Set PassWd=convert(varchar(32),HashBytes('MD5',:passWd),2) where user_name= :user_name`
- Params: `:user_name` ← `DmServer.getSsnUserName(this).toLowerCase()`; `:userPass` ← `DmShared.xNull(mrTemp.getOldPassword())`; `:PassWd` ← `DmServer.getSsnUserName(this).toLowerCase() + mrTemp.getNewPassword()`; `:user_name` ← `DmServer.getSsnUserName(this).toLowerCase()`
- Errors: "Old Password Is Wrong."
- Other calls: `ChangePassword_Vld.chkSave`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`, `dataman.libraryMenu.self.shared`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (rsTemp.getLong("Cnt") <= 0)` | "Old Password Is Wrong." |

## Notes (hand-written)
_No hand-written notes yet._
