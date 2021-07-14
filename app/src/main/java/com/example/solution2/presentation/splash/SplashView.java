package com.example.solution2.presentation.splash;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

public interface SplashView extends MvpView {
    @AddToEndSingle
    void showNextScreen();
}
