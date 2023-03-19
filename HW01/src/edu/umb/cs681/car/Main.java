package edu.umb.cs681.car;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        ArrayList<Car> carsList = new ArrayList<>();

        Car car1 = new Car("Lexus","LC 500", 2022, 19, 93150);
        Car car2 = new Car("Nissan", "GT-R", 2021, 19, 113540);
        Car car3 = new Car("Chevrolet", "Corvette", 2023, 20, 64500);
        Car car4 = new Car("Dodge","Charger", 2020, 24, 32645);
        Car car5 = new Car("Toyota","Corolla", 2023, 35, 20425);
        Car car6 = new Car("Aston Martin","DBX707", 2019, 15, 20768);
        Car car7 = new Car("BMW","4 Series Coupe", 2023, 23, 47400);

        carsList.add(car1);
        carsList.add(car2);
        carsList.add(car3);
        carsList.add(car4);
        carsList.add(car5);
        carsList.add(car6);
        carsList.add(car7);

        carsList.forEach((Car car)->car.setDominationCount(carsList));

        System.out.println("Order of Cars in original order:");
        carsList.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getMileage()+" "+car.getYear()+" "+car.getPrice()+" "+car.getDominationCount()));

//      Sorting cars by ascending order of price
        System.out.println("\norder of cars in ascending order of Price:");
        List<Car> carsByPrice = carsList.stream().sorted(Comparator.comparing((Car car)->car.getPrice())).collect(Collectors.toList());
        carsByPrice.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getPrice()));

        double threshold = 40000,min,max,avg;
        System.out.println("\nSetting Threshold Price of 40000.");

        System.out.println("\nHigh Priced Group:");
        Map<Boolean,List<Car>> carGroupPrice = carsList.stream().collect(Collectors.partitioningBy((Car car)->car.getPrice()>threshold));
        List<Car> carHighPrice = carGroupPrice.get(true);
        carHighPrice.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getPrice()));
        min = carHighPrice.stream().mapToDouble((Car car)->car.getPrice()).min().getAsDouble();
        max = carHighPrice.stream().mapToDouble((Car car)->car.getPrice()).max().getAsDouble();
        avg = carHighPrice.stream().mapToDouble((Car car)->car.getPrice()).average().getAsDouble();
        System.out.println("Lowest Price:"+min+" Highest Price:"+max+" Average Price:"+avg+" Cars in Group:"+carHighPrice.size());

        System.out.println("\nLow Priced Group:");
        List<Car> carLowPrice = carGroupPrice.get(false);
        carLowPrice.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getPrice()));
        min = carLowPrice.stream().mapToDouble((Car car)->car.getPrice()).min().getAsDouble();
        max = carLowPrice.stream().mapToDouble((Car car)->car.getPrice()).max().getAsDouble();
        avg = carLowPrice.stream().mapToDouble((Car car)->car.getPrice()).average().getAsDouble();
        System.out.println("Lowest Price:"+min+" Highest Price:"+max+" Average Price:"+avg+" Cars in Group:"+carLowPrice.size());

//      Sorting Cars by order of Year.
        System.out.println("\norder of cars in ascending order of Year of manufacture:");
        List<Car> carsByYear = carsList.stream().sorted(Comparator.comparing((Car car)->car.getYear())).collect(Collectors.toList());
        carsByYear.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getYear()));

        int threshold1=2022,min1,max1;
        System.out.println("\nSetting Threshold Year as 2022.");

        System.out.println("\nOld Cars Group:");
        Map<Boolean,List<Car>> carGroupYear = carsList.stream().collect(Collectors.partitioningBy((Car car)->car.getYear()<threshold1));
        List<Car> oldCars = carGroupYear.get(true);
        oldCars.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getYear()));
        min1 = oldCars.stream().mapToInt((Car car)->car.getYear()).min().getAsInt();
        max1 = oldCars.stream().mapToInt((Car car)->car.getYear()).max().getAsInt();
        avg = oldCars.stream().mapToInt((Car car)->car.getYear()).average().getAsDouble();
        System.out.println("Oldest Car Year:"+min1+" Newest Car Year:"+max1+" Average Year:"+avg+" Cars in Group:"+oldCars.size());

        System.out.println("\nNew Cars Group:");
        List<Car> newCars = carGroupYear.get(false);
        newCars.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getYear()));
        min1 = newCars.stream().mapToInt((Car car)->car.getYear()).min().getAsInt();
        max1 = newCars.stream().mapToInt((Car car)->car.getYear()).max().getAsInt();
        avg = newCars.stream().mapToInt((Car car)->car.getYear()).average().getAsDouble();
        System.out.println("Oldest Car Year:"+min1+" Newest Car Year:"+max1+" Average Year:"+avg+" Cars in Group:"+newCars.size());

