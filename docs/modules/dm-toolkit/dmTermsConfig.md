# dm-toolkit / dmTermsConfig

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmTermsConfig/`). Hand-written notes are in the "Notes" section.

## Summary (auto-derived from SQL + validator)
- Insert tables: termsTrans
- Update tables: ttd
- Delete cascade (in order): -
- Duplicate checks on save: none found
- Delete: **no in-use check in code** (relies on DB foreign keys; DB error message is returned)
- Validations: -
- Lookups/suggestions: -
- CSV export: no

## Screen spec (auto-extracted from client)
### Screen `DmTermsConfig`

**Fields in screen order** (tab order `vl.add`; label = nearest `DmLabel` before it):

| # | Label | Widget | Variable | Constructor args |
|---|---|---|---|---|

Other widgets (not in tab list): `textBox` (DmTextBox), `intBox` (DmTextBoxNumeric), `longBox` (DmTextBoxNumeric), `doubleBox` (DmTextBoxNumeric), `dateBox` (DmTextBox)

**Event handlers (condensed code, CRUD flow and field callbacks):**

`textBox.addBlurHandler` → `onBlur()`
```java
detail_Mdl.setValue(DmShared.xNull(textBox.getText()));
```

`intBox.addBlurHandler` → `onBlur()`
```java
detail_Mdl.setValue(String.valueOf(DmShared.iNull(intBox.getText()).intValue()));
```

`longBox.addBlurHandler` → `onBlur()`
```java
detail_Mdl.setValue(String.valueOf(DmShared.iNull(longBox.getText()).longValue()));
```

`doubleBox.addBlurHandler` → `onBlur()`
```java
detail_Mdl.setValue(DmShared.vNull(doubleBox.getText()).toString());
```

`dateBox.addBlurHandler` → `onBlur()`
```java
dateBox.setText(DmClient.returnDate(DmShared.xNull(dateBox.getText())));
detail_Mdl.setValue(DmShared.dNull(dateBox.getText()));
if (rowIndex == grid.getRowCount() - 1) {
setFocus();
```

## Files
| file | lines |
|---|---|
| client/DmTermsConfig.java | 354 |
| client/DmTermsConfig_Dsg.java | 69 |
| server/DmTermsConfig_Server.java | 553 |
| server/DmTermsConfig_SrvImpl.java | 41 |
| shared/DmTermsConfigDetail_Mdl.java | 140 |
| shared/DmTermsConfig_Srv.java | 12 |
| shared/DmTermsConfig_SrvAsync.java | 12 |
| shared/DmTermsConfig_Vld.java | 52 |

## Models (shared DTOs)
### DmTermsConfigDetail_Mdl (extends `KeyField implements Serializable, IsSerializable`)
| type | field |
|---|---|
| String | docId |
| String | termsConfigV_SNo |
| String | tableName |
| String | termsTypeCode |
| String | termsTypeName |
| String | termsTypeManualCode |
| String | dataTypeCode |
| String | saveInField |
| boolean | mandatory |
| String | value |

## RPC interface
| method | params | returns |
|---|---|---|
| getTermsConfig | String docId, String termsConfigCode, String voucherType | DmResult<List<DmTermsConfigDetail_Mdl>> |

## Validator `DmTermsConfig_Vld`
```java
public class DmTermsConfig_Vld {
    public static void chkSave(List<DmTermsConfigDetail_Mdl> mrTemp) {
        DmEnum.DmDataType dataType = null;
        boolean flag = false;
        if (mrTemp != null && mrTemp.size() > 0) {
            for (DmTermsConfigDetail_Mdl detail_Mdl : mrTemp) {
                if (!DmShared.xNull(detail_Mdl.getSharedKeyFieldCode()).trim().equals("") && detail_Mdl.isMandatory()) {
                    dataType = null;
                    flag = false;
                    try {
                        dataType = DmEnum.DmDataType.valueOf_(DmShared.iNull(detail_Mdl.getDataTypeCode()).intValue());
                    } catch (NullPointerException npEx) {
                    }
                    if (dataType != null) {
                        switch (dataType) {
                            case STRING:
                                flag = DmShared.xNull(detail_Mdl.getValue()).trim().equals("");
                                break;
                            case INTEGER:
                            case LONG:
                            case DOUBLE:
                                flag = DmShared.vNull(detail_Mdl.getValue()).doubleValue() == 0;
                                break;
                            case DATE:
                                flag = DmShared.dNull(detail_Mdl.getValue()).trim().equals("");
                                break;
                            default:
                                ;
                        }
                    }
                    if (flag) {
                        throw new DmException(DmShared.iNull(detail_Mdl.getTermsTypeCode()).intValue(), DmShared.xNull(detail_Mdl.getTermsTypeName()) + " is mandatory!");
                    }
                }
            }
        }
    }
}
```

## Server `DmTermsConfig_SrvImpl.java` (41 lines)
Helpers used: `DmServer.getConnection`, `DmServer.getSsnDBName`, `DmShared.xNull`

### `getTermsConfig`
`public DmResult<List<DmTermsConfigDetail_Mdl>> getTermsConfig(String docId, String termsConfigCode, String voucherType)`

- Other calls: `DmTermsConfig_Server.getTermsConfig`

## Server `DmTermsConfig_Server.java` (553 lines)
Helpers used: `DmShared.foriegnKey`, `DmShared.iNull`, `DmShared.xNull`

### `getTermsConfigCode`
`private static String getTermsConfigCode(String voucherGroupCode, String voucherCategory, String voucherType, Connection cnTemp)`

- SQL 1: `SELECT tc.code FROM voucher_Type vt LEFT JOIN termsConfig tc ON tc.voucherGroupCode = vt.voucherGroupCode WHERE 1 = 1 {…}`
- Params: `:code` ← `code`

### `getValueBySaveInField`
`private static String getValueBySaveInField(List<DmTermsConfigDetail_Mdl> list, String saveInFieldName)`


### `save`
`public static void save(String docId, String v_Type, List<DmTermsConfigDetail_Mdl> detail_MdlList, Connection cnTemp)`

- SQL 1: `INSERT INTO termsTrans (docId, termsConfigCode, termsConfigV_SNo, termsTypeCode, v_Type, valueCode, value, dataTypeCode, saveInField, mandatory) VALUES (:docId, :termsConfigCode, :termsConfigV_SNo, :termsTypeCode, :v_Type, :valueCode, :value, :dataTypeCode, :saveInField, :mandatory)`
- SQL 2: `UPDATE ttd SET ttd.mandatory = tcd.mandatory FROM termsTrans ttd JOIN termsConfigDetail tcd ON tcd.code = ttd.termsConfigCode AND tcd.v_SNo = ttd.termsConfigV_SNo AND tcd.mandatory IS NOT NULL WHERE ttd.docId = :docId AND ttd.termsConfigCode = :termsConfigCode AND ttd.termsConfigV_SNo = :termsConfigV_SNo`
- Params: `:docId` ← `DmShared.foriegnKey(docId)`; `:termsConfigCode` ← `DmShared.xNull(detail_Mdl.getSharedKeyFieldCode())`; `:termsConfigV_SNo` ← `DmShared.iNull(detail_Mdl.getTermsConfigV_SNo()).intValue()`; `:termsTypeCode` ← `DmShared.xNull(detail_Mdl.getTermsTypeCode())`; `:v_Type` ← `DmShared.xNull(v_Type)`; `:valueCode` ← `null`; `:value` ← `DmShared.xNull(detail_Mdl.getValue())`; `:dataTypeCode` ← `DmShared.iNull(detail_Mdl.getDataTypeCode()).toString()`; `:saveInField` ← `DmShared.xNull(detail_Mdl.getSaveInField())`; `:mandatory` ← `detail_Mdl.isMandatory() ? "1" : "0"`; `:docId` ← `DmShared.foriegnKey(docId)`; `:termsConfigCode` ← `DmShared.xNull(detail_Mdl.getSharedKeyFieldCode())`; `:termsConfigV_SNo` ← `DmShared.iNull(detail_Mdl.getTermsConfigV_SNo()).intValue()`; `:docId` ← `DmShared.xNull(docId)`
- Other calls: `Collectors.joining`

### `delete`
`private static void delete(String docId, String termsConfigV_SNo, String termsConfigCode, Connection cnTemp)`

- SQL 1: `DELETE FROM termsTrans WHERE 1= 1 {…}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:termsConfigCode` ← `DmShared.xNull(termsConfigCode)`; `:termsConfigV_SNo` ← `DmShared.iNull(termsConfigV_SNo).intValue()`
- Errors: "docId or termsConfigCode is mandatory!"

### `getTermsConfig`
`public static List<DmTermsConfigDetail_Mdl> getTermsConfig(final String docId, final String termsConfigCode, final String voucherType, Connection cnTemp)`

- SQL 1: `DECLARE @configCode AS INT DECLARE @docId AS BIGINT SET @configCode = :configCode SET @docId = :docId SELECT ttd.docId , tc.code , ttd.termsConfigV_SNo AS v_SNo , tc.name , tc.tableName , ttm.code AS termsTypeCode , ttm.name AS termsTypeName , ttm.manualCode AS termsTypeManualCode , ttd.dataTypeCode , ttd.termsTypeCode , ttd.saveInField , ttd.value , ttd.mandatory FROM termsConfig tc JOIN termsTrans ttd ON ttd.termsConfigCode = tc.code LEFT JOIN termsTypeMast ttm ON ttm.code = ttd.termsTypeCode WHERE tc.code = @configCode AND ttd.docId = @docId UNION ALL SELECT NULL AS docId , tc.code , tcd.v_SNo , tc.name , tc.tableName , ttm.code AS termsTypeCode , ttm.name AS termsTypeName , ttm.manualCode AS termsTypeManualCode , tcd.dataTypeCode , tcd.termsTypeCode , tcd.saveInField , '' AS value , 0 AS mandatory FROM termsConfig tc JOIN termsConfigDetail tcd ON tcd.code = tc.code LEFT JOIN termsTypeMast ttm ON ttm.code = tcd.termsTypeCode WHERE tcd.v_SNo IS NOT NULL AND tc.code = @configCode AND NOT EXISTS ( SELECT 1 FROM termsTrans ttd WHERE ttd.termsConfigCode = tc.code AND ttd.termsConfigV_SNo = tcd.v_SNo AND ttd.docId = @docId ) {…}`
- SQL 2: `SELECT ttd.docId , tc.code , ttd.termsConfigV_SNo AS v_SNo , tc.name , tc.tableName , ttm.code AS termsTypeCode , ttm.name AS termsTypeName , ttm.manualCode AS termsTypeManualCode , ttd.dataTypeCode , ttd.termsTypeCode , ttd.dataTypeCode , ttd.saveInField , ttd.value , ttd.mandatory FROM termsConfig tc LEFT JOIN termsTrans ttd ON ttd.termsConfigCode = tc.code LEFT JOIN termsTypeMast ttm ON ttm.code = ttd.termsTypeCode WHERE 1 = 1 AND ttd.docId = :docId {…}`
- SQL 3: `SELECT NULL AS docId , tc.code , tcd.v_SNo , tc.name , tc.tableName , ttm.code AS termsTypeCode , ttm.name AS termsTypeName , ttm.manualCode AS termsTypeManualCode , tcd.dataTypeCode , tcd.termsTypeCode , tcd.dataTypeCode , tcd.saveInField , tcd.defaultValue AS value , tcd.mandatory FROM termsConfig tc LEFT JOIN termsConfigDetail tcd ON tcd.code = tc.code LEFT JOIN termsTypeMast ttm ON ttm.code = tcd.termsTypeCode WHERE tcd.v_SNo IS NOT NULL AND tc.code = :configCode {…}`
- Params: `:docId` ← `DmShared.xNull(docId)`; `:configCode` ← `DmShared.xNull(termsConfigCodeTemp)`

### `getTermsConfig_Old`
`private static List<DmTermsConfigDetail_Mdl> getTermsConfig_Old(String termsConfigCode, String docId, Connection cnTemp)`

- Params: `:code` ← `termsConfigCode`; `:docId` ← `docId`

### `getDataType`
`public static DmDataType getDataType(int dataType)`

- Other calls: `DATE.toInt`, `DATETIME.toInt`, `DOUBLE.toInt`, `INTEGER.toInt`, `STRING.toInt`, `STRING_MULTILINE.toInt`

### `getPrintSQLQuery`
`public static String getPrintSQLQuery(String docId)`

- SQL 1: `SELECT docId , termsConfigCode , termsConfigV_SNo , termsTypeCode , ttm.name AS termsTypeName , v_Type , valueCode , value , saveInField , tableName FROM termsTrans tt LEFT JOIN termsTypeMast ttm ON ttm.code = tt.termsTypeCode WHERE docId = $P{docId} ORDER BY termsConfigV_SNo`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.dmSession.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmMessageBox.client`, `dataman.dmToolkit.dmStatic.client`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dmTermsConfig.shared`, `dataman.dmToolkit.dmTextBox.client`, `dataman.dmToolkit.dmTextBoxNumeric.client`, `dataman.dmToolkit.dmTopControl.client`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
