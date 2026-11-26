package com.dataman.toolkit.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.dataman.base.context.CompanyInfo;
import com.dataman.base.context.SessionInfo;
import com.dataman.base.context.SiteInfo;
import com.dataman.base.context.UserInfo;
import com.dataman.base.exception.DmException;
import com.dataman.toolkit.session.SessionModels.Selection;
import com.dataman.toolkit.session.SessionService;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AuthServiceTest {

    private SessionService sessions;
    private PasswordVerifier verifier;
    private SessionStore store;
    private JwtService jwt;
    private AuthService auth;

    @BeforeEach
    void setUp() {
        AuthProperties p = new AuthProperties();
        p.setJwtSecret("0123456789-0123456789-0123456789-xx");
        p.setMaxFailures(2);
        sessions = mock(SessionService.class);
        verifier = mock(PasswordVerifier.class);
        store = new SessionStore(p);
        jwt = new JwtService(p);
        auth = new AuthService(sessions, verifier, store, jwt, new LoginAttemptLimiter(p));
    }

    private static SessionInfo info(String plt, String company, String site) {
        UserInfo u = new UserInfo("admin", "Admin User", "", "", "", "", 0, plt, "PLT", List.of("1"), List.of());
        return new SessionInfo(u,
                company.isEmpty() ? null : new CompanyInfo(company, "Co", "2026-27", "2026-04-01", "2027-03-31", "1", "", "", "", "", "", "", "", ""),
                site.isEmpty() ? null : new SiteInfo(site, "HO", "HO", "HO", "B", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""),
                company.isEmpty() ? "" : "LeatherZ", company.isEmpty() ? "" : "LeatherZFiles");
    }

    private SessionService.Result result(SessionInfo i) {
        return new SessionService.Result(new Selection(null, "admin", "Admin User", "2026-07-09", List.of(), null, List.of(),
                null, List.of(), null, "", "1", ""), i);
    }

    private void user(String hash) {
        when(sessions.findLoginUser(any(), eq("admin"))).thenReturn(List.of(Map.of("user_Name", "admin", "description", "Admin User",
                "passWd", hash, "UserLockTimeOut", 5, "natureCode", "", "userName_Linked", "")));
    }

    @Test
    void goodLoginIssuesTokenForTheSelection() {
        user("HASH");
        when(verifier.matches("admin", "HASH", "secret")).thenReturn(true);
        when(sessions.select(any(), any(), any(), any(), anyInt(), any(), any(), any())).thenReturn(result(info("2", "7", "1")));
        Selection s = auth.login("admin", "secret", "USERNAME", "9.9.9.9");
        SessionClaims c = jwt.parse(s.token()).orElseThrow();
        assertEquals("admin", c.userName());
        assertEquals("LeatherZ", c.companyDb());
        assertEquals("1", c.siteCode());
        assertTrue(c.hasWorkContext());
        assertEquals(c.sessionId(), store.get(c.sessionId()).sessionId());
        assertEquals(5, store.get(c.sessionId()).lockTimeOutMinutes());
    }

    @Test
    void wrongPasswordUnknownUserAndBlankNameAreRefused() {
        user("HASH");
        when(verifier.matches(any(), any(), any())).thenReturn(false);
        assertEquals("Access denied", assertThrows(DmException.class, () -> auth.login("admin", "bad", null, "ip")).getMessage());
        assertEquals("Access denied", assertThrows(DmException.class, () -> auth.login("nobody", "x", null, "ip")).getMessage());
        assertEquals("User Name is mandatory.", assertThrows(DmException.class, () -> auth.login(" ", "x", null, "ip")).getMessage());
    }

    @Test
    void blockedAfterTooManyFailuresEvenWithTheRightPassword() {
        user("HASH");
        when(verifier.matches("admin", "HASH", "bad")).thenReturn(false);
        when(verifier.matches("admin", "HASH", "good")).thenReturn(true);
        assertThrows(DmException.class, () -> auth.login("admin", "bad", null, "ip"));
        assertThrows(DmException.class, () -> auth.login("admin", "bad", null, "ip"));
        assertTrue(assertThrows(DmException.class, () -> auth.login("admin", "good", null, "ip")).getMessage().startsWith("Too many"));
    }

    @Test
    void selectIssuesNewTokenAndClearsPermissionCache() {
        ServerSession s = store.create("admin", 0);
        s.setInfo(info("2", "", ""));
        s.setPermissions("cached");
        when(sessions.select(eq("admin"), any(), any(), any(), anyInt(), eq("2"), eq("7"), eq("1"))).thenReturn(result(info("2", "7", "1")));
        Selection sel = auth.select(s, "2", "7", "1");
        assertTrue(jwt.parse(sel.token()).orElseThrow().hasWorkContext());
        assertEquals(null, s.permissions());
    }

    @Test
    void unlockNeedsTheUsersOwnPassword() {
        ServerSession s = store.create("admin", 0);
        s.lock();
        user("HASH");
        when(verifier.matches("admin", "HASH", "good")).thenReturn(true);
        when(verifier.matches("admin", "HASH", "bad")).thenReturn(false);
        assertThrows(DmException.class, () -> auth.unlock(s, "bad", "ip"));
        assertEquals(ServerSession.Status.LOCKED, s.status());
        auth.unlock(s, "good", "ip");
        assertEquals(ServerSession.Status.LOGGED_IN, s.status());
        auth.logout(s);
        assertFalse(store.get(s.sessionId()) != null);
    }
}
