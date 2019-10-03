package com.example.sugarorm_dagger2.di.model;

import javax.inject.Inject;

/**
 * Created by Aliyari on 1/18/2018.
 */


public class Data {

    private Chronometer_model chronometer_model;

    @Inject
    public Data(Chronometer_model chronometer_model) {
        this.chronometer_model = chronometer_model;
    }

    public Long getId() {
        return (chronometer_model.getId() );
    }

    public void setId(Long id) {
        this.chronometer_model.setId(id);
    }

    public String getYears() {
        return chronometer_model.getYears();
    }

    public void setYears(String years) {
        this.chronometer_model.setYears(years);
    }

    public String getMonth() {
        return chronometer_model.getMonth();

    }

    public void setMonth(String month) {
        this.chronometer_model.setMonth(month);
    }

    public String getDay() {
        return chronometer_model.getDay();
    }

    public void setDay(String day) {
        this.chronometer_model.setDay(day);
    }

    public String getTime() {
        return chronometer_model.getTime();
    }

    public void setTime(String time) {
        this.chronometer_model.setTime(time);
    }

    public String getChronometer() {
        return chronometer_model.getChronometer();
    }

    public void setChronometer(String chronometer) {
        this.chronometer_model.setChronometer(chronometer);
    }
}