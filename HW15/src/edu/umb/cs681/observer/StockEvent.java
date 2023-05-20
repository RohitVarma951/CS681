package edu.umb.cs681.observer;

public record StockEvent(String ticker,double quote) {
    public String getTicker(){ return ticker; }

    public double getQuote(){ return quote; }
}
