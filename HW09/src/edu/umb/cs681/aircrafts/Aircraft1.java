package edu.umb.cs681.aircrafts;

import java.util.concurrent.locks.ReentrantLock;

public class Aircraft1 {
    private Position position;
    private ReentrantLock lock = new ReentrantLock();
    public Aircraft1(Position pos){ this.position = pos; }
    public void setPosition(double newLat, double newLong, double newAlt){
        lock.lock();
        try {
            this.position = this.position.change(newLat, newLong, newAlt);
        }finally {
            lock.unlock();
        }
    }
    public Position getPosition(){
        Position pos;
        lock.lock();
        try{
            pos = this.position;
        }finally {
            lock.unlock();
        }
        return pos;
    }
}
