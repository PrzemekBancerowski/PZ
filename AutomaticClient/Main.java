import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import Response.AskRESTResponse;
import Response.GetHostsAndSensorsResponse;
import Service.AskRESTService;
import Service.GetHostsAndSensorsService;
import Utilities.MeasurementType;
import Utilities.ReadHosts;
import Utilities.StitchRequest;

public class Main {

	@SuppressWarnings({ "unchecked", "resource" })
	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("Automatic client start");
		ArrayList<String> hostList = new ArrayList<String>();
		Map<String, Integer> typeValue = new HashMap<String, Integer>();
		Map<String, Map<String, Integer>> sensorTypeValue = new HashMap<String,  Map<String, Integer>>();
		
		hostList = ReadHosts.ReadHostsFromFile("C:\\hosts.dat");
		
		Map<String, List<String>> hosts = new HashMap<>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Input type of listening :\n"
				+ "CPU\n"
				+ "Memory\n"
				+ "Network\n"
				+ "\nAnd max top argument:");
		String inputType = scan.next();
		int top = scan.nextInt();
/*		
		System.out.println("inputType : " + inputType);
		System.out.println("input int : " + top);
		System.out.println(inputType.equals(MeasurementType.CPU.name().toString()));
		*/
		if ( !(inputType.equals(MeasurementType.CPU.name().toString())) && !(inputType.equals(MeasurementType.Memory.name().toString())) && !(inputType.equals(MeasurementType.Network.name().toString()))){
			System.out.println("Incorrect input");
			System.exit(-1);
		}
		
		while(true){
			for(String server : hostList) {
				//System.out.println(server);
				GetHostsAndSensorsResponse getHostsAndSensorsResponse = new GetHostsAndSensorsResponse();			
				getHostsAndSensorsResponse = GetHostsAndSensorsService.getInfo(server);
				hosts = getHostsAndSensorsResponse.getHosts();

				for(Map.Entry<String, List<String>> entry : hosts.entrySet())
				{
					String key = entry.getKey();
					List<String> value = entry.getValue();
					System.out.println(key);
					
					
					for(String sensor : value)
					{
						
						typeValue.clear();					
						
						typeValue.put(MeasurementType.CPU.name(), executeMeasurement(server, sensor, MeasurementType.CPU.name()));
						typeValue.put(MeasurementType.Memory.name(), executeMeasurement(server, sensor, MeasurementType.Memory.name()));
						typeValue.put(MeasurementType.Network.name(), executeMeasurement(server, sensor, MeasurementType.Network.name()));
						sensorTypeValue.put(sensor, typeValue);

						//System.out.println("\t" + sensor);					
						
						/*System.out.println("CPU : " +
								executeMeasurement(server, sensor, MeasurementType.CPU.name())
						);
						System.out.println("Memory : " +
								executeMeasurement(server, sensor, MeasurementType.Memory.name())
						);
						System.out.println("Network : " +
								executeMeasurement(server, sensor, MeasurementType.Network.name())
						);*/
						
						
					}
					showTop(sensorTypeValue, inputType, top);
					sensorTypeValue.clear();
					System.out.println("------------------------------");
					
				}
			}
			
			Thread.sleep(5000);
			clearConsole();
		}
		
		
		
	}

	public static Integer executeMeasurement(String host, String sensor, String measurement) throws ClientProtocolException, JSONException, IOException{

		String reqPath = host + StitchRequest.getRequest(sensor, measurement);
		AskRESTResponse askRESTResponse = new AskRESTResponse();
		askRESTResponse = AskRESTService.getInfo(reqPath);
		
		
		return askRESTResponse.getValues().get(askRESTResponse.getValues().size() - 1);
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				return (e1.getValue()).compareTo(e2.getValue());
			}
		});
	 
		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
	 
		return result;
	}
	
	public static void showTop(Map<String, Map<String, Integer>> map, String type, Integer limit){
		
		HashMap<String, Integer> sortedValues = new HashMap<String, Integer>();
		for(String key : map.keySet()){
			sortedValues.put(key + "-" + type, map.get(key).get(type));
			
		}
	
		sortedValues = (HashMap<String, Integer>) sortByValue(sortedValues);
						
		int counter = 1;
		for(String key : sortedValues.keySet()){
			System.out.println("name : " + key + " value : " + sortedValues.get(key));
			++counter;
			if (counter > limit)
				break;
			//sortedValues.put(key + type, map.get(key).get(type));
			
		}
	}
	
	public final static void clearConsole()
	{
		for(int clear = 0; clear < 1000; clear++) {
		    System.out.println("\b") ;
		}
		
	    try
	    {
	        final String os = System.getProperty("os.name");

	        if (os.contains("Windows"))
	        {
	            Runtime.getRuntime().exec("cls");
	        }
	        else
	        {
	            Runtime.getRuntime().exec("clear");
	        }
	    }
	    catch (final Exception e)
	    {
	        //  Handle any exceptions.
	    }
	}
}
