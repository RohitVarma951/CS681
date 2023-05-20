package edu.umb.cs681.singleton;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

    private ConcurrentHashMap<Path, AtomicInteger> concurrentHashMap = new ConcurrentHashMap<Path, AtomicInteger>();
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
        concurrentHashMap.putIfAbsent(path,new AtomicInteger(0));
        concurrentHashMap.get(path).incrementAndGet();
    }

    public AtomicInteger getCount(Path path) {
        concurrentHashMap.putIfAbsent(path,new AtomicInteger(0));
        return concurrentHashMap.get(path);
    }
}