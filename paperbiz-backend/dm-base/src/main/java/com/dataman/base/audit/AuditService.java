package com.dataman.base.audit;

import com.dataman.base.context.UserContext;
import com.dataman.base.context.UserContextHolder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.stereotype.Service;

/**
 * Audit values written on every master record (preparedBy/Dt/IP, u_name/u_EntDt) and the
 * display formats the legacy screens showed.
 */
@Service
public class AuditService {

    public Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public String userName() {
        return UserContextHolder.require().userName();
    }

    public String ip() {
        UserContext c = UserContextHolder.require();
        return c.ip() == null ? "" : c.ip();
    }

    /** "dd-MMM-yyyy HH.mm" (legacy formatTimestamp). */
    public static String formatTimestamp(Timestamp ts) {
        if (ts == null) {
            return null;
        }
        return new SimpleDateFormat("dd-MMM-yyyy HH.mm", Locale.ENGLISH).format(new Date(ts.getTime()));
    }

    /** "user - dd-MMM-yyyy - HH:mm", or "" when user or time is missing (legacy getUserWithDateTime). */
    public static String userWithDateTime(String userName, Timestamp ts) {
        if (userName == null || userName.isEmpty() || ts == null) {
            return "";
        }
        return userName + " - " + new SimpleDateFormat("dd-MMM-yyyy - HH:mm", Locale.ENGLISH).format(new Date(ts.getTime()));
    }
}
