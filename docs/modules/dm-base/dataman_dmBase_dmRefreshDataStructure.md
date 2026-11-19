# dm-base / dataman_dmBase_dmRefreshDataStructure

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_dmRefreshDataStructure/`). Hand-written notes are in the "Notes" section.

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
| server/DmConfigDataStructure.java | 72 |
| server/DmRefreshDataStructure.java | 1175 |
| shared/DmRefreshDataStructure_Model.java | 202 |

## Server `DmConfigDataStructure.java` (72 lines)
### `setColumnValue`
`public void setColumnValue(String columnName, SQLDataType dataType, String precisionLength, SQLColumnType columnType, SQLIsNullable isNullable, String defaultValue) throws Exception`


### `setForeignKey`
`public void setForeignKey(String onColumn, String withColumn, String withTable) throws Exception`


### `setUnique`
`public void setUnique(String onColumn) throws Exception`


### `setIndex`
`public void setIndex(String onColumn) throws Exception`


### `chkValidation`
`private void chkValidation()`

- Errors: "Class path is mandatory" | "Table name is mandatory" | "Refresh Data Structure instacnce could not be nullable"
- Other calls: `DmBaseShared.xNull`

## Server `DmRefreshDataStructure.java` (1175 lines)
### `close`
`public void close()`


### `executeDBScript`
`public void executeDBScript() throws Exception`


### `dropAll_Keys`
`public void dropAll_Keys() throws Exception`

- SQL 1: `Select SO.Name As Constraint_Name,SO1.Name As Table_Name From SysObjects SO Left Join SysObjects SO1 On SO.Parent_Obj=SO1.ID Where SO.XType in ('F','D','UQ') and isNull(SO1.Name,'') <> '' Order By SO.XType`
- SQL 2: `dropAllKeysConstraint(F,D,UQ) Key {…}`
- SQL 3: `Select SO.Name As Constraint_Name,SO1.Name As Table_Name From SysObjects SO Left Join SysObjects SO1 On SO.Parent_Obj=SO1.ID Where SO.XType in ('PK') and isNull(SO1.Name,'') <> '' and so1.xtype <> 'TT' Order By SO.XType`
- SQL 4: `dropAllKeysPrimary Key {…}`
- SQL 5: `Select SI.Name As Index_Name, SO1.Name As Table_Name From Sys.Indexes SI Left Join SysObjects SO1 On SI.Object_ID = SO1.ID Where SI.Type Not In (0) And SO1.XType = 'U'`
- SQL 6: `dropAllKeysIndex Key {…}`
- Other calls: `DmBaseShared.xNull`

### `dropConstraintKey`
`private void dropConstraintKey(String onTableName, String constraintName) throws Exception`

- SQL 1: `alter table [ {…} ] drop constraint [ {…} ];`
- SQL 2: `dropConstraintKey {…}`

### `dropPrimaryKey`
`private void dropPrimaryKey(String onTableName, String constraintName) throws Exception`

- SQL 1: `alter table [ {…} ] drop constraint [ {…} ];`
- SQL 2: `dropPrimaryKey {…} {…}`

### `dropIndexKey`
`private void dropIndexKey(String onTableName, String indexName) throws Exception`

- SQL 1: `drop index [ {…} ] on [ {…} ];`
- SQL 2: `dropIndexKey {…} {…}`

### `addAll_PK_FK`
`public void addAll_PK_FK() throws Exception`

- SQL 1: `Select tableName From ##actualStructure Where columnType = 'PRIMARY' Group By tableName;`
- SQL 2: `Select tableName,onColumn,withColumn,withTable From ##actualStructure Where columnType = 'FOREIGN' Group By tableName,onColumn,withColumn,withTable;`
- Other calls: `DmBaseShared.xNull`

### `addPrimaryKey`
`private void addPrimaryKey(String onTableName) throws Exception`

- SQL 1: `Select columnName From ##actualStructure Where columnType = 'PRIMARY' And tableName = ' {…} ' Group By columnName Order By Min(rowId);`
- SQL 2: `alter table {…} add constraint [ {…} ] primary key clustered ( {…} );`
- Other calls: `DmBaseShared.xNull`

### `addForeignKey`
`private void addForeignKey(String onTableName, String onColumnName, String withColumnName, String withTableName) throws Exception`

- SQL 1: `Select SO.Name As Constraint_Name,SO1.Name As Table_Name From SysObjects SO Left Join SysObjects SO1 On SO.Parent_Obj=SO1.ID Where SO.XType in ('F') and SO.Name = ' {…} '`
- SQL 2: `alter table {…} add constraint [ {…} ] foreign key ([ {…} ,],[]) references [ {…} ] ([ {…} ,],[]);`

