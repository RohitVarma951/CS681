package edu.umb.cs681.hw05;

import java.util.List;
import java.util.stream.Collectors;

public class DataPro1 implements Runnable {
    private final List<List<String>> data;
    public DataPro1(List<List<String>> data){
        this.data=data;
    }
    public void run(){
        double[] crime = data.stream().mapToDouble(line->Double.parseDouble(line.get(0))).sorted().toArray();
        double[] pupil = data.stream().mapToDouble(line->Double.parseDouble(line.get(10))).sorted().toArray();
        List<List<String>> crimePupilData = data.stream()
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
        System.out.println("Max Rooms : "+maxRooms+" | Min Rooms : "+minRooms+" | Average Rooms : "+avgRooms+"\n");
    }
}
