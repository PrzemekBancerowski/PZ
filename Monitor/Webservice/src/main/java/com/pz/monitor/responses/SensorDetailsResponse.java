package com.pz.monitor.responses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class SensorDetailsResponse {
    private final String id;
    private final String systemName;
    private final String systemVendor;
    private final String systemVersion;
    private final String systemArch;
    private final String cpu;
    private final String cpuVendor;
    private final String cpuCoreCount;
    private final String hostName;
    private final String Dns;
    private final int memorySize;
    private final String CreatedOn;

    public SensorDetailsResponse(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            this.id= resultSet.getString("sensorId");
            this.systemName= resultSet.getString("name");
            this.systemVendor= resultSet.getString("vendor");
            this.systemVersion= resultSet.getString("version");
            this.systemArch= resultSet.getString("architecture");
            this.cpu= resultSet.getString("cpu_model");
            this.cpuVendor= resultSet.getString("cpu_vendor");
            this.cpuCoreCount= resultSet.getString("cpu_cores");
            this.hostName= resultSet.getString("hostname");
            this.Dns = resultSet.getString("dns");
            this.CreatedOn = resultSet.getString("createdOn");
            this.memorySize = new Random().nextInt();
        } else {
            throw new IllegalArgumentException("Invalid sensorId");
        }
    }
}
