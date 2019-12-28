package fdffd.com.tutorials.hp.nearbyplaces1;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.List;

@Entity
public class searchPlaceAndNearbyPlaces {



    @PrimaryKey @NonNull
    private String place;


    @NonNull
    private String facility;




    @TypeConverters(fdffd.com.tutorials.hp.nearbyplaces1.TypeConverter.class)
    private List<String> nearbyPlace;


    @NonNull
    public String getFacility() {
        return facility;
    }

    public void setFacility(@NonNull String facility) {
        this.facility = facility;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public List<String> getNearbyPlace() {
        return nearbyPlace;
    }

    public void setNearbyPlace(List<String> nearbyPlace) {
        this.nearbyPlace = nearbyPlace;
    }
}
