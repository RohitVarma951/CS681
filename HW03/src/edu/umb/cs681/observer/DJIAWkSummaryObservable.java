package edu.umb.cs681.observer;

import java.util.*;

public class DJIAWkSummaryObservable extends Observable{
    private ArrayList<DSummary> collection = new ArrayList<>();

    public void addSummary(DSummary d){
        collection.add(d);
        if(collection.size() == 5){
            double open = collection.get(4).getOpen();
            double close = collection.get(0).getClose();
            double high = collection.stream().max(Comparator.comparing(DSummary::getHigh)).get().getHigh();
            double low = collection.stream().min(Comparator.comparing(DSummary::getLow)).get().getLow();
            WkSummary weekSummary = new WkSummary(open,high,low,close);
            notifyObservers(weekSummary);
            collection.clear();
        }
    }
}
