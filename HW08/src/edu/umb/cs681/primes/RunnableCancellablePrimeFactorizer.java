package edu.umb.cs681.primes;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private ReentrantLock lock = new ReentrantLock();
    private boolean done = false;
    public RunnableCancellablePrimeFactorizer(long num){
        super(num);
    }
    public ReentrantLock getLock(){
        return this.lock;
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
}
