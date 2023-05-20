package edu.umb.cs681.observer;

public class LineChartObserver implements Observer{

    @Override
    public void update(Observable sender, Object o) {
        System.out.println("Ticker : "+((StockEvent)o).getTicker());
        System.out.println("Quote  : "+((StockEvent)o).getQuote());
    }
}
