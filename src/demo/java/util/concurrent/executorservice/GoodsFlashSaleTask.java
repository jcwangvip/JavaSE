package demo.java.util.concurrent.executorservice;

public class GoodsFlashSaleTask implements Runnable {

    private static int id = 10;
    private String userName;

    public GoodsFlashSaleTask(String userName) {
        this.userName = userName;
    }

    @Override
    public void run() {

        String name = Thread.currentThread().getName();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (GoodsFlashSaleTask.class) {
            if (id > 0) {
                System.out.println(userName + "使用" + name + "秒杀:" + id-- + "号商品成功啦!");
            } else {
                System.out.println(userName + "使用" + name + "秒杀失败啦!");
            }
        }

    }


}
