package org.pz.listener.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Katarzyna Piotrowicz
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class SensorMetadata implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sensorid;
    private String name;
    private String vendor;
    private String version;
    private String architecture;
    private String cpu_model;
    private String cpu_vendor;
    private Integer cpu_cores;
    private String cpu_cache;
    private String hostname;
    private String dns;

    @JsonProperty("metadata")
    public void setMetadata(Map<String, Object> metadata) {
        Map system = (Map) metadata.get("System");
        Map cpu = (Map) metadata.get("CPU");
        Map netIf = (Map) metadata.get("NetIF");

        name = (String) system.get("name");
        architecture = (String) system.get("arch");
        vendor = (String) system.get("vendor");
        version = (String) system.get("version");

        cpu_model = (String) cpu.get("model");
        cpu_vendor = (String) cpu.get("vendor");
        cpu_cores = (Integer) cpu.get("total_cores");
        cpu_cache = Integer.toString((Integer) cpu.get("cache_size"));

        hostname = (String) netIf.get("host_name");
        dns = (String) netIf.get("primary_dns");
    }

    @JsonProperty("data")
    public void setData(Map<String, Object> data) {
        sensorid = (String) data.get("ID");
    }

    @JsonIgnore
    public Integer getId() {
        return id;
    }

    @JsonIgnore
    public void setId(Integer id) {
        this.id = id;
    }

    public String getSensorid() {
        return sensorid;
    }

    public void setSensorid(String sensorid) {
        this.sensorid = sensorid;
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

    public String getCpu_model() {
        return cpu_model;
    }

    public void setCpu_model(String cpu_model) {
        this.cpu_model = cpu_model;
    }

    public String getCpu_vendor() {
        return cpu_vendor;
    }

    public void setCpu_vendor(String cpu_vendor) {
        this.cpu_vendor = cpu_vendor;
    }

    public Integer getCpu_cores() {
        return cpu_cores;
    }

    public void setCpu_cores(Integer cpu_cores) {
        this.cpu_cores = cpu_cores;
    }

    public String getCpu_cache() {
        return cpu_cache;
    }

    public void setCpu_cache(String cpu_cache) {
        this.cpu_cache = cpu_cache;
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

    @Override
    public String toString() {
        return "SensorMetadata{" + "id=" + id + ", sensorId=" + sensorid + ", name=" + name + ", vendor=" + vendor + ", version=" + version + ", architecture=" + architecture + ", cpu_model=" + cpu_model + ", cpu_vendor=" + cpu_vendor + ", cpu_cores=" + cpu_cores + ", cpu_cache=" + cpu_cache + ", hostname=" + hostname + ", dns=" + dns + '}';
    }
}
