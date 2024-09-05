package com.example.halCinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.TimeTable;

@Repository
public interface TimeTableRepository extends JpaRepository<TimeTable, Integer> {
	
	//  上映可能なタイムテーブル取得
    @Query(value = "with available_times as (" +
            "    select tt1.timeId as start_time_id " +
            "    from TimeTable tt1 " +
            "    where tt1.screen.screenId = ?1 " +
            "      and not exists (" +
            "        select 1 " +
            "        from TimeTable tt2 " +
            "        where tt2.screen.screenId = tt1.screen.screenId " +
            "          and tt2.timeId >= tt1.timeId " +
            "          and tt2.timeId < tt1.timeId + ?2 " +
            "          and tt2.freeStatus = true" +
            "      ) " +
            "      and tt1.timeId >= 800 " +
            "      and tt1.timeId <= 2159 - 149 " +
            ") " +
            "select start_time_id " +
            "from available_times " +
            "order by start_time_id", 
    nativeQuery = true)
	List<Object[]> findFreeTime(Integer screenId, Integer runningTime);
	
	
	
	//  空き状況を空きなしに更新
    @Modifying
    @Transactional
    @Query("update timeTable tt set tt.freeStatus = true where tt.time.timeId = ?1 and tt.screen.screenId = ?2")
	void updateTrueFreeStatus(Integer timeId, Integer screenId);
	
	
	
	//  空き状況を空きありに更新
    @Modifying
    @Transactional
    @Query("update timeTable tt set tt.freeStatus = false where tt.time.timeId = ?1 and tt.screen.screenId = ?2")
	void updateFalseFreeStatus(Integer timeId, Integer screenId);
	
}
