package com.dataman.base.context;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * TEMPORARY: builds the {@link UserContext} from request headers so the entries can be
 * developed before login exists. To be replaced by JWT/Spring Security (same
 * {@link UserContextHolder} contract, so no entry code changes).
 * Headers: X-Dm-User, X-Dm-Company-Db, X-Dm-Site.
 */
@Component
@org.springframework.boot.autoconfigure.condition.ConditionalOnProperty(prefix = "dataman.auth", name = "dev-headers", havingValue = "true")
public class UserContextFilter extends OncePerRequestFilter {

    public static final String H_USER = "X-Dm-User";
    public static final String H_DB = "X-Dm-Company-Db";
    public static final String H_SITE = "X-Dm-Site";

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        try {
            String db = req.getHeader(H_DB);
            if (db != null && !db.isBlank()) {
                UserContextHolder.set(new UserContext(
                        nz(req.getHeader(H_USER)), db.trim(), nz(req.getHeader(H_SITE)), clientIp(req)));
            }
            chain.doFilter(req, res);
        } finally {
            UserContextHolder.clear();
        }
    }

    private static String clientIp(HttpServletRequest req) {
        String xff = req.getHeader("X-Forwarded-For");
        if (xff != null && !xff.isBlank()) {
            return xff.split(",")[0].trim();
        }
        return nz(req.getRemoteAddr());
    }

    private static String nz(String s) {
        return s == null ? "" : s.trim();
    }
}
