package com.example.solution2.domain;

import java.util.Timer;
import java.util.TimerTask;

public class TimerInteractImpl implements TimerInteract {

    private Timer mTimer = null;

    @Override
    public void start(int milliseconds, StartCallback callback) {
        mTimer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                callback.onFinish();
            }
        };
        mTimer.schedule(timerTask, milliseconds);
    }

    @Override
    public void stop() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }
}
