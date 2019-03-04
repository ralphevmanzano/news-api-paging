package com.ralphevmanzano.newspaging.viewmodel;

import com.ralphevmanzano.newspaging.data.NewsRepository;
import com.ralphevmanzano.newspaging.data.models.NewsSearchResult;
import com.ralphevmanzano.newspaging.vo.News;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class NewsViewModel extends ViewModel {

    private NewsRepository newsRepository;
    private MutableLiveData<String> queryLiveData = new MutableLiveData<>();
    private LiveData<NewsSearchResult> newsResult = Transformations.map(queryLiveData, query -> newsRepository.search(query));

    @Inject
    public NewsViewModel(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    public LiveData<PagedList<News>> news = Transformations.switchMap(newsResult, NewsSearchResult::getData);
    public LiveData<String> networkErrors = Transformations.switchMap(newsResult, NewsSearchResult::getNetworkErrors);

    public void searchNews(String queryString) {
        queryLiveData.postValue(queryString);
    }

//    public void updateNewsTitle(String url, String title) {
//        newsRepository.updateNews(url, title);
//    }

    public String getLastQuery() {
        return queryLiveData.getValue();
    }
}
