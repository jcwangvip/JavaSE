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
public class SemaphoreDemo1 implements Runnable {

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
            System.out.println("release permit " + semaphore.availablePermits() + "--》" + Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(100);
        SemaphoreDemo1 test = new SemaphoreDemo1();
        for (int i = 0; i < 10; i++) {
            es.execute(new Thread(test, "线程" + i));
        }
        es.shutdown();
    }
}
