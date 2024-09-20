package com.example.halCinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	@Query("select mo.movieTitle from movie mo where mo.releaseStatus = TRUE and mo.releaseDay <= CURRENT_DATE")
	List<Object[]> findMovieTitle();
	

	
	
	// 三次開発用 //////////////////////////////////////////////////////////////////////
	
	// 全映画情報を取得
    @Query("select mo.movieId, mo.movieTitle, mo.releaseDay, mo.movieDetails, mo.runningTime, mo.releaseStatus, mo.img, mo.url, mo.staff " +
           "from Movie mo")
    List<Object[]> findAllMovies();
	
	
    // 映画情報を削除
	@Modifying
	@Query("delete from Movie mo where mo.movieId = ?1")
	void deleteMovieById(Integer movieId);
	
	
    // 映画情報を追加	
    // 映画情報を編集
	
}