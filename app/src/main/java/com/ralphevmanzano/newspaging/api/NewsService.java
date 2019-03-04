package com.ralphevmanzano.newspaging.api;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsService {

    @GET("/v2/everything")
    Call<NewsResponse> getNewsFeed(@Query("q") String q,
            @Query("apiKey") String apiKey,
            @Query("sortBy") String sortBy,
            @Query("page") long page,
            @Query("pageSize") int pageSize);
}
