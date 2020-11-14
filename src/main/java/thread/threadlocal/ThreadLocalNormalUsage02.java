package thread.threadlocal;

import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 10:38 下午
 * @Description: 使用ThreadLocal,给每个线程分配自己的dateFormat对象,保证了线程安全,高效利用内存
 *
 */
@Component
public class ThreadLocalNormalUsage02 {
    ExecutorService es = Executors.newFixedThreadPool(10);


    public void printFormatTime() {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            es.submit(new Runnable() {
                @Override
                public void run() {
                    String date = date(finalI);
                    System.out.println(date);
                }
            });
        }
        es.shutdown();
    }

    public String date(int seconds) {
        Date date = new Date(1000 * seconds);
        return ThreadSafeFormatter.dateFormatThreadLocal
                .get()
                .format(date);
    }

}


class ThreadSafeFormatter {
//    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal01 = new ThreadLocal<SimpleDateFormat>() {
//        @Override
//        protected SimpleDateFormat initialValue() {
//            return new SimpleDateFormat("yy-MM-dd hh:mm:ss");
//        }
//    };

    // 使用lambda表达式实现
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yy-MM-dd hh:mm:ss"));
}



