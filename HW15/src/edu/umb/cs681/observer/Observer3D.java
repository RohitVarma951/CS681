package edu.umb.cs681.observer;

public class Observer3D implements Observer{

    @Override
    public void update(Observable sender, Object o) {
        System.out.println("3DObserver("+Thread.currentThread().threadId()
                +")\nTicker : "+((StockEvent)o).getTicker()
                +"\nQuote  : "+((StockEvent)o).getQuote());
    }
}
