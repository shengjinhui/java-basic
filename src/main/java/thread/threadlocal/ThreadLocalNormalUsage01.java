package thread.threadlocal;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 11:14 下午
 * @Description: 典型案例1-ThreadLocal之每个线程都需要独享的对象
 * 使用加锁的方式保证SimpleDateFormat安全性
 */
@Component
public class ThreadLocalNormalUsage01 {
    private static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh-mm-ss");

    public void printFormatTime() {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> {
                System.out.println(date(finalI));
            });
        }
    }

    private synchronized String date(int seconds) {
        Date date = new Date(1000 * seconds);
        return simpleDateFormat.format(date);
    }


}
