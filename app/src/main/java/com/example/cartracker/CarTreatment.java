package com.example.cartracker;

import java.util.Date;

public class CarTreatment {
    private long lastTreatmentKM;
    private Date lastTreatmentDate;

    public long getLastTreatmentKM() {
        return lastTreatmentKM;
    }

    public void setLastTreatmentKM(long lastTreatmentKM) {
        this.lastTreatmentKM = lastTreatmentKM;
    }

    public Date getLastTreatmentDate() {
        return lastTreatmentDate;
    }

    public void setLastTreatmentDate(Date lastTreatmentDate) {
        this.lastTreatmentDate = lastTreatmentDate;
    }

    public CarTreatment(long lastTreatmentKM, Date lastTreatmentDate) {
        this.lastTreatmentKM = lastTreatmentKM;
        this.lastTreatmentDate = lastTreatmentDate;
    }

    public CarTreatment (){

    }
}
