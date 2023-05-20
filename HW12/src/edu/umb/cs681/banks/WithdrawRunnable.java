package edu.umb.cs681.banks;

import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable{
    private BankAccount account;
    private Boolean done = false;
    private ReentrantLock lock;

    public WithdrawRunnable(BankAccount account) {
        this.account = account;
        lock = new ReentrantLock();
    }

    public void setDone(){
        done = true;
    }

    public void run(){
        lock.lock();
        try{
            while(true) {
                if(done) {
                    break;
                }
                for (int i = 0; i < 10; i++) {
                    account.withdraw(100);
                    Thread.sleep(2000);
                }
            }
        } catch (InterruptedException exception){}
        finally {
            lock.unlock();
        }
    }
}