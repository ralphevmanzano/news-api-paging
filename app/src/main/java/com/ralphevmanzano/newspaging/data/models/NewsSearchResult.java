package com.ralphevmanzano.newspaging.data.models;

import com.ralphevmanzano.newspaging.vo.News;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import io.reactivex.Observable;

public class NewsSearchResult {
    private LiveData<PagedList<News>> data;
    private LiveData<String> networkErrors;

    public NewsSearchResult(LiveData<PagedList<News>> data, LiveData<String> networkErrors) {
        this.data = data;
        this.networkErrors = networkErrors;
    }

    public LiveData<PagedList<News>> getData() {
        return data;
    }

    public LiveData<String> getNetworkErrors() {
        return networkErrors;
    }
}
