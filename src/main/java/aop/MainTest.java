package aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: yichuan
 * @Date: 2020/6/8 11:52 上午
 * @Description:
 */
public class MainTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) context.getBean("arithmeticCalculator");
        System.out.println(arithmeticCalculator.getClass());
        int result = arithmeticCalculator.add(3, 5);
        System.out.println("result: " + result);
        result = arithmeticCalculator.div(5, 0);
        System.out.println("result: " + result);
    }
}
