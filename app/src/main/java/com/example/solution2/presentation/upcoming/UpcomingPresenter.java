package com.example.solution2.presentation.upcoming;


import com.example.solution2.domain.MoviesInteract;
import com.example.solution2.domain.model.MoviesItemDomain;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class UpcomingPresenter extends MvpPresenter<UpcomingView> {

    private final MoviesInteract mMoviesInteract;

    @Inject
    public UpcomingPresenter(MoviesInteract moviesInteract) {
        mMoviesInteract = moviesInteract;
    }

    public void onCreate() {
        mMoviesInteract.getUpcomingData(list -> getViewState().setData(list));
    }

    public void onItemClick(MoviesItemDomain item) {
        getViewState().showDetailsScreen(item);
    }
}
