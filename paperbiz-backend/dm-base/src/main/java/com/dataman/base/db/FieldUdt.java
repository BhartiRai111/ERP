package com.dataman.base.db;

import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;

/**
 * Value of the SQL Server table type {@code dbo.fieldUDT} (legacy FieldUDT_Mdl + getFieldUDT): the
 * input some DB procedures take (e.g. getItemManualCode: itemName, itemGroup, itemCat).
 */
public record FieldUdt(String caption, String text, String tag) {

    /** Column size of fieldCode and fieldName in the DB type. */
    public static final int MAX_CODE_LENGTH = 30;

    /** Table-valued parameter: columns fieldCode, fieldName, fieldText, fieldTag, fieldTag2nd (last one stays empty). */
    public static SQLServerDataTable toTable(Map<String, FieldUdt> fields) throws SQLException {
        SQLServerDataTable table = new SQLServerDataTable();
        table.addColumnMetadata("fieldCode", Types.VARCHAR);
        table.addColumnMetadata("fieldName", Types.VARCHAR);
        table.addColumnMetadata("fieldText", Types.VARCHAR);
        table.addColumnMetadata("fieldTag", Types.VARCHAR);
        table.addColumnMetadata("fieldTag2nd", Types.VARCHAR);
        if (fields != null) {
            for (Map.Entry<String, FieldUdt> e : fields.entrySet()) {
                if (e.getKey() != null && !e.getKey().isBlank()) {
                    FieldUdt f = e.getValue();
                    if (e.getKey().length() > MAX_CODE_LENGTH) {
                        throw new IllegalArgumentException("fieldCode longer than " + MAX_CODE_LENGTH + ": " + e.getKey());
                    }
                    if (f.caption() != null && f.caption().length() > MAX_CODE_LENGTH) {
                        throw new IllegalArgumentException("fieldName longer than " + MAX_CODE_LENGTH + ": " + f.caption());
                    }
                    table.addRow(e.getKey(), f.caption(), f.text(), f.tag());
                }
            }
        }
        return table;
    }
}
