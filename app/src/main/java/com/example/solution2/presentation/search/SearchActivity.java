package com.example.solution2.presentation.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.DividerItemDecoration;
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

public class SearchActivity extends MvpAppCompatActivity implements SearchView {
    @Inject
    Provider<SearchPresenter> presenterProvider;

    @ProvidePresenter
    SearchPresenter providePresenter() {
        return presenterProvider.get();
    }

    @InjectPresenter
    SearchPresenter presenter;

    RvAdapter rvAdapter = new RvAdapter();
    LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
    RecyclerView recyclerView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MovieApp.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.rvList);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setListener(item -> presenter.onItemClick(item));
        presenter.onCreate();

        EditText editText = findViewById(R.id.search_edit_text);
        editText.setOnKeyListener((v, keyCode, event) -> {
                    if (event.getAction() == KeyEvent.ACTION_DOWN &&
                            (keyCode == KeyEvent.KEYCODE_ENTER)) {
                        getSearch(1);
                        hideKeyBoard();
                        return true;
                    }
                    return false;
                }
        );

        ImageView ivNext = findViewById(R.id.ic_next);
        ivNext.setOnClickListener(v -> {
                    getSearch(1);
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

    @Override
    public void setData(List<MoviesItemDomain> list) {
        runOnUiThread(() -> {
            rvAdapter.setData(list);
            rvAdapter.notifyDataSetChanged();
        });
    }

    @Override
    public void getSearch(int page) {
        EditText searchEt = findViewById(R.id.search_edit_text);
        String searchText = searchEt.getText().toString();
        presenter.setData(searchText, page);
    }

    @Override
    public void showDetailsScreen(MoviesItemDomain item) {
        Intent intent = new Intent(SearchActivity.this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.EXTRA_ID, item.id);
        startActivity(intent);
    }
}