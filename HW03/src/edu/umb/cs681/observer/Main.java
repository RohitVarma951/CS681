package edu.umb.cs681.observer;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args){
        ArrayList<DSummary> daySummaryList = new ArrayList<>();
        Path path = Paths.get("src/edu/umb/cs681/observer/Summary.csv");
        try(Stream<String> lines = Files.lines(path)){
            List<List<String>> csv = lines.map( line ->{
                    return Stream.of( line.split(","))
                    .map(value->value.substring(0))
                    .collect(Collectors.toList());})
                    .collect(Collectors.toList());
            List<String> line = csv.get(0);
            System.out.println("The Data is loaded.");
            System.out.println("\t"+line.get(0)+"\t"+line.get(1)+"\t"+line.get(2)+"\t"+line.get(3)+"\t"+line.get(4));
            for(int i=1;i<6;i++){
                line = csv.get(i);
                DSummary d = new DSummary(line.get(0),Double.parseDouble(line.get(1)),Double.parseDouble(line.get(2)),
                        Double.parseDouble(line.get(3)),Double.parseDouble(line.get(4)));
                daySummaryList.add(d);
                System.out.println(d.getDate()+" "+d.getOpen()+" "+d.getHigh()+" "+d.getLow()+" "+d.getClose());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DJIAWkSummaryObservable weeklyObservable = new DJIAWkSummaryObservable();
        weeklyObservable.addObserver(new CandleStickObserver());

        for(int i=0;i<5;i++){
            weeklyObservable.addSummary(daySummaryList.get(i));
        }
    }
}
