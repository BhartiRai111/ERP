package com.dataman.toolkit.changelog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.dataman.base.context.CompanyInfo;
import com.dataman.base.context.SessionContextHolder;
import com.dataman.base.context.SessionInfo;
import com.dataman.base.context.SiteInfo;
import com.dataman.base.context.UserContext;
import com.dataman.base.context.UserContextHolder;
import com.dataman.base.context.UserInfo;
import com.dataman.base.enums.DmTransactionMode;
import com.dataman.base.exception.DmException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import java.util.Map;
import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

class ModifyReasonServiceTest {

    private MongoCollection<Document> collection;
    private NamedParameterJdbcTemplate jdbc;
    private ModifyReasonService service;

    @SuppressWarnings("unchecked")
    @BeforeEach
    void setUp() {
        MongoClient client = mock(MongoClient.class);
        MongoDatabase db = mock(MongoDatabase.class);
        collection = mock(MongoCollection.class);
        when(client.getDatabase("LeatherZFiles")).thenReturn(db);
        when(db.getCollection("datamodificationlog")).thenReturn(collection);
        ObjectProvider<MongoClient> provider = mock(ObjectProvider.class);
        when(provider.getIfAvailable()).thenReturn(client);
        jdbc = mock(NamedParameterJdbcTemplate.class);
        service = new ModifyReasonService(provider, jdbc, null);
        UserInfo u = new UserInfo("admin", "Admin User", "", "", "", "", 0, "2", "ABC Papers", List.of(), List.of());
        CompanyInfo c = new CompanyInfo("7", "ABC", "2026-27", "", "", "1", "", "", "", "", "", "", "", "");
        SiteInfo s = new SiteInfo("1", "Head Office", "HO", "HO", "B", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "");
        SessionContextHolder.set(new SessionContextHolder.Current("sid-1", new SessionInfo(u, c, s, "LeatherZ", "LeatherZFiles")));
        UserContextHolder.set(new UserContext("admin", "LeatherZ", "1", "10.0.0.5"));
    }

    @AfterEach
    void tearDown() {
        SessionContextHolder.clear();
        UserContextHolder.clear();
    }

    @Test
    void editLogCarriesUserCompanySiteReasonAndBothRecords() {
        ChangeLogEntry e = ChangeLogEntry.master("101", "Kg", "unitmast", "Master", "Unit Master", DmTransactionMode.EDIT,
                "UnitMasterDto", "{\"old\":1}", "{\"new\":2}");
        service.log(e, new ModifyReason("3", "Data correction", "typo"));
        ArgumentCaptor<Document> cap = ArgumentCaptor.forClass(Document.class);
        verify(collection).insertOne(cap.capture());
        Document d = cap.getValue();
        assertEquals("sid-1", d.getString("sessionid"));
        assertEquals(2, d.getInteger("pltcode"));
        assertEquals(7, d.getInteger("companycode"));
        assertEquals("HO", d.getString("sitemcode"));
        assertEquals("10.0.0.5", d.getString("ipaddresses"));
        assertEquals("Edit", d.getString("mode"));
        assertEquals(3, d.getInteger("modifyreasoncode"));
        assertEquals("typo", d.getString("remark"));
        assertEquals("{\"old\":1}", d.getString("olddata"));
        assertEquals("unitmast", d.getString("tablename"));
    }

    @Test
    void newRecordIsLoggedAsSystemActivity() {
        when(jdbc.queryForList(anyString(), any(org.springframework.jdbc.core.namedparam.SqlParameterSource.class)))
                .thenReturn(List.of(Map.of("code", 1, "name", "System Activity")));
        service.log(ChangeLogEntry.master("101", "Kg", "unitmast", "Master", "Unit Master", DmTransactionMode.NEW, "X", null, "{}"), null);
        ArgumentCaptor<Document> cap = ArgumentCaptor.forClass(Document.class);
        verify(collection).insertOne(cap.capture());
        assertEquals("System Activity", cap.getValue().getString("modifyreason"));
        assertEquals(1, cap.getValue().getInteger("modifyreasoncode"));
    }

    @Test
    void editAndDeleteNeedAReasonNewDoesNot() {
        assertThrows(DmException.class, () -> service.requireReason(DmTransactionMode.EDIT, null));
        assertThrows(DmException.class, () -> service.requireReason(DmTransactionMode.DELETE, new ModifyReason("0", "", "")));
        service.requireReason(DmTransactionMode.NEW, null);
        service.requireReason(DmTransactionMode.EDIT, new ModifyReason("3", "x", ""));
        assertThrows(DmException.class, () -> service.log(
                ChangeLogEntry.master("", "Kg", "t", "m", "n", DmTransactionMode.EDIT, "X", null, null), new ModifyReason("3", "x", "")));
    }
}
