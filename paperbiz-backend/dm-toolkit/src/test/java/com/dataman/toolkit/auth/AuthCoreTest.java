package com.dataman.toolkit.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.dataman.base.context.UserContextHolder;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

class AuthCoreTest {

    private AuthProperties props() {
        AuthProperties p = new AuthProperties();
        p.setJwtSecret("0123456789-0123456789-0123456789-xx");
        p.setMaxFailures(3);
        return p;
    }

    @Test
    void tokenRoundTripAndTamperingIsRejected() {
        JwtService jwt = new JwtService(props());
        SessionClaims c = new SessionClaims("admin", "sid-1", "2", "7", "LeatherZ", "LeatherZFiles", "1");
        String token = jwt.issue(c);
        assertEquals(c, jwt.parse(token).orElseThrow());
        assertTrue(jwt.parse(token.substring(0, token.length() - 3) + "abc").isEmpty());
        assertTrue(jwt.parse("not a token").isEmpty());
        AuthProperties other = props();
        other.setJwtSecret("another-secret-another-secret-another-secret");
        assertTrue(new JwtService(other).parse(token).isEmpty(), "token of another key");
    }

    @Test
    void expiredTokenIsRejected() {
        AuthProperties p = props();
        p.setTokenMinutes(-1);
        JwtService jwt = new JwtService(p);
        assertTrue(jwt.parse(jwt.issue(new SessionClaims("u", "s", "", "", "", "", ""))).isEmpty());
    }

    @Test
    void secretIsRequiredUnlessDevHeaders() {
        AuthProperties p = new AuthProperties();
        assertThrows(IllegalStateException.class, () -> new JwtService(p));
        p.setDevHeaders(true);
        assertTrue(new JwtService(p).parse("x").isEmpty());
    }

    @Test
    void limiterBlocksAfterMaxFailuresAndClearsOnSuccess() {
        LoginAttemptLimiter l = new LoginAttemptLimiter(props());
        l.failed("Admin", "1.1.1.1");
        l.failed("admin", "1.1.1.1");
        assertFalse(l.isBlocked("admin", "1.1.1.1"));
        l.failed("admin", "1.1.1.1");
        assertTrue(l.isBlocked("ADMIN", "1.1.1.1"));
        assertFalse(l.isBlocked("admin", "2.2.2.2"), "other address is not blocked");
        l.succeeded("admin", "1.1.1.1");
        assertFalse(l.isBlocked("admin", "1.1.1.1"));
    }

    @Test
    void sessionLocksWhenIdleAndUnlocks() {
        SessionStore store = new SessionStore(props());
        ServerSession s = store.create("admin", 1);
        assertEquals(ServerSession.Status.LOGGED_IN, store.get(s.sessionId()).status());
        ServerSession never = store.create("x", 0);
        assertFalse(never.idleTooLong(java.time.Instant.now().plusSeconds(100000)));
        assertTrue(s.idleTooLong(java.time.Instant.now().plusSeconds(120)));
        s.lock();
        assertEquals(ServerSession.Status.LOCKED, store.get(s.sessionId()).status());
        s.unlock();
        assertEquals(ServerSession.Status.LOGGED_IN, s.status());
        store.remove(s.sessionId());
        assertNull(store.get(s.sessionId()));
    }

    @Test
    void filterRequiresTokenAndSetsContext() throws Exception {
        JwtService jwt = new JwtService(props());
        SessionStore store = new SessionStore(props());
        JwtContextFilter filter = new JwtContextFilter(jwt, store);
        ServerSession s = store.create("admin", 0);
        String token = jwt.issue(new SessionClaims("admin", s.sessionId(), "2", "7", "LeatherZ", "F", "3"));

        // no token
        MockHttpServletResponse r1 = run(filter, "/api/master/unit", null, null);
        assertEquals(401, r1.getStatus());
        // login is open
        assertEquals(200, run(filter, "/api/auth/login", null, null).getStatus());
        // good token: context is available inside the chain
        String[] seen = new String[1];
        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/api/master/unit");
        req.addHeader("Authorization", "Bearer " + token);
        MockHttpServletResponse r2 = new MockHttpServletResponse();
        filter.doFilter(req, r2, (rq, rs) -> seen[0] = UserContextHolder.require().companyDb() + "/" + UserContextHolder.require().siteCode());
        assertEquals("LeatherZ/3", seen[0]);
        assertNull(UserContextHolder.get(), "context is cleared after the request");
        // locked session: only /api/auth/**
        s.lock();
        assertEquals(423, run(filter, "/api/master/unit", token, null).getStatus());
        assertEquals(200, run(filter, "/api/auth/unlock", token, null).getStatus());
        // dropped session
        store.remove(s.sessionId());
        assertEquals(401, run(filter, "/api/auth/me", token, null).getStatus());
        assertNotNull(token);
    }

    private MockHttpServletResponse run(JwtContextFilter f, String path, String token, String unused) throws IOException, jakarta.servlet.ServletException {
        MockHttpServletRequest req = new MockHttpServletRequest("GET", path);
        if (token != null) {
            req.addHeader("Authorization", "Bearer " + token);
        }
        MockHttpServletResponse res = new MockHttpServletResponse();
        f.doFilter(req, res, new MockFilterChain());
        return res;
    }
}
