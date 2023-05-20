package edu.umb.cs681.singleton;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AccessCounter {

    private HashMap<Path,Integer> hashMap = new HashMap<>();
    private ReentrantLock lock = new ReentrantLock();
    private static ReentrantLock staticLock = new ReentrantLock();
    private static AccessCounter instance = null;
    private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();

    public static AccessCounter getInstance() {
        staticLock.lock();
        try {
            if (instance == null)
                instance = new AccessCounter();
            return instance;
        }
        finally {
            staticLock.unlock();
        }
    }

    public void increment(Path path) {
        rwlock.writeLock().lock();
        try{
            if(hashMap.get(path)==null)
                hashMap.put(path,1);
            else
                hashMap.put(path,hashMap.get(path)+1);
        }finally {
            rwlock.writeLock().unlock();
        }
    }

    public int getCount(Path path) {
        rwlock.readLock().lock();
        try{
            if(hashMap.get(path)!=null){
                return hashMap.get(path);
            }else{
                return 0;
            }
        }finally {
            rwlock.readLock().unlock();
        }
    }
}