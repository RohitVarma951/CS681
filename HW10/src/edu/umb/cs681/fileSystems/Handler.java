package edu.umb.cs681.fileSystems;

import java.time.LocalDateTime;

public class Handler implements Runnable{
    private boolean done = false;

    public void setDone(){
        done = true;
    }

    private static FileSystem fs;

    @Override
    public void run() {
        if(!done){
            int i = (int) Thread.currentThread().threadId();
            System.out.println("("+i+")"+fs.getRootDirectories());
            fs.getRootDirectories().getFirst().getChildren().getLast().setName("abcd");
            System.out.println("("+i+")"+fs.getRootDirectories().getFirst().getName());
            System.out.println("("+i+")"+fs.getRootDirectories().getFirst().getChildren().getLast().getName());
            System.out.println("("+i+")"+fs.getRootDirectories().getLast().getChildren().getFirst().getCreationTime());
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

        fs.appendRootDirectory(root);
        fs.getRootDirectories().getFirst().getChildren().forEach(e->{System.out.println(e.getName());});

        File fileX = new File(apps,"file:x",1, LocalDateTime.now() );
        File fileY = new File(bin,"file:y",2, LocalDateTime.now() );
        File fileA = new File(pictures,"file:c",3, LocalDateTime.now() );
        File fileB = new File(pictures,"file:d",1, LocalDateTime.now() );
        File fileC = new File(home,"file:e",4, LocalDateTime.now() );
        Link linkD = new Link(root,"file:f",0, LocalDateTime.now(),pictures);
        Link linkE = new Link(root,"file:g",0, LocalDateTime.now(),fileX);

        System.out.println("\n Starting Threads:\n");

        Handler[] handler = new Handler[15];
        for (int i = 0; i < 15 ; i++)
            handler[i] = new Handler();

        Thread[] thread = new Thread[15];
        for (int i = 0; i < 15; i++)
            thread[i] = new Thread(handler[i]);

        for (int i = 0; i < 15; i++)
            thread[i].start();

        try{
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }

        for (int i = 0; i < 15; i++)
            handler[i].setDone();

        try {
            for (int i = 0; i < 15; i++)
                thread[i].join();
        }catch (Exception e){
            e.printStackTrace();
        }
//        System.out.println("\nThe Size of the directory Root: " + root.getTotalSize());
//        System.out.println("The Size of the directory Apps: " + apps.getTotalSize());
//        System.out.println("The Size of the directory Bin: " + bin.getTotalSize());
//        System.out.println("The Size of the directory Home: " + home.getTotalSize());
//        System.out.println("The Size of the directory Pictures: " + pictures.getTotalSize());
    }
}