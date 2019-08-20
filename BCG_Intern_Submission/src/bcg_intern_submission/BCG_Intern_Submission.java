/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bcg_intern_submission;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
/**
 *
 * @author nichole
 */
public class BCG_Intern_Submission {

    /**
     * @param args the command line arguments
     */
    //Store the value of key  
    String key="";
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        BCG_Intern_Submission http = new BCG_Intern_Submission();

		System.out.println("Send Http GET request");
		http.sendGet();
		
		System.out.println("\nSend Http POST request");
		http.sendPost();
    }
    private void sendGet() throws Exception {

		String url = "https://interns.bcgdvsydney.com/api/v1/key";
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		
                BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
                
		System.out.println(response.toString());
                String r= response.toString();
                // Get the value of key
                key = r.substring(9,45);
                System.out.println(key);

	}
	
	// HTTP POST request
	private void sendPost() throws Exception {

		String url = "https://interns.bcgdvsydney.com/api/v1/submit?apiKey="+key;
                //<base URI>/api/v1/submit?apiKey=<API_KEY>
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                con.setDoOutput(true);

                //json format
                byte[] out = "{\"name\":\"Jiayue Yang\",\"email\":\"jiayueya@andrew.cmu.edu\"}" .getBytes(StandardCharsets.UTF_8);
                int length = out.length;

                con.setFixedLengthStreamingMode(length);
                con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                con.connect();
                try(OutputStream os = con.getOutputStream()) {
                os.write(out);
                }
		

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
	
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		          
		//print result
		System.out.println(response.toString());

	}
    
}
