package com.pz.monitor.responses;

import com.pz.monitor.database.DatabaseTables.SENSOR_METADATA;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SensorMetadataResponse {
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

    public SensorMetadataResponse(ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            this.SensorId = resultSet.getString(SENSOR_METADATA.sensorId.toString());
            this.Name = resultSet.getString(SENSOR_METADATA.name.toString());
            this.Vendor = resultSet.getString(SENSOR_METADATA.vendor.toString());
            this.Version = resultSet.getString(SENSOR_METADATA.version.toString());
            this.Architecture = resultSet.getString(SENSOR_METADATA.architecture.toString());
            this.CpuModel = resultSet.getString(SENSOR_METADATA.cpu_model.toString());
            this.CpuVendor = resultSet.getString(SENSOR_METADATA.cpu_vendor.toString());
            this.CpuCores = resultSet.getString(SENSOR_METADATA.cpu_cores.toString());
            this.CpuCache = resultSet.getString(SENSOR_METADATA.cpu_cache.toString());
            this.Hostname = resultSet.getString(SENSOR_METADATA.hostname.toString());
            this.Dns = resultSet.getString(SENSOR_METADATA.dns.toString());
            this.CreatedOn = resultSet.getString(SENSOR_METADATA.createdOn.toString());
        } else {
            throw new IllegalArgumentException("Invalid sensorId");
        }
    }
}
