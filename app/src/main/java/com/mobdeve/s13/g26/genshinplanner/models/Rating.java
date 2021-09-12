package com.mobdeve.s13.g26.genshinplanner.models;

public class Rating {
    String userId;
    float rating;

    public Rating() {};

    public Rating(String userId, float rating) {
        this.userId = userId;
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
