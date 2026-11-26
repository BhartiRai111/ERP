package com.dataman.toolkit.auth;

import java.security.MessageDigest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * The legacy rule: {@code PassWd = MD5(lower(user_Name) + password)} as 32 hex digits, compared without regard to
 * case. The hash is computed by SQL Server itself ({@code HashBytes}) so passwords with non-ASCII characters give
 * the same bytes as before (the connection must keep {@code sendStringParametersAsUnicode=false}). KNOWN RISK:
 * MD5 with a guessable salt is weak; replace this bean with a strong-hash verifier when the migration is decided.
 */
@Component
public class LegacyMd5PasswordVerifier implements PasswordVerifier {

    private final NamedParameterJdbcTemplate jdbc;

    public LegacyMd5PasswordVerifier(JdbcTemplate jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    @Override
    public boolean matches(String userName, String storedHash, String rawPassword) {
        if (storedHash == null) {
            return false;
        }
        String mine = jdbc.queryForObject("select convert(varchar(32), HashBytes('MD5', lower(:u) + :p), 2)",
                new MapSqlParameterSource("u", userName).addValue("p", rawPassword == null ? "" : rawPassword), String.class);
        // constant-time compare of the two hex texts
        return mine != null && MessageDigest.isEqual(mine.toLowerCase().getBytes(), storedHash.trim().toLowerCase().getBytes());
    }
}
