package edu.umb.cs681.hw14;

public class EntranceHandler implements Runnable{
    private AdmissionMonitor monitor;
    private Boolean done = false;

    public EntranceHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void setDone(){
        done = true;
    }

    @Override
    public void run() {
        monitor.enter();
    }
}