package com.example.solution2.presentation.upcoming;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.solution2.R;
import com.example.solution2.app.MovieApp;
import com.example.solution2.domain.model.MoviesItemDomain;
import com.example.solution2.presentation.RvAdapter;
import com.example.solution2.presentation.details.DetailsActivity;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class UpcomingActivity extends MvpAppCompatActivity implements UpcomingView {
    @Inject
    Provider<UpcomingPresenter> presenterProvider;

    @ProvidePresenter
    UpcomingPresenter providePresenter() {
        return presenterProvider.get();
    }

    @InjectPresenter
    UpcomingPresenter presenter;

    private final RvAdapter mAdapter = new RvAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MovieApp.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);

        RecyclerView mRecyclerView = findViewById(R.id.rvList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setListener(item -> presenter.onItemClick(item));
        presenter.onCreate();
    }

    @Override
    public void setData(List<MoviesItemDomain> list) {
        runOnUiThread(() -> {
            mAdapter.setData(list);
            mAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void showDetailsScreen(MoviesItemDomain item) {
        Intent intent = new Intent(UpcomingActivity.this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_ID, item.id);
        startActivity(intent);
    }
}