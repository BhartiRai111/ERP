package com.dataman.base.filestore;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/** The SQL side of attachments: table {@code LINKFILECOLLECTION} (links a document row to a stored file). */
@Repository
public class FileLinkRepository {

    private final NamedParameterJdbcTemplate jdbc;

    public FileLinkRepository(NamedParameterJdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void insert(String fileLink, String docId, String sNo, String ruleCode, String fileName,
                       long fileSize, String userName) {
        jdbc.update("INSERT INTO LINKFILECOLLECTION (fileLink,docId,sNo,ruleCode,fileName,fileSize,u_Name,u_EntDt) "
                        + "VALUES (:fileLink,:docId,:sNo,:ruleCode,:fileName,:fileSize,:userName,:u_EntDt)",
                new MapSqlParameterSource("fileLink", fileLink).addValue("docId", docId).addValue("sNo", sNo)
                        .addValue("ruleCode", ruleCode).addValue("fileName", fileName)
                        .addValue("fileSize", String.valueOf(fileSize)).addValue("userName", userName)
                        .addValue("u_EntDt", new Timestamp(System.currentTimeMillis())));
    }

    public List<String> fileLinks(String docId, String sNo, String ruleCode) {
        return jdbc.queryForList("SELECT fileLink FROM LINKFILECOLLECTION "
                        + "WHERE (docId=:docId) and (sNo=:sNo) and (ruleCode=:ruleCode)",
                params(docId, sNo, ruleCode), String.class);
    }

    public int delete(String docId, String sNo, String ruleCode) {
        return jdbc.update("DELETE FROM LINKFILECOLLECTION WHERE (docId=:docId) and (sNo=:sNo) and (ruleCode=:ruleCode)",
                params(docId, sNo, ruleCode));
    }

    private static MapSqlParameterSource params(String docId, String sNo, String ruleCode) {
        return new MapSqlParameterSource("docId", docId).addValue("sNo", sNo).addValue("ruleCode", ruleCode);
    }
}
