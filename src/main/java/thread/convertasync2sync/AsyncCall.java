package thread.convertasync2sync;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yichuan
 * @Date: 2020/9/28 2:54 下午
 * @Description: 异步调用
 */
public class AsyncCall {

    private Random random = new Random(System.currentTimeMillis());
    private ExecutorService threadPool = Executors.newSingleThreadExecutor();


    public void call(AbstractBaseDemo demo) {
        new Thread(() -> {
            long res = random.nextInt(10);

            try {
                Thread.sleep(5);
            } catch (Exception e) {
                System.out.println(e);
            }
            demo.callback(res);

        }).start();
    }

    public void shutdown() {
        threadPool.shutdown();
    }
}
