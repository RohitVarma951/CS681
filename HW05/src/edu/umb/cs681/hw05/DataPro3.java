package edu.umb.cs681.hw05;

import java.util.List;
import java.util.stream.Collectors;

public class DataPro3 implements Runnable {
    private final List<List<String>> data;
    public DataPro3(List<List<String>> data){
        this.data=data;
    }
    public void run(){
        List<List<String>> roomsDist = data.stream()
                .filter(line->Double.parseDouble(line.get(5))>6.5)
                .filter(line->Double.parseDouble(line.get(7))<4)
                .collect(Collectors.toList());
        double avgRAD = roomsDist.stream().mapToDouble(line->Double.parseDouble(line.get(8))).average().getAsDouble();
        double avgRooms = roomsDist.stream().mapToDouble(line->Double.parseDouble(line.get(5))).average().getAsDouble();
        double avgDist = roomsDist.stream().mapToDouble(line->Double.parseDouble(line.get(7))).average().getAsDouble();
        System.out.println("\nFor Blocks that are near to employment centres and have more rooms,");
        System.out.println("Average RAD      : "+avgRAD+"\nAverage Rooms    : "+avgRooms+"\nAverage Distance : "+avgDist+"\n");
    }
}
