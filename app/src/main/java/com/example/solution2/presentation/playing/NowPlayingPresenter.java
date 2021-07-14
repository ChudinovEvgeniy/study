package com.example.solution2.presentation.playing;


import com.example.solution2.domain.MoviesInteract;
import com.example.solution2.domain.model.MoviesItemDomain;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class NowPlayingPresenter extends MvpPresenter<NowPlayingView> {

    private final MoviesInteract mMoviesInteract;

    @Inject
    public NowPlayingPresenter(MoviesInteract moviesInteract) {
        mMoviesInteract = moviesInteract;
    }

    public void onCreate() {
        mMoviesInteract.getNowPlayingData(list -> getViewState().setData(list));
    }

    public void onItemClick(MoviesItemDomain item) {
        getViewState().showDetailsScreen(item);
    }
}
