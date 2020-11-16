package thread.flowcontroller.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 5:25 下午
 * @Description:
 */
public class ConditionDemo02 {

    int queueSize = 10;
    private PriorityQueue<Integer> priorityQueue = new PriorityQueue(queueSize);
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ConditionDemo02 conditionDemo02 = new ConditionDemo02();

        Producer producer = conditionDemo02.new Producer();
        Consumer consumer = conditionDemo02.new Consumer();
        consumer.start();
        producer.start();


    }

    class Consumer extends Thread {

        @Override
        public void run() {
            try {
                consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    while (priorityQueue.size() == 0) {
                        System.out.println("[消费者] 队列空了,没有消息消费了");
                        notEmpty.await();
                    }
                    priorityQueue.poll();
                    notFull.signalAll();
                    System.out.println("从队列中取出一个数据,队列剩余" + priorityQueue.size() + "个元素");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            produce();

        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    while (priorityQueue.size() == queueSize) {
                        System.out.println("[生产者] 队列满了,别生产喽");
                        notFull.await();
                    }
                    priorityQueue.offer(1);
                    notEmpty.signalAll();
                    System.out.println("生产一个消息到队列中去了,队列还有" + (queueSize - priorityQueue.size()) + "个元素");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
