package com.example.halCinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer>{
	

    // 新しい順にニュースを取得するクエリ（リスト表示用）
    @Query("select n.newsId, n.newsTitle, n.streamingDate from news n order by n.streamingDate desc")
	List<Object[]> findNewsStreamingDate();


    // 特定のニュースをIDに基づいて取得するクエリ（詳細表示用）
    @Query("select n from news n where n.newsId = ?1")
    News findNewsById(Integer newsId);

}