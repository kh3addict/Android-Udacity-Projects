package fdffd.com.tutorials.hp.nearbyplaces1;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MyDao {
    @Insert
    public void addSearchPlaceAndNearbyPlaces(searchPlaceAndNearbyPlaces place);

    @Query("select * from searchPlaceAndNearbyPlaces")
    public LiveData<List<searchPlaceAndNearbyPlaces>> getplaceAndNearByPlaces();


    @Query("delete FROM searchPlaceAndNearbyPlaces")
    public void deleteAll();




}
