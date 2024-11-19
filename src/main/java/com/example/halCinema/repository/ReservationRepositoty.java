package com.example.halCinema.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.halCinema.model.Reservation;

@Repository
public interface ReservationRepositoty  extends JpaRepository<Reservation, UUID>{
	
	//  空き座席状況取得
	@Query("select r.seatNumber " +
	           "from reservation r " +
	           "where r.screeningSchedule.screeningScheduleId = ?1 "+
	           "union all " +
	           "select r.guestSeatNumber "+
	           "from reservation r "+
	           "where r.screeningSchedule.screeningScheduleId = ?1")
	List<Object[]> findReservationSeat(Integer screeningScheduleId);
	
	
	//  予約ID取得
	@Query("select r.reservationId " +
	           "from reservation r " +
	           "where r.member.memberId = ?1 "+
	           "and r.reservationDatetime = ?2")
	List<Object[]> findReservationId(UUID memberId, LocalDateTime reservationDatetime);
	
}
