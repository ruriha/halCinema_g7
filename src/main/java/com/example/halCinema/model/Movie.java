package com.example.halCinema.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity(name = "movie")
public class Movie {

    @Id
    @GeneratedValue
    private Integer movieId;

    private String movieTitle;

    private LocalDate releaseDay;

    private String movieDetails;
    
    private Integer runningTime;
    
    private Boolean releaseStatus;
    
    private String img;
    
    private String url;
    
    private String staff;
    

    @OneToMany(mappedBy="movie")
    private List<ScreeningSchedule> screeningSchedule;
    
    
    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }
    
    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }
    
    public LocalDate getReleaseDay() {
        return releaseDay;
    }

    public void setReleaseDay(LocalDate releaseDay) {
        this.releaseDay = releaseDay;
    }
    
    public String getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(String movieDetails) {
        this.movieDetails = movieDetails;
    }    

    public Integer getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(Integer runningTime) {
        this.runningTime = runningTime;
    }

    public Boolean getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Boolean releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }
    
    
    public List<ScreeningSchedule> getScreeningSchedule() {
        return screeningSchedule;
    }

    public void setScreeningSchedule(List<ScreeningSchedule> screeningSchedule) {
        this.screeningSchedule = screeningSchedule;
    }

}