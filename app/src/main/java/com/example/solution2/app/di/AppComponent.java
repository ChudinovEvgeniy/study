package com.example.solution2.app.di;

import com.example.solution2.presentation.choice.ChoiceActivity;
import com.example.solution2.presentation.details.DetailsActivity;
import com.example.solution2.presentation.playing.NowPlayingActivity;
import com.example.solution2.presentation.popular.PopActivity;
import com.example.solution2.presentation.search.SearchActivity;
import com.example.solution2.presentation.splash.SplashActivity;
import com.example.solution2.presentation.upcoming.UpcomingActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class, DomainModule.class})
public interface AppComponent {
    void inject(ChoiceActivity choiceActivity);
    void inject(PopActivity popActivity);
    void inject(DetailsActivity detailsActivity);
    void inject(SplashActivity splashActivity);
    void inject(NowPlayingActivity nowPlayingActivity);
    void inject(UpcomingActivity upcomingActivity);
    void inject(SearchActivity searchActivity);
}
