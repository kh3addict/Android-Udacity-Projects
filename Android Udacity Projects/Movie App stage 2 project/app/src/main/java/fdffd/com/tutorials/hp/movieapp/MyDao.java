package fdffd.com.tutorials.hp.movieapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by family on 6/8/18.
 */
@Dao
public interface MyDao {

    @Insert
    public void addMovie(Movie movie);

    @Query("select * from Movie")
    public List<Movie> getMovie();

     @Delete
    public void deleteMovie(Movie movie);







}
