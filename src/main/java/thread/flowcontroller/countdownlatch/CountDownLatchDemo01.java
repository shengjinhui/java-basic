package thread.flowcontroller.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 3:12 下午
 * @Description: 并发流程控制工具类CountDownLatch使用代码演示
 * 一等多的场景
 *    => 假设工厂生产车间,每个零件最后的合格判定,需要5个工人来判定才算生产成功
 */
public class CountDownLatchDemo01 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Runnable runnable = () -> {
                try {
                    System.out.println("No." + (finalI+1) + " worker start checking ");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("No." + (finalI+1) + " worker finish checking");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            };
            threadPool.submit(runnable);
        }

        System.out.println("等待工人们检查零件....");
        countDownLatch.await();

        System.out.println("检查完毕...");
        threadPool.shutdown();
    }
}
