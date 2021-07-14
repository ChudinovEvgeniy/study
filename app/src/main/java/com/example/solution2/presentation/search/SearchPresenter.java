package com.example.solution2.presentation.search;


import com.example.solution2.domain.MoviesInteract;
import com.example.solution2.domain.model.MoviesItemDomain;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class SearchPresenter extends MvpPresenter<SearchView> {

    private final MoviesInteract mMoviesInteract;

    @Inject
    public SearchPresenter(MoviesInteract moviesInteract) {
        mMoviesInteract = moviesInteract;
    }

    public void onCreate() {
    }

    public void setData(String searchText, int pageId) {
        mMoviesInteract.getSearchData(searchText, pageId, list -> getViewState().setData(list));
    }

    public void onItemClick(MoviesItemDomain item) {
        getViewState().showDetailsScreen(item);
    }
}
