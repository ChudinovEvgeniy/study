package com.example.solution2.domain;


import com.example.solution2.domain.model.MoviesDetailDomain;
import com.example.solution2.domain.model.MoviesItemDomain;

import java.util.List;

public interface MoviesInteract {
    void getData(GetDataCallback getDataCallback);
    void getPopularData(GetDataCallback getDataCallback);
    void getNowPlayingData(GetDataCallback getDataCallback);
    void getDetailData(int id, GetDetailCallback getDetailCallback);
    void getUpcomingData(GetDataCallback getDataCallback);
    void getSearchData(String searchText, int pageId, GetDataCallback getDataCallback);

    interface GetDataCallback {
        void onFinish(List<MoviesItemDomain> list);
    }

    interface GetDetailCallback {
        void onFinish(MoviesDetailDomain moviesDetailDomain);
    }
}

