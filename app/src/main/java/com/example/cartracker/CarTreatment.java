package com.example.cartracker;

import java.util.Date;
import java.util.GregorianCalendar;

public class CarTreatment {
    private String lastTreatmentKM;
    private String lastTreatmentDate;

    public String getLastTreatmentKM() {
        return lastTreatmentKM;
    }

    public void setLastTreatmentKM(String lastTreatmentKM) {
        this.lastTreatmentKM = lastTreatmentKM;
    }

    public String getLastTreatmentDate() {
        return lastTreatmentDate;
    }

    public void setLastTreatmentDate(String lastTreatmentDate) {
        this.lastTreatmentDate = lastTreatmentDate;
    }

    public CarTreatment(String lastTreatmentKM, String lastTreatmentDate) {
        this.lastTreatmentKM = lastTreatmentKM;
        this.lastTreatmentDate = lastTreatmentDate;
    }

    public CarTreatment (){

    }
}
