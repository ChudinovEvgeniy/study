package com.example.solution2.domain.mapper;

import com.example.solution2.data.repositories.model.MoviesDetailData;
import com.example.solution2.domain.model.MoviesDetailDomain;

public class MoviesDetailDomainMapper {
    public static MoviesDetailDomain create(MoviesDetailData source) {
        MoviesDetailDomain moviesDetailData = new MoviesDetailDomain();
        moviesDetailData.title = source.title;
        moviesDetailData.image = source.image;
        moviesDetailData.date = source.date;
        moviesDetailData.rating = source.rating;
        moviesDetailData.overview = source.overview;
        return moviesDetailData;
    }
}
