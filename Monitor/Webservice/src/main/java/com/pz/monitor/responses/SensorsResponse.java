package com.pz.monitor.responses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SensorsResponse {
    List<SensorDetailsResponse> items = new ArrayList<>();

    public SensorsResponse(ResultSet resultSet) throws SQLException {
        while (!resultSet.isLast()) {
            items.add(new SensorDetailsResponse(resultSet));
        }
    }

    public List<SensorDetailsResponse> getResponse() {
        return items;
    }
}
