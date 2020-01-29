package kz.java.core.concurrent.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicFieldUpdaterExample {

    private static class Test {
        volatile int count;
        volatile String size;

        public int getCount() {
            return count;
        }

        public String getSize() {
            return size;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerFieldUpdater<Test> countFieldUpdater =
                AtomicIntegerFieldUpdater.newUpdater(Test.class, "size");
        Test test = new Test();

        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            es.execute(() -> {
                for (int c = 0; c < 100; c++) {
                    countFieldUpdater.incrementAndGet(test);
                }
            });
        }
        es.shutdown();
        es.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("count: " + test.getCount());
    }
}