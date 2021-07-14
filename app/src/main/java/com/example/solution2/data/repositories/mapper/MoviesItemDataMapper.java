package com.example.solution2.data.repositories.mapper;

import com.example.solution2.data.local.model.MoviesItemEntity;
import com.example.solution2.data.remote.model.ApiResponse;
import com.example.solution2.data.repositories.model.MoviesItemData;

import java.util.ArrayList;
import java.util.List;

public class MoviesItemDataMapper {
    public static List<MoviesItemData> create(List<MoviesItemEntity> source) {
        ArrayList<MoviesItemData> result = new ArrayList<>();
        for (MoviesItemEntity entity : source) {
            MoviesItemData moviesItemData = new MoviesItemData();
            moviesItemData.title = entity.title;
            moviesItemData.image = entity.image;
            moviesItemData.date = entity.date;
            moviesItemData.rating = entity.rating;
            moviesItemData.overview = entity.overview;
            moviesItemData.id = entity.id;
            moviesItemData.total_pages = entity.total_pages;
            result.add(moviesItemData);
        }
        return result;
    }

    public static List<MoviesItemData> create(ApiResponse source) {
        ArrayList<MoviesItemData> result = new ArrayList<>();
        for (ApiResponse.ItemMovies item : source.results) {
            MoviesItemData moviesItemData = new MoviesItemData();
            moviesItemData.title = item.title;
            moviesItemData.image = item.image;
            moviesItemData.date = item.date;
            moviesItemData.rating = item.rating;
            moviesItemData.overview = item.overview;
            moviesItemData.id = item.id;
            moviesItemData.total_pages = source.total_pages;
            result.add(moviesItemData);
        }
        return result;
    }
}
