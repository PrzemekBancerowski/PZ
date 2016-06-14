package Utilities;

public class StitchRequest {

	public StitchRequest() {
	}
	
	public static String getRequest(String sensor, String type){
		
		String req = "";
		req += "sensorDetails/simple?"
				+ "SensorId=" + sensor
				+ "&Measurement=" + type;
		
		return req;
	}
}
