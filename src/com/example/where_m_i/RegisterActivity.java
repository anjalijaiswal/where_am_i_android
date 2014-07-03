package com.example.where_m_i;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import library.UserFunctions;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	EditText pw,un,fn,ln,e_mail,phone,addrss;
	Button login,submit;
	String first_name,last_name,password,email,phone_number,address,username,contact_id, contact_no, contact_name;
	Intent intent2 = getIntent();
	Cursor c = null;
	ArrayList<String>  contactlist =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
        
//		Context c = getApplicationContext();
//		SharedPreferences sp = c.getSharedPreferences("shared_preference", Context.MODE_PRIVATE);

        Button submit = (Button) findViewById(R.id.buttonsubmit);
	
			submit.setOnClickListener(new View.OnClickListener() {
	
				@Override
				public void onClick(View view) {
				// TODO Auto-generated method stub
							
					String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
						EditText fn = (EditText)findViewById(R.id.editTextFN);
						EditText ln = (EditText)findViewById(R.id.editTextLN);
						EditText un = (EditText)findViewById(R.id.editTextUN);
						EditText pw = (EditText)findViewById(R.id.editTextPW);
						EditText e_mail = (EditText)findViewById(R.id.editTextemail);
						EditText phone = (EditText)findViewById(R.id.editTextphone);
						EditText addrss = (EditText)findViewById(R.id.editTextaddress);
						Boolean b =e_mail.getText().toString().matches(EMAIL_REGEX);
							
						String first_name = fn.getText().toString();
						String last_name = ln.getText().toString();
						String username = un.getText().toString();
						String password = pw.getText().toString();
						String email = e_mail.getText().toString();
						String phone_number = phone.getText().toString();
						String address = addrss.getText().toString();
								
								
						if( fn.getText().toString().length() == 0 )
						 fn.setError(Html.fromHtml("<font color='red'>First name is required!</font>"));
						
						else if( ln.getText().toString().length() == 0 )
							
								 ln.setError(Html.fromHtml("<font color='red'>Last name is required!</font>") );
						else if( un.getText().toString().length() == 0 )
							
								 un.setError(Html.fromHtml("<font color='red'>user name is required!</font>") );
						else if( pw.getText().toString().length() == 0 )
							
								pw.setError( Html.fromHtml("<font color='red'>password is required!</font>") );
						else if( e_mail.getText().toString().length() == 0)
						
								 e_mail.setError( Html.fromHtml("<font color='red'>email is required!</font>"));
						else if( b==false)
							     e_mail.setError( Html.fromHtml("<font color='red'>Email is Invalid!</font>"));
						else if( phone.getText().toString().length() < 10 )
							
								 phone.setError( Html.fromHtml("<font color='red'>phone is required!</font>") );
						else if( addrss.getText().toString().length() == 0 )
							  
							    addrss.setError(Html.fromHtml("<font color='red'>address is required!</font>") ); 
						
						
						else {
//						
							new SendRequestTask(first_name,last_name,username,password,email,phone_number,address).execute();
							
						}
							
				}
			}
		);
//			

	}
	
	

	class SendRequestTask extends AsyncTask<Void,Void,JSONObject> 
	{

		public SendRequestTask(String first_name, String last_name, String username, String password, String email, String phone_number, String address)
		{
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			try {
				String json_msg = result.getString("response_code");
			    //System.out.println("ohkz m in tryAND reSULT iS............."+result);
			    if (json_msg != null) {
			            int msg=Integer.parseInt(json_msg);
			            
			            if(msg==200) {
			            		Toast.makeText(RegisterActivity.this, "registration successfull. Please Login", Toast.LENGTH_SHORT).show();
			            		Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
				                finish();
			            	}
			            	else if(msg==400){	            	   
			            	 
			            		Toast.makeText(RegisterActivity.this, "Server Not Reachable", Toast.LENGTH_SHORT).show();
		                        
			            	}
			            	
		            	else {
		           	            
		            		Toast.makeText(RegisterActivity.this, "Register Unsuccessfull", Toast.LENGTH_SHORT).show();
			            	}
			            	
			    }       
			            
			    }
			        catch (JSONException e) {
			            e.printStackTrace();
			        }
					

			
		}
		@Override
		protected JSONObject doInBackground(Void... params) {
			// TODO Auto-generated method stub
			UserFunctions userFunction =new UserFunctions();
			JSONObject json = userFunction.registerUser(first_name,last_name,username,password,email,phone_number,address);
			
			return json;
		}
	}

}
