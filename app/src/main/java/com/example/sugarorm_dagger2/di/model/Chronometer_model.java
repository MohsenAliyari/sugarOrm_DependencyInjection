package com.example.sugarorm_dagger2.di.model;

import com.orm.SugarRecord;

/**
 * Created by Aliyari on 1/15/2018.
 */

public class Chronometer_model extends SugarRecord {


    private String id;
    private String years;
    private String month;
    private String day;
    private String time;
    private String chronometer;


    public Chronometer_model() {

    }

    public Chronometer_model(String years, String month, String day, String time, String chronometer) {

        this.setYears(years);
        this.setMonth(month);
        this.setDay(day);
        this.setTime(time);
        this.setChronometer(chronometer);

    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getChronometer() {
        return chronometer;
    }

    public void setChronometer(String chronometer) {
        this.chronometer = chronometer;
    }
}
