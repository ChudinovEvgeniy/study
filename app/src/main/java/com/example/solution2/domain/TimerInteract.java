package com.example.solution2.domain;

public interface TimerInteract {
    void start(int milliseconds, StartCallback callback);

    void stop();

    interface StartCallback {
        void onFinish();
    }
}
