package edu.umb.cs681.hw20;

import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args){
        Path path = Paths.get("src/edu/umb/cs681/hw20/bos-housing.csv");
        try( Stream<String> lines = Files.lines(path) ){
            List<List<String>> matrix = lines.map( line -> Stream.of( line.split(",") )
                            .collect( Collectors.toList() ))
                    .collect( Collectors.toList() );
            DataPro.nearCharles(matrix);
            DataPro.lessCrime(matrix);
            DataPro.processingByAge(matrix);
            DataPro.roomsAndDist(matrix);
        } catch (Exception  ex) {
            ex.printStackTrace();
            System.out.println("failed due to exception\n"+ex.getMessage());
        }
    }
}