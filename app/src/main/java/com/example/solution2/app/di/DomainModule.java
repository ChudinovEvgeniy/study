package com.example.solution2.app.di;

import com.example.solution2.data.repositories.MoviesRepository;
import com.example.solution2.domain.MoviesInteract;
import com.example.solution2.domain.MoviesInteractImpl;
import com.example.solution2.domain.TimerInteract;
import com.example.solution2.domain.TimerInteractImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {
    @Provides
    public TimerInteract getTimerInteract() {
        return new TimerInteractImpl();
    }

    @Provides
    @Singleton
    public MoviesInteract getMoviesInteract(MoviesRepository moviesRepository) {
        return new MoviesInteractImpl(moviesRepository);
    }
}
