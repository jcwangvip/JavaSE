package demo.java.util.concurrent.executorservice;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 商品秒杀
 *
 * <p>案例介绍:
 * 假如某网上商城推出活动,新上架10部新手机免费送客户体验,要求所有参与活动的人员在规定的时间同时参与秒杀挣抢,假如有20人同时参与了该活动,请使用线程池模拟这个场景,保证前10人秒杀成功,后10人秒杀失败;
 * 要求:
 * 1:使用线程池创建线程
 * 2:解决线程安全问题
 * 思路提示:
 * 1:既然商品总数量是10个,那么我们可以在创建线程池的时候初始化线程数是10个及以下,设计线程池最大数量为10个;
 * 2:当某个线程执行完任务之后,可以让其他秒杀的人继续使用该线程参与秒杀;
 * 3:使用synchronized控制线程安全,防止出现错误数据;
 * 代码步骤:
 * 1:编写任务类,主要是送出手机给秒杀成功的客户;
 * 2:编写主程序类,创建20个任务(模拟20个客户);
 * 3:创建线程池对象并接收20个任务,开始执行任务;</p>
 */
public class GoodsFlashSaleMain {

    public static void main(String[] args) {
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,
//                3,
//                1, TimeUnit.MINUTES,
//                new LinkedBlockingDeque<>(15),
//                new ThreadPoolExecutor.DiscardPolicy());
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(15));
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(15));
        try {
            for (int i = 1; i <= 20; i++) {
                GoodsFlashSaleTask task = new GoodsFlashSaleTask("客户" + i);
                pool.submit(task);
            }
        } catch (Exception e) {
            System.out.println("提交任务异常:" + e.getMessage());
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}
