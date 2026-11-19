# dm-toolkit / accountDetailPopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/accountDetailPopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHAccount
- CSV export: no

## Files
| file | lines |
|---|---|
| client/AccountDetailGridPopup.java | 242 |
| client/AccountDetailGridPopup_Dsg.java | 94 |
| client/AccountDetailPopup.java | 220 |
| client/AccountDetailPopup_Dsg.java | 154 |
| server/AccountDetailPopup_SrvImpl.java | 80 |
| shared/AccountDetailPopup_Srv.java | 14 |
| shared/AccountDetailPopup_SrvAsync.java | 15 |
| shared/AccountDetailPopup_Vld.java | 26 |
| shared/AccountDetailPopup_mdl.java | 97 |

## UI labels / columns (client)
`Account`

## RPC interface
| method | params | returns |
|---|---|---|
| getTxtHAccount | DmHelper helper, boolean blnToEndOfFile | DmResultPageing<List<KeyFieldExtended>> |

## Validator `AccountDetailPopup_Vld`
```java
public class AccountDetailPopup_Vld {
    public static void validateAccountDetail(HashMap<String, AccountDetailPopup_mdl> mrTemp) {
        for (String key : mrTemp.keySet()) {
            validateAccountDetail(mrTemp.get(key));
        }
    }
    public static void validateAccountDetail(AccountDetailPopup_mdl accountDetailPopup_mdl) {
        if (accountDetailPopup_mdl != null) {
            if (accountDetailPopup_mdl.isAccountMandatory()) {
                if (DmShared.xNull(accountDetailPopup_mdl.getSharedKeyFieldCode()).trim().equalsIgnoreCase("")) {
                    throw new DmException(0, accountDetailPopup_mdl.getAcPerticularCaption() + " is mandatory!");
                }
            }
        }
    }
}
```

## Server `AccountDetailPopup_SrvImpl.java` (80 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmServer.getSsnSiteCode`, `DmShared.iNull`, `DmShared.xNull`

### `getTxtHAccount`
`public DmResultPageing<List<KeyFieldExtended>> getTxtHAccount(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT sg.subCode , sg.name , sg.manualCode , ct.cityName FROM subGroup sg LEFT JOIN city ct ON ct.cityCode = sg.cityCode LEFT JOIN subGroupSiteList sgl ON sgl.subCode = sg.subCode WHERE sgl.site_Code = :site_Code AND sg.isActive = 1`
- Params: `:site_Code` ← `DmServer.getSsnSiteCode(this)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.accountDetailPopup.shared`, `dataman.dmToolkit.dmCheckBox.client`, `dataman.dmToolkit.dmGrid.client`, `dataman.dmToolkit.dmGridEditPanel.client`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmToolContainer.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
