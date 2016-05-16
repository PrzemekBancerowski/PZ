package com.pz.monitor.responses;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorDetailsResponse {
    private final String SensorId;
    private final String Name;
    private final String Vendor;
    private final String Version;
    private final String Architecture;
    private final String CpuModel;
    private final String CpuVendor;
    private final String CpuCores;
    private final String CpuCache;
    private final String Hostname;
    private final String Dns;
    private final String CreatedOn;

    public SensorDetailsResponse(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            this.SensorId = resultSet.getString("sensorId");
            this.Name = resultSet.getString("name");
            this.Vendor = resultSet.getString("vendor");
            this.Version = resultSet.getString("version");
            this.Architecture = resultSet.getString("architecture");
            this.CpuModel = resultSet.getString("cpu_model");
            this.CpuVendor = resultSet.getString("cpu_vendor");
            this.CpuCores = resultSet.getString("cpu_cores");
            this.CpuCache = resultSet.getString("cpu_cache");
            this.Hostname = resultSet.getString("hostname");
            this.Dns = resultSet.getString("dns");
            this.CreatedOn = resultSet.getString("createdOn");
        } else {
            throw new IllegalArgumentException("Invalid sensorId");
        }
    }
}
