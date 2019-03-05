package com.ralphevmanzano.newspaging.data;

import com.ralphevmanzano.newspaging.api.NewsResponse;
import com.ralphevmanzano.newspaging.api.NewsService;
import com.ralphevmanzano.newspaging.db.NewsDatabase;
import com.ralphevmanzano.newspaging.utils.keys.Keys;
import com.ralphevmanzano.newspaging.utils.PagingRequestHelper;
import com.ralphevmanzano.newspaging.vo.News;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PagedList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class NewsBoundaryCallback extends PagedList.BoundaryCallback<News> {

    private String query;
    private NewsService newsService;
    private NewsDatabase newsDatabase;
    private PagingRequestHelper helper;

    // keep the last requested page.
    // When the request is successful, increment the page number.
    private int lastPage = 1;
    private MutableLiveData<String> _networkErrors = new MutableLiveData<>();

    NewsBoundaryCallback(String query, NewsService newsService, NewsDatabase newsDatabase) {
        this.query = query;
        this.newsService = newsService;
        this.newsDatabase = newsDatabase;

        Executor executor = Executors.newSingleThreadExecutor();
        helper = new PagingRequestHelper(executor);
    }

    @Override
    public void onZeroItemsLoaded() {
        requestAndSave(PagingRequestHelper.RequestType.INITIAL);
    }

    @Override
    public void onItemAtEndLoaded(@NonNull News itemAtEnd) {
        Timber.d("onItemAtEndLoaded");
        requestAndSave(PagingRequestHelper.RequestType.AFTER);
    }

    private void requestAndSave(PagingRequestHelper.RequestType type) {
        Timber.d("onItemAtEndLoaded %s", type);

        helper.runIfNotRunning(type, callback ->
                newsService.getNewsFeed(query, Keys.NEWS_API_KEY, "publishedAt", lastPage, 20)
                           .enqueue(new Callback<NewsResponse>() {
                               @Override
                               public void onResponse(@NotNull Call<NewsResponse> call, @NotNull Response<NewsResponse> response) {
                                   if (response.isSuccessful()) {
                                       Timber.d("onNext: newsresponse");
                                       if (response.body() != null) {
                                           if (!response.body().getnews().isEmpty()) {
                                               Timber.d("onNext: newsresponse not empty");
                                               newsDatabase.newsDao().insert(response.body().getnews());
                                           }
                                       }
                                       lastPage++;
                                       callback.recordSuccess();
                                   }
                               }

                               @Override
                               public void onFailure(@NotNull Call<NewsResponse> call, @NotNull Throwable t) {
                                   _networkErrors.postValue(t.getLocalizedMessage());
                                   callback.recordFailure(t);
                               }
                           }));
    }

    LiveData<String> networkErrors() {
        return _networkErrors;
    }
}
