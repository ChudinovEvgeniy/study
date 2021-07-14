package com.example.solution2.presentation.splash;

import com.example.solution2.domain.TimerInteract;

import javax.inject.Inject;

import moxy.MvpPresenter;

public class SplashPresenter extends MvpPresenter<SplashView> {
    private static final int DELAY = 3000;


    private final TimerInteract mTimerInteract;


    @Inject
    public SplashPresenter(TimerInteract timerInteract) {
        mTimerInteract = timerInteract;
    }


    public void onResume() {
        mTimerInteract.start(DELAY, () -> getViewState().showNextScreen());
    }

    public void onPause() {
        mTimerInteract.stop();
    }
}
