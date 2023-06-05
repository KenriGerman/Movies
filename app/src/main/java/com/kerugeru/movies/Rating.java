package com.kerugeru.movies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("kp")
    private double kp;

    public double getKinopoiskRating() {
        return kp;
    }

    public Rating(double kp) {
        this.kp = kp;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "kp='" + kp + '\'' +
                '}';
    }
}
