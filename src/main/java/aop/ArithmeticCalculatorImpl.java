package aop;

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
        return i / j;
    }
}
