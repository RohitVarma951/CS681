package edu.umb.cs681.primes;

import java.util.*;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer{
    public RunnableCancellableInterruptiblePrimeFactorizer(long num){
        super(num);
    }
    public void generatePrimeFactors() {
        this.factors.clear();
        this.getLock().lock();
        try {
            if(isPrime(num)){
                factors.add(num);
                return;
            }
        } finally {
            this.getLock().unlock();
        }
        for (int i = (int)Math.sqrt(num); i > 1; i--) {
            this.getLock().lock();
            try {
                if (Thread.interrupted()) {
                    System.out.println("Stopped Prime Factor Generation.");
                    this.factors.clear();
                    break;
                }
                if (isPrime((long) i)&&num % i == 0) {
                    factors.add((long) i);
                    if(isPrime(num/i))
                        factors.add(num/i);
                }
            } finally {
                this.getLock().unlock();
            }
        }
    }

    public static void main(String[] args){
        int num = 8633;
        RunnableCancellableInterruptiblePrimeFactorizer RCIPF = new RunnableCancellableInterruptiblePrimeFactorizer(num);
        long start = System.currentTimeMillis();
        Thread t = new Thread(RCIPF);
        t.start();
        RCIPF.setDone();
        try{
            t.sleep(200);
            RCIPF.getLock().lock();
            t.interrupt();
            RCIPF.getLock().unlock();
            t.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        List<Long> lists = RCIPF.getPrimeFactors();
        Collections.sort(lists);
        System.out.println("Prime Factors of "+num+" are :");
        lists.forEach((Long factor)->System.out.print(factor+" "));
        System.out.println("\n" + lists.size() + " prime factors are generated.");
        System.out.println("Time Taken : "+(System.currentTimeMillis()-start)+" milliSeconds.");
    }
}
