package com.example.solution2.data.local.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "movies")
public class MoviesItemEntity {

    @NonNull
    @PrimaryKey
    public String title;
    public String image;
    public String rating;
    public String date;
    public String overview;
    public int id;
    public int total_pages;
}
