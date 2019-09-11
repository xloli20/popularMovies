package com.example.popularmovies.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Trailers implements Parcelable {
    public static final Creator<Trailers> CREATOR = new Creator<Trailers>() {
        @Override
        public Trailers createFromParcel(Parcel in) {
            return new Trailers(in);
        }

        @Override
        public Trailers[] newArray(int size) {
            return new Trailers[size];
        }
    };
    String tKey;
    String name;

    public Trailers(String tKey, String name) {
        this.tKey = tKey;
        this.name = name;
    }

    public Trailers(String tKey) {
        this.tKey = tKey;
    }

    protected Trailers(Parcel in) {
        tKey = in.readString();
        name = in.readString();
    }

    public String gettKey() {
        return tKey;
    }

    public void settKey(String tKey) {
        this.tKey = tKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tKey);
        parcel.writeString(name);
    }
}
