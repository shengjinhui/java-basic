package thread.flowcontroller.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 4:45 下午
 * @Description: Semaphore实现条件等待, 模拟CountDownLatch
 * 线程1只有等到线程2执行完毕才可以执行
 */
public class SimulateCountDownLatch {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire(1);

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("线程1开始执行");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("执行2开始执行");
                Thread.sleep(2000);
                semaphore.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
