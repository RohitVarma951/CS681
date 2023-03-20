package edu.umb.cs681.hw05;

import java.nio.file.*;
import java.util.List;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {
        Path path = Paths.get("bos-housing.csv");
        List<List<String>> matrix = null;
        try( Stream<String> lines = Files.lines(path) ){
            matrix = lines.map( line -> {
                        return Stream.of( line.split(",") )
                                .map(value->value.substring(0))
                                .collect( Collectors.toList() ); })
                    .collect( Collectors.toList() );
        } catch (Exception  ex) {
            System.out.println("failed due to exception\n"+ex.getMessage());
        }
        DataPro dp1 = new DataPro(matrix);
        DataPro1 dp2 = new DataPro1(matrix);
        DataPro2 dp3 = new DataPro2(matrix);
        DataPro3 dp4 = new DataPro3(matrix);
        Thread t1,t2,t3,t4;
        t1 = new Thread(dp1);
        t2 = new Thread(dp2);
        t3 = new Thread(dp3);
        t4 = new Thread(dp4);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        try{
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}