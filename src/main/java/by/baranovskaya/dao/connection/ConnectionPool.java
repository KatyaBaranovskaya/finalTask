package by.baranovskaya.dao.connection;

import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import by.baranovskaya.exception.DBException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static AtomicBoolean instanceCreated = new AtomicBoolean();
    private static Lock lock = new ReentrantLock();
    private BlockingQueue<ProxyConnection> connections;
    private PoolManager manager;

    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

    public ConnectionPool()  {
        init();
    }

    private void init() {
        manager = new PoolManager();
        connections = new ArrayBlockingQueue<>(manager.getPoolSize());

        for (int i = 0; i < manager.getPoolSize(); i++) {
            try {
                connections.put(manager.getConnection());
            } catch (InterruptedException e) {
                LOGGER.log(Level.ERROR, "Can not init connection: " + e); //????
            }
        }
    }

    public static ConnectionPool getInstance() {
        if (!instanceCreated.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    instanceCreated.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            if (!connections.isEmpty()){
                connection = connections.take();
            }
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, "Can not take connection from connection: " + e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) throws DBException {
        try {
            if(connections.size() < manager.getPoolSize()) {
                connections.put(connection);
            } else {
                throw new DBException("Can not release connection connection, the connection is full.");
            }
        } catch (InterruptedException e) {
            throw new DBException("Can not release connection connection: " + e);
        }
    }

    public void closePool() throws DBException {
        for (ProxyConnection connection : connections) {
            try {
                connection.closeConnection();
            } catch (SQLException e) {
                throw new DBException("Can not close connection connection: " + e);
            }
        }
    }
}
