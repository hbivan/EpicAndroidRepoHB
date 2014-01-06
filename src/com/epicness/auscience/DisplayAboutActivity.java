package com.epicness.auscience;

import java.text.DecimalFormat;

import android.annotation.TargetApi;
import android.content.Intent;
import android.location.*;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.SyncStateContract.Constants;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class DisplayAboutActivity extends FragmentActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_display_about);
		// Show the Up button in the action bar.

		GoogleMap map = ((MapFragment) getFragmentManager()
	            .findFragmentById(R.id.map)).getMap();

        LatLng aberdeen = new LatLng(57.165564,-2.102049);
        Location abdn = new Location("Aberdeen");
        abdn.setLatitude(aberdeen.latitude);
        abdn.setLongitude(aberdeen.longitude);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setCompassEnabled(true);
        map.getUiSettings().setRotateGesturesEnabled(true);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(aberdeen, 15));
        map.addMarker(new MarkerOptions()
                .title("Aberdeen, UK")
                .snippet("The University of Aberdeen.")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE))
                .position(aberdeen));
        
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        boolean enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        
        if (!enabled){
        	Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        	startActivity(intent);
        }
        
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);
        Location myLoc = locationManager.getLastKnownLocation(provider);
        if (myLoc != null) { 
        	Float dist = myLoc.distanceTo(abdn)/1000;
        	DecimalFormat df = new DecimalFormat("#.##");
        	Toast.makeText(getApplicationContext(),
                "You are  " + df.format(dist).toString() + " km. away from the University", Toast.LENGTH_LONG).show();
	    } else {
        	Toast.makeText(getApplicationContext(),
                "Your Current Location Cannot be Discovered", Toast.LENGTH_LONG).show();
	    }
		setupActionBar();
	}
	public void toHybrid(View view){
		GoogleMap map = ((MapFragment) getFragmentManager()
	            .findFragmentById(R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	}
	public void toTerrain(View view){
		GoogleMap map = ((MapFragment) getFragmentManager()
	            .findFragmentById(R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
	}
	public void toNone(View view){
		GoogleMap map = ((MapFragment) getFragmentManager()
	            .findFragmentById(R.id.map)).getMap();
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.display_about, menu);
//		return true;
//	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		case R.id.action_tweet:
			Intent about = new Intent(this, DisplayTweetActivity.class);
			startActivity(about);
            return true;
		}
		return super.onOptionsItemSelected(item);
	}


}
