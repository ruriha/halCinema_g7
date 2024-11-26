package com.example.halCinema.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.halCinema.model.Member;
import com.example.halCinema.model.Reservation;
import com.example.halCinema.model.ScreeningSchedule;
import com.example.halCinema.repository.ReservationRepositoty;


@Service
@Transactional
public class ReservationService {
	
    @Autowired
    ReservationRepositoty ReservationRepositoty;    

    public Reservation saveReservation(Integer seatNumber, Integer guestSeatNumber, Member member, ScreeningSchedule screeningSchedule, LocalDateTime reservationDatetime) {
        Reservation reservation = new Reservation();
        reservation.setSeatNumber(seatNumber);
        reservation.setGuestSeatNumber(guestSeatNumber);
        reservation.setMember(member);
        reservation.setScreeningSchedule(screeningSchedule);
        reservation.setReservationDatetime(reservationDatetime);
        return ReservationRepositoty.save(reservation);
    }
    

	//  空き座席状況の取得
    public List<Object[]> findReservationSeat(Integer screeningScheduleId) {
        return ReservationRepositoty.findReservationSeat(screeningScheduleId);
    }
    

	//  予約ID取得
    public List<Object[]> findReservationId(UUID memberId, LocalDateTime reservationDatetime) {
        return ReservationRepositoty.findReservationId(memberId, reservationDatetime);
    }

}
