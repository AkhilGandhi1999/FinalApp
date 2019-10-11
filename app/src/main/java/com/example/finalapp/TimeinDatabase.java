package com.example.finalapp;

public class TimeinDatabase {
    Integer time;
     String med_ass ;

    public TimeinDatabase(int time ,String med_ass) {

        this.time= time ;
        this.med_ass = med_ass;

    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getMed_ass() {
        return med_ass;
    }

    public void setMed_ass(String med_ass) {
        this.med_ass = med_ass;
    }
}
