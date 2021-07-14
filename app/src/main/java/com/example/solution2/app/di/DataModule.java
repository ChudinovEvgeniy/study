package com.example.solution2.app.di;

import android.content.Context;

import androidx.room.Room;

import com.example.solution2.data.local.AppDatabase;
import com.example.solution2.data.local.MoviesDao;
import com.example.solution2.data.remote.ApiClient;
import com.example.solution2.data.remote.ApiService;
import com.example.solution2.data.repositories.MoviesRepository;
import com.example.solution2.data.repositories.MoviesRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {
    @Singleton
    @Provides
    public ApiService getMovieApi() {
        return new ApiClient().getRetrofit().create(ApiService.class);
    }

    @Singleton
    @Provides
    public MoviesDao getMoviesDB(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "movie.db").build().moviesDao();
    }

    @Provides
    @Singleton
    public MoviesRepository getMoviesRepository(MoviesDao moviesDao, ApiService apiService) {
        return new MoviesRepositoryImpl(moviesDao, apiService);
    }
}
