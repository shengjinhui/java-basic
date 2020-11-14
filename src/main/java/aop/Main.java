package aop;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Objects;
import java.util.Optional;

/**
 * @Author: yichuan
 * @Date: 2020/6/8 11:52 上午
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class Main {
    @Autowired
    private ArithmeticCalculator arithmeticCalculator;

    @Test
    public void test() {
        arithmeticCalculator.div(3, 5);
//        result = arithmeticCalculator.div(5, 0);
//        System.out.println("result: " + result);
//        WishListOrderExtra wishListOrderExtra = new WishListOrderExtra();
//        wishListOrderExtra.setExpiredLiveTime(0);
//        String s = JSON.toJSONString(wishListOrderExtra);

    }

}
