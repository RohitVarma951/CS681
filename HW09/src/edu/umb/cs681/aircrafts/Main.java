package edu.umb.cs681.aircrafts;

import java.util.List;

public class Main implements Runnable{
    @Override
    public void run() {
        List<Double> coordinateList;
        Aircraft plane = new Aircraft(new Position(42.3601,-71.0589,0));
        System.out.println("The Aircraft's Starting Position is : "+ plane.getPosition());
        coordinateList = plane.getPosition().coordinate();
        System.out.println("The Aircraft's Starting coordinates are : " + coordinateList.get(0)
                + " " + coordinateList.get(1)
                + " " + coordinateList.get(2));
        Position pos = plane.getPosition();
        plane.setPosition(40.778944,-72.069688,10000);
        System.out.println("The Aircraft's Current Position is : " + plane.getPosition());
        coordinateList = plane.getPosition().coordinate();
        System.out.println("The Aircraft's Current coordinates are : " + coordinateList.get(0)
                + " " + coordinateList.get(1)
                + " " + coordinateList.get(2));
        Position pos1 = plane.getPosition();
        if(pos.higherAltThan(pos1))
            System.out.println("Aircraft Descended from start to current location.");
        if(pos.lowerAltThan(pos1))
            System.out.println("Aircraft Ascended from start to current location.");
        if(pos.northOf(pos1))
            System.out.println("Aircraft Moved Towards South.");
        if(pos.southOf(pos1))
            System.out.println("Aircraft Moved Towards North.");
    }
    public static void main(String[] args){
        Thread t1,t2,t3;
        t1 = new Thread(new Main());
        t2 = new Thread(new Main());
        t3 = new Thread(new Main());

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
