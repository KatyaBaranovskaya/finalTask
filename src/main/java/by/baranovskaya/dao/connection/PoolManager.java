package by.baranovskaya.dao.connection;

import by.baranovskaya.constant.DBProperty;
import by.baranovskaya.exception.DBException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PoolManager {
    private String url;
    private String user;
    private String password;
    private int poolSize;

    private final static Logger LOGGER = LogManager.getLogger(PoolManager.class);

    PoolManager() {
        ResourceBundle resource = ResourceBundle.getBundle(DBProperty.DB_PROPERTY_FILE);
        url = resource.getString(DBProperty.DB_URL);
        user = resource.getString(DBProperty.DB_USER);
        password = resource.getString(DBProperty.DB_PASSWORD);
        poolSize = Integer.parseInt(resource.getString(DBProperty.DB_POOL_SIZE));
    }

    ProxyConnection getConnection() {
        ProxyConnection proxyConnection = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            proxyConnection = new ProxyConnection(DriverManager.getConnection(url, user, password));
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, e + " Driver wasn't found.");
            throw new RuntimeException("Driver connection error", e);
        }
        return proxyConnection;
    }

    public int getPoolSize() {
        return poolSize;
    }
}
