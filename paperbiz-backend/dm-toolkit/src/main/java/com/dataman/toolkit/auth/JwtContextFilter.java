package com.dataman.toolkit.auth;

import com.dataman.base.context.SessionContextHolder;
import com.dataman.base.context.SessionInfo;
import com.dataman.base.context.UserContext;
import com.dataman.base.context.UserContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Every {@code /api/**} call except login needs a valid token of a live session. The user context (user, company
 * database, site, address) is set from the token for the whole request. A locked session may only call
 * {@code /api/auth/**} (unlock, logout).
 */
@Component
@ConditionalOnProperty(prefix = "dataman.auth", name = "dev-headers", havingValue = "false", matchIfMissing = true)
public class JwtContextFilter extends OncePerRequestFilter {

    public static final String SESSION_ATTRIBUTE = "dm.session";
    public static final String CLAIMS_ATTRIBUTE = "dm.claims";

    private final JwtService jwt;
    private final SessionStore sessions;

    public JwtContextFilter(JwtService jwt, SessionStore sessions) {
        this.jwt = jwt;
        this.sessions = sessions;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest req) {
        String p = req.getRequestURI();
        return !p.startsWith("/api/") || p.equals("/api/auth/login") || req.getMethod().equals("OPTIONS");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        String header = req.getHeader("Authorization");
        Optional<SessionClaims> claims = header != null && header.startsWith("Bearer ")
                ? jwt.parse(header.substring(7).trim()) : Optional.empty();
        ServerSession session = claims.map(c -> sessions.get(c.sessionId())).orElse(null);
        if (claims.isEmpty() || session == null || !session.userName().equals(claims.get().userName())) {
            reject(res, 401, "Session expired. Please login again.");
            return;
        }
        boolean authCall = req.getRequestURI().startsWith("/api/auth/");
        if (session.status() == ServerSession.Status.LOCKED && !authCall) {
            reject(res, 423, "Session is locked.");
            return;
        }
        if (session.status() == ServerSession.Status.LOGGED_IN) {
            session.touch();
        }
        req.setAttribute(SESSION_ATTRIBUTE, session);
        req.setAttribute(CLAIMS_ATTRIBUTE, claims.get());
        SessionClaims c = claims.get();
        try {
            UserContextHolder.set(new UserContext(c.userName(), c.companyDb(), c.siteCode(), clientIp(req)));
            if (session.info() instanceof SessionInfo info) {
                SessionContextHolder.set(new SessionContextHolder.Current(session.sessionId(), info));
            }
            chain.doFilter(req, res);
        } finally {
            UserContextHolder.clear();
            SessionContextHolder.clear();
        }
    }

    public static String clientIp(HttpServletRequest req) {
        String xff = req.getHeader("X-Forwarded-For");
        if (xff != null && !xff.isBlank()) {
            return xff.split(",")[0].trim();
        }
        return req.getRemoteAddr() == null ? "" : req.getRemoteAddr();
    }

    private static void reject(HttpServletResponse res, int status, String message) throws IOException {
        res.setStatus(status);
        res.setContentType("application/json");
        res.setCharacterEncoding(StandardCharsets.UTF_8.name());
        res.getWriter().write("{\"errorNumber\":0,\"message\":\"" + message + "\"}");
    }
}
