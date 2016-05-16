package com.pz.monitor.requests;

import com.pz.monitor.database.DatabaseTables;

import java.util.Date;
import java.util.List;

public class ComplexSensorDetailsRequest implements Request {
    public String Operation;
    public String Measure;
    public Long StartTime;
    public Long EndTime;
    public List<Sensor> sensors;

    public static class Sensor {
        public String id;
        public String name;
    }

    @Override
    public void validate() {
        boolean isCorrect = Operation!=null && Measure!=null &&
                sensors!=null && sensors.size()!=0 &&
                sensors.stream().allMatch(sensor -> sensor.name!=null && sensor.id!=null) &&
                DatabaseTables.measurementMapper.containsKey(Measure) && DatabaseTables.operationMapper.containsKey(Operation);
        if (!isCorrect) throw new IllegalStateException("Invalid payload");
    }

    @Override
    public void reassignValues() {
        StartTime = StartTime == null ? 0L : StartTime;
        EndTime = EndTime == null ? new Date().getTime() : EndTime;
        Measure = DatabaseTables.measurementMapper.get(Measure).toString();
        Operation = DatabaseTables.operationMapper.get(Operation);
    }

}
