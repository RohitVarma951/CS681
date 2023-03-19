package edu.umb.cs681.hw04;

import java.util.List;
import java.util.stream.Collectors;

public class DataPro {
    public static void nearCharles(List<List<String>> housingData){
        List<List<String>> charlesData = housingData.stream()
                .filter(line->Double.parseDouble(line.get(3).substring(1,2))>0)
                .collect(Collectors.toList());
        double highPrice = charlesData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).max().getAsDouble()*1000;
        double lowPrice = charlesData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).min().getAsDouble()*1000;
        double avgPrice = charlesData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).average().getAsDouble()*1000;
        System.out.println("After Processing Data By nearness to the river,\nHighest Price : $"+highPrice+"" +
                "\nLowest Price  : $"+lowPrice+"\nAverage Price : $"+avgPrice);
    }

    public static void lessCrime(List<List<String>> housingData){
        double[] crime = housingData.stream().mapToDouble(line->Double.parseDouble(line.get(0))).sorted().toArray();
        double[] pupil = housingData.stream().mapToDouble(line->Double.parseDouble(line.get(10))).sorted().toArray();
        List<List<String>> crimePupilData = housingData.stream()
                .filter(line->Double.parseDouble(line.get(0))<crime[crime.length/10])
                .filter(line->Double.parseDouble(line.get(10))<pupil[pupil.length/10])
                .collect(Collectors.toList());
        double maxPrice = crimePupilData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).max().getAsDouble();
        double minPrice = crimePupilData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).min().getAsDouble();
        double avgPrice = crimePupilData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).average().getAsDouble();
        double maxNOX = crimePupilData.stream().mapToDouble(line->Double.parseDouble(line.get(4))).max().getAsDouble();
        double minNOX = crimePupilData.stream().mapToDouble(line->Double.parseDouble(line.get(4))).min().getAsDouble();
        double avgNOX = crimePupilData.stream().mapToDouble(line->Double.parseDouble(line.get(4))).average().getAsDouble();
        double maxRooms = crimePupilData.stream().mapToDouble(line->Double.parseDouble(line.get(5))).max().getAsDouble();
        double minRooms = crimePupilData.stream().mapToDouble(line->Double.parseDouble(line.get(5))).min().getAsDouble();
        double avgRooms = crimePupilData.stream().mapToDouble(line->Double.parseDouble(line.get(5))).average().getAsDouble();

        System.out.println("\nIn The Blocks with least 10% crime rate and least 10% pupil to teacher ratio,");
        System.out.println("Max Price : "+maxPrice+"  | Min Price : "+minPrice+"  | Average Price : "+avgPrice);
        System.out.println("Max NOX   : "+maxNOX+" | Min NOX   : "+minNOX+" | Average NOX   : "+avgNOX);
        System.out.println("Max Rooms : "+maxRooms+" | Min Rooms : "+minRooms+" | Average Rooms : "+avgRooms);
    }
    public static void processingByAge(List<List<String>> housingData){
        List<List<String>> ageData = housingData.stream()
                .filter(line->Double.parseDouble(line.get(6)) <= 75 )
                .filter(line->Double.parseDouble(line.get(6)) >= 30 )
                .collect(Collectors.toList());
        double avgPrice = ageData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).average().getAsDouble()*1000;
        double avgTax = ageData.stream().mapToDouble(line->Double.parseDouble(line.get(9))).average().getAsDouble();
        System.out.println("\nIn the Age group of 30 to 75,");
        System.out.println("Average Price : "+avgPrice+"\nAverage Tax   : "+avgTax);
    }

    public static void roomsAndDist(List<List<String>> housingData){
        List<List<String>> roomsDist = housingData.stream()
                .filter(line->Double.parseDouble(line.get(5))>6.5)
                .filter(line->Double.parseDouble(line.get(7))<4)
                .collect(Collectors.toList());
        double avgRAD = roomsDist.stream().mapToDouble(line->Double.parseDouble(line.get(8))).average().getAsDouble();
        double avgRooms = roomsDist.stream().mapToDouble(line->Double.parseDouble(line.get(5))).average().getAsDouble();
        double avgDist = roomsDist.stream().mapToDouble(line->Double.parseDouble(line.get(7))).average().getAsDouble();
        System.out.println("\nFor Blocks that are near to employment centres and have more rooms,");
        System.out.println("Average RAD      : "+avgRAD+"\nAverage Rooms    : "+avgRooms+"\nAverage Distance : "+avgDist);
    }
}
