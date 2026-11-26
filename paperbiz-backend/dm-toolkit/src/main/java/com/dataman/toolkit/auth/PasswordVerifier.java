package com.dataman.toolkit.auth;

/**
 * Checks a password against what {@code UserMast.PassWd} holds. Kept behind an interface so the weak legacy
 * hash can be replaced (strong hash with opt-in migration) without touching the login code.
 */
public interface PasswordVerifier {

    /** @param userName the stored user name; @param storedHash PassWd as stored */
    boolean matches(String userName, String storedHash, String rawPassword);
}
