package async;


import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @Author: yichuan
 * @Date: 2020/11/11 5:33 下午
 * @Description:
 */

@Component
public class CompletableFutureSetTest {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(AVAILABLE_PROCESSORS, AVAILABLE_PROCESSORS * 2,
            1, TimeUnit.MINUTES, new LinkedBlockingQueue(5), new ThreadPoolExecutor.CallerRunsPolicy());


    /**
     * 这里使用CompletableFuture来实现通知等待模型,让主线程通过调用get方法来阻塞起来
     */
    public void executeTaskByCompletableFuture() {
        CompletableFuture<String> future = new CompletableFuture<>();

        THREAD_POOL_EXECUTOR.submit(() -> {
            // 2.1 休眠模拟计算任务
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 2.2 设置计算结果到future
            System.out.println(">>>>>>" + Thread.currentThread().getName() + " set future result <<<<<<<");
            future.complete("hello,world");
        });
        // 3. 等待计算结果
        try {
            System.out.println("result:" + future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void runAsyncByCompleteFuture() throws Exception {
        CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // 模拟执行任务计算2s
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 打印日志
                System.out.println("print log");
            }
        });
        // 同步等待任务执行结束
        System.out.println(future.get());
    }
}
