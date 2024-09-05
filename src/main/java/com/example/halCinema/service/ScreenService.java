package com.example.halCinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Screen;
import com.example.halCinema.repository.ScreenRepository;

@Service
@Transactional
public class ScreenService {
    
	@Autowired
	ScreenRepository ScreenRepositoty;
    
	
	//  すべてのスクリーン一覧取得
    public List<Object[]> findAllScreen() {
        return ScreenRepositoty.findAllScreen();
    }
    
    
    //  上映スケジュール追加時に必要
    public Screen findScreenById(Integer screenId) {
        return ScreenRepositoty.findById(screenId).orElse(null);
    }

}
