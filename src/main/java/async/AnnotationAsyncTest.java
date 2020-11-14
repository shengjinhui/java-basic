package async;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.function.Supplier;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 12:11 下午
 * @Description:
 */
@Component
public class AnnotationAsyncTest {

    @Async
    public CompletableFuture<String> doSomething() throws Exception {
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        CompletableFuture<String> result = new CompletableFuture<>();
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName() + " doSomeThing");
        result.complete("done");

        return result;
    }

    public static void main(String[] args) throws Exception {
        AnnotationAsyncTest annotationAsyncTest = new AnnotationAsyncTest();
        annotationAsyncTest.doSomething();
    }

    class AnnotationAsyncTestProxy {
        private AnnotationAsyncTest asyncTask;
        private TaskExecutor executor = new SimpleAsyncTaskExecutor();

        public AnnotationAsyncTest getAsyncTask() {
            return asyncTask;
        }

        public CompletableFuture<String> doSomethingAsyncFuture() {
            return CompletableFuture.supplyAsync(new Supplier<String>() {
                @Override
                public String get() {
                    try {
                        return asyncTask.doSomething().get();
                    } catch (Throwable e) {
                        throw new CompletionException(e);
                    }
                }
            }, executor);
        }
    }
}
