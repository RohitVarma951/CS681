package edu.umb.cs681.primes;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;
    public RunnableCancellablePrimeGenerator(long start,long end){
        super(start,end);
    }
    public void setDone(){
        lock.lock();
        try {
            done = false;
        } finally {
            lock.unlock();
        }
    }
    public void generatePrimes(){
        this.primes.clear();
        for(long  i = start; i <= end; i++){
            lock.lock();
            try {
                if(done){
                    System.out.println("Stopped Prime Number Generation.");
                    break;
                }
                if(isPrime(i)){
                    this.primes.add(i);
                }
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args){
        RunnableCancellablePrimeGenerator RCPG = new RunnableCancellablePrimeGenerator(101,999);
        long start = System.currentTimeMillis();
        Thread t = new Thread(RCPG);
        t.start();
        RCPG.setDone();
        try{
            t.join();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        List<Long> lists = RCPG.getPrimes();
        lists.forEach((Long prime)->System.out.print(prime+", "));
        System.out.println("\n" + lists.size() + " prime numbers are generated.");
        System.out.println("Time Taken : "+(System.currentTimeMillis()-start)+" milliSeconds.");
    }
}
