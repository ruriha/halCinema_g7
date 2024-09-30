package com.example.halCinema.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Integer>  {
	
	//  時刻取得
    @Query("select t.time from time t where t.timeId = ?1")
	List<Object[]> findTime(Integer timeId);

}
