package thread.flowcontroller.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 3:45 下午
 * @Description: 结合一等多和多等一的场景
 * 假设运动场上跑步,五个运动员,等待裁判一声令下,所有人就开始跑步,在终点的时候,裁判员会一个个计时相应的运动员,等他们全部跑完时,比赛结束
 */
public class CountDownLatchDemoWith1And2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch beginLatch = new CountDownLatch(1);
        CountDownLatch endLatch = new CountDownLatch(5);
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
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("No. " + num + " runner 跑步结束...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    endLatch.countDown();
                }
            };
            threadPool.submit(runner);
        }

        // 裁判开始准备了
        Thread.sleep(5000);
        System.out.println("发令枪响,比赛开始....");
        beginLatch.countDown();
        System.out.println("裁判员等待所有人跑完....");
        endLatch.await();
        System.out.println("所有人跑完了,比赛结束...");
        threadPool.shutdown();
    }
}
