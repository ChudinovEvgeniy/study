package com.example.solution2.presentation.popular;

import com.example.solution2.domain.MoviesInteract;
import com.example.solution2.domain.model.MoviesItemDomain;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class PopPresenter extends MvpPresenter<PopView> {

    private final MoviesInteract mMoviesInteract;

    @Inject
    public PopPresenter(MoviesInteract moviesInteract) {
        mMoviesInteract = moviesInteract;
    }

    public void onCreate() {
        mMoviesInteract.getData(list -> getViewState().setData(list));
    }

    public void onItemClick(MoviesItemDomain item) {
        getViewState().showDetailsScreen(item);
    }
}
