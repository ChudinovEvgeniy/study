package com.example.solution2.presentation.choice;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface ChoiceView extends MvpView {
    void showSearchScreen();
    void showNowPlayingScreen();
    void showUpcomingScreen();
    void showPopularScreen();
}
