package com.example.halCinema.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.repository.TimeRepository;

@Service
@Transactional
public class TimeService {
    
	@Autowired
	TimeRepository TimeRepository;
    
	
	//  時刻取得
    public List<Object[]> findTime(Integer timeId) {
        return TimeRepository.findTime(timeId);
    }

}
