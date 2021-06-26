package com.example.solution2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/popular?&language=ru")
    Call<ApiResponse> getPopularMovies(@Query("api_key") String apiKey);
}
