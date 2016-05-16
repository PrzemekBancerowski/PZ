package com.pz.monitor.database;

import java.sql.SQLException;

public class Query {
    protected String sqlQuery;

    public Query(String query) {
        sqlQuery = query;
    }

    public String getSqlQuery() throws SQLException {
        if (sqlQuery != null) {
            return sqlQuery;
        } else {
            throw new SQLException("Sql query has not been set.");
        }
    }
}
