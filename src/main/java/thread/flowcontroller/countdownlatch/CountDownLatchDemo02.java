package thread.flowcontroller.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 3:30 下午
 * @Description:并发流程控制工具类CountDownLatch使用代码演示 多等一的场景
 * 假设运动场上跑步,五个运动员,等待裁判一声令下,所有人就开始跑步
 */
public class CountDownLatchDemo02 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch beginLatch = new CountDownLatch(1);
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            Runnable runner = () -> {
                int num = finalI + 1;
                try {
                    System.out.println("No. " + num + " runner ready!!!");
                    // 运动员进入等待状态
                    beginLatch.await();
                    System.out.println("No. " + num + " start running");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            threadPool.submit(runner);
        }

        // 裁判开始准备了
        Thread.sleep(5000);
        System.out.println("发令枪响,比赛开始....");
        beginLatch.countDown();
        threadPool.shutdown();
    }
}
