package demo.java.util.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁学习
 *
 * @author jcwang
 */
public class LockDemo {

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(() -> new LockDemo().run());
        es.execute(() -> new LockDemo().run());
        es.shutdown();

    }

    public void run() {
        System.out.println("before lock");
        lock.lock();
        System.out.println("after lock");
        try {
            System.out.println("lock");
            Thread.sleep(10000L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
