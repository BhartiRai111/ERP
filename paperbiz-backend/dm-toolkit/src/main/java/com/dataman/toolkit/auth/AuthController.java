package com.dataman.toolkit.auth;

import com.dataman.base.context.SessionInfo;
import com.dataman.toolkit.session.SessionModels.Selection;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Login screen and session endpoints. Everything except login needs the token (see {@link JwtContextFilter}). */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public record LoginRequest(String userName, String password, String mode) {
    }

    public record SelectRequest(String pltCode, String companyCode, String siteCode) {
    }

    public record UnlockRequest(String password) {
    }

    private final AuthService auth;

    public AuthController(AuthService auth) {
        this.auth = auth;
    }

    @PostMapping("/login")
    public Selection login(@RequestBody LoginRequest body, HttpServletRequest req) {
        return auth.login(body.userName(), body.password(), body.mode(), JwtContextFilter.clientIp(req));
    }

    @PostMapping("/select")
    public Selection select(@RequestBody SelectRequest body, HttpServletRequest req) {
        return auth.select(session(req), body.pltCode(), body.companyCode(), body.siteCode());
    }

    @GetMapping("/me")
    public Selection me(HttpServletRequest req) {
        return auth.current(session(req));
    }

    @PostMapping("/lock")
    public void lock(HttpServletRequest req) {
        auth.lock(session(req));
    }

    @PostMapping("/unlock")
    public void unlock(@RequestBody UnlockRequest body, HttpServletRequest req) {
        auth.unlock(session(req), body.password(), JwtContextFilter.clientIp(req));
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest req) {
        auth.logout(session(req));
    }

    static ServerSession session(HttpServletRequest req) {
        return (ServerSession) req.getAttribute(JwtContextFilter.SESSION_ATTRIBUTE);
    }

    /** Session info of the request (company, site, user details) for other controllers. */
    public static SessionInfo info(HttpServletRequest req) {
        return (SessionInfo) session(req).info();
    }
}
