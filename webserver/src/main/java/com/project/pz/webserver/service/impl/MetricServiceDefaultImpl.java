package com.project.pz.webserver.service.impl;

import com.google.common.collect.Lists;
import com.project.pz.webserver.dict.Measure;
import com.project.pz.webserver.dict.MetricType;
import com.project.pz.webserver.exception.MetricNotFoundException;
import com.project.pz.webserver.model.MetricModel;
import com.project.pz.webserver.service.MetricService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Piotr Sołtysiak on 2016-06-13.
 * Contact: piotrek.soltysiak@gmail.com
 */
@Service
public class MetricServiceDefaultImpl implements MetricService {

    private List<MetricModel> metricList = mockMetricList();

    @Override
    public List<MetricModel> getMetrics(String monitorId, String sensorId) {
        return metricList;
    }

    @Override
    public MetricModel getMetric(String monitorId, String sensorId, String metricId) throws MetricNotFoundException {

        for (MetricModel metricModel : metricList) {
            if (metricModel.getId().equals(metricId)) {
                return metricModel;
            }
        }

        throw new MetricNotFoundException(monitorId, sensorId, metricId);
    }

    @Override
    public MetricModel addMetric(String monitorId, String sensorId, MetricModel metric) {
        metric.setId("MOCK_ID_" + RandomUtils.nextInt(1, 9999999));

        metricList.add(metric);

        return metric;
    }

    @Override
    public void removeMetric(String monitorId, String sensorId, String metricId) {
        for (MetricModel metricModel : metricList) {
            if (metricModel.getId().equals(metricId)) {
                metricList.remove(metricModel);
                return;
            }
        }
    }

    private List<MetricModel> mockMetricList() {
        List<MetricModel> metricList = Lists.newArrayList();

        for (int i = 1; i < 10; i++) {
            MetricModel metricModel = new MetricModel();
            metricModel.setId("Id_" + i);
            metricModel.setDescription("Description_" + i);
            metricModel.setInterval(i * 1234);
            metricModel.setMeasure(i > 3 ? i > 6 ? Measure.CPU : Measure.MEMORY : Measure.NETWORK);
            metricModel.setMetricType(i > 5 ? MetricType.SIMPLE : MetricType.COMPLEX);
            metricModel.setWindowSize(i * 100);
            metricModel.setUserId("TEST_USER");

            metricList.add(metricModel);
        }

        return metricList;
    }


}
