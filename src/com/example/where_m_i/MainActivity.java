package com.example.where_m_i;



import org.json.JSONException;
import org.json.JSONObject;

import library.UserFunctions;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	public final static String API_KEY = "api_key";
	public final static String EXTRA_MESSAGE2 = "com.example.Where_m_i.MESSAGE";
	EditText pw,un,fn,ln,email,phone,address;
	Button login,signup;
	TextView loginErrorMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		signup = (Button)findViewById(R.id.buttonsignup);
		login = (Button)findViewById(R.id.buttonlogin);
		//login button click event
		login.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//				Intent intent = new Intent(MainActivity.this, LoginActivity.class);
				un = (EditText) findViewById(R.id.editTextusername);
				pw = (EditText) findViewById(R.id.editTextpassword);
				String enteredUsername = un.getText().toString();
				String	enteredPassword = pw.getText().toString();
				//				    intent.putExtra(EXTRA_MESSAGE1, message1);
				//				    intent.putExtra(EXTRA_MESSAGE2, message2);
				//			        startActivity(intent);

				
				
				 if( un.getText().toString().length() == 0 ){
					 un.setError(Html.fromHtml("<font color='red'>Username is required!</font>") );
					 un.setTextColor(Color.RED);}
				 else if( pw.getText().toString().length() == 0 ){
					 pw.setError(Html.fromHtml("<font color='red'>password is required!</font>") );
				     un.setTextColor(Color.RED);}
				 else{
				    String username = un.getText().toString();
				    String password = pw.getText().toString();
				   
				    System.out.println("username main activity" + username);
					System.out.println("password main activity "+password);

					//UserFunctions userFunction =new UserFunctions();
					//JSONObject json1 = userFunction.loginUser(username,password);
					  
					        	System.out.println("What the hell");
				new SendRequestTask(enteredUsername, enteredPassword).execute();

			}
			
			}} ) ;

		signup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent2 = new Intent(MainActivity.this, RegisterActivity.class);
				startActivity(intent2);

			}
		}) ;

	}
	
	
	class SendRequestTask extends AsyncTask<Void,Void,JSONObject> 
	{
		String userName;
		String password;

		public SendRequestTask(String userName,String password)
		{
			this.password = password;
			this.userName = userName;

		}

		@Override
		protected void onPreExecute() {
			System.out.println("i m in async");
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			 try {
       	 // get LL json object
          // JSONObject json_msg = json1.getJSONObject("message");
				 setContentView(R.layout.activity_main);
				 loginErrorMsg=	(TextView) findViewById(R.id.login_error);
	        	String json_msg = result.getString("response_code");
	            
	           System.out.println("ohkz m in tryAND reSULT iS............."+result);
	           
	         
	            if (json_msg != null) {
	            	
	            	
	            	int msg=Integer.parseInt(json_msg);
	            	
	         
	            	
	            	if(msg==200) {
	            		String api_key = result.getString("login");
	            	//	System.out.println("ohkz m in tryAND api_key value 'login' iS.............");
		            	//loginErrorMsg.setText("");
		               // String res = json1.getString(SUCESS); 
		            	//Context c = getApplicationContext();
		        		//SharedPreferences sp = c.getSharedPreferences("shared_preference", Context.MODE_PRIVATE);
		            //	System.out.println("m in login>>>>>>>>>>>>>>>>>>>>>>>>>"+api_key);
		                Intent intentUserShow = new Intent(MainActivity.this, UserShow.class);
		                intentUserShow.putExtra(API_KEY, api_key);
		                startActivity(intentUserShow);
	            	}
	            	else if(msg==400){	            	   
	            	   //loginErrorMsg.setText("Server Not Reachable");
	            	  // System.out.println("ohkz m in server not reachable");
						Toast.makeText(MainActivity.this, "Server Not Reachable", Toast.LENGTH_SHORT).show();
                         
	            		//startActivity(new Intent(MainActivity.this,MainActivity.class));
	            	   
	               }
	            	else if(msg==0111){
	            		Toast.makeText(MainActivity.this, "Already signed in", Toast.LENGTH_SHORT).show();
	            	//loginErrorMsg.setText("Already Signed In");	
	            	// startActivity(new Intent(MainActivity.this,MainActivity.class));	
	            	}
	                else if(msg==1101){
                    // Error in login
	                	Toast.makeText(MainActivity.this, "Invalid login", Toast.LENGTH_SHORT).show();
	                   // loginErrorMsg.setText("InstartActivity(new Intent(RegisterActivity.this,UserShow.class));correct username/password");
	                   // startActivity(new Intent(MainActivity.this,MainActivity.class));
	                }
	            }
           	else {
           	            // startActivity(new Intent(MainActivity.this,MainActivity.class));
           	          Toast.makeText(MainActivity.this, "Server Not Reachable", Toast.LENGTH_SHORT).show();
           	          
	            	}
	            	
	            
	            
	        } 
	        catch (JSONException e) {
	            e.printStackTrace();
	        }
			


		}
		@Override
		protected JSONObject doInBackground(Void... params)           {
			// TODO Auto-generated method stub                                                                                                                        
			System.out.println("i m in do in background");
			UserFunctions userFunction =new UserFunctions();
			JSONObject json = null;

			json = userFunction.loginUser(userName,password);
			
			return json;
		}
	}
}
