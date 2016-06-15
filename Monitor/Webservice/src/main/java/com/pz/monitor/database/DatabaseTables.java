package com.pz.monitor.database;

import java.util.HashMap;
import java.util.Map;

public class DatabaseTables {

    public static final String SENSOR_METADATA_TABLE= "SENSOR_METADATA";
    public enum SENSOR_METADATA {
        id("id"),
        sensorId("sensorId"),
        name("name"),
        vendor("vendor"),
        version("version"),
        architecture("architecture"),
        cpu_model("cpu_model"),
        cpu_vendor("cpu_vendor"),
        cpu_cores("cpu_cores"),
        cpu_cache("cpu_cache"),
        hostname("hostname"),
        dns("dns"),
        createdOn("createdOn");

        private String text;
        SENSOR_METADATA(String value) {
            text = value;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static final String SENSOR_DETAILS_TABLE= "SENSOR_DETAILS";
    public enum SENSOR_DETAILS {
        id("id"),
        sensorId("sensorId"),
        cpu_value("cpu_value"),
        cpu_unit("cpu_unit"),
        network_value("network_value"),
        network_unit("network_unit"),
        memory_value("memory_value"),
        memory_unit("memory_uni"),
        procRunning("procRunning"),
        procSleeping("procSleeping"),
        procStopped("procStopped"),
        threads("threads"),
        zombie("zombie"),
        createdOn("createdOn");


        private String text;
        SENSOR_DETAILS(String value) {
            text = value;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static final String METRICS_TABLE= "METRICS_TABLE";
    public enum METRICS {
        id("id"),
        sensorId("sensorId"),
        description("description"),
        metricType("metricType"),
        measure("measure"),
        userId("userId"),
        interval("interval"),
        windowSize("windowSize");

        private String text;
        METRICS(String value) {
            text = value;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public static Map<String, SENSOR_DETAILS> measurementMapper = new HashMap<>();
    static {
        measurementMapper.put("CPU", SENSOR_DETAILS.cpu_value);
        measurementMapper.put("Network", SENSOR_DETAILS.network_value);
        measurementMapper.put("Memory", SENSOR_DETAILS.network_value);
    }


    public static Map<String, String> operationMapper = new HashMap<>();
    static {
        operationMapper.put("Average", "avg");
        operationMapper.put("Max", "max");
        operationMapper.put("Min", "min");
        operationMapper.put("Summary", "sum");
    }

}
