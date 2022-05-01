package demo.java.util.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

class Solver {
    final int N;
    final float[][] data;
    final CyclicBarrier barrier;

    class Worker implements Runnable {
        int myRow;

        Worker(int row) {
            myRow = row;
        }

        @Override
        public void run() {
            while (!done()) {
                processRow(myRow);
                try {
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
            }
        }

        private void processRow(int myRow) {
            System.out.println("processRow = " + myRow);
        }

        private boolean done() {
            return false;
        }
    }

    public Solver(float[][] matrix) {
        data = matrix;
        N = matrix.length;
        barrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                mergeRows();
            }

            private void mergeRows() {
                System.out.println("mergeRows");
            }
        });
        for (int i = 0; i < N; ++i) {
            new Thread(new Worker(i)).start();
        }

        waitUntilDone();
    }

    private void waitUntilDone() {
        System.out.println("waitUntilDone");
    }

    public static void main(String[] args) {

        float[][] arr = {{22, 0, 15, 1, 32, 2, 20, 3, 18, 4}, {12, 21, 25, 19, 33}, {14, 58, 34, 24, 66}};

        Solver solver = new Solver(arr);
    }

}

