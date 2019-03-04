package com.ralphevmanzano.newspaging.api;

import com.ralphevmanzano.newspaging.vo.News;
import com.squareup.moshi.Json;

import java.util.List;

public class NewsResponse {

    @Json(name = "status")
    private String status;
    @Json(name = "totalResults")
    private Integer totalResults;
    @Json(name = "articles")
    private List<News> news = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<News> getnews() {
        return news;
    }

    public void setnews(List<News> news) {
        this.news = news;
    }

}