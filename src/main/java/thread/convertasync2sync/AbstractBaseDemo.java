package thread.convertasync2sync;

/**
 * @Author: yichuan
 * @Date: 2020/9/28 2:56 下午
 * @Description: 异步调动实例类
 */
public abstract class AbstractBaseDemo {
    public AsyncCall asyncCall = new AsyncCall();

    public abstract void callback(long response);

    /**
     * 发起异步调用
     */
    public void call() {
        System.out.println(Thread.currentThread().getName() + "发起调用");
        asyncCall.call(this);
        System.out.println(Thread.currentThread().getName() + "调用返回");
    }
}
