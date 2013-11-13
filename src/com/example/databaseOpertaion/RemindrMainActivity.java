package com.example.databaseOpertaion;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.searchlocation1.R;

public class RemindrMainActivity extends Activity {
	
	static EditText rmndr;
	private Button savebtn;
	String remndrtxt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remindr_main);
		rmndr = (EditText)findViewById(R.id.setRemndr);
		savebtn = (Button)findViewById(R.id.save);
		savebtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			
				remndrtxt = rmndr.getText().toString();
				Toast.makeText(getApplicationContext(), remndrtxt, Toast.LENGTH_LONG).show();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.remindr_main, menu);
		return true;
	}

}
