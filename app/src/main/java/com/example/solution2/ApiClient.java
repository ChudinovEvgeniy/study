package com.example.solution2;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new IntEx())
            .build();

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static ApiService getMovies() {
        return getRetrofit().create(ApiService.class);
    }
}
