package com.dataman.toolkit.permission;

import com.dataman.base.exception.DmException;
import com.dataman.base.permission.MenuPermission;
import com.dataman.base.permission.UserPermissions;
import com.dataman.toolkit.auth.JwtContextFilter;
import com.dataman.toolkit.auth.ServerSession;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Menus and permissions for the screens: the whole set after login, or one module's menu. */
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    private final PermissionService permissions;

    public PermissionController(PermissionService permissions) {
        this.permissions = permissions;
    }

    @GetMapping
    public UserPermissions all(HttpServletRequest req) {
        return permissions.of(session(req));
    }

    @GetMapping("/menu/{module}")
    public List<MenuPermission> menu(@PathVariable String module, HttpServletRequest req) {
        return permissions.of(session(req)).menus().getOrDefault(module.toLowerCase(), List.of());
    }

    private static ServerSession session(HttpServletRequest req) {
        ServerSession s = (ServerSession) req.getAttribute(JwtContextFilter.SESSION_ATTRIBUTE);
        if (s == null) {
            throw new DmException(0, "Session expired. Please login again.");
        }
        return s;
    }
}
