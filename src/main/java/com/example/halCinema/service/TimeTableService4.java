package com.example.halCinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.repository.TimeTableRepository4;

@Service
@Transactional
public class TimeTableService4 {
    
	@Autowired
	TimeTableRepository4 TimeTableRepository4;
    
	
	//  上映可能なタイムテーブル取得
    public List<Object[]> findFreeTime(Integer screenId, Integer runningTime) {
        return TimeTableRepository4.findFreeTime(screenId, runningTime);
    }
    

    //  空き状況を空きなしに更新
    public void updateTrueFreeStatus(Integer timeId, Integer screenId) {
    	TimeTableRepository4.updateTrueFreeStatus(timeId, screenId);
    }
    

    //  空き状況を空きありに更新
    public void updateFalseFreeStatus(Integer timeId, Integer screenId) {
    	TimeTableRepository4.updateFalseFreeStatus(timeId, screenId);
    }
}
