package edu.umb.cs681.observer;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable{
    private HashMap <String, Double> tickerprice = new HashMap<String, Double>();
    private ReentrantLock lockTQ = new ReentrantLock();

    public void changeQuote(String t, double q) {
        lockTQ.lock();
        try {
            tickerprice.put(t, q);
        }finally {
            lockTQ.unlock();
        }
        notifyObservers(new StockEvent(t, q));
    }
}