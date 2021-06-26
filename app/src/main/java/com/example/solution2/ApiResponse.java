package com.example.solution2;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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