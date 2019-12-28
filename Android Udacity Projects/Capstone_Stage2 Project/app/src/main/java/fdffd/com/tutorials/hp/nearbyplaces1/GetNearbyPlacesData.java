package fdffd.com.tutorials.hp.nearbyplaces1;

/**
 * Created by family on 12/13/17.
 */

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Priyanka
 */

class GetNearbyPlacesData extends AsyncTask<Object, String, String> {

    private String googlePlacesData;
    private GoogleMap mMap;
    static public ArrayList<MarkerOptions> markerOptions1 = new ArrayList<MarkerOptions>();
    static public ArrayList<LatLng> latLngList = new ArrayList<LatLng>();
    String url;
    List<String> nearbyPlaces = new ArrayList<>();
    public static myAppDatabase myAppDatabase1;
    static Context mContext;

    GetNearbyPlacesData(Context context){
        mContext = context;

    }

    public GetNearbyPlacesData() {

    }


    @Override
    protected String doInBackground(Object... objects){
        mMap = (GoogleMap)objects[0];
        url = (String)objects[1];

        DownloadURL downloadURL = new DownloadURL();
        try {
            googlePlacesData = downloadURL.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googlePlacesData;
    }

    @Override
    protected void onPostExecute(String s){

        List<HashMap<String, String>> nearbyPlaceList;
        DataParser parser = new DataParser();
        nearbyPlaceList = parser.parse(s);
        Log.d("nearbyplacesdata","called parse method");
        showNearbyPlaces(nearbyPlaceList);
    }

    private void showNearbyPlaces(List<HashMap<String, String>> nearbyPlaceList)
    {

        myAppDatabase1 = Room.databaseBuilder(mContext,myAppDatabase.class,"userdb").fallbackToDestructiveMigration().allowMainThreadQueries().build();

       myAppDatabase1.myDao().deleteAll();

        searchPlaceAndNearbyPlaces place = new searchPlaceAndNearbyPlaces();
        for(int i = 0; i < nearbyPlaceList.size(); i++)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> googlePlace = nearbyPlaceList.get(i);

            String placeName = googlePlace.get("place_name");
            nearbyPlaces.add(placeName);
            String vicinity = googlePlace.get("vicinity");
            double lat = Double.parseDouble( googlePlace.get("lat"));
            double lng = Double.parseDouble( googlePlace.get("lng"));

            LatLng latLng = new LatLng( lat, lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName + " : "+ vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            markerOptions1.add(markerOptions);
            latLngList.add(latLng);


            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));

        }


        place.setNearbyPlace(nearbyPlaces);
        place.setPlace(MapsActivity.location);
        place.setFacility(MapsActivity.facility);
        myAppDatabase1.myDao().addSearchPlaceAndNearbyPlaces(place);



    }}
