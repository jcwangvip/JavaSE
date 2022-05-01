package demo.java.util.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁学习
 *
 * @author jcwang
 */
public class FairLock implements Runnable {

    private static final Lock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        FairLock test = new FairLock();
        Thread t1 = new Thread(test, "线程1");
        Thread t2 = new Thread(test, "线程2");
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lock();
                System.err.println(Thread.currentThread().getName() + "获取到了锁！");
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

}
