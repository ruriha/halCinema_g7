package com.example.halCinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Screen;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer>  {
	
	//  すべてのスクリーン一覧取得
    @Query("select s.screenId from screen s")
	List<Object[]> findAllScreen();

}
