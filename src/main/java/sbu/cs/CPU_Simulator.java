package sbu.cs;

import javax.annotation.processing.ProcessingEnvironment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
    For this exercise, you must simulate a CPU with a single core.
    You will receive an arraylist of tasks as input. Each task has a processing
    time which is the time it needs to run in order to fully execute.

    The CPU must choose the task with the shortest processing time and create
    a new thread for it. The main thread should wait for the task to fully
    execute and then join with it, before starting the next task.

    Once a task is fully executed, add its ID to the executed task arraylist.
    Use the tests provided in the test folder to ensure your code works correctly.
 */

public class CPU_Simulator
{
    public static class Task implements Runnable {
        private long processingTime;
        private String ID;

        public Task( String ID, long processingTime) {
            this.processingTime = processingTime;
            this.ID = ID;
        }

        public long getProcessingTime() {
            return processingTime;
        }

        public void setProcessingTime(long processingTime) {
            this.processingTime = processingTime;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        /*
                        Simulate running a task by utilizing the sleep method for the duration of
                        the task's processingTime. The processing time is given in milliseconds.
                    */
        @Override
        public void run() {
            try {
                Thread.sleep(processingTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /*
        The startProcessing function should be called at the start of your program.
        Here the CPU selects the next shortest task to run (also known as the
        shortest task first scheduling algorithm) and creates a thread for it to run.
    */
    public static ArrayList<String> startSimulation(ArrayList<Task> tasks) {
        ArrayList<String> executedTasks = new ArrayList<>();
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task p1, Task p2) {
                return (int) (p1.getProcessingTime() - p2.getProcessingTime());
            }
        });

        for(int i=0; i < tasks.size(); i++){
                Runnable runnable = new Task(tasks.get(i).getID(), tasks.get(i).getProcessingTime());
                Thread thread = new Thread(runnable);
                thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());;
            }
                executedTasks.add(tasks.get(i).getID());
        }

        return executedTasks;
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks =new ArrayList<>();
        tasks.add(new Task("a",5000));
        tasks.add(new Task("b",5000));
        tasks.add(new Task("c",2000));

        System.out.println(startSimulation(tasks));
    }
}
