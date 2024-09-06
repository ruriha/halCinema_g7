package com.example.halCinema.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "screen")
public class Screen {

    @Id
    @GeneratedValue
    private Integer screenId;
    
    private String screenName;
    
    private Integer capacity;
    

    @OneToMany(mappedBy="screen")
    private List<ScreeningSchedule> screeningSchedule;
    

    @OneToMany(mappedBy="screen")
    private List<TimeTable> timeTable;
    
    
    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }
    
    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }


    public List<ScreeningSchedule> getScreeningSchedule() {
        return screeningSchedule;
    }

    public void setScreeningSchedule(List<ScreeningSchedule> screeningSchedule) {
        this.screeningSchedule = screeningSchedule;
    }


    public List<TimeTable> getTimeTable() {
        return timeTable;
    }

    public void setTimeTable(List<TimeTable> timeTable) {
        this.timeTable = timeTable;
    }
}
