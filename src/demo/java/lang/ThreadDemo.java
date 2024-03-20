package demo.java.lang;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程wait/notify/notifyAll
 * <p>
 * 线程间通信
 *
 * @author jcwang
 */
public class ThreadDemo implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ThreadDemo.class);

    private static final byte[] flag = new byte[0];

    public static void main(String[] args) {

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new ThreadDemo());
        es.execute(new ThreadDemo());
        es.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (flag) {
                    log.warn(Thread.currentThread().getName() + "-->flag.notifyAll()");
                    flag.notifyAll();
//                    log.warn(Thread.currentThread().getName() + "-->flag.notify()");
//                    flag.notify();
                }
            }
        });

        es.shutdown();
    }

    @Override
    public void run() {
        synchronized (flag) {
            try {
                Thread thread = Thread.currentThread();
                String name = thread.getName();
                log.warn(name + "-->before flag.wait(),state=" + thread.getState());
                flag.wait();
                log.warn(name + "-->after flag.wait(),state=" + thread.getState());
//                Thread.sleep(1000L);
//                flag.wait();
//                log.warn(name + "-->After Thread.sleep()");
                log.warn(name + "-->end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
