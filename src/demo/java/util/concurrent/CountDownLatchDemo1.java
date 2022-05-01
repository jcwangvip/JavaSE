package demo.java.util.concurrent;

/**
 * 倒计时锁
 * <p>
 * 一种同步辅助工具，它允许一个或多个线程等待，直到在其他线程中执行的一组操作完成。
 *
 * @author jcwang
 */
public class CountDownLatchDemo1 {

    public static void main(String[] args) throws InterruptedException {
        new Driver().main();
    }

}
