package com.example.where_m_i;


import org.json.JSONObject;

import library.UserFunctions;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class UserShow extends Activity implements OnClickListener{
	private static final String MSG = "erhweuireyu";
	private static String API_KEY1 ;
	Button btnlocation,btnsignout,btnprofile,btnfriend;
	public final static String API_KEY = "api_key1";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_show);
		
		Intent intentUSerShow = getIntent();	
		
		API_KEY1 = intentUSerShow.getStringExtra(MainActivity.API_KEY);
		System.out.println("the api key in usher show is*************"+API_KEY1);
		btnlocation = (Button)findViewById(R.id.buttonlocation);
		btnlocation.setOnClickListener(this);
		btnsignout =(Button) findViewById(R.id.buttonsignout);
		btnsignout.setOnClickListener(this);
		btnprofile = (Button) findViewById(R.id.buttonprofile);
		btnprofile.setOnClickListener(this);
		btnfriend = (Button) findViewById(R.id.buttonfriend);
		btnfriend.setOnClickListener(this);


	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.buttonlocation:
			Intent intentloc = new Intent(this, MyLocationActivity.class);
			 intentloc.putExtra(MSG,API_KEY1);
			startActivity(intentloc);
			
			break;
		case R.id.buttonfriend:
			System.out.println("m in button friend");
			 Intent intentfriend = new Intent(this, MyFriendActivity.class);
			 intentfriend.putExtra(MSG,API_KEY1);
			 startActivity(intentfriend);
			break;
		
		case R.id.buttonprofile:
			Intent intent_profile = new Intent(this, MyProfileActivity.class);
		 intent_profile.putExtra(MSG,API_KEY1);
		startActivity(intent_profile); 
			
			break;
		case R.id.buttonsignout:
			System.out.println("m in button signout and api key is"+API_KEY1);
			UserFunctions userFunction =new UserFunctions();
			JSONObject json1 = userFunction.logoutUser(API_KEY1);
			
			Intent intentsignout = new Intent(this, MainActivity.class);
			startActivity(intentsignout);
			finish();
			break;

		default:
			break;
		}
	}
}
