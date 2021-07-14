package com.example.solution2.presentation.details;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.solution2.R;
import com.example.solution2.app.MovieApp;
import com.example.solution2.data.remote.model.ApiResponse;
import com.example.solution2.domain.model.MoviesDetailDomain;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;
import javax.inject.Provider;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class DetailsActivity extends MvpAppCompatActivity implements DetailsView {
    public static final String EXTRA_ID = "EXTRA_ID";

    @Inject
    Provider<DetailsPresenter> presenterProvider;

    @ProvidePresenter
    DetailsPresenter providePresenter() {
        return presenterProvider.get();
    }

    @InjectPresenter
    DetailsPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MovieApp.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        int id = getIntent().getIntExtra(EXTRA_ID, 0);

        ((Toolbar) findViewById(R.id.detailToolbar)).setNavigationOnClickListener(v -> presenter.onBackClick());

        presenter.onCreate(id);
    }

    @Override
    public void setData(MoviesDetailDomain moviesDetailDomain) {
        runOnUiThread(() -> {
            ((Toolbar) findViewById(R.id.detailToolbar)).setTitle(moviesDetailDomain.title);
            ImageView iv = findViewById(R.id.imageDetailMovie);
            String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500/";
            Picasso.get()
                    .load(BASE_IMAGE_URL + moviesDetailDomain.image)
                    .into(iv);
            ((TextView) findViewById(R.id.overviewText)).setText(moviesDetailDomain.overview);
            if (moviesDetailDomain.date != null) {
                String revDate = ApiResponse.reverseDate(moviesDetailDomain.date);
                ((TextView) findViewById(R.id.detailDateReleaseText)).setText(revDate);
            } else
                ((TextView) findViewById(R.id.detailDateReleaseText)).setText("нет данных");
            if (moviesDetailDomain.rating != null) {
                if (moviesDetailDomain.rating.startsWith("0")) {
                    ((TextView) findViewById(R.id.detailRatingText)).setText("нет данных");
                } else
                    ((TextView) findViewById(R.id.detailRatingText)).setText(moviesDetailDomain.rating);
            }
        });
    }

    @Override
    public void back() {
        finish();
    }

    @Override
    public void showError() {
        runOnUiThread(() -> Toast.makeText(DetailsActivity.this, "Ошибка получения данных", Toast.LENGTH_LONG).show());
    }
}
