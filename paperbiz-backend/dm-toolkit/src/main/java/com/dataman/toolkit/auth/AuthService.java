package com.dataman.toolkit.auth;

import com.dataman.base.context.SessionInfo;
import com.dataman.base.exception.DmException;
import com.dataman.base.util.Strings;
import com.dataman.toolkit.session.SessionModels.Selection;
import com.dataman.toolkit.session.SessionService;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 * Login, company/site selection, lock/unlock and logout (legacy Login_SrvImpl + DmServer.validateUser). Wrong
 * user or password both answer "Access denied". New: wrong passwords are counted per user and address, and a
 * locked screen is opened with the user's own password (legacy compared it with the database password).
 */
@Service
public class AuthService {

    private final SessionService sessionService;
    private final PasswordVerifier verifier;
    private final SessionStore store;
    private final JwtService jwt;
    private final LoginAttemptLimiter limiter;

    public AuthService(SessionService sessionService, PasswordVerifier verifier, SessionStore store, JwtService jwt,
            LoginAttemptLimiter limiter) {
        this.sessionService = sessionService;
        this.verifier = verifier;
        this.store = store;
        this.jwt = jwt;
        this.limiter = limiter;
    }

    public Selection login(String loginName, String password, String mode, String ip) {
        if (Strings.xNull(loginName).isBlank()) {
            throw new DmException(0, "User Name is mandatory.");
        }
        if (limiter.isBlocked(loginName, ip)) {
            throw new DmException(0, "Too many wrong attempts. Try again after some time.");
        }
        List<Map<String, Object>> rows = sessionService.findLoginUser(mode, loginName.trim());
        if (rows.size() != 1) {
            limiter.failed(loginName, ip);
            throw new DmException(0, "Access denied");
        }
        Map<String, Object> u = rows.get(0);
        String userName = text(u, "user_Name");
        if (!verifier.matches(userName, text(u, "passWd"), password)) {
            limiter.failed(loginName, ip);
            throw new DmException(0, "Access denied");
        }
        limiter.succeeded(loginName, ip);
        int lockTimeOut = Strings.iNull(text(u, "UserLockTimeOut")).intValue();
        ServerSession session = store.create(userName, lockTimeOut);
        SessionService.Result r = sessionService.select(userName, text(u, "description"), text(u, "natureCode"),
                text(u, "userName_Linked"), lockTimeOut, null, null, null);
        return finish(session, r);
    }

    /** Choose / change PLT, company or site; the answer carries a new token. */
    public Selection select(ServerSession session, String pltCode, String companyCode, String siteCode) {
        SessionInfo cur = (SessionInfo) session.info();
        SessionService.Result r = sessionService.select(session.userName(), cur.user().description(),
                cur.user().natureCode(), cur.user().linkedUser(), cur.user().lockTimeOutMinutes(), pltCode,
                companyCode, siteCode);
        return finish(session, r);
    }

    /** The current state again (page reload) with a fresh token. */
    public Selection current(ServerSession session) {
        SessionInfo cur = (SessionInfo) session.info();
        return select(session, cur.user().pltCode(), cur.company() == null ? null : cur.company().code(),
                cur.site() == null ? null : cur.site().code());
    }

    public void lock(ServerSession session) {
        session.lock();
    }

    public void unlock(ServerSession session, String password, String ip) {
        if (limiter.isBlocked(session.userName(), ip)) {
            throw new DmException(0, "Too many wrong attempts. Try again after some time.");
        }
        List<Map<String, Object>> rows = sessionService.findLoginUser("USERNAME", session.userName());
        if (rows.size() != 1 || !verifier.matches(session.userName(), text(rows.get(0), "passWd"), password)) {
            limiter.failed(session.userName(), ip);
            throw new DmException(0, "Access denied");
        }
        limiter.succeeded(session.userName(), ip);
        session.unlock();
    }

    public void logout(ServerSession session) {
        store.remove(session.sessionId());
    }

    private Selection finish(ServerSession session, SessionService.Result r) {
        session.setInfo(r.info());
        session.setPermissions(null);   // menu and permissions depend on the company
        SessionInfo i = r.info();
        SessionClaims claims = new SessionClaims(session.userName(), session.sessionId(), i.user().pltCode(),
                i.company() == null ? "" : i.company().code(), i.companyDb(), i.fileDb(),
                i.site() == null ? "" : i.site().code());
        Selection s = r.selection();
        return new Selection(jwt.issue(claims), s.userName(), s.userDescription(), s.loginDate(), s.plts(), s.pltSelected(),
                s.companies(), s.companySelected(), s.sites(), s.siteSelected(), s.regionalLanguage(),
                s.localeCompanyCode(), s.message());
    }

    private static String text(Map<String, Object> row, String column) {
        for (Map.Entry<String, Object> e : row.entrySet()) {
            if (e.getKey().equalsIgnoreCase(column)) {
                return e.getValue() == null ? "" : e.getValue().toString().trim();
            }
        }
        return "";
    }
}
