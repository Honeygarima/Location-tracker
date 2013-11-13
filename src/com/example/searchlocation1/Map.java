package com.example.searchlocation1;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.databaseOpertaion.AndroidSQLiteActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class Map extends Activity implements OnMapLongClickListener, OnMapClickListener {

	  private GoogleMap map;
	  double lat , lng;
	  LocationManager lm;
	  TextView lati,lngi;
	  String address_set;
	  MapDrawer draw1 = new MapDrawer();
	  Polygon mapShape;
	//SeekBar mapseekbar;
	PolygonOptions ploy;
	  String latstr,lngstr;
	  
	 Geocoder geocoder;
		final int UPDATE_ADDRESS = 1;

		final int UPDATE_LATLNG = 2;
	  @SuppressLint("NewApi")
	@Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    lati = (TextView)findViewById(R.id.lati);
	    lngi = (TextView)findViewById(R.id.lng);
	    
	  //  mapseekbar=(SeekBar)findViewById(R.id.seekBar1);
	 // GetCurrent location	  
	    
	     lm = (LocationManager)getSystemService(LOCATION_SERVICE);
	     
	    
	    lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0,new LocationListener() 
	    {
			
			public void onLocationChanged(Location location) {}

			public void onProviderDisabled(String provider){}

			public void onProviderEnabled(String provider){}

			public void onStatusChanged(String provider, int status,Bundle extras){}
	});
	    
	      
		//sb.append("\n").append(provider2).append(": ");
		Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		if (location != null) {
			lat = location.getLatitude();
			lng = location.getLongitude();
			
			latstr= Double.toString(lat);		
			lngstr=Double.toString(lng);
			Toast.makeText(getApplicationContext(), latstr+ " "+lngstr, Toast.LENGTH_LONG).show();
		}
	    
	    setContentView(R.layout.activity_map);
	    	    
	    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.fragment1))
	            .getMap();
	        Marker hamburg = map.addMarker(new MarkerOptions().position(new LatLng(lat, lng))
	            .title("My Location"));
	        Marker kiel = map.addMarker(new MarkerOptions()
	            .position(new LatLng(lat, lng))
	            .title("Kiel")
	            .snippet("Kiel is cool")
	            .icon(BitmapDescriptorFactory
	                .fromResource(R.drawable.ic_launcher)));

	        // Move the camera instantly to hamburg with a zoom of 15.
	        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15));

	        // Zoom in, animating the camera.
	        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
	        
	        map.setOnMapLongClickListener(this);
	        map.setOnMapClickListener(this);
	       /* geocoder = new Geocoder(this);
	        
	        if(!Geocoder.isPresent())
	        {
	        	Toast.makeText(getApplicationContext(), "Sorry Geocoder service not Present", Toast.LENGTH_LONG).show();
	        }
	        */

	  }

	@Override
	public void onMapLongClick(LatLng point) {
		// TODO Auto-generated method stub
		
		
		
		    Intent nxt = new Intent(this, AndroidSQLiteActivity.class);
	        Bundle bn = new Bundle();
	        bn.putString("Latitude", latstr);
	        bn.putString("Longitude", lngstr);
	        nxt.putExtra("Bundle", bn);
	        startActivity(nxt);
	      
	}
	//This method is been used for setting the radius

	@Override
	public void onMapClick(LatLng point) {
		// TODO Auto-generated method stub
		//mapShape = map.addPolygon(ploy=draw.drawCircle(point,mapseekbar.getProgress()));
		//mapShape=map.addPolygon(ploy.add(point));
		mapShape=map.addPolygon(draw1.drawCircle(point, 30));
	}
}