# dm-base / dataman_dmBase_dmPrepareStatement

> Auto-extracted facts from legacy GWT source (`legacy-gwt/dataman_V2/dmBase/src/dataman_dmBase_dmPrepareStatement/`). Hand-written notes are in the "Notes" section.

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
| server/DmPreparedStatement.java | 520 |
| server/DmPreparedStatementDelegate.java | 473 |
| server/DmSavePoint.java | 60 |
| server/ParseResult.java | 140 |
| server/ParsedSqlParametersInfo.java | 37 |

## Server `ParsedSqlParametersInfo.java` (37 lines)
## Server `DmPreparedStatement.java` (520 lines)
### `prepareStatement`
`public static DmPreparedStatement prepareStatement(Connection conn, String sql) throws SQLException`

- Other calls: `ParseResult.parse`

### `prepareStatement`
`public static DmPreparedStatement prepareStatement(Connection conn, String sql, int resultSetType, int resultSetConcurrency) throws SQLException`

- Other calls: `ParseResult.parse`

### `prepareStatementWithGKey`
`public static DmPreparedStatement prepareStatementWithGKey(Connection conn, String sql) throws SQLException`

- Other calls: `ParseResult.parse`

### `getParameterIndexes`
`private Collection<Integer> getParameterIndexes(String parameter)`


### `setNull`
`public void setNull(String parameter, int sqlType) throws SQLException`


### `setBoolean`
`public void setBoolean(String parameter, Boolean x) throws SQLException`


### `setByte`
`public void setByte(String parameter, Byte x) throws SQLException`


### `setShort`
`public void setShort(String parameter, Short x) throws SQLException`


### `setInt`
`public void setInt(String parameter, Integer x) throws SQLException`


### `setIntegerList`
`public void setIntegerList(String parameter, String x) throws SQLException`

- Other calls: `DmBaseShared.iNull`, `DmBaseShared.xNull`

### `setIntegerList`
`public void setIntegerList(String parameter, String x, boolean ignoreIfBlank) throws SQLException`

- Other calls: `DmBaseShared.xNull`

### `setLong`
`public void setLong(String parameter, Long x) throws SQLException`


### `setLongList`
`public void setLongList(String parameter, String x, boolean ignoreIfBlank) throws SQLException`

- Other calls: `DmBaseShared.xNull`

### `setLongList`
`public void setLongList(String parameter, String x) throws SQLException`

- Other calls: `DmBaseShared.iNull`, `DmBaseShared.xNull`

### `setFloat`
`public void setFloat(String parameter, Float x) throws SQLException`


### `setDouble`
`public void setDouble(String parameter, Double x) throws SQLException`


### `setBigDecimal`
`public void setBigDecimal(String parameter, BigDecimal x) throws SQLException`


### `setString`
`public void setString(String parameter, String x) throws SQLException`


### `setString`
`public void setString(String parameter, String x, boolean ignoreIfBlank) throws SQLException`

- Other calls: `DmBaseShared.xNull`

### `setNString`
`public void setNString(String parameter, String x) throws SQLException`


### `setNString`
`public void setNString(String parameter, String x, boolean ignoreIfBlank) throws SQLException`

- Other calls: `DmBaseShared.xNull`

### `setStringList`
`public void setStringList(String parameter, String x) throws SQLException`

- Other calls: `DmBaseShared.xNull`

### `setStringList`
`public void setStringList(String parameter, String x, boolean ignoreIfBlank) throws SQLException`

- Other calls: `DmBaseShared.xNull`

### `setBytes`
`public void setBytes(String parameter, byte[] x) throws SQLException`


### `setDate`
`public void setDate(String parameter, Date x) throws SQLException`


### `setDate`
`public void setDate(String parameter, Date x, boolean ignoreIfBlank) throws SQLException`


### `setTime`
`public void setTime(String parameter, Time x) throws SQLException`


### `setTimestamp`
`public void setTimestamp(String parameter, Timestamp x) throws SQLException`


### `setAsciiStream`
`public void setAsciiStream(String parameter, InputStream x) throws SQLException`


### `setUnicodeStream`
`public void setUnicodeStream(String parameter, InputStream x, int length) throws SQLException`


### `setBinaryStream`
`public void setBinaryStream(String parameter, InputStream x, int length) throws SQLException`


### `setObject`
`public void setObject(String parameter, Object x, int targetSqlType, int scale) throws SQLException`


### `setObject`
`public void setObject(String parameter, Object x, int targetSqlType) throws SQLException`


### `setObject`
`public void setObject(String parameter, Object x) throws SQLException`


### `format`
`private String format(Object o, FormatType type)`


## Server `DmPreparedStatementDelegate.java` (473 lines)
## Server `DmSavePoint.java` (60 lines)
### `commit`
`public void commit() throws Exception`


### `rollBack`
`public void rollBack() throws Exception`


## Server `ParseResult.java` (140 lines)
### `getExecutableSql`
`public String getExecutableSql()`


### `parse`
`public static ParseResult parse(String query)`


## Depends on (imports from other packages)
`dataman.dmBase.dmBaseStatic.shared`

## Business rules (derived from code conditions)
No explicit rule/exception found beyond validator.

## Notes (hand-written)
_No hand-written notes yet._
