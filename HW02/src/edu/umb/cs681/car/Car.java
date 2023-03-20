package edu.umb.cs681.car;

import java.util.ArrayList;

public class Car {
    private final String make,model;
    private final int mileage,year;
    private final double price;
    private int domination_Count;

    public Car(String make,String model,int year,int mileage,float price){
        this.make=make;
        this.model=model;
        this.year=year;
        this.mileage=mileage;
        this.price=price;
        this.domination_Count=0;
    }

    public String getMake(){
        return this.make;
    }

    public String getModel(){
        return this.model;
    }

    public int getMileage(){
        return this.mileage;
    }

    public int getYear(){
        return this.year;
    }

    public double getPrice(){
        return this.price;
    }

    public void setDominationCount(ArrayList<Car> cars){
        for(Car car:cars){
            if((car.getPrice()>this.getPrice()))
                this.domination_Count++;
            if((car.getMileage()<this.getMileage()))
                this.domination_Count++;
            if((car.getYear()<this.getYear()))
                this.domination_Count++;
        }
    }

    public int getDominationCount(){
        return this.domination_Count;
    }

    private String[] carToStringArray(){
        return new String[]{this.make,this.model,Integer.toString(this.mileage),Integer.toString(this.year),Double.toString(this.price)};
    }
}
