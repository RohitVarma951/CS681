package edu.umb.cs681.fileSystems;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement{
    private LinkedList<FSElement> children;

    public Directory(Directory parent, String name, int sizeof, LocalDateTime creationTime) {
        super(parent, name, sizeof, creationTime);
        this.children = new LinkedList<>();
    }

    public LinkedList<FSElement> getChildren(){ return this.children; }

    public void appendChild(FSElement child) { this.children.add(child); }

    public int countChildren() { return children.size(); }

    public LinkedList<Directory> getSubDirectories() {
        LinkedList<Directory> subDirectories = new LinkedList<>();
        for (FSElement fselement : children)
            if (fselement.isDirectory()) {
                Directory subDirect = (Directory)fselement;
                subDirectories.add(subDirect);
            }
        return subDirectories;
    }

    public LinkedList<File> getFiles(){
        LinkedList<File> files = new LinkedList<>();
        for (FSElement fselement : children)
            if (!fselement.isDirectory()) {
                File file = (File)fselement;
                files.add(file);
            }
        return files;
    }

    public LinkedList<Link> getLinks(){
        LinkedList<Link> links = new LinkedList<>();
        for (FSElement fselement : children)
            if (fselement.isLink()) {
                Link link = (Link)fselement;
                links.add(link);
            }
        return links;
    }

    public int getTotalSize() {
        int totalSize = 0;
        for (FSElement fselement : children) {
            if (fselement.isDirectory()) {
                Directory subDirectory = (Directory)fselement;
                totalSize += subDirectory.getTotalSize();
            }else
                totalSize += fselement.getSize();
        }
        return totalSize;
    }

    public boolean isDirectory() { return true; }

    public boolean isFile() { return false; }

    public boolean isLink() { return false; }
}