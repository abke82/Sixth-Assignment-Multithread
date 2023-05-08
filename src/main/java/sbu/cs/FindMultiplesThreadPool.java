package sbu.cs;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//Thread pool for bones

/*
    In this exercise, you must write a multithreaded program that finds all
    integers in the range [1, n] that are divisible by 3, 5, or 7. Return the
    sum of all unique integers as your answer.
    Note that an integer such as 15 (which is a multiple of 3 and 5) is only
    counted once.

    The Positive integer n > 0 is given to you as input. Create as many threads as
    you need to solve the problem. You can use a Thread Pool for bonus points.

    Example:
    Input: n = 10
    Output: sum = 40
    Explanation: Numbers in the range [1, 10] that are divisible by 3, 5, or 7 are:
    3, 5, 6, 7, 9, 10. The sum of these numbers is 40.

    Use the tests provided in the test folder to ensure your code works correctly.
 */

public class FindMultiplesThreadPool
{

    // TODO create the required multithreading class/classes using your preferred method.
    public static class Doing implements Runnable {
        int n;
        int magh;
        int result;
        static Lock lock = new ReentrantLock();

        public Doing(int n, int magh) {
            this.n = n;
            this.magh = magh;
        }

        @Override
        public void run() {
            for(int i=magh ;i <= n; i++){
                if(i % magh == 0) result += i;
            }
            if ((this.magh == 15) || (this.magh == 21) || (this.magh == 35)) result *= -1;

            lock.lock();
            sum+= result;
            lock.unlock();
        }
    }

    /*
    The getSum function should be called at the start of your program.
    New Threads and tasks should be created here.
    */
    static int sum = 0;
    public static int getSum(int n) {

        ArrayList<Runnable> runnables = new ArrayList<>();

        runnables.add(new Doing(n, 3));
        runnables.add(new Doing(n, 5));
        runnables.add(new Doing(n, 7));
        runnables.add(new Doing(n, 15));
        runnables.add(new Doing(n, 21));
        runnables.add(new Doing(n, 35));
        runnables.add(new Doing(n, 105));


        ExecutorService threadPool = Executors.newFixedThreadPool(7);
        for (Runnable task : runnables) {
            threadPool.execute(task);
        }
        threadPool.shutdown();

        try {
            threadPool.awaitTermination(10000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return sum;
    }

    public static void main(String[] args) {
        int n = 10;
        System.out.println(getSum(n));
        n= 1000;
        System.out.println(getSum(n));
    }
}
