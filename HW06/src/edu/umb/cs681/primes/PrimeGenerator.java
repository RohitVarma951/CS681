package edu.umb.cs681.primes;

import java.util.*;

public class PrimeGenerator {
    protected LinkedList<Long> primes = new LinkedList<>();
    protected long start,end;

    public PrimeGenerator(long start,long end){
        if(start>0&&end>0&&start<end) {
            this.start = start;
            this.end = end;
        }else {
            throw new RuntimeException("Give Proper Ranges for start and end.\nstart and end should be greater than 0.\nend should be greater than start");
        }
    }

    public List<Long> getPrimes(){
        return primes;
    }

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

    public void generatePrimes(){
        for (long i=start;i<=end;i++)
            if(isPrime(i))
                primes.add(i);
    }
}
