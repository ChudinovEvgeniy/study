package com.example.solution2.presentation.choice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


import com.example.solution2.R;
import com.example.solution2.app.MovieApp;
import com.example.solution2.presentation.playing.NowPlayingActivity;
import com.example.solution2.presentation.popular.PopActivity;
import com.example.solution2.presentation.search.SearchActivity;
import com.example.solution2.presentation.upcoming.UpcomingActivity;

import javax.inject.Inject;
import javax.inject.Provider;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class ChoiceActivity extends MvpAppCompatActivity implements ChoiceView {
    @Inject
    Provider<ChoicePresenter> presenterProvider;

    @ProvidePresenter
    ChoicePresenter providePresenter() {
        return presenterProvider.get();
    }

    @InjectPresenter
    ChoicePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MovieApp.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);

        TextView searchTv = findViewById(R.id.search_movies);
        TextView releaseTv = findViewById(R.id.release_movies);
        TextView upcomingTv = findViewById(R.id.upcoming_movies);
        TextView popularTv = findViewById(R.id.popular_movies);

        searchTv.setOnClickListener(v -> presenter.onSearchClick());
        releaseTv.setOnClickListener(v -> presenter.onNowPlayingClick());
        upcomingTv.setOnClickListener(v -> presenter.onUpcomingClick());
        popularTv.setOnClickListener(v -> presenter.onPopClick());
    }

    @Override
    public void showSearchScreen() {
        Intent intent = new Intent(ChoiceActivity.this, SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void showNowPlayingScreen() {
        Intent intent = new Intent(ChoiceActivity.this, NowPlayingActivity.class);
        startActivity(intent);
    }

    @Override
    public void showUpcomingScreen() {
        Intent intent = new Intent(ChoiceActivity.this, UpcomingActivity.class);
        startActivity(intent);
    }

    @Override
    public void showPopularScreen() {
        Intent intent = new Intent(ChoiceActivity.this, PopActivity.class);
        startActivity(intent);
    }
}