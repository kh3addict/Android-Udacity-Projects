package fdffd.com.tutorials.hp.movieapp;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by family on 6/8/18.
 */
@Entity
public class Movie {
@PrimaryKey
 private int id;

 private String name;


 boolean isInFavorites1 = false;
 boolean isInUnfavorites1 = false;


    public boolean isInUnfavorites1() {
        return isInUnfavorites1;
    }

    public void setInUnfavorites1(boolean inUnfavorites1) {
        isInUnfavorites1 = inUnfavorites1;
    }

    public boolean isInFavorites() {
        return isInFavorites1;
    }

    public void setInFavorites(boolean inFavorites) {
        isInFavorites1 = inFavorites;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





}
