package kz.java.core.concurrent.collections;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SynchronizedCollections {

    public static void main(String[] args) throws InterruptedException {
        List<Long> list = IntStream.range(20, 30)
                .mapToObj(Long::valueOf)
                .collect(Collectors.toList());

        var syncList = new CopyOnWriteArrayList<Long>(list);

        for (Long value : syncList) {
            System.out.printf("[%s] ", value);
        }
        System.out.println("\n---------------------------");

        new Thread(() -> {
            for (Long value : syncList) {
                System.out.printf("[%s] ", value);
            }
            System.out.println("\n---------------------------");

            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

//            syncList.add(syncList.size() - 1, 101l);
            syncList.remove(syncList.size()-1);
        }
        ).start();

        new Thread(() -> {
            for (Long value : syncList) {
                System.out.printf("[%s] ", value);
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { }
            }
            System.out.println("\n--------------------------");
//            syncList.remove(syncList.size()-1);
        }
        ).start();

        try { TimeUnit.SECONDS.sleep(12); } catch (InterruptedException e) { }

        System.out.println("**************************");
        for (Long value : syncList) {
            System.out.printf("[%s] ", value);
        }
        System.out.println("\n**************************");
    }
}
