package com.example.popularmovies.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Movies implements Parcelable {

    private static final String TAG = Movies.class.getSimpleName();
    private int id;
    private String mTitle;
    private String mImage;
    private String mPlot;
    private Double mRating;
    private String mDate;

    public Movies(String mTitle, String mImage, String mPlot, Double mRating, String mDate, int id) {
        this.mTitle = mTitle;
        this.mImage = mImage;
        this.mPlot = mPlot;
        this.mRating = mRating;
        this.mDate = mDate;
        this.id = id;
    }

    public Movies() {
    }


    public static final Parcelable.Creator<Movies> CREATOR = new Parcelable.Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel in) {
            return new Movies(in);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[0];
        }
    };

    public Movies(Parcel in) {
        mTitle = in.readString();
        mImage = in.readString();
        mPlot = in.readString();
        if (in.readByte() == 0) {
            mRating = null;
        } else {
            mRating = in.readDouble();
        }
        mDate = in.readString();
        id = in.readInt();
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmImage() {
        Log.d(TAG, "getmImage: http://image.tmdb.org/t/p/w185/" + mImage);

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return hashCode();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mTitle);
        parcel.writeString(mImage);
        parcel.writeString(mPlot);
        if (mRating == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(mRating);
        }
        parcel.writeString(mDate);
        parcel.writeInt(id);
    }
}
