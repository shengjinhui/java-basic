package async;

import org.springframework.stereotype.Component;

import java.util.PrimitiveIterator;
import java.util.concurrent.*;

/**
 * @Author: yichuan
 * @Date: 2020/11/10 6:47 下午
 * @Description: 显式使用线程实现异步编程
 */
@Component("asyncByNewThread")
public class AsyncByNewThread {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS, AVAILABLE_PROCESSORS * 2,
            1, TimeUnit.MINUTES, new LinkedBlockingQueue(5), new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 同步执行,costTime:4.015s
     */
    public void executeBySync() {
        taskA();
        taskB();
    }

    /**
     * 显式开启一个线程进行异步处理,costTime: 2.013s
     *
     * @throws InterruptedException
     */
    public void executeByAsync01() throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                taskA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        taskB();
        thread.join();
    }

    /**
     * 使用线程池管理线程
     *
     * @throws InterruptedException
     */
    public void executeByThreadPool() throws InterruptedException {
        THREAD_POOL_EXECUTOR.execute(() -> {
            try {
                taskA();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        taskB();
    }

    /**
     * 使用Future获取线程执行结果
     * time:2.017s
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void executeByThreadPoolAndFuture() throws ExecutionException, InterruptedException {
        Future<?> resultA = THREAD_POOL_EXECUTOR.submit(() -> taskA());
        Future<?> resultB = THREAD_POOL_EXECUTOR.submit(() -> taskB());
        // 同步等待结果
        System.out.println(resultA.get());
        System.out.println(resultB.get());
    }

    private String taskA() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------doSomethingA------");
        return "taskA done!";
    }

    private String taskB() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("------doSomethingB-------");
        return "taskB done!";
    }
}

