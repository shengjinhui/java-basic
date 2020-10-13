package thread.callable;

import java.util.concurrent.*;

/**
 * @Author: yichuan
 * @Date: 2020/9/28 3:35 下午
 * @Description:
 */
public class CallableAndFutureTest {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Future<String> submit = executorService.submit(new CallbackTask());
        System.out.println(Thread.currentThread().getName() + "-主线程开始执行任务");

        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = null;
                try {
                    result = submit.get();
                    System.out.println(result);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
//        String result = null;
//        try {
//            result = submit.get();
//            System.out.println(result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        System.out.println("主线程执行任务...");
        if (executorService != null) {
            executorService.shutdown();
        }

    }
}

class CallbackTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("子线程正在执行任务,请等待5s");
        Thread.sleep(5000);
        System.out.println("子线程 任务结束");
        return "result";
    }
}
