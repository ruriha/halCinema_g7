package com.example.halCinema.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.ScreeningSchedule;

@Repository
public interface ScreeningScheduleRepositoty  extends JpaRepository<ScreeningSchedule, Integer>{

	//  座席予約システム(seat)の上映スケジュール取得
	@Query("select ss.movie.movieTitle, ss.screeningDatetime, ss.screen.capacity , ss.screen.screenId " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "inner join ss.screen s " +
	           "where ss.screeningScheduleId = ?1")
	List<Object[]> findSelectScreeningSchedule(Integer screeningScheduleId);
	
	
	//  本日の上映タイトル取得
	@Query("select ss.movie.movieId, ss.movie.movieTitle " +
	           "from screeningSchedule ss " +
	           "where date(ss.screeningDatetime) = ?1 "+
	           "group by ss.movie.movieId, ss.movie.movieTitle")
	List<Object[]> findSelectScreeningTitle(LocalDate nowDate);
	
	
	

	//  指定タイトルの上映スクリーン取得
	@Query("select ss.movie.movieId, ss.screen.screenId, ss.movie.runningTime " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "inner join ss.screen s " +
	           "where ss.movie.movieId = ?1 "+
	           "and date(ss.screeningDatetime) = ?2 "+
	           "group by ss.movie.movieId, ss.screen.screenId, ss.movie.runningTime "
	           + "order by ss.screen.screenId")
	List<Object[]> findSelectScreeningScreen(Integer movieId, LocalDate nowDate);
	
	

	//  指定スクリーンの上映時間取得
	@Query("select ss.screeningScheduleId, ss.screeningDatetime " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "inner join ss.screen s " +
	           "where ss.screen.screenId = ?1 "+
	           "and date(ss.screeningDatetime) = ?2 "+
	           "and ss.movie.movieId = ?3 "+
	           "order by ss.screeningDatetime")
	List<Object[]> findSelectScreeningDatetime(Integer screenId, LocalDate nowDate, Integer movieId);
	
	
	
	
	//  すべての上映スケジュール取得
    @Query("select ss.screeningScheduleId, ss.movie.movieTitle, ss.screen.screenId, ss.screeningDatetime, ss.movie.runningTime, ss.movie.movieId " +
    		"from screeningSchedule ss " +
    		"inner join ss.movie mo " +
    	    "order by ss.screeningDatetime DESC")
	List<Object[]> findAllScreeningSchedule();
	

	//  指定スケジュールを除外したすべての上映スケジュール取得
    @Query("select ss.screeningScheduleId, ss.movie.movieTitle, ss.screen.screenId, ss.screeningDatetime, ss.movie.runningTime, ss.movie.movieId " +
    		"from screeningSchedule ss " +
    		"inner join ss.movie mo " +
	        "where ss.screeningScheduleId != ?1 "+
    	    "order by ss.screeningDatetime DESC")
	List<Object[]> findElseScreeningSchedule(Integer screeningScheduleId);
	
	//  指定月の上映スケジュール取得
    @Query("select ss.screeningScheduleId, ss.movie.movieTitle, ss.screen.screenId, ss.screeningDatetime, ss.movie.runningTime, ss.movie.movieId " +
    		"from screeningSchedule ss " +
    		"inner join ss.movie mo " +
    	    "where MONTH(ss.screeningDatetime) = ?1 " +
    	    "order by ss.screeningDatetime ASC")
	List<Object[]> findMonthScreeningSchedule(Integer month);
	
	
	
