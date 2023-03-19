package umb.edu.cs681.observer;

import java.util.ArrayList;
import java.util.Comparator;

public class DJIAWkSummaryObservable extends Observable{
    ArrayList<DSummary> collection = new ArrayList<>();
    public void addSummary(DSummary dSummary){
        collection.add(dSummary);
        if(collection.size() == 5){
            double open,close,high,low;
            open  = collection.get(0).getOpen();
            close = collection.get(4).getClose();
            high  = collection.stream().max(Comparator.comparing((DSummary d)->d.getHigh())).get().getHigh();
            low   = collection.stream().min(Comparator.comparing((DSummary d)->d.getLow())).get().getLow();
            WkSummary weekSummary = new WkSummary(open,close,high,low);

            notifyObservers(weekSummary);
            collection.clear();
        }
    }
}