### `addAll_UNQ`
`public void addAll_UNQ() throws Exception`

- SQL 1: `Select tableName,onColumn From ##actualStructure Where columnType In ('UNIQUE') Group By tableName,onColumn;`
- Other calls: `DmBaseShared.xNull`

### `addUNQ`
`private void addUNQ(String onTableName, String onColumnName) throws Exception`

- SQL 1: `Select SO.Name As Constraint_Name From SysObjects SO Left Join SysObjects SO1 On SO.Parent_Obj=SO1.ID Where SO.XType in ('UQ') and SO1.Name = ' {…} ' and SO.Name = ' {…} '`
- SQL 2: `alter table {…} add constraint [ {…} ] unique ([ {…} ,],[])`

### `addAll_IDX`
`public void addAll_IDX() throws Exception`

- SQL 1: `Select tableName,onColumn From ##actualStructure Where columnType In ('INDEX') Group By tableName,onColumn;`
- Other calls: `DmBaseShared.xNull`

### `addIDX`
`private void addIDX(String onTableName, String onColumnName) throws Exception`

- SQL 1: `Select SI.Name As Index_Name From Sys.Indexes SI Left Join SysObjects SO1 On SI.Object_ID = SO1.ID Where SI.Name = ' {…} ' and SO1.Name = ' {…} '`
- SQL 2: `create nonClustered index [ {…} ] on {…} ([ {…} ,],[])`

### `synchronizeAllTables`
`public void synchronizeAllTables() throws Exception`

- SQL 1: `Select tableName From ##actualStructure Group By tableName;`
- Other calls: `DmBaseShared.xNull`

### `createTable`
`private void createTable(String tableName) throws Exception`

- SQL 1: `Select Count(*) as cnt from SysObjects Where Name = ' {…} ';`
- SQL 2: `create table {…} (__Temp__ varChar(1))`
- SQL 3: `createTable {…} {…}`

### `dropColumn`
`private void dropColumn(String tableName, String columnName) throws Exception`

- SQL 1: `select count(*) as cnt from sysColumns sc left join sysObjects so on sc.id = so.id where so.name = ' {…} ' and sc.name = ' {…} '`
- SQL 2: `alter table {…} drop column [ {…} ];`
- SQL 3: `dropColumn {…}`

### `createAllColumn`
`private void createAllColumn(String tableName) throws Exception`

- SQL 1: `select sc.Name as sysColumnName,tmp.* from ( Select a.* From ##actualStructure a where a.rowId In ( select max(aa.rowId) from ##actualStructure aa where aa.tableName = ' {…} ' and isNull(columnName,'') <> '' group by aa.columnName ) ) as tmp left join sysObjects so on so.name = tmp.tableName and so.xtype = 'U' left join sysColumns sc on sc.id = so.id and sc.name = tmp.columnName where so.name = ' {…} ' order by rowId;`
- SQL 2: `createAllColumn {…} {…}`
- Other calls: `DmBaseShared.xNull`

### `addColumn`
`private void addColumn(String tableName, ColumnProperty columnProperty) throws Exception`

- SQL 1: `alter column`
- SQL 2: `alter table {…} {…} [ {…} ] {…} {…} ;`
- SQL 3: `alter column {…} {…} DF_ {…} _ {…}`
- SQL 4: `Select SO.Name As Constraint_Name,SO1.Name As Table_Name From SysObjects SO Left Join SysObjects SO1 On SO.Parent_Obj=SO1.ID Where SO.XType in ('D') and SO.Name = ' {…} '`
- SQL 5: `alter table {…} add constraint {…} default ' {…} ' for {…}`
- Other calls: `DmBaseShared.xNull`, `NOT_NULL.toString`, `PRIMARY.toString`

### `getDataType`
`private String getDataType(String dataType, String length)`

- Other calls: `DmBaseShared.xNull`, `NVARCHAR.toString`, `NVARCHARMAX.toString`, `VARCHAR.toString`, `VARCHARMAX.toString`

### `addResultLog`
`public void addResultLog(String procedureName, String tableName, String otherInfo, String sqlStatement, String sqlResult) throws Exception`

- SQL 1: `insert into ##result(procedureName,tableName,otherInfo,sqlStatement,sqlResult) values(' {…} ',' {…} ',:otherInfo,:sqlStatement,:sqlResult);`
- Params: `:otherInfo` ← `DmBaseShared.xNull(otherInfo)`; `:sqlStatement` ← `DmBaseShared.xNull(sqlStatement)`; `:sqlResult` ← `DmBaseShared.xNull(sqlResult)`
- Other calls: `DmBaseShared.xNull`

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.server`, `dataman.dmBase.dmBaseStatic.shared`, `dataman.dmBase.dmPrepareStatement.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
