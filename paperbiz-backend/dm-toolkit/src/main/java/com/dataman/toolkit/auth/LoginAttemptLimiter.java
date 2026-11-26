package com.dataman.toolkit.auth;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

/**
 * Blocks password guessing: after {@code maxFailures} wrong passwords in a row for the same user and address the
 * login is refused for {@code lockMinutes}. A good login clears the count. (New; the old system had no limit.)
 */
@Component
public class LoginAttemptLimiter {

    private record State(int failures, Instant blockedUntil) {
    }

    private final Map<String, State> states = new ConcurrentHashMap<>();
    private final int maxFailures;
    private final Duration lock;

    public LoginAttemptLimiter(AuthProperties props) {
        this.maxFailures = props.getMaxFailures();
        this.lock = Duration.ofMinutes(props.getLockMinutes());
    }

    private static String key(String user, String ip) {
        return (user == null ? "" : user.trim().toLowerCase()) + "|" + (ip == null ? "" : ip);
    }

    public boolean isBlocked(String user, String ip) {
        State s = states.get(key(user, ip));
        return s != null && s.blockedUntil() != null && Instant.now().isBefore(s.blockedUntil());
    }

    public void failed(String user, String ip) {
        states.compute(key(user, ip), (k, old) -> {
            boolean fresh = old == null || (old.blockedUntil() != null && !Instant.now().isBefore(old.blockedUntil()));
            int n = fresh ? 1 : old.failures() + 1;
            return new State(n, n >= maxFailures ? Instant.now().plus(lock) : null);
        });
    }

    public void succeeded(String user, String ip) {
        states.remove(key(user, ip));
    }
}
