package com.example.admin_app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

public class ConnectionPool {
    private String jdbcURL;
    private String username;
    private String password;
    private int preconnectCount;
    private int maxIdleConnections;
    private int maxConnections;
    private int connectCount;
    private Vector<Connection> usedConnections;
    private Vector<Connection> freeConnections;

    private static ConnectionPool connectionPool;

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }


    static {
        String jdbcURL = "jdbc:mysql://localhost:3306/web_shop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "radenko123";
        String driver = "com.mysql.cj.jdbc.Driver";
        int preconnectCount = 2;
        int maxIdleConnections = 25;
        int maxConnections = 25;

        try {
            Class.forName(driver);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            connectionPool = new ConnectionPool(jdbcURL, username, password, preconnectCount, maxIdleConnections, maxConnections);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    protected ConnectionPool(String aJdbcURL, String aUsername,
                             String aPassword, int aPreconnectCount,
                             int aMaxIdleConnections,
                             int aMaxConnections)
            throws SQLException {

        freeConnections = new Vector<>();
        usedConnections = new Vector<>();
        jdbcURL = aJdbcURL;
        username = aUsername;
        password = aPassword;
        preconnectCount = aPreconnectCount;
        maxIdleConnections = aMaxIdleConnections;
        maxConnections = aMaxConnections;

        for (int i = 0; i < preconnectCount; i++) {
            Connection conn = DriverManager.getConnection(
                    jdbcURL, username, password);
            conn.setAutoCommit(true);
            freeConnections.addElement(conn);
        }
        connectCount = preconnectCount;
    }


    public synchronized Connection checkOut() throws SQLException {
        Connection conn = null;
        if (freeConnections.size() > 0) {
            conn = freeConnections.remove(0);
            usedConnections.add(conn);
        } else {
            if (connectCount < maxConnections) {
                conn = DriverManager.getConnection(jdbcURL, username, password);
                usedConnections.add(conn);
                connectCount++;
            } else {
                try {
                    wait();
                    conn = freeConnections.remove(0);
                    usedConnections.add(conn);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return conn;
    }

    public synchronized void checkIn(Connection conn) {
        if (conn == null)
            return;
        if (usedConnections.remove(conn)) {
            freeConnections.add(conn);
            while (freeConnections.size() > maxIdleConnections) {
                int lastOne = freeConnections.size() - 1;
                Connection c = freeConnections.remove(lastOne);
                try {
                    c.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            notify();
        }
    }
}
