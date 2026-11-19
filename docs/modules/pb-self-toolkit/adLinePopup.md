# pb-self-toolkit / adLinePopup

> Auto-extracted facts from legacy GWT source (`legacy-gwt/paperbiz/selfToolkit/src/paperbiz/selfToolkit/adLinePopup/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: -
- Update tables: -
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `AdLinePopup`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|
| 1 | - | DmTextBoxNumeric | `txtDeduction1Per` | `4, false, 8, false` |
| 2 | - | DmTextBoxNumeric | `txtDeduction1Amt` | `2, false, 12, false` |
| 3 | - | DmTextBoxNumeric | `txtDeduction2Per` | `4, false, 8, false` |
| 4 | - | DmTextBoxNumeric | `txtDeduction2Amt` | `2, false, 12, false` |
| 5 | - | DmTextBoxNumeric | `txtDeduction3Per` | `4, false, 8, false` |
| 6 | - | DmTextBoxNumeric | `txtDeduction3Amt` | `2, false, 12, false` |
| 7 | - | DmTextBoxNumeric | `txtDeduction4Per` | `4, false, 8, false` |
| 8 | - | DmTextBoxNumeric | `txtDeduction4Amt` | `2, false, 12, false` |
| 9 | - | DmTextBoxNumeric | `txtDeduction5Per` | `4, false, 8, false` |
| 10 | - | DmTextBoxNumeric | `txtDeduction5Amt` | `2, false, 12, false` |
| 11 | Total Deduction | DmTextBoxNumeric | `txtTotalDeductionAmt` | `2, false, 12, false` |

Other widgets (not in tab list): `txtBaseAmt` (DmTextBoxNumeric)

## Files
| file | lines |
|---|---|
| client/AdLinePopup.java | 301 |
| client/AdLinePopup_Dsg.java | 358 |
| server/AdLinePopup_Server.java | 69 |
| shared/AdLinePopup_Mdl.java | 183 |

## UI labels / columns (client)
`Total Deduction`

## Models (shared DTOs)
### AdLinePopup_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | ded1Head |
| String | ded1Per |
| String | ded1Amt |
| String | ded2Head |
| String | ded2Per |
| String | ded2Amt |
| String | ded3Head |
| String | ded3Per |
| String | ded3Amt |
| String | ded4Head |
| String | ded4Per |
| String | ded4Amt |
| String | ded5Head |
| String | ded5Per |
| String | ded5Amt |
| String | totalDedAmt |

## Server `AdLinePopup_Server.java` (69 lines)
Helpers used: `DmServer.vNull`, `DmShared.iNull`, `DmShared.xNull`

### `getDefaultDeductionLine`
`public static AdLinePopup_Mdl getDefaultDeductionLine(String tNatureCode, String siteCode, Connection cnTemp) throws Exception`

- SQL 1: `select adlc.ded1_Head,adlc.ded1_Per,adlc.ded1_Amt, adlc.ded2_Head,adlc.ded2_Per,adlc.ded2_Amt, adlc.ded3_Head,adlc.ded3_Per,adlc.ded3_Amt, adlc.ded4_Head,adlc.ded4_Per,adlc.ded4_Amt, adlc.ded5_Head,adlc.ded5_Per,adlc.ded5_Amt FROM adLineConfig as adlc LEFT JOIN adLineConfigSiteList as adlcs on adlc.code = adlcs.code WHERE adlc.tNatureCode = :tNatureCode AND ISNULL(adlcs.site_Code, :siteCode) = :siteCode`
- Params: `:tNatureCode` ← `DmShared.iNull(tNatureCode).toString()`; `:siteCode` ← `DmShared.iNull(siteCode).toString()`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.client`, `dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmLabel.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmToolContainer.client`, `paperbiz.selfToolkit.adLinePopup.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
Additional deduction line popup (`deductionLineParameter` ki lines ke liye UI).
