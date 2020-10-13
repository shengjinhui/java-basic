package proxy;

/**
 * @Author: yichuan
 * @Date: 2020/9/28 10:57 上午
 * @Description:
 */
public class HelloWorld implements Hello {
    @Override
    public void hello(String target) {
        System.out.println("Hello World!");
    }
}
