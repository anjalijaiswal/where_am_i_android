package com.example.where_m_i;

import java.util.List;

import library.UserFunctions;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyLocationActivity extends Activity implements OnClickListener{

	static int counter = 0;
	Button btnGPSLocation,btnNetworkLocation,btnLocateOnMap,btnGetLastLocation;
	TextView tvLocationDetail=null;

	LocationManager locManager=null;
	LocationProvider locProvider = null;
	Location location = null;

	List<String> locationList=null;
	LocationListener gpsLocationListener,networkLocationListener = null;
	private static String API_KEY1 ;
	double lat;
	double lon;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_location);
		Intent intentloc = getIntent();	
		
		API_KEY1 = intentloc.getStringExtra(UserShow.API_KEY);
		
		
		btnGPSLocation = (Button) findViewById(R.id.get_gps_location);
		btnGPSLocation.setOnClickListener(this);

		btnNetworkLocation = (Button) findViewById(R.id.get_network_location);
		btnNetworkLocation.setOnClickListener(this);

		btnLocateOnMap = (Button) findViewById(R.id.locate_on_map);
		btnLocateOnMap.setOnClickListener(this);

		btnGetLastLocation = (Button) findViewById(R.id.get_last_location);
		btnGetLastLocation.setOnClickListener(this);

		tvLocationDetail = (TextView) findViewById(R.id.my_location);

		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		locationList = locManager.getAllProviders();



		gpsLocationListener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				lat = location.getLatitude();
				lon = location.getLongitude();
				tvLocationDetail.setText(counter+"Latitude : "+lat +"\nLongitude : "+lon);
				counter++;

				new SendLocationTask().execute();
			}
		};

		networkLocationListener = new LocationListener() {

			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				lat = location.getLatitude();
				lon = location.getLongitude();
				tvLocationDetail.setText(counter+"Latitude : "+lat +"\nLongitude : "+lon);
				counter++;
				new SendLocationTask().execute();
			}
		};



	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.get_gps_location:
			if (locationList.contains("gps")) {

				if (locManager.isProviderEnabled("gps")) {
					locManager.requestLocationUpdates("gps", 2000, 0, gpsLocationListener);
				}else 
					Toast.makeText(MyLocationActivity.this, "Gps Disabled", Toast.LENGTH_SHORT).show();
			}
			else {
				Toast.makeText(MyLocationActivity.this, "Provider not available ", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.get_network_location:
			if (locationList.contains("network")) {
				if (locManager.isProviderEnabled("network"))
					locManager.requestLocationUpdates("network", 2000, 0, networkLocationListener);
				else 
					Toast.makeText(MyLocationActivity.this, "Network Disabled", Toast.LENGTH_SHORT).show();
			}
			else {
				Toast.makeText(MyLocationActivity.this, "Provider not available ", Toast.LENGTH_SHORT).show();
			}
			break;

		case R.id.get_last_location:

			location = locManager.getLastKnownLocation("passive");
			if (location==null) {
				Toast.makeText(MyLocationActivity.this, "Not available ", Toast.LENGTH_SHORT).show();
				return;
			}
			lat = location.getLatitude();
			lon = location.getLongitude();
			tvLocationDetail.setText("Latitude : "+lat +"\nLongitude : "+lon);
			break;



		case R.id.locate_on_map:
			locateOnMap();
			break;

		default:
			break;
		}
	}


	private void locateOnMap() {


		String label = "My Location";
		String uriBegin = "geo:" + lat + "," + lon;
		String query = lat + "," + lon + "(" + label + ")";
		String encodedQuery = Uri.encode(query);
		String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
		Uri uri = Uri.parse(uriString);
		Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}
	class SendLocationTask extends AsyncTask<Void,Void,JSONObject> 
	{

		public SendLocationTask()
		{
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			Toast.makeText(MyLocationActivity.this, "Sending Location", 1000);
		}
		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if (result!=null) {
				Toast.makeText(MyLocationActivity.this, result.toString(), Toast.LENGTH_SHORT);
			}

		}
		@Override
		protected JSONObject doInBackground(Void... params) {
			// TODO Auto-generated method stub
			UserFunctions userFunction =new UserFunctions();
			JSONObject json = userFunction.locationUser(lon, lat,API_KEY1);

			return json;
			//{
			//	String[] contact;
			//	String API_KEY1;
			//	public SendRequestTask( String[] projection,String API_KEY1)
			//	{
			//		this.contact=projection;
			//		this.API_KEY1=API_KEY1;
			//	}
			//
			//	@Override
			//	protected void onPreExecute() {
			//		// TODO Auto-generated method stub
			//		super.onPreExecute();
			//	}
			//	@Override
			//	protected void onPostExecute(JSONObject result) {
			//		// TODO Auto-generated method stub
			//		
			//		super.onPostExecute(result);
			//
			//		
			//	}
			//	@Override
			//	protected JSONObject doInBackground(Void... params) {
			//		// TODO Auto-generated method stub
			//		UserFunctions userFunction =new UserFunctions();
			//		JSONObject json = null;
			//		
			//		json = userFunction.contactUser(contact,API_KEY1);
			//		
			//		return json;
			//	}
			//}class SendRequestTask extends AsyncTask<Void,Void,JSONObject> 
			//{
			//	String[] contact;
			//	String API_KEY1;
			//	public SendRequestTask( String[] projection,String API_KEY1)
			//	{
			//		this.contact=projection;
			//		this.API_KEY1=API_KEY1;
			//	}
			//
			//	@Override
			//	protected void onPreExecute() {
			//		// TODO Auto-generated method stub
			//		super.onPreExecute();
			//	}
			//	@Override
			//	protected void onPostExecute(JSONObject result) {
			//		// TODO Auto-generated method stub
			//		
			//		super.onPostExecute(result);
			//
			//		
			//	}
			//	@Override
			//	protected JSONObject doInBackground(Void... params) {
			//		// TODO Auto-generated method stub
			//		UserFunctions userFunction =new UserFunctions();
			//		JSONObject json = null;
			//		
			//		json = userFunction.contactUser(contact,API_KEY1);
			//		
			//		return json;
			//	}
			//}
		}
	}
}
