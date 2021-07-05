package com.example.solution2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("movie/{type}?&language=ru")
    Call<ApiResponse> getPopularMovies(@Path("type") String type, @Query("api_key") String apiKey);
}
