package thread.flowcontroller.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @Author: yichuan
 * @Date: 2020/11/16 1:52 下午
 * @Description: AQS实现一个简单的线程协作器
 */
public class OneShotLatch {

    private Sync sync = new Sync();


    public void await() {
        sync.acquireShared(0);
    }

    public void signal() {
        sync.releaseShared(0);
    }

    private static final class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected int tryAcquireShared(int arg) {
            return (getState() == 1) ? 1 : -1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1);
            return true;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        OneShotLatch oneShotLatch = new OneShotLatch();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("线程" + finalI + "阻塞了,进行等待!");
                oneShotLatch.await();
                System.out.println("主流程执行完毕,线程" + finalI + "被放行了");
            }).start();
        }
        Thread.sleep(5000);
        System.out.println("啊哈哈哈哈哈");
        oneShotLatch.signal();
        new Thread(() -> {
            System.out.println("后续线程阻塞了,进行等待!");
            oneShotLatch.await();
            System.out.println("后续线程被放行了");
        }).start();

    }

}
