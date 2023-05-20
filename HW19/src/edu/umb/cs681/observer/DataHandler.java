package edu.umb.cs681.observer;

public class DataHandler extends StockQuoteObservable implements Runnable{
    public void run(){
        changeQuote("test1",100.10);
        changeQuote("test2",200.99);
        changeQuote("test3",10.80);
    }
    public static void main(String[] args){
        DataHandler dataHandler = new DataHandler();
        TableObserver tableObserver = new TableObserver();
        dataHandler.addObserver(tableObserver);
        Observer3D observer3D = new Observer3D();
        dataHandler.addObserver(observer3D);
        Thread[] t=new Thread[15];
        for(int i=0;i<15;i++){
            t[i] = new Thread(dataHandler);
            t[i].start();
        }
        try{
            Thread.sleep(10);
        }catch (Exception e){}
        for(int i=0;i<15;i++)
            t[i].interrupt();
        try {
            for (int i=0;i<15;i++)
                t[i].join();
        }catch (Exception e){}
    }
}