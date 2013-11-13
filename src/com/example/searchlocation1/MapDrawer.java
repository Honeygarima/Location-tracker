package com.example.searchlocation1;

import java.util.ArrayList;
import java.util.List;

import android.app.Notification.Style;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapDrawer {
	

private GoogleMap map1;
private static int earth_radius=6371000;




public LatLng getpoint(LatLng center,int radius,double angle)
{
	//get the coordinates of a circle point at a give angle
	
	double east=radius*Math.sin(angle);
	double north=radius*Math.sin(angle);
	
	
	double clat=center.latitude;
	double clng=center.longitude;
	double latradius=earth_radius *Math.cos(clat / 180*Math.PI);
	
	double newlat=clat+(north/earth_radius/Math.PI*180);
	  double newLng = clng + (east / latradius / Math.PI * 180);

	    return new LatLng(newlat, newLng);
	    
	}
public PolygonOptions drawCircle(LatLng center, int radius) {
    // Clear the map to remove the previous circle
    map1.clear();
    // Generate the points
    List<LatLng> points = new ArrayList<LatLng>();
    int totalPonts = 50; // number of corners of the pseudo-circle
    for (int i = 0; i < totalPonts; i++) {
        points.add(getpoint(center, radius, i*2*Math.PI/totalPonts));
    }
    // Create and return the polygon
    return (new PolygonOptions().addAll(points).geodesic(true).strokeWidth(2).strokeColor(Color.TRANSPARENT).
    		fillColor(Color.HSVToColor(100, new float[] {234, 1, 1})));

	
	
}



/*private Bitmap getBitmap() {

    // fill color
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint1.setColor(0x110000FF);
    paint1.setStyle();

    // stroke color
    Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
    paint2.setColor(0xFF0000FF);
    paint2.setStyle(Style.STROKE);

    // icon
    Bitmap icon = BitmapFactory.decodeResource(YourActivity.getResources(), R.drawable.blue);

    // circle radius - 200 meters
    int radius = offset = convertMetersToPixels(lat, lng, 200);

    // if zoom too small
    if (radius < icon.getWidth() / 2) {

        radius = icon.getWidth() / 2;
    }

    // create empty bitmap 
    Bitmap b = Bitmap.createBitmap(radius * 2, radius * 2, Config.ARGB_8888);
    Canvas c = new Canvas(b);

    // draw blue area if area > icon size
    if (radius != icon.getWidth() / 2) {

        c.drawCircle(radius, radius, radius, paint1);
        c.drawCircle(radius, radius, radius, paint2);
    }

    // draw icon
    c.drawBitmap(icon, radius - icon.getWidth() / 2, radius - icon.getHeight() / 2, new Paint());

    return b;
}

//4. calculate image offset:

private LatLng getCoords(double lat, double lng) {

    LatLng latLng = new LatLng(lat, lng);

    Projection proj = Map.getMap().getProjection();
    Point p = proj.toScreenLocation(latLng);
    p.set(p.x, p.y + offset);

    return proj.fromScreenLocation(p);
}

//5. draw:

    MarkerOptions options = new MarkerOptions();
        options.position(getCoords(lat, lng));
        options.icon(BitmapDescriptorFactory.fromBitmap(getBitmap()));

        marker = YourActivity.getMap().addMarker(options);
*/

}
