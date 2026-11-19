# pb-app / redirect_changePassword

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/paperbiz/src/redirect_changePassword/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: UserMast
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: New Password (Text); Confirm Password (Text); rule: Confirm Password Not Matched.
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `ChangePassword`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | User Name | DmTextBox | `txtUserName` | `` |

**Save: model field ← widget** (`topControlSaveInitialize`):
- `UserName` ← DmShared.xNull(txtUserName.getText())
- `NewPassword` ← DmShared.xNull(txtNewPassword.getText())
- `ConfirmPasword` ← DmShared.xNull(txtConfirmPassword.getText())
- `SharedKeyFieldName` ← DmShared.xNull(txtUserName.getTag())

**Event handlers (condensed code, CRUD flow and field callbacks):**

`lblLogin.addClickHandler` → `onClick()`
```java
if (DmShared.xNull(txtUserName.getTag2nd()).equalsIgnoreCase("CT")) {
Window.Location.assign(GWT.getHostPageBaseURL() + "welcomeGWT.html" + Window.Location.getQueryString());
} else if (DmShared.xNull(txtUserName.getTag2nd()).equalsIgnoreCase("EM")) {
Window.Location.assign(GWT.getHostPageBaseURL() + "welcomeGWT.html" + Window.Location.getQueryString());
} else {
Window.Location.assign(GWT.getHostPageBaseURL() + "welcomeGWT.html" + Window.Location.getQueryString());
```
- Confirm: "Are you sure? You want to close."

## Files
| file | lines |
|---|---|
| client/ChangePassword.java | 244 |
| client/ChangePassword_Dsg.java | 149 |
| server/ChangePassword_SrvImpl.java | 140 |
| shared/ChangePassword_Mdl.java | 58 |
| shared/ChangePassword_Srv.java | 13 |
| shared/ChangePassword_SrvAsync.java | 13 |
| shared/ChangePassword_Vld.java | 16 |

## UI labels / columns (client)
`User Name`, `New Password`, `Confirm Password`

## Models (shared DTOs)
### ChangePassword_Mdl (extends `KeyField implements Serializable,IsSerializable`)
| type | field |
|---|---|
| String | oldPassword |
| String | userName |
| String | userNatureCode |
| String | newPassword |
| String | confirmPasword |

## RPC interface
| method | params | returns |
|---|---|---|
| saveRecord | ChangePassword_Mdl mrTemp | DmResult<String> |
| getUserDetail | String strName, String strKey | DmResult<ChangePassword_Mdl> |

## Validator `ChangePassword_Vld`
```java
public class ChangePassword_Vld {
    public static void chkSave(ChangePassword_Mdl mrTemp) {
        DmShared.isValidText(mrTemp.getNewPassword(), "New Password", 200);
        DmShared.isValidText(mrTemp.getConfirmPasword(), "Confirm Password", 300);
        if (!DmShared.xNull(mrTemp.getNewPassword()).trim().toUpperCase().equalsIgnoreCase(DmShared.xNull(mrTemp.getConfirmPasword()))) {
            throw new DmException(400, "Confirm Password Not Matched.");
        }
    }
}
```

## Server `ChangePassword_SrvImpl.java` (140 lines)
Helpers used: `DmServer.getConnection`, `DmShared.xNull`

### `saveRecord`
`public DmResult<String> saveRecord(ChangePassword_Mdl mrTemp)`

- SQL 1: `SELECT code,username FROM linkVerification WHERE code=:code and userName=:name AND expiryDate > getdate()`
- SQL 2: `Update UserMast Set PassWd=convert(varchar(32),HashBytes('MD5',:passWd),2) where user_name= :user_name`
- SQL 3: `delete from linkVerification where code=:code`
- Params: `:code` ← `mrTemp.getSharedKeyFieldCode()`; `:name` ← `mrTemp.getSharedKeyFieldName()`; `:PassWd` ← `mrTemp.getSharedKeyFieldName().toLowerCase() + mrTemp.getNewPassword()`; `:user_name` ← `mrTemp.getSharedKeyFieldName().toLowerCase()`; `:code` ← `mrTemp.getSharedKeyFieldCode()`
- Errors: "Your link has been expired"
- Other calls: `ChangePassword_Vld.chkSave`

### `getUserDetail`
`public DmResult<ChangePassword_Mdl> getUserDetail(String strName, String strKey)`

- SQL 1: `select lv.code,um.user_name,un.manualCode as natureCode from userMast um left join userNature un on um.nature = un.code left join linkVerification lv on um.user_name = lv.userName and lv.code = :code and lv.expiryDate > getdate() where um.user_Name = :name`
- Params: `:code` ← `strKey`; `:name` ← `strName`
- Errors: "Your link has been expired."

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmToolContainer.client`, `dataman.dmToolkit.dmUserControl.client`, `dataman.dmToolkit.isWidgetExtended.client`

## Business rules (derived from code conditions)
| method | when | error message |
|---|---|---|
| saveRecord | `if (!rsTemp.next())` | "Your link has been expired" |
| saveRecord | `if (DmShared.xNull(mrTemp.getSharedKeyFieldName()).equalsIgnoreCase(""))` | "Your link has been expired" |
| getUserDetail | `if (rsTemp.next())` | "Your link has been expired." |

## Notes (hand-written)
**Password badalna (email link se):** link me `linkVerification.code` + userName, expiry check (**"Your link has been expired"**). Save par `UserMast.PassWd = MD5(lower(user) + newPassword)` aur link row delete. (Naye design me bhi same hash jab tak DB password format wahi hai.)
