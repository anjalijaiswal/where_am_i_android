package library;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.os.AsyncTask;
import android.util.Log;
 
public class JSONParser {
 
    static InputStream is = null;
    static JSONObject jObj = null;
    static String json = "";
 
    // constructor
    public JSONParser() {
 
    }
 
    public JSONObject getJSONFromUrl(String url, List<NameValuePair> params,String API_KEY1)  {
    	  System.out.println("m in getjsonfromurl");
        try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("api_key", "API_KEY1");
            httpPost.setEntity(new UrlEncodedFormEntity(params));
 
          //  System.out.println("its hiting (json parser)"+url);
          //  System.out.println("its hiting (json parser)"+params);
           
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();
           // System.out.println("username json parser" +params);
           // System.out.println("password json func "+params);
        } catch (Exception e) {
        	
          //  e.printStackTrace();
        
        	System.out.println("this is exception" +is);
            //e.printStackTrace();
        	
            //json= "{"response_code:400"}" ;
           // json.toString();
            System.out.println("b4 try");
            
           //(JSONException e1)  {
			try { 
				jObj = new JSONObject("{\"response_code\":\"400\"}");
				System.out.println("this is in tryjObj = new JSONObject(json); ");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				System.out.println("this is running catchss");
				e1.printStackTrace();
			}
			System.out.println("the result is " +json);
			System.out.println("jobject is   ......" +jObj);
			// TODO Auto-generated catch block
			e.printStackTrace();
			return jObj;
		}   
          
        
       
 
        try {
        	System.out.println(".......jkhjghjgjhg..........");
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
            Log.e("JSON", json);
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);            
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
 
        // return JSON String
        return jObj;
 
    }
}