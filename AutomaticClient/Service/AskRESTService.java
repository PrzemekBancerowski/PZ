package Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Response.AskRESTResponse;

public class AskRESTService {

	public static AskRESTResponse getInfo(String req)
			throws ClientProtocolException, IOException, JSONException {

		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet(req);

		Gson gson = new GsonBuilder().serializeNulls().create();

		AskRESTResponse askRESTResponse = new AskRESTResponse();

			getRequest.setHeader("Content-type", "application/json");

			HttpResponse response = httpClient.execute(getRequest);

			HttpEntity entity = response.getEntity();

			if (entity != null) {
				
				askRESTResponse = gson.fromJson(new BufferedReader(new InputStreamReader(entity.getContent())),
						AskRESTResponse.class);

				return askRESTResponse;
				
			}

		return null;

	}
	
}
