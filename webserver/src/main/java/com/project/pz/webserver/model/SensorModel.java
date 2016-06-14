package com.project.pz.webserver.model;

import java.math.BigInteger;

/**
 * Created by Piotr Sołtysiak on 2016-05-17.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class SensorModel {

    private String id;

    private String hostName;

    private String cpu;

    private String cpuVendor;

    private Integer cpuCoreCount;

    private BigInteger memorySize;

    private String systemName;

    private String systemArch;

    private String systemVendor;

    private String systemVersion;

    public SensorModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getCpuVendor() {
        return cpuVendor;
    }

    public void setCpuVendor(String cpuVendor) {
        this.cpuVendor = cpuVendor;
    }

    public Integer getCpuCoreCount() {
        return cpuCoreCount;
    }

    public void setCpuCoreCount(Integer cpuCoreCount) {
        this.cpuCoreCount = cpuCoreCount;
    }

    public BigInteger getMemorySize() {
        return memorySize;
    }

    public void setMemorySize(BigInteger memorySize) {
        this.memorySize = memorySize;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemArch() {
        return systemArch;
    }

    public void setSystemArch(String systemArch) {
        this.systemArch = systemArch;
    }

    public String getSystemVendor() {
        return systemVendor;
    }

    public void setSystemVendor(String systemVendor) {
        this.systemVendor = systemVendor;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    @Override
    public String toString() {
        return "SensorModel{" +
                "id='" + id + '\'' +
                ", hostName='" + hostName + '\'' +
                ", cpu='" + cpu + '\'' +
                ", cpuVendor='" + cpuVendor + '\'' +
                ", cpuCoreCount=" + cpuCoreCount +
                ", memorySize=" + memorySize +
                ", systemName='" + systemName + '\'' +
                ", systemArch='" + systemArch + '\'' +
                ", systemVendor='" + systemVendor + '\'' +
                ", systemVersion='" + systemVersion + '\'' +
                '}';
    }
}
