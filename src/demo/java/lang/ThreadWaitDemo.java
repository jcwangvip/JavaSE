package demo.java.lang;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程wait
 *
 * @author jcwang
 */
public class ThreadWaitDemo implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ThreadWaitDemo.class);

    private static final byte[] flag = new byte[0];

    public static void main(String[] args) {

        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new ThreadWaitDemo());
        es.execute(() -> {
            synchronized (flag) {
                log.warn(Thread.currentThread().getName() + "-->flag.notifyAll()");
                flag.notifyAll();
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
                // 一直等待,需要其他线程介入才能重新触发
//                flag.wait();
                // 单位是毫秒,等待时间过后,重新进入RUNNABLE状态
                // 可不用其他线程触发,触发也能提前结束等待状态
                flag.wait(10000);
                log.warn(name + "-->after flag.wait(),state=" + thread.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
