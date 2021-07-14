package com.example.solution2.presentation.choice;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class ChoicePresenter extends MvpPresenter<ChoiceView> {
    @Inject
    public ChoicePresenter() {
    }

    public void onSearchClick() {
        getViewState().showSearchScreen();
    }

    public void onNowPlayingClick() {
        getViewState().showNowPlayingScreen();
    }

    public void onUpcomingClick() {
        getViewState().showUpcomingScreen();
    }

    public void onPopClick() {
        getViewState().showPopularScreen();
    }
}
