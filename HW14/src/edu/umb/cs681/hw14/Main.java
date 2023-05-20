package edu.umb.cs681.hw14;

public class Main implements Runnable {
    public void run(){}

    public static void main(String[] args){
        AdmissionMonitor monitor = new AdmissionMonitor();
        EntranceHandler entrant = new EntranceHandler(monitor);
        ExitHandler exitant = new ExitHandler(monitor);
        StatsHandler stats = new StatsHandler(monitor);

        Thread[] t1 = new Thread[20];
        Thread[] t2 = new Thread[8];
        Thread[] t3 = new Thread[5];

        int i;
        for(i=0;i<20;i++)
            t1[i] = new Thread(entrant);
        for(i=0;i<8;i++)
            t2[i] = new Thread(exitant);
        for(i=0;i<5;i++)
            t3[i] = new Thread(stats);

        for(i=0;i<20;i++)
            t1[i].start();
        for(i=0;i<8;i++)
            t2[i].start();
        for(i=0;i<5;i++)
            t3[i].start();

        entrant.setDone();
        exitant.setDone();
        stats.setDone();

        try {
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }

        for(i=0;i<20;i++)
            t1[i].interrupt();
        for(i=0;i<8;i++)
            t2[i].interrupt();
        for(i=0;i<5;i++)
            t3[i].interrupt();

        try {
            for(i=0;i<20;i++)
                t1[i].join();
            for(i=0;i<8;i++)
                t2[i].join();
            for(i=0;i<5;i++)
                t3[i].join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}