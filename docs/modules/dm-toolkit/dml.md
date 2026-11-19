# dm-toolkit / dml

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dml/`). Hand-written notes are in the "Notes" section.

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
| server/DML_Server.java | 217 |
| shared/Row_Mdl.java | 67 |

## Models (shared DTOs)
### Row_Mdl
| type | field |
|---|---|
| long | serialCode |
| String | dTVersion |
| String | date |
| String | module |
| String | dbScript |

## Server `DML_Server.java` (217 lines)
Helpers used: `DmServer.convertDate`, `DmServer.getServerDateTime`, `DmShared.iNull`, `DmShared.xNull`

### `inilizationDML`
`private void inilizationDML(DmEnum.DML_Table enmTable)`

- Errors: "DML can not be initialized with null 'Table Name'!"

### `addDMLScript`
`private void addDMLScript()`


### `runDML`
`public String runDML(String userName, Connection cnTemp) throws Exception`

- SQL 1: `Declare @Temp Table (messageError Varchar(Max), serialCode BigInt, DMLScript Varchar(Max))`
- SQL 2: `IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ' {…} ') BEGIN CREATE TABLE {…} ( serialCode int NULL, DTVersion varchar(50) NULL, DMLDate datetime NULL, moduleName varchar(50) NULL, DMLScript varchar(500) NULL ); END;`
- SQL 3: `SELECT t.serialCode FROM {…} AS t`
- SQL 4: `Insert Into @Temp Values(' {…} '`',' {…} ',' {…} ')`
- SQL 5: `IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ' {…} ') BEGIN TRUNCATE TABLE {…} END;`
- SQL 6: `INSERT INTO {…} (serialCode, DTVersion, DMLDate, moduleName, DMLScript) VALUES(:serialCode, :DTVersion, :DMLDate, :moduleName, :DMLScript)`
- SQL 7: `Select * From @Temp`
- Params: `:serialCode` ← `DmShared.iNull(latestRow.getSerialCode()).toString()`; `:DTVersion` ← `DmShared.xNull(latestRow.getdTVersion())`; `:DMLDate` ← `DmServer.convertDate(latestRow.getDate())`; `:moduleName` ← `DmShared.xNull(latestRow.getModule())`; `:DMLScript` ← `DmShared.xNull(latestRow.getDbScript())`
- Errors: "This feature is only available for 'SA' or 'SUPER' user!" | "Latest DML script has already been executed!"
- Other calls: `Collectors.toList`

### `getForeignKeyStr`
`private String getForeignKeyStr(String onTableName, String onColumnName, String withColumnName, String withTableName) throws Exception`

- SQL 1: `alter table {…} add constraint [ {…} ] foreign key ([ {…} ,],[]) references [ {…} ] ([ {…} ,],[]);`

### `getIndexStr`
`private String getIndexStr(String onTableName, String onColumnName) throws Exception`

- SQL 1: `create nonClustered index [ {…} ] on {…} ([ {…} ,],[])`

## Depends on (imports from other packages)
`dataman.dmBase.dmPrepareStatement.server`, `dataman.dmBase.self.shared`, `dataman.dmToolkit.dmStatic.server`, `dataman.dmToolkit.dmStatic.shared`, `dataman.dmToolkit.dml.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**DB schema migration scripts (versioned):** `DML_Server` me ek list hai (`Row_Mdl`: serial, **version** jaise "15.03", **date**, module Master/Sales..., **SQL script**): `ALTER TABLE ... ADD ...`, `CREATE TABLE ...`, `INSERT ...` jo app chalate hue DB ko naye version par laate the. **Ye tables badalte hain, isliye naye project me port nahi** (rule: DB schema unchanged). Par ye list **schema ka itihaas/reference** hai (kaun si column kab judi), isliye zaroorat par padh sakte hain.
