package com.example.solution2;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("results")
    public List<ItemPopularMovies> results;

    static class ItemPopularMovies {
        @SerializedName("title")
        public String title;
        @SerializedName("poster_path")
        public String image;
        @SerializedName("vote_average")
        public String rating;
        @SerializedName("release_date")
        public String date;
    }
}