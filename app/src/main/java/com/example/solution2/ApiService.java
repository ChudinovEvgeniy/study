package com.example.solution2;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("movie/popular?api_key=41f30fbbd3a76dee965d7e3be04c0f9d&language=ru")
    Call<ApiResponse> getPopularMovies();
}
