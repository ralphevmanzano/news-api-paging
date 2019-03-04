package com.ralphevmanzano.newspaging.data;

import android.util.Log;

import com.ralphevmanzano.newspaging.api.NewsService;
import com.ralphevmanzano.newspaging.data.models.NewsSearchResult;
import com.ralphevmanzano.newspaging.db.NewsDatabase;
import com.ralphevmanzano.newspaging.vo.News;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class NewsRepository {

    private NewsDatabase newsDatabase;
    private NewsService newsService;

    @Inject
    public NewsRepository(NewsDatabase newsDatabase, NewsService newsService) {
        this.newsDatabase = newsDatabase;
        this.newsService = newsService;
    }

    public NewsSearchResult search(String query) {
        Timber.d("search: %s", query);

        // Get data source from dao
        // appending '%' so we can allow other characters to be before and after the query string
        String qry = "%" + query.replace(' ', '%') + "%";
        DataSource.Factory<Integer, News> dataSourceFactory = newsDatabase.newsDao().newsByQuery(qry);

        // Construct the boundary callback
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(30)
                .setEnablePlaceholders(false)
                .build();

        NewsBoundaryCallback boundaryCallback = new NewsBoundaryCallback(query, newsService, newsDatabase);
        LiveData<String> networkErrors = boundaryCallback.networkErrors();

        LiveData<PagedList<News>> data = new LivePagedListBuilder<>(dataSourceFactory, config).setBoundaryCallback(boundaryCallback).build();

        return new NewsSearchResult(data, networkErrors);
    }

    public void updateNews(String url, String title) {
        newsDatabase.newsDao()
                    .updateTitle(url, title)
                    .subscribeOn(Schedulers.io())
                    .subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onComplete() {
                            Timber.d("Update Complete: ");
                        }

                        @Override
                        public void onError(Throwable e) {
                            Timber.e("Error");
                        }
                    });
    }
}
