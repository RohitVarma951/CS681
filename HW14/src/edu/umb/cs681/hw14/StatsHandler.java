package edu.umb.cs681.hw14;

public class StatsHandler implements Runnable{
    private AdmissionMonitor monitor;
    private Boolean done = false;

    public StatsHandler(AdmissionMonitor monitor) {
        this.monitor = monitor;
    }

    public void setDone(){
        done = true;
    }

    @Override
    public void run() {
        System.out.println(monitor.countCurrentVisitors());
    }
}
