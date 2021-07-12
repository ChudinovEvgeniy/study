package com.example.solution2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = SearchActivity.class.getSimpleName();
    private int count = 1;
    private int total_pages;
    private final List<ApiResponse.ItemMovies> allMovies = new ArrayList<>();

    RvAdapter rvAdapter = new RvAdapter();
    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
    RecyclerView recyclerView = null;
    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView rv, int dx, int dy) {
            int totalItemCount = mLinearLayoutManager.getItemCount();
            int lastVisible = mLinearLayoutManager.findLastVisibleItemPosition();
            boolean endHasBeenReached = lastVisible + 5 >= totalItemCount;
            if (totalItemCount >= 0 && endHasBeenReached && total_pages >= count) {
                count++;
                recyclerView.clearOnScrollListeners();
                addSearchMovies();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = findViewById(R.id.rvList);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.addOnScrollListener(scrollListener);
        rvAdapter.setListener(item -> {
            Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
            intent.putExtra((DetailActivity.EXTRA_NUMBER), item.id);
            startActivity(intent);
        });

        EditText editText = findViewById(R.id.search_edit_text);
        editText.setOnKeyListener((v, keyCode, event) -> {
                    if (event.getAction() == KeyEvent.ACTION_DOWN &&
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        getSearchMovies(1);
                        hideKeyBoard();
                        return true;
                    }
                    return false;
                }
        );

        ImageView ivNext = findViewById(R.id.ic_next);
        ivNext.setOnClickListener(v -> {
                    allMovies.clear();
                    getSearchMovies(1);
                    hideKeyBoard();
                }
        );
    }

    public void hideKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void getSearchMovies(int page) {
        EditText searchEt = findViewById(R.id.search_edit_text);
        String searchText = searchEt.getText().toString();

        if (searchText.length() != 0) {
            Call<ApiResponse> popularMovies = ApiClient.getRetrofit().getSearchMovies(searchText, page);
            popularMovies.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                    if (response.isSuccessful()) {
                        List<ApiResponse.ItemMovies> popMoviesResponse;
                        if (response.body() != null) {
                            Log.e(TAG, response.body().results.toString());
                            popMoviesResponse = response.body().results;
                            total_pages = response.body().total_pages;
                            allMovies.addAll(popMoviesResponse);
                            runOnUiThread(() -> rvAdapter.setData(popMoviesResponse));
                            runOnUiThread(() -> recyclerView.setAdapter(rvAdapter));
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                    Log.e("failure", t.getLocalizedMessage());
                }
            });
        } else Toast.makeText(this, "Введите текст", Toast.LENGTH_SHORT).show();
    }

    public void addSearchMovies() {
        EditText searchEt = findViewById(R.id.search_edit_text);
        String searchText = searchEt.getText().toString();

        Call<ApiResponse> popularMovies = ApiClient.getRetrofit().getSearchMovies(searchText, count);
        popularMovies.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, response.body().results.toString());
                    List<ApiResponse.ItemMovies> addItems;
                    addItems = response.body().results;
                    allMovies.addAll(addItems);
                    runOnUiThread(() -> rvAdapter.setData(allMovies));
                    recyclerView.addOnScrollListener(scrollListener);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }
}
