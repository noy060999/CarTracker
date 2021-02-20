package com.example.cartracker;

public class User {
    private String email;
    private String fullName;
    private String phone;
    private Car[] cars;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Car[] getCars() {
        return cars;
    }

    public void setCars(Car[] cars) {
        this.cars = cars;
    }

    public User(String email, String fullName, String phone, Car[] cars) {
        this.email = email;
        this.cars = cars;
        this.fullName = fullName;
        this.phone = phone;
    }

    public User(){

    }

    public User(String email){
        this.email = email;
    }
}
