package com.pz.monitor.responses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SimpleSensorDetailsResponse {
    List<Integer> values = new ArrayList<>();

    public SimpleSensorDetailsResponse(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int value = resultSet.getInt("val");
            values.add(value);
        }
    }
}
