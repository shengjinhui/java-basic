package thread.collection;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: yichuan
 * @Date: 2020/11/13 5:06 下午
 * @Description:
 */
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample {
    private final static String DATA = "DUMMY DATA";
    private List<String> arrayList;
    private List<String> linkedList;

    @Setup(Level.Iteration)
    public void setUp() {
        this.arrayList = new ArrayList<>();
        this.linkedList = new LinkedList<>();
    }

    @Benchmark
    public List<String> arrayListAdd() {
        this.arrayList.add(DATA);
        return arrayList;
    }

    @Benchmark
    public List<String> linkedListAd() {
        this.linkedList.add(DATA);
        return linkedList;
    }

    public static void main(String[] args) throws Exception {
        final Options opts = new OptionsBuilder().include(JMHExample.class.getSimpleName())
                .forks(1)
                .measurementIterations(10)
                .warmupIterations(10)
                .build();
        new Runner(opts).run();
    }
}
