package umb.edu.cs681.observer;

public class Summary {
    private double open,close,high,low;
    public Summary(double open,double close,double high,double low){
        this.open=open;
        this.close=close;
        this.high=high;
        this.low=low;
    }
    public double getOpen(){
        return open;
    }
    public double getClose(){
        return close;
    }
    public double getHigh(){
        return high;
    }
    public double getLow(){
        return low;
    }
}
