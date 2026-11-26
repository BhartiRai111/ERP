package com.dataman.toolkit.auth;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Service;

/** Signs and checks the session tokens (HS256, minimal claims). */
@Service
public class JwtService {

    private static final String ISSUER = "paperbiz";

    private final byte[] secret;
    private final int minutes;

    public JwtService(AuthProperties props) {
        String s = props.getJwtSecret();
        boolean ok = s != null && s.getBytes(StandardCharsets.UTF_8).length >= 32;
        if (!ok && !props.isDevHeaders()) {
            throw new IllegalStateException("dataman.auth.jwt-secret must be set (at least 32 characters)");
        }
        this.secret = ok ? s.getBytes(StandardCharsets.UTF_8) : null;
        this.minutes = props.getTokenMinutes();
    }

    public String issue(SessionClaims c) {
        if (secret == null) {
            throw new IllegalStateException("Tokens are off (dev headers mode)");
        }
        Instant now = Instant.now();
        try {
            JWTClaimsSet set = new JWTClaimsSet.Builder()
                    .issuer(ISSUER)
                    .subject(c.userName())
                    .jwtID(c.sessionId())
                    .issueTime(Date.from(now))
                    .expirationTime(Date.from(now.plusSeconds(minutes * 60L)))
                    .claim("plt", c.pltCode())
                    .claim("cc", c.companyCode())
                    .claim("db", c.companyDb())
                    .claim("fdb", c.fileDb())
                    .claim("site", c.siteCode())
                    .build();
            SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), set);
            jwt.sign(new MACSigner(secret));
            return jwt.serialize();
        } catch (JOSEException e) {
            throw new IllegalStateException("Token signing failed", e);
        }
    }

    /** The claims of a valid, unexpired token; empty for anything else (bad signature, expired, malformed). */
    public Optional<SessionClaims> parse(String token) {
        if (secret == null) {
            return Optional.empty();
        }
        try {
            SignedJWT jwt = SignedJWT.parse(token);
            if (!JWSAlgorithm.HS256.equals(jwt.getHeader().getAlgorithm()) || !jwt.verify(new MACVerifier(secret))) {
                return Optional.empty();
            }
            JWTClaimsSet c = jwt.getJWTClaimsSet();
            if (!ISSUER.equals(c.getIssuer()) || c.getExpirationTime() == null
                    || c.getExpirationTime().before(new Date()) || c.getSubject() == null || c.getJWTID() == null) {
                return Optional.empty();
            }
            return Optional.of(new SessionClaims(c.getSubject(), c.getJWTID(), c.getStringClaim("plt"),
                    c.getStringClaim("cc"), c.getStringClaim("db"), c.getStringClaim("fdb"), c.getStringClaim("site")));
        } catch (ParseException | JOSEException | RuntimeException e) {
            return Optional.empty();
        }
    }
}
