package aop;

import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Component;

/**
 * @Author: yichuan
 * @Date: 2020/6/8 10:58 上午
 * @Description:
 */
@Component("arithmeticCalculator")
@LogTag(appName = "calculator")
public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    @Override
    public int add(int i, int j) {
        return i + j;
    }

    @Override
    public int div(int i, int j) {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i / j;
    }
}
