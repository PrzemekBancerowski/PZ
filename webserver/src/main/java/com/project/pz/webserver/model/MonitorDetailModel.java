package com.project.pz.webserver.model;

import java.util.List;
import java.util.Map;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class MonitorDetailModel {

    private Integer id;

    private String name;

    private String address;

    private Map<String, List<String>> hosts;

    public MonitorDetailModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, List<String>> getHosts() {
        return hosts;
    }

    public void setHosts(Map<String, List<String>> hosts) {
        this.hosts = hosts;
    }
}
