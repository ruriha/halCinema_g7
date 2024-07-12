package com.example.halCinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.halCinema.model.News;
import com.example.halCinema.repository.NewsRepository;

@Service
public class NewsService {

    @Autowired
    NewsRepository NewsRepository;

    public List<Object[]> findNewsStreamingDate() {
        return NewsRepository.findNewsStreamingDate();
    }

    public News findNewsById(Integer newsId) {
        return NewsRepository.findNewsById(newsId);
    }

}
