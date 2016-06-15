package com.pz.monitor.responses;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorDetailsResponse {
    private final String id;
    private final String sysName;
    private final String sysVendor;
    private final String sysVersion;
    private final String systemArch;
    private final String cpu;
    private final String cpuVendor;
    private final String cpuCoreCount;
    private final String hostName;
    private final String Dns;
    private final String CreatedOn;

    public SensorDetailsResponse(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            this.id= resultSet.getString("sensorId");
            this.sysName= resultSet.getString("name");
            this.sysVendor= resultSet.getString("vendor");
            this.sysVersion= resultSet.getString("version");
            this.systemArch= resultSet.getString("architecture");
            this.cpu= resultSet.getString("cpu_model");
            this.cpuVendor= resultSet.getString("cpu_vendor");
            this.cpuCoreCount= resultSet.getString("cpu_cores");
            this.hostName= resultSet.getString("hostname");
            this.Dns = resultSet.getString("dns");
            this.CreatedOn = resultSet.getString("createdOn");
        } else {
            throw new IllegalArgumentException("Invalid sensorId");
        }
    }
}
