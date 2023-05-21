package edu.umb.cs681.fileSystems;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem {
    private static FileSystem instance = null;
    private final LinkedList<Directory> rootDirectories = new LinkedList<>();
    private static final ReentrantLock lock = new ReentrantLock();

    private FileSystem() {}

    public static FileSystem getFileSystem() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new FileSystem();
            }
            System.out.println(instance);
            return instance;
        }finally {
            lock.unlock();
        }
    }

    public LinkedList<Directory> getRootDirectories(){
        lock.lock();
        try { return rootDirectories;
        } finally {
            lock.unlock();
        }
    }

    public void appendRootDirectory(Directory rootdir) {
        lock.lock();
        try { rootDirectories.add(rootdir);
        } finally {
            lock.unlock();
        }
    }
}