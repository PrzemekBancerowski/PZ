package org.pz.listener.model;

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
public class SensorDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sensorid;
    private Integer cpu_value;
    private Integer network_value;
    private Integer memory_value;
    private Integer procrunning;
    private Integer procsleeping;
    private Integer procstopped;
    private Integer threads;
    private Integer zombie;
    // private String networkUnit; 
    // private String cpuUnit; 
    // private String memoryUnit; 
    
    @JsonProperty("data")
    public void setData(Map<String, Object> data) {
        Map proc = (Map) data.get("Proc");
        sensorid = (String) data.get("ID");
        cpu_value = (Integer) ((Map) data.get("CPU")).get("Usage");
        network_value = 100;
        memory_value = ((Double) ((Map) data.get("Memory")).get("usage")).intValue();

        procrunning = (Integer) proc.get("running");
        procsleeping = (Integer) proc.get("sleeping");
        procstopped = (Integer) proc.get("stopped");
        threads = (Integer) proc.get("threads");
        zombie = (Integer) proc.get("zombie");
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

    public Integer getCpu_value() {
        return cpu_value;
    }

    public void setCpu_value(Integer cpu_value) {
        this.cpu_value = cpu_value;
    }

    public Integer getNetwork_value() {
        return network_value;
    }

    public void setNetwork_value(Integer network_value) {
        this.network_value = network_value;
    }

    public Integer getMemory_value() {
        return memory_value;
    }

    public void setMemory_value(Integer memory_value) {
        this.memory_value = memory_value;
    }

    public Integer getProcsleeping() {
        return procsleeping;
    }

    public void setProcsleeping(Integer procsleeping) {
        this.procsleeping = procsleeping;
    }

    public Integer getProcrunning() {
        return procrunning;
    }

    public void setProcrunning(Integer procrunning) {
        this.procrunning = procrunning;
    }

    public Integer getProcstopped() {
        return procstopped;
    }

    public void setProcstopped(Integer procstopped) {
        this.procstopped = procstopped;
    }

    public Integer getThreads() {
        return threads;
    }

    public void setThreads(Integer threads) {
        this.threads = threads;
    }

    public Integer getZombie() {
        return zombie;
    }

    public void setZombie(Integer zombie) {
        this.zombie = zombie;
    }

    @Override
    public String toString() {
        return "SensorDetails{" + "id=" + id + ", sensorId=" + sensorid + ", cpu_value=" + cpu_value + ", network_value=" + network_value + ", memory_value=" + memory_value + ", procRunnig=" + procrunning + ", procSleeping=" + procsleeping + ", procStopped=" + procstopped + ", threads=" + threads + ", zombie=" + zombie + '}';
    }

}
