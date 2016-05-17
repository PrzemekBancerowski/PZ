package com.project.pz.webserver.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class SensorMetadataResponse {

    @JsonProperty("SensorId")
    private String sensorId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Vendor")
    private String vendor;

    @JsonProperty("Version")
    private String version;

    @JsonProperty("Architecture")
    private String architecture;

    @JsonProperty("CpuModel")
    private String cpuModel;

    @JsonProperty("CpuVendor")
    private String cpuVendor;

    @JsonProperty("CpuCores")
    private String cpuCores;

    @JsonProperty("CpuCache")
    private String cpuCache;

    @JsonProperty("Hostname")
    private String hostname;

    @JsonProperty("Dns")
    private String dns;

    @JsonProperty("CreatedOn")
    private String createdOn;

    public SensorMetadataResponse() {
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public String getCpuVendor() {
        return cpuVendor;
    }

    public void setCpuVendor(String cpuVendor) {
        this.cpuVendor = cpuVendor;
    }

    public String getCpuCores() {
        return cpuCores;
    }

    public void setCpuCores(String cpuCores) {
        this.cpuCores = cpuCores;
    }

    public String getCpuCache() {
        return cpuCache;
    }

    public void setCpuCache(String cpuCache) {
        this.cpuCache = cpuCache;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getDns() {
        return dns;
    }

    public void setDns(String dns) {
        this.dns = dns;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
}
