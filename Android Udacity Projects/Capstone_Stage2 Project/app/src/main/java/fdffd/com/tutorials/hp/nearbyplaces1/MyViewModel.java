package fdffd.com.tutorials.hp.nearbyplaces1;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.support.annotation.NonNull;

import java.util.List;



public class MyViewModel extends AndroidViewModel {

    static public LiveData<List<searchPlaceAndNearbyPlaces>> list;

    static myAppDatabase myAppDatabase1;


    public MyViewModel(@NonNull Application application) {

        super(application);

        myAppDatabase1 = Room.databaseBuilder(application.getApplicationContext(),myAppDatabase.class,"userdb").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        list = myAppDatabase1.myDao().getplaceAndNearByPlaces();






    }
    public LiveData<List<searchPlaceAndNearbyPlaces>> getCurrentData(){
        return list;
    }
}
