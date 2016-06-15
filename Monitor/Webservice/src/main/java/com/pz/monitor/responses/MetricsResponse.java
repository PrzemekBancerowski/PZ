package com.pz.monitor.responses;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MetricsResponse {
	List<MetricResponse> items = new ArrayList<>();

	public MetricsResponse(ResultSet resultSet) throws SQLException {
		while (!resultSet.isLast()) {
			items.add(new MetricResponse(resultSet));
		}
	}

	public List<MetricResponse> getResponse() {
		return items;
	}
}
