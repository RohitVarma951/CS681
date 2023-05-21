package edu.umb.cs681.fileSystems;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class Directory extends FSElement{
    private LinkedList<FSElement> children;
    private ReentrantLock lock;

    public Directory(Directory parent, String name, int sizeof, LocalDateTime creationTime) {
        super(parent, name, sizeof, creationTime);
        this.children = new LinkedList<>();
        this.lock = new ReentrantLock();
    }

    public LinkedList<FSElement> getChildren(){
        lock.lock();
        try {
            return this.children;
        } finally {
            lock.unlock();
        }
    }

    public void appendChild(FSElement child) {
        lock.lock();
        try {
            this.children.add(child);
        } finally {
            lock.unlock();
        }
    }

    public int countChildren() {
        lock.lock();
        try {
            return children.size();
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> subDirectories = new LinkedList<>();
        lock.lock();
        try {
            for (FSElement fselement : children)
                if (fselement.isDirectory()) {
                    Directory subDirect = (Directory) fselement;
                    subDirectories.add(subDirect);
                }
        } finally {
            lock.unlock();
        }
        return subDirectories;
    }

    public LinkedList<File> getFiles(){
        LinkedList<File> files = new LinkedList<>();
        lock.lock();
        try {
        for (FSElement fselement : children)
            if (!fselement.isDirectory()) {
                File file = (File)fselement;
                files.add(file);
            }
        } finally {
            lock.unlock();
        }
        return files;
    }

    public LinkedList<Link> getLinks(){
        LinkedList<Link> links = new LinkedList<>();
        lock.lock();
        try {
            for (FSElement fselement : children)
                if (fselement.isLink()) {
                    Link link = (Link)fselement;
                    links.add(link);
                }
        } finally {
            lock.unlock();
        }
        return links;
    }

    public int getTotalSize() {
        int totalSize = 0;
        lock.lock();
        try {
            for (FSElement fselement : children) {
                if (fselement.isDirectory()) {
                    Directory subDirectory = (Directory)fselement;
                    totalSize += subDirectory.getTotalSize();
                }else
                    totalSize += fselement.getSize();
            }
        } finally {
            lock.unlock();
        }
        return totalSize;
    }

    public boolean isDirectory() { return true; }

    public boolean isFile() { return false; }

    public boolean isLink() { return false; }
}