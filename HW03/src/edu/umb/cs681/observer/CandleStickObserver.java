package edu.umb.cs681.observer;

public class CandleStickObserver implements Observer<WkSummary>{
    @Override
    public void update(Observable<WkSummary> sender, WkSummary event) {
        System.out.println("\nWeekly Report.");
        System.out.println("OPEN  : "+event.getOpen());
        System.out.println("CLOSE : "+event.getClose());
        System.out.println("HIGH  : "+event.getHigh());
        System.out.println("LOW   : "+event.getLow());
    }
}
