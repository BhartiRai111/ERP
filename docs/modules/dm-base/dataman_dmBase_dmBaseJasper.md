# dm-base / dataman_dmBase_dmBaseJasper

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_dmBaseJasper/`). Hand-written notes are in the "Notes" section.

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
| server/DmAmtInWord.java | 71 |
| server/DmAmtInWordLakh.java | 151 |
| server/DmAmtInWordMillion.java | 161 |
| server/DmBaseJasper.java | 58 |
| server/DmNumberFormat.java | 60 |
| server/DmWaterMark.java | 64 |

## Server `DmAmtInWord.java` (71 lines)
### `getAmountToWords`
`public static String getAmountToWords(BigDecimal bigDecimal, String localeDmCode)`

- Other calls: `DmBaseShared.xNull`, `DmLocaleCountry.getLocaleViaDmCode`

### `getAmountToWords`
`public static String getAmountToWords(BigDecimal bigDecimal, DmEnum.DmLocaleCountry localeCountry)`

- Other calls: `DmAmtInWordLakh.getAmountToWords`, `DmAmtInWordMillion.getAmountToWords`

## Server `DmBaseJasper.java` (58 lines)
### `formatAmount`
`public static String formatAmount(BigDecimal decimal, int allowedDecimalPlaces, boolean showDecimalUptoAllowedDecimalPlaces)`


### `formatAmount`
`public static String formatAmount(BigDecimal decimal, int allowedDecimalPlaces, boolean showDecimalUptoAllowedDecimalPlaces, boolean showCurrencySymbol)`


### `formatAmount`
`public static String formatAmount(BigDecimal decimal, int allowedDecimalPlaces, boolean showDecimalUptoAllowedDecimalPlaces, boolean showCurrencySymbol, String localeDmCode)`

- Other calls: `DmBaseShared.xNull`, `DmLocaleCountry.getLocaleViaDmCode`

### `formatQuantity`
`public static String formatQuantity(BigDecimal decimal, int allowedDecimalPlaces, boolean showCommaSeparator)`


### `getWatermark`
`public static DmWaterMark getWatermark(String watermarkText, double rotate, int leftMargin, int waterMarkWidth, int waterMarkHeight, int fontSize)`

- Other calls: `DmBaseShared.xNull`

## Server `DmAmtInWordMillion.java` (161 lines)
### `getAmountToWords`
`public static String getAmountToWords(BigDecimal bigDecimal, String majorCurrency, String minorCurrency)`


## Server `DmAmtInWordLakh.java` (151 lines)
### `getAmountToWords`
`public static String getAmountToWords(BigDecimal bigDecimal, String majorCurrency, String minorCurrency)`

- Other calls: `DmBaseJasper.getAmountToWords`

### `numberToWords`
`private static String numberToWords(int number)`


### `wordInTenMultiples`
`private static String wordInTenMultiples(int number)`


### `wordInTens`
`private static String wordInTens(int number)`


### `numberInWords`
`private static String numberInWords(int number)`


## Server `DmNumberFormat.java` (60 lines)
### `formatNumber`
`public String formatNumber(BigDecimal bigDecimal, int allowedDecimalPlaces, boolean showDecimalUptoAllowedDecimalPlaces, boolean showCurrenySymbol, ULocale locale)`


### `formatNumber`
`public String formatNumber(BigDecimal bigDecimal, int allowedDecimalPlaces, boolean showDecimalUptoAllowedDecimalPlaces, boolean showCurrenySymbol, boolean showCommaSeparator, ULocale locale)`

- Other calls: `DmBaseShared.xNull`, `NumberFormat.getCurrencyInstance`

## Server `DmWaterMark.java` (64 lines)
### `render`
`public void render(Graphics2D g2, Rectangle2D rectangle) throws JRException`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.self.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
