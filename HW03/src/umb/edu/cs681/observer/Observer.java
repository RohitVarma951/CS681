package umb.edu.cs681.observer;

public interface Observer<WkSummary> {
    public void update(Observable<WkSummary> sender, WkSummary event);
}