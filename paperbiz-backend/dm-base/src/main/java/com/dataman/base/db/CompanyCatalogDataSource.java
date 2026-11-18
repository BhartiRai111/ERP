package com.dataman.base.db;

import com.dataman.base.context.UserContext;
import com.dataman.base.context.UserContextHolder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.regex.Pattern;
import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DelegatingDataSource;

/**
 * Multi-company support. Every connection taken from the pool is switched to the current
 * request's company database ({@code setCatalog}) and is switched back to the default
 * catalog when it is closed (returned to the pool), so a company's catalog can never leak
 * to another request. Replaces the legacy {@code getConnection(dbName)} + {@code setCatalog}.
 */
public class CompanyCatalogDataSource extends DelegatingDataSource implements AutoCloseable {

    private static final Pattern SAFE_NAME = Pattern.compile("[A-Za-z0-9_\\-]+");

    private final String defaultCatalog;

    public CompanyCatalogDataSource(DataSource target, String defaultCatalog) {
        super(target);
        this.defaultCatalog = defaultCatalog;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return wrap(super.getConnection());
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return wrap(super.getConnection(username, password));
    }

    private Connection wrap(Connection raw) throws SQLException {
        UserContext ctx = UserContextHolder.get();
        String catalog = (ctx == null) ? null : ctx.companyDb();
        if (catalog == null || catalog.isBlank()) {
            return raw; // default catalog; the pool restores it itself
        }
        if (!SAFE_NAME.matcher(catalog).matches()) {
            raw.close();
            throw new SQLException("Invalid company database name.");
        }
        raw.setCatalog(catalog);
        return (Connection) Proxy.newProxyInstance(Connection.class.getClassLoader(),
                new Class<?>[]{Connection.class}, (proxy, method, args) -> {
                    if ("close".equals(method.getName()) && method.getParameterCount() == 0) {
                        try {
                            if (defaultCatalog != null && !defaultCatalog.isBlank() && !raw.isClosed()) {
                                raw.setCatalog(defaultCatalog);
                            }
                        } finally {
                            raw.close();
                        }
                        return null;
                    }
                    try {
                        return method.invoke(raw, args);
                    } catch (InvocationTargetException e) {
                        throw e.getCause();
                    }
                });
    }

    @Override
    public void close() throws Exception {
        if (getTargetDataSource() instanceof AutoCloseable ac) {
            ac.close();
        }
    }
}
