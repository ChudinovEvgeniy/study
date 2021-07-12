package com.example.solution2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends Activity {
    public static final String EXTRA_NUMBER = "EXTRA_NUMBER";
    public static String id;
    public static int idResp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id = getIntent().getStringExtra(EXTRA_NUMBER);
        idResp = Integer.parseInt(id);
        getDetailMovie();

        ((Toolbar) findViewById(R.id.detailToolbar)).setNavigationOnClickListener(v -> finish());
    }

    private void getDetailMovie() {
        Call<ApiDetailResponse> movie = ApiClient.getRetrofit().getDetailMovie(idResp);
        movie.enqueue(new Callback<ApiDetailResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiDetailResponse> call, @NonNull Response<ApiDetailResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiDetailResponse detailResponse = response.body();
                    Log.e("success", response.body().toString());

                    runOnUiThread(() -> {
                        ((Toolbar) findViewById(R.id.detailToolbar)).setTitle(detailResponse.title);
                        ImageView iv = findViewById(R.id.imageDetailMovie);
                        String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
                        Picasso.get()
                                .load(BASE_IMAGE_URL + detailResponse.image)
                                .into(iv);
                        ((TextView) findViewById(R.id.overviewText)).setText(detailResponse.overview);
                        if (detailResponse.date != null) {
                            String revDate = ApiResponse.reverseDate(detailResponse.date);
                            ((TextView) findViewById(R.id.detailDateReleaseText)).setText(revDate);
                        } else
                            ((TextView) findViewById(R.id.detailDateReleaseText)).setText("нет данных");
                        if (detailResponse.rating != null) {
                            if (detailResponse.rating.startsWith("0")) {
                                ((TextView) findViewById(R.id.detailRatingText)).setText("нет данных");
                            } else
                                ((TextView) findViewById(R.id.detailRatingText)).setText(detailResponse.rating);
                        }
                    });
                }
            }


            @Override
            public void onFailure(@NonNull Call<ApiDetailResponse> call, @NonNull Throwable t) {
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }
}