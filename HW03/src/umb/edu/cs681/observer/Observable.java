package umb.edu.cs681.observer;

import java.util.LinkedList;
import java.util.List;

public abstract class Observable<WkSummary> {
    private LinkedList<Observer<WkSummary>> observers = new LinkedList<>();

    public void addObserver(Observer<WkSummary> o) {
        observers.add(o);
    }

    public void clearObservers() {
        observers.clear();

    }
    public List<Observer<WkSummary>> getObservers(){
        return observers;
    }

    public int countObservers() {
        return observers.size();

    }
    public void removeObserver(Observer<WkSummary> o) {
        observers.remove(o);
    }

    public void notifyObservers(WkSummary event) {
        observers.forEach( (observer)->{observer.update(this, event);} );
    }

}
