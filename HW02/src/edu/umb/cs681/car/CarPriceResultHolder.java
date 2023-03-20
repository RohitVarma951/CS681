package edu.umb.cs681.car;

public class CarPriceResultHolder {
    private int numCarsExamined;
    private double average;
    public CarPriceResultHolder(){
        numCarsExamined = 0;
        average = 0;
    }

    public void updateNumCarExamined() {
        numCarsExamined++;
    }

    public void updateAverage(double average) {
        this.average = average;
    }

    public double getNumCarsExamined(){
        return numCarsExamined;
    }

    public double getAverage() {
        return average;
    }
}
