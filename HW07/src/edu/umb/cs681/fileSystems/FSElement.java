package edu.umb.cs681.fileSystems;

import java.time.LocalDateTime;

public abstract class FSElement {
    protected String name;
    protected int size;
    protected LocalDateTime creationTime;
    private Directory parent;

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
    }

    public void setParent(Directory parent) { this.parent = parent; }

    public Directory getParent() { return parent; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getSize() { return size; }

    public void setSize(int size) { this.size = size; }

    public LocalDateTime getCreationTime() { return creationTime; }

    public abstract boolean isDirectory();
    public abstract boolean isFile();
    public abstract boolean isLink();
}