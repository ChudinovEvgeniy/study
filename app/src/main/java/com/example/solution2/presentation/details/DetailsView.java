package com.example.solution2.presentation.details;

import com.example.solution2.domain.model.MoviesDetailDomain;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface DetailsView extends MvpView {
    void setData(MoviesDetailDomain moviesDetailDomain);

    void back();

    void showError();
}
