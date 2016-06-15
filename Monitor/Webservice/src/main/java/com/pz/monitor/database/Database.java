package com.pz.monitor.database;

import java.sql.*;

public class Database {
    private Connection connection;
    private QueryFactory queryFactory;

    public synchronized boolean connect() {
        try {
            if (connection != null && !connection.isClosed()) return true;
            Class.forName(Configuration.driver);
            connection = DriverManager.getConnection(Configuration.connectionUrl, Configuration.user, Configuration.password);
            queryFactory = new QueryFactory(connection);
        } catch (ClassNotFoundException ex) {
            System.err.println("Driver not found");
            return false;
        } catch (SQLException ex) {
            System.err.println("Could not connect to database: " + ex.getMessage());
            return false;
        }
        return true;
    }

    public synchronized void disconnect() {
        try {
            if (!connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.err.println("Connection error: " + e.getMessage());
        }
    }

    public ResultSet execute(Query query) throws SQLException {
        connect();
        return query.getStatement().executeQuery();
    }

    public int executeUpdate(Query query) throws SQLException {
        connect();
        return query.getStatement().executeUpdate();
    }

    public QueryFactory queryFactory() {
        return queryFactory;
    }

    private static class Configuration {
        private final static String driver = getPropertyOrDefault("db.driver", "org.postgresql.Driver");
        private final static String host = getPropertyOrDefault("db.host", "ec2-54-235-120-118.compute-1.amazonaws.com");
        private final static int port = getPropertyOrDefault("db.port", 5432);
        private final static String user = getPropertyOrDefault("db.user", "wpzvaplckvrdhi");
        private final static String password = getPropertyOrDefault("db.password", "NcZlco3bj1ilgSws42m_uwG1Nk");
        private final static String databaseName = getPropertyOrDefault("db.name", "dd5fmjf2ek3hib");
        private final static String sslConfig = getPropertyOrDefault("db.sslConfig", "ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory");
        private final static String connectionUrl = "jdbc:postgresql://" + host + ":" + port + "/" + databaseName + "?" + sslConfig;

        private static String getPropertyOrDefault(String propertyName, String defaultValue) {
            String systemPropertyValue = System.getProperty(propertyName);
            return systemPropertyValue == null ? defaultValue : systemPropertyValue;
        }

        private static int getPropertyOrDefault(String propertyName, int defaultValue) {
            try {
                String systemPropertyValue = System.getProperty(propertyName);
                return Integer.parseInt(systemPropertyValue);
            } catch (NumberFormatException ex) {
                return defaultValue;
            }
        }
    }
}