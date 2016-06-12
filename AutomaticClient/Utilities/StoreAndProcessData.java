package Utilities;

import java.util.HashMap;
import java.util.Map;

public class StoreAndProcessData {

	String server;
	String host;
	String sensor;
	Map<String, Integer> typeValue = new HashMap<String, Integer>();
	
	public StoreAndProcessData(){
		this.server = null;
		this.host = null;
		this.sensor = null;
		this.typeValue.clear();
	};
	
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public Map<String, Integer> getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(Map<String, Integer> typeValue) {
		this.typeValue = typeValue;
	}	
	
}
