package kz.java.core.concurrent;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;

import java.text.SimpleDateFormat;

public class CallableExample {

    //-----------------------------------------------------
    public static void main(String args[]) {
        new CallableExample();
        String s;
        s = "Тестовая строка, используемая для разложения на слова";
        StringTokenizer st = new StringTokenizer(s, " \t\n\r,.");
        while (st.hasMoreTokens()) {
            // Выводим лексемы в консоль
            System.out.println(st.nextToken());
        }
    }

    //-----------------------------------------------------
    // Класс, реализующий интерфейс Callable
    class CallableClass implements Callable<String> {
        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            // наименование потока, выполняющего
            // callable задачу
            return Thread.currentThread().getName();
        }
    }

    public CallableExample() {
        // Определяем пул из трех потоков
        ExecutorService executor;
        executor = Executors.newFixedThreadPool(3);

        // Список ассоциированных с Callable задач Future
        List<Future<String>> futures;

        futures = new ArrayList();

        // Создание экземпляра Callable класса
        Callable<String> callable = new CallableClass();

        for (int i = 0; i < 3; i++) {
            /*
             * Стартуем возвращаюший результат исполнения
             * в виде объекта Future поток
             */
            Future<String> future;
            future = executor.submit(callable);
            /*
             * Добавляем объект Future в список для
             * отображения результат выполнения (получение
             * наименования потока)
             */
            futures.add(future);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        for (Future<String> future : futures) {
            try {
                // Выводим в консоль полученное значение
                String text = sdf.format(new Date()) + " " + future.get();
                System.out.println(text);
            } catch (InterruptedException | ExecutionException e) {
            }
        }
        // Останавливаем пул потоков
        executor.shutdown();
    }
}