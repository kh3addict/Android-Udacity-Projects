package fdffd.com.tutorials.hp.movieapp;

/**
 * Created by family on 6/7/18.
 */

public class Movies {

    String mTitle;

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
    Movies(String title, String moviePoster,String plotSynposis, double voteAverage, String releaseDate){
        mTitle = title;
        mPoster = moviePoster;
        mPlotAnalysis = plotSynposis;
        mVoteAverage = voteAverage;
        mReleaseDate = releaseDate;

    }
}
