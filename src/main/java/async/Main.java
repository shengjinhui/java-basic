package async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: yichuan
 * @Date: 2020/11/10 8:22 下午
 * @Description:
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class Main {
    @Autowired
    private AsyncByNewThread asyncByNewThread;

    @Autowired
    private CompletableFutureSetTest completableFutureSetTest;

    @Autowired
    private StreamTest streamTest;

    @Autowired
    private CacheLineEffect cacheLineEffect;

    @Autowired
    private AnnotationAsyncTest annotationAsyncTest;

    @Autowired
    private RxJavaTest rxJavaTest;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();
//        asyncByNewThread.executeByThreadPoolAndFuture();
//        completableFutureSetTest.executeTaskByCompletableFuture();
//        completableFutureSetTest.runAsyncByCompleteFuture();
//streamTest.rpcCallBySync();
//        streamTest.rpcCallByStreamAndFuture();
//        cacheLineEffect.calculateWithCacheLine();
//        CompletableFuture<String> future = annotationAsyncTest.doSomething();
//
//        System.out.println(future.get());
//        System.out.println(System.currentTimeMillis() - start);
        rxJavaTest.rpcCall();

    }
}
