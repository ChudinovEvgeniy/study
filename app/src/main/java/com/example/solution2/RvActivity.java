package com.example.solution2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RvActivity extends AppCompatActivity {
    private static final String TAG = RvActivity.class.getSimpleName();

    private AppDatabase mMoviesDb;

    RecyclerView recyclerView;
    RvAdapter rvAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        mMoviesDb = Room.databaseBuilder(newBase, AppDatabase.class, "movie.Db").build();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvAdapter = new RvAdapter();
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setListener(item -> Toast.makeText(getApplicationContext(), item.overview, Toast.LENGTH_SHORT).show());
        getPopMovies();
    }

    public void getPopMovies() {
        String API_KEY = "41f30fbbd3a76dee965d7e3be04c0f9d";
        String TYPE = "popular";

        Call<ApiResponse> popularMovies = ApiClient.getMovies().getPopularMovies(TYPE, API_KEY);
        popularMovies.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                List<ApiResponse.ItemPopularMovies> popMoviesResponse;
                if (response.isSuccessful() && response.body() != null) {
                    mMoviesDb.moviesDao().clear();
                    Log.e(TAG, response.body().results.toString());
                    popMoviesResponse = response.body().results;
                    ArrayList<MoviesItemEntity> entities = new ArrayList<>();
                    for (ApiResponse.ItemPopularMovies item : popMoviesResponse) {
                        MoviesItemEntity entity = new MoviesItemEntity();
                        entity.title = item.title;
                        entity.rating = item.rating;
                        entity.date = item.date;
                        entity.image = item.image;
                        entity.overview = item.overview;
                        entities.add(entity);
                    }
                    mMoviesDb.moviesDao().append(entities);
                } else {
                    popMoviesResponse = new ArrayList<>();
                    Log.d(TAG, "failure");
                    for (MoviesItemEntity entity : mMoviesDb.moviesDao().getAll()) {
                        ApiResponse.ItemPopularMovies item = new ApiResponse.ItemPopularMovies();
                        item.title = entity.title;
                        item.rating = entity.rating;
                        item.date = entity.date;
                        item.image = entity.image;
                        item.overview = entity.overview;
                        popMoviesResponse.add(item);
                    }
                }
                runOnUiThread(() -> rvAdapter.setData(popMoviesResponse));
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
                List<ApiResponse.ItemPopularMovies> popMoviesResponse = new ArrayList<>();
                for (MoviesItemEntity entity : mMoviesDb.moviesDao().getAll()) {
                    ApiResponse.ItemPopularMovies item = new ApiResponse.ItemPopularMovies();
                    item.title = entity.title;
                    item.rating = entity.rating;
                    item.date = entity.date;
                    item.image = entity.image;
                    item.overview = entity.overview;
                    popMoviesResponse.add(item);
                }
                runOnUiThread(() -> rvAdapter.setData(popMoviesResponse));
            }
        });
    }
}