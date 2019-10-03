package com.example.popularmovies.Models;


import android.os.Parcel;
import android.os.Parcelable;

public class Reviews implements Parcelable {

    private String rAuthor;
    private String rContent;

    public Reviews(String rAuthor, String rContent) {
        this.rAuthor = rAuthor;
        this.rContent = rContent;
    }

    public static final Creator<Reviews> CREATOR = new Creator<Reviews>() {
        @Override
        public Reviews createFromParcel(Parcel in) {
            return new Reviews(in);
        }

        @Override
        public Reviews[] newArray(int size) {
            return new Reviews[size];
        }
    };

    protected Reviews(Parcel in) {
        rAuthor = in.readString();
        rContent = in.readString();
    }

    public String getrAuthor() {
        return rAuthor;
    }

    public void setrAuthor(String rAuthor) {
        this.rAuthor = rAuthor;
    }

    public String getrContent() {
        return rContent;
    }

    public void setrContent(String rContent) {
        this.rContent = rContent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(rAuthor);
        parcel.writeString(rContent);
    }
}
