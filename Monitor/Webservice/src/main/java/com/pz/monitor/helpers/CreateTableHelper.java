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
                SENSOR_METADATA.cpu_cores + "   text,\n "+
                SENSOR_METADATA.cpu_cache + "   text,\n "+
                SENSOR_METADATA.hostname + "    text,\n "+
                SENSOR_METADATA.dns + "         text,\n "+
                SENSOR_METADATA.createdOn + "    bigint\n "+
                ");\n";

        return createSensorTable + createMetadataTable;
    }
}
