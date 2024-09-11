package com.example.halCinema.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity(name = "screeningSchedule")
public class ScreeningSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "screening_schedule_seq")
    @SequenceGenerator(name = "screening_schedule_seq", sequenceName = "screening_schedule_seq", allocationSize = 1)
    private Integer screeningScheduleId;
    
    private LocalDateTime screeningDatetime;
    
    
    @ManyToOne
    @JoinColumn(name="screenId")
    private Screen screen;
    
    @ManyToOne
    @JoinColumn(name="movieId")
    private Movie movie;

    
    @OneToMany(mappedBy="screeningSchedule")
    private List<Reservation> reservation;
    
    
    public Integer getScreeningScheduleId() {
        return screeningScheduleId;
    }

    public void setScreeningScheduleId(Integer screeningScheduleId) {
        this.screeningScheduleId = screeningScheduleId;
    }
    
    public LocalDateTime getScreeningDatetime() {
        return screeningDatetime;
    }

    public void setScreeningDatetime(LocalDateTime screeningDatetime) {
        this.screeningDatetime = screeningDatetime;
    }


    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
    
    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
    
    
    public List<Reservation> getReservation() {
        return reservation;
    }

    public void setReservation(List<Reservation> reservation) {
        this.reservation = reservation;
    }

}
