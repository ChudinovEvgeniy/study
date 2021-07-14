package com.example.solution2.presentation.details;


import com.example.solution2.domain.MoviesInteract;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class DetailsPresenter extends MvpPresenter<DetailsView> {
    private final MoviesInteract mMoviesInteract;

    @Inject
    public DetailsPresenter(MoviesInteract moviesInteract) {
        mMoviesInteract = moviesInteract;
    }

    public void onCreate(int id) {
        mMoviesInteract.getDetailData(id, moviesDetailDomain -> getViewState().setData(moviesDetailDomain));
    }

    public void onBackClick() {
        getViewState().back();
    }
}
