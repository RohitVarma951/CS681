package edu.umb.cs681.primes;

import java.util.LinkedList;

// Generates prime factors of a given number (dividend)
// Prime factors are generated in the range of 2 and dividend
// from:	lower bound of the range (2)
// to:		upper bound of the range (dividend)

public class PrimeFactorizer {
    protected long num;
    protected LinkedList<Long> factors = new LinkedList<Long>();

    public PrimeFactorizer(long num){
        if(num >= 2){
            this.num = num;
        }else{
            throw new RuntimeException("dividend must be >= 2. dividend==" + num);
        }
    }

    public LinkedList<Long> getPrimeFactors(){ return factors; }

    protected boolean isPrime(Long num){
        if(num==2)
            return true;
        int i;
        for(i= (int) Math.sqrt(num); i>1; i--){
            if(num%i==0)
                break;
        }
        return i==1;
    }

    public void generatePrimeFactors() {
        if(isPrime(num)){
            factors.add(num);
            return;
        }
        for(int i=(int)Math.sqrt(num);i>1;i--)
            if(isPrime((long) i)&&num%i==0)
                factors.add((long) i);
    }
}