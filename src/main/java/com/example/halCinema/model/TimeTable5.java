package com.example.halCinema.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "timeTable5")
public class TimeTable5 {

    @Id
    @GeneratedValue
    private Integer timeTableId;
    
    private Boolean freeStatus;
    
    
    @ManyToOne
    @JoinColumn(name="screenId")
    private Screen screen;
    
    
    @ManyToOne
    @JoinColumn(name="timeId")
    private Time time;
    
    
    public Integer getTimeTableId() {
        return timeTableId;
    }

    public void setTimeTableId(Integer timeTableId) {
        this.timeTableId = timeTableId;
    }

    public Boolean getFreeStatus() {
        return freeStatus;
    }

    public void setFreeStatus(Boolean freeStatus) {
        this.freeStatus = freeStatus;
    }
    


    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

}
