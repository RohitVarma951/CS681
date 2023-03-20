package edu.umb.cs681.hw05;

import java.util.List;
import java.util.stream.Collectors;

public class DataPro implements Runnable {
    private final List<List<String>> data;
    public DataPro(List<List<String>> data){
        this.data=data;
    }
    public void run(){
        List<List<String>> charlesData = data.stream()
                .filter(line->Double.parseDouble(line.get(3).substring(1,2))>0)
                .collect(Collectors.toList());
        double highPrice = charlesData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).max().getAsDouble()*1000;
        double lowPrice = charlesData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).min().getAsDouble()*1000;
        double avgPrice = charlesData.stream().mapToDouble(line->Double.parseDouble(line.get(13))).average().getAsDouble()*1000;
        System.out.println("After Processing Data By nearness to the river,\nHighest Price : $"+highPrice+"" +
                "\nLowest Price  : $"+lowPrice+"\nAverage Price : $"+avgPrice+"\n");
    }
}
