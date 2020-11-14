package thread;


import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: yichuan
 * @Date: 2020/11/3 6:13 下午
 * @Description: 可见性问题
 */
public class VisibilityBugTest {
    private static AtomicLong count = new AtomicLong(0);

    private void add() {
        int init = 0;
        while (init++ < 10000) {
            count.addAndGet(1);
        }
    }

    public static long calc() throws InterruptedException {
        final VisibilityBugTest visibilityBugTest = new VisibilityBugTest();

        Thread th1 = new Thread(() -> visibilityBugTest.add());
        Thread th2 = new Thread(() -> visibilityBugTest.add());

        th1.start();
        th2.start();

        // 等待线程结束
        th1.join();
        th2.join();
        return count.get();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(calc());
    }
}
