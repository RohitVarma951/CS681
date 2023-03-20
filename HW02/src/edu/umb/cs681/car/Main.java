package edu.umb.cs681.car;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<Car> carsList = new ArrayList<>();

        Car car1 = new Car("Lexus", "LC 500", 2022, 19, 93150);
        Car car2 = new Car("Nissan", "GT-R", 2021, 19, 113540);
        Car car3 = new Car("Chevrolet", "Corvette", 2023, 20, 64500);
        Car car4 = new Car("Dodge", "Charger", 2020, 24, 32645);
        Car car5 = new Car("Toyota", "Corolla", 2023, 35, 20425);
        Car car6 = new Car("Aston Martin", "DBX707", 2019, 15, 20768);
        Car car7 = new Car("BMW", "4 Series Coupe", 2023, 23, 47400);

        carsList.add(car1);
        carsList.add(car2);
        carsList.add(car3);
        carsList.add(car4);
        carsList.add(car5);
        carsList.add(car6);
        carsList.add(car7);

        double average = carsList.stream()
            .map(car->car.getPrice())
            .reduce(new CarPriceResultHolder(),
                (result,price)->{
                result.updateAverage(result.getAverage()*result.getNumCarsExamined()+price);
                result.updateNumCarExamined();
                result.updateAverage(result.getAverage()/result.getNumCarsExamined());
                return result;},
            (finalResult,IntermediateResult)->finalResult).getAverage();
        System.out.println("Average Car Price is "+average);
    }
}