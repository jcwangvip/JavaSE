package demo.java.util.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计时锁
 * <p>
 * 一种同步辅助工具，它允许一个或多个线程等待，直到在其他线程中执行的一组操作完成。
 *
 * @author jcwang
 */
public class CountDownLatchDemo {

    private final static int threadCount = 200;

    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println("当前线程" + Thread.currentThread().getName() + "|||" + finalI);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        System.out.println("countDownLatch.await() after ..." + countDownLatch.getCount());
        executorService.shutdown();


    }
}
