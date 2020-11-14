package async;

import org.springframework.stereotype.Component;

/**
 * @Author: yichuan
 * @Date: 2020/11/12 10:04 上午
 * @Description: CPU 缓存行对程序运行的影响影响
 * 这里创建了一个二维数组,长1024*1024,宽8
 */
@Component
public class CacheLineEffect {
    // 考虑一般缓存行大小是64字节，一个long类型占8字节
    static long[][] arr;

    static {
        arr = new long[1024 * 1024][];
        for (int i = 0; i < 1024 * 1024; i++) {
            arr[i] = new long[8];
            for (int j = 0; j < 8; j++) {
                arr[i][j] = 0L;
            }
        }
    }


    /**
     * >>>>>>>>>>method:calculateWithCacheLine execute time:0.027s
     */
    public void calculateWithCacheLine() {
        long sum = 0L;
        for (int i = 0; i < 1024 * 1024; i += 1) {
            for (int j = 0; j < 8; j++) {
                sum = arr[i][j];
            }
        }
        System.out.println(sum);
    }

    /**
     *  >>>>>>>>>>method:calculateWithoutCacheLine execute time:0.343s
     */
    public void calculateWithoutCacheLine() {
        long sum = 0L;
        for (int i = 0; i < 8; i += 1) {
            for (int j = 0; j < 1024 * 1024; j++) {
                sum = arr[j][i];
            }
        }

        System.out.println(sum);
    }
}
