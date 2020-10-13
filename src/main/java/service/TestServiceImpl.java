package service;

import org.springframework.stereotype.Service;

/**
 * @Author: yichuan
 * @Date: 2020/8/7 1:28 下午
 * @Description:
 */
@Service("testService")
public class TestServiceImpl implements TestService {
    @Override
    public String hello(String name) {
        return "hello " + name;
    }
}
