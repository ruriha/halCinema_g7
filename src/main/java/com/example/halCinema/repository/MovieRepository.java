package com.example.halCinema.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Movie;

import jakarta.transaction.Transactional;

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
	


	//  すべての映画情報取得
	@Query("select mo " +
			"from movie mo " +
    	    "order by mo.releaseDay DESC")
	List<Movie> findAllMovie();

	//  指定条件の映画情報取得
	@Query("select mo " +
			"from movie mo " +
			"where mo.movieTitle like %?1% " +
			"and month(mo.releaseDay) = ?2 " +
			"and mo.releaseStatus = ?3 "+
    	    "order by mo.releaseDay DESC")
	List<Movie> findSelectAllMovie(String searchTitle, Integer seachDate, Boolean searchStatus);

	//  指定条件の上映スケジュール取得
	@Query("select mo " +
			"from movie mo " +
			"where mo.movieTitle like %?1% "+
    	    "order by mo.releaseDay DESC")
	List<Movie> findSelectTitleMovie(String searchTitle);

	//  指定条件の上映スケジュール取得
	@Query("select mo " +
			"from movie mo " +
			"where month(mo.releaseDay) = ?1 "+
    	    "order by mo.releaseDay DESC")
	List<Movie> findSelectDateMovie(Integer seachDate);

	//  指定条件の上映スケジュール取得
	@Query("select mo " +
			"from movie mo " +
			"where mo.releaseStatus = ?1 "+
    	    "order by mo.releaseDay DESC")
	List<Movie> findSelectStatusMovie(Boolean searchStatus);

	//  指定条件の上映スケジュール取得
	@Query("select mo " +
			"from movie mo " +
			"where month(mo.releaseDay) = ?1 " +
			"and mo.releaseStatus = ?2 "+
    	    "order by mo.releaseDay DESC")
	List<Movie> findSelectStatusAndDateMovie(Integer seachDate, Boolean searchStatus);

	//  指定条件の上映スケジュール取得
	@Query("select mo " +
			"from movie mo " +
			"where mo.movieTitle like %?1% " +
			"and mo.releaseStatus = ?2 "+
    	    "order by mo.releaseDay DESC")
	List<Movie> findSelectStatusAndTitleMovie(String searchTitle, Boolean searchStatus);

	//  指定条件の上映スケジュール取得
	@Query("select mo " +
			"from movie mo " +
			"where mo.movieTitle like %?1% " +
			"and month(mo.releaseDay) = ?2 "+
    	    "order by mo.releaseDay DESC")
	List<Movie> findSelectDateAndTitleMovie(String searchTitle, Integer seachDate);

	// データの更新(画像以外)
    @Modifying
    @Transactional
    @Query("update movie mo set mo.movieTitle = ?1, mo.releaseDay = ?2, mo.movieDetails = ?4, mo.runningTime = ?3, mo.releaseStatus = ?7, mo.url = ?5, mo.staff = ?6, mo.img = ?9 where mo.movieId = ?8")
	void updateMovie(String titleNameUpdate, LocalDate publicationDateUpdate, Integer runningTimeUpdate,
			String descriptionUpdate, String urlUpdate, String staff,
			Boolean releaseStatus, Integer movieId, String imgPath);

}