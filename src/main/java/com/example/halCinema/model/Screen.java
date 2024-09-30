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
    @OneToMany(mappedBy="screen")
    private List<TimeTable2> timeTable2;
    @OneToMany(mappedBy="screen")
    private List<TimeTable3> timeTable3;
    @OneToMany(mappedBy="screen")
    private List<TimeTable4> timeTable4;
    @OneToMany(mappedBy="screen")
    private List<TimeTable5> timeTable5;
    @OneToMany(mappedBy="screen")
    private List<TimeTable6> timeTable6;
    @OneToMany(mappedBy="screen")
    private List<TimeTable7> timeTable7;
    
    
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
    
    
    public List<TimeTable2> getTimeTable2() {
        return timeTable2;
    }

    public void setTimeTable2(List<TimeTable2> timeTable2) {
        this.timeTable2 = timeTable2;
    }
    
    
    public List<TimeTable3> getTimeTable3() {
        return timeTable3;
    }

    public void setTimeTable3(List<TimeTable3> timeTable3) {
        this.timeTable3 = timeTable3;
    }
    
    
    public List<TimeTable4> getTimeTable4() {
        return timeTable4;
    }

    public void setTimeTable4(List<TimeTable4> timeTable4) {
        this.timeTable4 = timeTable4;
    }
    
    
    public List<TimeTable5> getTimeTable5() {
        return timeTable5;
    }

    public void setTimeTable5(List<TimeTable5> timeTable5) {
        this.timeTable5 = timeTable5;
    }
    
    
    public List<TimeTable6> getTimeTable6() {
        return timeTable6;
    }

    public void setTimeTable6(List<TimeTable6> timeTable6) {
        this.timeTable6 = timeTable6;
    }
    
    
    public List<TimeTable7> getTimeTable7() {
        return timeTable7;
    }

    public void setTimeTable7(List<TimeTable7> timeTable7) {
        this.timeTable7 = timeTable7;
    }
}
