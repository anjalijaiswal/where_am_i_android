package com.example.where_m_i;


import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.TextView;

public class MyProfileActivity extends Activity 
{
	StringBuilder sb = new StringBuilder();
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myprofile);
		Intent intent_profile = getIntent();	
		
		String API_KEY1 = intent_profile.getStringExtra(UserShow.API_KEY);
	}
		
	


}					