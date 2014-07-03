package library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.util.Log;


public class UserFunctions {

	private static JSONParser jsonParser;

	// Testing in localhost using wamp or xampp 
	// use http://10.0.2.2/ to connect to your localhost ie http://localhost/
	//	private static String loginURL = "http://192.168.97.112:3000/users/login";
	private static String logoutURL = "http://192.168.97.122:3000/users/logout.json";
	private static String loginURL = "http://192.168.97.122:3000/users/login.json";
	private static String registerURL = "http://192.168.97.122:3000/users/registration.json";
	private static String locationURL= "http://192.168.97.122:3000/users/login.json" ;
	private static String contactURL= "http://192.168.97.112:3000/users/fetch_contacts.json?" ;
	//private static String login_tag = "login";
	//private static String register_tag = "register";

	// constructor
	public UserFunctions(){
		jsonParser = new JSONParser();
	}

	/**
	 * function make Login Request
	 * @param email
	 * @param password
	 * */
	public static JSONObject loginUser(String username,String password) {
		System.out.println("M in login user");
		// Building Parameters
		String API_KEY1="null";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//   params.add(new BasicNameValuePair("tag", login_tag));
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		JSONObject json = jsonParser.getJSONFromUrl(loginURL, params,API_KEY1);
		// return json
		//	Log.e("JSON", json.toString());
		return json;
	}
	public static JSONObject contactUser(ArrayList<String> name,ArrayList<String> number,String API_KEY1) {
		System.out.println("M in contact user");
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		String[] stringArray = name.toArray(new String[name.size()]);
		for (int i = 0; i < stringArray.length; i++) {
		   params.add(new BasicNameValuePair("name", stringArray[i]));
		   
		}
		String[] stringA = number.toArray(new String[number.size()]);
		for (int i = 0; i < stringA.length; i++) {
			   params.add(new BasicNameValuePair("number", stringA[i]));
			}
		System.out.println("this is contact%%%%%%%%%%%   "+params);
//		params.add(new BasicNameValuePair("contactId", contact_id));
//		params.add(new BasicNameValuePair("contactName", contact_name));
//		params.add(new BasicNameValuePair("contactNo", contact_no));
		
		JSONObject json = jsonParser.getJSONFromUrl(contactURL, params,API_KEY1);
		// return json
		//	Log.e("JSON", json.toString());
		return json;
	}
	/**
	 * function make Login Request
	 * @param name
	 * @param email
	 * @param password
	 * */
	public static JSONObject registerUser(String first_name, String last_name, String username, String password, String email, String phone_number, String address){
		// Building Parameters
		String API_KEY1="jsdhbasghdvasghvhgasdvghdfv";
		List<NameValuePair> params = new ArrayList<NameValuePair>();
	//	params.add(new BasicNameValuePair("tag", register_tag));
		params.add(new BasicNameValuePair("firstname",first_name ));
		params.add(new BasicNameValuePair("lastname",last_name ));
		params.add(new BasicNameValuePair("username",username ));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("email", email));
		params.add(new BasicNameValuePair("phone", phone_number));
		params.add(new BasicNameValuePair("address", address));
		// getting JSON Object
		JSONObject json = jsonParser.getJSONFromUrl(registerURL, params,API_KEY1);
		// return json
		System.out.println("m in json object register url n url for register is        "+registerURL);
		Log.e("JSON", json.toString());
		return json;
	}

	/**
	 * Function get Login status
	 * */
	//    public boolean isUserLoggedIn(Context context){
	//        DatabaseHandler db = new DatabaseHandler(context);
	//        int count = db.getRowCount();
	//        if(count > 0){
	//            // user logged in
	//            return true;
	//        }
	//        return false;
	//    }

	/**
	 * Function to logout user
	 * kill session
	 * */
	    public JSONObject logoutUser(String API_KEY1) {
	    	List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	
	    	System.out.println("json parser logout api key///////" +API_KEY1);
	    	
	    	params.add(new BasicNameValuePair("",null ));
	    	JSONObject json = jsonParser.getJSONFromUrl(logoutURL,params,API_KEY1);
	    	return json;
	    }
	public JSONObject locationUser(double lon,double lat,String API_KEY1) {
		// Building Parameters
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		//   params.add(new BasicNameValuePair("tag", login_tag));
		System.out.println("longitude  " +lon);
		System.out.println("latitude  "+lat);
		params.add(new BasicNameValuePair("longitude",""+lon ));
		params.add(new BasicNameValuePair("latitude", ""+lat));
		JSONObject json = jsonParser.getJSONFromUrl(locationURL, params,API_KEY1);
		// return json
		//Log.e("JSON", json.toString());
		return json;
	}

}