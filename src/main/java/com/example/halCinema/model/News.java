package com.example.halCinema.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "news")
public class News {

    @Id
    @GeneratedValue
    private Integer newsId;

    private Date streamingDate;

    private String newsTitle;

    private String newsContents;
    

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Date getStreamingDate() {
        return streamingDate;
    }

    public void setStreamingDate(Date streamingDate) {
        this.streamingDate = streamingDate;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContents() {
        return newsContents;
    }

    public void setNewsContents(String newsContents) {
        this.newsContents = newsContents;
    }

}
