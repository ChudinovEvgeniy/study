package com.example.solution2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RvActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RvAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rvList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvAdapter = new RvAdapter();
        rvAdapter.setListener(new RvAdapter.OnClickListener() {
            @Override
            public void onClick(ApiResponse.ItemPopularMovies item) {
                Toast.makeText(getApplicationContext(), item.title, Toast.LENGTH_SHORT).show();
            }
        });
        getPopMovies();
    }

    public void getPopMovies() {
        Call<ApiResponse> popularMovies = ApiClient.getMovies().getPopularMovies();
        popularMovies.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    List<ApiResponse.ItemPopularMovies> popMoviesResponse = null;
                    if (response.body() != null) {
                        popMoviesResponse = response.body().results;
                    }
                    rvAdapter.setData(popMoviesResponse);
                    recyclerView.setAdapter(rvAdapter);
                    if (response.body() != null) {
                        Log.e("success", response.body().toString());
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