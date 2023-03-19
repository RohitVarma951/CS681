package umb.edu.cs681.observer;

public class DSummary extends Summary{
    private String date;

    public DSummary(double open, double close, double high, double low) {
        super(open, close, high, low);
    }

    public DSummary(double open, double close, double high, double low,String date) {
        super(open, close, high, low);
        this.date=date;
    }

    public String getDate(){
        return date;
    }
}
