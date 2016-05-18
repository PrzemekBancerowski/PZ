package com.project.pz.webserver.entity;

import javax.persistence.*;

/**
 * Created by Piotr Sołtysiak on 2016-05-18.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Entity
@Table(name = "measurement_sensors")
public class MeasurementSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "measurement_id", nullable = false)
    private UserMeasurement measurement;

    @Column(name = "sensor_name", nullable = false)
    private String sensorName;

    public MeasurementSensor() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserMeasurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(UserMeasurement measurement) {
        this.measurement = measurement;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}
