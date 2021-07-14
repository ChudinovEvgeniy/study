package com.example.solution2.presentation.splash;

import android.content.Intent;
import android.os.Bundle;

import com.example.solution2.R;
import com.example.solution2.app.MovieApp;
import com.example.solution2.presentation.choice.ChoiceActivity;

import javax.inject.Inject;
import javax.inject.Provider;

import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;

public class SplashActivity extends MvpAppCompatActivity implements SplashView {

    @Inject
    Provider<SplashPresenter> presenterProvider;

    @ProvidePresenter
    SplashPresenter providePresenter() {
        return presenterProvider.get();
    }

    @InjectPresenter
    SplashPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        MovieApp.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void showNextScreen() {
        startActivity(new Intent(SplashActivity.this, ChoiceActivity.class));
        finish();
    }
}