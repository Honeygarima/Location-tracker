package com.example.searchlocation1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Checkboxcode extends Activity {
	private CheckBox obj,obj2,obj3;
	
	
	private boolean status;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.addlocation);
		obj=(CheckBox)findViewById(R.id.VibrateCB);
		obj.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean ischecked) {
				// TODO Auto-generated method stub
				
				if(ischecked)
				{
					status=true;
				}
				
				else
				{
					status=false;
				}
			}
		});
		
		
		obj2=(CheckBox)findViewById(R.id.SilentCB);
		obj2.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				
				if(isChecked)
				{
					status=true;
				}
				
			
				else
				{
					status=false;
				}
			}
		});
		
		obj3=(CheckBox)findViewById(R.id.ReminderCB);
		obj3.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				
				if(isChecked)
				{
					status=true;
				}
				else
				{
					status=false;
				}
					
				
			}
		});
				
				
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.checkboxcode, menu);
		return true;
	}

}
