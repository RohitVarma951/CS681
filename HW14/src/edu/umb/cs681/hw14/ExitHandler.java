package edu.umb.cs681.hw14;

public class ExitHandler implements Runnable{
    private AdmissionMonitor monitor;
    private Boolean done = false;

    public ExitHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void setDone(){
        done = true;
    }

    public void run(){
        monitor.exit();
    }
}
