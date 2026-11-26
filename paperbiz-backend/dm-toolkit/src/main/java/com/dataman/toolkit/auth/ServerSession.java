package com.dataman.toolkit.auth;

import java.time.Instant;

/** What the server keeps about one login (the token only carries the id). */
public final class ServerSession {

    public enum Status { LOGGED_IN, LOCKED }

    private final String sessionId;
    private final String userName;
    private volatile Status status = Status.LOGGED_IN;
    private volatile Instant lastAccess = Instant.now();
    private final int lockTimeOutMinutes;
    private volatile Object info;         // SessionInfo once company/site are chosen
    private volatile Object permissions;  // UserPermissions cache

    public ServerSession(String sessionId, String userName, int lockTimeOutMinutes) {
        this.sessionId = sessionId;
        this.userName = userName;
        this.lockTimeOutMinutes = lockTimeOutMinutes;
    }

    public String sessionId() { return sessionId; }
    public String userName() { return userName; }
    public Status status() { return status; }
    public Instant lastAccess() { return lastAccess; }
    public int lockTimeOutMinutes() { return lockTimeOutMinutes; }
    public Object info() { return info; }
    public Object permissions() { return permissions; }
    public void setInfo(Object info) { this.info = info; }
    public void setPermissions(Object permissions) { this.permissions = permissions; }
    public void lock() { this.status = Status.LOCKED; }
    public void unlock() { this.status = Status.LOGGED_IN; this.lastAccess = Instant.now(); }
    public void touch() { this.lastAccess = Instant.now(); }

    /** The user's own idle limit (UserMast.UserLockTimeOut, minutes, 0 = never) has passed. */
    public boolean idleTooLong(Instant now) {
        return lockTimeOutMinutes > 0 && lastAccess.plusSeconds(lockTimeOutMinutes * 60L).isBefore(now);
    }
}
