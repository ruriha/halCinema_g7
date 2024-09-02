package com.example.halCinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.halCinema.model.Movie;

public interface MovieRepositoty extends JpaRepository<Movie, Integer> {
	
	//  情報公開中の映画タイトルを取得
    @Query("select mo.movieTitle from movie mo where releaseStatus = TRUE")
	List<Object[]> findMovieTitle();
	
	
}
