package com.example.solution2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NowPlayingActivity extends AppCompatActivity {
    private static final String TAG = NowPlayingActivity.class.getSimpleName();

    RecyclerView recyclerView;
    RvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        recyclerView = findViewById(R.id.rvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvAdapter = new RvAdapter();
        rvAdapter.setListener(item -> {
            Intent intent = new Intent(NowPlayingActivity.this, DetailActivity.class);
            intent.putExtra((DetailActivity.EXTRA_NUMBER), item.id);
            startActivity(intent);
        });
        getNowPlayingMovies();
    }

    public void getNowPlayingMovies() {
        Call<ApiResponse> popularMovies = ApiClient.getRetrofit().getNowPlayingMovies();
        popularMovies.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    List<ApiResponse.ItemMovies> popMoviesResponse = null;
                    if (response.body() != null) {
                        popMoviesResponse = response.body().results;
                    }
                    rvAdapter.setData(popMoviesResponse);
                    runOnUiThread(() -> recyclerView.setAdapter(rvAdapter));
                    if (response.body() != null) {
                        Log.e(TAG, response.body().results.toString());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }
}