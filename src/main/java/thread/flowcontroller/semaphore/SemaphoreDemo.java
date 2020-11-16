package thread.flowcontroller.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 4:28 下午
 * @Description:
 */
public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(3, true);

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            threadPool.submit(new SlowTask());
        }

        threadPool.shutdown();
    }


    static class SlowTask implements Runnable {
        @Override
        public void run() {
            try {
                semaphore.acquire();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "获取到许可证...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放许可证...");
            semaphore.release();
        }
    }
}
