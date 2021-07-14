package com.example.solution2.presentation.upcoming;

import com.example.solution2.domain.model.MoviesItemDomain;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface UpcomingView extends MvpView {
    void setData(List<MoviesItemDomain> list);

    void showDetailsScreen(MoviesItemDomain item);
}
