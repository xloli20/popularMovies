package com.example.popularmovies.Models;


public class Reviews {

    private String rAuthor;
    private String rContent;

    public Reviews(String rAuthor, String rContent) {
        this.rAuthor = rAuthor;
        this.rContent = rContent;
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

}
