package edu.umb.cs681.observer;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable<T> {
    private LinkedList<Observer<T>> observers;
    private ReentrantLock lockObs;
    public Observable() {
        observers = new LinkedList<>();
        lockObs = new ReentrantLock();
    }

    public void addObserver(Observer<T> o) { observers.add(o); }

    public void removeObserver(Observer<T> o) { observers.remove(o); }

    public int countObservers() { return observers.size(); }

    public LinkedList<Observer<T>> getObservers(){ return observers; }

    public void clearObservers() { observers.clear(); }

    public void notifyObservers(T event) {
        LinkedList<Observer<T>> observersLocal;
        lockObs.lock();
        observersLocal = new LinkedList<Observer<T>>(observers);
        lockObs.unlock();
        observers.forEach( (observer)->{observer.update(this, event);} );
    }
}