package umb.edu.cs681.observer;

public class CandleStickObserver implements Observer<WkSummary>{
    @Override
    public void update(Observable<WkSummary> sender, WkSummary event) {
        System.out.println("\nWeekly Summary Report:");
        System.out.println("OPEN:  "+event.getOpen()+"\nCLOSE: "+event.getClose());
        System.out.println("HIGH:  "+event.getHigh()+"\nLOW:   "+event.getLow());
    }
}
