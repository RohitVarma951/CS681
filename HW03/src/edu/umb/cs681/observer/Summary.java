package edu.umb.cs681.observer;

public class Summary {
    private double open,high,low,close;
    public Summary(double open,double high,double low,double close){
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
    }

    public double getOpen(){
        return open;
    }

    public double getHigh(){
        return high;
    }

    public double getLow(){
        return low;
    }

    public double getClose(){
        return close;
    }
}
