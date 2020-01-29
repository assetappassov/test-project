package kz.java.core.concurrent.synchronizers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreExampleTwo {

    public static void main(String[] args) {

        Semaphore sem = new Semaphore(2); // 1 разрешение
        CommonResource res = new CommonResource();
        new Thread(new CountThread(res, sem, "CountThread 1")).start();
        new Thread(new CountThread(res, sem, "CountThread 2")).start();
        new Thread(new CountThread(res, sem, "CountThread 3")).start();
        new Thread(new CountThread(res, sem, "CountThread 4")).start();
        new Thread(new CountThread(res, sem, "CountThread 5")).start();
        new Thread(new CountThread(res, sem, "CountThread 6")).start();
        new Thread(new CountThread(res, sem, "CountThread 7")).start();
        new Thread(new CountThread(res, sem, "CountThread 8")).start();
        new Thread(new CountThread(res, sem, "CountThread 9")).start();
        new Thread(new CountThread(res, sem, "CountThread 10")).start();
    }
}

class CommonResource {
    AtomicInteger x = new AtomicInteger(0);

    public int incrementAndGet() {
        System.out.println("trying get resource");
        return x.incrementAndGet();
    }
}

class CountThread implements Runnable {

    CommonResource res;
    Semaphore sem;
    String name;

    CountThread(CommonResource res, Semaphore sem, String name) {
        this.res = res;
        this.sem = sem;
        this.name = name;
    }

    public void run() {

        try {
            System.out.println(name + " ожидает разрешение");
            sem.acquire();
            System.out.println(this.name + ", resource = " + res.incrementAndGet());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(name + ", освобождает разрешение");
        sem.release();
    }
}