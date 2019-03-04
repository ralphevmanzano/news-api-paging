package com.ralphevmanzano.newspaging.vo;

import com.ralphevmanzano.newspaging.db.DateTypeConverter;
import com.squareup.moshi.Json;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

@Entity(tableName = "table_news")
public class News {

    @PrimaryKey
    @Json(name = "url")
    @NonNull
    private String url;
    @Embedded(prefix = "src_")
    @Json(name = "source")
    private Source source;
    @Json(name = "author")
    private String author;
    @Json(name = "title")
    private String title;
    @Json(name = "description")
    private String description;
    @Json(name = "urlToImage")
    private String urlToImage;
    @Json(name = "publishedAt")
    @TypeConverters(DateTypeConverter.class)
    private String publishedAt;
    @Json(name = "content")
    private String content;

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}