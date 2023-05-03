# **In the name of god**
# abolfazl keypour
# Multithread Basics

## Introduction
In this assignment, we have given three introductory exercises focused on the basics of multithreading.
## Design and Implementation
 ### `CPU Simulator`: In this code, user input some ID and thier processing time and code sort them and sleeps as long as processing time.
 - startSimulation function sorts ID by their processing time, makes new instance for sorted tasks, makes thread for them and start them and wait untill the end of threads. Actually we have one core so we can't start multithread.
 - For sorting, I override compare function to sort arraylist with attribute that I want.
### `CPU 2core Simulator`: It is like 1core, but difference is that we have 2 thread and run them.
- I used thread pool. We set one pool that we want 2 thread and give it our thread and it will handle it for us.
### `Find Multiples`: In this exercise, I ocde a multithreaded program that finds all integers in the range [1, n] that are divisible by 3, 5, or 7. Return the sum of all unique integers as answer.
- getSum function makes threads that calcute sum of divisible by choosen number. It is based on the principle of inclusion and exclusion.
- For handing race condition we use lock for sum.
### `Use Interrupts`: In this code I use interrupts in the main function to terminate threads that run for longer than 3 seconds.
- When SleepThread interrupted, in catch of sleep we get that and break it.
- When LoopThread interrupted, beaks it loop.
## Testing and Evaluation
- We have unit tests for `CPU Simulator` and `Find Multiples` and we can use them.
## Conclusion
- Sometimes we have race condition. For handeling them we can use the “synchronized” key word, atomic Variables (Atomic = non interruptible), locks (Mutex, Reentrant, etc.) and semaphores and Monitors.
- Multithreading can Increase Responsiveness, Resource Sharing and etc.