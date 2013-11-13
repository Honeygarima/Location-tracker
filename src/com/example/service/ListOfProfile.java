package com.example.service;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import com.example.searchlocation1.MainActivity;

public class ListOfProfile extends BaseAdapter {
	
	static String profile[];
	private EditText profl;
	Context ct;
	public ListOfProfile(Context ctxt)
	{
		ct = ctxt;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return profile.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		
		/*View vw = null ;
		
		
		LayoutInflater infl = (LayoutInflater) ct.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
		vw = infl.inflate(R.layout.next, null);
	
	
	TextView txt1 = (TextView)vw.findViewById(R.id.textView1);
	txt1.setText(name[arg0]);
	txt1 = (TextView)vw.findViewById(R.id.textView2);
	txt1.setText(name2[arg0]);
	txt1 = (TextView)vw.findViewById(R.id.textView3);
	txt1.setText(name3[arg0]);*/


		View view = null;
		/*LayoutInflater infltr = (LayoutInflater)ct.getSystemService(ct.LAYOUT_INFLATER_SERVICE);
		view = infltr.inflate(R.layout., null);
		*/
		return view;
	}

}
