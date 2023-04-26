package edu.umb.cs681.singleton;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

    private HashMap<Path,Integer> hashMap = new HashMap<>();
    private ReentrantLock lock = new ReentrantLock();
    private static ReentrantLock staticLock = new ReentrantLock();
    private static AccessCounter instance = null;

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
        lock.lock();
        try{
            if(hashMap.get(path)==null)
                hashMap.put(path,1);
            else
                hashMap.put(path,hashMap.get(path)+1);
        }finally {
            lock.unlock();
        }
    }

    public int getCount(Path path) {
        lock.lock();
        try{
            if(hashMap.get(path)!=null){
                return hashMap.get(path);
            }else{
                return 0;
            }
        }finally {
            lock.unlock();
        }
    }
}