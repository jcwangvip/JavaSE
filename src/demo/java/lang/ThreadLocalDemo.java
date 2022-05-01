package demo.java.lang;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadLocalDemo implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ThreadLocalDemo.class);

    protected static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    @Override
    public void run() {
        log.warn("start = " + threadLocal.get());
        threadLocal.set(Thread.currentThread().getName());
        test();
        threadLocal.remove();
    }

    private void test() {
        log.warn("test = " + threadLocal.get());
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            es.execute(new ThreadLocalDemo());
        }
        es.shutdown();
        try {
            es.awaitTermination(10L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
