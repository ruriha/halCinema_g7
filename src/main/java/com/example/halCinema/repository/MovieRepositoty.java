package com.example.halCinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.halCinema.model.Movie;

public interface MovieRepositoty extends JpaRepository<Movie, Integer> {
	
	//  情報公開中の映画タイトルを取得
//    @Query("select mo.movieTitle from movie mo where releaseStatus = TRUE")
//	List<Object[]> findMovieTitle();
	
	
}