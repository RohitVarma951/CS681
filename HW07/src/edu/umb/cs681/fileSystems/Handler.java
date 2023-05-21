package edu.umb.cs681.fileSystems;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Handler implements Runnable{
    static LinkedList<Directory> dirList = new LinkedList<>();
    private boolean done = false;

    public void setDone(){
        done = true;
    }

    private static FileSystem fs;

    @Override
    public void run() {
        if(!done){
            System.out.println(Thread.currentThread().getName());
                System.out.println(fs.getFileSystem());
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }


    public static void main(String[] args){

        fs = FileSystem.getFileSystem();

        Directory root      = new Directory(null,"Root",0, LocalDateTime.now() );
        Directory apps      = new Directory(root,"Apps",0, LocalDateTime.now() );
        Directory bin       = new Directory(root,"Bin",0, LocalDateTime.now() );
        Directory home      = new Directory(root,"Home",0, LocalDateTime.now() );
        Directory pictures  = new Directory(home,"Pictures",0, LocalDateTime.now() );
        root.appendChild(apps);
        root.appendChild(bin);
        root.appendChild(home);
        home.appendChild(pictures);
        dirList.add(root);
        dirList.add(apps);
        dirList.add(bin);
        dirList.add(home);
        dirList.add(pictures);

        File fileX = new File(apps,"file:x",1, LocalDateTime.now() );
        File fileY = new File(bin,"file:y",2, LocalDateTime.now() );
        File fileA = new File(pictures,"file:c",3, LocalDateTime.now() );
        File fileB = new File(pictures,"file:d",1, LocalDateTime.now() );
        File fileC = new File(home,"file:e",4, LocalDateTime.now() );
        File fileD = new File(root,"file:f",1, LocalDateTime.now() );
        File fileE = new File(root,"file:g",2, LocalDateTime.now() );

        System.out.println("\n Starting Threads:\n");

        Handler handler1 = new Handler();
        Handler handler2 = new Handler();
        Handler handler3 = new Handler();

        Thread thread1 = new Thread(handler1);
        Thread thread2 = new Thread(handler2);
        Thread thread3 = new Thread(handler3);

        thread1.start();
        thread2.start();
        thread3.start();

        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }

        handler1.setDone();
        handler2.setDone();
        handler3.setDone();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("\nThe Size of the directory Root: " + root.getTotalSize());
        System.out.println("The Size of the directory Apps: " + apps.getTotalSize());
        System.out.println("The Size of the directory Bin: " + bin.getTotalSize());
        System.out.println("The Size of the directory Home: " + home.getTotalSize());
        System.out.println("The Size of the directory Pictures: " + pictures.getTotalSize());
    }
}