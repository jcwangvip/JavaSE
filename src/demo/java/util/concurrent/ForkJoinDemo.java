package demo.java.util.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;        //计算的开始值
    private Long end;        //计算的最终值
    private Long max = 100L; //计算区间的最大差值
    private static AtomicInteger worker1 = new AtomicInteger(0); //分别记录每个线程执行次数
    private static AtomicInteger worker2 = new AtomicInteger(0);
    private static AtomicInteger worker3 = new AtomicInteger(0);


    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        Long sum = 0L;
        //如果计算的区间小于100直接计算
        if (end - start < max) {
            if ("ForkJoinPool-1-worker-1".equals(Thread.currentThread().getName())) {
                worker1.incrementAndGet(); //如果线程1执行就++
                System.out.println("worker1:" + worker1.get());
            }
            if ("ForkJoinPool-1-worker-2".equals(Thread.currentThread().getName())) {
                worker2.incrementAndGet(); //如果线程2执行就++
                System.out.println("worker2:" + worker2.get());
            }
            if ("ForkJoinPool-1-worker-3".equals(Thread.currentThread().getName())) {
                worker3.incrementAndGet(); //如果线程3执行就++
                System.out.println("worker3:" + worker3.get());
            }
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            // 如果计算的区间>=100,分两半继续分直到分的区间小于100为止
            Long l = (start + end) / 2;
            ForkJoinDemo left = new ForkJoinDemo(start, l);  //执行前面的一半
            //Fork（）方法 Fork()方法类似于Thread.start()，但是它并不立即执行任务，而是将任务放入工作队列中，拆分子任务。
            left.fork();
            ForkJoinDemo rigt = new ForkJoinDemo(l + 1, end); //执行后面的一半
            rigt.fork();
            try {
                sum = left.get() + rigt.get(); //每一半的最终结果加起来
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinDemo forkJoinTest = new ForkJoinDemo(1L, 10000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool(3); //线程池有3个线程
        ForkJoinTask<Long> submit = forkJoinPool.submit(forkJoinTest);
        System.out.println("执行结果为：" + submit.get());
    }


}
