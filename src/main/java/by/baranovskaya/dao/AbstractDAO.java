package by.baranovskaya.dao;

import by.baranovskaya.dao.connection.ProxyConnection;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.sql.Statement;

public interface AbstractDAO {
    Logger LOGGER = LogManager.getLogger(AbstractDAO.class);

    default void close(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Can not close statement: " + e);
            }
        }
    }

    default void close(ProxyConnection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, "Can not close connection: " + e);
            }
        }
    }

}
