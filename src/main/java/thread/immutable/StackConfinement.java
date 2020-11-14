package thread.immutable;

import java.util.Stack;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 12:33 下午
 * @Description:
 */
public class StackConfinement implements Runnable {
    int index = 0;

    public void inThread() {
        int neverGoOut = 0;
        for (int i = 0; i < 10000; i++) {
            neverGoOut++;

        }
        System.out.println("栈内保护的数字是线程安全的: " + neverGoOut);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            index++;
        }
        inThread();
    }


    public static void main(String[] args) throws Exception {
        StackConfinement r1 = new StackConfinement();
        Thread thread = new Thread(r1);
        Thread thread1 = new Thread(r1);

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();
        System.out.println(r1.index);
    }
}
