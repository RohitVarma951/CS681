package edu.umb.cs681.primes;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;

    public RunnableCancellablePrimeFactorizer(long num){
        super(num);
    }

    public void setDone(){
        lock.lock();
        try {
            done = false;
        } finally {
            lock.unlock();
        }
    }

    public void generatePrimeFactors() {
        this.factors.clear();
        lock.lock();
        try {
            if(isPrime(num)){
                factors.add(num);
                return;
            }
        } finally {
            lock.unlock();
        }
        for (int i = (int)Math.sqrt(num); i > 1; i--) {
            lock.lock();
            try {
                if (done) {
                    System.out.println("Stopped Prime Factor Generation.");
                    break;
                }
                if (isPrime((long) i)&&num % i == 0) {
                    factors.add((long) i);
                    if(isPrime(num/i))
                       factors.add(num/i);
                }
            } finally {
                lock.unlock();
            }
        }
    }
/*
* 6 2 3
* 17 17
* 131 131
* 84 7 3 2
* 125 5
* 2489 19
* 8633 89 97
* */
    public static void main(String[] args){
        int num = 8633;
        RunnableCancellablePrimeFactorizer RCPF = new RunnableCancellablePrimeFactorizer(num);
        long start = System.currentTimeMillis();
        Thread t = new Thread(RCPF);
        t.start();
        RCPF.setDone();
        try{
            t.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        List<Long> lists = RCPF.getPrimeFactors();
        Collections.sort(lists);
        System.out.println("Prime Factors of "+num+" are :");
        lists.forEach((Long factor)->System.out.print(factor+" "));
        System.out.println("\n" + lists.size() + " prime factors are generated.");
        System.out.println("Time Taken : "+(System.currentTimeMillis()-start)+" milliSeconds.");
    }
}
