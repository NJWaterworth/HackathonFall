package w.nick.safecar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetClientGet {

	public static void main(String[] args){
		System.out.println("Temperature: " + getTemp(36.08,-94.17));
	}

	// http://localhost:8080/RESTfulExample/json/product/get
	public static double getTemp(double lat, double lng) {
		String ural = "http://api.openweathermap.org/data/2.5/weather?lat=";
		String ural2 = "&lon=";
		String ural3 = "&units=imperial&type=accurate&mode=xml&APPID=66bf8bca4952f3cb9d0b08273919dc35"; 
		ural = ural + lat + ural2 + lng + ural3;
		
		String temp = "";
	  try {

		URL url = new URL(ural);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/json");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
			(conn.getInputStream())));

		String outline;
		String output = "";
		while ((outline = br.readLine()) != null) {
			output += outline;
		}
		output = output.substring(output.indexOf("temperature value"),output.indexOf("temperature value")+24);
		output = output.substring(output.indexOf("\""),output.indexOf("\"",output.indexOf("\"")+1)+1);
				
		temp = output.substring(1,output.length()-1);
		
		conn.disconnect();

	  } catch (MalformedURLException e) {

		e.printStackTrace();

	  } catch (IOException e) {

		e.printStackTrace();

	  }
		return Double.parseDouble(temp);
	}

}