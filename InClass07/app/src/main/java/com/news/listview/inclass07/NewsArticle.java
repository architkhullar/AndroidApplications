package com.news.listview.inclass07;

import java.io.Serializable;


//Implement NewsArticle class to store object
public class NewsArticle implements Serializable{
    String author, title, description, url, urlToImage, publishedAt;
    Fromsrc source;

    public NewsArticle() {
    }

    public NewsArticle(String author, String title, String description, String url, String urlToImage, String publishedAt, Fromsrc source) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.source = source;
    }

    @Override
    public String toString() {
        return "NewsArticle{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source=" + source +
                '}';
    }
}
