package com.example.halCinema.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue
    private UUID reservationId;

    private LocalDateTime reservationDatetime;

    private Integer seatNumber;

    private Integer guestSeatNumber;
    
    
    @ManyToOne
    @JoinColumn(name="memberId")
    private Member member;
    
    @ManyToOne
    @JoinColumn(name="screeningScheduleId")
    private ScreeningSchedule screeningSchedule;
    
    
    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID reservationId) {
        this.reservationId = reservationId;
    }
    
    public LocalDateTime getReservationDatetime() {
        return reservationDatetime;
    }

    public void setReservationDatetime(LocalDateTime reservationDatetime) {
        this.reservationDatetime = reservationDatetime;
    }
    
    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    public Integer getGuestSeatNumber() {
        return guestSeatNumber;
    }

    public void setGuestSeatNumber(Integer guestSeatNumber) {
        this.guestSeatNumber = guestSeatNumber;
    }
    
    
    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    
    public ScreeningSchedule getScreeningSchedule() {
        return screeningSchedule;
    }

    public void setScreeningSchedule(ScreeningSchedule screeningSchedule) {
        this.screeningSchedule = screeningSchedule;
    }

}
