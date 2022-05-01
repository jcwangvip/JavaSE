package demo.java.util.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 循环栅栏
 * <p>
 * 一种同步辅助工具，它允许一组线程都等待彼此到达一个共同的障碍点。CyclicBarriers在涉及固定大小的线程群的程序中很有用，这些线程群必须偶尔相互等待。该屏障称为循环，因为它可以在释放等待线程后重复使用。
 * 作用:等待所有的线程都完成后统一进入下一步
 * 注意:创建的时候parties这个值是要和任务匹配好,要不主程序一直等待中
 *
 * @author jcwang
 */
public class CyclicBarrierDemo {


    private static CyclicBarrier barrier1 = new CyclicBarrier(10, CyclicBarrierDemo::barrierComplete);

    private static void barrierComplete() {
        System.out.println("所有任务都已结束");
    }


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println("执行任务");
                try {
                    barrier1.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();

    }
}
