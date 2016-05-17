package com.pz.monitor.responses;

import com.pz.monitor.database.DatabaseTables.SENSOR_METADATA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HostsResponse {
    private Map<String, List<String>> hosts = new HashMap<>();

    public HostsResponse(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            String sensorId = resultSet.getString(SENSOR_METADATA.sensorId.toString());
            String host = resultSet.getString(SENSOR_METADATA.hostname.toString());

            if (!hosts.containsKey(host)) {
                hosts.put(host, new LinkedList<>());
            }
            hosts.get(host).add(sensorId);
        }
    }
}
