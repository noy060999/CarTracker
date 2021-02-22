package com.example.cartracker;

import android.location.Location;

public class Car {
    private String carBrand;
    private String carModel;
    private String carNumber;
    private String carKM;
    private String carManufactureYear;
    private CarTreatment carTreatment;
    private Double lon, lat;

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getCarKM() {
        return carKM;
    }

    public void setCarKM(String carKM) {
        this.carKM = carKM;
    }

    public String getCarManufactureYear() {
        return carManufactureYear;
    }

    public void setCarManufactureYear(String carManufactureYear) {
        this.carManufactureYear = carManufactureYear;
    }

    public CarTreatment getCarTreatment() {
        return carTreatment;
    }

    public void setCarTreatment(CarTreatment carTreatment) {
        this.carTreatment = carTreatment;
    }

    public Car(String carBrand, String carModel, String carNumber, String carKM, String carManufactureYear,Double lon, Double lat, CarTreatment carTreatment) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carNumber = carNumber;
        this.carKM = carKM;
        this.lat = lat;
        this.lon = lon;
        this.carManufactureYear = carManufactureYear;
        this.carTreatment = carTreatment;
    }

    public Car() {
    }

    public Car (String carNumber){
        this.carNumber = carNumber;
    }
}
