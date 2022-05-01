package demo.java.util.concurrent;

import java.security.SecureRandom;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 *
 * @author jcwang
 */
public class SemaphoreDemo implements Runnable {

    private static final Semaphore semaphore = new Semaphore(5, true);
    private static final SecureRandom random = new SecureRandom();

    @Override
    public void run() {
        System.out.println("Acquire permit");
        try {
            semaphore.acquire();
            System.out.println("got permit " + semaphore.availablePermits() + "--》" + Thread.currentThread().getName());
            Thread.sleep(1000L + random.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.println("release permit " + semaphore.availablePermits()+ "--》" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            es.execute(new SemaphoreDemo());
        }
        es.shutdown();
    }
}
