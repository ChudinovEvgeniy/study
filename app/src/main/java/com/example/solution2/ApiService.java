package com.example.solution2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular?&language=ru")
    Call<ApiResponse> getPopularMovies();
    @GET("movie/now_playing?&language=ru")
    Call<ApiResponse> getNowPlayingMovies();
    @GET("movie/upcoming?&language=ru&region=Ru")
    Call<ApiResponse> getUpcomingMovies();
    @GET("search/multi?&language=ru")
    Call<ApiResponse> getSearchMovies(@Query("query") String search, @Query("page") int page);
    @GET("movie/{id}?&language=ru")
    Call<ApiDetailResponse> getDetailMovie(@Path("id") int id);
}
