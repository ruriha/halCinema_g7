package com.example.halCinema.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Movie;
import com.example.halCinema.model.Screen;
import com.example.halCinema.model.ScreeningSchedule;
import com.example.halCinema.repository.MovieRepository;
import com.example.halCinema.repository.ScreenRepository;
import com.example.halCinema.repository.ScreeningScheduleRepositoty;


@Service
@Transactional
public class ScreeningScheduleService {
	
    @Autowired
    ScreeningScheduleRepositoty ScreeningScheduleRepositoty;	
    
    @Autowired
    MovieRepository MovieRepository;
	
    @Autowired
    ScreenRepository ScreenRepository;
    
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
    
    
	//  すべての上映スケジュール取得
    public List<Object[]> findAllScreeningSchedule() {
        return ScreeningScheduleRepositoty.findAllScreeningSchedule();
    }
    
    
	//  上映スケジュールの最大ID取得
    public List<Object[]> findMaxScreeningScheduleId() {
        return ScreeningScheduleRepositoty.findMaxScreeningScheduleId();
    }
    

    
    // 上映スケジュールの追加
    public ScreeningSchedule addScreeningSchedule(LocalDateTime screeningDatetime, Screen screen, Movie movie) {
    	ScreeningSchedule screeningSchedule = new ScreeningSchedule();
        screeningSchedule.setScreeningDatetime(screeningDatetime);
        screeningSchedule.setMovie(movie);
        screeningSchedule.setScreen(screen);
        return ScreeningScheduleRepositoty.save(screeningSchedule);
    }
    //  Controllerで用いるとき
	//  String dateTimeString = "2024-09-06 15:00:00";
	//  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	//  LocalDateTime screeningDatetime = LocalDateTime.parse(dateTimeString, formatter);
	//  Integer screenId = 2;
	//  Integer movieId = 2;
	//  Screen screen = ScreenService.findScreenById(screenId);
	//  Movie movie = MovieService.findMovieById(movieId);
	//        
	//  ScreeningScheduleService.addScreeningSchedule(screeningDatetime, screen, movie);
    
    
    
    //  例）PKがserial(連番)のscreening_scheduleテーブルにレコード追加する場合
    //  1. CREATE SEQUENCE screening_schedule_seq START WITH 1 INCREMENT BY 1 NO MINVALUE NO MAXVALUE CACHE 1;をコマンドで実行
    //  2. screening_scheduleのserviceクラス(ScreeningScheduleService.java)のPKの記述を以下のようにする
    //  @Id
    //  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "screening_schedule_seq")
    //  @SequenceGenerator(name = "screening_schedule_seq", sequenceName = "screening_schedule_seq", allocationSize = 1)
    //  private Integer screeningScheduleId;
    //  3. もしも追加ができない場合は連番の認識が追いついていないので、以下のSQLでlast_valueが追いたことが確認できるまでプログラムを繰返し実行する
    //  SELECT * FROM pg_sequences WHERE schemaname = 'public' AND sequencename = 'screening_schedule_seq';
    

    

    //  上映時間を更新
    public void updateScreeningDatetime(LocalDateTime screeningDatetime, Integer screeningScheduleId) {
    	ScreeningScheduleRepositoty.updateScreeningDatetime(screeningDatetime, screeningScheduleId);
    }
    

    

    //  上映スケジュールを削除
    public void deleteScreeningDatetime(Integer screeningScheduleId) {
    	ScreeningScheduleRepositoty.deleteScreeningDatetime(screeningScheduleId);
    }
}
