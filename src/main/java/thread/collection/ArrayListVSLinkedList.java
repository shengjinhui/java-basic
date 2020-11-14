package thread.collection;


import com.google.common.base.Stopwatch;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yichuan
 * @Date: 2020/11/13 3:40 下午
 * @Description:
 */
public class ArrayListVSLinkedList {


    public static final int MAX_CAPACITY = 1_000_000;
    private static final String DATA = "DUMMY DATA";

    private static void test(List<String> list) {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            list.add(DATA);
        }
    }


    private static void arrayListPerfTest(int iterations) {
        for (int i = 0; i < iterations; i++) {
            final List<String> list = new ArrayList<>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            test(list);
            System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS
            ));
        }
    }

    private static void linkedListPerfTest(int iterations) {
        for (int i = 0; i < iterations; i++) {
            final List<String> list = new LinkedList<>();
            final Stopwatch stopwatch = Stopwatch.createStarted();
            test(list);
            System.out.println(stopwatch.stop().elapsed(TimeUnit.MILLISECONDS));

        }
    }

    public static void main(String[] args) {
        arrayListPerfTest(MAX_CAPACITY);
        System.out.println(Strings.repeat("#", 100));
        linkedListPerfTest(MAX_CAPACITY);
    }
}

