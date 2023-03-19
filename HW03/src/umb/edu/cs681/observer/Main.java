package umb.edu.cs681.observer;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args){
        ArrayList<DSummary> dSummaryList = new ArrayList<>();
        Path path = Paths.get("src/umb/edu/cs681/observer/Summary.csv");
        System.out.println("Adding the data");
        System.out.println("\tDATE\tOPEN\tCLOSE\tHIGH \tLOW");
        try( Stream<String> lines = Files.lines(path) ){
            List<List<String>> csv = lines.map( line -> {
                                        return Stream.of( line.split(",") )
                                                .map(value->value.substring(0))
                                                .collect( Collectors.toList() ); })
                                                .collect( Collectors.toList() );
            List<String> line;
            for(int i=1;i<=5;i++){
                line=csv.get(i);
                DSummary d = new DSummary(Double.parseDouble(line.get(1)),Double.parseDouble(line.get(2)),Double.parseDouble(line.get(3)),Double.parseDouble(line.get(4)),line.get(0));
                dSummaryList.add(d);
                System.out.println(d.getDate()+" "+d.getOpen()+" "+d.getClose()+" "+d.getHigh()+" "+d.getLow());
            }
        } catch (Exception  ex) {
            System.out.println("failed due to exception\n"+ex.getMessage());
        }

        DJIAWkSummaryObservable weeklySummary = new DJIAWkSummaryObservable();
        weeklySummary.addObserver(new CandleStickObserver());

        for(int i=0;i<5;i++)
            weeklySummary.addSummary(dSummaryList.get(i));
    }
}
