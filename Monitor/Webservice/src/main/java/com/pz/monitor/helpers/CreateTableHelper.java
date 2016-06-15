package com.pz.monitor.helpers;

import com.pz.monitor.database.DatabaseTables;
import com.pz.monitor.database.DatabaseTables.SENSOR_DETAILS;
import com.pz.monitor.database.DatabaseTables.SENSOR_METADATA;

public class CreateTableHelper {

    public static String initTablesQuery() {
        final String createSensorTable =
                "DROP TABLE " + DatabaseTables.SENSOR_DETAILS_TABLE+ " ;\n " +
                "CREATE TABLE "+ DatabaseTables.SENSOR_DETAILS_TABLE+ " (\n "+
                SENSOR_DETAILS.id + "         serial primary key,\n "+
                SENSOR_DETAILS.sensorId + "   text,\n "+
                SENSOR_DETAILS.cpu_value + "  integer,\n "+
                SENSOR_DETAILS.cpu_unit +  "  text,\n "+
                SENSOR_DETAILS.network_value + " integer,\n "+
                SENSOR_DETAILS.network_unit + " text,\n "+
                SENSOR_DETAILS.memory_value + " integer,\n "+
                SENSOR_DETAILS.memory_unit + "  text,\n "+
                SENSOR_DETAILS.procRunning + "  integer,\n "+
                SENSOR_DETAILS.procSleeping + " integer,\n "+
                SENSOR_DETAILS.procStopped + "  integer,\n "+
                SENSOR_DETAILS.threads + "      integer,\n "+
                SENSOR_DETAILS.zombie + "       integer,\n "+
                SENSOR_DETAILS.createdOn + "    bigint\n "+
                ");\n ";

        final String createMetadataTable =
                "DROP TABLE " + DatabaseTables.SENSOR_METADATA_TABLE+ ";\n " +
                "CREATE TABLE "+ DatabaseTables.SENSOR_METADATA_TABLE+ " (\n "+
                SENSOR_METADATA.id + "          serial primary key,\n "+
                SENSOR_METADATA.sensorId + "    text,\n "+
                SENSOR_METADATA.name + "        text,\n "+
                SENSOR_METADATA.vendor + "      text,\n "+
                SENSOR_METADATA.version + "     text,\n "+
                SENSOR_METADATA.architecture + " text,\n "+
                SENSOR_METADATA.cpu_model + "   text,\n "+
                SENSOR_METADATA.cpu_vendor + "  text,\n "+
                SENSOR_METADATA.cpu_cores + "   integer,\n "+
                SENSOR_METADATA.cpu_cache + "   text,\n "+
                SENSOR_METADATA.hostname + "    text,\n "+
                SENSOR_METADATA.dns + "         text,\n "+
                SENSOR_METADATA.createdOn + "    bigint\n "+
                ");\n";

            final String createMetricsTable =
                "DROP TABLE " + DatabaseTables.METRICS_TABLE+ ";\n " +
                "CREATE TABLE "+ DatabaseTables.METRICS_TABLE+ " (\n "+
                DatabaseTables.METRICS.id + "           serial primary key,\n "+
                DatabaseTables.METRICS.sensorId + "     text,\n "+
                DatabaseTables.METRICS.description + "  text,\n "+
                DatabaseTables.METRICS.metricType + "   text,\n "+
                DatabaseTables.METRICS.measure + "      text,\n "+
                DatabaseTables.METRICS.userId + "       integer,\n "+
                DatabaseTables.METRICS.interval + "     integer,\n "+
                DatabaseTables.METRICS.windowSize + "   integer\n "+
                ");\n";

        final String insertSensor = "INSERT INTO " + DatabaseTables.SENSOR_METADATA_TABLE +
            " (id, sensorId, name, vendor, version, architecture, cpu_model, cpu_vendor, cpu_cores, cpu_cache, hostname, dns, createdOn )\n " +
            " values (1, 'sensor1' ,'sensor name', 'vendor1', 'v1', 'arch1', 'model1', 'vendorcpu', 3, 'cache', 'host1', 'dsfs', 1231231);\n";

        return createSensorTable + createMetadataTable + createMetricsTable + insertSensor;
    }
}
