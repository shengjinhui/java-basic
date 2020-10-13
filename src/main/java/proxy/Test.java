package proxy;

/**
 * @Author: yichuan
 * @Date: 2020/9/28 10:55 上午
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        Hello bind = (Hello) new HelloProxy().bind(new HelloWorld());
        bind.hello("hello world2");

    }
}
