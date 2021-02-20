package com.example.cartracker;

public class Car {
    private String carBrand;
    private String carModel;
    private String carNumber;
    private String carKM;
    private long carManufactureYear;
    private CarTreatment carTreatment;

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

    public long getCarManufactureYear() {
        return carManufactureYear;
    }

    public void setCarManufactureYear(long carManufactureYear) {
        this.carManufactureYear = carManufactureYear;
    }

    public CarTreatment getCarTreatment() {
        return carTreatment;
    }

    public void setCarTreatment(CarTreatment carTreatment) {
        this.carTreatment = carTreatment;
    }

    public Car(String carBrand, String carModel, String carNumber, String carKM, long carManufactureYear, CarTreatment carTreatment) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carNumber = carNumber;
        this.carKM = carKM;
        this.carManufactureYear = carManufactureYear;
        this.carTreatment = carTreatment;
    }

    public Car() {
    }

    public Car (String carNumber){
        this.carNumber = carNumber;
    }
}
