package com.example.halCinema.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.halCinema.model.ScreeningSchedule;

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
	

}
