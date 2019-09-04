package com.example.popularmovies;

public class Movies {

    private String mTitle;
    private String mImage;
    private String mPlot;
    private Double mRating;
    private String mDate;

    public Movies(String mTitle, String mImage, String mPlot, Double mRating, String mDate) {
        this.mTitle = mTitle;
        this.mImage = mImage;
        this.mPlot = mPlot;
        this.mRating = mRating;
        this.mDate = mDate;
    }

    public Movies() {
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmImage() {
        return "http://image.tmdb.org/t/p/w185/" + mImage;
    }

    public String getmPlot() {
        return mPlot;
    }

    public Double getmRating() {
        return mRating;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public void setmPlot(String mPlot) {
        this.mPlot = mPlot;
    }

    public void setmRating(Double mRating) {
        this.mRating = mRating;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}
