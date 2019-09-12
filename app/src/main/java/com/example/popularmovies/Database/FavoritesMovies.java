package com.example.popularmovies.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class FavoritesMovies {


    @ColumnInfo(name = "MOVIE_TITLE")
    private String mTitle;
    private String mImage;

    @PrimaryKey
    @ColumnInfo(name = "MOVIE_ID")
    private int mId;

    @ColumnInfo(name = "MOVIE_RELEASE_DATE")
    private String mDate;

    public FavoritesMovies(String mTitle, String mImage, int mId, String mDate) {
        this.mTitle = mTitle;
        this.mImage = mImage;
        this.mId = mId;
        this.mDate = mDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}
