package com.pz.monitor.responses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class HostsResponse {
    private Map<String, List<String>> hosts = new HashMap<>();

    public HostsResponse(ResultSet resultSet) throws SQLException {

        while (resultSet.next()) {
            String sensorId = resultSet.getString("id");
            String host = resultSet.getString("host");

            if (!hosts.containsKey(host)) {
                hosts.put(host, new LinkedList<>());
            }
            hosts.get(host).add(sensorId);
        }
    }
}
