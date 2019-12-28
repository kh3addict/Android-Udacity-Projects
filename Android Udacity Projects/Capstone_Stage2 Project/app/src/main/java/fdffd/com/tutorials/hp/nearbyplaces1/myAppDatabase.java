package fdffd.com.tutorials.hp.nearbyplaces1;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;


@Database(entities = {searchPlaceAndNearbyPlaces.class}, version = 2)



public abstract class myAppDatabase extends RoomDatabase {

    private static myAppDatabase myAppDatabase1;

    static myAppDatabase getDatabase(final Context context){
        if (myAppDatabase1== null) {
            synchronized (myAppDatabase.class) {
                if (myAppDatabase1 == null) {
                    myAppDatabase1 = Room.databaseBuilder(context.getApplicationContext(),
                            myAppDatabase.class, "userdb").allowMainThreadQueries()
                            .build();

                }
            }
        }

        return myAppDatabase1;
    }


    public abstract MyDao myDao();


}
