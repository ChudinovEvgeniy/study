package com.example.solution2.data.remote.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ApiResponse {
    @SerializedName("results")
    public List<ItemMovies> results;
    @SerializedName("total_pages")
    public int total_pages;

    public static class ItemMovies {
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
        @SerializedName("id")
        public int id;

        @NonNull
        @Override
        public String toString() {
            return "UserResponse{" +
                    ", title='" + title + '\'' +
                    ", poster_path='" + image + '\'' +
                    ", vote_average='" + rating + '\'' +
                    ", release_date='" + date + '\'' +
                    ", overview='" + overview + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    public static String reverseDate(String dateRelease) {
        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd-MM-yyyy";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.US);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.US);

        Date date;
        String str = null;

        try {
            date = inputFormat.parse(dateRelease);
            if (date != null) {
                str = outputFormat.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}