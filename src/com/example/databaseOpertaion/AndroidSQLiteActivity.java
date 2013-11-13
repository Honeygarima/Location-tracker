package com.example.databaseOpertaion;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.searchlocation1.MainActivity;
import com.example.searchlocation1.R;

public class AndroidSQLiteActivity extends Activity {
	private EditText profilename;
	private TextView latitxt;
	private TextView lngtxt;
	private TextView addres;
	private Button okbtn;
	private  DataBaseHandler db;
	static CheckBox vibrt,silnt,remindr;
	static int status,silentstatus;
	private SeekBar pvolum;
	static AudioManager audo;
	private String profilestring;
	 static String latDouble;
	 static String lngdouble;
	 static String address;
	static String log = null;
	 
	 AudioManager audMangr;

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addlocation);
        
       
         
        db = new DataBaseHandler(this);
        profilename = (EditText)findViewById(R.id.editText1);
        okbtn=(Button)findViewById(R.id.OK);
       
        latitxt=(TextView)findViewById(R.id.lati);
         lngtxt=(TextView)findViewById(R.id.lng);
         vibrt=(CheckBox)findViewById(R.id.VibrateCB);
         silnt=(CheckBox)findViewById(R.id.SilentCB);
		remindr=(CheckBox)findViewById(R.id.ReminderCB);
		pvolum = (SeekBar)findViewById(R.id.seekBar1);
		addres = (TextView)findViewById(R.id.addr);
		
			Bundle b = getIntent().getBundleExtra("Bundle");
			latDouble = b.getString("Latitude");
			lngdouble = b.getString("Longitude");
			
	       


		latitxt.setText(latDouble);
		lngtxt.setText(lngdouble);
        
        /**
         * CRUD Operations
         * */
         
		vibrt.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean ischecked) {
				// TODO Auto-generated method stub
				Log.d("control", "incheckedchanged");
				
				if(ischecked)
				{
				
					Log.d("control", "inside if");
					silnt.setChecked(false);
					pvolum.setProgress(0);
					status=1;
					Toast.makeText(getBaseContext(),"vibrate"+status, Toast.LENGTH_LONG).show();
					
					//code to vibrate the phone
					audMangr= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
					//For Vibrate mode
					audMangr.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		
				}
				
				else
				{
					Log.d("control", "inside else");
					pvolum.setProgress(50);
					status=0;
					Toast.makeText(getBaseContext(),"vibrate"+status, Toast.LENGTH_LONG).show();
				}
			}
			});
		
		silnt.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
				
					Log.d("control", "inside if");
					vibrt.setChecked(false);
					pvolum.setProgress(0);
					silentstatus=1;
					Toast.makeText(getBaseContext(),"silent"+silentstatus, Toast.LENGTH_LONG).show();
					
					//code to vibrate the phone
					audMangr= (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
					//For Vibrate mode
					//For Silent mode
					audMangr.setRingerMode(AudioManager.RINGER_MODE_SILENT);
		
				}
				
				else
				{
					Log.d("control", "inside else");
					pvolum.setProgress(50);
					silentstatus=0;
					Toast.makeText(getBaseContext(),"vibrate"+silentstatus, Toast.LENGTH_LONG).show();
				}
			}
		});
         
		remindr.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				// TODO Auto-generated method stub
				if(isChecked)
				{
				
				Intent tormndr = new Intent(getApplicationContext(), RemindrMainActivity.class);
				startActivity(tormndr);
				}
			}
		});
         okbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
							
				
				profilestring=profilename.getText().toString();
		    	Log.d("abc", latDouble);
				
				
				
				db.addContact(new ProfileData(profilestring,latDouble,lngdouble,""));        
				// Reading all contacts
			    Log.d("Reading: ", "Reading all contacts.."); 
			    
			    List<ProfileData> cn=db.getAllContacts();
			  
			  for(ProfileData pd:cn)
			  {
				  
		            try {
						Log.d("in for loop", "in for loop");
						log = "Id: "+" ,Name: " + pd.getPname() + " ,Lat: " + pd.getLat() +
								
								" ,Long: " +pd.getLng()+"remenable:";
												//Log.d("Name: ", log);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            

			  }
			  Toast.makeText(getApplicationContext(), log, Toast.LENGTH_LONG).show();
			 
				Intent nxtin = new Intent(getApplicationContext(), MainActivity.class);
				startActivity(nxtin);
				
				
			}
			
		});
                 
        
}
   
    
}
  
         
					
				
				 
       
  
    
      
