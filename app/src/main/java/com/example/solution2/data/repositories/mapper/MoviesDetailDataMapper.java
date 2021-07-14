package com.example.solution2.data.repositories.mapper;

import com.example.solution2.data.remote.model.ApiDetailResponse;
import com.example.solution2.data.repositories.model.MoviesDetailData;

public class MoviesDetailDataMapper {
    public static MoviesDetailData create(ApiDetailResponse source) {
        MoviesDetailData moviesDetailData = new MoviesDetailData();
        moviesDetailData.title = source.title;
        moviesDetailData.image = source.image;
        moviesDetailData.date = source.date;
        moviesDetailData.rating = source.rating;
        moviesDetailData.overview = source.overview;
        return moviesDetailData;
    }
}
