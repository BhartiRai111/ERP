# pb-self-toolkit / bankDetailInfo

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/bankDetailInfo/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: getTxtHBankAC
- CSV export: no

## Files
| file | lines |
|---|---|
| client/BankDetailInfo.java | 219 |
| client/BankDetailInfo_Dsg.java | 193 |
| server/BankDetailInfo_Server.java | 91 |
| server/BankDetailInfo_SrvImpl.java | 111 |
| shared/BankDetailInfo_Mdl.java | 58 |
| shared/BankDetailInfo_Srv.java | 14 |
| shared/BankDetailInfo_SrvAsync.java | 14 |
| shared/TxtHBankAc_Mdl.java | 58 |

## UI labels / columns (client)
`Bank Name`, `A/C Holder Name`, `A/C No`, `IFSC Code`, `Branch`, `Address`

## Models (shared DTOs)
### TxtHBankAc_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountNo |
| String | acAccountHolder |
| String | branch |
| String | branchAddress |
| String | ifscCode |

### BankDetailInfo_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | accountNo |
| String | acAccountHolder |
| String | branch |
| String | branchAddress |
| String | ifscCode |

## RPC interface
| method | params | returns |
|---|---|---|
| getBankDetail | String voucherCategory, String bankAcCode | DmResult<BankDetailInfo_Mdl> |
| getTxtHBankAC | DmHelper pageing, boolean blnToEndOfFile | DmResultPageing<List<TxtHBankAc_Mdl>> |

## Server `BankDetailInfo_SrvImpl.java` (111 lines)
Helpers used: `DmServer.evaluateHelper`, `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getTxtHBankAC`
`public DmResultPageing<List<TxtHBankAc_Mdl>> getTxtHBankAC(DmHelper helper, boolean blnToEndOfFile)`

- SQL 1: `SELECT CONCAT(bd.subCode, '#', bd.sno) AS bankAcCode , bd.acHolderName, bd.accountNo, bd.bankName , bd.ifscCode, bd.branch, bd.branchAddress FROM bankDetail bd LEFT JOIN subGroup sg ON sg.subCode = bd.subCode LEFT JOIN subGroupNature sgn ON sgn.code = sg.nature WHERE sgn.manualCode = 'BK'`

### `getBankDetail`
`public DmResult<BankDetailInfo_Mdl> getBankDetail(String voucherCategory, String bankAcCode)`

- Other calls: `BankDetailInfo_Server.getBankAcDetail`, `BankDetailInfo_Server.getDefaultBankAcCode`

## Server `BankDetailInfo_Server.java` (91 lines)
Helpers used: `DmShared.xNull`

### `getDefaultBankAcCode`
`public static String getDefaultBankAcCode(String voucherCategory, Connection cnTemp) throws Exception`

- SQL 1: `SELECT vt.bankAcCode FROM voucher_Type vt WHERE vt.category = :voucherCategory`
- Params: `:voucherCategory` ← `DmShared.xNull(voucherCategory)`

### `getBankAcDetail`
`public static BankDetailInfo_Mdl getBankAcDetail(String bankAcCode, Connection cnTemp) throws Exception`

- SQL 1: `SELECT CONCAT(bd.subCode, '#', bd.sno) AS bankAcCode , bd.acHolderName, bd.accountNo, bd.bankName, bd.ifscCode, bd.branch , bd.branchAddress FROM bankDetail bd WHERE CONCAT(bd.subCode, '#', bd.sno) = :code`
- Params: `:code` ← `DmShared.xNull(bankAcCode)`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextHelp.client`, `dataman.dmToolkit.dmTopControl.client`, `paperbiz.selfToolkit.bankDetailInfo.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
