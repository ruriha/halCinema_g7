package com.example.halCinema.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.ScreeningSchedule;
import com.example.halCinema.repository.ScreeningScheduleRepositoty;

@Service
@Transactional
public class ScreeningScheduleService {
	
    @Autowired
    ScreeningScheduleRepositoty ScreeningScheduleRepositoty;
    
    //  座席予約時に必要
    public ScreeningSchedule findScreeningScheduleById(Integer screeningScheduleId) {
        return ScreeningScheduleRepositoty.findById(screeningScheduleId).orElse(null);
    }
    
	
	//  座席予約システム(seat)の上映スケジュール取得
    public List<Object[]> findSelectScreeningSchedule(Integer screeningScheduleId) {
        return ScreeningScheduleRepositoty.findSelectScreeningSchedule(screeningScheduleId);
    }
    

	//  本日の上映タイトル取得
    public List<Object[]> findSelectScreeningTitle(LocalDate nowDate) {
        return ScreeningScheduleRepositoty.findSelectScreeningTitle(nowDate);
    }
    
    
	//  指定タイトルの上映スクリーン取得
    public List<Object[]> findSelectScreeningScreen(Integer movieId, LocalDate nowDate) {
        return ScreeningScheduleRepositoty.findSelectScreeningScreen(movieId, nowDate);
    }
    
    
	//  指定スクリーンの上映時間取得
    public List<Object[]> findSelectScreeningDatetime(Integer screenId, LocalDate nowDate, Integer movieId) {
        return ScreeningScheduleRepositoty.findSelectScreeningDatetime(screenId, nowDate, movieId);
    }
}
