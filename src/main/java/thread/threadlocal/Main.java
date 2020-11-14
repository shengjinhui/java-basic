package thread.threadlocal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 11:05 下午
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
public class Main {
    @Autowired
    private ThreadLocalNormalUsage02 normalUsage00;

    @Autowired
    private ThreadLocalNormalUsage01 normalUsage01;
    @Test
    public void test(){
        normalUsage00.printFormatTime();
    }
}
