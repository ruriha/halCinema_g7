package com.example.halCinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

	//  情報公開中の映画情報を取得
	@Query("select mo.movieId, mo.movieTitle, mo.staff, mo.movieDetails, mo.url, mo.img from movie mo where mo.releaseStatus = TRUE and mo.releaseDay <= CURRENT_DATE")
	List<Object[]> findMovie();

	//  情報公開中の公開前映画情報を取得
	@Query("select mo.movieId, mo.movieTitle, mo.staff, mo.movieDetails, mo.url, mo.img, mo.releaseDay from movie mo where mo.releaseStatus = TRUE and mo.releaseDay > CURRENT_DATE")
	List<Object[]> findUpcomingMovie();

	//  情報公開中の映画タイトルを取得
	@Query("select mo.movieTitle, mo.movieId from movie mo where mo.releaseStatus = TRUE and mo.releaseDay <= CURRENT_DATE")
	List<Object[]> findMovieTitle();

	//  タイトルから上映時間を取得
	@Query("select mo.runningTime from movie mo where mo.movieTitle = ?1")
	List<Object[]> findRunningTime(String movieTitle);

	//  タイトルから映画IDを取得
	@Query("select mo.movieId from movie mo where mo.movieTitle = ?1")
	List<Object[]> findMovieId(String movieTitle);

	// 三次開発用 //////////////////////////////////////////////////////////////////////

}