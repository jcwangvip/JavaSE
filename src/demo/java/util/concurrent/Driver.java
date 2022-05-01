package demo.java.util.concurrent;

import java.util.concurrent.CountDownLatch;

public class Driver {

    void main() throws InterruptedException {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(10);

        for (int i = 0; i < 10; ++i) // create and start threads
        {
            new Thread(new Worker(startSignal, doneSignal)).start();
        }

        doSomethingElse();            // don't let run yet
        startSignal.countDown();      // let all threads proceed
        doSomethingElse();
        doneSignal.await();           // wait for all to finish
    }

    private void doSomethingElse() {
        System.out.println("doSomethingElse");
    }


}
