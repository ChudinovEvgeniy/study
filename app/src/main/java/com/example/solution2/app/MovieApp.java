package com.example.solution2.app;

import android.app.Application;

import com.example.solution2.app.di.AppComponent;
import com.example.solution2.app.di.AppModule;
import com.example.solution2.app.di.DaggerAppComponent;

public class MovieApp extends Application {
    private static AppComponent mAppComponent;

    public static AppComponent getComponent() {
        return mAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }
}
