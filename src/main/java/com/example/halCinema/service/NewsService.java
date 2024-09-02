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

    // 新しい順にニュースを取得するクエリ（リスト表示用）
    public List<Object[]> findNewsStreamingDate() {
        return NewsRepository.findNewsStreamingDate();
    }

    // 特定のニュースをIDに基づいて取得するクエリ（詳細表示用）
    public News findNewsById(Integer newsId) {
        return NewsRepository.findNewsById(newsId);
    }

}
