package edu.umb.cs681.aircrafts;

import java.util.ArrayList;
import java.util.List;

public record Position(double latitude, double longitude, double altitude){
    public List<Double> coordinate(){
        List<Double> coordinateList = new ArrayList<>();
        coordinateList.add(this.latitude);
        coordinateList.add(this.longitude);
        coordinateList.add(this.altitude);
        return coordinateList;
    }

    public static Position change(double newLat, double newLon, double newAlt){
        return new Position(newLat, newLon, newAlt);
    }
    public boolean higherAltThan(Position anotherPosition){
        return this.altitude>anotherPosition.altitude;
    }
    boolean lowerAltThan(Position anotherPosition){
        return this.altitude<anotherPosition.altitude;
    };
    boolean northOf(Position anotherPosition){
        return this.latitude>anotherPosition.latitude;
    }
    boolean southOf(Position anotherPosition){
        return this.latitude<anotherPosition.latitude;
    }
}