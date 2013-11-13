package com.example.databaseOpertaion;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHandler extends SQLiteOpenHelper {
	
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    String ux;
 
    // Database Name
    private static final String DATABASE_NAME = "mapManager1.db";
 
    // Contacts table name
    private static final String TABLE_MAP = "maps1";
 
    // Contacts Table Columns names
    private static final String KEY_COLUMN_ID = "id";
    private static final String KEY_NAME = "pname";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";
    
   
    private static final String KEY_Reminder="rem";
  
   private String allcolumns[] = { KEY_COLUMN_ID,
		   KEY_NAME, KEY_LATITUDE, KEY_LONGITUDE,KEY_Reminder
		   
   };
 
 
		public DataBaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
				 String CREATE_MAP_TABLE = "CREATE TABLE "+TABLE_MAP+"("
						 + KEY_COLUMN_ID + " integer primary key autoincrement,"
						 + KEY_NAME + " varchar(30)," +
						 KEY_LATITUDE + " varchar(30),"+
			             KEY_LONGITUDE + " varchar(30)," +
					      KEY_Reminder + " varchar(300)"+");";
			        db.execSQL(CREATE_MAP_TABLE);

			
		}
		

		

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MAP);
		 
        // Create tables again
        onCreate(db);
		
	}
	
	public void addContact(ProfileData profiledata) {
	        SQLiteDatabase db = this.getWritableDatabase();
	        Log.d("datacontact", "datacontact working");
	 
	        ContentValues values = new ContentValues();
	        values.put(KEY_NAME, profiledata.getPname()); // Contact Name
	        values.put(KEY_LATITUDE, profiledata.getLat()); // Contact Phone
	        values.put(KEY_LONGITUDE, profiledata.getLng());
	        values.put(KEY_Reminder, profiledata.getReminder());
	     
	        
	        // Inserting Row
	        db.insert(TABLE_MAP, null, values);
	        db.close(); // Closing database connection
	    }
	 
	public List<ProfileData> getAllContacts() {
	    List<ProfileData> mapList = new ArrayList<ProfileData>();
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_MAP;
	     ux = "SELECT KEY_NAME FROM"+TABLE_MAP;
	    SQLiteDatabase db = this.getWritableDatabase();
	    //Cursor cursor = db.rawQuery(selectQuery, null);
	    Cursor cursor = db.query(TABLE_MAP, allcolumns	, null, null, null, null, null);
	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
	        do {
	        	ProfileData data = new ProfileData();
	        	data.setCid(Integer.parseInt(cursor.getString(0)));
	        	data.setPname(cursor.getString(1));
	        	data.setLat(cursor.getString(2));
	        	data.setLng(cursor.getString(3));
	        	data.setReminder(cursor.getString(4));
	        	/*data.setRem_enable(cursor.getInt(5));
	        	data.setRing_enable(cursor.getInt(6));
	        	data.setDefault_volume(cursor.getInt(7));
	        	data.setViberate_enable(cursor.getInt(8));*/
	        	
	            	            // Adding contact to list
	            mapList.add(data);
	        } while (cursor.moveToNext());
	    }
	 
	    // return contact list
	    return mapList;
	}
	
	  public int getContactsCount() {
	        String mapQuery = "SELECT  * FROM " + TABLE_MAP;
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor cursor = db.rawQuery(mapQuery, null);
	        cursor.close();
	 
	        // return count
	        return cursor.getCount();
	    }

	
}
