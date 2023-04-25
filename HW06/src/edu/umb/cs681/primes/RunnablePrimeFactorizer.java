package edu.umb.cs681.primes;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {

    public RunnablePrimeFactorizer(long num) {
        super(num);
    }

    public void run() {
        generatePrimeFactors();
    }
}
