package com.swd.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static java.lang.System.exit;

/**
 * Self test coding for concurrent
 */
public class SelfTestCallable implements Callable<SelfTestCallable.NewTask> {

    public static class NewTask {
        private int taskId;
        private String taskName;

        public int getTaskId() {
            return taskId;
        }

        public void setTaskId(int taskId) {
            this.taskId = taskId;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public String toString() {
            return "NewTask{" +
                    "taskId=" + taskId +
                    ", taskName='" + taskName + '\'' +
                    '}';
        }
    }

    private static int COUNT = 0;
    private final static Object LOCK = new Object();
    private static Random RANDOM_MILLIS = new Random(53 * 10007);


    private String name;

    public static SelfTestCallable newSelfTestCallable(String name){
        SelfTestCallable selfTestCallable = new SelfTestCallable();
        selfTestCallable.setName(name);
        return  selfTestCallable;
    }

    @Override
    public NewTask call() throws Exception {

            System.out.println("Starting " + name + " thread ... ");
            Thread.sleep(RANDOM_MILLIS.nextInt(20) * 100);
            NewTask newTask = new NewTask();
            newTask.setTaskId(incrementAndReturnCount());
            newTask.setTaskName(name + RANDOM_MILLIS.nextInt(1000));
            return newTask;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private static int incrementAndReturnCount(){
        synchronized (LOCK){
            COUNT++;
        }
        return COUNT;
    }


    public static void main(String[] args){

        // Create a List of Future<NewTask> objects
        List<Future<NewTask>> futureNewTasks = new ArrayList<Future<NewTask>>();

        // Create an Executor to execute SelfTestCallables
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // Queue up all of the work
        for(int threadCount = 0; threadCount < 25 ; threadCount++ ) {
            futureNewTasks.add(executorService.submit(SelfTestCallable.newSelfTestCallable("name" + threadCount)));
        }

        // Iterate through the Futures and get their status ( but only give them 600 milliseconds )
        List<NewTask> newTasks = new ArrayList<NewTask>();
        for(Future<NewTask> futureNewTask: futureNewTasks){
            try {
                if (!futureNewTask.isDone()) {
                    System.out.println("Waiting for future new task: " + futureNewTask.toString());
                }
                newTasks.add(futureNewTask.get(600, TimeUnit.MILLISECONDS));
            } catch (TimeoutException tex){
                System.out.println("Task: " + futureNewTask.toString() + " , timed out.");
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }

        for(NewTask newTask : newTasks){
            System.out.println("New Tasks: " + newTask.toString());
        }

        System.out.println("All done ??");
        exit(0);
    }

    public void testing(){

        ExecutorService es = Executors.newFixedThreadPool(5);
        Future futureAN = es.submit(new RR());
        if (futureAN.isDone()){
//            String an = futureAN.get();

        }

        Thread t = new Thread(new RR());
        t.run();

    }

    public static class AN implements Callable<String> {

        public String call(){

            Thread.currentThread().getName();
            return "Hi";
        }
    }

    public static class RR implements Runnable {
        public void run(){}
    }

}
