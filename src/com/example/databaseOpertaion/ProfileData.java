package com.example.databaseOpertaion;

public class ProfileData {
	/*private int pid;
	private String pname;
	private double platitude;
	private double plongitude;
	private boolean pro_enable;
	private boolean rem_enable;
	private int pvolume;
	private int pcoverage_radius;
	private String preminder;
	private String pringtone;*/
	
	private String lat;
	private String lng;
	
	private String reminder;
	
	

	
	private int rem_enable;
	
	private int ring_enable;
	
	private int default_volume;
	private int viberate_enable;
	
	private int cid;
	
	
	
	
	private String pname;
	
	
	
	public String getPname() {
		return pname;
	}







	public void setPname(String pname) {
		this.pname = pname;
	}







	public String getLat() {
		return lat;
	}







	public void setLat(String lat) {
		this.lat = lat;
	}







	public String getLng() {
		return lng;
	}







	public void setLng(String lng) {
		this.lng = lng;
	}







	public String getReminder() {
		return reminder;
	}







	public void setReminder(String reminder) {
		this.reminder = reminder;
	}







	public int getRem_enable() {
		return rem_enable;
	}







	public void setRem_enable(int rem_enable) {
		this.rem_enable = rem_enable;
	}







	public int getRing_enable() {
		return ring_enable;
	}







	public void setRing_enable(int ring_enable) {
		this.ring_enable = ring_enable;
	}







	public int getDefault_volume() {
		return default_volume;
	}







	public void setDefault_volume(int default_volume) {
		this.default_volume = default_volume;
	}







	public int getViberate_enable() {
		return viberate_enable;
	}







	public void setViberate_enable(int viberate_enable) {
		this.viberate_enable = viberate_enable;
	}







	public int getCid() {
		return cid;
	}







	public void setCid(int cid) {
		this.cid = cid;
	}


	
	
	
	public ProfileData(String pname, String lat, String lng, String reminder/*,
			int rem_enable, int ring_enable, int default_volume,
			int viberate_enable*/) {
		super();
		this.pname = pname;
		this.lat = lat;
		this.lng = lng;
		this.reminder = reminder;
		this.rem_enable = rem_enable;
		this.ring_enable = ring_enable;
		this.default_volume = default_volume;
		this.viberate_enable = viberate_enable;
	}

	public ProfileData()
	{
		
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return pname;
	}
	

}
