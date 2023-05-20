package edu.umb.cs681.banks;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2 implements BankAccount{
    private double balance = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition sufficientFundsCondition = lock.newCondition();
    private final Condition belowUpperLimitFundsCondition = lock.newCondition();

    public void deposit(double amount){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().threadId() + " (d): Lock obtained");
            System.out.println(Thread.currentThread().threadId() + " (d): current balance: " + balance);
            while(balance >= 300){
                System.out.println(Thread.currentThread().threadId() + " (d): await(): Balance exceeds the upper limit.");
                belowUpperLimitFundsCondition.await();
            }
            balance += amount;
            System.out.println(Thread.currentThread().threadId() + " (d): new balance: " + balance);
            sufficientFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println(Thread.currentThread().threadId() + " (d): Lock released");
        }
    }

    public void withdraw(double amount){
        lock.lock();
        try{
            System.out.println(Thread.currentThread().threadId() + " (w): Lock obtained");
            System.out.println(Thread.currentThread().threadId() + " (w): current balance: " + balance);
            while(balance <= 0){
                System.out.println(Thread.currentThread().threadId() + " (w): await(): Insufficient funds");
                sufficientFundsCondition.await();
            }
            balance -= amount;
            System.out.println(Thread.currentThread().threadId() + " (w): new balance: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        }
        catch (InterruptedException exception){
            exception.printStackTrace();
        }
        finally{
            lock.unlock();
            System.out.println(Thread.currentThread().threadId() + " (w): Lock released");
        }
    }

    public double getBalance() {
        lock.lock();
        try{
            return this.balance;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args){
        ThreadSafeBankAccount2 bankAccount = new ThreadSafeBankAccount2();
        DepositRunnable depositRunnable[] = new DepositRunnable[4];
        depositRunnable[0] = new DepositRunnable(bankAccount);
        depositRunnable[1] = new DepositRunnable(bankAccount);
        depositRunnable[2] = new DepositRunnable(bankAccount);
        depositRunnable[3] = new DepositRunnable(bankAccount);
        WithdrawRunnable withdrawRunnable[] = new WithdrawRunnable[3];
        withdrawRunnable[0] = new WithdrawRunnable(bankAccount);
        withdrawRunnable[1] = new WithdrawRunnable(bankAccount);
        withdrawRunnable[2] = new WithdrawRunnable(bankAccount);

        Thread t1 = new Thread(depositRunnable[0]);
        Thread t2 = new Thread(depositRunnable[1]);
        Thread t3 = new Thread(depositRunnable[2]);
        Thread t4 = new Thread(depositRunnable[3]);

        Thread t5 = new Thread(withdrawRunnable[0]);
        Thread t6 = new Thread(withdrawRunnable[1]);
        Thread t7 = new Thread(withdrawRunnable[2]);

        t1.start(); t2.start(); t3.start(); t4.start();
        t5.start(); t6.start(); t7.start();

        try{
            Thread.sleep(5000);
        }catch (Exception e){}

        depositRunnable[0].setDone();
        withdrawRunnable[0].setDone();
        depositRunnable[1].setDone();
        withdrawRunnable[1].setDone();
        depositRunnable[2].setDone();
        withdrawRunnable[2].setDone();
        depositRunnable[3].setDone();

        t1.interrupt(); t2.interrupt(); t3.interrupt(); t4.interrupt();
        t5.interrupt(); t6.interrupt(); t7.interrupt();
    }
}