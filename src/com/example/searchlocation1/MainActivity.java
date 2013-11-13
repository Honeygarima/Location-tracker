package com.example.searchlocation1;

import com.example.searchlocation1.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	// flag for Internet connection status
		Boolean isInternetPresent = false;
		
		// Connection detector class
		ConnectionDetector cd;
		
	private Button addlocationbtn;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addlocationbtn=(Button)findViewById(R.id.addit);
		
		cd = new ConnectionDetector(getApplicationContext());
		
		addlocationbtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// get Internet status
				isInternetPresent = cd.isConnectingToInternet();

				// check for Internet status
				if (isInternetPresent) {
					// Internet Connection is Present
					// make HTTP requests
					showAlertDialog(MainActivity.this, "Internet Connection",
							"You have internet connection", true);
				} else {
					// Internet connection is not present
					// Ask user to connect to Internet
					showAlertDialog(MainActivity.this, "No Internet Connection",
							"You don't have internet connection.", false);
				}
				
				
				
			}
		});
		
		
		
	}

	public void showAlertDialog(Context context, String title, String message, Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);
		
		// Setting alert dialog icon
		alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);

		// Setting OK Button
		if(status)
		{
			//Toast.makeText(getApplicationContext(), "its working", Toast.LENGTH_LONG).show();
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
				Intent mapintent=new Intent(getApplicationContext(), Map.class);
				startActivity(mapintent);
			}
		});

		// Showing Alert Message
		alertDialog.show();
		}
		
		else
		{
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
				Toast.makeText(getApplicationContext(), "Switch ON ur INTERNET Connection", Toast.LENGTH_LONG).show();
			//	setContentView(R.layout.activity_main);
				
			}
		});

		// Showing Alert Message
		alertDialog.show();
		}
		

			
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