	//  指定条件の上映スケジュール取得
	@Query("select ss.screeningScheduleId, ss.movie.movieTitle, ss.screen.screenId, ss.screeningDatetime, ss.movie.runningTime, ss.movie.movieId " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "where ss.screen.screenId = ?1 "+
	           "and date(ss.screeningDatetime) = ?2 "+
	           "and ss.movie.movieId = ?3 "+
	           "order by ss.screeningDatetime DESC")
	List<Object[]> findSelectAllScreeningSchedule(Integer searchScreen, LocalDate searchDate, Integer searchMovieId);
	//  指定条件の上映スケジュール取得
	@Query("select ss.screeningScheduleId, ss.movie.movieTitle, ss.screen.screenId, ss.screeningDatetime, ss.movie.runningTime, ss.movie.movieId " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "where ss.movie.movieId = ?1 "+
	           "order by ss.screeningDatetime DESC")
	List<Object[]> findSelectTitleScreeningSchedule(Integer searchMovieId);
	//  指定条件の上映スケジュール取得
	@Query("select ss.screeningScheduleId, ss.movie.movieTitle, ss.screen.screenId, ss.screeningDatetime, ss.movie.runningTime, ss.movie.movieId " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "where date(ss.screeningDatetime) = ?1 " +
	           "order by ss.screeningDatetime DESC")
	List<Object[]> findSelectDateScreeningSchedule(LocalDate searchDate);
	
	//  指定条件の上映スケジュール取得
	@Query("select ss.screeningScheduleId, ss.movie.movieTitle, ss.screen.screenId, ss.screeningDatetime, ss.movie.runningTime, ss.movie.movieId " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "where ss.screen.screenId = ?1 "+
	           "order by ss.screeningDatetime DESC")
	List<Object[]> findSelectScreenScreeningSchedule(Integer searchScreen);
	//  指定条件の上映スケジュール取得
	@Query("select ss.screeningScheduleId, ss.movie.movieTitle, ss.screen.screenId, ss.screeningDatetime, ss.movie.runningTime, ss.movie.movieId " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "where ss.screen.screenId = ?1 "+
	           "and date(ss.screeningDatetime) = ?2 "+
	           "order by ss.screeningDatetime DESC")
	List<Object[]> findSelectScreenAndDateScreeningSchedule(Integer searchScreen, LocalDate searchDate);
	//  指定条件の上映スケジュール取得
	@Query("select ss.screeningScheduleId, ss.movie.movieTitle, ss.screen.screenId, ss.screeningDatetime, ss.movie.runningTime, ss.movie.movieId " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "where ss.screen.screenId = ?1 "+
	           "and ss.movie.movieId = ?2 "+
	           "order by ss.screeningDatetime DESC")
	List<Object[]> findSelectScreenAndTitleScreeningSchedule(Integer searchScreen, Integer searchMovieId);
	//  指定条件の上映スケジュール取得
	@Query("select ss.screeningScheduleId, ss.movie.movieTitle, ss.screen.screenId, ss.screeningDatetime, ss.movie.runningTime, ss.movie.movieId " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "where date(ss.screeningDatetime) = ?1 "+
	           "and ss.movie.movieId = ?2 "+
	           "order by ss.screeningDatetime DESC")
	List<Object[]> findSelectDateAndTitleScreeningSchedule(LocalDate searchDate, Integer searchMovieId);
	
	
	
	
	//  上映スケジュールの最大ID取得
    @Query("select max(ss.screeningScheduleId) from screeningSchedule ss")
	List<Object[]> findMaxScreeningScheduleId();
	
	
	
	//  上映時間を更新
    @Modifying
    @Transactional
    @Query("update screeningSchedule ss set ss.screeningDatetime = ?1 where ss.screeningScheduleId = ?2")
	void updateScreeningDatetime(LocalDateTime screeningDatetime, Integer screeningScheduleId);
	
	
	
	//  上映時間を削除
    @Modifying
    @Transactional
    @Query("delete from screeningSchedule ss where ss.screeningScheduleId = ?1")
	void deleteScreeningDatetime(Integer screeningScheduleId);
    
    

	//  上映スケジュールに映画IDが使われているか取得
	@Query("select ss.movie.movieTitle, ss.screeningDatetime, ss.screen.capacity , ss.screen.screenId " +
	           "from screeningSchedule ss " +
	           "inner join ss.movie mo " +
	           "inner join ss.screen s " +
	           "where mo.movieId = ?1")
	List<Object[]> findMovieScreeningSchedule(Integer movieId);
    


}
