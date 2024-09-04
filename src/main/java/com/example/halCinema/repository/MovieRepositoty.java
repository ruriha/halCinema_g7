package com.example.halCinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.halCinema.model.Movie;

public interface MovieRepositoty extends JpaRepository<Movie, Integer> {
	
	//  情報公開中の映画タイトルを取得
	@Query("select mo.movieId, mo.movieTitle, mo.staff, mo.movieDetails, mo.url, mo.img from movie mo where mo.releaseStatus = TRUE and mo.releaseDay <= CURRENT_DATE")
	List<Object[]> findMovieTitle();
	
	
	//  情報公開中の公開前映画タイトルを取得
	@Query("select mo.movieId, mo.movieTitle, mo.staff, mo.movieDetails, mo.url, mo.img, mo.releaseDay from movie mo where mo.releaseStatus = TRUE and mo.releaseDay > CURRENT_DATE")
	List<Object[]> findUpcomingMovieTitle();
	
}