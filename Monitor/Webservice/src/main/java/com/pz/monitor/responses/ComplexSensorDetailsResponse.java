package com.pz.monitor.responses;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComplexSensorDetailsResponse {
    private Double Value;

    public ComplexSensorDetailsResponse(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            Value = resultSet.getDouble("val");
        } else {
            Value = null;
        }
    }
}
