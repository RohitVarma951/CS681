package edu.umb.cs681.primes;

public class RunnablePrimeGenerator extends PrimeGenerator implements Runnable{
    public RunnablePrimeGenerator(long start,long end){
        super(start,end);
    }

    public void run(){
        generatePrimes();
    }
}
