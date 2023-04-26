package edu.umb.cs681.aircrafts;

import java.util.concurrent.atomic.AtomicReference;

public class Aircraft {
    private AtomicReference<Position> position;
    public Aircraft(Position pos){
        this.position = new AtomicReference<>(pos);
    }
    public void setPosition(double newLat, double newLong, double newAlt){
        Position pos = new Position(newLat,newLong,newAlt);
        //this.position = new AtomicReference<>(pos);
        position.set(pos);
        //position.set(new Position(newLat,newLong,newAlt));
    }
    public Position getPosition(){
        return this.position.get();
    }
}