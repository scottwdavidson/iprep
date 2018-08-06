package com.swd.concurrent;

/**
 * If your class provides more functionality rather than just running as Thread, you should implement Runnable interface
 * to provide a way to run it as Thread. If your class only goal is to run as Thread, you can extend Thread class.
 */
public class BasicThreads implements Runnable {

    public static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println("MyThread - START " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
                //Get database connection, delete unused data from DB
                doDBProcessing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread - END " + Thread.currentThread().getName());
        }

        private void doDBProcessing() throws InterruptedException {
            System.out.println("In doDBProcessing " + Thread.currentThread().getName());
            Thread.sleep(5000);
            System.out.println("Out doDBProcessing " + Thread.currentThread().getName());
        }

    }


    public static void main(String[] args) {
        Thread t1 = new Thread(new BasicThreads(), "t1");
        Thread t2 = new Thread(new BasicThreads(), "t2");
        System.out.println("Starting Runnable threads");
        t1.start();
        t2.start();

        System.out.println("Runnable Threads has been started");
        Thread t3 = new MyThread("t3");
        Thread t4 = new MyThread("t4");
        System.out.println("Starting MyThreads");
        t3.start();
        t4.start();
        System.out.println("MyThreads has been started");

    }

    @Override
    public void run() {

        System.out.println("Doing heavy processing - START " + Thread.currentThread().getName());

        try {

            Thread.sleep(1000);
            //Get database connection, delete unused data from DB
            doDBProcessing();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Doing heavy processing - END " + Thread.currentThread().getName());
    }

    protected void doDBProcessing() throws InterruptedException {
        System.out.println("In doDBProcessing " + Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("Out doDBProcessing " + Thread.currentThread().getName());

    }
}
