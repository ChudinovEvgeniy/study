package com.example.solution2.data.remote.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ApiDetailResponse {
    @SerializedName("title")
    public String title;
    @SerializedName("poster_path")
    public String image;
    @SerializedName("vote_average")
    public String rating;
    @SerializedName("release_date")
    public String date;
    @SerializedName("overview")
    public String overview;

    @NonNull
    @Override
    public String toString() {
        return "UserResponse{" +
                ", title='" + title + '\'' +
                ", poster_path='" + image + '\'' +
                ", vote_average='" + rating + '\'' +
                ", release_date='" + date + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}