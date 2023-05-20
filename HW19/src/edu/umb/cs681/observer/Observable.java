package edu.umb.cs681.observer;

import java.util.concurrent.ConcurrentLinkedQueue;

public abstract class Observable<T> {
    private ConcurrentLinkedQueue<Observer<T>> observers;
    public Observable() {
        observers = new ConcurrentLinkedQueue<>();
    }

    public void addObserver(Observer<T> o) { observers.add(o); }

    public void removeObserver(Observer<T> o) { observers.remove(o); }

    public int countObservers() { return observers.size(); }

    public ConcurrentLinkedQueue<Observer<T>> getObservers(){ return observers; }

    public void clearObservers() { observers.clear(); }

    public void notifyObservers(T event) {
        observers.forEach( (observer)->{observer.update(this, event);} );
    }
}