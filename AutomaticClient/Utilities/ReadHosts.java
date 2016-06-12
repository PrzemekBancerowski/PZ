package Utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadHosts {

	public ReadHosts() {
	};
	
	@SuppressWarnings("rawtypes")
	public static ArrayList ReadHostsFromFile(String filename) throws IOException{

		ArrayList<String> hostList = new ArrayList<String>();
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.out.println("Can't find hosts file. "
					+ "\nPress enter to exit.");
			System.in.read();
			System.exit(-1);
		}
		
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    if (line == null)
		    {
		    	System.out.println("Hosts file is empty. \n"
		    			+ "Press enter to exit.");
		    	System.in.read();
		    	System.exit(-1);
		    }
	
		    while (line != null) {
		    	hostList.add(line);
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    //String everything = sb.toString();
		    //System.out.println(everything);
		} catch (IOException e) {
			System.out.println("I/O exception. "
					+ e
					+ "\nPress enter to exit.");
			System.in.read();
			System.exit(-1);
		} finally {
		    br.close();
		}
		
		return hostList;
	}
}

