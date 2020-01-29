package kz.java.core.concurrent.locks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockInterruptiblyExample {
    String resource = "Hello, World!";
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss  ");
    Lock lock;
    Thread thread1;
    Thread thread2;
    Thread thread3;

    final int TIME_WAIT = 7000;
    final int TIME_SLEEP = 5000;

    ReentrantLockInterruptiblyExample() {
        lock    = new ReentrantLock();
        thread1 = new Thread(new LockClass("first" ,
                "Первый поток"));
        thread2 = new Thread(new LockClass("second",
                "Второй поток"));
        thread3 = new Thread(new LockClass("third" ,
                "Третий поток"));

        thread1.start();
        try {
            Thread.sleep(400);
            thread2.start();
            thread3.start();
        } catch (InterruptedException e) {}

        while (thread1.isAlive() || thread2.isAlive() || thread3.isAlive()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("\nЗавершение работы примера");
        System.exit(0);
    }

    //-----------------------------------------------------
    void printMessage(final String msg) {
        String text = sdf.format(new Date());
        if (msg == null)
            text += resource;
        else
            text += msg;
        System.out.println(text);
    }

    //-----------------------------------------------------
    class LockClass implements Runnable {
        String name;
        String text;

        public LockClass(String name, String text) {
            this.name = name;
            this.text = text;
        }

        @Override
        public void run() {
            try {
                printMessage("Wait (" + name + ") ...");
                lock.lockInterruptibly();
                try {
                    Thread.sleep(2000);
                    if (name.equalsIgnoreCase("first")) {
                        printMessage("Прерывание второго потока");
                        thread2.interrupt();
                        thread2.join();
                    }
                    // доступ к ресурсу
                    resource = text;
                    printMessage(null);
                    Thread.sleep(TIME_SLEEP);
                } finally {
                    // Убираем блокировку
                    lock.unlock();
                    String text = name + " : завершил работу";
                    printMessage(text);
                }
            } catch (InterruptedException e) {
                printMessage(name + " : interrupted wait");
            }
        }
    }

    //-----------------------------------------------------
    public static void main(String[] args) {
        new ReentrantLockInterruptiblyExample();
    }
}