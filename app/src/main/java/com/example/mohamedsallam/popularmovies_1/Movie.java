package com.example.mohamedsallam.popularmovies_1;

public class Movie {
    private String title;
    private String releaseData;
    private String descrption;
    private double voteAverage;
    private final String posterUrl;

    public Movie(String title, String posterUrl, String releaseData, double voteAverage, String descrption) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.releaseData = releaseData;
        this.voteAverage = voteAverage;
        this.descrption = descrption;
    }

    public Movie(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getTitle() {
        return this.title;
    }

    public String getReleaseData() {
        return this.releaseData;
    }

    public String getPosterUrl() {
        return this.posterUrl;
    }

    public String getDescrption() {
        return this.descrption;
    }

    public double getVoteAverage() {
        return this.voteAverage;
    }
}
