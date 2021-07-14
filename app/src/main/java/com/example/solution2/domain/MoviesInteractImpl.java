package com.example.solution2.domain;


import com.example.solution2.data.repositories.MoviesRepository;
import com.example.solution2.data.repositories.model.MoviesDetailData;
import com.example.solution2.data.repositories.model.MoviesItemData;
import com.example.solution2.domain.mapper.MoviesDetailDomainMapper;
import com.example.solution2.domain.mapper.MoviesItemDomainMapper;
import com.example.solution2.domain.model.MoviesDetailDomain;
import com.example.solution2.domain.model.MoviesItemDomain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MoviesInteractImpl implements MoviesInteract {
    private final MoviesRepository mMoviesRepository;

    @Inject
    public MoviesInteractImpl(MoviesRepository moviesRepository) {
        mMoviesRepository = moviesRepository;
    }

    @Override
    public void getData(GetDataCallback getDataCallback) {
        new Thread() {
            @Override
            public void run() {
                List<MoviesItemDomain> list;
                try {
                    List<MoviesItemData> movies = mMoviesRepository.getMovies();
                    list = MoviesItemDomainMapper.create(movies);
                } catch (IOException e) {
                    e.printStackTrace();
                    list = new ArrayList<>();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    list = new ArrayList<>();
                }
                getDataCallback.onFinish(list);
            }
        }.start();
    }

    @Override
    public void getPopularData(GetDataCallback getDataCallback) {
        new Thread() {
            @Override
            public void run() {
                List<MoviesItemDomain> list;
                try {
                    List<MoviesItemData> movies = mMoviesRepository.getPopularMovies();
                    list = MoviesItemDomainMapper.create(movies);
                } catch (IOException e) {
                    e.printStackTrace();
                    list = new ArrayList<>();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    list = new ArrayList<>();
                }
                getDataCallback.onFinish(list);
            }
        }.start();
    }

    @Override
    public void getNowPlayingData(GetDataCallback getDataCallback) {
        new Thread(){
            @Override
            public void run() {
                List<MoviesItemDomain> list;
                try {
                    List<MoviesItemData> movies = mMoviesRepository.getNowPlayingMovies();
                    list = MoviesItemDomainMapper.create(movies);
                } catch (IOException e) {
                    e.printStackTrace();
                    list = null;
                }
                getDataCallback.onFinish(list);
            }
        }.start();
    }

    @Override
    public void getUpcomingData(GetDataCallback getDataCallback) {
        new Thread(){
            @Override
            public void run() {
                List<MoviesItemDomain> list;
                try {
                    List<MoviesItemData> movies = mMoviesRepository.getUpcomingMovies();
                    list = MoviesItemDomainMapper.create(movies);
                } catch (IOException e) {
                    e.printStackTrace();
                    list = null;
                }
                getDataCallback.onFinish(list);
            }
        }.start();
    }

    @Override
    public void getSearchData(String searchText, int pageId, GetDataCallback getDataCallback) {
        new Thread(){
            @Override
            public void run() {
                List<MoviesItemDomain> list;
                try {
                    List<MoviesItemData> movies = mMoviesRepository.getSearchMovies(searchText, pageId);
                    list = MoviesItemDomainMapper.create(movies);
                } catch (IOException e) {
                    e.printStackTrace();
                    list = null;
                }
                getDataCallback.onFinish(list);
            }
        }.start();
    }

    @Override
    public void getDetailData(int id, GetDetailCallback getDetailCallback) {
        new Thread(){
            @Override
            public void run() {
                MoviesDetailDomain moviesDetailDomain;
                try {
                    MoviesDetailData moviesDetailData = mMoviesRepository.getDetailMovies(id);
                    moviesDetailDomain = MoviesDetailDomainMapper.create(moviesDetailData);
                } catch (IOException e) {
                    e.printStackTrace();
                    moviesDetailDomain = null;
                }
                getDetailCallback.onFinish(moviesDetailDomain);
            }
        }.start();
    }
}
