package Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetHostsAndSensorsResponse {

	private Map<String, List<String>> hosts = new HashMap<>();

	public Map<String, List<String>> getHosts() {
		return hosts;
	}

	public void setHosts(Map<String, List<String>> hosts) {
		this.hosts = hosts;
	}		
		

}
