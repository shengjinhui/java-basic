package thread.threadlocal;

/**
 * @Author: yichuan
 * @Date: 2020/11/14 11:04 上午
 * @Description: ThreadLocal空指针异常代码演示
 */
public class ThreadLocalNPE {
    ThreadLocal<Long> longThreadLocal = new ThreadLocal<Long>();

    public void set() {
        longThreadLocal.set(Thread.currentThread().getId());
    }

    // 重点是这里,本身如果是包装类型,是会返回null字符,并不抛出空指针异常, 而这里因为进行了拆包,则会抛异常
    public long get() {
        return longThreadLocal.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE threadLocalNPE = new ThreadLocalNPE();
        System.out.println(threadLocalNPE.get());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocalNPE.set();
                System.out.println(threadLocalNPE.get());
            }
        });
        thread.start();
    }
}
