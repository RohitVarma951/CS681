package edu.umb.cs681.observer;

public class TableObserver implements Observer{

    @Override
    public void update(Observable sender, Object o) {
        System.out.println("Table Observer("+Thread.currentThread().threadId()
                +")\nTicker : "+((StockEvent)o).getTicker()
                +"\nQuote  : "+((StockEvent)o).getQuote());
    }
}