//      Sorting Cars by order of Mileage.
        System.out.println("\norder of cars in ascending order of Mileage:");
        List<Car> carsByMileage = carsList.stream().sorted(Comparator.comparing((Car car)->car.getMileage())).collect(Collectors.toList());
        carsByMileage.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getMileage()));

        int threshold2=20;
        System.out.println("\nSetting Threshold Mileage of 20.");

        System.out.println("\nGroup with Lesser Mileage:");
        Map<Boolean,List<Car>> carGroupMileage = carsList.stream().collect(Collectors.partitioningBy((Car car)->car.getMileage()<threshold2));
        List<Car> lesserMileage = carGroupMileage.get(true);
        lesserMileage.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getMileage()));
        min1 = lesserMileage.stream().mapToInt((Car car)->car.getMileage()).min().getAsInt();
        max1 = lesserMileage.stream().mapToInt((Car car)->car.getMileage()).max().getAsInt();
        avg = lesserMileage.stream().mapToInt((Car car)->car.getMileage()).average().getAsDouble();
        System.out.println("Least Mileage:"+min1+" Highest Mileage:"+max1+" Average Mileage:"+avg+" Cars in Group:"+lesserMileage.size());

        System.out.println("\nGroup with Higher Mileage:");
        List<Car> higherMileage = carGroupMileage.get(false);
        higherMileage.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getMileage()));
        min1 = higherMileage.stream().mapToInt((Car car)->car.getMileage()).min().getAsInt();
        max1 = higherMileage.stream().mapToInt((Car car)->car.getMileage()).max().getAsInt();
        avg = higherMileage.stream().mapToInt((Car car)->car.getMileage()).average().getAsDouble();
        System.out.println("Least Mileage:"+min1+" Highest Mileage:"+max1+" Average Mileage:"+avg+" Cars in Group:"+higherMileage.size());

//      Sorting Cars by order of Domination Count.
        System.out.println("\norder of cars in ascending order of Domination Count:");
        List<Car> carsByDominationCount = carsList.stream().sorted(Comparator.comparing((Car car)->car.getDominationCount())).collect(Collectors.toList());
        carsByDominationCount.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getDominationCount()));

        int threshold3=10;
        System.out.println("\nSetting Threshold Domination Count of 8.");

        System.out.println("\nGroup with Low Domination Count:");
        Map<Boolean,List<Car>> carGroupDominationCount = carsList.stream().collect(Collectors.partitioningBy((Car car)->car.getDominationCount()<threshold3));
        List<Car> lowDomCount = carGroupDominationCount.get(true);
        lowDomCount.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getDominationCount()));
        min1 = lowDomCount.stream().mapToInt((Car car)->car.getDominationCount()).min().getAsInt();
        max1 = lowDomCount.stream().mapToInt((Car car)->car.getDominationCount()).max().getAsInt();
        avg = lowDomCount.stream().mapToInt((Car car)->car.getDominationCount()).average().getAsDouble();
        System.out.println("Least Domination Count:"+min1+" Highest Domination Count:"+max1+" Average Domination Count:"+avg+" Cars in Group:"+lowDomCount.size());

        System.out.println("\nGroup with Higher Domination Count:");
        List<Car> highDomCount = carGroupDominationCount.get(false);
        highDomCount.forEach((Car car)->System.out.println(car.getMake()+" "+car.getModel()+" "+car.getDominationCount()));
        min1 = highDomCount.stream().mapToInt((Car car)->car.getDominationCount()).min().getAsInt();
        max1 = highDomCount.stream().mapToInt((Car car)->car.getDominationCount()).max().getAsInt();
        avg = highDomCount.stream().mapToInt((Car car)->car.getDominationCount()).average().getAsDouble();
        System.out.println("Least Domination Count:"+min1+" Highest Domination Count:"+max1+" Average Domination Count:"+avg+" Cars in Group:"+highDomCount.size());

    }
}
