package com.dataman.base.context;

import com.dataman.base.exception.DmException;

/**
 * Request-scoped holder of the logged-in session details (user, company, site names, file database, session id).
 * Services that need more than {@link UserContext} (change log, print headers) read it here instead of taking the
 * session as a parameter. Set and cleared by the request filter.
 */
public final class SessionContextHolder {

    public record Current(String sessionId, SessionInfo info) {
    }

    private static final ThreadLocal<Current> CURRENT = new ThreadLocal<>();

    private SessionContextHolder() {
    }

    public static void set(Current c) {
        CURRENT.set(c);
    }

    public static Current get() {
        return CURRENT.get();
    }

    public static Current require() {
        Current c = CURRENT.get();
        if (c == null || c.info() == null) {
            throw new DmException("Session details are not available.");
        }
        return c;
    }

    public static void clear() {
        CURRENT.remove();
    }
}
