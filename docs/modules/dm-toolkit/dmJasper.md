# dm-toolkit / dmJasper

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmToolkit/src/dataman/dmToolkit/dmJasper/`). Hand-written notes are in the "Notes" section.

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
| server/DmJasper.java | 8 |
| server/DmPLSqlAbstractQueryExecutor.java | 232 |
| server/DmPLSqlExecutorFactory.java | 58 |
| server/DmPLSqlMicrosoftQueryExecutor.java | 46 |

## Server `DmJasper.java` (8 lines)
## Server `DmPLSqlAbstractQueryExecutor.java` (232 lines)
### `createDatasource`
`public JRDataSource createDatasource() throws JRException`

- Errors: "Error executing SQL statement for : "

### `close`
`public synchronized void close()`


### `isStoredProcedure`
`private boolean isStoredProcedure(String queryString) throws SQLException`


### `createStatement`
`private void createStatement() throws JRException`

- Errors: "OUT paramater used in non-stored procedure call : " | "A stored procedure can have at most one cursor parameter : " | "Error preparing statement for executing the report query : \n\n"
- Other calls: `JRProperties.getIntegerProperty`

## Server `DmPLSqlMicrosoftQueryExecutor.java` (46 lines)
### `doCreateStatementMicrosoft`
`protected void doCreateStatementMicrosoft(SQLServerConnection connection, PreparedStatement statement, int paramIndex, Object paramValue) throws JRException`


### `doCreateDataSourceMicrosoft`
`protected ResultSet doCreateDataSourceMicrosoft(CallableStatement callableStatement) throws JRException`


## Server `DmPLSqlExecutorFactory.java` (58 lines)
### `createQueryExecuter`
`public JRQueryExecuter createQueryExecuter(JRDataset dataset, Map parameters) throws JRException`

- Errors: "Report Connection not found..."

### `getDbVendor`
`private DBType getDbVendor(Connection connection) throws JRException`

- Errors: "Connection not found..."

## Depends on (imports from other packages)
`dataman.dmBase.dmBaseJasper.server`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
**Jasper ka custom query executer** (SQL Server): `createDatasource`, `createStatement`, stored procedure support (`isStoredProcedure`; legacy `DmPLSqlExecutorFactory`). Jasper 7 upgrade ke saath dekhenge.
