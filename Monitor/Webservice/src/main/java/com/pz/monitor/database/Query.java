package com.pz.monitor.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Query {
    protected PreparedStatement statement;

    public Query(PreparedStatement query) {
        statement= query;
    }

    public PreparedStatement getStatement() throws SQLException {
        if (statement!= null) {
            return statement;
        } else {
            throw new SQLException("Sql query has not been set.");
        }
    }
}
