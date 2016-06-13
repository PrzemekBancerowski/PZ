package com.project.pz.webserver.model;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
public class MonitorSimpleModel {

    private String id;

    private String name;

    private String address;

    public MonitorSimpleModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonitorSimpleModel that = (MonitorSimpleModel) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        return getAddress() != null ? getAddress().equals(that.getAddress()) : that.getAddress() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        return result;
    }
}
