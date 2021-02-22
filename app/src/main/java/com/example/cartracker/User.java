package com.example.cartracker;

public class User {
    //properties
    private String email;
    private String fullName;
    private Car car;
    private float distance;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User(String email, String fullName, Car car) {
        this.email = email;
        this.car = car;
        this.fullName = fullName;
    }

    public User(){

    }

    public User(String email){
        this.email = email;
    }
}
