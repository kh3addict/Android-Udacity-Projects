package fdffd.com.tutorials.hp.movieapp;

import java.util.List;

/**
 * Created by family on 6/7/18.
 */

public class Movies {

    String mTitle;
     boolean inFavorites;
    String youtubeUrl;
    String reviews;
    List<String[]> list2;
    List<String[]>list1;
    boolean isInPopular = false;
    boolean isInTopRated= false;

    public boolean isInTopRated() {
        return isInTopRated;
    }

    public void setInTopRated(boolean inTopRated) {
        isInTopRated = inTopRated;
    }

    public boolean isInPopular() {
        return isInPopular;
    }

    public void setInPopular(boolean inPopular) {
        isInPopular = inPopular;
    }



    public static boolean isFinishedExecuting() {
        return finishedExecuting;
    }

    public static void setFinishedExecuting(boolean finishedExecuting) {
        Movies.finishedExecuting = finishedExecuting;
    }

    static boolean finishedExecuting;


     public boolean isInFavorites() {
        return inFavorites;
    }

     public void setInFavorites(boolean inFavorites) {
        this.inFavorites = inFavorites;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmPoster() {
        return mPoster;
    }

    public void setmPoster(String mPoster) {
        this.mPoster = mPoster;
    }

    public String getmPlotAnalysis() {
        return mPlotAnalysis;
    }

    public void setmPlotAnalysis(String mPlotAnalysis) {
        this.mPlotAnalysis = mPlotAnalysis;
    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    String mPoster;
    String mPlotAnalysis;
    double mVoteAverage;
    String mReleaseDate;
    static int [] id;
    int id1;


    public static int[] getId() {
        return id;
    }

    public static void setId(int[] id) {
        Movies.id = id;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }


    public Movies(int [] id1){
        id = id1;
    }

    public Movies(String mTitle, String mPoster, String mPlotAnalysis, double mVoteAverage, String mReleaseDate) {
        this.mTitle = mTitle;
        this.mPoster = mPoster;
        this.mPlotAnalysis = mPlotAnalysis;
        this.mVoteAverage = mVoteAverage;
        this.mReleaseDate = mReleaseDate;
    }

    public Movies(String title, String moviePoster, String plotSynposis, double voteAverage, String releaseDate, int [] id1, List<String[]> youtubeUrl, List<String[]> review){
        mTitle = title;
        mPoster = moviePoster;
        mPlotAnalysis = plotSynposis;
        mVoteAverage = voteAverage;
        mReleaseDate = releaseDate;
        id = id1;
        list1 = youtubeUrl;
        list2 = review;
    }


    public Movies(String title, String moviePoster, String plotSynposis, double voteAverage, String releaseDate, int  id1, List<String[]> youtubeUrl, List<String[]> review){
        mTitle = title;
        mPoster = moviePoster;
        mPlotAnalysis = plotSynposis;
        mVoteAverage = voteAverage;
        mReleaseDate = releaseDate;
        this.id1= id1;
        list1 = youtubeUrl;
        list2 = review;
    }
}
