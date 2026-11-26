package com.dataman.toolkit.auth;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Server side sessions by id (replaces the static session table). A session that stays idle longer than the
 * user's lock time-out becomes LOCKED (must be unlocked with the password); one idle for a whole token life is
 * dropped. Single instance only; with several instances this needs a shared store.
 */
@Component
public class SessionStore {

    private final Map<String, ServerSession> sessions = new ConcurrentHashMap<>();
    private final Duration maxIdle;

    public SessionStore(AuthProperties props) {
        this.maxIdle = Duration.ofMinutes(props.getTokenMinutes());
    }

    public ServerSession create(String userName, int lockTimeOutMinutes) {
        ServerSession s = new ServerSession(UUID.randomUUID().toString(), userName, lockTimeOutMinutes);
        sessions.put(s.sessionId(), s);
        return s;
    }

    /** The live session, with the idle rule applied; null when unknown or expired. */
    public ServerSession get(String sessionId) {
        ServerSession s = sessions.get(sessionId);
        if (s == null) {
            return null;
        }
        Instant now = Instant.now();
        if (s.lastAccess().plus(maxIdle).isBefore(now)) {
            sessions.remove(sessionId);
            return null;
        }
        if (s.status() == ServerSession.Status.LOGGED_IN && s.idleTooLong(now)) {
            s.lock();
        }
        return s;
    }

    public void remove(String sessionId) {
        sessions.remove(sessionId);
    }

    @Scheduled(fixedDelay = 600_000)
    void sweep() {
        Instant now = Instant.now();
        sessions.values().removeIf(s -> s.lastAccess().plus(maxIdle).isBefore(now));
    }
}
