package demo.java.lang;

/**
 * InterruptedException 测试demo
 *
 * <p>
 * 1.public static boolean interrupted(); // 检测当前线程是否已经中断，此方法会清除中断状态，也就是说，假设当前线程中断状态为true，第一次调此方法，将返回true，表明的确已经中断了，但是第二次调用后，将会返回true，因为第一次调用的操作已将中断状态重新置为false了。
 * <p>
 * 2.public boolean isInterrupted() ; // 检测当前线程是否已经中断，此方法与上一方法的区别在于此方法不会清除中断状态。
 * <p>
 * 3.public void interrupt(); //将线程中断状态设置为true，表明此线程目前是中断状态。此时如果调用isInterrupted方法，将会得到true的结果。
 * </p>
 * <p>
 * interrupt方法本质上不会进行线程的终止操作的，它不过是改变了线程的中断状态。
 * 而改变了此状态带来的影响是，部分可中断的线程方法（比如Object.wait, Thread.sleep）会定期执行isInterrupted方法，检测到此变化，随后会停止阻塞并抛出InterruptedException异常
 * </p>
 *
 * @author jcwang
 */
public class InterruptedExceptionDemo {

    public static void main(String[] args) throws Exception {
        /**
         *
         *
         */
        System.out.println("初始中断状态：" + Thread.currentThread().isInterrupted());
        Thread.currentThread().interrupt();
        System.out.println("执行完interrupt方法后，中断状态：" + Thread.currentThread().isInterrupted());


        System.out.println("首次调用interrupted方法返回结果：" + Thread.currentThread().interrupted());
        System.out.println("此时中断状态：" + Thread.currentThread().isInterrupted());
        System.out.println("第二次调用interrupted方法返回结果：" + Thread.currentThread().interrupted());
        System.out.println("此时中断状态：" + Thread.currentThread().isInterrupted());

    }
}
