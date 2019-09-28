package com.example.popularmovies.Models;

public class Trailers {

    private String tKey;
    private String name;

    public Trailers(String tKey) {
        this.tKey = tKey;
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

}
