package com.example.solution2;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "moviesDb")
public class MoviesItemEntity {

    @NonNull
    @PrimaryKey
    public String title;
    public String image;
    public String rating;
    public String date;
    public String overview;
}
