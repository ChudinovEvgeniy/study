package com.example.solution2.data.repositories.mapper;


import com.example.solution2.data.local.model.MoviesItemEntity;
import com.example.solution2.data.repositories.model.MoviesItemData;

import java.util.ArrayList;
import java.util.List;

public class MoviesItemEntityMapper {
    public static List<MoviesItemEntity> create(List<MoviesItemData> source) {
        ArrayList<MoviesItemEntity> result = new ArrayList<>();
        for (MoviesItemData moviesItem : source) {
            MoviesItemEntity entity = new MoviesItemEntity();
            entity.title = moviesItem.title;
            entity.image = moviesItem.image;
            entity.date = moviesItem.date;
            entity.rating = moviesItem.rating;
            entity.overview = moviesItem.overview;
            entity.total_pages = moviesItem.total_pages;
            result.add(entity);
        }
        return result;
    }
}
