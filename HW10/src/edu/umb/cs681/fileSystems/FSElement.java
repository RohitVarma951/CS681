package edu.umb.cs681.fileSystems;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    private Directory parent;
    protected ReentrantLock lock;

    public FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
        if(parent!=null)
            parent.appendChild(this);
        else
            this.parent = null;
        this.name = name;
        if(isFile())
            this.size = size;
        else
            this.size = 0;
        this.creationTime = creationTime;
        this.lock = new ReentrantLock();
    }

    public void setParent(Directory parent) {
        lock.lock();
        try{
            this.parent = parent;
        }finally {
            lock.unlock();
        }
    }

    public Directory getParent() {
        lock.lock();
        try{
            return parent;
        }finally {
            lock.unlock();
        }
    }

    public String getName() {
        lock.lock();
        try {
            return name;
        }finally {
            lock.unlock();
        }
    }

    public void setName(String name) {
        lock.lock();
        try {
            this.name = name;
        }finally {
            lock.unlock();
        }
    }

    public int getSize() {
        lock.lock();
        try {
            return size;
        }finally {
            lock.unlock();
        }
    }

    public void setSize(int size) {
        lock.lock();
        try {
            this.size = size;
        }finally {
            lock.unlock();
        }
    }

    public LocalDateTime getCreationTime() { return creationTime; }

    public abstract boolean isDirectory();
    public abstract boolean isFile();
    public abstract boolean isLink();
}