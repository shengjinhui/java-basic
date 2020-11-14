package async;

/**
 * @Author: yichuan
 * @Date: 2020/11/11 12:21 上午
 * @Description:
 */
public class RetryDemo {

    public static void main(String[] args) {
        int count = 0;
        retry:
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                count++;
                if (count == 3) {
                    break retry;
                }
                System.out.println(count);
            }
            System.out.println(i);
        }
    }
}
