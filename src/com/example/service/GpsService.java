package com.example.service;

import java.util.List;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.example.searchlocation1.MainActivity;
import com.example.searchlocation1.R;
import com.example.databaseOpertaion.DataBaseHandler;
import com.example.databaseOpertaion.ProfileData;
import com.google.android.gms.maps.model.LatLng;

public class GpsService extends Service {
	
static NotificationManager mNotificationManager;
	
	private static int NOTIFICATIONS_ID = R.layout.activity_main;
	
	private static AudioManager mode;
	static DataBaseHandler datasource;
	private static List<ProfileData> value;
	LocationManager locationManager;
	LocationListener locationListener;
	Location gpsLocation = null;
	Location networkLocation = null;
	
	static SharedPreferences defaultsettings;
	
	static int defmode;
	static int dervol;
	static int dertime;
	static String defuri;
	static int defaultid = Integer.MAX_VALUE;
	int currentPid;
	
	boolean defaultactive;
	
	private int activepro;
	private int[] activepro_ID;
	static LatLng currentloc;
	
	Handler gPSproservicehand;
	private static  Intent resultIntent;
	private static TaskStackBuilder stackBuilder;
	private static PendingIntent resultPendingIntent;

	private SharedPreferences.Editor editor;
	
	public GpsService()
	{
		
	}
	
	
	@Override
	public void onDestroy() {
		
		locationManager.removeUpdates(locationListener);
		mode.setRingerMode(defmode);
		shownotification("Stopped...Swiched to Default profile.", false);
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stubgPSproservicehand=new Handler();
		gPSproservicehand=new Handler();
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		resultIntent = new Intent(this, MainActivity.class);//move to main class
		stackBuilder = TaskStackBuilder.create(this);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(MainActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		mode = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);
		setupservice();
		return super.onStartCommand(intent, flags, startId);
	}




	private void setupservice() {
		// TODO Auto-generated method stub
		datasource=new DataBaseHandler(this);
		datasource.addContact(new ProfileData());
		value = datasource.getAllContacts();
	
		if (value.size() <= 0) {
			stopSelf();// database empty.
		}
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new Gpsloclin();
		// gPSproservicehand=new Handler();
		defaultsettings = PreferenceManager.getDefaultSharedPreferences(this);
		defmode = defaultsettings.getInt("mode",
				AudioManager.RINGER_MODE_NORMAL);
		dervol = defaultsettings.getInt("volu", 70);
		dertime=Integer.parseInt(defaultsettings.getString("time_intervals","5"))*60*1000;
		defuri = defaultsettings.getString("ring", RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_RINGTONE).toString());
		currentPid = defaultsettings.getInt("current", Integer.MAX_VALUE);
		defaultactive = defaultsettings.getBoolean("defaultactive", false);
		/*notistr = defaultsettings.getString("note", "Touch to configure");

		shownotification(notistr, true);
*/		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, dertime, 0,
					locationListener, getMainLooper());
		if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, dertime, 0,
					locationListener, getMainLooper());

		
	}


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}





class Gpsloclin implements LocationListener
{

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		 activepro = 0;
		
		if (location.getProvider().equals(LocationManager.GPS_PROVIDER)) {
			gpsLocation = location;
			} else {
			networkLocation = location;
			
		}
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	
}
private void locationupdated(Location location) {
	
	value = datasource.getAllContacts();
	activepro_ID = new int[value.size()];
	
	if (activepro == 0) {
		stopSelf();// No active profile.

		Toast.makeText(GpsService.this, "No active profile.",
				Toast.LENGTH_SHORT).show();

	}

	verifyandrespond(location);

}

private void verifyandrespond(Location location) {
	// TODO Auto-generated method stub

	currentloc = new LatLng(location.getLatitude(), location.getLongitude());

	LatLng savedloc = null;
	

	for (int i = 0; i < activepro; i++) {

	//	savedloc = new LatLng(((ProfileData) value).getLat(),
//				((ProfileData) value).getLng());

		

			currentPid = value.get(activepro_ID[i]).getCid();
			writedefaultpref(currentPid, null);
			processrequest(activepro_ID[i]);defaultactive = false;
			break;// found first match. Jump out of for loop
		// no match found.
		//defaultactive = true;// if current location is not under any
								// profile.
	}// for loop end.
	if (defaultactive) {
		if (currentPid != defaultid) {

			mode.setRingerMode(defmode);
					}
		currentPid = defaultid;
		//writedefaultpref(currentPid, notistr);
	}
}

	protected void writedefaultpref(int pid, String strmsg) {
		// TODO Auto-generated method
		editor = defaultsettings.edit();
		if (pid != 0)
			editor.putInt("current", pid);
		if (strmsg != null)
			editor.putString("note", strmsg);
		editor.commit();

	}

	private void processrequest(int cid) {
		// TODO Auto-generated method stubLog.e("GPSproservice.processrequest",
		// mode.getStreamMaxVolume(AudioManager.STREAM_RING)+"");mode =
		// (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);


		if (value.get(cid).getDefault_volume() == 0) {
			
			mode.setRingerMode(AudioManager.RINGER_MODE_SILENT);
			//notistr = values.get(pid).getPname()+ "-ACTIVE- (Silent mode)";
			
		} else if (value.get(cid).getDefault_volume() == -1) {
			
			//notistr = values.get(pid).getPname()+ "-ACTIVE- (Vibrate mode)";
			mode.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
			
		} else {
			
			
			mode.setStreamVolume(AudioManager.STREAM_RING,
			value.get(cid).getDefault_volume(),
					AudioManager.FLAG_PLAY_SOUND);
	
		}
}
	
	private void shownotification(String msg, boolean ongoing) {
		// TODO Auto-generated method stub
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
				.setSmallIcon(R.drawable.ic_stat_name)
				.setContentTitle("GPSProfiler..").setTicker(msg)
				.setOngoing(ongoing).setContentText(msg);
		// Creates an explicit intent for an Activity in your appGPSpro_main
		
		mBuilder.setContentIntent(resultPendingIntent);
		// mId allows you to update the notification later on.
		try {
			mNotificationManager.notify(NOTIFICATIONS_ID, mBuilder.build());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}