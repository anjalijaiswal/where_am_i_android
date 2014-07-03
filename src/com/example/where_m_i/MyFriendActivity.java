package com.example.where_m_i;

import java.util.ArrayList;

import library.UserFunctions;

import org.json.JSONObject;

import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import android.widget.ArrayAdapter;
import android.widget.Toast;


public class MyFriendActivity  extends ListActivity{
	public static String API_KEY1 = "api_key2";
	private ArrayAdapter<String> listAdapter ;
	ArrayList<String> arname = new ArrayList<String>();
	ArrayList<String> arnumber = new ArrayList<String>();
	//static String[] contact;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.myfriends);

		Intent intentfriend= getIntent();	
		API_KEY1 = intentfriend.getStringExtra(UserShow.API_KEY);

		//getDetails();
		super.onCreate(savedInstanceState);
		Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
		ContentResolver cr = getContentResolver();
		//	Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,null, null, null, null);

		String[] projection    = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,ContactsContract.CommonDataKinds.Phone._ID,
				ContactsContract.CommonDataKinds.Phone.NUMBER ,};
		Cursor names = getContentResolver().query(uri, projection, null, null, null);
		/*if( cur != null && cur.moveToFirst()){

			System.out.println("m in if");
			//String name= cur.getString(cur.getColumnIndex("ContactNumber"));
			cur.close(); 	

		}*/
		//	else{
		if (names!=null) {
			names.moveToFirst();
			int indexName = names.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
			int indexNumber = names.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
			int indexId = names.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
			//System.out.println("m in else");
			
			do {
				
				String name   = names.getString(indexName);
				Log.e("Name new:", name);
				String number = names.getString(indexNumber);
				Log.e("Number new:","::"+number);
				//String id     = names.getString(indexId);
				//Log.e("ID new:","::"+id);
			  // String contact[];
			   
			   arname.add(name);
			   arnumber.add(number);
			//   ar.add(number);
			 //  System.out.println("m in do of contact             "+ar);
			  
			} while (names.moveToNext());
			 ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arname);
			   setListAdapter(adapter);
			   new  SendRequestTask(arname,arnumber,API_KEY1).execute();
		}
		else
			Toast.makeText(MyFriendActivity.this, "No Data", Toast.LENGTH_SHORT).show();
		   names.close();
	}


	//}
	protected void onListItemClick(View v,int position,long id) 
	{
		String item=(String)getListAdapter().getItem(position);
		Toast.makeText(this, item+"selected", Toast.LENGTH_SHORT).show();

	}

}

class SendRequestTask extends AsyncTask<Void,Void,JSONObject> 
{   
	ArrayList<String> name;
	ArrayList<String> number;
	String API_KEY1;
	public SendRequestTask(ArrayList<String> arname,ArrayList<String> arnumber,String API_KEY1)
	{
		this.name=arname;
		this.number=arnumber;
		this.API_KEY1=API_KEY1;
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

		
	}
	@Override
	protected JSONObject doInBackground(Void... params) {
		// TODO Auto-generated method stub
		UserFunctions userFunction =new UserFunctions();
		JSONObject json = null;
		
		json = userFunction.contactUser(name,number,API_KEY1);
		
		return json;
	}
}
