package com.example.halCinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.TimeTable2;

@Repository
public interface TimeTableRepository2 extends JpaRepository<TimeTable2, Integer> {
	
	//  上映可能なタイムテーブル取得
	@Query("SELECT tt1.time.timeId AS start_time_id " +
		       "FROM timeTable2 tt1 " +
		       "WHERE tt1.screen.screenId = ?1 " +
		       "AND tt1.freeStatus = false " +
		       "AND NOT EXISTS (" +
		       "    SELECT 1 " +
		       "    FROM timeTable2 tt2 " +
		       "    WHERE tt2.screen.screenId = tt1.screen.screenId " +
		       "    AND tt2.time.timeId >= tt1.time.timeId " +
		       "    AND tt2.time.timeId < tt1.time.timeId + ?2 " +
		       "    AND tt2.freeStatus = true" +
		       ") " +
		       "AND tt1.time.timeId >= 800 " +
		       "AND tt1.time.timeId <= 2159 - ?2 " +
		       "ORDER BY tt1.time.timeId")
	List<Object[]> findFreeTime(Integer screenId, Integer runningTime);
	
	
	
	//  空き状況を空きなしに更新
    @Modifying
    @Transactional
    @Query("update timeTable2 tt set tt.freeStatus = true where tt.time.timeId = ?1 and tt.screen.screenId = ?2")
	void updateTrueFreeStatus(Integer timeId, Integer screenId);
	
	
	
	//  空き状況を空きありに更新
    @Modifying
    @Transactional
    @Query("update timeTable2 tt set tt.freeStatus = false where tt.time.timeId = ?1 and tt.screen.screenId = ?2")
	void updateFalseFreeStatus(Integer timeId, Integer screenId);
	
}
