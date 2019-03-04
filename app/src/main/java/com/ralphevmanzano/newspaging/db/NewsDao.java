package com.ralphevmanzano.newspaging.db;

import com.ralphevmanzano.newspaging.vo.News;

import java.util.List;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Query;
import io.reactivex.Completable;

@Dao
public abstract class NewsDao extends BaseDao<News> {

    @Query("SELECT * FROM table_news WHERE (title LIKE :query) OR (description LIKE :query) ORDER BY publishedAt DESC")
    public abstract DataSource.Factory<Integer, News> newsByQuery(String query);

    @Query("UPDATE table_news SET title = :title WHERE url = :url")
    public abstract Completable updateTitle(String url, String title);

}
