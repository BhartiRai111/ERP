package com.dataman.base.report.html;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.dataman.base.exception.DmException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

class ReportDataServiceTest {

    @Test
    void readsRowsWithCaseInsensitiveKeys() {
        JdbcTemplate jdbc = new JdbcTemplate(new DriverManagerDataSource("jdbc:h2:mem:rd1;DB_CLOSE_DELAY=-1"));
        jdbc.execute("create table t(id int, itemName varchar(20))");
        jdbc.execute("insert into t values (1,'Paper'),(2,'Board')");
        jdbc.execute("create alias rpt_items as $$ java.sql.ResultSet rptItems(java.sql.Connection c, int min) throws java.sql.SQLException "
                + "{ return c.createStatement().executeQuery(\"select * from t where id >= \" + min); } $$");
        ReportDataService s = new ReportDataService(jdbc);
        List<Map<String, Object>> rows = s.rows("rpt_items", 2);
        assertEquals(1, rows.size());
        assertEquals("Board", rows.get(0).get("itemname"));
        assertEquals("Board", rows.get(0).get("ITEMNAME"));
        assertEquals(1, s.resultSets("rpt_items", 1).size());
    }

    @Test
    void procedureNameIsChecked() {
        ReportDataService s = new ReportDataService(new JdbcTemplate(new DriverManagerDataSource("jdbc:h2:mem:rd2;DB_CLOSE_DELAY=-1")));
        assertThrows(DmException.class, () -> s.rows("x; drop table t"));
    }
}
