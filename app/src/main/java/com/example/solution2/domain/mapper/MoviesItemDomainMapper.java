package com.example.solution2.domain.mapper;

import com.example.solution2.data.repositories.model.MoviesItemData;
import com.example.solution2.domain.model.MoviesItemDomain;

import java.util.ArrayList;
import java.util.List;

public class MoviesItemDomainMapper {
    public static List<MoviesItemDomain> create(List<MoviesItemData> source) {
        ArrayList<MoviesItemDomain> result = new ArrayList<>();
        for (MoviesItemData item : source) {
            result.add(create(item));
        }
        return result;
    }

    public static MoviesItemDomain create(MoviesItemData source) {
        MoviesItemDomain moviesItem = new MoviesItemDomain();
        moviesItem.title = source.title;
        moviesItem.image = source.image;
        moviesItem.rating = source.rating;
        moviesItem.date = source.date;
        moviesItem.overview = source.overview;
        moviesItem.id = source.id;
        moviesItem.total_pages = source.total_pages;
        return moviesItem;
    }
}
