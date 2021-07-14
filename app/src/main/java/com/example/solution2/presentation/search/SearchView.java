package com.example.solution2.presentation.search;

import com.example.solution2.domain.model.MoviesItemDomain;

import java.util.List;

import moxy.MvpView;
import moxy.viewstate.strategy.alias.AddToEndSingle;

@AddToEndSingle
public interface SearchView extends MvpView {
    void setData(List<MoviesItemDomain> list);

    void getSearch(int page);

    void showDetailsScreen(MoviesItemDomain item);
}
