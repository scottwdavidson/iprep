package com.swd.concurrent;

public class SelfTestBasicThread implements Runnable {

    private int dbConnectionInfo;

    public SelfTestBasicThread(int dbConnectionInfo) {
        this.dbConnectionInfo = dbConnectionInfo;
    }

    public void run() {

        // Use the DB connection info (ficticious) to perform some task
        System.out.println("Performing some DB task ...  (" + Thread.currentThread().getName() + ")");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Done ... (" + Thread.currentThread().getName() + ")");
    }


    public static void main(String[] args) {

        for ( int i = 0; i < 10; i++) {
            Thread aThread = new Thread(new SelfTestBasicThread(44), "t" + i);
            aThread.start();
        }

    }

}
