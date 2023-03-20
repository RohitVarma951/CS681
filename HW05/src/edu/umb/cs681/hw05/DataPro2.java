package edu.umb.cs681.hw05;

import java.util.List;
import java.util.stream.Collectors;

public class DataPro2 implements Runnable {
    private final List<List<String>> data;
    public DataPro2(List<List<String>> data){
        this.data=data;
    }
    public void run(){
        List<List<String>> ageData = data.stream()
                .filter(line->Double.parseDouble(line.get(6)) <= 75 )
                .filter(line->Double.parseDouble(line.get(6)) >= 30 )
                .collect(Collectors.toList());
        double avgPrice = ageData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).average().getAsDouble()*1000;
        double avgTax = ageData.stream().mapToDouble(line->Double.parseDouble(line.get(9))).average().getAsDouble();
        System.out.println("\nIn the Age group of 30 to 75,");
        System.out.println("Average Price : "+avgPrice+"\nAverage Tax   : "+avgTax+"\n");
    }
}
