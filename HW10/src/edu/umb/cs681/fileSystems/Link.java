package edu.umb.cs681.fileSystems;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public class Link extends FSElement {
    private FSElement target;
    private ReentrantLock lock;

    public Link(Directory parent, String name, int sizeof, LocalDateTime creationTime, FSElement target) {
        super(parent, name, sizeof, creationTime);
        this.target = target;
        this.lock = new ReentrantLock();
    }

    public boolean isDirectory() { return false; }

    public boolean isFile() { return false; }

    public boolean isLink() { return true; }

    public void setTarget(FSElement target) {
        lock.lock();
        try {
            this.target = target;
        } finally {
            lock.unlock();
        }
    }

    public FSElement getTarget() {
        lock.lock();
        try {
            return target;
        } finally {
            lock.unlock();
        }
    }
}