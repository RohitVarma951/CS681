package edu.umb.cs681.observer;

public class DSummary extends Summary{
    private String date;

    public DSummary(double open,double high,double low,double close){
        super(open,high,low,close);
    }

    public DSummary(String date,double open,double high,double low,double close){
        super(open,high,low,close);
        this.date = date;
    }

    public String getDate(){
        return date;
    }
}
