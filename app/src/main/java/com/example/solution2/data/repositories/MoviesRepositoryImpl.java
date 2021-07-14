package com.example.solution2.data.repositories;


import android.app.Application;
import android.util.Log;
import android.view.View;

import com.example.solution2.data.local.MoviesDao;
import com.example.solution2.data.local.model.MoviesItemEntity;
import com.example.solution2.data.remote.ApiService;
import com.example.solution2.data.remote.model.ApiDetailResponse;
import com.example.solution2.data.remote.model.ApiResponse;
import com.example.solution2.data.repositories.mapper.MoviesDetailDataMapper;
import com.example.solution2.data.repositories.mapper.MoviesItemDataMapper;
import com.example.solution2.data.repositories.mapper.MoviesItemEntityMapper;
import com.example.solution2.data.repositories.model.MoviesDetailData;
import com.example.solution2.data.repositories.model.MoviesItemData;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class MoviesRepositoryImpl implements MoviesRepository {
    private final MoviesDao mMoviesDao;
    private final ApiService mApiService;

    @Inject
    public MoviesRepositoryImpl(MoviesDao moviesDao, ApiService apiService) {
        mMoviesDao = moviesDao;
        mApiService = apiService;
    }

    @Override
    public List<MoviesItemData> getMovies() throws IOException {
        List<MoviesItemEntity> moviesDb = mMoviesDao.getAll();
        if (moviesDb.size() > 0) {
            return MoviesItemDataMapper.create(moviesDb);
        } else {
            return this.getPopularMovies();
        }
    }

    @Override
    public List<MoviesItemData> getPopularMovies() throws IOException {
        try {
            Response<ApiResponse> response = mApiService.getPopularMovies().execute();
            List<MoviesItemData> list = MoviesItemDataMapper.create((response.body()));
            List<MoviesItemEntity> dbList = MoviesItemEntityMapper.create(list);
            mMoviesDao.clear();
            mMoviesDao.append(dbList);
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public MoviesDetailData getDetailMovies(int id) throws IOException {
        try {
            Response<ApiDetailResponse> response = mApiService.getDetailMovie(id).execute();
            ApiDetailResponse apiDetailResponse = response.body();
            return MoviesDetailDataMapper.create(apiDetailResponse);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<MoviesItemData> getNowPlayingMovies() throws IOException {
        try {
            Response<ApiResponse> response = mApiService.getNowPlayingMovies().execute();
            ApiResponse apiResponse = response.body();
            return MoviesItemDataMapper.create(apiResponse);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<MoviesItemData> getUpcomingMovies() throws IOException {
        try {
            Response<ApiResponse> response = mApiService.getUpcomingMovies().execute();
            ApiResponse apiResponse = response.body();
            return MoviesItemDataMapper.create(apiResponse);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<MoviesItemData> getSearchMovies(String textSearch, int pageId) throws IOException {
        try {
            Response<ApiResponse> response = mApiService.getSearchMovies(textSearch,pageId).execute();
            Log.e("SEARCH", response.body().results.toString());
            ApiResponse apiResponse = response.body();
            return MoviesItemDataMapper.create(apiResponse);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
