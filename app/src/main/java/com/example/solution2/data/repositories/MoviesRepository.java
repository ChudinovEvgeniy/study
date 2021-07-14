package com.example.solution2.data.repositories;

import com.example.solution2.data.repositories.model.MoviesDetailData;
import com.example.solution2.data.repositories.model.MoviesItemData;

import java.io.IOException;
import java.util.List;

public interface MoviesRepository {
    List<MoviesItemData> getMovies() throws IOException;
    List<MoviesItemData> getPopularMovies() throws IOException;
    List<MoviesItemData> getNowPlayingMovies() throws IOException;
    List<MoviesItemData> getUpcomingMovies() throws IOException;
    List<MoviesItemData> getSearchMovies(String textSearch, int pageId) throws IOException;
    MoviesDetailData getDetailMovies(int id) throws IOException;
}
