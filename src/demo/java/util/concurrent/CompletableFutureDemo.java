package demo.java.util.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;

/**
 * 异步编程
 *
 * @author jcwang
 */
public class CompletableFutureDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        test9();

//        test8();

//        test7();

//        test6();

//        test5_2();
//        test5_1();

//        test5();

//        test4();

//        test3();

//        test2();

//        test1();

    }

    private static void test9() {
        CompletableFuture<Boolean> completableFutures = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询boolean");
//            return null;
            throw new NullPointerException("");
        });
        CompletableFuture<Map<String, String>> prCodeMapFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("查询map");
//            Thread.sleep();
            HashMap<String, String> map = new HashMap<>();
            return map;
        });
        try {
            CompletableFuture.allOf(completableFutures, prCodeMapFuture).get();
            Boolean isReplace = completableFutures.get();
            System.out.println("boolean输出最后结果 -》 " + isReplace);

            Map<String, String> map = prCodeMapFuture.get();
            System.out.println("map输出最后结果 -》 " + isReplace);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    private static void test8() {
        final ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final long startAll = System.currentTimeMillis();
        final CompletableFuture[] completableFutures = IntStream.rangeClosed(1, 10).mapToObj(index -> CompletableFuture.supplyAsync(() -> {
            final long start = System.currentTimeMillis();
            try {
                Thread.sleep(100 * index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (index > 1) {
//                throw new RuntimeException("异常了");
            }
            System.out.printf("线程'%s'执行完成,花费%d毫秒%n", Thread.currentThread().getName(), System.currentTimeMillis() - start);
            return index;
        }, threadPool)).toArray(CompletableFuture[]::new);
//        final Object result = CompletableFuture.anyOf(completableFutures).join();
//        final Object result = CompletableFuture.anyOf(completableFutures).getNow(null);
        Object result = null;
        try {
            result = CompletableFuture.anyOf(completableFutures).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.printf("any of 得到结果：%s，共花费%d毫秒%n", result, System.currentTimeMillis() - startAll);
        CompletableFuture.allOf(completableFutures).join();//阻塞主线程
        System.out.printf("all of 得到结果，共花费%d毫秒%n", System.currentTimeMillis() - startAll);
    }

    private static void test7() {
        final ExecutorService threadPool = Executors.newFixedThreadPool(10);
        final long startAll = System.currentTimeMillis();
        final CompletableFuture[] completableFutures = IntStream.rangeClosed(1, 10).mapToObj(index -> CompletableFuture.supplyAsync(() -> {
            final long start = System.currentTimeMillis();
            try {
                Thread.sleep(100 * index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("线程'%s'执行完成,花费%d毫秒%n", Thread.currentThread().getName(), System.currentTimeMillis() - start);
            return index;
        }, threadPool)).toArray(CompletableFuture[]::new);
        final Object result = CompletableFuture.anyOf(completableFutures).join();
        System.out.printf("any of 得到结果：%s，共花费%d毫秒%n", result, System.currentTimeMillis() - startAll);
        CompletableFuture.allOf(completableFutures).join();//阻塞主线程
        System.out.printf("all of 得到结果，共花费%d毫秒%n", System.currentTimeMillis() - startAll);
    }

    private static void test6() {
        //任务1：洗水壶->烧开水
        CompletableFuture<Void> f1 = CompletableFuture
                .runAsync(() -> {
                    System.out.println("T1:洗水壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T1:烧开水...");
                    sleep(15, TimeUnit.SECONDS);
                });
        //任务2：洗茶壶->洗茶杯->拿茶叶
        CompletableFuture<String> f2 = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println("T2:洗茶壶...");
                    sleep(1, TimeUnit.SECONDS);

                    System.out.println("T2:洗茶杯...");
                    sleep(2, TimeUnit.SECONDS);

                    System.out.println("T2:拿茶叶...");
                    sleep(1, TimeUnit.SECONDS);
                    return "龙井";
                });
        //任务3：任务1和任务2完成后执行：泡茶
        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tf) -> {
            System.out.println("T1:拿到茶叶:" + tf);
            System.out.println("T1:泡茶...");
            return "上茶:" + tf;
        });
        //等待任务3执行结果
        System.out.println(f3.join());
    }

    static void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }

    private static void test5_2() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() -> {
            int number = new Random().nextInt(30);
            System.out.println("第一次运算：" + number);
            return number;
        }).thenCompose(integer -> CompletableFuture.supplyAsync(() -> integer + "这个数字很好"));
        String s = stringCompletableFuture.get();
        System.out.println(s);
    }


    private static void test5_1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(new Supplier<Integer>() {
            @Override
            public Integer get() {
                int number = new Random().nextInt(30);
                System.out.println("第一次运算：" + number);
                return number;
            }
        }).thenCompose(new Function<Integer, CompletionStage<String>>() {
            @Override
            public CompletionStage<String> apply(Integer integer) {
                return CompletableFuture.supplyAsync(new Supplier<String>() {
                    @Override
                    public String get() {
                        return integer + "这个数字很好";
                    }
                });
            }
        });
        String s = stringCompletableFuture.get();
        System.out.println(s);
    }

    private static void test5() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(new Supplier<Integer>() {
                    @Override
                    public Integer get() {
                        int number = new Random().nextInt(30);
                        System.out.println("第一次运算：" + number);
                        return number;
                    }
                })
                .thenCompose(new Function<Integer, CompletionStage<Integer>>() {
                    @Override
                    public CompletionStage<Integer> apply(Integer param) {
                        return CompletableFuture.supplyAsync(new Supplier<Integer>() {
                            @Override
                            public Integer get() {
                                int number = param * 2;
                                System.out.println("第二次运算：" + number);
                                return number;
                            }
                        });
                    }
                });
        Integer integer = future.get();
        System.out.println(integer);
    }

    private static void test4() throws ExecutionException, InterruptedException {

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("第一次运算：" + result);
            return result;
        }).thenApplyAsync(number -> {
            int result = number * 3;
            System.out.println("第二次运算：" + result);
            return result;
        });
        Integer integer = future.get();
        System.out.println(integer);

    }

    private static void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int result = 100;
            System.out.println("第一次运算：" + result);
            return result;
        }).thenApply(number -> {
            int result = number * 3;
            System.out.println("第二次运算：" + result);
            return result;
        });
        boolean completedExceptionally = future.isCompletedExceptionally();
        boolean done = future.isDone();
        Integer integer = future.get();
        System.out.println(integer);
    }

    private static void test2() throws ExecutionException, InterruptedException {
        Runnable runnable = () -> System.out.println("无返回结果异步任务");
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(runnable);
        Void unused = voidCompletableFuture.get();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回值的异步任务");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello World";
        });
        String result = future.get();
    }

    private static void test1() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10,
                50,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());


        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("当前线程：" + Thread.currentThread().getId());
            int i = 10 / 2;
            System.out.println("运行结果：" + i);
        }, executor);
    }
}
