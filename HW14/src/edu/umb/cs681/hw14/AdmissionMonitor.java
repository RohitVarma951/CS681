package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AdmissionMonitor {
    private int currentVisitors = 0;
    private final ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
    private final Condition tooManyVisitors = rwlock.writeLock().newCondition();
    private final Condition tooFewVisitors = rwlock.writeLock().newCondition();

    public void enter(){
        rwlock.writeLock().lock();
        try {
            while (currentVisitors > 9) {
                System.out.println("Too many visitors. Please wait for a while! " + currentVisitors);
                tooManyVisitors.await();
            }
        }catch (Exception e){
            System.out.println("Interrupted Exception");
        }
        currentVisitors++;
        System.out.println("New Visiter Entry." + currentVisitors);
        tooFewVisitors.signalAll();
        rwlock.writeLock().unlock();
    }

    public void exit(){
        rwlock.writeLock().lock();
        try {
            while (currentVisitors <= 0) {
                System.out.println("No visiters. Please wait for a while!" + currentVisitors);
                tooFewVisitors.await();
            }
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        currentVisitors--;
        System.out.println("Visiter Exited." + currentVisitors);
        tooManyVisitors.signalAll();
        rwlock.writeLock().unlock();
    }

    public int countCurrentVisitors(){
        rwlock.readLock().lock();
        try {
            return currentVisitors;
        }finally {
            rwlock.readLock().unlock();
        }
    }
}