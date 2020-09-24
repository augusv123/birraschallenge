package com.tutorial.crud.dto;


import javax.validation.constraints.NotBlank;

public class MeetingInfo {
    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    private int numberOfPeople;


    private double temperature;


}