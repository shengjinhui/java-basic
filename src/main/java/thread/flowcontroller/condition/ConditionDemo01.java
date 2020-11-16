package thread.flowcontroller.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 5:09 下午
 * @Description: Condition演示demo01
 */
public class ConditionDemo01 {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private void method1() {

        lock.lock();
        try {
            System.out.println("条件不满足,开始await...");
            condition.await();
            System.out.println("条件满足了,开始执行后续任务...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    private void method2() {
        lock.lock();
        try {
            System.out.println("唤醒操作");
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ConditionDemo01 conditionDemo01 = new ConditionDemo01();
        // 注意: 这里必须先开启线程,才能执行method1,否则会主线程会一直阻塞
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            conditionDemo01.method2();
        }).start();
        conditionDemo01.method1();
    }
}
