package fdffd.com.tutorials.hp.movieapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by family on 6/8/18.
 */



@Database(entities = Movie.class,version = 1)
public abstract class MyAppDatabase extends RoomDatabase {


    public abstract MyDao myDao();

}
