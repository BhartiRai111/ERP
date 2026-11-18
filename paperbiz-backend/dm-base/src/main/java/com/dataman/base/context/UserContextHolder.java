package com.dataman.base.context;

import com.dataman.base.exception.DmException;

/** Request-scoped holder (ThreadLocal) for the current {@link UserContext}. */
public final class UserContextHolder {

    private static final ThreadLocal<UserContext> CURRENT = new ThreadLocal<>();

    private UserContextHolder() {
    }

    public static void set(UserContext ctx) {
        CURRENT.set(ctx);
    }

    public static UserContext get() {
        return CURRENT.get();
    }

    /** Current context; throws if the request has none. */
    public static UserContext require() {
        UserContext ctx = CURRENT.get();
        if (ctx == null) {
            throw new DmException("User context is not available.");
        }
        return ctx;
    }

    public static void clear() {
        CURRENT.remove();
    }
}
