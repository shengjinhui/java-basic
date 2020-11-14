package async;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: yichuan
 * @Date: 2020/11/11 6:28 下午
 * @Description:
 */
@Component
public class StreamTest {

    public String rpcCall(String ip, String param) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = "ip:" + ip + "param:" + param;
        System.out.println(result);
        return result;
    }

    /**
     * 同步调用rpc方法
     * >>>>>>>>>>method:rpcCallBySync execute time:10.052s<<<<<<<<<<<<
     */
    public void rpcCallBySync() {
        List<String> ipList = new ArrayList<>(10);
        List<String> result = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            ipList.add("192.19.23.31");
        }

        for (int i = 0; i < ipList.size(); i++) {
            result.add(rpcCall(ipList.get(i), String.valueOf(i)));
        }
        result.forEach(System.out::println);
    }

    /**
     * 异步调用rpc方法
     * >>>>>>>>>>method:rpcCallByStreamAndFuture execute time:1.047s<<<<<<<<<<<<
     */
    public void rpcCallByStreamAndFuture() throws Exception {
        List<String> ipList = new ArrayList<>(10);
        List<String> result = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            ipList.add("192.19.23.31");
        }

        List<CompletableFuture<String>> futureList = ipList.stream()
                .map(ip -> CompletableFuture.supplyAsync(() -> rpcCall(ip, ip)))
                .collect(Collectors.toList());
        result = futureList.stream().map(future -> future.join())
                .collect(Collectors.toList());
        result.forEach(System.out::println);
    }

    public void rpcCallByReactiveStream() throws InterruptedException {
        List<String> ipList = new ArrayList<>(10);
        List<String> result = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            ipList.add("192.19.23.31");
        }
        Flowable.fromArray(ipList.toArray(new String[0]))
                .observeOn(Schedulers.io())
                .map(v -> rpcCall(v, v))
                .subscribe(System.out::println);
        System.out.println("main execute over and wait");
        Thread.currentThread().join();
    }
}
