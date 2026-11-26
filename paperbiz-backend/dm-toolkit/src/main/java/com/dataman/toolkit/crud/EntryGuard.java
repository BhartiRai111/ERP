package com.dataman.toolkit.crud;

import com.dataman.base.context.SessionContextHolder;
import com.dataman.base.exception.DmException;
import com.dataman.base.permission.MenuPermission;
import com.dataman.toolkit.auth.ServerSession;
import com.dataman.toolkit.auth.SessionStore;
import com.dataman.toolkit.permission.PermissionService;
import org.springframework.stereotype.Component;

/**
 * Server-side check that the user may do something on an entry. Legacy only greyed out the buttons on the screen;
 * the server did not check, so a crafted request could bypass it. Now: opening needs the menu item, New needs
 * A, Edit E, Delete D, Print P. Message: "Permission denied.".
 */
@Component
public class EntryGuard {

    public enum Action { VIEW, ADD, EDIT, DELETE, PRINT }

    private final SessionStore sessions;
    private final PermissionService permissions;

    public EntryGuard(SessionStore sessions, PermissionService permissions) {
        this.sessions = sessions;
        this.permissions = permissions;
    }

    /** The menu item (so the caller can also read its dates/limits). */
    public MenuPermission require(String module, String menuName, Action action) {
        SessionContextHolder.Current cur = SessionContextHolder.require();
        ServerSession s = sessions.get(cur.sessionId());
        if (s == null) {
            throw new DmException(0, "Session expired. Please login again.");
        }
        MenuPermission m = PermissionService.find(permissions.of(s), module, menuName)
                .orElseThrow(() -> new DmException(0, "Permission denied."));
        if (!allowed(m, action)) {
            throw new DmException(0, "Permission denied.");
        }
        return m;
    }

    static boolean allowed(MenuPermission m, Action a) {
        return switch (a) {
            case VIEW -> true;
            case ADD -> m.canAdd();
            case EDIT -> m.canEdit();
            case DELETE -> m.canDelete();
            case PRINT -> m.canPrint();
        };
    }
}
